package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
 * @author: David Mavilla
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESD009001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0090 extends datapro.eibs.master.SuperServlet {
	// RETAIL ACCOUNTS

	protected static final int R_RT_ACC_TELLER_MSG = 1;
	protected static final int A_RT_ACC_TELLER_MSG = 2;
	protected static final int R_RT_INQ_ACC_TELLER_MSG = 3;
	protected static final int R_RT_APP_ACC_TELLER_MSG = 5;
	protected static final int R_RT_CUST_TELLER_MSG = 7;
	protected static final int A_RT_CUST_TELLER_MSG = 8;
	protected static final int R_RT_INQ_CUST_TELLER_MSG = 9;
	protected static final int R_RT_AP_CUST_TELLER_MSG = 11;

	protected String LangPath = "S";

	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) ses.getAttribute("instructions");
			//msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD000010");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			msgRT.setE01SPITYP("T");
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
			flexLog("ESD009001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
					flexLog("ESD009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
	
				userPO.setIdentifier(msgRT.getE01SPIACC());
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("RT")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_basic.jsp");
								callPage(
									LangPath + "EDD0000_rt_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							{
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_sv_basic.jsp");
								callPage(
									LangPath + "EDD0000_sv_basic.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
	
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_rt_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_rt_teller_instructions.jsp", req, res);
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
	protected void procActionCustTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) ses.getAttribute("instructions");
			//msgRT = (ELD000001Message)mc.getMessageRecord("ELD000001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD000010");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			msgRT.setE01SPITYP("X");
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
			flexLog("ESD009001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
					flexLog("ESD009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
	
				userPO.setIdentifier(msgRT.getE01SPIACC());
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0000_cust_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_cust_teller_instructions.jsp", req, res);
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
	protected void procReqTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getIdentifier());
			msgRT.setE01SPITYP("T");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_rt_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_rt_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqCustTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getHeader1());
			msgRT.setE01SPITYP("X");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_cust_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_cust_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
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
	protected void procReqInqTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getIdentifier());
			msgRT.setE01SPITYP("T");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_rt_inq_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_rt_inq_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
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
	protected void procReqApprovalTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getIdentifier());
			msgRT.setE01SPITYP("T");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_rt_ap_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_rt_ap_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getOption().equals("RT")) {
											try {
												flexLog(
													"About to call Page4: "
														+ LangPath
														+ "EDD1000_rt_ap_basic.jsp");
												callPage(
													LangPath + "EDD1000_rt_ap_basic.jsp",
													req,
													res);
											} catch (Exception e) {
												flexLog("Exception calling page " + e);
											}
										} else if (userPO.getOption().equals("SV")) {
											try {
												flexLog(
													"About to call Page4: "
														+ LangPath
														+ "EDD1000_sv_ap_basic.jsp");
												callPage(
													LangPath + "EDD1000_sv_ap_basic.jsp",
													req,
													res);
											} catch (Exception e) {
												flexLog("Exception calling page " + e);
											}

										}				}
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
	protected void procReqInqCustTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getHeader1());
			msgRT.setE01SPITYP("X");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_cust_inq_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_cust_inq_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_personal_basic.jsp");
						callPage(LangPath + "ESD0080_client_inq_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0080_client_inq_corp_basic.jsp");
						callPage(LangPath + "ESD0080_client_inq_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
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
	protected void procReqApprovalCustTellerMessages(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD009001Message msgRT = null;
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
			msgRT = (ESD009001Message) mc.getMessageRecord("ESD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD009001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opCode);
			msgRT.setE01SPIACC(userPO.getIdentifier());
			msgRT.setE01SPITYP("X");
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
	
			if (newmessage.getFormatName().equals("ESD009001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ESD009001Message) newmessage;
				
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("instructions", msgRT);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "ESD0090_cust_ap_teller_instructions.jsp");
						callPage(LangPath + "ESD0090_cust_ap_teller_instructions.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog("About to call Page: " + LangPath + "ESD0100_client_ap_personal_basic.jsp");
						callPage(LangPath + "ESD0100_client_ap_personal_basic.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0100_client_ap_corp_basic.jsp");
						callPage(LangPath + "ESD0100_client_ap_corp_basic.jsp", req, res);
					}
				}
				catch (Exception e) {
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

			int screen = R_RT_ACC_TELLER_MSG;

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
						// BEGIN RETAIL ACCOUNT
						// Request
						case R_RT_ACC_TELLER_MSG :
							procReqTellerMessages(mc, msgUser, req, res, session);
							break;
						case A_RT_ACC_TELLER_MSG :
							procActionTellerMessages(mc, msgUser, req, res, session);
							break;
						//Inquiry
						case R_RT_INQ_ACC_TELLER_MSG :
							procReqInqTellerMessages(mc, msgUser, req, res, session);
							break;
						//Approval
						case R_RT_APP_ACC_TELLER_MSG :
							procReqApprovalTellerMessages(mc, msgUser, req, res, session);
							break;
						
						//Begin Customer
						case R_RT_CUST_TELLER_MSG :
							procReqCustTellerMessages(mc, msgUser, req, res, session);
							break;
						case A_RT_CUST_TELLER_MSG :
							procActionCustTellerMessages(mc, msgUser, req, res, session);
							break;
						case R_RT_INQ_CUST_TELLER_MSG :
							procReqInqCustTellerMessages(mc, msgUser, req, res, session);
							break;
						case R_RT_AP_CUST_TELLER_MSG :
							procReqApprovalCustTellerMessages(mc, msgUser, req, res, session);
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
	

}