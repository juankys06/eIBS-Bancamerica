package datapro.eibs.transfer;

/**
  */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR204001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEPR2040 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_SHOW_LETTRF = 1;
	protected static final int A_SHOW_LETTRF = 2;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEPR2040() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqShowLetter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;	
		EPR204001Message msgLetter=null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		
		//msgLetter.setH01SCRCOD("01");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgLetter = new EPR204001Message();
		
		boolean IsNotError = true;
		try {
				flexLog("Send Initial Data");
				msgLetter = (EPR204001Message) mc.getMessageRecord("EPR204001");
				msgLetter.setH01USERID(user.getH01USR());
				msgLetter.setH01PROGRM("EPR2040");
				msgLetter.setH01TIMSYS(getTimeStamp());
				//msgSearch.setEWDUSR(user.getH01USR());
				try {

					try {
						msgLetter.setE01PRPDAC(req.getParameter("account"));
					} catch (Exception e) {
						flexLog("E01STPACC null");
					}
					try {
						msgLetter.setE01PRPNUM(req.getParameter("ref"));
					} catch (Exception e) {
						flexLog("E01STPACC null");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Input data error " + e);
				}

				msgLetter.send();
				msgLetter.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
		}
		
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			}
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EPR204001")){
				try {
					msgLetter = new datapro.eibs.beans.EPR204001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgLetter = (EPR204001Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("msgLetter", msgLetter);
			}
			if (IsNotError) {
				try {
					flexLog(
					"About to call Page: "
						+ LangPath
						+ "EPR2040_pr_transfer_letter.jsp");
					callPage(LangPath + "EPR2040_pr_transfer_letter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
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
			int screen = R_SHOW_LETTRF;

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
						case R_SHOW_LETTRF :
							procReqShowLetter(mc,msgUser, req, res, session);
							break;
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