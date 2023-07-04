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
import datapro.eibs.beans.UserPos;
 
import datapro.eibs.sockets.*;

public class JSEFE0140 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange 


	protected static final int A_SPOT				= 2;
	protected static final int A_FAST_SPOT		= 10;
	protected static final int A_OPTION			= 12;
	protected static final int A_FORWARD			= 14;
	protected static final int A_SWAP				= 16;
	protected static final int A_NDF				= 18;	
	protected static final int A_MAINT_DEAL       = 40;

	protected static final int A_SPOT_MOD			= 20;
	protected static final int A_FORWARD_MOD		= 22;
	protected static final int A_OPTION_MOD		= 24;
	protected static final int A_NDF_MOD			= 26;
	protected static final int A_SWAP_MOD			= 28;
	
	protected static final int R_SPOT				= 1;
	protected static final int R_FORWARD			= 3;
	protected static final int R_NDF				= 5;
	protected static final int R_OPTION			= 7;
	protected static final int R_SWAP				= 9;
	protected static final int R_MAINT_DEAL		= 100;
	
	protected static final int R_S_SPOT			= 31;
	protected static final int R_S_FORWARD		= 33;
	protected static final int R_S_NDF			= 35;	
	protected static final int R_S_OPT			= 37;
	protected static final int R_S_SWAP			= 39;
	
	protected static final int A_S_SPOT			= 32;
	protected static final int A_S_FORWARD		= 34;
	protected static final int A_S_NDF			= 36;	
	protected static final int A_S_OPT			= 38;
	protected static final int A_S_SWAP			= 42;
	
	protected static final int R_SC_SPOT			= 41;
	protected static final int R_SC_FORWARD		= 43;
	protected static final int R_SC_NDF			= 45;	
	protected static final int R_SC_OPT			= 47;
	protected static final int R_SC_SWAP			= 49;
	
	protected static final int R_SPECIAL_INST 	= 55;
	protected static final int A_SPECIAL_INST 	= 56;


	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0140() {
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

protected void procActionMaintDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	//userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	  try{
	   userPO = new UserPos();
	  }
	  catch (Exception e)	{
	  }
	String opCode = null;
	
	// Send Initial data
	try
	{
		msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0002");

		try {
		 	msgFex.setE01WKFACC(req.getParameter("E01WKFACC"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WKFACC("0");
		}

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0140DS Message Sent");
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

		if (newmessage.getFormatName().equals("EFE0140DS")) {
			try {
				msgFex = new EFE0140DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0140DSMessage)newmessage;
			// showESD008004(msgFex);
			userPO.setIdentifier(msgFex.getE01WKFACC());
			userPO.setCusNum(msgFex.getE01WKFCUN());
			userPO.setBank(msgFex.getE01WKFBNK());
			userPO.setHeader1(msgFex.getE01WKFAM1());
			userPO.setCusName(msgFex.getD01WKFCP1());
			userPO.setProdCode(msgFex.getE01WKFCLS());
			userPO.setCurrency(msgFex.getE01WKFCCY());
			userPO.setHeader2("2");	
			
			if(msgFex.getE01WKFTYP().equals("OPTI")){
			     userPO.setHeader3("OPTI");				
				}
				else if(msgFex.getE01WKFTYP().equals("SPOT")){
			     userPO.setHeader3("SPOT"); 				
				}
				else if(msgFex.getE01WKFTYP().equals("FWRD")){
				 userPO.setHeader3("FWRD");				
				}
				else if(msgFex.getE01WKFTYP().equals("SWAP")){
				 userPO.setHeader3("SWAP");				
				}
				else if(msgFex.getE01WKFTYP().equals("NDF")){
				 userPO.setHeader3("NDF");				
				}				


			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
				if(msgFex.getE01WKFTYP().equals("OPTI")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_opt.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("SPOT")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_sf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("FWRD")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_forward.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("SWAP")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_swap.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("NDF")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_ndf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
				}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");			
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
protected void procActionSpot(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
	 	msgFex = (EFE0140DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01FLGWK2("");
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
	 	flexLog("EFE0140DS Message Sent");
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
					msgFex = new EFE0140DSMessage();
					flexLog("EDL001301 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgFex = (EFE0140DSMessage)newmessage;
				// showESD008004(msgFex);

				userPO.setIdentifier(msgFex.getE01WKFACC());
				userPO.setCusNum(msgFex.getE01WKFCUN());
				userPO.setBank(msgFex.getE01WKFBNK());
				userPO.setHeader1(msgFex.getE01WKFAM1());
				userPO.setHeader2("2");	
				userPO.setHeader3("SPOT");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf_conf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_sf_conf.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_sf.jsp", req, res);	
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
protected void procActionForward(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
	 	msgFex = (EFE0140DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01FLGWK2("");
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
	 	flexLog("EFE0140DS Message Sent");
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
					msgFex = new EFE0140DSMessage();
					flexLog("EDL001301 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgFex = (EFE0140DSMessage)newmessage;
				// showESD008004(msgFex);

				userPO.setIdentifier(msgFex.getE01WKFACC());
				userPO.setCusNum(msgFex.getE01WKFCUN());
				userPO.setBank(msgFex.getE01WKFBNK());
				userPO.setHeader1(msgFex.getE01WKFAM1());
				userPO.setHeader2("2");	
				userPO.setHeader3("FWRD");



				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward_conf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_forward_conf.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_forward.jsp", req, res);	
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


protected void procActionNDForward(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
	 	msgFex = (EFE0140DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01FLGWK2("");
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
	 	flexLog("EFE0140DS Message Sent");
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
					msgFex = new EFE0140DSMessage();
					flexLog("EDL001301 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE0140DSMessage)newmessage;
				// showESD008004(msgFex);

				userPO.setIdentifier(msgFex.getE01WKFACC());
				userPO.setCusNum(msgFex.getE01WKFCUN());
				userPO.setBank(msgFex.getE01WKFBNK());
				userPO.setHeader1(msgFex.getE01WKFAM1());
				userPO.setHeader2("2");	
				userPO.setHeader3("NDF");


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf_conf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_ndf_conf.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_ndf.jsp", req, res);	
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


protected void procActionSwap(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
	 	msgFex = (EFE0140DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");
	 	msgFex.setH01FLGWK2("");


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
	 	flexLog("EFE0140DS Message Sent");
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
					msgFex = new EFE0140DSMessage();
					flexLog("EDL001301 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE0140DSMessage)newmessage;
				// showESD008004(msgFex);


				userPO.setIdentifier(msgFex.getE01WKFACC());
				userPO.setCusNum(msgFex.getE01WKFCUN());
				userPO.setBank(msgFex.getE01WKFBNK());
				userPO.setHeader1(msgFex.getE01WKFAM1());
				userPO.setHeader2("2");	
				userPO.setHeader3("SWAP");





				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap_conf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_swap_conf.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_swap.jsp", req, res);	
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


protected void procActionOption(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
	 	msgFex = (EFE0140DSMessage)ses.getAttribute("fex");
		//msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
		msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140P");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0005");
	 	msgFex.setH01FLGWK2("");

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
	 	flexLog("EFE0140DS Message Sent");
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
					msgFex = new EFE0140DSMessage();
					flexLog("EDL001301 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgFex = (EFE0140DSMessage)newmessage;
				// showESD008004(msgFex);


				userPO.setIdentifier(msgFex.getE01WKFACC());
				userPO.setCusNum(msgFex.getE01WKFCUN());
				userPO.setBank(msgFex.getE01WKFBNK());
				userPO.setHeader1(msgFex.getE01WKFAM1());
				userPO.setHeader2("2");	
				userPO.setHeader3("OPTI");





				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt_conf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_opt_conf.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_opt.jsp", req, res);	
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


	protected void procReqMaintDeal(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");



		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_enter_maint.jsp");
			callPage(LangPath + "EFE0140B_fe_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	
	protected void procReqSpotMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf.jsp");
			callPage(LangPath + "EFE0140B_fe_basic_sf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqForwardMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward.jsp");
			callPage(LangPath + "EFE0140B_fe_basic_forward.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqNDMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf.jsp");
			callPage(LangPath + "EFE0140B_fe_basic_ndf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

	protected void procReqSwapMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap.jsp");
			callPage(LangPath + "EFE0140B_fe_basic_swap.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	
	protected void procReqOptionMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt.jsp");
			callPage(LangPath + "EFE0140B_fe_basic_opt.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

protected void procReqSwift100SPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
				msgSwift = new ESWF10001Message();
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
						flexLog("About to call Page: " + LangPath + "ESWF100M_fe_sf_format.jsp");
						callPage(LangPath + "ESWF100M_fe_sf_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_sf_mod.jsp", req, res);
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

protected void procReqSwift100FWR(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
				msgSwift = new ESWF10001Message();
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
						flexLog("About to call Page: " + LangPath + "ESWF100M_fe_fw_format.jsp");
						callPage(LangPath + "ESWF100M_fe_fw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_forward_mod.jsp", req, res);
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

protected void procReqSwift100NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
				msgSwift = new ESWF10001Message();
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
						flexLog("About to call Page: " + LangPath + "ESWF100M_fe_nd_format.jsp");
						callPage(LangPath + "ESWF100M_fe_nd_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_ndf_mod.jsp", req, res);
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

protected void procReqSwift100OPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
				msgSwift = new ESWF10001Message();
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
						flexLog("About to call Page: " + LangPath + "ESWF100M_fe_op_format.jsp");
						callPage(LangPath + "ESWF100M_fe_op_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_opt_mod.jsp", req, res);
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


protected void procReqSwift100SWP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
				msgSwift = new ESWF10001Message();
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
						flexLog("About to call Page: " + LangPath + "ESWF100M_fe_sw_format.jsp");
						callPage(LangPath + "ESWF100M_fe_sw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_swap_mod.jsp", req, res);
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


protected void procReqSwift300SPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300M_fe_sf_format.jsp");
						callPage(LangPath + "ESWF300M_fe_sf_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_sf_mod.jsp", req, res);
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

protected void procReqSwift300FWR(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300M_fe_fw_format.jsp");
						callPage(LangPath + "ESWF300M_fe_fw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_forward_mod.jsp", req, res);
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

protected void procReqSwift300NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300M_fe_nd_format.jsp");
						callPage(LangPath + "ESWF300M_fe_nd_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_ndf_mod.jsp", req, res);
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

protected void procReqSwift300OPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300M_fe_op_format.jsp");
						callPage(LangPath + "ESWF300M_fe_op_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_option_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_option_mod.jsp", req, res);
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

protected void procReqSwift300SWP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300M_fe_sw_format.jsp");
						callPage(LangPath + "ESWF300M_fe_sw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap_mod.jsp");
						callPage(LangPath + "EFE0140B_fe_basic_swap_mod.jsp", req, res);
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


protected void procActionSwift100SPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1("1");

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
					msgSwift = new ESWF10001Message();
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
						try {
							    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf_mod.jsp");
								callPage(LangPath + "EFE0140B_fe_basic_sf_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100M_fe_sf_format.jsp");
							callPage(LangPath + "ESWF100M_fe_sf_format.jsp", req, res);	
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
	
protected void procActionSwift100FWR(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1("1");

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
					msgSwift = new ESWF10001Message();
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
						try {
							    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward_mod.jsp");
								callPage(LangPath + "EFE0140B_fe_basic_forward_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100M_fe_fw_format.jsp");
							callPage(LangPath + "ESWF100M_fe_fw_format.jsp", req, res);	
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
	
protected void procActionSwift100NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1("1");

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
					msgSwift = new ESWF10001Message();
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
						try {
							    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf_mod.jsp");
								callPage(LangPath + "EFE0140B_fe_basic_ndf_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100M_fe_nd_format.jsp");
							callPage(LangPath + "ESWF100M_fe_nd_format.jsp", req, res);	
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
		
protected void procActionSwift100OPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1("1");

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
					msgSwift = new ESWF10001Message();
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
						try {
							    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt_mod.jsp");
								callPage(LangPath + "EFE0140B_fe_basic_opt_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100M_fe_op_format.jsp");
							callPage(LangPath + "ESWF100M_fe_op_format.jsp", req, res);	
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
		
protected void procActionSwift100SWP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	 	msgSwift.setH01WK1("1");

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
					msgSwift = new ESWF10001Message();
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
						try {
							    flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap_mod.jsp");
								callPage(LangPath + "EFE0140B_fe_basic_swap_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100M_fe_sw_format.jsp");
							callPage(LangPath + "ESWF100M_fe_sw_format.jsp", req, res);	
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


	protected void procReqEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE(opCode);
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");
			msgCD.setH05WK1("M");
			msgCD.send();
			msgCD.destroy();
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
					msgCD = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_special_inst.jsp");
						callPage(LangPath + "EFE0140B_fe_special_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if(userPO.getHeader3().equals("SPOT") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf_mod.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_sf_mod.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("FWRD") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward_mod.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_forward_mod.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("NDF")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf_mod.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_ndf_mod.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("SWAP") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap_mod.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_swap_mod.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("OPTI")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt_mod.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_opt_mod.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
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


	protected void procActionEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
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
			msgCD = (ESD000005Message) ses.getAttribute("cdInst");
			//msgCD = (ESD000005Message)mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE("0005");
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");
			msgCD.setH05WK1("M");
			
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
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
					msgCD = new ESD000005Message();
					flexLog("ESD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE05ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if(userPO.getHeader3().equals("SPOT") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_sf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_sf.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("FWRD") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_forward.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_forward.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("NDF")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_ndf.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_ndf.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("SWAP") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_swap.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_swap.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("OPTI")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0140B_fe_basic_opt.jsp");
							callPage(LangPath + "EFE0140B_fe_basic_opt.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140B_fe_special_inst.jsp");
						callPage(LangPath + "EFE0140B_fe_special_inst.jsp", req, res);
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

		int screen = A_SPOT;
		
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
				case A_SPOT :
					procActionSpot(mc, msgUser, req, res, session);
					break;
				case A_OPTION :
					procActionOption(mc, msgUser, req, res, session);
					break;	
				case A_FORWARD :
					procActionForward(mc, msgUser, req, res, session);
					break;					
				case A_SWAP :
					procActionSwap(mc, msgUser, req, res, session);
					break;	
				case A_NDF :
					procActionNDForward(mc, msgUser, req, res, session);
					break;	
				case A_MAINT_DEAL :
					procActionMaintDeal(mc, msgUser, req, res, session);
					break;
					
					
				case R_SPOT :
				     procReqSpotMod(msgUser, req, res, session);
					 break;
				case R_FORWARD :
				     procReqForwardMod(msgUser, req, res, session);
					 break;
				case R_NDF :
				     procReqNDMod(msgUser, req, res, session);
					 break;
				case R_OPTION :
				     procReqOptionMod(msgUser, req, res, session);
					 break;
				case R_SWAP :
				     procReqSwapMod(msgUser, req, res, session);
					 break;
				case R_MAINT_DEAL :
				     procReqMaintDeal(msgUser, req, res, session);
					 break;


   				case R_S_SPOT : 
					procReqSwift100SPT(mc, msgUser, req, res, session);
					break;					
   				case R_S_FORWARD : 
					procReqSwift100FWR(mc, msgUser, req, res, session);
					break;					
   				case R_S_NDF : 
					procReqSwift100NDF(mc, msgUser, req, res, session);
					break;					
   				case R_S_OPT : 
					procReqSwift100OPT(mc, msgUser, req, res, session);
					break;					
   				case R_S_SWAP : 
					procReqSwift100SWP(mc, msgUser, req, res, session);
					break;					

   				case A_S_SPOT : 
					procActionSwift100SPT(mc, msgUser, req, res, session);
					break;					
   				case A_S_FORWARD : 
					procActionSwift100FWR(mc, msgUser, req, res, session);
					break;					
   				case A_S_NDF : 
					procActionSwift100NDF(mc, msgUser, req, res, session);
					break;					
   				case A_S_OPT : 
					procActionSwift100OPT(mc, msgUser, req, res, session);
					break;					
   				case A_S_SWAP : 
					procActionSwift100SWP(mc, msgUser, req, res, session);
					break;					
	 

   				case R_SC_SPOT : 
					procReqSwift300SPT(mc, msgUser, req, res, session);
					break;					
   				case R_SC_FORWARD : 
					procReqSwift300FWR(mc, msgUser, req, res, session);
					break;					
   				case R_SC_NDF : 
					procReqSwift300NDF(mc, msgUser, req, res, session);
					break;					
   				case R_SC_OPT : 
					procReqSwift300OPT(mc, msgUser, req, res, session);
					break;					
   				case R_SC_SWAP : 
					procReqSwift300SWP(mc, msgUser, req, res, session);
					break;
										
				case R_SPECIAL_INST :
					procReqEspInst(mc, msgUser, req, res, session);
					break;
				case A_SPECIAL_INST :
				     procActionEspInst(mc, msgUser, req, res, session);
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