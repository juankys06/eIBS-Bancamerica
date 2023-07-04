package datapro.eibs.forex;

/**
 * Creation date: (04/30/2002 6:02:17 PM)
 * @author: David Mavilla
**/

/**
 * This class drives all the process for Foreign Exchange
 * linking the following classes :
 * 
 * JSEDL0120  ----- Foreign Exchange Deals
 * JSEFE0120P ----- Foreign Exchange Maintenance
 * JSEFE0325  ----- Customer Limits Maintenance
 * JSELN0000T ----- Credit Lines Maintenance
 * JSELN0115  ----- Credit Lines Incidence
 * JSETR0120  ----- Forward Rate Agreements
 * JSEWD0012F ----- Currency Rates Inquiry
 * JSEWD0015T ----- Credit Lines Inquiry Help
 * JSEWD0321I ----- Today Deals Inquiry
 * JSEWD0322T ----- Customer Limits Inquiry
 * JSEWD0322S ----- Customer Limits Maintenance Selection
 * JSEWD0323  ----- Currency Position
 * JSEWD0324  ----- Credit Lines Incidence Selection
 * JSEWD0321  ----- Today Deals Help 
 *  
 * This class utilized the following classes itself :
 * 
 *  JSPs :
 * 
 *  EFE0000_fe_enter_opt.jsp     -----  Dealer Slip Management
 *  EFE0000_fe_enter_delete.jsp	 -----  Entering Deletion  
 *  EFE0000_fe_enter_inquiry.jsp -----  Entering Inquiry
 *	EFE0000_fe_enter_prof.jsp	 -----  Entering Profitability
 *	EFE0000_fe_enter_set.jsp	 -----  Entering Settlement Limits
 *  EFE0000_fe_enter_lin.jsp	 -----  Entering Credit Lines
 *	EFE0120_del_confirm.jsp		 -----  Deletion Confirmation
 * 
 *  Beans :
 * 
 *  ELEERRMessage    			 ----- 	Error Message
 *  EFE0120DSMessage			 ----- 	Foreign Exchange Data Bean
 *  EWD0001RMessage     		 ----- 	Customer Search Send
 *  EWD0001SMessage				 -----  Customer Search Send
 *  JBList						 -----  Data Transfer Auxiliar (Multiple)
 *  UserPos						 -----  Data Transfer Auxiliar (Single)
 *  
 **/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEFE0000B extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_BASIC			= 100;
	protected static final int R_ENTER_INQUIRY		= 300;
	protected static final int R_ENTER_PROF			= 500;
	protected static final int R_ENTER_CALC			= 700;
	protected static final int R_ENTER_LIN			= 900;
	protected static final int R_ENTER_SET			= 1100;
	protected static final int R_ENTER_DELETE			= 1300;
	
	protected static final int A_ENTER_BASIC			= 200;
	protected static final int A_ENTER_DELETE			= 400;
	protected static final int A_ENTER_DELETE_DEALS	= 1800;
	protected static final int A_ENTER_DELETE_FRA		= 2000;

	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEFE0000B() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEFE0000B(int logType) {
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
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	int inptOPT = 0;
	
	String DEATYP = req.getParameter("E01FESTYP");
	String DEATYPO = req.getParameter("E01FESTYPO");
	
	if (!req.getParameter("E01FESREF").trim().equals("")) {
		if(req.getParameter("INPTOPT").trim().equals("42")){
		   inptOPT = 42;	
		} 
		else if(req.getParameter("E01FESTYPO").trim().equals("LNS") ||
		   req.getParameter("E01FESTYPO").trim().equals("CDS") ||
		   req.getParameter("E01FESTYPO").trim().equals("PLP") ||
		   req.getParameter("E01FESTYPO").trim().equals("ACC") ||
		   req.getParameter("E01FESTYPO").trim().equals("TDS") ||
		   req.getParameter("E01FESTYPO").trim().equals("IRS")
		   ){
		   inptOPT = 103;	
		}
		else if(req.getParameter("E01FESTYPO").trim().equals("FRA")){
		   inptOPT = 12;	
		}   		   
		else if(req.getParameter("E01FESTYPO").trim().equals("OPTI") ||
		   req.getParameter("E01FESTYPO").trim().equals("SPOT") ||
		   req.getParameter("E01FESTYPO").trim().equals("FWRD") ||
		   req.getParameter("E01FESTYPO").trim().equals("SWAP") ||
		   req.getParameter("E01FESTYPO").trim().equals("FSP")
		   ){
		   inptOPT = 100;	
		}
	}	
	else {
		inptOPT = Integer.parseInt(req.getParameter("INPTOPT"));
	}
	
	String CusNumb = "";
	String CusAux = "";
	
	try{
	   CusAux = req.getParameter("CUNCOD");
	   }
	catch (Exception ex) {
		CusAux =""; 
  	}

	if(CusAux.trim().equals("")){	
	  CusNumb = req.getParameter("CUSTOMER"); 
	}
	else {
	  CusNumb = CusAux;	
	}
	
	
	String RefNumb = req.getParameter("E01FESREF");
	String DeaID = req.getParameter("E01FESDID");
	String  bank  = user.getE01UBK();
	
			switch (inptOPT) {
				case 1 : //Fast Spot
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=FSP " + "&E01FESREF=" + RefNumb);
					break;				
				case 2 : //Spot
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=200&E01FESCUN=" + CusNumb + "&E01FESTYP=SPOT" + "&E01FESREF=" + RefNumb);
					break;
				case 3  ://Forward
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1000&E01FESCUN=" + CusNumb + "&E01FESTYP=FWRD" + "&E01FESREF=" + RefNumb);
					break;					
				case 4  ://Swap
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1200&E01FESCUN=" + CusNumb + "&E01FESTYP=SWAP" + "&E01FESREF=" + RefNumb);
					break;										
				case 5  ://Option	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=800&E01FESCUN=" + CusNumb + "&E01FESTYP=OPTI" + "&E01FESREF=" + RefNumb);
					break;
				case 6  ://Deposits	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=200&E01DLSCUN=" + CusNumb + "&E01FESTYP=TDS" + "&E01DLSREF=" + RefNumb);
					break;	
				case 7  ://Bankers Acceptance	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=800&E01DLSCUN=" + CusNumb + "&E01FESTYP=ACC" + "&E01DLSREF=" + RefNumb);
					break;
				case 8  ://Commercial Papers	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=600&E01DLSCUN=" + CusNumb + "&E01FESTYP=PLP" + "&E01DLSREF=" + RefNumb);
					break;
				case 9  ://CDS	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=1000&E01DLSCUN=" + CusNumb + "&E01FESTYP=CDS" + "&E01DLSREF=" + RefNumb);
					break;
				case 10  ://Loans	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=400&E01DLSCUN=" + CusNumb + "&E01FESTYP=LNS" + "&E01DLSREF=" + RefNumb);
					break;																																												
				case 11  :// Not Delivered Forward
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1400&E01FESCUN=" + CusNumb + "&E01FESTYP=NFR " + "&E01FESREF=" + RefNumb);
					break;											
				case 12  ://FRA	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSETR0120?SCREEN=200&E01WFRCUN=" + CusNumb + "&E01WFRPRO=FRA" + "&E01WFRREF=" + RefNumb);
					break;												
				case 42  ://Line of Credit Maintenance	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSELN0000T?SCREEN=200&CUSNUM=" + CusNumb +  "&LNENUM=" + RefNumb);
					break;																												
				case 61  ://Customer Details
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + CusNumb);
					break;
				case 62  ://Customer Limits
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322?SCREEN=1&E01FESCUN=" + CusNumb);
					break;
				case 65  ://Currency Rates
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0012F?BankNumber=" + bank);
					break;																				
				case 66  ://Currency Positions
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0323?SCREEN=1");
					break;
				case 68  ://Incomplete Deals
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0321I");
					break;																																								
				case 69  ://Today Deals
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0325T");
					break;																																		
				case 100  ://Maintenance Foreign Exchange	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=40&E01FESREF=" + RefNumb + "&E01FESDID=" + DeaID);
					break;
				case 102  ://Customer Limits Maintenance
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322S?SCREEN=1&E01FESCUN=" + CusNumb);
					break;
				case 103  ://Maintenance Deals	
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=14&E01DLSREF=" + RefNumb + "&E01DLSDID=" + DeaID);
					break;	
				case 104  ://Incomplete Deals Maintenance
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0325S?SCREEN=1");
					break;																																																										
				default :
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=SPOT");
  		}
}


