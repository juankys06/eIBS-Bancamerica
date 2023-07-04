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
public class JSELC0100 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC010001Message msg01 = null;
	ELC056001Message msgInq = null;
	ELC056002Message msgCon = null;
	ELC010002Message msg02 = null;
	JBObjList jbList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSELC0100() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0100");
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
					case 3 : // ENTER / CALL LIST (from side menu)
					case 6 : // ENTER / CALL LIST (from maintenance help)
						requestList(req, res, screen);
						break;
					case 5 : // Call basic page from List
						requestNegotiation(req, res, screen);
						break;
					case 8 : // Call basic page from optMenu
						requestNegotiation(req, res, screen);
						break;
					case 7 : // Call confirmation page from optMenu
						requestConfirmation(req, res, screen);
						break;
					case 2 :
						requestAction(req, res, screen);
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
				flexLog("Socket used by JSELC0100 closed.");
			}
		}	
	}			

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void requestAction(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg02 = (ELC010002Message) msgHandle.initMessage("ELC010002", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msgHandle.setFieldsFromPage(req, msg02);
			msgHandle.sendMessage(msg02);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			requestList(req, res, 3); //Request Approval List
		
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
	private void requestConfirmation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgCon = (ELC056002Message) msgHandle.initMessage("ELC056002", user.getH01USR(), "0004");
			initTransaction(String.valueOf(screen), "");
			msgCon.setE02LCRNUM(userPO.getHeader1());
			msgCon.setE02DRWNUM(userPO.getHeader2());
			msgHandle.sendMessage(msgCon);
			//msgError = msgHandle.receiveErrorMessage();
			//boolean isNotError = msgError.getERRNUM().equals("0");
			//msgCon = (ELC056002Message) msgHandle.receiveMessage();
			MessageRecord newmsg = msgHandle.receiveMessage();
			boolean isNotError = true;
			if (newmsg.getFormatName().equals("ELEERRMessage")) {
				msgError = (ELEERRMessage) newmsg;
				PageToCall = "ELC0100_lc_nego_apr_list.jsp";
			} else {
				msgCon = (ELC056002Message) newmsg;
				PageToCall = "ELC0100_lc_nego_view_conf.jsp";
			}
			putDataInSession(session, "msg", msgCon);
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
	private void requestNegotiation(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgInq = (ELC056001Message) msgHandle.initMessage("ELC056001", user.getH01USR(), "0004");
			initTransaction(String.valueOf(screen), "VIEW");
			try {
				msgInq.setE01LCRNUM(req.getParameter("E02LCRNUM"));
				userPO.setHeader1(req.getParameter("E02LCRNUM"));
			} catch (Exception e) {
				msgInq.setE01LCRNUM(userPO.getHeader1());
			}
			try {
				msgInq.setE01DRWNUM(req.getParameter("E02DRWNUM"));
				userPO.setHeader2(req.getParameter("E02DRWNUM"));
			} catch (Exception e) {
				msgInq.setE01DRWNUM(userPO.getHeader2());
			}
			msgHandle.sendMessage(msgInq);
			//msgError = msgHandle.receiveErrorMessage();
			//boolean isNotError = msgError.getERRNUM().equals("0");
			msgInq = (ELC056001Message) msgHandle.receiveMessage();
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
				case 3:
				case 5: PageToCall = "ELC0560_lc_nego_ref_readonly.jsp"; break;
				default: break;
			}
			putDataInSession(session, "msg01", msgInq);
			if (screen == 5) {
				requestList(req, res, screen);
			} else {
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
	private void requestList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "MISC_not_available.jsp";
		switch (screen) {
			case 3: PageToCall = "ELC0100_lc_nego_apr_list.jsp"; break;
			case 6: PageToCall = "ELC0100_lc_nego_help_list.jsp"; break;
		}
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (ELC010001Message) msgHandle.initMessage("ELC010001", user.getH01USR(), "0015");
			initTransaction(String.valueOf(screen), "");
			msgHandle.sendMessage(msg01);
			jbList = msgHandle.receiveMessageList("H01FLGMAS");
			putDataInSession(session, "", null);
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
	
	private void putDataInSession(HttpSession session, String msg_name, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

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
