package datapro.eibs.menu;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class JSESS0040 extends datapro.eibs.master.SuperServlet {

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSESS0040() {
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
	ESS0040DSMessage msgMenu = null;
	ESS0030DSMessage msgUser = null;

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
			s = new Socket(super.hostIP, getInitSocket(req));
			s.setSoTimeout(super.sckTimeOut);
		  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
						      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
								    "datapro.eibs.beans");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			int sck = getInitSocket(req);
			flexLog("Socket not Open(Port " + sck + "). Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
			return;
		}

		// set MINE type for HTTP Header
		res.setContentType("text/html");
		res.setHeader("Pragma", "No-cache");
		res.setDateHeader ("Expires", 0);
		res.setHeader("Cache-Control", "no-cache");
		// get a handle to the output stream
		PrintWriter out = res.getWriter();

		// Send Request
		flexLog("Sending Request. UserID = " + msgUser.getH01USR());
	  	try {
			msgMenu = (ESS0040DSMessage)mc.getMessageRecord("ESS0040DS");
		 	msgMenu.setESSUSR(msgUser.getH01USR());
		 	msgMenu.setESSTYP(Language.toUpperCase());
		 	msgMenu.send();	
		 	msgMenu.destroy();
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
		  
		  if (newmessage.getFormatName().equals("ESS0040DS")) {
			msgMenu = (ESS0040DSMessage)newmessage; 
			
		  	flexLog("Data received TYP = " + msgMenu.getESSTYP());
			if (msgMenu.getESSTYP().equals("T")) {
				
				int buttonId = 0;
		  		flexLog("Data received IDE = " + msgMenu.getESSIDE());
				int buttonNumber = Integer.parseInt(msgMenu.getESSIDE());
				flexLog("OK");

				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>IBS Menu</TITLE>");
				out.println("<SCRIPT SRC=\"" + super.webAppPath  + LangPath + "javascripts/eIBS.js\"></SCRIPT>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<APPLET archive=\"eibs_applets.zip\" CODE=datapro.eibs.applets.menu.AButtonTree.class WIDTH=100% HEIGHT=100% CODEBASE=\""  + super.webAppPath  + LangPath + "\">");
				//out.println("	<param name=backgroundColorRed value=145>");
				//out.println("	<param name=backgroundColorGreen value=146>");
				//out.println("	<param name=backgroundColorBlue value=162>");
				out.println("	<param name=imagePath value=\"" + getServerRoot(req) + super.webAppPath + "/images/" + "\">");

				flexLog("Total Number of Buttons = " + buttonNumber);
				out.println("	<param name=buttonNumber value=\"" + buttonNumber + "\">");

				int thisButtonNumber = -1;
				int optionNumber = -1;
				int thisOptionNumber = -1;
				String buttonCaption = "";
				String optionCaption = "";
				String optionImage = "";
				String optionURL = "";
				String optionTarget = "";
					
				while (true) {
					newmessage = mc.receiveMessage();
					msgMenu = (ESS0040DSMessage)newmessage; 
					flexLog("Receiving data");
			
					if ( msgMenu.getESSTYP().equals("M") ) {

						thisButtonNumber ++; // Integer.parseInt(msgMenu.getESSSID().substring(3)) - 1;
						optionNumber = Integer.parseInt(msgMenu.getESSIDE());
						buttonCaption = msgMenu.getESSDSC();

						thisOptionNumber = -1;

						out.println("		<param name=button" + thisButtonNumber + " value=\"" + buttonCaption + "\">");
						out.println("		<param name=imageNumberOfButton" + thisButtonNumber + " value=\"" + optionNumber + "\">");
					}
					else if ( msgMenu.getESSTYP().equals("C") ) {
					
						thisOptionNumber ++; // Integer.parseInt(msgMenu.getESSIDE()) - 1;
						optionCaption = msgMenu.getESSDSC();
						optionImage = msgMenu.getESSIMG();
						optionURL = super.webAppPath + msgMenu.getESSTAD();
						optionTarget = msgMenu.getESSTPO();

						out.println("			<param name=button" + thisButtonNumber + "imageName" + thisOptionNumber + " value=\"" + optionImage + "\">");
						out.println("			<param name=button" + thisButtonNumber + "imageCaption" + thisOptionNumber + " value=\"" + optionCaption + "\">");
						out.println("			<param name=button" + thisButtonNumber + "imageURL" + thisOptionNumber + " value=\"" + optionURL + "\">");
						out.println("			<param name=button" + thisButtonNumber + "imageTarget" + thisOptionNumber + " value=\"" + optionTarget + "\">");
					}
					else if ( msgMenu.getESSTYP().equals("*") ) {
						break;
					}
				}

				out.println("</APPLET>");
				out.println("</BODY>");
				out.println("</HTML>");
				
			}
			else if (msgMenu.getESSTYP().equals("*")) {
				res.sendRedirect(super.srctx + LangPath + "ESS0040_menu_access_denied.jsp");
			}
			else {
				res.sendRedirect(super.srctx + LangPath + "ESS0040_menu_access_denied.jsp");
			}
		  }
		  else
			flexLog("Message " + newmessage.getFormatName() + " received.");
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