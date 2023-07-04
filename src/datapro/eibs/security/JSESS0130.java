package datapro.eibs.security;

/**
 * Insert the type's description here.
 * Creation date: (7/6/04 6:08:55 PM)
 * @author: Manuel Justo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSESS0130 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_ENTER_USER_GROUP = 1;
	protected static final int A_ENTER_USER_GROUP = 2;
	protected static final int A_MAINT_USER_GROUP = 3;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESS0130() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESS0240");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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

			int screen = R_ENTER_USER_GROUP;

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

						//Request
						case R_ENTER_USER_GROUP :
							procReqEnterUserGroup(msgUser, req, res, session);
							break;

							//Actions
						case A_ENTER_USER_GROUP :
							procActionEnterUserGroup(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case A_MAINT_USER_GROUP :
							procActionMaintUserGroup(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END Entering
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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

	protected void procReqEnterUserGroup(
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

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESS0130_user_group_maint_enter.jsp");
			callPage(LangPath + "ESS0130_user_group_maint_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnterUserGroup(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS013001Message msgUsr = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgUsr = (ESS013001Message) mc.getMessageRecord("ESS013001");
			msgUsr.setH01USERID(user.getH01USR());
			msgUsr.setH01PROGRM("ESS0130");
			msgUsr.setH01TIMSYS(getTimeStamp());
			msgUsr.setH01SCRCOD("01");
			try {
				msgUsr.setH01OPECOD(req.getParameter("E01OPETYP"));
			} catch (Exception e) {
				msgUsr.setH01OPECOD("0002");
			}

			try {
				msgUsr.setE01USEUID(req.getParameter("E01USEUID"));
			} catch (Exception e) {
				msgUsr.setE01USEUID("");
			}

			if (!msgUsr.getH01OPECOD().equals("0001"))
				msgUsr.send(); //Si la operacion es una insercion
			// no se necesita ejecutar ninguna
			// Operacion en el core (AS400).

			msgUsr.destroy();
			flexLog("ESS013001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
		if (!req.getParameter("E01OPETYP").equals("0001")) {
			// Si es insercion de un grupo nuevo no se envia ningun mensaje
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {

					try {
						msgError =
							(
								datapro
									.eibs
									.beans
									.ELEERRMessage) Beans
									.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.ELEERRMessage");
						flexLog("IsNotError = " + IsNotError);
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					showERROR(msgError);
					//beanList.setNoResult(true);

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);

				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e + newmessage);
				throw new RuntimeException("Socket Communication Error Receiving");
			}

			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESS013001")) {
					try {
						msgUsr = new ESS013001Message();
						flexLog("ESS013001 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgUsr = (ESS013001Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("user", msgUsr);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) { // There are no errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESS0130_user_group_maint.jsp");
							callPage(
								LangPath + "ESS0130_user_group_maint.jsp",
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
									+ "ESS0130_user_group_maint_enter.jsp");
							callPage(
								LangPath + "ESS0130_user_group_maint_enter.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Data Receiving");
			}
		} else {
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESS0130_user_group_maint.jsp");
				callPage(LangPath + "ESS0130_user_group_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}

	protected void procActionMaintUserGroup(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS013001Message msgUsr = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgUsr = (ESS013001Message) mc.getMessageRecord("ESS013001");
			msgUsr.setH01USERID(user.getH01USR());
			msgUsr.setH01PROGRM("ESS0130");
			msgUsr.setH01TIMSYS(getTimeStamp());
			msgUsr.setH01SCRCOD("01");
			try {
				msgUsr.setH01OPECOD(req.getParameter("E01OPETYP"));
			} catch (Exception e) {
				msgUsr.setH01OPECOD("0002");
			}

			try {
				msgUsr.setE01USEUID(req.getParameter("E01USEUID"));
				msgUsr.setE01USECLS(req.getParameter("E01USECLS"));
			} catch (Exception e) {
				msgUsr.setE01USEUID("");
				msgUsr.setE01USECLS("");
			}

			msgUsr.send();
			msgUsr.destroy();
			flexLog("ESS013001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
					flexLog("IsNotError = " + IsNotError);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESS013001")) {
				try {
					msgUsr = new ESS013001Message();
					flexLog("ESS013001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgUsr = (ESS013001Message) newmessage;

				flexLog("Putting java beans into the session");
				//ses.setAttribute("user", msgUsr);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESS0130_user_group_maint_enter.jsp");
						callPage(
							LangPath + "ESS0130_user_group_maint_enter.jsp",
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
								+ "ESS0130_user_group_maint.jsp");
						callPage(
							LangPath + "ESS0130_user_group_maint.jsp",
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
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	}

}
