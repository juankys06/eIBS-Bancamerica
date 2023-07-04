package datapro.eibs.params;

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

public class JSESD0005A extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected static final int A_BANK = 300;
	protected static final int A_MESSAGE = 400;
	protected static final int A_WIRE = 500;
	protected static final int A_LETTER = 600;
	protected static final int A_LOANS = 700;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESD0005A() {
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
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterParameter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String appCod = "";
		
		try {
			appCod = req.getParameter("appCode");
			
			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		if (appCod.equals("3"))
		{
			try {
				flexLog("About to call Page: " + LangPath + "ESD0005_wt_parameters_enter.jsp");
				res.sendRedirect(super.srctx + LangPath + "ESD0005_wt_parameters_enter.jsp?APPCODE=" + appCod);			
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}


		}
		else
		{
		
		try {
			flexLog("About to call Page: " + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
			res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + appCod);			
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		}
		
	}


	protected void procActionEnterBank(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int appcod = 0;
		appcod = Integer.parseInt(req.getParameter("APPCODE"));
		
		switch (appcod) {
			case 1 : //Bank
				procReqBankControl(mc, user, req, res, ses);
				break;
			case 2 : //Statement Message
				procReqStatementMessage(mc, user, req, res, ses);
				break;
			case 3 : //Paying & Receive
				procReqPayingReceive(mc, user, req, res, ses);
				break;
			case 4 : //Letter of Credit
				procReqLetterCredit(mc, user, req, res, ses);
				break;	
			case 5 : //Loans
				procReqLoans(mc, user, req, res, ses);
				break;							
			default :
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100" +"&appCode=" + appcod);
				break;
		}
	}

	protected void procReqBankControl(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000501Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			bankOld = (ESD000501Message) mc.getMessageRecord("ESD000501");
			bankOld.setH01USR(user.getH01USR());
			bankOld.setH01PGM("ESD0005");
			bankOld.setH01TIM(getTimeStamp());
			bankOld.setH01SCR("01");
			bankOld.setH01OPE("0002");

			try {
				bankOld.setE01CNTBNK(req.getParameter("CODBANK"));
			} catch (Exception e) {
				bankOld.setE01CNTBNK("0");
			}

			bankOld.send();
			bankOld.destroy();
		} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("ESD000501")) {
				try {
					bankOld = new datapro.eibs.beans.ESD000501Message();
					flexLog("ESD000501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				bankOld = (ESD000501Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESD0005_bank_param_maint.jsp");
						callPage(LangPath + "ESD0005_bank_param_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + 1);	
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

	protected void procReqStatementMessage(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000503Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			bankOld = (ESD000503Message) mc.getMessageRecord("ESD000503");
			bankOld.setH03USR(user.getH01USR());
			bankOld.setH03PGM("ESD0005");
			bankOld.setH03TIM(getTimeStamp());
			bankOld.setH03SCR("01");
			bankOld.setH03OPE("0002");

			try {
				bankOld.setE03MSGBNK(req.getParameter("CODBANK"));
			} catch (Exception e) {
				bankOld.setE03MSGBNK("0");
			}
			bankOld.send();
			bankOld.destroy();
		} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("ESD000503")) {
				try {
					bankOld = new datapro.eibs.beans.ESD000503Message();
					flexLog("ESD000503 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				bankOld = (ESD000503Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESD0005_statement_messages_param_maint.jsp");
						callPage(LangPath + "ESD0005_statement_messages_param_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + 3);	
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

	protected void procReqPayingReceive(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000504Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			bankOld = (ESD000504Message) mc.getMessageRecord("ESD000504");
			bankOld.setH04USR(user.getH01USR());
			bankOld.setH04PGM("ESD0004");
			bankOld.setH04TIM(getTimeStamp());
			bankOld.setH04SCR("01");
			bankOld.setH04OPE("0002");

			try {
				bankOld.setE04CWTBNK(req.getParameter("CODBANK"));
			} catch (Exception e) {
				bankOld.setE04CWTBNK("0");
			}

			bankOld.send();
			bankOld.destroy();
		} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("ESD000504")) {
				try {
					bankOld = new datapro.eibs.beans.ESD000504Message();
					flexLog("ESD000504 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				bankOld = (ESD000504Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESD0005_wire_transf_param_maint.jsp");
						callPage(LangPath + "ESD0005_wire_transf_param_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + 3);	
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

	protected void procReqLetterCredit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000505Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			bankOld = (ESD000505Message) mc.getMessageRecord("ESD000505");
			bankOld.setH05USERID(user.getH01USR());
			bankOld.setH05PROGRM("ESD0005");
			bankOld.setH05TIMSYS(getTimeStamp());
			bankOld.setH05SCRCOD("01");
			bankOld.setH05OPECOD("0002");

			try {
				bankOld.setE05LCPBNK(req.getParameter("CODBANK"));
			} catch (Exception e) {
				bankOld.setE05LCPBNK("0");
			}

			bankOld.send();
			bankOld.destroy();
		} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("ESD000505")) {
				try {
					bankOld = new datapro.eibs.beans.ESD000505Message();
					flexLog("ESD000505 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				bankOld = (ESD000505Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESD0005_letter_credit_param_maint.jsp");
						callPage(LangPath + "ESD0005_letter_credit_param_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + 4);	
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

	protected void procReqLoans(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000507Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			bankOld = (ESD000507Message) mc.getMessageRecord("ESD000507");
			bankOld.setH07USERID(user.getH01USR());
			bankOld.setH07PROGRM("ESD0005");
			bankOld.setH07TIMSYS(getTimeStamp());
			bankOld.setH07SCRCOD("01");
			bankOld.setH07OPECOD("0002");

			try {
				bankOld.setE07CNTBNK(req.getParameter("CODBANK"));
			} catch (Exception e) {
				bankOld.setE07CNTBNK("0");
			}

			bankOld.send();
			bankOld.destroy();
		} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("ESD000507")) {
				try {
					bankOld = new datapro.eibs.beans.ESD000507Message();
					flexLog("ESD000507 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				bankOld = (ESD000507Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESD0005_loans_param_maint.jsp");
						callPage(LangPath + "ESD0005_loans_param_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						res.sendRedirect(super.srctx + LangPath + "ESD0005_bank_setup_basic_enter.jsp?APPCODE=" + 4);	
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
	
	protected void procActionBank(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000501Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bankOld = (ESD000501Message) ses.getAttribute("bankOld");
			bankOld.setH01USR(user.getH01USR());
			bankOld.setH01PGM("ESD000501");
			bankOld.setH01TIM(getTimeStamp());
			bankOld.setH01SCR("01");     // Add
			bankOld.setH01OPE("0005");

			// all the fields here
			java.util.Enumeration enu = bankOld.fieldEnumeration();
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

			mc.sendMessage(bankOld);
			bankOld.destroy();
			flexLog("ESD000501 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000501")) {
				try {
					bankOld = new ESD000501Message();
					flexLog("ESD000501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				bankOld = (ESD000501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100&appCode=" + 1);
				} else { // There are errors
					flexLog("About to call Page: " + LangPath + "ESD0005_bank_param_maint.jsp");
					callPage(LangPath + "ESD0005_bank_param_maint.jsp", req, res);	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionMessage(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000503Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bankOld = (ESD000503Message) ses.getAttribute("bankOld");
			bankOld.setH03USR(user.getH01USR());
			bankOld.setH03PGM("ESD000503");
			bankOld.setH03TIM(getTimeStamp());
			bankOld.setH03SCR("01");     // Add
			bankOld.setH03OPE("0005");

			// all the fields here
			java.util.Enumeration enu = bankOld.fieldEnumeration();
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

			mc.sendMessage(bankOld);
			bankOld.destroy();
			flexLog("ESD000503 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000503")) {
				try {
					bankOld = new ESD000503Message();
					flexLog("ESD000503 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				bankOld = (ESD000503Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100&appCode=" + 2);
				} else { // There are errors
					flexLog("About to call Page: " + LangPath + "ESD0005_statement_messages_param_maint.jsp");
					callPage(LangPath + "ESD0005_statement_messages_param_maint.jsp", req, res);	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionWire(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000504Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bankOld = (ESD000504Message) ses.getAttribute("bankOld");
			bankOld.setH04USR(user.getH01USR());
			bankOld.setH04PGM("ESD000504");
			bankOld.setH04TIM(getTimeStamp());
			bankOld.setH04SCR("01");     // Add
			bankOld.setH04OPE("0005");

			// all the fields here
			java.util.Enumeration enu = bankOld.fieldEnumeration();
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

			mc.sendMessage(bankOld);
			bankOld.destroy();
			flexLog("ESD000504 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000504")) {
				try {
					bankOld = new ESD000504Message();
					flexLog("ESD000504 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				bankOld = (ESD000504Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100&appCode=" + 3);
				} else { // There are errors
					flexLog("About to call Page: " + LangPath + "ESD0005_wire_transf_param_maint.jsp");
					callPage(LangPath + "ESD0005_wire_transf_param_maint.jsp", req, res);	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionLetter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000505Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bankOld = (ESD000505Message) ses.getAttribute("bankOld");
			bankOld.setH05USERID(user.getH01USR());
			bankOld.setH05PROGRM("ESD000505");
			bankOld.setH05TIMSYS(getTimeStamp());
			bankOld.setH05SCRCOD("01");     // Add
			bankOld.setH05OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = bankOld.fieldEnumeration();
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

			mc.sendMessage(bankOld);
			bankOld.destroy();
			flexLog("ESD000505 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000505")) {
				try {
					bankOld = new ESD000505Message();
					flexLog("ESD000505 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				bankOld = (ESD000505Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100&appCode=" + 4);
				} else { // There are errors
					flexLog("About to call Page: " + LangPath + "ESD0005_letter_credit_param_maint.jsp");
					callPage(LangPath + "ESD0005_letter_credit_param_maint.jsp", req, res);	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionLoans(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000507Message bankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bankOld = (ESD000507Message) ses.getAttribute("bankOld");
			bankOld.setH07USERID(user.getH01USR());
			bankOld.setH07PROGRM("ESD000507");
			bankOld.setH07TIMSYS(getTimeStamp());
			bankOld.setH07OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = bankOld.fieldEnumeration();
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

			mc.sendMessage(bankOld);
			bankOld.destroy();
			flexLog("ESD000507 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000507")) {
				try {
					bankOld = new ESD000507Message();
					flexLog("ESD000507 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				bankOld = (ESD000507Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", bankOld);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005A?SCREEN=100&appCode=" + 5);
				} else { // There are errors
					flexLog("About to call Page: " + LangPath + "ESD0005_loans_param_maint.jsp");
					callPage(LangPath + "ESD0005_loans_param_maint.jsp", req, res);	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}








	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_ENTER;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {

						//Request
						case R_ENTER :
							procReqEnterParameter(msgUser, req, res, session);
							break;

						// Action
						case A_ENTER :
							procActionEnterBank(mc, msgUser, req, res, session);
							break;

						case A_BANK :
							procActionBank(mc, msgUser, req, res, session);
							break;

						case A_MESSAGE :
							procActionMessage(mc, msgUser, req, res, session);
							break;
																								
						case A_WIRE :
							procActionWire(mc, msgUser, req, res, session);
							break;

						case A_LETTER :
							procActionLetter(mc, msgUser, req, res, session);
							break;
							
						case A_LOANS :
							procActionLoans(mc, msgUser, req, res, session);
							break;
							
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	protected void showERROR(ELEERRMessage m) {
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
