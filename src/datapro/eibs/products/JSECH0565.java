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

public class JSECH0565 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_CHECK_BOOK			= 1;
	protected static final int A_CHECK_BOOK			= 2;
	protected static final int R_CHECK				= 3;
	protected static final int A_CHECK				= 4;


	// entering options
	protected static final int R_ENTER_INQ 			= 100;
	protected static final int A_ENTER_INQ 			= 200;
	
	

	protected String LangPath = "";

/**
 * JSECLI001 constructor comment.
 */
public JSECH0565() {
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
protected void procReqCheck(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH056502Message msgList = null;
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
		msgList = (ECH056502Message)mc.getMessageRecord("ECH056502");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ECH0565");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setH02SCRCOD("01");
	 	msgList.setE02CHMACC(userPO.getIdentifier());
	 	try {
		 	msgList.setE02CHMNCB(req.getParameter("chkNum"));
	 	}
	 	catch (Exception e) {
		 	msgList.setE02CHMNCB("0");
	 	}

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECH056502 Message Sent");
	}		
	catch (Exception e) {
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

			// showERROR(msgError);

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
		else if (newmessage.getFormatName().equals("ECH056502")) {
			
			try {
				beanList = new datapro.eibs.beans.JBList();
				flexLog("ECH056502 Message Received");
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			
			while (true) {

				msgList = (ECH056502Message)newmessage;

				marker = msgList.getH02FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					myFlag = "";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN01()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS01()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN02()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS02()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN03()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS03()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN04()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS04()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN05()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS05()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("checks", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECH0565_checks_inq.jsp");
				callPage(LangPath + "ECH0565_checks_inq.jsp", req, res);

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
protected void procActionEnterCheckInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH056501Message msgList = null;
	ECH056503Message msgHeader = null;
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
		msgList = (ECH056501Message)mc.getMessageRecord("ECH056501");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECH0565");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	try{
	 	msgList.setE01CHMACC(req.getParameter("E01CHMACC"));
	 	}
	 	catch (Exception e){
	 	msgList.setE01CHMACC(userPO.getIdentifier());	
	 	}
		
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECH056501 Message Sent");
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
		// Receive Error Message
		if (newmessage.getFormatName().equals("ELEERR")) 
			{
				msgError = (ELEERRMessage) newmessage;
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);				
			} 

		newmessage = mc.receiveMessage();
		// Receive Data Message
		if (newmessage.getFormatName().equals("ECH056503")) 
			{					
				msgHeader = (ECH056503Message) newmessage;
				msgHeader.setHandler(null);
							
			}
		
		newmessage = mc.receiveMessage();
		// Receive Data Message
        if (newmessage.getFormatName().equals("ECH056501")) {
			
			String chk = "";
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			beanList = new JBList();
			
			while (true) {

				msgList = (ECH056501Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					chk = msgList.getE01CHMNCB().trim();
					myFlag = "";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatDate(msgList.getE01CHMRQ1(), msgList.getE01CHMRQ2(), msgList.getE01CHMRQ3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatDate(msgList.getE01CHMAC1(), msgList.getE01CHMAC2(), msgList.getE01CHMAC3()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(chk) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMNTC()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMICK()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMFCK()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01STSSOL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01STSRCV()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01STSENV()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01STSSUC()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01STSENT()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:checksStatus('" + chk + "')\">" + Util.formatCell(msgList.getE01CHMDET()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			if (IsNotError){ 
				ses.setAttribute("rtBasic",msgHeader);
				ses.setAttribute("checkBooks", beanList);
				
				userPO.setIdentifier(msgHeader.getE03ACMACC());
				
				ses.setAttribute("userPO", userPO);
				flexLog("About to call Page: " + LangPath + "ECH0565_chb_list_inq.jsp");
				callPage(LangPath + "ECH0565_chb_list_inq.jsp", req, res);
				
			}
			else
			{	
				flexLog("About to call Page: " + LangPath + "ECH0565_chb_enter_inq.jsp");
				callPage(LangPath + "ECH0565_chb_enter_inq.jsp", req, res);							
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


protected void procReqEnterCheckInq( ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH0565_chb_enter_inq.jsp");
		callPage(LangPath + "ECH0565_chb_enter_inq.jsp", req, res);	
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

		int screen = R_ENTER_INQ;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
			
			if (screen == R_ENTER_INQ ) procReqEnterCheckInq( msgUser, req, res, session);
			else
			 {
				try
				{
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
			  		mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
								
					switch (screen) {						
						case R_CHECK :
							procReqCheck(mc, msgUser, req, res, session);
							break;
						case A_CHECK :
							break;				
						case A_ENTER_INQ :
							procActionEnterCheckInq(mc, msgUser, req, res, session);
							break;						
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//	return;
				}
				finally {
					s.close();
				}
			}
		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
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