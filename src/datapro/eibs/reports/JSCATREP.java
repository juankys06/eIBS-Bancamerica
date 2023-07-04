package datapro.eibs.reports;

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

public class JSCATREP extends datapro.eibs.master.SuperServlet {

static final int A_ENTER_REPORT			= 200;
static final int R_30_DAYS				= 4;
// CIF options
static final int R_ACC_BY_CUST 			= 1;
static final int R_ACC_FOR_CUST			= 2;
// entering options
static final int R_ENTER_REPORT			= 100;
static final int R_O_ENTER_REPORT		= 300;
static final int R_OFF_NEW_CUST			= 7;
static final int R_OFF_PORTFOLIO		= 6;
static final int R_OFF_REPORT			= 5;

private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSCATREP() {
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

		int screen = R_ENTER_REPORT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getValue("currUser");

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
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				return;
			}
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				case R_ENTER_REPORT :
				case R_O_ENTER_REPORT :
					procReqEnterReport(mc,msgUser, req, res, session);
					break;
				case A_ENTER_REPORT :
					procActionEnterReport(mc,msgUser, req, res, session);
					break;
				case R_ACC_BY_CUST :
					procReqShowAccountsByCustomer(mc, msgUser, req, res, session);
					break;
				case R_ACC_FOR_CUST :
					procReqShowAccountsForCustomer(mc, msgUser, req, res, session);
					break;
				case R_30_DAYS :
					procReqShow30DaysProjecteMaturity(mc, msgUser, req, res, session);
					break;
				case R_OFF_REPORT :
					procReqShowOfficerActivity(mc, msgUser, req, res, session);
					break;
				case R_OFF_PORTFOLIO :
					procReqShowOfficerPortfolioBreakdown(mc,msgUser, req, res, session);
					break;
				case R_OFF_NEW_CUST :
					procReqShowOfficerNewCustomerRel(mc,msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			try {
				s.close();
			}
			catch (Exception e) {
				flexLog("Error: " + e);
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		
	}

}
private synchronized void showERROR(ELEERRMessage m)
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
private synchronized void procActionEnterReport(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	DataCAT dataCAT = null;
	String fieldName = null;

	try
	{
		flexLog("Storing dataCAT");
		dataCAT = new DataCAT();

		try {
			dataCAT.setCusNum(req.getParameter("E01CUN"));
		}		
	    catch (Exception e){
	    }	
	    try { 
			dataCAT.setDate1(req.getParameter("DATE1"));
		}		
	    catch (Exception e){
	    }	
	    try { 
			dataCAT.setDate2(req.getParameter("DATE2"));
		}		
	    catch (Exception e){
	    }	
	    try { 
		 	dataCAT.setDate3(req.getParameter("DATE3"));
		}		
	    catch (Exception e){
	    }	

	 	// all the fields here
	 	java.util.Enumeration enu = req.getParameterNames();
		String value = null;
		while (enu.hasMoreElements()) {
			fieldName = (String)enu.nextElement();
			if (fieldName.length() == 8) {
				if (fieldName.charAt(0) == 'C') {
					try {
						value = fieldName.substring(2);
						dataCAT.addCostCenter(value);
					}
					catch (Exception e) {
					}	
				}
			}
					
		}

		ses.putValue("dataCAT", dataCAT);

	 	int opt = 0;
	 	 
		try { 
		 	opt = Integer.parseInt(req.getParameter("opt"));
		}		
	    catch (Exception e){
	    }	
		
	    switch (opt) {
			case R_ACC_BY_CUST :
				procReqShowAccountsByCustomer(mc, user, req, res, ses);
				break;
			case R_ACC_FOR_CUST :
				procReqShowAccountsForCustomer(mc, user, req, res, ses);
				break;
			case R_30_DAYS :
				procReqShow30DaysProjecteMaturity(mc, user, req, res, ses);
				break;
			case R_OFF_REPORT :
				procReqShowOfficerActivity(mc, user, req, res, ses);
				break;
			case R_OFF_PORTFOLIO :
				procReqShowOfficerPortfolioBreakdown(mc, user, req, res, ses);
				break;
			case R_OFF_NEW_CUST :
				procReqShowOfficerNewCustomerRel(mc, user, req, res, ses);
				break;
			default: {
			
			}
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
private synchronized void procReqEnterReport(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF099P1Message msgCAT = null;
	JBListRec beanGroupList = null;
	JBListRec beanSGroupList = null;
	JBListRec beanCCenterList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");

	// Send Initial data
	try
	{
		flexLog("Sending header");
		flexLog("header 1");
	 	msgCAT = (ECIF099P1Message)mc.getMessageRecord("ECIF099P1");
	 	msgCAT.setH01USERID(user.getH01USR());
	 	msgCAT.setH01PROGRM("ECIF099");
	 	msgCAT.setH01TIMSYS(getTimeStamp());
	 	msgCAT.setH01SCRCOD("01");
	 	msgCAT.setH01OPECOD("0002");

	 	msgCAT.send();	
	 	msgCAT.destroy();
	 	flexLog("ECIF099P1 Message Sent");
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
			
			try {
				flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				callPage(LangPath + "error_viewer.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF099P1")) {

			int colNum = 3;
			try {
				beanGroupList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanGroupList.init(colNum);
				beanSGroupList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanSGroupList.init(colNum);
				beanCCenterList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanCCenterList.init(colNum);
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			boolean newGrp = false;
			String marker = "";
			String myGFlag = "";
			String mySFlag = "";
			String myCFlag = "";

			int gCounter = -1;
			int sCounter = -1;

			String myGRow[] = new String[colNum];
			String mySRow[] = new String[colNum];
			String myCRow[] = new String[colNum];
			
			for (int i=0; i<colNum; i++) {
				myGRow[i] = "";
				mySRow[i] = "";
				myCRow[i] = "";
			}
			
			while (true) {

				msgCAT = (ECIF099P1Message)newmessage;

				marker = msgCAT.getH01FLGMAS();

				if (marker.equals("*")) {
					break;
				}
				else {
					if (!mySFlag.equals(msgCAT.getE01GROUPN())) {
						mySFlag = msgCAT.getE01GROUPN();
						newGrp = true;
						
						gCounter++;
						sCounter = -1;
						myGRow[0] = gCounter + "";
						myGRow[1] = msgCAT.getE01GROUPN();  //  Group Number
						myGRow[2] = msgCAT.getE01GRNAME();  //  Group Description
					
						beanGroupList.addRow(myGFlag, myGRow);
						// flexLog("New Group: " + mySFlag);
					}
					if (newGrp || !myCFlag.equals(msgCAT.getE01SGROUP())) {
						myCFlag = msgCAT.getE01SGROUP();
						newGrp = false;
						
						sCounter++;
						mySRow[0] = gCounter + "_" + sCounter;
						mySRow[1] = msgCAT.getE01SGROUP();  //  Sub Group Number
						mySRow[2] = msgCAT.getE01SGRNAM();  //  Sub Group Description
					
						beanSGroupList.addRow(mySFlag, mySRow);
						// flexLog("New SubGroup: " + mySFlag + " - " + myCFlag);
					}
					myCRow[0] = gCounter + "_" + sCounter; 
					myCRow[1] = Util.justifyRight(msgCAT.getE01CENCOS(), 6);  // Cost Center Number
					myCRow[2] = msgCAT.getE01CENNAM();  // Cost Center Description
					// flexLog("New Cost Center: " + mySFlag + " - " + myCFlag + " - " + myCRow[1]);

					beanCCenterList.addRow(mySFlag + "_" + myCFlag, myCRow);
					
				}

				newmessage = mc.receiveMessage();
			}


			flexLog("Putting java beans into the session");
			ses.putValue("error", msgError);
			ses.putValue("blGroup", beanGroupList);
			ses.putValue("blSGroup", beanSGroupList);
			ses.putValue("blCCenter", beanCCenterList);

			switch (Integer.parseInt(req.getParameter("SCREEN"))) {
				case R_ENTER_REPORT :
					try {
						flexLog("About to call Page: " + LangPath + "ECIF099_rp_enter.jsp");
						callPage(LangPath + "ECIF099_rp_enter.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				case R_O_ENTER_REPORT :
					try {
						flexLog("About to call Page: " + LangPath + "ECIF0991_rp_enter.jsp");
						callPage(LangPath + "ECIF0991_rp_enter.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
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
private synchronized void procReqShow30DaysProjecteMaturity(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL0230P1Message msgList = null;
	ELEERRMessage msgError = null;
	DataCAT dataCAT = null;
	JBList beanList = null;
	JBListRec beanListRec = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int col = 0;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");

	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");


	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EDL0230P1Message)mc.getMessageRecord("EDL0230P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("EDL0230");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
	 	
	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

	 	String Pos = req.getParameter("Pos");
	 	
	 	try{
	 		msgList.setH01OPECOD(req.getParameter("Pos"));
		}
	 	catch (Exception e) {
			msgList.setH01OPECOD("0");
			Pos = "0";
	 	}

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("EDL020301 Message Sent");
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
	   
	    if (newmessage.getFormatName().equals("EDL0230P1")) {
			int colNum = 8;
			try {
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				beanListRec = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanListRec.init(colNum);
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String chk = "";
			String marker = "";
			String myFlag = "";
			String myRow = "";
			String fmature ="";
			int daystomat = 0;
			
			String myRowT[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRowT[i] = "";
			}


			msgList = (EDL0230P1Message)newmessage;
			
			marker = msgList.getH01FLGMAS();

			while (true) {

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					myRowT[0] = marker;
					myRowT[4] = ""; // Total By Loan Counter
					myRowT[5] = msgList.getE01WTOTAL();  
					beanListRec.addRow("", myRowT);
 					beanList.addRow(marker, "");
					break;
				}
				else {
					if (marker.equals("+")) {
						beanList.setFirstRec(Integer.parseInt(msgList.getH01OPECOD()));
						beanList.setShowNext(true);
						break;
					}
					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader1(msgList.getE01DEAOFC().trim());
						userPO.setHeader2(msgList.getE01OFCNME().trim());
					}
					else {
						chk = "";
					}

					if (!marker.equals("-")) {

						daystomat =  Integer.parseInt(msgList.getE01MATDAY());

						if(daystomat < 5){
							fmature = "R";	
						}
						if(daystomat < 11 && daystomat > 5){
							fmature = "O";	
						}
						if(daystomat > 11){
							fmature = "N";
						}

						String term = "";

						if(msgList.getE01DEATRC().equals("D")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "DAYS";
						   } else {		
						     term = "DAYS";
						   }
						}
						else if(msgList.getE01DEATRC().equals("M")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "MONTH";
						   } else {		
						     term = "MONTHS";
						   }

						}
						else if(msgList.getE01DEATRC().equals("Y")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "YEAR";
						   } else {		
						     term = "YEARS";
						   }

						}
					
						myRow =  "<TR id=/trclear/>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEAOFC()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEAACC()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE01CUSSHN()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEARTE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01MATDAY()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.fcolorDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01MATDT1(), msgList.getE01MATDT2(), msgList.getE01MATDT3(),fmature) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01MATBAL()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEAEXR()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01MATBSE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEATYP()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEATRM()) + " - " + Util.formatCell(term) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01OPNDT1(), msgList.getE01OPNDT2(), msgList.getE01OPNDT3()) + "</TD>";
						myRow += "</TR>";

		 				beanList.addRow(myFlag, myRow);
					
					}
						
					newmessage = mc.receiveMessage();

					msgList = (EDL0230P1Message)newmessage;
					
					marker = msgList.getH01FLGMAS();

					if (marker.equals("-")){
						try{
							myRowT[0] = marker;
							myRowT[1] = Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01MATDT1(), msgList.getE01MATDT2(), msgList.getE01MATDT3());
							myRowT[2] = ""; // Total By Loan Counter
							myRowT[3] = msgList.getE01WTOTAL();  
							beanListRec.addRow("", myRowT);
		 					beanList.addRow(marker, "");
						}
		 				catch (Exception e) {
		 				}
					
					}
					
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}


				}

			}
			
			
			flexLog("Putting java beans into the session");
			ses.putValue("listRep", beanList);
			ses.putValue("totalsRep", beanListRec);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EDL0230_rp_30_days_proj_mat.jsp");
				callPage(LangPath + "EDL0230_rp_30_days_proj_mat.jsp", req, res);

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
private synchronized void procReqShowAccountsByCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF100P1Message msgList = null;
	ELEERRMessage msgError = null;
	DataCAT dataCAT = null;
	JBList beanList = null;
	JBListRec beanListRec = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int col = 0;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");

	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF100P1Message)mc.getMessageRecord("ECIF100P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF100");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
		
	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

	 	String Pos = req.getParameter("Pos");
	 	
	 	try{
	 		msgList.setH01OPECOD(req.getParameter("Pos"));
		}
	 	catch (Exception e) {
			msgList.setH01OPECOD("0");
			Pos = "0";
	 	}

	 	if(!Pos.equals("0")){ 	
	 		msgList.setE01OFCCDE(userPO.getHeader1());
	 	}
 	
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF100P1 Message Sent");
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
	   
	    if (newmessage.getFormatName().equals("ECIF100P1")) {
			int colNum = 8;
			try {
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				beanListRec = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanListRec.init(colNum);
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String chk = "";
			String marker = "";
			String myFlag = "";
			String myRow = "";
			int total = 0;
			
			String myRowT[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRowT[i] = "";
			}

			msgList = (ECIF100P1Message)newmessage;
			marker = msgList.getH01FLGMAS();

			while (true) {

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader1(msgList.getE01OFCCDE().trim());
						userPO.setHeader2(msgList.getE01OFCNME().trim());
						beanList.setFirstRec(Integer.parseInt(msgList.getH01OPECOD()));
					}
					else {
						chk = "";
					}
					
					if (!marker.equals("-")) {

						String term = "";

						if(msgList.getE01DEATRC().equals("D")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "DAYS";
						   } else {		
						     term = "DAYS";
						   }
						}
						else if(msgList.getE01DEATRC().equals("M")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "MONTH";
						   } else {		
						     term = "MONTHS";
						   }

						}
						else if(msgList.getE01DEATRC().equals("Y")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "YEAR";
						   } else {		
						     term = "YEARS";
						   }

						}
						
						myRow =  "<TR>";
						
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01LOANYN()) + "</TD>";			
		 				myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCCDE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEAACC()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OLDRSP()) + "</TD>";
						if(msgList.getE01DEATRM().trim().equals("0")){
							myRow += "<TD NOWRAP ALIGN=CENTER>" +  "&nbsp;" + "</TD>";
						}
						else{
							myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEATRM()) + " - " + Util.formatCell(term) + "</TD>";
						}
						myRow += "<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE01CUSSHN()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01CUSTOM()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BALANC()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01EXCRTE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BALBSE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01INTRTE()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(), msgList.getE01MATDT1(), msgList.getE01MATDT2(), msgList.getE01MATDT3()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01OPNDT1(), msgList.getE01OPNDT2(), msgList.getE01OPNDT3()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01INIDT1(), msgList.getE01INIDT2(), msgList.getE01INIDT3()) + "</TD>";
						myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01PRODUC()) + "</TD>";
						myRow += "</TR>";

						
		 				try {
							myFlag = total + "";
						}
		 				catch (Exception e) {
		 				}
						
		 				beanList.addRow(myFlag, myRow);
						
	 				}
					
					newmessage = mc.receiveMessage();

					msgList = (ECIF100P1Message)newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("-")){
						try{
							myRowT[0] = marker;
							myRowT[1] = msgList.getE01WTOTAL();
							myRowT[2] = "";  // Total By Loan Counter
							myRowT[3] = msgList.getE01LOANYN();
							beanListRec.addRow("", myRowT);
							total++;
		 					beanList.addRow(marker, "");
						}
		 				catch (Exception e) {
		 				}
					
					}
					
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}


				}

			}
			
			
			flexLog("Putting java beans into the session");
			ses.putValue("listRep", beanList);
			ses.putValue("totalsRep", beanListRec);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF0100P_rp_sum_by_cust.jsp");
				callPage(LangPath + "ECIF0100P_rp_sum_by_cust.jsp", req, res);

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
private synchronized void procReqShowAccountsForCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF100P1Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	JBListRec beanListRec = null;
	UserPos	userPO = null;
	DataCAT dataCAT = null;
	boolean IsNotError = false;
	int col = 0;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");
	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF100P1Message)mc.getMessageRecord("ECIF100P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF100");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
	 	try {
	 		msgList.setE01CUSTOM(dataCAT.getCusNum());
	 	}
	 	catch (Exception e) {
	 	}
	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

	 	try{
	 		msgList.setH01OPECOD(req.getParameter("Pos"));
		}
	 	catch (Exception e) {
			msgList.setH01OPECOD("0");
	 	}
	 	

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF100P1 Message Sent");
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
	   
	    if (newmessage.getFormatName().equals("ECIF100P1")) {
			int colNum = 8;
			try {
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				beanListRec = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanListRec.init(colNum);
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String chk = "";
			String marker = "";
			String myFlag = "";
			String myRow = "";
			java.math.BigDecimal totalByLoan = new java.math.BigDecimal(0);
			java.math.BigDecimal totalFinal = new java.math.BigDecimal(0);
			int totalByLoanCounter = 0;
			int total = 0;
			String loan = "";
			
			String myRowT[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRowT[i] = "";
			}

			msgList = (ECIF100P1Message)newmessage;
			marker = msgList.getH01FLGMAS();

			while (true) {

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						loan = msgList.getE01LOANYN();
						userPO.setHeader1(msgList.getE01OFCCDE().trim());
						userPO.setHeader2(msgList.getE01OFCNME().trim());
						userPO.setHeader3(msgList.getE01CUSTOM().trim());
						beanList.setFirstRec(Integer.parseInt(msgList.getH01OPECOD()));
					}
					else {
						chk = "";
					}
					if (!marker.equals("-")) {
						
						String term = "";

						if(msgList.getE01DEATRC().equals("D")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "DAYS";
						   } else {		
						     term = "DAYS";
						   }
						}
						else if(msgList.getE01DEATRC().equals("M")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "MONTH";
						   } else {		
						     term = "MONTHS";
						   }

						}
						else if(msgList.getE01DEATRC().equals("Y")){
						   if(msgList.getE01DEATRM().trim().equals("1")){					
						     term = "YEAR";
						   } else {		
						     term = "YEARS";
						   }

						}
					
					
					 myRow =  "<TR>";
					
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01LOANYN()) + "</TD>";			
	 				 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCCDE()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEAACC()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OLDRSP()) + "</TD>";
					 if(msgList.getE01DEATRM().trim().equals("0")){
					 myRow += "<TD NOWRAP ALIGN=CENTER>" +  "&nbsp;" + "</TD>";
					 }
					 else{
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01DEATRM()) + " - " + Util.formatCell(term) + "</TD>";
					 }
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01CUSSHN()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BALANC()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01EXCRTE()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BALBSE()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01INTRTE()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01MATDT1(), msgList.getE01MATDT2(), msgList.getE01MATDT3()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01OPNDT1(), msgList.getE01OPNDT2(), msgList.getE01OPNDT3()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01INIDT1(), msgList.getE01INIDT2(), msgList.getE01INIDT3()) + "</TD>";
					 myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01PRODUC()) + "</TD>";
					 myRow += "</TR>";

					
	 				 try {
					  myFlag = total + "";
					 }
	 				 catch (Exception e) {
	 				 }
					 beanList.addRow(myFlag, myRow);
					
					
					 try{	
					  totalByLoan = totalByLoan.add(msgList.getBigDecimalE01BALBSE());
					 }
	 				  catch (Exception e) {
	 				 }
	 				 try {
					  totalByLoanCounter ++;
					 }
	 				 catch (Exception e) {
	 				 }
	 				 
	 				}
	 				
					newmessage = mc.receiveMessage();

					msgList = (ECIF100P1Message)newmessage;

					marker = msgList.getH01FLGMAS();
					
					if (marker.equals("*") || marker.equals("+")) {
						myRowT[0] = "*";
						myRowT[1] = totalByLoan.toString();
						myRowT[2] = totalByLoanCounter + "";
						myRowT[3] = loan;
						beanListRec.addRow("", myRowT);
						
					}
					else if (!(loan.equals(msgList.getE01LOANYN()))) {
						myRowT[0] = "";
						myRowT[1] = totalByLoan.toString();
						myRowT[2] = totalByLoanCounter + "";
						myRowT[3] = loan;
						beanListRec.addRow("", myRowT);
						totalByLoan = new java.math.BigDecimal(0);
						totalByLoanCounter = 0;
						total++;
						loan = msgList.getE01LOANYN();
					}
	 				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}


				}

			}
			
			
			flexLog("Putting java beans into the session");
			ses.putValue("listRep", beanList);
			ses.putValue("totalsRep", beanListRec);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF0100P_rp_sum_for_cust.jsp");
				callPage(LangPath + "ECIF0100P_rp_sum_for_cust.jsp", req, res);

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
private synchronized void procReqShowOfficerActivity(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF110P1Message msgList = null;
	ELEERRMessage msgError = null;
	DataCAT dataCAT = null;
	JBList beanList = null;
	JBListRec beanListRec = null;	
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int col = 0;
	int Pos = 0;
	String date1="";
	String date2="";
	String date3="";
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");
	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF110P1Message)mc.getMessageRecord("ECIF110P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF110");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");

	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

		date1 = dataCAT.getDate1();
		date2 = dataCAT.getDate2();
		date3 = dataCAT.getDate3(); 
		
	 	try {
	 		msgList.setE01POSDT1(Util.justifyLeft(date1,2));
	 	}
	 	catch (Exception e) {
	 	}
		try {
	 		msgList.setE01POSDT2(Util.justifyLeft(date2,2));
	 	}
	 	catch (Exception e) {
	 	}
	 	try {
	 		msgList.setE01POSDT3(Util.justifyLeft(date3,2));
	 	}
	 	catch (Exception e) {
	 	}

	 	try{
	 		Pos = Integer.parseInt(req.getParameter("Pos"));
		 	msgList.setH01OPECOD(Pos + "");
		}
	 	catch (Exception e) {
		 	Pos=0;
		 	msgList.setH01OPECOD("0");
	 	}
	 	if(Pos>0){ 	
	 		msgList.setE01OFCCDE(userPO.getHeader1());
	 	}

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF110P1 Message Sent");
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
	   
	    if (newmessage.getFormatName().equals("ECIF110P1")) {
   			int colNum = 8;
			try {
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				beanListRec = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				beanListRec.init(colNum);

		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow = "";
			
			String myRowT[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRowT[i] = "";
			}			
						
			while (true) {

				msgList = (ECIF110P1Message)newmessage;
				
				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);				
					break;
				}
				else {
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}

				}					
				if ( firstTime ) {
					firstTime = false;
					userPO.setHeader1(msgList.getE01OFCCDE().trim());
					//userPO.setHeader2(Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01POSDT1().trim(),msgList.getE01POSDT2().trim(),msgList.getE01POSDT3().trim()));
					beanList.setFirstRec(Integer.parseInt(msgList.getH01OPECOD()));
					
				}

				if (!marker.equals("-")) {
				
					myRow =  "<TR>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCCDE()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAACC()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE01CUSSHN()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01POSDT1(), msgList.getE01POSDT2(), msgList.getE01POSDT3()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DEPPTD()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAREF()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DEPUNC()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgList.getE01VALDT1(), msgList.getE01VALDT2(), msgList.getE01VALDT3()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DEPVAL()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01WTWVAL()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01NETVAL()) + "</TD>";				
					myRow += "</TR>";

	 				beanList.addRow(myFlag, myRow);
					
 				}

				newmessage = mc.receiveMessage();

				msgList = (ECIF110P1Message)newmessage;

				marker = msgList.getH01FLGMAS();
				
				if (marker.equals("-")){
					try{
						myRowT[1] = msgList.getE01WTOT01();
						myRowT[2] = msgList.getE01WTOT02();
						myRowT[3] = msgList.getE01WTOT03();
						myRowT[4] = msgList.getE01WTOT04();
						myRowT[5] = msgList.getE01WTOT05();
						beanListRec.addRow("", myRowT);
	 					beanList.addRow(marker, "");											
					}
	 				catch (Exception e) {
	 				}				
				}
			}
			
			userPO.setHeader2(Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),Util.justifyLeft(date2,2),Util.justifyLeft(date1,2),Util.justifyLeft(date3,2)));
					
			flexLog("Putting java beans into the session");
			ses.putValue("listRep", beanList);
			ses.putValue("totalsOff", beanListRec);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF110P_rp_officer_activity.jsp");
				callPage(LangPath + "ECIF110P_rp_officer_activity.jsp", req, res);

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
private synchronized void procReqShowOfficerNewCustomerRel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF120P1Message msgList = null;
	ELEERRMessage msgError = null;
	DataCAT dataCAT = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int col = 0;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");
	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF120P1Message)mc.getMessageRecord("ECIF120P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF110");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");

	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

		String date1="";
		String date2="";
		String date3="";


		date1 = dataCAT.getDate1();
		date2 = dataCAT.getDate2();
		date3 = dataCAT.getDate3();  
	 	
	 	try {
	 		msgList.setE01STRDT1(Util.justifyLeft(date1,2));
	 	}
	 	catch (Exception e) {
	 	}
		try {
	 		msgList.setE01STRDT2(Util.justifyLeft(date2,2));
	 	}
	 	catch (Exception e) {
	 	}
	 	try {
	 		msgList.setE01STRDT3(Util.justifyLeft(date3,2));
	 	}
	 	catch (Exception e) {
	 	}
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF120P1 Message Sent");
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
	   
	    if (newmessage.getFormatName().equals("ECIF120P1")) {
			try {
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String chk = "";
			String marker = "";
			String myFlag = "";
			String myRow = "";
			java.math.BigDecimal totalPrevMonth = new java.math.BigDecimal(0);
			java.math.BigDecimal totalCurrMonth = new java.math.BigDecimal(0);
			java.math.BigDecimal totalPrevYear = new java.math.BigDecimal(0);
			java.math.BigDecimal totalYTD = new java.math.BigDecimal(0);
			java.math.BigDecimal counterPrevMonth = new java.math.BigDecimal(0);
			java.math.BigDecimal counterCurrMonth = new java.math.BigDecimal(0);
			java.math.BigDecimal counterPrevYear = new java.math.BigDecimal(0);
			java.math.BigDecimal counterYTD = new java.math.BigDecimal(0);
						
			while (true) {

				msgList = (ECIF120P1Message)newmessage;
				
				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader1(Util.formatDate(msgList.getE01STRDT1().trim(),msgList.getE01STRDT2().trim(),msgList.getE01STRDT3().trim()));
						
					}
					else {
						chk = "";
					}
					
					myRow =  "<TR>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCCDE()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCNME()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNTPM()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNTCM()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNTPY()) + "</TD>";
					myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNTYT()) + "</TD>";
					
					myRow += "</TR>";

					beanList.addRow(myFlag, myRow);

					totalPrevMonth = totalPrevMonth.add(msgList.getBigDecimalE01AMNTPM());
					totalCurrMonth = totalCurrMonth.add(msgList.getBigDecimalE01AMNTCM());
					totalPrevYear  = totalPrevYear.add(msgList.getBigDecimalE01AMNTPY());
					totalYTD	   = totalYTD.add(msgList.getBigDecimalE01AMNTYT());
					counterPrevMonth = counterPrevMonth.add(msgList.getBigDecimalE01CONTPM());
					counterCurrMonth = counterCurrMonth.add(msgList.getBigDecimalE01CONTCM());
					counterPrevYear  = counterPrevYear.add(msgList.getBigDecimalE01CONTPY());
					counterYTD       = counterYTD.add(msgList.getBigDecimalE01CONTYT());
						
					newmessage = mc.receiveMessage();

				}

			}
			
			userPO.setHeader2(Util.fcolorCCY(totalPrevMonth.toString()));
	        userPO.setHeader3(Util.fcolorCCY(totalCurrMonth.toString()));
		    userPO.setHeader4(Util.fcolorCCY(totalPrevYear.toString()));
			userPO.setHeader5(Util.fcolorCCY(totalYTD.toString()));
			userPO.setHeader6(counterPrevMonth.toString());
			userPO.setHeader7(counterCurrMonth.toString());
			userPO.setHeader8(counterPrevYear.toString());
			userPO.setHeader9(counterYTD.toString());
						
			flexLog("Putting java beans into the session");
			ses.putValue("listRep", beanList);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF120P_rp_officer_new_customer.jsp");
				callPage(LangPath + "ECIF120P_rp_officer_new_customer.jsp", req, res);

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
private synchronized void procReqShowOfficerPortfolioBreakdown(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF130P1Message msgList = null;
	ELEERRMessage msgError = null;
	DataCAT dataCAT = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	int col = 0;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getValue("userPO");
	dataCAT = (datapro.eibs.beans.DataCAT)ses.getValue("dataCAT");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF130P1Message)mc.getMessageRecord("ECIF130P1");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF100");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
	 	try{
	 		msgList.setE01CCAUTH(dataCAT.getStrCostCenter().toString());
		}
	 	catch (Exception e) {
	 	}

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF130P1 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ECIF130P1")) {

		try {
			beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
	  	} 
		catch (Exception ex) {
			flexLog("ECIF130P1 Error: " + ex); 
	  	}

		boolean firstTime = true;
		String marker = "";
		String myFlag = "";
		String myRow = "";
		String chk = "";
		String officer ="";
		
		while (true) {

			msgList = (ECIF130P1Message)newmessage;

			marker = msgList.getH01FLGMAS();

			if (marker.equals("*")) {
				beanList.setShowNext(false);
				break;
			}
			else {
				if (firstTime) {
					firstTime = false;
				}

				myRow =  "<TR>";
				myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCCDE()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01OFCNME()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01NETPOR()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT01()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT02()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT03()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT04()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT05()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT06()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT07()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT08()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT09()) + "</TD>";
	   			myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT10()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT11()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT12()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT13()) + "</TD>";
	   			myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT14()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT15()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DEPBAL()) + "</TD>";
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01DEPPCT()) + "</TD>";				
				myRow += "<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01AMNT16()) + "</TD>";				
				myRow += "</TR>";
				beanList.addRow(myFlag, myRow);
				
								
				if (marker.equals("+")) {
					beanList.setShowNext(true);
					break;
				}
			}

				newmessage = mc.receiveMessage();
				msgList = (ECIF130P1Message)newmessage;

		}
		
		
			flexLog("Putting java beans into the session");
			ses.putValue("listOPB", beanList);
			ses.putValue("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF0130_rp_officer_portfolio.jsp");
				callPage(LangPath + "ECIF0130_rp_officer_portfolio.jsp", req, res);

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