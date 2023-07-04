package datapro.eibs.bolgaran;

/**
 * Insert the type's description here.
 * Creation date: (3/19/02 10:08:55 PM)
 * @author: Ramses Amaro
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

public class JSELC5560 extends datapro.eibs.master.SuperServlet {

	// Request 
	protected static final int R_PAY_CANCEL			= 100;
	protected static final int R_ENTER				= 200;
	
	// actions
	protected static final int A_PAY_CANCEL			= 3;
	protected static final int A_ENTER				= 2;
	
	protected static final int R_PASSWORD 			= 1;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELC5560() {
	super();
}
/**
 * This method was created in VisualAge.
 */
public void destroy() {

	flexLog("free resources used by JSELC5560");
	
}
/**
 * This method was created in VisualAge.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

/**
 * Metodos del Menu "Mantenedor de Boletas 
 */

protected void procActionPayCancel(MessageContext mc,ESS0030DSMessage user,  HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException 
{
	
	MessageRecord newmessage = null;
	ELC556001Message msgBol = null;
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
		msgBol = (ELC556001Message)ses.getAttribute("bolgaran"); //mc.getMessageRecord("ELC556001");
		msgBol.setH01USERID(user.getH01USR());
	 	msgBol.setH01PROGRM("ELC5560");
	 	msgBol.setH01SCRCOD("01");
	    msgBol.setH01OPECOD("0005");
	    
	  	// all the fields here
	 	java.util.Enumeration enu = msgBol.fieldEnumeration();
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
	    
	 	mc.sendMessage(msgBol);
	 	msgBol.destroy();
	 	flexLog("ELC556001 Message Sent");
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


		if (newmessage.getFormatName().equals("ELC556001")) {
			try {
				msgBol = new datapro.eibs.beans.ELC556001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBol = (ELC556001Message)newmessage;
			ses.setAttribute("userPO", userPO);
			
			if (IsNotError) {  // There are no errors
		 		
				try {
					flexLog("About to call Page: " + LangPath + "ELC5560_bg_enter_paycancel.jsp");
					callPage(LangPath + "ELC5560_bg_enter_paycancel.jsp", req, res);					
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				ses.setAttribute("error", msgError);
				ses.setAttribute("bolgaran", msgBol);		 		 	    
				try {
					flexLog("About to call Page: " + LangPath + "ELC5560_bg_paycancel.jsp");
					callPage(LangPath + "ELC5560_bg_paycancel.jsp", req, res);				
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
	

protected void procReqEnter(MessageContext mc,ESS0030DSMessage user,  HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException 

{
	ELC556001Message msgBol= null;	
	UserPos	userPO = null;
	ELEERRMessage msgError = null;
	boolean IsNotError = false;
	
	try {
		msgBol = new ELC556001Message();
	} catch (Exception e) {
		flexLog("Error: " + e);
	}

	try {
		userPO = new UserPos();
	} catch (Exception e) {
		flexLog("Error: " + e);
	}
	
	userPO.setPurpose("MAINTENANCE");
	userPO.setOption("0");
	ses.setAttribute("bolgaran", msgBol);
	ses.setAttribute("userPO", userPO);

	try 
		{
			flexLog("About to call Page: " + LangPath + "ELC5560_bg_enter_paycancel.jsp");
			callPage(LangPath + "ELC5560_bg_enter_paycancel.jsp", req, res);	
		}
	catch (Exception e) 
		{
			flexLog("Exception calling page " + e);
		}	
	
}

protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
	MessageRecord newmessage = null;
	ELC556001Message msgBol = null;
	ELC500004Message msgAviBen = null;
	ELEERRMessage msgError = null;
	ELEERRMessage msgError2 = null;	
	
	UserPos	userPO = null;	
	boolean IsNotError = false;
	String opt = "";
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	// Send Initial data
	try
	{
		msgBol = (ELC556001Message)mc.getMessageRecord("ELC556001");
	 	msgBol.setH01USERID(user.getH01USR());
	 	msgBol.setH01PROGRM("ELC5560");
	 	msgBol.setH01TIMSYS(getTimeStamp());
	 	msgBol.setH01SCRCOD("01");
	 	msgBol.setH01OPECOD("0002");
	 	msgBol.setE01LCMACD("43");
		try {
			msgBol.setE01LCMACC(req.getParameter("E01LCMACC"));
			userPO.setIdentifier(msgBol.getE01LCMACC());
		}
		catch (Exception e)	{
			msgBol.setE01LCMACC(userPO.getIdentifier());
		}
		try {
			msgBol.setE01TYPOPE(req.getParameter("OPT"));
		}
		catch (Exception e)	{
			msgBol.setE01TYPOPE("1");
		}
		//userPO.setOption(opt);
		
		msgBol.send();	
	 	msgBol.destroy();
	 	flexLog("ELC556001 Message Sent");
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

		if (newmessage.getFormatName().equals("ELC556001")) {
			try {
				msgBol = new datapro.eibs.beans.ELC556001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBol = (ELC556001Message)newmessage;
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			
			if (IsNotError) {  // There are no errors
//JLS
				try
				{
					msgAviBen = (ELC500004Message)mc.getMessageRecord("ELC500004");
				 	msgAviBen.setH04USERID(user.getH01USR());
				 	msgAviBen.setH04PROGRM("ELC5000");
				 	msgAviBen.setH04TIMSYS(getTimeStamp());
				 	msgAviBen.setH04SCRCOD("01");
				    msgAviBen.setH04OPECOD("0002");
				 	msgAviBen.setE04LCMACC(req.getParameter("E01LCMACC"));
				 	msgAviBen.send();
				 	msgAviBen.destroy();
				}		
			
				catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
			  	throw new RuntimeException("Socket Communication Error");
				}

				try
				{
				  newmessage = mc.receiveMessage();
	  
				  if (newmessage.getFormatName().equals("ELEERR")) {
					msgError2 = (ELEERRMessage)newmessage;
					IsNotError = msgError2.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError2);
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
					if (newmessage.getFormatName().equals("ELC500004")) {
			
					  msgAviBen = (ELC500004Message)newmessage;
				   	  ses.setAttribute("bolaviben", msgAviBen);
					}  
				}
			
				catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
			  	throw new RuntimeException("Socket Communication Error");
			  	
				}
//JLS				
				ses.setAttribute("bolgaran", msgBol);
				if (userPO.getPurpose().equals("APPROVAL_INQ")) {
					try {					
						flexLog("About to call Page: " + LangPath + "ELC5560_bg_app_paycancel.jsp");
						callPage(LangPath + "ELC5560_bg_app_paycancel.jsp",req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {				
					try {					
						flexLog("About to call Page: " + LangPath + "ELC5560_bg_paycancel.jsp");
						callPage(LangPath + "ELC5560_bg_paycancel.jsp",req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else {  // There are errors
				if (userPO.getPurpose().equals("APPROVAL_INQ")) {
					try {
						 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 		callPage(LangPath + "error_viewer.jsp", req, res);
					}
					catch (Exception e) {
				 		flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ELC5560_bg_enter_paycancel.jsp");
						callPage(LangPath + "ELC5560_bg_enter_paycancel.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
protected void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	try {
		//mod emat 01/24/2002
		//send draft parameter
						
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.bolgaran.JSELC5560?SCREEN=" + R_ENTER + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
		ses.setAttribute("userPO", userPO);
		res.sendRedirect(super.srctx +"/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}

public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
   
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
	else 
{

		int screen = R_ENTER;
		
		try {

			flexLog("Screen  Number: " + screen);
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, super.iniSocket + 7);
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
				case R_PASSWORD :
					 procReqPassword(req, res, session);
					 break;				
				case R_ENTER :
					 procReqEnter(mc, msgUser , req, res, session);
				     break;					
				case A_ENTER :
					 procActionEnter(mc, msgUser , req, res, session);
				     break; 
				case A_PAY_CANCEL :				     
					 procActionPayCancel(mc, msgUser , req, res, session);
 					 break;
				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
//				return;
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
}