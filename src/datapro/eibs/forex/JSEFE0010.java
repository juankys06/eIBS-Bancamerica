package datapro.eibs.forex;

/**
 * Creation date: (04/30/2002 6:02:17 PM)
 * @author: David Mavilla
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEFE0010 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_BASIC			= 100;
	protected static final int A_ENTER_BASIC			= 200;
	protected static final int R_ENTER_MAINT			= 300;
	
	protected static final int A_ENTER_BUY_CASH		= 400;
	protected static final int A_ENTER_BUY_DOC		= 600;
	protected static final int A_ENTER_BUY_TRASP		= 800;
	protected static final int A_ENTER_SAL_CASH		= 1000;
	protected static final int A_ENTER_SAL_DOC		= 1200;
	protected static final int A_ENTER_SAL_TRASP		= 1400;
	
	protected static final int R_ENTER_APP_INQ		= 1600;
	
	protected static final int A_BUY_CASH				= 2;
	protected static final int A_BUY_DOC				= 4;
	protected static final int A_BUY_TRASP			= 6;	  
	protected static final int A_SAL_CASH				= 8;
	protected static final int A_SAL_DOC				= 10;
	protected static final int A_SAL_TRASP			= 12;
	
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEFE0010() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEFE0010(int logType) {
	super(logType);
	
}
/**
 * This method was created in WSAD.
 * 
 */
protected void procActionEnterBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	

	     int inptOPT = Integer.parseInt(req.getParameter("INPTOPT"));


			switch (inptOPT) {
				case 0 : //Cash Transactions Buy
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=400");
					
					break;				
				case 1 : //Document Transactions Buy
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=600");
								
					break;				
				case 2 : //Traspassing Transactions Buy
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=800");
									
					break;				
				case 3 : //Cash Transactions Sale
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=1000");
					
					break;				
				case 4 : //Document Transactions Sale
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=1200");				
			    	break;				
				case 5 : //Traspassing Transactions Sale
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=1400");			
					break;						
				default :
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=1");
  		}
  		
}
	protected void procReqEnterBasic(
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
			userPO.setOption("FEX");
			userPO.setPurpose("DEALER");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0010_fe_enter_opt.jsp");
			callPage(LangPath + "EFE0010_fe_enter_opt.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqEnterMaint(
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
			userPO.setOption("FEX");
			userPO.setPurpose("DEALER");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);


		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		try {
			flexLog("About to call Page: " + LangPath + "EFE0010_fe_enter_maint.jsp");
			callPage(LangPath + "EFE0010_fe_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}
protected void procActionEnterBuyCash(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("1");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_cash_pur.jsp");
						callPage(LangPath + "EFE0010_fe_basic_cash_pur.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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

protected void procActionEnterBuyDoc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("2");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_doc_pur.jsp");
						callPage(LangPath + "EFE0010_fe_basic_doc_pur.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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


protected void procActionEnterBuyTrasp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("3");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_trasp_pur.jsp");
						callPage(LangPath + "EFE0010_fe_basic_trasp_pur.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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

protected void procActionEnterSalCash(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("4");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_cash_sal.jsp");
						callPage(LangPath + "EFE0010_fe_basic_cash_sal.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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

protected void procActionEnterSalDoc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("5");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_doc_sal.jsp");
						callPage(LangPath + "EFE0010_fe_basic_doc_sal.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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

protected void procActionEnterSalTrasp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0001");

    	msgFex.setE01EFETRN("6");
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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




		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);




			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_trasp_sal.jsp");
						callPage(LangPath + "EFE0010_fe_basic_trasp_sal.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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


protected void procActionBuyCash(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_cash_pur.jsp");
							callPage(LangPath + "EFE0010_fe_basic_cash_pur.jsp", req, res);	
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

protected void procActionBuyDoc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_doc_pur.jsp");
							callPage(LangPath + "EFE0010_fe_basic_doc_pur.jsp", req, res);	
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


protected void procActionBuyTrasp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_trasp_pur.jsp");
							callPage(LangPath + "EFE0010_fe_basic_trasp_pur.jsp", req, res);	
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

protected void procActionSalCash(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_cash_sal.jsp");
							callPage(LangPath + "EFE0010_fe_basic_cash_sal.jsp", req, res);	
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

protected void procActionSalDoc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_doc_sal.jsp");
							callPage(LangPath + "EFE0010_fe_basic_doc_sal.jsp", req, res);	
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

protected void procActionSalTrasp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	 	msgFex = (EFE001001Message)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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


	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE001001 Message Sent");
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
					msgFex = new EFE001001Message();
					flexLog("EFE001001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE001001Message)newmessage;
				
				
				userPO.setHeader1(getFullTime());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_confirm.jsp");
							callPage(LangPath + "EFE0010_fe_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0010_fe_basic_trasp_sal.jsp");
							callPage(LangPath + "EFE0010_fe_basic_trasp_sal.jsp", req, res);	
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

protected void procReqApprovalInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	EFE001001Message msgFex = null;
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
	
	
	// Send Initial data
	try
	{
		msgFex = (EFE001001Message)mc.getMessageRecord("EFE001001");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0010");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0004");


    	msgFex.setE01EFEACC(req.getParameter("E01EFEACC"));
		
		msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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








		if (newmessage.getFormatName().equals("EFE001001")) {
			try {
				msgFex = new EFE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}








			msgFex = (EFE001001Message)newmessage;
			// showESD008004(msgFex);








			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0010_fe_app_inq.jsp");
						callPage(LangPath + "EFE0010_fe_app_inq.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=100");			}
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

		int screen = R_ENTER_BASIC;
		
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
				case R_ENTER_BASIC :
					procReqEnterBasic(msgUser, req, res, session);
					break;
				case R_ENTER_MAINT :
					procReqEnterMaint(msgUser, req, res, session);
					break;
				case R_ENTER_APP_INQ :
					procReqApprovalInq(mc, msgUser, req, res, session);
					break;	
					
				case A_ENTER_BASIC :
					procActionEnterBasic(mc, msgUser, req, res, session);
					break;
				case A_ENTER_BUY_CASH :
					procActionEnterBuyCash(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_BUY_DOC :
					procActionEnterBuyDoc(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_BUY_TRASP :
					procActionEnterBuyTrasp(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_SAL_CASH :
					procActionEnterSalCash(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_SAL_DOC :
					procActionEnterSalDoc(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_SAL_TRASP :
					procActionEnterSalTrasp(mc, msgUser, req, res, session);
					break;	
					
				case A_BUY_CASH :
					procActionBuyCash(mc, msgUser, req, res, session);
					break;
				case A_BUY_DOC :
					procActionBuyDoc(mc, msgUser, req, res, session);
					break;	
				case A_BUY_TRASP :
					procActionBuyTrasp(mc, msgUser, req, res, session);
					break;
				case A_SAL_CASH :
					procActionSalCash(mc, msgUser, req, res, session);
					break;
				case A_SAL_DOC :
					procActionSalDoc(mc, msgUser, req, res, session);
					break;
				case A_SAL_TRASP :
					procActionSalTrasp(mc, msgUser, req, res, session);
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