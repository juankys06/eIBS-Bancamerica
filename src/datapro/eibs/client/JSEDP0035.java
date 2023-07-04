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

public class JSEDP0035 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_TRANSACTION = 1;
	protected static final int A_TRANSACTION = 2;

	// entering options
	protected static final int R_FORMAT = 100;
	protected static final int A_FORMAT = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0035() {
		super();
	}
	/**
	 * This method was created in VisualAge.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0035");

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
	protected void procReqFormat(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP003501Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDP003501Message) mc.getMessageRecord("EDP003501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0035");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0015");
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
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDP003501")) {

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
				String fmtCod = req.getParameter("FMTCOD");

				if (fmtCod == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;
				while (true) {

					msgList = (EDP003501Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						//beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							if (msgList.getE01IFFFMT().trim().equals(fmtCod))
								chk = "checked";
							else
								chk = "";
						}
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"FMTCOD\" value=\""
								+ msgList.getE01IFFFMT()
								+ "\" "
								+ chk
								+ " onclick=\"setFormatInfo("
								+ indexRow
								+ ")\"></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showAccountChart("
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgList.getE01IFFFMT())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP><A HREF=\"javascript:showAccountChart("
								+ indexRow
								+ ")\">"
								+ Util.formatCell(msgList.getE01IFFDSC())
								+ "</A></TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
						indexRow++;
						//if (marker.equals("+")) {
						//	beanList.setShowNext(true);
						//	break;
						//}
					}
					newmessage = mc.receiveMessage();
				}

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setOption("FMT_COD");
				userPO.setPurpose("MAINTENANCE");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("fmtList", beanList);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0035_format_list.jsp");
					callPage(LangPath + "EDP0035_format_list.jsp", req, res);
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
	protected void procActionFormat(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;

		EDP003501Message msgList = null;
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
			msgList = (EDP003501Message) mc.getMessageRecord("EDP003501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0035");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD(opeCode);
			try {
				msgList.setE01IFFFMT(req.getParameter("E01IFFFMT"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01IFFDSC(req.getParameter("E01IFFDSC"));
			} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("EDP003501")) {

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.client.JSEDP0035?SCREEN=100");
				} else { // There are errors
					msgList = (EDP003501Message) newmessage;
					String fmtCod = "";
					String fmtDsc = "";
					try {
						fmtCod = msgList.getE01IFFFMT();
					} catch (Exception e) {
						fmtCod = "";
					}
					try {
						fmtDsc = msgList.getE01IFFDSC();
					} catch (Exception e) {
						fmtDsc = "";
					}
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0035_format_list.jsp?OPT="
								+ opt
								+ "&FMTCOD="
								+ fmtCod
								+ "&FMTDSC="
								+ fmtDsc);
						res.sendRedirect(super.srctx + 
							LangPath
								+ "EDP0035_format_list.jsp?ROW="
								+ req.getParameter("ROW")
								+ "&FMTCOD="
								+ fmtCod
								+ "&FMTDSC="
								+ fmtDsc);
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

			int screen = R_FORMAT;

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
						case R_FORMAT :
							procReqFormat(mc, msgUser, req, res, session);
							break;
							// Action 
						case A_FORMAT :
							procActionFormat(mc, msgUser, req, res, session);
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