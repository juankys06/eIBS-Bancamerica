package datapro.eibs.helps;

/**
 * This type was created by Gustavo Adolfo Villarroel.
 * This method give all the limits created by the user
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0380 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (23/4/09 17:34:44 PM)
	 */
	public JSEWD0380() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel
	public void destroy() {
	
		flexLog("free resources used by JSEWD0380");
		
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
		EWD038001Message msgHelp = null;
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
					msgHelp = (EWD038001Message) mc.getMessageRecord("EWD038001");

					try {
						msgHelp.setE01TASBNK(req.getParameter("bnk"));
					} catch (Exception e) {}

					try {
						msgHelp.setE01TASTBL(req.getParameter("tbl"));
					} catch (Exception e) {}

					try {
						msgHelp.setE01TASPRD(req.getParameter("prd"));
					} catch (Exception e) {}
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD038001 Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD038001")) {

						try {
							beanList = new JBList();
							flexLog("EWD038001 Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";
						boolean firstTime = true;

						while (true) {

							msgHelp = (EWD038001Message) newmessage;

							marker = msgHelp.getE01TASOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								if (firstTime) {
									firstTime = false;
									//beanList.setFirstRec(Integer.parseInt(msgHelp.getSWDREC()));
								}						
								
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getE01TASTBL()
										+ "')\">"
										+ msgHelp.getE01TASTBL()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getE01TASTBL()
										+ "')\">"
										+ msgHelp.getE01TASDSC()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getE01TASTBL()
										+ "')\">"
										+ msgHelp.getE01TASDTE()
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
						session.setAttribute("EWD0380Help", beanList);
						session.setAttribute("prefere", msgHelp);

						try {
							if (req.getParameter("loan").equals("true")) {
								flexLog("About to call Page: " + LangPath + "EWD0380_limits_parameters.jsp");
								callPage(LangPath + "EWD0380_limits_parameters.jsp", req, res);
							}
							else {
								flexLog("About to call Page: " + LangPath + "EWD0380_limits_parameters_helpmessage.jsp");
								callPage(LangPath + "EWD0380_limits_parameters_helpmessage.jsp", req, res);
							}
						} catch (Exception e) {
							flexLog("About to call Page: " + LangPath + "EWD0380_limits_parameters_helpmessage.jsp");
							callPage(LangPath + "EWD0380_limits_parameters_helpmessage.jsp", req, res);
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