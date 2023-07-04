package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEOF0120 extends datapro.eibs.master.SuperServlet {

	// Oficial Checks options
	protected static final int R_CHK_CANCEL 	   = 10;
	protected static final int R_ENTER_CANCEL	   = 11;
	protected static final int R_DET_CHK_CANCEL	   = 13;
	protected static final int A_CHK_DET_CANCEL    = 14;
	protected static final int A_CHK_CANCEL 	   = 16;
	
	// Checks Certificate options 	
	protected static final int R_ENTER_CANC_CHKCER = 201;
	protected static final int R_DET_CHKCER_CANCEL = 203;
	protected static final int A_CHKCER_DET_CANCEL = 204;
	protected static final int A_CHKCER_CANCEL 	   = 206;
	
	protected static final int R_CHK_REVERSION 	   = 20;
	protected static final int A_CHK_REVERSION 	   = 102;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEOF0120() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procReqChkCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EOF0120_of_inq_det_cancel.jsp");
			callPage(LangPath + "EOF0120_of_inq_det_cancel.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}

	}
	
	protected  void procReqChkEnterCancel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos	userPO = null;	

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
		
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			try {
				flexLog("About to call Page: " + LangPath + "EOF0120_of_chk_enter_cancel.jsp");
				callPage(LangPath + "EOF0120_of_chk_enter_cancel.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	}
	
	protected  void procReqChkDetCancel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012001Message msgDV = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
		// Send Initial data
		try
		{
			msgDV = (EOF012001Message)mc.getMessageRecord("EOF012001");
			msgDV.setH01USERID(user.getH01USR());
			msgDV.setH01PROGRM("ETL0510");
			msgDV.setH01TIMSYS(getTimeStamp());
			msgDV.setH01SCRCOD("01");
			msgDV.setH01OPECOD("0002");
	 	
			// all the fields here
			java.util.Enumeration enu = msgDV.fieldEnumeration();
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
		
			msgDV.send();	
			msgDV.destroy();
	 	
		} catch (Exception e)	{
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

			if (newmessage.getFormatName().equals("EOF012001")) {

				msgDV = (EOF012001Message)newmessage; 

				flexLog("Putting java beans into the session");
				ses.setAttribute("dvDocDet", msgDV);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors			
					try {
						flexLog("About to call Page: " + LangPath + "EOF0120_of_inq_det_cancel.jsp");
						callPage(LangPath + "EOF0120_of_inq_det_cancel.jsp", req, res);
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0120_of_chk_enter_cancel.jsp");
						callPage(LangPath + "EOF0120_of_chk_enter_cancel.jsp", req, res);	
					}
					catch (Exception e) {
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
	
	protected void procActionChkDetCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012001Message msgCD = null;
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
			msgCD = (EOF012001Message) mc.getMessageRecord("EOF012001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EOF012001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0001");

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

			msgCD.send();
			msgCD.destroy();
			flexLog("EOF012001 Message Sent");
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

			if (newmessage.getFormatName().equals("EOF012001")) {

				msgCD = (EOF012001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("canchk", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0120_of_can_det.jsp");
						callPage(LangPath + "EOF0120_of_can_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0120_of_inq_det_cancel.jsp");
						callPage(
							LangPath + "EOF0120_of_inq_det_cancel.jsp",
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

	protected void procActionChkCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012001Message msgDeal = null;
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
			flexLog("Send Initial Data");
			msgDeal = (EOF012001Message) ses.getAttribute("canchk");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EOF012001");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");
			msgDeal.setH01FLGWK1("C");

			// all the fields here
			java.util.Enumeration enu = msgDeal.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgDeal.send();
			mc.sendMessage(msgDeal);
			msgDeal.destroy();
			flexLog("EOF012001 Message Sent");
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

			msgDeal = (EOF012001Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("canchk", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EOF0120_of_can_det_confirm.jsp");
					callPage(
						LangPath + "EOF0120_of_can_det_confirm.jsp",
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
							+ "EOF0120_of_can_det.jsp");
					callPage(LangPath + "EOF0120_of_can_det.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqChkReversion(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		
		throws ServletException, IOException {

			try {
				flexLog("About to call Page: " + LangPath + "EOF0120_of_chk_basic_rev.jsp");
				callPage(LangPath + "EOF0120_of_chk_basic_rev.jsp", req, res);	
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	}
	
	protected void procActionReversion(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012001Message msgOffChk = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}  catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		int posi = 0;
		
		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgOffChk = (EOF012001Message)mc.getMessageRecord("EOF012001");
		
			// all the fields here
			try{
				// all the fields here of the two beans
				java.util.Enumeration enu = msgOffChk.fieldEnumeration();
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
			} catch (Exception e) { }

			// setting chech to reverse info
			msgOffChk.setH01USERID(user.getH01USR());
			msgOffChk.setH01PROGRM("EOF0120");
			msgOffChk.setH01TIMSYS(getTimeStamp());
			msgOffChk.setH01SCRCOD("01");
			msgOffChk.setH01OPECOD("0005");
			msgOffChk.setH01FLGWK1("R");
			msgOffChk.send();	
			msgOffChk.destroy(); 

		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try
		{
		  newmessage = mc.receiveMessage();
	  
		  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			ses.setAttribute("error", msgError);
			showERROR(msgError);
		
		  } else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		try	{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EOF012001")) { 
				msgOffChk = (EOF012001Message)newmessage;
				// showESD008004(msgOffChk);

				userPO.setIdentifier(msgOffChk.getE01OFMCKN());
				userPO.setCurrency(msgOffChk.getE01OFMCCY());
				userPO.setHeader5(msgOffChk.getE01OFMEM1());
				userPO.setHeader6(msgOffChk.getE01OFMEM2());
				userPO.setHeader7(msgOffChk.getE01OFMEM3());
				userPO.setHeader8(msgOffChk.getE01OFMAMT());
				userPO.setHeader9(msgOffChk.getE01OFMBNF());
				userPO.setHeader10(msgOffChk.getE01OFMBN1());
				userPO.setHeader11(msgOffChk.getE01LETAMT());
				userPO.setHeader12(msgOffChk.getE01OFMCO1());
				userPO.setHeader13(msgOffChk.getE01OFMCO2());
				userPO.setHeader14(msgOffChk.getE01OFMCO3());
				userPO.setHeader19(msgOffChk.getE01OFMAPV());
				userPO.setHeader20(msgOffChk.getE01OFMBTH());
				userPO.setHeader21(user.getE01DTF());
				userPO.setHeader22(user.getE01LAN());			
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	

		if (IsNotError) {  // There are no errors
		   flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			try {
				flexLog("About to call Page: " + LangPath + "EOF0120_of_chk_cont_rev.jsp");
				callPage(LangPath + "EOF0120_of_chk_cont_rev.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else {  // There are errors
			try {
				flexLog("About to call Page: " + LangPath + "EOF0120_of_chk_basic_rev.jsp");
				callPage(LangPath + "EOF0120_of_chk_basic_rev.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}
	/**
	 * 
	 * Cancelacion de cheques Certificado
	 * 
	 */
	protected  void procReqEnterCancChkCer(
		MessageContext mc, 
		ESS0030DSMessage user, 
		HttpServletRequest req, 
		HttpServletResponse res, 
		HttpSession ses)
		throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos	userPO = null;	

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
		
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			try {
				flexLog("About to call Page: " + LangPath + "EOF0120_of_chkcer_enter_cancel.jsp");
				callPage(LangPath + "EOF0120_of_chkcer_enter_cancel.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	/*
	 *
	 * Cancelacion de Cheques Certificados
	 *  
	 */
	protected  void procReqChkCerDetCancel(
					MessageContext mc, 
					ESS0030DSMessage user, 
					HttpServletRequest req, 
					HttpServletResponse res, 
					HttpSession ses)
					throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012002Message msgDV = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
		// Send Initial data
		try
		{
			msgDV = (EOF012002Message)mc.getMessageRecord("EOF012002");
			msgDV.setH02USERID(user.getH01USR());
			msgDV.setH02PROGRM("EOF0120");
			msgDV.setH02TIMSYS(getTimeStamp());
			msgDV.setH02SCRCOD("01");
			msgDV.setH02OPECOD("0002");
	 	
			// all the fields here
			java.util.Enumeration enu = msgDV.fieldEnumeration();
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
		
			msgDV.send();	
			msgDV.destroy();
	 	
		} catch (Exception e)	{
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

			if (newmessage.getFormatName().equals("EOF012002")) {

				msgDV = (EOF012002Message)newmessage; 

				flexLog("Putting java beans into the session");
				ses.setAttribute("dvDocDet", msgDV);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors			
					try {
						flexLog("About to call Page: " + LangPath + "EOF0120_of_chkcer_inq_det_cancel.jsp");
						callPage(LangPath + "EOF0120_of_chkcer_inq_det_cancel.jsp", req, res);
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0120_of_chkcer_enter_cancel.jsp");
						callPage(LangPath + "EOF0120_of_chkcer_enter_cancel.jsp", req, res);	
					}
					catch (Exception e) {
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

	/*
	 * 
	 * Cancelacion de Cheques Certificados 
	 * 
	 */
	protected void procActionChkCerDetCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012002Message msgCD = null;
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
			msgCD = (EOF012002Message) mc.getMessageRecord("EOF012002");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("EOF0120");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("0001");

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

			msgCD.send();
			msgCD.destroy();
			flexLog("EOF012001 Message Sent");
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

			if (newmessage.getFormatName().equals("EOF012002")) {

				msgCD = (EOF012002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("canchk", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0120_of_chkcer_can_det.jsp");
						callPage(LangPath + "EOF0120_of_chkcer_can_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0120_of_chkcer_inq_det_cancel.jsp");
						callPage(
							LangPath + "EOF0120_of_chkcer_inq_det_cancel.jsp",
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
	
	/*
	 * 
	 * Cancelacion de Cheques Certificados
	 * 
	 */
	protected void procActionChkCerCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF012002Message msgDeal = null;
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
			flexLog("Send Initial Data");
			msgDeal = (EOF012002Message) ses.getAttribute("canchk");
			msgDeal.setH02USERID(user.getH01USR());
			msgDeal.setH02PROGRM("EOF0120");
			msgDeal.setH02TIMSYS(getTimeStamp());
			msgDeal.setH02SCRCOD("01");
			msgDeal.setH02OPECOD("0005");
			msgDeal.setH02FLGWK1("C");

			// all the fields here
			java.util.Enumeration enu = msgDeal.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgDeal.send();
			mc.sendMessage(msgDeal);
			msgDeal.destroy();
			flexLog("EOF012002 Message Sent");
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

			msgDeal = (EOF012002Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("canchk", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EOF0120_of_chkcer_can_det_confirm.jsp");
					callPage(
						LangPath + "EOF0120_of_chkcer_can_det_confirm.jsp",
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
							+ "EOF0120_of_chkcer_can_det.jsp");
					callPage(LangPath + "EOF0120_of_chkcer_can_det.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
	}

	/*
	 *   Service
	 */
		public void service(
					HttpServletRequest req, 
					HttpServletResponse res)
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
					flexLog("Error: " + e);
				}
			} else {
	
				int screen = A_CHK_DET_CANCEL;
	
				try {
					msgUser =
						(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
							"currUser");
	
					// Get the path from the user profile
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
					} catch (Exception e) {
						e.printStackTrace();
						int sck = getInitSocket(req) + 1;
						flexLog("Socket not Open(Port " + sck + "). Error: " + e);
						res.sendRedirect(LangPath + super.sckNotOpenPage);
						return;
					}
	
					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}
	
					switch (screen) {
						//entering options
						case R_CHK_CANCEL :
							procReqChkCancel(mc, msgUser, req, res, session);
							break;
						case R_ENTER_CANCEL :
							procReqChkEnterCancel(mc, msgUser, req, res, session);
							break;
						case R_DET_CHK_CANCEL :
							procReqChkDetCancel(mc, msgUser, req, res, session);
							break;
						case A_CHK_DET_CANCEL :
							procActionChkDetCancel(mc, msgUser, req, res, session);
							break;
						case A_CHK_CANCEL :
							procActionChkCancel(mc, msgUser, req, res, session);
							break;
							
						//Cheques certificados	
						case R_ENTER_CANC_CHKCER :
							procReqEnterCancChkCer(mc, msgUser, req, res, session);
							break;
						case R_DET_CHKCER_CANCEL :
							procReqChkCerDetCancel(mc, msgUser, req, res, session);
							break;
						case A_CHKCER_DET_CANCEL :
							procActionChkCerDetCancel(mc, msgUser, req, res, session);
							break;
						case A_CHKCER_CANCEL :
							procActionChkCerCancel(mc, msgUser, req, res, session);
							break;
							
						//Reverse Official Checks	
						case R_CHK_REVERSION :
							procReqChkReversion(mc, msgUser, req, res, session);
							break;
						case A_CHK_REVERSION :
							procActionReversion(mc, msgUser, req, res, session);
							break;
	
						default :
							res.sendRedirect(LangPath + super.devPage);
							break;
					}
	
					try {
						s.close();
					} catch (Exception e) {
						flexLog("Error: " + e);
					}
	
				} catch (Exception e) {
					flexLog("Error: " + e);
					res.sendRedirect(LangPath + super.sckNotRespondPage);
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
