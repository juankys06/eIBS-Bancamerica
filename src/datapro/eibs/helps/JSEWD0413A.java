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

public class JSEWD0413A extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0413A() {
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
		EWD0413DSMessage msgHelp = null;
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

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
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
					msgHelp = (EWD0413DSMessage) mc.getMessageRecord("EWD0413DS");
					
					try {
						msgHelp.setEWDFLG(req.getParameter("codeflag"));  
					} catch (Exception e)	{
						msgHelp.setEWDFLG("");              	  
					}

					try {
						msgHelp.setEWDTPL(req.getParameter("type"));  
					} catch (Exception e)	{
						msgHelp.setEWDTPL("");              	  
					}
					try {
						msgHelp.setEWDBRN(req.getParameter("branch"));  
					} catch (Exception e)	{
						msgHelp.setEWDBRN("");              	  
					}
					try {
						msgHelp.setEWDUSE(req.getParameter("user"));  
					} catch (Exception e)	{
						msgHelp.setEWDUSE("");              	  
					}
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0413DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0413DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0413DS Message Recived");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";

						while (true) {

							msgHelp = (EWD0413DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "','"
										+ msgHelp.getEWDMOX()
										+ "','"
										+ Util.formatYear(msgHelp.getEWDYEX())
										+ "','"
										+ msgHelp.getEWDDAS()
										+ "','"
										+ msgHelp.getEWDMOS()
										+ "','"
										+ msgHelp.getEWDYES()
										+"')\">"
										+ msgHelp.getEWDNUM()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "','"
										+ msgHelp.getEWDMOX()
										+ "','"
										+ Util.formatYear(msgHelp.getEWDYEX())
										+ "','"
										+ msgHelp.getEWDDAS()
										+ "','"
										+ msgHelp.getEWDMOS()
										+ "','"
										+ msgHelp.getEWDYES()
										+"')\">"
										+ Util.formatDate(msgHelp.getEWDDAS(), msgHelp.getEWDMOS(), msgHelp.getEWDYES())
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDNUM()
										+ "','"
										+ msgHelp.getEWDMOX()
										+ "','"
										+ Util.formatYear(msgHelp.getEWDYEX())
										+ "','"
										+ msgHelp.getEWDDAS()
										+ "','"
										+ msgHelp.getEWDMOS()
										+ "','"
										+ msgHelp.getEWDYES()
										+"')\">"
										+ msgHelp.getEWDMOX() + "/" + Util.formatYear(msgHelp.getEWDYEX())
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
						session.setAttribute("EWD0413Help", beanList);
						try {
							flexLog("About to call Page: " + LangPath + "EWD0413A_cc_helpmessage.jsp");
							callPage(LangPath + "EWD0413A_cc_helpmessage.jsp",	req, res);
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