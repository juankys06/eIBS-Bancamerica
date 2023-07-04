package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (8/31/04 10:58:35 AM)
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

public class JSEEJ0000 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_LIST = 100;
	protected static final int A_DELETE = 200;
	protected static final int A_NEW = 300;
	protected static final int A_MAINT = 400;
	protected static final int R_LIST = 500;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEEJ0000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEEJ0000");

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
		
		EEJ000001Message msgSearch = new EEJ000001Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_enter_inq.jsp");
			callPage(LangPath + "EEJ0000_params_approv_limits_enter_inq.jsp", req, res);
		
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
		EEJ000001Message msgSearch = null;
		EEJ000001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EEJ000001Message) mc.getMessageRecord("EEJ000001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EEJ0000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
			try { 
				msgSearch.setE01EJLGRT(req.getParameter("E01EJLGRT").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01EJLGRT(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01EJLGRC(req.getParameter("E01EJLGRC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01EJLGRC(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01EJLJRQ(req.getParameter("E01EJLJRQ").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01EJLJRQ(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01EJLCCY(req.getParameter("E01EJLCCY").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01EJLCCY(userPO.getHeader13());
			}
		    	
			userPO.setHeader10(msgSearch.getE01EJLGRT());
			userPO.setHeader11(msgSearch.getE01EJLGRC());
			userPO.setHeader12(msgSearch.getE01EJLJRQ());
			userPO.setHeader13(msgSearch.getE01EJLCCY());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EEJ000001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EEJ000001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EEJ000001Message) newmessage;

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
				
				if (IsNotError) {
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_list.jsp");
						callPage(LangPath + "EEJ0000_params_approv_limits_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_enter_inq.jsp");
						callPage(LangPath + "EEJ0000_params_approv_limits_enter_inq.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			EEJ000001Message msgMT = (EEJ000001Message) beanList.getRecord();

			msgMT.setH01OPECOD("0009");
			mc.sendMessage(msgMT);
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EEJ000001")) {
		
				msgMT = (EEJ000001Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSEEJ0000?SCREEN=100");
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_list.jsp");
						callPage(LangPath + "EEJ0000_params_approv_limits_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
	
		EEJ000001Message msgClas = new EEJ000001Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgClas = (EEJ000001Message)mc.getMessageRecord("EEJ000001");
			msgClas.setH01USERID(user.getH01USR());
			msgClas.setH01PROGRM("EEJ0000");
			msgClas.setH01TIMSYS(getTimeStamp());
			msgClas.setH01OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgClas.fieldEnumeration();
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

			msgClas.send();
			msgClas.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EEJ000001")) {
				
				msgClas = (EEJ000001Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.params.JSEEJ0000?SCREEN=100'";
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
					ses.setAttribute("msgClas", msgClas);
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_new.jsp");
						callPage(LangPath + "EEJ0000_params_approv_limits_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
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
	
		EEJ000001Message msgMT = new EEJ000001Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMT = (EEJ000001Message)mc.getMessageRecord("EEJ000001");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("EEJ0000");
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
	
			msgMT.send();
			msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EEJ000001")) {
				
				msgMT = (EEJ000001Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.params.JSEEJ0000?SCREEN=100'";
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
					ses.setAttribute("msgMT", msgMT);
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0000_params_approv_limits_maint.jsp");
						callPage(LangPath + "EEJ0000_params_approv_limits_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
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
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_NEW :
							procActionNew(mc,msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaintenance(mc,msgUser, req, res, session);
							break;
						case A_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;	
						case R_LIST :
							procReqSearch(msgUser, req, res, session);
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