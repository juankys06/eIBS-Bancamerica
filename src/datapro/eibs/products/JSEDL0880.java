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

public class JSEDL0880 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_CALC_LIST				= 0;

	protected static final int R_CALC_COUPON			= 1;
	protected static final int R_CALC_YIELD				= 3;
	protected static final int R_CALC_INT 				= 5;

	protected static final int A_CALC_COUPON			= 2;
	protected static final int A_CALC_YIELD				= 4;
	protected static final int A_CALC_INT 				= 6;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0880() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0880");
	
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
protected void procReqCalcList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		flexLog("About to call Page1: " + LangPath + "EDL0880_ln_cal_list.jsp");
		callPage(LangPath + "EDL0880_ln_cal_list.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqCalcCoupon(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL088001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		boolean IsData = false;

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
			msgCD = (EDL088001Message)ses.getAttribute("calcLoans");
			if (msgCD != null) {
				IsData = true;
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01PROGRM("EDL0880");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01");
				msgCD.setH01OPECOD("0002");

				// all the fields here
				java.util.Enumeration enu = msgCD.fieldEnumeration();
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

				//msgCD.send();
				mc.sendMessage(msgCD);
				msgCD.destroy();

				flexLog("EDL088001 Message Sent");
			}
		}		
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
				if (IsData) {
					try
					{
					  newmessage = mc.receiveMessage();
  
					  if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
			  } else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			}
			catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}	
		}

		// Receive Data
		if (IsData) {
			try
			{
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL088001")) {
						try {
							msgCD = new datapro.eibs.beans.EDL088001Message();
							flexLog("EDL088001 Message Received");
						} 
						catch (Exception ex) {
							flexLog("Error: " + ex); 
						}

						msgCD = (EDL088001Message)newmessage;
						// showESD008004(msgCD);

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("calcLoans", msgCD);
						ses.setAttribute("userPO", userPO);

						if (IsNotError) {  // There are no errors
							try {
									flexLog("About to call Page1: " + LangPath + "EDL0880_ln_coupon_cal.jsp");
									callPage(LangPath + "EDL0880_ln_coupon_cal.jsp", req, res);
							}
							catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
						else {  // There are errors
							try {
								flexLog("About to call Page2: " + LangPath + "EDL0880_ln_coupon_cal.jsp");
								callPage(LangPath + "EDL0880_ln_coupon_cal.jsp", req, res);	
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
		} else {
			flexLog("About to call Page1: " + LangPath + "EDL0880_ln_coupon_cal.jsp");
			callPage(LangPath + "EDL0880_ln_coupon_cal.jsp", req, res);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqCalcYield(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL088002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		boolean IsData = false;

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
			msgCD = (EDL088002Message)ses.getAttribute("calcLoans");
			if (msgCD != null) {
				IsData = true;
				msgCD.setH02USERID(user.getH01USR());
				msgCD.setH02PROGRM("EDL0880");
				msgCD.setH02TIMSYS(getTimeStamp());
				msgCD.setH02SCRCOD("01");
				msgCD.setH02OPECOD("0002");

				// all the fields here
				java.util.Enumeration enu = msgCD.fieldEnumeration();
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

				//msgCD.send();
				mc.sendMessage(msgCD);
				msgCD.destroy();

				flexLog("EDL088001 Message Sent");
			}
		}		
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
						if (IsData) {
							try
							{
							  newmessage = mc.receiveMessage();
  
							  if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
			  } else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			}
			catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}	
		}

		// Receive Data
		if (IsData) {
			try
			{
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL088002")) {
						try {
							msgCD = new datapro.eibs.beans.EDL088002Message();
							flexLog("EDL088001 Message Received");
						} 
						catch (Exception ex) {
							flexLog("Error: " + ex); 
						}

						msgCD = (EDL088002Message)newmessage;
						// showESD008004(msgCD);

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("calcLoans", msgCD);
						ses.setAttribute("userPO", userPO);

						if (IsNotError) {  // There are no errors
							try {
									flexLog("About to call Page1: " + LangPath + "EDL0880_ln_yield_cal.jsp");
									callPage(LangPath + "EDL0880_ln_yield_cal.jsp", req, res);
							}
							catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
						else {  // There are errors
							try {
								flexLog("About to call Page2: " + LangPath + "EDL0880_ln_yield_cal.jsp");
								callPage(LangPath + "EDL0880_ln_yield_cal.jsp", req, res);	
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
		} else {
			flexLog("About to call Page1: " + LangPath + "EDL0880_ln_yield_cal.jsp");
			callPage(LangPath + "EDL0880_ln_yield_cal.jsp", req, res);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqCalcInt(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL029001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		boolean IsData = false;

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
			msgCD = (EDL029001Message)ses.getAttribute("calcLoans");
			if (msgCD != null) {
				IsData = true;
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01PROGRM("EDL0290");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01");
				msgCD.setH01OPECOD("0002");

				// all the fields here
				java.util.Enumeration enu = msgCD.fieldEnumeration();
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

				//msgCD.send();
				mc.sendMessage(msgCD);
				msgCD.destroy();

				flexLog("EDL029001 Message Sent");
			}
		}		
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		if (IsData) {
			try
			{
			  newmessage = mc.receiveMessage();
  
			  if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
			  } else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			}
			catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}	
		}

		// Receive Data
		if (IsData) {
			try
			{
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL029001")) {
						try {
							msgCD = new datapro.eibs.beans.EDL029001Message();
							flexLog("EDL029001 Message Received");
						} 
						catch (Exception ex) {
							flexLog("Error: " + ex); 
						}

						msgCD = (EDL029001Message)newmessage;
						// showESD008004(msgCD);

						userPO.setIdentifier(msgCD.getE01DEAACC());

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("calcLoans", msgCD);
						ses.setAttribute("userPO", userPO);

						if (IsNotError) {  // There are no errors
							try {
									flexLog("About to call Page1: " + LangPath + "EDL0880_ln_int_cal.jsp");
									callPage(LangPath + "EDL0880_ln_int_cal.jsp", req, res);
							}
							catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
						else {  // There are errors
							try {
								flexLog("About to call Page2: " + LangPath + "EDL0880_ln_int_cal.jsp");
								callPage(LangPath + "EDL0880_ln_int_cal.jsp", req, res);	
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
		} else {
			flexLog("About to call Page1: " + LangPath + "EDL0880_ln_int_cal.jsp");
			callPage(LangPath + "EDL0880_ln_int_cal.jsp", req, res);
		}

}

/**
 * This method was created in VisualAge.
 */
protected void procActionCalcList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	ESS0030DSMessage msgUser = null;
	HttpSession session = null;

	session = (HttpSession)req.getSession(false); 
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int value = Integer.parseInt(req.getParameter("calculator"));
	
	switch (value) {
		// BEGIN CD
		// Request
		case R_CALC_LIST:
			procReqCalcList(mc, msgUser, req, res, session);
			break;					

		case R_CALC_COUPON:
			procReqCalcCoupon(mc, msgUser, req, res, session);
			break;					
											
		case R_CALC_YIELD:
			procReqCalcYield(mc, msgUser, req, res, session);
			break;					
											
		case R_CALC_INT:
			procReqCalcInt(mc, msgUser, req, res, session);
			break;					
											
		default :
			res.sendRedirect(super.srctx + LangPath + super.devPage);
			break;
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procActionCalcCoupon(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL088001Message msgCD = null;
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
	 	msgCD = (EDL088001Message)ses.getAttribute("calcLoans");
		//msgCD = (EDL029001Message)mc.getMessageRecord("EDL029001");
		msgCD.setH01USERID(user.getH01USR());
	 	msgCD.setH01PROGRM("EDL0880");
	 	msgCD.setH01TIMSYS(getTimeStamp());
	 	msgCD.setH01SCRCOD("01");
	 	msgCD.setH01OPECOD("0002");

		// all the fields here
	 	java.util.Enumeration enu = msgCD.fieldEnumeration();
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
				e.toString();
			}	
		}

	 	//msgCD.send();
	 	mc.sendMessage(msgCD);
	 	msgCD.destroy();

	 	flexLog("EDL088001 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL088001")) {
				try {
					msgCD = new datapro.eibs.beans.EDL088001Message();
					flexLog("EDL088001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgCD = (EDL088001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("calcLoans", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page1: " + LangPath + "EDL0880_ln_coupon_cal.jsp");
							callPage(LangPath + "EDL0880_ln_coupon_cal.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0880_ln_coupon_cal.jsp");
						callPage(LangPath + "EDL0880_ln_coupon_cal.jsp", req, res);	
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
protected void procActionCalcYield(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL088002Message msgCD = null;
	EDL088003Message msgList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	JBList beanList = null;
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
	 	msgCD = (EDL088002Message)ses.getAttribute("calcLoans");
		//msgCD = (EDL029001Message)mc.getMessageRecord("EDL029001");
		msgCD.setH02USERID(user.getH01USR());
	 	msgCD.setH02PROGRM("EDL0880");
	 	msgCD.setH02TIMSYS(getTimeStamp());
	 	msgCD.setH02SCRCOD("01");
	 	msgCD.setH02OPECOD("0002");

		// all the fields here
	 	java.util.Enumeration enu = msgCD.fieldEnumeration();
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

	 	//msgCD.send();
	 	mc.sendMessage(msgCD);
	 	msgCD.destroy();

	 	flexLog("EDL088002 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL088002")) {
				try {
					msgCD = new datapro.eibs.beans.EDL088002Message();
					flexLog("EDL088002 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgCD = (EDL088002Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("calcLoans", msgCD);
				ses.setAttribute("userPO", userPO);
	    }
		else 
			flexLog("Message " + newmessage.getFormatName() + " received.");
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

	// Receive List
	try
	{
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EDL088003")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;

			int indexRow = 0;
					
			while (true) {
				msgList = (EDL088003Message)newmessage;

				marker = msgList.getE03SFLEND();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					myRow = new StringBuffer("<tr>");
					myRow.append("<td width='30%' nowrap align='center'>" + msgList.getE03SFLYLD() + "</td>");
					myRow.append("<td width='30%' nowrap align='center'>" + msgList.getE03SFLPRC() + "</td>");
					myRow.append("<td width='30%' nowrap align='center'>" + msgList.getE03SFLQTE() + "</td>");
					myRow.append("</tr>");
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}

					newmessage = mc.receiveMessage();
				}

			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("resultList", beanList);
			ses.setAttribute("error", msgError);

		}
		else 
			flexLog("Message " + newmessage.getFormatName() + " received.");
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	


	if (IsNotError) {  // There are no errors
		try {
				flexLog("About to call Page1: " + LangPath + "EDL0880_ln_yield_cal.jsp");
				callPage(LangPath + "EDL0880_ln_yield_cal.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	else {  // There are errors
		try {
			flexLog("About to call Page2: " + LangPath + "EDL0880_ln_yield_cal.jsp");
			callPage(LangPath + "EDL0880_ln_yield_cal.jsp", req, res);	
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
}

/**
 * This method was created in VisualAge.
 */
protected void procActionCalcInt(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL029001Message msgCD = null;
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
			msgCD = (EDL029001Message)ses.getAttribute("calcLoans");
			//msgCD = (EDL029001Message)mc.getMessageRecord("EDL029001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0880");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();

			flexLog("EDL029001 Message Sent");
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
		  	} else
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

			if (newmessage.getFormatName().equals("EDL029001")) {
					try {
						msgCD = new datapro.eibs.beans.EDL029001Message();
						flexLog("EDL029001 Message Received");
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}

					msgCD = (EDL029001Message)newmessage;

					userPO.setIdentifier(msgCD.getE01DEAACC());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("calcLoans", msgCD);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) {  // There are no errors
						try {
								flexLog("About to call Page1: " + LangPath + "EDL0880_ln_int_cal.jsp");
								callPage(LangPath + "EDL0880_ln_int_cal.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else {  // There are errors
						try {
							flexLog("About to call Page2: " + LangPath + "EDL0880_ln_int_cal.jsp");
							callPage(LangPath + "EDL0880_ln_int_cal.jsp", req, res);	
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

		int screen = R_CALC_LIST;
		
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
				// BEGIN CD
				// Request
				case R_CALC_LIST:
					procReqCalcList(mc, msgUser, req, res, session);
					break;					

				case R_CALC_COUPON:
					procReqCalcCoupon(mc, msgUser, req, res, session);
					break;					
											
				case R_CALC_YIELD:
					procReqCalcYield(mc, msgUser, req, res, session);
					break;					
											
				case R_CALC_INT:
					procReqCalcInt(mc, msgUser, req, res, session);
					break;					
											
				// Action
				case A_CALC_COUPON :
					procActionCalcCoupon(mc, msgUser, req, res, session);
					break;

				case A_CALC_YIELD :
					procActionCalcYield(mc, msgUser, req, res, session);
					break;

				case A_CALC_INT :
					procActionCalcInt(mc, msgUser, req, res, session);
					break;
				// END CD

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