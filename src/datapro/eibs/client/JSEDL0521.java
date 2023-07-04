package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0521 extends datapro.eibs.master.SuperServlet {

	protected static final int R_CLASIF = 1;
	protected static final int A_CLASIF = 2;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0521() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0521");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL052101Message msgClasif = null;
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
			msgClasif = (EDL052101Message) mc.getMessageRecord("EDL052101");
			msgClasif.setH01USERID(user.getH01USR());
			msgClasif.setH01PROGRM("EDL0521");
			msgClasif.setH01TIMSYS(getTimeStamp());
			msgClasif.setH01SCRCOD("01");
			msgClasif.setH01OPECOD("0002");
			try {
				msgClasif.setE01CUSCUN(req.getParameter("E01CUSCUN"));
			} catch (Exception e) {
				msgClasif.setE01CUSCUN("0");
			}
			try {
				msgClasif.setE01CUSIDN(req.getParameter("E01CUSIDN"));
			} catch (Exception e) {
				msgClasif.setE01CUSIDN("0");
			}

			msgClasif.send();
			msgClasif.destroy();
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

			if (newmessage.getFormatName().equals("EDL052101")) {
				try {
					msgClasif = new datapro.eibs.beans.EDL052101Message();
					flexLog("EDL052101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgClasif = (EDL052101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("clasif", msgClasif);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0521_client_clasif.jsp");
						callPage(
							LangPath + "EDL0521_client_clasif.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: /pages/"
								+ LangPath
								+ "EDL0521_client_clasif_enter.jsp");
						callPage(
							LangPath + "EDL0521_client_clasif_enter.jsp",
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
	protected void procActionClasif(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL052101Message msgClasif = null;
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
			//msgClasif = (EDL052101Message)mc.getMessageRecord("EDL052101");
			msgClasif = (EDL052101Message) ses.getAttribute("clasif");
			msgClasif.setH01USERID(user.getH01USR());
			msgClasif.setH01PROGRM("EDL0521");
			msgClasif.setH01TIMSYS(getTimeStamp());
			msgClasif.setH01SCRCOD("01");
			msgClasif.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgClasif.fieldEnumeration();
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

			mc.sendMessage(msgClasif); //msgClasif.send();	
			msgClasif.destroy();
			flexLog("EDL052101 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL052101")) {
				try {
					msgClasif = new datapro.eibs.beans.EDL052101Message();
					flexLog("EDL052101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgClasif = (EDL052101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("clasif", msgClasif);
				//ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (userPO.getOption().equals("CLIENT_P")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0080_client_personal_basic.jsp");
						callPage(
							LangPath + "ESD0080_client_personal_basic.jsp",
							req,
							res);
					} else if (userPO.getOption().equals("CLIENT_C")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0080_client_corp_basic.jsp");
						callPage(
							LangPath + "ESD0080_client_corp_basic.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0521_client_clasif_enter.jsp");
						callPage(
							LangPath + "EDL0521_client_clasif_enter.jsp",
							req,
							res);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0521_client_clasif.jsp");
						callPage(
							LangPath + "EDL0521_client_clasif.jsp",
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
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDL052101Message msgClasif = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgClasif = new datapro.eibs.beans.EDL052101Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("CLASIF");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("clasif", msgClasif);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0521_client_clasif_enter.jsp");
			callPage(LangPath + "EDL0521_client_clasif_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqClasif(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL052101Message msgClasif = null;
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
			msgClasif = (EDL052101Message) mc.getMessageRecord("EDL052101");
			msgClasif.setH01USERID(user.getH01USR());
			msgClasif.setH01PROGRM("EDL0521");
			msgClasif.setH01TIMSYS(getTimeStamp());
			msgClasif.setH01SCRCOD("01");
			msgClasif.setH01OPECOD(opCode);
			msgClasif.setE01CUSCUN(userPO.getCusNum());
			msgClasif.send();
			msgClasif.destroy();
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

			if (newmessage.getFormatName().equals("EDL052101")) {
				try {
					msgClasif = new datapro.eibs.beans.EDL052101Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgClasif = (EDL052101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("clasif", msgClasif);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0521_client_clasif.jsp");
						callPage(
							LangPath + "EDL0521_client_clasif.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						if (userPO.getOption().equals("CLIENT_P")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0080_client_personal_basic.jsp");
							callPage(
								LangPath + "ESD0080_client_personal_basic.jsp",
								req,
								res);
						} else if (userPO.getOption().equals("CLIENT_C")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0080_client_corp_basic.jsp");
							callPage(
								LangPath + "ESD0080_client_corp_basic.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0521_client_clasif_enter.jsp");
							callPage(
								LangPath + "EDL0521_client_clasif_enter.jsp",
								req,
								res);
						}
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

						//req. options
						case R_ENTER :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						case R_CLASIF :
							procReqClasif(mc, msgUser, req, res, session);
							break;
							//action options
						case A_ENTER :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_CLASIF :
							procActionClasif(mc, msgUser, req, res, session);
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