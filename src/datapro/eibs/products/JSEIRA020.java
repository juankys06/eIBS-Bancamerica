package datapro.eibs.products;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSEIRA020 extends SuperServlet {

	protected static final int R_ENTER		= 1;
	protected static final int R_LIST_ACC	= 11;
	protected static final int R_LIST_CUS	= 111;
	protected static final int R_LIST_TYP 	= 1111;
	protected static final int R_LIST		= 11111;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSEIRA020() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSEIRA020");
	}

	protected void procReqEnterGen(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setHeader1(req.getParameter("CUS"));
		userPO.setHeader2(req.getParameter("CUSNAM"));
		try {
			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EIRA020_ira_pension_summary_enter.jsp");
			callPage(LangPath + "EIRA020_ira_pension_summary_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListGen(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EIRA02002Message msgSearch = null;
		EIRA02002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("procReqListGen Send Initial Data");
			msgSearch = (EIRA02002Message) mc.getMessageRecord("EIRA02002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EIRA020");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");

			try {
				msgSearch.setE02IRACUN(req.getParameter("E02IRACUN"));
				msgSearch.setE02IRAYEA(req.getParameter("E02IRAYEA"));
			} catch (Exception e){
				flexLog("Error: Costumer and Year needed");
			}
			msgSearch.setH02FLGWK1("T");

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EIRA02002 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				//ses.setAttribute("msgPart", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EIRA02002")) {
				beanList = new JBObjList();
				String marker = "";
				boolean firstTime = true;

				while (true) {
					msgList = (EIRA02002Message) newmessage;
					marker = msgList.getH02FLGMAS();
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getE02IRACUN());
								userPO.setHeader2(msgList.getD02CUSNA1());
								userPO.setHeader3(msgList.getE02IRAYEA());
						}						
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
				
				try {
					flexLog("About to call Page: " + LangPath + "EIRA020_ira_pension_summary_list.jsp");
					callPage(LangPath + "EIRA020_ira_pension_summary_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
				
			} else {
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
	protected void procReqListWhenAccount(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EIRA02001Message msgSearch = null;
		EIRA02001Message msgList = null;
		EDL016001Message msgGet = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("procReqListWhenAccount Send Initial Data");
			msgSearch = (EIRA02001Message) mc.getMessageRecord("EIRA02001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EIRA020");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			try {
				msgGet = (EDL016001Message) ses.getAttribute("cdInquiry");
				msgSearch.setE01IRAACC(msgGet.getE01DEAACC());
			} catch (Exception e){
				flexLog("Error: Can't resolve Account");
			}
			msgSearch.setH01FLGWK1("1");

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EIRA02001 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				//ses.setAttribute("msgPart", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EIRA02001")) {
				beanList = new JBObjList();
				String marker = "";
				boolean firstTime = true;

				while (true) {
					msgList = (EIRA02001Message) newmessage;
					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						if (firstTime == false) {
							userPO.setHeader8(msgList.getD01CUREBL());
							userPO.setHeader10(msgList.getD01PRVEBL());
						}			
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getE01IRACUN());
								userPO.setHeader2(msgList.getD01CUSNA1());
								userPO.setHeader3(msgList.getE01IRAACC());
								userPO.setHeader4(msgList.getD01IRADSC());
								userPO.setHeader5(msgList.getD01CUSBD1() +
										"/" + msgList.getD01CUSBD2() +
										"/" + msgList.getD01CUSBD3());
								userPO.setHeader6(msgList.getD01DEAOD1() +
										"/" + msgList.getD01DEAOD2() +
										"/" + msgList.getD01DEAOD3());
								userPO.setHeader7(msgList.getD01CURBBL());
								userPO.setHeader9(msgList.getD01PRVBBL());
						}						
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
				
				try {
					flexLog("About to call Page: " + LangPath + "EIRA020_ira_account_summary_list.jsp");
					callPage(LangPath + "EIRA020_ira_account_summary_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
				
			} else {
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
	protected void procReqListWhenCustomer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EIRA02001Message msgSearch = null;
		EIRA02001Message msgList = null;
		ESD008001Message msgGet = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("procReqListWhenCustomer Send Initial Data");
			msgSearch = (EIRA02001Message) mc.getMessageRecord("EIRA02001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EIRA020");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			try {
				msgGet = (ESD008001Message) ses.getAttribute("client");
				msgSearch.setE01IRACUN(msgGet.getE01CUN());
			} catch (Exception e){
				flexLog("Error: Can't resolve Costumer");
			}			
			msgSearch.setH01FLGWK1("C");

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EIRA02001 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				//ses.setAttribute("msgPart", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal.jsp");
					callPage(LangPath + "ESD0080_client_inq_personal.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EIRA02001")) {
				beanList = new JBObjList();
				String marker = "";
				boolean firstTime = true;

				while (true) {
					msgList = (EIRA02001Message) newmessage;
					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						if (firstTime == false) {
							userPO.setHeader8(msgList.getD01CUREBL());
							userPO.setHeader10(msgList.getD01PRVEBL());
						}			
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getE01IRACUN());
								userPO.setHeader2(msgList.getD01CUSNA1());
								userPO.setHeader3(msgList.getE01IRAACC());
								userPO.setHeader4(msgList.getD01IRADSC());
								userPO.setHeader5(msgList.getD01CUSBD1() +
										"/" + msgList.getD01CUSBD2() +
										"/" + msgList.getD01CUSBD3());
								userPO.setHeader6(msgList.getD01DEAOD1() +
										"/" + msgList.getD01DEAOD2() +
										"/" + msgList.getD01DEAOD3());
								userPO.setHeader7(msgList.getD01CURBBL());
								userPO.setHeader9(msgList.getD01PRVBBL());
						}						
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
				
				try {
					flexLog("About to call Page: " + LangPath + "EIRA020_ira_account_summary_list.jsp");
					callPage(LangPath + "EIRA020_ira_account_summary_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
				
			} else {
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
	protected void procReqListWhenType(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EIRA02001Message msgSearch = null;
		EIRA02001Message msgList = null;
		EIRA02002Message msgGet = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("procReqListWhenType Send Initial Data");
			msgSearch = (EIRA02001Message) mc.getMessageRecord("EIRA02001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EIRA020");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			try {
				msgGet = (EIRA02002Message) ses.getAttribute("msgList");
				msgSearch.setE01IRATCD(req.getParameter("TYP"));
			} catch (Exception e){
				flexLog("Error: Can't resolve Type");
			}				
			msgSearch.setH01FLGWK1("T");					

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EIRA02001 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				//ses.setAttribute("msgPart", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EIRA02001")) {
				beanList = new JBObjList();
				String marker = "";
				boolean firstTime = true;

				while (true) {
					msgList = (EIRA02001Message) newmessage;
					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						if (firstTime == false) {
							userPO.setHeader8(msgList.getD01CUREBL());
							userPO.setHeader10(msgList.getD01PRVEBL());
						}			
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getE01IRACUN());
								userPO.setHeader2(msgList.getD01CUSNA1());
								userPO.setHeader3(msgList.getE01IRAACC());
								userPO.setHeader4(msgList.getD01IRADSC());
								userPO.setHeader5(msgList.getD01CUSBD1() +
										"/" + msgList.getD01CUSBD2() +
										"/" + msgList.getD01CUSBD3());
								userPO.setHeader6(msgList.getD01DEAOD1() +
										"/" + msgList.getD01DEAOD2() +
										"/" + msgList.getD01DEAOD3());
								userPO.setHeader7(msgList.getD01CURBBL());
								userPO.setHeader9(msgList.getD01PRVBBL());
						}						
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
				
				try {
					flexLog("About to call Page: " + LangPath + "EIRA020_ira_account_summary_list.jsp");
					callPage(LangPath + "EIRA020_ira_account_summary_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
				
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
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

			int screen = R_LIST;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
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
						case R_ENTER :							
							procReqEnterGen(mc, msgUser, req, res, session);
							break;	
						case R_LIST :							
							procReqListGen(mc, msgUser, req, res, session);
							break;	
						case R_LIST_ACC :							
							procReqListWhenAccount(mc, msgUser, req, res, session);
							break;
						case R_LIST_CUS :							
							procReqListWhenCustomer(mc, msgUser, req, res, session);
							break;	
						case R_LIST_TYP :							
							procReqListWhenType(mc, msgUser, req, res, session);
							break;								
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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

	protected void showERROR(ELEERRMessage m){
		if (logType != NONE) {
		
			flexLog("ERROR received.");
		
			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());
		}
	}
}