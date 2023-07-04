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

public class JSEWD0113 extends datapro.eibs.master.SuperServlet {
	

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSEWD0113() {
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
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	Socket s = null;
	MessageContext mc = null;
  	HttpSession session;
	MessageRecord newmessage = null;
	EWD0113DSMessage msgHelp = null;
	ESS0030DSMessage msgUser = null;
	JBList beanList = null;

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
			msgHelp = (EWD0113DSMessage)mc.getMessageRecord("EWD0113DS");
			msgHelp.send();	
		 	msgHelp.destroy();
		 	flexLog("EWD0113DS Message Sent");
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
	  
	  		if (newmessage.getFormatName().equals("EWD0113DS")) {

				try {
					beanList = new JBList();
					flexLog("EWD0113DS Message Sent");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgHelp = (EWD0113DSMessage)newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						myRow = new StringBuffer("<TR>");
						myRow.append("<td nowrap><a href=\"javascript:enter('" + msgHelp.getEWDTBL() + "')\">" + msgHelp.getEWDTBL() + "</a></td>");
						myRow.append("<td nowrap><a href=\"javascript:enter('" + msgHelp.getEWDTBL() + "')\">" + msgHelp.getEWDCLS() + "</a></td>");
						myRow.append("<td nowrap><a href=\"javascript:enter('" + msgHelp.getEWDTBL() + "')\">" + msgHelp.getEWDDSC() + "</a></td>");
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
				session.setAttribute("ewd0113Help", beanList);

				try {
					 flexLog("About to call Page: " + LangPath + "EWD0113_insurance_par_table_helpmessage.jsp");
					 callPage(LangPath + "EWD0113_insurance_par_table_helpmessage.jsp", req, res);
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