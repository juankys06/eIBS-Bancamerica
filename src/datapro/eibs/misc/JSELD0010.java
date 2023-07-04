package datapro.eibs.misc;

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

public class JSELD0010 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_MAINTENANCE		= 3;
	protected static final int A_MAINTENANCE		= 4;
	

	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_ENTER	 		= 200;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELD0010() {
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
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELD001001Message msgEX = null;
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
		msgEX = (ELD001001Message)mc.getMessageRecord("ELD001001");
	 	msgEX.setH01USERID(user.getH01USR());
	 	msgEX.setH01PROGRM("ELD0010");
	 	msgEX.setH01TIMSYS(getTimeStamp());
	 	msgEX.setH01SCRCOD("01");
	 	msgEX.setH01OPECOD("0002");
		try {
			msgEX.setE01LDEACC(req.getParameter("E01LDEACC"));
		}
		catch (Exception e)	{
		}
		try {
			msgEX.setE01LDEBDY(req.getParameter("E01LDEBDY"));
		}
		catch (Exception e)	{
		}
		try {
			msgEX.setE01LDEBDM(req.getParameter("E01LDEBDM"));
		}
		catch (Exception e)	{
		}
		try {
			msgEX.setE01LDESEQ(req.getParameter("E01LDESEQ"));
		}
		catch (Exception e)	{
		}

		msgEX.send();	
	 	msgEX.destroy();
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

		if (newmessage.getFormatName().equals("ELD001001")) {
			try {
				msgEX = new datapro.eibs.beans.ELD001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgEX = (ELD001001Message)newmessage;

			if (msgEX.getE01LDESEQ().equals("0")) { 
				userPO.setPurpose("NEW");}
			else {
				userPO.setPurpose("MAINTENANCE");}
			userPO.setAccNum(msgEX.getE01LDEACC());
			userPO.setHeader1(msgEX.getE01LDEBDM());
			userPO.setHeader2(msgEX.getE01LDEBDY());
			userPO.setHeader3(msgEX.getE01LDESEQ());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("exMaint", msgEX);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELD0010_exception_maint.jsp");
					callPage(LangPath + "ELD0010_exception_maint.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELD0010_exception_enter_maint.jsp");
					callPage(LangPath + "ELD0010_exception_enter_maint.jsp", req, res);	
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
protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELD001001Message msgEX = null;
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
	 	msgEX = (ELD001001Message)ses.getAttribute("exMaint");
		msgEX.setH01USERID(user.getH01USR());
	 	msgEX.setH01PROGRM("ELD0010");
	 	msgEX.setH01TIMSYS(getTimeStamp());
	 	msgEX.setH01SCRCOD("01");
	 	msgEX.setH01OPECOD("0005");
		try{
			
			try{
				msgEX.setE01LDETPY(req.getParameter("E01LDETPY"));
			}
			catch (Exception e){
			}
			try{
				String[] myfield ; //= new String[4];
				myfield = datapro.eibs.master.Util.splitField(req.getParameter("E01LDERM"),4,35);
				msgEX.setE01LDERM1(myfield[0]);
				msgEX.setE01LDERM2(myfield[1]);
				msgEX.setE01LDERM3(myfield[2]);
				msgEX.setE01LDERM4(myfield[3]);
			}
			catch (Exception e){
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

	 	//msgEX.send();
	 	mc.sendMessage(msgEX);
	 	msgEX.destroy();
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

		if (newmessage.getFormatName().equals("ELD001001")) {
				try {
					msgEX = new datapro.eibs.beans.ELD001001Message();
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgEX = (ELD001001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("exMaint", msgEX);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page1: " + LangPath + "ELD0010_exception_enter_maint.jsp");
							callPage(LangPath + "ELD0010_exception_enter_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "ELD0010_exception_maint.jsp");
						callPage(LangPath + "ELD0010_exception_maint.jsp", req, res);	
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
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("EX");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELD0010_exception_enter_maint.jsp");
		callPage(LangPath + "ELD0010_exception_enter_maint.jsp", req, res);	
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
									
				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
				

				// BEGIN Entering
				// Request
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER : 
					procActionEnter(mc, msgUser, req, res, session);
					break;
				// END Entering

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			} finally {
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