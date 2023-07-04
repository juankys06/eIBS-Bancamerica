package datapro.eibs.reports;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.*;

public class JSERPT000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD = 1;
	protected static final int R_REPORT = 2;
	
	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSERPT000() {
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

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		DataCheckReject dataCR = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			dataCR =
				(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

			ses.setAttribute("error", msgError);
			ses.setAttribute("dataCR", dataCR);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0924_rejection_chk_search.jsp");
			callPage(LangPath + "EDD0924_rejection_chk_search.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}


	protected void procReqReport(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EREPORTSTDMessage msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String ReportName = "";
		DataCheckReject dataCR = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		dataCR = (datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

		try {
			ReportName = dataCR.getOption();
		} catch (Exception e) {}
					
		// Send Initial data
		try {
			msgCC = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM(ReportName);
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0000");
			
			try {
				msgCC.setE01REPORT(ReportName);
			} catch (Exception e) {}
			
		
			msgCC.send();
			msgCC.destroy();
			flexLog("EREPORTSTD Message Sent");
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
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EREPORTSTD")) {
				try {
					msgCC = new EREPORTSTDMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (EREPORTSTDMessage) newmessage;

				try {
					flexLog("About to call Page: " + LangPath + "EREPORTSTD_message_confirm.jsp");
					callPage(LangPath + "EREPORTSTD_message_confirm.jsp", req, res);
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
	 * This method was created in VisualAge.
	 */
	protected void procReqPassword(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		DataCheckReject dataCR = null;
		JBListRec beanList = null;

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			dataCR = new datapro.eibs.beans.DataCheckReject();
			beanList = new datapro.eibs.beans.JBListRec();
			//beanList.init(colNum);
			
			dataCR.setOption(req.getParameter("opt"));
			dataCR.setOfficer("");
			ses.setAttribute("chkList", beanList);
			userPO.setRedirect("/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=" + R_REPORT);

			dataCR.setBank(user.getE01UBK());
			dataCR.setCurrency(user.getE01BCU());
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("dataCR", dataCR);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	
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

			int screen = R_REPORT;

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
						// Requests
						case R_PASSWORD :
							procReqPassword(msgUser, req, res, session);
							break;

						case R_REPORT :
							procReqReport(mc, msgUser, req, res, session);
							break;
														
							// Actions
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
	

}