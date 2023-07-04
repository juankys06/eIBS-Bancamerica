package datapro.eibs.invest;

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

public class JSEIE0210A extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int A_MAINTENANCE		= 2;
	protected static final int A_CALCULATE		    = 16;
	
	
	protected static final int A_ENTER_MAINT	 	= 400;
	protected static final int A_APPROVAL	 	= 600;
	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEIE0210A() {
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
protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE021001Message msgInst = null;
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
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE021001Message)mc.getMessageRecord("EIE021001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE021001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0002");


		try { // Instrument Code
		 	  msgInst.setE01CPNIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Order Number
		 	  msgInst.setE01CPNNUM(req.getParameter("ORDERNUM"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try { // Payment Type
		 	  msgInst.setE01CPNTYP(req.getParameter("PAYTYPE"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Payment Date
		 	  msgInst.setE01CPNPM1(req.getParameter("PDATE1"));
		 	  msgInst.setE01CPNPM2(req.getParameter("PDATE2"));
		 	  msgInst.setE01CPNPM3(req.getParameter("PDATE3"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try { // Custodian Cod
		 	  msgInst.setD01CSTCOD(req.getParameter("CUSTODIANCOD"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Customer Cod
		 	  msgInst.setD01CUSCUN(req.getParameter("CUSTOMERCOD"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Delivered Amount
		 	  msgInst.setE01CPNDAM(req.getParameter("DELAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Commission Amount
		 	  msgInst.setE01CPNCOM(req.getParameter("COMAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Projected Amount
		 	  msgInst.setE01CPNAMT(req.getParameter("PROJAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  Amount to be Paid 
		 	  msgInst.setE01CPNPAM(req.getParameter("PAIDAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  RCD
		 	  msgInst.setE01CPNRCD(req.getParameter("RCD"));
		}
		catch (Exception e)	{
	 	  
		}
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE012001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE021001")) {
			try {
				msgInst = new datapro.eibs.beans.EIE021001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE021001Message)newmessage;
			
			userPO.setPurpose("M");		
			String INSTCODE = msgInst.getE01CPNIIC();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invTrade", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
				
				try {
						flexLog("About to call Page: " + LangPath + "EIE0210A_inv_coupon_approval.jsp");
						callPage(LangPath + "EIE0210A_inv_coupon_approval.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}							
					
			}
			else {  // There are errors
				
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0315?SCREEN=1" + "&INSTCODE=" + INSTCODE);
				
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
	EIE021001Message msgInst = null;
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
	 	msgInst = (EIE021001Message)ses.getAttribute("invTrade");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");
		msgInst.setH01FLGWK1("");
		
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
					msgInst = new datapro.eibs.beans.EIE021001Message();
					flexLog("EIE000001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EIE021001Message)newmessage;
				// showESD008004(msgInst);
				
				String INSTCODE="";
				INSTCODE = msgInst.getE01CPNIIC();
							
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invTrade", msgInst);
				ses.setAttribute("userPO", userPO);
				
										

				if (IsNotError) {  // There are no errors
					
					   res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0315?SCREEN=1" + "&INSTCODE=" + INSTCODE);
					
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0210_inv_coupon_approval.jsp");
							callPage(LangPath + "EIE0210_inv_coupon_approval.jsp", req, res);	
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

protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE021001Message msgInst = null;
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
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE021001Message)mc.getMessageRecord("EIE021001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE021001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0006");


		try { // Instrument Code
		 	  msgInst.setE01CPNIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Order Number
		 	  msgInst.setE01CPNNUM(req.getParameter("ORDERNUM"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Customer
		 	  msgInst.setD01CUSNME(req.getParameter("CUSTOMER"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Custodian
		 	  msgInst.setD01CSTNME(req.getParameter("CUSTODIAN"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Payment Type
		 	  msgInst.setE01CPNTYP(req.getParameter("PAYTYPE"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Payment Date
		 	  msgInst.setE01CPNPM1(req.getParameter("PDATE1"));
		 	  msgInst.setE01CPNPM2(req.getParameter("PDATE2"));
		 	  msgInst.setE01CPNPM3(req.getParameter("PDATE3"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try { // Custodian Cod
		 	  msgInst.setD01CSTCOD(req.getParameter("CUSTODIANCOD"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Customer Cod
		 	  msgInst.setD01CUSCUN(req.getParameter("CUSTOMERCOD"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Delivered Amount
		 	  msgInst.setE01CPNDAM(req.getParameter("DELAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Commission Amount
		 	  msgInst.setE01CPNCOM(req.getParameter("COMAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { // Projected Amount
		 	  msgInst.setE01CPNAMT(req.getParameter("PROJAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  Amount to be Paid 
		 	  msgInst.setE01CPNPAM(req.getParameter("PAIDAMOUNT"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  RCD
		 	  msgInst.setE01CPNRCD(req.getParameter("RCD"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  Action
		 	  msgInst.setE01CPNACT(req.getParameter("ACTION"));
		}
		catch (Exception e)	{
	 	  
		}
		try { //  Reason
		 	  msgInst.setE01CPNRMK(req.getParameter("REASON"));
		}
		catch (Exception e)	{
	 	  
		}
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE012001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE021001")) {
			try {
				msgInst = new datapro.eibs.beans.EIE021001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE021001Message)newmessage;
			
			userPO.setPurpose("M");		
			String INSTCODE = msgInst.getE01CPNIIC();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invTrade", msgInst);
			ses.setAttribute("userPO", userPO);
 
						
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0315A?SCREEN=1");
						
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
protected void procActionCalculate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE021001Message msgInst = null;
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
	 	msgInst = (EIE021001Message)ses.getAttribute("invTrade");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");
	 	msgInst.setH01FLGWK1("C");

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
					msgInst = new datapro.eibs.beans.EIE021001Message();
					flexLog("EIE000001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EIE021001Message)newmessage;
				// showESD008004(msgInst);
				
				String INSTCODE="";
				INSTCODE = msgInst.getE01CPNIIC();
							
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invTrade", msgInst);
				ses.setAttribute("userPO", userPO);
				
										

									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0210_inv_coupon_approval.jsp");
							callPage(LangPath + "EIE0210_inv_coupon_approval.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
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

				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
					
				case A_APPROVAL :
					procActionApproval(mc, msgUser, req, res, session);
					break;	
			
				case A_CALCULATE :
					procActionCalculate(mc, msgUser, req, res, session);
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
