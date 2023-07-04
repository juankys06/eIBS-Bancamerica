package datapro.eibs.transfer;

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

public class JSEWD0341A extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIMIT = 1;
	protected static final int A_LIMIT = 2;
	
	protected static final int R_SWIFT = 3;
	protected static final int A_SWIFT = 4;
	protected static final int R_LOGS	 = 5;
	protected static final int A_APP	 = 6;
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0341A() {
		super();
	}
	/**
	 * This method was created by David Mavilla.
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
	protected void procReqPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0341DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try{
			msgList = (EWD0341DSMessage)mc.getMessageRecord("EWD0341DS");
			
			msgList.setH01OPE("0002");
	 	try {
		 	msgList.setEWDFRC(req.getParameter("Pos"));
		 	}
		catch (Exception ex) {
			msgList.setEWDFRC("0");
		 	}	 				
			
			msgList.send();	
		 	msgList.destroy();
			flexLog("EWD0341DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1100");

			} else
				if (newmessage.getFormatName().equals("EWD0341DS")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

						boolean firstTime;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";
						//var for ofac status
						//var for Warning status
						String chkOfac = "";
						String chkWarn = "";
						String Stat = "";
						int indexRow = 0;
					
					while (true) {

							msgList = (EWD0341DSMessage) newmessage;
	
							marker = msgList.getEWDFLG();
	
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							}
							if(msgList.getEWDRPR().equals("R")){
							   Stat = "Released";	
							}
							else if(msgList.getEWDRPR().equals("D")){
							   Stat = "Deleted";	
							}
							else if(msgList.getEWDRPR().equals("P")){
							   Stat = "O/D Pending";	
							}
							else if(msgList.getEWDRPR().equals("S")){
							   Stat = "Straight Thru";	
							}
							else if(msgList.getEWDRPR().equals("V")){
							   Stat = "Voided";	
							}
							else if(msgList.getEWDRPR().equals("C")){
							   Stat = "Repairing";	
							}							
							else if(msgList.getEWDRPR().equals("X")){
							   Stat = "Reversed";	
							}
							else {
							   Stat ="Pending";
							}
							
								myRow = new StringBuffer("<TR>");
								myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\"" + msgList.getEWDNUM() + "\"></TD>");
								chkOfac = (msgList.getH01WK3().equals("3") ? "<a href=\"javascript:showInqOFAC('" + msgList.getEWDNUM() + "')\"><img src=\"../images/warning_16.jpg\" alt=\"OFAC Match List\" align=\"absmiddle\" border=\"0\" ></a>" : "");
								chkWarn = (msgList.getH01WK2().equals("A") ? "<a href=\"javascript:showInqWarn('" + msgList.getEWDNUM() + "')\"><img src=\"../images/warning01.gif\" alt=\"Warnings\" align=\"absmiddle\" border=\"0\" ></a>" : "");
								myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"  + msgList.getEWDNUM() + "</A></TD>");
								myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"  + msgList.getEWDTPE() + "</A></TD>");							
								myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"  + Stat + "</A></TD>");								
								myRow.append("<TD NOWRAP  ALIGN=RIGHT><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"    + msgList.getEWDORC() + "</A></TD>");
								myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"  + Util.formatDate(msgList.getEWDVD1(),msgList.getEWDVD2(),msgList.getEWDVD3())  + "</A></TD>");																						
								myRow.append("<TD NOWRAP  ALIGN=RIGHT><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"   + Util.fcolorCCY(msgList.getEWDAMT()) + ":" + msgList.getEWDTCY() + "</A></TD>");
								myRow.append("<TD NOWRAP  ALIGN=RIGHT><A HREF=\"javascript:showSwift('" + msgList.getEWDNUM() + "')\">"    + msgList.getEWDBNF() + "</A></TD>");
								myRow.append("</TR>");
								 
								beanList.addRow(myFlag, myRow.toString());
								indexRow++;
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							newmessage = mc.receiveMessage();
						}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0341Help", beanList);
					ses.setAttribute("userPO", userPO);

						try {
							flexLog("About to call Page: " + LangPath + "EWD0341A_approval_list.jsp");
							callPage(LangPath + "EWD0341A_approval_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
protected void procReqSwift100(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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




	  opCode = "0001";
	
	// Send Initial data
	try
	{
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
	 	
	 	
		if(!userPO.getIdentifier().trim().equals("")){
		try {
		 	  msgSwift.setE01SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFACC("0");
		}
		}
		else{
		 try {
		 	  msgSwift.setE01SWFACC(req.getParameter("REFNUM"));
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFACC("0");
		}
		}
	
	 	msgSwift.send();
	 	msgSwift.destroy();
	 	flexLog("ESWF10001 Message Sent");
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




		if (newmessage.getFormatName().equals("ESWF10001")) {
			try {
				msgSwift = new datapro.eibs.beans.ESWF10001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgSwift = (ESWF10001Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF100_generic_ap.jsp");
						callPage(LangPath + "ESWF100_generic_ap.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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

protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESWF10004Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ESWF10004Message)mc.getMessageRecord("ESWF10004");
		msgList.setH04USR(user.getH01USR());
	 	msgList.setH04PGM("ESWF10004");
	 	msgList.setH04TIM(getTimeStamp());
	 	try {
	 	  msgList.setE04SWFACC(req.getParameter("REFNUM"));
	 	}		
	    catch (Exception e)	{
	      msgList.setE04SWFACC("0");	
	    }
	    try{
	 	  msgList.setE04ACTION(req.getParameter("action"));
	 	}		
	    catch (Exception e)	{
	      msgList.setE04ACTION("A");	
	    }
	     try{
	 	msgList.setE04MSGTXT(req.getParameter("reason"));
	 	flexLog("Text: " + req.getParameter("reason"));
	 	}		
	    catch (Exception e)	{
	      msgList.setE04MSGTXT("");	
	    }
	    
	 	msgList.send();	
	 	msgList.destroy();
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
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There is no error
				//mod emat 01/24/2002
				//send draft parameter
				String typCode ="";
				flexLog("typCode: " + req.getParameter("typCode"));
				if (req.getParameter("typCode") != null) {
					typCode = req.getParameter("typCode");
				}
				// mod emat 01/24/2002
				//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=" + req.getParameter("appCode"));
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=1");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "EWD0341A_approval_list.jsp");
					 callPage(LangPath + "EWD0341A_approval_list.jsp", req, res);
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
	  	throw new RuntimeException("Socket Communication Error");
	}	
}

protected void procReqLogsInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EPR020001Message msgList = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

    int colNum = 17;
	try {
		beanList = new datapro.eibs.beans.JBListRec();
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
        msgList.setH01FLGWK1("2");
        
		try {
		 	  msgList.setE01ACCNUM(req.getParameter("REFNUM"));
		}
		catch (Exception e)	{
	 	  msgList.setE01ACCNUM("0");
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
				msgError = new datapro.eibs.beans.ELEERRMessage();
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
				flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				callPage(LangPath + "error_viewer.jsp", req, res);
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
						
						userPO.setIdentifier(msgList.getE01REFNUM());
						
						myRow[0] = msgList.getE01PRLFMT();
						myRow[1] = Util.fcolorCCY(msgList.getE01AMOUNT());
						myRow[2] = "";//msgList.getE01PRLSFR();
						myRow[3] = msgList.getE01PRLSRR();
						myRow[4] = msgList.getE01PRLUSR();								
						myRow[5] = msgList.getE01PRLRID();
						myRow[6] = "";//msgList.getE01PRLTST();
						myRow[7] = "";//msgList.getE01PRLMOD();
						myRow[8] = "";//msgList.getE01PRLPRT();
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
				flexLog("About to call Page: " + LangPath + "EPR0200_logs_inquiry.jsp");
				callPage(LangPath + "EPR0200_logs_inquiry.jsp", req, res);
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
					flexLog("Opennig Socket Connection");
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
						procReqPos(mc, msgUser, req, res, session);
						break;
						
					case R_SWIFT : 
						procReqSwift100(mc, msgUser, req, res, session);
						break;
					case R_LOGS :
						procReqLogsInq(mc, msgUser, req, res, session);
						break;	
					case A_APP :
						procActionApproval(mc, msgUser, req, res, session);
						break;
									
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