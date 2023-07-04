package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
 * @author: David Mavilla
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDD000001Message;
import datapro.eibs.beans.EDD000002Message;
import datapro.eibs.beans.EDD000003Message;
import datapro.eibs.beans.EDD000004Message;
import datapro.eibs.beans.EDD000005Message;
import datapro.eibs.beans.EDD000010Message;
import datapro.eibs.beans.EDD009001Message;
import datapro.eibs.beans.EDD009002Message;
import datapro.eibs.beans.EDD009201Message;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.ESD009001Message;
import datapro.eibs.beans.EFT000015Message;
import datapro.eibs.beans.ELD000001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD071103Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDD0000 extends datapro.eibs.master.SuperServlet {
	// RETAIL ACCOUNTS
	protected static final int R_RT_NEW = 1;
	protected static final int A_RT_NEW = 2;
	protected static final int R_RT_MAINTENANCE = 3;
	protected static final int A_RT_MAINTENANCE = 4;
	protected static final int R_RT_OVERDRAFT = 5;
	protected static final int A_RT_OVERDRAFT = 6;
	protected static final int R_RT_LIN_CRED = 7;
	protected static final int A_RT_LIN_CRED = 8;
	protected static final int R_RT_MONEY = 11;
	protected static final int A_RT_MONEY = 12;
	protected static final int R_RT_CODES = 13;
	protected static final int A_RT_CODES = 14;
	protected static final int R_RT_STATUS = 15;
	protected static final int A_RT_STATUS = 16;
	protected static final int R_RT_TITULARES = 17;
	protected static final int A_RT_TITULARES = 18;
	protected static final int R_RT_FIRMANTES = 19;
	protected static final int A_RT_FIRMANTES = 20;
	protected static final int R_RT_SPECIAL_INST = 21;
	protected static final int A_RT_SPECIAL_INST = 22;
	protected static final int R_RT_BENEFICIARIES = 23;
	protected static final int A_RT_BENEFICIARIES = 24;
	protected static final int R_RT_PRINT_NEW = 43;
	protected static final int A_RT_PRINT_NEW = 44;
	protected static final int R_RT_OVERNIGHT = 45;
	protected static final int A_RT_OVERNIGHT = 46;
	protected static final int R_RT_LEGAL_REP = 47;
	protected static final int A_RT_LEGAL_REP = 48;
	protected static final int R_RT_ACC_ANALYSIS = 101;
	protected static final int A_RT_ACC_ANALYSIS = 102;
	protected static final int R_RT_ACC_TELLER_MSG = 103;
	protected static final int A_RT_ACC_TELLER_MSG = 104;

	protected static final int R_INQ_ACCOUNT_ANALYSIS = 105;

	protected static final int R_SV_NEW = 25;
	protected static final int R_SV_MAINTENANCE = 27;

	protected static final int A_RT_BASIC = 29;
	protected static final int A_SV_BASIC = 30;

	// Entering options
	protected static final int R_RT_ENTER_NEW = 100;
	protected static final int R_RT_ENTER_MAINT = 300;
	protected static final int R_SV_ENTER_NEW = 500;
	protected static final int R_SV_ENTER_MAINT = 700;
	protected static final int R_RT_ENTER_PRINT = 1100;
	protected static final int R_RT_ENTER_INQUIRY = 1300;
	protected static final int R_SV_ENTER_INQUIRY = 1500;
	protected static final int R_SV_ENTER_PRINT = 1700;

	protected static final int A_RT_ENTER_NEW = 200;
	protected static final int A_RT_ENTER_MAINT = 400;
	protected static final int A_SV_ENTER_NEW = 600;
	protected static final int A_SV_ENTER_MAINT = 800;
	protected static final int A_RT_ENTER_PRINT = 1200;
	protected static final int A_RT_ENTER_INQUIRY = 1400;
	protected static final int A_SV_ENTER_INQUIRY = 1600;
	protected static final int A_SV_ENTER_PRINT = 1800;

	//Inquiry Options
	protected static final int R_INQ_BALANCES = 31;
	protected static final int R_INQ_BASIC = 32;
	protected static final int R_INQ_MONEY = 33;
	protected static final int R_INQ_AVERAG = 34;
	protected static final int R_INQ_CODES = 35;
	protected static final int R_INQ_SIGNERS = 36;
	protected static final int R_INQ_SP_INST = 37;
	protected static final int R_INQ_TIT = 38;
	protected static final int R_INQ_BENE = 39;
	protected static final int R_PRODUCT = 40;
	protected static final int R_RELATED_ACC = 41;
	protected static final int R_DAILY_BAL = 42;
	protected static final int R_INQ_LEGAL_REP = 50;

	//inquiry CHECKBOOK option
	protected static final int R_INQ_CHECKBOOK = 51;

	protected static final int R_ACCOUNT_TITLE = 57;
	protected static final int A_ACCOUNT_TITLE = 58;

	protected String LangPath = "S";

	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
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
					userPO.setCusNum(msgRT.getE01ACMCUN());
					userPO.setHeader1(msgRT.getE01ACMPRO());
					userPO.setHeader2(msgRT.getE01ACMCCY());
					userPO.setHeader3(msgRT.getE01CUSNA1());
					userPO.setCusType(msgRT.getH01FLGWK3());

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
					if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_inquiry.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_inquiry.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_enter_inquiry.jsp");
							callPage(
								LangPath + "EDD0000_rt_enter_inquiry.jsp",
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
			msgRT.setH01FLGWK1("R");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
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
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
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
							flexLog(
								"About to call Page: "
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
				} else { // There are errors
					if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_maint.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_rt_enter_maint.jsp",
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
	 */
	protected void procActionEnterPrint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD009002Message msgRT = null;
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

			msgRT = (EDD009002Message) mc.getMessageRecord("EDD009002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDD0000");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD("0002");
			try {
				msgRT.setE02ACMACC(req.getParameter("E02ACMACC"));
			} catch (Exception e) {
				msgRT.setE02ACMACC("0");
			}

			msgRT.send();
			msgRT.destroy();
			flexLog("EDD009002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD009002")) {
				try {
					msgRT = new datapro.eibs.beans.EDD009002Message();
					flexLog("EDD009002 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD009002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					userPO.setIdentifier(msgRT.getE02ACMACC());

					ses.setAttribute("userPO", userPO);
					ses.setAttribute("rtFinish", msgRT);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_finish.jsp");
						callPage(LangPath + "EDD0000_rt_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_enter_print.jsp");
							callPage(
								LangPath + "EDD0000_rt_enter_print.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_print.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_print.jsp",
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
	protected void procActionEnterSVMaint(
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
			msgRT.setH01FLGWK1("S");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
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
					userPO.setOption("SV");
					userPO.setAccNum(msgRT.getE01ACMACC());
					userPO.setIdentifier(msgRT.getE01ACMACC());
					userPO.setBank(msgRT.getE01ACMBNK());
					userPO.setCusNum(msgRT.getE01ACMCUN());
					userPO.setHeader1(msgRT.getE01ACMPRO());
					userPO.setHeader2(msgRT.getE01ACMCUN());
					userPO.setHeader3(msgRT.getE01CUSNA1());
					userPO.setCurrency(msgRT.getE01ACMCCY());

					ses.setAttribute("userPO", userPO);
					ses.setAttribute("svBasic", msgRT);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_sv_basic.jsp");
						callPage(LangPath + "EDD0000_sv_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_maint.jsp",
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
	 */
	protected void procActionFinish(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDD009002Message msgCD = null;
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
			msgCD = (EDD009002Message) ses.getAttribute("cdFinish");
			msgCD = (EDD009002Message) mc.getMessageRecord("EDD009002");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("EDD0000");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("0005");
			try {
				msgCD.setE02ACMACC(userPO.getIdentifier());
			} catch (Exception e) {
				flexLog("E01CUMACC");
			}

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDD009002 Message Sent");

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
			if (newmessage.getFormatName().equals("EDD009002")) {
				try {
					msgCD = new datapro.eibs.beans.EDD009002Message();
					flexLog("EDD009002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgCD = (EDD009002Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE02ACMACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtFinish", msgCD);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_enter_maint.jsp");
						callPage(
							LangPath + "EDD0000_rt_enter_maint.jsp",
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
								+ "EDD0000_rt_finish.jsp");
						callPage(LangPath + "EDD0000_rt_finish.jsp", req, res);
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
	protected void procActionFirm(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String country = null;

		country = req.getParameter("COUNTRY");

		// Send Initial data
		try {
			//msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
			msgBene = (ESD000004Message) ses.getAttribute("rtFirm");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE("0005");
			msgBene.setE04RTP("S");
			msgBene.setE04CUN(userPO.getIdentifier());

			// all the fields here
			java.util.Enumeration enu = msgBene.fieldEnumeration();
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

			//msgBene.send();
			flexLog("JSEDD0000:procActionFirm, ESD000004 Message Sent: "+ msgBene.toString());
			mc.sendMessage(msgBene);
			msgBene.destroy();
			
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtFirm", msgBene);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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
				} else { // There are errors
					if (country.equals("PA")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_firm_pa.jsp");
							callPage(
								LangPath + "EDD0000_rt_firm_pa.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (country.equals("VE")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_firm_ve.jsp");
							callPage(
								LangPath + "EDD0000_rt_firm_ve.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_firm_generic.jsp");
							callPage(
								LangPath + "EDD0000_rt_firm_generic.jsp",
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
	protected void procActionPrintNew(
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
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgRT.setE01ACMACC("0");
			}
			msgRT.send();
			msgRT.destroy();
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
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
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

						ses.setAttribute("userPO", userPO);
						ses.setAttribute("svBasic", msgRT);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
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
							flexLog(
								"About to call Page: "
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
				} else { // There are errors
					if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_maint.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_rt_enter_maint.jsp",
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
			catch (Exception e) {}
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
					flexLog("EFT000015 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFinish = (EFT000015Message) newmessage;
				userPO.setIdentifier(msgFinish.getE15ACCNUM());
				userPO.setHeader2(msgFinish.getE15CUSCUN());
				userPO.setHeader3(msgFinish.getE15CUSNA1());
				userPO.setCurrency(msgFinish.getE15CCYCDE());
				userPO.setHeader1(msgFinish.getE15PROCDE());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				try {
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtFinish", msgFinish);
					flexLog("About to call Page1: "	+ LangPath + "EDD0000_rt_confirm.jsp");
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
						try {
							flexLog("About to call Page2: " + LangPath + "EDD0000_rt_enter_maint.jsp");
							callPage(LangPath + "EDD0000_rt_enter_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page2: " + LangPath + "background.jsp");
							res.sendRedirect(super.srctx + "/pages/background.jsp");
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
								"About to call Page2: " + LangPath 	+ "EDD0000_rt_new.jsp");
							callPage(LangPath + "EDD0000_rt_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page2: " + LangPath + "EDD0000_rt_basic.jsp");
							callPage(LangPath + "EDD0000_rt_basic.jsp", req, res);
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
	 */
	protected void procActionRTBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
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
			//msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
			msgBene = (ESD000004Message) ses.getAttribute("bene");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(""); //getTimeStamp()
			msgBene.setH04SCR("01");
			msgBene.setH04OPE("0005");
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("J");

			// all the fields here
			java.util.Enumeration enu = msgBene.fieldEnumeration();
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

			//msgBene.send();
			mc.sendMessage(msgBene);
			msgBene.destroy();
			flexLog("ESD000004 Message Sent");
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
				if (!IsNotError)
					userPO.setAccOpt("LEGALREP");
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_bene.jsp");
						callPage(LangPath + "EDD0000_rt_bene.jsp", req, res);
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
	protected void procActionRTLegalRep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String type = "I";
		type = userPO.getHeader10();
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
			msgBene = (ESD000004Message) ses.getAttribute("legalRep");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(""); //getTimeStamp()
			msgBene.setH04SCR("01");
			msgBene.setH04OPE("0005");
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP(type);
	
			// all the fields here
			java.util.Enumeration enu = msgBene.fieldEnumeration();
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
	
			msgBene.setE14MA4(
				req.getParameter("E14MA4") + ((req.getParameter("E14MA42")!=null)?req.getParameter("E14MA42"):""));
			msgBene.setE24MA4(
				req.getParameter("E24MA4") + ((req.getParameter("E24MA42")!=null)?req.getParameter("E24MA42"):""));
			msgBene.setE34MA4(
				req.getParameter("E34MA4") + ((req.getParameter("E34MA42")!=null)?req.getParameter("E34MA42"):""));
			msgBene.setE44MA4(
				req.getParameter("E44MA4") + ((req.getParameter("E44MA42")!=null)?req.getParameter("E44MA42"):""));
			msgBene.setE54MA4(
				req.getParameter("E54MA4") + ((req.getParameter("E54MA42")!=null)?req.getParameter("E54MA42"):""));
			msgBene.setE64MA4(
				req.getParameter("E64MA4") + ((req.getParameter("E64MA42")!=null)?req.getParameter("E64MA42"):""));
			msgBene.setE74MA4(
				req.getParameter("E74MA4") + ((req.getParameter("E74MA42")!=null)?req.getParameter("E74MA42"):""));
			msgBene.setE84MA4(
				req.getParameter("E84MA4") + ((req.getParameter("E84MA42")!=null)?req.getParameter("E84MA42"):""));
			msgBene.setE94MA4(
				req.getParameter("E94MA4") + ((req.getParameter("E94MA42")!=null)?req.getParameter("E94MA42"):""));
			msgBene.setEA4MA4(
				req.getParameter("EA4MA4") + ((req.getParameter("EA4MA42")!=null)?req.getParameter("EA4MA42"):""));

			//msgBene.send();
			mc.sendMessage(msgBene);
			msgBene.destroy();
			flexLog("ESD000004 Message Sent");
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
				if (!IsNotError)
					userPO.setAccOpt("LEGALREP");
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
	
			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("legalRep", msgBene);
	
				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
	
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_legal_rep.jsp");
						callPage(LangPath + "EDD0000_rt_legal_rep.jsp", req, res);
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
	protected void procActionRTCredit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000004Message msgRT = null;
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
			msgRT = (EDD000004Message) ses.getAttribute("rtCredit");
			//msgRT = (EDD000004Message)mc.getMessageRecord("EDD000004");
			msgRT.setH04USERID(user.getH01USR());
			msgRT.setH04PROGRM("EDD0000");
			msgRT.setH04TIMSYS(getTimeStamp());
			msgRT.setH04SCRCOD("01");
			msgRT.setH04OPECOD("0005");

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
			flexLog("EDD000004 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000004")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000004Message();
					flexLog("EDD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000004Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE04ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtCredit", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_credit.jsp");
						callPage(LangPath + "EDD0000_rt_credit.jsp", req, res);
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
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionRTEnterNew(
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
			msgRT.setH01OPECOD("0001");
			try {
				msgRT.setE01ACMPRO(req.getParameter("E01ACMPRO"));
			} catch (Exception e) {
				msgRT.setE01ACMPRO("");
			}
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
			} catch (Exception e) {
			}

			try {
				msgRT.setE01OFFAC1(req.getParameter("E01OFFAC1"));
			} catch (Exception e) {
			}

			try {
				msgRT.setE01OFFAM1(req.getParameter("E01ACMAMT"));
				msgRT.setH01FLGWK3("T");
			} catch (Exception e) {
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
				userPO.setProdCode(msgRT.getE01ACMPRO());
				userPO.setHeader1(msgRT.getE01ACMCUN());
				userPO.setHeader2(msgRT.getE01CUSNA1());
				userPO.setCurrency(msgRT.getE01ACMCCY());
				userPO.setHeader4("N");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("rtBasic", msgRT);
				if (IsNotError) { // There are no errors
					// procReqRTNew(mc, user, req, res, ses);
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_new.jsp");
						callPage(LangPath + "EDD0000_rt_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_enter_new.jsp");
						// callPage(LangPath + "EDL0130_cd_enter_new.jsp", req, res);

						String firstLink =
							super.webAppPath
								+ LangPath
								+ "ESD0711_products_detail.jsp?appcode="
								+ req.getParameter("appcode").trim()
								+ "&typecode="
								+ req.getParameter("typecode").trim()
								+ "&generic="
								+ req.getParameter("generic").trim()
								+ "&title="
								+ req.getParameter("title").trim()
								+ "&bank="
								+ req.getParameter("bank").trim();
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						printProdFrame(out, firstLink, LangPath);

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
	protected void procActionRTMoney(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELD000001Message msgRT = null;
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
			msgRT = (ELD000001Message) ses.getAttribute("rtMoney");
			//msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgRT.setH06USERID(user.getH01USR());
			msgRT.setH06PROGRM("EDD0000");
			msgRT.setH06TIMSYS(getTimeStamp());
			msgRT.setH06SCRCOD("01");
			msgRT.setH06OPECOD("0005");

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
			flexLog("ELD000001 Message Sent");
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

			if (newmessage.getFormatName().equals("ELD000001")) {
				try {
					msgRT = new datapro.eibs.beans.ELD000001Message();
					flexLog("ELD000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ELD000001Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE06LDMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtMoney", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_money.jsp");
						callPage(LangPath + "EDD0000_rt_money.jsp", req, res);
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
	protected void procActionAccountAnalysis(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000010Message msgRT = null;
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
			msgRT = (EDD000010Message) ses.getAttribute("accAnalysis");
			//msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgRT.setH10USERID(user.getH01USR());
			msgRT.setH10PROGRM("EDD000010");
			msgRT.setH10TIMSYS(getTimeStamp());
			msgRT.setH10SCRCOD("01");
			msgRT.setH10OPECOD("0005");

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
			flexLog("EDD000010 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000010")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000010Message();
					flexLog("EDD000010 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000010Message) newmessage;

				userPO.setIdentifier(msgRT.getE10ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("accAnalysis", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_money.jsp");
						callPage(
							LangPath + "EDD0000_rt_account_analysis.jsp",
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

	protected void procReqInqAccountAnalysis(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000010Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000010Message) mc.getMessageRecord("EDD000010");
			msgRT.setH10USERID(user.getH01USR());
			msgRT.setH10PROGRM("EDD0000");
			msgRT.setH10TIMSYS(getTimeStamp());
			msgRT.setH10SCRCOD("01");
			msgRT.setH10OPECOD(opCode);
			msgRT.setE10ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000010")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000010Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("accAnalysis", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_inq_account_analysis.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_account_analysis.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_rt_inq_balances.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_balances.jsp",
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) ses.getAttribute("instructions");
			//msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD000010");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			msgRT.setE01SPITYP("T");
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
			flexLog("ESD009001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
					flexLog("ESD009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD009001Message) newmessage;

				userPO.setIdentifier(msgRT.getE01SPIACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_teller_instructions.jsp");
						callPage(
							LangPath + "EDD0000_rt_teller_instructions.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionRTOverdraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000003Message msgRT = null;
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
			msgRT = (EDD000003Message) ses.getAttribute("rtOverdraft");
			//msgRT = (EDD000003Message)mc.getMessageRecord("EDD000003");
			msgRT.setH03USERID(user.getH01USR());
			msgRT.setH03PROGRM("EDD0000");
			msgRT.setH03TIMSYS(getTimeStamp());
			msgRT.setH03SCRCOD("01");
			msgRT.setH03OPECOD("0005");

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
			flexLog("EDD000003 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000003")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000003Message();
					flexLog("EDD000003 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000003Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE03ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOverdraft", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_overdraft.jsp");
						callPage(
							LangPath + "EDD0000_rt_overdraft.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionRTOvernight(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000005Message msgRT = null;
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
			msgRT = (EDD000005Message) ses.getAttribute("rtOvernight");
			//msgRT = (EDD000003Message)mc.getMessageRecord("EDD000003");
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0000");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD("0005");
			msgRT.setE05ACMACC(userPO.getIdentifier());

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
			flexLog("EDD000005 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000005")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000005Message();
					flexLog("EDD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000005Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOvernight", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_basic.jsp");
						callPage(LangPath + "EDD0000_rt_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_overnight.jsp");
						callPage(
							LangPath + "EDD0000_rt_overnight.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionStatus(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000002Message msgRT = null;
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
			msgRT = (EDD000002Message) ses.getAttribute("rtStatus");
			//msgRT = (EDD000002Message)mc.getMessageRecord("EDD000002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDD0000");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD("0005");

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
			flexLog("EDD000002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD000002")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000002Message();
					flexLog("EDD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000002Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE02ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtStatus", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_chg_status.jsp");
						callPage(LangPath + "EDD0000_chg_status.jsp", req, res);
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
	protected void procActionSpcInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgRT = null;
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
			msgRT = (ESD000005Message) ses.getAttribute("rtInst");
			//msgRT = (ESD000005Message)mc.getMessageRecord("ESD000005");
			msgRT.setH05USR(user.getH01USR());
			msgRT.setH05PGM("EDD0000");
			msgRT.setH05TIM(""); //getTimeStamp()
			msgRT.setH05SCR("01");
			msgRT.setH05OPE("0005");
			msgRT.setE05TYP("1");
			msgRT.setE05ACC(userPO.getIdentifier());

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
					msgRT = new datapro.eibs.beans.ESD000005Message();
					flexLog("ESD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000005Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE05ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtInst", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_special_inst.jsp");
							callPage(
								LangPath + "EDD0000_rt_special_inst.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_special_inst.jsp");
							callPage(
								LangPath + "EDD0000_sv_special_inst.jsp",
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
	 */
	protected void procActionSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgRT = null;
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
			msgRT = (ESD000002Message) ses.getAttribute("rtCodes");
			//msgRT = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgRT.setH02USR(user.getH01USR());
			msgRT.setH02PGM("EDD0000");
			msgRT.setH02TIM(getTimeStamp());
			msgRT.setH02SCR("01");
			msgRT.setH02OPE("0005");

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
			flexLog("ESD000002 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgRT = new datapro.eibs.beans.ESD000002Message();
					flexLog("ESD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000002Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE02ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtCodes", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_codes.jsp");
							callPage(
								LangPath + "EDD0000_rt_codes.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_codes.jsp");
							callPage(
								LangPath + "EDD0000_rt_codes.jsp",
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

				userPO.setAccNum(msgRT.getE01ACMACC());
				userPO.setIdentifier(msgRT.getE01ACMACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("svBasic", msgRT);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) {
					if (userPO.getPurpose().equals("MAINTENANCE")) {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_sv_enter_maint.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_maint.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page2: "
									+ LangPath
									+ "EDD0000_sv_enter_new.jsp");
							//callPage(LangPath + "EDD0000_rt_enter_new.jsp", req, res);
							res.sendRedirect(
								super.srctx + "/pages/background.jsp");
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
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionSVEnterNew(
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
			flexLog("Sending header");
			msgRT = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0001");
			try {
				if (req.getParameter("E01ACMPRO") != null)
					msgRT.setE01ACMPRO(req.getParameter("E01ACMPRO"));
			} catch (Exception e) {
			}
			try {
				if (req.getParameter("E01ACMACC") != null)
					msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
			} catch (Exception e) {
			}

			try {
				msgRT.setE01OFFAC1(req.getParameter("E01OFFAC1"));
			} catch (Exception e) {
			}

			try {
				msgRT.setE01OFFAM1(req.getParameter("E01ACMAMT"));
				msgRT.setH01FLGWK3("T");
			} catch (Exception e) {
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
					flexLog("EDD000001 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgRT = (EDD000001Message) newmessage;
				userPO.setProdCode(msgRT.getE01ACMPRO());
				userPO.setHeader1(msgRT.getE01ACMCUN());
				userPO.setHeader2(msgRT.getE01CUSNA1());
				userPO.setCurrency(msgRT.getE01ACMCCY());
				userPO.setHeader4("N");
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("svBasic", msgRT);
				if (IsNotError) { // There are no errors
					// procReqSVNew(mc, user, req, res, ses);

					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_sv_new.jsp");
						callPage(LangPath + "EDD0000_sv_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_sv_enter_new.jsp");
						// callPage(LangPath + "EDL0130_cd_enter_new.jsp", req, res);

						String firstLink =
							super.webAppPath
								+ LangPath
								+ "ESD0711_products_detail.jsp?appcode="
								+ req.getParameter("appcode").trim()
								+ "&typecode="
								+ req.getParameter("typecode").trim()
								+ "&generic="
								+ req.getParameter("generic").trim()
								+ "&title="
								+ req.getParameter("title").trim()
								+ "&bank="
								+ req.getParameter("bank").trim();
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						printProdFrame(out, firstLink, LangPath);
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
	protected void procActionTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgRT = null;
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
			//msgRT = (ESD000006Message)ses.getAttribute("rtTit");
			msgRT = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgRT.setH06USR(user.getH01USR());
			msgRT.setH06PGM("EDD0000");
			msgRT.setH06TIM(getTimeStamp());
			msgRT.setH06SCR("01");
			msgRT.setH06OPE("0005");
			msgRT.setE06ACC(userPO.getIdentifier());
			msgRT.setE06RTP("H");

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
			flexLog("EDD000006 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgRT = new datapro.eibs.beans.ESD000006Message();
					flexLog("EDD000006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000006Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE06ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtTit", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					//if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_tit.jsp");
							callPage(LangPath + "EDD0000_rt_tit.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						/*} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_tit.jsp");
							callPage(LangPath + "EDD0000_sv_tit.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}*/

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
	protected void procReqDayBal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD009201Message msgSearch = null;
		EDD009201Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int type = 0;
		String num = "";
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD009201Message) mc.getMessageRecord("EDD009201");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDL0300");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0004");

			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgSearch.setE01ACMACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E01ACMACC");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			}

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD009201")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				try {
					beanList.setSearchText(num);
					beanList.setSearchType(type + "");
				} catch (Exception e) {
					e.printStackTrace();
					beanList.setSearchText("A");
					beanList.setSearchType("3");
					flexLog("Input data error " + e);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String chk = "";

				while (true) {

					msgList = (EDD009201Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							chk = "";
						}
						StringBuffer myRow = null;
						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\">"
								+ Util.formatDate(
									msgList.getE01BALDT1(),
									msgList.getE01BALDT2(),
									msgList.getE01BALDT3())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getE01GRSBAL())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getE01UNCOLL())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>"
								+ Util.formatCell(msgList.getE01HOLDIN())
								+ "</TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getE01DISPON())
								+ "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("cifList", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0092_rt_inq_day_bal.jsp");
					callPage(LangPath + "EDD0092_rt_inq_day_bal.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterPrint(
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
			userPO.setPurpose("PRINT");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_rt_enter_print.jsp");
			callPage(LangPath + "EDD0000_rt_enter_print.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFinish(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD009002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD009002Message) mc.getMessageRecord("EDD009002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDL0130");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD(opCode);
			msgRT.setE02ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD009002")) {
				try {
					msgRT = new datapro.eibs.beans.EDD009002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD009002Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtFinish", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_finish.jsp");
						callPage(LangPath + "EDD0000_rt_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_rt_enter_print.jsp");
						callPage(
							LangPath + "EDD0000_rt_enter_print.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFirm(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		userPO.setAccOpt("SIGNERS");

		String opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			try {
				msgBene.setH04SCR(req.getParameter("H04SCR"));
			} catch (Exception e) {
				msgBene.setH04SCR("07");
			}
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("S");

			

			msgBene.send();
			msgBene.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtFirm", msgBene);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_firm.jsp");
						callPage(
							LangPath + "EDD0000_rt_firm.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInqTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgRT.setH06USR(user.getH01USR());
			msgRT.setH06PGM("EDD0000");
			msgRT.setH06TIM(getTimeStamp());
			msgRT.setH06SCR("01");
			msgRT.setH06OPE(opCode);
			msgRT.setE06ACC(userPO.getIdentifier());
			msgRT.setE06RTP("H");
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgRT = new datapro.eibs.beans.ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000006Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtTit", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_inq_tit.jsp");
						callPage(LangPath + "EDD0000_rt_inq_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_rt_inq_balances.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_balances.jsp",
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
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqProductInfo(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD071103Message msgProdDDA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String bank = userPO.getBank();
		String prodCode = userPO.getHeader1();

		String opCode = "0004";

		// Send Initial data
		try {
			msgProdDDA = (ESD071103Message) mc.getMessageRecord("ESD071103");
			msgProdDDA.setH03USERID(user.getH01USR());
			msgProdDDA.setH03PROGRM("ESD0711");
			msgProdDDA.setH03TIMSYS(getTimeStamp());
			msgProdDDA.setH03SCRCOD("01");
			msgProdDDA.setH03OPECOD(opCode);
			msgProdDDA.setE03APCCDE(prodCode);
			msgProdDDA.setE03APCBNK(bank);
			msgProdDDA.send();
			msgProdDDA.destroy();
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

			if (newmessage.getFormatName().equals("ESD071103")) {
				try {
					msgProdDDA = new datapro.eibs.beans.ESD071103Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgProdDDA = (ESD071103Message) newmessage;
				// showESD071103(msgProdDDA);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ddaProdInq", msgProdDDA);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0711_products_inq_dda.jsp");
						callPage(
							LangPath + "ESD0711_products_inq_dda.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		userPO.setAccOpt("BENE");

		String opCode = "";
		if (userPO.getPurpose().equals("NEW"))
			opCode = "0001";
		else
			opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("J");
			msgBene.send();
			msgBene.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_bene.jsp");
						callPage(LangPath + "EDD0000_rt_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTLegalRep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		userPO.setAccOpt("LEGALREP");
	
		String type = "I";
		//type = req.getParameter("Type");
		if (type == null)
			type = "";
		userPO.setHeader10(type);
	
		String opCode = "";
		if (userPO.getPurpose().equals("NEW"))
			opCode = "0001";
		else
			opCode = "0002";
	
		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP(type);
			msgBene.send();
			msgBene.destroy();
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
	
			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);
				if (msgBene.getE14MA4().length() < 5)
					msgBene.setE14MA4("00000 ");
				if (msgBene.getE24MA4().length() < 5)
					msgBene.setE24MA4("00000 ");
				if (msgBene.getE34MA4().length() < 5)
					msgBene.setE34MA4("00000 ");
				if (msgBene.getE44MA4().length() < 5)
					msgBene.setE44MA4("00000 ");
				if (msgBene.getE54MA4().length() < 5)
					msgBene.setE54MA4("00000 ");
				if (msgBene.getE64MA4().length() < 5)
					msgBene.setE64MA4("00000 ");
				if (msgBene.getE74MA4().length() < 5)
					msgBene.setE74MA4("00000 ");
				if (msgBene.getE84MA4().length() < 5)
					msgBene.setE84MA4("00000 ");
				if (msgBene.getE94MA4().length() < 5)
					msgBene.setE94MA4("00000 ");
				if (msgBene.getEA4MA4().length() < 5)
					msgBene.setEA4MA4("00000 ");
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("legalRep", msgBene);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_legal_rep.jsp");
						callPage(LangPath + "EDD0000_rt_legal_rep.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInqRTBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		if (userPO.getPurpose().equals("NEW"))
			opCode = "0001";
		else
			opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("J");
			msgBene.send();
			msgBene.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_inq_bene.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_bene.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_inq_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_inq_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInqRTLegalRep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String type = null;
		type = req.getParameter("Type");
		userPO.setHeader10(type);
	
		String opCode = null;
		if (userPO.getPurpose().equals("NEW"))
			opCode = "0001";
		else
			opCode = "0002";
	
		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP(type);
			msgBene.send();
			msgBene.destroy();
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
	
			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("legalRep", msgBene);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_inq_legal_rep.jsp");
						callPage(
							LangPath + "EDD0000_rt_inq_legal_rep.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_inq_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_inq_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTCredit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000004Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000004Message) mc.getMessageRecord("EDD000004");
			msgRT.setH04USERID(user.getH01USR());
			msgRT.setH04PROGRM("EDD0000");
			msgRT.setH04TIMSYS(getTimeStamp());
			msgRT.setH04SCRCOD("01");
			msgRT.setH04OPECOD(opCode);
			msgRT.setE04ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000004")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000004Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtCredit", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_credit.jsp");
						callPage(LangPath + "EDD0000_rt_credit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_rt_enter_inquiry.jsp");
			callPage(LangPath + "EDD0000_rt_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_rt_enter_maint.jsp");
			callPage(LangPath + "EDD0000_rt_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqRTEnterNew(
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
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0000_rt_enter_new.jsp");
			callPage(LangPath + "EDD0000_rt_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTEnterPrint(
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
			userPO.setPurpose("PRINT");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_rt_enter_print.jsp");
			callPage(LangPath + "EDD0000_rt_enter_print.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTMaintenance(
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

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtBasic", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_basic.jsp");
						callPage(LangPath + "EDD0000_rt_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_rt_enter_maint.jsp");
						callPage(
							LangPath + "EDD0000_rt_enter_maint.jsp",
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTMoney(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELD000001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (ELD000001Message) mc.getMessageRecord("ELD000001");
			msgRT.setH06USERID(user.getH01USR());
			msgRT.setH06PROGRM("EDD0000");
			msgRT.setH06TIMSYS(getTimeStamp());
			msgRT.setH06SCRCOD("01");
			msgRT.setH06OPECOD(opCode);
			msgRT.setE06LDMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("ELD000001")) {
				try {
					msgRT = new datapro.eibs.beans.ELD000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ELD000001Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtMoney", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_money.jsp");
						callPage(LangPath + "EDD0000_rt_money.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqAccountAnalysis(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000010Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000010Message) mc.getMessageRecord("EDD000010");
			msgRT.setH10USERID(user.getH01USR());
			msgRT.setH10PROGRM("EDD0000");
			msgRT.setH10TIMSYS(getTimeStamp());
			msgRT.setH10SCRCOD("01");
			msgRT.setH10OPECOD(opCode);
			msgRT.setE10ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000010")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000010Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("accAnalysis", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_account_analysis.jsp");
						callPage(
							LangPath + "EDD0000_rt_account_analysis.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getIdentifier());
			msgRT.setE01SPITYP("T");
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_teller_instructions.jsp");
						callPage(
							LangPath + "EDD0000_rt_teller_instructions.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqRTNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0001";

		// Send Initial data
		try {
			msgCD = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDD0000");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setE01ACMPRO(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDD000001")) {
				try {
					msgCD = new datapro.eibs.beans.EDD000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDD000001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtNew", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_basic.jsp");
						callPage(LangPath + "EDD0000_rt_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: /servlet/datapro.eibs.products.JSESD0711?TYPE=RT");
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.products.JSESD0711?TYPE=RT");
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
	protected void procReqRTOverdraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000003Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000003Message) mc.getMessageRecord("EDD000003");
			msgRT.setH03USERID(user.getH01USR());
			msgRT.setH03PROGRM("EDD0000");
			msgRT.setH03TIMSYS(getTimeStamp());
			msgRT.setH03SCRCOD("01");
			msgRT.setH03OPECOD(opCode);
			msgRT.setE03ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000003")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000003Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000003Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOverdraft", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_overdraft.jsp");
						callPage(
							LangPath + "EDD0000_rt_overdraft.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTOvernight(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000005Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000005Message) mc.getMessageRecord("EDD000005");
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0000");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD(opCode);
			msgRT.setE05ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000005")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000005Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOvernight", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_overnight.jsp");
						callPage(
							LangPath + "EDD0000_rt_overnight.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_rt_basic.jsp");
						callPage(LangPath + "EDD0000_rt_basic.jsp", req, res);
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
	protected void procReqStatus(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000002Message) mc.getMessageRecord("EDD000002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDD0000");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD(opCode);
			msgRT.setE02ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD000002")) {
				try {
					msgRT = new datapro.eibs.beans.EDD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000002Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtStatus", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_chg_status.jsp");
						callPage(LangPath + "EDD0000_chg_status.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSpcInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgRT = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgRT.setH05USR(user.getH01USR());
			msgRT.setH05PGM("EDD0000");
			msgRT.setH05TIM(getTimeStamp());
			msgRT.setH05SCR("01");
			msgRT.setH05OPE(opCode);
			msgRT.setE05ACC(userPO.getIdentifier());
			msgRT.setE05TYP("1");
			msgRT.send();
			msgRT.destroy();
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
					msgRT = new datapro.eibs.beans.ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000005Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtInst", msgRT);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_special_inst.jsp");
						callPage(
							LangPath + "EDD0000_rt_special_inst.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgRT = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgRT.setH02USR(user.getH01USR());
			msgRT.setH02PGM("EDD0000");
			msgRT.setH02TIM(getTimeStamp());
			msgRT.setH02SCR("01");
			msgRT.setH02OPE(opCode);
			msgRT.setE02ACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgRT = new datapro.eibs.beans.ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000002Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtCodes", msgRT);

				if (IsNotError) { // There are no errors
					try {
						if (userPO.getPurpose().equals("MAINTENANCE")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_codes.jsp");
							callPage(
								LangPath + "EDD0000_rt_codes.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_codes.jsp");
							callPage(
								LangPath + "EDD0000_rt_codes.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_sv_enter_inquiry.jsp");
			callPage(LangPath + "EDD0000_sv_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_sv_enter_maint.jsp");
			callPage(LangPath + "EDD0000_sv_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqSVEnterNew(
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
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0000_sv_enter_new.jsp");
			callPage(LangPath + "EDD0000_sv_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSVEnterPrint(
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
			userPO.setPurpose("PRINT");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0000_sv_enter_print.jsp");
			callPage(LangPath + "EDD0000_sv_enter_print.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSVMaintenance(
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

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("svBasic", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_sv_basic.jsp");
						callPage(LangPath + "EDD0000_sv_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0000_sv_enter_maint.jsp");
						callPage(
							LangPath + "EDD0000_sv_enter_maint.jsp",
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
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqSVNew(
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

		String opCode = null;
		opCode = "0001";

		// Send Initial data
		try {
			msgRT = (EDD000001Message) mc.getMessageRecord("EDD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01ACMPRO(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD000001Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("svNew", msgRT);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_sv_basic.jsp");
						callPage(LangPath + "EDD0000_sv_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: /servlet/datapro.eibs.products.JSESD0711?TYPE=04");
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.products.JSESD0711?TYPE=04");
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
	protected void procReqTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgRT.setH06USR(user.getH01USR());
			msgRT.setH06PGM("EDD0000");
			msgRT.setH06TIM(getTimeStamp());
			msgRT.setH06SCR("01");
			msgRT.setH06OPE(opCode);
			msgRT.setE06ACC(userPO.getIdentifier());
			msgRT.setE06RTP("H");
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgRT = new datapro.eibs.beans.ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000006Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtTit", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_tit.jsp");
						callPage(LangPath + "EDD0000_rt_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
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

			int screen = R_RT_ENTER_MAINT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 4);
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
						// BEGIN RETAIL ACCOUNT
						// Request
						case R_RT_NEW :
							procReqRTNew(mc, msgUser, req, res, session);
							break;
						case R_RT_MAINTENANCE :
							procReqRTMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_RT_OVERDRAFT :
							procReqRTOverdraft(mc, msgUser, req, res, session);
							break;
						case R_RT_OVERNIGHT :
							procReqRTOvernight(mc, msgUser, req, res, session);
							break;
						case R_RT_LIN_CRED :
							procReqRTCredit(mc, msgUser, req, res, session);
							break;
						case R_RT_MONEY :
							procReqRTMoney(mc, msgUser, req, res, session);
							break;
						case R_RT_STATUS :
							procReqStatus(mc, msgUser, req, res, session);
							break;
						case R_RT_CODES :
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_ACCOUNT_TITLE :
							procReqAccountTitle(mc, msgUser, req, res, session);
							break;
						case R_RT_TITULARES :
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_RT_FIRMANTES :
							procReqFirm(mc, msgUser, req, res, session);
							break;
						case R_RT_SPECIAL_INST :
							procReqSpcInst(mc, msgUser, req, res, session);
							break;
						case R_RT_BENEFICIARIES :
							procReqRTBene(mc, msgUser, req, res, session);
							break;
						case R_RT_LEGAL_REP :
							procReqRTLegalRep(mc, msgUser, req, res, session);
							break;
						case R_INQ_BENE :
							procReqInqRTBene(mc, msgUser, req, res, session);
							break;
						case R_INQ_LEGAL_REP :
							procReqInqRTLegalRep(mc, msgUser, req, res, session);
							break;
						case R_RT_ACC_ANALYSIS :
							procReqAccountAnalysis(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_RT_ACC_TELLER_MSG :
							procReqTellerMessages(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							//Savings
						case R_SV_NEW :
							procReqSVNew(mc, msgUser, req, res, session);
							break;
						case R_SV_MAINTENANCE :
							procReqSVMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// Action
						case A_RT_BASIC :
							procActionRTBasic(mc, msgUser, req, res, session);
							break;
						case A_RT_OVERDRAFT :
							procActionRTOverdraft(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_OVERNIGHT :
							procActionRTOvernight(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_LIN_CRED :
							procActionRTCredit(mc, msgUser, req, res, session);
							break;
						case A_RT_MONEY :
							procActionRTMoney(mc, msgUser, req, res, session);
							break;
						case A_RT_STATUS :
							procActionStatus(mc, msgUser, req, res, session);
							break;
						case A_ACCOUNT_TITLE :
							procActionAccountTitle(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_CODES :
							procActionSpecialCodes(
								mc,
								msgUser,
								req,
								res,
								session);
							break;      
						case A_RT_TITULARES :
							procActionTit(mc, msgUser, req, res, session);
							break;
						case A_RT_FIRMANTES :
							procActionFirm(mc, msgUser, req, res, session);
							break;
						case A_RT_SPECIAL_INST :
							procActionSpcInst(mc, msgUser, req, res, session);
							break;
						case A_RT_BENEFICIARIES :
							procActionRTBene(mc, msgUser, req, res, session);
							break;
						case A_RT_LEGAL_REP :
							procActionRTLegalRep(mc, msgUser, req, res, session);
							break;
						case A_RT_PRINT_NEW :
							procActionPrintNew(mc, msgUser, req, res, session);
							break;
						case A_RT_ACC_ANALYSIS :
							procActionAccountAnalysis(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_ACC_TELLER_MSG :
							procActionTellerMessages(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							//Savings
						case A_SV_BASIC :
							procActionSVBasic(mc, msgUser, req, res, session);
							break;

							/*
											case R_RELATED_ACC :
												break;
											case R_DAILY_BAL :
												break;
							*/
							// END RETAIL ACCOUNT

							// BEGIN EnterinG
							// Request
						case R_RT_ENTER_NEW :
							procReqRTEnterNew(msgUser, req, res, session);
							break;
						case R_SV_ENTER_NEW :
							procReqSVEnterNew(msgUser, req, res, session);
							break;
						case R_RT_ENTER_MAINT :
							procReqRTEnterMaint(msgUser, req, res, session);
							break;
						case R_SV_ENTER_MAINT :
							procReqSVEnterMaint(msgUser, req, res, session);
							break;
						case R_RT_ENTER_INQUIRY :
							procReqRTEnterInquiry(msgUser, req, res, session);
							break;
						case R_SV_ENTER_INQUIRY :
							procReqSVEnterInquiry(msgUser, req, res, session);
							break;
						case R_RT_ENTER_PRINT :
							procReqRTEnterPrint(msgUser, req, res, session);
							break;
						case R_SV_ENTER_PRINT :
							procReqSVEnterPrint(msgUser, req, res, session);
							break;
						case R_INQ_ACCOUNT_ANALYSIS :
							procReqInqAccountAnalysis(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// Action 
						case A_RT_ENTER_NEW :
							procActionRTEnterNew(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_SV_ENTER_NEW :
							procActionSVEnterNew(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_SV_ENTER_MAINT :
							procActionEnterSVMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_ENTER_INQUIRY :
						case A_SV_ENTER_INQUIRY :
							procActionEnterInquiry(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_ENTER_PRINT :
						case A_SV_ENTER_PRINT :
							procActionEnterPrint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case R_INQ_CHECKBOOK :
							procReqInqCheckbook(mc, msgUser, req, res, session);
							break;

							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 4;
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

	protected void procReqAccountTitle(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgMailA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		if (userPO.getPurpose().equals("NEW"))
			opCode = "0001";
		else
			opCode = "0002";

		// Send Initial data
		try {
			msgMailA = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgMailA.setH04USR(user.getH01USR());
			msgMailA.setH04PGM("ESD0080");
			msgMailA.setH04TIM(getTimeStamp());
			msgMailA.setH04SCR("01");
			msgMailA.setH04OPE(opCode);
			msgMailA.setE04CUN(userPO.getIdentifier());
			msgMailA.setE04RTP("1");
			msgMailA.setH04WK1("T");
			msgMailA.setH04WK3("1");
			msgMailA.send();
			msgMailA.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgMailA = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgMailA = (ESD000004Message) newmessage;
				// showESD000004(msgMailA);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailA);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_account_title.jsp");
							callPage(
								LangPath + "EDD0000_rt_account_title.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_account_title.jsp");
							callPage(
								LangPath + "EDD0000_sv_account_title.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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

	protected void procActionAccountTitle(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgMailAddress = null;
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
			msgMailAddress =
				(ESD000004Message) mc.getMessageRecord("ESD000004");
			// msgMailAddress = (ESD000004Message)ses.getAttribute("mailA");
			msgMailAddress.setH04USR(user.getH01USR());
			msgMailAddress.setH04PGM("ESD0080");
			msgMailAddress.setH04TIM(getTimeStamp());
			msgMailAddress.setH04SCR("01");
			msgMailAddress.setH04OPE("0005");
			msgMailAddress.setE04CUN(userPO.getIdentifier());
			msgMailAddress.setE04RTP("1");
			msgMailAddress.setH04WK1("T");
			msgMailAddress.setH04WK3("1");

			// all the fields here
			java.util.Enumeration enu = msgMailAddress.fieldEnumeration();
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

			msgMailAddress.send();
			msgMailAddress.destroy();
			flexLog("ESD000004 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgMailAddress = new datapro.eibs.beans.ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgMailAddress = (ESD000004Message) newmessage;
				// showESD000004(msgMailAddress);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailAddress);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
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
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_account_title.jsp");
							callPage(
								LangPath + "EDD0000_rt_account_title.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_account_title.jsp");
							callPage(
								LangPath + "EDD0000_sv_account_title.jsp",
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
	protected void procReqInqCheckbook(
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
			msgRT.setH01FLGWK1("R");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(userPO.getIdentifier());
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
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
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
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD1000_rt_inq_checkbook.jsp");
							callPage(
								LangPath + "EDD1000_rt_inq_checkbook.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_sv_enter_inquiry.jsp");
							callPage(
								LangPath + "EDD0000_sv_enter_inquiry.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0000_rt_enter_inquiry.jsp");
							callPage(
								LangPath + "EDD0000_rt_enter_inquiry.jsp",
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