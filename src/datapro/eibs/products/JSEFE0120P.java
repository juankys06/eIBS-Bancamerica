package datapro.eibs.products;

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

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;
	protected static final int R_FINISH = 37;
	protected static final int A_FINISH = 38;

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 200;

	protected static final int A_ENTER_NEW = 400;
	protected static final int A_ENTER_MAINT = 500;

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
		EFE0120DSMessage msgFex = null;
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
		opCode = "0005";

		// Send Initial data
		try {
			msgFex = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgFex.setH01USERID(user.getH01USR());
			msgFex.setH01PROGRM("EFE0120P");
			msgFex.setH01TIMSYS(getTimeStamp());
			msgFex.setH01SCRCOD("01");
			msgFex.setH01OPECOD(opCode);
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

			if (newmessage.getFormatName().equals("EFE0120DS")) {
				try {
					msgFex = new datapro.eibs.beans.EFE0120DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFE0120DSMessage) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fex", msgFex);

				if (IsNotError) { // There are no errors
					if (msgFex.getE01FESTYP().equals("OPT")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EFE0120P_fe_basic_opt.jsp");
							callPage(
								LangPath + "EFE0120P_fe_basic_opt.jsp",
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
									+ "EFE0120P_fe_basic_sf.jsp");
							callPage(
								LangPath + "EFE0120P_fe_basic_sf.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EFE0120P_fe_enter_new.jsp");
						callPage(
							LangPath + "EFE0120P_fe_enter_new.jsp",
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
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0120DSMessage msgFex = null;
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
			msgFex = (EFE0120DSMessage) ses.getAttribute("fex");
			//msgFex = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
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
				msgFex = new datapro.eibs.beans.EFE0120DSMessage();
				flexLog("EDL001301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgFex = (EFE0120DSMessage) newmessage;
			// showESD008004(msgFex);

			userPO.setIdentifier(msgFex.getE01FESREF());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EFE0120P_fe_confirm.jsp");
					callPage(LangPath + "EFE0120P_fe_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EFE0120P_fe_basic_sf.jsp");
					callPage(LangPath + "EFE0120P_fe_basic_sf.jsp", req, res);
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

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("FE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EFE0120P_fe_enter_new.jsp");
			callPage(LangPath + "EFE0120P_fe_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFinish(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFT000010Message msgFex = null;
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
					msgFex = new datapro.eibs.beans.EFT000010Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgFex = (EFT000010Message) newmessage;
				// showESD008004(msgFex);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgFex);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0130_cd_finish.jsp");
						callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page4: "
								+ LangPath
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
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 3);
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
						case A_MAINTENANCE :
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
					int sck = getInitSocket(req) + 3;
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
			msgError = new datapro.eibs.beans.ELEERRMessage();
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
					beanTypeList = new datapro.eibs.beans.JBListRec();
					beanTypeList.init(colNum);
					beanProdList = new datapro.eibs.beans.JBListRec();
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