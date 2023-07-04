package datapro.eibs.forex;

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

public class JSEWD0012F extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0012F() {
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
		EWD0328DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;

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
						(EWD0328DSMessage) mc.getMessageRecord("EWD0328DS");
					msgHelp.setEWDSHR(req.getParameter("BankNumber"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0328DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0328DS")) {

						try {
							beanList = new datapro.eibs.beans.JBList();
							flexLog("EWD0012DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;

						while (true) {

							msgHelp = (EWD0328DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append("<TD NOWRAP ALIGN=CENTER>"
									+ msgHelp.getEWDCCY()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDDSC()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDEXR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDTLR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=CENTER>"
									+ msgHelp.getEWDMUD()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=CENTER>"
									+ msgHelp.getEWDBSE()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDCPR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDCSR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFPR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFSR()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR1()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR2()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR3()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR4()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR5()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR6()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR7()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR8()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDFR9()
									+ "</td>");
								myRow.append("<TD NOWRAP ALIGN=RIGHT>"
									+ msgHelp.getEWDF10()
									+ "</td>");
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
						session.setAttribute("ewd0328Help", beanList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0012F_currency_helpmessage.jsp");
							callPage(
								LangPath + "EWD0012F_currency_helpmessage.jsp",
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
				// return;
			} finally {
				s.close();
			}

		}

	}
}