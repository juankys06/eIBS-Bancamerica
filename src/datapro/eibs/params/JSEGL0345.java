package datapro.eibs.params; 

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

public class JSEGL0345 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_ENTER		= 1;
	protected static final int A_ENTER		= 2;
	
	protected static final int A_MAINT		= 3;
	

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEGL0345() {
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

			int screen = R_ENTER;

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
						case R_ENTER :
							 procReqEnter(msgUser, req, res, session);							
							 break;
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaint(mc, msgUser, req, res, session);
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

	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EGL034501Message msgBdg = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("budget", msgBdg);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EGL0345_budget_master_enter.jsp");
			callPage(LangPath + "EGL0345_budget_master_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034501Message msgBdg = null;
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
			msgBdg = (EGL034501Message) mc.getMessageRecord("EGL034501");
			msgBdg.setH01USERID(user.getH01USR());
			msgBdg.setH01PROGRM("EGL0345");
			msgBdg.setH01TIMSYS(getTimeStamp());
			msgBdg.setH01SCRCOD("01");

			try {
				msgBdg.setH01OPECOD(req.getParameter("E01OPETYP"));
			} catch (Exception e){
				msgBdg.setH01OPECOD("0002");
			}
			
			msgBdg.setH01FLGWK3(req.getParameter("H01FLGWK3"));
			msgBdg.setE01BUMBNK(req.getParameter("E01BUMBNK"));			
			msgBdg.setE01BUMBRN(req.getParameter("E01BUMBRN"));
			msgBdg.setE01BUMCCY(req.getParameter("E01BUMCCY"));
			msgBdg.setE01BUMNUM(req.getParameter("E01BUMNUM"));
			msgBdg.setE01BUMCCN(req.getParameter("E01BUMCCN"));
			
			msgBdg.send();
			msgBdg.destroy();
			flexLog("EGL034501 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034501")) {
				try {
					msgBdg = new datapro.eibs.beans.EGL034501Message();
					flexLog("EGL034501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBdg = (EGL034501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("budget", msgBdg);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: "	+ LangPath	+ "EGL0345_budget_master_basic.jsp");
						callPage(LangPath + "EGL0345_budget_master_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: "	+ LangPath	+ "EGL0345_budget_master_enter.jsp");
						callPage(LangPath + "EGL0345_budget_master_enter.jsp",	req, res);
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
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EGL034501Message msgBdg = null;
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
			msgBdg = (EGL034501Message) mc.getMessageRecord("EGL034501");
			msgBdg.setH01USERID(user.getH01USR());
			msgBdg.setH01PROGRM("EGL0345");
			msgBdg.setH01TIMSYS(getTimeStamp());
			msgBdg.setH01SCRCOD("01");
			msgBdg.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgBdg.fieldEnumeration();
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

			msgBdg.send();
			msgBdg.destroy();
			flexLog("EGL034501 Message Sent");
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

			if (newmessage.getFormatName().equals("EGL034501")) {
				try {
					msgBdg = new datapro.eibs.beans.EGL034501Message();
					flexLog("EGL034501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBdg = (EGL034501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("budget", msgBdg);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: "	+ LangPath	+ "EGL0345_budget_master_enter.jsp");
						callPage(LangPath + "EGL0345_budget_master_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: "	+ LangPath	+ "EGL0345_budget_master_basic.jsp");
						callPage(LangPath + "EGL0345_budget_master_basic.jsp", req, res);
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

}
