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

public class JSECRA020 extends SuperServlet {

	protected static final int R_ENTER 		= 1;
	protected static final int R_LIST 		= 2;
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int R_INQ 		= 300;
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSECRA020() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSECRA020");
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
						case R_ENTER:
							procReqEnterState(msgUser, req, res, session);
							break;	
						case R_LIST:
							procReqList(mc, msgUser, req, res, session);
							break;
												
						case R_NEW:
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_MAINT :							
							procReqMaint(mc, msgUser, req, res, session);
							break;								
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaint(mc,msgUser, req, res, session);
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
			flexLog("About to call Page: "	+ LangPath	+ "ECRA020_county_codes_enter.jsp");
			callPage(LangPath + "ECRA020_county_codes_enter.jsp", req, res);
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
		ECRA02001Message msgCC = null;
		ECRA02001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCC = (ECRA02001Message) mc.getMessageRecord("ECRA02001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECRA020");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0015");
						
			try {
				msgCC.setE01CRCSTC(req.getParameter("E01CRCSTC"));
				userPO.setIdentifier(req.getParameter("E01CRCSTC"));
			}  catch (Exception e) {
				msgCC.setE01CRCSTC("");
				userPO.setIdentifier("");
			}
		    	
			msgCC.send();
			msgCC.destroy();
			flexLog("ECRA03001 Message Sent");
		
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
				ses.setAttribute("msgCC", msgCC);

				try {
					flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_enter.jsp");
					callPage(LangPath + "ECRA020_county_codes_enter.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
							
			} else if (newmessage.getFormatName().equals("ECRA02001")) {
				beanList = new JBObjList();
				String marker = "";
				msgCC = null;

				while (true) {
					msgCC = (ECRA02001Message) newmessage;
					marker = msgCC.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgCC);
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
					flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_list.jsp");
					callPage(LangPath + "ECRA020_county_codes_list.jsp", req, res);						
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
	
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECRA02001Message msgCC = new ECRA02001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgCC = (ECRA02001Message)mc.getMessageRecord("ECRA02001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECRA020");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01OPECOD("0001");

			try {
				msgCC.setE01CRCCTC(req.getParameter("E01CRCTC"));
			} catch (Exception e) {
				msgCC.setE01CRCCTC("");
			}
			try {
				msgCC.setE01CRCSTC(userPO.getIdentifier());
			} catch (Exception e) {
				msgCC.setE01CRCSTC("");
			}
			msgCC.send();
			msgCC.destroy();
			flexLog("ECRA02001 Message Sent");
			
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
				if (newmessage.getFormatName().equals("ECRA02001")) {
					msgCC = new datapro.eibs.beans.ECRA02001Message();
					msgCC = (ECRA02001Message) newmessage;
					flexLog("ECRA02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCC", msgCC);
					userPO.setHeader1(req.getParameter("E01CRCCTC"));
					userPO.setHeader2(req.getParameter("E01CRCSAC"));
					userPO.setHeader3(req.getParameter("E01CRCSTC"));
					
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_basic.jsp");
							callPage(LangPath + "ECRA020_county_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 	"/servlet/datapro.eibs.client.JSECRA020?SCREEN=2&E01CRCSTC=" + userPO.getIdentifier() );
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

	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECRA02001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCC = (ECRA02001Message)mc.getMessageRecord("ECRA02001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECRA020");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgCC.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgCC.send();
			msgCC.destroy();
			flexLog("ECRA02001 Message Sent");
	
		// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else 
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		// Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ECRA02001")) {
				msgCC = new datapro.eibs.beans.ECRA02001Message();
				msgCC = (ECRA02001Message) newmessage;
				flexLog("ECRA02001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCC", msgCC);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA020?SCREEN=2&E01CRCSTC=" + userPO.getIdentifier() );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_basic.jsp");
							callPage(LangPath + "ECRA020_county_codes_basic.jsp", req, res);
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
	
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECRA02001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			//Get the message from the row 
			ECRA02001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA02001Message) beanList.getRecord();
			
			msgCC = (ECRA02001Message) mc.getMessageRecord("ECRA02001");
			msgCC.setE01CRCCTC(msgTemp.getE01CRCCTC());
			msgCC.setE01CRCSTC(userPO.getIdentifier());

			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECRA020");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0002");

			msgCC.send();
			msgCC.destroy();
			flexLog("ECRA02001 Message Sent");

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
	
			// Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ECRA02001")) {
					msgCC = new datapro.eibs.beans.ECRA02001Message();
					msgCC = (ECRA02001Message) newmessage;
					flexLog("ECRA02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCC", msgCC);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_basic.jsp");
							callPage(LangPath + "ECRA020_county_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA020?SCREEN=2&E01CRCSTC=" + userPO.getIdentifier() );
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
	
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		ECRA02001Message msgCC = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
				
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			//Get the message from the row 
			ECRA02001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA02001Message) beanList.getRecord();
			
			msgCC = (ECRA02001Message) mc.getMessageRecord("ECRA02001");
			msgCC.setE01CRCCTC(msgTemp.getE01CRCCTC());
			msgCC.setE01CRCSTC(userPO.getIdentifier());

			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECRA020");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0004");
		
			msgCC.send();
			msgCC.destroy();
			flexLog("ECRA02001 Message Sent");
			
			// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCC", msgCC);
				ses.setAttribute("userPO", userPO);
		
				if (IsNotError) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA020?SCREEN=2&E01CRCSTC=" + userPO.getIdentifier());
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECRA020_county_codes_list.jsp");
						callPage(LangPath + "ECRA020_county_codes_list.jsp", req, res);						
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
	
	
}