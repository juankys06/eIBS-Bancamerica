package datapro.eibs.safedeposit;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSESB0030 extends SuperServlet {

	protected static final int R_LIST 		= 1;
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSESB0030() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSESB0030");
	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ESB003001Message msgPart = new ESB003001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgPart = (ESB003001Message)mc.getMessageRecord("ESB003001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ESB0030");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01OPECOD("0001");

			msgPart.setE01LOCLOC(req.getParameter("E01LOCLOC"));

			msgPart.send();
			msgPart.destroy();
			flexLog("ESB003001 Message Sent");
			
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
				if (newmessage.getFormatName().equals("ESB003001")) {
					msgPart = new datapro.eibs.beans.ESB003001Message();
					msgPart = (ESB003001Message) newmessage;
					flexLog("ESB003001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					userPO.setHeader1(req.getParameter("E01LOCLOC"));
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ESB0030_safe_deposit_locations_new.jsp");
							callPage(LangPath + "ESB0030_safe_deposit_locations_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.safedeposit.JSESB0030?SCREEN=1" );
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
		ESB003001Message msgSearch = null;
		ESB003001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ESB003001Message) mc.getMessageRecord("ESB003001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ESB0030");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ESB003001 Message Sent");
		
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
				ses.setAttribute("msgPart", msgSearch);
				
			} else if (newmessage.getFormatName().equals("ESB003001")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (ESB003001Message) newmessage;
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
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
			try {
				flexLog("About to call Page: " + LangPath + "ESB0030_safe_deposit_locations_list.jsp");
				callPage(LangPath + "ESB0030_safe_deposit_locations_list.jsp", req, res);						
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created by Frank Hernandez.
	 */
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESB003001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			//Get the message from the row 
			ESB003001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ESB003001Message) beanList.getRecord();

			msgPart = (ESB003001Message) mc.getMessageRecord("ESB003001");
			msgPart.setE01LOCLOC(msgTemp.getE01LOCLOC());

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ESB0030");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");

			msgPart.send();
			msgPart.destroy();
			flexLog("ESB003001 Message Sent");

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
				if (newmessage.getFormatName().equals("ESB003001")) {
					msgPart = new datapro.eibs.beans.ESB003001Message();
					msgPart = (ESB003001Message) newmessage;
					flexLog("ESB003001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ESB0030_safe_deposit_locations_maint.jsp");
							callPage(LangPath + "ESB0030_safe_deposit_locations_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.safedeposit.JSESB0030?SCREEN=1" );
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
		ESB003001Message msgPart = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			//Get the message from the row 
			ESB003001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ESB003001Message) beanList.getRecord();
			
			msgPart = (ESB003001Message) mc.getMessageRecord("ESB003001");
			msgPart.setE01LOCLOC(msgTemp.getE01LOCLOC());
			//msgPart.setE01SBTTYP(req.getParameter("E01SBTTYP"));

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ESB0030");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0004");
		
			msgPart.send();
			msgPart.destroy();
			
			// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
		
				if (IsNotError) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.safedeposit.JSESB0030?SCREEN=1");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ESB0030_safe_deposit_locations_list.jsp");
						callPage(LangPath + "ESB0030_safe_deposit_locations_list.jsp", req, res);						
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
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESB003001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (ESB003001Message)mc.getMessageRecord("ESB003001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ESB0030");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgPart.fieldEnumeration();
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
	
			msgPart.send();
			msgPart.destroy();
			flexLog("ESB003001 Message Sent");
	
			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
					// Receive Error
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
					
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
					newmessage = mc.receiveMessage();
					
					if (IsNotError) { // There are no errors
							try {
								res.sendRedirect(super.srctx + 
									"/servlet/datapro.eibs.safedeposit.JSESB0030?SCREEN=1" );
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
					} else { // There are errors
						try {
							String myPage = "";
							if (userPO.getPurpose().equals("NEW"))
								myPage = "ESB0030_safe_deposit_locations_new.jsp";
							else if (userPO.getPurpose().equals("MAINTENANCE")) 
								myPage = "ESB0030_safe_deposit_locations_maint.jsp";
							flexLog("About to call Page: " + LangPath + myPage);
							callPage(LangPath + myPage, req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}	
					}
		
			} else if (newmessage.getFormatName().equals("ESB003001")) {
					// Receive Data
					msgPart = new datapro.eibs.beans.ESB003001Message();
					msgPart = (ESB003001Message) newmessage;
					flexLog("ESB003001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.safedeposit.JSESB0030?SCREEN=1" );
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
						case R_NEW:
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_LIST :							
							procReqList(mc, msgUser, req, res, session);
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
}