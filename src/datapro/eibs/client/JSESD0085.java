package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;

import datapro.eibs.master.Util; 
 
import datapro.eibs.sockets.*;

public class JSESD0085 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int A_P_SHORT_BASIC 		= 2;
	protected static final int R_ENTER_NEW 			= 100;
	protected static final int A_ENTER_NEW	 		= 400;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSESD0085() {
	super();
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

		int screen = 100;
		
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
				// BEGIN Entering
				// Request
				case R_ENTER_NEW : 
					procReqEnterNew(msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_P_SHORT_BASIC :
					procActionPerShortBasic(mc, msgUser, req, res, session);
					break;
				// END Entering

				// Reports
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
/***********************************************************************************
 * This method was created in VisualAge. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> jlbq
 */
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008501Message msgClientPersonal = null;
	ESD008002Message msgClientEntity_80 = null;
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
		if (req.getParameter("TYPE").equals("PERSONAL")) {
			flexLog("Entering to Personal");
			msgClientPersonal = (ESD008501Message)mc.getMessageRecord("ESD008501");
		 	msgClientPersonal.setH01USR(user.getH01USR());
		 	msgClientPersonal.setH01PGM("ESD0085");
		 	msgClientPersonal.setH01TIM(getTimeStamp());
		 	msgClientPersonal.setH01SCR("01");
		 	msgClientPersonal.setH01OPE("0001");
		 	msgClientPersonal.setE01FL1("1");
			try {
			 	if (req.getParameter("CUN") != null)
			 	  msgClientPersonal.setE01CUN(req.getParameter("CUN"));
			 	if (req.getParameter("IDN") != null)
				 	  msgClientPersonal.setE01IDN(req.getParameter("IDN"));
			 	if (req.getParameter("COUNTRY") != null)
				 	  msgClientPersonal.setE01GEC(req.getParameter("COUNTRY"));
			 	if (req.getParameter("IDN2") != null &&
			 		!req.getParameter("IDN2").equals("") ){
			 		//This is Because for PANAMA the Identification uses the long
			 		//format and is stored in the IDN2 field, if the IDN2 field has 
			 		//a value, this means that PANAMA is the country for the customer
			 		//and the IDN must be empty.
				 	  msgClientPersonal.setE01IDN("");
				 	  msgClientPersonal.setE01LN3(req.getParameter("IDN2"));
			 	  
			 	}
			}
			catch (Exception e)
			{
				msgClientPersonal.setE01CUN("0");
			    flexLog("Input data error " + e);
			}

			msgClientPersonal.send();	
		 	msgClientPersonal.destroy();
		 	flexLog("ESD008001 Message Sent");
		} 	
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

		if (newmessage.getFormatName().equals("ESD008501")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008501Message();
				flexLog("ESD008501 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008501Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setOption("CLIENT_P");
			
			req.setAttribute("IDN", msgClientPersonal.getE01IDN());
			req.setAttribute("IDN2", msgClientPersonal.getE01LN3());
			req.setAttribute("COUNTRY", msgClientPersonal.getE01GEC());
			req.setAttribute("COUNTRYDSC", msgClientPersonal.getD01GEC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0085_client_personal_basic.jsp");
					callPage(LangPath + "ESD0085_client_personal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0085_client_both_enter_new.jsp");
					callPage(LangPath + "ESD0085_client_both_enter_new.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}
/**************************************************************************
 * This method was created in VisualAge.
 */
protected void procReqEnterNew(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos(); 
		userPO.setOption("CLIENT");
		userPO.setPurpose("NEW");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0085_client_both_enter_new.jsp");
		callPage(LangPath + "ESD0085_client_both_enter_new.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
protected void showERROR(ELEERRMessage m)
{
	if (logType != NONE) {
		
		flexLog("ERROR received.");
		
		flexLog("ERROR number:" + m.getERRNUM());
		flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01() + " code : " + m.getERDF01());
		flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02() + " code : " + m.getERDF02());
		flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03() + " code : " + m.getERDF03());
		flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04() + " code : " + m.getERDF04());
		flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05() + " code : " + m.getERDF05());
		flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06() + " code : " + m.getERDF06());
		flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07() + " code : " + m.getERDF07());
		flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08() + " code : " + m.getERDF08());
		flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09() + " code : " + m.getERDF09());
		flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10() + " code : " + m.getERDF10());
		
	}
}

/**
 * This method was created in VisualAge.
 */
protected void procActionPerShortBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008501Message msgClientPersonal = null;
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
		msgClientPersonal = (ESD008501Message)mc.getMessageRecord("ESD008501");
	 	// msgClientPersonal = (ESD008501Message)ses.getAttribute("client");
		msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0085");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE("0002");

		// all the fields here
	 	java.util.Enumeration enu = msgClientPersonal.fieldEnumeration();
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
	 	
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
	 	flexLog("ESD008501 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008501")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008501Message(); 
				flexLog("ESD008501 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008501Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setCusName(msgClientPersonal.getE01NA1());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
		    ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0085_client_both_enter_new.jsp");
					callPage(LangPath + "ESD0085_client_both_enter_new.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0085_client_personal_basic.jsp");
					callPage(LangPath + "ESD0085_client_personal_basic.jsp", req, res);	
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