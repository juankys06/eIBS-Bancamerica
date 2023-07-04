package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (6/7/2004 4:02:17 PM)
 * @author: Manuel Justo
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEAA0020 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ACCOUNT_ANALYSIS		= 1;
	protected static final int A_VALIDATE_ANALYSIS		= 2;
	protected static final int A_UPDATE_ANALYSIS		= 4;
	
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEAA0020() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEAA0020(int logType) {
	super(logType);
}

/**
 * This method was created in VisualAge.
 */
protected void procReqAccAnalysis(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EAA002001Message msgAccAn = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgAccAn = (EAA002001Message)mc.getMessageRecord("EAA002001");
	 	msgAccAn.setH01USERID(user.getH01USR());
	 	msgAccAn.setH01PROGRM("EAA0020");
	 	msgAccAn.setH01TIMSYS(getTimeStamp());
	 	msgAccAn.setH01SCRCOD("01");
	 	msgAccAn.setH01OPECOD(opCode);
	 	
	 	msgAccAn.send();	
	 	msgAccAn.destroy();
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
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("accOthMant", msgAccAn);
			
			try {
				flexLog("About to call Page: " + LangPath + "EAA0020_acc_analysis_other_trans.jsp");
				callPage(LangPath + "EAA0020_acc_analysis_other_trans.jsp", req, res);
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
protected void procActionValidateAccAnalysis(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EAA002001Message msgAccAn = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0003";

	// Send Initial data
	try
	{
		msgAccAn = (EAA002001Message)mc.getMessageRecord("EAA002001");
		msgAccAn.setH01USERID(user.getH01USR());
		msgAccAn.setH01PROGRM("EAA0020");
		msgAccAn.setH01TIMSYS(getTimeStamp());
		msgAccAn.setH01SCRCOD("01");
		msgAccAn.setH01OPECOD(opCode);
	 	
		// all the fields here
		java.util.Enumeration enu = msgAccAn.fieldEnumeration();
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
		
		msgAccAn.send();	
		msgAccAn.destroy();
		flexLog("EAA002001 Message Sent");
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
	
		if (newmessage.getFormatName().equals("EAA002001")) {
			try {
				msgAccAn = new datapro.eibs.beans.EAA002001Message(); 
				flexLog("ESD020502 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgAccAn = (EAA002001Message) newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error",msgError);
			ses.setAttribute("accOthMant", msgAccAn);
		}
	
		try {
			flexLog("About to call Page: " + LangPath + "EAA0020_acc_analysis_other_trans.jsp");
			callPage(LangPath + "EAA0020_acc_analysis_other_trans.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}
/**
 * This method was created in VisualAge.
 */
protected void procActionUpdateAccAnalysis(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EAA002001Message msgAccAn = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0005";

	// Send Initial data
	try
	{
		msgAccAn = (EAA002001Message)mc.getMessageRecord("EAA002001");
		msgAccAn.setH01USERID(user.getH01USR());
		msgAccAn.setH01PROGRM("EAA0020");
		msgAccAn.setH01TIMSYS(getTimeStamp());
		msgAccAn.setH01SCRCOD("01");
		msgAccAn.setH01OPECOD(opCode);

		// all the fields here
		java.util.Enumeration enu = msgAccAn.fieldEnumeration();
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

		msgAccAn.send();	
		msgAccAn.destroy();
		flexLog("EAA002001 Message Sent");
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

	msgAccAn = new datapro.eibs.beans.EAA002001Message(); 

	flexLog("Putting java beans into the session"); 
	ses.setAttribute("userPO", userPO);
	ses.setAttribute("error",msgError);
	ses.setAttribute("accOthMant", msgAccAn);

	if(IsNotError){  // There are no errors
		try {
			flexLog("About to call Page: " + LangPath + "../background.jsp");
			callPage(LangPath + "../background.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	else{ //There are errors
		try {
			flexLog("About to call Page: " + LangPath + "EAA0020_acc_analysis_other_trans.jsp");
			callPage(LangPath + "EAA0020_acc_analysis_other_trans.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
}	
	

/**
 * service method comment.
 */
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
	else {

		int screen = R_ACCOUNT_ANALYSIS;
		
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
				case R_ACCOUNT_ANALYSIS :
					procReqAccAnalysis(mc, msgUser, req, res, session);
					break;
				case A_VALIDATE_ANALYSIS :
					procActionValidateAccAnalysis(mc, msgUser, req, res, session);
					break;
				case A_UPDATE_ANALYSIS :
					procActionUpdateAccAnalysis(mc, msgUser, req, res, session);
					break;
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



}