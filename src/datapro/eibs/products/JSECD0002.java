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

public class JSECD0002 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int R_LIST 			= 1;
	static final int A_LIST 			= 2;
	static final int R_NEW				= 3;
	static final int R_MAINT			= 4;
	static final int A_MAINT			= 5;
	static final int R_DELETE			= 6;
	static final int R_RECEP			= 7;
	static final int A_RECEP			= 8;
	static final int R_INQ				= 9;
	static final int A_INQ				= 10;
	static final int R_REDIST			= 11;
	static final int A_REDIST			= 12;
	static final int R_CHG_STS			= 101;
	static final int A_CHG_STS			= 102;
			
	protected String LangPath = "S";

	/**
	 * JSECD0002 constructor comment.
	 */
	public JSECD0002() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0002");

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
		ECD0002DSMessage msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ECD0002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			
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
					flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_list.jsp");
					callPage(LangPath + "ECD0002_plastic_dist_recep_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0002DS")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0002DSMessage) newmessage;
						marker = msgList.getH02FLGMAS();

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
						flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_list.jsp");
						callPage(LangPath + "ECD0002_plastic_dist_recep_list.jsp", req, res);
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
				case 6 : // validate
					procReqValidate(mc, user, req, res, ses);
					break;
				case 101 : // Status Change
					procReqChangeStatus(mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	private void procReqValidate(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses) 
			throws ServletException, IOException {
		
			MessageRecord newmessage = null;
			ECD0002DSMessage msgCD = null;
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
				msgCD = (ECD0002DSMessage) appList.getRecord();
				msgCD.setH02USERID(user.getH01USR());
				msgCD.setH02PROGRM("ECD0002");
				msgCD.setH02TIMSYS(getTimeStamp());
				msgCD.setH02SCRCOD("01");
				msgCD.setH02OPECOD("0025");
				
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
		
				if (newmessage.getFormatName().equals("ECD0002DS")) {
					msgCD = (ECD0002DSMessage) newmessage;

					if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx
												+ "/servlet/datapro.eibs.products.JSECD0002?SCREEN=1");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("msgCD", msgCD);
						ses.setAttribute("userPO", userPO);
						try {
							flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_list.jsp");
							callPage(LangPath + "ECD0002_plastic_dist_recep_list.jsp", req, res);
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
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("9999");
	
			msgCD.send();
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;
				flexLog("Message " + newmessage.getFormatName() + " received.");
				
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_new.jsp");
					callPage(LangPath + "ECD0002_plastic_dist_recep_new.jsp", req, res);
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
	
	protected void procAgencyRedistribution(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("9999");
	
			msgCD.send();
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;
				flexLog("Message " + newmessage.getFormatName() + " received.");
				
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0002_agency_redist.jsp");
					callPage(LangPath + "ECD0002_agency_redist.jsp", req, res);
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
	

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
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
	
		msgCD = (ECD0002DSMessage) appList.getRecord();
		
		userPO.setHeader1(msgCD.getE02CDRNUM());
		userPO.setHeader3(msgCD.getE02CDRBRD());
		userPO.setHeader10(msgCD.getE02CDRTPL());
		
		procActionInquiry(mc, user, req, res, ses);
	
	}
	
	protected void procActionInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("0002");
			
			msgCD.setE02CDRNUM(userPO.getHeader1());
			msgCD.setE02CDRBRD(userPO.getHeader3());
			msgCD.setE02CDRTPL(userPO.getHeader10());
			
			msgCD.send();
			msgCD.destroy();
			//msgRT.send();
			//mc.sendMessage(msgCD);
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_list.jsp");
					callPage(LangPath + "ECD0002_plastic_dist_recep_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0002DS")) {
				appList = new JBObjList();
				boolean firstTime = true;
				String marker = "";

				while (true) {
					msgCD = (ECD0002DSMessage) newmessage;
					marker = msgCD.getH02FLGMAS();

					if (marker.equals("*")) {
						userPO.setHeader1(msgCD.getE02CDRNUM());
						userPO.setHeader2(msgCD.getE02CDRBRN());
						userPO.setHeader3(msgCD.getE02CDRBRD());
						userPO.setHeader4(msgCD.getE02CDRDAY());
						userPO.setHeader5(msgCD.getE02CDRMON());
						userPO.setHeader6(msgCD.getE02CDRYEA());
						userPO.setHeader7(msgCD.getE02CDRDAR());
						userPO.setHeader8(msgCD.getE02CDRMOR());
						userPO.setHeader9(msgCD.getE02CDRYER());
						appList.setShowNext(false);
						break;
					} else {
						appList.addRow(msgCD);
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
					flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_inq_list.jsp");
					callPage(LangPath + "ECD0002_plastic_dist_recep_inq_list.jsp", req, res);
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
	
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
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
			msgCD = (ECD0002DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_maint.jsp");
				callPage(LangPath + "ECD0002_plastic_dist_recep_maint.jsp", req, res);
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
		ECD0002DSMessage msgCD = null;
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
			msgCD = (ECD0002DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_reception.jsp");
				callPage(LangPath + "ECD0002_plastic_dist_recep_reception.jsp", req, res);
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
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String confirm = "";
		boolean IsNotError = false;
		int option;

		try {
			confirm = req.getParameter("CONFIRM");
		} catch (Exception e) {
			confirm = "";
		}
		if (confirm == null) confirm = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "0005";
		if (userPO.getPurpose().equals("NEW")) {
			if (confirm.equals("") || confirm.equals("0") ){
				opecode = "0001";
			} else {
				opecode = "0019";
			} 
		} else {
			opecode = "0005";
		}
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD(opecode);
	
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						//if (confirm.equals("0"))
						//{
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0002?SCREEN=1");
						//}
						/*else
						{
							flexLog("Putting java beans into the session");
							ses.setAttribute("error", msgError);
							ses.setAttribute("msgCD", msgCD);
							ses.setAttribute("userPO", userPO);

							flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_confirm.jsp");
							callPage(LangPath + "ECD0002_plastic_dist_recep_confirm.jsp", req, res);
						}*/
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					if (userPO.getPurpose().equals("NEW")) {

						try {
							flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_new.jsp");
							callPage(LangPath + "ECD0002_plastic_dist_recep_new.jsp", req, res);
						} catch (Exception e) {
								flexLog("Exception calling page " + e);
						}						
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_maint.jsp");
							callPage(LangPath + "ECD0002_plastic_dist_recep_maint.jsp", req, res);
						} catch (Exception e) {
								flexLog("Exception calling page " + e);
						}
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
	
	protected void procActionAgencyRedist(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String confirm = "";
		boolean IsNotError = false;
		int option;

		confirm = (String) req.getParameter("CONFIRM");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "";
		if (confirm.equals("0")){
			opecode = "0011";
		} else {
			opecode = "0018";
		} 

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD(opecode);
	
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						if (confirm.equals("0"))
						{
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0002?SCREEN=1");
						}
						else
						{
							flexLog("Putting java beans into the session");
							ses.setAttribute("error", msgError);
							ses.setAttribute("msgCD", msgCD);
							ses.setAttribute("userPO", userPO);

							flexLog("About to call Page: " + LangPath + "ECD0002_agency_redist_confirm.jsp");
							callPage(LangPath + "ECD0002_agency_redist_confirm.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0002_agency_redist.jsp");
						callPage(LangPath + "ECD0002_agency_redist.jsp", req, res);
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
		ECD0002DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("0005");
	
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0002?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_reception.jsp");
						callPage(LangPath + "ECD0002_plastic_dist_recep_reception.jsp", req, res);
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
			ECD0002DSMessage msgCD = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int option;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgCD = (ECD0002DSMessage) mc.getMessageRecord("ECD0002DS");
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
		
				if (newmessage.getFormatName().equals("ECD0002DS")) {
					msgCD = (ECD0002DSMessage) newmessage;

					if (IsNotError) { // There are no errors
						try {
							flexLog("Abaout to call:" + 
									"/servlet/datapro.eibs.products.JSECD0002?SCREEN=10" );
							res.sendRedirect(super.srctx
												+ "/servlet/datapro.eibs.products.JSECD0002?SCREEN=10");
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
							flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_status.jsp");
							callPage(LangPath + "ECD0002_plastic_dist_recep_status.jsp", req, res);
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
		ECD0002DSMessage msgCD = null;
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
			msgCD = (ECD0002DSMessage) appList.getRecord();
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("ECD0002");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD("0004");
			
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
	
			if (newmessage.getFormatName().equals("ECD0002DS")) {
				msgCD = (ECD0002DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0002?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_list.jsp");
						callPage(LangPath + "ECD0002_plastic_dist_recep_list.jsp", req, res);
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
	
	protected void procReqChangeStatus(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0002DSMessage msgCD = null;
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
			msgCD = (ECD0002DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0002_plastic_dist_recep_status.jsp");
				callPage(LangPath + "ECD0002_plastic_dist_recep_status.jsp", req, res);
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
					case R_CHG_STS:
						procReqChangeStatus(mc, msgUser, req, res, session);						
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
					case R_INQ:
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case A_INQ:
						procActionInquiry(mc, msgUser, req, res, session);
						break;
					case A_CHG_STS:
						procActionChangeStatus(mc, msgUser, req, res, session);
						break;
					case R_REDIST:
						procAgencyRedistribution(mc, msgUser, req, res, session);
						break;
					case A_REDIST:
						procActionAgencyRedist(mc, msgUser, req, res, session);
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