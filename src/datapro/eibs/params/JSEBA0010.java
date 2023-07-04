package datapro.eibs.params;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EBA001001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageContextHandler;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * Comercial Letter Of Credit Maintenance
 */
public class JSEBA0010 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	protected static final int R_ENTER = 1;
	protected static final int A_ENTER = 2;
	protected static final int A_MAINT = 3;

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEBA0010() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEBA0010");
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
					case R_ENTER :
						procReqEnterMaintenance(req, res, screen);
						break;
					case A_ENTER :
						procActionEnterMaintenance(req, res, screen);
						break;
					case A_MAINT :
						procActionMaintenance(req, res, screen);
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
				flexLog("Socket used by JSEBA0010 closed.");
			}
		}	
	}
	
	private String formatString(String str, int len) {
		String result = "";
		if (str.length() < len) {
			result = "0" + str;
			result = formatString(result, len);
		} else {
			result = str;
		}
		return result;
	}
	
	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EBA001001Message msg = (EBA001001Message)  msgHandle.initMessage("EBA001001", user.getH01USR(), "0005");
			msgHandle.setFieldsFromPage(req, msg);
			//Change ABA Number to 10 Characaters
			msg.setE01BAVABA(formatString(msg.getE01BAVABA().trim(), 10));
			if (msg.getE01BAVPYT().equals("C")) {
				msg.setE01BAVVAN(formatString(msg.getE01BAVVAN().trim(), 12));
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EBA001001Message) msgHandle.receiveMessage();
			if (isNotError) { // There are no errors 
				PageToCall = "EBA0010_enter_vendor.jsp";
			} else {
				PageToCall = "EBA0010_vendor_basic.jsp";
			}
			putDataInSession(session, "vendor", msg);
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
	private void procActionEnterMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "";
		try {
			String opeCode = "";
			try {
				opeCode = req.getParameter("E01OPETYP");
			} catch (Exception e){
				opeCode = "0002";
			}
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EBA001001Message msg = (EBA001001Message)  msgHandle.initMessage("EBA001001", user.getH01USR(), opeCode);
			msg.setH01SCRCOD("01");
			try {
				msg.setE01BAVNUM(req.getParameter("E01BAVNUM"));
			} catch (Exception e){
				msg.setE01BAVNUM("");
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EBA001001Message) msgHandle.receiveMessage();
			if (isNotError) { // There are no errors 
				PageToCall = "EBA0010_vendor_basic.jsp";
			} else {
				PageToCall = "EBA0010_enter_vendor.jsp";
			}
			putDataInSession(session, "vendor", msg);
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
	private void procReqEnterMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) {
		HttpSession session = (HttpSession) req.getSession(false);
		msgError = new ELEERRMessage();
		String PageToCall = "EBA0010_enter_vendor.jsp";
		putDataInSession(session, null, null);
		callPage(PageToCall, req, res);
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
	
	private void putDataInSession(HttpSession session, String name, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (msg != null) session.setAttribute(name, msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
