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

public class JSEWD0118 extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";

	protected static final int R_R04M07 = 1;
	protected static final int R_R04M03 = 2;
	protected static final int R_CHKBOOK = 3;
	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0118() {
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
	protected void procReqR04M03(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0118DSMessage msgHelp = null;
		//ESS0030DSMessage msgUser = null;
		JBList beanList = null;

		try {
			msgHelp = (EWD0118DSMessage) mc.getMessageRecord("EWD0118DS");
			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0118DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0118DS")) {

				try {
					beanList = new JBList();
					flexLog("EWD0118DS Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgHelp = (EWD0118DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDCCY()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDATY()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDTBL()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDCLS()
								+ "</a></td>");		
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDDSC()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTBL()
								+ "','"
								+ msgHelp.getEWDCLS()								
								+ "')\">"
								+ msgHelp.getEWDNCK()
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
				ses.setAttribute("ewd0118Help", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EWD0118_rt_typ_check_helpmessage.jsp");
					callPage(
						LangPath + "EWD0118_rt_typ_check_helpmessage.jsp",
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

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqR04M07(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0118DSMessage msgHelp = null;
		//ESS0030DSMessage msgUser = null;
		JBList beanList = null;

		try {
			msgHelp = (EWD0118DSMessage) mc.getMessageRecord("EWD0118DS");
			try {
				msgHelp.setEWDATY(req.getParameter("PRODTYP"));
			} catch (Exception e) {
			}
			try {
				msgHelp.setEWDCCY(req.getParameter("CCY"));
			} catch (Exception e) {
			}
			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0118DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0118DS")) {

				try {
					beanList = new JBList();
					flexLog("EWD0118DS Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgHelp = (EWD0118DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTCB()
								+ "')\">"
								+ msgHelp.getEWDTCB()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTCB()
								+ "')\">"
								+ msgHelp.getEWDATY()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTCB()
								+ "')\">"
								+ msgHelp.getEWDCCY()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTCB()
								+ "')\">"
								+ msgHelp.getEWDDSC()
								+ "</a></td>");
						myRow.append(
							"<td nowrap><a href=\"javascript:enter('"
								+ msgHelp.getEWDTCB()
								+ "')\">"
								+ msgHelp.getEWDNCK()
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
				ses.setAttribute("ewd0118Help", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EWD0118_rt_typ_check_helpmessage.jsp");
					callPage(
						LangPath + "EWD0118_rt_typ_check_helpmessage.jsp",
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

	}

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqTypChkBook(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0118DSMessage msgHelp = null;
		//ESS0030DSMessage msgUser = null;
		JBObjList beanList = null;

		try {
			msgHelp = (EWD0118DSMessage) mc.getMessageRecord("EWD0118DS");
			try {
				msgHelp.setEWDATY(req.getParameter("PRODTYP"));
			} catch (Exception e) {
			}
			try {
				if (req.getParameter("CCY2")== null ) { 
					msgHelp.setEWDCCY(req.getParameter("CCY"));
				}
				
			} catch (Exception e) {
			}
			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0118DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0118DS")) {

				try {
					beanList = new JBObjList();
					flexLog("EWD0118DS Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				//String myFlag = "";
				//String myRow = "";

				while (true) {

					msgHelp = (EWD0118DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgHelp);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ewd0118Help", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EWD0118_rt_typ_chkbook_helpmessage.jsp");
					callPage(
						LangPath + "EWD0118_rt_typ_chkbook_helpmessage.jsp",
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
		EWD0118DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		//JBList beanList = null;

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

			int screen = R_R04M07;

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
					"currUser");
			String Language = msgUser.getE01LAN();
			LangPath = super.rootPath + Language + "/";

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
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					//Request
					case R_R04M07 :
						procReqR04M07(mc, msgUser, req, res, session);
						break;
					case R_R04M03 :
						procReqR04M03(mc, msgUser, req, res, session);
						break;
					case R_CHKBOOK :
						procReqTypChkBook(mc, msgUser, req, res, session);
						break;
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