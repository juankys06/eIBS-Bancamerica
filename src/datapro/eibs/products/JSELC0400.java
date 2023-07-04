/*
 * Created on Apr 7, 2008
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

import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSELC0400 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC040001Message msg01 = null;
	ELC040002Message msg02 = null;
	ESD000002Message msg03 = null;
	ELC040004Message msg04 = null;
	ESD000005Message msg05 = null;
	ELC040003Message msg06 = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	protected static final int R_OPENING_NEW = 1;
	protected static final int A_OPENING_NEW = 2;
	protected static final int R_OPENING_MAINT = 3;
	protected static final int A_OPENING_MAINT = 4;
	protected static final int R_DETAILS = 5;
	protected static final int A_DETAILS = 6;
	protected static final int R_COMMISSI = 7;
	protected static final int A_COMMISSI = 8;

	protected static final int R_ENTER_MAINT = 200;
	protected static final int R_SPECIAL_CODES = 101;
	protected static final int A_SPECIAL_CODES = 102;
	protected static final int R_SPECIAL_INST = 103;
	protected static final int A_SPECIAL_INST = 104;
	protected static final int R_FIELDS = 50;
	protected static final int A_FIELDS = 2000;

	public JSELC0400() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0400");
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
						procReqCommission(req, res, screen);
						break;
					case A_COMMISSI :
						proActionCommission(req, res, screen);
						break;
					case R_SPECIAL_CODES :
						procReqSpecialCodes(req, res, screen);
						break;
					case A_SPECIAL_CODES :
						procActionSpecialCodes(req, res, screen);
						break;
					case R_SPECIAL_INST :
						procReqSpecialInst(req, res, screen);
						break;
					case A_SPECIAL_INST :
						procActionSpecialInst(req, res, screen);
						break;
					case R_FIELDS :
						procRecOptionMenu(req, res, screen);
						break;
					case A_FIELDS :
						procActionOptionMenu(req, res, screen);
						break;
					case 1111 :
						procActionClausula(req, res, screen);
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
				flexLog("Socket used by JSELC0400 closed.");
			}
		}	
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionClausula(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg06 = (ELC040003Message) msgHandle.initMessage("ELC040003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "", "");
			try {
				msg06.setE03LCDFCD(req.getParameter("E01CLSCDE"));
			} catch (Exception e) {
			}
			try {
				msg06.setE03CLSTXN(req.getParameter("E01CLSTXN"));
			} catch (Exception e) {
			}
			msg06.setE03LCDACC(userPO.getIdentifier());
			msg06.setE03LCDPRO(userPO.getHeader1());
			msg06.setE03LCDBNK(userPO.getBank());
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC040003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg06);
			if (isNotError) {
				PageToCall = "ELC0400_sb_field_info.jsp";
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
			try{
				msg06 = (ELC040003Message) session.getAttribute("msg");	
			}catch(Exception ex){}
			
			msg06 = (ELC040003Message) msgHandle.initMessage(msg06, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "", "");
			msgHandle.setFieldsFromPage(req, msg06);
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC040003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg06);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0400_sb_field_info.jsp";
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
			msg06 = (ELC040003Message) msgHandle.initMessage("ELC040003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "", "");
			msg06.setE03LCDACC(userPO.getIdentifier());
			msg06.setE03LCDPRO(userPO.getHeader1());
			msg06.setE03LCDBNK(userPO.getBank());
			try {
				msg06.setE03LCDFCD(req.getParameter("CODE"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg06);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg06 = (ELC040003Message) msgHandle.receiveMessage();
			putDataInSession(session, msg06);
			if (isNotError) {
				PageToCall = "ELC0400_sb_field_info.jsp";
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
			try{
				msg05 = (ESD000005Message) session.getAttribute("msg");	
			}catch(Exception ex){}			
			msg05 = (ESD000005Message) msgHandle.initMessage(msg05, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "", "");
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
			putDataInSession(session, msg05);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				PageToCall = "ELC0400_sb_special_inst.jsp";
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
			initTransaction(String.valueOf(screen), "", "");
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
			putDataInSession(session, msg05);
			if (isNotError) {
				PageToCall = "ELC0400_sb_special_inst.jsp";
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
			initTransaction(String.valueOf(screen), "", "");
			msg03.setH02USR(user.getH01USR());
			msg03.setH02PGM("ELC0400");
			msg03.setH02TIM(getTimeStamp());
			msg03.setH02SCR("01");
			msg03.setH02OPE("0005");
			msg03.setH02SCR("01");
			msgHandle.setFieldsFromPage(req, msg03);
			msgHandle.sendMessage(msg03);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg03 = (ESD000002Message) msgHandle.receiveMessage();
			putDataInSession(session, msg03);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {	
				PageToCall = "ESD0000_sb_codes.jsp";
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
			initTransaction(String.valueOf(screen), "", "");
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
				PageToCall = "ESD0000_sb_codes.jsp";
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
	private void proActionCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC040004Message) session.getAttribute("msg");
			msg04 = (ELC040004Message) msgHandle.initMessage(msg04, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "", "");
			msg04.setH04SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg04);
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC040004Message) msgHandle.receiveMessage();
			putDataInSession(session, msg04);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				if (userPO.getOption().equals("NEW")) {
					PageToCall = "ELC0400_sb_commissi.jsp";
				} else {
					PageToCall = "ELC0400_sb_comm_mto.jsp";
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
	private void procReqCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg04 = (ELC040004Message) msgHandle.initMessage("ELC040004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "", "");
			msg04.setH04SCRCOD("01");
			msg04.setE04LCMBNK(userPO.getBank());
			try {
				msg04.setE04LCMACC(userPO.getIdentifier());
				msg04.setE04LCMPRO(userPO.getHeader1());
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg04);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg04 = (ELC040004Message) msgHandle.receiveMessage();
			putDataInSession(session, msg04);
			if (isNotError) {
				if (msg04.getE04LCMOPT().equals("N")) {
					PageToCall = "ELC0400_sb_commissi.jsp";
				} else {
					PageToCall = "ELC0400_sb_comm_mto.jsp";
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
	private void procActionDetails(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC040002Message) session.getAttribute("msg");
			msg02 = (ELC040002Message) msgHandle.initMessage(msg02, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msg02.setH02SCRCOD("01");
			if(req.getParameter("H02FLGWK1") == null) {
				msg02.setH02FLGWK1("");
			}
			msgHandle.setFieldsFromPage(req, msg02);
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC040002Message) msgHandle.receiveMessage();
			putDataInSession(session, msg02);
			if (isNotError && msg02.getH02FLGWK1().trim().equals("Y")) {
				if (!userPO.getRedirect().equals("0001")) {
					PageToCall = "ELC0400_sb_enter_maint.jsp";
					callPage(PageToCall, req, res);
				} else {
					flexLog("About to call: " + "/servlet/datapro.eibs.products.JSESD0711?TYPE=SB");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0711?TYPE=SB");
				}
			} else {
				PageToCall = "ELC0400_sb_details.jsp";
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
			msg01 = (ELC040001Message) msgHandle.initMessage("ELC040001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01LCMACC(req.getParameter("E01LCMACC").toUpperCase());
			} catch (Exception e) {
				msg01.setE01LCMACC(userPO.getIdentifier());
			}
			if (userPO.getHeader7().equals("Y")) {
				msg01.setE01LCMAMF(userPO.getHeader7());
			} else {
				try {
					msg01.setE01LCMAMF(req.getParameter("E01LCMAMF"));
					userPO.setHeader7(msg01.getE01LCMAMF());
				} catch (Exception e) {
				}
			}
			userPO.setIdentifier(msg01.getE01LCMACC());
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC040001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg01);
			if (isNotError) {
				if (msg01.getE01LCMTYP().equals("R")) {
					PageToCall = "ELC0400_sb_opening_incoming.jsp";
				} else {
					PageToCall = "ELC0400_sb_opening_outgoing.jsp";
				}		
			} else {
				PageToCall = "ELC0400_sb_enter_maint.jsp";
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
	private void procActionOpeningMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC040001Message) session.getAttribute("msg");
			msg01 = (ELC040001Message) msgHandle.initMessage(msg01, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msg01.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC040001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			putDataInSession(session, msg01);
			if (isNotError) {
				PageToCall = "ELC0400_sb_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				if (msg01.getE01LCMTYP().equals("R")) {
					PageToCall = "ELC0400_sb_opening_incoming.jsp";
				} else {
					PageToCall = "ELC0400_sb_opening_outgoing.jsp";
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
	private void procActionOpeningNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC040001Message) session.getAttribute("msg");
			msg01 = (ELC040001Message) msgHandle.initMessage(msg01, user.getH01USR(), "0005");
			userPO.setCusNum(req.getParameter("E01LCMACC"));
			initTransaction(String.valueOf(screen), "NEW", "0001");
			msg01.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC040001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg01.getE01LCMACC());
			userPO.setBank(msg01.getE01LCMBNK());
			userPO.setHeader1(msg01.getE01LCMPRO());
			userPO.setHeader2(msg01.getE01DSCPRO());
			userPO.setAccNum(msg01.getE01LCMACC().trim());
			putDataInSession(session, msg01);
			if (isNotError) {
				procReqDetails(req, res, screen);
			} else {
				if (msg01.getE01LCMTYP().equals("R")) {
					PageToCall = "ELC0400_sb_opening_incoming.jsp";
				} else {
					PageToCall = "ELC0400_sb_opening_outgoing.jsp";
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
	private void procReqDetails(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC040002Message) msgHandle.initMessage("ELC040002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "", "");
			msg02.setH02SCRCOD("01");
			msg02.setE02LCMACC(userPO.getIdentifier());
			msg02.setE02LCMAMF(userPO.getHeader7());
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg02 = (ELC040002Message) msgHandle.receiveMessage();
			if (isNotError) {
				putDataInSession(session, msg02);
				PageToCall = "ELC0400_sb_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				if (msg02.getE02LCMTYP().equals("R")) {
					PageToCall = "ELC0400_sb_opening_incoming.jsp";
				} else {
					PageToCall = "ELC0400_sb_opening_outgoing.jsp";
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
			msg01 = (ELC040001Message) msgHandle.initMessage("ELC040001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "NEW", "0001");
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
			msg01 = (ELC040001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg01);
			if (isNotError) {
				if (msg01.getE01LCMTYP().equals("R")) {
					PageToCall = "ELC0400_sb_opening_incoming.jsp";
				} else {
					PageToCall = "ELC0400_sb_opening_outgoing.jsp";
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
		String PageToCall = "ELC0400_sb_enter_maint.jsp";
		initTransaction(String.valueOf(screen), "MAINTENANCE", "");
		userPO.setHeader7("");	//Clear Amendment Flag
		putDataInSession(session, null);
		callPage(PageToCall, req, res);
	}
					
	private void initTransaction(String optMenu, String purpose, String opeCode) {
		try {
			if (!optMenu.equals("")) userPO.setOption(optMenu);
			if (!purpose.equals("")) userPO.setPurpose(purpose);
			userPO.setID(user.getE01INT());
			if (!opeCode.equals("")) userPO.setRedirect(opeCode);
			
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
			
			if (msg.getFormatName().equals("ELC040001")) {
				userPO.setHeader1(((ELC040001Message) msg).getE01LCMPRO());
				userPO.setHeader2(((ELC040001Message) msg).getE01DSCPRO());
				userPO.setBank(((ELC040001Message) msg).getE01LCMBNK());
			}
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
