package datapro.eibs.services;

/**
 * Insert the type's description here.
 * Creation date: (10/05/04 05:21:08 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSSRV0035 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_PROC = 200;
	protected static final int R_FILT = 100;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSSRV0035() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSSRV0035");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFilter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		SRV003501Message msgSearch = new SRV003501Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "SRV0035_services_application_enter_inq.jsp");
			callPage(LangPath + "SRV0035_services_application_enter_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procSndPro(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		SRV003501Message msgSearch = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (SRV003501Message) mc.getMessageRecord("SRV003501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("SRV0035");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0003");

			//all the fields here
			
			try { 
				msgSearch.setE01BALCIA(req.getParameter("E01BALCIA").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01BALCIA(userPO.getHeader16());
			}
			
			try { 
				msgSearch.setE01BALSRV(req.getParameter("E01BALSRV").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01BALSRV(userPO.getHeader17());
			}
			
			try { 
				msgSearch.setE01BALSEQ(req.getParameter("E01BALSEQ").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01BALSEQ(userPO.getHeader18());
			}
			
			userPO.setHeader16(msgSearch.getE01BALCIA());
			userPO.setHeader17(msgSearch.getE01BALSRV());
			userPO.setHeader18(msgSearch.getE01BALSEQ());
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("SRV003501 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "SRV0035_services_application_enter_inq.jsp");
					callPage(LangPath + "SRV0035_services_application_enter_inq.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
			else
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

			int screen = R_FILT;

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
						case A_PROC :							
							procSndPro(mc, msgUser, req, res, session);
							break;	
						case R_FILT :
							procReqFilter(msgUser, req, res, session);
							break;					
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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