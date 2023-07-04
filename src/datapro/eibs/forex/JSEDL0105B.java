package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class JSEDL0105B extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange Deals
	protected static final int A_PLP			= 4;
	protected static final int A_PLP_MOD			= 6;	
	
	// entering options

	protected static final int A_ENTER_PLP		= 1000;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0105B() {
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


protected void procActionEnterCommPap(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL010502Message msgDeal = null;
	ELEERRMessage msgError = null;
	
	UserPos userPO = null;
	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;

	  opCode = "0002";
	
	// Send Initial data
	try
	{
		msgDeal = (EDL010502Message)mc.getMessageRecord("EDL010502");
	 	msgDeal.setH02USERID(user.getH01USR());
	 	msgDeal.setH02PROGRM("EDL0105");
	 	msgDeal.setH02TIMSYS(getTimeStamp());
	 	msgDeal.setH02SCRCOD("01");
	 	msgDeal.setH02OPECOD(opCode);
	 	msgDeal.setH02FLGWK2("2");
		
		try {
		 	if (req.getParameter("E02DEAREF") != null)
		 	  msgDeal.setE02DEAREF(req.getParameter("E02DEAREF"));
		}
		catch (Exception e)	{
	 	  msgDeal.setE02DEAREF("0");
		}
		try {
		 	if (req.getParameter("E02DLSUSR") != null)
		 	  msgDeal.setE02DLSUSR(req.getParameter("E02DLSUSR"));
		}
		catch (Exception e)	{
	 	  msgDeal.setE02DLSUSR("");
		}
		try {
			if (req.getParameter("E02DEAACC") != null)
			  msgDeal.setE02DEAACC(req.getParameter("E02DEAACC"));
		}
		catch (Exception e)	{
		  msgDeal.setE02DEAACC("");
		}

	 	msgDeal.send();
	 	msgDeal.destroy();
	 	flexLog("EDL010502 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL010502")) {
			try {
				msgDeal = new EDL010502Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgDeal = (EDL010502Message)newmessage;
			

			userPO.setIdentifier(msgDeal.getE02DEAACC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105B_fe_cp.jsp");
						callPage(LangPath + "EDL0105B_fe_cp.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");			}
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
protected void procActionCommPap(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EDL010502Message msgDeal = null;
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


	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgDeal = (EDL010502Message)ses.getAttribute("deal");
		msgDeal.setH02USERID(user.getH01USR());
		msgDeal.setH02PROGRM("EDL0105");
		msgDeal.setH02TIMSYS(getTimeStamp());
		msgDeal.setH02SCRCOD("01");
		msgDeal.setH02OPECOD("0005");


		// all the fields here
		java.util.Enumeration enu = msgDeal.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase().trim();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}


		//msgDeal.send();
		mc.sendMessage(msgDeal);
		msgDeal.destroy();
		flexLog("EDL010502 Message Sent");
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

		try {
			msgDeal = new EDL010502Message();
			flexLog("EDL010502 Message Received");
		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		msgDeal = (EDL010502Message)newmessage;

		userPO.setIdentifier(msgDeal.getE02DEAACC());
		userPO.setCusNum(msgDeal.getE02DEACUN());
		userPO.setBank(user.getE01UBK());
		userPO.setCurrency(msgDeal.getE02DEACCY());
		userPO.setCusName(msgDeal.getE02CUSNA1());
		userPO.setProdCode(msgDeal.getE02DEAPRO());
		//userPO.setHeader1(msgDeal.getE01DLSAMN());
		userPO.setHeader2("2");	
		userPO.setHeader3("PLP");

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("deal", msgDeal);
		ses.setAttribute("userPO", userPO);


		if (IsNotError) {  // There are no errors
			try {
				flexLog("About to call Page: " + LangPath + "EDL0105B_fe_cp_mod.jsp");
				callPage(LangPath + "EDL0105B_fe_cp_mod.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else {  // There are errors
			try {
				flexLog("About to call Page: " + LangPath + "EDL0105B_fe_cp.jsp");
				callPage(LangPath + "EDL0105B_fe_cp.jsp", req, res);	
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

protected void procActionPLPMod(ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {

		msgError = new ELEERRMessage();

		userPO.setHeader8(getFullTime());
			
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0105B_fe_cp_confirm.jsp");
		callPage(LangPath + "EDL0105B_fe_cp_confirm.jsp", req, res);
	} catch (Exception e) {
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

		int screen = A_ENTER_PLP;
		
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
				// BEGIN Fex
				// Action
				case A_PLP :
					procActionCommPap(mc, msgUser, req, res, session);
					break;	
				case A_PLP_MOD :
					 procActionPLPMod(msgUser, req, res, session);
					 break;	
				 														
					 													
									
			// END CD

				// BEGIN Entering

				// Action 
				case A_ENTER_PLP : 
					procActionEnterCommPap(mc, msgUser, req, res, session);
					break;					
					
				// END Entering

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

}