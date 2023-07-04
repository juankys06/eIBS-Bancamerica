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

public class JSEPR0360 extends datapro.eibs.master.SuperServlet {

	protected static final int R_LIST 		= 100;
	protected static final int A_DELETE 	= 200;
	protected static final int A_NEW		= 400;

	protected String LangPath = "S";

	/**
	 * JSEPR0360 constructor comment.
	 */
	public JSEPR0360() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR0360");

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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EPR036001Message msgSearch = null;
		EPR036001Message msgList = null;
		
		JBObjList beanList = null;
		boolean IsNotError = false;

		UserPos userPO = new UserPos();

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR036001Message) mc.getMessageRecord("EPR036001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0360");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
					    	
			userPO.setBank(user.getE01UBK());
			userPO.setBranch(user.getE01UBR());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR036001 Message Sent");
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EPR036001")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EPR036001Message) newmessage;

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

				try {
					flexLog("About to call Page: " + LangPath + "EPR0360_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0360_foreign_currency_exchange_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
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
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			beanList = (JBObjList) ses.getAttribute("mtList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			EPR036001Message msgMT = (EPR036001Message) beanList.getRecord();		
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
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEPR0360?SCREEN=100&Opt=" + userPO.getHeader10());
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0360_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0360_foreign_currency_exchange_list.jsp", req, res);						
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
	
		EPR036001Message msgMT = new EPR036001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
	
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String action = req.getParameter("H01FLGWK1");

		try {
			flexLog("Send Initial Data");
			msgMT = (EPR036001Message)mc.getMessageRecord("EPR036001");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("EPR0330");
			msgMT.setH01TIMSYS(getTimeStamp());
			msgMT.setH01OPECOD("0001");

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

			msgMT.setH01FLGWK1(action);
			mc.sendMessage(msgMT);
		
			// Receive Error Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

			// Receive Data
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR036001")) {
				msgMT = (EPR036001Message) newmessage;
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

			if (IsNotError) {
				if (action.equals("V")) {
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgInst", msgMT);

					flexLog("About to call Page: " + LangPath + "EPR0360_foreign_currency_exchange_new.jsp");
					callPage(LangPath + "EPR0360_foreign_currency_exchange_new.jsp?", req, res);
				} else if (action.equals("S")) {
					msgMT.destroy();
					msgMT = new EPR036001Message();

					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgInst", msgMT);

					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0360?SCREEN=100'";
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
					ses.setAttribute("msgInst", msgMT);

					flexLog("About to call Page: " + LangPath + "EPR0360_foreign_currency_exchange_new.jsp");
					callPage(LangPath + "EPR0360_foreign_currency_exchange_new.jsp?", req, res);						

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

			int screen = R_LIST;

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
						case R_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;						
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_NEW :
							procActionNew(mc,msgUser, req, res, session);
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