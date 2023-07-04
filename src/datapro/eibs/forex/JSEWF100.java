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

public class JSEWF100 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange Deals

	static final int A_MT				= 2;

		
	
	// entering options

	static final int A_ENTER_MT		= 100;
	
	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEWF100() {
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


private void procActionEnterMT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1(userPO.getHeader2());
		
		try {
		 	  msgSwift.setE01SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE01SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFBNK("0");
		}

		try {
		 	  msgSwift.setE01SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
		}
		try {
		 	  msgSwift.setE01SWFAMT(userPO.getHeader1());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
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
				msgSwift = (datapro.eibs.beans.ESWF10001Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ESWF10001Message");
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
						flexLog("About to call Page: " + LangPath + "ESWF100_fe_format.jsp");
						callPage(LangPath + "ESWF100_fe_format.jsp", req, res);	
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
private void procActionMT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	String opeCode = "";
	
    if(req.getParameter("CODOPT").equals("V")){
       opeCode="0006";	
    }
	else opeCode="0005";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgSwift = (ESWF10001Message)ses.getAttribute("swift");
		msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opeCode);
	 	msgSwift.setH01WK1(userPO.getHeader2());

	 	// all the fields here
	 	java.util.Enumeration enu = msgSwift.fieldEnumeration();
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

	 	//msgSwift.send();
	 	mc.sendMessage(msgSwift);
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

				try {
					msgSwift = (datapro.eibs.beans.ESWF10001Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ESWF10001Message");
					flexLog("ESWF10001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgSwift = (ESWF10001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					if(userPO.getHeader2().equals("CDS")){
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130B_fe_cds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else if(userPO.getHeader2().equals("FFS")){
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130B_fe_ffs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				   else if(userPO.getHeader2().equals("TDS")){
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				  else if(userPO.getHeader2().equals("TPS")){
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tps_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tps_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				  else if(userPO.getHeader2().equals("ACC")){
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130B_fe_acc_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_acc_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100_fe_format.jsp");
							callPage(LangPath + "ESWF100_fe_format.jsp", req, res);	
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

		int screen = A_MT;
		
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
				// BEGIN Fex
				// Action
				case A_MT :
					procActionMT(mc, msgUser, req, res, session);
					break;																
									
			// END CD

				// BEGIN Entering
				

				// Action 
				case A_ENTER_MT : 
					procActionEnterMT(mc, msgUser, req, res, session);
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