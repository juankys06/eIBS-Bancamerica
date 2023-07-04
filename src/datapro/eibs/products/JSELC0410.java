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

import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * StandBy Letter Of Credit Maintenance
 */
public class JSELC0410 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC040001Message msg = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	private static final int R_OPENING_MAINT = 3;
	private static final int A_OPENING_MAINT = 4;
	private static final int R_ENTER_MAINT = 200;
	
	public JSELC0410() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0410");
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
					case R_OPENING_MAINT : // OPENING MAINTENANCE
						procReqOpeningMaint(req, res, screen);
						break;
					case A_OPENING_MAINT :
						procActionOpeningMaint(req, res, screen);
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
				flexLog("Socket used by JSELC0410 closed.");
			}
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
			msg = (ELC040001Message) session.getAttribute("msg");
			msg = (ELC040001Message) msgHandle.initMessage(msg, user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC040001Message) msgHandle.receiveMessage();
			userPO.setIdentifier(msg.getE01LCMACC());
			userPO.setHeader11(msg.getE01LCMORF());
			userPO.setHeader12(msg.getE01LCMTRF());
			userPO.setHeader13(msg.getE01LCMPRO());
			userPO.setHeader14(msg.getE01DSCPRO());
			putDataInSession(session, msg);
			if (isNotError) {
				procReqDetailsMaint(req, res, screen);
			} else {
				PageToCall = "ELC0400_sb_basic_info.jsp";
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
	private void procReqDetailsMaint(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC040002Message msg = (ELC040002Message) msgHandle.initMessage("ELC040002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg.setH02SCRCOD("01");
			msg.setE02LCMCUN("E01LCMCUN");
			try {
				msg.setE02LCMACC(req.getParameter("E02LCMACC").toUpperCase());
			} catch (Exception e) {
				msg.setE02LCMACC(userPO.getIdentifier());
			}
			userPO.setIdentifier(msg.getE02LCMACC());
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC040002Message) msgHandle.receiveMessage();
			userPO.setHeader1(msg.getE02LCMPRO());
			userPO.setIdentifier(msg.getE02LCMACC());
			userPO.setBank(msg.getE02LCMBNK());
			userPO.setHeader2(msg.getE02DSCPRO());
			userPO.setHeader3(msg.getE02LCMTRF());
			userPO.setAccNum(msg.getE02LCMACC());
			putDataInSession(session, msg);
			if (isNotError) {
				PageToCall = "ELC0400_sb_details.jsp";
				callPage(PageToCall, req, res);
			} else {
				PageToCall = "ELC0400_sb_enter_maint.jsp";
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
			msg = (ELC040001Message) msgHandle.initMessage("ELC040001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			msg.setH01SCRCOD("01");
			msg.setE01LCMCUN("E01LCMCUN");
			try {
				msg.setE01LCMACC(req.getParameter("E01LCMACC").toUpperCase());
			} catch (Exception e) {
				msg.setE01LCMACC(userPO.getIdentifier());
			}
			try {
				msg.setE01LCMSWF(req.getParameter("E01LCMSWF").toUpperCase());
			} catch (Exception e) {
			}
			try {
				msg.setE01LCMAMF(req.getParameter("AMENDMENT").toUpperCase());
			} catch (Exception e) {
			}
			try {
				msg.setH01FLGMAS(req.getParameter("H01FLGMAS"));
			} catch (Exception e) {
			}
			userPO.setIdentifier(msg.getE01LCMACC());
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC040001Message) msgHandle.receiveMessage();
			userPO.setHeader1(msg.getE01LCMPRO());
			userPO.setIdentifier(msg.getE01LCMACC());
			userPO.setBank(msg.getE01LCMBNK());
			userPO.setHeader2(msg.getE01DSCPRO());
			userPO.setHeader3(msg.getE01LCMTRF());
			userPO.setAccNum(msg.getE01LCMACC());
			putDataInSession(session, msg);
			if (isNotError) {
				PageToCall = "ELC0400_sb_basic_info.jsp";
				callPage(PageToCall, req, res);
			} else {
				PageToCall = "ELC0400_sb_enter_maint.jsp";
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
	private void procReqEnterMaint(HttpServletRequest req, HttpServletResponse res, int screen) {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0400_sb_enter_maint.jsp";
		initTransaction(String.valueOf(screen), "MAINTENANCE");
		userPO.setHeader18("NO_MENU");
		userPO.setProdCode("CC");
		putDataInSession(session, msg);
		callPage(PageToCall, req, res);
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
	
	private void putDataInSession(HttpSession session, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
