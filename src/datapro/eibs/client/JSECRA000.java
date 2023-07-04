package datapro.eibs.client;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSECRA000 extends SuperServlet {

	protected static final int R_ENTER 		= 1;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSECRA000() {
		super();
	}
	
	protected void procReqEnter(
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
			
			userPO.setHeader1(user.getE01UBK());
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ECRA000_transmittal_sheet.jsp");
			callPage(LangPath + "ECRA000_transmittal_sheet.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECRA00001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		//String opt = "";
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//opt = userPO.getPurpose();
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (ECRA00001Message)mc.getMessageRecord("ECRA00001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("ECRA000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgPart.fieldEnumeration();
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
	
			msgPart.send();
			msgPart.destroy();
			flexLog("ECRA00002 Message Sent");
	
		// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else 
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		// Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ECRA00001")) {
				msgPart = new datapro.eibs.beans.ECRA00001Message();
				msgPart = (ECRA00001Message) newmessage;
				flexLog("ECRA00001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
						try {
							res.sendRedirect(super.srctx + 
								"/servlet/datapro.eibs.client.JSECRA000?SCREEN=1" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECRA000_transmittal_sheet.jsp");
							callPage(LangPath + "ECRA000_transmittal_sheet.jsp", req, res);
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
						case 100:
						case R_ENTER :
							procReqEnter(msgUser, req, res, session);
							break;						
						case A_MAINT :
							procActionMaint(mc,msgUser, req, res, session);
							break;
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

	protected void showERROR(ELEERRMessage m){
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