package datapro.eibs.products;

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

public class JSEDL0910 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_RENEWAL		= 1;
	protected static final int A_RENEWAL		= 2;	

	// entering options
	protected static final int R_ENTER_RENEWAL		= 100;	
	protected static final int A_ENTER_RENEWAL		= 200;
	
	protected String LangPath = "S";

/**
 * JSEDL0910 constructor comment.
 */
public JSEDL0910() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0910");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionRenewal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL091001Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
    String opcode="";
    String opt="";
    String recalc="X";
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
    opcode="0005";
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgLN = (EDL091001Message)ses.getAttribute("lnRenew");
		msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL0910");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01SCRCOD("01");
	 	msgLN.setH01OPECOD(opcode);		
		
		recalc= req.getParameter("H01FLGWK1");
		// all the fields here
	 	java.util.Enumeration enu = msgLN.fieldEnumeration();
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

	 	mc.sendMessage(msgLN); //msgLN.send();	
	 	msgLN.destroy();

	 	flexLog("EDL091001 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL091001")) { 
			try {
				msgLN = new datapro.eibs.beans.EDL091001Message();
				flexLog("EDL091001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLN = (EDL091001Message)newmessage;
			
			//userPO.setIdentifier(msgLN.getE01DEAACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnRenew", msgLN);
			ses.setAttribute("userPO", userPO);
			if (IsNotError) {  // There are no errors
				if (recalc.equals("Y")) {
			  	 	try {
						flexLog("About to call Page: " + LangPath + "EDL0910_ln_enter_renewal.jsp");
						callPage(LangPath + "EDL0910_ln_enter_renewal.jsp", req, res);	
				 	}
				 	catch (Exception e) {
						flexLog("Exception calling page " + e);
				 	}
				} else {
					try {
					flexLog("About to call Page: " + LangPath + "EDL0910_ln_renewal.jsp");
						res.sendRedirect(super.srctx + LangPath + "EDL0910_ln_renewal.jsp");	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			  } 
			  else {
				try {
					flexLog("About to call Page: " + LangPath + "EDL0910_ln_renewal.jsp");
					res.sendRedirect(super.srctx + LangPath + "EDL0910_ln_renewal.jsp");	
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
protected void procActionEnterRenewal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL091001Message msgLN = null;
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
		flexLog("Sending header");
	 	
	 	msgLN = (EDL091001Message)mc.getMessageRecord("EDL091001");
	 	msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL0150");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01SCRCOD("01");
	 	msgLN.setH01OPECOD("0002");
		flexLog("Header has been sended");
		try {
		 	if (req.getParameter("E01DEAACC") != null)
		 	  msgLN.setE01DEAACC(req.getParameter("E01DEAACC"));
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgLN.setE01DEAACC("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		msgLN.send();	
	 	msgLN.destroy();

	 	flexLog("EDL091001 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL091001")) {
			try {
				msgLN = new datapro.eibs.beans.EDL091001Message();
				flexLog("EDL091001 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLN = (EDL091001Message)newmessage;


			userPO.setIdentifier(msgLN.getE01DEAACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("lnRenew", msgLN);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0910_ln_renewal.jsp");
					res.sendRedirect(super.srctx + LangPath + "EDL0910_ln_renewal.jsp");	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0910_ln_enter_renewal.jsp");
					callPage(LangPath + "EDL0910_ln_enter_renewal.jsp", req, res);	
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
protected void procReqEnterRenewal(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0910_ln_enter_renewal.jsp");
		callPage(LangPath + "EDL0910_ln_enter_renewal.jsp", req, res);	
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

		int screen = R_ENTER_RENEWAL;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
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
				case R_ENTER_RENEWAL : 
					procReqEnterRenewal(msgUser, req, res, session);
					break;				
				// Action 
				case A_ENTER_RENEWAL : 
					procActionEnterRenewal(mc, msgUser, req, res, session);
					break;
				case A_RENEWAL :
					procActionRenewal(mc, msgUser, req, res, session);
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
			} finally {
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