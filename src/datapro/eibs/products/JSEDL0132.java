package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/20/04 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDL013012Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0132 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_RENOV 	= 100;
	protected static final int R_ENTER_INC	 	= 200;
	protected static final int A_UPDATE	 		= 300;
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0132() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0132");

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
	protected void procReqRenovation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013012Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opcode = "0002";
		
		String option = req.getParameter("OPT");
		if (option != null) opcode = "0004";
		else option = "";
		
		// Send Initial data
		try {
			msgCD = (EDL013012Message) mc.getMessageRecord("EDL013012");
			msgCD.setH12USERID(user.getH01USR());
			msgCD.setH12PROGRM("EDL0130");
			msgCD.setH12TIMSYS(getTimeStamp());
			msgCD.setH12SCRCOD("01");
			msgCD.setH12OPECOD(opcode);
			msgCD.setH12FLGWK1("1");
			try {
				msgCD.setE12DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
			
			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013012 Message Sent");
		

		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		
		// Receive Data
	
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013012")) {
				
				msgCD = (EDL013012Message) newmessage;
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdData", msgCD);
				try {
					if (IsNotError) { // There are no errors
						if (!option.equals("")) {
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_inq_renov_pay.jsp");
							callPage(LangPath + "EDL0130_cd_inq_renov_pay.jsp", req, res);	
						} else {	
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_renov_pay.jsp");
							callPage(LangPath + "EDL0130_cd_renov_pay.jsp", req, res);	
						}
					} else { // There are errors
						if (option.equals("A")){
							flexLog("About to call Page: " + LangPath + "EDL0140_cd_ap_maint.jsp");
							callPage(LangPath + "EDL0140_cd_ap_maint.jsp", req, res);
						} else if (option.equals("I")) {
							flexLog("About to call Page: "
										+ LangPath
										+ "EDL0160_cd_inquiry.jsp");
								callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_maint.jsp");
							callPage(LangPath + "EDL0130_cd_maint.jsp", req, res);
						}
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
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
	protected void procReqIncrease(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL013012Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opcode = "0002";
		
		String option = req.getParameter("OPT");
		if (option != null) opcode = "0004";
		else option = "";
		// Send Initial data
		try {
			msgCD = (EDL013012Message) mc.getMessageRecord("EDL013012");
			msgCD.setH12USERID(user.getH01USR());
			msgCD.setH12PROGRM("EDL0130");
			msgCD.setH12TIMSYS(getTimeStamp());
			msgCD.setH12SCRCOD("01");
			msgCD.setH12OPECOD(opcode);
			msgCD.setH12FLGWK1("2");
			try {
				msgCD.setE12DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
			
			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013012 Message Sent");
		
	
		// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		
		// Receive Data
	
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EDL013012")) {
				
				msgCD = (EDL013012Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdData", msgCD);
				
				try {
					
					if (IsNotError) { // There are no errors
						if (!option.equals("")) {
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_inq_auto_increase.jsp");
							callPage(LangPath + "EDL0130_cd_inq_auto_increase.jsp", req, res);	
						} else {	
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_auto_increase.jsp");
							callPage(LangPath + "EDL0130_cd_auto_increase.jsp", req, res);
						}
					} else { // There are errors
						
						if (option.equals("A")){
							flexLog("About to call Page: " + LangPath + "EDL0140_cd_ap_maint.jsp");
							callPage(LangPath + "EDL0140_cd_ap_maint.jsp", req, res);
						} else if (option.equals("I")) {
							flexLog("About to call Page: "
										+ LangPath
										+ "EDL0160_cd_inquiry.jsp");
								callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "EDL0130_cd_maint.jsp");
							callPage(LangPath + "EDL0130_cd_maint.jsp", req, res);
						}
						
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionUpdate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EDL013012Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgCD = (EDL013012Message)ses.getAttribute("cdData");
		msgCD.setH12USERID(user.getH01USR());
	 	msgCD.setH12PROGRM("EDL0130");
	 	msgCD.setH12TIMSYS(getTimeStamp());
	 	msgCD.setH12SCRCOD("01");
	 	msgCD.setH12OPECOD("0005");
	 	
	 	// all the fields here
	 	java.util.Enumeration enu = msgCD.fieldEnumeration();
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


	 	//msgCD.send();
	 	mc.sendMessage(msgCD);
	 	msgCD.destroy();
	 	flexLog("EFE0120DS Message Sent");
	
		
	// Receive Error Message
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	// Receive Data
	
	    newmessage = mc.receiveMessage();

				

				msgCD = (EDL013012Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdData", msgCD);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EDL0130_cd_maint.jsp");
							callPage(LangPath + "EDL0130_cd_maint.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							if (msgCD.getH12FLGWK1().equals("1")) {
								flexLog("About to call Page: " + LangPath + "EDL0130_cd_renov_pay.jsp");
								callPage(LangPath + "EDL0130_cd_renov_pay.jsp", req, res);	
							} else {
								flexLog("About to call Page: " + LangPath + "EDL0130_cd_auto_increase.jsp");
								callPage(LangPath + "EDL0130_cd_auto_increase.jsp", req, res);	
							}
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

			int screen = R_ENTER_INC;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 3);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					
						// Action
					case A_UPDATE :
						procActionUpdate(mc, msgUser, req, res, session);
						break;

					
						// Request
					case R_ENTER_RENOV :
						procReqRenovation(mc, msgUser, req, res, session);
						break;

						
					case R_ENTER_INC :
						procReqIncrease(mc, msgUser, req, res, session);
						break;

						// END Entering

					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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