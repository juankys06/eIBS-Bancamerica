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

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0152A extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_TRANSACTION = 5;

	protected static final int A_ENTER_TRANSAC = 800;

	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0152A() {
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
	protected void procReqTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL015203Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0004";

		// Send Initial data
		try {
			msgLN = (EDL015203Message) mc.getMessageRecord("EDL015203");
			msgLN.setH03USERID(user.getH01USR());
			msgLN.setH03PROGRM("EDL0130");
			msgLN.setH03TIMSYS(getTimeStamp());
			msgLN.setH03SCRCOD("01");
			msgLN.setH03OPECOD(opCode);
			msgLN.setE03DEAACC(userPO.getIdentifier());
			msgLN.setH03FLGWK1("A");
			msgLN.send();
			msgLN.destroy();
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
			if (newmessage.getFormatName().equals("EDL015203")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015203Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgLN = (EDL015203Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTransac", msgLN);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_ln_ap_transac.jsp");
						callPage(
							LangPath + "EDL0140_ln_ap_transac.jsp",
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
								+ "EDL0150_ln_enter_transac.jsp");
						callPage(
							LangPath + "EDL0150_ln_enter_transac.jsp",
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

			int screen = A_ENTER_TRANSAC;

			try {

				flexLog("Screen  Number: " + screen);

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 5);
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
						case R_TRANSACTION :
							procReqTransaction(mc, msgUser, req, res, session);
							break;
						case R_OTHERS_TRANS :
							procReqOthersTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_TRANSAC :
							procActionEnterTR(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 5;
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

	protected static final int R_OTHERS_TRANS = 27;

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqOthersTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;
		boolean IsNotError = false;
		DataTransaction transData = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			transData = new datapro.eibs.beans.DataTransaction();

			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());
			transData.setProdtype(userPO.getType());

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=1");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterTR(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			try {
				userPO.setIdentifier(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			procReqTransaction(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}