package datapro.eibs.menu;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ESS0040DSMessage;
import datapro.eibs.sockets.MessageHandler;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.routers.TOSocketMessageRouter;
import datapro.eibs.beans.JBObjList;

public class JSESS0040Bean extends datapro.eibs.master.SuperServlet {
	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSESS0040Bean() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESS0040Bean");

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

		MessageHandler mh = null;
		HttpSession session;
		MessageRecord newmessage = null;

		ESS0040DSMessage msgMenu = null;
		ESS0030DSMessage msgUser = null;
		JBObjList mainMenu = null;
		JBObjList subMenu = null;

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

			msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
			String Language = msgUser.getE01LAN();
			String strPath = super.rootPath + Language + "/";

			try {
				flexLog("Opennig Socket Connection");

				mh =
					new MessageHandler(
						new TOSocketMessageRouter(
							super.hostIP,
							getInitSocket(req),
							super.sckTimeOut),
						"datapro.eibs.beans");

				// Send Request
				flexLog("Sending Request. UserID = " + msgUser.getH01USR());
				try {
					msgMenu =
						(ESS0040DSMessage) mh.getMessageRecord("ESS0040DS");
					msgMenu.setESSUSR(msgUser.getH01USR());
					msgMenu.setESSTYP(Language.toUpperCase());
					msgMenu.send();
					msgMenu.destroy();
				} catch (Exception e) {
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

				// Receiving
				try {
					newmessage = mh.receiveMessage();

					if (newmessage.getFormatName().equals("ESS0040DS")) {
						msgMenu = (ESS0040DSMessage) newmessage;

						flexLog("Receiving data");
						if (msgMenu.getESSTYP().equals("T")) {

							int buttonId = 0;
							flexLog(
								"Data received IDE = " + msgMenu.getESSIDE());
							int buttonNumber =
								Integer.parseInt(msgMenu.getESSIDE());
							flexLog("OK");

							mainMenu = new JBObjList();
							subMenu = new JBObjList();

							while (true) {
								newmessage = mh.receiveMessage();
								msgMenu = (ESS0040DSMessage) newmessage;

								if (msgMenu.getESSTYP().equals("M")) {
									mainMenu.addRow(msgMenu);
								} else if (msgMenu.getESSTYP().equals("C")) {
									subMenu.addRow(msgMenu);
								} else if (msgMenu.getESSTYP().equals("*")) {
									break;
								}
							}

							if (mainMenu.getNoResult()) {
								res.sendRedirect(super.srctx + 
									strPath + "ESS0040_menu_access_denied.jsp");
							} else {
								session.setAttribute("mainMenu", mainMenu);
								session.setAttribute("subMenu", subMenu);
								res.sendRedirect(super.srctx + 
									strPath
										+ "ESS0040_menu_access_authorized.jsp?WEBPATH="
										+ super.webAppPath);
							}

						} else if (msgMenu.getESSTYP().equals("*")) {
							res.sendRedirect(super.srctx + 
								strPath + "ESS0040_menu_access_denied.jsp");
						} else {
							res.sendRedirect(super.srctx + 
								strPath + "ESS0040_menu_access_denied.jsp");
						}
					} else
						flexLog(
							"Message "
								+ newmessage.getFormatName()
								+ " received.");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + strPath + super.sckNotRespondPage);
				}

			} catch (RuntimeException e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + strPath + super.sckNotRespondPage);
			} catch (Exception e) {
				int sck = getInitSocket(req);
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + strPath + super.sckNotOpenPage);
			} finally {
				mh.releaseMessageRouter().close();
			}

		}

	}
}