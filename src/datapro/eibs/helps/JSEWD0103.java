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

public class JSEWD0103 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (9/4/08 11:47:45 AM)
	 */
	public JSEWD0103() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0103");

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
		EWD0103DSMessage msgHelp = null;
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
						(EWD0103DSMessage) mc.getMessageRecord("EWD0103DS");
					msgHelp.setEWDPRD(req.getParameter("PROD"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0103DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0103DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0103DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;

						while (true) {

							msgHelp = (EWD0103DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDTBL()
										+ "')\">"
										+ msgHelp.getEWDTBL()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDTBL()
										+ "')\">"
										+ msgHelp.getEWDPRT()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDTBL()
										+ "')\">"
										+ msgHelp.getEWDSRT()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDTBL()
										+ "')\">"
										+ msgHelp.getEWDNME()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDTBL()
										+ "')\">"
										+ Util.formatDate(
											msgHelp.getEWDDT1(),
											msgHelp.getEWDDT2(),
											msgHelp.getEWDDT3())
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
						session.setAttribute("ewd0100Help", beanList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0100_floating_rate_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0100_floating_rate_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else
						flexLog("Message " + newmessage.getFormatName() + " received.");
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