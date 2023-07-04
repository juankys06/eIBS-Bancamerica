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

public class JSEWD0400 extends datapro.eibs.master.SuperServlet {

	private static final int R_AMORT = 1;


	private String LangPath = "S";

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0400() {
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

			int screen = R_AMORT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");
				String Language = msgUser.getE01LAN();
				LangPath = super.rootPath + Language + "/";

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
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_AMORT :
							procReqCCYHelp(mc, msgUser, req, res, session);
							break;
						
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
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
		EWD0400DSMessage msgHelp = null;
		JBList beanList = null;

		try {
			msgHelp = (EWD0400DSMessage) mc.getMessageRecord("EWD0400DS");
			
			try{
				msgHelp.setSELBNK(req.getParameter("BNK"));
			}
			catch (Exception e) {
				msgHelp.setSELBNK(user.getE01UBK());
			}

			try{
				msgHelp.setSELBRN(req.getParameter("BRN"));
			}
			catch (Exception e) {
				msgHelp.setSELBRN(user.getE01UBR());
			}
			
			try{
				msgHelp.setEWDREC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
				msgHelp.setEWDREC("0");
			}


			
			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0012DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0400DS")) {

				try {
					beanList = new JBList();
					flexLog("EWD0400DS Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgHelp = (EWD0400DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(
							Integer.parseInt(msgHelp.getEWDREC()));
						
					} 	
					

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<td nowrap ><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDBNK()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDBRN()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDASN()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDMAR()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDMOD()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDEST()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDCLS()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDASN()
								+ "')\">"
								+ msgHelp.getEWDLOC()
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
				ses.setAttribute("ewd0400Help", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EWD0400_amortization_id.jsp");
					callPage(
						LangPath + "EWD0400_amortization_id.jsp",
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