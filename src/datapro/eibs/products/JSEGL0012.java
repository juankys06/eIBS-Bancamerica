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

public class JSEGL0012 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL = 5;
	protected static final int A_APPROVAL = 2;
	protected static final int R_APPROVAL_INQ = 3;

	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSEGL0012() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEGL0012(int logType) {
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
		EGL001202Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EGL001202Message) mc.getMessageRecord("EGL001202");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EGL0012");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02BTHNUM(req.getParameter("BTHNUM"));
			msgList.setE02ACTION(req.getParameter("action"));
			msgList.setE02MSGTXT(req.getParameter("reason"));
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
						"/servlet/datapro.eibs.products.JSEGL0012?SCREEN=5&appCode="
							+ req.getParameter("appCode"));
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0012_approval_list.jsp");
						callPage(
							LangPath + "EGL0012_approval_list.jsp",
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
		EGL001201Message msgList = null;
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
			msgList = (EGL001201Message) mc.getMessageRecord("EGL001201");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EGL0012");
			msgList.setH01TIMSYS(getTimeStamp());
			//msgList.setH01SCRCOD(req.getParameter("appCode"));
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

			} else if (newmessage.getFormatName().equals("EGL001201")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String bthNum = req.getParameter("BTHNUM");

				if (bthNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;
				while (true) {

					msgList = (EGL001201Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							if (msgList.getE01BTHNUM().trim().equals(bthNum))
								chk = "checked";
							else
								chk = "";
						}

						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"BTHNUM\" value=\""
								+ msgList.getE01BTHNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo("
								+ indexRow
								+ ")\"></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01BTHNUM())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01ORGBNK())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01ORGBRN())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatDate(
									msgList.getE01RUNDT1(),
									msgList.getE01RUNDT2(),
									msgList.getE01RUNDT3())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01USROPE())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01STATUS())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCCY(msgList.getE01TOTDEB())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCCY(msgList.getE01TOTCRE())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01BTHNUM()
								+ "', '"
								+ msgList.getE01ORGBNK()
								+ "', '"
								+ msgList.getE01ORGBRN()
								+ "')\">"
								+ Util.formatCell(msgList.getE01TOTTRN())
								+ "</A></TD>");
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

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setPurpose("APPROVAL");
				//if (req.getParameter("appCode").equalsIgnoreCase("CD"))
				//	userPO.setOption("CD");
				//else
				//	userPO.setOption("LN");

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
								+ "EGL0012_approval_list.jsp");
						callPage(
							LangPath + "EGL0012_approval_list.jsp",
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

		UserPos userPO = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			int appCode = Integer.parseInt(req.getParameter("appCode"));
			String bthNum = req.getParameter("BTHNUM");
			String typeCode = req.getParameter("typeCode");

			datapro.eibs.products.JOActionRedirect red =
				new datapro.eibs.products.JOActionRedirect(
					typeCode,
					ACC_APPROVAL_INQ,
					appCode,
					bthNum,
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
					mc = new MessageContext(getMessageHandler("EGL0012", req));

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
					flexLog("Socket not Open. Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					if(mc != null) mc.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	protected static final int R_PASSWORD = 1;

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

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEGL0012?SCREEN="
					+ R_APPROVAL
					+ "&appCode="
					+ req.getParameter("appCode"));
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}