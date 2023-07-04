package datapro.eibs.client;

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

public class JSERA0010 extends datapro.eibs.master.SuperServlet {

	protected static final int A_GA_BASIC 			= 2;
	protected static final int A_GA_DETAIL 			= 4;
	protected static final int A_GA_NONE_ACC 		= 8;
	protected static final int A_SPECIAL_COD 		= 10;
	protected static final int A_INST_SPECIAL		= 12;
	protected static final int A_TR_COLL          	= 14;
	protected static final int A_GA_INS_POLICY    	= 16;
	protected static final int A_ENTER_INS 			= 1200;
	protected static final int A_DELETE_INS 		= 1400;

	// options
	protected static final int R_GA_BASIC 			= 1;
	protected static final int R_GA_DETAIL 			= 3;
	protected static final int R_GA_NONE_ACC 		= 7;
	protected static final int R_SPECIAL_COD 		= 9;
	protected static final int R_INST_SPECIAL		= 11;
	protected static final int R_GA_INS_POLICY    	= 15;
	protected static final int R_ENTER_NEW 			= 300;
	protected static final int R_ENTER_MAINT		= 500;
	protected static final int R_ENTER_NEW_INS 		= 1100;
	protected static final int R_ENTER_MAINT_INS	= 1300;
	protected static final int R_ENTER_DELETE_INS	= 1500;

	// entering options
	protected static final int R_ENTER 				= 100;
	protected static final int A_ENTER 				= 200;

	protected static final int A_ENTER_NEW 			= 400;
	protected static final int A_DELETE 			= 600;
	protected static final int A_DELETE_DETAIL 		= 800;

