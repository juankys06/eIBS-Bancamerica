package datapro.eibs.helps;

/**
 * @author C. Castillo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0406 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (10/02/07)
	 */
	public JSEWD0406() {
		super();
	}
	/**
	 * 
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0406");

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
		EWD0406DSMessage msgHelp = null; 
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;
		int screen = 1;
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
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch (Exception e) {
				flexLog("Screen set to default value");
			}

			switch (screen) {
			//	Request from Menu
			case 100 :
				procReqEnterInquiry(msgUser, req, res, session);
				break;
			//	Request from Help
			default :	
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
					msgHelp = (EWD0406DSMessage) mc.getMessageRecord("EWD0406DS");
					try {
						msgHelp.setEWDSBK(req.getParameter("selBank"));
					} catch (Exception e) {
						msgHelp.setEWDSBK("  ");
					}
					try {
						msgHelp.setEWDOAC(req.getParameter("selOldAcc"));
					} catch (Exception e) {
						msgHelp.setEWDOAC("0");
					}
					try {
						msgHelp.setEWDACC(req.getParameter("selNewAcc"));
					} catch (Exception e) {
						msgHelp.setEWDACC("0");
					}
					try {
						msgHelp.setEWDSBR(req.getParameter("selBranch").trim());
					} catch (Exception e) {
						msgHelp.setEWDSBR("0");
					}
					try {
						msgHelp.setEWDSCY(req.getParameter("selCurrency").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSCY("   ");
					}
					try {
						msgHelp.setEWDSTY(req.getParameter("selPrdType").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSTY(" ");
					}
					try {
						msgHelp.setEWDSCU(req.getParameter("selCustomer").trim());
					} catch (Exception e) {
						msgHelp.setEWDSCU("0");
					}
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0406DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0406DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0406DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						
						while (true) {

							msgHelp = (EWD0406DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(Integer.parseInt(msgHelp.getEWDREC()));
								}
								String enter = "<a href=\"javascript:enter('"
									+ msgHelp.getEWDACC()
									+ "')\"> ";	

								myRow = new StringBuffer("<TR>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDOAC())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDACC())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDSTS())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDATY())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDCUN())
										+ "</a></td>");
								myRow.append("<td nowrap align=left>" + enter
										+ msgHelp.getEWDDSC()
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDBNK())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(msgHelp.getEWDBRN())
										+ "</a></td>");
								myRow.append("<td nowrap align=center>" + enter
										+ Util.formatCell(
											msgHelp.getEWDCCY().toUpperCase())
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
						session.setAttribute("ewd0406Help", beanList);

						try {
							req.setAttribute("selOdlAcc",req.getParameter("selOldAcc"));
							req.setAttribute("selNewAcc",req.getParameter("selNewAcc"));
							req.setAttribute("selBank",req.getParameter("selBank"));
							req.setAttribute("selBranch",req.getParameter("selBranch"));
							req.setAttribute("selPrdType",req.getParameter("selPrdType"));
							req.setAttribute("selCurrency",req.getParameter("selCurrency"));
							req.setAttribute("selCustomer",req.getParameter("selCustomer"));
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0406_old_acc_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0406_old_acc_help_helpmessage.jsp", req, res);
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
		break;	
		}
		}
	}

	/**
	 * Old Account Search Screen
	 */
	protected void procReqEnterInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		ESS0030DSMessage msgUser = null;
		UserPos userPO = null;
		msgUser =
			(datapro.eibs.beans.ESS0030DSMessage) ses.getAttribute(
				"currUser");
		String Language = msgUser.getE01LAN();
		String LangPath = super.rootPath + Language + "/";

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EWD0406_old_acc_help_container.jsp");
			callPage(LangPath + "EWD0406_old_acc_help_container.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
		
}