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

public class JSEIV0301 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange 

	protected static final int A_BONDS = 4;
	protected static final int A_STOCKS = 6;

	protected static final int R_ENTER_BASIC = 100;
	protected static final int A_ENTER_BASIC = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIV0301() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0130");

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
	protected void procActionBonds(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIV030101Message msgFex = null;
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
			msgFex = (EIV030101Message) ses.getAttribute("inv");
			//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EIV0301");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01FLGWK2("2");
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
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

			//msgFex.send();
			mc.sendMessage(msgFex);
			msgFex.destroy();
			flexLog("EFE0120DS Message Sent");
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
				msgFex = new datapro.eibs.beans.EIV030101Message();
				flexLog("EIV030101 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EIV030101Message) newmessage;
			// showESD008004(msgFex);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inv", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIV0301_inv_basic_bonds_ticket.jsp");
					callPage(
						LangPath + "EIV0301_inv_basic_bonds_ticket.jsp",
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
							+ "EIV0301_inv_basic_bonds.jsp");
					callPage(
						LangPath + "EIV0301_inv_basic_bonds.jsp",
						req,
						res);
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

	protected void procActionStocks(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIV030101Message msgFex = null;
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
			msgFex = (EIV030101Message) ses.getAttribute("inv");
			//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EIV0301");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01FLGWK2("2");
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
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

			//msgFex.send();
			mc.sendMessage(msgFex);
			msgFex.destroy();
			flexLog("EFE0120DS Message Sent");
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
				msgFex = new datapro.eibs.beans.EIV030101Message();
				flexLog("EIV030101 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EIV030101Message) newmessage;
			// showESD008004(msgFex);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inv", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIV0301_inv_basic_stocks_ticket.jsp");
					callPage(
						LangPath + "EIV0301_inv_basic_stocks_ticket.jsp",
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
							+ "EIV0301_inv_basic_stocks.jsp");
					callPage(
						LangPath + "EIV0301_inv_basic_stocks.jsp",
						req,
						res);
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

	protected void procActionEnterBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIV030101Message msgFex = null;
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
			//msgFex = (EIV030101Message)ses.getAttribute("inv");
			msgFex = (EIV030101Message) mc.getMessageRecord("EIV030101");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EIV0301");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01FLGWK2("2");
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
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

			//msgFex.send();
			mc.sendMessage(msgFex);
			msgFex.destroy();
			flexLog("EFE0120DS Message Sent");
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
				msgFex = new datapro.eibs.beans.EIV030101Message();
				flexLog("EIV030101 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EIV030101Message) newmessage;
			// showESD008004(msgFex);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inv", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				if (msgFex.getE01IVSCLS().equals("F")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIV0301_inv_basic_bonds.jsp");
						callPage(
							LangPath + "EIV0301_inv_basic_bonds.jsp",
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
								+ "EIV0301_inv_basic_stocks.jsp");
						callPage(
							LangPath + "EIV0301_inv_basic_stocks.jsp",
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
							+ "EIV0301_inv_enter_basic.jsp");
					callPage(
						LangPath + "EIV0301_inv_enter_basic.jsp",
						req,
						res);
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

	protected void procReqEnterBasic(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EIV0301_inv_enter_basic.jsp");
			callPage(LangPath + "EIV0301_inv_enter_basic.jsp", req, res);
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

			int screen = A_BONDS;

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
						// BEGIN Fex
						// Action
						case A_BONDS :
							procActionBonds(mc, msgUser, req, res, session);
							break;
						case A_STOCKS :
							procActionStocks(mc, msgUser, req, res, session);
							break;

							// END CD

							// BEGIN Entering
							// Request

						case R_ENTER_BASIC :
							procReqEnterBasic(msgUser, req, res, session);
							break;

							// Action 
						case A_ENTER_BASIC :
							procActionEnterBasic(
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