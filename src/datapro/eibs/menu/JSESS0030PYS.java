package datapro.eibs.menu;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
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

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSESS0030PYS extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "";

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			session = (HttpSession) req.getSession(true);
			boolean succeed = authenticateUser(req, res, session);
			if (! succeed) 
				return;
		}

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

				msgUser = (ESS0030DSMessage) session.getValue("currUser");
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				String URL = req.getParameter("URL").replace('@', '?');
				flexLog("URL: " + URL);
				res.sendRedirect(super.srctx + URL);

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected boolean authenticateUser(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		MessageRecord newmessage = null;

		try {
			flexLog("Opennig Socket Connection");
			s = new Socket(super.hostIP, getInitSocket(req));
			s.setSoTimeout(super.sckTimeOut);
			mc =
				new MessageContext(
					new DataInputStream(new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
					"datapro.eibs.beans");
		} catch (Exception e) {
			e.printStackTrace();
			int sck = getInitSocket(req);
			flexLog("Socket not Open(Port " + sck + "). Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
			return IsNotError;
		}

		String Language = req.getParameter("Language").toLowerCase();

		userPO = new UserPos();
		msgUser = new ESS0030DSMessage();

		// Send Initial data
		try {
			msgUser = (ESS0030DSMessage) mc.getMessageRecord("ESS0030DS");
			msgUser.setH01USR(req.getParameter("UserId").toUpperCase());
			msgUser.setE01PSW(req.getParameter("Password").toUpperCase());
			msgUser.setH01PGM("ESS0030");
			msgUser.setH01TIM("20000119");
			msgUser.setH01SCR("01");
			msgUser.setH01OPE("0004");
			msgUser.setE01LAN(Language.toUpperCase());
			try {
				msgUser.setH01WK1(req.getParameter("SOURCE"));
			} catch (Exception e) {
				msgUser.setH01WK1("");
			}
			msgUser.send();
			msgUser.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return IsNotError;
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return IsNotError;
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESS0030DS")) {
				msgUser = (ESS0030DSMessage) newmessage;
				// Set the language to the user selection
				msgUser.setE01LAN(Language);
				ses.putValue("currUser", msgUser);
				ses.putValue("error", msgError);
				ses.putValue("userPO", userPO);

				if (!IsNotError) {

					try {
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						res.sendRedirect(super.srctx + LangPath + "error_viewer.jsp");
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
		try {
			s.close();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		return IsNotError;
	}

}