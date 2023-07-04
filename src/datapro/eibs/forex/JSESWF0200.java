package datapro.eibs.forex;

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

public class JSESWF0200 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIMIT = 1;
	protected static final int R_SWIFT_INQ = 3;
	protected static final int R_SWIFT_COP = 5;
	
	protected static final int A_LIMIT = 2;
	protected static final int A_LOGS = 18;
	
	protected static final int R_ENTER_LIMIT = 100;

	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESWF0200() {
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
	
protected void procReqEnterSearch(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ESWF200_enter_search.jsp");
			callPage(LangPath + "ESWF200_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}	
	
protected void procReqListInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESWF20001Message msgSearch = null;
	ESWF20001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ESWF20001Message)mc.getMessageRecord("ESWF20001");
		msgSearch.setESW1OPE("4");	
	 	
	 	try{		 
		 msgSearch.setESW1USR(req.getParameter("USERSWIFT"));						
		}
		catch (Exception e)
		{
		  
		}
	 	
	 	
		try{		 
		 msgSearch.setESW1SWI(req.getParameter("SWIFTID"));						
		}
		catch (Exception e)
		{
		 
		}
	 	
		try{ 
		 msgSearch.setESW1DT1(req.getParameter("DATE1"));					
		}
		catch (Exception e)
		{
		  
		}
		
		try{
		 msgSearch.setESW1DT2(req.getParameter("DATE2"));				
		}
		catch (Exception e)
		{
		  
		}		
		
		try{
		 msgSearch.setESW1DT3(req.getParameter("DATE3"));				
		}
		catch (Exception e)
		{
		  
		}	
		
		try{
		 msgSearch.setESW1FTM(req.getParameter("FORMAT"));				
		}
		catch (Exception e)
		{
		
		}	
		
		try{
		 msgSearch.setESW1REF(req.getParameter("REFERENCE"));				
		}
		catch (Exception e)
		{
		  
		}	
		
	 	msgSearch.send();	
	 	msgSearch.destroy();
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
	  
			if (newmessage.getFormatName().equals("ESWF20001")) {


			try {
				beanList = new JBList();
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


				msgList = (ESWF20001Message)newmessage;


				marker = msgList.getESW1OPE();


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
					
					
					String showInqSwift = "showInqSwift('" + msgList.getESW1FTM() + "', '" 
														   + msgList.getESW1REF() + "', '" 
														   + msgList.getESW1USR() + "')";
					myRow = new  StringBuffer();
					myRow.append("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showInqSwift + "\">" + Util.formatCell(msgList.getESW1FTM()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showInqSwift + "\">" + Util.formatCell(msgList.getESW1SWI()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showInqSwift + "\">" + Util.formatCell(msgList.getESW1REF()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showInqSwift + "\">" + Util.formatDate(msgList.getESW1DT1(),msgList.getESW1DT2(),msgList.getESW1DT3()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showInqSwift + "\">" + Util.formatCell(msgList.getESW1USR()) + "</A></TD>");
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
			ses.setAttribute("ESWF0200Help", beanList);
			ses.setAttribute("userPO", userPO);


			try {
				 flexLog("About to call Page: " + LangPath + "ESWF200_sel.jsp");
				 callPage(LangPath + "ESWF200_sel.jsp", req, res);
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

protected void procReqSwiftInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF20002Message msgDV = null;
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
	  opCode = "0004";
	
	// Send Initial data
	try
	{
		msgDV = (ESWF20002Message)mc.getMessageRecord("ESWF20002");
	 	
	 	try{
	 	msgDV.setESW2FTM(req.getParameter("FORMAT"));
	    }		
	    catch (Exception e)	{

	    }
	 	
	 	try{
	 	msgDV.setESW2REF(req.getParameter("REFERENCE"));
	    }		
	    catch (Exception e)	{
	
	    }
	    
	    try{ 
	    msgDV.setESW2USR(req.getParameter("USERID")); 
	    }		
	    catch (Exception e)	{
	
	    }    	 	
		msgDV.send();	
	 	msgDV.destroy();
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
			try {
				msgError = new ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			
			res.setContentType("text/html");
			printClose(res.getWriter());
			
	  	}	
   		else if (newmessage.getFormatName().equals("ESWF20002")) {
			try {
				msgDV = new ESWF20002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgDV = (ESWF20002Message)newmessage; 

			flexLog("Putting java beans into the session");
			ses.setAttribute("swff", msgDV);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ESWF200_swift_free_format_inq.jsp");
				callPage(LangPath + "ESWF200_swift_free_format_inq.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
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

}

	protected void procReqCopy(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF00001Message msgSWFF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		String opCode = null;
		opCode = "0001";


		// Send Initial data
		try {
			msgSWFF = (ESWF00001Message) mc.getMessageRecord("ESWF00001");
			msgSWFF.setH01USR(user.getH01USR());
			msgSWFF.setH01PGM("ESWF00001");
			msgSWFF.setH01TIM(getTimeStamp());
			msgSWFF.setH01SCR("01");
			msgSWFF.setH01OPE(opCode);
			//msgSWFF.setE01ACMPRO(userPO.getIdentifier());
			
			userPO.setHeader1(req.getParameter("ESW2SWI"));
			userPO.setHeader2(req.getParameter("ESW2FTM"));
			
			msgSWFF.send();
			msgSWFF.destroy();
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


			if (newmessage.getFormatName().equals("ESWF00001")) {
				try {
					msgSWFF = new ESWF00001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}


				msgSWFF = (ESWF00001Message) newmessage;

				String[] rows = Util.splitField(req.getParameter("ESW2REC"), 35, 50 );
				msgSWFF.setESWF01(rows[0]);	
				msgSWFF.setESWF02(rows[1]);	
				msgSWFF.setESWF03(rows[2]);
				msgSWFF.setESWF04(rows[3]);
				msgSWFF.setESWF05(rows[4]);
				msgSWFF.setESWF06(rows[5]);
				msgSWFF.setESWF07(rows[6]);
				msgSWFF.setESWF08(rows[7]);				
				msgSWFF.setESWF09(rows[8]);	
				msgSWFF.setESWF10(rows[9]);	
				msgSWFF.setESWF11(rows[10]);
				msgSWFF.setESWF12(rows[11]);
				msgSWFF.setESWF13(rows[12]);
				msgSWFF.setESWF14(rows[13]);
				msgSWFF.setESWF15(rows[14]);
				msgSWFF.setESWF16(rows[15]);				
				msgSWFF.setESWF17(rows[16]);	
				msgSWFF.setESWF18(rows[17]);	
				msgSWFF.setESWF19(rows[18]);
				msgSWFF.setESWF20(rows[19]);
				msgSWFF.setESWF21(rows[20]);
				msgSWFF.setESWF22(rows[21]);
				msgSWFF.setESWF23(rows[22]);
				msgSWFF.setESWF24(rows[23]);				
				msgSWFF.setESWF25(rows[24]);	
				msgSWFF.setESWF26(rows[25]);	
				msgSWFF.setESWF27(rows[26]);
				msgSWFF.setESWF28(rows[27]);
				msgSWFF.setESWF29(rows[28]);
				msgSWFF.setESWF30(rows[29]);
				msgSWFF.setESWF31(rows[30]);
				msgSWFF.setESWF32(rows[31]);				
				msgSWFF.setESWF33(rows[32]) ;
				msgSWFF.setESWF34(rows[33]) ;
				msgSWFF.setESWF35(rows[34]) ;		
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);


				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF000_swift_free_format_new.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF010_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
					} catch (Exception e) {
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
protected void procReqPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR020001Message msgList = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
    int colNum = 17;
	try {
		beanList = new JBListRec();
		beanList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
  	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EPR020001Message)mc.getMessageRecord("EPR020001");
		msgList.setH01USERID(user.getH01USR());
        msgList.setH01FLGWK1("");
        
		try {
		 	  msgList.setE01PRLSFR(req.getParameter("ESW2REF"));
		}
		catch (Exception e)	{
	 	  msgList.setE01PRLSFR("");
		}
        
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("EPR020001 Message Sent");
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

			try {
				msgError = new ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);
			beanList.setNoResult(true);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("docList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EFE0000_enter_inquiry.jsp");
				callPage(LangPath + "EFE0000_enter_inquiry.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EPR020001")) {

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			String VDate = "";
			String VCCY  = "";
			
			while (true) {
				
				

				msgList = (EPR020001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					break;
				}
				else {


						myFlag = msgList.getE01PRLFMT();
						
						userPO.setIdentifier(msgList.getE01ACCNUM());
						
						myRow[0] = msgList.getE01PRLFMT();
						myRow[1] = Util.fcolorCCY(msgList.getE01AMOUNT());
						myRow[2] = msgList.getE01PRLSFR();
						myRow[3] = msgList.getE01PRLSRR();
						myRow[4] = msgList.getE01PRLUSR();								
						myRow[5] = msgList.getE01PRLRID();
						myRow[6] = msgList.getE01DSCTST();
						myRow[7] = msgList.getE01DSCMOD();
						myRow[8] = msgList.getE01DSCPRT();
						myRow[9] = Util.formatDate(msgList.getE01PRLSY1(),msgList.getE01PRLSY2(),msgList.getE01PRLSY3());
						myRow[10] = msgList.getE01PRLSYT();
						myRow[11] = msgList.getE01PRLCCY();
						myRow[12] = msgList.getE01ACCNUM();
																							
						  beanList.addRow(myFlag, myRow);
						}
						newmessage = mc.receiveMessage();
					}

			flexLog("Putting java beans into the session");
			ses.setAttribute("logs", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EPR0200_logs.jsp");
				callPage(LangPath + "EPR0200_logs.jsp", req, res);
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






	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_LIMIT;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Requests
					case R_LIMIT :
						procReqListInquiry(mc, msgUser, req, res, session);
						break;					
					case R_ENTER_LIMIT :
				     	procReqEnterSearch(msgUser, req, res, session);
						 break;
					 case R_SWIFT_INQ :
						procReqSwiftInquiry(mc, msgUser, req, res, session);
						break;
					  case R_SWIFT_COP :
						procReqCopy(mc, msgUser, req, res, session);
						break;
					  case A_LOGS :
						procReqPos(mc, msgUser, req, res, session);
						break;			
						// Actions
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				}
				finally {
					s.close();
				} 
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	protected void showERROR(ELEERRMessage m) {
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