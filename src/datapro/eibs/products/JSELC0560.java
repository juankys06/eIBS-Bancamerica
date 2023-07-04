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
public class JSELC0560 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC056001Message msg01 = null;
	ELC056002Message msg02 = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSELC0560() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0560");
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
					case 1 : // LC NEGO ENTER NEW
						procNewNegotiation(req, res, screen);
						break;
					case 5 : // LC NEGO ENTER MAINT
						procMaintNegotiation(req, res, screen);
						break;
					case 2 : // LC NEGO ENTER NEW/MAINT SUBMIT
						requestNegotiation(req, res, screen);
						break;
					case 3 : // submit sp, acp, or ref page
						requestReferencePage(req, res, screen);
						break;
					case 8 : // SUBMIT CONFIRMATION PAGE
						requestConfirmationPage(req, res, screen);
						break;
					case 11 :
						requestInquiry(req, res, screen);
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
				flexLog("Socket used by JSELC0560 closed.");
			}
		}	
	}	
	
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procNewNegotiation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		initTransaction(String.valueOf(screen), "NEW", "");
		String PageToCall = "ELC0560_lc_nego_enter.jsp";
		userPO.setPrevPage(PageToCall);
		putDataInSession(session);	
		callPage(PageToCall, req, res);
	}	
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procMaintNegotiation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		initTransaction(String.valueOf(screen), "MAINTENANCE", "");
		String PageToCall = "ELC0560_lc_nego_enter_maint.jsp";
		userPO.setPrevPage(PageToCall);
		putDataInSession(session);	
		callPage(PageToCall, req, res);
	}	
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void requestInquiry(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC056001Message) msgHandle.initMessage("ELC056001", user.getH01USR(), "0002");
			try	{
				msg01.setE01LCRNUM(req.getParameter("E01LCRNUM"));
			} catch (Exception e) {
			}
			try	{
				msg01.setE01DRWNUM(req.getParameter("E01DRWNUM"));
			} catch (Exception e) {
			}
			initTransaction(String.valueOf(screen), "", msg01.getE01LCRNUM());
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC056001Message) msgHandle.receiveMessage();
			String PageToCall = "";
			int opcode; 
			try {
				opcode = Integer.parseInt(msg01.getE01OPCODE().trim());
			} catch (Exception e) {
				opcode = 0;
			}
			switch (opcode)	{
				case 1:
				case 6: PageToCall = "ELC0560_lc_nego_sp_readonly.jsp"; break;
				case 2:
				case 4: PageToCall = "ELC0560_lc_nego_acp_readonly.jsp"; break;
				case 9: PageToCall = "ELC0560_lc_nego_com_readonly.jsp"; break;
				default: PageToCall = "ELC0560_lc_nego_ref_readonly.jsp"; break;
			}
			userPO.setNextPage(PageToCall);
			putDataInSession(session);	
			callPage(isNotError ? userPO.getNextPage() : userPO.getPrevPage(), req, res);
		
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
	private void requestConfirmationPage(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			try {
				msg01 = (ELC056001Message) session.getAttribute("msg01");
				msg01 = (ELC056001Message) msgHandle.initMessage(msg01, user.getH01USR(), "");
			} catch (Exception e) {
				msg01 = (ELC056001Message) msgHandle.initMessage("ELC056001", user.getH01USR(), "");
			}
			try	{
				msg01.setE01LCRNUM(req.getParameter("E01LCRNUM"));
			} catch (Exception e) {
			}
			initTransaction(String.valueOf(screen), "", msg01.getE01LCRNUM());
			String PageToCall = "";
			if (req.getParameter("H01FLGWK1").equals("Y")) {
				msg01.setH01FLGWK1(req.getParameter("H01FLGWK1"));
				msgHandle.sendMessage(msg01);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				if (msg01.getE01PMTVIA().equals("5")) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEPR0000?SCREEN=0003&REFNUM=" + msg01.getE01TRFNUM());
					return;
				} else {
					if (userPO.getPurpose().equals("MAINTENANCE")) {
						PageToCall = "ELC0560_lc_nego_enter_maint.jsp";
					} else {
						PageToCall = "ELC0560_lc_nego_enter.jsp";
					}
				}
			} else {
				int opcode; 
				try {
					opcode = Integer.parseInt(msg01.getE01OPCODE().trim());
				} catch (Exception e) {
					opcode = 0;
				}
				switch (opcode)	{
					case 1:
					case 6: PageToCall = "ELC0560_lc_nego_sp.jsp"; break;
					case 2:
					case 4: PageToCall = "ELC0560_lc_nego_acp.jsp"; break;
					case 9: PageToCall = "ELC0560_lc_nego_com.jsp"; break;
					default: PageToCall = "ELC0560_lc_nego_ref.jsp"; break;
				}
			}
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

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void requestReferencePage(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			try {
				msg01 = (ELC056001Message) session.getAttribute("msg01");
				msg01 = (ELC056001Message) msgHandle.initMessage(msg01, user.getH01USR(), "");
			} catch (Exception e) {
				msg01 = (ELC056001Message) msgHandle.initMessage("ELC056001", user.getH01USR(), "");
			}
			try	{
				msg01.setE01LCRNUM(req.getParameter("E01LCRNUM"));
			} catch (Exception e) {
			}
			msg01.setH01FLGWK1("N");
			initTransaction(String.valueOf(screen), "", msg01.getE01LCRNUM());
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC056001Message) msgHandle.receiveMessage();
			if (isNotError) {
				msg02 = (ELC056002Message) msgHandle.initMessage("ELC056002", user.getH01USR(), "");
				msg02 = (ELC056002Message) msgHandle.receiveMessage();
			}
			endTransaction(msg01);
			putDataInSession(session);	
			callPage(isNotError ? userPO.getNextPage() : userPO.getPrevPage(), req, res);
			
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
	private void requestNegotiation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			try {
				msg01 = (ELC056001Message) session.getAttribute("msg01");
				msg01 = (ELC056001Message) msgHandle.initMessage(msg01, user.getH01USR(), "");
			} catch (Exception e) {
				msg01 = (ELC056001Message) msgHandle.initMessage("ELC056001", user.getH01USR(), "");
			}
			try	{
				msg01.setE01LCRNUM(req.getParameter("E01LCRNUM"));
			} catch (Exception e) {
			}
			initTransaction(String.valueOf(screen), "", msg01.getE01LCRNUM());
			msgHandle.setFieldsFromPage(req, msg01);
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC056001Message) msgHandle.receiveMessage();
			endTransaction(msg01);

			// decide between which page to call
			String PageToCall = "";
			
			int code = 0;
			try {
				code = Integer.parseInt(msg01.getE01OPCODE().trim());
			} catch (Exception e) {
			}
			
			switch (code)	{
				case 1:
				case 6: PageToCall = "ELC0560_lc_nego_sp.jsp"; break;
				case 2:
				case 4: PageToCall = "ELC0560_lc_nego_acp.jsp"; break;
				case 9: PageToCall = "ELC0560_lc_nego_com.jsp"; break;
				default: PageToCall = "ELC0560_lc_nego_ref.jsp"; break;
			}
			userPO.setNextPage(PageToCall);
			putDataInSession(session);	
			callPage(isNotError ? userPO.getNextPage() : userPO.getPrevPage(), req, res);
			
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

	private void initTransaction(String optMenu, String purpose, String number) {
		try {
			if (!optMenu.equals("")) userPO.setOption(optMenu);
			if (!purpose.equals("")) userPO.setPurpose(purpose);
			userPO.setHeader1(user.getE01INT());
			if (!number.equals(""))	userPO.setAccNum(number);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
		} catch (Exception ex) {
			flexLog("Error getting userPO from session: " + ex);
		}
	}
	
	private void endTransaction(ELC056001Message msg) {
		userPO.setHeader18(msg.toString());
		userPO.setHeader19(msgError.toString());
		userPO.setHeader20(msg.toString());
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
			if (msg01 != null)	session.setAttribute("msg01", msg01);
			if (msg02 != null) session.setAttribute("msg02", msg02);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
