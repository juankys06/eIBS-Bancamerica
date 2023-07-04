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

public class JSEWD0355 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0355() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0091");

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
		EWD0355DSMessage msgHelp = null;
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
						(EWD0355DSMessage) mc.getMessageRecord("EWD0355DS");
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0355DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0355DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0355DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";
						String sRef = "";
						while (true) {

							msgHelp = (EWD0355DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									
									chk = "checked";
								} else {
									chk = "";
								}
								myRow = new StringBuffer("<TR>");
								sRef = "<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDPRF()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "','"
										+ msgHelp.getEWDCDP()
										+ "','"
										+ msgHelp.getEWDCDA()
										+ "','"
										+ msgHelp.getEWDKDP()
										+ "','"
										+ msgHelp.getEWDKDA()										
										+ "','"
										+ msgHelp.getEWDCWD()										
										+ "','"
										+ msgHelp.getEWDCWA()										
										+ "','"
										+ msgHelp.getEWDCPY()										
										+ "','"
										+ msgHelp.getEWDCPA()										
										+ "','"
										+ msgHelp.getEWDTIN()										
										+ "','"
										+ msgHelp.getEWDTIA()										
										+ "','"
										+ msgHelp.getEWDTRV()										
										+ "','"
										+ msgHelp.getEWDTRA()										
										+ "','"
										+ msgHelp.getEWDSCN()										
										+ "','"
										+ msgHelp.getEWDSCA()										
										+ "','"
										+ msgHelp.getEWDSWN()										
										+ "','"
										+ msgHelp.getEWDSWA()										
										+ "','"
										+ msgHelp.getEWDSDN()										
										+ "','"
										+ msgHelp.getEWDSDA()										
										+ "','"
										+ msgHelp.getEWDSKN()										
										+ "','"
										+ msgHelp.getEWDSKA()										
										+ "','"
										+ msgHelp.getEWDBCN()										
										+ "','"
										+ msgHelp.getEWDBCA()										
										+ "','"
										+ msgHelp.getEWDBWN()										
										+ "','"
										+ msgHelp.getEWDBWA()										
										+ "','"
										+ msgHelp.getEWDBDN()										
										+ "','"
										+ msgHelp.getEWDBDA()										
										+ "','"
										+ msgHelp.getEWDBKN()										
										+ "','"
										+ msgHelp.getEWDBKA()										
										+ "','"
										+ msgHelp.getEWDGAV()										
										+ "','"
										+ msgHelp.getEWDNAV()										
										+ "')\">";
								
								myRow.append(sRef  
										+ Util.formatCell(msgHelp.getEWDPRF())
										+ "</a></td>");
								myRow.append(sRef
										+ Util.formatCell(msgHelp.getEWDDSC())
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
						session.setAttribute("EWD0355Help", beanList);

						try {
							flexLog("About to call Page: " + LangPath + "EWD0355_profile_table.jsp");
							callPage(LangPath + "EWD0355_profile_table.jsp", req, res);
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