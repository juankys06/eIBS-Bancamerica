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

public class JSEDC0600 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";

	static final int R_ENTER = 1;
	static final int R_LIST = 2;
	static final int A_LIST = 3;
	static final int A_MAINT = 4;
	static final int A_DELETE = 9;
	static final int R_CUST_ENTER = 5;
	static final int R_CUST_LIST = 6;
	static final int A_CUST_LIST = 7;
	static final int A_CUST_MAINT = 8;
	static final int A_CUST_DELETE = 10;

	public JSEDC0600() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0600");
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
					case R_ENTER: // '\001'
						procReqEnterBank(req, res, screen);
						break;
					case R_LIST: // '\002'
						procReqList(req, res, screen);
						break;
					case A_LIST: // '\003'
						procActionList(req, res, screen);
						break;
					case A_MAINT: // '\004'
						procActionMaintenance(req, res, screen);
						break;
					case R_CUST_ENTER: // '\005'
						procReqCustEnterBank(req, res, screen);
						break;
					case R_CUST_LIST: // '\006'
						procReqCustList(req, res, screen);
						break;
					case A_CUST_LIST: // '\007'
						procActionCustList(req, res, screen);
						break;
					case A_CUST_MAINT: // '\008'
						procActionCustMaintenance(req, res, screen);
						break;
					case A_DELETE: // '\009'
						procActionDelete(req, res, screen);
						break;
					case A_CUST_DELETE: // '\010'
						procActionCustDelete(req, res, screen);
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
				flexLog("Socket used by JSEDC0600 closed.");
			}
		}	
	}	

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionCustDelete(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0009");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("2");
			try {
				msg.setE01RCOBNK(req.getParameter("BNK"));
			} catch(Exception e) { 
			}
			try {
				msg.setE01RCOTAR(req.getParameter("STN"));
			} catch(Exception e) { 
			}
			try {
				msg.setE01RCOCUN(req.getParameter("CUN"));
			} catch(Exception e) { 
			}
			initTransaction(String.valueOf(screen), "D");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			putDataInSession(session, null, null);
			procReqCustList(req, res, screen);
		
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
	private void procActionDelete(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0009");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			try {
				msg.setE01RCOBNK(req.getParameter("BNK"));
			} catch(Exception e) { 
			}
			try {
				msg.setE01RCOTAR(req.getParameter("STN"));
			} catch(Exception e) { 
			}
			try {
				msg.setE01RCOATY(req.getParameter("ATY"));
			} catch(Exception e) { 
			}
			initTransaction(String.valueOf(screen), "D");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			putDataInSession(session, null, null);
			procReqList(req, res, screen);
		
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
	private void procActionCustMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0005");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("2");
			String opt = req.getParameter("OPT");
			initTransaction(String.valueOf(screen), opt);
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC060001Message) msgHandle.receiveMessage();
			putDataInSession(session, "sc", msg);
			if (isNotError) {
				procReqCustList(req, res, screen);
			} else {	
				String PageToCall = "EDC0600_sc_cust_basic.jsp";
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
	private void procActionCustList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = null;
			String opt = req.getParameter("opt");
			if (opt.equals("N")) {
				msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0001");
				try {
					msg.setE01RCOBNK(req.getParameter("NEWBNK"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOTAR(req.getParameter("NEWSTN"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOCUN(req.getParameter("NEWCUN"));
				} catch(Exception e) { 
				}
			} else {
				msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0002");
				try {
					msg.setE01RCOBNK(req.getParameter("BNK"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOTAR(req.getParameter("STN"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOCUN(req.getParameter("CUN"));
				} catch(Exception e) { 
				}
			}
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("2");
			initTransaction(String.valueOf(screen), opt);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC060001Message) msgHandle.receiveMessage();
			putDataInSession(session, "sc", msg);
			String PageToCall = "EDC0600_sc_cust_list.jsp";
			if (isNotError) {
				PageToCall = "EDC0600_sc_cust_basic.jsp";
			}	
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
	private void procReqCustList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0015");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("2");
			try {
				msg.setE01RCOBNK(req.getParameter("E01RCOBNK"));
			} catch(Exception e) {
				msg.setE01RCOBNK(req.getParameter("BNK"));
			}
			try {
				msg.setE01RCOCUN(req.getParameter("E01RCOCUN"));
			} catch(Exception e) {
				msg.setE01RCOCUN(req.getParameter("CUN"));
			}
			initTransaction(String.valueOf(screen), "M");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			jbList = msgHandle.receiveMessageList("E01INDOPE");
			putDataInSession(session, null, null);
			String PageToCall = "MISC_not_available.jsp";
			if (isNotError) {
				PageToCall = "EDC0600_sc_cust_list.jsp";
			} else {
				PageToCall = "EDC0600_sc_cust_enter_selection.jsp";
			}
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
	private void procReqCustEnterBank(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "EDC0600_sc_cust_enter_selection.jsp";
		initTransaction(String.valueOf(screen), "M");
		putDataInSession(session, null, null);
		callPage(PageToCall, req, res);
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionMaintenance(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0005");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			String opt = req.getParameter("OPT");
			initTransaction(String.valueOf(screen), opt);
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC060001Message) msgHandle.receiveMessage();
			putDataInSession(session, "sc", msg);
			if (isNotError) {
				procReqList(req, res, screen);
			} else {	
				String PageToCall = "EDC0600_sc_basic.jsp";
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
	private void procActionList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = null;
			String opt = req.getParameter("opt");
			if (opt.equals("N")) {
				msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0001");
				try {
					msg.setE01RCOBNK(req.getParameter("NEWBNK"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOTAR(req.getParameter("NEWSTN"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOATY(req.getParameter("NEWATY"));
				} catch(Exception e) { 
				}
			} else {
				msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0002");
				try {
					msg.setE01RCOBNK(req.getParameter("BNK"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOTAR(req.getParameter("STN"));
				} catch(Exception e) { 
				}
				try {
					msg.setE01RCOATY(req.getParameter("ATY"));
				} catch(Exception e) { 
				}
			}
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			initTransaction(String.valueOf(screen), opt);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EDC060001Message) msgHandle.receiveMessage();
			putDataInSession(session, "sc", msg);
			String PageToCall = "EDC0600_sc_list.jsp";
			if (isNotError) {
				if (opt.equals("I")) {
					PageToCall = "EDC0600_sc_basic_inq.jsp";
				} else {	
					PageToCall = "EDC0600_sc_basic.jsp";
				}
			}	
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
	private void procReqList(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC060001Message msg = (EDC060001Message) msgHandle.initMessage("EDC060001", user.getH01USR(), "0015");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			try {
				msg.setE01RCOBNK(req.getParameter("E01RCOBNK"));
			} catch(Exception e) {
				msg.setE01RCOBNK(req.getParameter("BNK"));
			}
			try {
				msg.setE01RCOATY(req.getParameter("E01RCOATY"));
			} catch(Exception e) {
				msg.setE01RCOATY(req.getParameter("ATY"));
			}
			initTransaction(String.valueOf(screen), "M");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			jbList = msgHandle.receiveMessageList("E01INDOPE");
			putDataInSession(session, null, null);
			String PageToCall = "MISC_not_available.jsp";
			if (isNotError) {
				PageToCall = "EDC0600_sc_list.jsp";
			} else {
				PageToCall = "EDC0600_sc_enter_selection.jsp";
			}
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
	private void procReqEnterBank(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		String PageToCall = "EDC0600_sc_enter_selection.jsp";
		initTransaction(String.valueOf(screen), "M");
		putDataInSession(session, null, null);
		callPage(PageToCall, req, res);
	}

	private void initTransaction(String optMenu, String purpose) {
		try {
			userPO.setOption(optMenu);
			userPO.setPurpose(purpose);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
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
			if (jbList != null) session.setAttribute("ccList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
					
}