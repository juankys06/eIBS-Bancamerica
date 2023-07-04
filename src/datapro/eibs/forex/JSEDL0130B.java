package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import java.util.*;
import datapro.eibs.sockets.*;

public class JSEDL0130B extends datapro.eibs.master.SuperServlet {

	// Basic Options Socket 1
	protected static final int R_CDS = 1;
	protected static final int R_TDS = 3;
	protected static final int R_FFS = 5;
	protected static final int R_TPS = 7;
	protected static final int R_ACC = 9;
	protected static final int A_CDS = 4;
	protected static final int A_TDS = 6;
	protected static final int A_FFS = 8;
	protected static final int A_TPS = 10;
	protected static final int A_ACC = 30;
	protected static final int A_BLK = 22;

	//Swift Options Socket 1
	protected static final int R_S_CDS = 31;
	protected static final int R_S_TDS = 33;
	protected static final int R_S_TPS = 35;
	protected static final int R_S_FFS = 37;
	protected static final int R_S_ACC = 39;
	protected static final int A_S_CDS = 32;
	protected static final int A_S_TDS = 34;
	protected static final int A_S_TPS = 36;
	protected static final int A_S_FFS = 38;
	protected static final int A_S_ACC = 40;
	protected static final int R_SC_CDS = 41;
	protected static final int R_SC_TDS = 43;
	protected static final int R_SC_TPS = 45;
	protected static final int R_SC_FFS = 47;
	protected static final int R_SC_ACC = 49;

	//Back Office Options Socket 1
	protected static final int A_CDS_MOD = 12;
	protected static final int A_TDS_MOD = 14;
	protected static final int A_FFS_MOD = 16;
	protected static final int A_TPS_MOD = 18;
	protected static final int A_ACC_MOD = 20;
	
	// Socket 3
	protected static final int R_TITULARES = 51;
	protected static final int A_TITULARES = 52;
	protected static final int R_PAYMENT_PLAN = 53;
	protected static final int A_PAYMENT_PLAN = 54;
	protected static final int R_SPECIAL_INST = 55;
	protected static final int A_SPECIAL_INST = 56;
	protected static final int R_BENEFICIARY = 57;
	protected static final int A_BENEFICIARY = 58;
	protected static final int R_EXCHANGE = 59;
	protected static final int A_EXCHANGE = 60;
	protected static final int R_CODES = 61;
	protected static final int A_CODES = 62;
	protected static final int R_RENOV_MANT = 63;
	protected static final int A_RENOV_MANT = 64;
	
	// Socket 1
	protected static final int R_MONEY = 65;
	protected static final int A_MONEY = 66;
	protected static final int A_ENTER_CDS = 1000;
	protected static final int A_ENTER_TDS = 1200;
	protected static final int A_ENTER_FFS = 1400;
	protected static final int A_ENTER_TPS = 1600;
	protected static final int A_ENTER_ACC = 1800;
	protected static final int A_ENTER_BLK = 2000;

	protected String LangPath = "S";

