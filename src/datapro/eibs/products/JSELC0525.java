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
 */
public class JSELC0525 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC052501Message msg052501 = null;
	ELC052502Message msg052502 = null;
	ELC040001Message msg040001 = null;
	ELC040002Message msg040002 = null;
	ELC040003Message msg040003 = null;
	ELC040004Message msg040004 = null;
	ELC050001Message msg050001 = null;
	ELC050002Message msg050002 = null;
	ELC050003Message msg050003 = null;
	ELC050004Message msg050004 = null;
	ELC050006Message msg050006 = null;
	ELC051001Message msg051001 = null;
	ELC051002Message msg051002 = null;
	ELC051003Message msg051003 = null;
	ELC051004Message msg051004 = null;
	ELC051005Message msg051005 = null;
	ELC051006Message msg051006 = null;
	EPR040001Message msgEPR040001 = null;
	ESD000005Message msgESD000005 = null;
	ESD000002Message msgESD000002 = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSELC0525() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0525");
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
					case 3 : // ENTER / CALL (from side menu)
						requestList(req, res, screen);
						break;
					case 0 : // MESSAGE SWIFT 
						requestMessageSwift(req, res, screen);
						break;
					case 1 : // APPROVE (from LC list) and call list again
						requestFromList(req, res, screen);
						break;
					case 2 : // DELETE (from LC list) and call list again
						requestDelete(req, res, screen);
						break;
					case 5 : // SELECT AN LC FROM THE LIST
						requestLetterOfCredit(req, res, screen);
						break;
					/* CALL BASIC INFO PAGE */
					case 5001 : // for type LC commercial new
						requestNewComercial(req, res, screen);
						break;
					case 5101 : // for type LC commercial maint
						requestMaintenanceComercial(req, res, screen);
						break; 
					case 4001 : // for type LC stand by
						requestStandby(req, res, screen);
						break; 
					/* CALL DOCUMENTS PAGE*/
					case 5002 :
						requestNewDocument(req, res, screen);
						break; 
					case 5102 :
						requestMaintenanceDocument(req, res, screen);
						break; 
					/* CALL DETAILS PAGE */
					case 5003 : // for type LC commercial new
						requestNewDetailComercial(req, res, screen);
						break; 
					case 5103 : // for type LC commercial maint
						requestMaintenanceDetailComercial(req, res, screen);
						break; 
					case 4003 : // for type LC stand by
						requestDetailStandby(req, res, screen);
						break; 
					/* CALL SPECIAL INSTRUCTIONS PAGE */
					case 5004 :
					case 5104 :
					case 4004 :
						requestSpecialInstruction(req, res, screen);
						break; 
					/* COMMISSIONS */
					case 5005 :
						requestNewCommission(req, res, screen);
						break; 
					case 5105 :
						requestMaintenanceCommission(req, res, screen);
						break; 
					case 4005 :
						requestStandbyCommission(req, res, screen);
						break; 
					/* SPECIAL CODES */
					case 5006 :
					case 5106 :
					case 4006 :
						requestSpecialCodes(req, res, screen);
						break; 
					/* ALL OTHER OPTIONS THAT USE ELC0**003 */
					case 40:
						requestStandbyOther(req, res, screen);
						break; 
					case 50:
						requestNewOther(req, res, screen);
						break; 
					case 51:
						requestMaintenanceOther(req, res, screen);
						break; 
					case 5107 :
						procReqTransfer(req, res, screen);
						break;
					case 5108 :
						procReqTransferCommission(req, res, screen);
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
				flexLog("Socket used by JSELC0525 closed.");
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
			msg051005 = (ELC051005Message) session.getAttribute("msg051005");
			msg051005 = (ELC051005Message) msgHandle.initMessage(msg051005, user.getH01USR(), "0008");
			initTransaction(String.valueOf(screen), "INQUIRY");
			msg051005.setH05SCRCOD("01");
			msg051005.setE05LCMACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg051005);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051005 = (ELC051005Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051005", msg051005);
			PageToCall = "ELC0525_lc_transfer_commissi.jsp";
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
	private void procReqTransfer(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051005 = (ELC051005Message) msgHandle.initMessage("ELC051005", user.getH01USR(), "0002");
			String action = "INQUIRY";
			initTransaction(String.valueOf(screen), action);
			msg051005.setH05SCRCOD("01");
			msg051005.setE05LCMACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg051005);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051005 = (ELC051005Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051005", msg051005);
			PageToCall = "ELC0525_lc_transfer.jsp";
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
	private void requestMaintenanceOther(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051003 = (ELC051003Message) msgHandle.initMessage("ELC051003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg051003.setE03LCDACC(userPO.getAccNum());
			msg051003.setE03LCDFCD(req.getParameter("fcode"));
			msgHandle.sendMessage(msg051003);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051003 = (ELC051003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg", msg051003);
			PageToCall = "ELC0525_lc_field_info_maint.jsp";
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
	private void requestNewOther(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg050003 = (ELC050003Message) msgHandle.initMessage("ELC050003", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg050003.setE03LCDACC(userPO.getAccNum());
			msg050003.setE03LCDFCD(req.getParameter("fcode"));
			msgHandle.sendMessage(msg050003);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg050003 = (ELC050003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg", msg050003);
			PageToCall = "ELC0525_lc_field_info.jsp";
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
	private void requestStandbyOther(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg040003 = (ELC040003Message) msgHandle.initMessage("ELC040003", user.getH01USR(), "0002");
			msg040003.setE03LCDFCD(req.getParameter("fcode"));
			initTransaction(String.valueOf(screen), "");
			msg040003.setE03LCDACC(userPO.getAccNum());
			msgHandle.sendMessage(msg040003);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg040003 = (ELC040003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg", msg040003);
			PageToCall = "ELC0525_sb_field_info.jsp";
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
	private void requestSpecialCodes(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgESD000002 = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msgESD000002.setH02OPE("0002");
			msgESD000002.setH02USR(user.getH01USR());
			msgESD000002.setE02ACC(userPO.getAccNum());
			msgESD000002.setH02PGM(req.getParameter("fcode"));
			msgESD000002.setH02SCR("01");
			msgHandle.sendMessage(msgESD000002);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msgESD000002 = (ESD000002Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgESD000002", msgESD000002);
			switch (screen) {
				case 4006: PageToCall = "ELC0525_lc_special_codes_sb.jsp"; break;
				case 5006: PageToCall = "ELC0525_lc_special_codes.jsp"; break;
				default: PageToCall = "ELC0525_lc_special_codes_m.jsp"; break; 
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
	private void requestStandbyCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg040004 = (ELC040004Message) msgHandle.initMessage("ELC040004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg040004.setH04SCRCOD("01");
			msg040004.setE04LCMACC(userPO.getAccNum());
			msg040004.setE04LCMPRO(userPO.getProdCode());
			msg040004.setE04LCMBNK(userPO.getBank());
			msgHandle.sendMessage(msg040004);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg040004 = (ELC040004Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg040004", msg040004);
			PageToCall = "ELC0525_lc_commissi_sb.jsp";
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
	private void requestMaintenanceCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051004 = (ELC051004Message) msgHandle.initMessage("ELC051004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENACE");
			msg051004.setH04SCRCOD("01");
			msg051004.setE04LCMACC(userPO.getAccNum());
			msg051004.setE04LCMPRO(userPO.getProdCode());
			msg051004.setE04LCMBNK(userPO.getBank());
			msgHandle.sendMessage(msg051004);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051004 = (ELC051004Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051004", msg051004);
			PageToCall = "ELC0525_lc_commissi_m.jsp";
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
	private void requestNewCommission(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg050004 = (ELC050004Message) msgHandle.initMessage("ELC050004", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg050004.setH04SCRCOD("01");
			msg050004.setE04LCMACC(userPO.getAccNum());
			msg050004.setE04LCMPRO(userPO.getProdCode());
			msg050004.setE04LCMBNK(userPO.getBank());
			msgHandle.sendMessage(msg050004);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg050004 = (ELC050004Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg050004", msg050004);
			PageToCall = "ELC0525_lc_commissi.jsp";
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
	private void requestSpecialInstruction(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgESD000005 = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msgESD000005.setH05TIM(getTimeStamp());
			msgESD000005.setH05OPE("0002");
			msgESD000005.setH05USR(user.getH01USR());
			msgESD000005.setE05ACC(userPO.getAccNum());
			msgESD000005.setH05PGM(req.getParameter("fcode"));
			msgESD000005.setH05SCR("01");
			msgESD000005.setE05TYP("3");
			msgHandle.sendMessage(msgESD000005);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msgESD000005 = (ESD000005Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgESD000005", msgESD000005);
			switch (screen) {
				case 4004: PageToCall = "ELC0525_lc_special_inst_sb.jsp"; break;
				case 5004: PageToCall = "ELC0525_lc_special_inst.jsp"; break;
				default: PageToCall = "ELC0525_lc_special_inst_m.jsp"; break; 
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
	private void requestDetailStandby(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg040002 = (ELC040002Message) msgHandle.initMessage("ELC040002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg040002.setE02LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msg040002);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg040002 = (ELC040002Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg040002", msg040002);
			PageToCall = "ELC0525_lc_details_sb.jsp";
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
	private void requestMaintenanceDetailComercial(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051002 = (ELC051002Message) msgHandle.initMessage("ELC051002", user.getH01USR(), "0004");
			initTransaction(String.valueOf(screen), "INQUIRY");
			msg051002.setE02LCMACC(userPO.getAccNum());
			msg051002.setH02FLGWK1("A");
			msgHandle.sendMessage(msg051002);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051002 = (ELC051002Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051002", msg051002);
			PageToCall = "ELC0525_lc_details_m.jsp";
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
	private void requestNewDetailComercial(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg050002 = (ELC050002Message) msgHandle.initMessage("ELC050002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg050002.setE02LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msg050002);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg050002 = (ELC050002Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg050002", msg050002);
			PageToCall = "ELC0525_lc_details.jsp";
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
	private void requestMaintenanceDocument(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051006 = (ELC051006Message) msgHandle.initMessage("ELC051006", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg051006.setH06SCRCOD("01");
			msg051006.setE06LCMACC(userPO.getAccNum());
			msg051006.setE06LCMPRO(userPO.getProdCode());
			msg051006.setE06LCMBNK(userPO.getBank());
			msgHandle.sendMessage(msg051006);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051006 = (ELC051006Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051006", msg051006);
			PageToCall = "ELC0525_lc_documents_m.jsp";
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
	private void requestNewDocument(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg050006 = (ELC050006Message) msgHandle.initMessage("ELC050006", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg050006.setH06SCRCOD("01");
			msg050006.setE06LCMACC(userPO.getAccNum());
			msg050006.setE06LCMPRO(userPO.getProdCode());
			msg050006.setE06LCMBNK(userPO.getBank());
			msgHandle.sendMessage(msg050006);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg050006 = (ELC050006Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg050006", msg050006);
			PageToCall = "ELC0525_lc_documents.jsp";
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
	private void requestStandby(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg040001 = (ELC040001Message) msgHandle.initMessage("ELC040001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg040001.setE01LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msg040001);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg040001 = (ELC040001Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg040001", msg040001);
			PageToCall = (userPO.getType().equals("R") ? "ELC0525_lc_opening_incoming_sb.jsp" : "ELC0525_lc_opening_outgoing_sb.jsp");
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
	private void requestMaintenanceComercial(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg051001 = (ELC051001Message) msgHandle.initMessage("ELC051001", user.getH01USR(), "0004");
			initTransaction(String.valueOf(screen), "INQUIRY");
			msg051001.setE01LCMACC(userPO.getAccNum());
			msg051001.setH01FLGWK1("A");
			msgHandle.sendMessage(msg051001);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051001 = (ELC051001Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg051001", msg051001);
			PageToCall = (userPO.getType().equals("I") ? "ELC0525_lc_opening_export_m.jsp" : "ELC0525_lc_opening_import_m.jsp");
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
	private void requestNewComercial(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg050001 = (ELC050001Message) msgHandle.initMessage("ELC050001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "NEW");
			msg050001.setE01LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msg050001);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg050001 = (ELC050001Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg050001", msg050001);
			PageToCall = (userPO.getType().equals("I") ? "ELC0525_lc_opening_export.jsp" : "ELC0525_lc_opening_import.jsp");
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
	private void requestLetterOfCredit(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			try {
				userPO.setIdentifier(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
			}
			try {
				userPO.setAccNum(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
			}
			try {
				userPO.setType(req.getParameter("E01LCMTYP"));
			} catch (Exception e) {
			}
			try {
				userPO.setPurpose(req.getParameter("E01LCMOPT"));
			} catch (Exception e) {
			}
		
			if (userPO.getType().equals("I") || userPO.getType().equals("O")) {
				/******************************************** COMMERCIAL LC ********************************************/
				if (userPO.getPurpose().equals("N")) {
					/********************************** NEW *************************************/
					msg050001 = (ELC050001Message) msgHandle.initMessage("ELC050001", user.getH01USR(), "0004");
					initTransaction(String.valueOf(screen), "NEW");
					msg050001.setE01LCMACC(userPO.getAccNum());
					msgHandle.sendMessage(msg050001);
					msgError = msgHandle.receiveErrorMessage();
					boolean isNotError = msgError.getERRNUM().equals("0");
					msg050001 = (ELC050001Message) msgHandle.receiveMessage();
					putDataInSession(session, "msg050001", msg050001);
					PageToCall = (userPO.getType().equals("I") ? "ELC0525_lc_opening_export.jsp" : "ELC0525_lc_opening_import.jsp");
				} else {
					/********************************* MAINTENANCE ************************************/
					msg051001 = (ELC051001Message) msgHandle.initMessage("ELC051001", user.getH01USR(), "0004");
					initTransaction(String.valueOf(screen), "MAINTENANCE");
					msg051001.setE01LCMACC(userPO.getAccNum());
					msg051001.setH01FLGWK1("A");
					msgHandle.sendMessage(msg051001);
					msgError = msgHandle.receiveErrorMessage();
					boolean isNotError = msgError.getERRNUM().equals("0");
					msg051001 = (ELC051001Message) msgHandle.receiveMessage();
					userPO.setAccOpt(msg051001.getE01LCMOPT());
					putDataInSession(session, "msg051001", msg051001);
					PageToCall = (userPO.getType().equals("I") ? "ELC0525_lc_opening_export_m.jsp" : "ELC0525_lc_opening_import_m.jsp");
				}
			} else {
				/********************************************* STANDBY LC *********************************************/
				msg040001 = (ELC040001Message) msgHandle.initMessage("ELC040001", user.getH01USR(), "0004");
				initTransaction(String.valueOf(screen), "");
				msg040001.setE01LCMACC(userPO.getAccNum());
				msgHandle.sendMessage(msg040001);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg040001 = (ELC040001Message) msgHandle.receiveMessage();
				putDataInSession(session, "msg040001", msg040001);
				PageToCall = (userPO.getType().equals("R") ? "ELC0525_lc_opening_incoming_sb.jsp" : "ELC0525_lc_opening_outgoing_sb.jsp");
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
	private void requestDelete(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg052502 = (ELC052502Message) msgHandle.initMessage("ELC052502", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "DELETE");
			try {
				msg052502.setE02LCMACC(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
				msg052502.setE02LCMACC(userPO.getAccNum());
			}
			msg052502.setE02ACTION("D");
			msgHandle.sendMessage(msg052502);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				requestList(req, res, screen);
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
	private void requestFromList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg052502 = (ELC052502Message) msgHandle.initMessage("ELC052502", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			try {
				msg052502.setE02LCMACC(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
				msg052502.setE02LCMACC(userPO.getAccNum());
			}
			msg052502.setE02ACTION("A");
			msgHandle.sendMessage(msg052502);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				requestList(req, res, screen);
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
	private void requestMessageSwift(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgEPR040001 = (EPR040001Message) msgHandle.initMessage("EPR040001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			try {
				msgEPR040001.setE01SWOACC(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
				msgEPR040001.setE01SWOACC(userPO.getAccNum());
			}
			msg040001.setE01LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msgEPR040001);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				requestList(req, res, screen);
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
	private void requestList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg052501 = (ELC052501Message) msgHandle.initMessage("ELC052501", user.getH01USR(), "0015");
			initTransaction(String.valueOf(screen), "");
			msg052501.setH01SCRCOD("01");
			msgHandle.sendMessage(msg052501);
			jbList = msgHandle.receiveMessageList("H01FLGMAS", JSEIBSProp.getMaxIterations());
			putDataInSession(session, "msg052501", msg052501);
			PageToCall = "ELC0525_approval_letter_of_credit_list.jsp";
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
	
	private void putDataInSession(HttpSession session, String msg_name, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			if (msg.getFormatName().equals("ELC040001")) {
				userPO.setHeader2(((ELC040001Message) msg).getE01DSCPRO());
				userPO.setHeader14(((ELC040001Message) msg).getE01DSCPRO());
				userPO.setBank(((ELC040001Message) msg).getE01LCMBNK());
				userPO.setProdCode(((ELC040001Message) msg).getE01LCMPRO());
			} else {
				if (msg.getFormatName().equals("ELC050001")) {
					userPO.setHeader2(((ELC050001Message) msg).getE01DSCPRO());
					userPO.setHeader14(((ELC050001Message) msg).getE01DSCPRO());
					userPO.setBank(((ELC050001Message) msg).getE01LCMBNK());
					userPO.setProdCode(((ELC050001Message) msg).getE01LCMPRO());
				} else {
					if (msg.getFormatName().equals("ELC051001")) {
						userPO.setHeader2(((ELC051001Message) msg).getE01DSCPRO());
						userPO.setHeader14(((ELC051001Message) msg).getE01DSCPRO());
						userPO.setBank(((ELC051001Message) msg).getE01LCMBNK());
						userPO.setProdCode(((ELC051001Message) msg).getE01LCMPRO());
					}	
				}
			}
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute(msg_name, msg);
			if (jbList != null) session.setAttribute("jbList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
