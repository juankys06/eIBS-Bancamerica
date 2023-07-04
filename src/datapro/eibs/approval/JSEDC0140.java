/*
 * Created on Jun 12, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.approval;

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
public class JSEDC0140 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	EDC014001Message msg01 = null;
	EDC014002Message msg02 = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSEDC0140() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0140");
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
					case 5 :
						procReqApprovalList(req, res, screen);
						break;
					case 7 :
						procInquiryPayment(req, res, screen);
						break;
					case 9 :
						procPaymentRejectApproval(req, res, screen);
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
				flexLog("Socket used by JSEDC0140 closed.");
			}
		}	
	}
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procPaymentRejectApproval(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "EDC0140_payments_list.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (EDC014001Message) msgHandle.initMessage("EDC014001", user.getH01USR(), "0009");
			int pos = req.getParameter("ITEM").indexOf('_');
			int len = req.getParameter("ITEM").length();
			String acc = req.getParameter("ITEM").substring(0, pos);
			String num = req.getParameter("ITEM").substring(pos + 1, len);
			msg01.setE01DCMACC(acc);
			msg01.setE01PMTNUM(num);
			msg01.setH01FLGWK1(req.getParameter("E02ACTION"));
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				procReqApprovalList(req,res,screen);	
			} else {
				putDataInSession(session);		
				callPage(PageToCall, req, res);
			}
		
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
	private void procInquiryPayment(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "EDC0140_coll_payment_inquiry.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (EDC014001Message) msgHandle.initMessage("EDC014001", user.getH01USR(), "0002");
			msg01.setE01DCMACC(req.getParameter("E01DCMACC"));
			msg01.setE01PMTNUM(req.getParameter("E01PMTNUM"));
			msg01.setE01DCMACD(req.getParameter("E01DCMACD"));
			userPO.setType(msg01.getE01DCMACD());
			msgHandle.sendMessage(msg01);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				//Receive General Data
				msg01 = (EDC014001Message) msgHandle.receiveMessage("EDC014001");
				//Receive Transaction Data
				msg02 = (EDC014002Message) msgHandle.receiveMessage("EDC014002");
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
	private void procReqApprovalList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "EDC0140_payments_list.jsp";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg01 = (EDC014001Message) msgHandle.initMessage("EDC014001", user.getH01USR(), "0001");
			msgHandle.sendMessage(msg01);
			jbList = msgHandle.receiveMessageList("H01FLGMAS", JSEIBSProp.getMaxIterations());
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
			if (msg01 != null) session.setAttribute("msg01", msg01);
			if (msg02 != null) session.setAttribute("msg02", msg02);
			if (jbList != null) session.setAttribute("appList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
