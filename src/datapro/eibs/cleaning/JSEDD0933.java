package datapro.eibs.cleaning;

/**
 * Insert the type's description here.
 * Creation date: (7/03/03 6:08:55 PM)
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

public class JSEDD0933 extends datapro.eibs.master.SuperServlet {

    // entering options
	protected static final int R_CONTROL				= 100;
	

	protected static final int A_CONTROL	 			= 200;

	protected String LangPath = "S";

/**
 * JSEDD0933 constructor comment.
 */
public JSEDD0933() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDD0933");
	
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
protected void procReqControl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD093301Message msgCTRL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgCTRL = (EDD093301Message)mc.getMessageRecord("EDD093301");
		msgCTRL.setH01USERID(user.getH01USR());
	 	msgCTRL.setH01PROGRM("EDD0933");
	 	msgCTRL.setH01TIMSYS(getTimeStamp());
	 	msgCTRL.setH01SCRCOD("01");
	 	msgCTRL.setH01OPECOD("0004");

	 	msgCTRL.send();
	 	msgCTRL.destroy();
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
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDD093301")) {
			
			msgCTRL = (EDD093301Message)newmessage;
			
			flexLog("Putting java beans into the session");

			if (IsNotError) {  // There are no errors
				ses.setAttribute("error", msgError);
				ses.setAttribute("cleaningCNTRL", msgCTRL);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0933_cleaning_ctrl.jsp");
					callPage(LangPath + "EDD0933_cleaning_ctrl.jsp", req, res);		
				}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				
				ses.setAttribute("error", msgError);
				
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

		int screen = R_CONTROL;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 29);
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
				case R_CONTROL :
					procReqControl(mc, msgUser, req, res, session);
					break;
				case A_CONTROL :
					procActionControl(mc, msgUser, req, res, session);
					break;				
				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 29;
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

protected void showERROR(ELEERRMessage m)
{
	if (logType != NONE) {
		
		flexLog("ERROR received.");
		
		flexLog("ERROR number:" + m.getERRNUM());
		flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
		flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
		flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
		flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
		flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
		flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
		flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
		flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
		flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
		flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());
		
	}
}


/**
 * This method was created in VisualAge.
 */
protected void procActionControl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD093301Message msgCTRL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgCTRL = (EDD093301Message)mc.getMessageRecord("EDD093301");
		msgCTRL.setH01USERID(user.getH01USR());
	 	msgCTRL.setH01PROGRM("EDD0933");
	 	msgCTRL.setH01TIMSYS(getTimeStamp());
	 	msgCTRL.setH01SCRCOD("01");
	 	msgCTRL.setH01OPECOD("0002");

		// all the fields here
	 	java.util.Enumeration enu = msgCTRL.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	msgCTRL.send();
	 	msgCTRL.destroy();
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
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDD093301")) {
			
			msgCTRL = (EDD093301Message)newmessage;
			

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDD0933_cleaning_confirm.jsp");
					callPage(LangPath + "EDD0933_cleaning_confirm.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cleaningCNTRL", msgCTRL);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0933_cleaning_ctrl.jsp");
					callPage(LangPath + "EDD0933_cleaning_ctrl.jsp", req, res);	
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