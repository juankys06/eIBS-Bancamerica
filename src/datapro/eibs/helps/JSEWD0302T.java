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

public class JSEWD0302T extends datapro.eibs.master.SuperServlet {
	private Socket s = null;
	private MessageContext mc = null;
  	private HttpSession session;

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSEWD0302T() {
	super();	
}
/**
 * This method was created by David Mavilla
public void destroy() {

	flexLog("free resources used by JSESS00    40");
	
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
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0302DSMessage msgHelp = null;
	ESS0030DSMessage msgUser = null;
	JBObjList beanList = null;

	session = (HttpSession)req.getSession(false);
	
	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	}
	else {

		msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");
		String Language = msgUser.getE01LAN();
		String LangPath = super.rootPath + Language + "/";

		try
		{
			flexLog("Opennig Socket Connection");
			s = new Socket(super.hostIP, getInitSocket(req) + 1);
			s.setSoTimeout(super.sckTimeOut);
		  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
						      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
								    "datapro.eibs.beans");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			int sck = getInitSocket(req) + 1;
			flexLog("Socket not Open(Port " + sck + "). Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
			return;
		}

	  	try {
			msgHelp = (EWD0302DSMessage)mc.getMessageRecord("EWD0302DS");
			msgHelp.setSWDCUN(req.getParameter("shrCust"));
			msgHelp.setRWDFRC(req.getParameter("FromRecord"));
			msgHelp.setSWDPRFFL1("A");
			msgHelp.send();	
		 	msgHelp.destroy();
		 	flexLog("EWD0302DS Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
			
		// Receiving
		try
		{
	  		newmessage = mc.receiveMessage();
	  
	  		if (newmessage.getFormatName().equals("EWD0302DS")) {

				try {
					beanList = new datapro.eibs.beans.JBObjList();
					flexLog("EWD0302DS Message Recived");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String myDesc = "";


				while (true) {

					msgHelp = (EWD0302DSMessage)newmessage;

					marker = msgHelp.getSWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						
						beanList.addRow(msgHelp);
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
				flexLog("Putting java beans into the session");
				session.setAttribute("ewd0302Help", beanList);
				try {
					 flexLog("About to call Page: " + LangPath + "EWD0302_inv_port_helpmessage.jsp");
					  callPage(LangPath + "EWD0302_inv_port_helpmessage.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

		try {
			s.close();
		}
		catch (Exception e) {
			flexLog("Error closing socket connection " + e);
		}
	}

}
}