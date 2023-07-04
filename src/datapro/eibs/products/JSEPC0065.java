package datapro.eibs.products;

/**
 * Insert the type's description here.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class JSEPC0065 extends datapro.eibs.master.SuperServlet {

	protected static final int R_SCHEME_PC		= 1;
	protected static final int A_SCHEME_PC		= 2;
	protected static final int R_SCHEME_PC_LIST	= 3;

	// entering options

	protected static final int R_ENTER_SCHEME_PC	= 100;
	protected static final int A_ENTER_SCHEME_PC	= 200;

	// enquiry options
	
	protected static final int R_ENTER_INQ = 300;
	protected static final int A_ENTER_INQ = 400;
	protected static final int A_INQ_SCHEME= 500;	

	
	protected String LangPath = "S";

/**
 *  constructor comment.
 */
public JSEPC0065() {
	super();
}
/**
 * This method was created by Author
 */
public void destroy() {

	flexLog("free resources used by JSEPC0065");
	
}
/**
 * This method was created by Author
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}


/**
 * This method was created in VisualAge.
 */
protected void procActionEnterSchemePC(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
	InqOption = userPO.getIdentifier();
			
	try {
		try {
			userPO.setIdentifier(req.getParameter("E01PCMACC"));
			userPO.setHeader23(null);
		}
		catch (Exception e)	{
		}

		if (userPO.getIdentifier() == null) {
			userPO.setIdentifier(InqOption);
			userPO.setHeader23(InqOption);
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemePCList(mc, user, req, res, ses);

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
protected void procActionInqScheme(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
	InqOption = userPO.getIdentifier();
			
	try {
		try {
			userPO.setIdentifier(req.getParameter("E01PCMACC"));
			userPO.setHeader23(null);
		}
		catch (Exception e)	{
		}

		if (userPO.getIdentifier() == null) {
			userPO.setIdentifier(InqOption);
			userPO.setHeader23(InqOption);
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemeList(mc, user, req, res, ses);

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
protected void procActionSchemePC(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EPC006501Message msgPC = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgPC = new datapro.eibs.beans.EPC006501Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("pcScheme", msgPC);

	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	
	option = req.getParameter("OPT");
	if (option.equals("1")) {
		opcode="0001"; //new
		msgPC = (EPC006501Message)ses.getAttribute("msgPC");
	}
	else {
		row = Integer.parseInt(req.getParameter("ROW"));
		extList = (JBObjList)ses.getAttribute("extList");
		extList.setCurrentRow(row);
		msgPC = (EPC006501Message) extList.getRecord();
		if (option.equals("2")) { //maintenance
		  opcode="0002";		  
		} 
		else if (option.equals("4")) {opcode="0004";}  //delete
		else if (option.equals("5")) {opcode="0005";}  //sale
	}	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgPC.setH01USERID(user.getH01USR());
	 	msgPC.setH01PROGRM("EPC0065");
	 	msgPC.setH01TIMSYS(getTimeStamp());
	 	msgPC.setH01SCRCOD("01");
	 	msgPC.setH01OPECOD(opcode);
		
		// all the fields here
	 	java.util.Enumeration enu = msgPC.fieldEnumeration();
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
	 	//msgPC.send();
	 	mc.sendMessage(msgPC);
	 	msgPC.destroy();

	 	flexLog("EPC006501 Message Sent");
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

		if (newmessage.getFormatName().equals("EPC006501")) {
			try {
				msgPC = new datapro.eibs.beans.EPC006501Message();
				flexLog("EPC006501 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPC = (EPC006501Message)newmessage;
			
			userPO.setIdentifier(msgPC.getE01PCMACC()); 

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
			  
			  	if (option.equals("1")) ses.removeAttribute("pcScheme");
				res.setContentType("text/html");
				PrintWriter  out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println("		top.opener.window.location.href='" + super.webAppPath + "/servlet/datapro.eibs.products.JSEPC0065?SCREEN=3'");
				out.println("		top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			  
			}
			else {  // There are errors
				if (option.equals("1")) { //new
					ses.setAttribute("pcScheme", msgPC);
					try {
						flexLog("About to call Page: " + LangPath + "EPC0065_pc_new_sch.jsp");
						callPage(LangPath + "EPC0065_pc_new_sch.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (option.equals("2")) { //maintenance					
					
		  			extList.setRecord(msgPC,row);  // Certificate Number
					ses.setAttribute("extList", extList);
					try {
						flexLog("About to call Page: " + LangPath + "EPC0065_pc_maint_sch.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EPC0065_pc_maint_sch.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} 
				else if (option.equals("4")) {  //delete 
					try {
						flexLog("About to call Page: " + LangPath + "EPC0065_pc_delete_sch.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EPC0065_pc_delete_sch.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (option.equals("5")) {  //sale 
					try {
						flexLog("About to call Page: " + LangPath + "EPC0065_pc_sale_sch.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EPC0065_pc_sale_sch.jsp?ROW"+ row);	
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
protected void procReqEnterSchemePC(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
    EPC006501Message msgPC = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgPC = new datapro.eibs.beans.EPC006501Message();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("cd_EXTRA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("pcScheme", msgPC);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EPC0065_pc_enter_sch.jsp");
		callPage(LangPath + "EPC0065_pc_enter_sch.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}




/**
 * This method was created in VisualAge.
 */
protected void procReqSchemePCList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EPC006501Message msgPC = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgPC = (EPC006501Message)mc.getMessageRecord("EPC006501");
	 	msgPC.setH01USERID(user.getH01USR());
	 	msgPC.setH01PROGRM("EPC0065");
	 	msgPC.setH01TIMSYS(getTimeStamp());
	 	msgPC.setH01SCRCOD("01");
	 	msgPC.setH01OPECOD("0015");
	 	// Get Parameters here		
		try	{ 	
	 		msgPC.setE01PCMACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgPC.send();	
		msgPC.destroy();
	
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
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_enter_sch.jsp");
					callPage(LangPath + "EPC0065_pc_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EPC006501")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgPC = (EPC006501Message)newmessage;
				marker = msgPC.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgPC);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusNum(msgPC.getE01PCMCUN());
			}
			catch (Exception e)	{
			}

			try {
				userPO.setCusName(msgPC.getD01PCMCUN());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
				 	flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_sch.jsp");
				 	callPage(LangPath + "EPC0065_pc_list_sch.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
//						flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_inq_sch.jsp");
//						callPage(LangPath + "EPC0065_pc_list_inq_sch.jsp", req, res);					
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_sch.jsp");
					callPage(LangPath + "EPC0065_pc_list_sch.jsp", req, res);					


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
protected void procReqSchemeList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EPC006501Message msgPC = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgPC = (EPC006501Message)mc.getMessageRecord("EPC006501");
		msgPC.setH01USERID(user.getH01USR());
		msgPC.setH01PROGRM("EPC0065");
		msgPC.setH01TIMSYS(getTimeStamp());
		msgPC.setH01SCRCOD("01");
		msgPC.setH01OPECOD("0015");
		// Get Parameters here		
		try	{ 	
			msgPC.setE01PCMACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgPC.send();	
		msgPC.destroy();
	
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
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_enter_sch.jsp");
					callPage(LangPath + "EPC0065_pc_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EPC006501")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgPC = (EPC006501Message)newmessage;
				marker = msgPC.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgPC);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusNum(msgPC.getE01PCMCUN());
			}
			catch (Exception e)	{
			}

			try {
				userPO.setCusName(msgPC.getD01PCMCUN());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_inq.jsp");
					callPage(LangPath + "EPC0065_pc_list_inq.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
//						flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_inq_sch.jsp");
//						callPage(LangPath + "EPC0065_pc_list_inq_sch.jsp", req, res);					
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_list_inq.jsp");
					callPage(LangPath + "EPC0065_pc_list_inq.jsp", req, res);					


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



protected void procReqEnterInq(
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;
	EPC006501Message msgCP = null;

	try {

		msgError = new ELEERRMessage();
		userPO = new UserPos();
		userPO.setOption("CP");
		userPO.setPurpose("INQUIRY");
		msgCP = new EPC006501Message();
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("pcMant", msgCP);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog("About to call Page: " + LangPath + "EPC0065_pc_enter_inquiry.jsp");
		callPage(LangPath + "EPC0065_pc_enter_inquiry.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionEnterInquiry(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPC006501Message msgCP = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		msgCP = (EPC006501Message) mc.getMessageRecord("EPC006501");
		msgCP.setH01USERID(user.getH01USR());
		msgCP.setH01PROGRM("EPC0065");
		msgCP.setH01TIMSYS(getTimeStamp());
		msgCP.setH01SCRCOD("01");
		msgCP.setH01OPECOD("0004");
		try {
			msgCP.setE01PCMACC(req.getParameter("E01PCMACC"));
		} catch (Exception e) {
			msgCP.setE01PCMACC(userPO.getIdentifier());
		}

		msgCP.send();
		msgCP.destroy();
		flexLog("EPC006501 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EPC006501")) {
			try {
				msgCP = new EPC006501Message();
				flexLog("EPC006501 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgCP = (EPC006501Message) newmessage;
				

			userPO.setIdentifier(msgCP.getE01PCMACC());
			userPO.setCusNum(msgCP.getE01PCMCUN());
			userPO.setCusName(msgCP.getD01PCMCUN());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("pcMant", msgCP);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_inq_maint.jsp");
					callPage(LangPath + "EPC0065_pc_inq_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EPC0065_pc_enter_inquiry.jsp");
					callPage(LangPath + "EPC0065_pc_enter_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
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

		int screen = R_ENTER_SCHEME_PC;
		
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
				case R_SCHEME_PC_LIST :
					procReqSchemePCList(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_PC :
					procActionSchemePC(mc, msgUser, req, res, session);
					break;			
				case R_ENTER_SCHEME_PC : 
					procReqEnterSchemePC(msgUser, req, res, session);
					break;
				case A_ENTER_SCHEME_PC : 
					procActionEnterSchemePC(mc, msgUser, req, res, session);
					break;
				case A_INQ_SCHEME : 
					procActionInqScheme(mc, msgUser, req, res, session);
					break;
				case R_ENTER_INQ :
					procReqEnterInq( msgUser, req, res, session);
					break;
				case A_ENTER_INQ :
					procActionEnterInquiry(mc, msgUser, req, res, session);
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