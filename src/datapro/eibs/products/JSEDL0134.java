package datapro.eibs.products;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSEDL0134 extends datapro.eibs.master.SuperServlet {

	protected static final int R_CUENTAS				= 1;
	protected static final int A_CUENTAS				= 2;
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEDL0134() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEDL0134(int logType) {
	super(logType);
	
}
/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
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

		int screen = R_CUENTAS;
		
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
				case R_CUENTAS :
					procReqCuentas(mc, msgUser, req, res, session);
					break;
				case A_CUENTAS :
					procActionCuentas(mc, msgUser, req, res, session);
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


protected void procReqCuentas(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				


	EDL013401Message ptmo = null;
	UserPos	userPO = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	} catch (Exception e) {
		flexLog("Error: " + e);
	}
	
// Send Initial data

	try
	{

		ptmo = (datapro.eibs.beans.EDL013401Message)mc.getMessageRecord("EDL013401");
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	 	
	 	ptmo.setH01USERID(user.getH01USR());
	 	ptmo.setH01PROGRM("EDL0134");
	 	ptmo.setH01TIMSYS(getTimeStamp());
	 	ptmo.setH01SCRCOD("01");
	 	ptmo.setH01OPECOD("0002");
	 	ptmo.setE01DEAACC(userPO.getIdentifier());
	 	
		mc.sendMessage(ptmo);
	 	ptmo.destroy();
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

	try {
	 	newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL013401")) {
			ptmo = (EDL013401Message)newmessage;
			ses.setAttribute("ptmo", ptmo);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0134_ln_reestructura.jsp");
					callPage(LangPath + "EDL0134_ln_reestructura.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
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

protected void procActionCuentas(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				


	EDL013401Message ptmo = null;
	UserPos	userPO = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	} catch (Exception e) {
		flexLog("Error: " + e);
	}
	
// Send Initial data

	try
	{

		ptmo = (datapro.eibs.beans.EDL013401Message)ses.getAttribute("ptmo");
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	 	
	 	ptmo.setH01USERID(user.getH01USR());
	 	ptmo.setH01PROGRM("EDL0134");
	 	ptmo.setH01TIMSYS(getTimeStamp());
	 	ptmo.setH01SCRCOD("01");
	 	ptmo.setH01OPECOD("0005");
	 	
	 	// all the fields here
	 	java.util.Enumeration enu = ptmo.fieldEnumeration();
		MessageField field = null;
		String value = null;
		String subName = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				} 
			}
			catch (Exception e) {
				subName = field.getTag().substring(0,6);
				if (subName.equals("E01FLG")) field.setString("");
			}	
		}
	 	
		mc.sendMessage(ptmo);
	 	ptmo.destroy();
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

	try {
	 	newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL013401")) {
			ptmo = (EDL013401Message)newmessage;
			ses.setAttribute("ptmo", ptmo);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0134_ln_reestructura.jsp");
					callPage(LangPath + "EDL0134_ln_reestructura.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
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