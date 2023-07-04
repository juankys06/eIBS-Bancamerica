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

public class JSEDL0152 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_TRANSACTION		= 5;
	protected static final int A_TRANSACTION		= 6;
	protected static final int R_OTHERS_TRANS		= 27;
	
	protected static final int A_INQUIRY_TRAFECI	= 4;
	// entering options
	protected static final int R_ENTER_TRANSAC	= 700;
	protected static final int A_ENTER_TRANSAC	= 800;

	protected static final int R_ENTER_CAMBIOTASA	= 900;
	protected static final int A_ENTER_CAMBIOTASA	= 1000;
	protected static final int A_EXCHANGE = 1100;
	
	protected static final int A_TRANSACTION_FECI 	= 16;
	protected static final int R_ENTER_TRAFECI		= 1700;
	protected static final int A_ENTER_TRAFECI		= 1800;

	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0152() {
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
protected void procActionEnterTransac(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		
	try {
		try {
			userPO.setIdentifier(req.getParameter("E03DEAACC"));
		}
		catch (Exception e)	{
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqTransaction(mc, user, req, res, ses);

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
protected void procActionEnterCambiotasa(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		
	try {
		try {
			userPO.setIdentifier(req.getParameter("E03DEAACC"));
		}
		catch (Exception e)	{
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqExchangeRate(mc, user, req, res, ses);

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
protected void procReqExchangeRate(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015206Message msgLN = null;
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
		msgLN = (EDL015206Message) mc.getMessageRecord("EDL015206");
		msgLN.setH06USERID(user.getH01USR());
		msgLN.setH06PROGRM("EDL0150");
		msgLN.setH06TIMSYS(""); //getTimeStamp()
		msgLN.setH06SCRCOD("01");
		msgLN.setH06OPECOD(opCode);
		msgLN.setE06DEAACC(userPO.getIdentifier());
		msgLN.send();
		msgLN.destroy();
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
			//showERROR(msgError);
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

		if (newmessage.getFormatName().equals("EDL015206")) {
			try {
				msgLN = new EDL015206Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgLN = (EDL015206Message) newmessage;
			// showESD008004(msgLN);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnRate", msgLN);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0152_ln_xchg_rate.jsp");
					callPage(LangPath + "EDL0152_ln_xchg_rate.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0152_ln_enter_cambiotasa.jsp");
					callPage(LangPath + "EDL0152_ln_enter_cambiotasa.jsp", req, res);
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

protected void procActionExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015206Message msgLN = null;
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
			flexLog("Send Initial Data");
			msgLN = (EDL015206Message) ses.getAttribute("lnRate");
			//msgLN = (EDL015206Message)mc.getMessageRecord("EDL015206");
			msgLN.setH06USERID(user.getH01USR());
			msgLN.setH06PROGRM("EDL0150");
			msgLN.setH06TIMSYS(getTimeStamp());
			msgLN.setH06SCRCOD("01");
			msgLN.setH06OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgLN.fieldEnumeration();
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

			//msgLN.send();
			mc.sendMessage(msgLN);
			msgLN.destroy();

			flexLog("EDL015206 Message Sent");
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
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDL015206")) {
				try {
					msgLN = new EDL015206Message();
					flexLog("EDL015206 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015206Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnRate", msgLN);

				if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EDL0152_ln_enter_cambiotasa.jsp");
							callPage(LangPath + "EDL0152_ln_enter_cambiotasa.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				 else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0152_ln_xchg_rate.jsp");
						callPage(LangPath + "EDL0152_ln_xchg_rate.jsp", req, res);
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
protected void procActionEnterTraFeci(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		
	try {
		try {
			userPO.setIdentifier(req.getParameter("E03DEAACC"));
		}
		catch (Exception e)	{
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqTransactionFeci(mc, user, req, res, ses);

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	

}

protected void procActionEnterTraFeciInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		
	try {
		//userPO.setPurpose("INQUIRY");

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqTransactionFeci(mc, user, req, res, ses);

	} catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
}


/**
 * This method was created in VisualAge.
 */
protected void procActionTransaction(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015203Message msgLN = null;
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
	 	msgLN = (EDL015203Message)ses.getAttribute("lnTransac");
		//msgLN = (EDL015203Message)mc.getMessageRecord("EDL015203");
		msgLN.setH03USERID(user.getH01USR());
	 	msgLN.setH03PROGRM("EDL0130");
	 	msgLN.setH03TIMSYS(getTimeStamp());
	 	msgLN.setH03SCRCOD("01");
	 	msgLN.setH03OPECOD("0005");

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

	 	flexLog("EDL015203 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL015203")) {
			try {
				msgLN = new datapro.eibs.beans.EDL015203Message();
				flexLog("EDL015203 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLN = (EDL015203Message)newmessage;
			// showESD008004(msgLN);

			userPO.setIdentifier(msgLN.getE03DEAACC()); 

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnTransac", msgLN);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					{ 	
					    flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_transac.jsp");
						callPage(LangPath + "EDL0150_ln_enter_transac.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_transac.jsp");
					callPage(LangPath + "EDL0150_ln_transac.jsp", req, res);	
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
protected void procActionTransactionFeci(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015203Message msgLN = null;
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
		msgLN = (EDL015203Message)ses.getAttribute("lnTransac");
		//msgLN = (EDL015203Message)mc.getMessageRecord("EDL015203");
		msgLN.setH03USERID(user.getH01USR());
		msgLN.setH03PROGRM("EDL0130");
		msgLN.setH03TIMSYS(getTimeStamp());
		msgLN.setH03FLGWK1("F");		
		msgLN.setH03SCRCOD("01");
		msgLN.setH03OPECOD("0005");

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

		flexLog("EDL015203 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL015203")) {
			try {
				msgLN = new datapro.eibs.beans.EDL015203Message();
				flexLog("EDL015203 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgLN = (EDL015203Message)newmessage;
			// showESD008004(msgLN);

			userPO.setIdentifier(msgLN.getE03DEAACC()); 

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnTransac", msgLN);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					{ 	
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_transac_feci.jsp");
						callPage(LangPath + "EDL0150_ln_enter_transac_feci.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_transac_feci.jsp");
					callPage(LangPath + "EDL0150_ln_transac_feci.jsp", req, res);	
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
protected void procReqEnterTransac(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN_TRANSACTION");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_transac.jsp");
		callPage(LangPath + "EDL0150_ln_enter_transac.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterCambiotasa(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN_CAMBIOTASA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0152_ln_enter_cambiotasa.jsp");
		callPage(LangPath + "EDL0152_ln_enter_cambiotasa.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqEnterTraFeci(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN_TRANSACTION");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex);  
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_transac_feci.jsp");
		callPage(LangPath + "EDL0150_ln_enter_transac_feci.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqTransaction(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	MessageRecord newmessage = null;
	EDL015203Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	String opCode = "0002";

	// Send Initial data
	try {
		msgLN = (EDL015203Message) mc.getMessageRecord("EDL015203");
		msgLN.setH03USERID(user.getH01USR());
		msgLN.setH03PROGRM("EDL0130");
		msgLN.setH03TIMSYS(getTimeStamp());
		msgLN.setH03SCRCOD("01");
		msgLN.setH03OPECOD(opCode);
		msgLN.setE03DEAACC(userPO.getIdentifier());
		msgLN.send();
		msgLN.destroy();
	} 
	catch (Exception e) {
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
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL015203")) {
			try {
				msgLN = new datapro.eibs.beans.EDL015203Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			msgLN = (EDL015203Message) newmessage;
			// showESD008004(msgLN);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnTransac", msgLN);
			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_transac.jsp");
					callPage(LangPath + "EDL0150_ln_transac.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_transac.jsp");
					callPage(LangPath + "EDL0150_ln_enter_transac.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
}

/**
 * This method was created in VisualAge.
 */
protected void procReqTransactionFeci(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	MessageRecord newmessage = null;
	EDL015203Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
	String opCode = "0002";
	if (userPO.getPurpose().equals("INQUIRY")) {
		opCode = "0004";
	} else if (userPO.getPurpose().equals("APPROVAL")) {
		opCode = "0004";
	}

	// Send Initial data
	try {
		msgLN = (EDL015203Message) mc.getMessageRecord("EDL015203");
		msgLN.setH03USERID(user.getH01USR());
		msgLN.setH03PROGRM("EDL0130");
		msgLN.setH03TIMSYS(getTimeStamp());
		msgLN.setH03SCRCOD("01");
		msgLN.setH03FLGWK1("F");		
		msgLN.setH03OPECOD(opCode);
		msgLN.setE03DEAACC(userPO.getIdentifier());
		msgLN.send();
		msgLN.destroy();
	} 
	catch (Exception e) {
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
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL015203")) {
			msgLN = (EDL015203Message) newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnTransac", msgLN);
			
			String page = "";
			if (IsNotError) { // There are no errors
				if (userPO.getPurpose().equals("INQUIRY")) {
					page = "EDL0150_ln_transac_feci_inquiry.jsp";
				} else if (userPO.getPurpose().equals("APPROVAL")) {
					page = "EDL0150_ln_transac_feci_inquiry.jsp";
				} else {
					page = "EDL0150_ln_transac_feci.jsp";
				}
			} else { // There are errors
				if (userPO.getPurpose().equals("INQUIRY")) {
					page = "error_viewer.jsp";
				} else if (userPO.getPurpose().equals("APPROVAL")) {
					page = "error_viewer.jsp";
				} else {
					page = "EDL0150_ln_enter_transac_feci.jsp";
				}
			}
			try {
				flexLog("About to call Page: " + LangPath + page);
				callPage(LangPath + page, req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} 
	catch (Exception e) {
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

		int screen = R_ENTER_TRANSAC;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
				s = new Socket(super.hostIP, getInitSocket(req) + 5);
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
				case R_TRANSACTION :
					procReqTransaction(mc, msgUser, req, res, session);
					break;			
				case A_TRANSACTION :
					procActionTransaction(mc, msgUser, req, res, session);
					break;
				case R_OTHERS_TRANS :
					procReqOthersTransaction(mc, msgUser, req, res, session);
					break;
				case A_INQUIRY_TRAFECI : 
					procActionEnterTraFeciInquiry(mc, msgUser, req, res, session);
					break;
				case R_ENTER_TRANSAC : 
					procReqEnterTransac(msgUser, req, res, session);
					break;
				case A_ENTER_TRANSAC : 
					procActionEnterTransac(mc, msgUser, req, res, session);
					break;
				case R_ENTER_CAMBIOTASA : 
					procReqEnterCambiotasa(msgUser, req, res, session);
					break;
				case A_ENTER_CAMBIOTASA : 
					procActionEnterCambiotasa(mc, msgUser, req, res, session);
					break;    
				case A_EXCHANGE : 
					procActionExchangeRate(mc, msgUser, req, res, session);
					break;  	
				case A_TRANSACTION_FECI :
					procActionTransactionFeci(mc, msgUser, req, res, session);
					break;
				case R_ENTER_TRAFECI : 
					procReqEnterTraFeci(msgUser, req, res, session);
					break;
				case A_ENTER_TRAFECI : 
					procActionEnterTraFeci(mc, msgUser, req, res, session);
					break;


				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 5;
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



/**
 * This method was created in VisualAge.
 */
protected void procReqOthersTransaction(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	UserPos	userPO = null;	
	boolean IsNotError = false;
 	DataTransaction transData = null;	

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	try {
		
		transData = new datapro.eibs.beans.DataTransaction();
		
		transData.setWrkFile("4");
		transData.setAccNum(userPO.getIdentifier());
		transData.setProdtype(userPO.getType());
		
		flexLog("Putting java beans into the session");
		ses.setAttribute("transData", transData);
		
		flexLog("About to redirect request to the procReqTr with specific parameters: ");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1");
		
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

}
}