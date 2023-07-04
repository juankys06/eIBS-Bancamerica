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

public class JSEWD0515 extends datapro.eibs.master.SuperServlet {

	private static final int R_CCY_HELP = 1;

	private String LangPath = "S";

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0515() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD00515");

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

			int screen = R_CCY_HELP;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");
				String Language = msgUser.getE01LAN();
				LangPath = super.rootPath + Language + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_CCY_HELP :
							procReqCCYHelp(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	private synchronized void procReqCCYHelp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0515DSMessage msgHelp = null;
		JBList beanList = null;

		try {
			msgHelp = (EWD0515DSMessage) mc.getMessageRecord("EWD0515DS");
			
			msgHelp.setEWDCUN(req.getParameter("Customer"));
			
			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0515DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0515DS")) {

				try {
					beanList = new JBList();
					flexLog("EWD0515DS Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgHelp = (EWD0515DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDMAN()
								+ "','"
								+ msgHelp.getEWDMA1()
								+ "','"
								+ msgHelp.getEWDJOB()
								+ "')\">"
								+ msgHelp.getEWDMAN()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDMAN()
								+ "','"
								+ msgHelp.getEWDMA1()
								+ "','"
								+ msgHelp.getEWDJOB()
								+ "')\">"
								+ msgHelp.getEWDMA1()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDMAN()
								+ "','"
								+ msgHelp.getEWDMA1()
								+ "','"
								+ msgHelp.getEWDJOB()
								+ "')\">"
								+ msgHelp.getEWDJOB()
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
				ses.setAttribute("EWD0515Help", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EWD0515_bastanteo_members_helpmessage.jsp");
					callPage(
						LangPath + "EWD0515_bastanteo_members_helpmessage.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

}