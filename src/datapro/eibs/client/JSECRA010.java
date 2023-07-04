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

public class JSECRA010 extends SuperServlet {

	protected static final int R_LIST 		= 1;
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int R_INQ 		= 300;	
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;
	protected static final int A_NEW 		= 600;
	protected static final int A_INQ 		= 700;
		
	protected static final int R_LIST_G		= 11;
	protected static final int R_NEW_G		= 110;
	protected static final int R_MAINT_G 	= 210;
	protected static final int A_DELETE_G 	= 410;
	protected static final int A_MAINT_G 	= 510;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSECRA010() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSECRA010");
	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECRA01001Message msgPart = new ECRA01001Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA01001Message)mc.getMessageRecord("ECRA01001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA010");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01OPECOD("0001");

			msgPart.setE01CRAARN(req.getParameter("E01CRAARN"));

			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01001 Message Sent");
			
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
				if (newmessage.getFormatName().equals("ECRA01001")) {
					msgPart = new datapro.eibs.beans.ECRA01001Message();
					msgPart = (ECRA01001Message) newmessage;
					flexLog("ECRA01001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_new.jsp");
							callPage(LangPath + "ECRA010_assessment_area_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=1" );
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
		ECRA01001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {

			//Get the message from the row 
			ECRA01001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA01001Message) beanList.getRecord();

			msgPart = (ECRA01001Message) mc.getMessageRecord("ECRA01001");
			msgPart.setE01CRAARN(msgTemp.getE01CRAARN());

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA010");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");



			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01001 Message Sent");

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
				if (newmessage.getFormatName().equals("ECRA01001")) {
					msgPart = new datapro.eibs.beans.ECRA01001Message();
					msgPart = (ECRA01001Message) newmessage;
					flexLog("ECRA01001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_maint.jsp");
							callPage(LangPath + "ECRA010_assessment_area_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=1" );
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
	 * This method was created by Frank Hernandez.
	 */
	protected void procReqInquiry(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECRA01001Message msgPart = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	JBObjList beanList = null;
	boolean IsNotError = false;
		
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("MAINTENANCE");

	// Send Initial data
	try {

		//Get the message from the row 
		ECRA01001Message msgTemp = null;
		beanList = (JBObjList) ses.getAttribute("msgList");
		beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
		msgTemp = (ECRA01001Message) beanList.getRecord();

		msgPart = (ECRA01001Message) mc.getMessageRecord("ECRA01001");
		msgPart.setE01CRAARN(msgTemp.getE01CRAARN());

		msgPart.setH01USERID(user.getH01USR());
		msgPart.setH01PROGRM("ECRA010");
		msgPart.setH01TIMSYS(getTimeStamp());
		msgPart.setH01SCRCOD("01");
		msgPart.setH01OPECOD("0003");



		msgPart.send();
		msgPart.destroy();
		flexLog("ECRA01001 Message Sent");

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
			if (newmessage.getFormatName().equals("ECRA01001")) {
				msgPart = new datapro.eibs.beans.ECRA01001Message();
				msgPart = (ECRA01001Message) newmessage;
				flexLog("ECRA01001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgPart", msgPart);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_inquiry.jsp");
						callPage(LangPath + "ECRA010_assessment_area_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.client.JSECRA010?SCREEN=1" );
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
protected void procActionInquiry(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	
	MessageRecord newmessage = null;
	ECRA01001Message msgPart = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
		
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgPart = (ECRA01001Message)mc.getMessageRecord("ECRA01001");
		msgPart.setH01USERID(user.getH01USR());
		msgPart.setH01PROGRM("ECRA010");
		msgPart.setH01TIMSYS(getTimeStamp());
		msgPart.setH01SCRCOD("01");
		msgPart.setH01OPECOD("0003");
			
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
		flexLog("ECRA01001 Message Sent");
	
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
		if (newmessage.getFormatName().equals("ECRA01001")) {
			msgPart = new datapro.eibs.beans.ECRA01001Message();
			msgPart = (ECRA01001Message) newmessage;
			flexLog("ECRA01001 Message Received");
								
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgPart", msgPart);
			ses.setAttribute("userPO", userPO);
	
			if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11&E01CRAARN=" + msgPart.getE01CRAARN() );
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_inquiry.jsp");
						callPage(LangPath + "ECRA010_assessment_area_inquiry.jsp", req, res);
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
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECRA01001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		//String opt = "";
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//opt = userPO.getPurpose();
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA01001Message)mc.getMessageRecord("ECRA01001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA010");
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
			flexLog("ECRA01001 Message Sent");
	
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
			if (newmessage.getFormatName().equals("ECRA01001")) {
				msgPart = new datapro.eibs.beans.ECRA01001Message();
				msgPart = (ECRA01001Message) newmessage;
				flexLog("ECRA01001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11&E01CRAARN=" + msgPart.getE01CRAARN() );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_new.jsp");
							callPage(LangPath + "ECRA010_assessment_area_new.jsp", req, res);
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
		ECRA01001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA01001Message)mc.getMessageRecord("ECRA01001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA010");
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
			flexLog("ECRA01001 Message Sent");
	
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
			if (newmessage.getFormatName().equals("ECRA01001")) {
				msgPart = new datapro.eibs.beans.ECRA01001Message();
				msgPart = (ECRA01001Message) newmessage;
				flexLog("ECRA01001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11&E01CRAARN=" + msgPart.getE01CRAARN() );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_maint.jsp");
							callPage(LangPath + "ECRA010_assessment_area_maint.jsp", req, res);
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
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECRA01001Message msgSearch = null;
		ECRA01001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECRA01001Message) mc.getMessageRecord("ECRA01001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECRA010");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECRA01001 Message Sent");
		
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
				
			} else if (newmessage.getFormatName().equals("ECRA01001")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (ECRA01001Message) newmessage;
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
				flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_list.jsp");
				callPage(LangPath + "ECRA010_assessment_area_list.jsp", req, res);						
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
		ECRA01001Message msgPart = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			//Get the message from the row 
			ECRA01001Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA01001Message) beanList.getRecord();
			
			msgPart = (ECRA01001Message) mc.getMessageRecord("ECRA01001");
			msgPart.setE01CRAARN(msgTemp.getE01CRAARN());
			//msgPart.setE01SBTTYP(req.getParameter("E01SBTTYP"));

			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA010");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0004");
		
			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01001 Message Sent");
			
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
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA010?SCREEN=1");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_list.jsp");
						callPage(LangPath + "ECRA010_assessment_area_list.jsp", req, res);						
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

	protected void procReqNewGeog(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECRA01002Message msgPart = new ECRA01002Message();
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA01002Message)mc.getMessageRecord("ECRA01002");
			msgPart.setH02USERID(user.getH01USR());
			msgPart.setH02PROGRM("ECRA010");
			msgPart.setH02TIMSYS(getTimeStamp());
			msgPart.setH02OPECOD("0001");

			msgPart.setE02CRGARN(req.getParameter("E01CRAARN"));

			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01002 Message Sent");
			
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
				if (newmessage.getFormatName().equals("ECRA01002")) {
					msgPart = new datapro.eibs.beans.ECRA01002Message();
					msgPart = (ECRA01002Message) newmessage;
					flexLog("ECRA01002 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_geog_maint.jsp");
							callPage(LangPath + "ECRA010_assessment_area_geog_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11" );
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
	 * This method was created by Frank Hernandez.
	 */
	protected void procReqMaintGeog(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECRA01002Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			//Get the message from the row 
			ECRA01002Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA01002Message) beanList.getRecord();

			msgPart = (ECRA01002Message) mc.getMessageRecord("ECRA01002");
			msgPart.setE02CRGARN(msgTemp.getE02CRGARN());

			msgPart.setH02USERID(user.getH01USR());
			msgPart.setH02PROGRM("ECRA010");
			msgPart.setH02TIMSYS(getTimeStamp());
			msgPart.setH02SCRCOD("02");
			msgPart.setH02OPECOD("0002");

			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01002 Message Sent");

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
				if (newmessage.getFormatName().equals("ECRA01002")) {
					msgPart = new datapro.eibs.beans.ECRA01002Message();
					msgPart = (ECRA01002Message) newmessage;
					flexLog("ECRA01002 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgPart", msgPart);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_geog_maint.jsp");
							callPage(LangPath + "ECRA010_assessment_area_geog_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11" );
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
	protected void procActionDeleteGeog(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		ECRA01002Message msgPart = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			//Get the message from the row 
			ECRA01002Message msgTemp = null;
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgTemp = (ECRA01002Message) beanList.getRecord();
			
			msgPart = (ECRA01002Message) mc.getMessageRecord("ECRA01002");
			msgPart.setE02CRGARN(msgTemp.getE02CRGARN());
			//msgPart.setE01SBTTYP(req.getParameter("E01SBTTYP"));

			msgPart.setH02USERID(user.getH01USR());
			msgPart.setH02PROGRM("ECRA010");
			msgPart.setH02TIMSYS(getTimeStamp());
			msgPart.setH02SCRCOD("02");
			msgPart.setH02OPECOD("0004");
		
			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA01002 Message Sent");
			
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
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSECRA010?SCREEN=11");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECRA010_geography_list.jsp");
						callPage(LangPath + "ECRA010_geography_list.jsp", req, res);						
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
	protected void procActionMaintGeog(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECRA01002Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		//String opt = "";
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//opt = userPO.getPurpose();
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA01002Message)mc.getMessageRecord("ECRA01002");
			msgPart.setH02USERID(user.getH01USR());
			msgPart.setH02PROGRM("ECRA010");
			msgPart.setH02TIMSYS(getTimeStamp());
			msgPart.setH02SCRCOD("02");
			msgPart.setH02OPECOD("0005");
			
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
			flexLog("ECRA01002 Message Sent");
	
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
			if (newmessage.getFormatName().equals("ECRA01002")) {
				msgPart = new datapro.eibs.beans.ECRA01002Message();
				msgPart = (ECRA01002Message) newmessage;
				flexLog("ECRA01002 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA010?SCREEN=11" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA010_assessment_area_geog_maint.jsp");
							callPage(LangPath + "ECRA010_assessment_area_geog_maint.jsp", req, res);
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

	protected void procReqListGeog(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECRA01002Message msgSearch = null;
		ECRA01002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECRA01002Message) mc.getMessageRecord("ECRA01002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("ECRA010");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("02");
			msgSearch.setH02OPECOD("0015");
			
			msgSearch.setE02CRGARN(req.getParameter("E01CRAARN"));
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECRA01002 Message Sent");
		
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
				
			} else if (newmessage.getFormatName().equals("ECRA01002")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (ECRA01002Message) newmessage;
					marker = msgList.getH02FLGMAS();

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
				userPO.setHeader1(req.getParameter("E01CRAARN"));
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
			try {
				flexLog("About to call Page: " + LangPath + "ECRA010_geography_list.jsp");
				callPage(LangPath + "ECRA010_geography_list.jsp", req, res);						
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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
						case R_LIST:
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_LIST_G:
							procReqListGeog(mc, msgUser, req, res, session);
							break;
						case R_NEW:
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_NEW_G:
							procReqNewGeog(mc, msgUser, req, res, session);
							break;							
						case R_MAINT :							
							procReqMaint(mc, msgUser, req, res, session);
							break;	
						case R_MAINT_G :							
							procReqMaintGeog(mc, msgUser, req, res, session);
							break;	
						case R_INQ :							
							procReqInquiry(mc, msgUser, req, res, session);
							break;								
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_DELETE_G :
							procActionDeleteGeog(mc,msgUser, req, res, session);
							break;							
						case A_MAINT :
							procActionMaint(mc,msgUser, req, res, session);
							break;
						case A_MAINT_G :
							procActionMaintGeog(mc,msgUser, req, res, session);
							break;							
						case A_NEW :
							procActionNew(mc,msgUser, req, res, session);
							break;
						case A_INQ :
							procActionInquiry(mc,msgUser, req, res, session);
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