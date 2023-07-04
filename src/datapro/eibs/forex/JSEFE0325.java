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

public class JSEFE0325 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange 
	protected static final int R_NEW				= 1;
	protected static final int A_NEW				= 2;	
	protected static final int R_MAINTENANCE		= 3;
	protected static final int A_MAINTENANCE		= 4;
		

	// entering options
	protected static final int R_ENTER_NEW 		= 100;
	protected static final int R_ENTER_MAINT 		= 300;
	
	protected static final int A_ENTER_NEW	 	= 200;
	protected static final int A_ENTER_MAINT		= 400;
	protected static final int A_ENTER_DELETE		= 600;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0325() {
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
/**
 * This method was created in VisualAge.
 */
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0325DSMessage msgFex = null;
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
	
	opCode = "0001";
	
	// Send Initial data
	try
	{
		msgFex = (EFE0325DSMessage)mc.getMessageRecord("EFE0325DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0325");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD(opCode);
		msgFex.setE01FEOCUN(userPO.getCusNum());
		
		try {
		 	if (req.getParameter("E01FEOBNK") != null)
		 	  msgFex.setE01FEOBNK(req.getParameter("E01FEOBNK"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEOBNK("01");
		}
		

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0325DS Message Sent");
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


		if (newmessage.getFormatName().equals("EFE0325DS")) {
			try {
				msgFex = new EFE0325DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0325DSMessage)newmessage;
			// showESD008004(msgFex);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_new.jsp");
						callPage(LangPath + "EFE0325_fe_lim_basic_new.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322S?SCREEN=1&E01FESCUN=" + req.getParameter("E01FEOCUN"));
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

protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0325DSMessage msgFex = null;
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
	
	opCode = "0002";
	
	// Send Initial data
	try
	{
		msgFex = (EFE0325DSMessage)mc.getMessageRecord("EFE0325DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0325");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD(opCode);


		try {
		 	if (req.getParameter("E01FEOCUN") != null)
		 	  msgFex.setE01FEOCUN(req.getParameter("E01FEOCUN"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEOCUN("0");
		}


		try {
		 	if (req.getParameter("E01FEODT1") != null)
		 	  msgFex.setE01FEODT1(req.getParameter("E01FEODT1"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT1("");
		}
		try {
		 	if (req.getParameter("E01FEODT2") != null)
		 	  msgFex.setE01FEODT2(req.getParameter("E01FEODT2"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT2("");
		}
		try {
		 	if (req.getParameter("E01FEODT3") != null)
		 	  msgFex.setE01FEODT3(req.getParameter("E01FEODT3"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT3("");
		}

		
		try {
		 	if (req.getParameter("E01FEOBNK") != null)
		 	  msgFex.setE01FEOBNK(req.getParameter("E01FEOBNK"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEOBNK("01");
		}
		

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0325DS Message Sent");
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





		if (newmessage.getFormatName().equals("EFE0325DS")) {
			try {
				msgFex = new EFE0325DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0325DSMessage)newmessage;
			// showESD008004(msgFex);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_maint.jsp");
						callPage(LangPath + "EFE0325_fe_lim_basic_maint.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322S?SCREEN=1&E01FESCUN=" + req.getParameter("E01FEOCUN"));
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

protected void procActionEnterDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0325DSMessage msgFex = null;
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
	
	opCode = "0004";
	
	// Send Initial data
	try
	{
		msgFex = (EFE0325DSMessage)mc.getMessageRecord("EFE0325DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0325");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD(opCode);

		try {
		 	if (req.getParameter("E01FEOCUN") != null)
		 	  msgFex.setE01FEOCUN(req.getParameter("E01FEOCUN"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEOCUN("0");
		}

		try {
		 	if (req.getParameter("E01FEODT1") != null)
		 	  msgFex.setE01FEODT1(req.getParameter("E01FEODT1"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT1("");
		}
		try {
		 	if (req.getParameter("E01FEODT2") != null)
		 	  msgFex.setE01FEODT2(req.getParameter("E01FEODT2"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT2("");
		}
		try {
		 	if (req.getParameter("E01FEODT3") != null)
		 	  msgFex.setE01FEODT3(req.getParameter("E01FEODT3"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEODT3("");
		}
	
		try {
		 	if (req.getParameter("E01FEOBNK") != null)
		 	  msgFex.setE01FEOBNK(req.getParameter("E01FEOBNK"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEOBNK("01");
		}
		

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0325DS Message Sent");
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

		if (newmessage.getFormatName().equals("EFE0325DS")) {
			try {
				msgFex = new EFE0325DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0325DSMessage)newmessage;
			// showESD008004(msgFex);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			
 
			if (IsNotError) {  // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_confirm_delete.jsp");
							callPage(LangPath + "EFE0325_fe_lim_basic_confirm_delete.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);	
					    } 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0322S?SCREEN=1&E01FESCUN=" + req.getParameter("E01FEOCUN"));
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

protected void procActionMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0325DSMessage msgFex = null;
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
	 	msgFex = (EFE0325DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0325");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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

	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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
					msgFex = new EFE0325DSMessage();
					flexLog("EDL00325 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgFex = (EFE0325DSMessage)newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_confirm.jsp");
							callPage(LangPath + "EFE0325_fe_lim_basic_confirm.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);	
					    } 
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0325P_fe_lim_basic_maint.jsp");
							callPage(LangPath + "EFE0325P_fe_lim_basic_maint.jsp", req, res);	
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

protected void procActionNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0325DSMessage msgFex = null;
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
	 	msgFex = (EFE0325DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0325");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");


	 	// all the fields here
	 	java.util.Enumeration enu = msgFex.fieldEnumeration();
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




	 	//msgFex.send();
	 	mc.sendMessage(msgFex);
	 	msgFex.destroy();
	 	flexLog("EFE0120DS Message Sent");
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
					msgFex = new EFE0325DSMessage();
					flexLog("EDL00325 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgFex = (EFE0325DSMessage)newmessage;
				// showESD008004(msgFex);


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_confirm_new.jsp");
							callPage(LangPath + "EFE0325_fe_lim_basic_confirm_new.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);	
					    } 
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0325_fe_lim_basic_new.jsp");
							callPage(LangPath + "EFE0325_fe_lim_basic_new.jsp", req, res);	
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

		int screen = R_ENTER_MAINT;
		
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
				case A_MAINTENANCE :
					procActionMaint(mc, msgUser, req, res, session);
					break;
				case A_NEW :
					procActionNew(mc, msgUser, req, res, session);
					break;					
									
				// END CD

				// BEGIN Entering
				// Action 
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE : 
					procActionEnterDelete(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
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