	/**
	 * Constructor comment.
	 */
	public JSEDL0130B() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created by David Mavilla.
	 * Modify by C. Castillo in order to use socket 1 or 3 according  with the Screen
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
			
		}
	} else {

		int screen = A_CDS;
		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		} catch (Exception e) {
		}
		int socketNumber = 1;
		if (screen > 50 && screen < 65) {
			socketNumber = 3;
		}
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			try {
				s = new Socket(super.hostIP, getInitSocket(req) + socketNumber);
				s.setSoTimeout(super.sckTimeOut);
				mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
					 new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
				
				switch (screen) {
					// BEGIN Fex
					// Action
					case A_CDS :
						procActionCDS(mc, msgUser, req, res, session);
						break;
					case A_TDS :
						procActionTDS(mc, msgUser, req, res, session);
						break;
					case A_FFS :
						procActionFFS(mc, msgUser, req, res, session);
						break;
					case A_TPS :
						procActionTPS(mc, msgUser, req, res, session);
						break;
					case A_ACC :
						procActionAcc(mc, msgUser, req, res, session);
						break;
					case A_BLK :
						procActionBLK(mc, msgUser, req, res, session);
						break;

						//Basic Information	
					case R_CDS :
						procReqEnterCDS(msgUser, req, res, session);
						break;
					case R_TDS :
						procReqEnterTDS(msgUser, req, res, session);
						break;
					case R_FFS :
						procReqEnterFFS(msgUser, req, res, session);
						break;
					case R_TPS :
						procReqEnterCDS(msgUser, req, res, session);
						break;
					case R_ACC :
						procReqEnterACC(msgUser, req, res, session);
						break;

						//Action Module	
					case A_CDS_MOD :
						procActionCDSMod(msgUser, req, res, session);
						break;
					case A_TDS_MOD :
						procActionTDSMod(msgUser, req, res, session);
						break;
					case A_FFS_MOD :
						procActionFFSMod(msgUser, req, res, session);
						break;
					case A_TPS_MOD :
						procActionTPSMod(msgUser, req, res, session);
						break;
					case A_ACC_MOD :
						procActionACCMod(msgUser, req, res, session);
						break;

						// Action 
					case A_ENTER_CDS :
						procActionEnterCDS(mc, msgUser, req, res, session);
						break;
					case A_ENTER_TDS :
						procActionEnterTDS(mc, msgUser, req, res, session);
						break;
					case A_ENTER_TPS :
						procActionEnterTPS(mc, msgUser, req, res, session);
						break;
					case A_ENTER_FFS :
						procActionEnterFFS(mc, msgUser, req, res, session);
						break;
					case A_ENTER_ACC :
						procActionEnterAcc(mc, msgUser, req, res, session);
						break;
					case A_ENTER_BLK :
						procActionEnterBLK(mc, msgUser, req, res, session);
						break;

						//Swift
					case R_SC_CDS :
						procReqSwift320CDS(mc, msgUser, req, res, session);
						break;
					case R_SC_TDS :
						procReqSwift320TDS(mc, msgUser, req, res, session);
						break;
					case R_SC_FFS :
						procReqSwift320FFS(mc, msgUser, req, res, session);
						break;
					case R_SC_TPS :
						procReqSwift320TPS(mc, msgUser, req, res, session);
						break;
					case R_SC_ACC :
						procReqSwift320ACC(mc, msgUser, req, res, session);
						break;

					case A_S_CDS :
						procActionSwift100CDS(mc, msgUser, req, res, session);
						break;
					case A_S_TDS :
						procActionSwift100TDS(mc, msgUser, req, res, session);
						break;
					case A_S_FFS :
						procActionSwift100FFS(mc, msgUser, req, res, session);
						break;
					case A_S_ACC :
						procActionSwift100ACC(mc, msgUser, req, res, session);
						break;
					case A_S_TPS :
						procActionSwift100TPS(mc, msgUser, req, res, session);
						break;

					case R_S_CDS :
						procReqSwift100CDS(mc, msgUser, req, res, session);
						break;
					case R_S_TDS :
						procReqSwift100TDS(mc, msgUser, req, res, session);
						break;
					case R_S_FFS :
						procReqSwift100FFS(mc, msgUser, req, res, session);
						break;
					case R_S_TPS :
						procReqSwift100TPS(mc, msgUser, req, res, session);
						break;
					case R_S_ACC :
						procReqSwift100ACC(mc, msgUser, req, res, session);
						break;
						//Common Options
					case R_BENEFICIARY :
						procReqBene(mc, msgUser, req, res, session);
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
					case R_RENOV_MANT :
						procReqRenovations(mc, msgUser, req, res, session);
						break;
					case R_MONEY :
						procReqCDMoney(mc, msgUser, req, res, session);
						break;
					case R_PAYMENT_PLAN :
						procReqMaintPay(mc, msgUser, req, res, session);
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
					case A_RENOV_MANT :
						procActionRenovations(mc, msgUser, req, res, session);
						break;
					case A_MONEY :
						procActionCDMoney(mc, msgUser, req, res, session);
						break;
					case A_PAYMENT_PLAN :
						procActionMaintPay(mc, msgUser, req, res, session);
						break;

						// END Entering

					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + socketNumber;
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				return;
			} finally {
				s.close();
			}
		} catch (Exception e) {
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
			} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
					
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_cds.jsp", req, res);
					} catch (Exception e) {
						
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
			//xxxxx("EDL013111 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
					
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_ffs.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_tps.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			} 
			} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterTDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_tds.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
	}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());
				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_blk.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			} 
			} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
			} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;
			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setCusName(msgDeal.getE11CUSNA1());
			userPO.setProdCode(msgDeal.getE11DEAPRO());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setCurrency(msgDeal.getE11DEACCY());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("CDS");
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_cds.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
			} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;

			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setCusName(msgDeal.getE11CUSNA1());
			userPO.setProdCode(msgDeal.getE11DEAPRO());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setCurrency(msgDeal.getE11DEACCY());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("TPS");
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_tps_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_tps.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
				} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;

			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setCusName(msgDeal.getE11CUSNA1());
			userPO.setProdCode(msgDeal.getE11DEAPRO());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setCurrency(msgDeal.getE11DEACCY());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("FFS");
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_ffs.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionTDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
			} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;

			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setCusName(msgDeal.getE11CUSNA1());
			userPO.setProdCode(msgDeal.getE11DEAPRO());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setCurrency(msgDeal.getE11DEACCY());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("TDS");
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_tds.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
			} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;

			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setCusName(msgDeal.getE11CUSNA1());
			userPO.setProdCode(msgDeal.getE11DEAPRO());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setCurrency(msgDeal.getE11DEACCY());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("MMP");
			userPO.setHeader8(getFullTime());
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(
						LangPath + "EDL0130B_fe_blk_confirm.jsp",
						req,
						res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_blk.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) mc.getMessageRecord("EDL013111");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL0131");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD(opCode);
			msgDeal.setH11FLGWK2("2");

			try {
				if (req.getParameter("E01DLSREF") != null)
					msgDeal.setE11DEAREF(req.getParameter("E01DLSREF"));
			} catch (Exception e) {
				msgDeal.setE11DEAREF("0");
			}
			try {
				if (req.getParameter("E01DLSDID") != null)
					msgDeal.setE11DLSUSR(req.getParameter("E01DLSDID"));
			} catch (Exception e) {
				msgDeal.setE11DLSUSR("");
			}

			msgDeal.send();
			msgDeal.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013111")) {
				try {
					msgDeal = new EDL013111Message();
				} catch (Exception ex) {
				}

				msgDeal = (EDL013111Message) newmessage;

				userPO.setIdentifier(msgDeal.getE11DEAACC());
				userPO.setHeader16(msgDeal.getE11DEAPVI());

				ses.setAttribute("error", msgError);
				ses.setAttribute("deal", msgDeal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "EDL0130B_fe_acc.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionCDSMod(
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
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_cds_confirm.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procActionTDSMod(
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
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_tds_confirm.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procActionFFSMod(
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
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_ffs_confirm.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procActionTPSMod(
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
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_tps_confirm.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procActionACCMod(
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
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_acc_confirm.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procActionAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL013111Message msgDeal = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDeal = (EDL013111Message) ses.getAttribute("deal");
			msgDeal.setH11USERID(user.getH01USR());
			msgDeal.setH11PROGRM("EDL013111");
			msgDeal.setH11TIMSYS(getTimeStamp());
			msgDeal.setH11SCRCOD("01");
			msgDeal.setH11OPECOD("0005");

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgDeal = new EDL013111Message();
			} catch (Exception ex) {
			}

			msgDeal = (EDL013111Message) newmessage;

			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setCusNum(msgDeal.getE11DEACUN());
			userPO.setBank(msgDeal.getE11DEABNK());
			userPO.setHeader1(msgDeal.getE11DEAOAM());
			userPO.setHeader2("1");
			userPO.setHeader3("ACC");
			userPO.setHeader16(msgDeal.getE11DEAPVI());

			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_acc_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "EDL0130B_fe_acc.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqEnterCDS(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO =
			(datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procReqEnterTDS(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO =
			(datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procReqEnterFFS(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO =
			(datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procReqEnterTPS(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO =
			(datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_tps_mod.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procReqEnterACC(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO =
			(datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
		}

		try {
			callPage(LangPath + "EDL0130B_fe_acc_mod.jsp", req, res);
		} catch (Exception e) {
		}

	}

	protected void procReqSwift100CDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try {
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE01SWFACC("0");
			}

			try {
				msgSwift.setE01SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE01SWFBNK("0");
			}

			try {
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			try {
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001")) {
				try {
					msgSwift = new ESWF10001Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF100_fe_cd_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_cds_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift100TDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try {
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE01SWFACC("0");
			}

			try {
				msgSwift.setE01SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE01SWFBNK("0");
			}

			try {
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			try {
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001")) {
				try {
					msgSwift = new ESWF10001Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF100_fe_td_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_tds_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift100FFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try {
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE01SWFACC("0");
			}

			try {
				msgSwift.setE01SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE01SWFBNK("0");
			}

			try {
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			try {
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001")) {
				try {
					msgSwift = new ESWF10001Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(LangPath + "ESWF100_ff_format.jsp", req, res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_ffs_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift100TPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try {
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE01SWFACC("0");
			}

			try {
				msgSwift.setE01SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE01SWFBNK("0");
			}

			try {
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			try {
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001")) {
				try {
					msgSwift = new ESWF10001Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF100_fe_tp_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_tps_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift100ACC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try {
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE01SWFACC("0");
			}

			try {
				msgSwift.setE01SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE01SWFBNK("0");
			}

			try {
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			try {
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			} catch (Exception e) {
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001")) {
				try {
					msgSwift = new ESWF10001Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF100_fe_ac_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_acc_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320CDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try {
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE02SWFACC("0");
			}

			try {
				msgSwift.setE02SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE02SWFBNK("0");
			}

			try {
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002")) {
				try {
					msgSwift = new ESWF10002Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10002Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF320_fe_cd_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_cds_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320TDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try {
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE02SWFACC("0");
			}

			try {
				msgSwift.setE02SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE02SWFBNK("0");
			}

			try {
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002")) {
				try {
					msgSwift = new ESWF10002Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10002Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF320_fe_td_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_tds_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320FFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try {
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE02SWFACC("0");
			}

			try {
				msgSwift.setE02SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE02SWFBNK("0");
			}

			try {
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002")) {
				try {
					msgSwift = new ESWF10002Message();
				} catch (Exception ex) {
				}

				msgSwift = (ESWF10002Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF320_fe_ff_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_ffs_mod.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320ACC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try {
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE02SWFACC("0");
			}

			try {
				msgSwift.setE02SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE02SWFBNK("0");
			}

			try {
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002")) {
				try {
					msgSwift = new ESWF10002Message();
				} catch (Exception ex) {
					
				}

				msgSwift = (ESWF10002Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF320_fe_ac_format.jsp",
							req,
							res);
					} catch (Exception e) {
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_acc_mod.jsp",
							req,
							res);
					} catch (Exception e) {
						
					}

				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320TPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try {
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try {
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgSwift.setE02SWFACC("0");
			}

			try {
				msgSwift.setE02SWFBNK(userPO.getBank());
			} catch (Exception e) {
				msgSwift.setE02SWFBNK("0");
			}

			try {
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002")) {
				try {
					msgSwift = new ESWF10002Message();
				} catch (Exception ex) {
					
				}

				msgSwift = (ESWF10002Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						callPage(
							LangPath + "ESWF320_fe_tp_format.jsp",
							req,
							res);
					} catch (Exception e) {
						
					}

				} else { // There are errors
					try {
						callPage(
							LangPath + "EDL0130B_fe_tps_mod.jsp",
							req,
							res);
					} catch (Exception e) {
						
					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSwift100CDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opeCode = "";

		if (req.getParameter("CODOPT").equals("V")) {
			opeCode = "0006";
		} else
			opeCode = "0005";

		// Send Initial data
		try {
			
			msgSwift = (ESWF10001Message) ses.getAttribute("swift");
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

			//msgSwift.send();
			mc.sendMessage(msgSwift);
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgSwift = new ESWF10001Message();
				
			} catch (Exception ex) {
				
			}

			msgSwift = (ESWF10001Message) newmessage;

			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
				} catch (Exception e) {
					
				}
			} else { // There are errors
				try {
					callPage(LangPath + "ESWF100_fe_cd_format.jsp", req, res);
				} catch (Exception e) {
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSwift100TDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opeCode = "";

		if (req.getParameter("CODOPT").equals("V")) {
			opeCode = "0006";
		} else
			opeCode = "0005";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) ses.getAttribute("swift");
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

			//msgSwift.send();
			mc.sendMessage(msgSwift);
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgSwift = new ESWF10001Message();
			} catch (Exception ex) {
				
			}

			msgSwift = (ESWF10001Message) newmessage;

			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
				} catch (Exception e) {
					
				}
			} else { // There are errors
				try {
					callPage(LangPath + "ESWF100_fe_td_format.jsp", req, res);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSwift100FFS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opeCode = "";

		if (req.getParameter("CODOPT").equals("V")) {
			opeCode = "0006";
		} else
			opeCode = "0005";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) ses.getAttribute("swift");
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

			//msgSwift.send();
			mc.sendMessage(msgSwift);
			msgSwift.destroy();
			} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgSwift = new ESWF10001Message();
			} catch (Exception ex) {
				
			}

			msgSwift = (ESWF10001Message) newmessage;
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
				} catch (Exception e) {
				}
			} else { // There are errors
				try {
					callPage(LangPath + "ESWF100_fe_ff_format.jsp", req, res);
				} catch (Exception e) {
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSwift100ACC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opeCode = "";

		if (req.getParameter("CODOPT").equals("V")) {
			opeCode = "0006";
		} else
			opeCode = "0005";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) ses.getAttribute("swift");
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

			//msgSwift.send();
			mc.sendMessage(msgSwift);
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgSwift = new ESWF10001Message();
				
			} catch (Exception ex) {
				
			}

			msgSwift = (ESWF10001Message) newmessage;

			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					
					callPage(LangPath + "EDL0130B_fe_acc_mod.jsp", req, res);
				} catch (Exception e) {
					
				}
			} else { // There are errors
				try {
					callPage(LangPath + "ESWF100_fe_ac_format.jsp", req, res);
				} catch (Exception e) {
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSwift100TPS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opeCode = "";

		if (req.getParameter("CODOPT").equals("V")) {
			opeCode = "0006";
		} else
			opeCode = "0005";

		// Send Initial data
		try {
			msgSwift = (ESWF10001Message) ses.getAttribute("swift");
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

			//msgSwift.send();
			mc.sendMessage(msgSwift);
			msgSwift.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				
				showERROR(msgError);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			try {
				msgSwift = new ESWF10001Message();
				
			} catch (Exception ex) {
				
			}

			msgSwift = (ESWF10001Message) newmessage;

			
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					callPage(LangPath + "EDL0130B_fe_tps_mod.jsp", req, res);
				} catch (Exception e) {
					
				}
			} else { // There are errors
				try {
					callPage(LangPath + "ESWF100_fe_tp_format.jsp", req, res);
				} catch (Exception e) {
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void showERROR(ELEERRMessage m) {
		if (logType != NONE) {
		 	
		}
	}
	//
	// Beneficiaries
	//
	protected void procReqBene(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
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
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("bene", msgBene);

					if (IsNotError) { // There are no errors 
						try {
							flexLog("About to call Page: " + LangPath + "EDL0130B_cd_bene.jsp");
							callPage(LangPath + "EDL0130B_cd_bene.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_cds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_ffs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_acs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_acs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
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
		//
		// Special Instructions
		//
		protected void procReqEspInst(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
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
				msgCD.setH05WK1("M");
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
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdInst", msgCD);

					if (IsNotError) { // There are no errors 
						try {
							flexLog("About to call Page: " + LangPath + "EDL0130B_cd_special_inst.jsp");
							callPage(LangPath + "EDL0130B_cd_special_inst.jsp",	req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_cds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_ffs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_acs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_acs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", 	req, res);
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
		//
		// Exchange Rate
		//
		protected void procReqExchangeRate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
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
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdRate", msgCD);

					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EDL0130B_cd_xchg_rate.jsp");
							callPage(LangPath + "EDL0130B_cd_xchg_rate.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_cds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_ffs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_acs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_acs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(	LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
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
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDL0130B_cd_renov_options.jsp");
							callPage(
								LangPath + "EDL0130B_cd_renov_options.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_cds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_cds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_ffs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_ffs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_acs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_acs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
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

							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0130B_cd_codes.jsp");
							callPage(LangPath + "EDL0130B_cd_codes.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_cds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_cds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_ffs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_ffs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_acs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_acs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
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
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdTit", msgCD);

					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page3: " + LangPath + "EDL0130B_cd_tit.jsp");
							callPage(LangPath + "EDL0130B_cd_tit.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_cds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_cds_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_ffs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_ffs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_acs_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_acs_mod.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog("About to call Page: " + LangPath + "EDL0130B_fe_tds_mod.jsp");
								callPage(LangPath + "EDL0130B_fe_tds_mod.jsp", req, res);
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
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_cds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_cds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_ffs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_ffs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_acs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_acs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0130B_cd_money.jsp");
							callPage(LangPath + "EDL0130B_cd_money.jsp", req, res);
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
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDL0130B_cd_money.jsp");
							callPage(LangPath + "EDL0130B_cd_money.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						if (userPO.getHeader3().equals("CDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_cds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_cds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TDS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("FFS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_ffs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_ffs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("ACS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_acs_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_acs_mod.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getHeader3().equals("TPS")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_fe_tds_mod.jsp");
								callPage(
									LangPath + "EDL0130B_fe_tds_mod.jsp",
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

						MessageRecord newmessage = null;
						EDL013004Message msgPmnt = null;
						ELEERRMessage msgError = null;
						UserPos userPO = null;
						boolean IsNotError = false;

						//Sending EDL013004
						try {
							userPO =
								(datapro.eibs.beans.UserPos) ses.getAttribute(
									"userPO");

							msgPmnt =
								(EDL013004Message) mc.getMessageRecord("EDL013004");

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
									msgPmnt.setE04DLPPNU(pmntList.getRecord(0));
									//Quota
								} catch (Exception e) {
									flexLog("E04DLPPNU");
								}
								try {
									msgPmnt.setE04DLPPD1(pmntList.getRecord(1));
									//Date
								} catch (Exception e) {
									flexLog("E04DLPPD1");
								}

								try {
									msgPmnt.setE04DLPPD2(pmntList.getRecord(2));
									//Date
								} catch (Exception e) {
									flexLog("E04DLPPD1");
								}

								try {
									msgPmnt.setE04DLPPD3(pmntList.getRecord(3));
									//Date
								} catch (Exception e) {
									flexLog("E04DLPPD1");
								}

								try {
									msgPmnt.setE04DLPPRI(pmntList.getRecord(4));
									//Principal
								} catch (Exception e) {
									flexLog("E04DLPPRI");
								}
								try {
									msgPmnt.setE04DLPINT(pmntList.getRecord(5));
									//Interest
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
									flexLog(
										"Message "
											+ newmessage.getFormatName()
											+ " received.");
							} catch (Exception e) {
								e.printStackTrace();
								flexLog("Error: " + e);
								throw new RuntimeException("Socket Communication Error");
							}

							// Receive Data
							try {
								newmessage = mc.receiveMessage();

								if (newmessage
									.getFormatName()
									.equals("EDL013004")) {
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
											myRow[0] = msgPmnt.getE04DLPPNU();
											// Quote Number
											myRow[1] = msgPmnt.getE04DLPPD1();
											myRow[2] = msgPmnt.getE04DLPPD2();
											myRow[3] = msgPmnt.getE04DLPPD3();
											// Date
											myRow[4] = msgPmnt.getE04DLPPRI();
											// Principal
											myRow[5] = msgPmnt.getE04DLPINT();
											// Interest

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
											out.println(
												"<SCRIPT LANGUAGE=\"JavaScript\">");
											out.println(
												"		top.opener.document.forms[0].submit();");
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
											flexLog(
												"About to call Page: "
													+ LangPath
													+ "EDL0130B_cd_pay_pln_det.jsp");
											callPage(
												LangPath
													+ "EDL0130B_cd_pay_pln_det.jsp",
												req,
												res);
										} catch (Exception e) {
											flexLog("Exception calling page " + e);
										}
									}

								} else
									flexLog(
										"Message "
											+ newmessage.getFormatName()
											+ " received.");

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
								pymNumLast =
									Integer.parseInt(pmntList.getRecord(0)) + 1;
							}
							int number =
								Integer.parseInt(req.getParameter("NUMBER"));
							int month = Integer.parseInt(req.getParameter("DATE1"));
							int day = Integer.parseInt(req.getParameter("DATE2"));
							int year =
								2000 + Integer.parseInt(req.getParameter("DATE3"));
							int feq =
								Integer.parseInt(req.getParameter("FREQUENCY"));
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
									myRow[1] =
										(myDate.get(Calendar.MONTH) + 1) + "";
									myRow[2] =
										myDate.get(Calendar.DAY_OF_MONTH) + "";
									String y = myDate.get(Calendar.YEAR) + "";
									myRow[3] = y.substring(2);
								} else if (user.getE01DTF().equals("DMY")) {
									myRow[1] =
										myDate.get(Calendar.DAY_OF_MONTH) + "";
									myRow[2] =
										(myDate.get(Calendar.MONTH) + 1) + "";
									String y = myDate.get(Calendar.YEAR) + "";
									myRow[3] = y.substring(2);
								} else {
									String y = myDate.get(Calendar.YEAR) + "";
									myRow[1] = y.substring(2);
									myRow[2] =
										(myDate.get(Calendar.MONTH) + 1) + "";
									myRow[3] =
										myDate.get(Calendar.DAY_OF_MONTH) + "";
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
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0130B_cd_pay_pln_det.jsp");
							callPage(
								LangPath + "EDL0130B_cd_pay_pln_det.jsp",
								req,
								res);
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
								"About to call Page: "
									+ LangPath
									+ "EDL0130B_cd_pay_pln_det.jsp?ROW="
									+ row);
							res.sendRedirect(super.srctx + 
								LangPath
									+ "EDL0130B_cd_pay_pln_det.jsp?ROW="
									+ row);
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
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0130B_cd_pay_pln_det.jsp");
							callPage(
								LangPath + "EDL0130B_cd_pay_pln_det.jsp",
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
									+ "error_viewer.jsp");
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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}

						} else { // There are errors
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_cd_bene.jsp");
								callPage(LangPath + "EDL0130B_cd_bene.jsp", req, res);
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
					msgCD.setH05WK1("M");

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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else { // There are errors
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_cd_special_inst.jsp");
								callPage(
									LangPath + "EDL0130B_cd_special_inst.jsp",
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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else { // There are errors
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130M_cd_xchg_rate.jsp");
								callPage(
									LangPath + "EDL0130M_cd_xchg_rate.jsp",
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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else { // There are errors
							try {
								flexLog(
									"About to call Page2: "
										+ LangPath
										+ "EDL0130M_cd_renov_options.jsp");
								callPage(
									LangPath + "EDL0130M_cd_renov_options.jsp",
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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else { // There are errors
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130B_cd_codes.jsp");
								callPage(LangPath + "EDL0130B_cd_codes.jsp", req, res);
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
							if (userPO.getHeader3().equals("CDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_cds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_cds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TDS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("FFS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_ffs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_ffs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("ACS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_acs_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_acs_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							} else if (userPO.getHeader3().equals("TPS")) {
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0130B_fe_tds_mod.jsp");
									callPage(
										LangPath + "EDL0130B_fe_tds_mod.jsp",
										req,
										res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else { // There are errors
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0130M_cd_tit.jsp");
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


	
}