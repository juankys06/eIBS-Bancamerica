package datapro.eibs.products;

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

public class JSESD0850 extends datapro.eibs.master.SuperServlet {

	protected static final int R_OFFICAL_INQ = 1;
	//protected static final int A_APPROVAL				= 2;
	//protected static final int R_APPROVAL_INQ			= 3;

	protected String LangPath = "S";
	protected String Lang = "S";
	/**
	 * JSReportManager constructor comment.
	 */
	public JSESD0850() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSESD0850(int logType) {
		super(logType);
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqOfficial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESD085001Message msgOfc = null;
		UserPos userPO = null;
		String cusnum = "";

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			if (!userPO.getCusNum().equals(""))
				cusnum = userPO.getCusNum();
			else if (!userPO.getHeader2().equals(""))
				cusnum = userPO.getHeader2();
			else
				cusnum = "";
		} catch (Exception ex) {
			cusnum = "";
			flexLog("Error: " + ex);
		}
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgOfc = (ESD085001Message) mc.getMessageRecord("ESD085001");
			msgOfc.setH01USERID(user.getH01USR());
			msgOfc.setH01PROGRM("ESD0850");
			msgOfc.setH01TIMSYS(getTimeStamp());
			msgOfc.setE01CUSCUN(cusnum);
			msgOfc.send();
			msgOfc.destroy();
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
					if (userPO.getOption().equals("CD")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_inq_basic.jsp");
						callPage(
							LangPath + "EDL0130_cd_inq_basic.jsp",
							req,
							res);
					} else if (userPO.getOption().equals("LN")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0160_ln_inq_basic.jsp");
						callPage(
							LangPath + "EDL0160_ln_inq_basic.jsp",
							req,
							res);
					} else if (
						userPO.getOption().equals("RT")
							|| userPO.getOption().equals("SV")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_inq_basic.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_basic.jsp",
							req,
							res);
					} else if (
						userPO.getOption().equals("CL_TYPE")
							|| userPO.getOption().equals("CL_CUSTOMER")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ELN0060_cl_inq_basic.jsp");
						callPage(
							LangPath + "ELN0060_cl_inq_basic.jsp",
							req,
							res);
					} else if (userPO.getOption().equals("DV")) {
						if (userPO.getHeader23().equals("CDOC")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDC0100_coll_doc_inq_basic.jsp");
							callPage(
								LangPath + "EDC0100_coll_doc_inq_basic.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDC0100_coll_simp_inq_basic.jsp");
							callPage(
								LangPath + "EDC0100_coll_simp_inq_basic.jsp",
								req,
								res);
						}
					} else if (userPO.getOption().equals("LC")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ELC0450_lc_inq_basic.jsp");
						callPage(
							LangPath + "ELC0450_lc_inq_basic.jsp",
							req,
							res);
					} else if (userPO.getOption().equals("CLIENT_C")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0080_client_inq_corp_basic.jsp");
						callPage(
							LangPath + "ESD0080_client_inq_corp_basic.jsp",
							req,
							res);
					} else if (userPO.getOption().equals("CLIENT_P")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0080_client_inq_personal_basic.jsp");
						callPage(
							LangPath + "ESD0080_client_inq_personal_basic.jsp",
							req,
							res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ESD085001")) {

				msgOfc = (ESD085001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("official", msgOfc);
				//ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0850_official_inq.jsp");
					callPage(LangPath + "ESD0850_official_inq.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
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

		UserPos userPO = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			int appCode = Integer.parseInt(req.getParameter("appCode"));
			String accNum = req.getParameter("ACCNUM");
			String typeCode = req.getParameter("typeCode");

			datapro.eibs.products.JOActionRedirect red =
				new datapro.eibs.products.JOActionRedirect(
					typeCode,
					ACC_APPROVAL_INQ,
					appCode,
					accNum,
					LangPath,
					ses);
			res.sendRedirect(super.srctx + red.action());
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

			int screen = R_OFFICAL_INQ;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				Lang = msgUser.getE01LAN();
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
						case R_OFFICAL_INQ :
							procReqOfficial(mc, msgUser, req, res, session);
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

}