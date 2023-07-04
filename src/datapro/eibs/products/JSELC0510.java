/*
 * Created on Apr 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * Comercial Letter Of Credit Maintenance
 */
public class JSELC0510 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC051001Message msg01 = null;
	ELC051002Message msg02 = null;
	ESD000002Message msg03 = null;
	ELC051004Message msg04 = null;
	ESD000005Message msg05 = null;
	ELC051006Message msg06 = null;
	ELC051005Message msg07 = null;
	ELC051003Message msg08 = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	private static final int R_NEW = 1;
	private static final int A_NEW = 2;
	private static final int R_OPENING_MAINT = 3;
	private static final int A_OPENING_MAINT = 4;
	private static final int R_DETAILS_MAINT = 5;
	private static final int A_DETAILS_MAINT = 6;
	private static final int R_COMMISSI = 7;
	private static final int A_COMMISSI = 8;

	private static final int R_SPECIAL_INST = 103;
	private static final int A_SPECIAL_INST = 104;
	private static final int R_TRANSFER_LIST = 105;
	private static final int R_TRANSFER = 106;
	private static final int A_TRANSFER = 107;
	private static final int R_TRANSFER_COMMISSION = 108;
	private static final int A_TRANSFER_COMMISSION = 109;
	private static final int R_ENTER_MAINT = 200;
	private static final int R_SPECIAL_CODES_MAINT = 201;
	private static final int A_SPECIAL_CODES_MAINT = 202;

	private static final int R_DOCUMENTS_REQ = 601;
	private static final int A_DOCUMENTS_REQ = 602;
	
	public JSELC0510() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0510");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		if (session == null) {
			try {
				res.setContentType("text/html");
				super.printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {
			int screen = -1;

			user = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			// Here we should get the path from the user profile
			LangPath = super.rootPath + user.getE01LAN() + "/";
			
			userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
			
			try {
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
						
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
					flexLog("Screen  Number: " + screen);
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}
				
				String PageToCall = "";

				switch (screen) {
					case R_ENTER_MAINT :
						procReqEnterMaint(req, res, screen);
						break;
						// New
					case R_NEW :
						procReqNew(req, res, screen);
						break;
					case A_NEW :
						procActionNew(req, res, screen);
						break;
						// OPENING MAINTENANCE
					case R_OPENING_MAINT :
						procReqBasicInformation(req, res, screen);
						break;
					case A_OPENING_MAINT :
						procActionOpening(req, res, screen);
						break;
						// DETAILS MAINTENANCE
					case R_DETAILS_MAINT :
						procReqCreditCondition(req, res, screen);
						break;
					case A_DETAILS_MAINT :
						proActionDetails(req, res, screen);
						break;
						// COMMISSIONS
					case R_COMMISSI :
						procReqCommission(req, res, screen);
						break;
					case A_COMMISSI :
						procActionCommission(req, res, screen);
						break;
						// SPECIAL INSTRUCTIONS
					case R_SPECIAL_INST :
						procReqSpecialInst(req, res, screen);
						break;
					case A_SPECIAL_INST :
						procActionSpecialInst(req, res, screen);
						break;
						// DOCUMENTS
					case R_DOCUMENTS_REQ :
						reqDocuments(req, res, screen);
						break;
					case A_DOCUMENTS_REQ :
						procDocuments(req, res, screen);
						break;
						// TRANSFER
					case R_TRANSFER_LIST :
						procReqTransferList(req, res, screen);
						break;
					case R_TRANSFER :
						procReqTransfer(req, res, screen);
						break;
					case R_TRANSFER_COMMISSION :
						procReqTransferCommission(req, res, screen);
						break;
					case A_TRANSFER :
					case A_TRANSFER_COMMISSION :
						procActionTransfer(req, res, screen);
						break;
						// SPECIAL CODES
					case R_SPECIAL_CODES_MAINT :
						procReqSpecialCodes(req, res, screen);
						break;
					case A_SPECIAL_CODES_MAINT :
						procActionSpecialCodes(req, res, screen);
						break;
						// this will handle all requests made from option menu
					case 50 :
						procRecOptionMenu(req, res, screen);
						break;
					case 2000 :
						procActionOptionMenu(req, res, screen);
						break;
					case 1111 :
						procRecClausula(req, res, screen);
						break;
					default :
						PageToCall = "MISC_not_available.jsp";
						callPage(PageToCall, req, res);
						break;
				}

			} catch (IOException e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				s.close();
				flexLog("Socket used by JSELC0510 closed.");
			}
		}	
	}
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqTransferCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg07 = (ELC051005Message) session.getAttribute("msgLC");
			msg07 = (ELC051005Message) msgHandle.initMessage(msg07, user.getH01USR(), "0008");
			initTransaction(String.valueOf(screen), "");
			msg07.setH05SCRCOD("01");
			msg07.setE05LCMACC(userPO.getIdentifier());
			msg07.setE05LCTNUM(userPO.getAccNum());
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC051005Message) msgHandle.receiveMessage();
			putDataInSession(session, msg07);
			if (isNotError) {
				PageToCall = "ELC0510_lc_transfer_commissi.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procRecClausula(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg08 = (ELC051003Message) msgHandle.initMessage("ELC051003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg08.setH03SCRCOD("01");
			msg08.setE03LCDACC(userPO.getIdentifier());
			msg08.setE03LCDPRO(userPO.getHeader1());
			msg08.setE03LCDBNK(userPO.getBank());
			try {
				msg08.setE03LCDFCD(req.getParameter("E01CLSCDE"));
			} catch (Exception e) {
			}
			try {
				msg08.setE03CLSTXN(req.getParameter("E01CLSTXN"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg08);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg08 = (ELC051003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg08);
			if (isNotError) {
				PageToCall = "ELC0510_lc_field_info_maint.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionOptionMenu(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg08 = (ELC051003Message) session.getAttribute("msgLC");
			msg08 = (ELC051003Message) msgHandle.initMessage(msg08, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg08.setH03SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg08);
			msgHandle.sendMessage(msg08);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg08 = (ELC051003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg08);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {
				PageToCall = "ELC0510_lc_field_info_maint.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procRecOptionMenu(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg08 = (ELC051003Message) msgHandle.initMessage("ELC051003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg08.setH03SCRCOD("01");
			msg08.setE03LCDACC(userPO.getIdentifier());
			msg08.setE03LCDPRO(userPO.getHeader1());
			msg08.setE03LCDBNK(userPO.getBank());
			try {
				msg08.setE03LCDFCD(req.getParameter("CODE"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg08);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg08 = (ELC051003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg08);
			if (isNotError) {
				PageToCall = "ELC0510_lc_field_info_maint.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionSpecialCodes(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg03 = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg03.setH02USR(user.getH01USR());
			msg03.setH02PGM("ELC0400");
			msg03.setH02TIM(getTimeStamp());
			msg03.setH02SCR("01");
			msg03.setH02OPE("0005");
			msgHandle.setFieldsFromPage(req, msg03);
			msgHandle.sendMessage(msg03);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg03 = (ESD000002Message) msgHandle.receiveMessage();
			putDataInSession(session, msg03);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {	
				PageToCall = "ESD0000_lc_codes_maint.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqSpecialCodes(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg03 = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg03.setH02USR(user.getH01USR());
			msg03.setH02PGM("ELC0500");
			msg03.setH02TIM(getTimeStamp());
			msg03.setH02SCR("01");
			msg03.setH02OPE("0001");
			msg03.setE02ACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg03);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg03 = (ESD000002Message) msgHandle.receiveMessage();
			putDataInSession(session, msg03);
			if (isNotError) {
				PageToCall = "ESD0000_lc_codes_maint.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionTransfer(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg07 = (ELC051005Message) session.getAttribute("msgLC");
			String action = "";
			if (msg07.getH05OPECOD().equals("0001")) {
				action = "NEW";
			} else {
				action = "MAINTENANCE";
			}
			String opcode = "0005";
			if (screen == A_TRANSFER_COMMISSION) opcode = "0016";
			
			msg07 = (ELC051005Message) msgHandle.initMessage(msg07, user.getH01USR(), opcode);
			initTransaction(String.valueOf(screen), action);
			msg07.setE05LCMACC(userPO.getIdentifier());
			msgHandle.setFieldsFromPage(req, msg07);
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC051005Message) msgHandle.receiveMessage();
			putDataInSession(session, msg07);
			if (isNotError) {
				procReqTransferList(req, res, screen);
			} else {
				PageToCall = "ELC0510_lc_transfer.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqTransfer(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			String opCode = "";
			try {
				opCode = req.getParameter("opCode");
				if (opCode == null) opCode = "0002";
			} catch (Exception e) {
				opCode = "0002";
			}
				
			msg07 = (ELC051005Message) msgHandle.initMessage("ELC051005", user.getH01USR(), opCode);
			String action = "";
			if (opCode.equals("0001")) {
				action = "NEW";
			} else {
				action = "MAINTENANCE";
			}
			initTransaction(String.valueOf(screen), action);
			msg07.setH05SCRCOD("01");
			msg07.setE05LCMACC(userPO.getIdentifier());
			try {
				msg07.setE05LCTNUM(req.getParameter("E05LCTNUM"));
				userPO.setAccNum(msg07.getE05LCTNUM());
			} catch (Exception e) {
				msg07.setE05LCTNUM(userPO.getAccNum());
			}
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC051005Message) msgHandle.receiveMessage();
			putDataInSession(session, msg07);
			if (isNotError) {
				PageToCall = "ELC0510_lc_transfer.jsp";
			} else {
				PageToCall = "ELC0510_lc_transfer_list.jsp";
			}
			callPage(PageToCall, req, res);
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqTransferList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg07 = (ELC051005Message) msgHandle.initMessage("ELC051005", user.getH01USR(), "0015");
			initTransaction(String.valueOf(screen), "");
			msg07.setH05SCRCOD("01");
			try {
				msg07.setE05LCMACC(req.getParameter("E05LCMACC"));
			} catch (Exception e) {
			}
			userPO.setIdentifier(msg07.getE05LCMACC());
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				jbList = msgHandle.receiveMessageList("H05FLGMAS", JSEIBSProp.getMaxIterations());
			}
			putDataInSession(session, msg07);
			if (isNotError) {
				PageToCall = "ELC0510_lc_transfer_list.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procDocuments(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg06 = (ELC051006Message) msgHandle.initMessage("ELC051006", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg06.setE06LCMACC(userPO.getIdentifier());
			msgHandle.setFieldsFromPage(req, msg06);
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC051006Message) msgHandle.receiveMessage();
			putDataInSession(session, msg06);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {
				PageToCall = "ELC0510_lc_documents.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void reqDocuments(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg06 = (ELC051006Message) msgHandle.initMessage("ELC051006", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg06.setE06LCMACC(userPO.getIdentifier());
			try {
				msg06.setH06SCRCOD(req.getParameter("H06SCRCOD"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC051006Message) msgHandle.receiveMessage();
			putDataInSession(session, msg06);
			if (isNotError) {
				if (msg06.getH06SCRCOD().equals("A")) {
					PageToCall = "ELC0525_lc_documents.jsp";
				} else {
					PageToCall = "ELC0510_lc_documents.jsp";				
				}
			} else {
				if (msg06.getH06SCRCOD().equals("A")) {
					PageToCall = "ELC0525_lc_documents.jsp";
				} else {
					PageToCall = "ELC0510_lc_documents.jsp";				
				}
			}
			callPage(PageToCall, req, res);
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionSpecialInst(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg05 = (ESD000005Message) session.getAttribute("msgLC");
			msg05 = (ESD000005Message) msgHandle.initMessage(msg05, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg05.setH05USR(user.getH01USR());
			msg05.setH05PGM("ELC0510");
			msg05.setH05TIM(getTimeStamp());
			msg05.setH05SCR("01");
			msg05.setH05OPE("0005");
			msg05.setE05ACC(userPO.getIdentifier());
			msg05.setE05TYP("3");
			msgHandle.setFieldsFromPage(req, msg05);
			msgHandle.sendMessage(msg05);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg05 = (ESD000005Message) msgHandle.receiveMessage();
			putDataInSession(session, msg05);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {
				PageToCall = "ELC0510_lc_special_inst.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqSpecialInst(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg05 = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg05.setH05USR(user.getH01USR());
			msg05.setH05PGM("ELC0510");
			msg05.setH05TIM(getTimeStamp());
			msg05.setH05SCR("01");
			msg05.setH05OPE("0002");
			msg05.setE05ACC(userPO.getIdentifier());
			msg05.setE05TYP("3");
			msgHandle.sendMessage(msg05);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg05 = (ESD000005Message) msgHandle.receiveMessage();
			putDataInSession(session, msg05);
			if (isNotError) {
				PageToCall = "ELC0510_lc_special_inst.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC051004Message) session.getAttribute("msgLC");
			msg04 = (ELC051004Message) msgHandle.initMessage(msg04, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MANINTENANCE");
			msg04.setH04SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg04);
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC051004Message) msgHandle.receiveMessage();
			putDataInSession(session, msg04);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {
				PageToCall = "ELC0510_lc_commissi.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC051004Message) msgHandle.initMessage("ELC051004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MANINTENANCE");
			msg04.setH04SCRCOD("01");
			msg04.setE04LCMBNK(userPO.getBank());
			msg04.setE04LCMACC(userPO.getIdentifier());
			msg04.setE04LCMPRO(userPO.getHeader1());
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC051004Message) msgHandle.receiveMessage();
			putDataInSession(session, msg04);
			if (isNotError) {
				PageToCall = "ELC0510_lc_commissi.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void proActionDetails(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0510_lc_details.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC051002Message) session.getAttribute("msgLC");
			msg02 = (ELC051002Message) msgHandle.initMessage(msg02, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg02.setH02SCRCOD("01");
			if(req.getParameter("H02FLGWK1") == null){
				msg02.setH02FLGWK1("");
			}
			msgHandle.setFieldsFromPage(req, msg02);
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC051002Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg02.getE02LCMACC());
			userPO.setHeader11(msg02.getE02LCMORF());
			userPO.setHeader12(msg02.getE02LCMTRF());
			userPO.setHeader13(msg02.getE02LCMPRO());
			userPO.setHeader14(msg02.getE02DSCPRO());
			userPO.setHeader18("MENU");
			putDataInSession(session, msg02);
			if (isNotError && msg02.getH02FLGWK1().trim().equals("Y")) {
				PageToCall = "ELC0510_lc_enter_maint.jsp";
			}
			callPage(PageToCall, req, res);
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqCreditCondition(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC051002Message) msgHandle.initMessage("ELC051002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg02.setH02SCRCOD("01");
			msg02.setE02LCMAMF(userPO.getHeader7());
			try {
				msg02.setE02LCMACC(req.getParameter("E02LCMACC").toUpperCase());
			} catch (Exception e) {
				msg02.setE02LCMACC(userPO.getIdentifier());
			}
			userPO.setIdentifier(msg02.getE02LCMACC());
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC051002Message) msgHandle.receiveMessage();
			userPO.setHeader1(msg02.getE02LCMPRO());
			userPO.setIdentifier(msg02.getE02LCMACC());
			userPO.setBank(msg02.getE02LCMBNK());
			userPO.setHeader2(msg02.getE02DSCPRO());
			userPO.setHeader3(msg02.getE02LCMTRF());
			userPO.setAccNum(msg02.getE02LCMACC());
			putDataInSession(session, msg02);
			if (isNotError) {
				PageToCall = "ELC0510_lc_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				PageToCall = "ELC0510_lc_enter_maint.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionOpening(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC051001Message) msgHandle.initMessage("ELC051001", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg01.setH01SCRCOD("01");
			msg01.setE01LCMAMF(userPO.getHeader7());
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC051001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			userPO.setHeader11(msg01.getE01LCMORF());
			userPO.setHeader12(msg01.getE01LCMTRF());
			userPO.setHeader13(msg01.getE01LCMPRO());
			userPO.setHeader14(msg01.getE01DSCPRO());
			putDataInSession(session, msg01);
			if (isNotError) {
				procReqCreditCondition(req, res, screen);
			} else {
				if (msg01.getE01LCMTYP().equals("O")) {
					PageToCall = "ELC0510_lc_opening_import.jsp";
				} else {
					PageToCall = "ELC0510_lc_opening_export.jsp";
				}		
				callPage(PageToCall, req, res);
			}
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqBasicInformation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC051001Message) msgHandle.initMessage("ELC051001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01LCMACC(req.getParameter("E01LCMACC").toUpperCase());
			} catch (Exception e) {
				msg01.setE01LCMACC(userPO.getIdentifier());
			}
			try {
				msg01.setE01LCMCUN("E01LCMCUN");
			} catch (Exception e) {
			}
			try {
				msg01.setE01LCMSWF(req.getParameter("E01LCMSWF").toUpperCase());
			} catch (Exception e) {
			}
			try {
				msg01.setE01LCMAMF(req.getParameter("E01LCMAMF"));
			} catch (Exception e) {
				msg01.setE01LCMAMF(userPO.getHeader7());
			}
			userPO.setHeader7(msg01.getE01LCMAMF());
			
			try {
				msg01.setH01FLGMAS(req.getParameter("H01FLGMAS"));
			} catch (Exception e) {
			}
			userPO.setIdentifier(msg01.getE01LCMACC());
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC051001Message) msgHandle.receiveMessage();
			userPO.setHeader1(msg01.getE01LCMPRO());
			userPO.setIdentifier(msg01.getE01LCMACC());
			userPO.setBank(msg01.getE01LCMBNK());
			userPO.setHeader2(msg01.getE01LCMORF());
			userPO.setHeader3(msg01.getE01LCMTRF());
			userPO.setHeader14(msg01.getE01DSCPRO());
			userPO.setCusType(msg01.getH01FLGWK3());
			putDataInSession(session, msg01);
			if (isNotError) {
				if (msg01.getE01LCMOPT().equals("N")) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSELC0500?SCREEN=3");
				} else {
					if (msg01.getE01LCMTYP().equals("O")) {
						PageToCall = "ELC0510_lc_opening_import.jsp";
					} else {
						PageToCall = "ELC0510_lc_opening_export.jsp";
					}
					callPage(PageToCall, req, res);
				}			
			} else {
				PageToCall = "ELC0510_lc_enter_maint.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC051001Message) session.getAttribute("msgLC");
			msg01 = (ELC051001Message) msgHandle.initMessage(msg01, user.getH01USR(), "0005");
			userPO.setCusNum(req.getParameter("E01LCMACC"));
			initTransaction(String.valueOf(screen), "NEW");
			msg01.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC051001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			userPO.setBank(msg01.getE01LCMBNK());
			userPO.setHeader1(msg01.getE01LCMPRO());
			userPO.setHeader2(msg01.getE01DSCPRO());
			userPO.setAccNum(msg01.getE01LCMACC().trim());
			putDataInSession(session, msg01);
			if (isNotError) {
				PageToCall = "ELC0510_lc_details.jsp";
			} else {
				if (msg01.getE01LCMTYP().equals("O")) { //Import
					PageToCall = "ELC0510_lc_opening_import.jsp";
				} else { //Export
					PageToCall = "ELC0510_lc_opening_export.jsp";
				}		
			}		
			callPage(PageToCall, req, res);
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ESD0711_products_detail.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC051001Message) msgHandle.initMessage("ELC051001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "NEW");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01LCMPRO(req.getParameter("E01LCMPRO"));
			} catch (Exception e) {
			}
			try {
				msg01.setE01LCMATY(req.getParameter("typecode"));
			} catch (Exception e) {
			}
			try {
				msg01.setE01LCMBNK(req.getParameter("bank"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC051001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg01);
			if (isNotError) {
				if (msg01.getE01LCMTYP().equals("O")) { //Import
					PageToCall = "ELC0510_lc_opening_import.jsp";
				} else { //Export
					PageToCall = "ELC0510_lc_opening_export.jsp";
				}
				callPage(PageToCall, req, res);
			} else {
				flexLog("About to call Page4: " + LangPath + PageToCall);
				String firstLink =
					super.webAppPath
						+ LangPath
						+ PageToCall
						+ "?appcode="
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
			}
		
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqEnterMaint(HttpServletRequest req, HttpServletResponse res, int screen) {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0510_lc_enter_maint.jsp";
		initTransaction(String.valueOf(screen), "MAINTENANCE");
		userPO.setHeader18("NO_MENU");
		userPO.setProdCode("CC");
		putDataInSession(session, msg01);
		callPage(PageToCall, req, res);
	}
	

	private void initTransaction(String optMenu, String purpose) {
		try {
			if (!optMenu.equals("")) userPO.setOption(optMenu);
			if (!purpose.equals("")) userPO.setPurpose(purpose);
			userPO.setID(user.getE01INT()); //Country Code
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
		} catch (Exception ex) {
			flexLog("Error getting userPO from session: " + ex);
		}
	}
	
	public void callPage(String page, HttpServletRequest req, HttpServletResponse res) {
		try {
			super.callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e.toString() + e.getMessage());
		}
		return; 
	}
	
	private void putDataInSession(HttpSession session, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute("msgLC", msg);
			if (jbList != null) session.setAttribute("jbList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
