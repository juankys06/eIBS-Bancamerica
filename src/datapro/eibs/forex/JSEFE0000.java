package datapro.eibs.forex;

/**
 * Creation date: (04/30/2002 6:02:17 PM)
 * @author: David Mavilla / 02/21/08 C. Castillo Adjust to R48 
**/

/**
 * This class drives all the process for Treasury
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDL0120DSMessage;
import datapro.eibs.beans.EFE0120DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETR0120DSMessage;
import datapro.eibs.beans.EWD0001RMessage;
import datapro.eibs.beans.EWD0001SMessage;
import datapro.eibs.beans.EWD0301DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.TrOption;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEFE0000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_BASIC		= 100;
	protected static final int R_ENTER_INQUIRY		= 300;
	protected static final int R_ENTER_PROF			= 500;
	protected static final int R_ENTER_CALC			= 700;
	protected static final int R_ENTER_LIN			= 900;
	protected static final int R_ENTER_SET			= 1100;
	protected static final int R_ENTER_DELETE		= 1300;
	protected static final int R_ENTER_CUST_DEALS	= 1500;
	protected static final int A_ENTER_BASIC		= 200;
	protected static final int A_ENTER_DELETE		= 400;
	protected static final int A_ENTER_DELETE_DEALS	= 1800;
	protected static final int A_ENTER_DELETE_FRA	= 2000;
	protected static final int A_ENTER_CUST_DEALS	= 2200;
	protected static final int R_PRODUCTS_BASIC		= 3000;

	protected String LangPath = "S";

/**
 * Constructor without params
 */
public JSEFE0000() {
	super();
	}

/**
 * Constructor with 1 param.
 * @param logType int
 */
public JSEFE0000(int logType) {
	super(logType);
	
}

/**
 * Menu
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
					procReqEnterBasic(mc, msgUser, req, res, session);
					break;
				case R_PRODUCTS_BASIC :
					procReqProductsBasic(mc, msgUser, req, res, session);
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
				case R_ENTER_CUST_DEALS :
					procReqEnterCustDeals(msgUser,req, res, session);
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
				case A_ENTER_CUST_DEALS :
					procActionCustDeals(mc, msgUser, req, res, session);
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
/**
 * This method was created in WSAD.
 * 
 */
