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

public class JSEWD0308F extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0308F() {
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
		EWD0308DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;

		JBList beanList = null;

		session = (HttpSession) req.getSession(false);
		String DESCRIPTION = "";
		String INSTTYPE = "";
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
						(EWD0308DSMessage) mc.getMessageRecord("EWD0308DS");

					try {
						DESCRIPTION =
							(req.getParameter("DESCRIPTION") == null)
								? ""
								: req.getParameter("DESCRIPTION");
					} catch (Exception e) {
						DESCRIPTION = "";
					}
					
					try {
						INSTTYPE =
							(req.getParameter("INSTTYPE") == null)
								? ""
								: req.getParameter("INSTTYPE");
					} catch (Exception e) {
						INSTTYPE = "";
					}

					try { //Description
						msgHelp.setSWDDSC(req.getParameter("DESCRIPTION"));
					} catch (Exception e) {
					}
					
					try { //Instrument Type
						msgHelp.setSWDPTY(req.getParameter("INSTTYPE"));
					} catch (Exception e) {
					}

					msgHelp.setRWDTYP("");
					msgHelp.setSWDSTS("A");

					try { //From Pos

						msgHelp.setRWDFRC(req.getParameter("Pos"));

					} catch (Exception e) {

					}

					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0308DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0308DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0308DS Message Recived");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";
						int compar = 0;
						boolean firstTime = true;
						
						while (true) {

							msgHelp = (EWD0308DSMessage) newmessage;

							marker = msgHelp.getSWDOPE();
							compar = Integer.parseInt(msgHelp.getSWDREC());
							
							
							
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							}	
							 else {
							 	
							 	if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelp.getSWDREC()));
									
								} 							 	
								myRow = new StringBuffer("<TR>");
								if(msgHelp.getSWDISIOPE().trim().equals("")){
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDIIC()
										+ "</a></td>");
								}
								else {
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/maintenance.gif\" alt=\"Pending\" align=\"absmiddle\" border=\"0\" ><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDIIC()
										+ "</a></td>");
								}		
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDDSC()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDBCY()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDPTY()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDSYM()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDNUM()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDCUS()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDSTS()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ msgHelp.getSWDEMI()
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDIIC()
										+ "','"
										+ msgHelp.getSWDDSC()
										+ "','"
										+ msgHelp.getSWDBCY()
										+ "','"
										+ msgHelp.getSWDSYM()
										+ "','"
										+ msgHelp.getSWDNUM()
										+ "','"
										+ msgHelp.getSWDCUS()
										+ "','"
										+ msgHelp.getSWDPTY()
										+ "')\">"
										+ Util.formatDate(
											msgHelp.getSWDSD1(),
											msgHelp.getSWDSD2(),
											msgHelp.getSWDSD3())
										+ "</a></td>");
								
								myRow.append("</TR>");
								
								compar = Integer.parseInt(msgHelp.getSWDREC());
								
								beanList.addRow(myFlag, myRow.toString());

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("EWD0308Help", beanList);
						try {
							req.setAttribute("DESCRIPTION", DESCRIPTION);
							req.setAttribute("INSTTYPE", INSTTYPE);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0308F_inv_inst_helpmessage.jsp");
							callPage(
								LangPath + "EWD0308F_inv_inst_helpmessage.jsp",
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