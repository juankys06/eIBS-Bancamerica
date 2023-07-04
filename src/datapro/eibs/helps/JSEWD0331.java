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

public class JSEWD0331 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0331() {
		super();
	}
	/**
	 * This method was created by David Mavilla
	public void destroy() {
	
		flexLog("free resources used by JSESS00    40");
		
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
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;
		ETLR00001Message msgHelp = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

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

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";

			try {
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 8);
				s.setSoTimeout(super.sckTimeOut);
				mc =	new MessageContext( new DataInputStream(
										 new BufferedInputStream(s.getInputStream())),
										 new DataOutputStream(
										 new BufferedOutputStream(s.getOutputStream())),
										 "datapro.eibs.beans");

				try {
					msgHelp = (ETLR00001Message) mc.getMessageRecord("ETLR00001");
					msgHelp.setH01USERID(msgUser.getH01USR());
					msgHelp.setH01OPECOD("0015");
					//msgHelp.setETLRTID(req.getParameter("UserId"));
					//msgHelp.setETLRCCY(req.getParameter("Currency"));
					//msgHelp.setETLRPWD(req.getParameter("Password"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("ETLR00001 Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving Data
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ETLR00001")) {

						try {
							beanList = new JBList();
							flexLog("ETLR00001 Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;

						while (true) {

							msgHelp = (ETLR00001Message) newmessage;

							marker = msgHelp.getH01OPECOD();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getETLRTCD()
										+ "')\">"
										+ msgHelp.getETLRTCD()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getETLRTCD()
										+ "')\">"
										+ msgHelp.getETLRITD()
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
						session.setAttribute("ETLR00001Help", beanList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ETLR000_teller_transactions_help.jsp");
							callPage(
								LangPath + "ETLR000_teller_transactions_help.jsp",
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