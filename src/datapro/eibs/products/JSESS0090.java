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

public class JSESS0090 extends datapro.eibs.master.SuperServlet {

	protected static final int R_VIEW_MSG = 1;
	protected static final int A_VIEW_MSG = 2;
	protected static final int R_CHECK_MSG = 3;

	// Pages
	protected static final String CHECK_MSG_PAGE =
		"ESS0090_message_checker.jsp";
	protected static final String MSG_LIST_PAGE = "ESS0090_message_list.jsp";

	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSESS0090() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSESS0090(int logType) {
		super(logType);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionFixed(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESS009003Message msgFixed = null;

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgFixed = (ESS009003Message) mc.getMessageRecord("ESS009003");
			msgFixed.setH03USERID(user.getH01USR());
			msgFixed.setH03PROGRM("ESS0090");
			msgFixed.setH03TIMSYS(getTimeStamp());

			String acc = req.getParameter("ACCNUM");
			String cusNum = Util.leftValue(acc);
			String lnNum = Util.rightValue(acc);

			msgFixed.setE03MSGACC(acc);
			
			try {
				msgFixed.setE03MSGCUN(cusNum);
				msgFixed.setE03MSGNUM(lnNum);
			}
			catch (Exception e) {}

			msgFixed.setE03MSGTXT(req.getParameter("reason"));
			msgFixed.setE03ACTION("F");
			msgFixed.send();
			msgFixed.destroy();

			flexLog("ESS009003 Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionGoto(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		datapro.eibs.products.JOActionRedirect red = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		char msgCode = req.getParameter("msgCode").charAt(0);
		int appCode = Integer.parseInt(req.getParameter("appCode"));

		String accNum = req.getParameter("ACCNUM");
		if (appCode == 90) {
			String cusNum = Util.leftValue(accNum);
			String lnNum = Util.rightValue(accNum);
			red =
				new datapro.eibs.products.JOActionRedirect(
					lnNum,
					appCode,
					cusNum,
					LangPath,
					ses);
		} else {
			red =
				new datapro.eibs.products.JOActionRedirect(
					appCode,
					accNum,
					LangPath,
					ses);
		}

		switch (msgCode) {
			case 'A' : // Account Approval
				red.setOption(ACC_APPROVAL);
				res.sendRedirect(super.srctx + red.action());
				break;
			case 'E' : // Account Maintenance
				red.setOption(ACC_MAINTENANCE);
				res.sendRedirect(super.srctx + red.action());
				break;
			default :
				res.sendRedirect(super.srctx + LangPath + super.devPage);
		}

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procActionViewMsg(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		int option = -1;

		try {
			option = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			switch (option) {
				case 1 : // Goto Action
					procActionGoto(mc, user, req, res, ses);
					break;
				case 2 : // Set it Fixed
					procActionFixed(mc, user, req, res, ses);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqCheckMsg(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS009001Message msgCheck = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (userPO == null) userPO = new datapro.eibs.beans.UserPos();
		// Send Initial data
		try {
			msgCheck = (ESS009001Message) mc.getMessageRecord("ESS009001");
			msgCheck.setH01USERID(user.getH01USR());
			msgCheck.send();
			msgCheck.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESS009001")) {

				msgCheck = (ESS009001Message) newmessage;

				if (msgCheck.getE01MSGFLG().equalsIgnoreCase("Y"))
					userPO.setThereIsMsg(true);
				else
					userPO.setThereIsMsg(false);

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + CHECK_MSG_PAGE);
					callPage(LangPath + CHECK_MSG_PAGE, req, res);
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
	protected void procReqViewMsg(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESS009002Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ESS009002Message) mc.getMessageRecord("ESS009002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ESS0090");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.send();
			msgList.destroy();

			flexLog("ESS009002 Sent");
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
				// showERROR(msgError);

				res.setContentType("text/html");
				printClose(res.getWriter());

			} else if (newmessage.getFormatName().equals("ESS009002")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
					flexLog("ESS009002 Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String acc = "";
				int rowCounter = 0;

				while (true) {

					msgList = (ESS009002Message) newmessage;

					marker = msgList.getH02FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							// chk = "checked";
						} else {
							chk = "";
						}

						myFlag = msgList.getE02MSGTXT();
						myRow = new StringBuffer("<TR>");

						if (msgList.getE02MSGACD().equals("90")){
						myRow.append(
						"<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\""
								+ msgList.getE02MSGCUN() 
								+ "_"
								+ msgList.getE02MSGNUM()
								+ "\" "
								+ chk
								+ " onClick=\"setMsg('"
								+ rowCounter
								+ "','"
								+ msgList.getE02MSGACD()
								+ "','"
								+ msgList.getE02MSGCOD().trim()
								+ "')\"></TD>");
						}else{
							myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\""
									+ msgList.getE02MSGACC() 
									+ "\" "
									+ chk
									+ " onClick=\"setMsg('"
									+ rowCounter
									+ "','"
									+ msgList.getE02MSGACD()
									+ "','"
									+ msgList.getE02MSGCOD().trim()
									+ "')\"></TD>");
						}

						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\">"
								+ Util.formatCell(msgList.getE02MSGACD())
								+ "</TD>");
						if (msgList.getE02MSGACD().equals("90"))
							acc =
								msgList.getE02MSGCUN()
									+ "_"
									+ msgList.getE02MSGNUM();
						else
							acc = msgList.getE02MSGACC();
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\">"
								+ Util.formatCell(acc)
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\">"
								+ Util.formatCell(msgList.getE02MSUSSM())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatDate(
									msgList.getE02MSGID1(),
									msgList.getE02MSGID2(),
									msgList.getE02MSGID3())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>"
								+ Util.formatTime(msgList.getE02MSGITM())
								+ "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
						++rowCounter;

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);

				try {
					flexLog("About to call Page: " + LangPath + MSG_LIST_PAGE);
					callPage(LangPath + MSG_LIST_PAGE, req, res);
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

			int screen = R_CHECK_MSG;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					mc =
						new MessageContext(super.getMessageHandler("ESS0090", req));

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_CHECK_MSG :
							procReqCheckMsg(mc, msgUser, req, res, session);
							break;
						case R_VIEW_MSG :
							procReqViewMsg(mc, msgUser, req, res, session);
							break;
						case A_VIEW_MSG :
							procActionViewMsg(mc, msgUser, req, res, session);
							break;

						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error On MessageContext : " + mc.toString());
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				} finally {
					mc.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
}