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
import java.util.Calendar;

import datapro.eibs.sockets.*;

public class JSEGL0035I extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_TRANSACTION = 1;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEGL0035I() {
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
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		DataTransaction transData = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			userPO = new datapro.eibs.beans.UserPos();
			transData = new datapro.eibs.beans.DataTransaction();
			userPO.setOption("TRANSACTION");
			userPO.setPurpose("APPROVAL_INQ");

			transData.setWrkFile("2");
			transData.setBthnum(req.getParameter("BATCH"));
			transData.setBank(req.getParameter("BANK"));
			transData.setBranch(req.getParameter("BRANCH"));
			
			userPO.setAccOpt("");

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);
			ses.setAttribute("userPO", userPO);
		 

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=1");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		DataTransaction transData = null;
		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			transData = new datapro.eibs.beans.DataTransaction();
			userPO.setOption("TRANSACTION");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("transData", transData);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EGL0035_transaction_enter_inq.jsp");
			callPage(LangPath + "EGL0035_transaction_enter_inq.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqTr(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL003501Message msgTr = null;
		JBListRec trList = null;
		ELEERRMessage msgError = null;
		DataTransaction transData = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		transData =
			(datapro.eibs.beans.DataTransaction) ses.getAttribute("transData");

		// Send Initial data
		try {
			msgTr = (EGL003501Message) mc.getMessageRecord("EGL003501");
			msgTr.setH01USERID(user.getH01USR());
			msgTr.setH01PROGRM("EGL0035");
			msgTr.setH01TIMSYS(getTimeStamp());
			msgTr.setH01SCRCOD("01");
			msgTr.setH01OPECOD("0004");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// Get Parameters here	
		try {
			msgTr.setE01NUMACC(transData.getAccNum());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01NUMREF(transData.getRefNum());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01CKNUMB(transData.getChkNum());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01WRKFIL(transData.getWrkFile());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01BTHNUM(transData.getBthnum());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01WRKOBK(transData.getBank());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01WRKOBR(transData.getBranch());
		} catch (Exception e) {
		}
		try {
			msgTr.setE01WRKTYP(transData.getProdtype());
		} catch (Exception e) {
		}		

		try {
			msgTr.send();
			msgTr.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		int colNum = 50;

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			trList = new datapro.eibs.beans.JBListRec();
			trList.init(colNum);
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

			if (newmessage.getFormatName().equals("EGL003501")) {

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				boolean myFirstRow = false;

				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}
				transData.setDescription("");
				transData.setDate1("0");
				transData.setDate2("0");
				transData.setDate3("0");
				transData.setDebitAmt("0");
				transData.setCreditAmt("0");

				while (true) {

					msgTr = (EGL003501Message) newmessage;

					marker = msgTr.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {

						myRow[0] = msgTr.getE01WRKBNK(); // Bank
						myRow[1] = msgTr.getE01WRKBRN(); // Branch
						myRow[2] = msgTr.getE01WRKCCY(); // Currency
						myRow[3] = msgTr.getE01WRKGLN(); // General Ledger
						myRow[4] = msgTr.getE01WRKCCN(); // Cost Center
						myRow[5] = msgTr.getE01WRKACC(); // Account
						myRow[6] = msgTr.getE01WRKCDE(); // Transaction Code
						myRow[7] = msgTr.getE01WRKAMT(); // Amount
						myRow[8] = msgTr.getE01WRKDCC(); // Debit or Credit
						myRow[9] = msgTr.getE01WRKTDS().trim(); // Description
						myRow[10] = msgTr.getE01WRKVD1(); // Value Date 1
						myRow[11] = msgTr.getE01WRKVD2(); // Value Date 2
						myRow[12] = msgTr.getE01WRKVD3(); // Value Date 3
						myRow[13] = msgTr.getE01WRKEXR(); // Exchange Rate
						myRow[14] = msgTr.getE01WRKREF();
						// Reference -- nonvisible
						myRow[15] = msgTr.getE01WRKDS1().trim();
						// Description 1
						myRow[16] = msgTr.getE01WRKDS2().trim();
						// Description 2
						myRow[17] = msgTr.getE01WRKDS3().trim();
						// Description 3
						myRow[18] = msgTr.getE01WRKDS4().trim(); //      "
						myRow[19] = msgTr.getE01WRKDS5().trim(); //      "
						myRow[20] = msgTr.getE01WRKDS6().trim(); //      "
						myRow[21] = msgTr.getE01WRKDS7().trim(); //      "
						myRow[22] = msgTr.getE01WRKDS8().trim(); //      "
						myRow[23] = msgTr.getE01WRKDS9().trim();
						// Description 9
						myRow[25] = msgTr.getE01WRKTYP().trim(); // Type
						//fields nonvisibles
						myRow[26] = msgTr.getE01WRKCUN().trim(); // nonvisible
						myRow[27] = msgTr.getE01WRKCKN().trim(); // 	
						myRow[28] = msgTr.getE01WRKREM().trim(); //      "
						myRow[29] = msgTr.getE01WRKTDB().trim(); //      "
						myRow[30] = msgTr.getE01WRKTDC().trim(); //      "
						myRow[31] = msgTr.getE01WRKEDB().trim(); //      "
						myRow[32] = msgTr.getE01WRKEDC().trim(); //      "
						myRow[33] = msgTr.getE01WRKDRR().trim(); // 
						myRow[34] = msgTr.getE01WRKNNR().trim(); // 
						myRow[35] = msgTr.getE01NUMREC().trim(); // 
						myRow[36] = msgTr.getE01INDOPE().trim(); //
						myRow[37] = msgTr.getE01WRKMD1(); // Value Date 1
						myRow[38] = msgTr.getE01WRKMD2(); // Value Date 2
						myRow[39] = msgTr.getE01WRKMD3(); // Value Date 3
						myRow[40] = msgTr.getE01TITDSC(); // Account Description
						myRow[41] = msgTr.getE01WRKCR2(); //Credit Amount 2
						myRow[42] = msgTr.getE01WRKUN2(); //Uncollected Days 2	
						myRow[43] = msgTr.getE01WRKCR3(); //Credit Amount 3
						myRow[44] = msgTr.getE01WRKUN3(); //Uncollected Days 3		
						
						if (!myFirstRow) {
							String myDesc =
								myRow[9]
									+ myRow[15]
									+ myRow[16]
									+ myRow[17]
									+ myRow[18]
									+ myRow[19]
									+ myRow[20]
									+ myRow[21]
									+ myRow[22]
									+ myRow[23];
							transData.setDescription(myDesc.trim());
							transData.setDate1(msgTr.getE01WRKVD1());
							transData.setDate2(msgTr.getE01WRKVD2());
							transData.setDate3(msgTr.getE01WRKVD3());
							transData.setDebitAmt(msgTr.getE01WRKEDB());
							transData.setCreditAmt(msgTr.getE01WRKEDC());
							transData.setBank(msgTr.getE01WRKOBK());
							transData.setBranch(msgTr.getE01WRKOBR());
							myFirstRow = true;
						}

						trList.addRow(myFlag, myRow);

					}

					newmessage = mc.receiveMessage();

				}

				if (trList.getNoResult()) {
					transData.setTrNum("6");
				} else {
					transData.setTrNum("0");
				}

				transData.setFlagPrint(false);
                userPO.setAccOpt("");
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("trans", trList);
				ses.setAttribute("transData", transData);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						if (userPO.getOption().equals("TRANSACTION")) {
							
							userPO.setAccOpt("AC");
							ses.setAttribute("userPO", userPO);
							
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EGL0035_transaction_print.jsp");
							callPage(
								LangPath + "EGL0035_transaction_print.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EGL0035_transaction_ap.jsp");
							callPage(
								LangPath + "EGL0035_transaction_ap.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						if (userPO.getOption().equals("TRANSACTION")) {
							//flexLog("About to call Page: " + LangPath + "EGL0035_transaction_enter_inq.jsp");
							//callPage(LangPath + "EGL0035_transaction_enter_inq.jsp", req, res);
							res.setContentType("text/html");
							printClose(res.getWriter());
						} else if (userPO.getOption().equals("CD")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0140_cd_ap_maint.jsp");
							callPage(
								LangPath + "EDL0140_cd_ap_maint.jsp",
								req,
								res);
						} else if (userPO.getOption().equals("PR")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EPR0000_pr_ap_maint.jsp");
							callPage(
								LangPath + "EPR0000_pr_ap_maint.jsp",
								req,
								res);
						} else if (userPO.getOption().equals("LN")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0140_ln_ap_basic.jsp");
							callPage(
								LangPath + "EDL0140_ln_ap_basic.jsp",
								req,
								res);
						} else if (userPO.getOption().equals("OCK")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EOF0115_of_chk_basic.jsp");
							callPage(
								LangPath + "EOF0115_of_chk_basic.jsp",
								req,
								res);
						}
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

			int screen = R_ENTER;

			try {

				flexLog("Screen  Number: " + screen);

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					mc = new MessageContext(getMessageHandler("EGL0035", req));

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						// Request
						case R_TRANSACTION :
							procReqTr(mc, msgUser, req, res, session);
							break;
							// Request
						case R_ENTER :
							procReqEnter(msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;

							// END Entering

						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Socket not Open. Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					if(mc != null) mc.close();
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
}