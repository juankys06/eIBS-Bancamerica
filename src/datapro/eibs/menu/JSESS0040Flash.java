package datapro.eibs.menu;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ESS0040DSMessage;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSESS0040Flash extends datapro.eibs.master.SuperServlet {
	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSESS0040Flash() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESS0040");

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
		HttpSession session;

		MessageRecord newmessage = null;
		ESS0040DSMessage msgMenu = null;
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

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getValue("currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";

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
				return;
			}

			// set MINE type for HTTP Header
			res.setContentType("text/html");
			res.setHeader("Pragma", "No-cache");
			res.setDateHeader("Expires", 0);
			res.setHeader("Cache-Control", "no-cache");
			// get a handle to the output stream
			PrintWriter out = res.getWriter();

			// Send Request
			flexLog("Sending Request. UserID = " + msgUser.getH01USR());
			try {
				msgMenu = (ESS0040DSMessage) mc.getMessageRecord("ESS0040DS");
				msgMenu.setESSUSR(msgUser.getH01USR());
				msgMenu.setESSTYP(Language.toUpperCase());
				msgMenu.send();
				msgMenu.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				return;
			}

			// Receiving
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESS0040DS")) {
					msgMenu = (ESS0040DSMessage) newmessage;

					flexLog("Data received TYP = " + msgMenu.getESSTYP());
					if (msgMenu.getESSTYP().equals("T")) {

						int buttonId = 0;
						flexLog("Data received IDE = " + msgMenu.getESSIDE());
						int buttonNumber = Integer.parseInt(msgMenu.getESSIDE());
						flexLog("OK");

						out.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>");
						out.println("<server");

						out.println("startPosY=\"\"");
						out.println("startPosX=\"\"");
						out.println("xspace=\"\"");
						out.println("yspace=\"15\"");
						out.println("speed=\"9.2\"");
						out.println("barrierX=\"146\"");
						out.println("barrierY=\"450\"");
						out.println("scrollfunc=\"scrollbars\"");
						out.println("sound=\"\"");
						out.println("defaultOverSound =\"3\"");
						out.println("defaultClickSound =\"4\"");
						out.println("networkpermissions=\"true\"");
						out.println(">");

						int thisButtonNumber = -1;
						int optionNumber = -1;
						int thisOptionNumber = -1;
						String buttonCaption = "";
						String optionCaption = "";
						String optionImage = "";
						String optionURL = "";
						String optionTarget = "";

						while (true) {
							newmessage = mc.receiveMessage();
							msgMenu = (ESS0040DSMessage) newmessage;
							flexLog("Receiving data");

							if (msgMenu.getESSTYP().equals("M")) {

								thisButtonNumber++; // Integer.parseInt(msgMenu.getESSSID().substring(3)) - 1;
								optionNumber = Integer.parseInt(msgMenu.getESSIDE());
								buttonCaption = msgMenu.getESSDSC();

								thisOptionNumber = -1;

								if (thisButtonNumber > 0)
									out.println("</folder>");
								out.println("<folder name=\"" + buttonCaption + "\" icon=\"2\">");

							} else
								if (msgMenu.getESSTYP().equals("C")) {

									thisOptionNumber++; // Integer.parseInt(msgMenu.getESSIDE()) - 1;
									optionCaption = msgMenu.getESSDSC();
									optionImage = msgMenu.getESSIMG();
									optionURL = super.webAppPath + msgMenu.getESSTAD();
									optionTarget = msgMenu.getESSTPO();

									out.println(
										"<object name=\""
											+ optionCaption
											+ "\" type=\"link\" icon=\"6\" url=\""
											+ optionURL
											+ "\" window=\""
											+ optionTarget
											+ "\" />");
								} else
									if (msgMenu.getESSTYP().equals("*")) {
										break;
									}
						}

						out.println("</folder>");
						out.println("</server>");

					} else
						if (msgMenu.getESSTYP().equals("*")) {
							res.sendRedirect(super.srctx + LangPath + "ESS0040_menu_access_denied.jsp");
						} else {
							res.sendRedirect(super.srctx + LangPath + "ESS0040_menu_access_denied.jsp");
						}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

			try {
				s.close();
			} catch (Exception e) {
				flexLog("Error closing socket connection " + e);
			}
		}

	}
}