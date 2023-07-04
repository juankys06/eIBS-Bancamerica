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

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEXEDD1150 extends JSEDD1150 {

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEXEDD1150() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		int screen)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD115001Message msgRT = null;
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
			msgRT = (EDD115001Message) mc.getMessageRecord("EDD115001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD115");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
			} catch (Exception e) {
				msgRT.setE01ACMACC("0");
			}

			msgRT.send();
			msgRT.destroy();
			flexLog("EDD11501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD115001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD115001Message();
					flexLog("EDD11501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD115001Message) newmessage;

				//msgRT.setE01NPGOVD("Y");
				//msgRT.setE01NPGINT("Y");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtCancel", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1150_cancel_basic.jsp");
						callPage(
							LangPath + "EDD1150_cancel_basic.jsp",
							req,
							res);
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
	protected void procReqEnterCancelRT(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("RT");
			userPO.setPurpose("CANCELATION");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD1150?SCREEN=200");
			userPO.setProdCode("RA");
			//Others Parameters
			userPO.setHeader1("E01ACMACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

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
	protected void procReqEnterCancelSV(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("SV");
			userPO.setPurpose("CANCELATION");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD1150?SCREEN=200");
			userPO.setProdCode("04");
			//Others Parameters
			userPO.setHeader1("E01ACMACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

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
}