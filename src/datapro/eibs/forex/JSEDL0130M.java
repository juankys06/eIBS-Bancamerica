package datapro.eibs.forex;

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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.EDL013003Message;
import datapro.eibs.beans.EDL013004Message;
import datapro.eibs.beans.EDL013006Message;
import datapro.eibs.beans.EDL013007Message;
import datapro.eibs.beans.EDL013008Message;
import datapro.eibs.beans.EDL013009Message;
import datapro.eibs.beans.EDL016001Message;
import datapro.eibs.beans.EDL016002Message;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.ELD000001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD000008Message;
import datapro.eibs.beans.ESD071111Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ESWF10001Message;
import datapro.eibs.beans.ESWF10002Message;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0130M extends datapro.eibs.master.SuperServlet {

	// Socket 3 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;
	protected static final int R_TRANSACTION = 5;
	protected static final int A_TRANSACTION = 6;
	protected static final int R_TITULARES = 7;
	protected static final int A_TITULARES = 8;
	protected static final int R_PAYMENT_PLAN = 9;
	protected static final int A_PAYMENT_PLAN = 10;
	protected static final int R_PRECANCEL = 11;
	protected static final int A_PRECANCEL = 12;
	protected static final int R_SPECIAL_INST = 15;
	protected static final int A_SPECIAL_INST = 16;
	protected static final int R_BENEFICIARY = 17;
	protected static final int A_BENEFICIARY = 18;
	protected static final int R_EXCHANGE = 19;
	protected static final int A_EXCHANGE = 20;
	protected static final int R_CODES = 21;
	protected static final int A_CODES = 22;
	protected static final int R_RENOV_MANT = 33;
	protected static final int A_RENOV_MANT = 34;
	protected static final int R_RENOV_OPEN = 35;
	protected static final int A_RENOV_OPEN = 36;
	protected static final int R_FINISH = 37;
	protected static final int A_FINISH = 38;
	protected static final int R_INT_PREP = 39;
	protected static final int A_INT_PREP = 40;
	protected static final int R_PRINT_FIRST = 47;
	protected static final int A_PRINT_FIRST = 48;
	protected static final int R_OTHERS_TRANS = 49;
	protected static final int R_DISBURSEMENT = 50;
	protected static final int A_DISBURSEMENT = 51;
	protected static final int R_CD_SIGNERS = 55;
	protected static final int A_CD_SIGNERS = 56;

	
	//Socket 1
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
	// Entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 200;
	protected static final int R_ENTER_INQUIRY = 300;
	protected static final int R_ENTER_CANCEL = 700;
	protected static final int R_ENTER_TRANSAC = 900;
	protected static final int R_ENTER_PRINT = 1100;
	protected static final int R_ENTER_MAINT_T = 1300;
	protected static final int A_ENTER_NEW = 400;
	protected static final int A_ENTER_MAINT = 500;
	protected static final int A_ENTER_INQUIRY = 600;
	protected static final int A_ENTER_CANCEL = 800;
	protected static final int A_ENTER_TRANSAC = 1000;
	protected static final int A_ENTER_PRINT = 1200;
	
	// Socket 1
	protected static final int R_MONEY = 52;
	protected static final int A_MONEY = 53;
	protected static final int R_S_CDS			= 61;
	protected static final int A_S_CDS			= 62;	
	protected static final int R_SC_CDS			= 63;

	protected String LangPath = "S";

	/**
	 * Constructor
	 */
	public JSEDL0130M() {
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
		int screen = R_ENTER_MAINT;
		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		} catch (Exception e) {
		}
		int socketNumber = 3;
		if ((screen > 51 && screen < 54) || (screen > 60 && screen < 64)) {
			socketNumber = 1;
		}

		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				flexLog("Opening Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + socketNumber);
				s.setSoTimeout(super.sckTimeOut);
				mc = new MessageContext(
					new DataInputStream(new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
			switch (screen) {
				// Request
				case R_NEW :
					procReqNew(mc, msgUser, req, res, session);
					break;
				case R_MAINTENANCE :
					procReqMaintenance(mc, msgUser, req, res, session);
					break;
				case R_BENEFICIARY :
					procReqBene(mc, msgUser, req, res, session);
					break;
				case R_SPECIAL_INST :
					procReqEspInst(mc, msgUser, req, res, session);
					break;
				case R_TITULARES :
					procReqTit(mc, msgUser, req, res, session);
					break;
				case R_INQUIRY :
					procReqInquiry(mc, msgUser, req, res, session);
					break;
				case R_EXCHANGE :
					procReqExchangeRate(mc, msgUser, req, res, session);
					break;
				case R_CODES :
					procReqSpecialCodes(mc, msgUser, req, res, session);
					break;
				case R_PRECANCEL :
					procReqCancelation(mc, msgUser, req, res, session);
					break;
				case R_TRANSACTION :
					procReqTransaction(mc, msgUser, req, res, session);
					break;
				case R_RENOV_MANT :
					procReqRenovations(mc, msgUser, req, res, session);
					break;
				case R_FINISH :
					procReqFinish(mc, msgUser, req, res, session);
					break;
				case R_INT_PREP :
					procReqIntPrep(mc, msgUser, req, res, session);
					break;
				case R_OTHERS_TRANS :
					procReqOthersTransaction(mc, msgUser, req, res, session);
					break;
				case R_DISBURSEMENT :
					procReqDisbursement(mc, msgUser, req, res, session);
					break;
				case R_MONEY :
					procReqCDMoney(mc, msgUser, req, res, session);
					break;
				case R_CD_SIGNERS :
					procReqSigners(mc, msgUser, req, res, session);
					break;
				case R_PAYMENT_PLAN :
					procReqMaintPay(mc, msgUser, req, res, session);
					break;

					//Reports	
				case R_BASIC_CONTR :
					procReqContr(mc, msgUser, req, res, session);
					break;

					//Inquiry Options
				case R_CODES_INQ :
					procReqSpecialCodesInq(mc, msgUser, req, res, session);
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
					
					//Swift
				case R_SC_CDS : 
					procReqSwift320CDS(mc, msgUser, req, res, session);
					break;
				case R_S_CDS : 
					procReqSwift100CDS(mc, msgUser, req, res, session);
					break;										
				case A_S_CDS : 
					procActionSwift100CDS(mc, msgUser, req, res, session);
					break;														

					// Action
				case A_NEW :
					procActionNew(mc, msgUser, req, res, session);
					break;
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
				case A_BENEFICIARY :
					procActionBene(mc, msgUser, req, res, session);
					break;
				case A_SPECIAL_INST :
					procActionEspInst(mc, msgUser, req, res, session);
					break;
				case A_TITULARES :
					procActionTit(mc, msgUser, req, res, session);
					break;
				case A_EXCHANGE :
					procActionExchangeRate(mc, msgUser, req, res, session);
					break;
				case A_CODES :
					procActionSpecialCodes(mc, msgUser, req, res, session);
					break;
				case A_PRECANCEL :
					procActionCancelation(mc, msgUser, req, res, session);
					break;
				case A_TRANSACTION :
					procActionTransaction(mc, msgUser, req, res, session);
					break;
				case A_RENOV_MANT :
					procActionRenovations(mc, msgUser, req, res, session);
					break;
				case A_FINISH :
					procActionFinish(mc, msgUser, req, res, session);
					break;
				case A_INT_PREP :
					procActionIntPrep(mc, msgUser, req, res, session);
					break;
				case A_PRINT_FIRST :
					procActionPrintNew(mc, msgUser, req, res, session);
					break;
				case A_DISBURSEMENT :
					procActionDisbursement(mc, msgUser, req, res, session);
					break;
				case A_MONEY :
					procActionCDMoney(mc, msgUser, req, res, session);
					break;
				case A_CD_SIGNERS :
					procActionSigners(mc, msgUser, req, res, session);
					break;
				case A_PAYMENT_PLAN :
					procActionMaintPay(mc, msgUser, req, res, session);
					break;

					// END CD

					// BEGIN Entering
					// Request
				case R_ENTER_NEW :
					procReqEnterNew(msgUser, req, res, session);
					break;
				case R_ENTER_MAINT :
					procReqEnterMaint(msgUser, req, res, session);
					break;
				case R_ENTER_CANCEL :
					procReqEnterCancel(msgUser, req, res, session);
					break;
				case R_ENTER_TRANSAC :
					procReqEnterTransac(msgUser, req, res, session);
					break;
				case R_ENTER_INQUIRY :
					procReqEnterInquiry(msgUser, req, res, session);
					break;
				case R_ENTER_PRINT :
					procReqEnterPrint(msgUser, req, res, session);
					break;
				case R_ENTER_MAINT_T :
					procReqEnterMaintT(msgUser, req, res, session);
					break;
					
					// Action 
				case A_ENTER_NEW :
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT :
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				case A_ENTER_CANCEL :
					procActionEnterCancel(mc, msgUser, req, res, session);
					break;
				case A_ENTER_TRANSAC :
					procActionEnterTransac(mc, msgUser, req, res, session);
					break;
				case A_ENTER_INQUIRY :
					procActionEnterInquiry(mc, msgUser, req, res, session);
					break;
				case A_ENTER_PRINT :
					procActionEnterPrint(mc, msgUser, req, res, session);
					break;

					// END Entering

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + socketNumber;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			} 

		} catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		}	
}

	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionBene(
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
			msgError = new ELEERRMessage();
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
			msgBene.setH04PGM("EDL0130");
			msgBene.setH04TIM(getTimeStamp());
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
					msgBene = new ESD000004Message();
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
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_bene.jsp");
						callPage(LangPath + "EDL0130M_cd_bene.jsp", req, res);
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
	protected void procActionCancelation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013007Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String opeCode = "";
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			opeCode = (req.getParameter("opt").equals("1")) ? "0005" : "0011";
		} catch (Exception ex) {
			opeCode = "0005";
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (EDL013007Message) ses.getAttribute("cdCancel");
			// msgCD = (EDL013007Message)mc.getMessageRecord("EDL013007");
			msgCD.setH07USERID(user.getH01USR());
			msgCD.setH07PROGRM("EDL0130");
			msgCD.setH07TIMSYS(getTimeStamp());
			msgCD.setH07SCRCOD("01");
			msgCD.setH07OPECOD(opeCode);

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

			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013007 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013007")) {
				try {
					msgCD = new EDL013007Message();
					flexLog("EDL013007 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013007Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE07DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCancel", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						if (opeCode.equals("0005")) {
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_cancel_confirm.jsp");
							callPage(LangPath + "EDL0130M_cd_cancel_confirm.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_cancel.jsp");
							callPage(LangPath + "EDL0130M_cd_cancel.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_cancel.jsp");
						callPage(LangPath + "EDL0130M_cd_cancel.jsp", req, res);
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
	protected void procActionEnterCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013007Message msgCD = null;
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
			flexLog("Sending header");
			flexLog("header 1");
			//msgCD = (EDL013007Message)ses.getAttribute("cdCancel");
			msgCD = (EDL013007Message) mc.getMessageRecord("EDL013007");
			msgCD.setH07USERID(user.getH01USR());
			msgCD.setH07PROGRM("EDL0130");
			msgCD.setH07TIMSYS(getTimeStamp());
			msgCD.setH07SCRCOD("01");
			msgCD.setH07OPECOD("0002");
			try {
				msgCD.setE07DEAACC(req.getParameter("E07DEAACC"));
			} catch (Exception e) {
				msgCD.setE07DEAACC("0");
			}

			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013007 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013007")) {
				try {
					msgCD = new EDL013007Message();
					flexLog("EDL013007 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013007Message) newmessage;

				userPO.setIdentifier(msgCD.getE07DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdCancel", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_cancel.jsp");
						callPage(LangPath + "EDL0130M_cd_cancel.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_cancel.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_cancel.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
				msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
				msgCD.setE01DEAACC("0");
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

			if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setBank(msgCD.getE01DEABNK());
				userPO.setBranch(msgCD.getE01DEABRN());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(msgCD.getE01DEACUN());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setCurrency(msgCD.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdInquiry", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_enter_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
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
			}
			try {
				msgCD.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}

			msgCD.send();
			msgCD.destroy();
		//	flexLog("EDL013001 Message Sent");
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
				showEDL013001(msgCD);

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(msgCD.getE01DEACUN());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setCurrency(msgCD.getE01DEACCY());
				userPO.setHeader16(msgCD.getE01DEAPVI());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors 
				      res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0326BO?SCREEN=1");
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
	protected void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;

		try {
			userPO = (UserPos) ses.getAttribute("userPO");
			userPO.setIdentifier(req.getParameter("E01DEAACC"));
			userPO.setProdCode(req.getParameter("E01DEAPRO"));
			ses.setAttribute("userPO", userPO);
			procReqNew(mc, user, req, res, ses);
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
			msgError = new ELEERRMessage();
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
					msgCD = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EFT000010Message) newmessage;

				userPO.setIdentifier(msgCD.getE10DEAACC());
				userPO.setHeader1(msgCD.getE10DEAPRO());
				userPO.setHeader2(msgCD.getE10DEACUN());
				userPO.setHeader3(msgCD.getE10CUSNA1());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdFinish", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_finish.jsp");
						callPage(LangPath + "EDL0130M_cd_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_print.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_print.jsp", req, res);
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
	protected void procActionEnterTransac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013003Message msgCD = null;
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
			//msgCD = (EDL013003Message)ses.getAttribute("cdTransac");
			msgCD = (EDL013003Message) mc.getMessageRecord("EDL013003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("EDL0130");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD("0002");
			try {
				msgCD.setE03DEAACC(req.getParameter("E03DEAACC"));
			} catch (Exception e) {
				msgCD.setE03DEAACC("0");
			}

			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013003 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013003")) {
				try {
					msgCD = new EDL013003Message();
					flexLog("EDL013003 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013003Message) newmessage;

				userPO.setIdentifier(msgCD.getE03DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdTransac", msgCD);

				if (IsNotError) { // There are no errors
					procReqTransaction(mc, user, req, res, ses);
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_transac.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_transac.jsp", req, res);
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
	protected void procActionEspInst(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgCD = (ESD000005Message) ses.getAttribute("cdInst");
			//msgCD = (ESD000005Message)mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE("0005");
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
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
					msgCD = new ESD000005Message();
					flexLog("ESD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE05ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
							callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_special_inst.jsp");
						callPage(LangPath + "EDL0130M_cd_special_inst.jsp", req, res);
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
	protected void procActionExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013006Message msgCD = null;
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
			msgCD = (EDL013006Message) ses.getAttribute("cdRate");
			//msgCD = (EDL013006Message)mc.getMessageRecord("EDL013006");
			msgCD.setH06USERID(user.getH01USR());
			msgCD.setH06PROGRM("EDL0130");
			msgCD.setH06TIMSYS(getTimeStamp());
			msgCD.setH06SCRCOD("01");
			msgCD.setH06OPECOD("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013006 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013006")) {
				try {
					msgCD = new EDL013006Message();
					flexLog("EDL013006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013006Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE06DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRate", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
							callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_xchg_rate.jsp");
						callPage(LangPath + "EDL0130M_cd_xchg_rate.jsp", req, res);
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
	protected void procActionFinish(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgCD = (EFT000010Message) ses.getAttribute("cdFinish");
			//msgCD = (EFT000010Message)mc.getMessageRecord("EFT000010");
			msgCD.setH10USERID(user.getH01USR());
			msgCD.setH10PROGRM("EDL0130");
			msgCD.setH10TIMSYS(getTimeStamp());
			msgCD.setH10SCRCOD("01");
			msgCD.setH10OPECOD("0002");
			try {
				msgCD.setE10DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				flexLog("E01CUMACC");
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
			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCD = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgCD = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE10DEAACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCD);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					try {

						if (msgCD.getE10DEAICT().equals("P")
							|| msgCD.getE10DEAICT().equals("A")
							|| msgCD.getE10DEAICT().equals("3")
							|| msgCD.getE10DEAICT().equals("4")) {
							procReqIntPrep(mc, user, req, res, ses);
						} else {
							if (userPO.getPurpose().equals("MAINTENANCE")) {
								flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
								callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
							} else if (userPO.getPurpose().equals("PRINT")) {
								flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_print.jsp");
								callPage(LangPath + "EDL0130M_cd_enter_print.jsp", req, res);
							} else {
								flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_new.jsp");
								//callPage(LangPath + "EDL0130M_cd_enter_new.jsp", req, res);
								res.sendRedirect(super.srctx + "/pages/background.jsp");
							}

						}

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_finish.jsp");
						callPage(LangPath + "EDL0130M_cd_finish.jsp", req, res);
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
	protected void procActionIntPrep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013009Message msgCD = null;
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
			msgCD = (EDL013009Message) ses.getAttribute("cdIntPrep");
			//msgCD = (EDL013009Message)mc.getMessageRecord("EDL013009");
			msgCD.setH09USERID(user.getH01USR());
			msgCD.setH09PROGRM("EDL0130");
			msgCD.setH09TIMSYS(getTimeStamp());
			msgCD.setH09SCRCOD("01");
			msgCD.setH09OPECOD("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013009 Message Sent");
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
					msgCD = new EDL013009Message();
					flexLog("EDL013009 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013009Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE09DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdIntPrep", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							if (userPO.getPurpose().equals("MAINTENANCE")) {
								flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
								callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
							} else {
								flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_new.jsp");
								callPage(LangPath + "EDL0130M_cd_enter_new.jsp", req, res);
							}

						}

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_prep_int.jsp");
						callPage(LangPath + "EDL0130M_cd_prep_int.jsp", req, res);
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
	protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
		EFT000010Message msgCDNew = null;
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

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCDNew = new EFT000010Message();
					flexLog("EFT000010 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCDNew = (EFT000010Message) newmessage;
				userPO.setIdentifier(msgCDNew.getE10DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCDNew);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page1: " + LangPath + "EDL0130M_cd_confirm.jsp");
					callPage(LangPath + "EDL0130M_cd_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL013001")) {
				try {
					msgCD = new EDL013001Message();
					flexLog("EDL001301 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				userPO.setIdentifier(msgCD.getE01DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMant", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0130M_cd_confirm_pas.jsp");
						callPage(LangPath + "EDL0130M_cd_confirm_pas.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
		EFT000010Message msgCDNew = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String action = "F";
		try {
			action = req.getParameter("ACTION");
		} catch (Exception e) {
			flexLog("ACTION");
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			// msgCD = (EDL013001Message)mc.getMessageRecord("EDL013001");
			msgCD = (EDL013001Message) ses.getAttribute("cdNew");
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
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgCD.send();
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

			if (IsNotError) {
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
					userPO.setCurrency(msgCDNew.getE10DEACCY());
					userPO.setHeader3(msgCDNew.getE10CUSNA1());
					userPO.setHeader2(msgCDNew.getE10DEACUN());
					userPO.setHeader1(msgCDNew.getE10DEAPRO());

					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);

					try {
						if (action.equals("R")) {
							procReqRenovations(mc, user, req, res, ses);
						} else {
							ses.setAttribute("error", msgError);
							ses.setAttribute("cdFinish", msgCDNew);
							flexLog("About to call Page1: " + LangPath + "EDL0130M_cd_confirm.jsp");
							callPage(LangPath + "EDL0130M_cd_confirm.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			} else {
				if (newmessage.getFormatName().equals("EDL013001")) {
					try {
						msgCD = new EDL013001Message();
						flexLog("EDL013001 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCD = (EDL013001Message) newmessage;
					// showESD008004(msgCD);

					userPO.setIdentifier(msgCD.getE01DEAACC());
					userPO.setCurrency(msgCD.getE01DEACCY());
					userPO.setHeader3(msgCD.getE01CUSNA1());
					userPO.setHeader2(msgCD.getE01DEACUN());
					userPO.setHeader1(msgCD.getE01DEAPRO());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdNew", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page2: " + LangPath + "EDL0130M_cd_opening.jsp");
						callPage(LangPath + "EDL0130M_cd_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
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
	protected void procActionPrintNew(
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
				msgCD.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgCD.setE01DEAACC("0");
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

			if (newmessage.getFormatName().equals("EDL013001")) {
				try {
					msgCD = new EDL013001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				showEDL013001(msgCD);

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(msgCD.getE01DEACUN());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setCurrency(msgCD.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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
	protected void procActionRenovations(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013008Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		flexLog("Por aqui");
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (EDL013008Message) ses.getAttribute("cdRenov");
			msgCD = (EDL013008Message) mc.getMessageRecord("EDL013008");
			msgCD.setH08USERID(user.getH01USR());
			msgCD.setH08PROGRM("EDL0130");
			msgCD.setH08TIMSYS(getTimeStamp());
			msgCD.setH08SCRCOD("01");
			msgCD.setH08OPECOD("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013008 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013008")) {
				try {
					msgCD = new EDL013008Message();
					flexLog("EDL013008 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013008Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE08DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0130M_cd_renov_options.jsp");
						callPage(LangPath + "EDL0130M_cd_renov_options.jsp", req, res);
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
	protected void procActionRenovationsOp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013008Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		flexLog("Por aqui");
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (EDL013008Message) ses.getAttribute("cdRenov");
			//msgCD = (EDL013008Message)mc.getMessageRecord("EDL013008");
			msgCD.setH08USERID(user.getH01USR());
			msgCD.setH08PROGRM("EDL0130");
			msgCD.setH08TIMSYS(getTimeStamp());
			msgCD.setH08SCRCOD("01");
			msgCD.setH08OPECOD("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013008 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013008")) {
				try {
					msgCD = new EDL013008Message();
					flexLog("EDL013008 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013008Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE08DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0130M_cd_renov_options_op.jsp");
						callPage(LangPath + "EDL0130M_cd_renov_options_op.jsp", req, res);
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
	protected void procActionSpecialCodes(
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
			msgCD = (ESD000002Message) ses.getAttribute("cdCodes");
			//msgCD = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EDL0130");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02SCR("01");
			msgCD.setH02OPE("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
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
					msgCD = new ESD000002Message();
					flexLog("ESD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE02ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
							callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_codes.jsp");
						callPage(LangPath + "EDL0130M_cd_codes.jsp", req, res);
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
		ESD000006Message msgCD = null;
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
			//msgCD = (ESD000006Message)ses.getAttribute("cdTit");
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EDL0130");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06SCR("01");
			msgCD.setH06OPE("0005");
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("ESD000006 Message Sent");
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
					msgCD = new ESD000006Message();
					flexLog("ESD000006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE06ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
							callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_tit.jsp");
						callPage(LangPath + "EDL0130M_cd_tit.jsp", req, res);
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
	protected void procActionTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013003Message msgCD = null;
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
			msgCD = (EDL013003Message) ses.getAttribute("cdTransac");
			//msgCD = (EDL013003Message)mc.getMessageRecord("EDL013003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("EDL0130");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD("0005");

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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("EDL013003 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013003")) {
				try {
					msgCD = new EDL013003Message();
					flexLog("EDL013003 Message Sent");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013003Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE03DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTransac", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog(
								"About to call Page: " + LangPath + "EDL0130M_cd_transaction_confirm.jsp");
							callPage(LangPath + "EDL0130M_cd_transaction_confirm.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_transac.jsp");
						callPage(LangPath + "EDL0130M_cd_transac.jsp", req, res);
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
	protected void procReqBene(
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
			msgError = new ELEERRMessage();
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
			msgBene.setH04PGM("EDL0130");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("J");
			msgBene.send();
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
					msgBene = new ESD000004Message();
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
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_bene.jsp");
						callPage(LangPath + "EDL0130M_cd_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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
	protected void procReqCancelation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013007Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013007Message) mc.getMessageRecord("EDL013007");
			msgCD.setH07USERID(user.getH01USR());
			msgCD.setH07PROGRM("EDL0130");
			msgCD.setH07TIMSYS(getTimeStamp());
			msgCD.setH07SCRCOD("01");
			msgCD.setH07OPECOD(opCode);
			msgCD.setE07DEAACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
			flexLog("EDL013007 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL013007")) {
				try {
					msgCD = new EDL013007Message();
					flexLog("EDL013007 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013007Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCancel", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_cancel.jsp");
						callPage(LangPath + "EDL0130M_cd_cancel.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procReqContr(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try {
			msgCD = (EFT000010Message) mc.getMessageRecord("EFT000010");
			msgCD.setH10USERID(user.getH01USR());
			msgCD.setH10PROGRM("EDL0130");
			msgCD.setH10TIMSYS(getTimeStamp());
			msgCD.setH10SCRCOD("01");
			msgCD.setH10OPECOD(opCode);
			msgCD.setE10DEAACC(userPO.getIdentifier());
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
					msgCD = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("report", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130_rp_contract.jsp");
						callPage(LangPath + "EDL0130_rp_contract.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_finish.jsp");
						callPage(LangPath + "EDL0130M_cd_finish.jsp", req, res);
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
	protected void procReqEnterCancel(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_cancel.jsp");
			callPage(LangPath + "EDL0130M_cd_enter_cancel.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0160_cd_enter_inquiry.jsp");
			callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EDL013001Message msgCD = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgCD = new EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("cdMant", msgCD);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
			callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqEnterMaintT(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EDL013001Message msgCD = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgCD = new EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("cdMant", msgCD);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130B_cd_enter_maint.jsp");
			callPage(LangPath + "EDL0130B_cd_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterNew(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_new.jsp");
			callPage(LangPath + "EDL0130M_cd_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("PRINT");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_print.jsp");
			callPage(LangPath + "EDL0130M_cd_enter_print.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterTransac(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("CD_TRANSACTION");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_transac.jsp");
			callPage(LangPath + "EDL0130M_cd_enter_transac.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEspInst(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

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
					msgCD = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_special_inst.jsp");
						callPage(LangPath + "EDL0130M_cd_special_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
					msgCD = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_inq_special_inst.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_special_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
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
	protected void procReqExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013006Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013006Message) mc.getMessageRecord("EDL013006");
			msgCD.setH06USERID(user.getH01USR());
			msgCD.setH06PROGRM("EDL0130");
			msgCD.setH06TIMSYS(getTimeStamp());
			msgCD.setH06SCRCOD("01");
			msgCD.setH06OPECOD(opCode);
			msgCD.setE06DEAACC(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDL013006")) {
				try {
					msgCD = new EDL013006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRate", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_xchg_rate.jsp");
						callPage(LangPath + "EDL0130M_cd_xchg_rate.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procReqFinish(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EFT000010Message) mc.getMessageRecord("EFT000010");
			msgCD.setH10USERID(user.getH01USR());
			msgCD.setH10PROGRM("EDL0130");
			msgCD.setH10TIMSYS(getTimeStamp());
			msgCD.setH10SCRCOD("01");
			msgCD.setH10OPECOD(opCode);
			msgCD.setE10DEAACC(userPO.getIdentifier());
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
					msgCD = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_finish.jsp");
						callPage(LangPath + "EDL0130M_cd_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_opening.jsp");
						callPage(LangPath + "EDL0130M_cd_opening.jsp", req, res);
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
			msgError = new ELEERRMessage();
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

			if (newmessage.getFormatName().equals("EDL016002")) {
				try {
					msgCD = new EDL016002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_inq_basic.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
			msgBene.setH04PGM("EDL0130");
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
					msgBene = new ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_inq_bene.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
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
			msgError = new ELEERRMessage();
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

			if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInquiry", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0160_cd_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0160_cd_enter_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
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
	protected void procReqIntPrep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL013009Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013009Message) mc.getMessageRecord("EDL013009");
			msgCD.setH09USERID(user.getH01USR());
			msgCD.setH09PROGRM("EDL0130");
			msgCD.setH09TIMSYS(getTimeStamp());
			msgCD.setH09SCRCOD("01");
			msgCD.setH09OPECOD(opCode);
			msgCD.setE09DEAACC(userPO.getIdentifier());
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
			if (newmessage.getFormatName().equals("EDL013009")) {
				try {
					msgCD = new EDL013009Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgCD = (EDL013009Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdIntPrep", msgCD);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_prep_int.jsp");
						callPage(LangPath + "EDL0130M_cd_prep_int.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_finish.jsp");
						callPage(LangPath + "EDL0130M_cd_finish.jsp", req, res);
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
	protected void procReqMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013001Message) mc.getMessageRecord("EDL013001");
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
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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
	protected void procReqNew(
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

		String opCode = null;
		opCode = "0001";

		// Send Initial data
		try {
			msgCD = (EDL013001Message) mc.getMessageRecord("EDL013001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setE01DEAPRO(userPO.getProdCode());
			try {
				msgCD.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
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

			if (newmessage.getFormatName().equals("EDL013001")) {
				try {
					msgCD =new EDL013001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdNew", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_opening.jsp");
						callPage(LangPath + "EDL0130M_cd_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_enter_new.jsp");
						//callPage(LangPath + "EDL0130M_cd_enter_new.jsp", req, res);	
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
			msgError = new ELEERRMessage();
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

			if (newmessage.getFormatName().equals("ESD071111")) {
				try {
					msgProdCD = new ESD071111Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgProdCD = (ESD071111Message) newmessage;
				// showESD071111(msgProdCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdProdInq", msgProdCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_inq_products.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_products.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						//
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
	protected void procReqRenovations(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013008Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013008Message) mc.getMessageRecord("EDL013008");
			msgCD.setH08USERID(user.getH01USR());
			msgCD.setH08PROGRM("EDL0130");
			msgCD.setH08TIMSYS(getTimeStamp());
			msgCD.setH08SCRCOD("01");
			msgCD.setH08OPECOD(opCode);
			msgCD.setE08DEAACC(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDL013008")) {
				try {
					msgCD = new EDL013008Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013008Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_renov_options.jsp");
						callPage(LangPath + "EDL0130M_cd_renov_options.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procReqSpecialCodes(
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
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
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
					msgCD = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);

				if (IsNotError) { // There are no errors
					try {

						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_codes.jsp");
						callPage(LangPath + "EDL0130M_cd_codes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_codes.jsp");
						callPage(LangPath + "EDL0130M_cd_codes.jsp", req, res);
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
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
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
					msgCD = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_inq_codes.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_codes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
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
	protected void procReqTit(
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
			msgError = new ELEERRMessage();
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
					msgCD = new ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_tit.jsp");
						callPage(LangPath + "EDL0130M_cd_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
					msgCD = new ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_inq_tit.jsp");
						callPage(LangPath + "EDL0130M_cd_inq_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0160_cd_inquiry.jsp");
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
	protected void procReqTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL013003Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (EDL013003Message) mc.getMessageRecord("EDL013003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("EDL0130");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD(opCode);
			msgCD.setE03DEAACC(userPO.getIdentifier());
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
			if (newmessage.getFormatName().equals("EDL013003")) {
				try {
					msgCD = new EDL013003Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgCD = (EDL013003Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTransac", msgCD);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_transac.jsp");
						callPage(LangPath + "EDL0130M_cd_transac.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	
	protected void procReqSwift100CDS(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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


	  opCode = "0001";
	
	// Send Initial data
	try
	{
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
		try {
		 	  msgSwift.setE01SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE01SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFBNK("0");
		}

		try {
		 	  msgSwift.setE01SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
		}

		try {
		 	  msgSwift.setE01SWFAMT(userPO.getHeader1());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
		}

	 	msgSwift.send();
	 	msgSwift.destroy();
	 	flexLog("ESWF10001 Message Sent");
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


		if (newmessage.getFormatName().equals("ESWF10001")) {
			try {
				msgSwift = new ESWF10001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10001Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF100_fe_cdm_format.jsp");
						callPage(LangPath + "ESWF100_fe_cdm_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EDL0130M_fe_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_fe_cd_maint.jsp", req, res);
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

protected void procActionSwift100CDS(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
	String opeCode = "";
	
    if(req.getParameter("CODOPT").equals("V")){
       opeCode="0006";	
    }
	else opeCode="0005";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgSwift = (ESWF10001Message)ses.getAttribute("swift");
		msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opeCode);
	 	msgSwift.setH01WK1("1");

	 	// all the fields here
	 	java.util.Enumeration enu = msgSwift.fieldEnumeration();
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


	 	//msgSwift.send();
	 	mc.sendMessage(msgSwift);
	 	msgSwift.destroy();
	 	flexLog("ESWF10001 Message Sent");
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


				try {
					msgSwift = new ESWF10001Message();
					flexLog("ESWF10001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgSwift = (ESWF10001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
						try {
							    flexLog("About to call Page: " + LangPath + "EDL0130M_fe_cd_maint.jsp");
								callPage(LangPath + "EDL0130M_fe_cd_maint.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100_fe_cdm_format.jsp");
							callPage(LangPath + "ESWF100_fe_cdm_format.jsp", req, res);	
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

protected void procReqSwift320CDS(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10002Message msgSwift = null;
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


	  opCode = "0001";
	
	// Send Initial data
	try
	{
		msgSwift = (ESWF10002Message)mc.getMessageRecord("ESWF10002");
	 	msgSwift.setH02USR(user.getH01USR());
	 	msgSwift.setH02PGM("ESWF10002");
	 	msgSwift.setH02TIM(getTimeStamp());
	 	msgSwift.setH02SCR("01");
	 	msgSwift.setH02OPE(opCode);
	 			
		try {
		 	  msgSwift.setE02SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE02SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE02SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE02SWFBNK("0");
		}

		try {
		 	  msgSwift.setE02SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE02SWFCUN("0");
		}

	 	msgSwift.send();
	 	msgSwift.destroy();
	 	flexLog("ESWF10002 Message Sent");
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


		if (newmessage.getFormatName().equals("ESWF10002")) {
			try {
				msgSwift = new ESWF10002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10002Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF320_fe_cdm_format.jsp");
						callPage(LangPath + "ESWF320_fe_cdm_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EDL0130M_fe_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_fe_cd_maint.jsp", req, res);
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



	protected void showEDL013001(EDL013001Message m) {
		if (logType != NONE) {

			flexLog("Client Information received (EDL013001).");

			flexLog("User ID: " + m.getH01USERID());
			flexLog("Program: " + m.getH01PROGRM());
			flexLog("Time date: " + m.getH01TIMSYS());
			flexLog("Screen code: " + m.getH01SCRCOD());
			flexLog("Operate code: " + m.getH01OPECOD());
			flexLog("More records" + m.getH01FLGMAS());
			flexLog("Flag 1: " + m.getH01FLGWK1());
			flexLog("Flag 2: " + m.getH01FLGWK2());
			flexLog("Flag 3: " + m.getH01FLGWK3());

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
	protected void procActionCDMoney(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgLaunder = (ELD000001Message) ses.getAttribute("cdMoney");
			//msgLaunder = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgLaunder.setH06USERID(user.getH01USR());
			msgLaunder.setH06PROGRM("ELD0000");
			msgLaunder.setH06TIMSYS(getTimeStamp());
			msgLaunder.setH06SCRCOD("01");
			msgLaunder.setH06OPECOD("0005");
			msgLaunder.setH06FLGWK1("1");

			// all the fields here
			java.util.Enumeration enu = msgLaunder.fieldEnumeration();
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

			//msgLaunder.send();
			mc.sendMessage(msgLaunder);
			msgLaunder.destroy();
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
					msgLaunder = new ELD000001Message();
					flexLog("ELD000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLaunder = (ELD000001Message) newmessage;
				// showESD008004(msgLaunder);

				userPO.setIdentifier(msgLaunder.getE06LDMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMoney", msgLaunder);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
							callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_money.jsp");
						callPage(LangPath + "EDL0130M_cd_money.jsp", req, res);
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
	protected void procActionDisbursement(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgCD = (ESD000008Message) ses.getAttribute("cdDisb");
			msgCD.setH08USR(user.getH01USR());
			msgCD.setH08PGM("PAYMTINST");
			msgCD.setH08TIM(getTimeStamp());
			msgCD.setH08SCR("01");
			msgCD.setH08OPE("0005");
			msgCD.setE08ACC(userPO.getIdentifier());

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

			//msgBene.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("ESD000008 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000008")) {
				try {
					msgCD = new ESD000008Message();
					flexLog("ESD000008 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000008Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdDisb", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_disb_inst.jsp");
						callPage(LangPath + "EDL0130M_cd_disb_inst.jsp", req, res);
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
	protected void procReqCDMoney(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLaunder = (ELD000001Message) mc.getMessageRecord("ELD000001");
			msgLaunder.setH06USERID(user.getH01USR());
			msgLaunder.setH06PROGRM("EDL0130");
			msgLaunder.setH06TIMSYS(getTimeStamp());
			msgLaunder.setH06SCRCOD("01");
			msgLaunder.setH06OPECOD("0002");
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
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELD000001")) {
				try {
					msgLaunder = new ELD000001Message();
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
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_money.jsp");
						callPage(LangPath + "EDL0130M_cd_money.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procReqDisbursement(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0003";

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

			if (newmessage.getFormatName().equals("ESD000008")) {
				try {
					msgCD = new ESD000008Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000008Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdDisb", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0130M_cd_disb_inst.jsp");
						callPage(LangPath + "EDL0130M_cd_disb_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0130M_cd_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_maint.jsp", req, res);
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
	protected void procReqOthersTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;
		boolean IsNotError = false;
		DataTransaction transData = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			transData = new DataTransaction();

			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSigners(
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			//msgBene = (ESD000004Message)mc.getMessageRecord("ESD000004");
			msgBene = (ESD000004Message) ses.getAttribute("cdFirm");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDL0");
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
					msgBene = new ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFirm", msgBene);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
						callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_firm.jsp");
						callPage(LangPath + "EDL0130M_cd_firm.jsp", req, res);
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

	protected void procReqSigners(
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
			msgError = new ELEERRMessage();
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
					msgBene = new ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFirm", msgBene);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_firm.jsp");
						callPage(LangPath + "EDL0130M_cd_firm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_enter_maint.jsp");
						callPage(LangPath + "EDL0130M_cd_enter_maint.jsp", req, res);
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

	protected void procActionMaintPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec pmntList = null;
		int colNum = 6;

		try {
			pmntList = new JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String sel = " ";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			myRow[i] = "";
		}

		for (int row = 0; row < 360; row++) {
			try {
				sel = req.getParameter("DLPPNU_" + row).toUpperCase();
				if (sel.equals(""))
					break;
			} catch (Exception e) {
				break;
			}

			myRow[0] = req.getParameter("DLPPNU_" + row);
			myRow[1] = req.getParameter("DLPPD1_" + row);
			myRow[2] = req.getParameter("DLPPD2_" + row);
			myRow[3] = req.getParameter("DLPPD3_" + row);
			myRow[4] = req.getParameter("DLPPRI_" + row);
			myRow[5] = req.getParameter("DLPINT_" + row);

			pmntList.addRow(myFlag, myRow);

		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("pmnt", pmntList);

		int opt;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			opt = 1;
		}

		switch (opt) {
			case 1 :
				{
					///////////////////
					//					res.setContentType("text/html");
					//					PrintWriter out = res.getWriter();
					//					printClose(out);
					//////////////////////

					MessageRecord newmessage = null;
					EDL013004Message msgPmnt = null;
					ELEERRMessage msgError = null;
					UserPos userPO = null;
					boolean IsNotError = false;

					//Sending EDL013004
					try {
						userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

						msgPmnt = (EDL013004Message) mc.getMessageRecord("EDL013004");

						pmntList.initRow();
						while (pmntList.getNextRow()) {
							msgPmnt.setH04USERID(user.getH01USR());
							msgPmnt.setH04PROGRM("EDL0130");
							msgPmnt.setH04TIMSYS(getTimeStamp());
							msgPmnt.setH04SCRCOD("01");
							msgPmnt.setH04OPECOD("0005");

							try {
								msgPmnt.setE04DEAACC(userPO.getIdentifier());
							} catch (Exception e) {
								flexLog("E04DEAACC");
							}

							try {
								msgPmnt.setE04DLPPNU(pmntList.getRecord(0)); //Quota
							} catch (Exception e) {
								flexLog("E04DLPPNU");
							}
							try {
								msgPmnt.setE04DLPPD1(pmntList.getRecord(1)); //Date
							} catch (Exception e) {
								flexLog("E04DLPPD1");
							}

							try {
								msgPmnt.setE04DLPPD2(pmntList.getRecord(2)); //Date
							} catch (Exception e) {
								flexLog("E04DLPPD1");
							}

							try {
								msgPmnt.setE04DLPPD3(pmntList.getRecord(3)); //Date
							} catch (Exception e) {
								flexLog("E04DLPPD1");
							}

							try {
								msgPmnt.setE04DLPPRI(pmntList.getRecord(4)); //Principal
							} catch (Exception e) {
								flexLog("E04DLPPRI");
							}
							try {
								msgPmnt.setE04DLPINT(pmntList.getRecord(5)); //Interest
							} catch (Exception e) {
								flexLog("E04DLPINT");
							}

							msgPmnt.send();
						}

						msgPmnt.setH04FLGMAS("*");
						msgPmnt.send();
						msgPmnt.destroy();
						flexLog("EDL013004 Message Sent");

						// procReqMaintPay(mc, user, req, res, ses);
						flexLog("Initializing java beans into the session");
						try {
							msgError = new ELEERRMessage();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}
						try {
							// int colNum = 6;
							pmntList = new JBListRec();
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

							if (newmessage.getFormatName().equals("EDL013004")) {
								//Deductions List
								// Fill List bean

								// int colNum = 6;

								// char sel = ' ';
								String marker = "";
								// String myFlag = "";
								// String myRow[] = new String[colNum];
								// for (int i = 0; i < colNum; i++) {
								// 	myRow[i] = "";
								// }

								while (true) {

									msgPmnt = (EDL013004Message) newmessage;

									marker = msgPmnt.getH04FLGMAS();

									if (marker.equals("*")) {
										break;
									} else {
										//Quote List
										myRow[0] = msgPmnt.getE04DLPPNU(); // Quote Number
										myRow[1] = msgPmnt.getE04DLPPD1();
										myRow[2] = msgPmnt.getE04DLPPD2();
										myRow[3] = msgPmnt.getE04DLPPD3(); // Date
										myRow[4] = msgPmnt.getE04DLPPRI(); // Principal
										myRow[5] = msgPmnt.getE04DLPINT(); // Interest

										pmntList.addRow(myFlag, myRow);

									}

									newmessage = mc.receiveMessage();

								}

								ses.setAttribute("error", msgError);
								ses.setAttribute("pmnt", pmntList);

								if (IsNotError) { // There are no errors
									try {
										res.setContentType("text/html");
										PrintWriter out = res.getWriter();
										out.println("<HTML>");
										out.println("<HEAD>");
										out.println("<TITLE>Close</TITLE>");
										out.println("</HEAD>");
										out.println("<BODY>");
										out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
										out.println("		top.opener.document.forms[0].submit();");
										out.println("		top.close();");
										out.println("</SCRIPT>");
										out.println("<P>Close it!!!</P>");
										out.println("</BODY>");
										out.println("</HTML>");
										out.close();
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
								} else { // There are errors
									try {
										flexLog("About to call Page: " + LangPath + "EDL0130M_cd_pay_pln_det.jsp");
										callPage(LangPath + "EDL0130M_cd_pay_pln_det.jsp", req, res);
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
						//

					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}

					break;
				}
			case 2 :
				{
					try {
						pmntList.setLastRow();
						int pymNumLast = 1;
						if (!pmntList.getNoResult()) {
							pymNumLast = Integer.parseInt(pmntList.getRecord(0)) + 1;
						}
						int number = Integer.parseInt(req.getParameter("NUMBER"));
						int month = Integer.parseInt(req.getParameter("DATE1"));
						int day = Integer.parseInt(req.getParameter("DATE2"));
						int year = 2000 + Integer.parseInt(req.getParameter("DATE3"));
						int feq = Integer.parseInt(req.getParameter("FREQUENCY"));
						char code = req.getParameter("CODE").toCharArray()[0];
						java.util.Calendar myDate = Calendar.getInstance();
						myDate.set(year, month - 1, day);

						for (int row = 0; row < number; row++) {

							// Payment Number
							myRow[0] = pymNumLast + row + "";
							// Dates
							if (row > 0) {
								switch (code) {
									case 'D' :
										{
											myDate.add(Calendar.DATE, feq);
											break;
										}
									case 'M' :
										{
											myDate.add(Calendar.MONTH, feq);
											break;
										}
									case 'Y' :
										{
											myDate.add(Calendar.YEAR, feq);
											break;
										}
								}
							}

							if (user.getE01DTF().equals("MDY")) {
								myRow[1] = (myDate.get(Calendar.MONTH) + 1) + "";
								myRow[2] = myDate.get(Calendar.DAY_OF_MONTH) + "";
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[3] = y.substring(2);
							} else if (user.getE01DTF().equals("DMY")) {
								myRow[1] = myDate.get(Calendar.DAY_OF_MONTH) + "";
								myRow[2] = (myDate.get(Calendar.MONTH) + 1) + "";
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[3] = y.substring(2);
							} else {
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[1] = y.substring(2);
								myRow[2] = (myDate.get(Calendar.MONTH) + 1) + "";
								myRow[3] = myDate.get(Calendar.DAY_OF_MONTH) + "";
							}
							// Amount
							myRow[4] = req.getParameter("AMOUNT");
							// Interest
							myRow[5] = "";

							pmntList.addRow(myFlag, myRow);

						}

						flexLog("Putting java beans into the session");
						ses.setAttribute("pmnt", pmntList);
					} catch (Exception e) {
					}

					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_pay_pln_det.jsp");
						callPage(LangPath + "EDL0130M_cd_pay_pln_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
			case 3 :
				{
					String row = req.getParameter("ROW");
					try {
						flexLog(
							"About to call Page: " + LangPath + "EDL0130M_cd_pay_pln_det.jsp?ROW=" + row);
						res.sendRedirect(super.srctx + LangPath + "EDL0130M_cd_pay_pln_det.jsp?ROW=" + row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
		}

	}

	protected void procReqMaintPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013004Message msgPmnt = null;
		JBListRec pmntList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgPmnt = (EDL013004Message) mc.getMessageRecord("EDL013004");
			msgPmnt.setH04USERID(user.getH01USR());
			msgPmnt.setH04PROGRM("EDL0130");
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 6;
			pmntList = new JBListRec();
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

			if (newmessage.getFormatName().equals("EDL013004")) {
				//Deductions List
				// Fill List bean

				int colNum = 6;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgPmnt = (EDL013004Message) newmessage;

					marker = msgPmnt.getH04FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myRow[0] = msgPmnt.getE04DLPPNU(); // Quote Number
						myRow[1] = msgPmnt.getE04DLPPD1();
						myRow[2] = msgPmnt.getE04DLPPD2();
						myRow[3] = msgPmnt.getE04DLPPD3(); // Date
						myRow[4] = msgPmnt.getE04DLPPRI(); // Principal
						myRow[5] = msgPmnt.getE04DLPINT(); // Interest

						pmntList.addRow(myFlag, myRow);

					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("error", msgError);
				ses.setAttribute("pmnt", pmntList);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130M_cd_pay_pln_det.jsp");
						callPage(LangPath + "EDL0130M_cd_pay_pln_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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