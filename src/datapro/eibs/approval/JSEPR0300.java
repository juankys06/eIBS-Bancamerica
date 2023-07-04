package datapro.eibs.approval;   

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSEPR0300 extends SuperServlet {

	protected static final int R_LIST 		= 1;
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;
	
	protected static final int R_LISTC 		= 10;
	protected static final int R_NEWC 		= 11;
	protected static final int R_MAINTC 	= 12;
	protected static final int A_DELETEC 	= 14;
	protected static final int A_MAINTC 	= 15;

	protected String LangPath = "S";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSEPR0300() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSEPR0300");
	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EPR030001Message msgPart = new EPR030001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_basic.jsp");
							callPage(LangPath + "EPR0300_transaction_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
	}
	protected void procReqNewC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EPR030201Message msgPart = new EPR030201Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_basic.jsp");
							callPage(LangPath + "EPR0300_chanel_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
	}

	protected void procReqNewNo(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EPR030001Message msgPart = new EPR030001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgPart = (EPR030001Message)mc.getMessageRecord("EPR030001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01OPECOD("0001");

			msgPart.setE01PRATCD(req.getParameter("E01PRATCD"));

			msgPart.send();
			msgPart.destroy();
			flexLog("EPR030001 Message Sent");
			
			// Receive Error Y Data
				// Error
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					//showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				// Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EPR030001")) {
					msgPart = new datapro.eibs.beans.EPR030001Message();
					msgPart = (EPR030001Message) newmessage;
					flexLog("EPR030001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					userPO.setHeader1(req.getParameter("E01PRATCD"));
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_basic.jsp");
							callPage(LangPath + "EPR0300_transaction_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=1" );
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
		EPR030001Message msgSearch = null;
		EPR030001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR030001Message) mc.getMessageRecord("EPR030001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0300");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR030001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");				
			}			
				
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EPR030001")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (EPR030001Message) newmessage;
					flexLog("EPR030001 Message Received");					
					marker = msgList.getE01INDOPE();

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
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_list.jsp");
						callPage(LangPath + "EPR0300_transaction_codes_list.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Error receiving the List");
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
		EPR030001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			//Get the message from the row 
			EPR030001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (EPR030001Message) beanList.getRecord();

			//msgPart = (EPR030001Message) mc.getMessageRecord("EPR030001");
			//msgPart.setE01PRATCD(msgTemp.getE01PRATCD());
			msgPart = msgTemp;

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");

			mc.sendMessage(msgPart);
			//msgPart.send();
			msgPart.destroy();
			flexLog("EPR030001 Message Sent");

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
				if (newmessage.getFormatName().equals("EPR030001")) {
					msgPart = new datapro.eibs.beans.EPR030001Message();
					msgPart = (EPR030001Message) newmessage;
					flexLog("EPR030001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_basic.jsp");
							callPage(LangPath + "EPR0300_transaction_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_list.jsp");
							callPage(LangPath + "EPR0300_transaction_codes_list.jsp", req, res);
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
	protected void procReqMaintC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR030201Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			//Get the message from the row 
			EPR030201Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (EPR030201Message) beanList.getRecord();

			//msgPart = (EPR030001Message) mc.getMessageRecord("EPR030001");
			//msgPart.setE01PRATCD(msgTemp.getE01PRATCD());
			msgPart = msgTemp;

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");

			mc.sendMessage(msgPart);
			//msgPart.send();
			msgPart.destroy();
			flexLog("EPR030001 Message Sent");

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
				if (newmessage.getFormatName().equals("EPR030201")) {
					msgPart = new datapro.eibs.beans.EPR030201Message();
					msgPart = (EPR030201Message) newmessage;
					flexLog("EPR030201 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_basic.jsp");
							callPage(LangPath + "EPR0300_chanel_codes_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_list.jsp");
							callPage(LangPath + "EPR0300_chanel_codes_list.jsp", req, res);
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
		EPR030001Message msgPart = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			//Get the message from the row 
			EPR030001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (EPR030001Message) beanList.getRecord();
			
			msgPart = (EPR030001Message) mc.getMessageRecord("EPR030001");
			msgPart.setE01PRATCD(msgTemp.getE01PRATCD());
			//msgPart.setE01SBTTYP(req.getParameter("E01SBTTYP"));

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0009");
		
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
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=1");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_list.jsp");
						callPage(LangPath + "EPR0300_transaction_codes_list.jsp", req, res);						
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
	
	protected void procActionDeleteC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		EPR030201Message msgPart = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			//Get the message from the row 
			EPR030201Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (EPR030201Message) beanList.getRecord();
			
			msgPart = (EPR030201Message) mc.getMessageRecord("EPR030201");
			msgPart.setE01PRCCNL(msgTemp.getE01PRCCNL());
			//msgPart.setE01SBTTYP(req.getParameter("E01SBTTYP"));

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0009");
		
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
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=10");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_list.jsp");
						callPage(LangPath + "EPR0300_chanel_codes_list.jsp", req, res);						
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
		EPR030001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (userPO.getPurpose().equals("NEW")) {
			opCode = "0001";
		} else {
			opCode = "0005";
		}		
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (EPR030001Message)mc.getMessageRecord("EPR030001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0300");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD(opCode);
	
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
			flexLog("EPR030001 Message Sent");
	
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
			if (newmessage.getFormatName().equals("EPR030001")) {
				msgPart = new datapro.eibs.beans.EPR030001Message();
				msgPart = (EPR030001Message) newmessage;
				flexLog("EPR030001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=1" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_transaction_codes_basic.jsp");
							callPage(LangPath + "EPR0300_transaction_codes_basic.jsp", req, res);
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
	protected void procActionMaintC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EPR030201Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (userPO.getPurpose().equals("NEW")) {
			opCode = "0001";
		} else {
			opCode = "0005";
		}		
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (EPR030201Message)mc.getMessageRecord("EPR030201");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EPR0320");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD(opCode);
	
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
			flexLog("EPR030201 Message Sent");
	
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
			if (newmessage.getFormatName().equals("EPR030201")) {
				msgPart = new datapro.eibs.beans.EPR030201Message();
				msgPart = (EPR030201Message) newmessage;
				flexLog("EPR030201 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=10" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_basic.jsp");
							callPage(LangPath + "EPR0300_chanel_codes_basic.jsp", req, res);
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
	protected void procReqListC(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EPR030201Message msgSearch = null;
		EPR030201Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR030201Message) mc.getMessageRecord("EPR030201");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR0300");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EPR030001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");				
			}			
				 
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EPR030201")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (EPR030201Message) newmessage;
					flexLog("EPR030201 Message Received");					
					marker = msgList.getE01INDOPE();

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
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPR0300_chanel_codes_list.jsp");
						callPage(LangPath + "EPR0300_chanel_codes_list.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Error receiving the List");
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
						case R_LISTC :							
							procReqListC(mc, msgUser, req, res, session);
							break;
						case R_NEWC:
							procReqNewC(mc, msgUser, req, res, session);
							break;	
						case R_MAINTC :							
							procReqMaintC(mc, msgUser, req, res, session);
							break;								
						case A_DELETEC :
							procActionDeleteC(mc,msgUser, req, res, session);
							break;
						case A_MAINTC :
							procActionMaintC(mc,msgUser, req, res, session);
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