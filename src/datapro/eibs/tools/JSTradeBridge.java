package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;
import java.net.*;

public class JSTradeBridge extends datapro.eibs.master.SuperServlet {

	protected static final int R_DIRECT = 1;
	protected static final int R_SEARCH = 3;
	protected static final int A_SEARCH = 4;
	protected static final int A_CALL   = 5;
	
	protected String LangPath = "S";

	/**
	 * constructor comment.
	 */
	public JSTradeBridge() {
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

			int screen = R_DIRECT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_DIRECT :
						procReqDirect(msgUser, req, res, session, "", "");
						break;					
					default :
						res.sendRedirect(super.srctx +LangPath + super.devPage);
						break;
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
			}

		}

	}

	/**
	 * 
	 */
	protected void procReqDirect(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		String acc,
		String prodCode)
		throws ServletException, IOException {

		UserPos userPO = (UserPos) ses.getAttribute("userPO");
		try {
			String exePath = req.getParameter("EXEPATH").toLowerCase();
			//String prog = user.getE01DSK() + exePath;
			String prog = exePath;
						
			String param = req.getParameter("EXEPARAM").replace(',',' ');
						
			ses.setAttribute("prog", prog);
			ses.setAttribute("param", param);
	
			try {
				flexLog("About to call Page: " + LangPath + "GENERIC_trade_bridge.jsp");
				callPage(LangPath + "GENERIC_trade_bridge.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
	
		} catch (Exception e) {
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

}