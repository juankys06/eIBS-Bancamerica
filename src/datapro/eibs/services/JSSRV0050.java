package datapro.eibs.services;

/**
 * Insert the type's description here.
 * Creation date: (10/07/04 7:05:43 PM)
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

public class JSSRV0050 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_LIST = 100;
	protected static final int A_DELETE = 200;
	protected static final int A_NEW = 300;
	protected static final int A_MAINT = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSSRV0050() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSSRV0050");

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
		SRV005001Message msgSearch = null;
		SRV005001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (SRV005001Message) mc.getMessageRecord("SRV005001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("SRV0050");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("SRV002001 Message Sent");
		
			// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("SRV005001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (SRV005001Message) newmessage;

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
					flexLog("About to call Page: " + LangPath + "SRV0050_services_cias_list.jsp");
					callPage(LangPath + "SRV0050_services_cias_list.jsp", req, res);						
				
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
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			SRV005001Message msgMT = (SRV005001Message) beanList.getRecord();		
			msgMT.setH01OPECOD("0009");
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("SRV005001")) {
		
				msgMT = (SRV005001Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.services.JSSRV0050?SCREEN=100");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "SRV0050_services_cias_list.jsp");
						callPage(LangPath + "SRV0050_services_cias_list.jsp", req, res);						

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
	
		SRV005001Message msgCia = new SRV005001Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgCia = (SRV005001Message)mc.getMessageRecord("SRV005001");
			msgCia.setH01USERID(user.getH01USR());
			msgCia.setH01PROGRM("SRV0050");
			msgCia.setH01TIMSYS(getTimeStamp());
			msgCia.setH01OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgCia.fieldEnumeration();
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

			msgCia.send();
			msgCia.destroy();
			
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

			if (newmessage.getFormatName().equals("SRV005001")) {
				
				msgCia = (SRV005001Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("error", null);
					ses.setAttribute("msgCia", null);
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.services.JSSRV0050?SCREEN=100'";
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
					ses.setAttribute("msgCia", msgCia);
					try {
						flexLog("About to call Page: " + LangPath + "SRV0050_services_cias_new.jsp");
						callPage(LangPath + "SRV0050_services_cias_new.jsp", req, res);						

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
	
		SRV005001Message msgMT = new SRV005001Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMT = (SRV005001Message)mc.getMessageRecord("SRV005001");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("SRV0050");
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
	
			if (newmessage.getFormatName().equals("SRV005001")) {
				
				msgMT = (SRV005001Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("error", null);
					ses.setAttribute("msgMT", null);
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.services.JSSRV0050?SCREEN=100'";
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
						flexLog("About to call Page: " + LangPath + "SRV0050_services_cias_maint.jsp");
						callPage(LangPath + "SRV0050_services_cias_maint.jsp", req, res);						
	
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

			int screen = A_LIST;

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