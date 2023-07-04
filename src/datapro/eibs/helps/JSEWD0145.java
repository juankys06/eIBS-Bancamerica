package datapro.eibs.helps;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0145 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0145() {
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
		ELEERRMessage msgError = null;
		EWD0145DSMessage msgHelp = null;
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
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(
							new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(
							new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");

				String Opt = "";
				try {
					msgHelp = (EWD0145DSMessage) mc.getMessageRecord("EWD0145DS");
					
					try {
						msgHelp.setE01SELLNS(req.getParameter("Account"));
					} catch (Exception e) {
						msgHelp.setE01SELLNS("");
					}
					try {
						msgHelp.setE01SELIDE(req.getParameter("id"));
					} catch (Exception e) {
						msgHelp.setE01SELIDE("");
					}
					try {
						msgHelp.setE01SELSTS(req.getParameter("Status"));
					} catch (Exception e) {
						msgHelp.setE01SELSTS("");
					}
					
					if (!msgHelp.getE01SELLNS().equals("0")
						|| !msgHelp.getE01SELLNS().trim().equals("")) {
						Opt = "LN";
					}
					if (msgHelp.getE01SELIDE().equals("0")
						|| msgHelp.getE01SELIDE().trim().equals("")) {
						Opt = "LN";
					}
					//try {
					//	msgHelp.setE01NUMREC(req.getParameter("FromRecord"));
					//} catch (Exception e) {
					//	msgHelp.setE01NUMREC("0");
					//}
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0145DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}
				
				// Receiving
				try {
					newmessage = mc.receiveMessage();
					
					if (newmessage.getFormatName().equals("EWD0145DS")) {
						beanList = new JBObjList();
						String marker = "";

						while (true) {
							msgHelp = (EWD0145DSMessage) newmessage;
							flexLog("EWD0145DS Message Received");	
							marker = msgHelp.getE01ENDFLD();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								beanList.addRow(msgHelp);
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();

						}

						flexLog("Putting java beans into the session");
						session.setAttribute("ewd0145Help", beanList);

					} else {
						flexLog("Message " + newmessage.getFormatName() + " received.");
					}
					
					try {
						req.setAttribute(
							"Account",
							req.getParameter("Account"));
						req.setAttribute(
							"Status",
							req.getParameter("Status"));
						req.setAttribute(
							"FromRecord",
							req.getParameter("FromRecord"));
						req.setAttribute(
							"OPT", Opt);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EWD0145_ln_doc_help_helpmessage.jsp");
						callPage(
							LangPath
								+ "EWD0145_ln_doc_help_helpmessage.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
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