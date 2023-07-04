package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util; 
 
import datapro.eibs.sockets.*;

public class JSESD0800 extends datapro.eibs.master.SuperServlet {
	
	// entering options
	protected static final int R_SET_CUST 			= 100;
	protected static final int A_SET_CUST	 		= 200;
	protected static final int R_GET_CUST	 		= 300;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSESD0800() {
	super();
}


/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSESD0080");
	
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
protected void procActionSetCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD080001Message msgClient = null;
	ELEERRMessage msgError = null;
		
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	// Send Initial data
	try
	{
		msgClient = (ESD080001Message)mc.getMessageRecord("ESD080001");
	 	msgClient.setH01USR(user.getH01USR());
	 	msgClient.setH01PGM("ESD0800");
	 	msgClient.setH01TIM(getTimeStamp());
	 	msgClient.setH01SCR("01");
	 	msgClient.setH01OPE("0002");
	 	try {
		 	if (req.getParameter("E01CUSCUN") != null)
			 	msgClient.setE01CUSCUN(req.getParameter("E01CUSCUN"));
		}
		catch (Exception e)
		{
			msgClient.setE01CUSCUN("0");
		    flexLog("Input data error " + e);
		}
		try {
		 	if (req.getParameter("E01CUSIDN") != null)
			 	msgClient.setE01CUSIDN(req.getParameter("E01CUSIDN"));
		}
		catch (Exception e)
		{
			msgClient.setE01CUSIDN("");
		    flexLog("Input data error " + e);
		}
		
		msgClient.send();	
	 	msgClient.destroy();
	 	flexLog("ESD080001 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
		
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: /pages/" + LangPath + "ESD0800_client_enter.jsp");
				callPage(LangPath + "ESD0800_client_enter.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}	
	  }
	  else if (newmessage.getFormatName().equals("ESD080001")) {
			try {
				msgClient = new datapro.eibs.beans.ESD080001Message();
				flexLog("ESD080001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			
			msgClient = (ESD080001Message)newmessage;
			msgClient.setH01WK1("1");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("currClient", msgClient);
			
			try {
				flexLog("About to call Page: /pages/" + LangPath + "ESD0800_client_enter.jsp");
				callPage(LangPath + "ESD0800_client_enter.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
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
/**
 * This method was created in VisualAge.
 */
protected void procReqSetCustomer(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ESD080001Message msgClient = null;
	ELEERRMessage msgError = null;

	try {
		msgClient = new datapro.eibs.beans.ESD080001Message(); 
		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgClient.setH01WK1("OK");
		ses.setAttribute("currClient", msgClient);
		ses.setAttribute("error", msgError);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		flexLog("About to call Page: " + LangPath + "ESD0800_client_enter.jsp");
		callPage(LangPath + "ESD0800_client_enter.jsp", req, res);
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqGetCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD080001Message msgClient = null;
	ELEERRMessage msgError = null;
		
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	// Send Initial data
	try
	{
		msgClient = (ESD080001Message)mc.getMessageRecord("ESD080001");
	 	msgClient.setH01USR(user.getH01USR());
	 	msgClient.setH01PGM("ESD0800");
	 	msgClient.setH01TIM(getTimeStamp());
	 	msgClient.setH01SCR("01");
	 	msgClient.setH01OPE("0002");
	 	msgClient.setE01CUSCUN("0");
		msgClient.setE01CUSIDN("");
		
		try {
		 	if (req.getParameter("ACC") != null)
			 	msgClient.setE01ACCNUM(req.getParameter("ACC"));
		}
		catch (Exception e)
		{
			msgClient.setE01ACCNUM("0");
		    flexLog("Input data error " + e);
		}
		
		msgClient.send();	
	 	msgClient.destroy();
	 	flexLog("ESD080001 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
		
			ses.setAttribute("error", msgError);
				
	  }
	  else if (newmessage.getFormatName().equals("ESD080001")) {
			
			
			msgClient = (ESD080001Message)newmessage;
			msgClient.setH01WK1("1");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("selClient", msgClient);
			
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

		int screen = R_SET_CUST;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				// BEGIN Personal
				
				// Request
				
				case R_SET_CUST : 
					procReqSetCustomer(msgUser, req, res, session);
					break;
				
				case A_SET_CUST : 
					procActionSetCustomer(mc, msgUser, req, res, session);
					break;
					
				case R_GET_CUST : 
					procReqGetCustomer(mc, msgUser, req, res, session);
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
}