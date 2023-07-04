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

public class JSEWD0092 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0092() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0092");

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
		EWD0092DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;

		session = (HttpSession) req.getSession(false);

		String shrAcc = req.getParameter("shrAcc");
		String shrCategory = req.getParameter("shrCategory");
		String shrAction = req.getParameter("shrAction");

		flexLog("shrAcc" + shrAcc);
		flexLog("shrCategory" + shrCategory);
		flexLog("shrAction" + shrAction);

		if (shrAcc == null) {
			shrAcc = "";
		}
		if (shrCategory == null) {
			shrCategory = "";
		}
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
						(EWD0092DSMessage) mc.getMessageRecord("EWD0092DS");
					msgHelp.setEWDACN(shrAcc);
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					flexLog("FromRecord" + req.getParameter("FromRecord"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0092DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				int varGroup = 0;
				String strCategory = "";
				int cntDetail = 0;
				if (shrCategory.equals("ALL")) {
					//if category == blank then group by category
					varGroup = 1;
				} else {
					varGroup = 0;
				}
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0092DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0092DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";

						while (true) {

							msgHelp = (EWD0092DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();
							flexLog("EWDMFN : " + msgHelp.getEWDMFN());
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelp.getEWDREC()));
									chk = "checked";
								} else {
									chk = "";
								}
								myRow = new StringBuffer("<TR>");
								//group by category
								flexLog("EWDPRO " + msgHelp.getEWDPRO());
								if (varGroup == 1) {
									if (!msgHelp
										.getEWDPRO()
										.trim()
										.equals("")) {
										myRow.append(
											"<td NOWRAP><A HREF=\"javascript:showOfacDetail('"
												+ shrAcc
												+ "','"
												+ msgHelp.getEWDPRO().trim()
												+ "')\">"
												+ msgHelp.getEWDPRO()
												+ "</TD> </TR>");
									} else {
										myRow.append(
											"<td NOWRAP><A HREF=\"javascript:showOfacDetail('"
												+ shrAcc
												+ "','GENERAL INFORMATION')\">GENERAL INFORMATION</TD> </TR>");
									}

									cntDetail = 1;
									if (!strCategory
										.equals(msgHelp.getEWDPRO())) {
										flexLog("fill category");

										beanList.addRow(
											myFlag,
											myRow.toString());
										if (shrCategory.equals("ALL")) {
											strCategory = msgHelp.getEWDPRO();
										} else {
											strCategory = shrCategory;
										}
										cntDetail = 0;
									}

								} else {
									if (shrCategory
										.trim()
										.equals(msgHelp.getEWDPRO().trim())
										|| (msgHelp.getEWDPRO().trim().equals("")
											&& shrCategory.equals(
												"GENERAL INFORMATION"))) {

										myRow.append(
											"<td NOWRAP >"
												+ msgHelp.getEWDMFN()
												+ "</td>");
										//myRow +="<td NOWRAP >" + msgHelp.getEWDPRO() + "</td>";
										myRow.append(
											"<td NOWRAP >"
												+ msgHelp.getEWDDT1()
												+ "/"
												+ msgHelp.getEWDDT2()
												+ "/"
												+ msgHelp.getEWDDT3()
												+ "</td>");
										myRow.append(
											"<td NOWRAP >"
												+ msgHelp.getEWDSCA()
												+ "</td>");
										myRow.append("</TR>");
										beanList.addRow(
											myFlag,
											myRow.toString());
									}
								}
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}
						if (cntDetail == 1) {
							beanList.addRow(myFlag, myRow.toString());
						}

						if (beanList.getNoResult()) {
							flexLog("empty bean");
						} else {
							flexLog("not empty bean");
						}

						flexLog("Putting java beans into the session");

						if (shrCategory.equals("ALL")) {
							session.setAttribute("EWD0092Help", beanList);
						} else {
							session.setAttribute("EWD0092HelpDetail", beanList);
						}

						try {
							req.setAttribute(
								"shrAcc",
								req.getParameter("shrAcc"));
							req.setAttribute(
								"shrCategory",
								req.getParameter("shrCategory"));
							req.setAttribute(
								"shrAction",
								req.getParameter("shrAction"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0092_ofac_help_helpmessage.jsp");
							if (shrCategory.equals("ALL")) {
								callPage(
									LangPath
										+ "EWD0092_ofac_help_container.jsp",
									req,
									res);
							} else {
								callPage(
									LangPath + "EWD0092_ofac_help_detail.jsp",
									req,
									res);
							}

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