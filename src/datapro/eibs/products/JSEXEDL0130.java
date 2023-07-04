package datapro.eibs.products;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.EDL013009Message;
import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

/**
 * @version 	1.0
 * @author		Ramses Amaro
 */
public class JSEXEDL0130 extends JSEDL0130 {
	
	/**
	 * JSEXEDL0130 constructor comment.
	 */
	public JSEXEDL0130() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
		}
		
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		//EDL013001Message msgCD = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=500");
			userPO.setProdCode("CD");
			//Others Parameters
			userPO.setHeader1("E01DEAACC");
			userPO.setHeader2("H01FLGWK2");
			//msgCD = new EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			//ses.setAttribute("cdMant", msgCD);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
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
			msgCD = (EDL013001Message) mc.getMessageRecord("EDL013001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			try {
				msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
				//msgCD.setE01DEAACC(userPO.getIdentifier());
			}
			try {
				msgCD.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}

			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013001")) {
				try {
					msgCD = new EDL013001Message();
					flexLog("EDL013001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				

				if (IsNotError) { // There are no errors
					try {
						
						ses.setAttribute("cdMant", msgCD);
						userPO = new UserPos();
						userPO.setOption("CD");
						userPO.setPurpose("MAINTENANCE");						
						userPO.setIdentifier(msgCD.getE01DEAACC());
						userPO.setHeader1(msgCD.getE01DEAPRO());
						userPO.setHeader2(msgCD.getE01DEACUN());
						userPO.setHeader3(msgCD.getE01CUSNA1());
						userPO.setCurrency(msgCD.getE01DEACCY());
						
						ses.setAttribute("userPO", userPO);
						
						flexLog("About to call Page: " + LangPath + "EDL0130_cd_maint.jsp");
						callPage(LangPath + "EDL0130_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
		EFT000010Message msgCDNew = null;
		EDL013009Message msgCDInt = null;
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
			msgCD = (EDL013001Message) ses.getAttribute("cdMant");
			//msgCD = (EDL013001Message)mc.getMessageRecord("EDL013001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013009")) {
				try {
					msgCDInt = new EDL013009Message();
					flexLog("EDL013009 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCDInt = (EDL013009Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCDInt.getE09DEAACC());
				userPO.setCurrency(msgCDInt.getE09TRNCCY());
				userPO.setHeader3(msgCDInt.getE09CUSNA1());
				userPO.setHeader2(msgCDInt.getE09DEACUN());
				userPO.setHeader1(msgCDInt.getE09CUSNA1());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdIntPrep", msgCDInt);

				flexLog("Message " + newmessage.getFormatName() + " received.");
				try {

					flexLog("About to call Page1: " + LangPath + "EDL0130_cd_prep_int.jsp");
					callPage(LangPath + "EDL0130_cd_prep_int.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCDNew = new EFT000010Message();
					flexLog("EFT000010 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCDNew = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCDNew.getE10DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCDNew);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page1: " + LangPath + "EDL0130_cd_confirm.jsp");
					callPage(LangPath + "EDL0130_cd_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				if (newmessage.getFormatName().equals("EDL013001")) {
					try {
						msgCD = new EDL013001Message();
						flexLog("EDL001301 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCD = (EDL013001Message) newmessage;
					// showESD008004(msgCD);

					userPO.setIdentifier(msgCD.getE01DEAACC());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdMant", msgCD);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) { // There are no errors
						procReqEnterMaint(user,req,res,ses);
					} else { // There are errors
						try {
							flexLog("About to call Page2: " + LangPath + "EDL0130_cd_maint.jsp");
							callPage(LangPath + "EDL0130_cd_maint.jsp", req, res);
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
}
