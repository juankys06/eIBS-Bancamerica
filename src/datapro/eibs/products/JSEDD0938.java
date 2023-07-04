package datapro.eibs.products;

/**================================================================================
 * Transfer File for Inclearing Process (Developed by Banesco Panamá)
 * Creation date: 08/02/2007
 * @author: Henry Guamantica
 ==================================================================================*/
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class JSEDD0938 extends datapro.eibs.master.SuperServlet {

	// Action 
	private ServletConfig config = null;

	protected static final int R_ENTER_CODE = 100;
	protected static final int A_ENTER_CODE = 200;
	protected static final int A_PROCS_CODE = 300;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDD0938() {
		super();
	}

	/**
	 * This method was created by Henry G.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD00946");

	}

	/**
	 * This method was created by Henry G.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	protected void procReqEnterCode(
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
					+ "EDD0938_causal_enter_code.jsp");
			callPage(LangPath + "EDD0938_causal_enter_code.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionCode(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD093801Message msgCA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//read file
		try {
			msgCA = new EDD093801Message();

			msgCA.setH01USERID(user.getH01USR());
			msgCA.setH01PROGRM("EDD0938");
			msgCA.setH01TIMSYS(getTimeStamp());
			msgCA.setH01OPECOD("0001");
			msgCA.setH01FLGWK1("");

			// Receiving Parameter CUASAL Code
			msgCA.setE01CDVCAU(req.getParameter("CAUSAL"));

			// Send Request to AS400								
			mc.sendMessage(msgCA);
			msgCA.destroy();
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
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDD093801")) {
				try {
					msgCA =
						(
							datapro
								.eibs
								.beans
								.EDD093801Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDD093801Message");
					flexLog("EDD093801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCA = (EDD093801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("caBasic", msgCA);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0938_causal_basic.jsp");
						callPage(
							LangPath + "EDD0938_causal_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0938_causal_enter_code.jsp");
					callPage(
						LangPath + "EDD0938_causal_enter_code.jsp",
						req,
						res);
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procProcessCode(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD093801Message msgCA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//read file
		try {
			msgCA = (EDD093801Message) ses.getAttribute("caBasic");
			msgCA.setH01USERID(user.getH01USR());
			msgCA.setH01PROGRM("EDD0938");
			msgCA.setH01TIMSYS(getTimeStamp());
			msgCA.setH01OPECOD("0002");
			msgCA.setH01FLGWK1("");

			// all the fields here
			java.util.Enumeration enu = msgCA.fieldEnumeration();
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

			// Send Request to AS400								
			mc.sendMessage(msgCA);
			msgCA.destroy();

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
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDD093801")) {
				try {
					msgCA =
						(
							datapro
								.eibs
								.beans
								.EDD093801Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDD093801Message");
					flexLog("EDD093801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCA = (EDD093801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("caBasic", msgCA);				

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0938_causal_enter_code.jsp");
						callPage(
							LangPath + "EDD0938_causal_enter_code.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0938_causal_basic.jsp");
					callPage(
						LangPath + "EDD0938_causal_basic.jsp",
						req,
						res);
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

			int screen = A_ENTER_CODE;

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
						case R_ENTER_CODE :
							procReqEnterCode(msgUser, req, res, session);
							break;
						case A_ENTER_CODE :
							procActionCode(mc, msgUser, req, res, session);
							break;
						case A_PROCS_CODE :
							procProcessCode(mc, msgUser, req, res, session);
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
