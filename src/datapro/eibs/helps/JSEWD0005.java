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

public class JSEWD0005 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0005() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0005");

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

		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0005DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBObjList beanList = null;

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
				mc =
					new MessageContext(getMessageHandler("EWD0005",req));

				try {
					msgHelp =
						(EWD0005DSMessage) mc.getMessageRecord("EWD0005DS");
					msgHelp.setEWDUSR(msgUser.getH01USR());
					msgHelp.setEWDSHR(
						req.getParameter("NameSearch").toUpperCase());
					//msgHelp.setEWDSBK(req.getParameter("shrBank"));

					if (msgUser.getE01SEC().equals("Y")) {
						msgHelp.setEWDSBK(msgUser.getE01UBK());
					} else {
						msgHelp.setEWDSBK("");
					}

					msgHelp.setEWDSCA(req.getParameter("shrAppCode"));
					msgHelp.setEWDSEL(req.getParameter("shrSelect"));
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					//search by Account
					try {
						msgHelp.setEWDACC(req.getParameter("shrAcc"));
					} catch (Exception e) {
						msgHelp.setEWDACC("0");
					}
					//search by Client
					try {
						msgHelp.setEWDCUN(req.getParameter("shrClient"));
					} catch (Exception e) {
						msgHelp.setEWDCUN("0");
					}
					//search by type 'W'=word, 'S'=short name, 'N'=client number
					try {
						msgHelp.setEWDTYP(req.getParameter("shrType"));
					} catch (Exception e) {
						msgHelp.setEWDTYP("W");
					}
					//search by old account 'O'=old account
					if (req.getParameter("shrSelect").equals("O")) {
						msgHelp.setEWDTYP(req.getParameter("shrSelect"));
						msgHelp.setEWDOAC(msgHelp.getEWDACC());
					}
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0005DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0005DS")) {

						try {
							beanList = new JBObjList();
							flexLog("EWD0005DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";

						while (true) {

							msgHelp = (EWD0005DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();
							msgHelp.setHandler(null);

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
								
								
								beanList.addRow(msgHelp);

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("ewd0005Help", beanList);

						try {
							req.setAttribute(
								"NameSearch",
								req.getParameter("NameSearch"));
							req.setAttribute(
								"shrBank",
								req.getParameter("shrBank"));
							req.setAttribute(
								"shrAppCode",
								req.getParameter("shrAppCode"));
							req.setAttribute(
								"shrSelect",
								req.getParameter("shrSelect"));
							req.setAttribute(
								"shrClient",
								req.getParameter("shrClient"));
							req.setAttribute(
								"shrType",
								req.getParameter("shrType"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0005_client_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0005_client_help_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//return;
			} finally {
				mc.close();
			}

		}

	}
}