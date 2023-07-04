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

public class JSEWB0110 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWB0110() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWB0010");

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
	HttpSession ses;
	MessageRecord newmessage = null;
	ESB011001Message msgSearch = null;
	ESB011001Message msgList = null;
	ESS0030DSMessage msgUser = null;
		
	JBObjList beanList = null;

	ses = (HttpSession) req.getSession(false);

	if (ses == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	} else {

		msgUser =
			(datapro.eibs.beans.ESS0030DSMessage) ses.getAttribute(
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

			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgSearch = (ESB011001Message) mc.getMessageRecord("ESB011001");
				msgSearch.setH01USERID(msgUser.getH01USR());
				msgSearch.setH01PROGRM("ESB0110");
				msgSearch.setH01TIMSYS(getTimeStamp());
				msgSearch.setH01SCRCOD("01");
				msgSearch.setH01OPECOD("0015");
		    	
				msgSearch.send();
				msgSearch.destroy();
				flexLog("ESB011001 Message Sent");

				// Receive Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ESB011001")) {
					beanList = new JBObjList();
					String marker = "";

					while (true) {
						msgList = (ESB011001Message) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							beanList.addRow(msgList);
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("msgList", beanList);
				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}
			
				try {
					flexLog("About to call Page: " + LangPath + "EWB0110_safe_deposit_help.jsp");
					callPage(LangPath + "EWB0110_safe_deposit_help.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
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