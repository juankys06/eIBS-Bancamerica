package datapro.eibs.alerts;

/**
 * Insert the type's description here.
 * Creation date: (9/13/2001 9:24:07 AM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;
import datapro.eibs.master.Util;
 
public class JSESD0000 extends datapro.eibs.master.SuperServlet {
	
	private Socket s = null;
	private MessageContext mc = null;
  	private HttpSession session;

	private String LangPath = "S";
/**
 * JSESD0000 constructor comment.
 */
public JSESD0000() {
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

	MessageRecord newmessage = null;
	ESD000012Message msgHelp = null;
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

		msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getValue("currUser");
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
			res.sendRedirect(LangPath + super.sckNotOpenPage);
			return;
		}

	  	try {
			msgHelp = (ESD000012Message)mc.getMessageRecord("ESD000012");
			msgHelp.setE12ACC(req.getParameter("ACCNUM"));
			msgHelp.setE12TYP("W");
			msgHelp.send();	
		 	msgHelp.destroy();
		 	flexLog("ESD000012 Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(LangPath + super.sckNotRespondPage);
			return;
		}
			
		// Receiving
		try
		{
	  		newmessage = mc.receiveMessage();
	  
	  		if (newmessage.getFormatName().equals("ESD000012")) {

				try {
					beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
					flexLog("EWD000012 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				String myRow = "";

				while (true) {

					msgHelp = (ESD000012Message)newmessage;

					marker = msgHelp.getH12MAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						myRow =  "<TR valign=TOP height=\"2%\">";
						myRow += "<td nowrap><IMG border=\"0\" src=\"../images/warning01.gif\"></td>";
						myRow += "<td nowrap>" + msgHelp.getE12DSC() + "</td>";
						myRow += "</TR>";
						beanList.addRow(myFlag, myRow);
										
					}
					newmessage = mc.receiveMessage();
				}
						
				flexLog("Putting java beans into the session");
				session.putValue("warnings", beanList);

				try {
					if (req.getParameter("APP") == null) {
					 flexLog("About to call Page: " + LangPath + "warning_viewer.jsp");
					 callPage(LangPath + "warning_viewer.jsp", req, res);
					}else {
					 flexLog("About to call Page: " + LangPath + "warning_ap_viewer.jsp");
					 callPage(LangPath + "warning_ap_viewer.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(LangPath + super.sckNotRespondPage);
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
