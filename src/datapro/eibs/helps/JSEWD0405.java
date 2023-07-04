package datapro.eibs.helps;

/**
 * 
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0405 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (09/21/07)
	 */
	public JSEWD0405() {
		super();
	}
	/**
	 * 
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0405");

	}
	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	/**
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0405DSMessage msgHelp = null; 
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
					msgHelp = (EWD0405DSMessage) mc.getMessageRecord("EWD0405DS");
					try {
						msgHelp.setEWDSBK(req.getParameter("shrBank"));
					} catch (Exception e) {
						msgHelp.setEWDSBK("  ");
					}
					try {
						msgHelp.setEWDSGL(req.getParameter("NameSearch"));
					} catch (Exception e) {
						msgHelp.setEWDSGL("0");
					}
					try {
						msgHelp.setEWDSBR(req.getParameter("shrBranch").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSBR("0");
					}
					try {
						msgHelp.setEWDSCY(req.getParameter("shrCurrency").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSCY("   ");
					}
					try {
						msgHelp.setEWDSCA(req.getParameter("shrAppCode").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSCA(" ");
					}
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0405DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0405DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0405DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						
						while (true) {

							msgHelp = (EWD0405DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(Integer.parseInt(msgHelp.getEWDREC()));
								}
								String enter = "<td nowrap ><a href=\"javascript:enter('"
									+ msgHelp.getEWDBNK() + "','"
									+ msgHelp.getEWDBRN() + "','"
									+ msgHelp.getEWDCCY() + "','"
									+ msgHelp.getEWDGLN() + "','"
									+ msgHelp.getEWDACC()
									+ "')\"> ";	

								myRow = new StringBuffer("<TR>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDBNK())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDBRN())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(
											msgHelp.getEWDCCY().toUpperCase())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDGLN())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDACC())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDDSC())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDCLS())
										+ "</a></td>");
								myRow.append(enter
										+ Util.formatCell(msgHelp.getEWDATY())
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
						session.setAttribute("ewd0405Help", beanList);

						try {
							req.setAttribute("NameSearch",req.getParameter("NameSearch"));
							req.setAttribute("shrBank",req.getParameter("shrBank"));
							req.setAttribute("shrBranch",req.getParameter("shrBranch"));
							req.setAttribute("shrAppCode",req.getParameter("shrAppCode"));
							req.setAttribute("shrCurrency",req.getParameter("shrCurrency"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0405_amortization_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0405_amortization_help_helpmessage.jsp", req, res);
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