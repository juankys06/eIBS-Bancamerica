/*
 * Created on Apr 4, 2008
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
public class JSELC0600 extends SuperServlet {
	
	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELC060001Message msg = null;
	JBObjList jbList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	private static final int STANDARD = 1;
	private static final int CUSTOMER = 5;
	private static final int CORRESPONDING_BANK = 35;
	
	public JSELC0600() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSELC0600");
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
					/********************************************************
					*		CALL ENTER SCREEN (1ST PAGE, FROM MAIN MENU)
					********************************************************/
					case STANDARD : // by product
						PageToCall = "ELC0600_sc_prod_enter.jsp";
						callPage(PageToCall, req, res);
						break; 
					case CUSTOMER : // by customer
						PageToCall = "ELC0600_sc_cust_enter.jsp"; 
						callPage(PageToCall, req, res);
						break;
					case CORRESPONDING_BANK : // by corresponding bank
						PageToCall = "ELC0600_sc_corresp_enter.jsp"; 
						callPage(PageToCall, req, res);
						break;
				}
				
				if (screen == STANDARD || screen == CUSTOMER || screen == CORRESPONDING_BANK) {
					return;
				} else {
					/*
					 * SCREEN RULES
					 * ---------------------------------------------------------
					 * 1. 100-199 is for products
					 * 2. 200-299 is for customer
					 * 3. 300-399 is for corresponding bank
					 * 
					 * 4. **1 is for submitting enter screens
					 * 5. **2 is for submitting new    on list screens
					 * 7. **4 is for submitting maint  on list screens
					 * 6. **5 is for submitting delete on list screens
					 * 7. **6 is for submitting info pages
					 * ---------------------------------------------------------
					 * The next section uses these rules to set nextPage, prevPage, opCode,
					 * and other message flags.
					 */
					switch (screen % 100) {
						case 1 : //        Submit enter Page
							requestList(req, res, screen); 
							break;					
						case 2 : //        submit NEW on list screens
							requestNew(req, res, screen); 
							break;					
						case 3 : //        submit MAINT on list screens
							requestMaintenance(req, res, screen); 
							break;					
						case 4 : //        submit DEL on list screens
							requestDelete(req, res, screen); 
							break;					
						case 5 : //        submit basic page
							requestPage(req, res, screen); 
							break;					
						case 6 : //        submit INQUIRY on list screens
							requestInquiry(req, res, screen); 
							break;					
						default: 		
							PageToCall = "MISC_not_available.jsp";
							callPage(PageToCall, req, res);
							break;
					}
				}
						
			} catch (IOException e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				s.close();
				flexLog("Socket used by JSELC0600 closed.");
			}
		}	
	}
	
	protected void requestList(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0015");
			initTransaction(String.valueOf(scr), "", scr);
			msgHandle.setFieldsFromPage(req, msg);
			String id = "";
			if (scr / 100 == 1) {
				id = msg.getE01RLCATY();
			} else {
				id = msg.getE01RLCCUN();
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				jbList = msgHandle.receiveMessageList("E01INDOPE");
				userPO.setAccNum(id);
			}
			putDataInSession(session);
			callPage(getPage(scr) + ((isNotError) ? "list.jsp" : "enter.jsp"), req, res);
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

	protected void requestNew(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(scr), "NEW", scr);
			msg.setE01RLCBNK(req.getParameter("NEWBNK"));
			msg.setE01RLCTAR(req.getParameter("NEWSTN"));
			if (scr / 100 == 1) {
				msg.setE01RLCATY(userPO.getAccNum());
			} else { 
				msg.setE01RLCCUN(userPO.getAccNum());
			}	
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC060001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			callPage(getPage(scr) + ((isNotError) ? "basic.jsp" : "list.jsp"), req, res);
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

	protected void requestMaintenance(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(scr), "MAINT", scr);
			msg.setE01RLCBNK(req.getParameter("BNK"));
			msg.setE01RLCTAR(req.getParameter("STN"));
			if (scr / 100 == 1) {
				msg.setE01RLCATY(req.getParameter("ATY"));
			} else {
				msg.setE01RLCCUN(req.getParameter("CUN"));
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC060001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			callPage(getPage(scr) + ((isNotError) ? "basic.jsp" : "list.jsp"), req, res);
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

	protected void requestDelete(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0009");
			initTransaction(String.valueOf(scr), "DELETE", scr);
			msg.setE01RLCBNK(req.getParameter("BNK"));
			msg.setE01RLCTAR(req.getParameter("STN"));
			if (scr / 100 == 1) {
				msg.setE01RLCATY(req.getParameter("ATY"));
			} else {
				msg.setE01RLCCUN(req.getParameter("CUN"));
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			putDataInSession(session);
			if (isNotError)	{
				msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0015");
				initTransaction(String.valueOf(scr), "DELETE", scr);
				msg.setE01RLCBNK(req.getParameter("E01RLCBNK"));
				if (scr / 100 == 1) {
					msg.setE01RLCATY(req.getParameter("E01RLCATY"));
				} else {
					msg.setE01RLCCUN(req.getParameter("E01RLCCUN"));
				}
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				isNotError = msgError.getERRNUM().equals("0");
				if (isNotError) {
					jbList = msgHandle.receiveMessageList("E01INDOPE");
				}
				putDataInSession(session);
				callPage(getPage(scr) + ((isNotError) ? "list.jsp" : "enter.jsp"), req, res);
			} else {	
				callPage(getPage(scr) + "list.jsp", req, res);
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

	protected void requestPage(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0005");
			initTransaction(String.valueOf(scr), "", scr);
			msgHandle.setFieldsFromPage(req, msg);
			String bank = msg.getE01RLCBNK();
			String id = "";
			if (scr / 100 == 1) {
				id = msg.getE01RLCATY();
			} else {
				id = msg.getE01RLCCUN();
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC060001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			if (isNotError) {
				msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0015");
				initTransaction(String.valueOf(scr), "", scr);
				msg.setE01RLCBNK(bank);
				if (scr / 100 == 1) {
					msg.setE01RLCATY(id);
				} else {
					msg.setE01RLCCUN(id);
				}
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				isNotError = msgError.getERRNUM().equals("0");
				if (isNotError) {
					jbList = msgHandle.receiveMessageList("E01INDOPE");
				}
				putDataInSession(session);
				callPage(getPage(scr) + "list.jsp", req, res);
			} else {
				callPage(getPage(scr) + "basic.jsp", req, res);
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

	protected void requestInquiry(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (ELC060001Message) msgHandle.initMessage("ELC060001", user.getH01USR(), "0004");
			initTransaction(String.valueOf(scr), "INQUIRY", scr);
			msg.setE01RLCBNK(req.getParameter("BNK"));
			msg.setE01RLCTAR(req.getParameter("STN"));
			if (scr / 100 == 1) {
				msg.setE01RLCATY(req.getParameter("ATY"));
			} else {
				msg.setE01RLCCUN(req.getParameter("CUN"));
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ELC060001Message) msgHandle.receiveMessage();
			putDataInSession(session);
			callPage(getPage(scr) + ((isNotError) ? "basic_inq.jsp" : "list.jsp"), req, res);
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

	private String getPage(int screen) {
		String result = "";
		int scr = screen / 100;

		switch (scr) {
			case 1: 
				result = "ELC0600_sc_prod_";
				break;
			case 2:	
				result = "ELC0600_sc_cust_";
				break;
			case 3:	
				result = "ELC0600_sc_corresp_";
				break;
			default: 
				result = "";
				break;	
		}	
		return result;
	}

	
	private void initTransaction(String optMenu, String purpose, int screen) {
		try {
			userPO.setOption(optMenu);
			userPO.setPurpose(purpose);
			int scr = screen / 100;
			switch (scr) {
				case 1: // BY PRODUCT
					msg.setH01FLGWK1("1");
					break;
				case 2:	// BY CUSTOMER
					msg.setH01FLGWK1("2");
					break;
				case 3:	// BY CORRESPONDENT BANK
					msg.setH01FLGWK1("2");
					msg.setH01FLGWK3("C");
					break;
				default: 
					break;	
			}	
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
		} catch (Exception ex) {
			flexLog("Error getting userPO from session: " + ex);
		}
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

	public void callPage(String page, HttpServletRequest req, HttpServletResponse res) {
		try {
			super.callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e.toString() + e.getMessage());
		}
		return; 
	}
	
}
