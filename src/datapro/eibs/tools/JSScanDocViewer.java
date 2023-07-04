package datapro.eibs.tools;

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

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.sockets.MessageContext;

// import datapro.eibs.generic.JODBConn;
// import datapro.eibs.generic.JOSQLExec;

public class JSScanDocViewer extends JSEDI0010 {

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSScanDocViewer() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
	}
	/**
	 * This method was created by Orestes Garcia.
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

			session = (HttpSession) req.getSession(true);

		} else {

			int screen = R_INQ_DOCS;

			try {

				msgUser = new ESS0030DSMessage();
				String lan = req.getParameter("Lang");
				if (lan == null || lan.equals(""))
					msgUser.setE01LAN("e");
				else
					msgUser.setE01LAN(lan);
					
				String usr = req.getParameter("UserID");
				if (usr == null || usr.equals(""))
					msgUser.setH01USR("R04DATAPRO");
				else
					msgUser.setH01USR(usr);
				
				session.setAttribute("currUser", msgUser);

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_INQ_DOCS :
						procReqInqDocList(msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + getLangPath(msgUser) + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + getLangPath(msgUser) + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + getLangPath(msgUser) + super.sckNotRespondPage);
			}
		}

	}
}