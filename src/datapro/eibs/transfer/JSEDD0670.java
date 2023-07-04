package datapro.eibs.transfer;

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

public class JSEDD0670 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_MAINT = 300;
	protected static final int R_ENTER_INQ = 1100;

	protected static final int A_ENTER_MAINT = 400;
	protected static final int A_ENTER_INQ = 1000;
	protected static final int R_ENTER_CONCENT = 700;
	protected static final int A_ENTER_CONCENT = 900;
	protected static final int A_BASIC = 200;
	protected static final int A_DEL = 600;
	protected static final int A_MAINT = 800;
	protected static final int R_BASIC = 100;
	protected static final int R_ENTER_DEL = 500;

	protected static final int A_CONCENT = 1;

	protected String LangPath = "E";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0670() {
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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD067001Message)ses.getAttribute("cdRate");
			msgPR = (EDD067001Message) mc.getMessageRecord("EDD067001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD0670");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			msgPR.setE01OPRCOD("2");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD067001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD067001")) {
				try {
					msgPR = new datapro.eibs.beans.EDD067001Message();
					flexLog("EDD067001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD067001Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_maint.jsp");
						callPage(LangPath + "EDD0670_pr_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_enter_maint.jsp");
						callPage(
							LangPath + "EDD0670_pr_enter_maint.jsp",
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

	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD067001Message)ses.getAttribute("cdRate");
			msgPR = (EDD067001Message) mc.getMessageRecord("EDD067001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD0670");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			msgPR.setE01OPRCOD("2");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD067001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD067001")) {
				try {
					msgPR = new datapro.eibs.beans.EDD067001Message();
					flexLog("EDD067001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD067001Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_inquiry.jsp");
						callPage(LangPath + "EDD0670_pr_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_enter_inq.jsp");
						callPage(
							LangPath + "EDD0670_pr_enter_inq.jsp",
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

			int screen = R_BASIC;

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
						case R_BASIC :
							procReqBasic(mc, msgUser, req, res, session);
							break;
						case R_ENTER_MAINT :
							procReqEnterMaint(mc, msgUser, req, res, session);
							break;
						case R_ENTER_DEL :
							procReqDelete(mc, msgUser, req, res, session);
							break;
						case R_ENTER_INQ :
							procReqEnterInquiry(mc, msgUser, req, res, session);
							break;

						case A_BASIC :
							procActionBasic(mc, msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaint(mc, msgUser, req, res, session);
							break;
						case A_DEL :
							procActionDelete(mc, msgUser, req, res, session);
							break;

						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_INQ :
							procActionEnterInquiry(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case R_ENTER_CONCENT :
							procReqEnterConcent(mc, msgUser, req, res, session);
							break;
						case A_CONCENT :
							procActionConcentration(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_CONCENT :
							procActionEnterConcent(
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procActionEnterConcent(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD066501Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD066501Message)ses.getAttribute("cdRate");
			msgPR = (EDD066501Message) mc.getMessageRecord("EDD066501");
			msgPR.setH01USR(user.getH01USR());
			msgPR.setH01PGM("EDD0665");
			msgPR.setH01TIM(getTimeStamp());
			msgPR.setH01SCR("01");
			msgPR.setH01OPE("0002");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD066501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD066501")) {
				try {
					msgPR = new datapro.eibs.beans.EDD066501Message();
					flexLog("EDD066501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD066501Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prConcent", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_concentration.jsp");
						callPage(
							LangPath + "EDD0670_pr_concentration.jsp",
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
								+ "EDD0670_pr_enter_concent.jsp");
						callPage(
							LangPath + "EDD0670_pr_enter_concent.jsp",
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
	protected void procActionConcentration(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD066501Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD066501Message)ses.getAttribute("cdRate");
			msgPR = (EDD066501Message) mc.getMessageRecord("EDD066501");
			msgPR.setH01USR(user.getH01USR());
			msgPR.setH01PGM("EDD0665");
			msgPR.setH01TIM(getTimeStamp());
			msgPR.setH01SCR("01");
			msgPR.setH01OPE("0005");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD066501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD066501")) {
				try {
					msgPR = new datapro.eibs.beans.EDD066501Message();
					flexLog("EDD066501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD066501Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prConcent", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_enter_concent.jsp");
						callPage(
							LangPath + "EDD0670_pr_enter_concent.jsp",
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
								+ "EDD0670_pr_concentration.jsp");
						callPage(
							LangPath + "EDD0670_pr_concentration.jsp",
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

	protected void procActionBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String amount1 = "";
		String amount2 = "";
		String auxamount1 = "";
		String auxamount2 = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD067001Message)ses.getAttribute("cdRate");
			msgPR = (EDD067001Message) mc.getMessageRecord("EDD067001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD0670");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			msgPR.setE01OPRCOD("1");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD067001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD067001")) {
				try {
					msgPR = new datapro.eibs.beans.EDD067001Message();
					flexLog("EDD067001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD067001Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_confirm.jsp");
						callPage(LangPath + "EDD0670_pr_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_basic.jsp");
						callPage(LangPath + "EDD0670_pr_basic.jsp", req, res);
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
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD067001Message)ses.getAttribute("cdRate");
			msgPR = (EDD067001Message) mc.getMessageRecord("EDD067001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD0670");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			msgPR.setE01OPRCOD("3");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD067001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD067001")) {
				try {
					msgPR = new datapro.eibs.beans.EDD067001Message();
					flexLog("EDD067001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD067001Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_confirm.jsp");
						callPage(LangPath + "EDD0670_pr_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_delete.jsp");
						callPage(LangPath + "EDD0670_pr_delete.jsp", req, res);
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
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPR = (EDD067001Message) ses.getAttribute("prBasic");
			//msgPR = (EDD067001Message)mc.getMessageRecord("EDD067001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD0670");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			msgPR.setE01OPRCOD("1");

			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
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

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
			flexLog("EDD067001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD067001")) {
				try {
					msgPR = new datapro.eibs.beans.EDD067001Message();
					flexLog("EDD067001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EDD067001Message) newmessage;
				// showESD008004(msgPR);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_confirm.jsp");
						callPage(LangPath + "EDD0670_pr_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0670_pr_maint.jsp");
						callPage(LangPath + "EDD0670_pr_maint.jsp", req, res);
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
	protected void procReqBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgPR = new datapro.eibs.beans.EDD067001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("prBasic", msgPR);

			try {
				flexLog(
					"About to call Page: " + LangPath + "EDD0670_pr_basic.jsp");
				callPage(LangPath + "EDD0670_pr_basic.jsp", req, res);
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
	protected void procReqDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgPR = new datapro.eibs.beans.EDD067001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("prBasic", msgPR);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDD0670_pr_delete.jsp");
				callPage(LangPath + "EDD0670_pr_delete.jsp", req, res);
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
	protected void procReqEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgPR = new datapro.eibs.beans.EDD067001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("prBasic", msgPR);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDD0670_pr_enter_maint.jsp");
				callPage(LangPath + "EDD0670_pr_enter_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqEnterConcent(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD066501Message msgPR = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgPR = new datapro.eibs.beans.EDD066501Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("prBasic", msgPR);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDD0670_pr_enter_concent.jsp");
				callPage(LangPath + "EDD0670_pr_enter_concent.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD067001Message msgPR = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgPR = new datapro.eibs.beans.EDD067001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("prBasic", msgPR);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDD0670_pr_enter_inq.jsp");
				callPage(LangPath + "EDD0670_pr_enter_inq.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}