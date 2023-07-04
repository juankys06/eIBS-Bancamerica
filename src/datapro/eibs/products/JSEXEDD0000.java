package datapro.eibs.products;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDD000001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EDD009001Message;
import datapro.eibs.beans.EFT000015Message;
import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
/**
 * @version 	1.0
 * @author Ramses Amaro
 */
public class JSEXEDD0000 extends JSEDD0000 {
	
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSEXEDD0000() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
		}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSVEnterInquiry(
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
			userPO.setPurpose("INQUIRY");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=1600");
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTEnterInquiry(
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
			userPO.setPurpose("INQUIRY");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=1400");
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
	 * 
	 */
	protected void procReqRTEnterMaint(
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
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=400");
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
	 * 
	 */
	protected void procReqSVEnterMaint(
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
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=400");
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
	/**
	 * 
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000001Message msgRT = null;
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
			msgRT = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			if (userPO.getOption().equals("SV")) msgRT.setH01FLGWK1("S");
			else msgRT.setH01FLGWK1("R");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
				//msgRT.setE01ACMACC(req.getParameter("ACCOUNT"));
			} catch (Exception e) {
				msgRT.setE01ACMACC("0");
			}

			msgRT.send();
			msgRT.destroy();
			flexLog("EDD000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000001Message();
					flexLog("EDD000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					userPO = new UserPos();
					userPO.setPurpose("MAINTENANCE");
					
					if (msgRT.getE01ACMACD().equals("04")) {
						userPO.setOption("SV");
						userPO.setAccNum(msgRT.getE01ACMACC());
						userPO.setIdentifier(msgRT.getE01ACMACC());
						userPO.setBank(msgRT.getE01ACMBNK());
						userPO.setCusNum(msgRT.getE01ACMCUN());
						userPO.setHeader1(msgRT.getE01ACMPRO());
						userPO.setHeader2(msgRT.getE01ACMCUN());
						userPO.setHeader3(msgRT.getE01CUSNA1());
						userPO.setCurrency(msgRT.getE01ACMCCY());
						userPO.setCusType(msgRT.getH01FLGWK3());

						ses.setAttribute("userPO", userPO);
						ses.setAttribute("svBasic", msgRT);

						try {
							flexLog("About to call Page: "+ LangPath + "EDD0000_sv_basic.jsp");
							callPage(LangPath + "EDD0000_sv_basic.jsp",req,res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						userPO.setOption("RT");
						userPO.setAccNum(msgRT.getE01ACMACC());
						userPO.setIdentifier(msgRT.getE01ACMACC());
						userPO.setBank(msgRT.getE01ACMBNK());
						userPO.setCusNum(msgRT.getE01ACMCUN());
						userPO.setHeader1(msgRT.getE01ACMPRO());
						userPO.setHeader2(msgRT.getE01ACMCUN());
						userPO.setHeader3(msgRT.getE01CUSNA1());
						userPO.setCurrency(msgRT.getE01ACMCCY());

						ses.setAttribute("userPO", userPO);
						ses.setAttribute("rtBasic", msgRT);

						try {
							flexLog("About to call Page: "+ LangPath + "EDD0000_rt_basic.jsp");
							callPage(LangPath + "EDD0000_rt_basic.jsp",req,res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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
	 * 
	 */
	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD009001Message msgRT = null;
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
			msgRT = (EDD009001Message) mc.getMessageRecord("EDD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
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
			flexLog("EDD009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD009001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD009001Message();
					flexLog("EDD009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					if (msgRT.getE01ACMACD().equals("04")) {
						userPO.setOption("SV");
					} else {
						userPO.setOption("RT");
					}
					userPO.setAccNum(msgRT.getE01ACMACC());
					userPO.setIdentifier(msgRT.getE01ACMACC());
					userPO.setBank(msgRT.getE01ACMBNK());
					//userPO.setCusNum(msgRT.getE01ACMCUN());
					userPO.setHeader2(msgRT.getE01ACMCUN());
					userPO.setHeader1(msgRT.getE01ACMPRO());
					//userPO.setHeader2(msgRT.getE01ACMCCY());
					userPO.setCurrency(msgRT.getE01ACMCCY());
					userPO.setHeader3(msgRT.getE01CUSNA1());
					userPO.setOfficer(msgRT.getE01ACMOFC() + " - " + msgRT.getE01DSCOFC());
					userPO.setCusType(msgRT.getH01FLGWK3());
					userPO.setHeader10(msgRT.getE01ACMATY());
			   	 	userPO.setHeader11(msgRT.getE01ACMACL());

					ses.setAttribute("userPO", userPO);
					ses.setAttribute("rtBalances", msgRT);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_inq_balances.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_balances.jsp",
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
	 * 
	 */
	protected void procActionRTBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000001Message msgRT = null;
		EFT000015Message msgFinish = null;
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
			msgRT = (EDD000001Message) ses.getAttribute("rtBasic");
			//msgRT = (EDD000001Message)mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			try {
			 	if (req.getParameter("APPROVAL").equals("Y")) msgRT.setH01OPECOD("0006");
			}
			catch (Exception e) {
			}
			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EFT000015")) {
				try {
					msgFinish = new datapro.eibs.beans.EFT000015Message();
					flexLog("EFT000015 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFinish = (EFT000015Message) newmessage;
				userPO.setIdentifier(msgFinish.getE15ACCNUM());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				try {
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtFinish", msgFinish);
					flexLog(
						"About to call Page1: "
							+ LangPath
							+ "EDD0000_rt_confirm.jsp");
					callPage(LangPath + "EDD0000_rt_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDD000001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000001Message();
					flexLog("EDD000001 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;
				// showESD008004(msgRT);

				if (userPO.getPurpose().equals("NEW")) {
					userPO.setIdentifier(msgRT.getE01ACMPRO());
					userPO.setHeader1(msgRT.getE01ACMCUN());
					userPO.setHeader2(msgRT.getE01CUSNA1());
					userPO.setCurrency(msgRT.getE01ACMCCY());
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtBasic", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {
					if (userPO.getPurpose().equals("MAINTENANCE")) {
						procReqRTEnterMaint(user,req,res,ses);
					} else if (userPO.getPurpose().equals("NEW")) {
						try {
							res.sendRedirect(super.srctx +"/pages/background.jsp");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						flexLog("Error Unknown");
					}
				} else {

					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_rt_new.jsp");
							callPage(LangPath + "EDD0000_rt_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
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
	
/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionSVBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDD000001Message msgRT = null;
		EFT000015Message msgFinish = null;
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
			msgRT = (EDD000001Message) ses.getAttribute("svBasic");
			//msgRT = (EDD000001Message)mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			try {
			 	if (req.getParameter("APPROVAL").equals("Y")) msgRT.setH01OPECOD("0006");
			}
			catch (Exception e) {
			}
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD000001 Message Sent");

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
			if (newmessage.getFormatName().equals("EFT000015")) {
				try {
					msgFinish = new datapro.eibs.beans.EFT000015Message();
					flexLog("EFT000015 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgFinish = (EFT000015Message) newmessage;
				userPO.setIdentifier(msgFinish.getE15ACCNUM());
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				try {
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtFinish", msgFinish);
					flexLog(
						"About to call Page1: "
							+ LangPath
							+ "EDD0000_rt_confirm.jsp");
					callPage(LangPath + "EDD0000_rt_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDD000001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000001Message();
					flexLog("EDD000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgRT = (EDD000001Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE01ACMACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("svBasic", msgRT);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) {
					if (userPO.getPurpose().equals("MAINTENANCE")) {
						procReqSVEnterMaint(user,req,res,ses);
					} else if (userPO.getPurpose().equals("NEW")) {
						try {
							res.sendRedirect(super.srctx +"/pages/background.jsp");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						flexLog("Error Unknown");
					}
				} else {
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_sv_new.jsp");
							callPage(LangPath + "EDD0000_sv_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
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
}
