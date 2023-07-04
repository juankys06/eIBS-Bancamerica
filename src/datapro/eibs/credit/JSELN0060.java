package datapro.eibs.credit;

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

public class JSELN0060 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_BALANCE				= 1;
	protected static final int A_BALANCE				= 2;
	protected static final int R_BASIC_INFO			= 3;
	protected static final int A_BASIC_INFO			= 4;
	protected static final int R_SPECIAL_CODE			= 5;
	protected static final int A_SPECIAL_CODE			= 6;
	protected static final int R_SPECIAL_INST			= 7;
	protected static final int A_SPECIAL_INST			= 8;
	protected static final int R_CTRL_LINE			= 9;
	protected static final int A_CTRL_LINE			= 10;

	// entering options
	protected static final int R_ENTER 				= 100;
	protected static final int A_ENTER	 			= 200;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELN0060() {
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
protected void procReqBalance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN006001Message msgCL = null;
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
		msgCL = (ELN006001Message)mc.getMessageRecord("ELN006001");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("EDD0000");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(opCode);
	 	msgCL.setE01LNECUN(userPO.getCusNum());
		msgCL.setE01LNENUM(userPO.getCreditLineNum());
	 	msgCL.send();	
	 	msgCL.destroy();
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
			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");
	  	}
	  	else if (newmessage.getFormatName().equals("ELN006001")) {
			try {
				msgCL = new datapro.eibs.beans.ELN006001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ELN006001Message)newmessage;

			userPO.setCusName(msgCL.getE01CUSNA1());
			userPO.setCurrency(msgCL.getE01LNECCY());
			userPO.setCreditLineType(msgCL.getE01LNETYL());
			userPO.setOfficer(msgCL.getE01LNEOFC() + " - " + msgCL.getE01DSCOFC());

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clBalance", msgCL);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0060_cl_inq_balance.jsp");
				callPage(LangPath + "ELN0060_cl_inq_balance.jsp", req, res);
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
protected void procReqBasicInfo(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN006002Message msgCL = null;
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
		msgCL = (ELN006002Message)mc.getMessageRecord("ELN006002");
	 	msgCL.setH02USERID(user.getH01USR());
	 	msgCL.setH02PROGRM("EDD0000");
	 	msgCL.setH02TIMSYS(getTimeStamp());
	 	msgCL.setH02SCRCOD("01");
	 	msgCL.setH02OPECOD(opCode);
	 	msgCL.setE02LNECUN(userPO.getCusNum());
		msgCL.setE02LNENUM(userPO.getCreditLineNum());
	 	msgCL.send();	
	 	msgCL.destroy();
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
			res.sendRedirect(super.srctx + LangPath + super.devPage);
	  }
	  else if (newmessage.getFormatName().equals("ELN006002")) {
			try {
				msgCL = new datapro.eibs.beans.ELN006002Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ELN006002Message)newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("clBasic", msgCL);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0060_cl_inq_basic.jsp");
				callPage(LangPath + "ELN0060_cl_inq_basic.jsp", req, res);
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
protected void procReqCtrlLine(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN006003Message msgCL = null;
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
		msgCL = (ELN006003Message)mc.getMessageRecord("ELN006003");
	 	msgCL.setH03USERID(user.getH01USR());
	 	msgCL.setH03PROGRM("EDD0000");
	 	msgCL.setH03TIMSYS(getTimeStamp());
	 	msgCL.setH03SCRCOD("01");
	 	msgCL.setH03OPECOD(opCode);
	 	msgCL.setE03LNECUN(userPO.getCusNum());
		msgCL.setE03LNENUM(userPO.getCreditLineNum());
	 	msgCL.send();	
	 	msgCL.destroy();
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
			res.sendRedirect(super.srctx + LangPath + super.devPage);
	  }
	  else if (newmessage.getFormatName().equals("ELN006003")) {
			try {
				msgCL = new datapro.eibs.beans.ELN006003Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ELN006003Message)newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("clControl", msgCL);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0060_cl_inq_control.jsp");
				callPage(LangPath + "ELN0060_cl_inq_control.jsp", req, res);
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
protected void procReqSpecialCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgCL = null;
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
		msgCL = (ESD000002Message)mc.getMessageRecord("ESD000002");
	 	msgCL.setH02USR(user.getH01USR());
	 	msgCL.setH02PGM("EDD0000");
	 	msgCL.setH02TIM(getTimeStamp());
	 	msgCL.setH02SCR("01");
	 	msgCL.setH02OPE(opCode);
	 	msgCL.setE02ACC(userPO.getCusNum());
		msgCL.setE02LNE(userPO.getCreditLineNum());
	 	msgCL.send();	
	 	msgCL.destroy();
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
			res.sendRedirect(super.srctx + LangPath + super.devPage);
		}
		else if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000002Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ESD000002Message)newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("clCodes", msgCL);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0060_cl_inq_codes.jsp");
				callPage(LangPath + "ELN0060_cl_inq_codes.jsp", req, res);
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
protected void procReqSpecialInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgCL = null;
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
		msgCL = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgCL.setH05USR(user.getH01USR());
	 	msgCL.setH05PGM("EDD0000");
	 	msgCL.setH05TIM(getTimeStamp());
	 	msgCL.setH05SCR("01");
	 	msgCL.setH05OPE(opCode);
	 	msgCL.setE05ACC(userPO.getCusNum());
	 	msgCL.setE05LNE(userPO.getCreditLineNum());
	 	msgCL.setE05TYP("7");
		msgCL.send();	
	 	msgCL.destroy();
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
			res.sendRedirect(super.srctx + LangPath + super.devPage);
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000005Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ESD000005Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("clInst", msgCL);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0060_cl_inq_special_inst.jsp");
				callPage(LangPath + "ELN0060_cl_inq_special_inst.jsp", req, res);	
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
				case R_BALANCE :
					procReqBalance(mc, msgUser, req, res, session);
					break;
				case R_BASIC_INFO :
					procReqBasicInfo(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_CODE :
					procReqSpecialCodes(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_INST :
					procReqSpecialInst(mc, msgUser, req, res, session);
					break;
				case R_CTRL_LINE :
					procReqCtrlLine(mc, msgUser, req, res, session);
					break;
				//entering options
				case R_ENTER :
					break;
				case A_ENTER :
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