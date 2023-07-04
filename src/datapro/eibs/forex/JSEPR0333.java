package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (4/6/05 5:15:00 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEPR0333 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH 	= 1;
	protected static final int R_LIST 		= 100;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "S";

	/**
	 * JSEPR0333 constructor comment.
	 */
	public JSEPR0333() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR0333");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		UserPos userPO = new UserPos();
		ses.setAttribute("userPO", userPO);
		
		try {
			flexLog("About to call Page: " + LangPath + "EPR0333_foreign_currency_exchange_enter_inq.jsp");
			callPage(LangPath + "EPR0333_foreign_currency_exchange_enter_inq.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EPR033301Message msgSearch = null;
		EPR033301Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR033301Message) mc.getMessageRecord("EPR033301");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0333");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			//all the fields here
			try { 
				msgSearch.setE01REQCUS(req.getParameter("E01REQCUS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCUS(userPO.getCusNum());
			}
			try { 
				msgSearch.setE01REQLD1(req.getParameter("E01REQLD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLD1(userPO.getHeader1());
			}
			try { 
				msgSearch.setE01REQLD2(req.getParameter("E01REQLD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLD2(userPO.getHeader2());
			}
			try { 
				msgSearch.setE01REQLD3(req.getParameter("E01REQLD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLD3(userPO.getHeader3());
			}
			try { 
				msgSearch.setE01REQLT1(req.getParameter("E01REQLT1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLT1(userPO.getHeader4());
			}
			try { 
				msgSearch.setE01REQLT2(req.getParameter("E01REQLT2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLT2(userPO.getHeader5());
			}
			try { 
				msgSearch.setE01REQLT3(req.getParameter("E01REQLT3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQLT3(userPO.getHeader6());
			}
			
			userPO.setCusNum(msgSearch.getE01REQCUS());
			userPO.setHeader1(msgSearch.getE01REQLD1());
			userPO.setHeader2(msgSearch.getE01REQLD2());
			userPO.setHeader3(msgSearch.getE01REQLD3());
			userPO.setHeader4(msgSearch.getE01REQLT1());
			userPO.setHeader5(msgSearch.getE01REQLT2());
			userPO.setHeader6(msgSearch.getE01REQLT3());
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR033301 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}

			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EPR033301")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EPR033301Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("mtList", beanList);
				ses.setAttribute("userPO", userPO);				
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
			if (IsNotError) {
				try {
					flexLog("About to call Page: " + LangPath + "EPR0333_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0333_foreign_currency_exchange_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0333_foreign_currency_exchange_enter_inq.jsp");
					callPage(LangPath + "EPR0333_foreign_currency_exchange_enter_inq.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EPR033302Message msgMT = new EPR033302Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String action = req.getParameter("H02FLGWK1");
		String change = req.getParameter("H02FLGWK2");
		

		try {
			flexLog("Send Initial Data");
			msgMT = (EPR033302Message)mc.getMessageRecord("EPR033302");
			msgMT.setH02USERID(user.getH01USR());
			msgMT.setH02PROGRM("EPR0333");
			msgMT.setH02TIMSYS(getTimeStamp());
			msgMT.setH02OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgMT.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgMT.setH02FLGWK1(action);
			msgMT.setH02FLGWK2(change);
			
			mc.sendMessage(msgMT);
			
			// Receive Error Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				if (!IsNotError) {
					if (msgError.getERNU01().equals("4120")) {
						IsNotError = true;
					}
				}
				flexLog("IsNotError = " + IsNotError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

			// Receive Data
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR033302")) {
				msgMT = (EPR033302Message) newmessage;
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

			if (IsNotError) {
				if (action.equals("V")) {
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCanje", msgMT);

					flexLog("About to call Page: " + LangPath + "EPR0333_foreign_currency_exchange_maint.jsp");
					callPage(LangPath + "EPR0333_foreign_currency_exchange_maint.jsp?", req, res);
				} else if (action.equals("P") || action.equals("E")) {

					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCanje", msgMT);

					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0333?SCREEN=100'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.location.href = \""+ href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} 	
			} else {
				try {
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCanje", msgMT);

					flexLog("About to call Page: " + LangPath + "EPR0333_foreign_currency_exchange_maint.jsp");
					callPage(LangPath + "EPR0333_foreign_currency_exchange_maint.jsp?", req, res);						

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_SEARCH;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_SEARCH :							
							procReqSearch(msgUser, req, res, session);
							break;						
						case R_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;						
						case A_MAINT :
							procActionMaintenance(mc,msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
}