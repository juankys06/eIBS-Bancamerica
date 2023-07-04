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

public class JSEDD0439 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_OD = 1;
	protected static final int A_OD = 2;

	protected static final int R_ENTER_OD = 100;
	protected static final int A_ENTER_OD = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0439() {
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
	protected void procActionEnterOD(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043901Message msgList = null;
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
			msgList = (EDD043901Message) mc.getMessageRecord("EDD043901");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD043901");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");
			try {
				msgList.setE01CLSACC(req.getParameter("E01CLSACC"));
			} catch (Exception e) {
				msgList.setE01CLSACC(userPO.getIdentifier());
			}
			msgList.send();
			msgList.destroy();
			flexLog("EDD043901 Message Sent");
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
				ses.setAttribute("accList", beanList);
				ses.setAttribute("userPO", userPO);

				//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDD0439?SCREEN=100");
			}

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD043901")) {

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
				String custNum = req.getParameter("cust");
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				firstTime = true;

				int indexRow = 0;
				while (true) {

					msgList = (EDD043901Message) newmessage;

					marker = msgList.getH01FLGMAS();
					userPO.setHeader4(msgList.getH01FLGWK1());

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						userPO.setHeader10(msgList.getE01CLSPR1());
						userPO.setHeader11(msgList.getE01CLSAM1());
						userPO.setHeader12(msgList.getE01CLSPR2());
						userPO.setHeader13(msgList.getE01CLSAM2());
						userPO.setHeader14(msgList.getE01CLSTOT());
						userPO.setHeader20(msgList.getE01CLSPRT());
						userPO.setHeader23(msgList.getE01CLSTIV());

						break;
					} else {
						if (firstTime) {
							firstTime = false;
							userPO.setHeader15(msgList.getE01CLSAMT());
							userPO.setHeader16(msgList.getE01CLSNBL());
							userPO.setHeader17(msgList.getE01CLSUNC());
							userPO.setHeader18(msgList.getE01CLSAVG());
							userPO.setHeader19(msgList.getE01CLSNME());
							userPO.setHeader21(msgList.getE01CLSACC());
							userPO.setHeader22(msgList.getE01CLSCCY());

						} else {
							chk = "";
						}

						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgList.getE01INVACC()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getE01INVAMT())
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgList.getE01INVTRM()
								+ "-"
								+ msgList.getE01INVTRC()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ Util.formatDate(
									msgList.getE01INVMA1(),
									msgList.getE01INVMA2(),
									msgList.getE01INVMA3())
								+ "</td>");
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
				ses.setAttribute("accList", beanList);
				ses.setAttribute("userPO", userPO);

				if (userPO.getPurpose().equals("INQUIRY")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0439_od_inq_protec.jsp");
						callPage(
							LangPath + "EDD0439_od_inq_protec.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0439_od_protec.jsp");
						callPage(LangPath + "EDD0439_od_protec.jsp", req, res);
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

	protected void procReqEnterOD(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EDL013001Message msgCD = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("OD");
			userPO.setPurpose("MAINTENANCE");
			msgCD = new datapro.eibs.beans.EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0439_rt_enter_od.jsp");
			callPage(LangPath + "EDD0439_rt_enter_od.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionOD(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043901Message msgRT = null;
		ELEERRMessage msgError = null;
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
			msgRT = (EDD043901Message) mc.getMessageRecord("EDD043901");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");

			try {
				msgRT.setE01CLSACC(req.getParameter("E01CLSACC"));
			} catch (Exception e) {
				msgRT.setE01CLSACC("0");
			}

			try {
				msgRT.setE01CLSPRT(req.getParameter("E01CLSPRT"));
			} catch (Exception e) {
				msgRT.setE01CLSPRT("0");
			}

			msgRT.send();
			msgRT.destroy();

			flexLog("EDD043901 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD043901")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043901Message();
					flexLog("EDD043901 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043901Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0439_od_confirm.jsp");
						callPage(LangPath + "EDD0439_od_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0439_od_protec.jsp");
						callPage(LangPath + "EDD0439_od_protec.jsp", req, res);
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

			int screen = R_OD;

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

						case R_ENTER_OD :
							procReqEnterOD(msgUser, req, res, session);
							break;
							// Actions
						case A_OD :
							procActionOD(mc, msgUser, req, res, session);
							break;
						case A_ENTER_OD :
							procActionEnterOD(mc, msgUser, req, res, session);
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