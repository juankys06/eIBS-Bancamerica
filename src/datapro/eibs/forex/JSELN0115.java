package datapro.eibs.forex;

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
 
import datapro.eibs.sockets.*;

public class JSELN0115 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_NEW		 		= 200;
	protected static final int A_MAINT	 		= 400;
	protected static final int A_DELETE	 		= 600;
	protected static final int A_ENTER_MAINT		= 800;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELN0115() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0130");
	
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
protected void procActionNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN0115DSMessage msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
    String OpeCod = "0005";
    
    
	// Send Initial data
	try
	{
		msgCL = (ELN0115DSMessage)mc.getMessageRecord("ELN0115DS");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0115DS");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(OpeCod);
	 	
		try {
			msgCL.setE01FLUBNK(req.getParameter("BANK"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUBNK(user.getE01UBK());
		}
		try {
			msgCL.setE01FLUTYP(req.getParameter("MODULE").toUpperCase());
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTYP("");
		}
		try {
			msgCL.setE01FLUTNR(req.getParameter("TENORF"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNR("");
		}
		try {
			msgCL.setE01FLUTNU(req.getParameter("TENORT"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNU("");
		}		
		try {
			msgCL.setE01FLUPER(req.getParameter("PERCENT"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUPER("");
		}

		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN0115 Message Sent");
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

		if (newmessage.getFormatName().equals("ELN0115DS")) {
			try {
				msgCL = new ELN0115DSMessage();
				flexLog("ELN0115DS Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ELN0115DSMessage)newmessage;

			userPO.setIdentifier(msgCL.getD01FLUDSC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_confirm.jsp");
					callPage(LangPath + "ELN0115_cl_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_new.jsp");
					callPage(LangPath + "ELN0115_cl_new.jsp", req, res);	
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


protected void procActionMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELN0115DSMessage msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
    String OpeCod = "0005";
    
    

	// Send Initial data
	try
	{
		msgCL = (ELN0115DSMessage)mc.getMessageRecord("ELN0115DS");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0115DS");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(OpeCod);
	 	
		try {
			msgCL.setE01FLUBNK(req.getParameter("BANK"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUBNK(user.getE01UBK());
		}
		try {
			msgCL.setE01FLUTYP(req.getParameter("MODULE").toUpperCase());
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTYP("");
		}
		try {
			msgCL.setE01FLUTNR(req.getParameter("TENORF"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNR("");
		}
		try {
			msgCL.setE01FLUTNU(req.getParameter("TENORT"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNU("");
		}

		try {
			msgCL.setE01FLUPER(req.getParameter("PERCENT"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUPER("");
		}


		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN0115 Message Sent");
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


		if (newmessage.getFormatName().equals("ELN0115DS")) {
			try {
				msgCL = new ELN0115DSMessage();
				flexLog("ELN0115DS Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgCL = (ELN0115DSMessage)newmessage;


			userPO.setIdentifier(msgCL.getD01FLUDSC());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_confirm.jsp");
					callPage(LangPath + "ELN0115_cl_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_maint.jsp");
					callPage(LangPath + "ELN0115_cl_maint.jsp", req, res);	
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


protected void procActionDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELN0115DSMessage msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
    String OpeCod = "0004";
 
	// Send Initial data
	try
	{
		msgCL = (ELN0115DSMessage)mc.getMessageRecord("ELN0115DS");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0115DS");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(OpeCod);
	 	
		try {
			msgCL.setE01FLUBNK(req.getParameter("E01FLUBNK"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUBNK(user.getE01UBK());
		}
		try {
			msgCL.setE01FLUTYP(req.getParameter("E01FLUTYP").toUpperCase());
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTYP("");
		}
		try {
			msgCL.setE01FLUTNR(req.getParameter("E01FLUTNR"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNR("");
		}

		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN0115 Message Sent");
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


		if (newmessage.getFormatName().equals("ELN0115DS")) {
			try {
				msgCL = new ELN0115DSMessage();
				flexLog("ELN0115DS Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgCL = (ELN0115DSMessage)newmessage;


			userPO.setIdentifier(msgCL.getD01FLUDSC());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_confirm.jsp");
					callPage(LangPath + "ELN0115_cl_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0324?SCREEN=1");			}
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
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new ELEERRMessage();
		userPO = new UserPos();
		userPO.setOption("CL");
		userPO.setPurpose("NEW");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELN0115_cl_new.jsp");
		callPage(LangPath + "ELN0115_cl_new.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN0115DSMessage msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
    String OpeCod = "0002";
 
	// Send Initial data
	try
	{
		msgCL = (ELN0115DSMessage)mc.getMessageRecord("ELN0115DS");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0115DS");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(OpeCod);
	 	
		try {
			msgCL.setE01FLUBNK(req.getParameter("E01FLUBNK"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUBNK(user.getE01UBK());
		}
		try {
			msgCL.setE01FLUTYP(req.getParameter("E01FLUTYP").toUpperCase());
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTYP("");
		}
		try {
			msgCL.setE01FLUTNR(req.getParameter("E01FLUTNR"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01FLUTNR("");
		}

		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN0115 Message Sent");
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


		if (newmessage.getFormatName().equals("ELN0115DS")) {
			try {
				msgCL = new ELN0115DSMessage();
				flexLog("ELN0115DS Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgCL = (ELN0115DSMessage)newmessage;


			userPO.setIdentifier(msgCL.getD01FLUDSC());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("inc", msgCL);


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0115_cl_maint.jsp");
					callPage(LangPath + "ELN0115_cl_maint.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0324?SCREEN=1");			}
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

		int screen = R_ENTER;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opening Socket Connection");
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
				// BEGIN CL

				// Request
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				// Action 
				case A_NEW : 
					procActionNew(mc, msgUser, req, res, session);
					break;
				case A_MAINT : 
					procActionMaint(mc, msgUser, req, res, session);
					break;
				case A_DELETE : 
					procActionDelete(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;														
					
				// END Entering

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				return;
			}
			finally {
				s.close();
			}
			

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}
	
}
protected void showEDL013001(EDL013001Message m)
  {
	if (logType != NONE) {
	  
		flexLog("Client Information received (EDL013001).");

		flexLog("User ID: " + m.getH01USERID());
		flexLog("Program: " + m.getH01PROGRM());
		flexLog("Time date: " + m.getH01TIMSYS());
		flexLog("Screen code: " + m.getH01SCRCOD());
		flexLog("Operate code: " + m.getH01OPECOD());
		flexLog("More records" + m.getH01FLGMAS());
		flexLog("Flag 1: " + m.getH01FLGWK1());
		flexLog("Flag 2: " + m.getH01FLGWK2());
		flexLog("Flag 3: " + m.getH01FLGWK3());

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