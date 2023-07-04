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

public class JSEWD0322S extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_LIMIT 			= 1;
	static final int A_LIMIT 			= 2;

	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEWD0322S() {
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
private void procReqPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0322DSMessage msgList = null;
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

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EWD0322DSMessage)mc.getMessageRecord("EWD0322DS");
		msgList.setRWDUSR(user.getH01USR());
        msgList.setRWDTYP("S");
        
		try {
		 	if (req.getParameter("E01FESCUN") != null) {
		 	  	msgList.setSWDCUN(req.getParameter("E01FESCUN"));
		    	userPO.setCusNum(req.getParameter("E01FESCUN"));
		 	}
		}
		catch (Exception e)	{
	 	  msgList.setSWDCUN("0");
		}
        
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("EWD0322DS Message Sent");
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

			// showERROR(msgError);
			beanList.setNoResult(true);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("docList", beanList);
			ses.setAttribute("userPO", userPO);

			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1100");
			
			
		}
	  	else if (newmessage.getFormatName().equals("EWD0322DS")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String custNum = req.getParameter("cust");
			//var for ofac status
			//var for Warning status
			String chkOfac = "";
			String chkWarn = "";
			if (custNum == null) 
				firstTime = true;
			else				
				firstTime = false;
			int indexRow = 0;
			while (true) {

				msgList = (EWD0322DSMessage)newmessage;

				marker = msgList.getSWDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
					}
					else {
						chk = "";
					}
					
						myFlag = msgList.getSWDTYL();
										
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=CENTER><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgList.getSWDCUN() + "\" " + chk + " onclick=\"getValor('" + msgList.getSWDCUN() + "', '" + msgList.getSWDVA1() + "', '" + msgList.getSWDVA2() +  "', '" + msgList.getSWDVA3() +"')\"></TD>");
						myRow.append("<TD NOWRAP ALIGN=LEFT>"+ Util.formatCell(msgList.getSWDCUN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>"+ Util.formatDate(msgList.getSWDVA1(),msgList.getSWDVA2(),msgList.getSWDVA3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>"+ Util.formatCell(msgList.getSWDCCY()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>"+ Util.fcolorCCY(msgList.getSWDAMN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>"+ Util.formatCell(msgList.getSWDAMU()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>"+ Util.formatCell(msgList.getSWDAMA()) + "</TD>");
						myRow.append("</TR>");
						
						beanList.addRow(myFlag, myRow.toString());
						indexRow ++;				
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
					}
				}
				newmessage = mc.receiveMessage();
			}
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("limPos", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EWD0322S_client_limits.jsp");
				callPage(LangPath + "EWD0322S_client_limits.jsp", req, res);
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


private void procActionPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	UserPos	userPO = null;
	boolean IsNotError = false;


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	int inptOPT = 0;

	inptOPT = Integer.parseInt(req.getParameter("opt"));
	
	String CusNumb = "";
	String CusAux = "";
	String CUNCOD = null;
	
	try{
	   CUNCOD = req.getParameter("CUNCOD");
	   }

	catch (Exception ex) {
		CUNCOD =""; 
  	}
	
	try{
	   if(CUNCOD == null || CUNCOD == ""){
	    CusNumb = req.getParameter("CUSTOMER");
	   }
	   else {
	   	CusNumb = CUNCOD;
	   }
	}
	catch (Exception ex) {
		CusNumb =""; 
  	}
	
	

	String Date1 = req.getParameter("date1");
	String Date2 = req.getParameter("date2");
	String Date3 = req.getParameter("date3");
	String  Bank  = user.getE01UBK();
	
			switch (inptOPT) {
				case 1 : //New
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0325?SCREEN=200&E01FEOCUN=" + CusNumb + "&E01FEOBNK=" + Bank);
					break;				
				case 2 : //Maintenance
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0325?SCREEN=400&E01FEOCUN=" + CusNumb +  "&E01FEODT1=" + Date1 +  "&E01FEODT2=" + Date2 +  "&E01FEODT3=" + Date3 +  "&E01FEOBNK=" + Bank);
					break;
				case 3  ://Delete
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0325?SCREEN=600&E01FEOCUN=" + CusNumb +  "&E01FEODT1=" + Date1 +  "&E01FEODT2=" + Date2 +  "&E01FEODT3=" + Date3 +  "&E01FEOBNK=" + Bank);
					break;																				
				default :
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0325?SCREEN=200&E01FEOCUN=" + CusNumb);
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
				// Requests
				case R_LIMIT :
					procReqPos(mc, msgUser, req, res, session);
					break;
				// Actions
				case A_LIMIT :
					procActionPos(mc, msgUser, req, res, session);
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
private void showERROR(ELEERRMessage m)
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