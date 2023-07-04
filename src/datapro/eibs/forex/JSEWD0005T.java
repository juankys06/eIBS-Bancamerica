package datapro.eibs.forex;

/**
 * This type was created by C.Castillo.
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

public class JSEWD0005T extends datapro.eibs.master.SuperServlet {

	/**
	 * Help for select accounts from a specific customer
	 * Creation date: (3/26/09 12:29:44 PM)
	 */
	public JSEWD0005T() {
		super();
	}
	/**
	 * 
	*/ 
	public void destroy() {
	
		flexLog("free resources used by JSEWD0005T");
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
		EWD0005DSMessage msgHelp = null;
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
				mc = new MessageContext(new DataInputStream(
					 new BufferedInputStream(s.getInputStream())),
					 new DataOutputStream(
					 new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");

				try {
					msgHelp = (EWD0005DSMessage) mc.getMessageRecord("EWD0005DS");
					msgHelp.setEWDSEL(req.getParameter("sel"));

					try {
						msgHelp.setEWDSBK(req.getParameter("bnk"));
					} catch (Exception e) {
						msgHelp.setEWDSBK(msgUser.getE01UBK());
					}
					try {
						msgHelp.setEWDCUN(req.getParameter("cun"));
					} catch (Exception e) {
						msgHelp.setEWDCUN("0");
					}
					try {
						msgHelp.setEWDSCA(req.getParameter("app"));
					} catch (Exception e) {
						msgHelp.setEWDSCA("");
					}
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0005DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0005DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0005DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";

						while (true) {

							msgHelp = (EWD0005DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDACC()
										+ "')\">"
										+ msgHelp.getEWDCCY()
										+ "</a></td>");
								myRow.append(
										"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getEWDACC()
											+ "')\">"
											+ msgHelp.getEWDACC()
											+ "</a></td>");
								myRow.append(
										"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getEWDACC()
											+ "')\">"
											+ msgHelp.getEWDPRD()
											+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelp.getEWDACC()
										+ "')\">"
										+ msgHelp.getEWDAMT()
										+ "</a></td>");
								myRow.append(
										"<td nowrap><a href=\"javascript:enter('"
											+ msgHelp.getEWDACC()
											+ "')\">"
											+ msgHelp.getEWDNME()
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
						session.setAttribute("EWD0005Help", beanList);

						try {
							flexLog("About to call Page: " + LangPath + "EWD0005T_account_helpmessage.jsp");
							callPage(LangPath + "EWD0005T_account_helpmessage.jsp", req, res);
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
			} finally {
				s.close();
			}
		}

	}
}