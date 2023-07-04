package datapro.eibs.misc;

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

public class JSARTA000C extends datapro.eibs.master.SuperServlet {

	static final int R_ERROR = 1;

	static final int A_ENTER = 200;
	static final int R_ENTER = 300;

	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSARTA000C() {
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
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ARTA20001Message msgArta = null;
		ELEERRMessage msgError = null;
		JBListRec artList = null;
		UserPos userPO = null;
		String sAcc = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgArta = (ARTA20001Message) mc.getMessageRecord("ARTA20001");
			msgArta.setH01USERID(user.getH01USR());
			msgArta.setH01PROGRM("ARTA20001");
			msgArta.setH01TIMSYS(getTimeStamp());
			msgArta.setH01SCRCOD("01");
			msgArta.setH01OPECOD("0005");

			try {
				sAcc = "" + req.getParameter("E01DEAACC");
				msgArta.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}

			//msgRT.send();
			mc.sendMessage(msgArta);
			msgArta.destroy();
			flexLog("ARTA20001 Message Sent");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procMaintData(mc,
				user,
				req,
				res,
				ses,
				sAcc)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ARTA0010_errors_list.jsp");
					callPage(LangPath + "ARTA0010_errors_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ARTA0010_enter_error.jsp");
					callPage(LangPath + "ARTA0010_enter_error.jsp", req, res);
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

	protected boolean procMaintData(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		String sAcc)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ARTA20001Message msgArta = null;
		JBListRec artList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Initializing java beans into the session");

		msgError = new datapro.eibs.beans.ELEERRMessage();

		int colNum = 4;
		artList = new datapro.eibs.beans.JBListRec();
		artList.init(colNum);
		userPO.setIdentifier(sAcc);

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("arta", artList);

		// Receive Error Message
		try {
			// Receive Data
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ARTA20001")) {

				flexLog("ARTA20001 Message Received");
				//int colNum = 4;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgArta = (ARTA20001Message) newmessage;
					
					marker = msgArta.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						//Quote List
						myRow[0] = msgArta.getE01MSSNUM().trim(); // 
						myRow[1] = msgArta.getE01DESCR().trim(); // 
						myRow[2] = msgArta.getE01FIELD().trim(); // 
						myRow[3] = msgArta.getE01TRNCOD().trim(); // 
						artList.addRow(myFlag, myRow);
					}
					newmessage = mc.receiveMessage();
				}
				ses.setAttribute("arta", artList);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		return IsNotError;	
	}

	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("EX");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "ARTA0010_enter_error.jsp");
			callPage(LangPath + "ARTA0010_enter_error.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
						// Requests
						case R_ENTER :
							procReqEnter(mc, msgUser, req, res, session);
							break;

							// Actions
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	private synchronized void showERROR(ELEERRMessage m) {
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