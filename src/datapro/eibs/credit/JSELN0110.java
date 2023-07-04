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

public class JSELN0110 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_CL_CUSTOMER			= 1;
	protected static final int A_CL_CUSTOMER			= 2;
	protected static final int R_CL_TYPE				= 3;
	protected static final int A_CL_TYPE				= 4;
	protected static final int R_CL_RELATED			= 5;
	protected static final int A_CL_RELATED			= 6;
	protected static final int R_ACTIVITY				= 7;
	protected static final int A_ACTIVITY				= 8;

	// entering options
	protected static final int R_ENTER 				= 100;
	protected static final int A_ENTER	 			= 200;
	protected static final int R_ENTER_CUSTOMER		= 300;
	protected static final int A_ENTER_CUSTOMER 		= 400;
	protected static final int R_ENTER_TYPE			= 500;
	protected static final int A_ENTER_TYPE 			= 600;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELN0110() {
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
protected void procActionEnterCust(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF01008Message msgCus = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

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
		msgCus = (ECIF01008Message)mc.getMessageRecord("ECIF01008");
	 	msgCus.setH08USERID(user.getH01USR());
	 	msgCus.setH08PROGRM("EDL0160");
	 	msgCus.setH08TIMSYS(getTimeStamp());
	 	msgCus.setH08SCRCOD("01");
	 	msgCus.setH08OPECOD("0004");
		try {
		 	if (req.getParameter("CUSNUM") != null)
		 	  msgCus.setE08CUSCUN(req.getParameter("CUSNUM"));
		}
		catch (Exception e)	{
	 	  msgCus.setE08CUSCUN("0");
		  flexLog("Error Sending");
		}
		msgCus.send();	
	 	msgCus.destroy();
	 	flexLog("ECIF01008 Message Sent");
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
			ses.setAttribute("error", msgError);
			if(!msgError.getERRNUM().equals("0")){
				try {
					flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
					callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			
	  	}
	  	
	  	newmessage = mc.receiveMessage();
	  	
	  	if (newmessage.getFormatName().equals("ECIF01008")) {
			try {
				msgCus = new datapro.eibs.beans.ECIF01008Message();
				flexLog("ECIF01008 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCus = (ECIF01008Message)newmessage;
			
			userPO.setCusNum(msgCus.getE08CUSCUN());
			userPO.setCusName(msgCus.getE08CUSNA1());
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=0");
			
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
protected void procActionEnterType(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000013Message msgCLType = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

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
		msgCLType = (ESD000013Message)mc.getMessageRecord("ESD000013");
	 	msgCLType.setH13USERID(user.getH01USR());
	 	msgCLType.setH13PROGRM("ESD0000");
	 	msgCLType.setH13TIMSYS(getTimeStamp());
	 	msgCLType.setH13SCRCOD("01");
	 	msgCLType.setH13OPECOD("0004");
	 	msgCLType.setE13CNOCFL("14");
		try {
		 	String typ = req.getParameter("CLTYPE");
			if (typ != null)
		 	  msgCLType.setE13CNORCD(typ.toUpperCase());
		}
		catch (Exception e)	{
		  flexLog("Error Sending");
		}
		msgCLType.send();	
	 	msgCLType.destroy();
	 	flexLog("ESD000013 Message Sent");
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
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_type_enter.jsp");
				callPage(LangPath + "ELN0110_cl_type_enter.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000013")) {
			try {
				msgCLType = new datapro.eibs.beans.ESD000013Message();
				flexLog("ESD000013 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCLType = (ESD000013Message)newmessage;
			
			userPO.setCreditLineType(msgCLType.getE13CNORCD());
			userPO.setHeader1(msgCLType.getE13CNODSC());
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=3&Pos=0");

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
protected void procActionList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (UserPos) ses.getAttribute("userPO");

  		int option = Integer.parseInt(req.getParameter("opt"));
  		String cus_line = req.getParameter("CUS_LINE");

		String cus = Util.leftValue(cus_line);
		String line = Util.rightValue(cus_line);
		String id = Util.formatID(cus, line);
		
  		userPO.setCusNum(cus);
  		userPO.setCreditLineNum(line);
  		if (userPO.getCusName() == null)
  		  userPO.setCusName("");
  		if (userPO.getHeader1() == null)
  		  userPO.setHeader1("");
  		userPO.setIdentifier(id);
		ses.setAttribute("userPO", userPO);

		switch (option) {
				case 1 : // Credit Line Inquiry
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0060?SCREEN=1");
					break;
				case 2 : // Activity
					procReqHeaderActivity(mc, user, req, res, ses);
					break;
				case 4 : // Related Credit Lines
					procReqListRelated(mc, user, req, res, ses);
					break;				
				case 3 : // Collaterals
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=1");
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");
  		}
  			

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
protected void procReqEnterCust(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("CL_CUSTOMER");
		userPO.setPurpose("");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
		callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqEnterType(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("CL_TYPE");
		userPO.setPurpose("");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELN0110_cl_type_enter.jsp");
		callPage(LangPath + "ELN0110_cl_type_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqHeaderActivity(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN011002Message msgHeader = null;
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

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgHeader = (ELN011002Message)mc.getMessageRecord("ELN011002");
		msgHeader.setH02USERID(user.getH01USR());
	 	msgHeader.setH02PROGRM("ELN0110");
	 	msgHeader.setH02TIMSYS(getTimeStamp());
	 	msgHeader.setH02SCRCOD("01");
	 	msgHeader.setH02OPECOD("0004");
		msgHeader.setE02LNENUM(userPO.getCreditLineNum());
		msgHeader.setE02LNECUN(userPO.getCusNum());

	 	msgHeader.send();	
	 	msgHeader.destroy();
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

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
				callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ELN011002")) {
			try {
				msgHeader = new datapro.eibs.beans.ELN011002Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgHeader = (ELN011002Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("header", msgHeader);

			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=7&Pos=0");
				
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
protected void procReqListActivity(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN011003Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN011003Message)mc.getMessageRecord("ELN011003");
		msgList.setH03USERID(user.getH01USR());
	 	msgList.setH03PROGRM("ELN0110");
	 	msgList.setH03TIMSYS(getTimeStamp());
	 	msgList.setH03SCRCOD("01");
	 	msgList.setH03OPECOD("0004");
		msgList.setE03LNENUM(userPO.getCreditLineNum());
		msgList.setE03LNECUN(userPO.getCusNum());
		try{
			msgList.setE03RECNUM(req.getParameter("Pos"));
		}
		catch (Exception e) {
			msgList.setE03RECNUM("0");
		}

	 	msgList.send();	
	 	msgList.destroy();
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

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
				callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ELN011003")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String acc = "";
			
			while (true) {

				msgList = (ELN011003Message)newmessage;

				marker = msgList.getE03ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE03RECNUM()));
					}

					acc = msgList.getE03ACCNUM();
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.formatCell(msgList.getE03ACCNUM()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.formatDate(msgList.getE03OPROP1(), msgList.getE03OPROP2(), msgList.getE03OPROP3()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.formatDate(msgList.getE03OPRMT1(), msgList.getE03OPRMT2(), msgList.getE03OPRMT3()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.formatCell(msgList.getE03OPRCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.fcolorCCY(msgList.getE03OPRAMT()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.formatCell(msgList.getE03OPRTYP()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + acc + "')\">" + Util.fcolorCCY(msgList.getE03OPRSUM()) + "</A></TD>");
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
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_list_activity.jsp");
				callPage(LangPath + "ELN0110_cl_list_activity.jsp", req, res);

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
protected void procReqListByCust(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN011001Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	if (req.getParameter("customer") != null) {
		userPO.setCusNum(req.getParameter("customer"));
	}

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN011001Message)mc.getMessageRecord("ELN011001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ELN0110");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
		try{
			msgList.setE01LNECUN(userPO.getCusNum());
		}
		catch (Exception e) {
			msgList.setE01LNECUN("0");
		}
		try{
			msgList.setE01RECNUM(req.getParameter("Pos"));
		}
		catch (Exception e) {
			msgList.setE01RECNUM("0");
		}

	 	msgList.send();	
	 	msgList.destroy();
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

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
				callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ELN011001")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			
			while (true) {

				msgList = (ELN011001Message)newmessage;

				marker = msgList.getE01ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01RECNUM()));
						userPO.setCusName(msgList.getE01DESCRP());
						chk = "checked";
					}
					else {
						chk = "";
					}

					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUS_LINE\" value=\"" + msgList.getE01LNECUN() + "_" + msgList.getE01LNENUM() + "\" " + chk + "></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01LNENUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01LNETYL()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE01LNEMT1(), msgList.getE01LNEMT2(), msgList.getE01LNEMT3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01LNECCY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01LNEAMN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AVAILA()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01COLDEP()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01COLNDO()) + "</TD>");
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
			ses.setAttribute("clList", beanList);
			ses.setAttribute("userPO", userPO);
			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_list_by_customer.jsp");
				callPage(LangPath + "ELN0110_cl_list_by_customer.jsp", req, res);

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
protected void procReqListByType(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN011001Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN011001Message)mc.getMessageRecord("ELN011001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ELN0110");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
		msgList.setE01LNETYL(userPO.getCreditLineType());
		try{
			msgList.setE01RECNUM(req.getParameter("Pos"));
		}
		catch (Exception e) {
			msgList.setE01RECNUM("0");
		}

	 	msgList.send();	
	 	msgList.destroy();
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

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_type_enter.jsp");
				callPage(LangPath + "ELN0110_cl_type_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ELN011001")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			
			while (true) {

				msgList = (ELN011001Message)newmessage;

				marker = msgList.getE01ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01RECNUM()));
						chk = "checked";
					}
					else {
						chk = "";
					}

					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUS_LINE\" value=\"" + msgList.getE01LNECUN() + "_" + msgList.getE01LNENUM() + "\" " + chk + "></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE01LNEMT1(), msgList.getE01LNEMT2(), msgList.getE01LNEMT3()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01LNENUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01LNECUN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01LNECCY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01LNEAMN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AVAILA()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01COLDEP()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01COLNDO()) + "</TD>");					
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
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_list_by_type.jsp");
				callPage(LangPath + "ELN0110_cl_list_by_type.jsp", req, res);

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
protected void procReqListRelated(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN011004Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN011004Message)mc.getMessageRecord("ELN011004");
		msgList.setH04USERID(user.getH01USR());
	 	msgList.setH04PROGRM("ELN0110");
	 	msgList.setH04TIMSYS(getTimeStamp());
	 	msgList.setH04SCRCOD("01");
	 	msgList.setH04OPECOD("0004");
		msgList.setE04LNENUM(userPO.getCreditLineNum());
		msgList.setE04LNECUN(userPO.getCusNum());
		try{
			msgList.setE04RECNUM(req.getParameter("Pos"));
		}
		catch (Exception e) {
			msgList.setE04RECNUM("0");
		}

	 	msgList.send();	
	 	msgList.destroy();
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

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_client_enter.jsp");
				callPage(LangPath + "ELN0110_cl_client_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ELN011004")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			
			while (true) {

				msgList = (ELN011004Message)newmessage;

				marker = msgList.getE04ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE04RECNUM()));
						chk = "checked";
					}
					else {
						chk = "";
					}

					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUS_LINE\" value=\"" + msgList.getE04LNECUN() + "_" + msgList.getE04LNENUM() + "\" " + chk + "></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE04LNEMT1(), msgList.getE04LNEMT2(), msgList.getE04LNEMT3()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04LNENUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04LNETYL()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04LNECUN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE04LNECCY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04LNEAMN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04AVAILA()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04COLDEP()) + "</TD>");
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
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ELN0110_cl_list_related.jsp");
				callPage(LangPath + "ELN0110_cl_list_related.jsp", req, res);

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
				mc = new MessageContext(super.getMessageHandler("ELN0110", req));
			
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				// Request
				case R_ACTIVITY :
					procReqListActivity(mc, msgUser, req, res, session);
					break;
				case R_CL_CUSTOMER : 
					procReqListByCust(mc, msgUser, req, res, session);
					break;
				case R_CL_RELATED : 
					// procReqListRelated(mc, msgUser, req, res, session);
					break;
				case R_CL_TYPE : 
					procReqListByType(mc, msgUser, req, res, session);
					break;
				// Action
				case A_ACTIVITY 	: 
					break;
				case A_CL_CUSTOMER 	: 
				case A_CL_RELATED	: 
				case A_CL_TYPE 		: 
					procActionList(mc, msgUser, req, res, session);
					break;
				//entering options
				case R_ENTER_CUSTOMER : 
					procReqEnterCust(msgUser, req, res, session);
					break;
				case A_ENTER_CUSTOMER : 
					procActionEnterCust(mc, msgUser, req, res, session);
					break;
				case R_ENTER_TYPE : 
					procReqEnterType(msgUser, req, res, session);
					break;
				case A_ENTER_TYPE : 
					procActionEnterType(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				if(mc != null)
					mc.close();
			}
			

		}
		catch (Exception e) {
			e.printStackTrace();
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