package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSECC0030 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_ENTER_LIST = 100;
	protected static final int R_CODES_LIST = 400;

	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;
	protected static final int A_MAINTENANCE = 600;
	protected static final int A_DELETE = 700;
	
	protected static final int R_TABLE_NEW = 1300;
	protected static final int R_TABLE_MAINTENANCE = 1500;
	protected static final int A_TABLE_MAINTENANCE = 1600;
	protected static final int A_TABLE_DELETE = 1700;

	protected String LangPath = "S";

	/**
	 * JSECC00030 constructor comment.
	 */
	public JSECC0030() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procActionPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=300");
				break;
			case 2 : //Maintenance
				procReqMaintenance(mc, user, req, res, ses);
				break;

			case 3 : //Deletion
				procActionDelete(mc, user, req, res, ses);
				break;

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=500");
				break;
		}
	}

	protected void procReqEnterList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC003001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECC003001Message) mc.getMessageRecord("ECC003001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC003001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.send();
			msgList.destroy();
			flexLog("ECC003001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		/*
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
		*/
		
		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECC003001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String chk = "";
				//var for ofac status
				//var for Warning status
				while (true) {

					msgList = (ECC003001Message) newmessage;

					marker = msgList.getE01CCDOPE();

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
				ses.setAttribute("ECC003001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECC0030_cnofc_enter_list.jsp");
					callPage(
						LangPath + "ECC0030_cnofc_enter_list.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procReqCodesList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC003002Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			msgList = (ECC003002Message) mc.getMessageRecord("ECC003002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ECC0030");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			
			try{
				msgList.setE02CCTTBL(req.getParameter("TABLECODE"));
			} catch (Exception e) {
				msgList.setE02CCTTBL(userPO.getHeader6());
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("ECC003002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		/*
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
		*/

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECC003002")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				//var for ofac status
				//var for Warning status
				while (true) {

					msgList = (ECC003002Message) newmessage;

					marker = msgList.getE02CCTOPE();

					if (firstTime) {
						firstTime = false;
						userPO.setHeader10(msgList.getE02CCTTBL());
						//userPO.setHeader5(msgList.getE02CCTREF());
						userPO.setHeader6(msgList.getE02CCTTBL());
					}

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
				ses.setAttribute("ECC003002Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECC0030_cnofc_cc_list.jsp");
					callPage(
						LangPath + "ECC0030_cnofc_cc_list.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}
	
	protected void procReqTableNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			ECC003001Message ccCodes = null;
			UserPos userPO = null;

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("NEW");

			ccCodes = new ECC003001Message();
			
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("ccCodes", ccCodes);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECC0030_cnofc_table_generic_details.jsp");
				callPage(LangPath
					+ "ECC0030_cnofc_table_generic_details.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	}
	
	protected void procReqTableMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
			ECC003001Message msgDoc = null;
			UserPos userPO = null;
			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");

			// Receive Data
			try {
				JBObjList bl = (JBObjList) ses.getAttribute("ECC003001Help");
				int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
				bl.setCurrentRow(idx);

				msgDoc = (ECC003001Message) bl.getRecord();

				flexLog("Putting java beans into the session");
			
				ses.setAttribute("ccCodes", msgDoc);
				ses.setAttribute("userPO", userPO);
			
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECC0030_cnofc_table_generic_details.jsp");
					callPage(LangPath
						+ "ECC0030_cnofc_table_generic_details.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

	}
	
	protected void procActionTableMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC003001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "0005";
		if (userPO.getPurpose().equals("NEW"))
			opecode = "0001";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ECC003001Message) ses.getAttribute("ccCodes");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECC0030");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD(opecode);

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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
			
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ECC003001 Message Sent");
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

			if (newmessage.getFormatName().equals("ECC003001")) {
				try {
					msgRT = new ECC003001Message();
					flexLog("ECC003001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ECC003001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ccCodes", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=100");
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECC0030_cnofc_table_generic_details.jsp");
						callPage(LangPath
							+ "ECC0030_cnofc_table_generic_details.jsp", req, res);
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
	
	protected void procActionTableDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECC003001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ECC003001Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
			bl.setCurrentRow(idx);
	
			msgRT = (ECC003001Message) bl.getRecord();
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECC0030");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0009");

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ECC003001 Message Sent");
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
	
			res.sendRedirect(
									super.srctx
										+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=100");
	
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
		ECC003002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "0005";
		if (userPO.getPurpose().equals("NEW"))
			opecode = "0001";
			
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ECC003002Message) ses.getAttribute("ccCodes");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("ECC0030");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02OPECOD(opecode);

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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
			
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ECC003002 Message Sent");
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

			if (newmessage.getFormatName().equals("ECC003002")) {
				try {
					msgRT = new ECC003002Message();
					flexLog("ECC003002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ECC003002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ccCodes", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=400");
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECC0030_cnofc_generic_details.jsp");
						callPage(LangPath
							+ "ECC0030_cnofc_generic_details.jsp", req, res);
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
	
		MessageRecord newmessage = null;
		ECC003002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ECC003002Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
			bl.setCurrentRow(idx);
	
			msgRT = (ECC003002Message) bl.getRecord();
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("ECC0030");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02OPECOD("0009");
			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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
	
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ECC003002 Message Sent");
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
	
			res.sendRedirect(
									super.srctx
										+ "/servlet/datapro.eibs.params.JSECC0030?SCREEN=400");
	
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
		ELEERRMessage msgError = null;
		ECC003002Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		msgRT = new ECC003002Message();
		msgRT.setE02CCTTBL(userPO.getHeader10());
		
		ses.setAttribute("userPO", userPO);	
		ses.setAttribute("ccCodes", msgRT);

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ECC0030_cnofc_generic_details.jsp");
			callPage(LangPath
				+ "ECC0030_cnofc_generic_details.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ECC003002Message msgDoc = null;
		UserPos userPO = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ECC003002Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
			bl.setCurrentRow(idx);

			msgDoc = (ECC003002Message) bl.getRecord();

			flexLog("Putting java beans into the session");
			
			ses.setAttribute("ccCodes", msgDoc);
			ses.setAttribute("userPO", userPO);
			
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECC0030_cnofc_generic_details.jsp");
				callPage(LangPath
					+ "ECC0030_cnofc_generic_details.jsp", req, res);
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

			int screen = A_POSITION;

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

						//Request

						case R_ENTER_LIST :
							procReqEnterList(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_CODES_LIST :
							procReqCodesList(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_TABLE_NEW :
							procReqTableNew(mc, msgUser, req, res, session);
							break;
						case R_TABLE_MAINTENANCE :
							procReqTableMaintenance(mc, msgUser, req, res, session);
							break;

							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_TABLE_DELETE :
							procActionTableDelete(mc, msgUser, req, res, session);
							break;
						case A_DELETE :
							procActionDelete(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_TABLE_MAINTENANCE :
							procActionTableMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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
