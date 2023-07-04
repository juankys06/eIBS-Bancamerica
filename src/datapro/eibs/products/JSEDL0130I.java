package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
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

import datapro.eibs.beans.ECN0030DSMessage;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.EDL016004Message;
import datapro.eibs.beans.EDL016001Message;
import datapro.eibs.beans.EDL016002Message;
import datapro.eibs.beans.EDL016007Message;
import datapro.eibs.beans.EDL029001Message;
import datapro.eibs.beans.EDL030502Message;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.ELD000001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD000008Message;
import datapro.eibs.beans.EDL016099Message;
import datapro.eibs.beans.ESD071111Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0002DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0130I extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	//inquiry options
	protected static final int R_INQUIRY = 13;
	protected static final int R_CODES_INQ = 27;
	protected static final int R_TITULARES_INQ = 29;
	protected static final int R_SPECIAL_INST_INQ = 31;
	protected static final int R_BASIC_INQ = 41;
	protected static final int R_BASIC_CONTR = 42;
	protected static final int R_PROD_INQ = 43;
	protected static final int R_BENE_INQ = 44;
	protected static final int R_CALC_INQ = 45;
	protected static final int R_GARANT_INQ = 46;
	protected static final int R_RELATION = 48;
	protected static final int R_TABLES = 49;
	protected static final int R_CD_SIGNERS = 55;

	protected static final int A_CALC_INQ = 47;
	protected static final int A_CALC_PTY = 53;
	protected static final int R_CALC_PTY = 52;
	protected static final int R_DISB_INQ = 50;
	protected static final int R_MONEY = 51;
	
	protected static final int R_ACCOUNT_TITLE = 57;
	protected static final int R_DISPLAY_TITLE = 58;
	
	
	// entering options
	protected static final int R_ENTER_INQUIRY = 300;
	protected static final int R_ENTER_INQUIRY_ACC = 500;
	protected static final int R_ENTER_PRINT = 1100;

	protected static final int A_ENTER_INQUIRY = 600;
	protected static final int A_ENTER_PRINT = 1200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0130I() {
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
	 */
	protected void procActionCalcInt(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL029001Message msgCD = null;
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
			msgCD = (EDL029001Message) ses.getAttribute("inqLoans");
			//msgCD = (EDL029001Message)mc.getMessageRecord("EDL029001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0290");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			try {

				try {
					msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
				} catch (Exception e) {
					flexLog("E01DEAACC");
				}

				msgCD.setE01DEAPRO("");

				try {
					msgCD.setE01DEACUN(req.getParameter("E01DEACUN"));
				} catch (Exception e) {
					flexLog("E01DEACUN");
				}

				msgCD.setE01CUSNA1("");

				try {
					msgCD.setE01DEAOD1(req.getParameter("E01DEAOD1"));
					msgCD.setE01DEAOD2(req.getParameter("E01DEAOD2"));
					msgCD.setE01DEAOD3(req.getParameter("E01DEAOD3"));
				} catch (Exception e) {
					flexLog("Opening Date");
				}

				try {
					msgCD.setE01DEATRM(req.getParameter("E01DEATRM"));
				} catch (Exception e) {
					flexLog("E01DEATRM");
				}

				msgCD.setE01DEATRC(req.getParameter("E01DEATRC"));

				try {
					msgCD.setE01DEAMD1(req.getParameter("E01DEAMD1"));
					msgCD.setE01DEAMD2(req.getParameter("E01DEAMD2"));
					msgCD.setE01DEAMD3(req.getParameter("E01DEAMD3"));
				} catch (Exception e) {
					flexLog("Maturity Date");
				}

				try {
					msgCD.setE01DEARTE(req.getParameter("E01DEARTE"));
				} catch (Exception e) {
					flexLog("E01DEARTE");
				}

				try {
					msgCD.setE01DSCRTE(req.getParameter("E01DSCRTE"));
				} catch (Exception e) {
					flexLog("E01DSCRTE");
				}

				try {
					msgCD.setE01VENCI1(req.getParameter("E01VENCI1"));
					msgCD.setE01VENCI2(req.getParameter("E01VENCI2"));
					msgCD.setE01VENCI3(req.getParameter("E01VENCI3"));
				} catch (Exception e) {
					flexLog("Revision Date");
				}

				msgCD.setE01CUMMA1(req.getParameter("E01CUMMA1"));
				msgCD.setE01CUMMA2(req.getParameter("E01CUMMA2"));
				msgCD.setE01CUMMA3(req.getParameter("E01CUMMA3"));
				msgCD.setE01CUMMA4(req.getParameter("E01CUMMA4"));

				msgCD.setE01CUMCTY(req.getParameter("E01CUMCTY"));
				msgCD.setE01CUMCTR(req.getParameter("E01CUMCTR"));
				msgCD.setE01CUMSTE(req.getParameter("E01CUMSTE"));
				msgCD.setE01CUMZPC(req.getParameter("E01CUMZPC"));
				msgCD.setE01CUMPOB(req.getParameter("E01CUMPOB"));

				try {
					msgCD.setE01DEABAS(req.getParameter("E01DEABAS"));
				} catch (Exception e) {
					flexLog("E01DEABAS");
				}

				msgCD.setE01DEAICT(req.getParameter("E01DEAICT").toUpperCase());

				try {
					msgCD.setE01DEAPRI(req.getParameter("E01DEAPRI"));
				} catch (Exception e) {
					flexLog("E01DEAPRI");
				}

				try {
					msgCD.setE01DEAINT(req.getParameter("E01DEAINT"));
				} catch (Exception e) {
					flexLog("E01DEAINT");
				}

				try {
					msgCD.setE01TOTDUE(req.getParameter("E01TOTDUE"));
				} catch (Exception e) {
					flexLog("E01TOTDUE");
				}

				try {
					msgCD.setE01DYSNME(req.getParameter("E01DYSNME"));
				} catch (Exception e) {
					flexLog("E01DYSNME");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			//msgCD.send();
			mc.sendMessage(msgCD);
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

			if (newmessage.getFormatName().equals("EDL029001")) {
				try {
					msgCD =new datapro.eibs.beans.EDL029001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL029001Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE01DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("inqLoans", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page1: "
								+ LangPath
								+ "EDL0290_cd_cal.jsp");
						callPage(LangPath + "EDL0290_cd_cal.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page2: "
								+ LangPath
								+ "EDL0290_cd_cal.jsp");
						callPage(LangPath + "EDL0290_cd_cal.jsp", req, res);
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
	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0160");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			try {
				if (req.getParameter("E01DEAACC") != null)
					msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
				flexLog("Certificate Sent");
			} catch (Exception e) {
				msgCD.setE01DEAACC("0");
				flexLog(" error " + e);
				flexLog("Certificate Error Sent");
			}
			flexLog("Send command");
			msgCD.send();
			flexLog("Destroy command");
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_enter_inquiry.jsp");
					callPage(
						LangPath + "EDL0160_cd_enter_inquiry.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD =new datapro.eibs.beans.EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setBank(msgCD.getE01DEABNK());
				userPO.setBranch(msgCD.getE01DEABRN());
				userPO.setCusNum(msgCD.getE01DEACUN());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(userPO.getCusNum());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setHeader23(msgCD.getE01DEARTB());
				userPO.setCurrency(msgCD.getE01DEACCY());
				userPO.setOfficer(
					msgCD.getE01DEAOFC() + " - " + msgCD.getE01DSCOFC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdInquiry", msgCD);
				ses.setAttribute("error", msgError);

				procReqMaintPay(user, req, res, ses);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
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
	protected void procActionEnterPrint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFT000010Message msgCD = null;
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
			msgCD = (EFT000010Message) mc.getMessageRecord("EFT000010");
			msgCD.setH10USERID(user.getH01USR());
			msgCD.setH10PROGRM("EDL0130");
			msgCD.setH10TIMSYS(getTimeStamp());
			msgCD.setH10SCRCOD("01");
			msgCD.setH10OPECOD("0002");
			try {
				msgCD.setE10DEAACC(req.getParameter("E10DEAACC"));
			} catch (Exception e) {
				msgCD.setE10DEAACC("0");
			}

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

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCD = new datapro.eibs.beans.EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EFT000010Message) newmessage;

				userPO.setIdentifier(msgCD.getE10DEAACC());
				userPO.setHeader1(msgCD.getE10DEAPRO());
				userPO.setHeader2(msgCD.getE10DEACUN());
				userPO.setHeader3(msgCD.getE10CUSNA1());
				userPO.setHeader9(msgCD.getE10BANKNM());
				userPO.setHeader10(msgCD.getE10CUSIDN());
				userPO.setHeader11(msgCD.getE10CNTRD1());
				userPO.setHeader12(msgCD.getE10CNTRD2());
				userPO.setHeader13(msgCD.getE10CNTRD3());
				userPO.setHeader14(msgCD.getE10RATE());
				userPO.setHeader15(msgCD.getE10DEASPR());
				userPO.setHeader16(msgCD.getE10CUSTID());
				userPO.setHeader17(msgCD.getE10DSCPRO());
				userPO.setHeader18(msgCD.getE10BRNNME());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdFinish", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_finish.jsp");
						callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_enter_print.jsp");
						callPage(
							LangPath + "EDL0130_cd_enter_print.jsp",
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
	protected void procReqCalcInt(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL029001Message msgCalc = null;
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
			msgCalc = (EDL029001Message) mc.getMessageRecord("EDL029001");
			msgCalc.setH01USERID(user.getH01USR());
			msgCalc.setH01PROGRM("EDL0130");
			msgCalc.setH01TIMSYS(getTimeStamp());
			msgCalc.setH01SCRCOD("01");
			msgCalc.setH01OPECOD(opCode);
			msgCalc.setE01DEAACC(userPO.getIdentifier());
			msgCalc.send();
			msgCalc.destroy();
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

			if (newmessage.getFormatName().equals("EDL029001")) {
				try {
					msgCalc = new datapro.eibs.beans.EDL029001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCalc = (EDL029001Message) newmessage;
				// showESD008004(msgCalc);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("inqLoans", msgCalc);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0290_cd_cal.jsp");
						callPage(LangPath + "EDL0290_cd_cal.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0160_cd_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
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
	protected void procReqCollLiabilities(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ERA0000_collateral_list.jsp");
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=3");
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqContr(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setE01DEAACC(userPO.getIdentifier());
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

				try {
					flexLog(
						"About to call Page4: "
							+ LangPath
							+ "EDL0130_cd_finish.jsp");
					callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
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

		// Receive Data
		try {
			if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new datapro.eibs.beans.EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInquiry", msgCD);

				// There are no errors
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "EDL0130_cd_rp_contr.jsp");
					callPage(LangPath + "EDL0130_cd_rp_contr.jsp", req, res);
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
	protected void procReqEnterInquiry(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
        String AppCode = "";
		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();				
			userPO = new datapro.eibs.beans.UserPos();
			try {
				AppCode = req.getParameter("AppCode");
				if (AppCode == null) {
					AppCode = "CD";
				}
			} catch (Exception e) {
				AppCode = "CD";
			}
			userPO.setOption(AppCode);
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
					+ "EDL0160_cd_enter_inquiry.jsp");
			callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterInquiryAcc(
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
			userPO.setOption("CD");
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
					+ "EDL0160_cd_enter_inquiry_acc.jsp");
			callPage(LangPath + "EDL0160_cd_enter_inquiry_acc.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

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
			userPO.setOption("CD");
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
					+ "EDL0130_cd_enter_print.jsp");
			callPage(LangPath + "EDL0130_cd_enter_print.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEspInstInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
				
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try {
			msgCD = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE(opCode);
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000005")) {
				try {
					msgCD = new datapro.eibs.beans.ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdInst", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_special_inst.jsp");
					callPage(
						LangPath + "EDL0130_cd_inq_special_inst.jsp",
						req,
						res);
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
	protected void procReqInqBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL016002Message) mc.getMessageRecord("EDL016002");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("EDL0160");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD(opCode);
			msgCD.setE02DEAACC(userPO.getIdentifier());
			msgCD.setE02DEAACD("CD");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016002")) {
				try {
					msgCD = new datapro.eibs.beans.EDL016002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdMant", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_basic.jsp");
					callPage(LangPath + "EDL0130_cd_inq_basic.jsp", req, res);
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
	protected void procReqInqBene(
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
		opCode = "0006";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("ESD0080");
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

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("bene", msgBene);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_bene.jsp");
					callPage(LangPath + "EDL0130_cd_inq_bene.jsp", req, res);
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
	protected void procReqInqTable(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECN0030DSMessage msgCDRenRat = null;
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
			msgCDRenRat = (ECN0030DSMessage) mc.getMessageRecord("ECN0030DS");
			msgCDRenRat.setH02USERID(user.getH01USR());
			msgCDRenRat.setH02PROGRM("ESD0711");
			msgCDRenRat.setH02TIMSYS(getTimeStamp());
			msgCDRenRat.setH02SCRCOD("01");
			msgCDRenRat.setH02OPECOD(opCode);
			msgCDRenRat.setE02CDRCCY(userPO.getCurrency());
			msgCDRenRat.setE02CDRRTB(userPO.getHeader23());
			msgCDRenRat.send();
			msgCDRenRat.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ECN0030DS")) {
				try {
					msgCDRenRat = new datapro.eibs.beans.ECN0030DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCDRenRat = (ECN0030DSMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdRenRat", msgCDRenRat);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_ren_rate.jsp");
					callPage(
						LangPath + "EDL0130_cd_inq_ren_rate.jsp",
						req,
						res);
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
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0160");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setE01DEAACC(userPO.getIdentifier());
			msgCD.setE01DEAACD("CD");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_enter_inquiry.jsp");
					callPage(
						LangPath + "EDL0160_cd_enter_inquiry.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new datapro.eibs.beans.EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdInquiry", msgCD);
				ses.setAttribute("error", msgError);

				procReqMaintPay(user, req, res, ses);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
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
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqProductCD(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD071111Message msgProdCD = null;
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
			msgProdCD = (ESD071111Message) mc.getMessageRecord("ESD071111");
			msgProdCD.setH11USERID(user.getH01USR());
			msgProdCD.setH11PROGRM("ESD0711");
			msgProdCD.setH11TIMSYS(getTimeStamp());
			msgProdCD.setH11SCRCOD("01");
			msgProdCD.setH11OPECOD(opCode);
			msgProdCD.setE11APCCDE(prodCode);
			msgProdCD.setE11APCBNK(bank);
			msgProdCD.send();
			msgProdCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD071111")) {
				try {
					msgProdCD = new datapro.eibs.beans.ESD071111Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgProdCD = (ESD071111Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdProdInq", msgProdCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_products.jsp");
					callPage(
						LangPath + "EDL0130_cd_inq_products.jsp",
						req,
						res);
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
	protected void procReqRel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL030502Message msgRel = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		String chk = "";
		String marker = "";
		String myFlag = "";
		StringBuffer myRow = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int type = 0;
		String num = "";

		// Send Initial data EDL030502
		try {
			flexLog("Send Initial Data");
			msgRel = (EDL030502Message) mc.getMessageRecord("EDL030502");
			msgRel.setH02USERID(user.getH01USR());
			msgRel.setH02PROGRM("EDL0305");
			msgRel.setH02TIMSYS(getTimeStamp());
			msgRel.setH02SCRCOD("01");
			msgRel.setH02OPECOD("0004");
			msgRel.setE02PRIACC(userPO.getIdentifier());

			msgRel.send();
			msgRel.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
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

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDL030502")) {
				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				while (true) {

					msgRel = (EDL030502Message) newmessage;

					marker = msgRel.getE02ENDFLD();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						myFlag = "2";
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.formatCell(msgRel.getE02RELACC())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.formatCell(msgRel.getE02RELNME())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.formatDate(
								msgRel.getE02RELMA1(),
								msgRel.getE02RELMA2(),
								msgRel.getE02RELMA3())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.fcolorCCY(msgRel.getE02RELRTE())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.fcolorCCY(msgRel.getE02RELPRI())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.fcolorCCY(msgRel.getE02RELINT())
							+ "</A></TD>");
						myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('"
							+ msgRel.getE02RELACC()
							+ "')\">"
							+ Util.formatCell(msgRel.getE02RELTYP())
							+ "</A></TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());

					}

					newmessage = mc.receiveMessage();

				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("cifPos", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0305_cd_rel.jsp");
					callPage(LangPath + "EDL0305_cd_rel.jsp", req, res);

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
	protected void procReqSpecialCodesInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try {
			msgCD = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EDL0130");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02SCR("01");
			msgCD.setH02OPE(opCode);
			msgCD.setE02ACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgCD = new datapro.eibs.beans.ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdCodes", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_codes.jsp");
					callPage(LangPath + "EDL0130_cd_inq_codes.jsp", req, res);
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
	protected void procReqTitInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgCD = null;
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
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EDL0130");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06SCR("01");
			msgCD.setH06OPE(opCode);
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgCD = new datapro.eibs.beans.ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdTit", msgCD);
				ses.setAttribute("error", msgError);
				getTitularsDescription(user, req, res, ses);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0130_cd_inq_tit.jsp");
					callPage(LangPath + "EDL0130_cd_inq_tit.jsp", req, res);
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

			int screen = R_ENTER_INQUIRY;

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
						// BEGIN CD

						//Inquiry Options
						case R_INQUIRY :
							procReqInquiry(mc, msgUser, req, res, session);
							break;
						case R_CODES_INQ :
							procReqSpecialCodesInq(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_TITULARES_INQ :
							procReqTitInq(mc, msgUser, req, res, session);
							break;
						case R_SPECIAL_INST_INQ :
							procReqEspInstInq(mc, msgUser, req, res, session);
							break;
						case R_BASIC_INQ :
							procReqInqBasic(mc, msgUser, req, res, session);
							break;
						case R_PROD_INQ :
							procReqProductCD(mc, msgUser, req, res, session);
							break;
						case R_BENE_INQ :
							procReqInqBene(mc, msgUser, req, res, session);
							break;
						case R_CALC_INQ :
							procReqCalcInt(mc, msgUser, req, res, session);
							break;
						case R_ACCOUNT_TITLE :
							procReqAccountTitle(mc, msgUser, req, res, session);
							break;	
						case R_DISPLAY_TITLE :
							procReqDisplayTitle(mc, msgUser, req, res, session);
							break;																		

						case R_RELATION :
							procReqRel(mc, msgUser, req, res, session);
							break;
						case R_TABLES :
							procReqInqTable(mc, msgUser, req, res, session);
							break;
						case R_GARANT_INQ :
							procReqCollLiabilities(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_DISB_INQ :
							procReqDisbInq(mc, msgUser, req, res, session);
							break;
						case R_MONEY :
							procReqInqMoney(mc, msgUser, req, res, session);
							break;
						case R_CALC_PTY :
							procReqCalcPty(mc, msgUser, req, res, session);
							break;
						case R_CD_SIGNERS :
							procReqInqSigners(mc, msgUser, req, res, session);
							break;
							//Reports	
						case R_BASIC_CONTR :
							procReqContr(mc, msgUser, req, res, session);
							break;

							//Action
						case A_CALC_INQ :
							procActionCalcInt(mc, msgUser, req, res, session);
							break;
						case A_CALC_PTY :
							procActionCalcPty(mc, msgUser, req, res, session);
							break;		
							
							// BEGIN Entering
							// Request
						case R_ENTER_INQUIRY :
							procReqEnterInquiry(msgUser, req, res, session);
							break;
						case R_ENTER_PRINT :
							procReqEnterPrint(msgUser, req, res, session);
							break;
						case R_ENTER_INQUIRY_ACC :
							procReqEnterInquiryAcc(msgUser, req, res, session);
							break;

							// Action 
						case A_ENTER_INQUIRY :
							procActionEnterInquiry(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_PRINT :
							procActionEnterPrint(
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionCalcPty(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016007Message msgCalc = null;
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
			msgCalc = (EDL016007Message) mc.getMessageRecord("EDL016007");
			msgCalc.setH07USERID(user.getH01USR());
			msgCalc.setH07PROGRM("EDL0160");
			msgCalc.setH07TIMSYS(getTimeStamp());
			msgCalc.setH07SCRCOD("01");
			msgCalc.setH07OPECOD(opCode);
			try {
				try {
					msgCalc.setE07DEAACC(req.getParameter("E07DEAACC"));
				} catch (Exception e) {
					flexLog("E07DEAACC");
				}

				try {
					msgCalc.setE07TRNTOT(req.getParameter("E07TRNTOT"));
				} catch (Exception e) {
					flexLog("E07TRNTOT");
				}
				try {
					msgCalc.setE07TRNVD1(req.getParameter("E07TRNVD1"));
				} catch (Exception e) {
					flexLog("E07TRNVD1");
				}
				try {
					msgCalc.setE07TRNVD2(req.getParameter("E07TRNVD2"));
				} catch (Exception e) {
					flexLog("E07TRNVD2");
				}
				try {
					msgCalc.setE07TRNVD3(req.getParameter("E07TRNVD3"));
				} catch (Exception e) {
					flexLog("E07TRNVD3");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgCalc.send();
			msgCalc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);

			} else if (newmessage.getFormatName().equals("EDL016007")) {
				try {
					msgCalc = new datapro.eibs.beans.EDL016007Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCalc = (EDL016007Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("calcPty", msgCalc);

			}

			try {
				flexLog(
					"About to call Page4: "
						+ LangPath
						+ "EDL0130_cd_penalty_cal.jsp");
				callPage(LangPath + "EDL0130_cd_penalty_cal.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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
	protected void procReqCalcPty(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016007Message msgCalc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		msgError = new ELEERRMessage();
		msgCalc = new EDL016007Message();

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("calcPty", msgCalc);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog(
				"About to call Page3: "
					+ LangPath
					+ "EDL0130_cd_penalty_cal.jsp");
			callPage(LangPath + "EDL0130_cd_penalty_cal.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqDisbInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000008Message msgCD = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCD = (ESD000008Message) mc.getMessageRecord("ESD000008");
			msgCD.setH08USR(user.getH01USR());
			msgCD.setH08PGM("PAYMTINST");
			msgCD.setH08TIM(getTimeStamp());
			msgCD.setH08SCR("01");
			msgCD.setH08OPE(opCode);
			msgCD.setE08ACC(userPO.getIdentifier());
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
				showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page4: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000008")) {
				try {
					msgCD = new datapro.eibs.beans.ESD000008Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000008Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdDisb", msgCD);
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "EDL0130_cd_inq_disb_inst.jsp");
					callPage(
						LangPath + "EDL0130_cd_inq_disb_inst.jsp",
						req,
						res);
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
	protected void procReqInqMoney(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELD000001Message msgLaunder = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try {
			msgLaunder = (ELD000001Message) mc.getMessageRecord("ELD000001");
			msgLaunder.setH06USERID(user.getH01USR());
			msgLaunder.setH06PROGRM("EDL0130");
			msgLaunder.setH06TIMSYS(""); //getTimeStamp()
			msgLaunder.setH06SCRCOD("01");
			msgLaunder.setH06OPECOD(opCode);
			msgLaunder.setE06LDMACC(userPO.getIdentifier());
			msgLaunder.setH06FLGWK1("1");
			msgLaunder.send();
			msgLaunder.destroy();
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
			flexLog("Read error " + e);
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELD000001")) {
				try {
					msgLaunder = new datapro.eibs.beans.ELD000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLaunder = (ELD000001Message) newmessage;
				// showESD008004(msgLaunder);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMoney", msgLaunder);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0130_cd_inq_money.jsp");
						callPage(
							LangPath + "EDL0130_cd_inq_money.jsp",
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
								+ "EDL0160_cd_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
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

	protected void procReqInqSigners(
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

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDL0130");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
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
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

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
			} else if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new datapro.eibs.beans.ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFirm", msgBene);

				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "EDL0130_cd_inq_firm.jsp");
					callPage(LangPath + "EDL0130_cd_inq_firm.jsp", req, res);
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
	protected void procReqMaintPay(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageContext mc = null;
		Socket s = null;
		MessageRecord newmessage = null;
		EDL016004Message msgPmnt = null;
		JBListRec pmntList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

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

			// Send Initial data
			try {
				msgPmnt = (EDL016004Message) mc.getMessageRecord("EDL016004");
				msgPmnt.setH04USERID(user.getH01USR());
				msgPmnt.setH04PROGRM("EDL0160");
				msgPmnt.setH04TIMSYS(getTimeStamp());
				msgPmnt.setH04SCRCOD("01");
				msgPmnt.setH04OPECOD("0002");
				try {
					msgPmnt.setE04DEAACC(userPO.getIdentifier());
				} catch (Exception e) {
					msgPmnt.setE04DEAACC("0");
				}

				msgPmnt.send();
				msgPmnt.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			flexLog("Initializing java beans into the session");
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try {
				int colNum = 4;
				pmntList = new datapro.eibs.beans.JBListRec();
				pmntList.init(colNum);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else if (newmessage.getFormatName().equals("EDL016004")) {
					//Deductions List
					// Fill List bean

					int colNum = 4;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgPmnt = (EDL016004Message) newmessage;

						marker = msgPmnt.getH04FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							myRow[0] = Util.formatCell(msgPmnt.getE04DLPPNU());
							// Quote Number
							myRow[1] =
								Util.formatDate(
									msgPmnt.getE04DLPPD1(),
									msgPmnt.getE04DLPPD2(),
									msgPmnt.getE04DLPPD3());
							// Date
							myRow[2] = Util.formatCCY(msgPmnt.getE04DLPPRI());
							// Principal
							myRow[3] = Util.formatCell(msgPmnt.getE04DLPINT());
							// Interest

							pmntList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("error", msgError);
					ses.setAttribute("pmnt", pmntList);

				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
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
		opCode = "0002";

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
			msgMailA.setH04WK3("2");
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
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDL0130_cd_inq_basic.jsp");
				callPage(LangPath + "EDL0130_cd_inq_basic.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}			
		  }
		  else if (newmessage.getFormatName().equals("ESD000004")) {
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

				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0130_cd_inq_account_title.jsp");
					callPage(LangPath + "EDL0130_cd_inq_account_title.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
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
	protected void procReqDisplayTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016099Message msgRT = null;
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

		String opCode = "0004";
	
		// Send Initial data
		try
		{
			msgRT = (EDL016099Message)mc.getMessageRecord("EDL016099");
			msgRT.setH99USERID(user.getH01USR());
			msgRT.setH99PROGRM("ESD0000");
			msgRT.setH99TIMSYS("");//getTimeStamp()
			msgRT.setH99SCRCOD("01");
			msgRT.setH99OPECOD(opCode);
			msgRT.setE99ACCNUM(userPO.getIdentifier());
			msgRT.send();	
			msgRT.destroy();
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
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDL0130_cd_inq_basic.jsp");
				callPage(LangPath + "EDL0130_cd_inq_basic.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}			
		  }
		  else if (newmessage.getFormatName().equals("EDL016099")) {
			  try {
				  msgRT = new datapro.eibs.beans.EDL016099Message();
			  } 
			  catch (Exception ex) {
				  flexLog("Error: " + ex); 
			  }
	
			  msgRT = (EDL016099Message)newmessage;
			  // showESD008004(msgRT);
			  //userPO.setHeader10(msgRT.getE02ACMATY());
			  //userPO.setHeader11(msgRT.getE02ACMACL());
				
			  flexLog("Putting java beans into the session");
			  ses.setAttribute("error", msgError);
			  ses.setAttribute("cdTitle", msgRT);

				try {
					flexLog("About to call Page3: " + LangPath + "EDL0130_cd_inq_display_title.jsp");
					callPage(LangPath + "EDL0130_cd_inq_display_title.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
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