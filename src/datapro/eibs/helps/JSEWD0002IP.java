package datapro.eibs.helps;

/**
 * This type was created by Frank Hernandez.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0002IP extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (9/14/05 12:29:44 PM)
	 */
	public JSEWD0002IP() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0002IP");

	}
	/**
	 * This method was created by Frank Hernandez.
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

		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD000203Message msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBObjList beanList = null;

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
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";

			try {
				flexLog("Opennig Socket Connection");
				mc =
					new MessageContext(getMessageHandler("EWD0002",req));

				try {
					msgHelp =
						(EWD000203Message) mc.getMessageRecord("EWD000203");
					msgHelp.setE03WDTBL("IP");
					msgHelp.setE03WDCOD(req.getParameter("branch"));
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD000203 Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECH5305?SCREEN=10");
					} catch (Exception ex) {
						flexLog("Exception calling /servlet/datapro.eibs.products.JSECH5305?SCREEN=10");
						flexLog("Error de Comunicación con la Dispensadora no procesado");
						res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					}
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD000203")) {
						beanList = new JBObjList();
						String marker = "";

						while (true) {
							msgHelp = (EWD000203Message) newmessage;
							flexLog("EWD000203 Message Received");					
							marker = msgHelp.getE03WDOPE();

							beanList.addRow(msgHelp);
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} 
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("ewd0002ipHelp", beanList);

						try {
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECH5300?SCREEN=2");
						} catch (Exception ex) {
							flexLog("Exception calling /servlet/datapro.eibs.products.JSECH5300?SCREEN=2");
							res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
			} finally {
				mc.close();
			}
		}

	}
}