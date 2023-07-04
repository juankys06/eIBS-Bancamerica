package datapro.eibs.security;

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

public class JSESS0240 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_ENTER 			= 1;
	protected static final int R_LIST 			= 100;
	protected static final int R_INQUIRY		= 300;
	
	protected static final int R_INQ_APPROVAL 	= 400;
	
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESS0240() {
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

	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		ESS024001Message msgSearch = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			msgSearch = new ESS024001Message();
			msgSearch.setE01FLGFLE("1");
			msgSearch.setE01FLGPRO("1");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("msgDB", msgSearch);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "ESS0240_db_access_enter.jsp");
			callPage(LangPath + "ESS0240_db_access_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS024001Message msgSearch = null;
		ESS024002Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;

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
			msgSearch = (ESS024001Message) mc.getMessageRecord("ESS024001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EGL0590");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			try {
				userPO.setIdentifier(req.getParameter("E01FLGFLE"));
			} catch (Exception e) {
				userPO.setIdentifier("");
			}
			
			//all the fields here
			java.util.Enumeration enu = msgSearch.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgSearch.send();
			//msgSearch.destroy();
			flexLog("ESS024001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgDB", msgSearch);

				try {
					flexLog("About to call Page: " + LangPath + "ESS0240_db_access_enter.jsp");
					callPage(LangPath + "ESS0240_db_access_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				if (newmessage.getFormatName().equals("ESS024002")) {

					JBObjList beanList = new JBObjList();

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (ESS024002Message) newmessage;

						marker = msgList.getH02FLGMAS();

						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							chk = "";
						}

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {

							beanList.addRow(msgList);

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("dbList", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: " + LangPath + "ESS0240_db_access_list.jsp");
						callPage(
							LangPath + "ESS0240_db_access_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS024001Message msgSearch = null;
		ESS024002Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try { 
			int option = 1;
			int appCode = 0;
			String accNum = "";
			
			try {
				accNum = req.getParameter("ACCNUMBER");
			} catch (Exception e) {}
			
			try {
				appCode = Integer.parseInt(req.getParameter("APCCDE"));
			} catch (Exception e) {}
			
			if (userPO.getIdentifier().equals("1")) {
				// Clients
				flexLog("About to redirect /servlet/datapro.eibs.products.JSEGL0080");
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + accNum);
			
			} else if (userPO.getIdentifier().equals("0")) {
				// Stop Payments
				
				/*userPO.setIdentifier(accNum);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				
				flexLog("About to redirect /servlet/datapro.eibs.products.JSEDD0405");
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDD0405?SCREEN=1");*/
				
				// enviar directamente a la cuenta
				appCode = 1;
				datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(option, appCode, accNum, LangPath, ses);
				res.sendRedirect(super.srctx + red.action());
				
								
			} else {
				// accounts
				/*
				if (userPO.getIdentifier().equals("2")) { //Retails
					appCode = 1;
				} else if (userPO.getIdentifier().equals("3")) { //CDs
					appCode = 95;
				} else if (userPO.getIdentifier().equals("4")) { //Loans
					appCode = 10;
				} else if (userPO.getIdentifier().equals("5")) { //Investment
					appCode = 95;
				} else if (userPO.getIdentifier().equals("6")) { //Letters of Credit
					appCode = 42;
				}
				*/
				datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(option, appCode, accNum, LangPath, ses);
				res.sendRedirect(super.srctx + red.action());
			}
			
		} catch (Exception e) {}

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
						case R_LIST :
							procReqList(mc,msgUser, req, res, session);
							break;
						case R_INQUIRY :
							procReqInquiry(mc,msgUser, req, res, session);
							break;							
						case R_INQ_APPROVAL :
							procReqInqApproval(mc,msgUser, req, res, session);
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

	protected void procReqInqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS024002Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		String typ = "";

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
			msgList = (ESS024002Message) mc.getMessageRecord("ESS024002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ESS0240");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			
			
			try {
				typ = req.getParameter("TYPE");
			} catch (Exception e) {
				typ = "";
			}
			if (typ == null) typ = "";
			
			if (typ.equals("C") ) {
				msgList.setE02NUMACC(userPO.getHeader1());				
			}else if(typ.equals("CUST")){
				msgList.setE02NUMACC(userPO.getHeader1());
				msgList.setE02CDTYPE(typ);
			} else {
				msgList.setE02NUMACC(userPO.getIdentifier());
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("ESS024002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESS024002")) {

					JBObjList beanList = new JBObjList();

					String marker = "";
					while (true) {

						msgList = (ESS024002Message) newmessage;

						marker = msgList.getH02FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {

							beanList.addRow(msgList);

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("dbList", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: " + LangPath + "ESS0240_db_change_list.jsp");
						callPage(
							LangPath + "ESS0240_db_change_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}
}
