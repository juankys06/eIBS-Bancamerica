package datapro.eibs.security;

/**
 * Insert the type's description here.
 * Creation date: (11/12/09 12:52:31 PM)
 * @author: Gustavo Adolfo Villarroel
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSESD0035 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER = 1;
	protected static final int R_LIST = 3;
	protected static final int A_LIST = 4;
	protected static final int A_MAINT = 5;
	protected static final int A_DELETE = 6;
	
	protected String LangPath = "S";

	/**
	 * JSESD0035 constructor comment.
	 */
	public JSESD0035() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0035");

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
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
						case R_ENTER :
							procReqEnter(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case A_LIST :
							procActionList(mc, msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaintenance(mc, msgUser, req, res, session);
							break;
						case A_DELETE :
							procActionDelete(mc, msgUser, req, res, session);
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
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD003501Message msgCCA = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			msgCCA = new datapro.eibs.beans.ESD003501Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("user", msgCCA);

			try {
				flexLog("About to call Page: "	+ LangPath	+ "ESD0035_ref_codes_user_access_enter.jsp");
				callPage(LangPath + "ESD0035_ref_codes_user_access_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
		ESD003501Message msgSearch = null;
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
			msgSearch = (ESD003501Message) mc.getMessageRecord("ESD003501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ESD0035");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
	
			//all the fields here
			msgSearch.setE01CREUSR(req.getParameter("E01CREUSR"));
	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ESD003501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ESD003501")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				
				while (true) {

					msgSearch = (ESD003501Message) newmessage;

					marker = msgSearch.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						userPO.setIdentifier(msgSearch.getE01CREUSR());
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						beanList.addRow(msgSearch);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("codList", beanList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);

				if (IsNotError) {
					try {
						flexLog(
							"About to call Page: " + LangPath + "ESD0035_ref_codes_user_access_list.jsp");
						callPage(
							LangPath + "ESD0035_ref_codes_user_access_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					try {
						flexLog(
							"About to call Page: " + LangPath + "ESD0035_ref_codes_user_access_enter.jsp");
						callPage(
							LangPath + "ESD0035_ref_codes_user_access_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	
	}

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
	
			switch (option) {
				case 1 : // Add
					procActionMaintenance(mc, user, req, res, ses);
					break;
				case 4 : // Delete
					procActionDelete(mc, user, req, res, ses);
					break;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD003501Message msgCode = null;
		ELEERRMessage msgError = null;
		JBObjList codList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCode = (ESD003501Message) mc.getMessageRecord("ESD003501");
			msgCode.setH01USERID(user.getH01USR());
			msgCode.setH01PROGRM("ESD0035");
			msgCode.setH01TIMSYS(getTimeStamp());
			msgCode.setH01SCRCOD("01");
			msgCode.setH01OPECOD("0005");

			msgCode.setE01CREUSR(userPO.getIdentifier());
			msgCode.setE01CREALL(req.getParameter("E01CREALL"));
			msgCode.setE01CRETCN(req.getParameter("E01CRETCN"));
					
			msgCode.send();
			msgCode.destroy();
			flexLog("ESD003501 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD003501")) {
				try {
					msgCode = new datapro.eibs.beans.ESD003501Message();
					flexLog("ESD003501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCode = (ESD003501Message) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.security.JSESD0035?SCREEN=3&E01CREUSR=" + userPO.getIdentifier());
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCode", msgCode);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ESD0035_ref_codes_user_access_list.jsp");
						callPage(LangPath + "ESD0035_ref_codes_user_access_list.jsp", req, res);
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

	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD003501Message msgCode = null;
		ELEERRMessage msgError = null;
		JBObjList codList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		codList = (JBObjList) ses.getAttribute("codList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		codList.setCurrentRow(row);		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCode = (ESD003501Message) codList.getRecord();
			msgCode.setH01USERID(user.getH01USR());
			msgCode.setH01PROGRM("ESD0035");
			msgCode.setH01TIMSYS(getTimeStamp());
			msgCode.setH01SCRCOD("01");
			msgCode.setH01OPECOD("0004");
			
			//msgRT.send();
			mc.sendMessage(msgCode);
			//msgCode.destroy();
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
	
			if (newmessage.getFormatName().equals("ESD003501")) {
				msgCode = (ESD003501Message) newmessage;
	
				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.security.JSESD0035?SCREEN=3&E01CREUSR=" + userPO.getIdentifier());
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCode", msgCode);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ESD0035_ref_codes_user_access_list.jsp");
						callPage(LangPath + "ESD0035_ref_codes_user_access_list.jsp", req, res);
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