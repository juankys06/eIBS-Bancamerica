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
 */
public class JSELC0515 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	private static final int R_SWIFT_LIST = 1;
	private static final int R_INQ_SWIFT = 2;
	private static final int A_APPROVAL_SWIFT = 3;
				
	public JSELC0515() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0515");
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
					// Request
					case R_SWIFT_LIST :
						procReqIncomingSwiftList(req, res, screen);
						break;
					case R_INQ_SWIFT :
						procReqIncomingSwiftInquiry(req, res, screen);
						break;
					// Action
					case A_APPROVAL_SWIFT :
						procActionApprovalSwift(req, res, screen);
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
				flexLog("Socket used by JSELC0515 closed.");
			}
		}	
	}
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionApprovalSwift(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC051501Message msg01 = (ELC051501Message) msgHandle.initMessage("ELC051501", user.getH01USR(), "0002");
			initTransaction(String.valueOf(screen), "");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01LCMPRO(req.getParameter("PRODUCT"));
			} catch (Exception e) {
				msg01.setE01LCMPRO("");
			}
			try {
				msg01.setE01WAPFMT(req.getParameter("FMT"));
			} catch (Exception e) {
				msg01.setE01WAPFMT("");
			}
			try {
				msg01.setE01WAPSBK(req.getParameter("SBK"));
			} catch (Exception e) {
				msg01.setE01WAPSBK("");
			}
			try {
				msg01.setE01WAPRBK(req.getParameter("RBK"));
			} catch (Exception e) {
				msg01.setE01WAPRBK("");
			}
			try {
				msg01.setE01WAPRNO(req.getParameter("RNO"));
			} catch (Exception e) {
				msg01.setE01WAPRNO("");
			}
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg01 = (ELC051501Message) msgHandle.receiveMessage();
			putDataInSession(session, "msgELC", msg01);
			if (isNotError) {
				if (msg01.getH01FLGWK2().equals("S") || msg01.getH01FLGWK2().equals("R")) {
					//stand by
					flexLog("About to redirect request to the procReqIncomingSwiftList with specific parameters: ");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSELC0410?SCREEN=3&E01LCMACC=" + msg01.getE01LCMACC() + "&H01FLGMAS=N");							
				} else {
					//commercial
					flexLog("About to redirect request to the procReqIncomingSwiftList with specific parameters: ");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSELC0510?SCREEN=3&E01LCMACC=" + msg01.getE01LCMACC() + "&H01FLGMAS=N");							
				}
			} else {
				procReqIncomingSwiftList(req, res, screen);
			}
			return;
		
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
	private void procReqIncomingSwiftInquiry(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC051502Message msg02 = (ELC051502Message) msgHandle.initMessage("ELC051502", user.getH01USR(), "0005");
			initTransaction(String.valueOf(screen), "");
			try {
				msg02.setE02SWIRNO(req.getParameter("RNO"));
			} catch (Exception e) {
				msg02.setE02SWIRNO("");
			}
			try {
				msg02.setE02SWIRBK(req.getParameter("RBK"));
			} catch (Exception e) {
				msg02.setE02SWIRBK("");
			}
			msgHandle.sendMessage(msg02);
			msgError = new ELEERRMessage();
			jbList = msgHandle.receiveMessageList("ELC051502", "H02FLGMAS", msgError);
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				PageToCall = "ELC0515_inq_incoming_swift_message.jsp";
			} else {
				PageToCall = "error_viewer.jsp";
			}
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

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqIncomingSwiftList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			ELC051501Message msg01 = (ELC051501Message) msgHandle.initMessage("ELC051501", user.getH01USR(), "0015");
			initTransaction(String.valueOf(screen), "");
			msg01.setH01SCRCOD("01");
			try {
				msg01.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				msg01.setE01NUMREC("0");
			}
			msgHandle.sendMessage(msg01);
			msgError = new ELEERRMessage();
			jbList = msgHandle.receiveMessageList("ELC051501", "E01INDOPE", msgError);
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				PageToCall = "ELC0515_incoming_swift_list.jsp";
			} else {
				PageToCall = "ELC0510_lc_enter_maint.jsp";
			}
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
		msgError = null;
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
			if (jbList != null) session.setAttribute("msgList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
