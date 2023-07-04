/*
 * Created on Apr 11, 2008
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
 */
public class JSEWP0010 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	ESS0030DSMessage user = null;
	EWP001001Message msg = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";
	
	public JSEWP0010() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEWP0010");
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
					case 1 : // Call List
						requestList(req, res, screen); 
						break;					
					case 0 : // New Clause
						requestNew(req, res, screen); 
						break;					
					case 2 :
						requestMaintenance(req, res, screen); 
						break;					
					case 5 :
						actionPage(req, res, screen); 
						break;					
					case 9 :
						requestDelete(req, res, screen); 
						break;					
					case 11 :
						requestHelpList(req, res, screen); 
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
				flexLog("Socket used by JSEWP0010 closed.");
			}
		}	
	}
	
	protected void requestHelpList(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0015");
			initTransaction(String.valueOf(scr), "");
			try {
				msg.setE01SELCDE(req.getParameter("TYPE"));
			} catch (Exception e) {
			}
			try {
				userPO.setRedirect(req.getParameter("SERVLET"));
			} catch (Exception e) {
			}
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				jbList = msgHandle.receiveMessageList("E01INDOPE");
			}
			putDataInSession(session, msg);
			callPage("EWP0010_lc_clauses_help_list.jsp", req, res);
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
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0009");
			initTransaction(String.valueOf(scr), "DELETE");
			msg.setE01CLSCDE(req.getParameter("E01CLSCDE"));
			msg.setE01CLSTXN(req.getParameter("E01CLSTXN"));
			msg.setE01CLSDSC(req.getParameter("E01CLSDSC"));
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			putDataInSession(session, msg);
			if (isNotError)	{
				requestList(req, res, scr);			
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
	protected void actionPage(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0005");
			initTransaction(String.valueOf(scr), "");
			msgHandle.setFieldsFromPage(req, msg);
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EWP001001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg);
			if (isNotError) {
				requestList(req, res, scr);
			} else {
				callPage("EWP0010_lc_clause.jsp", req, res);
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
	
	protected void requestMaintenance(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0002");
			initTransaction(String.valueOf(scr), "MAINTENANCE");
			msg.setE01CLSCDE(req.getParameter("E01CLSCDE"));
			msg.setE01CLSTXN(req.getParameter("E01CLSTXN"));
			msg.setE01CLSDSC(req.getParameter("E01CLSDSC"));
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EWP001001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg);
			if (isNotError) {
				callPage("EWP0010_lc_clause.jsp", req, res);
			} else {
				requestList(req, res, scr);
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

	protected void requestNew(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0001");
			initTransaction(String.valueOf(scr), "NEW");
			msg.setE01CLSCDE(req.getParameter("CLSCDE"));
			msg.setE01CLSTXN(req.getParameter("CLSTXN"));
			msg.setE01CLSDSC(req.getParameter("CLSDSC"));
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (EWP001001Message) msgHandle.receiveMessage();
			putDataInSession(session, msg);
			callPage((isNotError) ? "EWP0010_lc_clause.jsp" : "EWP0010_lc_clauses_list.jsp", req, res);
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

	protected void requestList(HttpServletRequest req, HttpServletResponse res, int scr) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);

		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msg = (EWP001001Message) msgHandle.initMessage("EWP001001", user.getH01USR(), "0015");
			initTransaction(String.valueOf(scr), "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				jbList = msgHandle.receiveMessageList("E01INDOPE");
			}
			putDataInSession(session, msg);
			callPage("EWP0010_lc_clauses_list.jsp", req, res);
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
			userPO.setID(user.getE01INT());
			
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
			session.setAttribute("msg", msg);
			session.setAttribute("jbList", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}
