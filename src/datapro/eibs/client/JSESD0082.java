package datapro.eibs.client;

/**
 * Customer Payment Instructions
 * Creation date: (12/21/08 9:30:55 PM)
 * @author: Linet Riano
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

public class JSESD0082 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PAY_INSTRUCTION 		= 51;
	protected static final int A_PAY_INSTRUCTION 		= 52;
	protected static final int R_PAY_INSTRUCTION_FRAME	= 53;
	protected static final int R_ENTER_PAY_TYPE			= 61;
	protected static final int A_ENTER_PAY_TYPE			= 62;
	protected static final int A_PAY_TYPE				= 64;
	protected static final int R_APR_INSTRUCTION 		= 65;
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSESD0082() {
	super();
}


/**
 * 
 */
public void destroy() {

	flexLog("free resources used by JSESD0082");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
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

		int screen = 51;
		
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
				case R_PAY_INSTRUCTION :
					procReqCorpPayInst(mc, msgUser, req, res, session);
					break;
				case R_PAY_INSTRUCTION_FRAME :
					res.sendRedirect(super.srctx + LangPath + "ESD0080_client_corp_payment_instructions_frame.jsp");
					break;
				case R_ENTER_PAY_TYPE : 
					procReqEnterPaymentInst(msgUser, req, res, session);
					break;	
				case A_ENTER_PAY_TYPE : 
					procActionEnterPaymentInst(mc, msgUser, req, res, session);
					break;
				case A_PAY_TYPE :
					procActionCorpPayInstType(mc, msgUser, req, res, session);
					break;
				case A_PAY_INSTRUCTION :
					procActionCorpPayInst(mc, msgUser, req, res, session);
					break;
				case R_APR_INSTRUCTION :	
					procReqAprCorpPayInst(mc, msgUser, req, res, session);
					break;
				
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 2;
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
     
protected void procReqEnterPaymentInst(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
throws ServletException, IOException {

ESD008001Message msgClient = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	

try {

msgClient = new datapro.eibs.beans.ESD008001Message(); 
msgError = new datapro.eibs.beans.ELEERRMessage();
userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

ses.setAttribute("client", msgClient);
ses.setAttribute("error", msgError);
ses.setAttribute("userPO", userPO);

} catch (Exception ex) {
flexLog("Error: " + ex); 
}


if (userPO.getPurpose().equals("INQUIRY")) {

flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_enter_inst.jsp");
callPage(LangPath + "ESD0080_client_inq_corp_enter_inst.jsp", req, res);
}
else {

flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_enter_inst.jsp");
callPage(LangPath + "ESD0080_client_corp_enter_inst.jsp", req, res);	
}

}


protected void procReqCorpPayInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008211Message msgPayInst = null;
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

	String opCode = "0003";

	// Send Initial data
	try
	{
		msgPayInst = (ESD008211Message)mc.getMessageRecord("ESD008211");
	 	msgPayInst.setH011USR(user.getH01USR());
	 	msgPayInst.setH011PGM("ESD0080");
	 	msgPayInst.setH011TIM(getTimeStamp());
	 	msgPayInst.setH011SCR("01");
	 	msgPayInst.setH011OPE(opCode);
	 	msgPayInst.setE11CUS(userPO.getCusNum());
		try {	 	
	 		msgPayInst.setE11CCY(req.getParameter("CCY"));
		}
		catch (Exception e) {
		}
		msgPayInst.send();	
	 	msgPayInst.destroy();
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

		if (newmessage.getFormatName().equals("ESD008211")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008211Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008211Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_payment_instructions.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_basic.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_instructions.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
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

protected void procActionEnterPaymentInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
throws ServletException, IOException {

MessageRecord newmessage = null;
ESD008211Message msgClient = null;
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
msgClient = (ESD008211Message)mc.getMessageRecord("ESD008211");
msgClient.setH011USR(user.getH01USR());
msgClient.setH011PGM("ESD0080");
msgClient.setH011TIM(getTimeStamp());
msgClient.setH011SCR("01");
msgClient.setH011OPE("0001");
try {
	msgClient.setE11CUS(req.getParameter("E11CUS"));
}
catch (Exception e)
{
msgClient.setE11CUS("0");
flexLog("Input data error " + e);
}
try {
	msgClient.setE11CCY(req.getParameter("E11CCY"));
}
catch (Exception e)
{
msgClient.setE11CCY("");
flexLog("Input data error " + e);
}
try {
	msgClient.setE11TYP(req.getParameter("E11TYP"));
}
catch (Exception e)
{
msgClient.setE11TYP("");
flexLog("Input data error " + e);
}

msgClient.send();	
msgClient.destroy();
flexLog("ESD008001 Message Sent");
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

if (newmessage.getFormatName().equals("ESD008211")) {
try {
	msgClient = new datapro.eibs.beans.ESD008211Message(); 
	flexLog("ESD008211 Message Received");
	} catch (Exception ex) {
	flexLog("Error: " + ex); 
	}

msgClient = (ESD008211Message)newmessage;

flexLog("Putting java beans into the session");
ses.setAttribute("error", msgError);
ses.setAttribute("pymInst", msgClient);
ses.setAttribute("userPO", userPO);

if (IsNotError) {  // There are no errors
	if (userPO.getPurpose().equals("INQUIRY")) {

			flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_payment_inst.jsp");
			callPage(LangPath + "ESD0080_client_inq_corp_payment_inst.jsp", req, res);
		}
		else {
			
		flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_inst.jsp");
		callPage(LangPath + "ESD0080_client_corp_payment_inst.jsp", req, res);	
		}
}
else {  // There are errors

		if (userPO.getPurpose().equals("INQUIRY")) {

			flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_enter_inst.jsp");
			callPage(LangPath + "ESD0080_client_inq_corp_enter_inst.jsp", req, res);
		}
		else {
			
			flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_enter_inst.jsp");
			callPage(LangPath + "ESD0080_client_corp_enter_inst.jsp", req, res);
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


protected void procActionCorpPayInstType(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008211Message msgPayInst = null;
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

	String opCode = "0005";
	if (req.getParameter("ACTION").equals("S")) {
		opCode = "0005";
	}
	else {
		opCode = "0004";
	}

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgPayInst = (ESD008211Message)mc.getMessageRecord("ESD008211");
		msgPayInst.setH011USR(user.getH01USR());
	 	msgPayInst.setH011PGM("ESD0080");
	 	msgPayInst.setH011TIM(getTimeStamp());
	 	msgPayInst.setH011SCR("01");
	 	msgPayInst.setH011OPE(opCode);
	 	msgPayInst.setE11CUS(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgPayInst.fieldEnumeration();
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

		if (msgPayInst.getE11OFB().equals("")) {
			msgPayInst.setE11OFA(req.getParameter("E11CTA"));
		} else {
			msgPayInst.setE11OFA(req.getParameter("E11GL"));
		}


	 	msgPayInst.send();	
	 	msgPayInst.destroy();
	 	flexLog("ESD008211 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008211")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008211Message(); 
				flexLog("ESD008211 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008211Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayInst);

			
				try {
					if (IsNotError) {
						callPage(LangPath + "ESD0080_client_corp_enter_inst.jsp", req, res);
					}
					else {
						callPage(LangPath + "ESD0080_client_corp_payment_inst.jsp", req, res);
					}						
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
protected void procActionCorpPayInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008211Message msgPayInst = null;
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

	String opCode = "0005";
	if (req.getParameter("ACTION").equals("S")) {
		opCode = "0005";
	}
	else {
		opCode = "0004";
	}

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgPayInst = (ESD008211Message)mc.getMessageRecord("ESD008211");
		msgPayInst.setH011USR(user.getH01USR());
	 	msgPayInst.setH011PGM("ESD0080");
	 	msgPayInst.setH011TIM(getTimeStamp());
	 	msgPayInst.setH011SCR("01");
	 	msgPayInst.setH011OPE(opCode);
	 	msgPayInst.setE11CUS(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgPayInst.fieldEnumeration();
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

	 	msgPayInst.send();	
	 	msgPayInst.destroy();
	 	flexLog("ESD008211 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008211")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008211Message(); 
				flexLog("ESD008211 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008211Message)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayInst);
			

				if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_payment_instructions.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_payment_instructions.jsp", req, res);
					}
					else {
						
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_instructions.jsp");
					callPage(LangPath + "ESD0080_client_corp_payment_instructions.jsp", req, res);	
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

protected void procReqAprCorpPayInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
throws ServletException, IOException {

MessageRecord newmessage = null;
ESD008211Message msgPayInst = null;
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

String opCode = "0003";

// Send Initial data
try
{
msgPayInst = (ESD008211Message)mc.getMessageRecord("ESD008211");
msgPayInst.setH011USR(user.getH01USR());
msgPayInst.setH011PGM("ESD0082");
msgPayInst.setH011TIM(getTimeStamp());
msgPayInst.setH011SCR("01");
msgPayInst.setH011OPE(opCode);
msgPayInst.setE11CUS(userPO.getCusNum());
try {	 	
	msgPayInst.setE11CCY(req.getParameter("CCY"));
}
catch (Exception e) {
}
msgPayInst.send();	
msgPayInst.destroy();
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

if (newmessage.getFormatName().equals("ESD008211")) {
try {
	msgPayInst = new datapro.eibs.beans.ESD008211Message(); 
	} catch (Exception ex) {
	flexLog("Error: " + ex); 
	}

msgPayInst = (ESD008211Message)newmessage;

flexLog("Putting java beans into the session");
ses.setAttribute("error", msgError);
ses.setAttribute("pymInst", msgPayInst);

if (IsNotError) {  // There are no errors
	try {
		flexLog("About to call Page: " + LangPath + "ESD0100_client_ap_corp_payment_instructions.jsp");
		callPage(LangPath + "ESD0100_client_ap_corp_payment_instructions.jsp", req, res);
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}
}
else {  // There are errors
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

}