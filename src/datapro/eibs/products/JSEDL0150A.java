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

import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0150A extends SuperServlet {

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINT_TO_BEAN = 500;
	protected static final int A_MAINT_TO_SCK = 502;
	protected static final int A_MAINT_DED = 504;
	protected static final int A_MAINT_COLL = 506;
	protected static final int A_MAINT_PMNT = 508;
	protected static final int R_TRANSACTION = 5;
	protected static final int A_TRANSACTION = 6;
	protected static final int R_TITULARES = 7;
	protected static final int A_TITULARES = 8;
	protected static final int R_PRECANCEL = 9;
	protected static final int A_PRECANCEL = 10;
	protected static final int R_SPECIAL_INST = 11;
	protected static final int A_SPECIAL_INST = 12;
	protected static final int R_EXCHANGE = 13;
	protected static final int A_EXCHANGE = 14;
	protected static final int R_CODES = 15;
	protected static final int A_CODES = 16;
	protected static final int R_FINISH = 17;
	protected static final int A_FINISH = 18;
	protected static final int R_INT_PREP = 19;
	protected static final int A_INT_PREP = 20;
	protected static final int R_CANCEL_DET = 21;
	protected static final int A_CANCEL_DET = 22;
	protected static final int R_PRINT_NEW = 21;
	protected static final int A_PRINT_NEW = 22;
	protected static final int R_GEN_INF = 23;
	protected static final int A_GEN_INF = 24;
	protected static final int R_NEW_TRAN = 25;
	protected static final int A_NEW_TRAN = 26;
	protected static final int R_OTHERS_TRANS = 28;
	
	protected static final int R_CANCEL_DEBIT = 33;

	protected static final int R_ADDCODES = 35;
	protected static final int A_ADDCODES = 36;	
	
	protected static final int R_ACCOUNT_TITLE = 57;
		
	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 300;
	protected static final int R_ENTER_CANCEL = 501;
	protected static final int R_ENTER_TRANSAC = 700;
	protected static final int R_ENTER_PRINT = 900;

	protected static final int A_ENTER_NEW = 200;
	protected static final int A_ENTER_MAINT = 400;
	protected static final int A_ENTER_CANCEL = 600;
	protected static final int A_ENTER_TRANSAC = 800;
	protected static final int A_ENTER_PRINT = 1000;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0150A() {
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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String accnum = req.getParameter("E01DEAACC");
		if (accnum != null) {
			userPO.setAccNum(accnum);			
			userPO.setIdentifier(accnum);			
		}

		// Send Initial data
		try {
			msgLN = (EDL015001Message) mc.getMessageRecord("EDL015001");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL0150");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
			msgLN.setH01OPECOD("0004");
			msgLN.setE01DEAACD("10");
			msgLN.setH01FLGWK1("A");
			try {
				msgLN.setE01DEAACC(userPO.getAccNum());
			} catch (Exception e) {
				msgLN.setE01DEAACC("0");
			}

			msgLN.send();
			msgLN.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			ses.setAttribute("userPO", userPO);
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0140_ln_ap_basic.jsp");
					callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
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
	
	protected boolean procRecMaintData(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		EDL015002Message msgDed = null;
		EDL015003Message msgPmnt = null;
		EDL015004Message msgColl = null;
		EDL015005Message msgPmntP = null;	
		EDL015230Message msgNewOK = null;
		JBListRec taxList = null;
		JBListRec comList = null;
		JBListRec ivaList = null;
		JBListRec insList = null;
		JBListRec dedList = null;
		JBListRec collList = null;
		JBListRec pmntList = null;
		JBListRec pmntPList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgLN = new datapro.eibs.beans.EDL015001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 6;
			dedList = new datapro.eibs.beans.JBListRec();
			dedList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 7;
			pmntList = new datapro.eibs.beans.JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 5;
			collList = new datapro.eibs.beans.JBListRec();
			collList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 2;
			pmntPList = new datapro.eibs.beans.JBListRec();
			pmntPList.init(colNum);
		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}  		

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("apBasic", msgLN);
		ses.setAttribute("ded", dedList);
		ses.setAttribute("pmnt", pmntList);
		ses.setAttribute("pmntPlus", pmntPList);
		ses.setAttribute("coll", collList);

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
			while (true) {

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL015001")) {

					msgLN = (EDL015001Message) newmessage;

					userPO.setIdentifier(msgLN.getE01DEAACC());
					userPO.setHeader1(msgLN.getE01DEAPRO());
					userPO.setHeader2(msgLN.getE01DEACUN());
					userPO.setHeader3(msgLN.getE01CUSNA1());
					userPO.setHeader4(msgLN.getE01DSCPRO());
					userPO.setHeader16(msgLN.getE01FLGDED());
					userPO.setHeader17(msgLN.getE01FLGCOL());
					userPO.setCurrency(msgLN.getE01DEACCY());
					userPO.setBank(msgLN.getE01DEABNK());
					userPO.setBranch(msgLN.getE01DEABRN());
					userPO.setType(msgLN.getE01DEATYP());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("apBasic", msgLN);
					ses.setAttribute("userPO", userPO);

				} else if (newmessage.getFormatName().equals("EDL015009")) {
					userPO.setHeader10("BASIC");
					ses.setAttribute("userPO", userPO);
					break;
				} else if (newmessage.getFormatName().equals("EDL015002")) {
					//Deductions List
					// Fill List bean

					int colNum = 29;

					try {
						dedList = new datapro.eibs.beans.JBListRec();
						dedList.init(colNum);
						taxList = new datapro.eibs.beans.JBListRec();
						taxList.init(colNum);
						ivaList = new datapro.eibs.beans.JBListRec();
						ivaList.init(colNum);
						comList = new datapro.eibs.beans.JBListRec();
						comList.init(colNum);
						insList = new datapro.eibs.beans.JBListRec();
						insList.init(colNum);
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}
					
					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgDed = (EDL015002Message) newmessage;

						marker = msgDed.getH02FLGMAS();
						if (marker.equals("*")) {
							break;
						} else {
							int typ;
							try{
							  typ = Integer.parseInt(msgDed.getE02DLIAPC());
							}
							catch (Exception e) {
							  typ = 0;
							}
							//Quote List
							myRow[0] =  msgDed.getE02BSEAMT().trim();	// 
							myRow[1] =  msgDed.getE02DEAACC().trim();	// 
							myRow[2] =  msgDed.getE02DEDAMT().trim();	// 
							myRow[3] =  msgDed.getE02DLIAPC().trim();	// 
							myRow[4] =  msgDed.getE02DLICDE().trim();	// 
							myRow[5] =  msgDed.getE02DLIDPM().trim();	// 
							myRow[6] =  msgDed.getE02DLIFAC().trim();	// 
							myRow[7] =  msgDed.getE02DLIIVA().trim();	// 
							myRow[8] =  msgDed.getE02DLIIVB().trim();	// 
							myRow[9] =  msgDed.getE02DLIIVP().trim();	// 
							myRow[10] =  msgDed.getE02DLINME().trim();	//CHARGE NAME 
							myRow[11] =  msgDed.getE02DLIOAC().trim();	// 
							myRow[12] =  msgDed.getE02DLIOBK().trim();	// 
							myRow[13] =  msgDed.getE02DLIOBR().trim();	// 
							myRow[14] =  msgDed.getE02DLIOCY().trim();	// 
							myRow[15] =  msgDed.getE02DLIOGL().trim();	// 
							myRow[16] =  msgDed.getE02DLIPLZ().trim();	// 
							myRow[17] =  msgDed.getE02DLIPRO().trim();	// 
							myRow[18] =  msgDed.getE02FACTOR().trim();	// 
							myRow[19] =  msgDed.getE02MAXAMT().trim();	// 
							myRow[20] =  msgDed.getE02MINAMT().trim();	// 
							myRow[21] =  msgDed.getE02DLIPBC().trim();	//
							myRow[22] =  msgDed.getE02DLISEG().trim();	//
							myRow[23] =  msgDed.getE02DLISEL().trim();	//
							myRow[24] =  msgDed.getE02DLIAPC().trim();	//
							myRow[25] =  msgDed.getE02DLIDAC().trim();	//
							myRow[26] =  msgDed.getE02DLINPM().trim();	//        
							myRow[27] =  msgDed.getE02DLINPD().trim();	//
							myRow[28] =  msgDed.getE02DLINPY().trim();	//
						
							if (msgDed.getE02DLIPRO().trim().equals("L")) {
								dedList.addRow(myFlag, myRow);
							} else  {
								switch (typ) {				
									case 1 : //taxes
											taxList.addRow(myFlag, myRow);
											break;
									case 2 ://commisions
											comList.addRow(myFlag, myRow);
											break;
									case 3 ://insurance
											insList.addRow(myFlag, myRow);
											break;
									default ://other charges
											ivaList.addRow(myFlag, myRow);
											break;
								}
							}				
						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("ded", dedList);
					ses.setAttribute("tax", taxList);
					ses.setAttribute("ins", insList);
					ses.setAttribute("com", comList);
					ses.setAttribute("iva", ivaList);
				} else if (newmessage.getFormatName().equals("EDL015003")) {
					//Payments List
					// Fill List bean

					int colNum = 7;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgPmnt = (EDL015003Message) newmessage;

						marker = msgPmnt.getH03FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							//Quote List
							myRow[0] = Util.formatCell(msgPmnt.getE03DLPPNU());
							// Quote Number
							myRow[1] = Util.formatCell(msgPmnt.getE03DLPPD1());
							// Date
							myRow[2] = Util.formatCell(msgPmnt.getE03DLPPD2());
							// Date
							myRow[3] = Util.formatCell(msgPmnt.getE03DLPPD3());
							// Date
							myRow[4] = Util.formatCell(msgPmnt.getE03DLPPRI());
							// Principal
							myRow[5] = Util.formatCell(msgPmnt.getE03DLPINT());
							// Interest
							myRow[6] = Util.formatCell(msgPmnt.getE03DLPIIP());
							// Interest Included

							pmntList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("pmnt", pmntList);
				} else if (newmessage.getFormatName().equals("EDL015004")) {
					//Deductions List
					// Fill List bean

					int colNum = 5;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgColl = (EDL015004Message) newmessage;

						marker = msgColl.getH04FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							//Quote List
							myRow[0] = Util.formatCell(msgColl.getE04RCLACB());
							// Collateral Number
							myRow[1] = Util.formatCell(msgColl.getE04RCLTYB());
							// Type
							myRow[2] = Util.formatCell(msgColl.getE04RCLCCY());
							// Currency
							myRow[3] = Util.formatCell(msgColl.getE04RCLAMT());
							// Amount
							myRow[4] = Util.formatCell(msgColl.getE04RCLF04());

							collList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("coll", collList);
				}
				else if (newmessage.getFormatName().equals("EDL015005")) {
					// Irregular Payments List
					// Fill List bean

					flexLog("EDL015005 Message Received");
				
					int colNum = 2;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i=0; i<colNum; i++) {
						myRow[i] = "";
					}
				
					while (true) {

						msgPmntP = (EDL015005Message)newmessage;

						marker = msgPmntP.getH05FLGMAS();

						if (marker.equals("*")) {
							break;
						}
						else {
							//Quote List
							myRow[0] =  Util.trim(msgPmntP.getE05DLPPNU());	// Quote Number
							myRow[1] =  Util.trim(msgPmntP.getE05DLPVEX());	// Principal
							
							pmntPList.addRow(myFlag, myRow);
						}
						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("pmntPlus", pmntPList);
				}							
				 else if (newmessage.getFormatName().equals("EDL015230")) {

					msgNewOK = (EDL015230Message) newmessage;

					userPO.setHeader10("CONF");
					// mod: EMAT 9/4/2001
					// to capture the new account number into userPO
					userPO.setIdentifier(msgNewOK.getE30DEAACC());
					//
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("lnGenTran", msgNewOK);
					break;
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		return IsNotError;

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

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("LN");
			userPO.setPurpose("APPROVAL_INQ");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0150_ln_enter_maint.jsp");
			callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
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
		ESD000005Message msgLN = null;
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
			msgLN = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgLN.setH05USR(user.getH01USR());
			msgLN.setH05PGM("EDL0150");
			msgLN.setH05TIM(""); //getTimeStamp()
			msgLN.setH05SCR("01");
			msgLN.setH05OPE(opCode);
			msgLN.setE05ACC(userPO.getIdentifier());
			msgLN.setE05TYP("2");
			msgLN.send();
			msgLN.destroy();
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
					msgLN = new datapro.eibs.beans.ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000005Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnInst", msgLN);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_special_inst.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_special_inst.jsp",
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
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
	protected void procReqExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015206Message msgLN = null;
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
			msgLN = (EDL015206Message) mc.getMessageRecord("EDL015206");
			msgLN.setH06USERID(user.getH01USR());
			msgLN.setH06PROGRM("EDL0150");
			msgLN.setH06TIMSYS(""); //getTimeStamp()
			msgLN.setH06SCRCOD("01");
			msgLN.setH06OPECOD(opCode);
			msgLN.setE06DEAACC(userPO.getIdentifier());
			msgLN.setH06FLGWK1("A");
			msgLN.send();
			msgLN.destroy();
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

			if (newmessage.getFormatName().equals("EDL015206")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015206Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015206Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnRate", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_xchg_rate.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_xchg_rate.jsp",
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
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
	protected void procReqGenInf(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL015005Message msgGenInf = null;
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
			msgGenInf = (EDL015005Message) mc.getMessageRecord("EDL015005");
			msgGenInf.setH05USERID(user.getH01USR());
			msgGenInf.setH05PROGRM("EDL0150");
			msgGenInf.setH05TIMSYS(getTimeStamp());
			msgGenInf.setH05SCRCOD("01");
			msgGenInf.setH05OPECOD(opCode);
			msgGenInf.setE05DEAACC(userPO.getIdentifier());
			msgGenInf.setH05FLGWK1("A");
			msgGenInf.send();
			msgGenInf.destroy();
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
			if (newmessage.getFormatName().equals("EDL015005")) {
				try {
					msgGenInf = new datapro.eibs.beans.EDL015005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgGenInf = (EDL015005Message) newmessage;
				// showESD008004(msgGenInf);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnGenInf", msgGenInf);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_gen_inf.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_gen_inf.jsp",
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
								+ "EDL0140_ln_ap_mant.jsp");
						callPage(LangPath + "EDL0140_ln_ap_mant.jsp", req, res);
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
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLN = (EDL015001Message) mc.getMessageRecord("EDL015001");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL0150");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
			msgLN.setH01OPECOD("0004");
			msgLN.setE01DEAACD("10");
			msgLN.setH01FLGWK1("A");
			try {
				msgLN.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgLN.setE01DEAACC("0");
			}

			msgLN.send();
			msgLN.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0140_ln_ap_basic.jsp");
					callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0150_ln_enter_maint.jsp");
					callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
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
	protected void procReqNewTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL015230Message msgLN = null;
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
			msgLN = (EDL015230Message) mc.getMessageRecord("EDL015230");
			msgLN.setH30USERID(user.getH01USR());
			msgLN.setH30PROGRM("EDL0130");
			msgLN.setH30TIMSYS(getTimeStamp());
			msgLN.setH30SCRCOD("01");
			msgLN.setH30OPECOD(opCode);
			msgLN.setE30DEAACC(userPO.getIdentifier());
			msgLN.setH30FLGWK1("A");
			msgLN.send();
			msgLN.destroy();
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
			if (newmessage.getFormatName().equals("EDL015230")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015230Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgLN = (EDL015230Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnGenTran", msgLN);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_new_transac.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_new_transac.jsp",
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
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgLN = null;
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
			msgLN = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgLN.setH02USR(user.getH01USR());
			msgLN.setH02PGM("EDL0150");
			msgLN.setH02TIM(""); //getTimeStamp()
			msgLN.setH02SCR("01");
			msgLN.setH02OPE(opCode);
			msgLN.setE02ACC(userPO.getIdentifier());
			msgLN.send();
			msgLN.destroy();
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
					msgLN = new datapro.eibs.beans.ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000002Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_codes.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_codes.jsp",
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
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
	
	protected void procReqAdditionalCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD001401Message msgLN = null;
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
			msgLN = (ESD001401Message) mc.getMessageRecord("ESD001401");
			msgLN.setH14USR(user.getH01USR());
			msgLN.setH14PGM("ESD0014");
			msgLN.setH14TIM(""); //getTimeStamp()
			msgLN.setH14SCR("01");
			msgLN.setH14OPE(opCode);
			msgLN.setE14ACC(userPO.getIdentifier());
			msgLN.send();
			msgLN.destroy();
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

			if (newmessage.getFormatName().equals("ESD001401")) {
				try {
					msgLN = new datapro.eibs.beans.ESD001401Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD001401Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_addcodes.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_addcodes.jsp",
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
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
		ESD000006Message msgLN = null;
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
			msgLN = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgLN.setH06USR(user.getH01USR());
			msgLN.setH06PGM("EDL0130");
			msgLN.setH06TIM(""); //getTimeStamp()
			msgLN.setH06SCR("01");
			msgLN.setH06OPE(opCode);
			msgLN.setE06ACC(userPO.getIdentifier());
			msgLN.setE06RTP("H");
			msgLN.send();
			msgLN.destroy();
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
					msgLN = new datapro.eibs.beans.ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000006Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTit", msgLN);
				getTitularsDescription(user, req, res, ses);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_ln_ap_tit.jsp");
						callPage(LangPath + "EDL0140_ln_ap_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_ln_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_basic.jsp",
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
	protected void procReqPaymPlan(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {


		MessageRecord newmessage = null;
		EDL090001Message msgHeader = null;
		ELEERRMessage msgError = null;
		EDL090002Message msgList = null;
		JBListRec beanList = null;
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
			msgHeader = (EDL090001Message)mc.getMessageRecord("EDL090001");
			msgHeader.setH01USERID(user.getH01USR());
			msgHeader.setH01PROGRM("EDL0900");
			msgHeader.setH01TIMSYS(getTimeStamp());
			msgHeader.setH01OPECOD(opCode);
			try {
				msgHeader.setE01DEAACC(userPO.getIdentifier());
			}
			catch (Exception e) {
				msgHeader.setE01DEAACC("0");
			}
			msgHeader.send();	
			msgHeader.destroy();

			flexLog("EDL090001 Message Sent");
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

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				// showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			}
			else if (newmessage.getFormatName().equals("EDL090001")) {
				try {
					msgHeader = new datapro.eibs.beans.EDL090001Message();
				
					flexLog("EDL090001 Message Received");
				
					msgHeader = (EDL090001Message)newmessage;
				
					//Header
					userPO.setHeader10(Util.formatDate(msgHeader.getE01OPEND1(),msgHeader.getE01OPEND2(),msgHeader.getE01OPEND3())); //Opening Date
					userPO.setHeader11(Util.formatDate(msgHeader.getE01MATUR1(),msgHeader.getE01MATUR2(),msgHeader.getE01MATUR3())); //Maturity Date
					userPO.setHeader12(Util.formatCCY(msgHeader.getE01DEAOAM()));  // Original Amount
					userPO.setHeader13(Util.formatCell(msgHeader.getE01DEARTE())); // Rate
					userPO.setHeader14(Util.formatCell(msgHeader.getE01DEABAS())); // Basis
					userPO.setHeader15(Util.formatCell(msgHeader.getE01DEAICT())); // Interest Type
				}
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgHeader = (EDL090001Message)newmessage;


				// Fill List bean
				int colNum = 73;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
			
				while (true) {

					newmessage = mc.receiveMessage();
					msgList = (EDL090002Message)newmessage;

					marker = msgList.getE02ENDFLD();

					if (marker.equals("*")) {
						break;
					}
					else {
						//Quote List
						myRow[0] =  Util.formatCell(msgList.getE02DLPPNU());	// Quote Number
						myRow[1] =  Util.formatDate(msgList.getE02DLPPD1(),msgList.getE02DLPPD2(),msgList.getE02DLPPD3());	// Fecha a Pagar
						myRow[2] =  Util.formatCCY(msgList.getE02DLPPRN());	// Principal Pymt
						myRow[3] =  Util.formatCCY(msgList.getE02DLPINT());	// Interest Pymnt
						myRow[4] =  Util.formatCCY(msgList.getE02DLPOTH());	// Other Charges
						myRow[5] =  Util.formatCell(msgList.getE02DLPTOT());	// Total Quotes
						myRow[6] =  Util.formatCell(msgList.getE02DLPBAL());	// Balance
						myRow[7] =  Util.formatCell(msgList.getE02DLPSTS());	// Status
						myRow[8] =  Util.formatCell(msgList.getE02DLPVEN());	// Mature
						myRow[9] =  Util.formatDate(msgList.getE02DLPDT1(),msgList.getE02DLPDT2(),msgList.getE02DLPDT3());	// Fecha a Pagar
						myRow[10] =  Util.formatCell(msgList.getE02DLPPAG());	// Total Pymnt
						//Quote Detail
						myRow[11] =  Util.formatCell(msgList.getE02DESC01());	// Description
						myRow[12] =  Util.formatCell(msgList.getE02DESC02());
						myRow[13] =  Util.formatCell(msgList.getE02DESC03());
						myRow[14] =  Util.formatCell(msgList.getE02DESC04());
						myRow[15] =  Util.formatCell(msgList.getE02DESC05());
						myRow[16] =  Util.formatCell(msgList.getE02DESC06());
						myRow[17] =  Util.formatCell(msgList.getE02DESC07());
						myRow[18] =  Util.formatCell(msgList.getE02DESC08());
						myRow[19] =  Util.formatCell(msgList.getE02DESC09());
						myRow[20] =  Util.formatCell(msgList.getE02DESC10());
						myRow[21] =  Util.formatCell(msgList.getE02DESC11());
						myRow[22] =  Util.formatCell(msgList.getE02DESC12());
						myRow[23] =  Util.formatCell(msgList.getE02DESC13());
						myRow[24] =  Util.formatCell(msgList.getE02DESC14());
						myRow[25] =  Util.formatCell(msgList.getE02DESC15());
						myRow[26] =  Util.formatCell(msgList.getE02DESC16());
						myRow[27] =  Util.formatCell(msgList.getE02DESC17());
						myRow[28] =  Util.formatCell(msgList.getE02DESC18());
						myRow[29] =  Util.formatCell(msgList.getE02DESC19());
						myRow[30] =  Util.formatCell(msgList.getE02DESC20());
						myRow[31] =  Util.formatCCY(msgList.getE02AMNT01().trim()); 	//Amount
						myRow[32] =  Util.formatCCY(msgList.getE02AMNT02().trim());
						myRow[33] =  Util.formatCCY(msgList.getE02AMNT03().trim());
						myRow[34] =  Util.formatCCY(msgList.getE02AMNT04().trim());
						myRow[35] =  Util.formatCCY(msgList.getE02AMNT05().trim());
						myRow[36] =  Util.formatCCY(msgList.getE02AMNT06().trim());
						myRow[37] =  Util.formatCCY(msgList.getE02AMNT07().trim());
						myRow[38] =  Util.formatCCY(msgList.getE02AMNT08().trim());
						myRow[39] =  Util.formatCCY(msgList.getE02AMNT09().trim());
						myRow[40] =  Util.formatCCY(msgList.getE02AMNT10().trim());
						myRow[41] =  Util.formatCCY(msgList.getE02AMNT11().trim());
						myRow[42] =  Util.formatCCY(msgList.getE02AMNT12().trim());
						myRow[43] =  Util.formatCCY(msgList.getE02AMNT13().trim());
						myRow[44] =  Util.formatCCY(msgList.getE02AMNT14().trim());
						myRow[45] =  Util.formatCCY(msgList.getE02AMNT15().trim());
						myRow[46] =  Util.formatCCY(msgList.getE02AMNT16().trim());
						myRow[47] =  Util.formatCCY(msgList.getE02AMNT17().trim());
						myRow[48] =  Util.formatCCY(msgList.getE02AMNT18().trim());
						myRow[49] =  Util.formatCCY(msgList.getE02AMNT19().trim());
						myRow[50] =  Util.formatCCY(msgList.getE02AMNT20().trim());
						myRow[51] =  Util.formatCCY(msgList.getE02PAID01());	//Paid	
						myRow[52] =  Util.formatCCY(msgList.getE02PAID02());
						myRow[53] =  Util.formatCCY(msgList.getE02PAID03());
						myRow[54] =  Util.formatCCY(msgList.getE02PAID04());
						myRow[55] =  Util.formatCCY(msgList.getE02PAID05());
						myRow[56] =  Util.formatCCY(msgList.getE02PAID06());
						myRow[57] =  Util.formatCCY(msgList.getE02PAID07());
						myRow[58] =  Util.formatCCY(msgList.getE02PAID08());
						myRow[59] =  Util.formatCCY(msgList.getE02PAID09());
						myRow[60] =  Util.formatCCY(msgList.getE02PAID10());
						myRow[61] =  Util.formatCCY(msgList.getE02PAID11());
						myRow[62] =  Util.formatCCY(msgList.getE02PAID12());
						myRow[63] =  Util.formatCCY(msgList.getE02PAID13());
						myRow[64] =  Util.formatCCY(msgList.getE02PAID14());
						myRow[65] =  Util.formatCCY(msgList.getE02PAID15());
						myRow[66] =  Util.formatCCY(msgList.getE02PAID16());
						myRow[67] =  Util.formatCCY(msgList.getE02PAID17());
						myRow[68] =  Util.formatCCY(msgList.getE02PAID18());
						myRow[69] =  Util.formatCCY(msgList.getE02PAID19());
						myRow[70] =  Util.formatCCY(msgList.getE02PAID20());
						myRow[71] =  Util.formatCCY(msgList.getE02TOAMNT());
						myRow[72] =  Util.formatCCY(msgList.getE02TOPAID());
						
						beanList.addRow(myFlag, myRow);
									
					}

				}

				ses.setAttribute("list", beanList);
				ses.setAttribute("header", msgHeader);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0900_ln_ap_crn_pay.jsp");
					callPage(LangPath + "EDL0900_ln_ap_crn_pay.jsp", req, res);
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
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqCancelDebit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL015210Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0004";
	
		// Send Initial data
		try {
			msgLN = (EDL015210Message) mc.getMessageRecord("EDL015210");
			msgLN.setH10USERID(user.getH01USR());
			msgLN.setH10PROGRM("EDL0150");
			msgLN.setH10TIMSYS(""); //getTimeStamp()
			msgLN.setH10SCRCOD("01");
			msgLN.setH10OPECOD(opCode);
			msgLN.setE10DEAACC(userPO.getIdentifier());
			msgLN.send();
			msgLN.destroy();
		
	
		// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		
		// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EDL015210")) {
				
	
				msgLN = (EDL015210Message) newmessage;
				// showESD008004(msgLN);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnAutoDebit", msgLN);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0140_ln_cancel_auto_debit.jsp");
						callPage(LangPath + "EDL0140_ln_cancel_auto_debit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0140_ln_ap_basic.jsp");
						callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);
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

				flexLog("Screen  Number: " + screen);

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 5);
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
						// Request
						case R_MAINTENANCE :
							procReqMaint(mc, msgUser, req, res, session);
							break;
						case R_SPECIAL_INST :
							procReqEspInst(mc, msgUser, req, res, session);
							break;
						case R_TITULARES :
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_EXCHANGE :
							procReqExchangeRate(mc, msgUser, req, res, session);
							break;
						case R_CODES :
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_ACCOUNT_TITLE :
							procReqAccountTitle(mc, msgUser, req, res, session);
							break;																															
						case R_ADDCODES :
							procReqAdditionalCodes(mc, msgUser, req, res, session);
							break;
							
						case R_GEN_INF :
							procReqGenInf(mc, msgUser, req, res, session);
							break;
						case R_CANCEL_DEBIT :
							procReqCancelDebit(mc, msgUser, req, res, session);
							break;
						case R_NEW_TRAN :
							procReqNewTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_OTHERS_TRANS :
							procReqOthersTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END LN

							// BEGIN Entering
							// Request
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;

							// Action 
						case A_ENTER_MAINT :
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
					int sck = getInitSocket(req) + 5;
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

			transData = new datapro.eibs.beans.DataTransaction();

			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=1");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
					try {
						flexLog("About to call Page: " + LangPath + "EDL0140_ln_ap_account_title.jsp");
						callPage(LangPath + "EDL0140_ln_ap_account_title.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0140_ln_ap_basic.jsp");
						callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);
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
	
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqProductLN(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD071110Message msgProdLN = null;
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

		String bank = userPO.getBank();
		String prodCode = userPO.getHeader1();

		String opCode = "0004";

		// Send Initial data
		try
		{
			msgProdLN = (ESD071110Message)mc.getMessageRecord("ESD071110");
			msgProdLN.setH10USERID(user.getH01USR());
			msgProdLN.setH10PROGRM("ESD0711");
			msgProdLN.setH10TIMSYS(getTimeStamp());
			msgProdLN.setH10SCRCOD("01");
			msgProdLN.setH10OPECOD(opCode);
			msgProdLN.setE10APCCDE(prodCode);
			msgProdLN.setE10APCBNK(bank);
			msgProdLN.send();	
			msgProdLN.destroy();

			flexLog("ESD071110 Message Sent");
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive  Messages
		try
		{
			newmessage = mc.receiveMessage();
	  
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			
				try {
					flexLog("About to call Page: " + LangPath + "EDL0140_ln_ap_basic.jsp");
					callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
			}	
			else if (newmessage.getFormatName().equals("ESD071110")) {
				try {
					msgProdLN = new datapro.eibs.beans.ESD071110Message();
					flexLog("ESD071110 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgProdLN = (ESD071110Message)newmessage; 
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("lnProdInq", msgProdLN);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_products_ap_ln.jsp");
					callPage(LangPath + "EDL0160_products_ap_ln.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
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