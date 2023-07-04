package datapro.eibs.tables;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.sockets.*;

public class JSESD0713 extends datapro.eibs.master.SuperServlet {

	protected static final int R_P_AUDIO 	= 2;
	protected static final int R_P_VIDEO 	= 4;
	protected static final int R_P_HTML 	= 6;
	protected static final int R_P_INQUIRY 	= 8;
	protected static final int R_P_NEW 		= 10;

	protected static final int R_P_SHOW 	= 100;

	protected String LangPath = "S";

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSESD0713() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSCD0030");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		MessageRecord newmessage = null;
		HttpSession session = null;

		ESS0030DSMessage msgUser = null;
		ESD0713DSMessage msgDDAServCharge = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		boolean IsNotError = false;

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

			try {
				msgError = new ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				flexLog("Opening Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");

				userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");

				String opCode = "0004";

				String bank = req.getParameter("BANK");
				String type = req.getParameter("TYPE");
				String table = req.getParameter("TABLE");

				// Send Initial data
				try {
					msgDDAServCharge = (ESD0713DSMessage) mc.getMessageRecord("ESD0713DS");
					msgDDAServCharge.setH01USERID(msgUser.getH01USR());
					msgDDAServCharge.setH01PROGRM("ESD0713");
					msgDDAServCharge.setH01TIMSYS(getTimeStamp());
					msgDDAServCharge.setH01SCRCOD("01");
					msgDDAServCharge.setH01OPECOD(opCode);
					msgDDAServCharge.setE01SELBNK(bank);
					msgDDAServCharge.setE01SELTLN(table);
					msgDDAServCharge.setE01SELTYP(type);
					msgDDAServCharge.send();
					msgDDAServCharge.destroy();

					flexLog("ESD0713DS Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
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
							flexLog("About to call Page: " + LangPath + "ESD0713_ln_inq_serv_charge.jsp");
							callPage(LangPath + "ESD0713_ln_inq_serv_charge.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else
						if (newmessage.getFormatName().equals("ESD0713DS")) {
							try {
								msgDDAServCharge = new ESD0713DSMessage();
								flexLog("ESD0713DS Received");
							} catch (Exception ex) {
								flexLog("Error: " + ex);
							}

							msgDDAServCharge = (ESD0713DSMessage) newmessage;
							// showESD0713DS(msgDDAServCharge);

							flexLog("Putting java beans into the session");
							session.setAttribute("error", msgError);
							session.setAttribute("lnServCharge", msgDDAServCharge);
							try {
								flexLog("About to call Page: " + LangPath + "ESD0713_ln_inq_serv_charge.jsp");
								callPage(LangPath + "ESD0713_ln_inq_serv_charge.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}

						} else
							flexLog("Message " + newmessage.getFormatName() + " received.");

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				}

			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				return;
			} finally {
				s.close();
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