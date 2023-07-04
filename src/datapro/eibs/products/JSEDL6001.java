package datapro.eibs.products;

/**
 * Option to test the Loan Simulator EDL6001.
 * @author: Creation: jparrado 
 * @author: Modifications: csepulveda
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDL600101Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL6001 extends datapro.eibs.master.SuperServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int R_ENTER_MAINT = 100;
	protected static final int A_ENTER_MAINT = 200;

	protected String LangPath = "S";

	/**
	 * Constructor comment.
	 */
	public JSEDL6001() {
		super();
	}
	/**
	 * This method was created by AUTHOR
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL6001");

	}
	/**
	 * This method was created by AUTHOR
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */

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

			int screen = R_ENTER_MAINT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Open Socket Connection");
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
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						// Action
						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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

	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL600101Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLN = (EDL600101Message) mc.getMessageRecord("EDL600101");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL6001");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
			msgLN.setH01OPECOD("0002");
			try {
				msgLN.setE01CDBANK(req.getParameter("E01CDBANK"));
			} catch (Exception e) {}
			try {
				msgLN.setE01ENTCDE(req.getParameter("E01ENTCDE"));
			} catch (Exception e) {}
			try {
				msgLN.setE01DEAPRO(req.getParameter("E01DEAPRO"));
			} catch (Exception e) {}
			try {
				msgLN.setE01DEACUN(req.getParameter("E01DEACUN"));
			} catch (Exception e) {}
			try {
				msgLN.setE01FNACO1(req.getParameter("E01FNACO1"));
			} catch (Exception e) {}
			try {
				msgLN.setE01FNACO2(req.getParameter("E01FNACO2"));
			} catch (Exception e) {}
			try {
				msgLN.setE01FNACO3(req.getParameter("E01FNACO3"));
			} catch (Exception e) {}
			try {
				msgLN.setE01EDADTI(req.getParameter("E01EDADTI"));
			} catch (Exception e) {}
			try {
				msgLN.setE01NTITUL(req.getParameter("E01NTITUL"));
			} catch (Exception e) {}
			try {
				msgLN.setE01MONPRE(req.getParameter("E01MONPRE"));
			} catch (Exception e) {}
			try {
				msgLN.setE01TASAPR(req.getParameter("E01TASAPR"));
			} catch (Exception e) {}
			try {
				msgLN.setE01NCUOTA(req.getParameter("E01NCUOTA"));
			} catch (Exception e) {}
			try {
				msgLN.setE01PLAZOP(req.getParameter("E01PLAZOP"));
			} catch (Exception e) {}
			try {
				msgLN.setE01NOPAGO(req.getParameter("E01NOPAGO"));
			} catch (Exception e) {}
			try {
				msgLN.setE01PPAGO1(req.getParameter("E01PPAGO1"));
			} catch (Exception e) {}
			try {
				msgLN.setE01PPAGO2(req.getParameter("E01PPAGO2"));
			} catch (Exception e) {}
			try {
				msgLN.setE01PPAGO3(req.getParameter("E01PPAGO3"));
			} catch (Exception e) {}
			try {
				msgLN.setE01CLISEX(req.getParameter("E01CLISEX"));
			} catch (Exception e) {}




			msgLN.send();
			msgLN.destroy();
			flexLog("EDL600101 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL600101")) {
				try {
					msgLN = new EDL600101Message();
					flexLog("EDL600101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL600101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnBasic", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL6001_sim_values.jsp");
						callPage(LangPath + "EDL6001_sim_values.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL6001_sim_enter.jsp");
						callPage(
							LangPath + "EDL6001_sim_enter.jsp",
							req,
							res);
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


	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EDL600101Message msgLN = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgLN = new EDL600101Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("lnBasic", msgLN);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page maint : "
					+ LangPath
					+ "EDL6001_sim_enter.jsp");
			callPage(LangPath + "EDL6001_sim_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}
