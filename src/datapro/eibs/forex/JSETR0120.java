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

public class JSETR0120 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 300;

	protected static final int A_ENTER_NEW = 200;
	protected static final int A_ENTER_MAINT = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSETR0120() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSETR0120");

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
	protected void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ETR0120DSMessage msgFex = null;
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
		if (!req.getParameter("E01WFRREF").trim().equals("")) {
			opCode = "0002";
		} else
			opCode = "0001";

		// Send Initial data
		try {
			msgFex = (ETR0120DSMessage) mc.getMessageRecord("ETR0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("ETR0120");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);

			try {
				if (req.getParameter("E01WFRCUN") != null)
					msgFex.setE01WFRCUN(req.getParameter("E01WFRCUN"));
			} catch (Exception e) {
				msgFex.setE01WFRCUN("0");
			}

			try {
				if (req.getParameter("E01WFRPRO") != null)
					msgFex.setE01WFRPRO(req.getParameter("E01WFRPRO"));
			} catch (Exception e) {
				msgFex.setE01WFRPRO("FRA");
			}

			try {
				if (req.getParameter("E01WFRREF") != null)
					msgFex.setE01WFRREF(req.getParameter("E01WFRREF"));
			} catch (Exception e) {
				msgFex.setE01WFRREF("0");
			}

			try {
				if (req.getParameter("E01WFRDID") != null)
					msgFex.setE01WFRDID(req.getParameter("E01WFRDID"));
			} catch (Exception e) {
				msgFex.setE01WFRDID("");
			}

			msgFex.send();
			msgFex.destroy();
			flexLog("ETR0120DS Message Sent");
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

			if (newmessage.getFormatName().equals("ETR0120DS")) {
				try {
					msgFex = new ETR0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (ETR0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fra", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ETR0120_fra_basic.jsp");
						callPage(LangPath + "ETR0120_fra_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");
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
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ETR0120DSMessage msgFex = null;
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
			msgFex = (ETR0120DSMessage) ses.getAttribute("fra");
			//msgFex = (EFE0120DSMessage)mc.getMessageRecord("ETR0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("ETR0120");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD("0005");

			String A = req.getParameter("FIRST").trim();
			String B = req.getParameter("SEC").trim();

			if (A.length() == 1) {
				A = '0' + A;
			}
			if (B.length() == 1) {
				B = '0' + B;
			}
			String C = A + 'X' + B;

			msgFex.setE01WFRITP(C);

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
			flexLog("ETR0120DS Message Sent");
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
				msgFex = new ETR0120DSMessage();
				flexLog("ETR0120 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (ETR0120DSMessage) newmessage;
			// showESD008004(msgFex);

			String LeftC = "";
			String RightC = "";

			try {
				LeftC = msgFex.getE01WFRITP().substring(0, 2);
			} catch (Exception e) {

			}
			try {
				RightC = msgFex.getE01WFRITP().substring(3, 5);
			} catch (Exception e) {

			}

			userPO.setHeader1(getFullTime());
			userPO.setIdentifier(msgFex.getE01WFRACC());
			userPO.setHeader20(LeftC);
			userPO.setHeader21(RightC);
			userPO.setHeader8(getFullTime());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fra", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ETR0120_fra_confirm.jsp");
					callPage(LangPath + "ETR0120_fra_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors				
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ETR0120_fra_basic.jsp");
					callPage(LangPath + "ETR0120_fra_basic.jsp", req, res);
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
	protected void procReqEnterNew(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("FRA");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ETR0120_fra_enter_new.jsp");
			callPage(LangPath + "ETR0120_fra_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("FRA");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ETR0120_fra_enter_maint.jsp");
			callPage(LangPath + "ETR0120_fra_enter_maint.jsp", req, res);
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

			int screen = R_ENTER_MAINT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
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
						// BEGIN CD
						// Action
						case A_NEW :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END CD

							// BEGIN Entering
							// Request
						case R_ENTER_NEW :
							procReqEnterNew(msgUser, req, res, session);
							break;
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;

							// Action 
						case A_ENTER_NEW :
							procActionEnterNew(mc, msgUser, req, res, session);
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
					return;
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
	protected void procReqProdList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
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
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EWD0320DS")) {

				/*************/
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
							myTRow[1] = msgListProd.getSWDTYP();
							//  Product Type Code
							myTRow[2] = ""; //  Product Type Description

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

				/*************/

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("blType", beanTypeList);
				ses.setAttribute("blProd", beanProdList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
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