	protected String LangPath = "S";
/**
 * JSECLI001 constructor comment.
 */
public JSERA0010() {
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

		int screen = R_ENTER;
		
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
				// Request
				case R_GA_BASIC :
					procReqCollBasic(mc, msgUser, req, res, session);
					break;
				case R_GA_DETAIL : 
					procReqCollDetail(mc, msgUser, req, res, session);
					break;
				case R_GA_NONE_ACC :
					procReqListNoneAccColl(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_COD :
					procReqCodes(mc, msgUser, req, res, session);
					break;					
				//entering options
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				case R_ENTER_NEW : 
					procReqEnterNew(mc, msgUser, req, res, session);
					break;
				case R_ENTER_MAINT : 
					procReqEnterMaint(mc, msgUser, req, res, session);
					break;
				case R_ENTER_NEW_INS : 
					procReqEnterNewIns(mc, msgUser, req, res, session);
					break;
				case R_ENTER_MAINT_INS : 
					procReqEnterMaintIns(mc, msgUser, req, res, session);
					break;
				case R_ENTER_DELETE_INS : 
					procReqEnterDeleteIns(mc, msgUser, req, res, session);
					break;
				case A_DELETE_INS : 
					procActionDeleteIns(mc, msgUser, req, res, session);
					break;
				case R_INST_SPECIAL:
				    procReqEspInst(mc, msgUser, req, res, session);
					break;					
				// action options
				case A_GA_BASIC:
				    procActionCollBasic(mc,msgUser, req, res, session);
					break;				
				case A_ENTER_NEW:
				    procActionEnterNew(mc,msgUser, req, res, session);
					break;					
				case A_ENTER_INS:
					procActionEnterIns(mc,msgUser, req, res, session);
					break;					
				case A_ENTER :
				case A_GA_NONE_ACC :
					procActionEnter(mc, msgUser, req, res, session);
					break;
				case A_DELETE:
				    procActionDelete(mc, msgUser, req, res, session);
					break;
				case A_DELETE_DETAIL:
					procActionDeleteDetail(mc, msgUser, req, res, session);
					break;					
				case A_TR_COLL:
				    procActionAddDetail(mc,msgUser, req, res, session);
				    break;
				case A_SPECIAL_COD:
				    procActionCodes(mc, msgUser, req, res, session);
				  	break;
				case A_INST_SPECIAL:
				    procActionEspInst(mc, msgUser, req, res, session);
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
 */
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_enter_maint.jsp");
		callPage(LangPath + "ERA0010_ga_enter_maint.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqListNoneAccColl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010004Message msgList = null;
	ELEERRMessage msgError = null;
	JBObjList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	userPO.setPurpose("MAINTENANCE");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA010004Message)mc.getMessageRecord("ERA010004");
		msgList.setH04USERID(user.getH01USR());
	 	msgList.setH04PROGRM("ERA0100");
	 	msgList.setH04TIMSYS(getTimeStamp());
	 	msgList.setH04SCRCOD("01");
	 	msgList.setH04OPECOD("0004");
		try {
	 		msgList.setE04ROCCUN(userPO.getCusNum());
	 	}
	 	catch (Exception e) {
	 	}
	 	msgList.send();	
	 	msgList.destroy();
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

		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page: " + LangPath + "ERA0010_ga_enter_maint.jsp");
				callPage(LangPath + "ERA0010_ga_enter_maint.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else if (newmessage.getFormatName().equals("ERA010004")) {
			try {
				beanList = new datapro.eibs.beans.JBObjList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String ref = "";
			String client = "";
			String chk = "";
			String refNum = msgList.getE04ROCREF();
			java.math.BigDecimal totalGross = new java.math.BigDecimal(0);
			java.math.BigDecimal totalUsed = new java.math.BigDecimal(0);
			java.math.BigDecimal totalAvailable = new java.math.BigDecimal(0);

			if (refNum == "0") 
				firstTime = true;
			else				
				firstTime = false;
			
			while (true) {

				msgList = (ERA010004Message)newmessage;

				marker = msgList.getE04ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					userPO.setCusNum(msgList.getE04ROCCUN().trim());
					userPO.setCusName(msgList.getE04CUSNA1().trim());
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						userPO.setCusNum(msgList.getE04ROCCUN().trim());
						userPO.setCusName(msgList.getE04CUSNA1().trim());
						userPO.setBank(msgList.getE04ROCBNK());
					}
					else {
						if (msgList.getE04ROCREF().trim().equals(refNum)){
							userPO.setCusNum(msgList.getE04ROCCUN().trim());
							userPO.setCusName(msgList.getE04CUSNA1().trim());
							userPO.setBank(msgList.getE04ROCBNK());
						    chk = "checked";
						}    
						else 
							chk = "";
					}

					myFlag = "";
					totalGross = totalGross.add(msgList.getBigDecimalE04ROCNBL());
					totalUsed = totalUsed.add(msgList.getBigDecimalE04ROCCOP());
					totalAvailable = totalAvailable.add(msgList.getBigDecimalE04DISPON());
					
					ref = msgList.getE04ROCREF();
					client = msgList.getE04ROCCUN();
					/**myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\"" + ref + "\" " + chk + "></TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(ref) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04ROCTYP()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE04ROCMT1(), msgList.getE04ROCMT2(), msgList.getE04ROCMT3()) + "</A></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04ROCCCY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04ROCNBL()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04ROCCOP()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04DISPON()) + "</TD>");
					myRow.append("</TR>");*/
					beanList.addRow(msgList);
									
				}

				newmessage = mc.receiveMessage();
			}
			
			userPO.setHeader3(Util.fcolorCCY(totalGross.toString()));
			userPO.setHeader4(Util.fcolorCCY(totalUsed.toString()));
			userPO.setHeader5(Util.fcolorCCY(totalAvailable.toString()));
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("noneCollList", beanList);
			ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_acc_list.jsp");
					callPage(LangPath + "ERA0010_ga_acc_list.jsp", req, res);

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
protected void procReqCollBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001001Message msgGa = null;
	ERA001003Message msgList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	JBObjList beanList = null;
	boolean IsNotError = false;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0002";
	
	// Send Initial data
	try
	{
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
	 	msgGa.setH01USERID(user.getH01USR());
	 	msgGa.setH01PROGRM("ERA0010");
	 	msgGa.setH01TIMSYS(getTimeStamp());
	 	msgGa.setH01SCRCOD("01");
	 	msgGa.setH01OPECOD(opCode);
	 	try {
	 		msgGa.setE01ROCCUN(userPO.getCusNum());
	 	}
	 	catch (Exception e) {
	 	}
	 	try {
	 		msgGa.setE01ROCREF(userPO.getIdentifier());
	 	}
	 	catch (Exception e) {
	 	}
	 	msgGa.send();	
	 	msgGa.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	try
	{
		msgList = (ERA001003Message)mc.getMessageRecord("ERA001003");
		msgList.setH03USERID(user.getH01USR());
		msgList.setH03PROGRM("ERA0010");
		msgList.setH03TIMSYS(getTimeStamp());
		msgList.setH03SCRCOD("01");
		msgList.setH03OPECOD("0015");
		try {
			msgList.setE03ROCCUN(userPO.getCusNum());
		}
		catch (Exception e) {
		}
		try {
			msgList.setE03ROCREF(userPO.getIdentifier());
		}
		catch (Exception e) {
		}
		msgList.send();	
		msgList.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try	{
	 	 newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
	} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
  		throw new RuntimeException("Socket Communication Error");
	}	
 
	// Receive Data
	try
		{
	    newmessage = mc.receiveMessage();
	
		 if (newmessage.getFormatName().equals("ERA001001")) {
			try {
				msgGa = new datapro.eibs.beans.ERA001001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGa = (ERA001001Message)newmessage;
			userPO.setPurpose("MAINTENANCE");
			userPO.setCusName(msgGa.getE01CUSNA1());
			userPO.setCusNum(msgGa.getE01ROCCUN());
			userPO.setBank(msgGa.getE01ROCBNK());
			userPO.setBranch(msgGa.getE01ROCBRN());
			userPO.setCurrency(msgGa.getE01ROCCCY());
			userPO.setGenLedger(msgGa.getE01ROCGLN());
			userPO.setType(msgGa.getE01ROCTYP());
			//Set collateral description on header1
			userPO.setHeader1(msgGa.getE01ROCDES());
			
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("gaMant", msgGa);

		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

	// Receive List
	try
	{
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ERA001003")) {
			try {
				beanList = new datapro.eibs.beans.JBObjList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String ref = "";
			String client = "";
			String chk = "";
			String seq = "0";

			if (seq.equals("0")) 
				firstTime = true;
			else				
				firstTime = false;
	
			while (true) {

				msgList = (ERA001003Message)newmessage;

				marker = msgList.getH03FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else 
				{
					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
					}
					else {
						if (msgList.getE03ROCSEQ().trim().equals(seq)){
							chk = "checked";
						}    
						else 
							chk = "";
					}

					myFlag = "";
			
					seq = msgList.getE03ROCSEQ();
					beanList.addRow(msgList);
				}
				newmessage = mc.receiveMessage();
			}
			//Set max seq at header2
			userPO.setHeader2(seq);
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("insList", beanList);
			ses.setAttribute("error", msgError);
		}
		else 
			flexLog("Message " + newmessage.getFormatName() + " received.");
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	

	if (IsNotError) {  // There are no errors
		try {
			flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
			callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqCollDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001002Message msgColl = null;
	JBListRec trList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgColl = (ERA001002Message)mc.getMessageRecord("ERA001002");
	 	msgColl.setH02USERID(user.getH01USR());
	 	msgColl.setH02PROGRM("ERA0010");
	 	msgColl.setH02TIMSYS(getTimeStamp());
	 	msgColl.setH02SCRCOD("01");
	 	msgColl.setH02OPECOD("0006");

	 	// Get Parameters here	
	
	try	{ 	
	 	msgColl.setE02RODREF(userPO.getIdentifier());
	}
	catch (Exception e)	{
	} 
	try	{ 	
	 	msgColl.setE02RODBNK(userPO.getBank());
	}
	catch (Exception e)	{
	} 
	try	{ 	
	 	msgColl.setE02RODCUN(userPO.getCusNum());
	}
	catch (Exception e)	{
	} 		
	
	msgColl.send();	
	msgColl.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	

	int colNum = 7;

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		trList = new datapro.eibs.beans.JBListRec();
		trList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}


	// Receive Data
	try	{

		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ERA001002")) {

			char sel = ' ';
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			boolean myFirstRow = false;
			
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			
			while (true) {

				msgColl = (ERA001002Message)newmessage;

				marker = msgColl.getH02FLGMAS();

				if (marker.equals("*")) {
					break;
				}
				else {

					
					myRow[0] =  msgColl.getE02RODSEQ();  // Sec Number
					myRow[1] =  msgColl.getE02RODNBR();  // Register Code
					myRow[2] =  msgColl.getE02RODDSC();  // Description
					myRow[3] =  msgColl.getE02RODAMT();  // Amount
					myRow[4] =  msgColl.getE02RODMD1();  // Maturity date MM
					myRow[5] =  msgColl.getE02RODMD2();  // Maturity date DD
					myRow[6] =  msgColl.getE02RODMD3();  // Maturity date YY
				
					
					trList.addRow(myFlag, myRow);

									
				}

				newmessage = mc.receiveMessage();

			}

			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("collList", trList);
						
			try {
				 flexLog("About to call Page: " + LangPath + "ERA0010_ga_list_detail.jsp");
				 callPage(LangPath + "ERA0010_ga_list_detail.jsp", req, res);					
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
protected void procReqEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	ERA001001Message msgGa = null;	
	
	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");

		JBObjList beanList = new datapro.eibs.beans.JBObjList();
		beanList.setNoResult(true);
		
		try{
			msgGa.setE01ROCCUN(userPO.getCusNum());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
  		}
		try{
			msgGa.setE01CUSNA1(userPO.getCusName());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
  		}
		try{
			msgGa.setE01ROCBNK(userPO.getBank());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
  		}

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("gaMant", msgGa);
		ses.setAttribute("insList", beanList);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
		callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procReqEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;
	ERA010004Message msgList = null;
	JBObjList beanList = null;
	
	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("noneCollList");
		int row =0;
		try{
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch(Exception ex){
			row = 0;
		}
		
		//Set row number at header 3
		userPO.setHeader3(row + "");
				
		beanList.setCurrentRow(row);
		msgList = (ERA010004Message) beanList.getRecord();
		
		//msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
		try{
			userPO.setIdentifier(msgList.getE04ROCREF());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
  		}

		//ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		procReqCollBasic(mc, user, req, res, ses);
		
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgGA = null;
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

	String opCode = "0002";

	// Send Initial data
	try
	{
		msgGA = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgGA.setH05USR(user.getH01USR());
	 	msgGA.setH05PGM("ERA0010");
	 	msgGA.setH05TIM(getTimeStamp());
	 	msgGA.setH05SCR("01");
	 	msgGA.setH05OPE(opCode);
	 	msgGA.setE05ACC(userPO.getIdentifier());
	 	msgGA.setE05TYP("C");
	 	msgGA.setH05WK1("M");
	 	msgGA.send();	
	 	msgGA.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgGA = new datapro.eibs.beans.ESD000005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ESD000005Message)newmessage;
			// showESD008004(msgGA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("gaInst", msgGA);

			if (IsNotError) {  // There are no errors 
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_special_inst.jsp");
					callPage(LangPath + "ERA0010_ga_special_inst.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
					callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);	
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
protected void procReqCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgGa = null;
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

	String opCode = "";
	if (userPO.getPurpose().equals("MAINTENANCE")) 
		opCode = "0002";
	else 
	 opCode = "0004";
	
	// Send Initial data
	try
	{
		msgGa = (ESD000002Message)mc.getMessageRecord("ESD000002");
	 	msgGa.setH02USR(user.getH01USR());
	 	msgGa.setH02PGM("ERA0010");
	 	msgGa.setH02TIM(getTimeStamp());
	 	msgGa.setH02SCR("01");
	 	msgGa.setH02OPE(opCode);
	 	msgGa.setE02ACC(userPO.getIdentifier());
	 	msgGa.setH02WK1("M");
	 	msgGa.send();	
	 	msgGa.destroy();
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

		if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgGa = new datapro.eibs.beans.ESD000002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGa = (ESD000002Message)newmessage;
			// showESD008004(msgGa);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("gaCodes", msgGa);

			if (IsNotError) {  // There are no errors
				try {
					
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_codes.jsp");
						callPage(LangPath + "ERA0010_ga_codes.jsp", req, res);
					}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_codes.jsp");
					callPage(LangPath + "ERA0010_ga_codes.jsp", req, res);	
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
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	


	try
	{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	 	try {
	 		userPO.setCusNum(req.getParameter("CUSNUM").trim());
	 	}
	 	catch (Exception e) {
		 	userPO.setCusNum("0");
	 	}
	 	try {
	 		userPO.setIdentifier(req.getParameter("REF").trim());
	 	}
	 	catch (Exception e) {
		 	userPO.setIdentifier("");
	 	}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		if (userPO.getIdentifier().equals("")) {
		 	procReqListNoneAccColl(mc, user, req, res, ses);
	 	}
	 	else {
		 	procReqCollBasic(mc, user, req, res, ses);
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
protected void procActionAddDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	JBListRec trList = null;
	int colNum = 7;


	try {
		trList = new datapro.eibs.beans.JBListRec();
		trList.init(colNum);
		
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	int maxRow = 15;
	int realMaxRow = maxRow;
	try {
		maxRow = Integer.parseInt(req.getParameter("ROW")) - Integer.parseInt(req.getParameter("TEMP_ROW"));
		realMaxRow = Integer.parseInt(req.getParameter("ROW"));
	}
	catch (Exception e) {
	}
	
	String sel = "";
	String sel2 = "";
	String myFlag = "";
	int sel3 = 0;
	double sel1 = 0;
	String myRow[] = new String[colNum];
	for (int i=0; i<colNum; i++) {
		myRow[i] = "";
	}
	
	String checked = "";
	int opt;
	try {
		opt = Integer.parseInt(req.getParameter("opt"));
	}
	catch (Exception e) {
		opt = 0;
	}
	
	for (int row = 0; row < maxRow; row++){
	  	try {
	      checked = req.getParameter("TRANSROW_" + row);
	      if (!checked.equals("checked")){checked = "";}
		}
		catch (Exception e) {
		  checked = "";	
		}
		 
		try {		
	     	myRow[0]  =  req.getParameter("E02RODSEQ_" + row).toUpperCase();  // Sec Number
			myRow[1]  =  req.getParameter("E02RODNBR_" + row).toUpperCase();  // Reg Mercant.
		 	myRow[2]  =  req.getParameter("E02RODDSC_" + row).toUpperCase();  // Description
		 	myRow[3]  =  req.getParameter("E02RODAMT_" + row).toUpperCase();  // Amount	
		 	myRow[4]  =  req.getParameter("E02RODMD1_" + row).toUpperCase();  // Maturity Day MM
			myRow[5]  =  req.getParameter("E02RODMD2_" + row).toUpperCase();  // Maturity Day DD	
			myRow[6]  =  req.getParameter("E02RODMD3_" + row).toUpperCase();  // Maturity Day YY
		  	trList.addRow(myFlag, myRow);
		 }
		catch (Exception e) {} 		
		
	}
	
			
	flexLog("Putting java beans into the session");
	ses.setAttribute("collList", trList);

			switch (opt){
				
				case 2 :{
					try{
						procActionCollDetail(mc,user,req,res,ses);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;	
					}
				default : {
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_list_detail.jsp?ROW=" + realMaxRow);
						res.sendRedirect(super.srctx + LangPath + "ERA0010_ga_list_detail.jsp?ROW=" + realMaxRow);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
			}
}	
					
				

	/**
 * This method was created in VisualAge.
 */
protected void procActionCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgGA = null;
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
	 	msgGA = (ESD000002Message)ses.getAttribute("gaCodes");
		//msgGA = (ESD000002Message)mc.getMessageRecord("ESD000002");
		msgGA.setH02USR(user.getH01USR());
	 	msgGA.setH02PGM("ERA0010");
	 	msgGA.setH02TIM(getTimeStamp());
	 	msgGA.setH02SCR("01");
	 	msgGA.setH02OPE("0003");
	 	msgGA.setE02ACC(userPO.getIdentifier());

		// all the fields here
	 	java.util.Enumeration enu = msgGA.fieldEnumeration();
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

	 	//msgGA.send();
	 	mc.sendMessage(msgGA);
	 	msgGA.destroy();
	 	flexLog("ESD00002 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgGA = new datapro.eibs.beans.ESD000002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ESD000002Message)newmessage;
			// showESD008004(msgGA);

			userPO.setIdentifier(msgGA.getE02ACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("gaCodes", msgGA);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors 
				try {
					    flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
					
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_codes.jsp");
					callPage(LangPath + "ERA0010_ga_codes.jsp", req, res);	
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
protected void procActionCollBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001001Message msgGa = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	boolean IsNotError = false;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
  	
	String opCode = "";
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
    
	if (userPO.getPurpose().equals("NEW")) opCode="0001"; else opCode = "0003";
	
	// Send Initial data
	try
	{
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
	 	msgGa.setH01USERID(user.getH01USR());
	 	msgGa.setH01PROGRM("ERA0010");
	 	msgGa.setH01TIMSYS(getTimeStamp());
	 	msgGa.setH01SCRCOD("01");
	 	msgGa.setH01OPECOD(opCode);
	 	
	 	// all the fields here
	 	java.util.Enumeration enu = msgGa.fieldEnumeration();
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
		
		if (!userPO.getPurpose().equals("NEW")){
	 		try {
	 			msgGa.setE01ROCREF(userPO.getIdentifier());
	 		}
	 		catch (Exception e) {
	 		}
	 	}

	 	msgGa.send();	
	 	msgGa.destroy();
	 	flexLog("ERA001001 Message Sent");
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
	
		 if (newmessage.getFormatName().equals("ERA001001")) {
			try {
				msgGa = new datapro.eibs.beans.ERA001001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGa = (ERA001001Message)newmessage;

			userPO.setCusName(msgGa.getE01CUSNA1());
			userPO.setCusNum(msgGa.getE01ROCCUN());
			userPO.setCurrency(msgGa.getE01ROCCCY());
			userPO.setType(msgGa.getE01ROCTYP());

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error",msgError);
			ses.setAttribute("gaMant", msgGa);

			if(IsNotError){  // There are no errors
				if (userPO.getPurpose().equals("NEW")) {
					userPO.setIdentifier(msgGa.getE01ROCREF());
					ses.setAttribute("userPO", userPO);
					procReqCollBasic(mc, user, req, res, ses);
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_acc_list.jsp");
						res.setContentType("text/html");
						PrintWriter  out = res.getWriter();
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println(" var myOpener= top.opener;");
						out.println("		myOpener.document.location.href='" + super.webAppPath +"/servlet/datapro.eibs.client.JSERA0010?SCREEN=7'"); // + super.webAppPath + LangPath + "ERA0010_ga_acc_list.jsp';");
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("<P>Close it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else{ //There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
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
protected void procActionCollDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001002Message msgColl = null;
	JBListRec trList = null;
	JBList beanList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	
	 	//Sending ERA001002
	 try{
	 	trList = (JBListRec)ses.getAttribute("collList");
		msgColl = (ERA001002Message)mc.getMessageRecord("ERA001002");
		msgColl.setH02USERID(user.getH01USR());
		msgColl.setH02PROGRM("ERA0010");
		msgColl.setH02TIMSYS(getTimeStamp());
		msgColl.setH02SCRCOD("01");
		msgColl.setH02OPECOD("0003");
		// Get Parameters here	
	
		try	{ 	
	 		msgColl.setE02RODREF(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 
		try	{ 	
	 		msgColl.setE02RODBNK(userPO.getBank());
		}
		catch (Exception e)	{
		} 
		try	{ 	
	 		msgColl.setE02RODCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
		}

		trList.initRow();
		while (trList.getNextRow()) {
					
			msgColl = (ERA001002Message)mc.getMessageRecord("ERA001002");
			msgColl.setH02USERID(user.getH01USR());
			msgColl.setH02PROGRM("ERA0010");
			msgColl.setH02TIMSYS(getTimeStamp());
			msgColl.setH02SCRCOD("01");
			msgColl.setH02OPECOD("0003");
		// Get Parameters here	
	
			try	{ 	
	 			msgColl.setE02RODREF(userPO.getIdentifier());
			}
			catch (Exception e)	{
			} 
			try	{ 	
	 			msgColl.setE02RODBNK(userPO.getBank());
			}
			catch (Exception e)	{
			} 
			try	{ 	
	 			msgColl.setE02RODCUN(userPO.getCusNum());
			}
			catch (Exception e)	{
			}
		 	
			try {
				msgColl.setE02RODSEQ(trList.getRecord(0));  // Seq Number
			}
			catch (Exception e){}
			try {
				msgColl.setE02RODNBR(trList.getRecord(1));  // Register Code
			}
			catch (Exception e){}
			try {
				msgColl.setE02RODDSC(trList.getRecord(2));  // Description
			}
			catch (Exception e){}
			try {
			 	msgColl.setE02RODAMT(trList.getRecord(3));  //Amount
			}
			catch (Exception e){  
			}
			try {
				msgColl.setE02RODMD1(trList.getRecord(4));  // Maturuty Day MM
			}
			catch (Exception e){	
			}
			try {	
				msgColl.setE02RODMD2(trList.getRecord(5));  // Maturuty Day DD
			}
			catch (Exception e){} 
			try {	
				msgColl.setE02RODMD3(trList.getRecord(6));  // Maturuty Day YY
			}
			catch (Exception e){} 	
						
			msgColl.setH02FLGMAS("");
		 	msgColl.send();
		 	msgColl.destroy();
		 	flexLog("ERA001002 Message Sent");

		 	// Receive Error Message
			try
				{
	 			 newmessage = mc.receiveMessage();
	  
	  			if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
					if (IsNotError )
					 	continue;
					else
						break; 
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
		
		flexLog("Putting java beans into the session");
		ses.setAttribute("error",msgError);
		ses.setAttribute("collList", beanList);
		ses.setAttribute("userPO", userPO);
		
		  
		if (IsNotError) {  // There are no errors 
				try {
					    flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
					
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_list_detail.jsp");
					callPage(LangPath + "ERA0010_ga_list_detail.jsp", req, res);	
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

/**
 * This method was created in VisualAge.
 */
protected void procActionDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001001Message msgGa = null;
	ELEERRMessage msgError = null;
	boolean IsNotError = false;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	String opCode = "0009";
	
	// Send Initial data
	try
	{
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
	 	msgGa.setH01USERID(user.getH01USR());
	 	msgGa.setH01PROGRM("ERA0010");
	 	msgGa.setH01TIMSYS(getTimeStamp());
	 	msgGa.setH01SCRCOD("01");
	 	msgGa.setH01OPECOD(opCode);
	 	
	 	try {
	 		msgGa.setE01ROCREF(req.getParameter("REFNUM"));
	 	}
	 	catch (Exception e) {
	 	}
	 	
		msgGa.send();	
	 	msgGa.destroy();
	 	flexLog("ERA001001 Message Sent");
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
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error",msgError);
			

			if(IsNotError){  // There are no errors

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=7");
			
			}
		
			else{ //There are errors
				
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_acc_list.jsp");
						callPage(LangPath + "ERA0010_ga_acc_list.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			   }
		 }			
		
	
/**
 * This method was created in VisualAge.
 */
protected void procActionDeleteDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001002Message msgGa = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	String opCode = "0009";
	
	// Send Initial data
	try
	{
		msgGa = (ERA001002Message)mc.getMessageRecord("ERA001002");
	 	msgGa.setH02USERID(user.getH01USR());
	 	msgGa.setH02PROGRM("ERA0010");
	 	msgGa.setH02TIMSYS(getTimeStamp());
	 	msgGa.setH02SCRCOD("01");
	 	msgGa.setH02OPECOD(opCode);
	 	
	 	try {
	 		msgGa.setE02RODSEQ(req.getParameter("COLLROW"));
	 	}
	 	catch (Exception e) {
	 	}
		try {
	 		msgGa.setE02RODREF(userPO.getIdentifier());
	 	}
	 	catch (Exception e) {
	 	}
	 	try	{ 	
	 		msgGa.setE02RODBNK(userPO.getBank());
		}
		catch (Exception e)	{
		} 
		try	{ 	
	 		msgGa.setE02RODCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
		}
	 	
	 	
		msgGa.send();	
	 	msgGa.destroy();
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
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error",msgError);
			

			if(IsNotError){  // There are no errors

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=3");
			
			}
		
			else{ //There are errors
				
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_list_detail.jsp");
						callPage(LangPath + "ERA0010_ga_list_detail.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			   }
		 }			
		
	
/**
 * This method was created in VisualAge.
 */
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA001001Message msgGa = null;
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

	String opCode = "0001";
	
	// Send Initial data
	try
	{
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
	 	msgGa.setH01USERID(user.getH01USR());
	 	msgGa.setH01PROGRM("ERA0010");
	 	msgGa.setH01TIMSYS(getTimeStamp());
	 	msgGa.setH01SCRCOD("01");
	 	msgGa.setH01OPECOD(opCode);
	 	
	 	//try {
	 		//msgGa.setE01ROCREF(userPO.getIdentifier());
	 	//}
	 	//catch (Exception e) {
	 	//}
	 	
		// all the fields here
	 	java.util.Enumeration enu = msgGa.fieldEnumeration();
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


	 	msgGa.send();	
	 	msgGa.destroy();
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
	
		 if (newmessage.getFormatName().equals("ERA001001")) {
			try {
				msgGa = new datapro.eibs.beans.ERA001001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGa = (ERA001001Message)newmessage;

			userPO.setCusName(msgGa.getE01CUSNA1());
			userPO.setCusNum(msgGa.getE01ROCCUN());
			userPO.setCurrency(msgGa.getE01ROCCCY());
			userPO.setType(msgGa.getE01ROCTYP());

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error",msgError);
			ses.setAttribute("gaMant", msgGa);

			if(IsNotError){  // There are no errors

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=7");
			
			}
		
			else{ //There are errors
				
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
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
protected void procActionEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgGA = null;
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
	 	msgGA = (ESD000005Message)ses.getAttribute("gaInst");
		//msgGA = (ESD000005Message)mc.getMessageRecord("ESD000005");
		msgGA.setH05USR(user.getH01USR());
	 	msgGA.setH05PGM("EDL0130");
	 	msgGA.setH05TIM(getTimeStamp());
	 	msgGA.setH05SCR("01");
	 	msgGA.setH05OPE("0003");
	 	msgGA.setE05ACC(userPO.getIdentifier());
		msgGA.setE05TYP("C");
		msgGA.setH05WK1("M");
		
		// all the fields here
	 	java.util.Enumeration enu = msgGA.fieldEnumeration();
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

	 	//msgGA.send();
	 	mc.sendMessage(msgGA);	
	 	msgGA.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgGA = new datapro.eibs.beans.ESD000005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgGA = (ESD000005Message)newmessage;
			// showESD008004(msgGA);

			userPO.setIdentifier(msgGA.getE05ACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("gaInst", msgGA);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					{ 	
					    flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_special_inst.jsp");
					callPage(LangPath + "ERA0010_ga_special_inst.jsp", req, res);	
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
protected void procReqEnterNewIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ESS0030DSMessage msgUser = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	ERA001001Message msgGa = null;
	ERA001003Message msgIns = null;	
	HttpSession session = (HttpSession)req.getSession(false); 	
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		/*
		if (userPO.getPurpose().equals("NEW")) {
			procActionCollBasic(mc, user, req, res, ses);
		}
		*/
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
		msgGa.setE01CUSNA1(userPO.getCusName());
		msgGa.setE01ROCBRN(userPO.getBranch());
		msgGa.setE01ROCCCY(userPO.getCurrency());
		msgGa.setE01ROCGLN(userPO.getGenLedger());
		msgGa.setE01ROCTYP(userPO.getType());
		msgGa.setE01ROCDES(userPO.getHeader1());
		
		msgIns = (ERA001003Message)mc.getMessageRecord("ERA001003");
		msgIns.setE03ROCREF(userPO.getIdentifier());
		msgIns.setE03ROCBNK(userPO.getBank());
		msgIns.setE03ROCCUN(userPO.getCusNum());
		int seq = Integer.parseInt(userPO.getHeader2()) + 1;
		msgIns.setE03ROCSEQ(seq + "");
		
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("gaMant", msgGa);
		ses.setAttribute("insMant", msgIns);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint_ins_detail.jsp");
		callPage(LangPath + "ERA0010_ga_maint_ins_detail.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}
}

protected void procReqEnterMaintIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	ERA001001Message msgGa = null;
	ERA001003Message msgIns = null;	
	JBObjList beanList = null;
	
	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
		msgGa.setE01CUSNA1(userPO.getCusName());
		msgGa.setE01ROCBRN(userPO.getBranch());
		msgGa.setE01ROCCCY(userPO.getCurrency());
		msgGa.setE01ROCGLN(userPO.getGenLedger());
		msgGa.setE01ROCTYP(userPO.getType());
		msgGa.setE01ROCDES(userPO.getHeader1());
		
		beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("insList");
		int row =0;
		try{
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch(Exception ex){
			row = 0;
		}
		
		beanList.setCurrentRow(row);
		msgIns = new datapro.eibs.beans.ERA001003Message(); 
		msgIns = (ERA001003Message)beanList.getRecord();
		msgIns.setE03ROCBNK(userPO.getBank());
		
		try{
			userPO.setIdentifier(msgIns.getE03ROCREF());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("gaMant", msgGa);
		ses.setAttribute("insMant", msgIns);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint_ins_detail.jsp");
		callPage(LangPath + "ERA0010_ga_maint_ins_detail.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procReqEnterDeleteIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	ERA001001Message msgGa = null;
	ERA001003Message msgIns = null;	
	JBObjList beanList = null;
	
	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		msgGa = (ERA001001Message)mc.getMessageRecord("ERA001001");
		msgGa.setE01CUSNA1(userPO.getCusName());
		msgGa.setE01ROCBRN(userPO.getBranch());
		msgGa.setE01ROCCCY(userPO.getCurrency());
		msgGa.setE01ROCGLN(userPO.getGenLedger());
		msgGa.setE01ROCTYP(userPO.getType());
		msgGa.setE01ROCDES(userPO.getHeader1());
		
		beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("insList");
		int row =0;
		try{
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch(Exception ex){
			row = 0;
		}
		
		beanList.setCurrentRow(row);
		msgIns = new datapro.eibs.beans.ERA001003Message(); 
		msgIns = (ERA001003Message)beanList.getRecord();
		msgIns.setE03ROCBNK(userPO.getBank());
		
		try{
			userPO.setIdentifier(msgIns.getE03ROCREF());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}
		
		userPO.setPurpose("DELETE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("gaMant", msgGa);
		ses.setAttribute("insMant", msgIns);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint_ins_detail.jsp");
		callPage(LangPath + "ERA0010_ga_maint_ins_detail.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procActionDeleteIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	UserPos	userPO = null;
	ERA001003Message msgIns = null;
	ELEERRMessage msgError = null;
	JBObjList beanList = null;
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	String opCode = "0009";
	
	// Send Initial data
	try
	{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("insList");
		int row =0;
		try{
			row = Integer.parseInt(req.getParameter("ACTROW"));
		}
		catch(Exception ex){
			row = 0;
		}
		
		beanList.setCurrentRow(row);
		msgIns = new datapro.eibs.beans.ERA001003Message(); 
		msgIns = (ERA001003Message)beanList.getRecord();
		msgIns.setH03USERID(user.getH01USR());
		msgIns.setH03PROGRM("ERA0010");
		msgIns.setH03TIMSYS(getTimeStamp());
		msgIns.setH03SCRCOD("01");
		msgIns.setH03OPECOD(opCode);
		
		mc.sendMessage(msgIns);
		msgIns.destroy();
	 	flexLog("ERA001003 Message Sent");

		ses.setAttribute("userPO", userPO);
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
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error",msgError);
			

			if(IsNotError){  // There are no errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=500&ROW=" + userPO.getHeader3());
			}
		
			else{ //There are errors
				
					try {
						flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
						callPage(LangPath + "ERA0010_ga_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			   }
		 }			

/**
 * This method was created in VisualAge.
 */
protected void procActionEnterIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERA001001Message msgGa = null;
		ERA001003Message msgIns = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;
		boolean IsNotError = false;	

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		String opCode = "";

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		if (userPO.getPurpose().equals("DELETE")) {
			opCode="0009";
		} else {
			opCode="0005";
		}

		// Send Initial data
		try
		{
			msgIns = (ERA001003Message)mc.getMessageRecord("ERA001003");
			msgIns.setH03USERID(user.getH01USR());
			msgIns.setH03PROGRM("ERA0010");
			msgIns.setH03TIMSYS(getTimeStamp());
			msgIns.setH03SCRCOD("01");
			msgIns.setH03OPECOD(opCode);

			// all the fields here
			java.util.Enumeration enu = msgIns.fieldEnumeration();
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

			msgIns.send();	
			msgIns.destroy();
			flexLog("ERA001003 Message Sent");
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

		msgGa = new datapro.eibs.beans.ERA001001Message(); 
		msgGa.setE01ROCCUN(userPO.getCusNum());
		msgGa.setE01CUSNA1(userPO.getCusName());
		msgGa.setE01ROCBNK(userPO.getBank());
		msgGa.setE01ROCBRN(userPO.getBranch());
		msgGa.setE01ROCCCY(userPO.getCurrency());
		msgGa.setE01ROCGLN(userPO.getGenLedger());
		msgGa.setE01ROCTYP(userPO.getType());
		msgGa.setE01ROCDES(userPO.getHeader1());

		if (newmessage.getFormatName().equals("ERA001003")) {
			try {
				msgIns = new datapro.eibs.beans.ERA001003Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgIns = (ERA001003Message)newmessage;

			userPO.setCusNum(msgIns.getE03ROCCUN());

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error",msgError);
			ses.setAttribute("gaMant", msgGa);
			ses.setAttribute("insMant", msgIns);

			if(IsNotError){  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint.jsp");
					res.setContentType("text/html");
					PrintWriter  out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("var myOpener= top.opener;");
					out.println("	myOpener.document.location.href='" + super.webAppPath + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=500&ROW=" + userPO.getHeader3() + "'"); 
					out.println("	top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else{ //There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint_ins_detail.jsp");
					callPage(LangPath + "ERA0010_ga_maint_ins_detail.jsp", req, res);
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
}