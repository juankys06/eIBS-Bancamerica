package datapro.eibs.forex;

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

import datapro.eibs.sockets.*;

public class JSEDL0120 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange Deals
	protected static final int R_LOANS = 1;
	protected static final int R_DEPOSITS = 3;
	protected static final int R_COMM = 5; 
	protected static final int R_BANKERS = 7;
	protected static final int R_CDS = 9;

	// 
	protected static final int A_LOANS = 2;
	protected static final int A_DEPOSITS = 4;
	protected static final int A_COMM = 6;
	protected static final int A_BANKERS = 8;
	protected static final int A_CDS = 10;
	protected static final int A_MAINT_DEAL = 14;
	protected static final int A_FFS = 16;
	protected static final int A_TPS = 18;
	protected static final int A_BLK = 20;

	// entering options
	protected static final int R_ENTER_LOAN = 100;
	protected static final int R_ENTER_MAINT = 300;

	protected static final int A_ENTER_DEPOSIT = 200;
	protected static final int A_ENTER_LOAN = 400;
	protected static final int A_ENTER_COMM = 600;
	protected static final int A_ENTER_BANKERS = 800;
	protected static final int A_ENTER_CDS = 1000;
	protected static final int A_ENTER_FFS = 1200;
	protected static final int A_ENTER_TPS = 1400;
	protected static final int A_ENTER_BLK = 1600;

	protected static final int A_ACC_CALCULATE = 4000;
	protected static final int A_BLK_CALCULATE = 4100;
	protected static final int A_CDS_CALCULATE = 4200;
	protected static final int A_COMM_CALCULATE = 4300;
	protected static final int A_COMM_GETCONTRACT = 4350;
	protected static final int A_DEP_CALCULATE = 4400;
	protected static final int A_FFS_CALCULATE = 4500;
	protected static final int A_LNS_CALCULATE = 4600;
	protected static final int A_TPS_CALCULATE = 4700;
	
	protected String LangPath = "S";

	/**
	 * JSEDL0120 constructor comment.
	 */
	public JSEDL0120() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0120");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procActionMaintDeal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0002");

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}
			
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE01DLSDID(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE01DLSDID("");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("deal", msgDeal);

				if (IsNotError) { // There are no errors
					if (msgDeal.getH01SCRCOD().equals("10")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_loans.jsp");
							callPage(LangPath + "EDL0120_fe_loans.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("11")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_deposits.jsp");
							callPage(LangPath + "EDL0120_fe_deposits.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("12")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_cds.jsp");
							callPage(LangPath + "EDL0120_fe_cds.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("15")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_ffs.jsp");
							callPage(LangPath + "EDL0120_fe_ffs.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("14")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_acc.jsp");
							callPage(LangPath + "EDL0120_fe_acc.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("13")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_cp.jsp");
							callPage(LangPath + "EDL0120_fe_cp.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgDeal.getH01SCRCOD().equals("FB")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0120_fe_blk.jsp");
							callPage(LangPath + "EDL0120_fe_blk.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionEnterLoans(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_loans.jsp");
						callPage(LangPath + "EDL0120_fe_loans.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterDeposits(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			/*
			try {
				if (req.getParameter("E01FESLN3") != null)
					msgDeal.setE01FESLN3(req.getParameter("E01FESLN3"));
			} catch (Exception e) {
				msgDeal.setE01FESLN3("0");
			}*/

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}

			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}
			

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_deposits.jsp");
						callPage(
							LangPath + "EDL0120_fe_deposits.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterCommercial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			if(opCode.equals("0002")){
				msgDeal.setE01DLSDID(req.getParameter("E01DLSDID"));	
			}
			
			
			try {
				if (req.getParameter("E01DLSCUN") != null || req.getParameter("E01DLSCUN") != ""){
				msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
				}
				else{
					msgDeal.setE01DLSCUN("0");
				}
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null || req.getParameter("E01DLSSTS") != ""){
				msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
				}
				else{
					msgDeal.setE01DLSSTS("0");
				}
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_cp.jsp");
						callPage(LangPath + "EDL0120_fe_cp.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterAccept(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_acc.jsp");
						callPage(LangPath + "EDL0120_fe_acc.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterCDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_cds.jsp");
						callPage(LangPath + "EDL0120_fe_cds.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterFFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_ffs.jsp");
						callPage(LangPath + "EDL0120_fe_ffs.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterTPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_tps.jsp");
						callPage(LangPath + "EDL0120_fe_tps.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterBLK(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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

		if (!req.getParameter("E01DLSREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgDeal = (EDL0120DSMessage) mc.getMessageRecord("EDL0120DS");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));
			msgDeal.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSCUN") != null)
					msgDeal.setE01DLSCUN(req.getParameter("E01DLSCUN"));
			} catch (Exception e) {
				msgDeal.setE01DLSCUN("0");
			}
			try {
				if (req.getParameter("E01DLSSTS") != null)
					msgDeal.setE01DLSSTS(req.getParameter("E01DLSSTS"));
			} catch (Exception e) {
				msgDeal.setE01DLSSTS("0");
			}

			msgDeal.setE01DLSTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE01DLSREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE01DLSREF("0");
			}

			msgDeal.send();
			msgDeal.destroy();
			flexLog("EDL0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("EDL0120DS")) {
				try {
					msgDeal = new EDL0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDeal = (EDL0120DSMessage) newmessage;
				// showESD008004(msgDeal);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0120_fe_blk.jsp");
						callPage(LangPath + "EDL0120_fe_blk.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionLoans(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			int H = getHour();
			int M = getMinute();
			String AM = "";

			if (H < 12) {
				AM = "AM";
			} else if (H > 12) {
				AM = "PM";
				H = H - 12;
			} else if (H == 12 && M == 0) {
				AM = "M";
			}

			String T = H + ":" + M + " " + AM;
			userPO.setHeader1(T);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_loans.jsp");
					callPage(
						LangPath + "EDL0120_fe_confirm_loans.jsp",
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
							+ "EDL0120_fe_loans.jsp");
					callPage(LangPath + "EDL0120_fe_loans.jsp", req, res);
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

	protected void procCalculateLoans(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			int H = getHour();
			int M = getMinute();
			String AM = "";
	
			if (H < 12) {
				AM = "AM";
			} else if (H > 12) {
				AM = "PM";
				H = H - 12;
			} else if (H == 12 && M == 0) {
				AM = "M";
			}
	
			String T = H + ":" + M + " " + AM;
			userPO.setHeader1(T);
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_loans.jsp");
					callPage(LangPath + "EDL0120_fe_loans.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionDeposits(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_deposits.jsp");
					callPage(
						LangPath + "EDL0120_fe_confirm_deposits.jsp",
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
							+ "EDL0120_fe_deposits.jsp");
					callPage(LangPath + "EDL0120_fe_deposits.jsp", req, res);
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

	protected void procCalculateDeposits(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_deposits.jsp");
					callPage(LangPath + "EDL0120_fe_deposits.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionAccept(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_acc.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_acc.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_acc.jsp");
					callPage(LangPath + "EDL0120_fe_acc.jsp", req, res);
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

	protected void procCalculateAccept(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_acc.jsp");
					callPage(LangPath + "EDL0120_fe_acc.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionCDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_cds.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_cds.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_cds.jsp");
					callPage(LangPath + "EDL0120_fe_cds.jsp", req, res);
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

	protected void procCalculateCDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_cds.jsp");
					callPage(LangPath + "EDL0120_fe_cds.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionFFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_ffs.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_ffs.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_ffs.jsp");
					callPage(LangPath + "EDL0120_fe_ffs.jsp", req, res);
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

	protected void procCalculateFFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_ffs.jsp");
					callPage(LangPath + "EDL0120_fe_ffs.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionTPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_tps.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_tps.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_tps.jsp");
					callPage(LangPath + "EDL0120_fe_tps.jsp", req, res);
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

	protected void procCalculateTPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_tps.jsp");
					callPage(LangPath + "EDL0120_fe_tps.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionBLK(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_blk.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_blk.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_blk.jsp");
					callPage(LangPath + "EDL0120_fe_blk.jsp", req, res);
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

	protected void procCalculateBLK(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0008");
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_blk.jsp");
					callPage(LangPath + "EDL0120_fe_blk.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionCommercial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

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
			flexLog("EFE0120DS Message Sent");
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

			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDeal = (EDL0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_confirm_cp.jsp");
					callPage(LangPath + "EDL0120_fe_confirm_cp.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_cp.jsp");
					callPage(LangPath + "EDL0120_fe_cp.jsp", req, res);
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

	protected void procCalculateCommercial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL0120DSMessage msgDeal = null;
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
			msgDeal = (EDL0120DSMessage) ses.getAttribute("deal");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EDL0120");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");

			String opCode = null;
			if (req.getParameter("SCREEN").trim().equals("4350")) {
				opCode = "0006";
			} else
				opCode = "0008";

			msgDeal.setH01OPECOD(opCode);
	
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
			flexLog("EFE0120DS Message Sent");
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
	
			try {
				msgDeal = new EDL0120DSMessage();
				flexLog("EDL0120DS Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgDeal = (EDL0120DSMessage) newmessage;
	
			userPO.setHeader1(getFullTime());
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
	
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0120_fe_cp.jsp");
					callPage(LangPath + "EDL0120_fe_cp.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

	
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

			int screen = R_ENTER_LOAN;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
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
						// BEGIN Fex
						// Action
						case A_LOANS :
							procActionLoans(mc, msgUser, req, res, session);
							break;
						case A_DEPOSITS :
							procActionDeposits(mc, msgUser, req, res, session);
							break;
						case A_COMM :
							procActionCommercial(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_BANKERS :
							procActionAccept(mc, msgUser, req, res, session);
							break;
						case A_CDS :
							procActionCDS(mc, msgUser, req, res, session);
							break;
						case A_FFS :
							procActionFFS(mc, msgUser, req, res, session);
							break;
						case A_TPS :
							procActionTPS(mc, msgUser, req, res, session);
							break;
						case A_BLK :
							procActionBLK(mc, msgUser, req, res, session);
							break;
							// END CD

							// BEGIN Entering

							// Action 
						case A_ENTER_LOAN :
							procActionEnterLoans(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_DEPOSIT :
							procActionEnterDeposits(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_COMM :
							procActionEnterCommercial(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_BANKERS :
							procActionEnterAccept(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_CDS :
							procActionEnterCDS(mc, msgUser, req, res, session);
							break;
						case A_ENTER_TPS :
							procActionEnterTPS(mc, msgUser, req, res, session);
							break;
						case A_ENTER_FFS :
							procActionEnterFFS(mc, msgUser, req, res, session);
							break;
						case A_MAINT_DEAL :
							procActionMaintDeal(mc, msgUser, req, res, session);
							break;
						case A_ENTER_BLK :
							procActionEnterBLK(mc, msgUser, req, res, session);
							break;
							// END Entering

							//Calculate
						case A_ACC_CALCULATE :
							procCalculateAccept(mc, msgUser, req, res, session);
							break;
						case A_BLK_CALCULATE :
							procCalculateBLK(mc, msgUser, req, res, session);
							break;
						case A_CDS_CALCULATE :
							procCalculateCDS(mc, msgUser, req, res, session);
							break;
						case A_COMM_CALCULATE :
						case A_COMM_GETCONTRACT :	
							procCalculateCommercial(mc, msgUser, req, res, session);
							break;
						case A_DEP_CALCULATE :
							procCalculateDeposits(mc, msgUser, req, res, session);
							break;
						case A_FFS_CALCULATE :
							procCalculateFFS(mc, msgUser, req, res, session);
							break;
						case A_LNS_CALCULATE :
							procCalculateLoans(mc, msgUser, req, res, session);
							break;
						case A_TPS_CALCULATE :
							procCalculateTPS(mc, msgUser, req, res, session);
							break;

							
							
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

}