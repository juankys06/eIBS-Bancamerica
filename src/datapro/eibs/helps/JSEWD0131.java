package datapro.eibs.helps;

/**
 * 08/30/07 C. Castillo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0131 extends datapro.eibs.master.SuperServlet {

		/**
		 * Send Beneficiary List for Help
		 * Creation date: (08/30/07 12:29:44 PM)
		 */
		public JSEWD0131() {
			super();
		}
		/**
		 * 
		*/ 
		public void destroy() {
		
			flexLog("free resources used by JSEWD0131");
			
		}
		/**
		 * 
		 */
		public void init(ServletConfig config) throws ServletException {
			super.init(config);

		}
		/**
		 * 
		 * @param request HttpServletRequest
		 * @param response HttpServletResponse
		 */
		public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

			Socket s = null;
			MessageContext mc = null;
			HttpSession session;
			MessageRecord newmessage = null;
			EWD0131DSMessage msgHelp = null;
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
							(EWD0131DSMessage) mc.getMessageRecord("EWD0131DS");
						msgHelp.setRWDTYP(" ");
						msgHelp.setRWDUSR(msgUser.getH01USR());
						try {
							msgHelp.setSWDCUN(req.getParameter("CUSTOMER"));
						} catch (Exception e) {
							msgHelp.setSWDCUN("0");
						}
						try{
							msgHelp.setRWDFRC(req.getParameter("FromRecord"));
							}
							catch (Exception e){
							}
							
						msgHelp.send();
						msgHelp.destroy();
						flexLog("EWD0131DS Message Sent");
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
						return;
					}

					// Receiving
					try {
						newmessage = mc.receiveMessage();

						if (newmessage.getFormatName().equals("EWD0131DS")) {

							try {
								beanList = new JBList();
								flexLog("EWD0131DS Message Received");
							} catch (Exception ex) {
								flexLog("Error: " + ex);
							}

							String marker = "";
							String myFlag = "";
							StringBuffer myRow = null;
							String myDesc = "";
							boolean firstTime = true;

							while (true) {

								msgHelp = (EWD0131DSMessage) newmessage;

								marker = msgHelp.getSWDOPE();

								if (marker.equals("*")) {
									beanList.setShowNext(false);
									break;
								} else {

									if (firstTime) {
										firstTime = false;
										beanList.setFirstRec(
											Integer.parseInt(msgHelp.getSWDREC()));
									}
									
									myRow = new StringBuffer("<TR>");
									myRow.append(
										"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBFAC()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBFA()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBF1()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBF2()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBF3()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBKID()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBKA1()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBKA2()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBKA3()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDBKA4()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDIBID()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDIBAD1()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDIBAD2()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDIBAD3()
											+ "</a></td>");
									myRow.append(
											"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getSWDCUN()
											+ "','"
											+ msgHelp.getSWDBFAC()
											+ "','"
											+ msgHelp.getSWDBF1()
											+ "','"
											+ msgHelp.getSWDBF2()
											+ "','"
											+ msgHelp.getSWDBF3()
											+ "','"
											+ msgHelp.getSWDBKID()
											+ "','"
											+ msgHelp.getSWDBKA1()											
											+ "','"
											+ msgHelp.getSWDBKA2()											
											+ "','"
											+ msgHelp.getSWDBKA3()											
											+ "','"
											+ msgHelp.getSWDBKA4()											
											+ "','"
											+ msgHelp.getSWDIBID()											
											+ "','"
											+ msgHelp.getSWDIBAD1()											
											+ "','"
											+ msgHelp.getSWDIBAD2()											
											+ "','"
											+ msgHelp.getSWDIBAD3()											
											+ "','"
											+ msgHelp.getSWDIBAD4()											
											+ "')\">"
											+ msgHelp.getSWDIBAD4()
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
							session.setAttribute("EWD0131Help", beanList);

							try {
								flexLog("About to call Page: " + LangPath + "EWD0131_beneficiary_help.jsp");
								callPage(LangPath + "EWD0131_beneficiary_help.jsp",	req, res);
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