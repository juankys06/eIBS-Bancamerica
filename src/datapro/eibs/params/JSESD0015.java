package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD001501Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0015 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER = 1;
	protected static final int A_ENTER = 2;
	protected static final int A_PROCESS = 4;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD0015() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0005");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
 * This method was created in VisualAge.
 */
protected  void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD001501Message msgPayment = null;
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
		msgPayment = (ESD001501Message)mc.getMessageRecord("ESD001501");
	 	msgPayment.setH01USR(user.getH01USR());
	 	msgPayment.setH01PGM("ESD0015");
	 	msgPayment.setH01TIM(getTimeStamp());
	 	msgPayment.setH01SCR("01");
	 	msgPayment.setH01OPE("0002");
	 	
	 	try{
	 	 msgPayment.setE01MID(req.getParameter("E01MID"));
	 	}
	 	catch (Exception e){
	 	}
	 
		
	 	msgPayment.send();
	 	msgPayment.destroy();
	 	flexLog("EIE011001 Message Sent");
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

		if (newmessage.getFormatName().equals("ESD001501")) {
			try {
				msgPayment = new ESD001501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPayment = (ESD001501Message)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("pymInst", msgPayment);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "ESD0015_automatic_cash_dispenser.jsp");
						callPage(LangPath + "ESD0015_automatic_cash_dispenser.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "ESD0015_automatic_cash_dispenser_enter.jsp");
						callPage(LangPath + "ESD0015_automatic_cash_dispenser_enter.jsp", req, res);	
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
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD001501Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgPayInst = new datapro.eibs.beans.ESD001501Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("BANK_INSTRUCTIONS");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("pymInst", msgPayInst);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0015_automatic_cash_dispenser_enter.jsp");
			callPage(
				LangPath + "ESD0015_automatic_cash_dispenser_enter.jsp",
				req,
				res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD001501Message msgPayment = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0005";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
		msgPayment = (ESD001501Message)mc.getMessageRecord("ESD001501");
	 	msgPayment.setH01USR(user.getH01USR());
	 	msgPayment.setH01PGM("ESD0010");
	 	msgPayment.setH01TIM(getTimeStamp());
	 	msgPayment.setH01SCR("01");
	 	msgPayment.setH01OPE("0005");

			// all the fields here
			java.util.Enumeration enu = msgPayment.fieldEnumeration();
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

			msgPayment.send();
			msgPayment.destroy();
			flexLog("ESD000506 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD001501")) {
				try {
					msgPayment = (datapro.eibs.beans.ESD001501Message) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ESD001501Message");
					flexLog("ESD001001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayment = (ESD001501Message) newmessage;
				// showESD000506(msgPayInst);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pymInst", msgPayment);

				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0015_automatic_cash_dispenser_enter.jsp");
						callPage(
							LangPath + "ESD0015_automatic_cash_dispenser_enter.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0015_automatic_cash_dispenser.jsp");
						callPage(
							LangPath + "ESD0015_automatic_cash_dispenser.jsp",
							req,
							res);

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
						// Request
						
							// Action
						case A_PROCESS :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END Personal & Corporative 

							// BEGIN Entering
							// Request
						case R_ENTER :
							procReqEnterMaint(msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END Entering
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

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
}