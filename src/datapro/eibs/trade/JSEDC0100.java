package datapro.eibs.trade;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import com.datapro.generic.tool.Util;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

import datapro.eibs.master.SuperServlet;

/**
 * @version 	1.0
 * @author	erodriguez
 */
public class JSEDC0100 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	MessageRecord message = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	protected static final int R_BASIC_INQ = 1;
	protected static final int R_CODES_INQ = 3;
	protected static final int R_STATEMENT = 5;
	protected static final int A_STATEMENT = 6;
	protected static final int R_SPECIAL_INST_INQ = 7;
	protected static final int R_PRODUCTS = 9;
	protected static final int R_RATE_TB = 11;
	protected static final int R_GUARANTEE = 13;
	protected static final int R_DOCUMENT = 15;

	// entering options
	protected static final int R_ENTER_INQUIRY = 100;
	protected static final int A_ENTER_INQUIRY = 200;
	
	public JSEDC0100() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0100");
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

				switch (screen)	{
					case R_BASIC_INQ :
						procReqInqBasic(req, res, screen);
						break;
					case R_CODES_INQ :
						procReqSpecialCodesInq(req, res, screen);
						break;
					case R_SPECIAL_INST_INQ :
						procReqEspInstInq(req, res, screen);
						break;
					case R_PRODUCTS :
						procReqProductLC(req, res, screen);
						break;
					case R_RATE_TB :
						procReqInqTable(req, res, screen);
						break;
					case R_ENTER_INQUIRY :
						procReqEnterInquiry(req, res, screen);
						break;
					case A_ENTER_INQUIRY :
						procActionEnterInquiry(req, res, screen);
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
				flexLog("Socket used by JSEDC0100 closed.");
			}
		}	
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqEspInstInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000005Message msg = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setH05USR(user.getH01USR());
			msg.setH05PGM("EDL0130");
			msg.setH05TIM(getTimeStamp());
			msg.setH05SCR("01");
			msg.setH05OPE("0004");
			msg.setE05ACC(userPO.getIdentifier());
			msg.setE05TYP("4");
			msgHandle.sendMessage(msg);
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				if (userPO.getHeader21().equals("D")) {
					PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
				} else {	
					PageToCall = "EDC0100_coll_simp_inq_basic.jsp";
				}
			} else {
				if (message.getFormatName().equals("ESD000005")) {
					msg = (ESD000005Message) message;
					PageToCall = "EDC0100_coll_inq_special_inst.jsp";
				}	
			}		
			putDataInSession(session, "collInst", msg);
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
	private void procActionEnterInquiry(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC010001Message msg = (EDC010001Message) msgHandle.initMessage("EDC010001", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setH01SCRCOD("01");
			try {
				msg.setE01DCMACC(req.getParameter("E01DCMACC"));
			} catch (Exception e)	{
				msg.setE01DCMACC("0");
			}
			msgHandle.sendMessage(msg);
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				PageToCall = "EDC0100_coll_enter_inquiry.jsp";
			} else {
				if (message.getFormatName().equals("EDC010001")) {
					msg = (EDC010001Message) message;
					userPO.setIdentifier(msg.getE01DCMACC());
					userPO.setBank(msg.getE01DCMBNK());
					userPO.setHeader1(msg.getE01DCMPRO());
					userPO.setHeader2(msg.getE01DCMCUN());
					userPO.setHeader3(msg.getE01CUSNA1());
					userPO.setCurrency(msg.getE01DCMCCY());
					userPO.setHeader21(msg.getE01DCMSTY());
					userPO.setHeader22(msg.getE01DCMTAR());
					userPO.setHeader23(msg.getE01DCMATY());
					userPO.setOfficer(msg.getE01DCMOFC() + " - " + msg.getE01DSCOFC());
			
					if (userPO.getHeader21().equals("D")) {
						PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
					} else { 
						PageToCall = "EDC0100_coll_simp_inq_basic.jsp";
					}	 
				}	
			}		
			putDataInSession(session, "collBasic", msg);
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
	private void procReqEnterInquiry(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		initTransaction("DV", "INQUIRY");
		putDataInSession(session, "", null);
		String PageToCall = "EDC0100_coll_enter_inquiry.jsp";
		callPage(PageToCall, req, res);
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqInqTable(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD0715DSMessage msg = (ESD0715DSMessage) msgHandle.initMessage("ESD0715DS", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setE01SELBNK(userPO.getBank());
			msg.setE01SELTLN(Util.justifyRight(userPO.getHeader22(), 2));
			msg.setE01SELTYP(userPO.getHeader23());
			msgHandle.sendMessage(msg);
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				if (userPO.getHeader21().equals("D")) {
					PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
				} else {	
					PageToCall = "EDC0100_coll_simp_inq_basic.jsp";
				}
			} else {
				if (message.getFormatName().equals("ESD0715DS")) {
					msg = (ESD0715DSMessage) message;
					PageToCall = "EDC0100_coll_inq_fee_tab.jsp";
				}	
			}		
			putDataInSession(session, "colProdInq", msg);
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
	private void procReqProductLC(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD071105Message msg = (ESD071105Message) msgHandle.initMessage("ESD071105", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setE05APCCDE(userPO.getHeader1());
			msg.setE05APCBNK(userPO.getBank());
			msgHandle.sendMessage(msg);
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				if (userPO.getHeader21().equals("D")) {
					PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
				} else {	
					PageToCall = "EDC0100_coll_simp_inq_basic.jsp";
				}
			} else {
				if (message.getFormatName().equals("ESD071105")) {
					msg = (ESD071105Message) message;
					PageToCall = "EDC0100_coll_inq_products.jsp";
				}	
			}		
			putDataInSession(session, "colProdInq", msg);
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
	private void procReqSpecialCodesInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000002Message msg = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setH02USR(user.getH01USR());
			msg.setH02PGM("ESD0000");
			msg.setH02TIM(getTimeStamp());
			msg.setH02SCR("01");
			msg.setH02OPE("0004");
			msg.setE02ACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg);
			
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				if (userPO.getHeader21().equals("D")) {
					PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
				} else {	
					PageToCall = "EDC0100_coll_simp_inq_basic.jsp";
				}
			} else {
				if (message.getFormatName().equals("ESD000002")) {
					msg = (ESD000002Message) message;
					PageToCall = "EDC0100_coll_inq_codes.jsp";
				}	
			}		
			putDataInSession(session, "collCodes", msg);
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
	private void procReqInqBasic(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC010001Message msg = (EDC010001Message) msgHandle.initMessage("EDC010001", user.getH01USR(), "0004");
			initTransaction("DV", "INQUIRY");
			msg.setH01SCRCOD("01");
			msg.setE01DCMACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg);
			message = msgHandle.receiveMessage();
			
			String PageToCall = "MISC_not_available.jsp";

			if (message.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) message;
				PageToCall = "EDC0100_coll_enter_inquiry.jsp";
			} else {
				if (message.getFormatName().equals("EDC010001")) {
					msg = (EDC010001Message) message;
					PageToCall = "EDC0100_coll_doc_inq_basic.jsp";
					userPO.setIdentifier(msg.getE01DCMACC());
					userPO.setBank(msg.getE01DCMBNK());
					userPO.setHeader1(msg.getE01DCMPRO());
					userPO.setHeader2(msg.getE01DCMCUN());
					userPO.setHeader3(msg.getE01CUSNA1());
					userPO.setCurrency(msg.getE01DCMCCY());
					userPO.setHeader21(msg.getE01DCMSTY());
					userPO.setHeader22(msg.getE01DCMTAR());
					userPO.setHeader23(msg.getE01DCMATY());
				}	
			}		
			putDataInSession(session, "collBasic", msg);
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
			userPO.setOption(optMenu);
			userPO.setPurpose(purpose);
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
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute(msg_name, msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
