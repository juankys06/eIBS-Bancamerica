package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (7/21/05 6:50:00 PM)
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

public class JSEPR0395 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH 	= 100;
	protected static final int R_LIST 		= 200;

	protected String LangPath = "S";

	/**
	 * JSEPR0353 constructor comment.
	 */
	public JSEPR0395() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR0395");

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
			flexLog("About to call Page: " + LangPath + "EPR0395_foreign_currency_exchange_mov_inq.jsp");
			callPage(LangPath + "EPR0395_foreign_currency_exchange_mov_inq.jsp", req, res);
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
		EPR039501Message msgSearch = null;
		EPR039501Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR039501Message) mc.getMessageRecord("EPR039501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0395");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			//all the fields here
			//all the fields here
			try { 
				msgSearch.setE01REQBNK(req.getParameter("E01REQBNK").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQBNK(userPO.getBank());
			}
			try { 
				msgSearch.setE01REQBRN(req.getParameter("E01REQBRN").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQBRN(userPO.getBranch());
			}
			try { 
				msgSearch.setE01REQCUS(req.getParameter("E01REQCUS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCUS(userPO.getCusNum());
			}
			try { 
				msgSearch.setE01REQCD1(req.getParameter("E01REQCD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCD1(userPO.getHeader1());
			}
			try { 
				msgSearch.setE01REQCD2(req.getParameter("E01REQCD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCD2(userPO.getHeader2());
			}
			try { 
				msgSearch.setE01REQCD3(req.getParameter("E01REQCD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCD3(userPO.getHeader3());
			}
			try { 
				msgSearch.setE01REQCH1(req.getParameter("E01REQCH1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCH1(userPO.getHeader4());
			}
			try { 
				msgSearch.setE01REQCH2(req.getParameter("E01REQCH2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCH2(userPO.getHeader5());
			}
			try { 
				msgSearch.setE01REQCH3(req.getParameter("E01REQCH3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQCH3(userPO.getHeader6());
			}
			try { 
				msgSearch.setE01REQREF(req.getParameter("E01REQREF").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQREF(userPO.getHeader7());
			}
			try { 
				msgSearch.setE01REQINS(req.getParameter("E01REQINS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQINS(userPO.getHeader8());
			}
			try { 
				msgSearch.setE01REQOPC(req.getParameter("E01REQOPC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQOPC(userPO.getHeader9());
			}
			try { 
				msgSearch.setE01REQSTS(req.getParameter("E01REQSTS").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01REQSTS(userPO.getHeader10());
			}
			userPO.setBank(msgSearch.getE01REQBNK());
			userPO.setBranch(msgSearch.getE01REQBRN());
			userPO.setCusNum(msgSearch.getE01REQCUS());
			userPO.setHeader1(msgSearch.getE01REQCD1());
			userPO.setHeader2(msgSearch.getE01REQCD2());
			userPO.setHeader3(msgSearch.getE01REQCD3());
			userPO.setHeader4(msgSearch.getE01REQCH1());
			userPO.setHeader5(msgSearch.getE01REQCH2());
			userPO.setHeader6(msgSearch.getE01REQCH3());
			userPO.setHeader7(msgSearch.getE01REQREF());
			userPO.setHeader8(msgSearch.getE01REQINS());
			userPO.setHeader9(msgSearch.getE01REQOPC());
			userPO.setHeader10(msgSearch.getE01REQSTS());
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR039501 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}

			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EPR039501")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EPR039501Message) newmessage;

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
					flexLog("About to call Page: " + LangPath + "EPR0395_foreign_currency_exchange_list.jsp");
					callPage(LangPath + "EPR0395_foreign_currency_exchange_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);				
				try {
					flexLog("About to call Page: " + LangPath + "EPR0395_foreign_currency_exchange_mov_inq.jsp");
					callPage(LangPath + "EPR0395_foreign_currency_exchange_mov_inq.jsp", req, res);						
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