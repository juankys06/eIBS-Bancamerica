package datapro.eibs.misc;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD1170 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL = 5;
	protected static final int A_APPROVAL = 2;
	protected static final int R_APPROVAL_INQ = 3;
	protected static final int R_PASSWORD = 1;
	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSEDD1170() {
		super();
	}

	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEDD1170(int logType) {
		super(logType);

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
		EDD117002Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDD117002Message) mc.getMessageRecord("EDD117002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ESS0090");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02ACMREF(req.getParameter("CONVM"));
			msgList.setE02ACTION(req.getParameter("action"));
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
					//mod emat 01/24/2002
					//send draft parameter
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.misc.JSEDD1170?SCREEN=1");
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1170_approval_list.jsp");
						callPage(
							LangPath + "EDD1170_approval_list.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD117001Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDD117001Message) mc.getMessageRecord("EDD117001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESS0090");
			msgList.setH01TIMSYS(getTimeStamp());

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

				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDD117001")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				int indexRow = 0;

				userPO = new datapro.eibs.beans.UserPos();

				while (true) {

					msgList = (EDD117001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
								chk = "";
						}


						myRow = new StringBuffer("<TR>");
							
							
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" value=\""
							+ msgList.getE01ACMREF()
							+ "\" "
							+ chk
						+ " onclick=\"getParams('"
						+ msgList.getE01ACMREF() 
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 							
						+ "')\"></TD>");
						
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 							
						+ "')\">" + msgList.getE01ACMATY() + "</A>" + "</td>");								
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"30%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 													
						+ "')\">" + msgList.getE01ACMUC3() + "</A>" + "</td>");
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"30%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 							
						+ "')\">" + msgList.getE01CANRSN() + "</A>" + "</td>");								
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 								
						+ "')\">" + msgList.getE01ACMBRN() + "</A>" + "</td>");
						myRow.append("<TD NOWRAP  ALIGN=RIGHT width=\"20%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 							
						+ "')\">" + msgList.getE01PAGOGL() + "</A>" + "</td>");
						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><A HREF=\"javascript:showInqApprovalRT('"+ msgList.getE01ACMREF()
						+ "','" 
						+ msgList.getE01ACMATY()
						+ "','" 
						+ msgList.getE01ACMUC3() 
						+ "','" 
						+ msgList.getE01ACMBRN()
						+ "','" 
						+ msgList.getE01CANRSN() 							
						+ "')\">" + msgList.getE01PAGOCN() + "</A>" + "</td>");
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

				userPO.setPurpose("APPROVAL");
				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("appList", beanList);
				ses.setAttribute("error", msgError);

				if (beanList.getNoResult()) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "MISC_no_result.jsp");
						res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1170_approval_list.jsp");
						callPage(
							LangPath + "EDD1170_approval_list.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqApprovalInq(
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

				
					  try {
						   flexLog("About to call Page: " + LangPath + "EDD1160_ap_rt_details_close.jsp");
						   callPage(LangPath + "EDD1160_ap_rt_details_close.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
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
	 * service method comment.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = R_APPROVAL;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_PASSWORD :
							procReqPassword(req, res, session);
							break;
						case R_APPROVAL :
							procReqApproval(mc, msgUser, req, res, session);
							break;
						case A_APPROVAL :
							procActionApproval(mc, msgUser, req, res, session);
							break;
						case R_APPROVAL_INQ :
							procReqApprovalInq(mc, msgUser, req, res, session);
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
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

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
			userPO.setRedirect(	"/servlet/datapro.eibs.misc.JSEDD1170?SCREEN=" + R_APPROVAL);
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}