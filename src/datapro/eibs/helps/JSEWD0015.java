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

public class JSEWD0015 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0015() {
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
		EWD0015DSMessage msgHelp = null;
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
						(EWD0015DSMessage) mc.getMessageRecord("EWD0015DS");
					try{
						msgHelp.setEWDSCU(req.getParameter("CustNum"));
					} catch (Exception e) {
						msgHelp.setEWDSCU("0");
					}
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0015DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0015DS")) {

						try {
							beanList = new datapro.eibs.beans.JBList();
							flexLog("EWD0015DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;

						while (true) {

							msgHelp = (EWD0015DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ msgHelp.getEWDNUM()
										+ "</a></td>");
								myRow.append(
									"<td nowrap align=\"center\"><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ msgHelp.getEWDCCY()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ msgHelp.getEWDTYL()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ Util.formatDate(
											msgHelp.getEWDMT1(),
											msgHelp.getEWDMT2(),
											msgHelp.getEWDMT3())
										+ "</a></td>");
								myRow.append(
									"<td nowrap align=\"right\"><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ Util.formatCCY(msgHelp.getEWDAMN())
										+ "</a></td>");
								myRow.append(
									"<td nowrap align=\"right\"><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "')\">"
										+ Util.fcolorCCY(msgHelp.getEWDBAL())
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
						session.setAttribute("ewd0015Help", beanList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0015_creditline_helpmessage.jsp");
							callPage(
								LangPath + "EWD0015_creditline_helpmessage.jsp",
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