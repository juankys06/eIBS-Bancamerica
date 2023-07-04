package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.EPC009001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEPC0090 extends datapro.eibs.master.SuperServlet {

	// Proyectos de constructor

	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;

	protected static final int A_PRINT_FIRST = 48;

	// entering options

	protected static final int R_ENTER_NEW = 100;
	protected static final int A_ENTER_NEW = 200;

	protected static final int R_ENTER_MAINT = 300;
	protected static final int A_ENTER_MAINT = 400;

	protected static final int R_ENTER_INQUIRY = 500;
	protected static final int A_ENTER_INQUIRY = 600;

	protected String LangPath = "S";

	/**
	 * Constructor comment.
	 */
	public JSEPC0090() {
		super();
	}
	/**
	 * This method was created by AUTHOR
	 */
	public void destroy() {

		flexLog("free resources used by JSEPC0090");

	}
	/**
	 * This method was created by AUTHOR
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
					flexLog("Open Socket Connection");
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
						case R_NEW :
							//procReqNew(mc, msgUser, req, res, session);
							break;
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						case R_ENTER_INQUIRY :
							procReqEnterInquiry(msgUser, req, res, session);
							break;

							// Action
						case A_ENTER_NEW :
							procActionEnterNew(mc, msgUser, req, res, session);
							break;
						case A_NEW :
							procActionNew(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_INQUIRY :
							procActionEnterInquiry(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case A_PRINT_FIRST :
							procActionPrintNew(mc, msgUser, req, res, session);
							break;

							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;

		try {
			userPO = (UserPos) ses.getAttribute("userPO");
			userPO.setIdentifier(req.getParameter("E01PCMACC"));
			userPO.setProdCode(req.getParameter("E01PCMPRO"));

			ses.setAttribute("userPO", userPO);
			procReqNew(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
		opCode = "0001";

		// Send Initial data
		try {
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD(opCode);
			msgPC.setE01PCMPRO(userPO.getProdCode());

			try {
				msgPC.setE01PCMACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
			msgPC.send();
			msgPC.destroy();
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
				flexLog("Message " + newmessage.getFormatName() + " 	.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcNew", msgPC);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EPC0090_pc_opening.jsp");
						callPage(LangPath + "EPC0090_pc_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EPC0090_pc_enter_new.jsp");

						String firstLink =
							super.webAppPath
								+ LangPath
								+ "ESD0711_products_detail.jsp?appcode="
								+ req.getParameter("appcode").trim()
								+ "&typecode="
								+ req.getParameter("typecode").trim()
								+ "&generic="
								+ req.getParameter("generic").trim()
								+ "&title="
								+ req.getParameter("title").trim()
								+ "&bank="
								+ req.getParameter("bank").trim();
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						printProdFrame(out, firstLink, LangPath);
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
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EDL0150");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD("0005");
			msgPC.setH01FLGWK3("U");

			// all the fields here
			java.util.Enumeration enu = msgPC.fieldEnumeration();
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

			// msgCD.send();
			mc.sendMessage(msgPC);
			msgPC.destroy();
			flexLog("EPC009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
					flexLog("EPC009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgPC.getE01PCMACC());
				userPO.setCurrency(msgPC.getE01PCMCCY());
				userPO.setHeader3(msgPC.getD01PCMCUN());
				userPO.setHeader2(msgPC.getE01PCMCUN());
				userPO.setHeader1(msgPC.getE01PCMPRO());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcNew", msgPC);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page2: "
							+ LangPath
							+ "EPC0090_pc_opening.jsp");
					callPage(LangPath + "EPC0090_pc_opening.jsp", req, res);
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
	protected void procActionPrintNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD("0002");

			try {
				msgPC.setE01PCMACC(req.getParameter("E01PCMACC"));
			} catch (Exception e) {
				msgPC.setE01PCMACC(userPO.getIdentifier());
			}

			msgPC.send();
			msgPC.destroy();
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;

				userPO.setIdentifier(msgPC.getE01PCMACC());
				userPO.setHeader1(msgPC.getE01PCMPRO());
				userPO.setHeader2(msgPC.getE01PCMCUN());
				userPO.setHeader3(msgPC.getD01PCMCUN());
				userPO.setCurrency(msgPC.getE01PCMCCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("pcMant", msgPC);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_maint.jsp");
						callPage(LangPath + "EPC0090_pc_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0090_pc_enter_maint.jsp",
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

	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD("0002");
			try {
				msgPC.setE01PCMACC(req.getParameter("E01PCMACC"));
			} catch (Exception e) {
				msgPC.setE01PCMACC(userPO.getIdentifier());
			}
			try {
				msgPC.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}

			msgPC.send();
			msgPC.destroy();
			flexLog("EPC009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
					flexLog("EPC009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;

				userPO.setIdentifier(msgPC.getE01PCMACC());
				userPO.setHeader1(msgPC.getE01PCMPRO());
				userPO.setHeader2(msgPC.getE01PCMCUN());
				userPO.setHeader3(msgPC.getD01PCMCUN());
				userPO.setCurrency(msgPC.getE01PCMCCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("pcMant", msgPC);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_maint_sch.jsp");
						callPage(LangPath + "EPC0090_pc_maint_sch.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0090_pc_enter_maint.jsp",
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
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD("0004");
			try {
				msgPC.setE01PCMACC(req.getParameter("E01PCMACC"));
			} catch (Exception e) {
				msgPC.setE01PCMACC(userPO.getIdentifier());
			}
			try {
				msgPC.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}

			msgPC.send();
			msgPC.destroy();
			flexLog("EPC009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
					flexLog("EPC009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;

				userPO.setIdentifier(msgPC.getE01PCMACC());
				userPO.setHeader1(msgPC.getE01PCMPRO());
				userPO.setHeader2(msgPC.getE01PCMCUN());
				userPO.setHeader3(msgPC.getD01PCMCUN());
				userPO.setCurrency(msgPC.getE01PCMCCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("pcMant", msgPC);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_inquiry.jsp");
						callPage(LangPath + "EPC0090_pc_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0090_pc_enter_inquiry.jsp");
						callPage(
							LangPath + "EPC0090_pc_enter_inquiry.jsp",
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



	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD(opCode);
			msgPC.setE01PCMACC(userPO.getIdentifier());
			msgPC.send();
			msgPC.destroy();
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcMant", msgPC);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EPC0090_pc_maint.jsp");
						callPage(LangPath + "EPC0090_pc_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EPC0090_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0090_pc_enter_maint.jsp",
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

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPC009001Message msgPC = null;
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
			msgPC = (EPC009001Message) ses.getAttribute("pcMant");
			//msgCD = (EPC009001Message)mc.getMessageRecord("EPC009001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0090");
			msgPC.setH01TIMSYS(getTimeStamp());
			msgPC.setH01SCRCOD("01");
			msgPC.setH01OPECOD("0005");
			msgPC.setH01FLGWK3("U");

			// all the fields here
			java.util.Enumeration enu = msgPC.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgPC);
			msgPC.destroy();
			flexLog("EPC009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC009001")) {
				try {
					msgPC = new EPC009001Message();
					flexLog("EPC009001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC009001Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgPC.getE01PCMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcMant", msgPC);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page1: "
								+ LangPath
								+ "EPC0090_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0090_pc_enter_maint.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page2: "
								+ LangPath
								+ "EPC0090_pc_maint_sch.jsp");
						callPage(LangPath + "EPC0090_pc_maint_sch.jsp", req, res);
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

	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EPC009001Message msgPC = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgPC = new EPC009001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("pcMant", msgPC);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EPC0090_pc_enter_maint.jsp");
			callPage(LangPath + "EPC0090_pc_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


protected void procReqEnterInquiry(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EPC009001Message msgPC = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgPC = new EPC009001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("pcMant", msgPC);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EPC0090_pc_enter_inquiry.jsp");
			callPage(LangPath + "EPC0090_pc_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}
