package datapro.eibs.invest;

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

public class JSEIE0050 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final  int A_MAINTENANCE		= 2;
	
	protected static final  int A_ENTER_NEW	 	    = 200;
	protected static final  int A_ENTER_MAINT	 	= 400;
	protected static final  int A_ENTER_DELETE	 	= 600;
	protected static final  int A_ENTER_INQUIRY 	    = 800;
	protected static final  int A_ENTER_APPROVAL 	= 1000;
	
	static final int R_ENTER_LIST 	  = 34;
	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEIE0050() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEIE00000");
	
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
protected  void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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


	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE005001Message)mc.getMessageRecord("EIE005001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0001");

		try {
		 	  msgInst.setE01ISIPTY(req.getParameter("TYPE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01ISIPTY("BND");
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
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

		if (newmessage.getFormatName().equals("EIE005001")) {
			try {
				msgInst = new EIE005001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EIE005001Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_basic.jsp");
						callPage(LangPath + "EIE0050_inv_inst_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308P?SCREEN=100");			}
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
protected  void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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




	String opCode = null;
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE005001Message)mc.getMessageRecord("EIE005001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0002");


		try {
		 	  msgInst.setE01ISIIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01ISIIIC(userPO.getIdentifier());
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE005001")) {
			try {
				msgInst = new EIE005001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE005001Message)newmessage;
			
			userPO.setPurpose("M");		
			userPO.setIdentifier(msgInst.getE01ISIIIC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_basic.jsp");
						callPage(LangPath + "EIE0050_inv_inst_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308P?SCREEN=100");			}
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

protected  void procActionEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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




	String opCode = null;
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE005001Message)mc.getMessageRecord("EIE005001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0003");


		try {
		 	  msgInst.setE01ISIIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01ISIIIC(userPO.getIdentifier());
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE005001")) {
			try {
				msgInst = new EIE005001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE005001Message)newmessage;
			
			userPO.setPurpose("I");		
			userPO.setIdentifier(msgInst.getE01ISIIIC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0050_inv_inq_inst_basic.jsp");
						callPage(LangPath + "EIE0050_inv_inq_inst_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308P?SCREEN=100");			}
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

protected  void procActionEnterApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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




	String opCode = null;
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE005001Message)mc.getMessageRecord("EIE005001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0003");


		try {
		 	  msgInst.setE01ISIIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01ISIIIC(userPO.getIdentifier());
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE005001")) {
			try {
				msgInst = new EIE005001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE005001Message)newmessage;
			
			userPO.setPurpose("I");		
			userPO.setIdentifier(msgInst.getE01ISIIIC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0050_inv_app_inst_basic.jsp");
						callPage(LangPath + "EIE0050_inv_app_inst_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0051?SCREEN=1");			}
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



protected  void procActionEnterDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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




	String opCode = null;
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE005001Message)mc.getMessageRecord("EIE005001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0004");


		try {
		 	  msgInst.setE01ISIIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01ISIIIC("0");
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE005001")) {
			try {
				msgInst = new EIE005001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE005001Message)newmessage;
			
			userPO.setPurpose("D");		
			userPO.setIdentifier(msgInst.getE01ISIIIC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_del_confirm.jsp");
						callPage(LangPath + "EIE0050_inv_inst_del_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308P?SCREEN=100");			}
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
protected  void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE005001Message msgInst = null;
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
	 	msgInst = (EIE005001Message)ses.getAttribute("invInst");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgInst.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).trim();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	//msgInst.send();
	 	mc.sendMessage(msgInst);
	 	msgInst.destroy();
	 	flexLog("EIE000001 Message Sent");
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
					msgInst = new EIE005001Message();
					flexLog("EIE000001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EIE005001Message)newmessage;
				// showESD008004(msgInst);
				
				userPO.setIdentifier(msgInst.getE01ISIIIC());
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invInst", msgInst);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_basic_confirm.jsp");
							callPage(LangPath + "EIE0050_inv_inst_basic_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_basic.jsp");
							callPage(LangPath + "EIE0050_inv_inst_basic.jsp", req, res);	
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

private synchronized void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0050_inv_enter_inst.jsp");
			callPage(LangPath + "EIE0050_inv_enter_inst.jsp", req, res);
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

		int screen = A_ENTER_MAINT;
		
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

				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
								
									
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE : 
					procActionEnterDelete(mc, msgUser, req, res, session);
					break;		
				case A_ENTER_INQUIRY : 
					procActionEnterInquiry(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_APPROVAL : 
					procActionEnterApproval(mc, msgUser, req, res, session);
					break;	
					
				case R_ENTER_LIST : 
					procReqEnterSearch(mc, msgUser, req, res, session);
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
