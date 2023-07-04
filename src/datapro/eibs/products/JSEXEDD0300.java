package datapro.eibs.products;

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

public class JSEXEDD0300 extends JSEDD0300 {

/**
	 * JSEXEDD0000 constructor comment.
*/
public JSEXEDD0300() {
		super();
}
	
public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
}

/**
 * This method was created in VisualAge.
 * by David Mavilla.
 * on 5/17/00.
 */
protected void procActionEnterHoldUncoll(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD030001Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
		userPO.setIdentifier(req.getParameter("E01UNCACC"));

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		flexLog("Calling Request");
		procReqHoldUncollList(mc,user,req,res,ses);

	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterHoldUncollRT(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO =  new datapro.eibs.beans.UserPos();
		userPO.setOption("HOLD_UNCOLLECTED_RT");
		userPO.setPurpose("MAINTENANCE");
		userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0300?SCREEN=200");
		userPO.setProdCode("RA");
			//Others Parameters
			userPO.setHeader1("E01UNCACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterHoldUncollSV(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("HOLD_UNCOLLECTED_SV");
		userPO.setPurpose("MAINTENANCE");
		userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0300?SCREEN=200");
		userPO.setProdCode("04");
			//Others Parameters
			userPO.setHeader1("E01UNCACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterHoldUncollCD(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("HOLD_UNCOLLECTED_CD");
		userPO.setPurpose("MAINTENANCE");
		userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0300?SCREEN=200");
		userPO.setProdCode("CD");
			//Others Parameters
			userPO.setHeader1("E01UNCACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqHoldUncollList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD030001Message msgList = null;
	JBListRec holduncollList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	int colNum = 12;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgList = (EDD030001Message)mc.getMessageRecord("EDD030001");
	 	msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("EDD0300");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0015");
		try {
			msgList.setE01UNCACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		}

		msgList.send();	
	 	msgList.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		holduncollList = new datapro.eibs.beans.JBListRec();
		holduncollList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	
	// Receive Error Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
		
			try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
			} catch (Exception e) {
						flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EDD030001")) {
		  	
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
					
			while (true) {

				msgList = (EDD030001Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (firstTime) {
					firstTime = false;
					userPO.setCurrency(msgList.getE01UNCCCY());
					userPO.setHeader1(msgList.getE01UNCCUN());
					userPO.setHeader5(msgList.getE01CUSNA1());
					userPO.setHeader6(msgList.getE01UNCPRO());
					userPO.setHeader10(msgList.getE01UNCBNK());
					userPO.setHeader11(msgList.getE01UNCBRN());
					userPO.setHeader12(""); // General Ledger
					userPO.setHeader20("");
					userPO.setHeader21("");
				}

				if (marker.equals("*")) {
					userPO.setHeader2(msgList.getE01UNCF01());
					break;
				}
				else {
					// Quote List
					myRow[0] =   msgList.getE01UNCF01();	// Seq
					myRow[1] =   msgList.getE01UNCFLG();	// Type
					myRow[2] =   msgList.getE01UNCRD1();	// Proccess Date 1
					myRow[3] =   msgList.getE01UNCRD2();	// Proccess Date 2
					myRow[4] =   msgList.getE01UNCRD3();	// Proccess Date 3
					myRow[5] =   msgList.getE01UNCMD1();	// Maturity Date 1
					myRow[6] =   msgList.getE01UNCMD2();	// Maturity Date 2
					myRow[7] =   msgList.getE01UNCMD3();	// Maturity Date 3
					myRow[8] =   msgList.getE01UNCDYS();	// Number of Days
					myRow[9] =   msgList.getE01UNCAMN();	// Amount
					myRow[10] =  msgList.getE01UNCRSN();	// Reason
					myRow[11] =  msgList.getE01UNCCKN();	// References
					
						
					holduncollList.addRow(myFlag, myRow);
									
				}

				newmessage = mc.receiveMessage();

			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("holduncoll", holduncollList);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected.jsp");
				callPage(LangPath + "EDD0300_hold_uncollected.jsp", req, res);

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