package datapro.eibs.helps;

/**
 * This type was created by Gustavo Adolfo Villarroel.
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
import datapro.eibs.beans.EWD0700RMessage;
import datapro.eibs.beans.EWD0700SMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEWD0700 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0700() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0700");

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		Socket s = null;
		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0700RMessage msgHelp = null;
		EWD0700SMessage msgHelpS = null;
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
			String Custype = "";
			try {
				flexLog("Opennig Socket Connection");
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
					msgHelp = (EWD0700RMessage) mc.getMessageRecord("EWD0700R");
					msgHelp.setRWDUSR(msgUser.getH01USR());
					msgHelp.setRWDSHR(
						req.getParameter("NameSearch").toUpperCase());
					msgHelp.setRWDTYP(req.getParameter("Type"));
					session.setAttribute("Type", msgHelp.getRWDTYP());
					msgHelp.setRWDFRC(req.getParameter("FromRecord"));
					try {
						Custype =
							(req.getParameter("CusType") == null)
								? ""
								: req.getParameter("CusType");
					} catch (Exception e) {
						Custype = "";
					}
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0700R Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0700S")) {
						beanList = new JBObjList();
						boolean firstTime = true;
						String marker = "";

						while (true) {
							msgHelpS = (EWD0700SMessage) newmessage;
							marker = msgHelpS.getSWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelpS.getSWDREC()));
								} 								
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
								beanList.addRow(msgHelpS);
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("EWD0700Help", beanList);

						try {
							req.setAttribute(
								"NameSearch",
								req.getParameter("NameSearch"));
							req.setAttribute("Type", req.getParameter("Type"));
							req.setAttribute("CusType", Custype);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0700_customer_details_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0700_customer_details_help_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				}
			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			} finally {
				s.close();
			}

		}

	}
}