protected void procActionEnterBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	UserPos	userPO = null;
	TrOption trOption = null;
	boolean IsNotError = false;

	userPO = new UserPos();
	trOption= new TrOption();
	
	int inptOPT = 0;
	String DEATYPO = req.getParameter("E01FESTYPO");
	String ACCOUNTINQ ="";
	String INPTTYP =req.getParameter("INPTTYP");
	String INPTOPTA ="";
	
	try{
		INPTOPTA = req.getParameter("INPTOPT"); 
	}
	catch (Exception ex) {
		INPTOPTA ="0"; 
  	}
	try{
		ACCOUNTINQ = req.getParameter("ACCOUNT"); 
	}
	catch (Exception ex) {
		ACCOUNTINQ =""; 
  	}
	 	
	String RefNumb = req.getParameter("E01FESREF");
	String CusNumb = "";
	String CusAux = "";
	String IDCusAux = "";
	String CusName = "";
	
	try{
		IDCusAux = req.getParameter("E01FESLN3");
	}
		catch (Exception ex) {
		IDCusAux =""; 
	}
	
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
	
	int inptOPTAux =0;
	if(!req.getParameter("E01FESREF").equals("")){
		if (DEATYPO != null) inptOPTAux =Integer.parseInt(DEATYPO);
		INPTTYP=req.getParameter("REFTYP");
	} else {
		if (INPTOPTA != null) inptOPTAux =Integer.parseInt(INPTOPTA);
	}
		
	switch (inptOPTAux) {
	case 31 : //Spot
		inptOPT = 0;
		break;
	case 32 : //Forward
		inptOPT = 3;	
		break;
	case 33 : //SWAP
		inptOPT = 4;
		break;
	case 34 : //Option
		inptOPT = 5;
		break;
	case 35 : //FRA
		inptOPT = 12;
		break;
	case 12 : //CDS
		inptOPT = 9;
		break;
	case 14 : //ACC
		inptOPT = 7;
		break;
	case 13 : //Commercial Papers
		inptOPT = 8;
		break;
	case 11 : //Time Deposits
		inptOPT = 6;
		break;
	case 15 : //Federal Funds
		inptOPT = 70;
		break;
	case 95 : //Financial Blocks
		inptOPT = 130;
		break;
	case 42 : //Lines of Credit
		inptOPT = 42;
		break;	  	
	default :
		inptOPT =Integer.parseInt(INPTOPTA);
	}
	
	String DeaID = req.getParameter("E01FESDID");
	String  bank  = user.getE01UBK();
	
	String AccNum = "";
	try{
	   AccNum = req.getParameter("ACCNUM");
	   }
	catch (Exception ex) {
		AccNum =""; 
  	}	
	
	String source = "";
	try {
		source = req.getParameter("SOURCE");
	} catch (Exception e){
		source = "";
	}
	
	// Description for title
	String dsc = "";
	try {
		dsc = req.getParameter("INPTDSC");	
	} catch (Exception e) {
		dsc = "";
	}
	if (dsc == null) dsc = "";
	trOption.setHeader2(dsc);
	
	switch (inptOPT) {
	case 0 : //Spot
		trOption.setHeader1("2");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=200&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01FESREF=" + RefNumb + "&E01FESDID=" + DeaID + "&E01FESTIN=" + source);
		break;				
	case 1 : //Fast Spot
		trOption.setHeader1("1");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=" + INPTTYP + "&E01FESLN3=" + IDCusAux + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;				
	case 2 : //Spot
		trOption.setHeader1("2");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=200&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;
	case 3  ://Forward
		trOption.setHeader1("3");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1000&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;					
	case 4  ://Swap
		trOption.setHeader1("4");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1200&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;										
	case 5  ://Option
		trOption.setHeader1("5");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=800&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;
	case 6  ://Deposits	
		trOption.setHeader1("6");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=200&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;	
	case 7  ://Bankers Acceptance
		trOption.setHeader1("7");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=800&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;
	case 8  ://Commercial Papers
		trOption.setHeader1("8");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=600&E01DLSDID=" + DeaID + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSCUN=" + CusNumb + "&E01DLSSTS=" + source);
		break;
	case 9  ://CDS	
		trOption.setHeader1("9");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=1000&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;
	case 10  ://Loans
		trOption.setHeader1("10");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=400&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;																																												
	case 11  :// Not Delivered Forward
		trOption.setHeader1("11");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=1400&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP  + "&E01FESREF=" + RefNumb + "&E01FESTIN=" + source);
		break;											
	case 12  ://FRA
		trOption.setHeader1("12");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSETR0120?SCREEN=200&E01WFRCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01WFRPRO="+ INPTTYP + "&E01WFRREF=" + RefNumb + "&E01WFRDID=" + DeaID );
		break;												
	case 42  ://Line of Credit Maintenance
		trOption.setHeader1("42");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSELN0000T?SCREEN=200&CUSNUM=" + CusNumb +  "&E01FESLN3=" + IDCusAux + "&LNENUM=" + RefNumb);
		break;
	case 43  ://Customer Deals
		trOption.setHeader1("61");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1500&CUSTOMER=" + CusNumb + "&E01FESLN3=" + IDCusAux);
		break;																													
	case 61  ://Customer Details
		trOption.setHeader1("61");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + CusNumb + "&E01FESLN3=" + IDCusAux);
		break;
	case 62  ://Customer Limits
		trOption.setHeader1("62");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322?SCREEN=1&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux);
		break;
	case 65  ://Currency Rates
		trOption.setHeader1("65");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0328?SCREEN=1&BankNumber=" + bank + "&E01FESLN3=" + IDCusAux);
		break;																				
	case 66  ://Currency Positions
		trOption.setHeader1("66");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0323?SCREEN=1");
		break;
	case 68  ://Incomplete Deals
		trOption.setHeader1("68");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0321I");
		break;																																								
	case 69  :// Deals in process
		trOption.setHeader1("69");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0325T");
		break;	
	case 70  ://Federal Funds
		trOption.setHeader1("70");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=1200&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;
	case 71  ://Placements	
		trOption.setHeader1("71");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=1400&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;																																							
	case 100  ://Maintenance Foreign Exchange	
		trOption.setHeader1("100");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=40&E01FESREF=" + RefNumb + "&E01FESLN3=" + IDCusAux + "&E01FESDID=" + DeaID + "&E01FESTIN=" + source);
		break;
	case 102  ://Customer Limits Maintenance
		trOption.setHeader1("102");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322S?SCREEN=1&E01FESCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux);
		break;
	case 103  ://Maintenance Deals
		trOption.setHeader1("103");	
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=14&E01DLSREF=" + RefNumb + "&E01FESLN3=" + IDCusAux + "&E01DLSDID=" + DeaID + "&E01DLSSTS=" + source);
		break;	
	case 104  ://Incomplete Deals Maintenance
		trOption.setHeader1("104");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0325S?SCREEN=1");
		break;	
	case 110  ://Foreign Exchange Accounts
		trOption.setHeader1("110");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0333?ACCOUNT=" + ACCOUNTINQ + "&E01FESLN3=" + IDCusAux);
		break;																																																										
	case 111  ://Forward Rate Agreements Accounts
		trOption.setHeader1("111");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0332?ACCOUNT=" + ACCOUNTINQ + "&E01FESLN3=" + IDCusAux);
		break;	
	case 112  ://Deals Accounts
		trOption.setHeader1("112");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0334?ACCOUNT=" + ACCOUNTINQ + "&E01FESLN3=" + IDCusAux);
		break;	
	case 120  ://Logs Inquiry
		trOption.setHeader1("120");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEPR0200?SCREEN=1&E01ACCNUM=" + AccNum + "&E01FESLN3=" + IDCusAux);
		break;
	case 121  ://Wire Transfers
		trOption.setHeader1("121");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEPR1060?SCREEN=10");
		break;
	case 130  ://Financial Blocks	
		trOption.setHeader1("130");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=1600&E01DLSCUN=" + CusNumb + "&E01FESLN3=" + IDCusAux + "&E01FESTYP=" + INPTTYP + "&E01DLSREF=" + RefNumb + "&E01DLSSTS=" + source);
		break;																																																																																																														
	default :
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=" + INPTTYP + "&E01FESLN3=" + IDCusAux + "&E01FESTIN=" + source);
	}
  		
  			flexLog("Putting java beans into the session");
			ses.setAttribute("trOption", trOption);
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
			ses.setAttribute("error", msgError);

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
		 	  msgFex.setE01WFRREF(req.getParameter("E01FESREF"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRREF("0");
		}
		try {
		 	if (req.getParameter("E01FESDID") != null)
		 	  msgFex.setE01WFRDID(req.getParameter("E01FESDID"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRDID("0");
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

			userPO.setIdentifier(msgFex.getE01WFRREF());

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

protected void procActionCustDeals(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String PROGRAM = req.getParameter("PROGRAM");
		String REFERENCE = req.getParameter("REFERENCE");
		int optio = 0;
		
		if(PROGRAM.equals("EDL0160")){
			optio = 1;
			
		}
		if(PROGRAM.equals("EFE0160") ){
			optio = 2;
			
		}
		if(PROGRAM.equals("ETR0160")){
			optio = 3;
			
		}
				
		switch (optio) {
				case 1 : //EDL0160			   
					res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=600&E01DEAACC=" + REFERENCE);
					break;	
				case 2 : //EFE0160			   
					res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=40&E01FEMACC=" + REFERENCE);
					break;	
				case 3 : //ETR0160			   
					res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSETR0160?SCREEN=100&E01FRAACC=" + REFERENCE);
					break;	
				default :
					res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSETR0160?SCREEN=100&E01FRAACC=" + REFERENCE);
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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					req.setAttribute("NameSearch", "A"); 
					req.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_var_opt.jsp");
					callPage(LangPath + "EFE0000_fe_enter_var_opt.jsp", req, res);
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

		userPO = new UserPos();
		userPO.setOption("FEX");
		userPO.setPurpose("MAINTENANCE");
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


protected void procReqEnterCustDeals(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			
			userPO.setCusNum(req.getParameter("CUSTOMER"));
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		ses.setAttribute("UserPos", userPO);
		
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EWD0335_inq_selection.jsp");
			callPage(LangPath + "EWD0335_inq_selection.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


protected void procReqEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0301DSMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;
	String source = "";
	
	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	// Send Initial data
		try {
			msgCust = (EWD0301DSMessage)mc.getMessageRecord("EWD0301DS");
			msgCust.setRWDUSR(user.getH01USR());

			try {
				msgCust.setRWDTYP(req.getParameter("SOURCE"));
			} catch (Exception e) {
				msgCust.setRWDTYP("T");
			}
			if (msgCust.getRWDTYP() == null) msgCust.setRWDTYP("T");
			source = msgCust.getRWDTYP();
				
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
			msgCust.destroy();
			flexLog("EWD0301DS Message Sent");
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
		else if (newmessage.getFormatName().equals("EWD0301DS")) {
			
			JBObjList objList = new JBObjList();
			boolean pfirstTime = true;
			String pmarker = "";
			String pchk = "";
			
			while (true) {

				msgCust = (EWD0301DSMessage) newmessage;

				pmarker = msgCust.getSWDOPE();

				if (pmarker.equals("*")) {
					objList.setShowNext(false);
					break;
				} else {

					objList.addRow(msgCust);

					if (pmarker.equals("+")) {
						objList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}

			ses.setAttribute("prdList", objList);
			
			newmessage = mc.receiveMessage();
			
		   	if (newmessage.getFormatName().equals("EWD0001S")) {

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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					req.setAttribute("NameSearch", "A"); 
					ses.setAttribute("Type", "T"); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_inquiry.jsp");
					callPage(LangPath + "EFE0000_fe_enter_inquiry.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
									
						
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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
									
						
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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
						
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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
									
						
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




protected void procReqProductsBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0301DSMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	JBList beanList = null;
	UserPos	userPO = null;
	String source = "";
	
	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	// Send Initial data
		try {
			msgCust = (EWD0301DSMessage)mc.getMessageRecord("EWD0301DS");
			msgCust.setRWDUSR(user.getH01USR());

			try {
				msgCust.setRWDTYP(req.getParameter("SOURCE"));
			} catch (Exception e) {
				msgCust.setRWDTYP("T");
			}
			if (msgCust.getRWDTYP() == null) msgCust.setRWDTYP("T");
			source = msgCust.getRWDTYP();
				
			try {
			 msgCust.setRWDFRC(req.getParameter("FromRecord"));
			}
			catch (Exception e) {
			 msgCust.setRWDFRC("0");
			}
			
			msgCust.send();	
			msgCust.destroy();
			flexLog("EWD0301DS Message Sent");
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
		else if (newmessage.getFormatName().equals("EWD0301DS")) {
			
			JBObjList objList = new JBObjList();
			boolean pfirstTime = true;
			String pmarker = "";
			String pchk = "";
			
			while (true) {

				msgCust = (EWD0301DSMessage) newmessage;

				pmarker = msgCust.getSWDOPE();

				if (pmarker.equals("*")) {
					objList.setShowNext(false);
					break;
				} else {

					objList.addRow(msgCust);

					if (pmarker.equals("+")) {
						objList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}

			ses.setAttribute("prdList", objList);
			
			newmessage = mc.receiveMessage();
			
		   	if (newmessage.getFormatName().equals("EWD0001S")) {

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
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgCustS.getSWDIDN()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("custList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					ses.setAttribute("NameSearch", "A"); 
					ses.setAttribute("Type", source); 
					flexLog("About to call Page: " + LangPath + "EFE0000_fe_enter_var_opt.jsp");
					callPage(LangPath + "EFE0000_fe_enter_var_opt.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		  }
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

}

}