package datapro.eibs.forex;  

/**
 * Insert the type's description here.
 * Creation date: (7/6/04 1:15:00 PM)
 * @author: Manuel Justo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEPR0320 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH 	= 1;
	protected static final int R_LIST 		= 100;
	protected static final int R_NEW 		= 300;
	protected static final int R_MAINT 		= 500;
	protected static final int A_DELETE 	= 200;
	protected static final int A_NEW 		= 400;
	protected static final int A_MAINT 		= 600;
	

	protected String LangPath = "S";

	/**
	 * JSEPR0320 constructor comment.
	 */
	public JSEPR0320() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR0320");

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
			flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_enter_inq.jsp");
			callPage(LangPath + "EPR0320_foreign_currency_exchange_enter_inq.jsp", req, res);
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
		EPR032001Message msgSearch = null;
		EPR032001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR032001Message) mc.getMessageRecord("EPR032001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0320");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			//all the fields here
			try { 
				msgSearch.setE01COMBNK(req.getParameter("E01COMBNK").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01COMBNK(userPO.getBank());
			}
			try { 
				msgSearch.setE01COMINS(req.getParameter("E01COMINS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01COMINS(userPO.getProdCode());
			}
			try { 
				msgSearch.setE01COMCCY(req.getParameter("E01COMCCY").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01COMCCY(userPO.getCurrency());
			}
			try { 
				msgSearch.setE01COMCUS(req.getParameter("E01COMCUS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01COMCUS(userPO.getCusNum());
			}

			userPO.setBank(msgSearch.getE01COMBNK());
			userPO.setProdCode(msgSearch.getE01COMINS());
			userPO.setCurrency(msgSearch.getE01COMCCY());
			userPO.setCusNum(msgSearch.getE01COMCUS());
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR032001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}

			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EPR032001")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EPR032001Message) newmessage;

					marker = msgList.getH01FLGMAS();
					userPO.setHeader20(msgList.getE01CNTBCU());

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
					flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0320_foreign_currency_exchange_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_enter_inq.jsp");
					callPage(LangPath + "EPR0320_foreign_currency_exchange_enter_inq.jsp", req, res);						
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
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		
		try {
			beanList = (JBObjList) ses.getAttribute("mtList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			EPR032001Message msgMT = (EPR032001Message) beanList.getRecord();		
			msgMT.setH01OPECOD("0009");
			mc.sendMessage(msgMT);
			msgMT.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			if (IsNotError) {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEPR0320?SCREEN=100");
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0320_foreign_currency_exchange_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EPR032002Message msgMT = new EPR032002Message();
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			flexLog("Send Initial Data");
			msgMT = (EPR032002Message)mc.getMessageRecord("EPR032002");
			msgMT.setH02USERID(user.getH01USR());
			msgMT.setH02PROGRM("EPR0320");
			msgMT.setH02TIMSYS(getTimeStamp());
			msgMT.setH02OPECOD("0001");

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

			mc.sendMessage(msgMT);
			
			// Receive Error Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (IsNotError) {
				msgMT.destroy();
				ses.setAttribute("userPO", userPO);
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0320?SCREEN=100'";
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
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgInst", msgMT);
				try {
					flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_new.jsp");
					callPage(LangPath + "EPR0320_foreign_currency_exchange_new.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
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
	
		EPR032001Message msgMT = new EPR032001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			flexLog("Send Initial Data");
			msgMT = (EPR032001Message)mc.getMessageRecord("EPR032001");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("EPR0320");
			msgMT.setH01TIMSYS(getTimeStamp());
			msgMT.setH01OPECOD("0005");
	
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
	
			mc.sendMessage(msgMT);
			msgMT.destroy();
			
			// Receive Error Message
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			if (IsNotError) {
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0320?SCREEN=100'";
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
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgInst", msgMT);
				try {
					flexLog("About to call Page: " + LangPath + "EPR0320_foreign_currency_exchange_maint.jsp");
					callPage(LangPath + "EPR0320_foreign_currency_exchange_maint.jsp", req, res);						
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
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_NEW :
							procActionNew(mc,msgUser, req, res, session);
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