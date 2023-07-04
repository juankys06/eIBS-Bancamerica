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

public class JSEWD0023 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0023() {
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
		EWD0023DSMessage msgHelp = null;
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
				
					msgHelp =
						(EWD0023DSMessage) mc.getMessageRecord("EWD0023DS");
					try {
						msgHelp.setEWDSHR(
							req.getParameter("NameSearch").toUpperCase());
					} catch (Exception e) {
						msgHelp.setEWDSHR("");
					}
					try {
						msgHelp.setEWDREC(req.getParameter("FromRecord"));
					} catch (Exception e) {
						msgHelp.setEWDREC("0");
					}
					try {
						msgHelp.setEWDTYP(req.getParameter("Type"));
					} catch (Exception e) {
						msgHelp.setEWDSTY("*");
					}
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0023DS Message Sent");
				

					newmessage = mc.receiveMessage();
					
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = new ELEERRMessage();
						msgError = (ELEERRMessage)newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						//showERROR(msgError);
					} 
					 else {
					
						//newmessage = mc.receiveMessage();
	
						if (newmessage.getFormatName().equals("EWD0023DS")) {
							beanList = new JBObjList();
							String marker = "";
		
							while (true) {
								msgHelp = (EWD0023DSMessage) newmessage;
								flexLog("EWD0023DS Message Received");					
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
							session.setAttribute("ewd0023Help", beanList);
							session.setAttribute("error", msgError);
							session.setAttribute("userPO", userPO);
							req.setAttribute("NameSearch", req.getParameter("NameSearch"));
							req.setAttribute("FromRecord", req.getParameter("FromRecord"));
							req.setAttribute("Type", req.getParameter("Type"));
		
							try {
								flexLog("About to call Page: " + LangPath + "EWD0023_brkr_help_helpmessage.jsp");
								callPage(LangPath + "EWD0023_brkr_help_helpmessage.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
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