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

public class JSEDL0158 extends datapro.eibs.master.SuperServlet {

	protected static final int R_EXTRA_CHG		= 1;
	protected static final int A_EXTRA_CHG		= 2;
	protected static final int R_EXTRA_CHG_LIST	= 3;
	// entering options
	protected static final int R_ENTER_EXTRA_CHG	= 100;
	protected static final int A_ENTER_EXTRA_CHG	= 200;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0158() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0158");
	
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
protected void procActionEnterExtChg(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
	try {
		try {
			userPO.setIdentifier(req.getParameter("E01DLSACC"));
		}
		catch (Exception e)	{
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqExtChgList(mc, user, req, res, ses);

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
protected void procActionExtChg(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015801Message msgLN = null;
	JBObjList extList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
    String opcode = "";
    String option = "";
    int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	option = req.getParameter("OPT");
	if (option.equals("1")) {
		opcode="0001"; //new
		msgLN = (EDL015801Message)ses.getAttribute("lnExtChg");
	}
	else {
		row = Integer.parseInt(req.getParameter("ROW"));
		extList = (JBObjList)ses.getAttribute("extList");
		extList.setCurrentRow(row);
		msgLN = (EDL015801Message) extList.getRecord();
		if (option.equals("2")) { //maintenance
		  opcode="0005";		  
		} 
		else if (option.equals("3")) opcode="0009";  //delete
	}
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL0158");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01SCRCOD("01");
	 	msgLN.setH01OPECOD(opcode);
		
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
	 	//msgLN.send();
	 	mc.sendMessage(msgLN);
	 	msgLN.destroy();

	 	flexLog("EDL015801 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL015801")) {
			try {
				msgLN = new datapro.eibs.beans.EDL015801Message();
				flexLog("EDL015801 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLN = (EDL015801Message)newmessage;
			
			userPO.setIdentifier(msgLN.getE01DLSACC()); 

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
			  
			  	if (option.equals("1")) ses.removeAttribute("lnExtChg");
				res.setContentType("text/html");
				PrintWriter  out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println("		top.opener.window.location.href='" + super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0158?SCREEN=3'");
				out.println("		top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			  
			}
			else {  // There are errors
				if (option.equals("1")) { //new
					ses.setAttribute("lnExtChg", msgLN);
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_extrachg.jsp");
						callPage(LangPath + "EDL0158_ln_new_extrachg.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (option.equals("2")) { //maintenance					
					
		  			extList.setRecord(msgLN,row);  // Loan Number
					ses.setAttribute("extList", extList);
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_maint_extrachg.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EDL0158_ln_maint_extrachg.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} 
				else if (option.equals("3")) {  //delete 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0158_ln_delete_extrachg.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EDL0158_ln_delete_extrachg.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
protected void procReqEnterExtChg(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
    EDL015801Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgLN = new datapro.eibs.beans.EDL015801Message();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN_EXTRA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnExtChg", msgLN);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0158_ln_enter_extrachg.jsp");
		callPage(LangPath + "EDL0158_ln_enter_extrachg.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqExtChgList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL015801Message msgLN = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgLN = (EDL015801Message)mc.getMessageRecord("EDL015801");
	 	msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL0158");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01SCRCOD("01");
	 	msgLN.setH01OPECOD("0015");
	 	// Get Parameters here		
		try	{ 	
	 		msgLN.setE01DLSACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgLN.send();	
		msgLN.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDL0158_ln_enter_extrachg.jsp");
					callPage(LangPath + "EDL0158_ln_enter_extrachg.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EDL015801")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgLN = (EDL015801Message)newmessage;
				marker = msgLN.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgLN);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			
			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
			try {
				 	flexLog("About to call Page: " + LangPath + "EDL0158_ln_list_extrachg.jsp");
				 	callPage(LangPath + "EDL0158_ln_list_extrachg.jsp", req, res);					
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

		int screen = R_ENTER_EXTRA_CHG;
		
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
				case R_EXTRA_CHG_LIST :
					procReqExtChgList(mc, msgUser, req, res, session);
					break;			
				case A_EXTRA_CHG :
					procActionExtChg(mc, msgUser, req, res, session);
					break;			
				case R_ENTER_EXTRA_CHG : 
					procReqEnterExtChg(msgUser, req, res, session);
					break;
				case A_ENTER_EXTRA_CHG : 
					procActionEnterExtChg(mc, msgUser, req, res, session);
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