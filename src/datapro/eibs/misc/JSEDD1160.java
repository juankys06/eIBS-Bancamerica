package datapro.eibs.misc;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD1160 extends datapro.eibs.master.SuperServlet {

	
	static final int R_LIST  	= 1;
	static final int A_LIST   	= 2;
	static final int R_I_LIST 	= 3;
	
	static final int A_MAINT  	= 200;
	
	static final int R_ENTER_NEW  	= 1000;
	
	static final int R_NEW 	 	= 100;
	static final int R_MAINT  	= 300;
	static final int R_DELETE 	= 500;
	static final int R_INQUIRY 	= 700;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD1160() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	private synchronized void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD116001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EDD116001Message) mc.getMessageRecord("EDD116001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD116001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
					
			msgList.send();
			msgList.destroy();
			flexLog("EDD116001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);


			} 
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			  newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EDD116001")) {

					beanList = new JBList();

					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (EDD116001Message) newmessage;

						marker = msgList.getE01ACCOPE();
						
						if (firstTime) {
							firstTime = false;
							chk = "checked";
							}
					    else {
						  	chk = "";
						}						
					
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" value=\""
								+ msgList.getE01ACCREF()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE01ACCREF() 
							+ "','" 
							+ msgList.getE01ACMATY()
							+ "','" 
							+ msgList.getE01ACMUC3() 
							+ "','" 
							+ msgList.getE01ACMBRN()
							+ "','" 
							+ msgList.getE01CANRSN() 							
							+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01ACMATY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01ACMUC3() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01CANRSN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01ACMBRN() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=RIGHT width=\"20%\">" + msgList.getE01PAGOGL() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PAGOCN() + "</td>");
							myRow.append("</TR>");
														
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EDD1160Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDD1160_rt_sel_close.jsp");
						callPage(LangPath + "EDD1160_rt_sel_close.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	private synchronized void procActionPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));
		//Maintenance
		String CONVM = req.getParameter("CONVM");
		String PRODUCT = req.getParameter("PRODUCT");
		String TYPCONV = req.getParameter("TYPCONV");
		String BRANCH = req.getParameter("BRANCH");	
		String REASON = req.getParameter("REASON");

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.misc.JSEDD1160?SCREEN=100");		    
				break;
			case 2 : //Maintenance
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.misc.JSEDD1160?SCREEN=300"  + "&CONVM=" + CONVM + "&PRODUCT=" + PRODUCT + "&TYPCONV=" + TYPCONV
												+ "&BRANCH=" + BRANCH + "&REASON=" + REASON);		    
				break;
			default :
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.misc.JSEDD1160?SCREEN=100");		    
		}
	}


	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD116001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD116001Message) ses.getAttribute("close");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD116001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD116001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD116001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD116001Message();
					flexLog("EDD116001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD116001Message) newmessage;
							
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("close", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + 
										"/servlet/datapro.eibs.misc.JSEDD1160?SCREEN=1");					

				} else { // There are errors
						try {
					    	flexLog("About to call Page: " + LangPath + "EDD1160_rt_details_close.jsp");
							callPage(LangPath + "EDD1160_rt_details_close.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD116001Message msgDoc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
				ses.setAttribute("close", msgDoc);
	
					  try {
						   flexLog("About to call Page: " + LangPath + "EDD1160_rt_details_close.jsp");
						   callPage(LangPath + "EDD1160_rt_details_close.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }
	
	}

	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD116001Message msgDoc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDoc = (EDD116001Message) mc.getMessageRecord("EDD116001");
			msgDoc.setH01USERID(user.getH01USR());
			msgDoc.setH01PROGRM("EDD116001");
			msgDoc.setH01TIMSYS(getTimeStamp());
			msgDoc.setH01SCRCOD("01");
			msgDoc.setH01OPECOD("0002");
			
			try{
				msgDoc.setE01ACCREF(req.getParameter("CONVM"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE01ACMATY(req.getParameter("PRODUCT"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE01ACMUC3(req.getParameter("TYPCONV"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE01ACMBRN(req.getParameter("BRANCH"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE01CANRSN(req.getParameter("REASON"));
			} catch (Exception e) {
			}	


			msgDoc.send();
			msgDoc.destroy();
			flexLog("EDD116001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD116001")) {
				try {
					msgDoc = new EDD116001Message();
					flexLog("EDD116001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDoc = (EDD116001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("close", msgDoc);

				if (IsNotError) { // There are no errors
					  try {
						   flexLog("About to call Page: " + LangPath + "EDD1160_rt_details_close.jsp");
						   callPage(LangPath + "EDD1160_rt_details_close.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }
				} else { // There are errors
					try {
								   flexLog("About to call Page: " + LangPath + "EDD1160_rt_sel_close.jsp");
								   callPage(LangPath + "EDD1160_rt_sel_close.jsp", req, res);
					  } catch (Exception e) {
							   flexLog("Exception calling page " + e);
					  }
				}	  
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_LIST;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Requests
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					
					case R_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;	
						// Actions
					case A_LIST :
						procActionPos(mc, msgUser, req, res, session);
						break;
					case A_MAINT :
						procActionMaint(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
			   } catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				}
				finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	private synchronized void showERROR(ELEERRMessage m) {
		if (logType != NONE) {

			flexLog("ERROR received.");

			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());

		}
	}
}