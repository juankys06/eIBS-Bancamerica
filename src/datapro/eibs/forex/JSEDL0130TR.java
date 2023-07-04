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
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0130TR extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
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
	protected static final int R_MONEY = 52;
	protected static final int A_MONEY = 53;

	protected static final int R_CD_SIGNERS = 55;
	protected static final int A_CD_SIGNERS = 56;

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

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 200;
	protected static final int R_ENTER_INQUIRY = 300;
	protected static final int R_ENTER_CANCEL = 700;
	protected static final int R_ENTER_TRANSAC = 900;
	protected static final int R_ENTER_PRINT = 1100;
	protected static final int R_ENTER_MAINT_T = 1300;
	protected static final int R_ENTER_MAINT_ACC = 1500;
	protected static final int R_ENTER_CANCEL_ACC = 1700;

	protected static final int A_ENTER_NEW = 400;
	protected static final int A_ENTER_MAINT = 500;
	protected static final int A_ENTER_INQUIRY = 600;
	protected static final int A_ENTER_CANCEL = 800;
	protected static final int A_ENTER_TRANSAC = 1000;
	protected static final int A_ENTER_PRINT = 1200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0130TR() {
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
								"About to call Page: " + LangPath + "EDL0130TR_cd_transaction_confirm.jsp");
							callPage(LangPath + "EDL0130TR_cd_transaction_confirm.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130TR_cd_transac.jsp");
						callPage(LangPath + "EDL0130TR_cd_transac.jsp", req, res);
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
						flexLog("About to call Page: " + LangPath + "EDL0130TR_cd_enter_transac.jsp");
						callPage(LangPath + "EDL0130TR_cd_enter_transac.jsp", req, res);
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
			flexLog("About to call Page: " + LangPath + "EDL0130TR_cd_enter_transac.jsp");
			callPage(LangPath + "EDL0130TR_cd_enter_transac.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
					msgCD =new EDL013003Message();
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
						flexLog("About to call Page: " + LangPath + "EDL0130TR_cd_transac.jsp");
						callPage(LangPath + "EDL0130TR_cd_transac.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130TR_cd_enter_transac.jsp");
						callPage(LangPath + "EDL0130TR_cd_enter_transac.jsp", req, res);
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

			int screen = R_ENTER_MAINT;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
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
					// BEGIN CD
					// Request

					case R_TRANSACTION :
						procReqTransaction(mc, msgUser, req, res, session);
						break;

					// Action

					case A_TRANSACTION :
						procActionTransaction(mc, msgUser, req, res, session);
						break;

						// END CD

						// BEGIN Entering
						// Request

					case R_ENTER_TRANSAC :
						procReqEnterTransac(msgUser, req, res, session);
						break;
						
						// Action 

					case A_ENTER_TRANSAC :
						procActionEnterTransac(mc, msgUser, req, res, session);
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

}