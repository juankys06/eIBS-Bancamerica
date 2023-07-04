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

public class JSEWD0146 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0146() {
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
		EWD0146DSMessage msgHelp = null;
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
			String ID = "";
			String CUN = "0";
			String SEL = "1";
			try {
				ID = req.getParameter("ID");
				if (ID == null) {
					ID = "";
				}
			} catch (Exception e) {
				ID = "";
			}
			try {
				CUN = req.getParameter("CUN");
				if (CUN == null) {
					CUN = "0";
				}
			} catch (Exception e) {
				CUN = "0";
			}
			try {
				SEL = req.getParameter("SEL");
				if (SEL == null) {
					SEL = "1";
				}
				
			} catch (Exception e) {
				SEL = "1";
			}

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

				msgHelp = (EWD0146DSMessage) mc.getMessageRecord("EWD0146DS");
				//msgHelp.setE01SELLNS(LN);

				try {

				} catch (Exception e) {

				}
				if (SEL.equals("1")) {
					try {
						msgHelp.setE01SELCUN(CUN);
					} catch (Exception e) {
						msgHelp.setE01SELCUN("0");
					}
					msgHelp.setE01SELIDE("");

				} else {
					try {
						msgHelp.setE01SELIDE(ID);
					} catch (Exception e) {
						msgHelp.setE01SELIDE("");
					}
					msgHelp.setE01SELCUN("0");
				}

				msgHelp.setE01SELSTS("A");

				//				try {
				//					msgHelp.setE01SHRNME(
				//						req.getParameter("NameSearch").toUpperCase());
				//				} catch (Exception e) {
				//					msgHelp.setE01SHRNME("");
				//				}
				//				try {
				//					msgHelp.setE01NUMREC(req.getParameter("FromRecord"));
				//				} catch (Exception e) {
				//					msgHelp.setE01NUMREC("0");
				//				}

				msgHelp.send();
				msgHelp.destroy();
				flexLog("EWD0140DS Message Sent");

				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0146DS")) {

						beanList = new JBList();

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						String myRow = "";
						String chk = "";

						while (true) {

							msgHelp = (EWD0146DSMessage) newmessage;

							marker = msgHelp.getE01ENDFLD();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								//								if (firstTime) {
								//									firstTime = false;
								//									beanList.setFirstRec(
								//										Integer.parseInt(
								//											msgHelp.getE01NUMREC()));
								//									chk = "checked";
								//								} else {
								//									chk = "";
								//								}
								myRow = "<TR>";
								myRow
									+= "<td NOWRAP ><a href=\"javascript:enter('"
									+ msgHelp.getE01DLHNRO()
									+ "')\">"
									+ Util.formatCell(msgHelp.getE01DLHNRO())
									+ "</a></td>";
								myRow
									+= "<td NOWRAP ><a href=\"javascript:enter('"
									+ msgHelp.getE01DLHNRO()
									+ "')\">"
									+ Util.formatCell(msgHelp.getE01DLHIDN())
									+ "</a></td>";
								myRow
									+= "<td NOWRAP ><a href=\"javascript:enter('"
									+ msgHelp.getE01DLHNRO()
									+ "')\">"
									+ Util.formatCell(msgHelp.getE01DLHCUN())
									+ "</a></td>";
								myRow
									+= "<td NOWRAP ><a href=\"javascript:enter('"
									+ msgHelp.getE01DLHNRO()
									+ "')\">"
									+ Util.formatCell(msgHelp.getE01DLHNME())
									+ "</a></td>";
								myRow += "</TR>";
								beanList.addRow(myFlag, myRow);

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("ewd0146Help", beanList);

						try {
							req.setAttribute(
								"NameSearch",
								req.getParameter("NameSearch"));
							req.setAttribute(
								"FromRecord",
								req.getParameter("FromRecord"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0146_colldraft_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0146_colldraft_help_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(LangPath + super.sckNotRespondPage);
				}

			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(LangPath + super.sckNotOpenPage);
				//			return;
			} finally {
				s.close();
			}
		}

	}
}