package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (3/19/02 10:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import java.util.Calendar;

import datapro.eibs.sockets.*;

public class JSEDP0001 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_TRANSACTION = 3;
	protected static final int A_TRANSACTION = 4;

	// entering options
	protected static final int R_ACC_PLAN = 100;
	protected static final int A_ACC_PLAN = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0001() {
		super();
	}
	/**
	 * This method was created in VisualAge.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0001");

	}
	/**
	 * This method was created in VisualAge.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionPlan(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP000101Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String opeCode = "";
		String flagAccept = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int opt;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			opt = 0;
		}

		switch (opt) {
			case 1 :
				{
					opeCode = "0001";
					break;
				}
			case 2 :
				{
					opeCode = "0002";
					break;
				}
			case 3 :
				{
					opeCode = "0009";
					break;
				}
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDP000101Message) mc.getMessageRecord("EDP000101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD(opeCode);
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
			msgList.send();
			msgList.destroy();
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

			if (newmessage.getFormatName().equals("EDP000101")) {

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.client.JSEDP0001?SCREEN=100&FMTCOD="
							+ userPO.getHeader1()
							+ "&FMTDSC="
							+ userPO.getHeader2());
				} else { // There are errors
					msgList = (EDP000101Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("plan", msgList);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0001_acc_plan.jsp?ROW="
								+ req.getParameter("ROW"));
						res.sendRedirect(super.srctx + 
							LangPath
								+ "EDP0001_acc_plan.jsp?ROW="
								+ req.getParameter("ROW"));
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
	protected void procReqAccPlan(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP000101Message msgPlan = null;
		JBList planList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		// Send Initial data
		try {
			msgPlan = (EDP000101Message) mc.getMessageRecord("EDP000101");
			msgPlan.setH01USERID(user.getH01USR());
			msgPlan.setH01PROGRM("EDP0001");
			msgPlan.setH01TIMSYS(getTimeStamp());
			msgPlan.setH01SCRCOD("01");
			msgPlan.setH01OPECOD("0015");

			// Get Parameters here	
			try {
				msgPlan.setE01DPGTIT(req.getParameter("FMTCOD"));
			} catch (Exception e) {
			}

			msgPlan.send();
			msgPlan.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		int colNum = 10;

		flexLog("Initializing java beans into the session");
		try {
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setHeader1(req.getParameter("FMTCOD"));
			userPO.setHeader2(req.getParameter("FMTDSC"));
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0035_format_list.jsp");
					callPage(LangPath + "EDP0035_format_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDP000101")) {

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				boolean firstTime = true;
				;
				String chk = "";

				try {
					planList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				int indexRow = 0;
				while (true) {

					msgPlan = (EDP000101Message) newmessage;

					marker = msgPlan.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							chk = "";
						}
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"PLANCOD\" value=\""
								+ msgPlan.getE01DPGTIT()
								+ "\" "
								+ chk
								+ " onclick=\"setPlanInfo("
								+ indexRow
								+ ")\"></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCTA())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCOD())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGDSC())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGOPE())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCF1())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCF2())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCF3())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCF4())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:radioClick('PLANCOD',"
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgPlan.getE01DPGCF5())
								+ "</A></TD>");
						myRow.append("</TR>");

						planList.addRow(myFlag, myRow.toString());
						indexRow++;
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("planList", planList);
				ses.setAttribute("plan", msgPlan);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0001_acc_plan.jsp");
					callPage(LangPath + "EDP0001_acc_plan.jsp", req, res);
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

			int screen = R_ACC_PLAN;

			try {

				flexLog("Screen  Number: " + screen);

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
						// Request
						case R_ACC_PLAN :
							procReqAccPlan(mc, msgUser, req, res, session);
							break;
							// Action 
						case A_ACC_PLAN :
							procActionPlan(mc, msgUser, req, res, session);
							break;

							// END Entering

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
}