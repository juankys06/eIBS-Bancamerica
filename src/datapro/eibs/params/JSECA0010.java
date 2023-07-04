package datapro.eibs.params;

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

public class JSECA0010 extends datapro.eibs.master.SuperServlet {


	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	protected static final int A_MAINTENANCE = 400;

	protected static final int R_ENTER_SETUP = 1;
	protected static final int A_ENTER_SETUP = 2;
	protected static final int A_SETUP = 4;
	
	protected static final int R_ENTER_INQ_SETUP = 5;
	protected static final int A_ENTER_INQ_SETUP = 6;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSECA0010() {
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
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterMaintenance(
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
					+ "ECA0010_cam_enter_params.jsp");
			callPage(
				LangPath + "ECA0010_cam_enter_params.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqEnterSetup(
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
					+ "ECA0010_cam_enter_setup.jsp");
			callPage(
				LangPath + "ECA0010_cam_enter_setup.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}

	protected void procReqEnterInqSetup(
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
					+ "ECA0010_cam_enter_inq_setup.jsp");
			callPage(
				LangPath + "ECA0010_cam_enter_inq_setup.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
		ECA001001Message msgRT = null;
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
			msgRT = (ECA001001Message) mc.getMessageRecord("ECA001001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECA001001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			msgRT.setE01CAMBNK(req.getParameter("E01CAMBNK"));
			msgRT.send();
			msgRT.destroy();
			flexLog("ECA001001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECA001001")) {
				try {
					msgRT = new ECA001001Message();
					flexLog("EFE022001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ECA001001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cam", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECA0010_cam_account_setup.jsp");
						callPage(
							LangPath + "ECA0010_cam_account_setup.jsp",
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
								+ "ECA0010_cam_enter_params.jsp");
						callPage(
							LangPath
								+ "ECA0010_cam_enter_params.jsp",
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

	protected void procReqSetup(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECA002001Message msgRT = null;
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
			msgRT = (ECA002001Message) mc.getMessageRecord("ECA002001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECA001001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			msgRT.setE01CAMPAC(req.getParameter("E01CAMPAC"));
			msgRT.send();
			msgRT.destroy();
			flexLog("ECA002001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
	
				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgError = (ELEERRMessage) newmessage;
	
				// showERROR(msgError);
				//beanList.setNoResult(true);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECA002001")) {
				try {
					msgRT = new ECA002001Message();
					flexLog("EFE022001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ECA002001Message) newmessage;
	
				userPO.setHeader1(msgRT.getE01CAMPAC());
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cam", msgRT);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECA0010_cam_setup.jsp");
						callPage(
							LangPath + "ECA0010_cam_setup.jsp",
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
								+ "ECA0010_cam_enter_setup.jsp");
						callPage(
							LangPath
								+ "ECA0010_cam_enter_setup.jsp",
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

	protected void procReqInqSetup(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECA002001Message msgRT = null;
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
			msgRT = (ECA002001Message) mc.getMessageRecord("ECA002001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECA001001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			msgRT.setE01CAMPAC(req.getParameter("E01CAMPAC"));
			msgRT.send();
			msgRT.destroy();
			flexLog("ECA002001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
	
				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgError = (ELEERRMessage) newmessage;
	
				// showERROR(msgError);
				//beanList.setNoResult(true);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECA002001")) {
				try {
					msgRT = new ECA002001Message();
					flexLog("EFE022001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ECA002001Message) newmessage;
	
				userPO.setHeader1(msgRT.getE01CAMPAC());
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cam", msgRT);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECA0010_cam_inq_setup.jsp");
						callPage(
							LangPath + "ECA0010_cam_inq_setup.jsp",
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
								+ "ECA0010_cam_enter_inq_setup.jsp");
						callPage(
							LangPath
								+ "ECA0010_cam_enter__inq_setup.jsp",
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

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECA001001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ECA001001Message) ses.getAttribute("cam");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECA001001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EIB000002 Message Sent");
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

			if (newmessage.getFormatName().equals("ECA001001")) {
				try {
					msgRT = new ECA001001Message();
					flexLog("ECA001001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ECA001001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cam", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECA0010_cam_enter_params.jsp");
						callPage(
							LangPath
								+ "ECA0010_cam_enter_params.jsp",
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
								+ "ECA0010_cam_account_setup.jsp");
						callPage(
							LangPath + "ECA0010_cam_account_setup.jsp",
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

	protected void procActionSetup(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECA002001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ECA002001Message) ses.getAttribute("cam");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECA002001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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
	
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EIB000002 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ECA002001")) {
				try {
					msgRT = new ECA002001Message();
					flexLog("ECA001001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgRT = (ECA002001Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cam", msgRT);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECA0010_cam_enter_setup.jsp");
						callPage(
							LangPath
								+ "ECA0010_cam_enter_setup.jsp",
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
								+ "ECA0010_cam_setup.jsp");
						callPage(
							LangPath + "ECA0010_cam_setup.jsp",
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

			int screen = A_ENTER;

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

						

						case A_ENTER :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case A_ENTER_SETUP :
							procReqSetup(mc, msgUser, req, res, session);
							break;
						case A_ENTER_INQ_SETUP :
							procReqInqSetup(mc, msgUser, req, res, session);
							break;
							
						case R_ENTER_SETUP :
							procReqEnterSetup(msgUser, req, res, session);
							break;
						case R_ENTER :
							procReqEnterMaintenance(msgUser, req, res, session);
							break;
						case R_ENTER_INQ_SETUP :
							procReqEnterInqSetup(msgUser, req, res, session);
							break;

							// Action

						case A_MAINTENANCE :
							procActionMaintenance(mc,msgUser,req,res,session);
							break;
						case A_SETUP :
							procActionSetup(mc,msgUser,req,res,session);
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

}
