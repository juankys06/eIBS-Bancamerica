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

import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util; 
 
import datapro.eibs.sockets.*;

public class JSESD0080 extends datapro.eibs.master.SuperServlet {

	// personal client options
	protected static final int R_P_BASIC 				= 1;
	protected static final int A_P_BASIC 				= 2;
	protected static final int R_P_BENE 				= 3;
	protected static final int A_P_BENE 				= 4;
	protected static final int R_P_EMPLOYMENT 		= 5;
	protected static final int A_P_EMPLOYMENT 		= 6;

	protected static final int R_P_SHORT_BASIC 		= 7;
	protected static final int A_P_SHORT_BASIC 		= 8;
	protected static final int R_KNOW_CUSTOMER 		= 49;
	protected static final int A_KNOW_CUSTOMER 		= 50;
	
	// corporative client options
	protected static final int R_C_BASIC 				= 11;
	protected static final int A_C_BASIC 				= 12;
	protected static final int R_C_STOCK_HOLDER 		= 13;
	protected static final int A_C_STOCK_HOLDER 		= 14;
	protected static final int R_C_BOARD 				= 15;
	protected static final int A_C_BOARD 				= 16;
	protected static final int R_C_ASSETS 			= 17;
	protected static final int A_C_ASSETS 			= 18;
	protected static final int R_C_LIABILITIES 		= 19;
	protected static final int A_C_LIABILITIES 		= 20;
	protected static final int R_C_CAPITAL			= 21;
	protected static final int A_C_CAPITAL			= 22;
	
	// personal & corporative client options
	protected static final int R_CODES 				= 31;
	protected static final int A_CODES 				= 32;
	protected static final int R_MAIL_ADDRESS 		= 33;
	protected static final int A_MAIL_ADDRESS 		= 34;
	protected static final int R_SPECIAL_INST 		= 35;
	protected static final int A_SPECIAL_INST 		= 36;
	protected static final int R_COM 					= 37;
	protected static final int A_COM 					= 38;
	protected static final int R_BANK_REF 			= 39;
	protected static final int A_BANK_REF 			= 40;
	protected static final int R_COM_REF 				= 41;
	protected static final int A_COM_REF 				= 42;
 	protected static final int R_PER_REF 				= 43;
	protected static final int A_PER_REF 				= 44;
	protected static final int R_LEGAL_REP 			= 45;
	protected static final int A_LEGAL_REP 			= 46;
	protected static final int R_MARKETING 			= 47;
	protected static final int A_MARKETING 			= 48;
	protected static final int R_PAY_INSTRUCTION 		= 51;
	protected static final int A_PAY_INSTRUCTION 		= 52;
	protected static final int R_PAY_INSTRUCTION_FRAME	= 53;
	protected static final int R_ID_CHANGE_ENTER		= 54;
	protected static final int A_ID_CHANGE_ENTER		= 55;
	protected static final int A_ID_CHANGE_MAINT		= 56; 

	protected static final int R_ENTER_PAY_TYPE		= 61;
	protected static final int A_ENTER_PAY_TYPE		= 62;
	protected static final int A_PAY_TYPE				= 64;
	protected static final int R_ACCOUNT_TITLE 		= 65;
	protected static final int A_ACCOUNT_TITLE 		= 66;
	
	
	// entering options
	protected static final int R_ENTER_NEW 			= 100;
	protected static final int R_ENTER_MAINT 			= 200;
	protected static final int R_ENTER_INQUIRY 		= 300;
	protected static final int A_ENTER_NEW	 		= 400;
	protected static final int A_ENTER_MAINT	 		= 500;
	protected static final int A_ENTER_INQ	 		= 600;

	protected static final int R_ENTER_REPORT	 		= 700;
	protected static final int A_ENTER_REPORT	 		= 800;
	protected static final int R_REPORT	 			= 900;
	
