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
public class JSEDC0060 extends SuperServlet {
	
	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	EDC006001Message dcPag = null;
	EDC006002Message rcPag = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	protected static final int R_PAYMENT_ENTER = 1;
	protected static final int R_PAYMENT = 2;
	protected static final int VALID_PAYMENT = 3;
	protected static final int SAVE_PAYMENT = 4;
	
	public JSEDC0060() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0060");
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
					case R_PAYMENT_ENTER :
						procReqEnterPayment(req, res, screen);
						break;
					case R_PAYMENT:
						procReqPayment(req, res, screen);
						break;
					case VALID_PAYMENT:
						reqValidPayment(req, res, screen);
						break;
					case SAVE_PAYMENT:
						reqSavePayment(req, res, screen);
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
				flexLog("Socket used by JSEDC0060 closed.");
			}
		}	
	}
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void reqSavePayment(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			String ACCION = req.getParameter("H02FLGWK1");
			if (!ACCION.equals("Y")) {
				PageToCall = "EDC0060_coll_payment.jsp";
			} else {
				MessageContextHandler msgHandle = new MessageContextHandler(mc);
				dcPag = (EDC006001Message) session.getAttribute("dcPag");
				dcPag = (EDC006001Message) msgHandle.initMessage(dcPag, user.getH01USR(), "0003");
				initTransaction(String.valueOf(screen), "MAINTENANCE");
				msgHandle.sendMessage(dcPag);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				if (isNotError) { // There are no errors 
					PageToCall = "EDC0060_coll_enter_maint.jsp";
				} else {
					PageToCall = "EDC0060_coll_payment_conf.jsp";
				}
			}
			putDataInSession(session);
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
	private void reqValidPayment(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			dcPag = (EDC006001Message)  msgHandle.initMessage("EDC006001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			dcPag.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, dcPag);
			msgHandle.sendMessage(dcPag);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			// Receive Data to Confirm
			dcPag = (EDC006001Message) msgHandle.receiveMessage();
			if (isNotError) { // There are no errors 
				// Receive Transaction
				rcPag = (EDC006002Message) msgHandle.receiveMessage("EDC006002");
				PageToCall = "EDC0060_coll_payment_conf.jsp";
			} else {
				PageToCall = "EDC0060_coll_payment.jsp";
			}
			putDataInSession(session);
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
	private void procReqPayment(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			dcPag = (EDC006001Message)  msgHandle.initMessage("EDC006001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			dcPag.setH01SCRCOD("01");
			msgHandle.setFieldsFromPage(req, dcPag);
			msgHandle.sendMessage(dcPag);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			dcPag = (EDC006001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			if (isNotError) { // There are no errors 
				PageToCall = "EDC0060_coll_payment.jsp";
			} else {
				PageToCall = "EDC0060_coll_enter_maint.jsp";
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
	private void procReqEnterPayment(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			if (dcPag != null) {
				MessageContextHandler msgHandle = new MessageContextHandler(mc);
				dcPag = (EDC006001Message)  msgHandle.initMessage("EDC006001", user.getH01USR(), "");
			}	 
			String PageToCall = "EDC0060_coll_enter_maint.jsp";
			initTransaction(String.valueOf(screen), "MAINTENANCE");
			putDataInSession(session);
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

	private void initTransaction(String optMenu, String purpose) {
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
	
	private void putDataInSession(HttpSession session) {
		try {
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (dcPag != null) session.setAttribute("dcPag", dcPag);
			if (rcPag != null) session.setAttribute("rcPag", rcPag);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	
}