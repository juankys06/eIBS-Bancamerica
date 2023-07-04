package datapro.eibs.products;      

/**
 * Insert the type's description here.
 * Creation date: (7/6/2000 4:10:55 PM)
 * @author: David J Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD0430 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_NIGTH_INV = 1;
	protected static final int A_NIGTH_INV = 2;
	protected static final int R_ACC_CONC = 3;
	protected static final int A_ACC_CONC = 4;
	protected static final int R_DEF_CHARGE = 5;
	protected static final int A_DEF_CHARGE = 6;
	protected static final int R_SOB_LIN = 7;
	protected static final int A_SOB_LIN = 8;
	protected static final int R_OVERD_LN = 9;
	protected static final int A_OVERD_LN = 10;
	protected static final int A_OVERD_HE = 11;
	

	// entering options
	protected static final int R_ENTER_ACC = 100;
	protected static final int A_ENTER_ACC = 400;
	protected static final int R_ENTER_CONC = 200;
	protected static final int A_ENTER_CONC = 500;
	protected static final int R_ENTER_SV_ACC = 300;
	protected static final int A_ENTER_SV_ACC = 700;
	protected static final int A_ENTER_TYP = 900;

	protected String LangPath = "S";

	/**
	 * JSEDD0430 constructor comment.
	 */
	public JSEDD0430() {
		super();
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
	protected void procActionConcList(
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
		HttpSession session = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		try {
			if (req.getParameter("ACCNUM") != null)
				userPO.setIdentifier(req.getParameter("ACCNUM"));
		} catch (Exception e) {
			userPO.setIdentifier("0");
			flexLog(" error " + e);
		}

		// Send Initial data

		// Receive Error Message
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			ses.setAttribute("userPO", userPO);
			switch (option) {
				case 1 : // New Relation
					procReqRTConcNew(mc, user, req, res, ses);
					break;
				case 2 : // Maintenance
					procReqRTConc(mc, user, req, res, ses);
					break;
				case 3 : // New Relation
					procReqRTConcNewFlat(mc, user, req, res, ses);
					break;
				default :
					res.sendRedirect(super.srctx +LangPath + "Under_construction.jsp");
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
	protected void procActionEnterAcc(
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
		HttpSession session = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			if (req.getParameter("ACCNUM") != null)
				userPO.setIdentifier(req.getParameter("ACCNUM"));

		} catch (Exception e) {
			userPO.setIdentifier("0");
			flexLog(" error " + e);
		}

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			ses.setAttribute("userPO", userPO);

			switch (option) {
				case 1 : // Overnigth Investments
					procReqRTNight(mc, user, req, res, ses);
					break;
				case 2 : // Account Concentration
					procReqConcList(mc, user, req, res, ses);
					break;
				case 3 : // Deferred Charges
					procReqRTDef(mc, user, req, res, ses);
					break;
				case 4 : // Lin Overdrf
					procReqRTSobLin(mc, user, req, res, ses);
					break;
				case 5 : // Overdrf with Loan
					//procReqRTOverdLn(mc, user, req, res, ses);
					//procReqOverdType(mc, user, req, res, ses);
					procReqRTOverd05(mc, user, req, res, ses);
					break;
				default :
					res.sendRedirect(super.srctx +LangPath + "Under_construction.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionOverdType(
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
		HttpSession session = null;
		String OverdType = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			OverdType = req.getParameter("E01TYP");
		} catch (Exception e) {
			flexLog(" error " + e);
		}

		if (OverdType.equals("L")){
			//New Loan
			procReqRTOverdNewLn(mc, user, req, res, ses);
		} else {
			// Home Equity Loan
			procReqRTOverdHE(mc, user, req, res, ses);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterSVAcc(
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
		HttpSession session = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			try {
				userPO.setIdentifier(req.getParameter("ACCNUM"));
			} catch (Exception e) {
				userPO.setIdentifier("0");
			}
			ses.setAttribute("userPO", userPO);

			// Account Concentration
			procReqConcList(mc, user, req, res, ses);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionRTConc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD043002Message) ses.getAttribute("rtConc");
			//msgRT = (EDD043002Message)mc.getMessageRecord("EDD043002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDD0430");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD("0005");

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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD004302 Message Sent");
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		

		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD043002")) {
				

				msgRT = (EDD043002Message) newmessage;
				msgRT.setHandler(null);

				userPO.setIdentifier(msgRT.getE02ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtConc", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						//flexLog("About to call Page: "	+ LangPath	+ "EDD0430_rt_conct.jsp");
						//callPage(LangPath + "EDD0430_rt_conct.jsp",	req, res);
						procReqConcList(mc, user, req, res, ses);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
		
				} else { // There are errors
					try {
						flexLog("About to call Page: "	+ LangPath	+ "EDD0430_rt_conc_det.jsp");
						callPage(LangPath + "EDD0430_rt_conc_det.jsp",	req, res);
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
	protected void procActionRTDef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043003Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD043003Message) ses.getAttribute("rtDif");
			//msgRT = (EDD043003Message)mc.getMessageRecord("EDD043003");
			msgRT.setH03USERID(user.getH01USR());
			msgRT.setH03PROGRM("EDD0430");
			msgRT.setH03TIMSYS(getTimeStamp());
			msgRT.setH03SCRCOD("01");
			msgRT.setH03OPECOD("0005");

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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD004303 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD043003")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043003Message();
					flexLog("EDD004303 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043003Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE03ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtDif", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0430_rt_enter_acc.jsp");
							callPage(
								LangPath + "EDD0430_rt_enter_acc.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0430_rt_deferr.jsp");
						callPage(LangPath + "EDD0430_rt_deferr.jsp", req, res);
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
	protected void procActionRTNight(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD043001Message) ses.getAttribute("rtNight");
			//msgRT = (EDD043001Message)mc.getMessageRecord("EDD043001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0430");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");

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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD004301 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD043001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043001Message();
					flexLog("EDD004301 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043001Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE01ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtNight", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0430_rt_enter_acc.jsp");
							callPage(
								LangPath + "EDD0430_rt_enter_acc.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0430_rt_overnight.jsp");
						callPage(
							LangPath + "EDD0430_rt_overnight.jsp",
							req,
							res);
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
	protected void procActionRTSobLin(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043004Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD043004Message) ses.getAttribute("rtConc");
			//msgRT = (EDD043004Message)mc.getMessageRecord("EDD043004");
			msgRT.setH04USERID(user.getH01USR());
			msgRT.setH04PROGRM("EDD0430");
			msgRT.setH04TIMSYS(getTimeStamp());
			msgRT.setH04SCRCOD("01");
			msgRT.setH04OPECOD("0005");

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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD004304 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD043004")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043004Message();
					flexLog("EDD004304 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043004Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE04ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtConc", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0430_rt_enter_acc.jsp");
							callPage(
								LangPath + "EDD0430_rt_enter_acc.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0430_rt_sob_lin.jsp");
						callPage(LangPath + "EDD0430_rt_sob_lin.jsp", req, res);
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
	protected void procActionRTOverdLn(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043005Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String recalc = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD043005Message) ses.getAttribute("rtOver");
			//msgRT = (EDD043004Message)mc.getMessageRecord("EDD043005");
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0430");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD("0005");

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

			//msgRT.send();
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD004305 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD043005")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043005Message();
					flexLog("EDD004305 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043005Message) newmessage;
				// showESD008004(msgRT);

				userPO.setIdentifier(msgRT.getE05ACMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOver", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					recalc = req.getParameter("E05RCLFLC");
					if (recalc.equals("X")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0430_rt_overd_ln.jsp");
							callPage(
								LangPath + "EDD0430_rt_overd_ln.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0430_rt_enter_acc.jsp");
							callPage(
								LangPath + "EDD0430_rt_enter_acc.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0430_rt_overd_ln.jsp");
						callPage(
							LangPath + "EDD0430_rt_overd_ln.jsp",
							req,
							res);
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
	protected void procActionRTOverdHE(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043005Message msgRT = null;
		EDD043011Message msgHE = null;
		JBObjList heList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String recalc = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		heList = (datapro.eibs.beans.JBObjList) ses.getAttribute("heList");
        int pos = Integer.parseInt(req.getParameter("PRODITEM"));
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgRT = (EDD043005Message) ses.getAttribute("rtOver");
			//msgRT = (EDD043004Message)mc.getMessageRecord("EDD043005");
			msgRT = new datapro.eibs.beans.EDD043005Message();
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0430");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD("0005");

			heList.setCurrentRow(pos);
			msgHE = (EDD043011Message) heList.getRecord();
			
			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					//value = req.getParameter(field.getTag()).toUpperCase();
					value = msgHE.getFieldString("E11" + field.getTag().substring(3,field.getTag().length())).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("rtOver", msgRT);
			try {
				flexLog("About to call Page: "	+ LangPath	+ "EDD0430_rt_overd_ln.jsp");
				callPage(LangPath + "EDD0430_rt_overd_ln.jsp", req, res);
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
	protected void procReqConcList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EWD0043DSMessage msgSearch = null;
		EWD0043DSMessage msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EWD0043DSMessage) mc.getMessageRecord("EWD0043DS");
			//msgSearch.setEWDUSR(user.getH01USR());
			try {

				try {
					msgSearch.setEWDPAC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("EWDPAC");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0430_rt_enter_acc.jsp");
					callPage(LangPath + "EDD0430_rt_enter_acc.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EWD0043DS")) {

				try {
					beanList = new datapro.eibs.beans.JBList();

				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";

				userPO.setHeader1("");
				userPO.setHeader2("");
				userPO.setHeader3("");

				while (true) {

					msgList = (EWD0043DSMessage) newmessage;

					marker = msgList.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						userPO.setHeader1(msgList.getEWDPRO());
						userPO.setHeader2(msgList.getEWDCUN());
						userPO.setHeader3(msgList.getEWDNA1());
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							userPO.setHeader1(msgList.getEWDPRO());
							userPO.setHeader2(msgList.getEWDCUN());
							userPO.setHeader3(msgList.getEWDNA1());
							chk = "checked";
						} else {
							chk = "";
						}
						try {
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"EWDRAC\" value=\""
								+ msgList.getEWDRAC()
								+ "\" "
								+ chk
								+ "></TD>");
							myRow.append("<TD NOWRAP ALIGN=RIGHT>"
								+ Util.formatCell(msgList.getEWDRAC())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getEWDRNM())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getEWDRCY())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getEWDMAX())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatDate(
									msgList.getEWDMD1(),
									msgList.getEWDMD2(),
									msgList.getEWDMD3())
								+ "</TD>");
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());
						} catch (Exception e) {
							flexLog("Row Failed");
						}

						newmessage = mc.receiveMessage();
					}
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("cifList", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0430_rt_conct.jsp");
					callPage(LangPath + "EDD0430_rt_conct.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterAcc(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("");
			userPO.setPurpose("");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0430_rt_enter_acc.jsp");
			callPage(LangPath + "EDD0430_rt_enter_acc.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	protected void procReqOverdType(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD0430_rt_over_enter.jsp");
			callPage(LangPath + "EDD0430_rt_over_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterSVAcc(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("");
			userPO.setPurpose("");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0430_sv_enter_acc.jsp");
			callPage(LangPath + "EDD0430_sv_enter_acc.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTConc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgRT = (EDD043002Message) mc.getMessageRecord("EDD043002");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDD0430");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD(opCode);
			msgRT.setE02ACMACC(userPO.getIdentifier());
			msgRT.setE02INVRAC(req.getParameter("EWDRAC"));
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043002")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043002Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtConc", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: "	+ LangPath	+ "EDD0430_rt_conc_det.jsp");
						callPage(LangPath + "EDD0430_rt_conc_det.jsp",	req, res);
						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0430_rt_conct.jsp");
						callPage(LangPath + "EDD0430_rt_conct.jsp", req, res);
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
	protected void procReqRTConcNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043002Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			msgRT = new datapro.eibs.beans.EDD043002Message();
			msgRT.setE02INVMUL("25");
			msgRT.setE02INVMIN("50");
			msgRT.setE02INVMAX("0");
			msgRT.setE02INVSTS("1");			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("rtConc", msgRT);

		try {
			flexLog(
				"About to call Page3: " + LangPath + "EDD0430_rt_conc_det.jsp");
			callPage(LangPath + "EDD0430_rt_conc_det.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTConcNewFlat(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043002Message msgRT = null;
		EWD0043DSMessage msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			msgRT = new datapro.eibs.beans.EDD043002Message();
			msgRT.setE02INVMUL("25");
			msgRT.setE02INVMIN("50");
			msgRT.setE02INVMAX("0");
			msgRT.setE02INVSTS("1");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("rtConc", msgRT);

		try {
			flexLog(
				"About to call Page3: " + LangPath + "EDD0430_rt_conc_det.jsp");
			callPage(LangPath + "EDD0430_rt_conc_det.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRTDef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043003Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgRT = (EDD043003Message) mc.getMessageRecord("EDD043003");
			msgRT.setH03USERID(user.getH01USR());
			msgRT.setH03PROGRM("EDD0430");
			msgRT.setH03TIMSYS(getTimeStamp());
			msgRT.setH03SCRCOD("01");
			msgRT.setH03OPECOD(opCode);
			msgRT.setE03ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043003")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043003Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043003Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtDif", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0430_rt_deferr.jsp");
						callPage(LangPath + "EDD0430_rt_deferr.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0430_rt_enter_acc.jsp");
						callPage(
							LangPath + "EDD0430_rt_enter_acc.jsp",
							req,
							res);
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
	protected void procReqRTNight(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgRT = (EDD043001Message) mc.getMessageRecord("EDD043001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0430");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043001Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtNight", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0430_rt_overnight.jsp");
						callPage(
							LangPath + "EDD0430_rt_overnight.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0430_rt_enter_acc.jsp");
						callPage(
							LangPath + "EDD0430_rt_enter_acc.jsp",
							req,
							res);
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
	protected void procReqRTSobLin(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043004Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgRT = (EDD043004Message) mc.getMessageRecord("EDD043004");
			msgRT.setH04USERID(user.getH01USR());
			msgRT.setH04PROGRM("EDD0430");
			msgRT.setH04TIMSYS(getTimeStamp());
			msgRT.setH04SCRCOD("01");
			msgRT.setH04OPECOD(opCode);
			msgRT.setE04ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043004")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043004Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtConc", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0430_rt_sob_lin.jsp");
						callPage(LangPath + "EDD0430_rt_sob_lin.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0430_rt_enter_acc.jsp");
						callPage(
							LangPath + "EDD0430_rt_enter_acc.jsp",
							req,
							res);
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
	protected void procReqRTOverdLn(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043005Message msgRT = null;
		EDD043010Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = null;
		String prodCode = null;
		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgRT = (EDD043005Message) mc.getMessageRecord("EDD043005");
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0430");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD(opCode);
			msgRT.setE05ACMACC(userPO.getIdentifier());
			try {
				prodCode = req.getParameter("PRODCOD");
				if (prodCode != null)
					msgRT.setE05INVPRD(prodCode);
			} catch (Exception e) {
			}
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043005")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043005Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOver", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0430_rt_overd_ln.jsp");
						callPage(
							LangPath + "EDD0430_rt_overd_ln.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDD0430_rt_enter_acc.jsp");
						callPage(
							LangPath + "EDD0430_rt_enter_acc.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else if (newmessage.getFormatName().equals("EDD043010")) {

				int colNum = 2;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgList = (EDD043010Message) newmessage;

					marker = msgList.getH10FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						try {
							myRow[0] = msgList.getE10PROCOD();
							// Product Code					
							myRow[1] = msgList.getE10PRONME();
							// Product Name	
							beanList.addRow(myFlag, myRow);
						} catch (Exception e) {
							flexLog("Row Failed");
						}
						newmessage = mc.receiveMessage();
					}
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("prodList", beanList);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0430_rt_prod_list.jsp");
					callPage(LangPath + "EDD0430_rt_prod_list.jsp", req, res);
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

	protected void procReqRTOverdHE(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043011Message msgList = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = null;
		String prodCode = null;
		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgList = (EDD043011Message) mc.getMessageRecord("EDD043011");
			msgList.setH11USERID(user.getH01USR());
			msgList.setH11PROGRM("EDD0430");
			msgList.setH11TIMSYS(getTimeStamp());
			msgList.setH11SCRCOD("01");
			msgList.setH11OPECOD(opCode);
			msgList.setE11ACMACC(userPO.getIdentifier());

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			 if (newmessage.getFormatName().equals("EDD043011")) {

				beanList = new datapro.eibs.beans.JBObjList();
				
				String marker = "";
				String myFlag = "";

				while (true) {

					msgList = (EDD043011Message) newmessage;
					
					marker = msgList.getH11FLGMAS();
					if (marker.equals("*")) {
						break;
					} else {
						beanList.addRow(msgList);
					}		
					newmessage = mc.receiveMessage();	
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("heList", beanList);
				try {
					flexLog("About to call Page: "	+ LangPath + "EDD0430_rt_he_list.jsp");
					callPage(LangPath + "EDD0430_rt_he_list.jsp", req, res);
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

			int screen = R_ENTER_ACC;

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
						// Requests
						case R_ENTER_ACC :
							procReqEnterAcc(msgUser, req, res, session);
							break;
						case R_ENTER_SV_ACC :
							procReqEnterSVAcc(msgUser, req, res, session);
							break;
						case R_NIGTH_INV :
							procReqRTNight(mc, msgUser, req, res, session);
							break;
						case R_ACC_CONC :
							procReqRTConc(mc, msgUser, req, res, session);
							break;
						case R_DEF_CHARGE :
							procReqRTDef(mc, msgUser, req, res, session);
							break;
						case R_SOB_LIN :
							procReqRTSobLin(mc, msgUser, req, res, session);
							break;
						case R_OVERD_LN :
							procReqRTOverdLn(mc, msgUser, req, res, session);
							break;
							// Actions
						case A_ENTER_ACC :
							procActionEnterAcc(mc, msgUser, req, res, session);
							break;
						case A_ENTER_SV_ACC :
							procActionEnterSVAcc(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_CONC :
							procActionConcList(mc, msgUser, req, res, session);
							break;
						case A_NIGTH_INV :
							procActionRTNight(mc, msgUser, req, res, session);
							break;
						case A_ACC_CONC :
							procActionRTConc(mc, msgUser, req, res, session);
							break;
						case A_DEF_CHARGE :
							procActionRTDef(mc, msgUser, req, res, session);
							break;
						case A_SOB_LIN :
							procActionRTSobLin(mc, msgUser, req, res, session);
							break;
						case A_OVERD_LN :
							procActionRTOverdLn(mc, msgUser, req, res, session);
							break;
						case A_OVERD_HE :
							procActionRTOverdHE(mc, msgUser, req, res, session);
							break;
						case A_ENTER_TYP :
							procActionOverdType(mc, msgUser, req, res, session);
							break;							
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
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
	
	protected void procReqRTOverd05(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD043005Message msgRT = null;
		EDD043010Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = null;
		String prodCode = null;
		opCode = (userPO.getPurpose().equals("APPROVAL_INQ")) ? "0004" : "0002";

		// Send Initial data
		try {
			msgRT = (EDD043005Message) mc.getMessageRecord("EDD043005");
			msgRT.setH05USERID(user.getH01USR());
			msgRT.setH05PROGRM("EDD0430");
			msgRT.setH05TIMSYS(getTimeStamp());
			msgRT.setH05SCRCOD("01");
			msgRT.setH05OPECOD(opCode);
			msgRT.setE05ACMACC(userPO.getIdentifier());
			try {
				prodCode = req.getParameter("PRODCOD");
				if (prodCode != null)
					msgRT.setE05INVPRD(prodCode);
			} catch (Exception e) {
			}
			msgRT.send();
			msgRT.destroy();
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

			if (newmessage.getFormatName().equals("EDD043005")) {
				try {
					msgRT = new datapro.eibs.beans.EDD043005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD043005Message) newmessage;
				// showESD008004(msgRT);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtOver", msgRT);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: "	+ LangPath	+ "EDD0430_rt_overd_ln.jsp");
						callPage(LangPath + "EDD0430_rt_overd_ln.jsp",	req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: "	+ LangPath	+ "EDD0430_rt_enter_acc.jsp");
						callPage(LangPath + "EDD0430_rt_enter_acc.jsp",	req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else if (newmessage.getFormatName().equals("EDD043010")) {

				int colNum = 2;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgList = (EDD043010Message) newmessage;

					marker = msgList.getH10FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						try {
							myRow[0] = msgList.getE10PROCOD();
							// Product Code					
							myRow[1] = msgList.getE10PRONME();
							// Product Name	
							beanList.addRow(myFlag, myRow);
						} catch (Exception e) {
							flexLog("Row Failed");
						}
						newmessage = mc.receiveMessage();
					}
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("prodList", beanList);
				try {
					flexLog("About to call Page: "	+ LangPath	+ "EDD0430_rt_over_enter.jsp");
					callPage(LangPath + "EDD0430_rt_over_enter.jsp", req, res);
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
	
	protected void procReqRTOverdNewLn(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//beanList = (datapro.eibs.beans.JBListRec) ses.getAttribute("prodList");

		flexLog("Putting java beans into the session");
		//ses.setAttribute("prodList", beanList);
		try {
			flexLog("About to call Page: "	+ LangPath	+ "EDD0430_rt_prod_list.jsp");
			callPage(LangPath + "EDD0430_rt_prod_list.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	
	
}