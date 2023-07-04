package datapro.eibs.params;

/**
 * Insert the type's description here. Creation date: (1/19/00 6:08:55 PM)
 * 
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEFE0090 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit
	protected static final int R_MAINTENANCE = 3;

	protected static final int A_MAINTENANCE = 4;

	// entering options
	protected static final int R_ENTER = 100;

	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	/**
	 * JSEFE0090 constructor comment.
	 */
	public JSEFE0090() {
		super();
	}

	/**
	 * This method was created by Ramses Amaro.
	 */
	public void destroy() {

		flexLog("free resources used by JSEFE0090");

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
	protected void procActionEnter(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE009001Message msgBroker = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String code = "";
		String purp = "";
		try {
			code = req.getParameter("E01FEBNUM");
			if (code == null || code.trim().equals("")) {
				msgBroker = new datapro.eibs.beans.EFE009001Message();
				msgBroker.setE01FEBATY(req.getParameter("E01FEBATY"));
				userPO.setPurpose("NEW");
				purp = "0001";
				IsNotError = true;
			} else {
				userPO.setPurpose("MAINTENANCE");
				purp = "0002";
				// Send Initial data
				try {
					msgBroker = (EFE009001Message) mc
							.getMessageRecord("EFE009001");
					msgBroker.setH01USERID(user.getH01USR());
					msgBroker.setH01PROGRM("EFE0090");
					msgBroker.setH01TIMSYS(getTimeStamp());
					msgBroker.setH01SCRCOD("01");
					msgBroker.setH01OPECOD(purp);

					msgBroker.setE01FEBNUM(code);

					msgBroker.send();
					msgBroker.destroy();
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
						flexLog("Message " + newmessage.getFormatName()
								+ " received.");

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

				// Receive Data
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EFE009001")) {
						try {
							msgBroker = new datapro.eibs.beans.EFE009001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgBroker = (EFE009001Message) newmessage;
					} else
						flexLog("Message " + newmessage.getFormatName()
								+ " received.");

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

			}
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There are no errors
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("broker", msgBroker);
				try {
					if (msgBroker.getE01FEBATY().equals("V")
							|| msgBroker.getE01FEBATY().equals("S")) {
						flexLog("About to call Page: " + LangPath
								+ "EFE0090_broker_maint.jsp");
						callPage(LangPath + "EFE0090_broker_maint.jsp", req,
								res);
					} else {
						flexLog("About to call Page: " + LangPath
								+ "EFE0090_insurance_maint.jsp");
						callPage(LangPath + "EFE0090_insurance_maint.jsp", req,
								res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0090_broker_enter_maint.jsp");
					callPage(LangPath + "EFE0090_enter_maint.jsp", req, res);
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
	protected void procActionMaintenance(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE009001Message msgBroker = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "0001";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opecode = "0005";
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgBroker = (EFE009001Message) ses.getAttribute("broker");
			msgBroker.setH01USERID(user.getH01USR());
			msgBroker.setH01PROGRM("EFE0090");
			msgBroker.setH01TIMSYS(getTimeStamp());
			msgBroker.setH01SCRCOD("01");
			msgBroker.setH01OPECOD(opecode);

			// all the fields here
			java.util.Enumeration enu = msgBroker.fieldEnumeration();
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

			//msgBroker.send();
			mc.sendMessage(msgBroker);
			msgBroker.destroy();
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

			if (newmessage.getFormatName().equals("EFE009001")) {
				try {
					msgBroker = new datapro.eibs.beans.EFE009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBroker = (EFE009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page1: " + LangPath
								+ "EFE0090_enter_maint.jsp");
						callPage(LangPath + "EFE0090_enter_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					ses.setAttribute("broker", msgBroker);
					ses.setAttribute("userPO", userPO);
					try {
						if (msgBroker.getE01FEBATY().equals("V")
								|| msgBroker.getE01FEBATY().equals("S")) {
							flexLog("About to call Page2: " + LangPath
									+ "EFE0090_broker_maint.jsp");
							callPage(LangPath + "EFE0090_broker_maint.jsp",
									req, res);
						} else {
							flexLog("About to call Page2: " + LangPath
									+ "EFE0090_insurance_maint.jsp");
							callPage(LangPath + "EFE0090_insurance_maint.jsp",
									req, res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		ELEERRMessage msgError = null;
		//UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			//userPO = new datapro.eibs.beans.UserPos();
			ses.setAttribute("error", msgError);
			//ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath
					+ "EFE0090_enter_maint.jsp");
			callPage(LangPath + "EFE0090_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
						.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {

				case R_ENTER:
					procReqEnter(msgUser, req, res, session);
					break;
				default:
					try {
						flexLog("Opennig Socket Connection");
						s = new Socket(super.hostIP, getInitSocket(req) + 1);
						s.setSoTimeout(super.sckTimeOut);
						mc = new MessageContext(new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
								new DataOutputStream(new BufferedOutputStream(s
										.getOutputStream())),
								"datapro.eibs.beans");

						switch (screen) {
						// Action
						case A_ENTER:
							procActionEnter(mc, msgUser, req, res, session);
							break;
						// END Entering
						case A_MAINTENANCE:
							procActionMaintenance(mc, msgUser, req, res,
									session);
							break;
						default:
							res.sendRedirect(super.srctx + LangPath
									+ super.devPage);
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
						int sck = getInitSocket(req) + 1;
						flexLog("Socket not Open(Port " + sck + "). Error: "
								+ e);
						res.sendRedirect(super.srctx + LangPath
								+ super.sckNotOpenPage);
					} finally {
						s.close();
					}

					break;
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath
						+ super.sckNotRespondPage);
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