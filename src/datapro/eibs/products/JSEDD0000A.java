package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
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


public class JSEDD0000A extends datapro.eibs.master.SuperServlet {

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
	protected static final int R_RT_ACCOUNT_ANALYSIS = 101;
	
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
	
	protected static final int R_ACCOUNT_TITLE 			= 57;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0000A() {
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
			msgRT.setH01OPECOD("0002");
			msgRT.setH01FLGWK1("A");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
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
						userPO.setCusType(msgRT.getH01FLGWK3());

						ses.setAttribute("userPO", userPO);
						ses.setAttribute("svBasic", msgRT);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
						callPage(LangPath + "EDD1000_rt_ap_account_analysis.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			try{
				msgBene.setH04SCR(req.getParameter("H04SCR"));
			}catch (Exception e){
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
								+ "EDD1000_rt_ap_firm.jsp");
						callPage(LangPath + "EDD1000_rt_ap_firm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
								+ "EDD1000_rt_ap_bene.jsp");
						callPage(LangPath + "EDD1000_rt_ap_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
	
		String type = "I";
		//type = req.getParameter("Type");
		if (type == null) type = ""; 
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
								+ "EDD1000_rt_ap_legal_rep.jsp");
						callPage(LangPath + "EDD1000_rt_ap_legal_rep.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
								+ "EDD1000_rt_ap_credit.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_credit.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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
			msgRT.setH06SCRCOD("05");
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
								+ "EDD1000_rt_ap_money.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_money.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
								+ "EDD1000_rt_ap_overdraft.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_overdraft.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
								+ "EDD1000_rt_ap_overnight.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_overnight.jsp",
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
	protected void procReqRTStatus(
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
								+ "EDD1000_rt_ap_chg_status.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_chg_status.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
								+ "EDD1000_rt_ap_special_inst.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_special_inst.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1000_rt_ap_codes.jsp");
						callPage(
							LangPath + "EDD1000_rt_ap_codes.jsp",
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
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
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
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD1000_rt_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_rt_ap_basic.jsp",
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
									+ "EDD1000_sv_ap_basic.jsp");
							callPage(
								LangPath + "EDD1000_sv_ap_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtTit", msgRT);

				if (IsNotError) { // There are no errors 
					// Load Descriptions for CNOFC Table T8
					getTitularsDescription(user, req, res, ses);
											
					try {
						flexLog("About to call Page3: " + LangPath + "EDD1000_rt_ap_tit.jsp");
						callPage(LangPath + "EDD1000_rt_ap_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog("About to call Page4: " + LangPath + "EDD1000_rt_ap_basic.jsp");
							callPage(LangPath + "EDD1000_rt_ap_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog("About to call Page4: " + LangPath + "EDD1000_sv_ap_basic.jsp");
							callPage(LangPath + "EDD1000_sv_ap_basic.jsp", req, res);
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
							procReqRTStatus(mc, msgUser, req, res, session);
							break;
						case R_RT_CODES :
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_RT_ACCOUNT_ANALYSIS :
							procReqAccountAnalysis(mc, msgUser, req, res, session);
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

							//Savings
						case R_SV_MAINTENANCE :
							procReqSVMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// BEGIN EnterinG
							// Request
						case R_RT_ENTER_MAINT :
							procReqRTEnterMaint(msgUser, req, res, session);
							break;
						case R_SV_ENTER_MAINT :
							procReqSVEnterMaint(msgUser, req, res, session);
							break;

							// Action 
						case A_RT_ENTER_MAINT :
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
					int sck = getInitSocket(req) + 4;
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
	
	protected void procReqAccountTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgMailA = null;
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

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgMailA = (ESD000004Message)mc.getMessageRecord("ESD000004");
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgMailA = new datapro.eibs.beans.ESD000004Message(); 
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgMailA = (ESD000004Message)newmessage;
				// showESD000004(msgMailA);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailA);

				if (IsNotError) {  // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD1000_rt_ap_account_title.jsp");
							callPage(LangPath + "EDD1000_rt_ap_account_title.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD1000_sv_ap_account_title.jsp");
							callPage(LangPath + "EDD1000_sv_ap_account_title.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}						
					}
				}
				else {  // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD1000_rt_ap_basic.jsp");
							callPage(LangPath + "EDD1000_rt_ap_basic.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD1000_sv_ap_basic.jsp");
							callPage(LangPath + "EDD1000_sv_ap_basic.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}						
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
	
	protected void getTitularsDescription(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0002DSMessage msgCnofc = null;
		boolean IsNotError = false;
		JBObjList ObjList = null;
		MessageContext mc = null;

		try {
			flexLog("Opennig Socket Connection");
			Socket s = null;
			s = new Socket(super.hostIP, getInitSocket(req));
			s.setSoTimeout(super.sckTimeOut);
			mc = new MessageContext(getMessageHandler("EWD0002",req));
			try {
				msgCnofc = (EWD0002DSMessage) mc.getMessageRecord("EWD0002DS");
				msgCnofc.setEWDREC("0");
				msgCnofc.setEWDTBL("T8");
				msgCnofc.send();
				msgCnofc.destroy();
				flexLog("EWD000202 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				return;
			}
			// Receiving
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EWD0002DS")) {
					try {
						ObjList = new JBObjList();
					}
					catch(Exception ex) {
						System.out.println("Error: " + ex);
					}
					try {
						StringBuffer myRow = null;
						for(msgCnofc = (EWD0002DSMessage)newmessage; !msgCnofc.getEWDOPE().equals("*"); msgCnofc = (EWD0002DSMessage)newmessage) {
							ObjList.addRow(msgCnofc);
							newmessage = mc.receiveMessage();
						}
						ses.setAttribute("cnofcList", ObjList);
					}
					catch(Exception e) {
						e.printStackTrace();
						System.out.println("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
				}
			} catch(Exception e) {
					e.printStackTrace();
					System.out.println("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
		}
	}	
	
}