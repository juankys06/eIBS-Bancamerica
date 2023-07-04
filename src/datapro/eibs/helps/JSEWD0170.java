package datapro.eibs.helps;

/**
 * This type was created by Frank Hernandez.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0170 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0170() {
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
		ELEERRMessage msgError = null;
		EWD0170DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = true;

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

				userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
				try {
					
					msgHelp = (EWD0170DSMessage)mc.getMessageRecord("EWD0170DS");
					
					try {
						msgHelp.setEWDSHO(req.getAttribute("SelOld").toString());
					} catch (Exception e) {
						msgHelp.setEWDSHO("");
					}
					try {
						msgHelp.setEWDSHN(req.getParameter("SelNew"));
					} catch (Exception e) {
						msgHelp.setEWDSHN("");
					}
					try {
						msgHelp.setEWDREC(req.getParameter("FromRecord"));
					} catch (Exception e) {
						msgHelp.setEWDREC("0");
					}
					try {
						msgHelp.setEWDRTP(req.getParameter("codeflag"));
					} catch (Exception e) {
						msgHelp.setEWDRTP("");
					}
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0170DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					//showERROR(msgError);
				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");				
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0170DS")) {
					beanList = new JBObjList();
					String marker = "";

					while (true) {
						msgHelp = (EWD0170DSMessage) newmessage;
						flexLog("EWD0170DS Message Received");					
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
					session.setAttribute("ewd0170Help", beanList);
					session.setAttribute("error", msgError);
					session.setAttribute("userPO", userPO);
					req.setAttribute("SelOld", req.getParameter("SelNew"));
					req.setAttribute("SelNew", req.getParameter("SelNew"));
					req.setAttribute("FromRecord", req.getParameter("FromRecord"));
					req.setAttribute("Type", req.getParameter("Type"));

					try {
						flexLog("About to call Page: " + LangPath + "EWD0170_dft_hlp_acpt_helpmessage.jsp");
						callPage(LangPath + "EWD0170_dft_hlp_acpt_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
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