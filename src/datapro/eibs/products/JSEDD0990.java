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

public class JSEDD0990 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_PRBOOKS = 1;
	protected static final int A_PRBOOKS = 2;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0990() {
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
	 * This method was created in VisualAge.
	 */
	protected void procReqPRBooks(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD0990DSMessage msgList = null;
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

		int type = 0;
		String num = "";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDD0990DSMessage) mc.getMessageRecord("EDD0990DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD0990");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setE01PBTACC(userPO.getIdentifier());

			msgList.send();
			msgList.destroy();
			flexLog("EDD004302 Message Sent");
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
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0000_rt_enter_inquiry.jsp");
					callPage(
						LangPath + "EDD0000_rt_enter_inquiry.jsp",
						req,
						res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDD0990DS")) {
				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				java.math.BigDecimal totalDebit = new java.math.BigDecimal(0);
				java.math.BigDecimal totalCredit = new java.math.BigDecimal(0);
				String credit = "";
				String debit = "";

				while (true) {

					msgList = (EDD0990DSMessage) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							userPO.setHeader4(
								Util.fcolorCCY(msgList.getE01BEGBAL()));
						}

						myFlag = msgList.getE01PBTDCC().trim();
						if (myFlag.equals("5")) {
							totalCredit =
								totalCredit.add(
									msgList.getBigDecimalE01PBTAMT());
							credit = Util.fcolorCCY(msgList.getE01PBTAMT());
							debit = Util.formatCell("");
						} else if (myFlag.equals("0")) {
							totalDebit =
								totalDebit.add(
									msgList.getBigDecimalE01PBTAMT());
							credit = Util.formatCell("");
							debit = Util.fcolorCCY(msgList.getE01PBTAMT());
						}
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatDate(
									msgList.getE01PBTDT1(),
									msgList.getE01PBTDT2(),
									msgList.getE01PBTDT3())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatCell(msgList.getE01PBTCDE())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatCell(msgList.getE01PBTNAR())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>" + debit + "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>" + credit + "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.fcolorCCY(msgList.getE01ENDBAL())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatCell(msgList.getE01PBTRLR())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatCell(msgList.getE01PBTREF())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP>"
								+ Util.formatTime(msgList.getE01PBTTIM())
								+ "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());

					}

					newmessage = mc.receiveMessage();
				}

				userPO.setHeader5(Util.fcolorCCY(totalDebit.toString()));
				userPO.setHeader6(Util.fcolorCCY(totalCredit.toString()));

				flexLog("Putting java beans into the session");
				ses.setAttribute("prBooks", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0990_print_books_inq.jsp");
					callPage(
						LangPath + "EDD0990_print_books_inq.jsp",
						req,
						res);

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

			int screen = R_ENTER;

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
						case R_PRBOOKS :
							procReqPRBooks(mc, msgUser, req, res, session);
							break;
						case A_PRBOOKS :
							break;
							//entering options
						case R_ENTER :
							break;
						case A_ENTER :
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
}