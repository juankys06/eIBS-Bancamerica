package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDL130001Message;

import datapro.eibs.beans.ELEERRMessage;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL1300 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	
	protected static final int A_REVERSAL = 1;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL1300() {
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
	protected void procActionEnterRever(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL130001Message msgRev = null;
		ELEERRMessage msgError = null;
		//UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList lnRever = null;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgRev = (EDL130001Message) mc.getMessageRecord("EDL130001");
			msgRev.setH01USERID(user.getH01USR());
			msgRev.setH01PROGRM("EDL1300");
			msgRev.setH01TIMSYS(getTimeStamp());
			msgRev.setH01SCRCOD("01");
			msgRev.setH01OPECOD("0015");
			try {
				msgRev.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
				//msgRev.setE01DEAACC(userPO.getIdentifier());
			}
			
			msgRev.send();
			msgRev.destroy();
			flexLog("EDL130001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message or Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
				try {
						flexLog("About to call Page: " + LangPath + "EDL0130_ln_enter_rever.jsp");
						callPage(LangPath + "EDL1300_ln_enter_rever.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			} 
			else if (newmessage.getFormatName().equals("EDL130001")) {
				
				String marker = "";
				lnRever = new JBObjList();
				while (true) {

					msgRev = (EDL130001Message) newmessage;

					marker = msgRev.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						lnRever.addRow(msgRev);
					}
					newmessage = mc.receiveMessage();
				}
				
				ses.setAttribute("lnRever", lnRever);
				ses.setAttribute("error", msgError);
								
				try {
						flexLog("About to call Page: " + LangPath + "EDL1300_ln_reversal.jsp");
						callPage(LangPath + "EDL1300_ln_reversal.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionReversal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL130001Message msgRev = null;	
		ELEERRMessage msgError = null;
		
		boolean IsNotError = false;
		JBObjList lnRever = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		lnRever = (JBObjList) ses.getAttribute("lnRever");
		lnRever.setCurrentRow(0);
				
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRev = (EDL130001Message) lnRever.getRecord();
			msgRev.setH01USERID(user.getH01USR());
			msgRev.setH01PROGRM("EDL1300");
			msgRev.setH01TIMSYS(getTimeStamp());
			msgRev.setH01SCRCOD("01");
			msgRev.setH01OPECOD("0005");
	
			try {
				msgRev.setH01FLGWK1(req.getParameter("H01FLGWK1"));
			} catch (Exception e) {
				msgRev.setH01FLGWK1("");
			}
			mc.sendMessage(msgRev);
			msgRev.destroy();
			flexLog("EDL130001 Message Sent");
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
				
				ses.setAttribute("error", msgError);
				
				if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page1: " + LangPath + "EDL1300_ln_enter_rever.jsp");
							callPage(LangPath + "EDL1300_ln_enter_rever.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page2: " + LangPath + "EDL1300_ln_reversal.jsp");
							callPage(LangPath + "EDL1300_ln_reversal.jsp", req, res);
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
	
	protected void procReqEnterRever(
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
			userPO.setOption("LN");
			userPO.setPurpose("REVERSAL");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL1300_ln_enter_rever.jsp");
			callPage(LangPath + "EDL1300_ln_enter_rever.jsp", req, res);
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

			int screen = R_ENTER;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					
					case A_REVERSAL :
						procActionReversal(mc, msgUser, req, res, session);
						break;
										
					case R_ENTER :
						procReqEnterRever(msgUser, req, res, session);
						break;						
					
					case A_ENTER :
						procActionEnterRever(mc, msgUser, req, res, session);
						break;
					

						// END Entering

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

}