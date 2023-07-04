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

public class JSEWD0325T extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0325T() {
		super();
	}
	/**
	 * This method was created by David Mavilla
	public void destroy() {
	
		flexLog("free resources used by JSEWD0325T");
		
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
		EWD0325DSMessage msgHelp = null;
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
						(EWD0325DSMessage) mc.getMessageRecord("EWD0325DS");
					msgHelp.setSWDTYP("");
					msgHelp.setRWDTYP("D");
					msgHelp.setRWDUSR(msgUser.getH01USR());
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0325DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0325DS")) {

						try {
							beanList = new datapro.eibs.beans.JBList();
							flexLog("EWD0325DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";

						while (true) {

							msgHelp = (EWD0325DSMessage) newmessage;

							marker = msgHelp.getSWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()
										+ "')\">"
										+ msgHelp.getSWDREF()
										+ "</A></td>");
								myRow.append(
									"<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()										
										+ "')\">"
										+ msgHelp.getSWDDSC()
										+ "</A></td>");
								myRow.append(
									"<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()										
										+ "')\">"
										+ msgHelp.getSWDTYP()
										+ "</A></td>");
								myRow.append(
									"<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()										
										+ "')\">"
										+ msgHelp.getSWDCCY()
										+ "</A></td>");
								myRow.append(
									"<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()										
										+ "')\">"
										+ Util.fcolorCCY(msgHelp.getSWDAMN())
										+ "</A></td>");
								myRow.append(
									"<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showTodDealsFr('"
										+ msgHelp.getSWDREF()
										+ "','"
										+ msgHelp.getSWDDID()
										+ "','"
										+ msgHelp.getSWDTYP()
										+ "','"
										+ msgHelp.getSWDACD()										
										+ "')\">"
										+ msgHelp.getSWDDID()
										+ "</A></td>");
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
						session.setAttribute("EWD0325Help", beanList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0325_fe_tod_deals.jsp");
							callPage(
								LangPath + "EWD0325_fe_tod_deals.jsp",
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