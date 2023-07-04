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

public class JSEDD0300 extends datapro.eibs.master.SuperServlet {


   	protected static final int A_HOLD_UNCOLL			= 2;
	protected static final int A_HOLD_UNCOLL_DET		= 4;
	// entering options
	protected static final int R_ENTER_RT				= 100;
	protected static final int R_ENTER_SV				= 300;
	protected static final int R_ENTER_CD				= 400;
	// options
	protected static final int R_HOLD_UNCOLL			= 1;
	protected static final int R_HOLD_UNCOLL_DET		= 3;

	protected static final int A_ENTER	 			= 200;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDD0300() {
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

		int screen = R_ENTER_RT;
		
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
		
			switch (	screen) {
				case R_HOLD_UNCOLL :
					procReqHoldUncollList(mc, msgUser, req, res, session);
					break;
				case A_HOLD_UNCOLL :
					procActionHoldUncollList(mc, msgUser, req, res, session);
					break;				
				case A_HOLD_UNCOLL_DET :
					procActionHoldUncollDet(mc, msgUser, req, res, session);
					break;				

				// Entering options
				case R_ENTER_RT :
					procReqEnterHoldUncollRT(msgUser, req, res, session);
					break;
				case R_ENTER_SV :
					procReqEnterHoldUncollSV(msgUser, req, res, session);
					break;
				case R_ENTER_CD :
					procReqEnterHoldUncollCD(msgUser, req, res, session);
					break;
				case A_ENTER :
					procActionEnterHoldUncoll(mc, msgUser, req, res, session);
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
protected void procActionHoldUncollDet(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD030001Message msgRT = null;
	ELEERRMessage msgError = null;
	JBListRec myList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int option;
	int row = -1;
	
	try {
			row = Integer.parseInt(req.getParameter("ROW"));
		}
	catch (Exception e) {
	}
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
		option = Integer.parseInt(req.getParameter("opt"));

		flexLog("Send Initial Data");
	 	msgRT = (EDD030001Message)mc.getMessageRecord("EDD030001");
		msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("EDD0300");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	if (option == 1) 
		 	msgRT.setH01OPECOD("0001");
		else
		 	msgRT.setH01OPECOD("0002");

		// all the fields here
	 	java.util.Enumeration enu = msgRT.fieldEnumeration();
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

	 	//msgRT.send();
	 	mc.sendMessage(msgRT);
	 	msgRT.destroy();
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

		if (newmessage.getFormatName().equals("EDD030001")) {
			try {
				msgRT = new datapro.eibs.beans.EDD030001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (EDD030001Message)newmessage;
			// showESD008004(msgRT);

			if (IsNotError) {  // There are no errors
				res.setContentType("text/html");
				PrintWriter  out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println("		top.opener.window.location.href='" + super.webAppPath + "/servlet/datapro.eibs.products.JSEDD0300?SCREEN=1'");
				out.println("		top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			}
			else {  // There are errors
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("huBasic", msgRT);
				ses.setAttribute("userPO", userPO);
				if (option == 1) {
					try {
						flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_det_new.jsp");
						callPage(LangPath + "EDD0300_hold_uncollected_det_new.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					myList = (datapro.eibs.beans.JBListRec)ses.getAttribute("holduncoll");
					myList.setRecord(msgRT.getE01UNCF01(),0,row);	// Seq
					myList.setRecord(msgRT.getE01UNCFLG(),1,row);	// Type
					myList.setRecord(msgRT.getE01UNCRD1(),2,row);	// Proccess Date 1
					myList.setRecord(msgRT.getE01UNCRD2(),3,row);	// Proccess Date 2
					myList.setRecord(msgRT.getE01UNCRD3(),4,row);	// Proccess Date 3
					myList.setRecord(msgRT.getE01UNCMD1(),5,row);	// Maturity Date 1
					myList.setRecord(msgRT.getE01UNCMD2(),6,row);	// Maturity Date 2
					myList.setRecord(msgRT.getE01UNCMD3(),7,row);	// Maturity Date 3
					myList.setRecord(msgRT.getE01UNCDYS(),8,row);	// Number of Days
					myList.setRecord(msgRT.getE01UNCAMN(),9,row);	// Amount
					myList.setRecord(msgRT.getE01UNCRSN(),10,row);	// Reason
					myList.setRecord(msgRT.getE01UNCCKN(),11,row);	// References
					ses.setAttribute("holduncoll", myList);
					try {
						flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_det.jsp?ROW=" + row);
						res.sendRedirect(super.srctx + LangPath + "EDD0300_hold_uncollected_det.jsp?ROW=" + row);	
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
protected void procActionHoldUncollList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	JBListRec holduncollList = null;

	holduncollList = (JBListRec)ses.getAttribute("holduncoll");
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try	{
		int option = Integer.parseInt(req.getParameter("opt"));
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch (Exception e) {
		}
		
		switch (option) {
				case 1 : // New
					int seq = 0;
					if (holduncollList.getNoResult()) {
						seq = 1;
					}
					else {
						holduncollList.setLastRow();
						seq = Integer.parseInt(holduncollList.getRecord(0)) + 1;
					}
	 				EDD030001Message msgHU = (EDD030001Message)mc.getMessageRecord("EDD030001");
	 				userPO.setHeader19(seq + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(super.webAppPath + LangPath + "EDD0300_hold_uncollected_det_new.jsp?seq=" + seq);
					try {
						if (user.getE01DTF().equals("DMY")) {
							msgHU.setE01UNCRD1(user.getE01RDD());
							msgHU.setE01UNCRD2(user.getE01RDM());
							msgHU.setE01UNCRD3(user.getE01RDY());
						} else if (user.getE01DTF().equals("MDY")) {
							msgHU.setE01UNCRD1(user.getE01RDM());
							msgHU.setE01UNCRD2(user.getE01RDD());
							msgHU.setE01UNCRD3(user.getE01RDY());
						} else if (user.getE01DTF().equals("YMD")) {
							msgHU.setE01UNCRD1(user.getE01RDY());
							msgHU.setE01UNCRD2(user.getE01RDM());
							msgHU.setE01UNCRD3(user.getE01RDD());
						}
					} catch (Exception e) {
					}
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("huBasic", msgHU);
					res.sendRedirect(super.srctx + LangPath + "EDD0300_hold_uncollected.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "EDD0300_hold_uncollected_det.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0300_hold_uncollected.jsp?SEL=" + row);
					break;
				case 5 : // Print Balances
					userPO.setHeader20("DO_MAINT");
					boolean showBal = procReqBalance( mc, user, req, res, ses, row);
					if (showBal== true) {
						userPO.setHeader21(super.webAppPath + LangPath + "EDD0300_hold_uncollected_balances.jsp?ROW=" + row);
					} else {
						userPO.setHeader21("");
					}
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0300_hold_uncollected.jsp?SEL=" + row);
					break;
					
				case 3 : // Delete
					boolean IsNotError = true;
					try
					{
						msgHU = (EDD030001Message)mc.getMessageRecord("EDD030001");
					 	msgHU.setH01USERID(user.getH01USR());
					 	msgHU.setH01PROGRM("EDD0300");
					 	msgHU.setH01TIMSYS(getTimeStamp());
					 	msgHU.setH01SCRCOD("01");
					 	msgHU.setH01OPECOD("0009");
						try {
							msgHU.setE01UNCACC(userPO.getIdentifier());
						}
						catch (Exception e)	{
						}
						try {
							msgHU.setE01UNCCCY(userPO.getCurrency());
						}
						catch (Exception e)	{
						}
						try {
							msgHU.setE01UNCBNK(userPO.getHeader10());
						}
						catch (Exception e)	{
						}
						try {
							msgHU.setE01UNCBRN(userPO.getHeader11());
						}
						catch (Exception e)	{
						}
						try{
							holduncollList.setCurrentRow(row);
							msgHU.setE01UNCF01(Util.justifyRight(holduncollList.getRecord(0), 2));
						}
						catch (Exception e){
						}

						msgHU.send();	
					 	msgHU.destroy();
					}		
					catch (Exception e)	{
						e.printStackTrace();
						flexLog("Error: " + e);
					  	throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try
					{
					  	newmessage = mc.receiveMessage();
					  	if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage)newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);

							if (IsNotError) {  // There are no errors
								procReqHoldUncollList(mc, user, req, res, ses);
							}
							else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx + LangPath + "EDD0300_hold_uncollected.jsp?ROW=" + row);
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
					break;
  		}

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	
		// Send Initial data
	


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
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_rt.jsp");
		callPage(LangPath + "EDD0300_hold_uncollected_enter_rt.jsp", req, res);	
	}
	catch (Exception e) {
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
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_sv.jsp");
		callPage(LangPath + "EDD0300_hold_uncollected_enter_sv.jsp", req, res);	
	}
	catch (Exception e) {
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
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_cd.jsp");
		callPage(LangPath + "EDD0300_hold_uncollected_enter_cd.jsp", req, res);	
	}
	catch (Exception e) {
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
				
				if (userPO.getOption().equals("HOLD_UNCOLLECTED_RT")){
				 	flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_rt.jsp");
				 	callPage(LangPath + "EDD0300_hold_uncollected_enter_rt.jsp", req, res);
				} 
				else if(userPO.getOption().equals("HOLD_UNCOLLECTED_SV")){
					flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_sv.jsp");
				    callPage(LangPath + "EDD0300_hold_uncollected_enter_sv.jsp", req, res);
				}
				else {
					flexLog("About to call Page: " + LangPath + "EDD0300_hold_uncollected_enter_cd.jsp");
				    callPage(LangPath + "EDD0300_hold_uncollected_enter_cd.jsp", req, res);
				}
			}
			catch (Exception e) {
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

protected boolean procReqBalance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses, int row)			throws ServletException, IOException {

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
				return(true);
			}
			else {  // There are errors
				return(false);
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
	return(false);
}
}