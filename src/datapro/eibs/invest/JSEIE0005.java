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

public class JSEIE0005 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int A_BR_MAINTENANCE = 2;
	protected static final int A_IS_MAINTENANCE = 4;
	protected static final int A_CU_MAINTENANCE = 6;

	protected static final  int A_ENTER_NEW_BROKER = 200;
	protected static final  int A_ENTER_MAINT_BROKER = 400;
	protected static final  int A_ENTER_DELETE_BROKER = 600;
	protected static final  int A_ENTER_INQUIRY_BROKER = 800;

	protected static final  int A_ENTER_NEW_CUSTODY = 1000;
	protected static final  int A_ENTER_MAINT_CUSTODY = 1200;
	protected static final  int A_ENTER_DELETE_CUSTODY = 1400;
	protected static final  int A_ENTER_INQUIRY_CUSTODY = 1600;

	protected static final  int A_ENTER_NEW_ISSUER = 2000;
	protected static final  int A_ENTER_MAINT_ISSUER = 2200;
	protected static final  int A_ENTER_DELETE_ISSUER = 2400;
	protected static final  int A_ENTER_INQUIRY_ISSUER = 2600;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEIE0005() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

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
	protected  void procActionEnterNewBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0001");

			try {
				msgBrok.setE01FEBTYP(req.getParameter("TYPE"));
			} catch (Exception e) {
				msgBrok.setE01FEBTYP("0");
			}

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("N");

				if (!req.getParameter("TYPE").equals("")) {
					msgBrok.setE01FEBTYP(req.getParameter("TYPE"));
				}
			
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_brokers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_brokers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterNewCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String opCode = null;
		String CODE = "";

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0001");

			msgBrok.setE01FEBTYP("3");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("N");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_custody_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_custody_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterNewIssuer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0001");

			msgBrok.setE01FEBTYP("5");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("N");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_issuers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_issuers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterMaintBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String opCode = null;

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0002");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("2");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_brokers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_brokers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterMaintCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String opCode = null;

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0002");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("3");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_custody_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_custody_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterMaintIssuer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String opCode = null;

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0002");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("5");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_issuers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_issuers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterInquiryBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0003");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("2");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_inq_brokers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_inq_brokers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterInquiryCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0003");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("3");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_inq_custody_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_inq_custody_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterInquiryIssuer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0000");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0003");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("5");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invBrok", msgBrok);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_inq_issuers_basic.jsp");
						callPage(
							LangPath + "EIE0005_inv_inq_issuers_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterDeleteBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0004");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("2");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setIdentifier(msgBrok.getE01FEBNUM());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invComm", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_del_confirm.jsp");
						callPage(
							LangPath + "EIE0005_inv_del_confirm.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterDeleteCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0004");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("3");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setIdentifier(msgBrok.getE01FEBNUM());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invComm", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_del_confirm.jsp");
						callPage(
							LangPath + "EIE0005_inv_del_confirm.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected  void procActionEnterDeleteIssuer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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

		// Send Initial data
		try {
			msgBrok = (EIE000501Message) mc.getMessageRecord("EIE000501");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE0005");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0004");

			try {
				msgBrok.setE01FEBNUM(req.getParameter("CODE"));
			} catch (Exception e) {
				msgBrok.setE01FEBNUM("0");
			}

			msgBrok.setE01FEBTYP("5");

			msgBrok.send();
			msgBrok.destroy();
			flexLog("EIE000001 Message Sent");
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

			if (newmessage.getFormatName().equals("EIE000501")) {
				try {
					msgBrok = new EIE000501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBrok = (EIE000501Message) newmessage;

				userPO.setIdentifier(msgBrok.getE01FEBNUM());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invComm", msgBrok);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EIE0005_inv_del_confirm.jsp");
						callPage(
							LangPath + "EIE0005_inv_del_confirm.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
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

	protected  void procActionMaintenanceBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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
			msgBrok = (EIE000501Message) ses.getAttribute("invBrok");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE000501");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0005");

			if (userPO.getPurpose().equals("N")) {
				msgBrok.setH01FLGWK1("1");
			} else
				msgBrok.setH01FLGWK1("2");

			// all the fields here
			java.util.Enumeration enu = msgBrok.fieldEnumeration();
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

			mc.sendMessage(msgBrok);
			String test = msgBrok.getE01FEBTYP();			
			msgBrok.destroy();
			flexLog("EIE000501 Message Sent");
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
				msgBrok = new EIE000501Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgBrok = (EIE000501Message) newmessage;
			// showESD008004(msgBrok);

			userPO.setIdentifier(msgBrok.getE01FEBNUM());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invBrok", msgBrok);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIV0005_inv_confirm.jsp");
					callPage(LangPath + "EIV0005_inv_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIE0005_inv_brokers_basic.jsp");
					callPage(
						LangPath + "EIE0005_inv_brokers_basic.jsp",
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

	/**
	 * This method was created in VisualAge.
	 */

	protected  void procActionMaintenanceIssuer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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
			msgBrok = (EIE000501Message) ses.getAttribute("invBrok");
			//msgBrok = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE000501");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0005");

			if (userPO.getPurpose().equals("N")) {
				msgBrok.setH01FLGWK1("1");
			} else
				msgBrok.setH01FLGWK1("2");

			// all the fields here
			java.util.Enumeration enu = msgBrok.fieldEnumeration();
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

			//msgBrok.send();
			mc.sendMessage(msgBrok);
			msgBrok.destroy();
			flexLog("EIE000501 Message Sent");
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
				msgBrok = new EIE000501Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgBrok = (EIE000501Message) newmessage;
			// showESD008004(msgBrok);

			userPO.setIdentifier(msgBrok.getE01FEBNUM());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invBrok", msgBrok);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIV0005_is_inv_confirm.jsp");
					callPage(LangPath + "EIV0005_inv_is_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIE0005_inv_issuers_basic.jsp");
					callPage(
						LangPath + "EIE0005_inv_issuers_basic.jsp",
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

	/**
	 * This method was created in VisualAge.
	 */

	protected  void procActionMaintenanceCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE000501Message msgBrok = null;
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
			msgBrok = (EIE000501Message) ses.getAttribute("invBrok");
			//msgBrok = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
			msgBrok.setH01USERID(user.getH01USR());
			msgBrok.setH01PROGRM("EIE000501");
			msgBrok.setH01TIMSYS(getTimeStamp());
			msgBrok.setH01SCRCOD("01");
			msgBrok.setH01OPECOD("0005");

			if (userPO.getPurpose().equals("N")) {
				msgBrok.setH01FLGWK1("1");
			} else
				msgBrok.setH01FLGWK1("2");

			// all the fields here
			java.util.Enumeration enu = msgBrok.fieldEnumeration();
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

			//msgBrok.send();
			mc.sendMessage(msgBrok);
			msgBrok.destroy();
			flexLog("EIE000501 Message Sent");
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
				msgBrok = new EIE000501Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgBrok = (EIE000501Message) newmessage;
			// showESD008004(msgBrok);

			userPO.setIdentifier(msgBrok.getE01FEBNUM());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invBrok", msgBrok);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIV0005_cus_inv_confirm.jsp");
					callPage(LangPath + "EIV0005_inv_cus_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EIE0005_inv_custody_basic.jsp");
					callPage(
						LangPath + "EIE0005_inv_custody_basic.jsp",
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

			int screen = A_BR_MAINTENANCE;

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

						// Action
						case A_BR_MAINTENANCE :
							procActionMaintenanceBroker(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_IS_MAINTENANCE :
							procActionMaintenanceIssuer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;	
						case A_CU_MAINTENANCE :
							procActionMaintenanceCustody(
								mc,
								msgUser,
								req,
								res,
								session);
							break;		

						case A_ENTER_NEW_BROKER :
							procActionEnterNewBroker(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_MAINT_BROKER :
							procActionEnterMaintBroker(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_DELETE_BROKER :
							procActionEnterDeleteBroker(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_INQUIRY_BROKER :
							procActionEnterInquiryBroker(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case A_ENTER_NEW_CUSTODY :
							procActionEnterNewCustody(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_MAINT_CUSTODY :
							procActionEnterMaintCustody(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_DELETE_CUSTODY :
							procActionEnterDeleteCustody(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_INQUIRY_CUSTODY :
							procActionEnterInquiryCustody(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case A_ENTER_NEW_ISSUER :
							procActionEnterNewIssuer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_MAINT_ISSUER :
							procActionEnterMaintIssuer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_DELETE_ISSUER :
							procActionEnterDeleteIssuer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_INQUIRY_ISSUER :
							procActionEnterInquiryIssuer(
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
