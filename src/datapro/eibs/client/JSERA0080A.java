package datapro.eibs.client;

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

public class JSERA0080A extends datapro.eibs.master.SuperServlet {

	// Collateral Approval
	static final int R_MAINTENANCE		= 3;

	static final int R_SPECIAL_INST		= 15;

	static final int R_CODES			= 21;


	// entering options
	static final int R_ENTER 			= 100;
	static final int A_ENTER	 		= 200;
	
	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSERA0080A() {
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
private synchronized void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001101Message msgGA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgGA = (ERA001101Message)mc.getMessageRecord("ERA001101");
	 	msgGA.setH01USERID(user.getH01USR());
	 	msgGA.setH01PROGRM("ERA0080");
	 	msgGA.setH01TIMSYS(getTimeStamp());
	 	msgGA.setH01SCRCOD("01");
	 	msgGA.setH01OPECOD("0004"); // 0004 - Approval Inquiry
		try {
			msgGA.setE01ROCREF(req.getParameter("REFNUM"));
		}
		catch (Exception e)	{
			msgGA.setE01ROCREF("0");
		}
		
		if (msgGA.getE01ROCREF().equals("0")) {
			try {
				msgGA.setE01ROCREF(userPO.getIdentifier());
			}
			catch (Exception e) {
				msgGA.setE01ROCREF("0");
			}
		}

		msgGA.send();	
	 	msgGA.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
			try {
					flexLog("About to call Page: " + LangPath + "ERA0080_approval_list.jsp");
					callPage(LangPath + "ERA0080_approval_list.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  }
	  else if (newmessage.getFormatName().equals("ERA001101")) {

		
			try {
				msgGA = (datapro.eibs.beans.ERA001101Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ERA001101Message");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ERA001101Message)newmessage;
			
			userPO.setCusNum(msgGA.getE01ROCCUN());
			userPO.setCusName(msgGA.getE01CUSNA1());
			userPO.setIdentifier(msgGA.getE01ROCREF());
			userPO.setBank(msgGA.getE01ROCBNK());
		    try {
			  userPO.setPurpose(req.getParameter("Purpose").trim());
		    }
		    catch (Exception e) { }			

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("gaMant", msgGA);

			    try {
					flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_maint.jsp");
					callPage(LangPath + "ERA0080_ga_ap_maint.jsp", req, res);	
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
private synchronized void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		userPO.setOption("CO");
		userPO.setPurpose("APPROVAL_INQ");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_maint.jsp");
		callPage(LangPath + "ERA0080_ga_ap_maint.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
private synchronized void procReqEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgGA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try
	{
		msgGA = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgGA.setH05USR(user.getH01USR());
	 	msgGA.setH05PGM("ERA0010");
	 	msgGA.setH05TIM(getTimeStamp());
	 	msgGA.setH05SCR("01");
	 	msgGA.setH05OPE(opCode);
	 	msgGA.setE05ACC(userPO.getIdentifier());
	 	msgGA.setE05TYP("C");
	 	msgGA.setH05WK1("M");
	 	
		msgGA.send();	
	 	msgGA.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgGA = (datapro.eibs.beans.ESD000005Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ESD000005Message");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ESD000005Message)newmessage;
			// showESD008004(msgGA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("gaInst", msgGA);

			if (IsNotError) {  // There are no errors 
				try {
					flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_special_inst.jsp");
					callPage(LangPath + "ERA0080_ga_ap_special_inst.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_maint.jsp");
					callPage(LangPath + "ERA0080_ga_ap_maint.jsp", req, res);	
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
private synchronized void procReqMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001101Message msgGA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgGA = (ERA001101Message)mc.getMessageRecord("ERA001101");
	 	msgGA.setH01USERID(user.getH01USR());
	 	msgGA.setH01PROGRM("ERA0080");
	 	msgGA.setH01TIMSYS(getTimeStamp());
	 	msgGA.setH01SCRCOD("01");
	 	msgGA.setH01OPECOD("0004"); // 0004 - Approval Inquiry
		try {
			msgGA.setE01ROCREF(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  	msgGA.setE01ROCREF("0");
		}
		
		msgGA.send();	
	 	msgGA.destroy();
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

		if (newmessage.getFormatName().equals("ERA001101")) {
			try {
				msgGA = (datapro.eibs.beans.ERA001101Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ERA001101Message");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ERA001101Message)newmessage;

			userPO.setCusNum(msgGA.getE01ROCCUN());
			userPO.setCusName(msgGA.getE01CUSNA1());
			userPO.setIdentifier(msgGA.getE01ROCREF());
			userPO.setBank(msgGA.getE01ROCBNK());
			

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("gaMant", msgGA);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_maint.jsp");
					callPage(LangPath + "ERA0080_ga_ap_maint.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0080_approval_container.jsp");
					callPage(LangPath + "ERA0080_approval_list.jsp", req, res);	
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
private synchronized void procReqSpecialCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgGA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgGA = (ESD000002Message)mc.getMessageRecord("ESD000002");
	 	msgGA.setH02USR(user.getH01USR());
	 	msgGA.setH02PGM("ERA0010");
	 	msgGA.setH02TIM(getTimeStamp());
	 	msgGA.setH02SCR("01");
	 	msgGA.setH02OPE(opCode);
	 	msgGA.setE02ACC(userPO.getIdentifier());
	 	msgGA.setH02WK1("M");
	 	msgGA.send();	
	 	msgGA.destroy();

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

		if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgGA = (datapro.eibs.beans.ESD000002Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ESD000002Message");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ESD000002Message)newmessage;
			// showESD008004(msgGA);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("clCodes", msgGA);

			if (IsNotError) {  // There are no errors
				try {
					
						flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_codes.jsp");
						callPage(LangPath + "ERA0080_ga_ap_codes.jsp", req, res);
					}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_maint.jsp");
					callPage(LangPath + "ERA0080_ga_ap_maint.jsp", req, res);	
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

		int screen = R_ENTER;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, super.iniSocket + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(LangPath + super.sckNotOpenPage);
				return;
			}
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				// BEGIN CD
				// Request
				case R_MAINTENANCE :
					procReqMaintenance(mc, msgUser, req, res, session);
					break;
				case R_COLL_DETAIL :
					procReqCollDetail(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_INST :
					procReqEspInst(mc, msgUser, req, res, session);
					break;
				case R_CODES :
					procReqSpecialCodes(mc, msgUser, req, res, session);
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
					res.sendRedirect(LangPath + super.devPage);
					break;
			}

			try {
				s.close();
			}
			catch (Exception e) {
				flexLog("Error: " + e);
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(LangPath + super.sckNotRespondPage);
		}

	}
	
}
private void showEDL013001(EDL013001Message m)
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
private void showERROR(ELEERRMessage m)
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

	static final int R_COLL_DETAIL      = 9;

/**
 * This method was created in VisualAge.
 */
private synchronized void procReqCollDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
	MessageRecord newmessage = null;
	ERA001002Message msgColl = null;
	JBListRec trList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	
	

	// Send Initial data
	try
	{
		msgColl = (ERA001002Message)mc.getMessageRecord("ERA001002");
	 	msgColl.setH02USERID(user.getH01USR());
	 	msgColl.setH02PROGRM("ERA0010");
	 	msgColl.setH02TIMSYS(getTimeStamp());
	 	msgColl.setH02SCRCOD("01");
	 	msgColl.setH02OPECOD("0006");

	 	// Get Parameters here	
	
	try	{ 	
	 	msgColl.setE02RODREF(userPO.getIdentifier());
	}
	catch (Exception e)	{
	} 
	try	{ 	
	 	msgColl.setE02RODBNK(userPO.getBank());
	}
	catch (Exception e)	{
	} 
	try	{ 	
	 	msgColl.setE02RODCUN(userPO.getCusNum());
	}
	catch (Exception e)	{
	} 		
	
	msgColl.send();	
	msgColl.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	

	int colNum = 26;

	flexLog("Initializing java beans into the session");
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		trList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
		trList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	
	// Receive Data
	try	{

		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ERA001002")) {

			char sel = ' ';
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			boolean myFirstRow = false;
			
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			
			while (true) {

				msgColl = (ERA001002Message)newmessage;

				marker = msgColl.getH02FLGMAS();

				if (marker.equals("*")) {
					break;
				}
				else {
					
					myRow[0] =  msgColl.getE02RODSEQ();  // Sec Number
					myRow[1] =  msgColl.getE02RODNBR();  // Register Code
					myRow[2] =  msgColl.getE02RODDSC();  // Description
					myRow[3] =  msgColl.getE02RODAMT();  // Amount Limit
					myRow[4] =  "" ; // msgColl.getE02RODPD1();  // 
					myRow[5] = " " ; // msgColl.getE02RODPD2();  // 
					myRow[6] = " " ; // msgColl.getE02RODPD3();  // 
					myRow[7] =  msgColl.getE02RODOD1();  // Inscrip date MM
					myRow[8] =  msgColl.getE02RODOD2();  // Inscrip date DD
					myRow[9] =  msgColl.getE02RODOD3();  // Inscrip date YY
					myRow[10] =  msgColl.getE02RODUC1();  // Grado
					myRow[11] =  msgColl.getE02RODUC2();  // Limitaciones
					myRow[12] =  msgColl.getE02RODUC7();  // Conservado
					myRow[13] =  msgColl.getE02RODRF2();  // No. de Folio
					myRow[14] =  msgColl.getE02RODRF4();  // Notaria
					myRow[15] =  msgColl.getE02RODAIN();  // Inscrip Year
					myRow[16] =  msgColl.getE02RODCUN();  // Cust. Number
					myRow[17] = " " ; // msgColl.getE02RODEST();  // Benef
					myRow[18] = " "; //  msgColl.getE02RODND1();  // 
					myRow[19] = " "; // msgColl.getE02RODND2();  // 
					myRow[20] = " " ; // msgColl.getE02RODND3();  // 
					myRow[21] = " "; // msgColl.getE02RODNPH();  // 
					myRow[22] = " "; //  msgColl.getE02RODCPH();  //
					myRow[23] =  " "; // msgColl.getE02RODFPH();  //
					myRow[24] =  "  "; //msgColl.getE02RODYPH();  //
					myRow[25] = " " ; // msgColl.getE02RODOFN();  // Branch
					
					
					trList.addRow(myFlag, myRow);
					 				
				}

				newmessage = mc.receiveMessage();

			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("collList", trList);
						
			try {
				 flexLog("About to call Page: " + LangPath + "ERA0080_ga_ap_list_detail.jsp");
				 callPage(LangPath + "ERA0080_ga_ap_list_detail.jsp", req, res);					
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
}