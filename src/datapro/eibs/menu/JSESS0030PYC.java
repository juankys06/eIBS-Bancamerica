package datapro.eibs.menu;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.SuperServlet;

public class JSESS0030PYC extends datapro.eibs.master.SuperServlet {

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session;
		ESS0030DSMessage msgUser = null;

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

			msgUser = (ESS0030DSMessage) session.getValue("currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";
			String pyURL = JSEIBSProp.getPYURL().trim();

			try {
				if (pyURL.equals("")) {
					res.sendRedirect(super.srctx + LangPath + super.devPage);
				} else {
					String url = req.getParameter("URL").replace('?', '@');
					res.sendRedirect(super.srctx + 
						pyURL
							+ "/servlet/datapro.eibs.menu.JSESS0030PYS?URL="
							+ url
							+ "&UserId="
							+ msgUser.getH01USR()
							+ "&Password="
							+ msgUser.getE01PSW()
							+ "&Language=" 
							+ Language);
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
			}

		}

	}

}