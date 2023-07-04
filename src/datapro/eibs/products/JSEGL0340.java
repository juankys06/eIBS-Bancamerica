package datapro.eibs.products;

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
import java.util.Calendar;

import datapro.eibs.sockets.*;

public class JSEGL0340 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_PASSWORD = 1;
	protected static final int R_GENLEDGER = 2;
	protected static final int A_GENLEDGER = 3;
	protected static final int R_CROSSREF = 4;
	protected static final int A_CROSSREF = 5;
	protected static final int A_GENLEDGER_CANCEL = 6;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	protected static final int A_ENTER_INQ = 300;
	protected String LangPath = "S";

	/**
	 * JSEGL0034 constructor comment.
	 */
	public JSEGL0340() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEGL0340");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		String Option = "";
		//Option = 'C' Cancel  or 'M' Maintenance
		try {
			Option = req.getParameter("OPT");
		}
		catch (Exception e){
			Option = "M"; 
		}
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEGL0340?SCREEN=" + R_ENTER + "&OPT=" + Option);
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034001Message msgGL = null;
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
			flexLog("Sending header");
			flexLog("header 1");

			msgGL = (EGL034001Message) mc.getMessageRecord("EGL034001");
			msgGL.setH01USERID(user.getH01USR());
			msgGL.setH01PROGRM("EGL0340");
			msgGL.setH01TIMSYS(getTimeStamp());
			msgGL.setH01SCRCOD("01");
			msgGL.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgGL.fieldEnumeration();
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

			msgGL.send();
			msgGL.destroy();
			flexLog("EGL034001 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034001")) {
				try {
					msgGL = new datapro.eibs.beans.EGL034001Message();
					flexLog("EGL034001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGL = (EGL034001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gLedger", msgGL);

				if (IsNotError) { // There are no errors
					try {
						if (userPO.getPurpose().equals("MAINTENANCE")) {
							flexLog("About to call Page: "+ LangPath + "EGL0340_gledger_basic.jsp");
							callPage(LangPath + "EGL0340_gledger_basic.jsp",req,res);
						} else {
							flexLog("About to call Page: "+ LangPath + "EGL0340_gledger_basic_cancel.jsp");
							callPage(LangPath + "EGL0340_gledger_basic_cancel.jsp",req,res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_enter.jsp");
						callPage(
							LangPath + "EGL0340_gledger_enter.jsp",
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
	protected void procActionEnterInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034001Message msgGL = null;
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
			flexLog("Sending header");
			flexLog("header 1");

			msgGL = (EGL034001Message) mc.getMessageRecord("EGL034001");
			msgGL.setH01USERID(user.getH01USR());
			msgGL.setH01PROGRM("EGL0340");
			msgGL.setH01TIMSYS(getTimeStamp());
			msgGL.setH01SCRCOD("01");
			msgGL.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgGL.fieldEnumeration();
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

			msgGL.send();
			msgGL.destroy();
			flexLog("EGL034001 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034001")) {
				try {
					msgGL = new datapro.eibs.beans.EGL034001Message();
					flexLog("EGL034001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}       

				msgGL = (EGL034001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gLedger", msgGL);

				if (IsNotError) { // There are no errors
					try {
							flexLog("About to call Page: "+ LangPath + "EGL0340_gledger_basic_inq.jsp");
							callPage(LangPath + "EGL0340_gledger_basic_inq.jsp",req,res);
						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_basic_inq.jsp");
						callPage(
							LangPath + "EGL0340_gledger_basic_inq.jsp",
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
	protected void procActionLedger(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034001Message msgGL = null;
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
			flexLog("Sending header");
			flexLog("header 1");

			msgGL = (EGL034001Message) mc.getMessageRecord("EGL034001");
			msgGL.setH01USERID(user.getH01USR());
			msgGL.setH01PROGRM("EGL0340");
			msgGL.setH01TIMSYS(getTimeStamp());
			msgGL.setH01SCRCOD("01");
			msgGL.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgGL.fieldEnumeration();
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

			msgGL.send();
			msgGL.destroy();
			flexLog("EGL034001 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034001")) {
				try {
					msgGL = new datapro.eibs.beans.EGL034001Message();
					flexLog("EGL034001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGL = (EGL034001Message) newmessage;
				//flexLog("" + msgGL);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gLedger", msgGL);

				if (IsNotError) { // There are no errors
					if (msgGL.getE01GLMACD().equals("")
						&& !(msgGL.getE01GLMRVF().equals("Y"))
						&& !(msgGL.getE01GLMPRV().equals("Y"))
						&& msgGL.getE01GLMREV().equals("N")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EGL0340_gledger_confirm.jsp");
							callPage(
								LangPath + "EGL0340_gledger_confirm.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						//Socket s = null;
						//s = new Socket(super.hostIP, getInitSocket(req) + 1);
						//s.setSoTimeout(super.sckTimeOut);
						//mc =
						//	new MessageContext(
						//		new DataInputStream(
						//			new BufferedInputStream(
						//				s.getInputStream())),
						//		new DataOutputStream(
						////			new BufferedOutputStream(
						//				s.getOutputStream())),
						//		"datapro.eibs.beans");
						procReqCrossRef(mc, user, req, res, ses);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_basic.jsp");
						callPage(
							LangPath + "EGL0340_gledger_basic.jsp",
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
	protected void procActionLedgerCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034001Message msgGL = null;
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
			flexLog("Sending header");
			flexLog("header 1");

			msgGL = (EGL034001Message) mc.getMessageRecord("EGL034001");
			msgGL.setH01USERID(user.getH01USR());
			msgGL.setH01PROGRM("EGL0340");
			msgGL.setH01TIMSYS(getTimeStamp());
			msgGL.setH01SCRCOD("01");
			msgGL.setH01OPECOD("0009");

			// all the fields here
			java.util.Enumeration enu = msgGL.fieldEnumeration();
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

			msgGL.send();
			msgGL.destroy();
			flexLog("EGL034001 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034001")) {
				try {
					msgGL = new datapro.eibs.beans.EGL034001Message();
					flexLog("EGL034001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGL = (EGL034001Message) newmessage;
				//flexLog("" + msgGL);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gLedger", msgGL);

				if (IsNotError) { // There are no errors
					
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EGL0340_gledger_enter.jsp");
							callPage(
								LangPath + "EGL0340_gledger_enter.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_basic_cancel.jsp");
						callPage(
							LangPath + "EGL0340_gledger_basic_cancel.jsp",
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
	protected void procActionCrossRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034002Message msgCR = null;
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
			flexLog("Sending header");
			flexLog("header 1");

			msgCR = (EGL034002Message) mc.getMessageRecord("EGL034002");
			msgCR.setH02USERID(user.getH01USR());
			msgCR.setH02PROGRM("EGL0340");
			msgCR.setH02TIMSYS(getTimeStamp());
			msgCR.setH02SCRCOD("01");
			msgCR.setH02OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgCR.fieldEnumeration();
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

			msgCR.send();
			msgCR.destroy();
			flexLog("EGL034001 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034002")) {
				try {
					msgCR = new datapro.eibs.beans.EGL034002Message();
					flexLog("EGL034001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCR = (EGL034002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("crossRef", msgCR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_confirm.jsp");
						callPage(
							LangPath + "EGL0340_gledger_confirm.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_crossRef_basic.jsp");
						callPage(
							LangPath + "EGL0340_crossRef_basic.jsp",
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
	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EGL034001Message glData = null;
		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			glData = new datapro.eibs.beans.EGL034001Message();
			userPO.setOption("TRANSACTION");
			try {
				if (req.getParameter("OPT").equals("C")) userPO.setPurpose("CANCEL");
				else userPO.setPurpose("MAINTENANCE");
			}
			catch(Exception e){
				userPO.setPurpose("MAINTENANCE");
			}
			glData.setE01GLMBNK(user.getE01UBK());
			glData.setE01GLMCCY(user.getE01BCU());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("gLedger", glData);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EGL0340_gledger_enter.jsp");
			callPage(LangPath + "EGL0340_gledger_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqCrossRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034001Message msgGL = null;
		EGL034002Message msgCR = null;
		ELEERRMessage msgError = null;
		DataTransaction transData = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgGL =
			(datapro.eibs.beans.EGL034001Message) ses.getAttribute("gLedger");

		// Send Initial data
		try {
			msgCR = (EGL034002Message) mc.getMessageRecord("EGL034002");
			msgCR.setH02USERID(user.getH01USR());
			msgCR.setH02PROGRM("EGL0340");
			msgCR.setH02TIMSYS(getTimeStamp());
			msgCR.setH02SCRCOD("01");
			msgCR.setH02OPECOD("0002");

			// Get Parameters here	
			try {
				msgCR.setE02GLMBNK(msgGL.getE01GLMBNK());
			} catch (Exception e) {
			}
			try {
				msgCR.setE02GLMCCY(msgGL.getE01GLMCCY());
			} catch (Exception e) {
			}
			try {
				msgCR.setE02GLMGLN(msgGL.getE01GLMGLN());
			} catch (Exception e) {
			}

			msgCR.send();
			msgCR.destroy();
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

			if (newmessage.getFormatName().equals("EGL034002")) {

				msgCR = (EGL034002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("crossRef", msgCR);
				//ses.setAttribute("transData", transData);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_crossRef_basic.jsp");
						callPage(
							LangPath + "EGL0340_crossRef_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EGL0340_gledger_basic.jsp");
						callPage(
							LangPath + "EGL0340_gledger_basic.jsp",
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

			int screen = R_ENTER;

			try {

				flexLog("Screen  Number: " + screen);

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
						// Request
						case R_PASSWORD :
							procReqPassword(req, res, session);
							break;
							// Request
						case R_ENTER :
							procReqEnter(msgUser, req, res, session);
							break;
						case R_CROSSREF :
							procReqCrossRef(mc, msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_ENTER_INQ :
							procActionEnterInq(mc, msgUser, req, res, session);
							break;	
						case A_GENLEDGER :
							procActionLedger(mc, msgUser, req, res, session);
							break;
						case A_GENLEDGER_CANCEL :
							procActionLedgerCancel(mc, msgUser, req, res, session);
							break;
						case A_CROSSREF :
							procActionCrossRef(mc, msgUser, req, res, session);
							break;
						// END Entering

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