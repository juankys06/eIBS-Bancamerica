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
import datapro.eibs.beans.EPC000001Message;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEPC0000 extends datapro.eibs.master.SuperServlet {

	// Proyectos de constructor

	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;

	protected static final int R_TITULARES = 7;
	protected static final int A_TITULARES = 8;
	protected static final int R_TITULARES_INQ = 9;

	protected static final int R_CODES = 21;
	protected static final int A_CODES = 22;
	protected static final int R_CODES_INQ = 23;
	
	
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
	public JSEPC0000() {
		super();
	}
	/**
	 * This method was created by AUTHOR
	 */
	public void destroy() {

		flexLog("free resources used by JSEPC0000");

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
						case R_CODES :
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_TITULARES :
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_CODES_INQ :
							procReqSpecialCodesInq(mc, msgUser, req, res, session);
							break;
						case R_TITULARES_INQ :
							procReqTitInq(mc, msgUser, req, res, session);
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
						case A_CODES :
							procActionSpecialCodes(mc, msgUser, req, res, session);
							break;
						case A_TITULARES :
							procActionTit(mc, msgUser, req, res, session);
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
		EPC000001Message msgPC = null;
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
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcNew", msgPC);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page Open New: "
								+ LangPath
								+ "EPC0000_pc_opening.jsp");
						callPage(LangPath + "EPC0000_pc_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page Enter New: "
								+ LangPath
								+ "EPC0000_pc_enter_new.jsp");

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
		EPC000001Message msgPC = null;
		EFT000010Message msgCD = null;
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
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
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
			flexLog("EPC000001 Message Sent");
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
		if (IsNotError) { // There are no errors
			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
					flexLog("EPC000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

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
						"About to call Page pc_confirm-001: "
							+ LangPath
							+ "EPC0000_pc_confirm.jsp");
					callPage(LangPath + "EPC0000_pc_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");



			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCD = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EFT000010Message) newmessage;

				userPO.setIdentifier(msgCD.getE10DEAACC());
				userPO.setHeader1(msgCD.getE10DEAPRO());
				userPO.setHeader2(msgCD.getE10DEACUN());
				userPO.setHeader3(msgCD.getE10CUSNA1());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdFinish", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page confirm_002: "
								+ LangPath
								+ "EPC0000_pc_confirm.jsp");
						callPage(LangPath + "EPC0000_pc_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} else {
			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcNew", msgPC);
 
					try {
						flexLog(
							"About to call Page Open New Today: "
								+ LangPath
								+ "EPC0000_pc_opening.jsp");
						callPage(LangPath + "EPC0000_pc_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				 
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");


		



		}

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
		EPC000001Message msgPC = null;
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
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

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
								+ "EPC0000_pc_maint.jsp");
						callPage(LangPath + "EPC0000_pc_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0000_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0000_pc_enter_maint.jsp",
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
		EPC000001Message msgPC = null;
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
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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
			flexLog("EPC000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
					flexLog("EPC000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

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
								+ "EPC0000_pc_maint.jsp");
						callPage(LangPath + "EPC0000_pc_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0000_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0000_pc_enter_maint.jsp",
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
		EPC000001Message msgPC = null;
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
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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
			flexLog("EPC000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
					flexLog("EPC000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;

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
								+ "EPC0000_pc_inquiry.jsp");
						callPage(LangPath + "EPC0000_pc_inquiry.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPC0000_pc_enter_inquiry.jsp");
						callPage(
							LangPath + "EPC0000_pc_enter_inquiry.jsp",
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
		EPC000001Message msgPC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			userPO = (UserPos) ses.getAttribute("userPO");
			userPO.setIdentifier(req.getParameter("E01PCMACC"));

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}



		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgPC = (EPC000001Message) mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcMant", msgPC);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page maint_03: "
								+ LangPath
								+ "EPC0000_pc_maint.jsp");
						callPage(LangPath + "EPC0000_pc_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page maint 04: "
								+ LangPath
								+ "EPC0000_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0000_pc_enter_maint.jsp",
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
		EPC000001Message msgPC = null;
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
			msgPC = (EPC000001Message) ses.getAttribute("pcMant");
			//msgCD = (EPC000001Message)mc.getMessageRecord("EPC000001");
			msgPC.setH01USERID(user.getH01USR());
			msgPC.setH01PROGRM("EPC0000");
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
			flexLog("EPC000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPC000001")) {
				try {
					msgPC = new EPC000001Message();
					flexLog("EPC000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPC = (EPC000001Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgPC.getE01PCMACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("pcMant", msgPC);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page enter maint 01: "
								+ LangPath
								+ "EPC0000_pc_enter_maint.jsp");
						callPage(
							LangPath + "EPC0000_pc_enter_maint.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page maint 005: "
								+ LangPath
								+ "EPC0000_pc_maint.jsp");
						callPage(LangPath + "EPC0000_pc_maint.jsp", req, res);
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
		EPC000001Message msgPC = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgPC = new EPC000001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("pcMant", msgPC);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page maint 007: "
					+ LangPath
					+ "EPC0000_pc_enter_maint.jsp");
			callPage(LangPath + "EPC0000_pc_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgCD = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EPC0000");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02WK1("M");
			msgCD.setH02SCR("01");
			msgCD.setH02OPE(opCode);
			msgCD.setE02ACC(userPO.getIdentifier());
			msgCD.setE02ACD("19");			
			msgCD.send();
			msgCD.destroy();
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgCD = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);

				if (IsNotError) { // There are no errors
					try {

						flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes.jsp");
						callPage(LangPath + "EPC0000_pc_codes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes.jsp");
						callPage(LangPath + "EPC0000_pc_codes.jsp", req, res);
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
	protected void procActionSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
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
			msgCD = (ESD000002Message) ses.getAttribute("cdCodes");
			//msgCD = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EPC0000");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02SCR("01");
			msgCD.setH02OPE("0005");

			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("ESD000002 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgCD = new ESD000002Message();
					flexLog("ESD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE02ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						{
							flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes.jsp");
							callPage(LangPath + "EPC0000_pc_codes.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes.jsp");
						callPage(LangPath + "EPC0000_pc_codes.jsp", req, res);
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
	protected void procReqSpecialCodesInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgCD = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EPC0000");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02WK1("M");
			msgCD.setH02SCR("01");
			msgCD.setH02OPE(opCode);
			msgCD.setE02ACC(userPO.getIdentifier());
			msgCD.setE02ACD("19");			
			msgCD.send();
			msgCD.destroy();
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgCD = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);

				if (IsNotError) { // There are no errors
					try {

						flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes_inq.jsp");
						callPage(LangPath + "EPC0000_pc_codes_inq.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPC0000_pc_codes_inq.jsp");
						callPage(LangPath + "EPC0000_pc_codes_inq.jsp", req, res);
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
	protected void procReqTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgCD = null;
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
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EPC0000");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06WK1("M");
			msgCD.setH06SCR("01");
			msgCD.setH06OPE(opCode);
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");
			msgCD.send();
			msgCD.destroy();
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgCD = new ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EPC0000_pc_tit.jsp");
						callPage(LangPath + "EPC0000_pc_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EPC0000_pc_enter_maint.jsp");
						callPage(LangPath + "EPC0000_pc_enter_maint.jsp", req, res);
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
	protected void procActionTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgCD = null;
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
			//msgCD = (ESD000006Message)ses.getAttribute("cdTit");
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EPC0000");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06WK1("M");
			msgCD.setH06SCR("01");
			msgCD.setH06OPE("0005");
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");

			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
			flexLog("ESD000006 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgCD = new ESD000006Message();
					flexLog("ESD000006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE06ACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						{
							flexLog("About to call Page: " + LangPath + "EPC0000_pc_tit.jsp");
							callPage(LangPath + "EPC0000_pc_tit.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPC0000_pc_tit.jsp");
						callPage(LangPath + "EPC0000_pc_tit.jsp", req, res);
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
	protected void procReqTitInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message msgCD = null;
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
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EPC0000");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06WK1("M");
			msgCD.setH06SCR("01");
			msgCD.setH06OPE(opCode);
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");
			msgCD.send();
			msgCD.destroy();
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					msgCD = new ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EPC0000_pc_tit_inq.jsp");
						callPage(LangPath + "EPC0000_pc_tit_inq.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EPC0000_pc_tit_inq.jsp");
						callPage(LangPath + "EPC0000_pc_tit_inq.jsp", req, res);
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


protected void procReqEnterInquiry(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EPC000001Message msgPC = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgPC = new EPC000001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("pcMant", msgPC);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page inquiry: "
					+ LangPath
					+ "EPC0000_pc_enter_inquiry.jsp");
			callPage(LangPath + "EPC0000_pc_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}
