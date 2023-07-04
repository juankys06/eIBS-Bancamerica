package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECD0001 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int R_LIST 			= 1;
	static final int A_LIST 			= 2;
	static final int R_NEW				= 3;	
	static final int R_MAINT			= 4;
	static final int A_MAINT			= 5;
	static final int R_DELETE			= 6;
	static final int R_RECEP			= 7;
	static final int A_RECEP			= 8;
	static final int R_LIST_E 			= 101;
	static final int A_LIST_E 			= 102;
	static final int R_NEW_E			= 103;	
	static final int R_MAINT_E			= 104;
	static final int A_MAINT_E			= 105;
	static final int R_DELETE_E			= 106;
	static final int R_RECEP_E			= 107;
	static final int A_RECEP_E			= 108;
	static final int A_NEW_E			= 109;	
	static final int A_CHG_STS			= 110;	
			
	protected String LangPath = "S";

	/**
	 * JSECD0001 constructor comment.
	 */
	public JSECD0001() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0001");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0001DSMessage msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
					callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0001DS")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0001DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								appList.setShowNext(false);
								break;
						} else {
							appList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								appList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("appList", appList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
						callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));

			switch (option) {
				case 1 : // New
					procReqNew(mc, user, req, res, ses);
					break;
				case 2 : // Maintenance
					procReqMaintenance(mc, user, req, res, ses);
					break;
				case 3 : // Recepción
					procReqReception(mc, user, req, res, ses);
					break;
				case 4 : // Delete
					procReqDelete(mc, user, req, res, ses);
					break;
				case 5 : // Inquiry
					procReqInquiry(mc, user, req, res, ses);
					break;
				case 6 : // Validate
					procReqValidate(mc, user, req, res, ses);
					break;
				case 101 : // Status Change
					procReqStatusChange(mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	private void procReqStatusChange(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses) 
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD000101Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appStatusList");

		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD000101Message) appList.getRecord();

			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_req_status.jsp");
				callPage(LangPath + "ECD0001_plastic_req_status.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	
	private void procReqInquiry(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses) 
			throws ServletException, IOException {
		
			MessageRecord newmessage = null;
			ECD0001DSMessage msgCD = null;
			//ECD0001Message msgReq = null;
			ECD000101Message msgResp = null;
			ELEERRMessage msgError = null;
			JBObjList appList = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int option;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			appList = (JBObjList) ses.getAttribute("appList");
			
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
				flexLog("ROW TO PROCESS:"+row) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			appList.setCurrentRow(row);
		
			msgCD = (ECD0001DSMessage) appList.getRecord();
			flexLog( "MSG TEST:" + msgCD.toString() ) ;
			
			userPO.setHeader1(msgCD.getE01CDRNUM());
			userPO.setHeader2(msgCD.getE01CDRNPL());
			userPO.setHeader3(msgCD.getE01CDRQTY());
			userPO.setHeader4(msgCD.getE01CDRREQ());
			userPO.setHeader5(msgCD.getE01CDRDAY());
			userPO.setHeader6(msgCD.getE01CDRMON());
			userPO.setHeader7(msgCD.getE01CDRYEA());
			userPO.setHeader8(msgCD.getE01CDRTPL());
			userPO.setHeader9( Integer.toString( row ) );
			userPO.setHeader10( msgCD.getE01CDRDAR() );
			userPO.setHeader11( msgCD.getE01CDRMOR() );
			userPO.setHeader12( msgCD.getE01CDRYER() );

			//procActionInquiry(mc, user, req, res, ses);	

			// Send Initial data
			try {
				/*
				msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01PROGRM("ECD0001");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01"); */

				msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01OPECOD("0002");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01");
				
				msgCD.setE01CDRNUM(userPO.getHeader1());
				//msgCD.setE01CDRBRD(msgCD.getE01CDRBRD());
				msgCD.setE01CDRTPL(userPO.getHeader8());
				
				msgCD.send();
				flexLog("MSG ECD0001DS Sent.");
				flexLog( msgCD.toString() );
				msgCD.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
						callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else if (newmessage.getFormatName().equals("ECD000101")) {
					JBObjList appInqList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgResp = (ECD000101Message) newmessage;
						marker = msgResp.getH02FLGMAS();

						if (marker.equals("*")) {
							appInqList.setShowNext(false);
							break;
						} else {
							appInqList.addRow(msgResp);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								appInqList.setShowNext(true);
								break;
							}
						}
						
						userPO.setHeader13( msgResp.getE02CDRBRN() );
						userPO.setHeader14( msgResp.getE02CDRBRD() );
						flexLog( "Msg received:" + msgResp.toString() ) ;
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					//ses.setAttribute("appList", appList);
					ses.setAttribute("appStatusList",appInqList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_req_inq_list.jsp");
						callPage(LangPath + "ECD0001_plastic_req_inq_list.jsp", req, res);
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
	
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0001");
	
			try {
				msgCD.setE01CDRTPL(req.getParameter("E01CDRTPL"));
			}
			catch (Exception e){
			}
			try {
				msgCD.setE01CDRQTY(req.getParameter("E01CDRQTY"));
			}
			catch (Exception e){
			}				
			try {
				msgCD.setE01CDRVEN(req.getParameter("E01CDRVEN"));
			}catch (Exception e){}	
			try {
				msgCD.setE01CDRMOX(req.getParameter("E01CDRMOX"));
			}catch (Exception e){}	
			try {
				msgCD.setE01CDRYEX(req.getParameter("E01CDRYEX"));
			}catch (Exception e){}	
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			flexLog("Msg Sent:" + msgCD.toString()  );
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			flexLog("Error Receiving ELEERR Message " );
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;
				flexLog("Msg Received:" + msgCD.toString() );

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
						callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
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
	
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_maint.jsp");
				callPage(LangPath + "ECD0001_plastic_request_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqReception(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_reception.jsp");
				callPage(LangPath + "ECD0001_plastic_request_reception.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_maint.jsp");
						callPage(LangPath + "ECD0001_plastic_request_maint.jsp", req, res);
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
	
	protected void procActionReception(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0090");
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_reception.jsp");
						callPage(LangPath + "ECD0001_plastic_request_reception.jsp", req, res);
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
	
	protected void procReqDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0004");
			
			//msgRT.send();
			mc.sendMessage(msgCD);
			//msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
						callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
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
	
	protected void procReqListEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0001DSMessage msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0095");
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_list.jsp");
					callPage(LangPath + "ECD0001_plastic_embossing_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0001DS")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0001DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								appList.setShowNext(false);
								break;
						} else {
							appList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								appList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("appList", appList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_list.jsp");
						callPage(LangPath + "ECD0001_plastic_embossing_list.jsp", req, res);
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

	protected void procActionListEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));

			switch (option) {
				case 1 : // New
					procReqNewEmbossing(mc, user, req, res, ses);
					break;
				case 2 : // Maintenance
					procReqMaintenanceEmbossing(mc, user, req, res, ses);
					break;
				case 3 : // Recepción
					procReqReceptionEmbossing(mc, user, req, res, ses);
					break;
				case 4 : // Delete
					procReqDeleteEmbossing(mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqNewEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_new.jsp");
				callPage(LangPath + "ECD0001_plastic_embossing_new.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procActionNewEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0096");
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=101");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_new.jsp");
						callPage(LangPath + "ECD0001_plastic_embossing_new.jsp", req, res);
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
	
	protected void procReqMaintenanceEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_maint.jsp");
				callPage(LangPath + "ECD0001_plastic_embossing_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqReceptionEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_reception.jsp");
				callPage(LangPath + "ECD0001_plastic_embossing_reception.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procActionMaintenanceEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0098");
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=101");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_maint.jsp");
						callPage(LangPath + "ECD0001_plastic_embossing_maint.jsp", req, res);
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
	
	protected void procActionReceptionEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) mc.getMessageRecord("ECD0001DS");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0099");
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=101");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_reception.jsp");
						callPage(LangPath + "ECD0001_plastic_embossing_reception.jsp", req, res);
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
	
	protected void procReqDeleteEmbossing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0001DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0001DSMessage) appList.getRecord();
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECD0001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0097");
			
			//msgRT.send();
			mc.sendMessage(msgCD);
			//msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECD0001DS")) {
				msgCD = (ECD0001DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=101");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECD0001_plastic_embossing_list.jsp");
						callPage(LangPath + "ECD0001_plastic_embossing_list.jsp", req, res);
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

			int screen = R_MAINT;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;											
					case A_LIST :					    
						procActionList(mc, msgUser, req, res, session);
						break;	
					case R_NEW:
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINT:
						procReqMaintenance(mc, msgUser, req, res, session);
						break;
					case R_RECEP:
						procReqReception(mc, msgUser, req, res, session);
						break;
					case R_DELETE:
						procReqDelete(mc, msgUser, req, res, session);
						break;
					case A_MAINT:
						procActionMaintenance(mc, msgUser, req, res, session);
						break;
					case A_RECEP:
						procActionReception(mc, msgUser, req, res, session);
						break;
					case R_LIST_E :
						procReqListEmbossing(mc, msgUser, req, res, session);
						break;											
					case A_LIST_E :					    
						procActionListEmbossing(mc, msgUser, req, res, session);
						break;	
					case R_NEW_E:
						procReqNewEmbossing(mc, msgUser, req, res, session);
						break;
					case R_MAINT_E:
						procReqMaintenanceEmbossing(mc, msgUser, req, res, session);
						break;
					case R_RECEP_E:
						procReqReceptionEmbossing(mc, msgUser, req, res, session);
						break;
					case R_DELETE_E:
						procReqDeleteEmbossing(mc, msgUser, req, res, session);
						break;
					case A_MAINT_E:
						procActionMaintenanceEmbossing(mc, msgUser, req, res, session);
						break;
					case A_RECEP_E:
						procActionReceptionEmbossing(mc, msgUser, req, res, session);
						break;
					case A_NEW_E:
						procActionNewEmbossing(mc, msgUser, req, res, session);
						break;
					case A_CHG_STS:
						procActionChangeStatus(mc, msgUser, req, res, session);
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
	
	private void procReqValidate(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses) 
			throws ServletException, IOException {
		
			MessageRecord newmessage = null;
			ECD0001DSMessage msgCD = null;
			ELEERRMessage msgError = null;
			JBObjList appList = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int option;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			appList = (JBObjList) ses.getAttribute("appList");
			
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}
			appList.setCurrentRow(row);		
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgCD = (ECD0001DSMessage) appList.getRecord();
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01PROGRM("ECD0002");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01");
				msgCD.setH01OPECOD("0025");
				
				//msgRT.send();
				mc.sendMessage(msgCD);
				//msgCD.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();
		
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
		
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Data
			try {
				newmessage = mc.receiveMessage();
		
				if (newmessage.getFormatName().equals("ECD0001DS")) {
					msgCD = (ECD0001DSMessage) newmessage;

					if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx
												+ "/servlet/datapro.eibs.products.JSECD0001?SCREEN=1");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("msgCD", msgCD);
						ses.setAttribute("userPO", userPO);
						try {
							flexLog("About to call Page: " + LangPath + "ECD0001_plastic_request_list.jsp");
							callPage(LangPath + "ECD0001_plastic_request_list.jsp", req, res);
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

	protected void procActionChangeStatus(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
		
			MessageRecord newmessage = null;
			ECD000101Message msgCD = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int option;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgCD = (ECD000101Message) mc.getMessageRecord("ECD000101");
				msgCD.setH02USERID(user.getH01USR());
				msgCD.setH02PROGRM("ECD0002");
				msgCD.setH02TIMSYS(getTimeStamp());
				msgCD.setH02SCRCOD("01");
				msgCD.setH02OPECOD("0008");
		
				// all the fields here
				java.util.Enumeration enu = msgCD.fieldEnumeration();
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
		
				//msgRT.send();
				mc.sendMessage(msgCD);
				msgCD.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();
		
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
		
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Data
			try {
				newmessage = mc.receiveMessage();
		
				if (newmessage.getFormatName().equals("ECD000101")) {
					msgCD = (ECD000101Message) newmessage;

					if (IsNotError) { // There are no errors
						try {
							//flexLog("About to call Page: " + LangPath + "ECD0001_plastic_req_inq_list.jsp");
							//callPage(LangPath + "ECD0001_plastic_req_inq_list.jsp", req, res);
							
							
							flexLog("About to call Page: " +
									super.srctx
									+ "/servlet/datapro.eibs.products.JSECD0001?" + 
									"SCREEN=2&opt=5&ROW=" + userPO.getHeader9() );
							res.sendRedirect( super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0001?" + 
											"SCREEN=2&opt=5&ROW=" + userPO.getHeader9() ) ;
							
						
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);

						// No se pone el msg de respuesta en la sesion porque
						// Banvalor quiere que se muestren los datos que inicialmente
						// existian y no los que el usuario ingresó.
						//ses.setAttribute("msgCD", msgCD);
						
						ses.setAttribute("userPO", userPO);

						try {
							flexLog("About to call Page: " + LangPath + "ECD0001_plastic_req_status.jsp");
							callPage(LangPath + "ECD0001_plastic_req_status.jsp", req, res);
							
							
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}						
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received. - MSG FORMAT INCORRECT");
		
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

		}
	
	protected void showERROR(ELEERRMessage m) {
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