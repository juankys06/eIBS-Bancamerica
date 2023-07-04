package datapro.eibs.params;

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

public class JSEDL0510 extends datapro.eibs.master.SuperServlet {

	
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
	public JSEDL0510() {
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL051001Message msgList = null;
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
			msgList = (EDL051001Message) mc.getMessageRecord("EDL051001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL051001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			try { 
				msgList.setE01DLSBNK(req.getParameter("E01DLSBNK").toUpperCase());
			} catch (Exception e) {
				
			}
			try { 
				msgList.setE01DLSTYP(req.getParameter("E01DLSTYP").toUpperCase());
			} catch (Exception e) {
				
			}

			msgList.send();
			msgList.destroy();
			flexLog("EDL051001 Message Sent");
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
								
				if (newmessage.getFormatName().equals("EDL051001")) {

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

						msgList = (EDL051001Message) newmessage;

						marker = msgList.getE01CDROPE();
						
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
								+ msgList.getE01DLSTLN()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE01DLSBNK() 
							+ "','" 
							+ msgList.getE01DLSTYP()
							+ "','" 
							+ msgList.getE01DLSTLN() 
							+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01DLSTLN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE01DLSDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getE01DLSTYP() + "</td>");
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
					ses.setAttribute("EDL0510Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDL0510_ln_sel_tables.jsp");
						callPage(LangPath + "EDL0510_ln_sel_tables.jsp", req, res);
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

	protected void procActionPos(
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
		//New
		String ACCTYPE = req.getParameter("ACCTYPE");
		String DESCRIPTION = req.getParameter("DESCRIPTION");
		String TABLE = req.getParameter("TABLE");		
		String CUSTOMER = req.getParameter("CUSTOMER");
		String PRODUCT = req.getParameter("PRODUCT");
		//Maintenance
		String TABLEN = req.getParameter("TABLEN");
		String BANK = req.getParameter("BANK");
		String ACD = req.getParameter("ACD");

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION
					+ "&ACCTYPE=" + ACCTYPE + "&CUSTOMER=" + CUSTOMER +  "&PRODUCT=" + PRODUCT);		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=300" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD);
				break;
				
			case 3 : //Delete
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=500" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD);
				break;
				
			case 4 : //Inquiry
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=700" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD);
				break;
			
			default :
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION
				+ "&ACCTYPE=" + ACCTYPE + "&CUSTOMER=" + CUSTOMER +  "&PRODUCT=" + PRODUCT);		    
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
		EDL051002Message msgRT = null;
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
			msgRT = (EDL051002Message) ses.getAttribute("lnParam");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDL051002");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD("0005");

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
			flexLog("EDL051002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL051002")) {
				try {
					msgRT = new datapro.eibs.beans.EDL051002Message();
					flexLog("EDL051002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDL051002Message) newmessage;
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnParam", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
						try {

							res.sendRedirect(super.srctx + 
												"/servlet/datapro.eibs.params.JSEDL0510?SCREEN=1000" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
							}
				

				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EDL0510_ln_table_maint.jsp");
							callPage(LangPath + "EDL0510_ln_table_maint.jsp", req, res);
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

	protected void procReqEnterNew(
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

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

					try {
						flexLog("About to call Page: " + LangPath + "EDL0510_ln_enter_sel_tables.jsp");
						callPage(LangPath + "EDL0510_ln_enter_sel_tables.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
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
		EDL051002Message msgDoc = null;
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
			flexLog("document sent");
			msgDoc = (EDL051002Message) mc.getMessageRecord("EDL051002");
			
			
			try{
				msgDoc.setE02DLSTLN(req.getParameter("TABLE"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02DLSBNK(user.getE01UBK());
			} catch (Exception e) {
			}	


			try{
				msgDoc.setE02DLSCUN(req.getParameter("CUSTOMER"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02DLSTYP(req.getParameter("PRODUCT"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02DLSDSC(req.getParameter("DESCRIPTION"));
			} catch (Exception e) {
			}	


			flexLog("document assigned");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
				ses.setAttribute("lnParam", msgDoc);
			
			try {
				flexLog("About to call Page: " + LangPath + "EDL0510_ln_table_maint.jsp");
				callPage(LangPath + "EDL0510_ln_table_maint.jsp", req, res);
				} 
			catch (Exception e) {
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
		EDL051002Message msgDoc = null;
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
			msgDoc = (EDL051002Message) mc.getMessageRecord("EDL051002");
			msgDoc.setH02USERID(user.getH01USR());
			msgDoc.setH02PROGRM("EDL051002");
			msgDoc.setH02TIMSYS(getTimeStamp());
			msgDoc.setH02SCRCOD("01");
			msgDoc.setH02OPECOD("0002");
			
			try{
				msgDoc.setE02DLSTLN(req.getParameter("TABLEN"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02DLSBNK(req.getParameter("BANK"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02DLSTYP(req.getParameter("ACD"));
			} catch (Exception e) {
			}	
			

			msgDoc.send();
			msgDoc.destroy();
			flexLog("EDL051002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL051002")) {
				try {
					msgDoc = new EDL051002Message();
					flexLog("EDL051002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDoc = (EDL051002Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnParam", msgDoc);

				if (IsNotError) { // There are no errors
					
					try {
						flexLog("About to call Page: " + LangPath + "EDL0510_ln_table_maint.jsp");
						callPage(LangPath + "EDL0510_ln_table_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					try {
								   flexLog("About to call Page: " + LangPath + "EDL0510_ln_sel_tables.jsp");
								   callPage(LangPath + "EDL0510_ln_sel_tables.jsp", req, res);
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
					case R_ENTER_NEW :
						procReqEnterNew(mc, msgUser, req, res, session);
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
	protected void showERROR(ELEERRMessage m) {
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