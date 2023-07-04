package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import java.util.Calendar;
 
import datapro.eibs.sockets.*;

public class JSESD0795 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	static final int R_DEAL			= 1;
	static final int A_DEAL			= 2;
	
	// entering options
	static final int R_ENTER	 		= 100;
	static final int A_ENTER		 	= 200;
	
	private String LangPath = "S";

/**
 * JSEGL0034 constructor comment.
 */
public JSESD0795() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSESD0790");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

/**
 * This method was created in VisualAge.
 */
private  void  procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
	MessageRecord newmessage = null;
	ESD079001Message msgDeal = null;
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
		
		flexLog("Sending header");
		flexLog("header 1");
	 	
	 	msgDeal = (ESD079001Message)mc.getMessageRecord("ESD079001");
	 	msgDeal.setH01USERID(user.getH01USR());
//		msgDeal.setH01USERID("DESHMPR");
	 	msgDeal.setH01PROGRM("ESD0790");
	 	msgDeal.setH01TIMSYS(getTimeStamp());
	 	msgDeal.setH01SCRCOD("01");
	 	msgDeal.setH01OPECOD("0002");
	 	try {
		  msgDeal.setE01COTCDE(req.getParameter("E01COTCDE"));
	 	  	} 
		catch (Exception ex) {
		  msgDeal.setE01COTCDE("0"); 
  	    }
		msgDeal.send();	
	 	msgDeal.destroy();
	 	flexLog("ESD079001 Message Sent");
	}		
	catch (Exception e)	{
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

		if (newmessage.getFormatName().equals("ESD079001")) {
			
			msgDeal = (ESD079001Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("deal", msgDeal);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_deal_basic.jsp");
					callPage(LangPath + "ESD0795_deal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_deal_enter.jsp");
					callPage(LangPath + "ESD0795_deal_enter.jsp", req, res);	
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
/**
 * This method was created in VisualAge.
 */
private  void  procActionDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD079001Message msgDeal = null;
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
		flexLog("Sending header");
		flexLog("header 1");
	 	
	 	msgDeal = (ESD079001Message)mc.getMessageRecord("ESD079001");
	 	msgDeal.setH01USERID(user.getH01USR());
//		msgDeal.setH01USERID("DESHMPR");
	 	msgDeal.setH01PROGRM("ESD0790");
	 	msgDeal.setH01TIMSYS(getTimeStamp());
	 	msgDeal.setH01SCRCOD("01");
	 	msgDeal.setH01OPECOD("0005");
	 	
		// all the fields here
	 	java.util.Enumeration enu = msgDeal.fieldEnumeration();
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

		msgDeal.send();	
	 	msgDeal.destroy();
	 	flexLog("ESD079001 Message Sent");
	}		
	catch (Exception e)	{
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

		if (newmessage.getFormatName().equals("ESD079001")) {
			
			msgDeal = (ESD079001Message)newmessage;
			//flexLog("" + msgDeal);
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("deal", msgDeal);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_deal_enter.jsp");
					callPage(LangPath + "ESD0795_deal_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_deal_basic.jsp");
					callPage(LangPath + "ESD0795_deal_basic.jsp", req, res);	
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
/**
 * This method was created in VisualAge.
 */
private  void  procReqDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD079001Message msgDeal = null;
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
		flexLog("Sending header");
		flexLog("header 1");
	 	
	 	msgDeal = (ESD079001Message)mc.getMessageRecord("ESD079001");
	 	msgDeal.setH01USERID(user.getH01USR());
//		msgDeal.setH01USERID("DESHMPR");
	 	msgDeal.setH01PROGRM("ESD0790");
	 	msgDeal.setH01TIMSYS(getTimeStamp());
	 	msgDeal.setH01SCRCOD("01");
	 	msgDeal.setH01OPECOD("0002");
	 	
		// all the fields here
	 	java.util.Enumeration enu = msgDeal.fieldEnumeration();
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

		msgDeal.send();	
	 	msgDeal.destroy();
	 	flexLog("ESD079001 Message Sent");
	}		
	catch (Exception e)	{
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

		if (newmessage.getFormatName().equals("ESD079001")) {
			
			msgDeal = (ESD079001Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("deal", msgDeal);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_deal_basic.jsp");
					callPage(LangPath + "ESD0795_deal_basic.jsp", req, res);	
				 }
				 catch (Exception e) {
					flexLog("Exception calling page " + e);
				 }
				
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0795_enter_basic.jsp");
					callPage(LangPath + "ESD0795_enter_basic.jsp", req, res);	
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
/**
 * This method was created in VisualAge.
 */
private  void  procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	ESD079001Message msgDeal = null;
	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		msgDeal = new datapro.eibs.beans.ESD079001Message();
		userPO.setOption("TRANSACTION");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("deal", msgDeal);
		
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

  	
	try {
		flexLog("About to call Page: " + LangPath + "ESD0795_deal_enter.jsp");
		callPage(LangPath + "ESD0795_deal_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
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

		int screen = R_ENTER;
		
		try {

			flexLog("Screen  Number: " + screen);
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

//			LangPath = "/pages/s/";
			
			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);

//				s = new Socket(super.hostIP, 35000 + 1);
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
				// Request
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				case R_DEAL : 
					procReqDeal(mc, msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER : 
					procActionEnter(mc, msgUser, req, res, session);
					break;
				case A_DEAL : 
					procActionDeal(mc, msgUser, req, res, session);
					break;	
				// END Entering

				default :
					res.sendRedirect(LangPath + super.devPage);
					break;
			}

			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(LangPath + super.sckNotOpenPage);
//				return;
			} finally {
				s.close();
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(LangPath + super.sckNotRespondPage);
		}

	}
	
}
      
private  void  showERROR(ELEERRMessage m)
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
}
