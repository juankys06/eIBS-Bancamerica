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

public class JSEWD0001D extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0001D() {
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
		
		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0001RMessage msgHelp = null;
		EWD0001SMessage msgHelpS = null;
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
			String Custype = "";
			try {
				flexLog("Opennig Socket Connection");
				mc =
					new MessageContext(getMessageHandler("EWD0001",req));

				try {
					msgHelp = (EWD0001RMessage) mc.getMessageRecord("EWD0001R");
					msgHelp.setRWDUSR(msgUser.getH01USR());
					msgHelp.setRWDSHR(
						req.getParameter("NameSearch").toUpperCase());
					msgHelp.setRWDTYP(req.getParameter("Type"));
					session.setAttribute("Type", msgHelp.getRWDTYP());
					msgHelp.setRWDFRC(req.getParameter("FromRecord"));
					try {
						Custype =
							(req.getParameter("CusType") == null)
								? ""
								: req.getParameter("CusType");
					} catch (Exception e) {
						Custype = "";
					}
					//msgHelp.setRWDSEL(Custype);
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0001R Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0001S")) {

						try {
							beanList = new JBList();
							flexLog("EWD0001S Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";
						String idn = "";

						while (true) {

							msgHelpS = (EWD0001SMessage) newmessage;

							marker = msgHelpS.getSWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelpS.getSWDREC()));
									chk = "checked";
								} else {
									chk = "";
								}
								
								if (msgHelpS.getSWDIDN().equals(""))
									idn = msgHelpS.getSWDLN3();
								else
									idn = msgHelpS.getSWDIDN();
								
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelpS.getSWDCUN()
										+ "','"
										+ msgHelpS.getSWDNA1()
										+ "','"
										+ idn
										+ "','"
										+ msgHelpS.getSWDTID()
										+ "','"
										+ msgHelpS.getSWDPID()
										+ "','"
										+ msgHelpS.getSWDNA2()
										+ "','"
										+ msgHelpS.getSWDNA3()
										+ "','"
										+ msgHelpS.getSWDNA4()
										+ "','"
										+ msgHelpS.getSWDCTY()
										+ "','"
										+ msgHelpS.getSWDSTE()
										+ "','"
										+ msgHelpS.getSWDCTR()
										+ "','"
										+ msgHelpS.getSWDPOB()
										+ "','"
										+ msgHelpS.getSWDZPC()
										+ "','"
										+ msgHelpS.getSWDCCS()
										+ "','"
										+ msgHelpS.getSWDEDL()
										+ "','"
										+ msgHelpS.getSWDSEX()
										+ "','"
										+ msgHelpS.getSWDMST()
										+ "','"
										+ msgHelpS.getSWDHPN()										
										+ "')\">"
										+ msgHelpS.getSWDCUN()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelpS.getSWDCUN()
										+ "','"
										+ msgHelpS.getSWDNA1()
										+ "','"
										+ idn
										+ "','"
										+ msgHelpS.getSWDTID()
										+ "','"
										+ msgHelpS.getSWDPID()
										+ "','"
										+ msgHelpS.getSWDNA2()
										+ "','"
										+ msgHelpS.getSWDNA3()
										+ "','"
										+ msgHelpS.getSWDNA4()
										+ "','"
										+ msgHelpS.getSWDCTY()
										+ "','"
										+ msgHelpS.getSWDSTE()
										+ "','"
										+ msgHelpS.getSWDCTR()
										+ "','"
										+ msgHelpS.getSWDPOB()
										+ "','"
										+ msgHelpS.getSWDZPC()
										+ "','"
										+ msgHelpS.getSWDCCS()
										+ "','"
										+ msgHelpS.getSWDEDL()
										+ "','"
										+ msgHelpS.getSWDSEX()
										+ "','"
										+ msgHelpS.getSWDMST()
										+ "','"
										+ msgHelpS.getSWDHPN()										
										+ "')\">"
										+ msgHelpS.getSWDNA1()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelpS.getSWDCUN()
										+ "','"
										+ msgHelpS.getSWDNA1()
										+ "','"
										+ idn
										+ "','"
										+ msgHelpS.getSWDTID()
										+ "','"
										+ msgHelpS.getSWDPID()
										+ "','"
										+ msgHelpS.getSWDNA2()
										+ "','"
										+ msgHelpS.getSWDNA3()
										+ "','"
										+ msgHelpS.getSWDNA4()
										+ "','"
										+ msgHelpS.getSWDCTY()
										+ "','"
										+ msgHelpS.getSWDSTE()
										+ "','"
										+ msgHelpS.getSWDCTR()
										+ "','"
										+ msgHelpS.getSWDPOB()
										+ "','"
										+ msgHelpS.getSWDZPC()
										+ "','"
										+ msgHelpS.getSWDCCS()
										+ "','"
										+ msgHelpS.getSWDEDL()
										+ "','"
										+ msgHelpS.getSWDSEX()
										+ "','"
										+ msgHelpS.getSWDMST()
										+ "','"
										+ msgHelpS.getSWDHPN()										
										+ "')\">"
										+ idn
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
						session.setAttribute("ewd0001Help", beanList);

						try {
							req.setAttribute(
								"NameSearch",
								req.getParameter("NameSearch"));
							req.setAttribute("Type", req.getParameter("Type"));
							req.setAttribute("CusType", Custype);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0001_client_desc_details_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0001_client_desc_details_help_helpmessage.jsp",
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
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			} finally {
				mc.close();
			}

		}

	}
}