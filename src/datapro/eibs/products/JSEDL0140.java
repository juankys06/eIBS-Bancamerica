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

public class JSEDL0140 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL = 5;
	protected static final int A_APPROVAL = 2;
	protected static final int R_APPROVAL_INQ = 3;
	protected static final int R_PASSWORD = 1;
	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSEDL0140() {
		super();
	}

	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEDL0140(int logType) {
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
		EDL014002Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDL014002Message) mc.getMessageRecord("EDL014002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ESS0090");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02DEAACC(req.getParameter("ACCNUM"));
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
					//mod emat 01/24/2002
					//send draft parameter
					String typCode = "";
					flexLog("typCode: " + req.getParameter("typCode"));
					if (req.getParameter("typCode") != null) {
						typCode = req.getParameter("typCode");
					}
					// mod emat 01/24/2002
					//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=" + req.getParameter("appCode"));
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSEDL0140?SCREEN=5&appCode="
							+ req.getParameter("appCode")
							+ "&typCode="
							+ typCode);
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_approval_list.jsp");
						callPage(
							LangPath + "EDL0140_approval_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else if (newmessage.getFormatName().equals("EDL014030")) {
				//Time deposit openning confirm	
				flexLog("Putting java beans into the session");
				msgError = new ELEERRMessage();
				EDL014030Message msgConfirm = (EDL014030Message) newmessage;
				
				
				ses.setAttribute("error", msgError);
				ses.setAttribute("receipt", msgConfirm);
								
				String typCode = "";
				if (req.getParameter("typCode") != null) {
					typCode = req.getParameter("typCode");
				}
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.products.JSEDL0140?SCREEN=5&appCode="
						+ req.getParameter("appCode")
						+ "&typCode="
						+ typCode
						+ "&doPrint=1");
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
		EDL014001Message msgList = null;
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
			msgList = (EDL014001Message) mc.getMessageRecord("EDL014001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESS0090");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setE01DEAACD(req.getParameter("appCode"));
			//mod: emat 02/24/2002
			//to extract drafts
			flexLog("typCode: " + req.getParameter("typCode"));
			if (req.getParameter("typCode") != null
				&& !req.getParameter("typCode").equalsIgnoreCase("null")) {
				msgList.setH01FLGWK1(req.getParameter("typCode").trim());
			}
			//
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

			} else if (newmessage.getFormatName().equals("EDL014001")) {

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
				String accNum = req.getParameter("ACCNUM");
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				if (accNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;

				userPO = new datapro.eibs.beans.UserPos();

				while (true) {

					msgList = (EDL014001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {

							if (msgList.getE01DEAACD().equals("14")) {
								userPO.setHeader10("14");
							} else if (msgList.getE01DEAACD().equals("10")) {
								userPO.setHeader10("10");
							} else if (msgList.getE01DEAACD().equals("13")) {
								userPO.setHeader10("13");								
							} else {
								userPO.setHeader10("CD");
							}

							firstTime = false;
							chk = "checked";
						} else {
							if (msgList.getE01DEAACC().trim().equals(accNum))
								chk = "checked";
							else
								chk = "";
						}

						myRow = new StringBuffer("<TR>");
						// mod EMAT 10/01/2001
						// add ofac status : H01FLGWK3 = '3'
						// mod EMAT 10/25/2001
						// add warning status : H01FLGWK2 = 'A'
						chkOfac = (msgList.getH01FLGWK3().equals("3")? "<a href=\"javascript:showInqOFAC('"	+ msgList.getE01DEAACC()+ "')\"><img src=\"../images/warning_16.jpg\" alt=\"OFAC Match List\" align=\"absmiddle\" border=\"0\" ></a>": "");
						chkWarn = (msgList.getH01FLGWK2().equals("A")? "<a href=\"javascript:showInqWarn('"	+ msgList.getE01DEAACC()+ "')\"><img src=\"../images/warning01.gif\" alt=\"Warnings\" align=\"absmiddle\" border=\"0\" ></a>": "");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\""+ msgList.getE01DEAACC()+ "\" "	+ chk+ " onclick=\"showAddInfo("+ indexRow+ ")\"></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApproval('"+ msgList.getE01DEAACD()	+ "', '"+ msgList.getE01DEAACC()+ "', '"+ msgList.getH01FLGWK1()+ "')\">"+ Util.formatCell(msgList.getE01DEAACC())+ "</A>"
								+ chkOfac
								+ chkWarn
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApproval('"
								+ msgList.getE01DEAACD()
								+ "', '"
								+ msgList.getE01DEAACC()
								+ "', '"
								+ msgList.getH01FLGWK1()
								+ "')\">"
								+ Util.formatCell(msgList.getE01DEACUN())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:showInqApproval('"
								+ msgList.getE01DEAACD()
								+ "', '"
								+ msgList.getE01DEAACC()
								+ "', '"
								+ msgList.getH01FLGWK1()
								+ "')\">"
								+ Util.formatCell(msgList.getE01CUSNA1())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApproval('"
								+ msgList.getE01DEAACD()
								+ "', '"
								+ msgList.getE01DEAACC()
								+ "', '"
								+ msgList.getH01FLGWK1()
								+ "')\">"
								+ Util.formatCell(msgList.getE01DEAPRO())
								+ "</A>");
						myRow.append(
							"<INPUT TYPE=HIDDEN NAME=\"STSOFAC"
								+ indexRow
								+ "\" VALUE=\""
								+ msgList.getH01FLGWK3()
								+ "\">");
						myRow.append(
							"<INPUT TYPE=HIDDEN NAME=\"STSWARN"
								+ indexRow
								+ "\" VALUE=\""
								+ msgList.getH01FLGWK2()
								+ "\">");

						myRow.append(
							"<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01DEARMK())
								+ "<br>");
						myRow.append(
							Util.fcolorCCY(msgList.getE01DEAPRI()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEACCY()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEABNK()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEABRN()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEAGLN()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEACCN()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEAUBT()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01DEAUSR()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01FLGBUS()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01FLGIBF()) + "<br>");
						myRow.append(
							Util.formatCell(msgList.getE01FLGTYP())
								+ "\"></TD>");
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
				if (req.getParameter("appCode").equalsIgnoreCase("CD"))
					userPO.setOption("CD");
				else if (req.getParameter("appCode").equalsIgnoreCase("13"))
					userPO.setOption("13");
				else
					userPO.setOption("LN");
				
				if (req.getParameter("doPrint") != null) {
					userPO.setRedirect("DO_PRINT");
				}
				//mod emat 01/24/2002
				//draft title
				flexLog(
					"setting title to Drafts : " + req.getParameter("typCode"));
				if (req.getParameter("typCode").equals("G")) {
					userPO.setOption("DF");
				}
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
								+ "EDL0140_approval_list.jsp");
						callPage(
							LangPath + "EDL0140_approval_list.jsp",
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
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEDL0140?SCREEN="
					+ R_APPROVAL
					+ "&appCode="
					+ req.getParameter("appCode")
					+ "&typCode="
					+ req.getParameter("typCode")
					+ (req.getParameter("ACCNUM") == null
						? ""
						: "&ACCNUM=" + req.getParameter("ACCNUM")));
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}