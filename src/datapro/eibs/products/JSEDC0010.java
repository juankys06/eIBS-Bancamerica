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


public class JSEDC0010 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";

	// DC 
	protected static final int R_OPENING = 1;
	protected static final int A_OPENING = 2;
	protected static final int R_OPENING_MAINT = 3;
	protected static final int A_OPENING_MAINT = 4;
	
	protected static final int R_COMMISSIONS = 5;
	protected static final int A_COMMISSIONS = 6;
	protected static final int R_SPECIAL_CODES = 7;
	protected static final int A_SPECIAL_CODES = 8;
	protected static final int R_SPECIAL_INST = 9;
	protected static final int A_SPECIAL_INST = 10;
	
	protected static final int R_ENTER_MAINT = 100;
			

	public JSEDC0010() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0010");
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
					case R_OPENING :
						procReqOpening(req, res, screen);
						break;
					case A_OPENING :
						procActionOpening(req, res, screen);
						break;
					case R_OPENING_MAINT :
						procReqOpeningMaint(req, res, screen);
						break;
					case A_OPENING_MAINT :
						procActionOpeningMaint(req, res, screen);
						break;
					case R_COMMISSIONS :
						procReqCommissions(req, res, screen);
						break;
					case A_COMMISSIONS :
						procActionCommissions(req, res, screen);
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
					case R_ENTER_MAINT :
						procReqEnterMaint(req, res, screen);
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
				flexLog("Socket used by JSEDC0010 closed.");
			}
		}	
	}	

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqEnterMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		initTransaction(String.valueOf(screen), "MAINTENANCE", "");
		putDataInSession(session, "dcNew", null);
		String PageToCall = "EDC0010_dc_enter_maint.jsp";
		callPage(PageToCall, req, res);
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionSpecialInst(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000005Message msg = (ESD000005Message) session.getAttribute("dcInst");
			msg = (ESD000005Message) msgHandle.initMessage(msg, user.getH01USR(), "0005");
			msg.setH05USR(user.getH01USR());
			msg.setH05PGM("EDC0010");
			msg.setH05TIM(getTimeStamp());
			msg.setH05SCR("01");
			msg.setH05OPE("0005");
			msg.setE05ACC(userPO.getIdentifier());
			msg.setE05TYP("4");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000005Message) msgHandle.receiveMessage();
			if (isNotError) {
				procReqOpeningMaint(req, res, screen);
			} else {
				String PageToCall = "EDC0010_dc_special_inst.jsp";
				putDataInSession(session, "dcInst", msg);	
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
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000005Message msg = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0002");
			msg.setH05USR(user.getH01USR());
			msg.setH05PGM("EDC0010");
			msg.setH05TIM(getTimeStamp());
			msg.setH05SCR("01");
			msg.setH05OPE("0002");
			msg.setE05ACC(userPO.getIdentifier());
			msg.setE05TYP("4");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000005Message) msgHandle.receiveMessage();
			String PageToCall = "EDC0010_dc_special_inst.jsp";
			putDataInSession(session, "dcInst", msg);	
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
	private void procActionSpecialCodes(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000002Message msg = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0005");
			msg.setH02USR(user.getH01USR());
			msg.setH02PGM("EDC0010");
			msg.setH02TIM(getTimeStamp());
			msg.setH02SCR("01");
			msg.setH02OPE("0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000002Message) msgHandle.receiveMessage();
			if (isNotError) {
				procReqOpeningMaint(req, res, screen);
			} else {
				String PageToCall = "EDC0010_dc_codes.jsp";
				putDataInSession(session, "dcCodes", msg);	
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
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ESD000002Message msg = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0002");
			msg.setH02USR(user.getH01USR());
			msg.setH02PGM("EDC0010");
			msg.setH02TIM(getTimeStamp());
			msg.setH02SCR("01");
			msg.setH02OPE("0002");
			msg.setE02ACC(userPO.getIdentifier());
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000002Message) msgHandle.receiveMessage();
			String PageToCall = "EDC0010_dc_codes.jsp";
			putDataInSession(session, "dcCodes", msg);	
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
	private void procActionCommissions(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC001002Message msg = (EDC001002Message) msgHandle.initMessage("EDC001002", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			userPO.setCusNum(req.getParameter("E01DCMCUN"));
			msg.setH02SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC001002Message) msgHandle.receiveMessage();
			if (isNotError) {
				procReqOpeningMaint(req, res, screen);
			} else {
				String PageToCall = "EDC0010_dc_commissions.jsp";
				putDataInSession(session, "dcNew2", msg);	
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
	private void procReqCommissions(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC001002Message msg = (EDC001002Message) msgHandle.initMessage("EDC001002", user.getH01USR(), "0002");
			msg.setH02SCRCOD("01");
			msg.setE02DCMBNK(userPO.getBank());
			try {
				msg.setE02DCMACC(userPO.getIdentifier());
				msg.setE02DCMPRO(userPO.getHeader1());
			} catch (Exception e) {
			}
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC001002Message) msgHandle.receiveMessage();
			String PageToCall = "EDC0010_dc_commissions.jsp";
			putDataInSession(session, "dcNew2", msg);	
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
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC001001Message msg = (EDC001001Message) session.getAttribute("dcNew");
			msg = (EDC001001Message) msgHandle.initMessage(msg, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			userPO.setCusNum(req.getParameter("E01DCMCUN"));
			msg.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC001001Message) msgHandle.receiveMessage();
			String PageToCall = "EDC0010_dc_opening.jsp";
			putDataInSession(session, "dcNew", msg);	
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
	private void procReqOpeningMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			String PageToCall = "EDC0010_dc_enter_maint.jsp";
			if (req.getParameter("E01DCMACC") != null || (userPO.getIdentifier() != null && !userPO.getIdentifier().equals(""))) {
				String opCode="";
				if (req.getParameter("H01OPECOD") == null) {
					opCode = "0002";
				} else {
					opCode = req.getParameter("H01OPECOD");
				}
				MessageContextHandler msgHandle = new MessageContextHandler(mc);
				EDC001001Message msg = (EDC001001Message) msgHandle.initMessage("EDC001001", user.getH01USR(), opCode);
				msg.setH01SCRCOD("01");
				try { 
					msg.setE01DCMACC(req.getParameter("E01DCMACC").toUpperCase());
				} catch (Exception e) {
					msg.setE01DCMACC(userPO.getIdentifier());
				}
				initTransaction(String.valueOf(screen), "MAINTENANCE", msg.getE01DCMACC());
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg = (EDC001001Message) msgHandle.receiveMessage();
				if (isNotError) {
					boolean firstScreen = (msgError.getBigDecimalERDR01().intValue() == 14 || msgError.getBigDecimalERDR01().intValue() == 223);
					if (!firstScreen) {
						PageToCall = "EDC0010_dc_opening.jsp";
					}
				}
				userPO.setHeader1(msg.getE01DCMPRO());
				userPO.setIdentifier(msg.getE01DCMACC());
				userPO.setBank(msg.getE01DCMBNK());				
				putDataInSession(session, "dcNew", msg);
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
	private void procActionOpening(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC001001Message msg = (EDC001001Message) session.getAttribute("dcNew");
			msg = (EDC001001Message) msgHandle.initMessage(msg, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE", "");
			userPO.setCusNum(req.getParameter("E01DCMCUN"));
			msg.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC001001Message) msgHandle.receiveMessage();
			String PageToCall = "MISC_not_available.jsp";
			if (isNotError) {
				userPO.setIdentifier(msg.getE01DCMACC());
			}	
			PageToCall = "EDC0010_dc_opening.jsp";
			putDataInSession(session, "dcNew", msg);	
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
	private void procReqOpening(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC001001Message msg = (EDC001001Message) msgHandle.initMessage("EDC001001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "NEW", "");
			msg.setH01SCRCOD("01");
			try {
				msg.setE01DCMPRO(req.getParameter("E01DCMPRO"));
			} catch (Exception e) {
			}
			try {
				msg.setE01DCMBNK(req.getParameter("bank"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC001001Message) msgHandle.receiveMessage();
			String PageToCall = "MISC_not_available.jsp";
			putDataInSession(session, "dcNew", msg);	
			if (isNotError) {
				PageToCall = "EDC0010_dc_opening.jsp";
				callPage(PageToCall, req, res);
			} else {
				flexLog("About to call Page: " + LangPath + "ESD0711_products_detail.jsp");
				String firstLink =
					super.webAppPath
						+ LangPath
						+ "ESD0711_products_detail.jsp?appcode="
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

	private void initTransaction(String optMenu, String purpose, String number) {
		try {
			userPO.setOption(optMenu);
			userPO.setPurpose(purpose);
			if (!number.equals(""))	userPO.setIdentifier(number);
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