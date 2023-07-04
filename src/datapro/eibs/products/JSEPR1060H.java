package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSEPR1060H extends datapro.eibs.master.SuperServlet {

	protected static final int A_MAINTENANCE = 4;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEPR1060H() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0130");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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

			int screen = R_SEARCH;

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
						// BEGIN PR
						// Request
						case R_SEARCH :
							procReqSearch(mc, msgUser, req, res, session);
							break;
						case R_INQUIRY_MAINT :
							procReqInqMaint(mc, msgUser, req, res, session);
							break;
							// Action
						case A_SEARCH :
							procActionSearch(mc, msgUser, req, res, session);
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

	protected static final int A_SEARCH = 2;
	protected static final int R_INQUIRY_MAINT = 3;
	// certificate of deposit 
	protected static final int R_SEARCH = 1;

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = new EPR106001Message();
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EPR1060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");

			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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

			//msgList.send();
			mc.sendMessage(msgList);
			msgList.destroy();
			flexLog("EPR106001 Message Sent");
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
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EPR106001")) {

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
				String priNum = req.getParameter("PRINUM");

				if (priNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;
				while (true) {

					msgList = (EPR106001Message) newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							if (msgList.getE01WRTNUM().trim().equals(priNum))
								chk = "checked";
							else
								chk = "";
						}

						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTSTS())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTBNF())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTBBK())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:enter('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatDate(
									msgList.getE01WRTTD1(),
									msgList.getE01WRTTD2(),
									msgList.getE01WRTTD3())
								+ "</A>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
						indexRow++;
					}
					newmessage = mc.receiveMessage();
				}

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setPurpose("INQUIRY");
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
								+ "EPR1060H_pr_inq_list.jsp");
						callPage(
							LangPath + "EPR1060H_pr_inq_list.jsp",
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
	protected void procReqInqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR010001Message msgPR = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try {
			msgPR = (EPR010001Message) mc.getMessageRecord("EPR010001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EPR0000");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD(opCode);
			msgPR.setE01PRINUM(req.getParameter("REFNUM"));
			msgPR.send();
			msgPR.destroy();
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

			if (newmessage.getFormatName().equals("EPR010001")) {
				try {
					msgPR =new datapro.eibs.beans.EPR010001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EPR010001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prMant", msgPR);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EPR0000_pr_inq_maint.jsp");
						callPage(
							LangPath + "EPR0000_pr_inq_maint.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors

					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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
	protected void procReqSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = new UserPos();
		userPO.setPurpose("INQUIRY");

		msgError = new ELEERRMessage();

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog(
				"About to call Page3: "
					+ LangPath
					+ "EPR1060H_pr_inq_search.jsp");
			callPage(LangPath + "EPR1060H_pr_inq_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}