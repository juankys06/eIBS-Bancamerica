package datapro.eibs.forex;  

/**
 * Insert the type's description here.
 * Creation date: (7/6/04 1:15:00 PM)
 * @author: Manuel Justo
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR035001Message;
import datapro.eibs.beans.EPR035002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;





	public class JSEPR0350 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH 	= 1;
	protected static final int R_LIST 		= 100;
	protected static final int A_DELETE 	= 200;
	protected static final int A_APPROVAL	= 400;
	protected static final int A_REJECT		= 700;
	// TIPOS DE INTRUMENTOS....CNOIVA DE TABLA YJ (CNOFC)
	protected static final int EFECTIVO			= 1;
	protected static final int CHEQUES			= 2;
	protected static final int TRANSFERENCIAS	= 3;
	protected static final int REMESAS			= 4;
	protected static final int TRAVELCHECKS		= 5;
	

	protected String LangPath = "S";

	/**
	 * JSEPR0350 constructor comment.
	 */
	public JSEPR0350() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR0350");

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
			flexLog("About to call Page: " + LangPath + "EPR0350_foreign_currency_exchange_enter_inq.jsp");
			callPage(LangPath + "EPR0350_foreign_currency_exchange_enter_inq.jsp", req, res);
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
		EPR035001Message msgSearch = null;
		EPR035001Message msgList = null;
		
		JBObjList beanList = null;
		boolean IsNotError = false;

		//UserPos userPO = new UserPos();
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR035001Message) mc.getMessageRecord("EPR035001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0350");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
			
			try { 
				msgSearch.setH01FLGWK2(req.getParameter("Opt").toUpperCase());
			} catch (Exception e) {
				msgSearch.setH01FLGWK2(userPO.getHeader10());
			}

			userPO.setHeader10(msgSearch.getH01FLGWK2());
					    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR035001 Message Sent");
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EPR035001")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EPR035001Message) newmessage;

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
					flexLog("About to call Page: " + LangPath + "EPR0350_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0350_foreign_currency_exchange_list.jsp", req, res);						
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
			
			EPR035001Message msgMT = (EPR035001Message) beanList.getRecord();		
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
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEPR0350?SCREEN=100&Opt=" + userPO.getHeader10());
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0350_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0350_foreign_currency_exchange_list.jsp", req, res);						
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
	protected void procActionReject(
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
			
			EPR035001Message msgMT = (EPR035001Message) beanList.getRecord();
			
			try {
				msgMT.setE01REQRJ1(req.getParameter("RAZON1").toUpperCase());
			} catch (Exception e) {
			}
			try {
				msgMT.setE01REQRJ2(req.getParameter("RAZON2").toUpperCase());
			} catch (Exception e) {
			}
						
			msgMT.setH01OPECOD("0007");
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgInst", msgMT);

				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0350?SCREEN=100'";
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
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgInst", msgMT);

				flexLog("About to call Page: " + LangPath + "EPR0350_foreign_currency_exchange_reject.jsp");
				callPage(LangPath + "EPR0350_foreign_currency_exchange_reject.jsp", req, res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		int instrument = 0;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		EPR035002Message msgLiquida = (datapro.eibs.beans.EPR035002Message) ses.getAttribute("msgLiquida");

		try {
			flexLog("Send Initial Data");
			msgLiquida.setH02USERID(user.getH01USR());
			msgLiquida.setH02PROGRM("EPR0350");
			msgLiquida.setH02TIMSYS(getTimeStamp());
			msgLiquida.setH02OPECOD("0005");
			
			// all the fields here
			java.util.Enumeration enu = msgLiquida.fieldEnumeration();
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

			mc.sendMessage(msgLiquida);
			
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


  // Receive data Message
 		 newmessage = mc.receiveMessage();

		  if (newmessage.getFormatName().equals("EPR035002")) {
	
				  try {
					  msgLiquida = new EPR035002Message();
					  flexLog("EPR035002 Message Received");
				 		 } catch (Exception ex) {
					  flexLog("Error: " + ex);
					  }
		  
				  msgLiquida = (EPR035002Message) newmessage;

		  }
		  
			if (IsNotError) {
				

				msgLiquida.destroy();

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgLiquida", msgLiquida);

				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.forex.JSEPR0350?SCREEN=100'";
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
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgLiquida", msgLiquida);

				flexLog("About to call Page: " + LangPath + "EPR0350_foreign_currency_exchange_maint.jsp");
				callPage(LangPath + "EPR0350_foreign_currency_exchange_maint.jsp", req, res);
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
						case A_APPROVAL :
							procActionApproval(mc,msgUser, req, res, session);
							break;
						case A_REJECT	 :
							procActionReject(mc,msgUser, req, res, session);
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