package datapro.eibs.products;

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

public class JSEATM010A extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_LIST  	= 1;
	protected static final int R_MAINT  	= 20;

	protected static final int A_APPROVAL = 2;
	protected static final int R_PASSWORD = 100;
	
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEATM010A() {
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
		EATM01002Message msgList = null;
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
			msgList = (EATM01002Message) mc.getMessageRecord("EATM01002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EATM01002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0016");
			

			
			msgList.send();
			msgList.destroy();
			flexLog("EATM01002 Message Sent");
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
								
				if (newmessage.getFormatName().equals("EATM01002")) {

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

						msgList = (EATM01002Message) newmessage;

						marker = msgList.getH02FLGMAS();
						
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
							
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"CARDNUM\" value=\""
								+ msgList.getE02ATMPAN()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\"><A HREF=\"javascript:showApprovalCard('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\">" + msgList.getE02ATMPAN() +  "</A>" + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT width=\"10%\"><A HREF=\"javascript:showApprovalCard('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\">" + msgList.getE02ATMCUN() + "</A>" + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\"><A HREF=\"javascript:showApprovalCard('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\">" + msgList.getE02ATMNAM() + "</A>" + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\"><A HREF=\"javascript:showApprovalCard('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\">" + Util.formatDate(msgList.getE02ATMODM(),msgList.getE02ATMODD(),msgList.getE02ATMODY()) + "</A>" + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><A HREF=\"javascript:showInqApproval('"
							+ msgList.getE02ATMCUN() 
							+ "','" 
							+ msgList.getE02ATMPAN()
							+ "')\">" + msgList.getE02ATMEXM()  + " - "+ msgList.getE02ATMEXY() + "</A>" + "</td>");
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
					ses.setAttribute("EATM01002Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EATM010A_approval_list.jsp");
						callPage(LangPath + "EATM010A_approval_list.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EATM01003Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EATM01003Message) mc.getMessageRecord("EATM01003");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ESS0090");
			msgList.setH03TIMSYS(getTimeStamp());
			try{			
				msgList.setE03ATMCUN(req.getParameter("E01ATMCUN"));
			} catch (Exception e) {
			}
			try{
				msgList.setE03ATMPAN(req.getParameter("E01ATMPAN"));
			} catch (Exception e) {
			}
			msgList.setH03FLGWK1(req.getParameter("action"));
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
	
				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
	
				if (IsNotError) { // There is no error
					
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSEATM010A?SCREEN=100");
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EATM010A_approval_list.jsp");
						callPage(
							LangPath + "EATM010A_approval_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
		EATM01001Message msgDoc = null;
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
			msgDoc = (EATM01001Message) mc.getMessageRecord("EATM01001");
			msgDoc.setH01USERID(user.getH01USR());
			msgDoc.setH01PROGRM("EDI010102");
			msgDoc.setH01TIMSYS(getTimeStamp());
			msgDoc.setH01SCRCOD("01");
			msgDoc.setH01OPECOD("0004");
			
			try{
				msgDoc.setE01ATMCUN(req.getParameter("E01ATMCUN"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE01ATMPAN(req.getParameter("E01ATMPAN"));
			} catch (Exception e) {
			}	

			msgDoc.send();
			msgDoc.destroy();
			flexLog("EATM01001 Message Sent");
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

			if (newmessage.getFormatName().equals("EATM01001")) {
				try {
					msgDoc = new EATM01001Message();
					flexLog("ESD020502 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDoc = (EATM01001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("atmDetail", msgDoc);

				if (IsNotError) { // There are no errors
						try {
										flexLog("About to call Page: " + LangPath + "EATM010A_cust_card_approval.jsp");
										callPage(LangPath + "EATM010A_cust_card_approval.jsp", req, res);
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
				} else { // There are errors
					try {
								   flexLog("About to call Page: " + LangPath + "EATM010A_cust_card_approval.jsp");
								   callPage(LangPath + "EATM010A_cust_card_approval.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		UserPos userPO = null;
	
		try {
			//mod emat 01/24/2002
			//send draft parameter
			String typCode = "";
			flexLog("typCode: " + req.getParameter("typCode"));
			if (req.getParameter("typCode") != null) {
				typCode = req.getParameter("typCode");
			}
	
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEATM010A?SCREEN=1");
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
	
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

					case R_PASSWORD :
						procReqPassword(req, res, session);
						break;
					
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
						
					case R_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;	
						// Actions
					case A_APPROVAL :
						procActionApproval(mc, msgUser, req, res, session);
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