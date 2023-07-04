/*
 * Created on Apr 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSELC0500 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC050001Message msg01 = null;
	ELC050002Message msg02 = null;
	ESD000002Message msg03 = null;
	ELC050004Message msg04 = null;
	ESD000005Message msg05 = null;
	ELC050006Message msg06 = null;
	ELC050003Message msg07 = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	private static final int R_OPENING_NEW = 1;
	private static final int A_OPENING_NEW = 2;
	private static final int R_OPENING_MAINT = 3;
	private static final int A_OPENING_MAINT = 4;
	private static final int R_DETAILS = 5;
	private static final int A_DETAILS = 6;
	private static final int R_COMMISSI = 7;
	private static final int A_COMMISSI = 8;

	private static final int R_ENTER_MAINT = 200;
	private static final int R_SPECIAL_CODES = 101;
	private static final int A_SPECIAL_CODES = 102;
	private static final int R_SPECIAL_CODES_MAINT = 201;
	private static final int A_SPECIAL_CODES_MAINT = 202;
	private static final int R_SPECIAL_INST = 103;
	private static final int A_SPECIAL_INST = 104;

	private static final int R_DOCUMENTS_REQ = 601;
	private static final int A_DOCUMENTS_REQ = 602;
	
	public JSELC0500() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0500");
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
					case R_OPENING_NEW :
						procReqOpeningNew(req, res, screen);
						break;
					case A_OPENING_NEW :
						procActionOpeningNew(req, res, screen);
						break;
					case R_OPENING_MAINT :
						procReqOpeningMaint(req, res, screen);
						break;
					case A_OPENING_MAINT :
						procActionOpeningMaint(req, res, screen);
						break;
					case R_DETAILS :
						procReqDetails(req, res, screen);
						break;
					case A_DETAILS :
						procActionDetails(req, res, screen);
						break;
					case R_COMMISSI :
						procReqCommissi(req, res, screen);
						break;
					case A_COMMISSI :
						proActionCommissi(req, res, screen);
						break;
					case R_SPECIAL_CODES :
					case R_SPECIAL_CODES_MAINT :
						procReqSpecialCodes(req, res, screen);
						break;
					case A_SPECIAL_CODES :
					case A_SPECIAL_CODES_MAINT :
						procActionSpecialCodes(req, res, screen);
						break;
					case R_SPECIAL_INST :
						procReqSpecialInst(req, res, screen);
						break;
					case A_SPECIAL_INST :
						procActionSpecialInst(req, res, screen);
						break;
					case R_DOCUMENTS_REQ :
						reqDocuments(req, res, screen);
						break;
					case A_DOCUMENTS_REQ :
						procDocuments(req, res, screen);
						break;
					case 50 : // this will handle all requests made from option menu
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
				if (s != null) s.close();
				flexLog("Socket used by JSELC0500 closed.");
			}
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
			msg07 = (ELC050003Message) msgHandle.initMessage("ELC050003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msg07.setH03SCRCOD("01");
			msg07.setE03LCDACC(userPO.getIdentifier());
			msg07.setE03LCDPRO(userPO.getHeader1());
			msg07.setE03LCDBNK(userPO.getBank());
			try {
				msg07.setE03LCDFCD(req.getParameter("E01CLSCDE"));
			} catch (Exception e) {
			}
			try {
				msg07.setE03CLSTXN(req.getParameter("E01CLSTXN"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC050003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg07);
			if (isNotError) {
				PageToCall = "ELC0500_lc_field_info.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			msg07 = (ELC050003Message) session.getAttribute("msgLC");
			msg07 = (ELC050003Message) msgHandle.initMessage(msg07, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "");
			msg07.setH03SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg07);
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC050003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg07);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0500_lc_field_info.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			msg07 = (ELC050003Message) msgHandle.initMessage("ELC050003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msg07.setH03SCRCOD("01");
			msg07.setE03LCDACC(userPO.getIdentifier());
			msg07.setE03LCDPRO(userPO.getHeader1());
			msg07.setE03LCDBNK(userPO.getBank());
			try {
				msg07.setE03LCDFCD(req.getParameter("CODE"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg07);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg07 = (ELC050003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg07);
			if (isNotError) {
				PageToCall = "ELC0500_lc_field_info.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			msg06 = (ELC050006Message) msgHandle.initMessage("ELC050006", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "");
			msg06.setE06LCMACC(userPO.getIdentifier());
			msgHandle.setFieldsFromPage(req, msg06);
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC050006Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg06);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0500_lc_documents.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			msg06 = (ELC050006Message) msgHandle.initMessage("ELC050006", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msg06.setE06LCMACC(userPO.getIdentifier());
			try {
				msg06.setH06SCRCOD(req.getParameter("H06SCRCOD"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC050006Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg06);
			if (isNotError) {
				PageToCall = "ELC0500_lc_documents.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			initTransaction(String.valueOf(screen), "");
			msg05.setH05USR(user.getH01USR());
			msg05.setH05PGM("ELC0400");
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
			putDataInSession(session, "msgLC", msg05);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0500_lc_special_inst.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			initTransaction(String.valueOf(screen), "");
			msg05.setH05USR(user.getH01USR());
			msg05.setH05PGM("ELC0400");
			msg05.setH05TIM(getTimeStamp());
			msg05.setH05SCR("01");
			msg05.setH05OPE("0002");
			msg05.setE05ACC(userPO.getIdentifier());
			msg05.setE05TYP("3");
			msgHandle.sendMessage(msg05);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg05 = (ESD000005Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg05);
			if (isNotError) {
				PageToCall = "ELC0500_lc_special_inst.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			initTransaction(String.valueOf(screen), "");
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
			putDataInSession(session, "lcCodes", msg03);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {	
				PageToCall = "ESD0000_lc_codes.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
			initTransaction(String.valueOf(screen), "");
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
			putDataInSession(session, "lcCodes", msg03);
			if (isNotError) {
				PageToCall = "ESD0000_lc_codes.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void proActionCommissi(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC050004Message) session.getAttribute("msgLC");
			msg04 = (ELC050004Message) msgHandle.initMessage(msg04, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MANINTENANCE");
			msg04.setH04SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg04);
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC050004Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg04);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0500_lc_commissi.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqCommissi(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC050004Message) msgHandle.initMessage("ELC050004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MANINTENANCE");
			msg04.setH04SCRCOD("01");
			msg04.setE04LCMBNK(userPO.getBank());
			msg04.setE04LCMACC(userPO.getIdentifier());
			msg04.setE04LCMPRO(userPO.getHeader1());
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC050004Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg04);
			if (isNotError) {
				PageToCall = "ELC0500_lc_commissi.jsp";
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionDetails(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC050002Message) session.getAttribute("msgLC");
			msg02 = (ELC050002Message) msgHandle.initMessage(msg02, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg02.setH02SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg02);
			if (req.getParameter("H02FLGWK1") == null) {
				msg02.setH02FLGWK1("");
			}
			boolean isFinal = msg02.getH02FLGWK1().equals("Y");
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC050002Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg02);
			if (isNotError && isFinal) {
				flexLog("About to call: " + "/servlet/datapro.eibs.products.JSESD0711?TYPE=LC");
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0711?TYPE=LC");
			} else {
				PageToCall = "ELC0500_lc_details.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqDetails(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC050002Message) msgHandle.initMessage("ELC050002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msg02.setH02SCRCOD("01");
			msg02.setE02LCMACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC050002Message) msgHandle.receiveMessage();
			if (isNotError) {
				putDataInSession(session, "msgLC", msg02);
				PageToCall = "ELC0500_lc_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				if (msg02.getE02LCMTYP().equals("O")) {
					PageToCall = "ELC0500_lc_opening_import.jsp";
				} else {
					PageToCall = "ELC0500_lc_opening_export.jsp";
				}
				callPage(PageToCall, req, res);
			}	
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionOpeningMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC050001Message) session.getAttribute("msgLC");
			msg01 = (ELC050001Message) msgHandle.initMessage(msg01, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg01.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC050001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			putDataInSession(session, "msgLC", msg01);
			if (isNotError) {
				PageToCall = "ELC0500_lc_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				if (msg01.getE01LCMTYP().equals("O")) {
					PageToCall = "ELC0500_lc_opening_import.jsp";
				} else {
					PageToCall = "ELC0500_lc_opening_export.jsp";
				}		
				callPage(PageToCall, req, res);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqOpeningMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC050001Message) msgHandle.initMessage("ELC050001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01LCMACC(req.getParameter("E01LCMACC").toUpperCase());
			} catch (Exception e) {
				msg01.setE01LCMACC(userPO.getIdentifier());
			}
			userPO.setIdentifier(msg01.getE01LCMACC());
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC050001Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg01);
			if (isNotError) {
				if (msg01.getE01LCMTYP().equals("O")) {
					PageToCall = "ELC0500_lc_opening_import.jsp";
				} else {
					PageToCall = "ELC0500_lc_opening_export.jsp";
				}		
			} else {
				//OJO
				PageToCall = "ELC0500_lc_enter_maint.jsp";
			}
			callPage(PageToCall, req, res);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionOpeningNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC050001Message) session.getAttribute("msgLC");
			msg01 = (ELC050001Message) msgHandle.initMessage(msg01, user.getH01USR(), "0005");
			userPO.setCusNum(req.getParameter("E01LCMACC"));
			initTransaction(String.valueOf(screen), "NEW");
			msg01.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC050001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			userPO.setBank(msg01.getE01LCMBNK());
			userPO.setHeader1(msg01.getE01LCMPRO());
			userPO.setHeader2(msg01.getE01DSCPRO());
			userPO.setAccNum(msg01.getE01LCMACC().trim());
			putDataInSession(session, "msgLC", msg01);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				if (msg01.getE01LCMTYP().equals("O")) { //Import
					PageToCall = "ELC0500_lc_opening_import.jsp";
				} else { //Export
					PageToCall = "ELC0500_lc_opening_export.jsp";
				}		
				callPage(PageToCall, req, res);
			}		
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqOpeningNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ESD0711_products_detail.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC050001Message) msgHandle.initMessage("ELC050001", user.getH01USR(), "0001");
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
			msg01 = (ELC050001Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgLC", msg01);
			if (isNotError) {
				if (msg01.getE01LCMTYP().equals("O")) { //Import
					PageToCall = "ELC0500_lc_opening_import.jsp";
				} else { //Export
					PageToCall = "ELC0500_lc_opening_export.jsp";
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
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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
		String PageToCall = "ELC0500_lc_enter_maint.jsp";
		initTransaction(String.valueOf(screen), "MAINTENANCE");
		userPO.setHeader18("NO_MENU");
		userPO.setProdCode("CC");
		putDataInSession(session, "msgLC", msg01);
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
	
	private void putDataInSession(HttpSession session, String name, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute(name, msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
