package datapro.eibs.helps.test;

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
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;

public class LoadTester extends datapro.eibs.master.SuperServlet {

	/**
	 * Constructor for LoadTester
	 */
	public LoadTester() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;
		
		String LangPath = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			session = (HttpSession) req.getSession(true);
		}

			try {

				msgUser = new ESS0030DSMessage();
				msgUser.setE01LAN("s");
				msgUser.setH01USR(req.getParameter("USERID"));
				session.putValue("currUser", msgUser);

				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				res.sendRedirect(super.srctx + req.getParameter("REQ"));

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}


	}

}
