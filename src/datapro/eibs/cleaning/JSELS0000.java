package datapro.eibs.cleaning;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSELS0000 extends datapro.eibs.master.SuperServlet {

    // entering options
	protected static final int A_ENTER				= 100;	
	
	protected String LangPath = "S";

/**
 * JSEDD0931 constructor comment.
 */
public JSELS0000() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSELS0000");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

/**
 * This method was created in VisualAge.
 */

public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;

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

		int screen = A_ENTER;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
			
			
			
			try
				{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
		
					switch (screen) {
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;			
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				}
			catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
					s.close();
			}
			
			
		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELS000001Message msgBATCH = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgBATCH = (ELS000001Message)mc.getMessageRecord("ELS000001");
		msgBATCH.setH01USERID(user.getH01USR());
	 	msgBATCH.setH01PROGRM("ELS0000");
	 	msgBATCH.setH01TIMSYS(getTimeStamp());
	 	msgBATCH.setH01SCRCOD("01");
	 	
		// all the fields here
	 	try {
				msgBATCH.setE01COLATR(req.getParameter("COLA"));
		} catch (Exception e) {
				msgBATCH.setE01COLATR("0");
		}
		try {
				msgBATCH.setE01NOMPRG(req.getParameter("PROG"));
		} catch (Exception e) {
				msgBATCH.setE01NOMPRG("");
		}
		try {
				msgBATCH.setE01NOMTRA(req.getParameter("TRAB"));
		} catch (Exception e) {
				msgBATCH.setE01NOMTRA("0");
		}
		try {
				msgBATCH.setE01ACTION(req.getParameter("ACTION"));
		} catch (Exception e) {
				msgBATCH.setE01ACTION("0");
		}
	 	msgBATCH.send();
	 	msgBATCH.destroy();
	
		
	// Receive Error Message
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	
	// Receive Data
	
	   newmessage = mc.receiveMessage();
	   		
	   if (newmessage.getFormatName().equals("ELS000001")) {
			
						
			msgBATCH = (ELS000001Message)newmessage;
				
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("batch", msgBATCH);
			
			if (IsNotError) {  // There are no errors
								
				try {
					flexLog("About to call Page: " + LangPath + "ELS0000_cleaning_batch.jsp");
					callPage(LangPath + "ELS0000_cleaning_batch.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors												
				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}								
			}
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}
}