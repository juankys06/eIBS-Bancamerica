package datapro.eibs.client;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSECRA050 extends SuperServlet {

	protected static final int R_ENTER 		= 1;
	protected static final int R_LIST 		= 2;
	
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSECRA050() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSECRA050");
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

			int screen = R_NEW;

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
						case R_ENTER:
							procReqEnterState(msgUser, req, res, session);
							break;	
						
						case R_LIST:
							procReqList(mc, msgUser, req, res, session);
							break;						
						case R_NEW:
							procReqNew(mc, msgUser, req, res, session);
							break;
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
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
	
	protected void procReqEnterState(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog("About to call Page: "	+ LangPath	+ "ECRA050_census_codes_enter.jsp");
			callPage(LangPath + "ECRA050_census_codes_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}		
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECRA05001Message msgCT = null;
		ECRA05001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCT = (ECRA05001Message) mc.getMessageRecord("ECRA05001");
			msgCT.setH01USERID(user.getH01USR());
			msgCT.setH01PROGRM("ECRA050");
			msgCT.setH01TIMSYS(getTimeStamp());
			msgCT.setH01SCRCOD("01");
			msgCT.setH01OPECOD("0015");
						
			try {
				msgCT.setE01CRUSTC(req.getParameter("E01CRUSTC"));
				userPO.setIdentifier(req.getParameter("E01CRUSTC"));				
			}  catch (Exception e) {
				msgCT.setE01CRUSTC("");
				userPO.setIdentifier("");
			}

			try {
				msgCT.setE01CRUCTC(req.getParameter("E01CRUCTC"));
				userPO.setHeader1(req.getParameter("E01CRUCTC"));				
			}  catch (Exception e) {
				msgCT.setE01CRUCTC("");
				userPO.setHeader1("");
			}
		    	
			msgCT.send();
			msgCT.destroy();
			flexLog("ECRA05001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				//msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgCT", msgCT);

				try {
					flexLog("About to call Page: " + LangPath + "ECRA050_census_codes_enter.jsp");
					callPage(LangPath + "ECRA050_census_codes_enter.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
				
			} else if (newmessage.getFormatName().equals("ECRA05001")) {
				beanList = new JBObjList();
				String marker = "";
				msgCT = null;

				while (true) {
					msgCT = (ECRA05001Message) newmessage;
					marker = msgCT.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgCT);
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
					flexLog("About to call Page: " + LangPath + "ECRA050_census_codes_list.jsp");
					callPage(LangPath + "ECRA050_census_codes_list.jsp", req, res);						
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
	
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		ECRA05001Message msgCT = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
				
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			//Get the message from the row 
			ECRA05001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA05001Message) beanList.getRecord();
			
			msgCT = (ECRA05001Message) mc.getMessageRecord("ECRA05001");
			msgCT.setE01CRUCET(msgTemp.getE01CRUCET());
			
			msgCT.setH01USERID(user.getH01USR());
			msgCT.setH01PROGRM("ECRA050");
			msgCT.setH01TIMSYS(getTimeStamp());
			msgCT.setH01SCRCOD("01");
			msgCT.setH01OPECOD("0004");
		
			msgCT.send();
			msgCT.destroy();
			flexLog("ECRA05001 Message Sent");
			
			// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCT", msgCT);
				ses.setAttribute("userPO", userPO);
		
				if (IsNotError) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA050?SCREEN=2&E01CRUSTC=" + userPO.getIdentifier() + "&E01CRUCTC=" + userPO.getHeader1());
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECRA050_census_codes_list.jsp");
						callPage(LangPath + "ECRA050_census_codes_list.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECRA05001Message msgCT = new ECRA05001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgCT = (ECRA05001Message)mc.getMessageRecord("ECRA05001");
			msgCT.setH01USERID(user.getH01USR());
			msgCT.setH01PROGRM("ECRA050");
			msgCT.setH01TIMSYS(getTimeStamp());
			msgCT.setH01OPECOD("0001");
			
			try {
				msgCT.setE01CRUCET(req.getParameter("E01CRUCET"));
			} catch (Exception e) {
				msgCT.setE01CRUCET("");
			}
			msgCT.setE01CRUSTC(userPO.getIdentifier());
			msgCT.setE01CRUCTC(userPO.getHeader1());
			
			msgCT.send();
			msgCT.destroy();
			flexLog("ECRA05001 Message Sent");
			
			// Receive Error Y Data
				// Error
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				// Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ECRA05001")) {
					msgCT = new datapro.eibs.beans.ECRA05001Message();
					msgCT = (ECRA05001Message) newmessage;
					flexLog("ECRA05001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCT", msgCT);
					ses.setAttribute("userPO", userPO);

					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.client.JSECRA050?SCREEN=2&E01CRUSTC=" + userPO.getIdentifier() + "&E01CRUCTC=" + userPO.getHeader1());
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}	

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}		
}