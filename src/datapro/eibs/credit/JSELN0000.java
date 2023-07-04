package datapro.eibs.credit;

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

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSELN0000 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_MAINTENANCE		= 3;
	protected static final int A_MAINTENANCE		= 4;
	protected static final int A_DELETE			= 5;
	protected static final int R_SPECIAL_INST		= 15;
	protected static final int A_SPECIAL_INST		= 16;
	protected static final int R_CODES			= 21;
	protected static final int A_CODES			= 22;

	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_ENTER	 		= 200;
	protected static final int R_ENTER_DELETE		= 300;
	protected static final int A_ENTER_DELETE		= 400;
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELN0000() {
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
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN000001Message msgCL = null;
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
		msgCL = (ELN000001Message)mc.getMessageRecord("ELN000001");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0000");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD("0002");
		try {
			msgCL.setE01WLNNUM(req.getParameter("LNENUM"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01WLNNUM("0");
		}
		try {
			msgCL.setE01WLNCUN(req.getParameter("CUSNUM"));
		}
		catch (Exception e)	{
	 	  	msgCL.setE01WLNCUN("0");
		}

		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN000001 Message Sent");
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

		if (newmessage.getFormatName().equals("ELN000001")) {

			msgCL = (ELN000001Message)newmessage;

			userPO.setCusNum(msgCL.getE01WLNCUN());
			userPO.setCreditLineNum(msgCL.getE01WLNNUM());
			userPO.setCurrency(msgCL.getE01WLNCCY());
			userPO.setIdentifier(Util.formatID(userPO.getCusNum(), userPO.getCreditLineNum()));
			userPO.setHeader1(msgCL.getE01WLNCCU());
			userPO.setHeader2(msgCL.getE01WLNCUN());
			userPO.setHeader3(msgCL.getE01CUSNA1());
			
			if(msgCL.getE01WLNCOU()==null || msgCL.getE01WLNCOU().trim().equals("") ){
				msgCL.setH01OPECOD("0001");
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clMant", msgCL);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_maint.jsp");
					callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_enter_maint.jsp");
					callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);	
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
protected void procActionEnterDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN000001Message msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	boolean reqThroughMaint = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	if(req.getParameter("OPTION")!=null){		
		reqThroughMaint = req.getParameter("OPTION").equals("M");
		if(reqThroughMaint)userPO.setOption("M");
	}

	// Send Initial data
	try
	{
		msgCL = (ELN000001Message)mc.getMessageRecord("ELN000001");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0000");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD("0002");
	 	
		try {
			if(req.getParameter("LNENUM")==null){				
				msgCL.setE01WLNNUM(userPO.getCreditLineNum());
			}else{
				msgCL.setE01WLNNUM(req.getParameter("LNENUM"));
			}
			
		}catch (Exception e) {
	 	  	msgCL.setE01WLNNUM("0");
		}
		try {
			if(req.getParameter("CUSNUM") == null){
				msgCL.setE01WLNCUN(userPO.getCusNum());
			} else {
				msgCL.setE01WLNCUN(req.getParameter("CUSNUM"));
			}
			
		} catch (Exception e) {
	 	  	msgCL.setE01WLNCUN("0");
		}

		msgCL.send();	
	 	msgCL.destroy();
	 	flexLog("ELN000001 Message Sent");
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

		if (newmessage.getFormatName().equals("ELN000001")) {
			try {
				msgCL = (datapro.eibs.beans.ELN000001Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELN000001Message");
				flexLog("ELN000001 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ELN000001Message)newmessage;

			userPO.setCusNum(msgCL.getE01WLNCUN());
			userPO.setCreditLineNum(msgCL.getE01WLNNUM());
			userPO.setIdentifier(Util.formatID(userPO.getCusNum(), userPO.getCreditLineNum()));
			userPO.setHeader1(msgCL.getE01WLNCCU());
			userPO.setHeader2(msgCL.getE01WLNCUN());
			userPO.setHeader3(msgCL.getE01CUSNA1());
			userPO.setCurrency(msgCL.getE01WLNCCY());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clDel", msgCL);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_delete.jsp");
					callPage(LangPath + "ELN0000_cl_delete.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				if(reqThroughMaint){
		        	try {
					    flexLog("About to call Page1: " + LangPath + "ELN0000_cl_enter_maint.jsp");
						callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
		        	
		        }else{
		        	try {
					    flexLog("About to call Page1: " + LangPath + "ELN0000_cl_enter_delete.jsp");
						callPage(LangPath + "ELN0000_cl_enter_delete.jsp", req, res);
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
protected void procActionEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	MessageRecord newmessage = null;
	ESD000005Message msgCL = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		msgCL = (ESD000005Message) ses.getAttribute("clInst");
		//msgCL = (ESD000005Message)mc.getMessageRecord("ESD000005");
		msgCL.setH05USR(user.getH01USR());
		msgCL.setH05PGM("ELN0000");
		msgCL.setH05TIM(getTimeStamp());
		msgCL.setH05SCR("01");
		msgCL.setH05OPE("0005");
	 	msgCL.setH05WK1("M");
		msgCL.setE05TYP("7");
		msgCL.setE05ACC(userPO.getCusNum());
		msgCL.setE05LNE(userPO.getCreditLineNum());

		// all the fields here
	 	java.util.Enumeration enu = msgCL.fieldEnumeration();
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

		//msgCL.send();
		mc.sendMessage(msgCL);
		msgCL.destroy();
		flexLog("ESD000005 Message Sent");
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
		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000005Message();
				flexLog("ESD000005 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			msgCL = (ESD000005Message) newmessage;
			// showESD008004(msgCL);

			userPO.setIdentifier(msgCL.getE05ACC());
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("clInst", msgCL);
			ses.setAttribute("userPO", userPO);
			if (IsNotError) { // There are no errors
				try {
					{
						flexLog("About to call Page: " + LangPath + "ELN0000_cl_maint.jsp");
						callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_enter_maint.jsp");
					callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);
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
/**
 * This method was created in VisualAge.
 */
protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN000001Message msgCL = null;
	EFT000010Message msgCWLNw = null;
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
		flexLog("Send Initial Data");
	 	msgCL = (ELN000001Message)ses.getAttribute("clMant");
		//msgCL = (ELN000001Message)mc.getMessageRecord("ELN000001");
		msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("EDL0130");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD("0005");

		// all the fields here
	 	java.util.Enumeration enu = msgCL.fieldEnumeration();
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

	 	//msgCL.send();
	 	mc.sendMessage(msgCL);
	 	msgCL.destroy();
	 	flexLog("ELN000001 Message Sent");
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

		if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCWLNw = new datapro.eibs.beans.EFT000010Message();
					flexLog("EFT000010 Message Sent");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgCWLNw = (EFT000010Message)newmessage;
				// showESD008004(msgCL);

				userPO.setIdentifier(msgCWLNw.getE10DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCWLNw);
				ses.setAttribute("userPO", userPO);

				try {
				    flexLog("About to call Page1: " + LangPath + "EDL0130_cd_print_op.jsp");
					callPage(LangPath + "EDL0130_cd_print_op.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		}
		else if (newmessage.getFormatName().equals("ELN000001")) {
				try {
					msgCL = new datapro.eibs.beans.ELN000001Message();
					flexLog("ELN000001 Message Sent");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgCL = (ELN000001Message)newmessage;
				// showESD008004(msgCL);

				userPO.setIdentifier(msgCL.getE01WLNNUM());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("clMant", msgCL);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page1: " + LangPath + "ELN0000_cl_enter_maint.jsp");
							callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "ELN0000_cl_maint.jsp");
						callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);	
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
protected void procActionDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN000001Message msgCL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	boolean reqThroughMaint = false;
	
	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	reqThroughMaint = userPO.getOption().equals("M");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	//msgCL = (ELN000001Message)ses.getAttribute("clDel");
		msgCL = (ELN000001Message)mc.getMessageRecord("ELN000001");
		msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("ELN0000");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD("0009");

		msgCL.setE01WLNCUN(userPO.getCusNum());
		msgCL.setE01WLNNUM(userPO.getCreditLineNum());

	 	//msgCL.send();
	 	mc.sendMessage(msgCL);
	 	//msgCL.destroy();
	 	flexLog("ELN000001 Message Sent");
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
		
		ses.setAttribute("userPO", userPO);

		if (IsNotError) {  // There are no errors
			        ses.removeAttribute("clDel");
			        if(reqThroughMaint){
			        	try {
						    flexLog("About to call Page1: " + LangPath + "ELN0000_cl_enter_maint.jsp");
							callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);
							}
							catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
			        	
			        }else{
			        	try {
						    flexLog("About to call Page1: " + LangPath + "ELN0000_cl_enter_delete.jsp");
							callPage(LangPath + "ELN0000_cl_enter_delete.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
			        	
			        }
					
		}
		else {  // There are errors
			 		ses.setAttribute("error", msgError);		
					try {
						flexLog("About to call Page2: " + LangPath + "ELN0000_cl_maint.jsp");
						callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);	
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
protected void procActionSpecialCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgCL = null;
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
		flexLog("Send Initial Data");
	 	msgCL = (ESD000002Message)ses.getAttribute("clCodes");
		//msgCL = (ESD000002Message)mc.getMessageRecord("ESD000002");
		msgCL.setH02USR(user.getH01USR());
	 	msgCL.setH02PGM("ELN0000");
	 	msgCL.setH02TIM(getTimeStamp());
	 	msgCL.setH02SCR("01");
	 	msgCL.setH02WK1("M");
	 	msgCL.setH02OPE("0005");

		// all the fields here
	 	java.util.Enumeration enu = msgCL.fieldEnumeration();
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
		
	 	msgCL.setE02ACC(userPO.getCusNum());
	 	msgCL.setE02LNE(userPO.getCreditLineNum());

	 	//msgCL.send();
	 	mc.sendMessage(msgCL);
	 	msgCL.destroy();
	 	flexLog("ESD000002 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000002Message();
				flexLog("ESD000002 Message Sent");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ESD000002Message)newmessage;
			// showESD008004(msgCL);

			userPO.setIdentifier(msgCL.getE02ACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("clCodes", msgCL);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors 
				try {
					{ 	
					    flexLog("About to call Page: " + LangPath + "ELN0000_cl_maint.jsp");
						callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_codes.jsp");
					callPage(LangPath + "ELN0000_cl_codes.jsp", req, res);	
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
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("CL");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELN0000_cl_enter_maint.jsp");
		callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterDelete(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		userPO.setOption("CL");
		userPO.setPurpose("DELETE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ELN0000_cl_enter_delete.jsp");
		callPage(LangPath + "ELN0000_cl_enter_delete.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgCL = null;
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

	String opCode = "0002";

	// Send Initial data
	try
	{
		msgCL = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msgCL.setH05USR(user.getH01USR());
	 	msgCL.setH05PGM("EDL0130");
	 	msgCL.setH05TIM(getTimeStamp());
	 	msgCL.setH05SCR("01");
	 	msgCL.setH05WK1("M");
	 	msgCL.setH05OPE(opCode);
	 	//msgCL.setE05ACC(userPO.getIdentifier());
		msgCL.setE05TYP("7");
		msgCL.setE05ACC(userPO.getCusNum());
		msgCL.setE05LNE(userPO.getCreditLineNum());

		msgCL.send();	
	 	msgCL.destroy();
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

		if (newmessage.getFormatName().equals("ESD000005")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ESD000005Message)newmessage;
			// showESD008004(msgCL);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("clInst", msgCL);

			if (IsNotError) {  // There are no errors 
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_special_inst.jsp");
					callPage(LangPath + "ELN0000_cl_special_inst.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELN0000_cl_maint.jsp");
					callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);	
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
protected void procReqMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELN000001Message msgCL = null;
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

	String opCode = null;
	  opCode = "0002";
	
	// Send Initial data
	try
	{
		msgCL = (ELN000001Message)mc.getMessageRecord("ELN000001");
	 	msgCL.setH01USERID(user.getH01USR());
	 	msgCL.setH01PROGRM("EDL0130");
	 	msgCL.setH01TIMSYS(getTimeStamp());
	 	msgCL.setH01SCRCOD("01");
	 	msgCL.setH01OPECOD(opCode);
	 	msgCL.setE01WLNCUN(userPO.getCusNum());
	 	msgCL.setE01WLNNUM(userPO.getCreditLineNum());
		msgCL.send();	
	 	msgCL.destroy();
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

		if (newmessage.getFormatName().equals("ELN000001")) {
			msgCL = (ELN000001Message)newmessage;
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("clMant", msgCL);	
 
			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "ELN0000_cl_maint.jsp");
					callPage(LangPath + "ELN0000_cl_maint.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "ELN0000_cl_enter_maint.jsp");
					callPage(LangPath + "ELN0000_cl_enter_maint.jsp", req, res);	
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
protected void procReqSpecialCodes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgCL = null;
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
		msgCL = (ESD000002Message)mc.getMessageRecord("ESD000002");
	 	msgCL.setH02USR(user.getH01USR());
	 	msgCL.setH02PGM("ELN0000");
	 	msgCL.setH02TIM(getTimeStamp());
	 	msgCL.setH02SCR("01");
	 	msgCL.setH02WK1("M");
	 	msgCL.setH02OPE("0002");
	 	msgCL.setE02ACC(userPO.getCusNum());
	 	msgCL.setE02LNE(userPO.getCreditLineNum());
	 	msgCL.send();	
	 	msgCL.destroy();
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

		if (newmessage.getFormatName().equals("ESD000002")) {
			try {
				msgCL = new datapro.eibs.beans.ESD000002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCL = (ESD000002Message)newmessage;
			// showESD008004(msgCL);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("clCodes", msgCL);

			// 20110218 asked to show page anyway, errors or not.
			// if (IsNotError) { // There are no errors
			try {

			    flexLog("About to call Page: " + LangPath
				    + "ELN0000_cl_codes.jsp");
			    callPage(LangPath + "ELN0000_cl_codes.jsp", req, res);
			} catch (Exception e) {
			    flexLog("Exception calling page " + e);
			}
			/*
	                 * } else { // There are errors try { flexLog("About to call
	                 * Page: " + LangPath + "ELN0000_cl_maint.jsp");
	                 * callPage(LangPath + "ELN0000_cl_maint.jsp", req, res); }
	                 * catch (Exception e) { flexLog("Exception calling page " + e); } }
	                 */
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

		int screen = R_ENTER;
		
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
				// BEGIN CD
				// Request
				case R_MAINTENANCE :
					procReqMaintenance(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_INST :
					procReqEspInst(mc, msgUser, req, res, session);
					break;
				case R_CODES :
					procReqSpecialCodes(mc, msgUser, req, res, session);
					break;
						
				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
				case A_SPECIAL_INST :
					procActionEspInst(mc, msgUser, req, res, session);
					break;
				case A_CODES :
					procActionSpecialCodes(mc, msgUser, req, res, session);
					break;
				case A_DELETE :
					procActionDelete(mc, msgUser, req, res, session);
					break;
				// END Cl

				// BEGIN Entering
				// Request
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER : 
					procActionEnter(mc, msgUser, req, res, session);
					break;
				case R_ENTER_DELETE : 
					procReqEnterDelete(msgUser, req, res, session);
					break;
				// Action 
				case A_ENTER_DELETE : 
					procActionEnterDelete(mc, msgUser, req, res, session);
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
//				return;
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