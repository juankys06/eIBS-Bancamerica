package datapro.eibs.security;

/**
 * Insert the type's description here.
 * Creation date: (7/19/00 6:55:55 PM)
 * @author: Enrique Almonte
 */
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.sql.manager.DBConnectorProperty;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.sockets.MessageContext;

public class JSPassword extends datapro.eibs.master.SuperServlet {

	// CIF options

	static final int R_CHANGE_PASSWORD = 1;
	static final int A_CHANGE_PASSWORD = 10;

	static final String PAGE_CHANGE_PASSWORD = "security_user_change_password.jsp";

	private String LangPath = "S";
	private String Lang = "S";

	private DBConnectorProperty dbProp;

	/**
	 * JSEODPDF constructor comment.
	 */
	public JSPassword() {
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
		dbProp = new DBConnectorProperty();
	}
	/**
	 * This method was created by Orestes Garcia.
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
			try {

				msgUser = (ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				//*------> check value of Lang variable , must be E or S
				Lang = msgUser.getE01LAN().toUpperCase();
				session.setAttribute("userid",msgUser.getH01USR());
				//
				int screen = R_CHANGE_PASSWORD;
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_CHANGE_PASSWORD:
						procReqChangePassword(req, res, session);
						break;
					case A_CHANGE_PASSWORD:
						procActionChangePassword(req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}


	private void procReqChangePassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		try {
			ESS0030DSMessage msgUser = null;
			msgUser = (ESS0030DSMessage) ses.getAttribute("currUser");

			ses.setAttribute("userid",msgUser.getH01USR());
			ses.setAttribute("message","");
			res.sendRedirect(super.srctx + LangPath + PAGE_CHANGE_PASSWORD);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
	}

	private void procActionChangePassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		try {
			ESS0030DSMessage msgUser = null;
			msgUser = (ESS0030DSMessage) ses.getAttribute("currUser");

			String user = msgUser.getH01USR();
			String password = req.getParameter("PASSWORD");
			String message = "This functionality is not yet implemented";
			ses.setAttribute("message",message);
			res.sendRedirect(super.srctx + LangPath + PAGE_CHANGE_PASSWORD);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
	}

}
