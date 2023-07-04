package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
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

public class JSEDD0000I extends datapro.eibs.master.SuperServlet {

	// RETAIL ACCOUNTS
	
	// Entering options
	protected static final int R_RT_ENTER_INQUIRY		= 1300;
	protected static final int R_SV_ENTER_INQUIRY		= 1500;

	
	protected static final int A_RT_ENTER_INQUIRY		= 1400;
	protected static final int A_SV_ENTER_INQUIRY		= 1600;


	//Inquiry Options
	protected static final int R_INQ_BALANCES			= 31;
	protected static final int R_INQ_BASIC				= 32;
	protected static final int R_INQ_MONEY				= 33;
	protected static final int R_INQ_AVERAG				= 34;
	protected static final int R_INQ_CODES				= 35;
	protected static final int R_INQ_SIGNERS			= 36;
	protected static final int R_INQ_SP_INST			= 37;
	protected static final int R_INQ_TIT				= 38;
	protected static final int R_INQ_BENE				= 39;
	protected static final int R_PRODUCT				= 40;
	protected static final int R_RELATED_ACC			= 41;
	protected static final int R_DAILY_BAL				= 42;
	protected static final int R_COLL_LIABILITIES		= 43;
	protected static final int R_COLL_ASSETS			= 44;
	protected static final int R_INQ_OVERNIGHT			= 45;
	protected static final int R_INQ_SRV_CHARGES		= 46;
	protected static final int R_INQ_LEGAL_REP			= 50;
	protected static final int R_ACCOUNT_TITLE 			= 57;
	protected static final int R_DISPLAY_TITLE 			= 58;
	protected static final int R_INQ_ACCOUNT_ANALYSIS	= 101;
    
    
    		
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDD0000I() {
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

protected void procActionEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009001Message msgRT = null;
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
		msgRT = (EDD009001Message)mc.getMessageRecord("EDD009001");
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("EDD0000");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0002");
		try {
		 	if (req.getParameter("E01ACMACC") != null)
		 	  msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
		}
		catch (Exception e)	{
	 	  msgRT.setE01ACMACC("0");
		  flexLog(" error " + e);
		}
		msgRT.send();	
	 	msgRT.destroy();
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

		if (newmessage.getFormatName().equals("EDD009001")) {
			try {
				msgRT =  new datapro.eibs.beans.EDD009001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (EDD009001Message)newmessage;
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) {  // There are no errors
				if (msgRT.getE01ACMACD().equals("04")) {
					userPO.setOption("SV");
				}
				else {
					userPO.setOption("RT");
				}
				userPO.setAccNum(msgRT.getE01ACMACC());
				userPO.setIdentifier(msgRT.getE01ACMACC());
				userPO.setBank(msgRT.getE01ACMBNK());
				userPO.setCusNum(msgRT.getE01ACMCUN());
				userPO.setHeader2(msgRT.getE01ACMCUN());
				userPO.setHeader1(msgRT.getE01ACMPRO());
				//userPO.setHeader2(msgRT.getE01ACMCCY());
				userPO.setCurrency(msgRT.getE01ACMCCY());
				userPO.setHeader3(msgRT.getE01CUSNA1());
				userPO.setOfficer(msgRT.getE01ACMOFC() + " - " + msgRT.getE01DSCOFC());
				userPO.setCusType(msgRT.getH01FLGWK3());
				userPO.setHeader10(msgRT.getE01ACMATY());
			    userPO.setHeader11(msgRT.getE01ACMACL());
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("rtBalances", msgRT);

				try {
					flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_balances.jsp");
					callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				if (userPO.getOption().equals("SV")) {
					try {
						flexLog("About to call Page: " + LangPath + "EDD0000_sv_enter_inquiry.jsp");
						callPage(LangPath + "EDD0000_sv_enter_inquiry.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					try {
						flexLog("About to call Page: " + LangPath + "EDD0000_rt_enter_inquiry.jsp");
						callPage(LangPath + "EDD0000_rt_enter_inquiry.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
protected void procReqInqAccountAnalysis(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD000010Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0002";

	// Send Initial data
	try {
		msgRT = (EDD000010Message) mc.getMessageRecord("EDD000010");
		msgRT.setH10USERID(user.getH01USR());
		msgRT.setH10PROGRM("EDD0000");
		msgRT.setH10TIMSYS(getTimeStamp());
		msgRT.setH10SCRCOD("01");
		msgRT.setH10OPECOD(opCode);
		msgRT.setE10ACMACC(userPO.getIdentifier());
		msgRT.send();
		msgRT.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDD000010")) {
			try {
				msgRT = new datapro.eibs.beans.EDD000010Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgRT = (EDD000010Message) newmessage;
			

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("accAnalysis", msgRT);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "EDD0000_rt_inq_account_analysis.jsp");
					callPage(LangPath + "EDD0000_rt_inq_account_analysis.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
					callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqCollAssets(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list.jsp");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=1");	
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
protected void procReqCollLiabilities(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list.jsp");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=3");	
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
protected void procReqDayBal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDD009201Message msgSearch = null;
	EDD009201Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int type = 0;
 	String num = "";
	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EDD009201Message)mc.getMessageRecord("EDD009201");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDL0300");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	
		try{
		 	posi= Integer.parseInt(req.getParameter("Pos"));
		}
		catch (Exception e){
		 	posi=0;	
			flexLog("E01NUMPOS");
		}

			
	
		try{
		 	msgSearch.setE01ACMACC(userPO.getIdentifier());
		}
		catch (Exception e){
		 	flexLog("E01ACMACC");
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try
	{
		newmessage = mc.receiveMessage();
		  
		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgError = (ELEERRMessage)newmessage;
				
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

		}
	   
		newmessage = mc.receiveMessage();

	  	if (newmessage.getFormatName().equals("EDD009201")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			try{
			 	beanList.setSearchText(num);
			 	beanList.setSearchType(type + "");
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			 	beanList.setSearchText("A");
			 	beanList.setSearchType("3");
			  	flexLog("Input data error " + e);
			}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";

			
			
			while (true) {

				msgList = (EDD009201Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					}
					else {
						chk = "";
					}
										
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msgList.getE01BALDT1(), msgList.getE01BALDT2() , msgList.getE01BALDT3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01GRSBAL()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01UNCOLL()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01HOLDIN()) + "</TD>");				
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DISPON()) + "</TD>");
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
			ses.setAttribute("cifList", beanList);

			try {
				 flexLog("About to call Page: " + LangPath + "EDD0092_rt_inq_day_bal.jsp");
				 callPage(LangPath + "EDD0092_rt_inq_day_bal.jsp", req, res);
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
protected void procReqInqBalances(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009001Message msgRT = null;
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

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (EDD009001Message)mc.getMessageRecord("EDD009001");
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("EDD0000");
	 	msgRT.setH01TIMSYS("");//getTimeStamp()
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD(opCode);
	 	msgRT.setE01ACMACC(userPO.getIdentifier());
		msgRT.send();	
	 	msgRT.destroy();
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

		if (newmessage.getFormatName().equals("EDD009001")) {
			try {
				msgRT = new datapro.eibs.beans.EDD009001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (EDD009001Message)newmessage;
			// showESD008004(msgRT);
			try {
			  userPO.setHeader10(msgRT.getE01ACMATY());
			  userPO.setHeader11(msgRT.getE01ACMACL());
			} catch (Exception e) {}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtBalances", msgRT);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_balances.jsp");
					callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_enter_inquiry.jsp");
					callPage(LangPath + "EDD0000_rt_enter_inquiry.jsp", req, res);	
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
protected void procReqInqBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009002Message msgRT = null;
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

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (EDD009002Message)mc.getMessageRecord("EDD009002");
	 	msgRT.setH02USERID(user.getH01USR());
	 	msgRT.setH02PROGRM("EDD0000");
	 	msgRT.setH02TIMSYS("");//getTimeStamp()
	 	msgRT.setH02SCRCOD("01");
	 	msgRT.setH02OPECOD(opCode);
	 	msgRT.setE02ACMACC(userPO.getIdentifier());
		msgRT.send();	
	 	msgRT.destroy();
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
	  flexLog("Read error " + e);
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDD009002")) {
			try {
				msgRT = new datapro.eibs.beans.EDD009002Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (EDD009002Message)newmessage;
			// showESD008004(msgRT);
			//userPO.setHeader10(msgRT.getE02ACMATY());
			//userPO.setHeader11(msgRT.getE02ACMACL());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtBasic", msgRT);
			//ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_basic.jsp");
					callPage(LangPath + "EDD0000_rt_inq_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_enter_inquiry.jsp");
					callPage(LangPath + "EDD0000_rt_enter_inquiry.jsp", req, res);	
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
protected void procReqInqBene(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
	ESD000004Message msgBene = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0006";

	// Send Initial data
	try
	{
		msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgBene.setH04USR(user.getH01USR());
	 	msgBene.setH04PGM("EDD0000");
	 	msgBene.setH04TIM(""); //getTimeStamp()
	 	msgBene.setH04SCR("01");
	 	msgBene.setH04OPE(opCode);
	 	msgBene.setE04CUN(userPO.getIdentifier());
	 	msgBene.setE04RTP("J");
		msgBene.send();	
	 	msgBene.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000004")) {
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
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_bene.jsp");
				callPage(LangPath + "EDD0000_rt_inq_bene.jsp", req, res);	
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
protected void procReqInqLegalRep(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
	ESD000004Message msgBene = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String type = "I";
	//type = req.getParameter("Type");
	if (type == null) type = "";
	userPO.setHeader10(type);

	String opCode = "0004";

	// Send Initial data
	try
	{
		msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
	 	msgBene.setH04USR(user.getH01USR());
	 	msgBene.setH04PGM("EDD0000");
	 	msgBene.setH04TIM(""); //getTimeStamp()
	 	msgBene.setH04SCR("01");
	 	msgBene.setH04OPE(opCode);
	 	msgBene.setE04CUN(userPO.getIdentifier());
	 	msgBene.setE04RTP(type);
		msgBene.send();	
	 	msgBene.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBene = new datapro.eibs.beans.ESD000004Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBene = (ESD000004Message)newmessage;
			// showESD000004(msgBene);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("legalRep", msgBene);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_legal_rep.jsp");
				callPage(LangPath + "EDD0000_rt_inq_legal_rep.jsp", req, res);	
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
protected void procReqInqFirm(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgBene = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	userPO.setAccOpt("SIGNERS");

	String opCode = null;
	String country = null;

	opCode = "0002";
	country = req.getParameter("COUNTRY");

	// Send Initial data
	try {
		msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
		msgBene.setH04USR(user.getH01USR());
		msgBene.setH04PGM("EDD0000");
		msgBene.setH04TIM(getTimeStamp());
		try {
			msgBene.setH04SCR(req.getParameter("H04SCR"));
		} catch (Exception e) {
			msgBene.setH04SCR("07");
		}
		msgBene.setH04OPE(opCode);
		msgBene.setE04CUN(userPO.getIdentifier());
		msgBene.setE04RTP("S");
		msgBene.send();
		msgBene.destroy();
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
				flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000004")) {
			try {
				msgBene = new datapro.eibs.beans.ESD000004Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBene = (ESD000004Message)newmessage;
			// showESD000004(msgBene);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtFirm", msgBene);

			try {
				if (country.equals("PA")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_inq_firm_pa.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_firm_pa.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else if (country.equals("VE")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_inq_firm_ve.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_firm_ve.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_inq_firm_generic.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_firm_generic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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

/**
 * This method was created in VisualAge.
 */
protected void procReqInqMoney(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELD000001Message msgRT = null;
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

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
	 	msgRT.setH06USERID(user.getH01USR());
	 	msgRT.setH06PROGRM("EDD0000");
	 	msgRT.setH06TIMSYS("");//getTimeStamp()
	 	msgRT.setH06SCRCOD("01");
	 	msgRT.setH06OPECOD(opCode);
	 	msgRT.setE06LDMACC(userPO.getIdentifier());
		msgRT.send();	
	 	msgRT.destroy();
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
	catch (Exception e) {
		flexLog("Read error " + e);
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELD000001")) {
			try {
				msgRT = new datapro.eibs.beans.ELD000001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ELD000001Message)newmessage;
			// showESD008004(msgRT);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtMoney", msgRT);
			
 
			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_money.jsp");
					callPage(LangPath + "EDD0000_rt_inq_money.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
					callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
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
protected void procReqInqOvernight(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009002Message msgRT = null;
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

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (EDD009002Message)mc.getMessageRecord("EDD009002");
	 	msgRT.setH02USERID(user.getH01USR());
	 	msgRT.setH02PROGRM("EDD0000");
	 	msgRT.setH02TIMSYS("");//getTimeStamp()
	 	msgRT.setH02SCRCOD("01");
	 	msgRT.setH02OPECOD(opCode);
	 	msgRT.setE02ACMACC(userPO.getIdentifier());
		msgRT.send();	
	 	msgRT.destroy();
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

		if (newmessage.getFormatName().equals("EDD009002")) {
			try {
				msgRT = new datapro.eibs.beans.EDD009002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (EDD009002Message)newmessage;
			// showESD008004(msgRT);

			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtOvernight", msgRT);
			
 
			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_overnight.jsp");
					callPage(LangPath + "EDD0000_rt_inq_overnight.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balance.jsp");
					callPage(LangPath + "EDD0000_rt_inq_balance.jsp", req, res);	
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
protected void procReqInqSpcInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgRT = null;
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
		msgRT = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgRT.setH05USR(user.getH01USR());
	 	msgRT.setH05PGM("EDD0000");
	 	msgRT.setH05TIM(""); //getTimeStamp()
	 	msgRT.setH05SCR("01");
	 	msgRT.setH05OPE(opCode);
	 	msgRT.setE05ACC(userPO.getIdentifier());
	 	msgRT.setE05TYP("1");
		msgRT.send();	
	 	msgRT.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgRT = new datapro.eibs.beans.ESD000005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ESD000005Message)newmessage;
			// showESD008004(msgRT);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtInst", msgRT);

			try {
				flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_special_inst.jsp");
				callPage(LangPath + "EDD0000_rt_inq_special_inst.jsp", req, res);	
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
protected void procReqInqSpecialCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgRT = null;
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
		msgRT = (ESD000002Message)mc.getMessageRecord("ESD000002");
	 	msgRT.setH02USR(user.getH01USR());
	 	msgRT.setH02PGM("EDD0000");
	 	msgRT.setH02TIM(""); //getTimeStamp()
	 	msgRT.setH02SCR("01");
	 	msgRT.setH02OPE(opCode);
	 	msgRT.setE02ACC(userPO.getIdentifier());
	 	msgRT.send();	
	 	msgRT.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgRT = new datapro.eibs.beans.ESD000002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ESD000002Message)newmessage;
			// showESD008004(msgRT);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtCodes", msgRT);

			try {
				flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_codes.jsp");
				callPage(LangPath + "EDD0000_rt_inq_codes.jsp", req, res);	
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
protected void procReqInqTit(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000006Message msgRT = null;
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
		msgRT = (ESD000006Message)mc.getMessageRecord("ESD000006");
	 	msgRT.setH06USR(user.getH01USR());
	 	msgRT.setH06PGM("EDD0000");
	 	msgRT.setH06TIM(getTimeStamp());
	 	msgRT.setH06SCR("01");
	 	msgRT.setH06OPE(opCode);
	 	msgRT.setE06ACC(userPO.getIdentifier());
	 	msgRT.setE06RTP("H");
		msgRT.send();	
	 	msgRT.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000006")) {
			try {
				msgRT = new datapro.eibs.beans.ESD000006Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ESD000006Message)newmessage; 
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtTit", msgRT);
			getTitularsDescription(user, req, res, ses);
			try {
				flexLog("About to call Page3: " + LangPath + "EDD0000_rt_inq_tit.jsp");
				callPage(LangPath + "EDD0000_rt_inq_tit.jsp", req, res);	
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductInfo(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071103Message msgProdDDA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = userPO.getBank();
  	String prodCode = userPO.getHeader1();

	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdDDA = (ESD071103Message)mc.getMessageRecord("ESD071103");
	 	msgProdDDA.setH03USERID(user.getH01USR());
	 	msgProdDDA.setH03PROGRM("ESD0711");
	 	msgProdDDA.setH03TIMSYS(getTimeStamp());
	 	msgProdDDA.setH03SCRCOD("01");
	 	msgProdDDA.setH03OPECOD(opCode);
	 	msgProdDDA.setE03APCCDE(prodCode);
	 	msgProdDDA.setE03APCBNK(bank);
		msgProdDDA.send();	
	 	msgProdDDA.destroy();
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
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD071103")) {
			try {
				msgProdDDA = new datapro.eibs.beans.ESD071103Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdDDA = (ESD071103Message)newmessage;
			// showESD071103(msgProdDDA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("ddaProdInq", msgProdDDA);

			try {
				flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_dda.jsp");
				callPage(LangPath + "ESD0711_products_inq_dda.jsp", req, res);	
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
protected void procReqRTEnterInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("RT");
		userPO.setPurpose("INQUIRY");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} 
	catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0000_rt_enter_inquiry.jsp");
		callPage(LangPath + "EDD0000_rt_enter_inquiry.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqSVEnterInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("SV");
		userPO.setPurpose("INQUIRY");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} 
	catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0000_sv_enter_inquiry.jsp");
		callPage(LangPath + "EDD0000_sv_enter_inquiry.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
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

		int screen = R_RT_ENTER_INQUIRY;
		
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
				// BEGIN RETAIL ACCOUNT		
					
					
				//Inquiry Options
				case R_INQ_BALANCES :
					procReqInqBalances(mc, msgUser, req, res, session);
					break;
				case R_INQ_BASIC :
					procReqInqBasic(mc, msgUser, req, res, session);
					break;
			 	case R_INQ_BENE  :
			 	    procReqInqBene(mc, msgUser, req, res, session);
					break;	
				case R_INQ_LEGAL_REP  :
					procReqInqLegalRep(mc, msgUser, req, res, session);
					break;	
				case R_INQ_MONEY :
					procReqInqMoney(mc, msgUser, req, res, session);
					break;
				case R_INQ_CODES :
					procReqInqSpecialCodes(mc, msgUser, req, res, session);
					break;
				case R_INQ_SIGNERS :
					procReqInqFirm(mc, msgUser, req, res, session);
					break;
				case R_INQ_SP_INST :
					procReqInqSpcInst(mc, msgUser, req, res, session);
					break;
				case R_INQ_TIT :
					procReqInqTit(mc, msgUser, req, res, session);
					break;
				case R_INQ_OVERNIGHT :
					procReqInqOvernight(mc, msgUser, req, res, session);
					break;	
				case R_PRODUCT :
					procReqProductInfo(mc, msgUser, req, res, session);
					break;
				case R_ACCOUNT_TITLE :
					procReqAccountTitle(mc, msgUser, req, res, session);
					break;																							
				case R_DISPLAY_TITLE :
					procReqDisplayTitle(mc, msgUser, req, res, session);
					break;																							
				case R_DAILY_BAL :
					procReqDayBal(mc, msgUser, req, res, session);
					break;
				case R_COLL_LIABILITIES :
					procReqCollLiabilities(mc, msgUser, req, res, session);
					break;
				case R_COLL_ASSETS :
					procReqCollAssets(mc, msgUser, req, res, session);
					break;
				case R_INQ_SRV_CHARGES :
					procReqInqSrvCharges(mc, msgUser, req, res, session);
					break;
				case R_INQ_ACCOUNT_ANALYSIS :
					procReqInqAccountAnalysis(mc, msgUser, req, res, session);
					break;

/*
				case R_RELATED_ACC :
					break;

*/
				// END RETAIL ACCOUNT

				// BEGIN EnterinG
				// Request
				case R_RT_ENTER_INQUIRY : 
					procReqRTEnterInquiry(msgUser, req, res, session);
					break;
				case R_SV_ENTER_INQUIRY : 
					procReqSVEnterInquiry(msgUser, req, res, session);
					break;
						
				// Action 
				case A_RT_ENTER_INQUIRY : 
				case A_SV_ENTER_INQUIRY : 
					procActionEnterInquiry(mc,msgUser, req, res, session);
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

	

/**
 * This method was created in VisualAge.
 */
protected void procReqInqSrvCharges(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD0712DSMessage msgDDAServCharge = null;
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

	String opCode = "0004";

	String bank = userPO.getBank();
	String type = userPO.getHeader10();
	String table = userPO.getHeader11();

		// Send Initial data
		try
		{
			msgDDAServCharge = (ESD0712DSMessage)mc.getMessageRecord("ESD0712DS");
		 	msgDDAServCharge.setH01USERID(user.getH01USR());
		 	msgDDAServCharge.setH01PROGRM("ESD0711");
		 	msgDDAServCharge.setH01TIMSYS(getTimeStamp());
		 	msgDDAServCharge.setH01SCRCOD("01");
		 	msgDDAServCharge.setH01OPECOD(opCode);
		 	try {
			 	msgDDAServCharge.setE01SELBNK(bank);
			 	msgDDAServCharge.setE01SELTBL(table);
			 	msgDDAServCharge.setE01SELTYP(type);
		 	} catch (Exception e) {
		 	}
			msgDDAServCharge.send();	
		 	msgDDAServCharge.destroy();

		 	flexLog("ESD0712DS Sent");
		}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}


	//Receive Error Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("ESD0712DS")) {
				try {
					msgDDAServCharge = new datapro.eibs.beans.ESD0712DSMessage();
					flexLog("ESD0712DS Received");
			  	} catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgDDAServCharge = (ESD0712DSMessage)newmessage;


				flexLog("Putting java beans into the session");
				ses.setAttribute("ddaServCharge", msgDDAServCharge);
				ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_serv_charges.jsp");
				callPage(LangPath + "EDD0000_rt_inq_serv_charges.jsp", req, res);	
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
	else
		opCode = "0004";

	// Send Initial data
	try
	{
		msgMailA = (ESD000004Message)mc.getMessageRecord("ESD000004");
		msgMailA.setH04USR(user.getH01USR());
		msgMailA.setH04PGM("ESD0080");
		msgMailA.setH04TIM(getTimeStamp());
		msgMailA.setH04SCR("01");
		msgMailA.setH04OPE(opCode);
		msgMailA.setE04CUN(userPO.getIdentifier());
		msgMailA.setE04RTP("1");
		msgMailA.setH04WK1("T");
		msgMailA.setH04WK3("1");
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
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
	
		try {
			flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_basic.jsp");
			callPage(LangPath + "EDD0000_rt_inq_basic.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}		
		
	  }
	  else if (newmessage.getFormatName().equals("ESD000004")) {
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
			
			if (userPO.getOption().equals("RT")) {
				try {
					flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_account_title.jsp");
					callPage(LangPath + "EDD0000_rt_inq_account_title.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (userPO.getOption().equals("SV")) {
				try {
					flexLog("About to call Page: " + LangPath + "EDD0000_sv_inq_account_title.jsp");
					callPage(LangPath + "EDD0000_sv_inq_account_title.jsp", req, res);
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

protected void procReqDisplayTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009099Message msgRT = null;
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

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (EDD009099Message)mc.getMessageRecord("EDD009099");
		msgRT.setH99USERID(user.getH01USR());
		msgRT.setH99PROGRM("EDD0000");
		msgRT.setH99TIMSYS("");//getTimeStamp()
		msgRT.setH99SCRCOD("01");
		msgRT.setH99OPECOD(opCode);
		msgRT.setE99ACCNUM(userPO.getIdentifier());
		msgRT.send();	
		msgRT.destroy();
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
	  flexLog("Read error " + e);
	}	
	
	// Receive Data
	try
	{
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDD009099")) {
			try {
				msgRT = new datapro.eibs.beans.EDD009099Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgRT = (EDD009099Message)newmessage;
			// showESD008004(msgRT);
			//userPO.setHeader10(msgRT.getE02ACMATY());
			//userPO.setHeader11(msgRT.getE02ACMACL());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("cdTitle", msgRT);
			//ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
				if (userPO.getOption().equals("RT")) {
					try {
						flexLog("About to call Page: " + LangPath + "EDD0000_rt_inq_display_title.jsp");
						callPage(LangPath + "EDD0000_rt_inq_display_title.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else if (userPO.getOption().equals("SV")) {
					try {
						flexLog("About to call Page: " + LangPath + "EDD0000_sv_inq_display_title.jsp");
						callPage(LangPath + "EDD0000_sv_inq_display_title.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
				}				
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_basic.jsp");
					callPage(LangPath + "EDD0000_rt_inq_basic.jsp", req, res);	
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

protected void getTitularsDescription(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0002DSMessage msgCnofc = null;
	boolean IsNotError = false;
	JBObjList ObjList = null;
	MessageContext mc = null;

	try {
		flexLog("Opennig Socket Connection");
		Socket s = null;
		s = new Socket(super.hostIP, getInitSocket(req));
		s.setSoTimeout(super.sckTimeOut);
		mc = new MessageContext(getMessageHandler("EWD0002",req));
		try {
			msgCnofc = (EWD0002DSMessage) mc.getMessageRecord("EWD0002DS");
			msgCnofc.setEWDREC("0");
			msgCnofc.setEWDTBL("T8");
			msgCnofc.send();
			msgCnofc.destroy();
			flexLog("EWD000202 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0002DS")) {
				try {
					ObjList = new JBObjList();
				}
				catch(Exception ex) {
					System.out.println("Error: " + ex);
				}
				try {
					StringBuffer myRow = null;
					while (true) {
					    msgCnofc = (EWD0002DSMessage) newmessage;
					    if (!msgCnofc.getEWDOPE().equals("+"))
						break;
					    ObjList.addRow(msgCnofc);
					    newmessage = mc.receiveMessage();
					}
					
					ses.setAttribute("cnofcList", ObjList);
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			}
		} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
		}	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
	}
}	

}