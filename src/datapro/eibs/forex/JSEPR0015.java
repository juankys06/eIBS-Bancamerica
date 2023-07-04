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

 
import datapro.eibs.sockets.*;

public class JSEPR0015 extends datapro.eibs.master.SuperServlet {

	// Action
	static final int R_NEW				= 1; 
	static final int A_MAINTENANCE		= 2;
	static final int R_PROCESS			= 3;
	
	static final int A_ENTER_NEW	 		= 200;
	static final int A_ENTER_MAINT	 	= 400;
	static final int A_ENTER_INQUIRY 		= 600;
	static final int A_ENTER_PROCESS 		= 800;
	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEPR0015() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEIE00000");
	
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
protected  void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR0015DSMessage msgInst = null;
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


	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EPR0015DSMessage)mc.getMessageRecord("EPR0015");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EPR0015");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0001");
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE011001 Message Sent");
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
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EPR0015DS")) {
			try {
				msgInst = new EPR0015DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EPR0015DSMessage)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("payments", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EPR0015_payments_basic.jsp");
						callPage(LangPath + "EPR0015_payments_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0311?SCREEN=1");			}
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
protected  void procActionEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR0015DSMessage msgInst = null;
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


	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EPR0015DSMessage)mc.getMessageRecord("EPR0015DS");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EPR0015");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0003");
	 	
	 	try{
	 	 msgInst.setE01PRCAGR(req.getParameter("AGREEMENT"));
	 	}
	 	catch (Exception e){
	 	}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE011001 Message Sent");
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
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EPR0015DS")) {
			try {
				msgInst = new EPR0015DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EPR0015DSMessage)newmessage;
			
			userPO.setPurpose("I");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("payments", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EPR0015_inq_payments_basic.jsp");
						callPage(LangPath + "EPR0015_inq_payments_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0311?SCREEN=1");			}
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
protected  void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR0015DSMessage msgInst = null;
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


	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EPR0015DSMessage)mc.getMessageRecord("EPR0015DS");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EPR0015");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0002");
	 	
	 	try{
	 	 msgInst.setE01PRCAGR(req.getParameter("AGREEMENT"));
	 	}
	 	catch (Exception e){
	 	}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE011001 Message Sent");
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
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EPR0015DS")) {
			try {
				msgInst = new EPR0015DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EPR0015DSMessage)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("payments", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EPR0015_payments_basic.jsp");
						callPage(LangPath + "EPR0015_payments_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0311?SCREEN=1");			}
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
protected  void procActionEnterProcess(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR0015DSMessage msgInst = null;
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


	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EPR0015DSMessage)mc.getMessageRecord("EPR0015DS");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EPR0015");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0006");
	 	
	 	try{
	 	 msgInst.setE01LIB(req.getParameter("LIBRARY"));
	 	}
	 	catch (Exception e){
	 	}
	 	
	 	try{
	 	 msgInst.setE01PRCAGR(req.getParameter("AGREEMENT"));
	 	}
	 	catch (Exception e){
	 	}
	 	
	 	try{
	 	 msgInst.setE01FILE(req.getParameter("FILE"));
	 	}
	 	catch (Exception e){
	 	}
		
		try{
		 msgInst.setE01PRCEXR(req.getParameter("E01PRCEXR"));
		}
		catch (Exception e){
		}
				
		try{
		 msgInst.setE01PRCOSA(req.getParameter("E01PRCOSA"));
		}
		catch (Exception e){
		}
						
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE011001 Message Sent");
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
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EPR0015DS")) {
			try {
				msgInst = new EPR0015DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EPR0015DSMessage)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("payments", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EPR0015_process_confirm.jsp");
						callPage(LangPath + "EPR0015_process_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EPR0015_enter_process.jsp");
						callPage(LangPath + "EPR0015_enter_process.jsp", req, res);	
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

	protected void procReqEnterProcess(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EPR0015_enter_process.jsp");
			callPage(LangPath + "EPR0015_enter_process.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqEnterNew(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EPR0015DSMessage msgInst = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			msgInst = new EPR0015DSMessage();
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("payments", msgInst);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EPR0015_payments_basic.jsp");
			callPage(LangPath + "EPR0015_payments_basic.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}



/**
 * This method was created in VisualAge.
 */
protected  void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR0015DSMessage msgInst = null;
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

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgInst = (EPR0015DSMessage)ses.getAttribute("payments");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgInst.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase().trim();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	//msgInst.send();
	 	mc.sendMessage(msgInst);
	 	msgInst.destroy();
	 	flexLog("EIE000001 Message Sent");
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
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

				try {
					msgInst = new EPR0015DSMessage();
					flexLog("EPR0015 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EPR0015DSMessage)newmessage;
				// showESD008004(msgInst);
				
				userPO.setIdentifier(msgInst.getE01PRCAGR());
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("payments", msgInst);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0311?SCREEN=1");
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EPR0015_payments_basic.jsp");
							callPage(LangPath + "EPR0015_payments_basic.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
			}
			
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

		int screen = A_ENTER_MAINT;
		
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
				//Request
				case R_NEW :
					procReqEnterNew(msgUser, req, res, session);
					break;
				case R_PROCESS :
					procReqEnterProcess(msgUser, req, res, session);
					break;	
					
				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
							
									
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;						
				case A_ENTER_INQUIRY : 
					procActionEnterInquiry(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_PROCESS : 
					procActionEnterProcess(mc, msgUser, req, res, session);
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
				//return;
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