protected void procActionEnterDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0120DSMessage msgFex = null;
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
		msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0120P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0004");

		try {
		 	if (req.getParameter("E01FESREF") != null)
		 	  msgFex.setE01FESREF(req.getParameter("E01FESREF"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FESREF("0");
		}
		try {
		 	if (req.getParameter("E01FESDID") != null)
		 	  msgFex.setE01FESDID(req.getParameter("E01FESDID"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FESDID("");
		}
		

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

		if (newmessage.getFormatName().equals("EFE0120DS")) {
			try {
				msgFex = new EFE0120DSMessage();
				flexLog("EFE0120DS Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0120DSMessage)newmessage;

			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EFE0120_del_confirm.jsp");
					callPage(LangPath + "EFE0120_del_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1300");
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

protected void procActionEnterDeleteDeals(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL0120DSMessage msgFex = null;
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
		msgFex = (EDL0120DSMessage)mc.getMessageRecord("EDL0120DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EDL0120");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0004");

		try {
		 	if (req.getParameter("E01FESREF") != null)
		 	  msgFex.setE01DLSREF(req.getParameter("E01FESREF"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01DLSREF("0");
		}
		try {
		 	if (req.getParameter("E01FESDID") != null)
		 	  msgFex.setE01DLSDID(req.getParameter("E01FESDID"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01DLSDID("");
		}
		

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EDL0120 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL0120DS")) {
			try {
				msgFex = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EDL0120DSMessage)newmessage;

			userPO.setIdentifier(msgFex.getE01DLSREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EFE0120_del_confirm.jsp");
					callPage(LangPath + "EFE0120_del_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1300");
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

protected void procActionEnterDeleteFRA(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ETR0120DSMessage msgFex = null;
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
		msgFex = (ETR0120DSMessage)mc.getMessageRecord("ETR0120DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("ETR0120");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0004");	
	 	
		try {
		 	if (req.getParameter("E01FESREF") != null)
		 	  msgFex.setE01WFRACC(req.getParameter("E01FESREF"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRACC("0");
		}

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("ETR0120DS Message Sent");
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

		if (newmessage.getFormatName().equals("ETR0120DS")) {
			try {
				msgFex = new ETR0120DSMessage();
				flexLog("ETR0120DS Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (ETR0120DSMessage)newmessage;

			userPO.setIdentifier(msgFex.getE01WFRACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EFE0120_del_confirm.jsp");
					callPage(LangPath + "EFE0120_del_confirm.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1300");
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


protected void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EFE0000B_enter_search.jsp");
			callPage(LangPath + "EFE0000B_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;

	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}


	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {

			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";

				while (true) {

					msgCustS = (EWD0001SMessage)newmessage;

					marker = msgCustS.getSWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {

						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + "></TD>"); 
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");	
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");											
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_opt.jsp");
					callPage(LangPath + "EFE0000_fe_enter_opt.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	
}

protected void procReqEnterDelete(MessageContext mc,ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;

	try {

		msgError = new ELEERRMessage();
		userPO = new UserPos();
		userPO.setOption("FEX");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_delete.jsp");
		callPage(LangPath + "EFE0000_fe_enter_delete.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}



protected void procReqEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);


			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}




	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";


				while (true) {


					msgCustS = (EWD0001SMessage)newmessage;


					marker = msgCustS.getSWDOPE();


					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {


						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + "></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);


				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_inquiry.jsp");
					callPage(LangPath + "EFE0000_fe_enter_inquiry.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

}


protected void procReqEnterProfitability(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);


			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}




	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";


				while (true) {


					msgCustS = (EWD0001SMessage)newmessage;


					marker = msgCustS.getSWDOPE();


					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {


						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + "></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);


				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_prof.jsp");
					callPage(LangPath + "EFE0000_fe_enter_prof.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

}

protected void procReqEnterSettlement(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);


			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}




	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";


				while (true) {


					msgCustS = (EWD0001SMessage)newmessage;


					marker = msgCustS.getSWDOPE();


					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {


						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + " onClick=\"\"></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);


				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_set.jsp");
					callPage(LangPath + "EFE0000_fe_enter_set.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

}


protected void procReqEnterLinCred(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);


			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}




	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";


				while (true) {


					msgCustS = (EWD0001SMessage)newmessage;


					marker = msgCustS.getSWDOPE();


					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {


						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + "></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);


				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_lin.jsp");
					callPage(LangPath + "EFE0000_fe_enter_lin.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

}



protected void procReqEnterCalculator(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	  	try {
			msgCust = (EWD0001RMessage)mc.getMessageRecord("EWD0001R");
			msgCust.setRWDUSR(user.getH01USR());
			msgCust.setRWDSHR("A");
			msgCust.setRWDTYP("T");
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
		 	msgCust.destroy();
		 	flexLog("EWD0001R Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);


			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}




	  	}
	  	else if (newmessage.getFormatName().equals("EWD0001S")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String chk = "";


				while (true) {


					msgCustS = (EWD0001SMessage)newmessage;


					marker = msgCustS.getSWDOPE();


					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {


						if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustS.getSWDREC()));
						chk = "checked";
										}
						else {
							chk = "";
						}
						myRow = new StringBuffer();
						myRow.append("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgCustS.getSWDCUN() + "\" " + chk + "></TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDNA1()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDTID()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
					
				userPO.setHeader1(user.getH01USR());
				userPO.setHeader2(Util.formatDate(user.getE01RDM(),user.getE01RDD(),user.getE01RDY()));
				
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);


				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_calc.jsp");
					callPage(LangPath + "EFE0000_fe_enter_calc.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
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
					procReqEnterSearch(mc, msgUser, req, res, session);
					break;
				case R_ENTER_INQUIRY :
					procReqEnterInquiry(mc, msgUser, req, res, session);
					break;
				case R_ENTER_PROF :
					procReqEnterProfitability(mc, msgUser, req, res, session);
					break;
				case R_ENTER_CALC :
					procReqEnterCalculator(mc, msgUser, req, res, session);
					break;
				case R_ENTER_LIN :
					procReqEnterLinCred(mc, msgUser, req, res, session);
					break;
				case R_ENTER_SET :
					procReqEnterSettlement(mc, msgUser, req, res, session);
					break;
				case R_ENTER_DELETE :
					procReqEnterDelete(mc, msgUser,req, res, session);
					break;
					
					
					
				case A_ENTER_BASIC :
					procActionEnterBasic(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE :
					procActionEnterDelete(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE_DEALS :
					procActionEnterDeleteDeals(mc, msgUser, req, res, session);
					break;					
				case A_ENTER_DELETE_FRA :
					procActionEnterDeleteFRA(mc, msgUser, req, res, session);
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