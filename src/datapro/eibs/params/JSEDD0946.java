package datapro.eibs.params;

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDD094601Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDD0946 extends datapro.eibs.master.SuperServlet {

	// Action 
	private ServletConfig config = null;

	protected static final int R_ENTER_FILE = 100;
	protected static final int A_ENTER_FILE = 200;
	protected static final int A_CONFIRM = 300;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDD0946() {
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

	protected void procReqEnterFileName(
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
					+ "EDD0946_camara_transfer_file.jsp");
			callPage(LangPath + "EDD0946_camara_transfer_file.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionFileName(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD094601Message msgFL = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String st = "";
		StringBuffer buf = new StringBuffer();

		//read file
		try {
			SmartUpload mySmartUpload = new SmartUpload();
			mySmartUpload.initialize(config, req, res);
			mySmartUpload.upload();
			com.jspsmart.upload.File myFile =
				mySmartUpload.getFiles().getFile(0);

			StringReader sr = new StringReader(myFile.getContentString());
			LineNumberReader lnr = new LineNumberReader(sr);
			String line = "";

			msgFL = new EDD094601Message();

			// Read text file									
			line = lnr.readLine();
			while (line != null) {
				msgFL.setH01USERID(user.getH01USR());
				msgFL.setH01PROGRM("EDD0946");
				msgFL.setH01TIMSYS(getTimeStamp());
				msgFL.setH01OPECOD("0001");
				msgFL.setH01FLGWK1("");
				msgFL.setE01DATA(line);
				mc.sendMessage(msgFL);
				msgFL.destroy();

				line = lnr.readLine();
			} // End Of Loop

			lnr.close();
			sr.close();

			//		res.sendRedirect(
			//			super.srctx
			//				+ "/servlet/datapro.eibs.params.JSEDD0946?SCREEN=300");

			//

			try {

				msgFL = new EDD094601Message();
				msgFL.setH01USERID(user.getH01USR());
				msgFL.setH01PROGRM("EDD0946");
				msgFL.setH01TIMSYS(getTimeStamp());
				msgFL.setH01OPECOD("0001");
				msgFL.setH01FLGWK1("9");
				msgFL.setE01DATA("");
				//msgFL.send();
				mc.sendMessage(msgFL);
				msgFL.destroy();

				flexLog(
					"Record 3 : Send Last Record Indicator = "
						+ msgFL.getH01FLGWK1());

				// Receive Error Message
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						if (!IsNotError) { //There are errors
							try {
								flexLog("Putting java beans into the session");
								ses.setAttribute("error", msgError);

								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0946_camara_transfer_file.jsp");
								callPage(
									LangPath
										+ "EDD0946_camara_transfer_file.jsp",
									req,
									res);

							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else {
							ses.setAttribute("msgFL", msgFL);
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0946_camara_transfer_confirm.jsp");
								callPage(
									LangPath
										+ "EDD0946_camara_transfer_confirm.jsp",
									req,
									res);

							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else {
						ses.setAttribute("msgFL", msgFL);
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0946_camara_transfer_confirm.jsp");
							callPage(
								LangPath
									+ "EDD0946_camara_transfer_confirm.jsp",
								req,
								res);

						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

					//			msgFL.destroy();

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			//

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

			int screen = A_ENTER_FILE;

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
						case R_ENTER_FILE :
							procReqEnterFileName(msgUser, req, res, session);
							break;
						case A_ENTER_FILE :
							procActionFileName(mc, msgUser, req, res, session);
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
