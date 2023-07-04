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
public class JSELC0200 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC020001Message msg = null;
	JBObjList jbList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSELC0200() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0200");
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
					case 1 : // Enter (Control de Documentos Recibidos)
						PageToCall = "ELC0200_lc_doc_enter.jsp";
						callPage(PageToCall, req, res);
						break;
					case 2 : // Call list (submit 1st page)
						requestList(req, res, screen);
						break;
					case 3 : // CALL LC DOCUMENTS NEW
						requestNew(req, res, screen);
						break;
					case 4 : // CALL LC DOCUMENTS MAINT
						requestMaintenance(req, res, screen);
						break;
					case 5 :
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
				flexLog("Socket used by JSELC0200 closed.");
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
		String PageToCall = "ELC0200_lc_doc_info.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC020001Message) msgHandle.initMessage("ELC020001", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC020001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			if (isNotError) {
				requestList(req, res, 2);
			} else
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
	private void requestMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0200_lc_doc_info.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC020001Message) msgHandle.initMessage("ELC020001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			try {
				msg.setE01LCMACC(userPO.getAccNum());
				msg.setE01LCIDNO(req.getParameter("E01LCIDNO"));
			} catch (Exception e) {
				flexLog("E01LCIDNO not available from page");
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC020001Message) msgHandle.receiveMessage();
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
	private void requestNew(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0200_lc_doc_info.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC020001Message) msgHandle.initMessage("ELC020001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(screen), "");
			msg.setE01LCMACC(userPO.getAccNum());
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC020001Message) msgHandle.receiveMessage();
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
	private void requestList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "ELC0200_lc_doc_list.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC020001Message) msgHandle.initMessage("ELC020001", user.getH01USR(), "0015");
			initTransaction(String.valueOf(screen), "");
			try {
				msg.setE01LCMACC(req.getParameter("E01LCMACC"));
				userPO.setAccNum(msg.getE01LCMACC());
			} catch (Exception e) {
				flexLog("E01LCMACC not available from page");
			}
			try {
				msg.setE01LCIDNO(req.getParameter("E01LCIDNO"));
			} catch (Exception e) {
				flexLog("E01LCIDNO not available from page");
			}
			msgHandle.sendMessage(msg);
			jbList = msgHandle.receiveMessageList("H01FLGMAS");
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
	
	private void putDataInSession(HttpSession session) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute("msg01", msg);
			if (jbList != null) session.setAttribute("jbList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
