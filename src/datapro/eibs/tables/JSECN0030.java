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

public class JSECN0030 extends datapro.eibs.master.SuperServlet {

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
	public JSECN0030() {
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
		ECN0030DSMessage msgCDRenRat = null;
		ECN0030DSMessage msgApyRate = null;
		
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

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
					"currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				flexLog("Opening Socket Connection");
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
						msgError = new ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				userPO =
					(datapro.eibs.beans.UserPos) session.getAttribute("userPO");

				String opCode = "0004";

				String ccy = req.getParameter("CCY");
				String table = req.getParameter("TABLE");

				// Send Initial data
				try {
					msgCDRenRat =
						(ECN0030DSMessage) mc.getMessageRecord("ECN0030DS");
					msgCDRenRat.setH02USERID(msgUser.getH01USR());
					msgCDRenRat.setH02PROGRM("ESD0711");
					msgCDRenRat.setH02TIMSYS(getTimeStamp());
					msgCDRenRat.setH02SCRCOD("01");
					msgCDRenRat.setH02OPECOD(opCode);
					msgCDRenRat.setE02CDRCCY(ccy);
					msgCDRenRat.setE02CDRRTB(table);
					msgCDRenRat.send();
					msgCDRenRat.destroy();

					flexLog("ECN0030DS Sent");
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
						session.setAttribute("error", msgError);

						try {
							flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
							callPage(LangPath + "error_viewer.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
												
					} else if (newmessage.getFormatName().equals("ECN0030DS")) {						
						try {
							msgApyRate = new ECN0030DSMessage();
							flexLog("ECN0030DS Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}
						
						msgCDRenRat = (ECN0030DSMessage) newmessage;
					
						msgApyRate = (ECN0030DSMessage) newmessage;
							
						flexLog("Putting java beans into the session");
						session.setAttribute("error", msgError);
						session.setAttribute("cdRenRat", msgCDRenRat);
						session.setAttribute("cdAPY", msgApyRate);
	
						try {
							flexLog("About to call Page: " + LangPath + "ECN0030_cd_inq_ren_rate.jsp");
							callPage(LangPath + "ECN0030_cd_inq_ren_rate.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else
						flexLog("Message "	+ newmessage.getFormatName() + " received.");

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
				//return;
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