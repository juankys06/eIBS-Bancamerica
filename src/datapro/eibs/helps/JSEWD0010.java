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

public class JSEWD0010 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0010() {
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

		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0010DSMessage msgHelp = null;
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
				mc =
					new MessageContext(getMessageHandler("EWD0010",req));

				try {
					msgHelp =
						(EWD0010DSMessage) mc.getMessageRecord("EWD0010DS");
					try {
						msgHelp.setEWDSGL(req.getParameter("NameSearch"));
					} catch (Exception e) {
						msgHelp.setEWDSGL("0");
					}
					msgHelp.setEWDSBK(req.getParameter("shrBank"));
					msgHelp.setEWDSCA(req.getParameter("shrAppCode"));
					try {
						msgHelp.setEWDSCY(
							req.getParameter("shrCurrency").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSCY("USD");
					}
					try {
						msgHelp.setEWDATY(
							req.getParameter("shrType").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDATY("");
					}
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0010DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0010DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0010DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";

						while (true) {

							msgHelp = (EWD0010DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelp.getEWDREC()));
									chk = "checked";
								} else {
									chk = "";
								}
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDBNK())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(
											msgHelp.getEWDCCY().toUpperCase())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDGLN())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDDSC())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDCLS())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDGLN()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDATY())
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
						session.setAttribute("ewd0010Help", beanList);

						try {
							req.setAttribute(
								"NameSearch",
								req.getParameter("NameSearch"));
							req.setAttribute(
								"shrBank",
								req.getParameter("shrBank"));
							req.setAttribute(
								"shrAppCode",
								req.getParameter("shrAppCode"));
							req.setAttribute(
								"shrCurrency",
								req.getParameter("shrCurrency"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0010_client_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0010_client_help_helpmessage.jsp",
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
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			} finally {
				mc.close();
			}

		}

	}
}