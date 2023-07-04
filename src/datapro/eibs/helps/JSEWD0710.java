package datapro.eibs.helps;

/**
 * This type was created by Gustavo Adolfo Villarroel.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0710 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0710() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel
	public void destroy() {
	
		flexLog("free resources used by JSEWD0710");
		
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
		EWD0710DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;
		String usr = "";

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

				try {
					msgHelp =
						(EWD0710DSMessage) mc.getMessageRecord("EWD0710DS");

					msgHelp.setEWDSEL("S");

					try { 
						msgHelp.setEWDREC(req.getParameter("FromRecord"));
					} catch (Exception e) {
						msgHelp.setEWDREC("0");
					}
					if (msgHelp.getEWDREC() == null) msgHelp.setEWDREC("0");

					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0710DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0710DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0710DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";

						while (true) {

							msgHelp = (EWD0710DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(Integer.parseInt(msgHelp.getEWDREC()));
								}
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDTCN()
										+ "','"
										+ msgHelp.getEWDDCN()										
										+ "')\">"
										+ msgHelp.getEWDTCN()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDTCN()
										+ "','"
										+ msgHelp.getEWDDCN()										
										+ "')\">"
										+ msgHelp.getEWDDCN()
										+ "</a></td>");
								myRow.append("</TR>");
								beanList.addRow(myFlag, myRow.toString());

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("shrType", usr);
						session.setAttribute("EWD0710Help", beanList);
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0710_refcodes_help_helpmessage.jsp");
							callPage(
								LangPath + "EWD0710_refcodes_help_helpmessage.jsp",
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