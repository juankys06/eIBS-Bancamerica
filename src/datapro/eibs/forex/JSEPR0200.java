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

public class JSEPR0200 extends datapro.eibs.master.SuperServlet {

	// Transfer Log Inquiry
	protected static final int R_LIMIT 			= 1;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEPR0200() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEPR0200");
	
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
  	 String SWIFTFREE = "";
  	 
  	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EPR020001Message)mc.getMessageRecord("EPR020001");
		msgList.setH01USERID(user.getH01USR());
        msgList.setH01FLGWK1("1");
        
         try{
  	        if(req.getParameter("SWIFTFREE").equals("Y")){ 
  	 
  	          SWIFTFREE = "Y";
  	          msgList.setH01FLGWK1("");
  	       }
  	     }
		 catch (Exception e)	{
		  SWIFTFREE = "";
		}
        
        
		try {
		 	  msgList.setE01ACCNUM(req.getParameter("E01ACCNUM"));
		 	  msgList.setE01REFNUM(req.getParameter("E01ACCNUM"));
		}
		catch (Exception e)	{
			  msgList.setE01ACCNUM(userPO.getIdentifier());
		 	  msgList.setE01REFNUM(userPO.getIdentifier());

		}

		
		if(SWIFTFREE.equals("Y")){
		try {
		 	  msgList.setE01REFNUM(req.getParameter("E01ACCNUM"));
		 	  msgList.setE01ACCNUM("0");
		}
		catch (Exception e)	{
		}
		try {
		 	 msgList.setH01FLGWK1("");
		}
		catch (Exception e)	{
		}
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

		int screen = R_LIMIT;
		
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
				// Requests
				case R_LIMIT :
					procReqPos(mc, msgUser, req, res, session);
					break;
				// Actions
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
				return;
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
}