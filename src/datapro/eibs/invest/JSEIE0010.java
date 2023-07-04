package datapro.eibs.invest;

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

import datapro.eibs.sockets.*;

public class JSEIE0010 extends datapro.eibs.master.SuperServlet {

	// Request	
	protected static final  int R_ENTER_NEW_UPD = 100;
	protected static final  int R_PORTFOLIO_MGMT = 1;
	protected static final  int R_PORTFOLIO_CASH = 3;
	protected static final  int R_PORTFOLIO_BENEF = 5;
	// Action 
	protected static final  int A_ENTER_NEW_UPD = 200;
	protected static final  int A_PORTFOLIO_MGMT = 2;
	protected static final  int A_PORTFOLIO_CASH = 4;
	protected static final  int A_PORTFOLIO_BENEF = 6;

	protected String LangPath = "S";

	/**
	 * JSEIE00100 constructor comment.
	 */
	public JSEIE0010() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE0010");

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
	protected  void procReqEnterNew(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		

		try {
			msgError = new ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
						
		try {	
			userPO = new UserPos();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}		
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EIE0010_inv_enter_port_new.jsp");
			callPage(LangPath + "EIE0010_inv_enter_port_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected  void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE001001Message msgPortfolio = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		
		if(req.getParameter("OPCODE") == null){
			opCode = "0002";
		}
		else {
		try {
			opCode = req.getParameter("OPCODE");
		} catch (Exception e) {
			opCode = "0002";
		}
		}

		// Send Initial data
		try {
			msgPortfolio = (EIE001001Message) mc.getMessageRecord("EIE001001");
			msgPortfolio.setH01USERID(user.getH01USR());
			msgPortfolio.setH01PROGRM("EIE0010");
			msgPortfolio.setH01TIMSYS(getTimeStamp());
			msgPortfolio.setH01SCRCOD("01");
			msgPortfolio.setH01OPECOD(opCode);

			try {
				msgPortfolio.setE01PRFCUN(req.getParameter("E01PRFCUN"));
			} catch (Exception e) {
				
			}

			try {
				msgPortfolio.setD01CUSNA1(req.getParameter("D01CUSNA1"));
			} catch (Exception e) {
				
			}

			try {
				msgPortfolio.setE01PRFNUM(req.getParameter("E01PRFNUM"));
			} catch (Exception e) {
				
			}

			msgPortfolio.send();
			msgPortfolio.destroy();
			flexLog("EIE001001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE001001")) {
				try {
					msgPortfolio = new EIE001001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPortfolio = (EIE001001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					if (opCode.equals("0001"))
						userPO.setPurpose("NEW");
					else {
						userPO.setPurpose("MAINTENANCE");
						userPO.setCusNum(msgPortfolio.getE01PRFCUN());
						userPO.setIdentifier(msgPortfolio.getE01PRFNUM());
					}
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("invPort", msgPortfolio);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0010_inv_port_basic.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_basic.jsp",
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
								+ "EIE0010_inv_enter_port_new.jsp");
						callPage(
							LangPath + "EIE0010_inv_enter_port_new.jsp",
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
	protected  void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE001001Message msgPortfolio = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgPortfolio = (EIE001001Message) mc.getMessageRecord("EIE001001");
			msgPortfolio.setH01USERID(user.getH01USR());
			msgPortfolio.setH01PROGRM("EIE0010");
			msgPortfolio.setH01TIMSYS(getTimeStamp());
			msgPortfolio.setH01SCRCOD("01");
			msgPortfolio.setH01OPECOD("0002");

			try {
				msgPortfolio.setE01PRFCUN(userPO.getCusNum());
			} catch (Exception e) {
				msgPortfolio.setE01PRFCUN("0");
			}

			try {
				msgPortfolio.setE01PRFNUM(userPO.getIdentifier());
			} catch (Exception e) {
				msgPortfolio.setE01PRFNUM("0");
			}

			msgPortfolio.send();
			msgPortfolio.destroy();
			flexLog("EIE001001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE001001")) {
				try {
					msgPortfolio = new EIE001001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPortfolio = (EIE001001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("invPort", msgPortfolio);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0010_inv_port_basic.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_basic.jsp",
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
								+ "EIE0010_inv_enter_port_new.jsp");
						callPage(
							LangPath + "EIE0010_inv_enter_port_new.jsp",
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
	protected  void procReqCashAccount(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE001101Message msgPortAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgPortAcc = (EIE001101Message) mc.getMessageRecord("EIE001101");
			msgPortAcc.setH01USERID(user.getH01USR());
			msgPortAcc.setH01PROGRM("EIE0011");
			msgPortAcc.setH01TIMSYS(getTimeStamp());
			msgPortAcc.setH01SCRCOD("01");
			msgPortAcc.setH01OPECOD("0015");

			try {
				msgPortAcc.setE01PCACUN(userPO.getCusNum());
			} catch (Exception e) {
				msgPortAcc.setE01PCACUN("0");
			}

			try {
				msgPortAcc.setE01PCANUM(userPO.getIdentifier());
			} catch (Exception e) {
				msgPortAcc.setE01PCANUM("0");
			}

			msgPortAcc.send();
			msgPortAcc.destroy();
			flexLog("EIE001001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE001101")) {
				try {
					msgPortAcc = new EIE001101Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPortAcc = (EIE001101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("invAcc", msgPortAcc);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0010_inv_port_acc.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_acc.jsp",
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
								+ "EIE0010_inv_port_basic.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_basic.jsp",
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
	protected  void procReqPortBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("I");
			msgBene.send();
			msgBene.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0010_inv_port_bene.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_bene.jsp",
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
								+ "EIE0010_inv_port_basic.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_basic.jsp",
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
	protected  void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE001001Message msgPortfolio = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPortfolio = (EIE001001Message) ses.getAttribute("invPort");
			//msgPortfolio = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgPortfolio.setH01USERID(user.getH01USR());
			msgPortfolio.setH01PROGRM("EIE001001");
			msgPortfolio.setH01TIMSYS(getTimeStamp());
			msgPortfolio.setH01SCRCOD("01");
			msgPortfolio.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgPortfolio.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgPortfolio.send();
			mc.sendMessage(msgPortfolio);
			msgPortfolio.destroy();
			flexLog("EIE001001 Message Sent");
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

			try {
				msgPortfolio = new EIE001001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgPortfolio = (EIE001001Message) newmessage;

			userPO.setIdentifier(msgPortfolio.getE01PRFNUM());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invPort", msgPortfolio);

			if (IsNotError) { // There are no errors
				if (userPO.getPurpose().equals("NEW")) {
					userPO.setPurpose("MAINTENANCE");
					userPO.setCusNum(msgPortfolio.getE01PRFCUN());
					userPO.setIdentifier(msgPortfolio.getE01PRFNUM());
					ses.setAttribute("userPO", userPO);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0010_inv_port_confirm.jsp");
						callPage(
							LangPath + "EIE0010_inv_port_confirm.jsp",
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
								+ "EIE0010_inv_enter_port_new.jsp");
						callPage(
							LangPath + "EIE0010_inv_enter_port_new.jsp",
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
							+ "EIE0010_inv_port_basic.jsp");
					callPage(LangPath + "EIE0010_inv_port_basic.jsp", req, res);
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
	protected  void procActionCashAccount(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE001101Message msgPortAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPortAcc = (EIE001101Message) ses.getAttribute("invAcc");
			msgPortAcc.setH01USERID(user.getH01USR());
			msgPortAcc.setH01PROGRM("EIE001101");
			msgPortAcc.setH01TIMSYS(getTimeStamp());
			msgPortAcc.setH01SCRCOD("01");
			msgPortAcc.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgPortAcc.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgPortAcc.send();
			mc.sendMessage(msgPortAcc);
			msgPortAcc.destroy();
			flexLog("EIE001101 Message Sent");
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

			try {
				msgPortAcc = new EIE001101Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgPortAcc = (EIE001101Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invAcc", msgPortAcc);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIE0010_inv_port_basic.jsp");
					callPage(LangPath + "EIE0010_inv_port_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else { // There are errors

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIE0010_inv_port_acc.jsp");
					callPage(LangPath + "EIE0010_inv_port_acc.jsp", req, res);
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

			int screen = R_ENTER_NEW_UPD;

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

						case A_PORTFOLIO_MGMT :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_PORTFOLIO_MGMT :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_PORTFOLIO_BENEF :
							procReqPortBene(mc, msgUser, req, res, session);
							break;
						case R_ENTER_NEW_UPD :
							procReqEnterNew(msgUser, req, res, session);
							break;
						case A_ENTER_NEW_UPD :
							procActionEnterNew(mc, msgUser, req, res, session);
							break;
						case R_PORTFOLIO_CASH :
							procReqCashAccount(mc, msgUser, req, res, session);
							break;
						case A_PORTFOLIO_CASH :
							procActionCashAccount(
								mc,
								msgUser,
								req,
								res,
								session);
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
