package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSECH0390 extends datapro.eibs.master.SuperServlet {

	// RETAIL ACCOUNTS
	static final int R_RT_ENTER_MAINT 		= 300;

	static final int A_RT_CHEQUERAS			= 1;
	static final int A_RT_ACCION_CHK		= 2;
	
	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECH0390() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0130");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

private void  procReqRTEnterMaint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		userPO.setOption("RT");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH39001_rt_check_enter.jsp");
		callPage(LangPath + "ECH39001_rt_check_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

private void  procReqRTChequeras(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH039001Message msgRT = null;
	
	JBList beanList = null;

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgRT = (ECH039001Message)mc.getMessageRecord("ECH039001");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH039001");
	 	msgRT.setH01TIMSYS(getTimeStamp());
//	 	msgRT.setH01SCRCOD("01");
//	 	msgRT.setH01OPECOD("0001");
	 	msgRT.setE01CHMACC(req.getParameter("E01ACMACC"));
		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECH039001 Message Sent");
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

		if (newmessage.getFormatName().equals("ECH039001"))
		{
			try 
			{
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				flexLog("ECH039001 Message Received");
		  	} 
			catch (Exception ex) 
			{
				flexLog("Error: " + ex); 
		  	}

			String marker = "";
			String myFlag = "";
			String myRow = "";
			int indexRow = 0;

			while (true) 
			{
					msgRT = (ECH039001Message)newmessage;

					//marker = msgHelp.getEWDOPE();
					marker = msgRT.getH01FLGMAS();

					if (marker.equals("*")) 
					{
						beanList.setShowNext(false);
						break;
					}
					else 
					{
						myRow =  "<TR>";
						myRow += "<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + indexRow + "\" " +  " onclick=\"showAddInfo("+indexRow+")\"></TD>";
						myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHMRQD(),msgRT.getE01CHMRQM(), msgRT.getE01CHMRQY()) +"</td>";
						myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHFEUD(),msgRT.getE01CHFEUM(), msgRT.getE01CHFEUY()) +"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMNCB()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01ESTADO()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMNTC()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMICK()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMFCK()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01STSSOL()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01STSREP()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01STSENS()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01STSRES()+"</td>";					
						myRow +="<td nowrap>" + msgRT.getE01STSENC()+"</td>";					
												
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=ACC" + indexRow +  " value=" + msgRT.getE01CHMACC() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=NCB" + indexRow +  " value=" + msgRT.getE01CHMNCB() +">";

						myRow += "</TR>";
						
						beanList.addRow(myFlag, myRow);
						indexRow ++;	
										
						if (marker.equals("+")) 
						{
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
			flexLog("Putting java beans into the session");
			ses.setAttribute("rtBasic", beanList);
			
				try 
				{
					flexLog("About to call Page: " + LangPath + "ECH39001_rt_list_chk.jsp");
					callPage(LangPath + "ECH39001_rt_list_chk.jsp", req, res);	
				}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
		}
		else
		{
 			  flexLog("About to call Page: " + LangPath + "ECH39001_rt_check_enter.jsp");
			  callPage(LangPath + "ECH39001_rt_check_enter.jsp", req, res);	
		}
		


	}
	catch (Exception e)	
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}

private void  procReqRTAccion_Chk(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH039002Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgRT = (ECH039002Message)mc.getMessageRecord("ECH039002");
	 	msgRT.setH02USERID(user.getH01USR());
	 	msgRT.setH02PROGRM("ECH039002");
	 	msgRT.setH02TIMSYS(getTimeStamp());
	 	msgRT.setE02CHMACC(req.getParameter("E02CHMACC"));
	 	msgRT.setE02CHMNCB(req.getParameter("E02CHMNCB"));
	 	msgRT.setE02ACCION(req.getParameter("E02ACCION"));
		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECH039002 Message Sent");
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

		if (newmessage.getFormatName().equals("ECH039002"))
		{
			msgRT = (ECH039002Message)newmessage;
			flexLog("Putting java beans into the session");
  		    ses.setAttribute("rtBasic", msgRT);

			try 
				{
					flexLog("About to call Page: " + LangPath + "ECH39001_rt_confirm.jsp");
					callPage(LangPath + "ECH39001_rt_confirm.jsp", req, res);	
				}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
		}
		else
		{
 			  flexLog("About to call Page: " + LangPath + "ECH39001_rt_check_enter.jsp");
			  callPage(LangPath + "ECH39001_rt_check_enter.jsp", req, res);	
		}
		
	}
	catch (Exception e)	
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}

private void  procActionConsulta(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD009001Message msgRT1 = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	Socket s1 = null;
	MessageContext mc1 = null;

	try	
	{
		flexLog("Reabriendo el socket");
		s1 = new Socket(super.hostIP, getInitSocket(req) + 4);
		s1.setSoTimeout(super.sckTimeOut);
		flexLog("Reasignando el MessageContext");
		mc1 = new MessageContext(new DataInputStream(new BufferedInputStream(s1.getInputStream())),
					      	    new DataOutputStream(new BufferedOutputStream(s1.getOutputStream())),
							    "datapro.eibs.beans");

		try 
		{
			msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		} 
		catch (Exception ex) 
		{
			flexLog("Error: " + ex); 
  		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgRT1 = (EDD009001Message)mc1.getMessageRecord("EDD009001");
		 	msgRT1.setH01USERID(user.getH01USR());
		 	msgRT1.setH01PROGRM("EDD0000");
		 	msgRT1.setH01TIMSYS(getTimeStamp());
		 	msgRT1.setH01SCRCOD("01");
		 	msgRT1.setH01OPECOD("0002");
			try 
			{
				msgRT1.setE01ACMACC(req.getParameter("E01ACMACC"));
			}
			catch (Exception e)	
			{
	 		  	msgRT1.setE01ACMACC(userPO.getIdentifier());
			}

			msgRT1.send();	
		 	msgRT1.destroy();
		 	flexLog("EDD09001 Message Sent");
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
		  newmessage = mc1.receiveMessage();
	  
		  if (newmessage.getFormatName().equals("ELEERR")) 
		  {
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
		  flexLog("Read error " + e);
		}	
	
		// Receive Data
		try
		{
		    newmessage = mc1.receiveMessage();

			if (newmessage.getFormatName().equals("EDD009001")) 
			{
				try 
				{
					msgRT1 = (datapro.eibs.beans.EDD009001Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.EDD009001Message");
					flexLog("EDD09001 Message Received");
		 	 	} 
		 	 	catch (Exception ex) 
		 	 	{
					flexLog("EDD09001 Error: " + ex); 
		  		}

				msgRT1 = (EDD009001Message)newmessage;
			
				if (IsNotError) 
				{  // There are no errors
					try 
					{
						userPO.setIdentifier(msgRT1.getE01ACMACC());
						userPO.setHeader1(msgRT1.getE01ACMPRO());
						userPO.setCusNum(msgRT1.getE01ACMCUN());
						userPO.setHeader2(msgRT1.getE01ACMCUN());
						userPO.setHeader3(msgRT1.getE01CUSNA1());
						userPO.setCurrency(msgRT1.getE01ACMCCY());
						userPO.setOfficer(msgRT1.getE01ACMOFC() + " - " + msgRT1.getE01DSCOFC());

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("userPO", userPO);
					}
					catch (Exception e) 
					{
						flexLog("Exception calling page " + e);
					}
				}
				else 
				{  // There are errors
					try 
					{
					ses.setAttribute("error", msgError);
					flexLog("About to call Page: " + LangPath + "corpbanca/ECC0050_enter_stat.jsp");
					}
					catch (Exception e) 
					{
						flexLog("Exception calling page " + e);
					}
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
	
	catch (Exception e) 
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("MessageContext reassignment Error");
	}

	finally
	{
		s1.close();
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

		int screen = R_RT_ENTER_MAINT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 26);
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

				case R_RT_ENTER_MAINT : 
					procReqRTEnterMaint(msgUser, req, res, session);
					break;
				case A_RT_CHEQUERAS :
					procActionConsulta(msgUser, req, res, session);
					procReqRTChequeras(mc,msgUser,req,res,session);
					break;
				case A_RT_ACCION_CHK :
				    procReqRTAccion_Chk(mc,msgUser,req,res,session);
					break;
				
				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 26;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}
		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
		
	}
	
}
private void showEDL013001(EDL013001Message m)
  {
	if (logType != NONE) {
	  
		flexLog("Client Information received (EDL013001).");

		flexLog("User ID: " + m.getH01USERID());
		flexLog("Program: " + m.getH01PROGRM());
		flexLog("Time date: " + m.getH01TIMSYS());
		flexLog("Screen code: " + m.getH01SCRCOD());
		flexLog("Operate code: " + m.getH01OPECOD());
		flexLog("More records" + m.getH01FLGMAS());
		flexLog("Flag 1: " + m.getH01FLGWK1());
		flexLog("Flag 2: " + m.getH01FLGWK2());
		flexLog("Flag 3: " + m.getH01FLGWK3());

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