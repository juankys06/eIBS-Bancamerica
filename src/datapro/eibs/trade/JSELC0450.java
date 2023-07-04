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
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSELC0450 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC044001Message msg044001 = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	// Letter of Credit 
	protected static final int R_BASIC_INQ			= 1;
	protected static final int R_CODES_INQ			= 3;
	protected static final int R_STATEMENT			= 5;
	protected static final int A_STATEMENT			= 6;	
	protected static final int R_SPECIAL_INST_INQ	= 7;
	protected static final int R_PRODUCTS			= 9;
	protected static final int R_RATE_TB			= 11;
	protected static final int R_GUARANTEE			= 13;
	protected static final int R_DOCUMENT			= 15;
	protected static final int R_SWIFT_INQUIRY		= 52;

	// entering options
	protected static final int R_ENTER_INQUIRY		= 100;	
	protected static final int A_ENTER_INQUIRY		= 200;
	protected static final int R_LIST				= 210;

	public JSELC0450() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0450");
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
					//Inquiry Options
					case R_BASIC_INQ :
						procReqInqBasic(req, res, session);
						break;									
					case R_CODES_INQ :
						procReqSpecialCodesInq(req, res, session);
						break;
					case R_SPECIAL_INST_INQ :
						procReqEspInstInq(req, res, session);
						break;
					case R_PRODUCTS:
						procReqProductLC(req, res, session);
						break;
					case R_RATE_TB:
						procReqInqTable(req, res, session);
						break;
					case R_GUARANTEE:
						procReqCollAssets(req, res, session);
						break;
					// Request
					case R_ENTER_INQUIRY : 
						procReqEnterInquiry(req, res, session);
						break;
					case R_LIST :
						procReqList(req, res, session);
						break;
					// Action 
					case A_ENTER_INQUIRY : 
						procActionEnterInquiry(req, res, session);
						break;
					case R_SWIFT_INQUIRY : 
						requestConsultaOther(req, res, session);
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
				flexLog("Socket used by JSELC0450 closed.");
			}
		}	
	}			

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	
	private void requestConsultaOther(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC051003Message msg051003 = (ELC051003Message) msgHandle.initMessage("ELC051003", user.getH01USR(), "0002");
			initTransaction("", "INQUIRY");
			msg051003.setE03LCDACC(userPO.getAccNum());
			msg051003.setE03LCDFCD(req.getParameter("fcode"));
			msgHandle.sendMessage(msg051003);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg051003 = (ELC051003Message) msgHandle.receiveMessage();
			putDataInSession(session, "msg", msg051003);
			PageToCall = "ELC0450_lc_field_info_inquiry.jsp";
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
	 * @param session
	 */
	private void procActionEnterInquiry(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC045001Message msg = (ELC045001Message) msgHandle.initMessage("ELC045001", user.getH01USR(), "0002");
			msg.setH01SCRCOD("01");
			msg.setE01LCMACD("LC");
			try {
				msg.setE01LCMACC(req.getParameter("E01LCMACC"));
			} catch (Exception e)	{
				msg.setE01LCMACC("0");
			}
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_enter_inquiry.jsp";
			} else {
				msg = (ELC045001Message) newmsg;

				userPO.setIdentifier(msg.getE01LCMACC());
				userPO.setAccNum(msg.getE01LCMACC());
				userPO.setBank(msg.getE01LCMBNK());
				userPO.setHeader1(msg.getE01LCMPRO());
				userPO.setHeader2(msg.getE01LCMCUN());
				userPO.setHeader3(msg.getE01CUSNA1());
				userPO.setCurrency(msg.getE01LCMCCY());
				userPO.setHeader22(msg.getE01LCMTAR());
				userPO.setHeader21(msg.getE01LCMATY());
				userPO.setOfficer(msg.getE01LCMOFC() + " - " + msg.getE01DSCOFC());
				userPO.setHeader10(msg.getE01LCMAP1());
				userPO.setHeader11(msg.getE01LCMAP2());
				userPO.setHeader12(msg.getE01LCMAP3());
				userPO.setHeader13(msg.getE01LCMAP4());
				userPO.setHeader14(msg.getE01LCMAPA());
				userPO.setProdCode(msg.getE01LCMPRO());
				
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			}
		
			putDataInSession(session, "lcBasic", msg);
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
	 * @param session
	 */
	private void procReqEnterInquiry(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		initTransaction("LC", "INQUIRY");
		putDataInSession(session, null, null);
		String PageToCall = "ELC0450_lc_enter_inquiry.jsp";
		callPage(PageToCall, req, res);
		
	}

	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqCollAssets(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		try {
			flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list.jsp");
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=1");	
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqInqTable(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD0714DSMessage msg = (ESD0714DSMessage) msgHandle.initMessage("ESD0714DS", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01SELBNK(userPO.getBank());
			msg.setE01SELTLN(Util.justifyRight(userPO.getHeader22(), 2));
			msg.setE01SELTYP(userPO.getHeader21());
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			} else {
				msg = (ESD0714DSMessage) newmsg;
				PageToCall = "ELC0450_lc_inq_fee_tab.jsp";
			}
			putDataInSession(session, "lctProdInq", msg);
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
	 * @param session
	 */
	private void procReqProductLC(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD071104Message msg = (ESD071104Message) msgHandle.initMessage("ESD071104", user.getH01USR(), "0004");
			msg.setH04SCRCOD("01");
			msg.setE04APCCDE(userPO.getHeader1());
			msg.setE04APCBNK(userPO.getBank());
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			} else {
				msg = (ESD071104Message) newmsg;
				PageToCall = "ELC0450_lc_inq_products.jsp";
			}
			putDataInSession(session, "lcProdInq", msg);
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
	 * @param session
	 */
	private void procReqEspInstInq(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000005Message msg = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0004");
			msg.setH05SCR("01");
			msg.setH05OPE("0004");
			msg.setE05ACC(userPO.getIdentifier());
			msg.setE05TYP("3");
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			} else {
				msg = (ESD000005Message) newmsg;
				PageToCall = "ELC0450_lc_inq_special_inst.jsp";
			}
			putDataInSession(session, "lcInst", msg);
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
	 * @param session
	 */
	private void procReqSpecialCodesInq(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000002Message msg = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0004");
			msg.setH02SCR("01");
			msg.setH02OPE("0004");
			msg.setE02ACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			} else {
				msg = (ESD000002Message) newmsg;
				PageToCall = "ELC0450_lc_inq_codes.jsp";
			}
			putDataInSession(session, "lcCodes", msg);
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
	 * @param session
	 */
	private void procReqInqBasic(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC045001Message msg = (ELC045001Message) msgHandle.initMessage("ELC045001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01LCMACC(userPO.getIdentifier());
			msgHandle.sendMessage(msg);
			MessageRecord newmsg = msgHandle.receiveMessage();
			if (newmsg.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0450_lc_enter_inquiry.jsp";
			} else {
				msg = (ELC045001Message) newmsg;
				PageToCall = "ELC0450_lc_inq_basic.jsp";
			}
			putDataInSession(session, "lcBasic", msg);
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
	private void procReqList(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		String PageToCall = "";
		boolean isNotError = false;
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg044001 = (ELC044001Message) msgHandle.initMessage("ELC044001", user.getH01USR(), "0015");
			msgHandle.setFieldsFromPage(req, msg044001);
			msgHandle.sendMessage(msg044001);
			msgError = msgHandle.receiveErrorMessage();
			isNotError = msgError.getERRNUM().equals("0");
			jbList = msgHandle.receiveMessageList("H01FLGMAS");
			putDataInSession(session, "", null);
			if (isNotError) PageToCall = "ELC0450_lc_inquiry_list.jsp";
			else PageToCall = "ELC0450_lc_enter_inquiry.jsp";
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
		//msgError = null;
		try {
			if (!optMenu.equals("")) userPO.setOption(optMenu);
			if (!purpose.equals("")) userPO.setPurpose(purpose);
			
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
	
	private void putDataInSession(HttpSession session, String msg_name, Object msg) {
		try {
			flexLog("Putting java beans into the session");

			if (msgError == null) msgError = new ELEERRMessage();
			session.setAttribute("error", msgError);
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