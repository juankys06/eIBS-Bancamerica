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

public class JSEWD0008 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0008() {
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
		MessageRecord newmessage = null;
		EWD0008DSMessage msgHelp = null;
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
					msgHelp = (EWD0008DSMessage) mc.getMessageRecord("EWD0008DS");
					msgHelp.setEWDUSR(msgUser.getH01USR());
					msgHelp.setEWDSBK(msgUser.getE01UBK());	
					msgHelp.setEWDSCA(req.getParameter("AppCode").toUpperCase());
					msgHelp.setEWDSBK(req.getParameter("ProductBank"));
					msgHelp.setEWDSPR(req.getParameter("srhProduct").toUpperCase());
					msgHelp.send();
					msgHelp.destroy();
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0008DS")) {

						try {
							beanList = new JBList();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";

						while (true) {

							msgHelp = (EWD0008DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									//beanList.setFirstRec(Integer.parseInt(msgHelp.getEWD REC()));
									chk = "checked";
								} else {
									chk = "";
								}
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td NOWRAP ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDCDE()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "','"
										+ msgHelp.getEWDTYP()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDCDE())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDCDE()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "','"
										+ msgHelp.getEWDTYP()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDTYP())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getEWDCDE()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "','"
										+ msgHelp.getEWDTYP()
										+ "')\">"
										+ Util.formatCell(msgHelp.getEWDCCY())
										+ "</a></td>");
								myRow.append(
									"<td NOWRAP ><a href=\"javascript:enter('"
										+ msgHelp.getEWDCDE()
										+ "','"
										+ msgHelp.getEWDDSC()
										+ "','"
										+ msgHelp.getEWDTYP()
										+ "')\">"
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
						session.setAttribute("ewd0008Help", beanList);

						try {
							req.setAttribute(
								"AppCode",
								req.getParameter("AppCode"));
							req.setAttribute(
								"ProductBank",
								req.getParameter("ProductBank"));
							req.setAttribute(
								"srhProduct",
								req.getParameter("srhProduct"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0008_client_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0008_client_help_helpmessage.jsp",
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