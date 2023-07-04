package datapro.eibs.creditproposal;

/** 
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla???????????????????????????
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDP0886 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	// second screen. list of deals 
	protected static final int R_GET_LIST = 100;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0886() {
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
	
	
	// first screen list of formats
	protected void procGetList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP088601Message Edp088601 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		boolean IsNotError = false;
		String option = "";
		if (req.getParameter("H01OPECOD") == null) {
			option = "0015";
		}else{
			option = req.getParameter("H01OPECOD");			
		}
		if (req.getParameter("NPR") != null) {
			userPO.setIdentifier(req.getParameter("NPR"));
		}
		if (req.getParameter("CUN") != null) {
			userPO.setCusNum(req.getParameter("CUN"));
		}
		if (req.getParameter("CUNAM") != null) {
			userPO.setCusName(req.getParameter("CUNAM"));
		}
		if(req.getParameter("OPTION")!=null){
			if(req.getParameter("OPTION").equals("1")){
				userPO.setPurpose("MAINTENANCE");
			} else {
				userPO.setPurpose("INQUIRY");
				}
			}

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			Edp088601 = (EDP088601Message) mc.getMessageRecord("EDP088601");
			Edp088601.setH01USERID(user.getH01USR());
			Edp088601.setH01PROGRM("EDP0886");
			Edp088601.setH01TIMSYS(getTimeStamp());
			Edp088601.setH01SCRCOD("01");
			Edp088601.setH01OPECOD(option);

			if(req.getParameter("NPR")!=null){
				Edp088601.setE01PLPNPR(req.getParameter("NPR"));
			}
			if(req.getParameter("CUN")!=null){
				Edp088601.setE01CUSCUN(req.getParameter("CUN"));
			}
	
			// all the fields here
			java.util.Enumeration enu = Edp088601.fieldEnumeration();
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

			mc.sendMessage(Edp088601);
			Edp088601.destroy();
			flexLog("Edp088601 Message Sent");
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
				if (newmessage.getFormatName().equals("EDP088601")) {
					try {
						Edp088601 = new datapro.eibs.beans.EDP088601Message();
						flexLog("Edp088601 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					Edp088601 = (EDP088601Message) newmessage;
				} 

				flexLog("Putting java beans into the session");
				userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
				ses.setAttribute("error", msgError);
				ses.setAttribute("Edp088601", Edp088601);
				ses.setAttribute("userPO", userPO);

				try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0886_evaluation_credit_scoring.jsp");
							callPage(
								LangPath + "EDP0886_evaluation_credit_scoring.jsp",
								req,
								res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
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

			int screen = R_GET_LIST;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
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
						case R_GET_LIST :
							procGetList(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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
