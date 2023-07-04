package datapro.eibs.forex;

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

public class JSEFE0120P extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange
	protected static final int R_SPOT = 1;

	protected static final int R_MAINTENANCE = 3;

	protected static final int A_MAINTENANCE = 4;

	protected static final int R_FINISH = 37;

	protected static final int A_FINISH = 38;

	protected static final int A_MAINT_DEAL = 40;

	// Spots
	protected static final int A_SPOT = 2;

	protected static final int A_FAST_SPOT = 10;

	protected static final int A_OPTION = 12;

	protected static final int A_FORWARD = 14;

	protected static final int A_SWAP = 16;

	protected static final int A_NDF = 18;

	protected static final int A_CNG_SPOT = 20;

	// entering options
	protected static final int R_ENTER_SPOT = 100;

	protected static final int R_ENTER_MAINT = 300;

	protected static final int A_ENTER_SPOT = 200;

	protected static final int A_ENTER_MAINT = 400;

	protected static final int A_ENTER_FAST_SPOT = 600;

	protected static final int A_ENTER_OPTION = 800;

	protected static final int A_ENTER_FORWARD = 1000;

	protected static final int A_ENTER_SWAP = 1200;

	protected static final int A_ENTER_ND_FORWARD = 1400;

	protected static final int A_ENTER_CNG_SPOT = 1600;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEFE0120P() {
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
	 * Treasury - Dealer Slip Main Menu
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

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
						.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc = new MessageContext(new DataInputStream(
							new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s
									.getOutputStream())), "datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
					// BEGIN
					// Action
					case A_SPOT:
						procActionMaintenance(mc, msgUser, req, res, session);
						break;
					case A_FAST_SPOT:
						procActionFastSpot(mc, msgUser, req, res, session);
						break;
					case A_OPTION:
						procActionOption(mc, msgUser, req, res, session);
						break;
					case A_FORWARD:
						procActionForward(mc, msgUser, req, res, session);
						break;
					case A_SWAP:
						procActionSwap(mc, msgUser, req, res, session);
						break;
					case A_NDF:
						procActionNDForward(mc, msgUser, req, res, session);
						break;
					// END ACTIONS

					// BEGIN Entering
					// Request
					case R_ENTER_SPOT:
						procReqEnterNew(msgUser, req, res, session);
						break;
					case R_ENTER_MAINT:
						procReqEnterMaint(msgUser, req, res, session);
						break;

					// Action
					case A_ENTER_SPOT:
						// Cutomized as required by Bancamerica
						procActionEnterNew(mc, msgUser, req, res, session);
						break;
					case A_ENTER_FAST_SPOT:
						procActionEnterFastSpot(mc, msgUser, req, res, session);
						break;
					case A_ENTER_OPTION:
						procActionEnterOption(mc, msgUser, req, res, session);
						break;
					case A_ENTER_FORWARD:
						procActionEnterForward(mc, msgUser, req, res, session);
						break;
					case A_ENTER_SWAP:
						procActionEnterSwap(mc, msgUser, req, res, session);
						break;
					case A_MAINT_DEAL:
						procActionMaintDeal(mc, msgUser, req, res, session);
						break;
					case A_ENTER_ND_FORWARD:
						procActionEnterNDForward(mc, msgUser, req, res, session);
						break;

					// END Entering

					default:
						res
								.sendRedirect(super.srctx + LangPath
										+ super.devPage);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath
							+ super.sckNotOpenPage);
					return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath
						+ super.sckNotRespondPage);
			}

		}
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESLN3") != null)
					msgFex.setE01FESLN3(req.getParameter("E01FESLN3"));
			} catch (Exception e) {
				msgFex.setE01FESLN3("0");
			}

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			try {
				if (req.getParameter("E01FESTYP") != null)
					msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));
			} catch (Exception e) {
				msgFex.setE01FESTYP("SPOT");
			}

			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors

					ESD008001Message client = new ESD008001Message();
					client.setE01JPG(msgFex.getE01JPG());
					client.setE01CUN(msgFex.getE01FESCUN());
					ses.setAttribute("client", client);

					if (msgFex.getE01FESTYP().equals("OPTI")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_opt.jsp");
							callPage(LangPath + "EFE0120P_fe_basic_opt.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_sf.jsp");
							callPage(LangPath + "EFE0120P_fe_basic_sf.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionMaintDeal(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		TrOption trOption = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		trOption = (datapro.eibs.beans.TrOption) ses.getAttribute("trOption");

		String opCode = null;

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0002");

			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;

				if (msgFex.getE01FESTYP().equals("OPTI")) {
					trOption.setHeader1("5");
				} else if (msgFex.getE01FESTYP().equals("SPOT")) {
					trOption.setHeader1("2");
				} else if (msgFex.getE01FESTYP().equals("FWRD")) {
					trOption.setHeader1("3");
				} else if (msgFex.getE01FESTYP().equals("SWAP")) {
					trOption.setHeader1("4");
				} else if (msgFex.getE01FESTYP().trim().equals("FSP")) {
					trOption.setHeader1("1");
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);
				ses.setAttribute("trOption", trOption);

				if (IsNotError) { // There are no errors
					if (msgFex.getE01FESTYP().equals("OPTI")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_opt.jsp");
							callPage(LangPath + "EFE0120P_fe_basic_opt.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgFex.getE01FESTYP().equals("SPOT")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_sf.jsp");
							callPage(LangPath + "EFE0120P_fe_basic_sf.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgFex.getE01FESTYP().equals("FWRD")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_forward.jsp");
							callPage(
									LangPath + "EFE0120P_fe_basic_forward.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgFex.getE01FESTYP().equals("SWAP")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_swap.jsp");
							callPage(LangPath + "EFE0120P_fe_basic_swap.jsp",
									req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (msgFex.getE01FESTYP().trim().equals("FSP")) {
						try {
							flexLog("About to call Page: " + LangPath
									+ "EFE0120P_fe_basic_fast_spot.jsp");
							callPage(LangPath
									+ "EFE0120P_fe_basic_fast_spot.jsp", req,
									res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionEnterFastSpot(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));

			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_fast_spot.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_fast_spot.jsp",
								req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterOption(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			try {
				if (req.getParameter("E01FESTYP") != null)
					msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));
			} catch (Exception e) {
				msgFex.setE01FESTYP("OPTI");
			}
			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}

			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_opt.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_opt.jsp", req,
								res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterForward(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			try {
				if (req.getParameter("E01FESTYP") != null)
					msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));
			} catch (Exception e) {
				msgFex.setE01FESTYP("OPTI");
			}

			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_forward.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_forward.jsp",
								req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterNDForward(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			try {
				if (req.getParameter("E01FESTYP") != null)
					msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));
			} catch (Exception e) {
				msgFex.setE01FESTYP("OPTI");
			}

			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_nd_forward.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_nd_forward.jsp",
								req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterSwap(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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

		if (!req.getParameter("E01FESREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01FESTIN") != null)
					msgFex.setE01FESTIN(req.getParameter("E01FESTIN"));
			} catch (Exception e) {
				msgFex.setE01FESTIN("0");
			}

			try {
				if (req.getParameter("E01FESCUN") != null)
					msgFex.setE01FESCUN(req.getParameter("E01FESCUN"));
			} catch (Exception e) {
				msgFex.setE01FESCUN("0");
			}

			try {
				if (req.getParameter("E01FESTYP") != null)
					msgFex.setE01FESTYP(req.getParameter("E01FESTYP"));
			} catch (Exception e) {
				msgFex.setE01FESTYP("OPTI");
			}
			try {
				if (req.getParameter("E01FESREF") != null)
					msgFex.setE01FESREF(req.getParameter("E01FESREF"));
			} catch (Exception e) {
				msgFex.setE01FESREF("0");
			}
			try {
				if (req.getParameter("E01FESDID") != null)
					msgFex.setE01FESDID(req.getParameter("E01FESDID"));
			} catch (Exception e) {
				msgFex.setE01FESDID("");
			}

			msgFex.send();
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_swap.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_swap.jsp", req,
								res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res
							.sendRedirect(super.srctx
									+ "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
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
	protected void procActionMaintenance(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;
			// showESD008004(msgFex);
			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_spot.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_spot.jsp", req,
							res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				if (msgFex.getE01FESTYP().equals("OPTI")) {
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_opt.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_opt.jsp", req,
								res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath
								+ "EFE0120P_fe_basic_sf.jsp");
						callPage(LangPath + "EFE0120P_fe_basic_sf.jsp", req,
								res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionFastSpot(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");
			msgFex.setE01FESTYP("FSP ");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_fast_spot.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_fast_spot.jsp",
							req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_basic_fast_spot.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_fast_spot.jsp", req,
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

	protected void procActionForward(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_forward.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_forward.jsp", req,
							res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_basic_forward.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_forward.jsp", req,
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

	protected void procActionNDForward(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_nd_forward.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_nd_forward.jsp",
							req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_basic_nd_forward.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_nd_forward.jsp",
							req, res);
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

	protected void procActionSwap(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_swap.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_swap.jsp", req,
							res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_basic_swap.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_swap.jsp", req, res);
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

	protected void procActionOption(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			// msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgFex.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase()
							.trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			// msgFex.send();
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
				msgFex = new EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_confirm_opt.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm_opt.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_basic_opt.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_opt.jsp", req, res);
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
	protected void procReqEnterNew(ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("FE");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath
					+ "EFE0120P_fe_enter_new.jsp");
			callPage(LangPath + "EFE0120P_fe_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("FE");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath
					+ "EFE0120P_fe_enter_maint.jsp");
			callPage(LangPath + "EFE0120P_fe_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFinish(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFT000010Message msgFex = null;
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
			msgFex = (EFT000010Message) mc.getMessageRecord("EFT000010");
			msgFex.setH10USERID(user.getH01USR());
			msgFex.setH10PROGRM("EDL0130");
			msgFex.setH10TIMSYS(getTimeStamp());
			msgFex.setH10SCRCOD("01");
			msgFex.setH10OPECOD(opCode);
			msgFex.setE10DEAACC(userPO.getIdentifier());
			msgFex.send();
			msgFex.destroy();
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

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgFex = new EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFT000010Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath
								+ "EDL0130_cd_finish.jsp");
						callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath
								+ "EDL0130_cd_opening.jsp");
						callPage(LangPath + "EDL0130_cd_opening.jsp", req, res);
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
	protected void procReqProdList(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0320DSMessage msgListProd = null;
		JBListRec beanTypeList = null;
		JBListRec beanProdList = null;
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
			flexLog("Sending header");
			flexLog("header 1");
			msgListProd = (EWD0320DSMessage) mc.getMessageRecord("EWD0320DS");
			msgListProd.setRWDUSR(user.getH01USR());

			msgListProd.send();
			msgListProd.destroy();
			flexLog("EWD0320DS Message Sent");
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

				try {
					flexLog("About to call Page: " + LangPath
							+ "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EWD0320DS")) {

				/** ********** */
				int colNum = 5;
				try {
					beanTypeList = new JBListRec();
					beanTypeList.init(colNum);
					beanProdList = new JBListRec();
					beanProdList.init(colNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myTFlag = "";
				String myPFlag = "";

				int tCounter = -1;

				String myTRow[] = new String[colNum];
				String myPRow[] = new String[colNum];

				for (int i = 0; i < colNum; i++) {
					myTRow[i] = "";
					myPRow[i] = "";
				}

				while (true) {

					msgListProd = (EWD0320DSMessage) newmessage;

					marker = msgListProd.getSWDREC();

					if (marker.equals("*")) {
						break;
					} else {
						if (!myPFlag.equals(msgListProd.getSWDTYP())) {
							myPFlag = msgListProd.getSWDTYP();

							tCounter++;
							myTRow[0] = tCounter + "";
							myTRow[1] = msgListProd.getSWDTYP(); // Product
							// Type Code
							myTRow[2] = ""; // Product Type Description

							beanTypeList.addRow(myTFlag, myTRow);
						}
						myPRow[0] = tCounter + "";
						myPRow[1] = msgListProd.getSWDTYP(); // Type
						myPRow[2] = msgListProd.getSWDDSC(); // Description
						myPRow[3] = msgListProd.getSWDCCY(); // Currency
						myPRow[4] = msgListProd.getSWDCLS(); // Class

						beanProdList.addRow(myPFlag, myPRow);

					}

					newmessage = mc.receiveMessage();
				}

				/** ********** */

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("blType", beanTypeList);
				ses.setAttribute("blProd", beanProdList);
				try {
					flexLog("About to call Page: " + LangPath
							+ "EFE0120P_fe_enter_new.jsp");
					callPage(LangPath + "EFE0120P_fe_enter_new.jsp", req, res);
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

}