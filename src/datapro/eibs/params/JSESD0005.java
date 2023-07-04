package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000506Message;
import datapro.eibs.beans.ESD000508Message;
import datapro.eibs.beans.ESD000504Message;
import datapro.eibs.beans.ESD000503Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.CharacterField;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0005 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PAY_INSTRUCTION 	= 1;
	protected static final int A_PAY_INSTRUCTION 	= 2;
	protected static final int A_WTP 				= 3;
	protected static final int A_MP 				= 4;
	protected static final int A_INTERFACE			= 5;
	protected static final int R_ENTER_MAINT 		= 100;
	protected static final int A_ENTER_MAINT 		= 200; 
	protected static final int R_ENTER_WTP 			= 300;
	protected static final int A_ENTER_WTP 			= 400;
	protected static final int R_ENTER_MP 			= 500;
	protected static final int A_ENTER_MP 			= 600;
	protected static final int R_ENTER_INTERFACE	= 700;
	protected static final int A_ENTER_INTERFACE	= 800;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD0005() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0005");

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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		// Send Initial data
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			String bnk = req.getParameter("BANK");
			String ccy = req.getParameter("CCY");
			String type = req.getParameter("E06TYP");


			userPO.setBank(bnk);
			userPO.setCurrency(ccy);
			userPO.setType(type);

			ses.setAttribute("userPO", userPO);

			procReqCorpPayInst(mc, user, req, res, ses);

		} catch (Exception e) {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0005_bank_instructions_enter.jsp");
			callPage(
				LangPath + "ESD0005_bank_instructions_enter.jsp",
				req,
				res);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD000506Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgPayInst = new datapro.eibs.beans.ESD000506Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("BANK_INSTRUCTIONS");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("pymInst", msgPayInst);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0005_bank_instructions_enter.jsp");
			callPage(
				LangPath + "ESD0005_bank_instructions_enter.jsp",
				req,
				res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterTransferParams(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ESD000504Message msgTP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
	
			msgTP = new datapro.eibs.beans.ESD000504Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("TRANSFER_PARAMETERS");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("pymInst", msgTP);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
	
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0005_wt_parameters_enter.jsp");
			callPage(
				LangPath + "ESD0005_wt_parameters_enter.jsp",
				req,
				res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
		 * This method was created in VisualAge.
		 */
		protected void procReqEnterInterfaceParams(
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			ESD000508Message msgEF = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
	
			try {
	
				msgEF = new datapro.eibs.beans.ESD000508Message();
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				userPO.setOption("EFUNDS_PARAMETERS");
				userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("efunds", msgEF);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESD0005_efunds_parameters_enter.jsp");
				callPage(
					LangPath + "ESD0005_efunds_parameters_enter.jsp",
					req,
					res);
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception calling page " + e);
			}
	
		}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMesgParams(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ESD000503Message msgMP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
	
			msgMP = new datapro.eibs.beans.ESD000503Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("GRAL_CTRL_PARAMETERS");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("pymInst", msgMP);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
	
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0005_msg_parameters_enter.jsp");
			callPage(
				LangPath + "ESD0005_msg_parameters_enter.jsp",
				req,
				res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	/**
	 * This method was created in VisualAge.
	 */
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

			int screen = R_ENTER_MAINT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}
				
				switch (screen) {
					case R_ENTER_MAINT :
						procReqEnterMaint(msgUser, req, res, session);
						break;
					case R_ENTER_WTP :
						procReqEnterTransferParams(msgUser, req, res, session);
						break;
					case R_ENTER_MP :
						procReqEnterMesgParams(msgUser, req, res, session);
						break;
					case R_ENTER_INTERFACE :
						procReqEnterInterfaceParams(msgUser, req, res, session);
						break;
					default :
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
		
							
							switch (screen) {
								// Request
								case R_PAY_INSTRUCTION :
									procReqCorpPayInst(mc, msgUser, req, res, session);
									break;
									// Action
								case A_PAY_INSTRUCTION :
									procActionCorpPayInst(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_WTP :
									procActionTransferParams(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_MP :
									procActionMesgParams(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_INTERFACE :
									procActionInterfaceParams(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
									// END Personal & Corporative 
		
									// Action 
								case A_ENTER_MAINT :
									procActionEnterMaint(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_ENTER_WTP :
									procActionEnterTransferParams(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_ENTER_MP :
									procActionEnterMesgParams(
										mc,
										msgUser,
										req,
										res,
										session);
									break;
								case A_ENTER_INTERFACE :
										procActionEnterInterfaceParams(
											mc,
											msgUser,
											req,
											res,
											session);
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
						break;
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	protected void procReqCorpPayInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000506Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0003";

		// Send Initial data
		try {
			msgPayInst = (ESD000506Message) mc.getMessageRecord("ESD000506");
			msgPayInst.setH06USR(user.getH01USR());
			msgPayInst.setH06PGM("ESD0080");
			msgPayInst.setH06TIM(getTimeStamp());
			msgPayInst.setH06SCR("01");
			msgPayInst.setH06OPE(opCode);
			try {
				msgPayInst.setE06BNK(userPO.getBank());
				msgPayInst.setE06CCY(userPO.getCurrency());
				msgPayInst.setE06TYP(userPO.getType());
			} catch (Exception e) {
			}
			msgPayInst.send();
			msgPayInst.destroy();
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

			if (newmessage.getFormatName().equals("ESD000506")) {
				try {
					msgPayInst =
						(
							datapro
								.eibs
								.beans
								.ESD000506Message) Beans
								.instantiate(
							getClass().getClassLoader(),"datapro.eibs.beans.ESD000506Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayInst = (ESD000506Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pymInst", msgPayInst);

				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: " + LangPath + "ESD0005_bank_instructions.jsp");
						callPage(
							LangPath + "ESD0005_bank_instructions.jsp",
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
								+ "ESD0005_bank_instructions_enter.jsp");
						callPage(
							LangPath + "ESD0005_bank_instructions_enter.jsp",
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

	protected void procActionEnterTransferParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000504Message msgWTP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0002";
	
		// Send Initial data
		try {
			msgWTP = (ESD000504Message) mc.getMessageRecord("ESD000504");
			msgWTP.setH04USR(user.getH01USR());
			msgWTP.setH04PGM("ESD0005");
			msgWTP.setH04TIM(getTimeStamp());
			msgWTP.setH04SCR("01");
			msgWTP.setH04OPE(opCode);
			try {
				msgWTP.setE04CWTBNK(req.getParameter("E04CWTBNK"));
			} catch (Exception e) {
			}
			msgWTP.send();
			msgWTP.destroy();
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
	
			if (newmessage.getFormatName().equals("ESD000504")) {
				
	
				msgWTP = (ESD000504Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("wtParam", msgWTP);
	
				if (IsNotError) { // There are no errors
					try {
	
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_wt_parameters.jsp");
						callPage(
							LangPath + "ESD0005_wt_parameters.jsp",
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
								+ "ESD0005_wt_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_wt_parameters_enter.jsp",
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
	
	protected void procActionEnterInterfaceParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000508Message msgEF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgEF = (ESD000508Message) mc.getMessageRecord("ESD000508");
			msgEF.setH08USERID(user.getH01USR());
			msgEF.setH08PROGRM("ESD0005");
			msgEF.setH08TIMSYS(getTimeStamp());
			msgEF.setH08SCRCOD("01");
			msgEF.setH08OPECOD(opCode);
			try {
				msgEF.setE08EFNBNK(req.getParameter("E08EFNBNK"));
				msgEF.setH08FLGWK1(req.getParameter("OPT"));
			} catch (Exception e) {
			}
			msgEF.send();
			msgEF.destroy();
		

		// Receive Error Message

		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		
		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000508")) {
			

				msgEF = (ESD000508Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("efunds", msgEF);

				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_efunds_parameters.jsp");
						callPage(
							LangPath + "ESD0005_efunds_parameters.jsp",
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
								+ "ESD0005_efunds_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_efunds_parameters_enter.jsp",
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
	
	protected void procActionEnterMesgParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000503Message msgMP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0002";
	
		// Send Initial data
		try {
			msgMP = (ESD000503Message) mc.getMessageRecord("ESD000503");
			msgMP.setH03USR(user.getH01USR());
			msgMP.setH03PGM("ESD0080");
			msgMP.setH03TIM(getTimeStamp());
			msgMP.setH03SCR("01");
			msgMP.setH03OPE(opCode);
			try {
				msgMP.setE03MSGBNK(req.getParameter("E03MSGBNK"));
				
			} catch (Exception e) {
			}
			msgMP.send();
			msgMP.destroy();
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
	
			if (newmessage.getFormatName().equals("ESD000503")) {
					
				msgMP = (ESD000503Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgParam", msgMP);
	
				if (IsNotError) { // There are no errors
					try {
	
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_msg_parameters.jsp");
						callPage(
							LangPath + "ESD0005_msg_parameters.jsp",
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
								+ "ESD0005_msg_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_msg_parameters_enter.jsp",
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
	protected void procActionCorpPayInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000506Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0005";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPayInst = (ESD000506Message) mc.getMessageRecord("ESD000506");
			msgPayInst.setH06USR(user.getH01USR());
			msgPayInst.setH06PGM("ESD0080");
			msgPayInst.setH06TIM(getTimeStamp());
			msgPayInst.setH06SCR("01");
			msgPayInst.setH06OPE(opCode);

			// all the fields here
			java.util.Enumeration enu = msgPayInst.fieldEnumeration();
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

			msgPayInst.send();
			msgPayInst.destroy();
			flexLog("ESD000506 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000506")) {
				
				msgPayInst = (ESD000506Message) newmessage;
				// showESD000506(msgPayInst);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pymInst", msgPayInst);

				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_bank_instructions_enter.jsp");
						callPage(
							LangPath + "ESD0005_bank_instructions_enter.jsp",
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
								+ "ESD0005_bank_instructions.jsp");
						callPage(
							LangPath + "ESD0005_bank_instructions.jsp",
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

	protected void procActionMesgParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000503Message msgMP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0005";
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgMP = (ESD000503Message) mc.getMessageRecord("ESD000503");
			msgMP.setH03USR(user.getH01USR());
			msgMP.setH03PGM("ESD0005");
			msgMP.setH03TIM(getTimeStamp());
			msgMP.setH03SCR("01");
			msgMP.setH03OPE(opCode);
	
			// all the fields here
			java.util.Enumeration enu = msgMP.fieldEnumeration();
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
	
			msgMP.send();
			msgMP.destroy();
			
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
	
			if (newmessage.getFormatName().equals("ESD000503")) {
				
				msgMP = (ESD000503Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgParam", msgMP);
	
				if (IsNotError) { // There are no errors
					try {
	
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_msg_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_msg_parameters_enter.jsp",
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
								+ "ESD0005_msg_parameters.jsp");
						callPage(
							LangPath + "ESD0005_msg_parameters.jsp",
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

	protected void procActionTransferParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000504Message msgWTP = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0005";
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgWTP = (ESD000504Message) mc.getMessageRecord("ESD000504");
			msgWTP.setH04USR(user.getH01USR());
			msgWTP.setH04PGM("ESD0080");
			msgWTP.setH04TIM(getTimeStamp());
			msgWTP.setH04SCR("01");
			msgWTP.setH04OPE(opCode);
	
			// all the fields here
			java.util.Enumeration enu = msgWTP.fieldEnumeration();
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
	
			msgWTP.send();
			msgWTP.destroy();
			flexLog("ESD000506 Message Sent");
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
			
			if (newmessage.getFormatName().equals("ESD000504")) {
				
				msgWTP = (ESD000504Message) newmessage;
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("wtParam", msgWTP);
	
				if (IsNotError) { // There are no errors
					try {
	
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_wt_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_wt_parameters_enter.jsp",
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
								+ "ESD0005_wt_parameters.jsp");
						callPage(
							LangPath + "ESD0005_wt_parameters.jsp",
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
	
	protected void procActionInterfaceParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD000508Message msgEF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0005";
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgEF = (ESD000508Message) mc.getMessageRecord("ESD000508");
			msgEF.setH08USERID(user.getH01USR());
			msgEF.setH08PROGRM("ESD0005");
			msgEF.setH08TIMSYS(getTimeStamp());
			msgEF.setH08SCRCOD("01");
			msgEF.setH08OPECOD(opCode);
	
			// all the fields here
			java.util.Enumeration enu = msgEF.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						if (field.getTag().toUpperCase().equals("E08EFNPWD")) {
							value = req.getParameter(field.getTag());
							CharacterField cf = (CharacterField) field;
							cf.setStringUD(value);
						} else field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgEF.send();
			msgEF.destroy();
			flexLog("ESD000508 Message Sent");
		
	
		// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
			
		// Receive Data
		
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ESD000508")) {
				
				msgEF = (ESD000508Message) newmessage;
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("efunds", msgEF);
	
				if (IsNotError) { // There are no errors
					try {
	
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0005_efunds_parameters_enter.jsp");
						callPage(
							LangPath + "ESD0005_efunds_parameters_enter.jsp",
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
								+ "ESD0005_efunds_parameters.jsp");
						callPage(
							LangPath + "ESD0005_efunds_parameters.jsp",
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


}