	protected static final int R_P_PICTURE 				= 1000;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSESD0080() {
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
protected void procActionBankRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBankRef = null;
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
		msgBankRef = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgBankRef = (ESD000004Message)ses.getAttribute("bankRef");
		msgBankRef.setH04USR(user.getH01USR());
	 	msgBankRef.setH04PGM("ESD0080");
	 	msgBankRef.setH04TIM(getTimeStamp());
	 	msgBankRef.setH04SCR("01");
	 	msgBankRef.setH04OPE("0005");
	 	msgBankRef.setE04CUN(userPO.getCusNum());
	 	msgBankRef.setE04RTP("6");

	 	// all the fields here
	 	java.util.Enumeration enu = msgBankRef.fieldEnumeration();
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

	 	msgBankRef.send();	
	 	msgBankRef.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBankRef = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBankRef = (ESD000004Message)newmessage;
			// showESD000004(msgBankRef);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bankRef", msgBankRef);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_bank_ref.jsp");
					callPage(LangPath + "ESD0080_client_both_bank_ref.jsp", req, res);

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
protected void procActionCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008003Message msgCodes = null;
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
		msgCodes = (ESD008003Message)mc.getMessageRecord("ESD008003");
	 	// msgCodes = (ESD008003Message)ses.getAttribute("codes");
		msgCodes.setH03USR(user.getH01USR());
	 	msgCodes.setH03PGM("ESD0080");
	 	msgCodes.setH03TIM(getTimeStamp());
	 	msgCodes.setH03SCR("01");
	 	msgCodes.setH03OPE("0005");
	 	msgCodes.setE03CUN(userPO.getCusNum());

		// all the fields here
	 	java.util.Enumeration enu = msgCodes.fieldEnumeration();
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

	 	msgCodes.send();	
	 	msgCodes.destroy();
	 	flexLog("ESD008003 Message Sent");
	}		
	catch (Exception e) {
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
		// showERROR(msgError);
		flexLog("ELEERRMessage: " + msgError.toString());
		
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

		if (newmessage.getFormatName().equals("ESD008003")) {
			try {
				msgCodes = new datapro.eibs.beans.ESD008003Message();
				flexLog("ESD008003 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCodes = (ESD008003Message)newmessage;
			// showESD008003(msgCodes);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("codes", msgCodes);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: /servlet/datapro.eibs.client.JSESD0080?SCREEN=1");
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=1");
					} else {
						flexLog("About to call Page: /servlet/datapro.eibs.client.JSESD0080?SCREEN=11");
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=11");
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_codes.jsp");
					callPage(LangPath + "ESD0080_client_both_codes.jsp", req, res);	
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
protected void procActionCom(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000007Message msgCom = null;
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
		msgCom = (ESD000007Message)mc.getMessageRecord("ESD000007");
	 	// msgCom = (ESD000007Message)ses.getAttribute("com");
		msgCom.setH07USR(user.getH01USR());
	 	msgCom.setH07PGM("ESD0080");
	 	msgCom.setH07TIM(getTimeStamp());
	 	msgCom.setH07SCR("01");
	 	msgCom.setH07OPE("0005");
	 	msgCom.setE07CUN(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgCom.fieldEnumeration();
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

	 	msgCom.send();	
	 	msgCom.destroy();
	 	flexLog("ESD000007 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000007")) {
			try {
				msgCom = new datapro.eibs.beans.ESD000007Message();
				flexLog("ESD000007 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCom = (ESD000007Message)newmessage;
			// showESD000007(msgCom);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("com", msgCom);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_com.jsp");
					callPage(LangPath + "ESD0080_client_both_com.jsp", req, res);	
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
protected void procActionComRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgComRef = null;
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
		msgComRef = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgComRef = (ESD000004Message)ses.getAttribute("comRef");
		msgComRef.setH04USR(user.getH01USR());
	 	msgComRef.setH04PGM("ESD0080");
	 	msgComRef.setH04TIM(getTimeStamp());
	 	msgComRef.setH04SCR("01");
	 	msgComRef.setH04OPE("0005");
	 	msgComRef.setE04CUN(userPO.getCusNum());
	 	msgComRef.setE04RTP("7");

	 	// all the fields here
	 	java.util.Enumeration enu = msgComRef.fieldEnumeration();
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

	 	msgComRef.send();	
	 	msgComRef.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgComRef = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgComRef = (ESD000004Message)newmessage;
			// showESD000004(msgComRef);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("comRef", msgComRef);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_com_ref.jsp");
					callPage(LangPath + "ESD0080_client_both_com_ref.jsp", req, res);	
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
protected void procActionCorpAssets(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgAssets = null;
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
		msgAssets = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgAssets = (ESD000004Message)ses.getAttribute("assets");
		msgAssets.setH04USR(user.getH01USR());
	 	msgAssets.setH04PGM("ESD0080");
	 	msgAssets.setH04TIM(getTimeStamp());
	 	msgAssets.setH04SCR("01");
	 	msgAssets.setH04OPE("0005");
	 	msgAssets.setE04CUN(userPO.getCusNum());
	 	msgAssets.setE04RTP("9");

	 	// all the fields here
	 	java.util.Enumeration enu = msgAssets.fieldEnumeration();
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

	 	msgAssets.send();	
	 	msgAssets.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgAssets = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgAssets = (ESD000004Message)newmessage;
			// showESD000004(msgAssets);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("assets", msgAssets);

			if (IsNotError) {  // There are no errors
				try {
					if ( userPO.getOption().equals("CLIENT_P") ) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
					} else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_assets.jsp");
					callPage(LangPath + "ESD0080_client_corp_assets.jsp", req, res);	
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
protected void procActionCorpBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008002Message msgClient = null;
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
		msgClient = (ESD008002Message)mc.getMessageRecord("ESD008002");
	 	// msgClient = (ESD008002Message)ses.getAttribute("client");
		msgClient.setH02USR(user.getH01USR());
	 	msgClient.setH02PGM("ESD0080");
	 	msgClient.setH02TIM(getTimeStamp());
	 	msgClient.setH02SCR("01");
	 	msgClient.setH02OPE("0005");
	 	try {
		 	if (req.getParameter("APPROVAL").equals("Y")) msgClient.setH02OPE("0006");
		}
		catch (Exception e) {
		}
		msgClient.setE02CUN(req.getParameter("E02CUN"));
		msgClient.setE02LGT("1"); // Cliente Empresarial

		// all the fields here
	 	java.util.Enumeration enu = msgClient.fieldEnumeration();
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
		
		String tes = "";
		String fid = "";
		String fem = "";
		String ter = "";
	 	
		try {if (req.getParameter("E02FL8_TES").equals("1")) tes = "T";	} catch (Exception e) {tes = "";}
		try {if (req.getParameter("E02FL8_FID").equals("1")) fid = "F";	} catch (Exception e) {fid = "";}
		try {if (req.getParameter("E02FL8_FEM").equals("1")) fem = "E";	} catch (Exception e) {fem = "";}
		try {if (req.getParameter("E02FL8_TER").equals("1")) ter = "R";	} catch (Exception e) {ter = "";}
	 	 
		msgClient.setE02FL8(tes + fid + fem + ter);

	 	msgClient.send();	
	 	msgClient.destroy();
	 	flexLog("ESD008002 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClient = new datapro.eibs.beans.ESD008002Message();
				flexLog("ESD008002 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClient = (ESD008002Message)newmessage;

			userPO.setCusNum(msgClient.getE02CUN());
			userPO.setHeader1(msgClient.getE02CUN());
			userPO.setHeader2(msgClient.getE02IDN());			
			userPO.setHeader3(msgClient.getE02NA1());
			if (!msgClient.getE02LN3().equals("") &&
					msgClient.getE02LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClient.getE02LN3());
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClient);
		    ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
			    if (userPO.getPurpose().equals("NEW") || msgClient.getE02SFR().equals("A")) {   
					userPO.setPurpose("MAINTENANCE");
				    ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_confirm.jsp");
						callPage(LangPath + "ESD0080_client_corp_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			    else {
					try {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
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
protected void procActionCorpBoard(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBoard = null;
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
		msgBoard = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgBoard = (ESD000004Message)ses.getAttribute("board");
		msgBoard.setH04USR(user.getH01USR());
	 	msgBoard.setH04PGM("ESD0080");
	 	msgBoard.setH04TIM(getTimeStamp());
	 	msgBoard.setH04SCR("01");
	 	msgBoard.setH04OPE("0005");
	 	msgBoard.setE04CUN(userPO.getCusNum());
	 	msgBoard.setE04RTP("3");

	 	// all the fields here
	 	java.util.Enumeration enu = msgBoard.fieldEnumeration();
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
/*		
		msgBoard.setE14MA4(req.getParameter("E14MA4") + req.getParameter("E14MA42"));
		msgBoard.setE24MA4(req.getParameter("E24MA4") + req.getParameter("E24MA42"));
		msgBoard.setE34MA4(req.getParameter("E34MA4") + req.getParameter("E34MA42"));
		msgBoard.setE44MA4(req.getParameter("E44MA4") + req.getParameter("E44MA42"));
		msgBoard.setE54MA4(req.getParameter("E54MA4") + req.getParameter("E54MA42"));
		msgBoard.setE64MA4(req.getParameter("E64MA4") + req.getParameter("E64MA42"));
		msgBoard.setE74MA4(req.getParameter("E74MA4") + req.getParameter("E74MA42"));
		msgBoard.setE84MA4(req.getParameter("E84MA4") + req.getParameter("E84MA42"));
		msgBoard.setE94MA4(req.getParameter("E94MA4") + req.getParameter("E94MA42"));
		msgBoard.setEA4MA4(req.getParameter("EA4MA4") + req.getParameter("EA4MA42"));
*/
	 	msgBoard.send();	
	 	msgBoard.destroy();
	 	flexLog("ESD000004 Message Sent");
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
		if (!IsNotError) userPO.setOption("BOARD");
		else userPO.setOption("");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBoard = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBoard = (ESD000004Message)newmessage;
			// showESD000004(msgBoard);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("board", msgBoard);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_board.jsp");
					callPage(LangPath + "ESD0080_client_corp_board.jsp", req, res);	
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
protected void procActionCorpLiabilities(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgLiabilities = null;
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
		msgLiabilities = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgLiabilities = (ESD000004Message)ses.getAttribute("liabilities");
		msgLiabilities.setH04USR(user.getH01USR());
	 	msgLiabilities.setH04PGM("ESD0080");
	 	msgLiabilities.setH04TIM(getTimeStamp());
	 	msgLiabilities.setH04SCR("01");
	 	msgLiabilities.setH04OPE("0005");
	 	msgLiabilities.setE04CUN(userPO.getCusNum());
	 	msgLiabilities.setE04RTP("A");

	 	// all the fields here
	 	java.util.Enumeration enu = msgLiabilities.fieldEnumeration();
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

	 	msgLiabilities.send();	
	 	msgLiabilities.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgLiabilities = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLiabilities = (ESD000004Message)newmessage;
			// showESD000004(msgLiabilities);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("liabilities", msgLiabilities);

			if (IsNotError) {  // There are no errors
				try {
					
					if ( userPO.getOption().equals("CLIENT_P") ) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
					} else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
						
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_liabilities.jsp");
					callPage(LangPath + "ESD0080_client_corp_liabilities.jsp", req, res);	
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
protected void procActionCorpStockHolder(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgStockHolder = null;
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
		msgStockHolder = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgStockHolder = (ESD000004Message)ses.getAttribute("stockHolder");
		msgStockHolder.setH04USR(user.getH01USR());
	 	msgStockHolder.setH04PGM("ESD0080");
	 	msgStockHolder.setH04TIM(getTimeStamp());
	 	msgStockHolder.setH04SCR("01");
	 	msgStockHolder.setH04OPE("0005");
	 	msgStockHolder.setE04CUN(userPO.getCusNum());
	 	msgStockHolder.setE04RTP("2");

	 	// all the fields here
	 	java.util.Enumeration enu = msgStockHolder.fieldEnumeration();
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
/*
		msgStockHolder.setE14MA4(req.getParameter("E14MA4") + req.getParameter("E14MA42"));
		msgStockHolder.setE24MA4(req.getParameter("E24MA4") + req.getParameter("E24MA42"));
		msgStockHolder.setE34MA4(req.getParameter("E34MA4") + req.getParameter("E34MA42"));
		msgStockHolder.setE44MA4(req.getParameter("E44MA4") + req.getParameter("E44MA42"));
		msgStockHolder.setE54MA4(req.getParameter("E54MA4") + req.getParameter("E54MA42"));
		msgStockHolder.setE64MA4(req.getParameter("E64MA4") + req.getParameter("E64MA42"));
		msgStockHolder.setE74MA4(req.getParameter("E74MA4") + req.getParameter("E74MA42"));
		msgStockHolder.setE84MA4(req.getParameter("E84MA4") + req.getParameter("E84MA42"));
		msgStockHolder.setE94MA4(req.getParameter("E94MA4") + req.getParameter("E94MA42"));
		msgStockHolder.setEA4MA4(req.getParameter("EA4MA4") + req.getParameter("EA4MA42"));
*/
	 	msgStockHolder.send();	
	 	msgStockHolder.destroy();
	 	flexLog("ESD000004 Message Sent");
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
		if (!IsNotError) userPO.setOption("STOCKHOLDER");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgStockHolder = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgStockHolder = (ESD000004Message)newmessage;
			// showESD000004(msgStockHolder);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("stockHolder", msgStockHolder);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_stock_holder.jsp");
					callPage(LangPath + "ESD0080_client_corp_stock_holder.jsp", req, res);	
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
protected void procActionEnterInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
	ESD008002Message msgClientEntity = null;
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
		msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
	 	msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0080");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE("0004");
	 	try {
		 	if (req.getParameter("E01CUN") != null)
			 	msgClientPersonal.setE01CUN(req.getParameter("E01CUN"));
		}
		catch (Exception e)
		{
			msgClientPersonal.setE01CUN("0");
		    flexLog("Input data error " + e);
		}
		try {
		 	if (req.getParameter("E01IDN") != null &&
		 		req.getParameter("E01IDN").indexOf("-") != -1	) {			 	
		 		msgClientPersonal.setE01LN3(req.getParameter("E01IDN"));
		 	}else {		 		
		 		msgClientPersonal.setE01IDN(req.getParameter("E01IDN"));
		 	}
		}
		catch (Exception e)
		{
			msgClientPersonal.setE01IDN("");
		    flexLog("Input data error " + e);
		}
	 	//msgClientPersonal.setE01IDN("");
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message();
				flexLog("ESD008001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setOption("CLIENT_P");
			userPO.setHeader1(msgClientPersonal.getE01CUN());
			userPO.setHeader2(msgClientPersonal.getE01IDN());
			userPO.setHeader3(msgClientPersonal.getE01NA1());
			
			if (!msgClientPersonal.getE01LN3().equals("") &&
     			 msgClientPersonal.getE01LN3().indexOf("-") != -1 ) {
					
				 userPO.setHeader2(msgClientPersonal.getE01LN3());
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_inq_personal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
					callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		else if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClientEntity = new datapro.eibs.beans.ESD008002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientEntity = (ESD008002Message)newmessage;

			userPO.setCusNum(msgClientEntity.getE02CUN());
			userPO.setOption("CLIENT_C");
			userPO.setHeader1(msgClientEntity.getE02CUN());
			userPO.setHeader2(msgClientEntity.getE02IDN());
			userPO.setHeader3(msgClientEntity.getE02NA1());

			if (!msgClientEntity.getE02LN3().equals("") &&
				 msgClientEntity.getE02LN3().indexOf("-") != -1 ) {
					
				 userPO.setHeader2(msgClientEntity.getE02LN3()); 
			}

			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientEntity);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_inq_corp_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
					callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);	
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
protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
	ESD008002Message msgClientEntity = null;
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
		msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
	 	msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0080");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE("0002");
	 	try {
		 	if (req.getParameter("E01CUN") != null)
			 	msgClientPersonal.setE01CUN(req.getParameter("E01CUN"));
		}
		catch (Exception e)
		{
			msgClientPersonal.setE01CUN("0");
		    flexLog("Input data error " + e);
		}
		try {
		 	if (req.getParameter("E01IDN") != null && 
		 		req.getParameter("E01IDN").indexOf("-") != -1	){
//		 	 For PANAMA LONG IDENTIFICATION
		 		msgClientPersonal.setE01LN3(req.getParameter("E01IDN"));			 		
		 	}else {
		 		msgClientPersonal.setE01IDN(req.getParameter("E01IDN"));
		 	}
		}
		catch (Exception e)
		{
			msgClientPersonal.setE01IDN("");
		    flexLog("Input data error " + e);
		}
	 	//msgClientPersonal.setE01IDN("");
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message();
				flexLog("ESD008001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setOption("CLIENT_P");
			userPO.setCusType(msgClientPersonal.getE01LGT());
			userPO.setHeader1(msgClientPersonal.getE01CUN());
			userPO.setHeader2(msgClientPersonal.getE01IDN());
			userPO.setHeader3(msgClientPersonal.getE01NA1());
			
			if (!msgClientPersonal.getE01LN3().equals("") &&
				msgClientPersonal.getE01LN3().indexOf("-") != -1 ) {
				
				userPO.setHeader2(msgClientPersonal.getE01LN3());
			}
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: /pages/" + LangPath + "ESD0080_client_both_enter.jsp");
					callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		else if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClientEntity = new datapro.eibs.beans.ESD008002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientEntity = (ESD008002Message)newmessage;

			userPO.setCusNum(msgClientEntity.getE02CUN());
			userPO.setCusType(msgClientEntity.getE02LGT());
			userPO.setOption("CLIENT_C");
			userPO.setHeader1(msgClientEntity.getE02CUN());
			userPO.setHeader2(msgClientEntity.getE02IDN());
			userPO.setHeader3(msgClientEntity.getE02NA1());

			if (!msgClientEntity.getE02LN3().equals("") &&
					msgClientEntity.getE02LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClientEntity.getE02LN3());
			}


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientEntity);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_enter.jsp");
					callPage(LangPath + "ESD0080_client_enter.jsp", req, res);	
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
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
	ESD008002Message msgClientEntity = null;
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
		if (req.getParameter("TYPE").equals("PERSONAL") || req.getParameter("TYPE").equals("MINOR")) {
			flexLog("Entering to Personal");
			msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
		 	msgClientPersonal.setH01USR(user.getH01USR());
		 	msgClientPersonal.setH01PGM("ESD0080");
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
			 	if (req.getParameter("TYPE").equals("MINOR"))
			 		msgClientPersonal.setH01WK3("M");
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
		else {
			flexLog("Entering to Corporative or Other");
			msgClientEntity = (ESD008002Message)mc.getMessageRecord("ESD008002");
		 	msgClientEntity.setH02USR(user.getH01USR());
		 	msgClientEntity.setH02PGM("ESD0080");
		 	msgClientEntity.setH02TIM(getTimeStamp());
		 	msgClientEntity.setH02SCR("01");
		 	msgClientEntity.setE02FL1("1");
		 	if(req.getParameter("TYPE").equals("OTHER")){
		 	  msgClientEntity.setE02LGT("3");
		 	}
		 	else {
		 	  msgClientEntity.setE02LGT("1");
		 	}
		 	
		 	msgClientEntity.setH02OPE("0001");
			try {
			 	if (req.getParameter("CUN") != null)
			 	  msgClientEntity.setE02CUN(req.getParameter("CUN"));
			 	if (req.getParameter("IDN") != null)
				 	  msgClientEntity.setE02IDN(req.getParameter("IDN"));
			 	if (req.getParameter("COUNTRY") != null)
				 	  msgClientEntity.setE02GEC(req.getParameter("COUNTRY"));
			 	if (req.getParameter("IDN2") != null &&
			 		!req.getParameter("IDN2").equals("") ){
			 		//This is Because for PANAMA the Identification uses the long
			 		//format and is stored in the IDN2 field in the jsp, if the IDN2 field has 
			 		//a value, this means that PANAMA is the country for the customer
			 		//and the IDN must be empty.
			 		msgClientEntity.setE02IDN("");
			 		msgClientEntity.setE02LN3(req.getParameter("IDN2"));
			 	  
			 	}

			}
			catch (Exception e)
			{
				msgClientEntity.setE02CUN("0");
			    flexLog("Input data error " + e);
			}
			
			msgClientEntity.send();	
		 	msgClientEntity.destroy();
		 	flexLog("ESD008002 Message Sent");
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
	
	IdentificationData idData = null;
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message();
				flexLog("ESD008001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setOption("CLIENT_P");
		
			if (!msgClientPersonal.getE01LN3().equals("")) {
				idData = new IdentificationData(
					msgClientPersonal.getE01LN3(), msgClientPersonal.getE01GEC());
			} else {
				idData = new IdentificationData(
					msgClientPersonal.getE01IDN(), msgClientPersonal.getE01GEC());
			}	
			idData.setCountryDescription(msgClientPersonal.getD01GEC());
			idData.setLegalType("PERSONAL");
			
			req.setAttribute("idData", idData);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter_new.jsp");
					callPage(LangPath + "ESD0080_client_both_enter_new.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		else if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClientEntity = new datapro.eibs.beans.ESD008002Message();
				flexLog("ESD008002 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientEntity = (ESD008002Message)newmessage;

			userPO.setCusNum(msgClientEntity.getE02CUN());
			userPO.setOption("CLIENT_C");
			
			if (!msgClientEntity.getE02LN3().equals("")) {
				idData = new IdentificationData(
				msgClientEntity.getE02LN3(), msgClientEntity.getE02GEC());				
			} else {
				idData = new IdentificationData(
				msgClientEntity.getE02IDN(), msgClientEntity.getE02GEC());
			}
			idData.setCountryDescription(msgClientEntity.getD02GEC());
			idData.setLegalType("CORPORATE");
			
			req.setAttribute("idData", idData);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientEntity);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
					callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter_new.jsp");
					callPage(LangPath + "ESD0080_client_both_enter_new.jsp", req, res);	
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
protected void procActionEnterReport(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
	ESD008002Message msgClientEntity = null;
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
		msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
	 	msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0080");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE("0004");
	 	try {
		 	if (req.getParameter("E01CUN") != null)
			 	msgClientPersonal.setE01CUN(req.getParameter("E01CUN"));
		}
		catch (Exception e) {
		}
	 	msgClientPersonal.setE01IDN("");
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message();
				flexLog("ESD008001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setOption("CLIENT_P");
			userPO.setHeader1(msgClientPersonal.getE01CUN());
			userPO.setHeader2(msgClientPersonal.getE01IDN());
			userPO.setHeader3(msgClientPersonal.getE01NA1());
			if (!msgClientPersonal.getE01LN3().equals("") &&
					msgClientPersonal.getE01LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClientPersonal.getE01LN3());
			}
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			// ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_report_summary.jsp");
					callPage(LangPath + "ESD0080_client_report_summary.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_report_enter.jsp");
					callPage(LangPath + "ESD0080_client_report_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		else if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClientEntity = new datapro.eibs.beans.ESD008002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientEntity = (ESD008002Message)newmessage;

			userPO.setCusNum(msgClientEntity.getE02CUN());
			userPO.setOption("CLIENT_C");
			userPO.setHeader1(msgClientEntity.getE02CUN());
			userPO.setHeader2(msgClientEntity.getE02IDN());
			userPO.setHeader3(msgClientEntity.getE02NA1());
			if (!msgClientEntity.getE02LN3().equals("") &&
					msgClientEntity.getE02LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClientEntity.getE02LN3());
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			// ses.setAttribute("client", msgClientEntity);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_report_summary.jsp");
					callPage(LangPath + "ESD0080_client_report_summary.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_report_enter.jsp");
					callPage(LangPath + "ESD0080_client_report_enter.jsp", req, res);	
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
protected void procActionLegalRep(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgLegalRep = null;
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
		msgLegalRep = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgLegalRep = (ESD000004Message)ses.getAttribute("legalRep");
		msgLegalRep.setH04USR(user.getH01USR());
	 	msgLegalRep.setH04PGM("ESD0080");
	 	msgLegalRep.setH04TIM(getTimeStamp());
	 	msgLegalRep.setH04SCR("01");
	 	msgLegalRep.setH04OPE("0005");
	 	msgLegalRep.setE04CUN(userPO.getCusNum());
	 	msgLegalRep.setE04RTP("5");

	 	// all the fields here
	 	java.util.Enumeration enu = msgLegalRep.fieldEnumeration();
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

/*
		msgLegalRep.setE14MA4(req.getParameter("E14MA4") + req.getParameter("E14MA42"));
		msgLegalRep.setE24MA4(req.getParameter("E24MA4") + req.getParameter("E24MA42"));
		msgLegalRep.setE34MA4(req.getParameter("E34MA4") + req.getParameter("E34MA42"));
		msgLegalRep.setE44MA4(req.getParameter("E44MA4") + req.getParameter("E44MA42"));
		msgLegalRep.setE54MA4(req.getParameter("E54MA4") + req.getParameter("E54MA42"));
		msgLegalRep.setE64MA4(req.getParameter("E64MA4") + req.getParameter("E64MA42"));
		msgLegalRep.setE74MA4(req.getParameter("E74MA4") + req.getParameter("E74MA42"));
		msgLegalRep.setE84MA4(req.getParameter("E84MA4") + req.getParameter("E84MA42"));
		msgLegalRep.setE94MA4(req.getParameter("E94MA4") + req.getParameter("E94MA42"));
		msgLegalRep.setEA4MA4(req.getParameter("EA4MA4") + req.getParameter("EA4MA42"));
*/

	 	msgLegalRep.send();	
	 	msgLegalRep.destroy();
	 	flexLog("ESD000004 Message Sent");
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
		if (!IsNotError) userPO.setOption("LEGALREP");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgLegalRep = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLegalRep = (ESD000004Message)newmessage;
			// showESD000004(msgLegalRep);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("legalRep", msgLegalRep);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getCusType().equals("2")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_legal_rep.jsp");
					callPage(LangPath + "ESD0080_client_both_legal_rep.jsp", req, res);	
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
protected void procActionMailAddress(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgMailAddress = null;
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
		msgMailAddress = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgMailAddress = (ESD000004Message)ses.getAttribute("mailA");
		msgMailAddress.setH04USR(user.getH01USR());
	 	msgMailAddress.setH04PGM("ESD0080");
	 	msgMailAddress.setH04TIM(getTimeStamp());
	 	msgMailAddress.setH04SCR("01");
	 	msgMailAddress.setH04OPE("0005");
	 	msgMailAddress.setE04CUN(userPO.getCusNum());
	 	msgMailAddress.setE04RTP("1");
		
	 	// all the fields here
	 	java.util.Enumeration enu = msgMailAddress.fieldEnumeration();
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

	 	msgMailAddress.send();	
	 	msgMailAddress.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgMailAddress = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgMailAddress = (ESD000004Message)newmessage;
			// showESD000004(msgMailAddress);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("mailA", msgMailAddress);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_mail_address.jsp");
					callPage(LangPath + "ESD0080_client_both_mail_address.jsp", req, res);	
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
protected void procActionAccountTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgMailAddress = null;
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
		msgMailAddress = (ESD000004Message)mc.getMessageRecord("ESD000004");
		// msgMailAddress = (ESD000004Message)ses.getAttribute("mailA");
		msgMailAddress.setH04USR(user.getH01USR());
		msgMailAddress.setH04PGM("ESD0080");
		msgMailAddress.setH04TIM(getTimeStamp());
		msgMailAddress.setH04SCR("01");
		msgMailAddress.setH04OPE("0005");
		msgMailAddress.setE04CUN(userPO.getCusNum());
		msgMailAddress.setE04RTP("1");
		msgMailAddress.setH04WK1("T");
		
		// all the fields here
		java.util.Enumeration enu = msgMailAddress.fieldEnumeration();
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

		msgMailAddress.send();	
		msgMailAddress.destroy();
		flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgMailAddress = new datapro.eibs.beans.ESD000004Message();
				flexLog("ESD000004 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgMailAddress = (ESD000004Message)newmessage;
			// showESD000004(msgMailAddress);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("mailA", msgMailAddress);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_account_title.jsp");
					callPage(LangPath + "ESD0080_client_both_account_title.jsp", req, res);	
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
protected void procActionMarketing(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008009Message msgMarketing = null;
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
		msgMarketing = (ESD008009Message)mc.getMessageRecord("ESD008009");
	 	// msgMarketing = (ESD008009Message)ses.getAttribute("marketing");
		msgMarketing.setH09USR(user.getH01USR());
	 	msgMarketing.setH09PGM("ESD0080");
	 	msgMarketing.setH09TIM(getTimeStamp());
	 	msgMarketing.setH09SCR("01");
	 	msgMarketing.setH09OPE("0005");
	 	msgMarketing.setE09CUN(userPO.getCusNum());

		// all the fields here
	 	java.util.Enumeration enu = msgMarketing.fieldEnumeration();
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

	 	msgMarketing.send();	
	 	msgMarketing.destroy();
	 	flexLog("ESD008009 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008009")) {
			try {
				msgMarketing = new datapro.eibs.beans.ESD008009Message();
				flexLog("ESD008009 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgMarketing = (ESD008009Message)newmessage;
			// showESD008009(msgMarketing);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("marketing", msgMarketing);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_marketing.jsp");
					callPage(LangPath + "ESD0080_client_both_marketing.jsp", req, res);	
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
protected void procActionPerBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
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
		msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
	 	// msgClientPersonal = (ESD008001Message)ses.getAttribute("client");
		msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0080");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE("0005");
	 	try {
		 	if (req.getParameter("APPROVAL").equals("Y")) msgClientPersonal.setH01OPE("0006");
		}
		catch (Exception e) {
		}
		msgClientPersonal.setE01CUN(req.getParameter("E01CUN"));
		msgClientPersonal.setE01LGT("2"); // Cliente Personal

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

		String tes = "";
		String fid = "";
		String fem = "";
		String ter = "";
	 	
	 	try {if (req.getParameter("E01FL8_TES").equals("1")) tes = "T";	} catch (Exception e) {tes = "";}
		try {if (req.getParameter("E01FL8_FID").equals("1")) fid = "F";	} catch (Exception e) {fid = "";}
		try {if (req.getParameter("E01FL8_FEM").equals("1")) fem = "E";	} catch (Exception e) {fem = "";}
		try {if (req.getParameter("E01FL8_TER").equals("1")) ter = "R";	} catch (Exception e) {ter = "";}
	 	 
	 	msgClientPersonal.setE01FL8(tes + fid + fem + ter);
	 	
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message(); 
				flexLog("ESD008001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE01CUN());
			userPO.setHeader1(msgClientPersonal.getE01CUN());
			userPO.setHeader2(msgClientPersonal.getE01IDN());
			userPO.setHeader3(msgClientPersonal.getE01NA1());
			if (!msgClientPersonal.getE01LN3().equals("") &&
					msgClientPersonal.getE01LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClientPersonal.getE01LN3());
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
		    ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
			    if (userPO.getPurpose().equals("NEW") || msgClientPersonal.getE01SFR().equals("A")) {   
					userPO.setPurpose("MAINTENANCE");
				    ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_confirm.jsp");
						callPage(LangPath + "ESD0080_client_personal_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			    else {
					try {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
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
protected void procActionPerBene(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBene = null;
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
		msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgBene = (ESD000004Message)ses.getAttribute("bene");
		msgBene.setH04USR(user.getH01USR());
	 	msgBene.setH04PGM("ESD0080");
	 	msgBene.setH04TIM(getTimeStamp());
	 	msgBene.setH04SCR("01");
	 	msgBene.setH04OPE("0005");
	 	msgBene.setE04CUN(userPO.getCusNum());
		msgBene.setE04RTP("4");

		// all the fields here
	 	java.util.Enumeration enu = msgBene.fieldEnumeration();
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
		 	
		msgBene.send();	
	 	msgBene.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBene = new datapro.eibs.beans.ESD000004Message(); 
				flexLog("ESD000004 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBene = (ESD000004Message)newmessage;
			// showESD000004(msgBene);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bene", msgBene);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_bene.jsp");
					callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_bene.jsp");
					callPage(LangPath + "ESD0080_client_personal_bene.jsp", req, res);	
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
protected void procActionPerEmployment(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008006Message msgEmployment = null;
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
		msgEmployment = (ESD008006Message)mc.getMessageRecord("ESD008006");
	 	// msgEmployment = (ESD008006Message)ses.getAttribute("employment");
		msgEmployment.setH06USR(user.getH01USR());
	 	msgEmployment.setH06PGM("ESD0080");
	 	msgEmployment.setH06TIM(getTimeStamp());
	 	msgEmployment.setH06SCR("01");
	 	msgEmployment.setH06OPE("0005");
		// all the fields here
	 	msgEmployment.setE06CUN(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgEmployment.fieldEnumeration();
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

	 	msgEmployment.send();	
	 	msgEmployment.destroy();
	 	flexLog("ESD008006 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008006")) {
			try {
				msgEmployment = new datapro.eibs.beans.ESD008006Message(); 
				flexLog("ESD008006 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgEmployment = (ESD008006Message)newmessage;
			// showESD008006(msgEmployment);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("employment", msgEmployment);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: /servlet/datapro.eibs.client.JSESD0080?SCREEN=1");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=1");
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_employment.jsp");
					callPage(LangPath + "ESD0080_client_personal_employment.jsp", req, res);	
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
protected void procActionPerRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgPerRef = null;
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
		msgPerRef = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	// msgPerRef = (ESD000004Message)ses.getAttribute("perRef");
		msgPerRef.setH04USR(user.getH01USR());
	 	msgPerRef.setH04PGM("ESD0080");
	 	msgPerRef.setH04TIM(getTimeStamp());
	 	msgPerRef.setH04SCR("01");
	 	msgPerRef.setH04OPE("0005");
	 	msgPerRef.setE04CUN(userPO.getCusNum());
	 	msgPerRef.setE04RTP("P");

	 	// all the fields here
	 	java.util.Enumeration enu = msgPerRef.fieldEnumeration();
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

		msgPerRef.send();	
	 	msgPerRef.destroy();
	 	flexLog("ESD000004 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgPerRef = new datapro.eibs.beans.ESD000004Message(); 
				flexLog("ESD000004 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPerRef = (ESD000004Message)newmessage;
			// showESD000004(msgPerRef);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("perRef", msgPerRef);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_per_ref.jsp");
					callPage(LangPath + "ESD0080_client_both_per_ref.jsp", req, res);	
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
protected void procActionSpecialInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgSpecialInst = null;
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
		msgSpecialInst = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	// msgSpecialInst = (ESD000005Message)ses.getAttribute("specInst");
		msgSpecialInst.setH05USR(user.getH01USR());
	 	msgSpecialInst.setH05PGM("ESD0080");
	 	msgSpecialInst.setH05TIM(getTimeStamp());
	 	msgSpecialInst.setH05SCR("01");
	 	msgSpecialInst.setH05OPE("0005");
	 	msgSpecialInst.setE05TYP("8");
	 	msgSpecialInst.setE05ACC(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgSpecialInst.fieldEnumeration();
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

	 	msgSpecialInst.send();	
	 	msgSpecialInst.destroy();
	 	flexLog("ESD000005 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgSpecialInst = new datapro.eibs.beans.ESD000005Message(); 
				flexLog("ESD000005 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgSpecialInst = (ESD000005Message)newmessage;
			// showESD000005(msgSpecialInst);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("specInst", msgSpecialInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_special_inst.jsp");
					callPage(LangPath + "ESD0080_client_both_special_inst.jsp", req, res);	
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
protected void procReqBankRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBR = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgBR = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgBR.setH04USR(user.getH01USR());
	 	msgBR.setH04PGM("ESD0080");
	 	msgBR.setH04TIM(getTimeStamp());
	 	msgBR.setH04SCR("01");
	 	msgBR.setH04OPE(opCode);
	 	msgBR.setE04CUN(userPO.getCusNum());
	 	msgBR.setE04RTP("6");
		msgBR.send();	
	 	msgBR.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBR = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBR = (ESD000004Message)newmessage;
			// showESD000004(msgBR);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bankRef", msgBR);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_bank_ref.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_bank_ref.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_bank_ref.jsp");
						callPage(LangPath + "ESD0080_client_both_bank_ref.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
	ESD008011Message msgClient = null;
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
		msgClient = (ESD008011Message)mc.getMessageRecord("ESD008011");
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
		
	 	//msgClientPersonal.setE01IDN("");
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

		if (newmessage.getFormatName().equals("ESD008011")) {
			try {
				msgClient = new datapro.eibs.beans.ESD008011Message(); 
				flexLog("ESD008011 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClient = (ESD008011Message)newmessage;
		
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

/**
 * This method was created in VisualAge.
 */
protected void procReqCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008003Message msgCodes = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgCodes = (ESD008003Message)mc.getMessageRecord("ESD008003");
	 	msgCodes.setH03USR(user.getH01USR());
	 	msgCodes.setH03PGM("ESD0080");
	 	msgCodes.setH03TIM(getTimeStamp());
	 	msgCodes.setH03SCR("01");
	 	msgCodes.setH03OPE(opCode);
	 	msgCodes.setE03CUN(userPO.getCusNum());
		msgCodes.send();	
	 	msgCodes.destroy();
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

		if (newmessage.getFormatName().equals("ESD008003")) {
			try {
				msgCodes = new datapro.eibs.beans.ESD008003Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCodes = (ESD008003Message)newmessage;
			//showESD008003(msgCodes);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("codes", msgCodes);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_codes.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_codes.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_codes.jsp");
						callPage(LangPath + "ESD0080_client_both_codes.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCom(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000007Message msgCom = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgCom = (ESD000007Message)mc.getMessageRecord("ESD000007");
	 	msgCom.setH07USR(user.getH01USR());
	 	msgCom.setH07PGM("ESD0080");
	 	msgCom.setH07TIM(getTimeStamp());
	 	msgCom.setH07SCR("01");
	 	msgCom.setH07OPE(opCode);
	 	msgCom.setE07CUN(userPO.getCusNum());
		msgCom.send();	
	 	msgCom.destroy();
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

		if (newmessage.getFormatName().equals("ESD000007")) {
			try {
				msgCom = new datapro.eibs.beans.ESD000007Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCom = (ESD000007Message)newmessage;
			// showESD000007(msgCom);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("com", msgCom);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_com.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_com.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_com.jsp");
						callPage(LangPath + "ESD0080_client_both_com.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqComRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgCR = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgCR = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgCR.setH04USR(user.getH01USR());
	 	msgCR.setH04PGM("ESD0080");
	 	msgCR.setH04TIM(getTimeStamp());
	 	msgCR.setH04SCR("01");
	 	msgCR.setH04OPE(opCode);
	 	msgCR.setE04CUN(userPO.getCusNum());
	 	msgCR.setE04RTP("7");
		msgCR.send();	
	 	msgCR.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgCR = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCR = (ESD000004Message)newmessage;
			// showESD000004(msgCR);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("comRef", msgCR);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_com_ref.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_com_ref.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_com_ref.jsp");
						callPage(LangPath + "ESD0080_client_both_com_ref.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpAssets(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgAssets = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgAssets = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgAssets.setH04USR(user.getH01USR());
	 	msgAssets.setH04PGM("ESD0080");
	 	msgAssets.setH04TIM(getTimeStamp());
	 	msgAssets.setH04SCR("01");
	 	msgAssets.setH04OPE(opCode);
	 	msgAssets.setE04CUN(userPO.getCusNum());
	 	msgAssets.setE04RTP("9");
		msgAssets.send();	
	 	msgAssets.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgAssets = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgAssets = (ESD000004Message)newmessage;
			// showESD000004(msgAssets);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("assets", msgAssets);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_assets.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_assets.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_assets.jsp");
						callPage(LangPath + "ESD0080_client_corp_assets.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESD008002Message msgClientEntity = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgClientEntity = (ESD008002Message)mc.getMessageRecord("ESD008002");
	 	msgClientEntity.setH02USR(user.getH01USR());
	 	msgClientEntity.setH02PGM("ESD0080");
	 	msgClientEntity.setH02TIM(getTimeStamp());
	 	msgClientEntity.setH02SCR("01");
	 	msgClientEntity.setH02OPE(opCode);
	 	msgClientEntity.setE02CUN(userPO.getCusNum());
		msgClientEntity.send();	
	 	msgClientEntity.destroy();
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

		if (newmessage.getFormatName().equals("ESD008002")) {
			try {
				msgClientEntity = new datapro.eibs.beans.ESD008002Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientEntity = (ESD008002Message)newmessage;

			userPO.setHeader1(msgClientEntity.getE02CUN());
			userPO.setHeader2(msgClientEntity.getE02IDN());
			userPO.setHeader3(msgClientEntity.getE02NA1());
			if (!msgClientEntity.getE02LN3().equals("") &&
					msgClientEntity.getE02LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClientEntity.getE02LN3());
			}
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientEntity);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_basic.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpBoard(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBoard = null;
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
	
	userPO.setOption("BOARD");

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgBoard = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgBoard.setH04USR(user.getH01USR());
	 	msgBoard.setH04PGM("ESD0080");
	 	msgBoard.setH04TIM(getTimeStamp());
	 	msgBoard.setH04SCR("01");
	 	msgBoard.setH04OPE(opCode);
	 	msgBoard.setE04CUN(userPO.getCusNum());
	 	msgBoard.setE04RTP("3");
		msgBoard.send();	
	 	msgBoard.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBoard = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBoard = (ESD000004Message)newmessage;
			// showESD000004(msgBoard);
/*			
			if (msgBoard.getE14MA4().length() < 5) msgBoard.setE14MA4("00000 ");
			if (msgBoard.getE24MA4().length() < 5) msgBoard.setE24MA4("00000 ");
			if (msgBoard.getE34MA4().length() < 5) msgBoard.setE34MA4("00000 ");
			if (msgBoard.getE44MA4().length() < 5) msgBoard.setE44MA4("00000 ");
			if (msgBoard.getE54MA4().length() < 5) msgBoard.setE54MA4("00000 ");
			if (msgBoard.getE64MA4().length() < 5) msgBoard.setE64MA4("00000 ");
			if (msgBoard.getE74MA4().length() < 5) msgBoard.setE74MA4("00000 ");
			if (msgBoard.getE84MA4().length() < 5) msgBoard.setE84MA4("00000 ");
			if (msgBoard.getE94MA4().length() < 5) msgBoard.setE94MA4("00000 ");
			if (msgBoard.getEA4MA4().length() < 5) msgBoard.setEA4MA4("00000 ");
*/
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("board", msgBoard);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_board.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_board.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_board.jsp");
						callPage(LangPath + "ESD0080_client_corp_board.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpCapital(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008008Message msgCapital = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgCapital = (ESD008008Message)mc.getMessageRecord("ESD008008");
	 	msgCapital.setH08USR(user.getH01USR());
	 	msgCapital.setH08PGM("ESD0080");
	 	msgCapital.setH08TIM(getTimeStamp());
	 	msgCapital.setH08SCR("01");
	 	msgCapital.setH08OPE(opCode);
	 	msgCapital.setE08CUN(userPO.getCusNum());
		msgCapital.send();	
	 	msgCapital.destroy();
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

		if (newmessage.getFormatName().equals("ESD008008")) {
			try {
				msgCapital = new datapro.eibs.beans.ESD008008Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCapital = (ESD008008Message)newmessage;
			// showESD008008(msgCapital);
			
			BigDecimal liquid = new java.math.BigDecimal(0);
			BigDecimal asset = msgCapital.getBigDecimalE08A11();
			BigDecimal liabilities = msgCapital.getBigDecimalE08L11();
			
			liquid = liquid.add(asset);
			liquid = liquid.subtract(liabilities);						
			userPO.setHeader21(Util.formatCCY(liquid.toString()));
			flexLog("asset :  " + asset.toString());
			flexLog("liabilities :  " + liabilities.toString());
			flexLog("liquid :  " + liquid.toString()); 
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("capital", msgCapital);
			ses.setAttribute("userPO", userPO);
			
			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_capital.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_capital.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_capital.jsp");
						callPage(LangPath + "ESD0080_client_corp_capital.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpLiabilities(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgLiabilities = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgLiabilities = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgLiabilities.setH04USR(user.getH01USR());
	 	msgLiabilities.setH04PGM("ESD0080");
	 	msgLiabilities.setH04TIM(getTimeStamp());
	 	msgLiabilities.setH04SCR("01");
	 	msgLiabilities.setH04OPE(opCode);
	 	msgLiabilities.setE04CUN(userPO.getCusNum());
	 	msgLiabilities.setE04RTP("A");
		msgLiabilities.send();	
	 	msgLiabilities.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgLiabilities = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLiabilities = (ESD000004Message)newmessage;
			// showESD000004(msgLiabilities);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("liabilities", msgLiabilities);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_liabilities.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_liabilities.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_liabilities.jsp");
						callPage(LangPath + "ESD0080_client_corp_liabilities.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqCorpStockHolder(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgStockHolder = null;
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

	userPO.setOption("STOCKHOLDER");

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgStockHolder = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgStockHolder.setH04USR(user.getH01USR());
	 	msgStockHolder.setH04PGM("ESD0080");
	 	msgStockHolder.setH04TIM(getTimeStamp());
	 	msgStockHolder.setH04SCR("01");
	 	msgStockHolder.setH04OPE(opCode);
	 	msgStockHolder.setE04CUN(userPO.getCusNum());
	 	msgStockHolder.setE04RTP("2");
		msgStockHolder.send();	
	 	msgStockHolder.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgStockHolder = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgStockHolder = (ESD000004Message)newmessage;
			// showESD000004(msgStockHolder);
/*			
			if (msgStockHolder.getE14MA4().length() < 5) msgStockHolder.setE14MA4("00000 ");
			if (msgStockHolder.getE24MA4().length() < 5) msgStockHolder.setE24MA4("00000 ");
			if (msgStockHolder.getE34MA4().length() < 5) msgStockHolder.setE34MA4("00000 ");
			if (msgStockHolder.getE44MA4().length() < 5) msgStockHolder.setE44MA4("00000 ");
			if (msgStockHolder.getE54MA4().length() < 5) msgStockHolder.setE54MA4("00000 ");
			if (msgStockHolder.getE64MA4().length() < 5) msgStockHolder.setE64MA4("00000 ");
			if (msgStockHolder.getE74MA4().length() < 5) msgStockHolder.setE74MA4("00000 ");
			if (msgStockHolder.getE84MA4().length() < 5) msgStockHolder.setE84MA4("00000 ");
			if (msgStockHolder.getE94MA4().length() < 5) msgStockHolder.setE94MA4("00000 ");
			if (msgStockHolder.getEA4MA4().length() < 5) msgStockHolder.setEA4MA4("00000 ");
*/
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("stockHolder", msgStockHolder);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_stock_holder.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_stock_holder.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_stock_holder.jsp");
						callPage(LangPath + "ESD0080_client_corp_stock_holder.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqEnterInq(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ESD008001Message msgClient = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgClient = new datapro.eibs.beans.ESD008001Message(); 
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos(); 
		userPO.setOption("CLIENT");
		userPO.setPurpose("INQUIRY");
		ses.setAttribute("client", msgClient);
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
		callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqEnterMaint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ESD008001Message msgClient = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgClient = new datapro.eibs.beans.ESD008001Message(); 
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos(); 
		userPO.setOption("CLIENT");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("client", msgClient);
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
		callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
	}

}
/**
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
		flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter_new.jsp");
		callPage(LangPath + "ESD0080_client_both_enter_new.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqEnterReport(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ESD008001Message msgClient = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgClient = new datapro.eibs.beans.ESD008001Message(); 
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos(); 
		userPO.setOption("CLIENT");
		userPO.setPurpose("REPORT");
		ses.setAttribute("client", msgClient);
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0080_client_report_enter.jsp");
		callPage(LangPath + "ESD0080_client_report_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqLegalRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgLR = null;
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

	//userPO.setOption("LEGALREP");

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgLR = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgLR.setH04USR(user.getH01USR());
	 	msgLR.setH04PGM("ESD0080");
	 	msgLR.setH04TIM(getTimeStamp());
	 	msgLR.setH04SCR("01");
	 	msgLR.setH04OPE(opCode);
	 	msgLR.setE04CUN(userPO.getCusNum());
	 	msgLR.setE04RTP("5");
		msgLR.send();	
	 	msgLR.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgLR = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLR = (ESD000004Message)newmessage;
			// showESD000004(msgLR);
/*			
			if (msgLR.getE14MA4().length() < 5) msgLR.setE14MA4("00000 ");
			if (msgLR.getE24MA4().length() < 5) msgLR.setE24MA4("00000 ");
			if (msgLR.getE34MA4().length() < 5) msgLR.setE34MA4("00000 ");
			if (msgLR.getE44MA4().length() < 5) msgLR.setE44MA4("00000 ");
			if (msgLR.getE54MA4().length() < 5) msgLR.setE54MA4("00000 ");
			if (msgLR.getE64MA4().length() < 5) msgLR.setE64MA4("00000 ");
			if (msgLR.getE74MA4().length() < 5) msgLR.setE74MA4("00000 ");
			if (msgLR.getE84MA4().length() < 5) msgLR.setE84MA4("00000 ");
			if (msgLR.getE94MA4().length() < 5) msgLR.setE94MA4("00000 ");
			if (msgLR.getEA4MA4().length() < 5) msgLR.setEA4MA4("00000 ");
*/
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("legalRep", msgLR);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_legal_rep.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_legal_rep.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_legal_rep.jsp");
						callPage(LangPath + "ESD0080_client_both_legal_rep.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqMailAddress(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgMailA = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgMailA = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgMailA.setH04USR(user.getH01USR());
	 	msgMailA.setH04PGM("ESD0080");
	 	msgMailA.setH04TIM(getTimeStamp());
	 	msgMailA.setH04SCR("01");
	 	msgMailA.setH04OPE(opCode);
	 	msgMailA.setE04CUN(userPO.getCusNum());
	 	msgMailA.setE04RTP("1");
		msgMailA.send();	
	 	msgMailA.destroy();
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
	    flexLog( newmessage.toString() );

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgMailA = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgMailA = (ESD000004Message)newmessage;
			// showESD000004(msgMailA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("mailA", msgMailA);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_mail_address.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_mail_address.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_mail_address.jsp");
						callPage(LangPath + "ESD0080_client_both_mail_address.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqAccountTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgMailA = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgMailA = (ESD000004Message)mc.getMessageRecord("ESD000004");
		msgMailA.setH04USR(user.getH01USR());
		msgMailA.setH04PGM("ESD0080");
		msgMailA.setH04TIM(getTimeStamp());
		msgMailA.setH04SCR("01");
		msgMailA.setH04OPE(opCode);
		msgMailA.setE04CUN(userPO.getCusNum());
		msgMailA.setE04RTP("1");
		msgMailA.setH04WK1("T");
		msgMailA.send();	
		msgMailA.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgMailA = new datapro.eibs.beans.ESD000004Message(); 
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgMailA = (ESD000004Message)newmessage;
			// showESD000004(msgMailA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("mailA", msgMailA);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_account_title.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_account_title.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_account_title.jsp");
						callPage(LangPath + "ESD0080_client_both_account_title.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqMarketing(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008009Message msgMarketing = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgMarketing = (ESD008009Message)mc.getMessageRecord("ESD008009");
	 	msgMarketing.setH09USR(user.getH01USR());
	 	msgMarketing.setH09PGM("ESD0080");
	 	msgMarketing.setH09TIM(getTimeStamp());
	 	msgMarketing.setH09SCR("01");
	 	msgMarketing.setH09OPE(opCode);
	 	msgMarketing.setE09CUN(userPO.getCusNum());
		msgMarketing.send();	
	 	msgMarketing.destroy();
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

		if (newmessage.getFormatName().equals("ESD008009")) {
			try {
				msgMarketing = new datapro.eibs.beans.ESD008009Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgMarketing = (ESD008009Message)newmessage;
			//showESD008009(msgMarketing);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("marketing", msgMarketing);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_marketing.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_marketing.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_marketing.jsp");
						callPage(LangPath + "ESD0080_client_both_marketing.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqPerBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008001Message msgClientPersonal = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgClientPersonal = (ESD008001Message)mc.getMessageRecord("ESD008001");
	 	msgClientPersonal.setH01USR(user.getH01USR());
	 	msgClientPersonal.setH01PGM("ESD0080");
	 	msgClientPersonal.setH01TIM(getTimeStamp());
	 	msgClientPersonal.setH01SCR("01");
	 	msgClientPersonal.setH01OPE(opCode);
	 	msgClientPersonal.setE01CUN(userPO.getCusNum());
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008001")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008001Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008001Message)newmessage;

			userPO.setHeader1(msgClientPersonal.getE01CUN());
			userPO.setHeader2(msgClientPersonal.getE01IDN());
			userPO.setHeader3(msgClientPersonal.getE01NA1());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_inq_personal_basic.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqPerBene(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBene = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgBene.setH04USR(user.getH01USR());
	 	msgBene.setH04PGM("ESD0080");
	 	msgBene.setH04TIM(getTimeStamp());
	 	msgBene.setH04SCR("01");
	 	msgBene.setH04OPE(opCode);
	 	msgBene.setE04CUN(userPO.getCusNum());
	 	msgBene.setE04RTP("4");
		msgBene.send();	
	 	msgBene.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBene = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBene = (ESD000004Message)newmessage;
			// showESD000004(msgBene);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bene", msgBene);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal_bene.jsp");
						callPage(LangPath + "ESD0080_client_inq_personal_bene.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_bene.jsp");
						callPage(LangPath + "ESD0080_client_personal_bene.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqPerEmployment(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008006Message msgEmployment = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgEmployment = (ESD008006Message)mc.getMessageRecord("ESD008006");
	 	msgEmployment.setH06USR(user.getH01USR());
	 	msgEmployment.setH06PGM("ESD0080");
	 	msgEmployment.setH06TIM(getTimeStamp());
	 	msgEmployment.setH06SCR("01");
	 	msgEmployment.setH06OPE(opCode);
	 	msgEmployment.setE06CUN(userPO.getCusNum());
		msgEmployment.send();	
	 	msgEmployment.destroy();
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

		if (newmessage.getFormatName().equals("ESD008006")) {
			try {
				msgEmployment = new datapro.eibs.beans.ESD008006Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgEmployment = (ESD008006Message)newmessage;
			// showESD008006(msgEmployment);
			if ( userPO.getPurpose().equals("NEW") ){
				msgEmployment.setE06CP1("");
				msgEmployment.setE06CP2("");
				msgEmployment.setE06CP3("");
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("employment", msgEmployment);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal_employment.jsp");
						callPage(LangPath + "ESD0080_client_inq_personal_employment.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_employment.jsp");
						callPage(LangPath + "ESD0080_client_personal_employment.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqPerRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgPR = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgPR = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgPR.setH04USR(user.getH01USR());
	 	msgPR.setH04PGM("ESD0080");
	 	msgPR.setH04TIM(getTimeStamp());
	 	msgPR.setH04SCR("01");
	 	msgPR.setH04OPE(opCode);
	 	msgPR.setE04CUN(userPO.getCusNum());
	 	msgPR.setE04RTP("P");
		msgPR.send();	
	 	msgPR.destroy();
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

		if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgPR = new datapro.eibs.beans.ESD000004Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPR = (ESD000004Message)newmessage;
			// showESD000004(msgPR);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("perRef", msgPR);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_per_ref.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_per_ref.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_per_ref.jsp");
						callPage(LangPath + "ESD0080_client_both_per_ref.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqReport(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESD008010Message msgClient = null;
	ELEERRMessage msgError = null;
	ESD000011Message msgList = null;
	ESD000012Message msgSpecInst = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgClient = (ESD008010Message)mc.getMessageRecord("ESD008010");
	 	msgClient.setH10USR(user.getH01USR());
	 	msgClient.setH10PGM("ESD0080");
	 	msgClient.setH10TIM(getTimeStamp());
	 	msgClient.setH10OPE(opCode);
	 	try {
	 		msgClient.setE10CUN(userPO.getCusNum());
	 	}
	 	catch (Exception e) {
		 	msgClient.setE10CUN("0");
	 	}
		msgClient.send();	
	 	msgClient.destroy();
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
		// showERROR(msgError);
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

		if (newmessage.getFormatName().equals("ESD008010")) {
			try {
				msgClient = new datapro.eibs.beans.ESD008010Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClient = (ESD008010Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) {  // There are no errors
				// Fill List bean
				int colNum = 20;
				try {
					beanList = new datapro.eibs.beans.JBListRec(); 
					beanList.init(colNum);
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
				
				while (true) {

					newmessage = mc.receiveMessage();
					msgList = (ESD000011Message)newmessage;

					marker = msgList.getH11MAS();
					sel = msgList.getE11RTP().charAt(0);

					if (marker.equals("F")) {
						break;
					}
					else if (!marker.equals("*")) {
						switch (sel) {
							case '1':
								msgClient.setXFLAG0("Y");
								break;
							case '4':
								msgClient.setXFLAG1("Y");
								break;
							case '5':
								msgClient.setE10UC3("Y");
								break;
							case '6':
								msgClient.setE10UC4("Y");
								break;
							case '7':
								msgClient.setE10UC5("Y");
								break;
							case '8':
								msgClient.setE10UC6("Y");
								break;
							case '9':
								msgClient.setE10UC7("Y");
								break;
							case 'A':
								msgClient.setE10UC8("Y");
								break;
								
						} 					
						myFlag = msgList.getE11RTP();
						myRow[0] =  Util.formatCell(msgList.getE11MA1());	// Mailing Address 1
						myRow[1] =  Util.formatCell(msgList.getE11MA2());	// Mailing Address 2
						myRow[2] =  Util.formatCell(msgList.getE11MA3());	// Mailing Address 3
						myRow[3] =  Util.formatCell(msgList.getE11CTY());	// City
						myRow[4] =  Util.formatCell(msgList.getD11STE());	// State
						myRow[5] =  Util.formatCell(msgList.getE11ZPC());	// Zip Code
						myRow[6] =  Util.formatCell(msgList.getE11POB());	// POBox
						myRow[7] =  Util.formatCell(msgList.getE11CTR());	// Country
						myRow[8] =  Util.formatCell(msgList.getE11HPN());	// Phone;
						myRow[9] =  Util.formatCell(msgList.getD11BNC());	// Citizenship
						myRow[10] =  Util.formatCell(msgList.getE11BNI());	// Identification
						myRow[11] =  Util.formatCell(msgList.getE11BSX());	// Sexo
						myRow[12] =  Util.formatCell(msgList.getE11BMS());	// Marital Status
						myRow[13] =  Util.formatCell(msgList.getD11TID());	// Identification Type
						myRow[14] =  Util.formatCell(msgList.getD11PID());	// Identification Country
						myRow[15] =  Util.formatCCY(msgList.getE11AM1());	// Amount 1
						myRow[16] =  Util.formatCCY(msgList.getE11AM2());	// Amount 1
						myRow[17] =  Util.formatDate(msgList.getE11DT1(), msgList.getE11DT2(), msgList.getE11DT3());	// Date

						beanList.addRow(myFlag, myRow);
										
					}

				}
/*				
				while (true) {

					msgSpecInst = (ESD000012Message)newmessage;

					marker = msgSpecInst.getH12MAS();

					if (marker.equals("*")) {
						break;
					}
					else {
						msgClient.setE10UC9("Y");
						myFlag = "";
						myRow[0] =  msgSpecInst.getE12DSC();	// Description

						beanList.addRow(myFlag, myRow);
										
					}

					newmessage = mc.receiveMessage();
				}
*/				
				ses.setAttribute("clientList", beanList);
				ses.setAttribute("client", msgClient);
				try {
					// res.setContentType("application/msword");
					flexLog("About to call Page: " + LangPath + "ESD0080_client_report.jsp");
					callPage(LangPath + "ESD0080_client_report.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					res.setContentType("text/html");
					printClose(res.getWriter());
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
protected void procReqSpecInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgSpecInst = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgSpecInst = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgSpecInst.setH05USR(user.getH01USR());
	 	msgSpecInst.setH05PGM("ESD0080");
	 	msgSpecInst.setH05TIM(getTimeStamp());
	 	msgSpecInst.setH05SCR("01");
	 	msgSpecInst.setH05OPE(opCode);
	 	msgSpecInst.setE05ACC(userPO.getCusNum());
	 	msgSpecInst.setE05TYP("8");
		msgSpecInst.send();	
	 	msgSpecInst.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgSpecInst = new datapro.eibs.beans.ESD000005Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgSpecInst = (ESD000005Message)newmessage;
			// showESD000005(msgSpecInst);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("specInst", msgSpecInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_special_inst.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_special_inst.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_special_inst.jsp");
						callPage(LangPath + "ESD0080_client_both_special_inst.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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

		int screen = R_ENTER_MAINT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 2);
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
				// BEGIN Personal
				// Request
				case R_P_BASIC :
					procReqPerBasic(mc, msgUser, req, res, session);
					break;
				case R_P_SHORT_BASIC :
					procReqPerShortBasic(mc, msgUser, req, res, session);
					break;
				case R_P_BENE :
					procReqPerBene(mc, msgUser, req, res, session);
					break;
				case R_P_EMPLOYMENT :
					procReqPerEmployment(mc, msgUser, req, res, session);
					break;
				// Action
				case A_P_BASIC :
					procActionPerBasic(mc, msgUser, req, res, session);
					break;
				case A_P_SHORT_BASIC :
					procActionPerShortBasic(mc, msgUser, req, res, session);
					break;
				case A_P_BENE :
					procActionPerBene(mc, msgUser, req, res, session);
					break;
				case A_P_EMPLOYMENT :
					procActionPerEmployment(mc, msgUser, req, res, session);
					break;
				// END Personal
				
				// BEGIN Corporative 
				// Request
				case R_C_BASIC :
					procReqCorpBasic(mc, msgUser, req, res, session);
					break;
				case R_C_STOCK_HOLDER :
					procReqCorpStockHolder(mc, msgUser, req, res, session);
					break;
				case R_C_BOARD :
					procReqCorpBoard(mc, msgUser, req, res, session);
					break;
				case R_C_ASSETS :
					procReqCorpAssets(mc, msgUser, req, res, session);
					break;
				case R_C_LIABILITIES :
					procReqCorpLiabilities(mc, msgUser, req, res, session);
					break;
				case R_C_CAPITAL :
					procReqCorpCapital(mc, msgUser, req, res, session);
					break;
				// Action
				case A_C_BASIC :
					procActionCorpBasic(mc, msgUser, req, res, session);
					break;
				case A_C_STOCK_HOLDER :
					procActionCorpStockHolder(mc, msgUser, req, res, session);
					break;
				case A_C_BOARD :
					procActionCorpBoard(mc, msgUser, req, res, session);
					break;
				case A_C_ASSETS :
					procActionCorpAssets(mc, msgUser, req, res, session);
					break;
				case A_C_LIABILITIES :
					procActionCorpLiabilities(mc, msgUser, req, res, session);
					break;
				case A_C_CAPITAL :
					break;
				// END Corporative 

				// BEGIN Personal & Corporative 
				// Request
				case R_CODES :
					procReqCodes(mc, msgUser, req, res, session);
					break;
				case R_MAIL_ADDRESS :
					procReqMailAddress(mc, msgUser, req, res, session);
					break;
				case R_ACCOUNT_TITLE :
					procReqAccountTitle(mc, msgUser, req, res, session);
					break;					
				case R_SPECIAL_INST :
					procReqSpecInst(mc, msgUser, req, res, session);
					break;
				case R_KNOW_CUSTOMER :
					procReqKnowYourCustomer(mc, msgUser, req, res, session);
					break;
				case R_COM :
					procReqCom(mc, msgUser, req, res, session);
					break;
				case R_BANK_REF :
					procReqBankRef(mc, msgUser, req, res, session);
					break;
				case R_COM_REF :
					procReqComRef(mc, msgUser, req, res, session);
					break;
				case R_PER_REF :
					procReqPerRef(mc, msgUser, req, res, session);
					break;
				case R_LEGAL_REP :
					procReqLegalRef(mc, msgUser, req, res, session);
					break;
				case R_MARKETING :
					procReqMarketing(mc, msgUser, req, res, session);
					break;
				case R_PAY_INSTRUCTION :
					procReqCorpPayInst(mc, msgUser, req, res, session);
					break;
				case R_PAY_INSTRUCTION_FRAME :
					res.sendRedirect(super.srctx + LangPath + "ESD0080_client_corp_payment_instructions_frame.jsp");
					break;
				case R_ID_CHANGE_ENTER :
					procReqIDChangeEnter(msgUser, req, res, session);
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
						
				// Action
				case A_CODES :
					procActionCodes(mc, msgUser, req, res, session);
					break;
				case A_MAIL_ADDRESS :
					procActionMailAddress(mc, msgUser, req, res, session);
					break;
				case A_ACCOUNT_TITLE :
					procActionAccountTitle(mc, msgUser, req, res, session);
					break;					
				case A_SPECIAL_INST :
					procActionSpecialInst(mc, msgUser, req, res, session);
					break;
				case A_KNOW_CUSTOMER :
					procActionKnowYourCustomer(mc, msgUser, req, res, session);
					break;
				case A_COM :
					procActionCom(mc, msgUser, req, res, session);
					break;
				case A_BANK_REF :
					procActionBankRef(mc, msgUser, req, res, session);
					break;
				case A_COM_REF :
					procActionComRef(mc, msgUser, req, res, session);
					break;
				case A_PER_REF :
					procActionPerRef(mc, msgUser, req, res, session);
					break;
				case A_LEGAL_REP :
					procActionLegalRep(mc, msgUser, req, res, session);
					break;
				case A_MARKETING :
					procActionMarketing(mc, msgUser, req, res, session);
					break;
				case A_PAY_INSTRUCTION :
					procActionCorpPayInst(mc, msgUser, req, res, session);
					break;
				case A_ID_CHANGE_ENTER :
					procActionIDChangeEnter(mc, msgUser, req, res, session);
					break;
				case A_ID_CHANGE_MAINT :
					procActionIDChangeMaint(mc, msgUser, req, res, session);
					break;
				// END Personal & Corporative 

				// BEGIN Entering
				// Request
				case R_ENTER_NEW : 
					procReqEnterNew(msgUser, req, res, session);
					break;
				case R_ENTER_MAINT : 
					procReqEnterMaint(msgUser, req, res, session);
					break;
				case R_ENTER_INQUIRY : 
					procReqEnterInq(msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				case A_ENTER_INQ : 
					procActionEnterInq(mc, msgUser, req, res, session);
					break;
				// END Entering

				// Reports
				case R_ENTER_REPORT : 
					procReqEnterReport(msgUser, req, res, session);
					break;
				case A_ENTER_REPORT : 
					procActionEnterReport(mc, msgUser, req, res, session);
					break;
				case R_REPORT :
					procReqReport(mc, msgUser, req, res, session);
					break;
					
				case R_P_PICTURE :
					procReqPersonalPicture(mc, msgUser, req, res, session);
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
     
/**
 * This method was created in VisualAge.
 */
protected void procActionKnowYourCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgSpecialInst = null;
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
		msgSpecialInst = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	// msgSpecialInst = (ESD000005Message)ses.getAttribute("specInst");
		msgSpecialInst.setH05USR(user.getH01USR());
	 	msgSpecialInst.setH05PGM("ESD0080");
	 	msgSpecialInst.setH05TIM(getTimeStamp());
	 	msgSpecialInst.setH05SCR("01");
	 	msgSpecialInst.setH05OPE("0005");
	 	msgSpecialInst.setE05TYP("K");
	 	msgSpecialInst.setE05ACC(userPO.getCusNum());

	 	// all the fields here
	 	java.util.Enumeration enu = msgSpecialInst.fieldEnumeration();
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

	 	msgSpecialInst.send();	
	 	msgSpecialInst.destroy();
	 	flexLog("ESD000005 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgSpecialInst = new datapro.eibs.beans.ESD000005Message(); 
				flexLog("ESD000005 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgSpecialInst = (ESD000005Message)newmessage;
			// showESD000005(msgSpecialInst);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("specInst", msgSpecialInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_both_know_your_customer.jsp");
					callPage(LangPath + "ESD0080_client_both_know_your_customer.jsp", req, res);	
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
protected void procActionPerShortBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008020Message msgClientPersonal = null;
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
		msgClientPersonal = (ESD008020Message)mc.getMessageRecord("ESD008020");
	 	// msgClientPersonal = (ESD008020Message)ses.getAttribute("client");
		msgClientPersonal.setH20USR(user.getH01USR());
	 	msgClientPersonal.setH20PGM("ESD0080");
	 	msgClientPersonal.setH20TIM(getTimeStamp());
	 	msgClientPersonal.setH20SCR("01");
	 	msgClientPersonal.setH20OPE("0005");
		msgClientPersonal.setE20CUN(req.getParameter("E20CUN"));
		msgClientPersonal.setE20LGT("2"); // Cliente Personal

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
	 	flexLog("ESD008020 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008020")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008020Message(); 
				flexLog("ESD008020 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008020Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE20CUN());
			userPO.setCusName(msgClientPersonal.getE20NA1());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
		    ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_short_personal_confirm.jsp");
					callPage(LangPath + "ESD0080_client_short_personal_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_short_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_short_personal_basic.jsp", req, res);	
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
protected void procReqKnowYourCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgSpecInst = null;
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

	String opCode = null;
	if ( userPO.getPurpose().equals("NEW") )
		opCode = "0001";
	else if ( userPO.getPurpose().equals("INQUIRY") )
		opCode = "0004";
	else
		opCode = "0002";

	// Send Initial data
	try
	{
		msgSpecInst = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgSpecInst.setH05USR(user.getH01USR());
	 	msgSpecInst.setH05PGM("ESD0080");
	 	msgSpecInst.setH05TIM(getTimeStamp());
	 	msgSpecInst.setH05SCR("01");
	 	msgSpecInst.setH05OPE(opCode);
	 	msgSpecInst.setE05ACC(userPO.getCusNum());
	 	msgSpecInst.setE05TYP("K");
		msgSpecInst.send();	
	 	msgSpecInst.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgSpecInst = new datapro.eibs.beans.ESD000005Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgSpecInst = (ESD000005Message)newmessage;
			// showESD000005(msgSpecInst);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("specInst", msgSpecInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_know_your_customer.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_know_your_customer.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_know_your_customer.jsp");
						callPage(LangPath + "ESD0080_client_both_know_your_customer.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_inq_both_enter.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_both_enter.jsp");
						callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
					}
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
protected void procReqPerShortBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008020Message msgClientPersonal = null;
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
		msgClientPersonal = (ESD008020Message)mc.getMessageRecord("ESD008020");
	 	msgClientPersonal.setH20USR(user.getH01USR());
	 	msgClientPersonal.setH20PGM("ESD0080");
	 	msgClientPersonal.setH20TIM(getTimeStamp());
	 	msgClientPersonal.setH20SCR("01");
	 	msgClientPersonal.setH20OPE("0002");
	 	try {
	 		msgClientPersonal.setE20CUN(req.getParameter("CUSNUM"));
	 	}
	 	catch (Exception e) {
	 	}
	 	try {
	 		msgClientPersonal.setE20IDN(req.getParameter("CUSIDN"));
	 		msgClientPersonal.setE20LN3(req.getParameter("CUSIDN"));
	 	}
	 	catch (Exception e) {
	 	}
		msgClientPersonal.send();	
	 	msgClientPersonal.destroy();
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

		if (newmessage.getFormatName().equals("ESD008020")) {
			try {
				msgClientPersonal = new datapro.eibs.beans.ESD008020Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClientPersonal = (ESD008020Message)newmessage;

			userPO.setCusNum(msgClientPersonal.getE20CUN());
			userPO.setCusName(msgClientPersonal.getE20NA1());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClientPersonal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_short_personal_basic.jsp");
					callPage(LangPath + "ESD0080_client_short_personal_basic.jsp", req, res);
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
protected void procReqCorpPayInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008011Message msgPayInst = null;
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
		msgPayInst = (ESD008011Message)mc.getMessageRecord("ESD008011");
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

		if (newmessage.getFormatName().equals("ESD008011")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008011Message(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008011Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayInst);

			if (IsNotError) {  // There are no errors
				try {
					if (userPO.getPurpose().equals("INQUIRY")) {

						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_payment_instructions.jsp");
						//callPage(LangPath + "ESD0080_client_inq_corp_payment_instructions.jsp", req, res);
						callPage(LangPath + "ESD0080_client_inq_corp_basic.jsp", req, res);
					}
					else {
						
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_instructions.jsp");
						//callPage(LangPath + "ESD0080_client_corp_payment_instructions.jsp", req, res);
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

protected void procActionCorpPayInstType(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008011Message msgPayInst = null;
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
		msgPayInst = (ESD008011Message)mc.getMessageRecord("ESD008011");
	 	// msgPayInst = (ESD008011Message)ses.getAttribute("board");
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
	 	flexLog("ESD008011 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008011")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008011Message(); 
				flexLog("ESD008011 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008011Message)newmessage;
			// showESD008011(msgPayInst);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayInst);

			
				try {
					//flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_inst.jsp");
					//callPage(LangPath + "ESD0080_client_corp_payment_inst.jsp", req, res);
					//callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					if (IsNotError) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_enter_inst.jsp");
						callPage(LangPath + "ESD0080_client_corp_enter_inst.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_payment_inst.jsp");
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
	ESD008011Message msgPayInst = null;
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
		msgPayInst = (ESD008011Message)mc.getMessageRecord("ESD008011");
	 	// msgPayInst = (ESD008011Message)ses.getAttribute("board");
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
	 	flexLog("ESD008011 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008011")) {
			try {
				msgPayInst = new datapro.eibs.beans.ESD008011Message(); 
				flexLog("ESD008011 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayInst = (ESD008011Message)newmessage;
			// showESD008011(msgPayInst);

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

/**
 * This method was created in VisualAge.
 */
protected void procReqIDChangeEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos(); 
		userPO.setOption("CLIENT");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0080_client_id_change_enter.jsp");
		callPage(LangPath + "ESD0080_client_id_change_enter.jsp", req, res);
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procActionIDChangeEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008015Message msgClient = null;
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
		msgClient = (ESD008015Message) mc.getMessageRecord("ESD008015");
	 	msgClient.setH15USR(user.getH01USR());
	 	msgClient.setH15PGM("ESD0080");
	 	msgClient.setH15TIM(getTimeStamp());
	 	msgClient.setH15SCR("01");
	 	msgClient.setH15OPE("0002");
	 	try {
		 	if (req.getParameter("E01CUN") != null)
			 	msgClient.setE15CUN(req.getParameter("E01CUN"));
		}
		catch (Exception e)
		{
			msgClient.setE15CUN("0");
		    flexLog("Input data error " + e);
		}
		try {
		 	if (req.getParameter("E01IDN") != null && 
		 		req.getParameter("E01IDN").indexOf("-") != -1	){
//		 	 For PANAMA LONG IDENTIFICATION
		 		msgClient.setE15LN3(req.getParameter("E01IDN"));			 		
		 	}else {
		 		msgClient.setE15IDN(req.getParameter("E01IDN"));
		 	}
		}
		catch (Exception e)
		{
			msgClient.setE15IDN("");
		    flexLog("Input data error " + e);
		}
	 	//msgClient.setE01IDN("");
		msgClient.send();	
	 	msgClient.destroy();
	 	flexLog("ESD008015 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008015")) {
			try {
				msgClient = new datapro.eibs.beans.ESD008015Message();
				flexLog("ESD008015 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClient = (ESD008015Message) newmessage;

			userPO.setCusNum(msgClient.getE15CUN());
			userPO.setCusType(msgClient.getE15LGT());
			userPO.setHeader1(msgClient.getE15CUN());
			userPO.setHeader2(msgClient.getE15IDN());
			userPO.setHeader3(msgClient.getE15NA1());
			
			if (!msgClient.getE15LN3().equals("") && msgClient.getE15LN3().indexOf("-") != -1 ) {
				userPO.setHeader2(msgClient.getE15LN3());
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgClient", msgClient);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_id_change.jsp");
					callPage(LangPath + "ESD0080_client_id_change.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: /pages/" + LangPath + "ESD0080_client_id_change_enter.jsp");
					callPage(LangPath + "ESD0080_client_id_change_enter.jsp", req, res);	
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
protected void procActionIDChangeMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD008015Message msgClient = null;
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
		msgClient = (ESD008015Message) mc.getMessageRecord("ESD008015");
		msgClient.setH15USR(user.getH01USR());
	 	msgClient.setH15PGM("ESD0080");
	 	msgClient.setH15TIM(getTimeStamp());
	 	msgClient.setH15SCR("01");
	 	msgClient.setH15OPE("0005");
	 	try {
		 	if (req.getParameter("APPROVAL").equals("Y")) msgClient.setH15OPE("0006");
		}
		catch (Exception e) {
		}
		msgClient.setE15IDN(req.getParameter("IDN"));
		msgClient.setE15LN3(req.getParameter("IDN2"));

		// all the fields here
	 	java.util.Enumeration enu = msgClient.fieldEnumeration();
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
		
	 	msgClient.send();	
	 	msgClient.destroy();
	 	flexLog("ESD008015 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD008015")) {
			try {
				msgClient = new datapro.eibs.beans.ESD008015Message();
				flexLog("ESD008015 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgClient = (ESD008015Message)newmessage;

			userPO.setCusNum(msgClient.getE15CUN());
			userPO.setHeader1(msgClient.getE15CUN());
			userPO.setHeader2(msgClient.getE15IDN());			
			userPO.setHeader3(msgClient.getE15NA1());
			if (!msgClient.getE15LN3().equals("") &&
					msgClient.getE15LN3().indexOf("-") != -1 ) {
					
					userPO.setHeader2(msgClient.getE15LN3());
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
		    ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: /servlet/datapro.eibs.client.JSESD0080?SCREEN=54");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=54");
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_id_change.jsp");
					callPage(LangPath + "ESD0080_client_id_change.jsp", req, res);	
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

protected void procReqPersonalPicture(MessageContext mc, ESS0030DSMessage user, 
	HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
	
	InputStream in = null;
	ServletOutputStream out = (ServletOutputStream) res
		.getOutputStream();
	try {
		ESD008001Message client = (ESD008001Message) ses.getAttribute("client");
		
		res.reset();
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", -1);
		//res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "max-age=0");
		res.addHeader("Cache-Control", "s-maxage=0");
		res.setContentType("image/jpeg");
		//res.setContentLength(length);
		
		in = new URL(JSEIBSProp.getImageImportPath()+
			Util.replace(client.getE01JPG(), "\\", "/")).openStream();
		
		byte[] buf = new byte[4 * 1024]; // 4K buffer
		int bytesRead;
		long size = 0;
		while ((bytesRead = in.read(buf)) != -1) {
			out.write(buf, 0, bytesRead);
			size = size + bytesRead;
		}
		out.flush();
			
	} catch (Exception e) {
		e.printStackTrace();
		throw new ServletException(e.getMessage());
	} finally {
		if (in != null) {
			in.close();
		}
		if (out != null) {
			out.close();
		}
	}
}
}