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

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECD0004 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int R_LIST 			= 1;
	static final int A_LIST 			= 2;
	static final int R_NEW				= 3;	
	static final int R_MAINT			= 4;
	static final int A_MAINT			= 5;
	static final int R_DELETE			= 6;
	static final int R_INQ				= 7;
	static final int A_INQ				= 8;
			
	protected String LangPath = "S";

	/**
	 * JSECD0004 constructor comment.
	 */
	public JSECD0004() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0004");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0004DSMessage msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0004DSMessage) mc.getMessageRecord("ECD0004DS");
			msgList.setH04USERID(user.getH01USR());
			msgList.setH04PROGRM("ECD0004");
			msgList.setH04TIMSYS(getTimeStamp());
			msgList.setH04SCRCOD("01");
			msgList.setH04OPECOD("0015");
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_list.jsp");
					callPage(LangPath + "ECD0004_plastic_param_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0004DS")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0004DSMessage) newmessage;
						marker = msgList.getH04FLGMAS();

						if (marker.equals("*")) {
								appList.setShowNext(false);
								break;
						} else {
							appList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								appList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("appList", appList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_list.jsp");
						callPage(LangPath + "ECD0004_plastic_param_list.jsp", req, res);
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
				case 1 : // New
					procReqNew(mc, user, req, res, ses);
					break;
				case 2 : // Maintenance
					procReqMaintenance(mc, user, req, res, ses);
					break;
				case 3 : // Inquiry
					procReqInquiry(mc, user, req, res, ses);
					break;
				case 4 : // Delete
					procReqDelete(mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0004DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		// Send Initial data
		try {
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_new.jsp");
				callPage(LangPath + "ECD0004_plastic_param_new.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0004DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0004DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_maint.jsp");
				callPage(LangPath + "ECD0004_plastic_param_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
		ECD0004DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList appList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0004DSMessage) appList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_inq.jsp");
				callPage(LangPath + "ECD0004_plastic_param_inq.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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
		ECD0004DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opecode = "0005";
		if (userPO.getPurpose().equals("NEW")) {
			opecode = "0001";
		} else {
			opecode = "0005";
		}
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0004DSMessage) mc.getMessageRecord("ECD0004DS");
			msgCD.setH04USERID(user.getH01USR());
			msgCD.setH04PROGRM("ECD0004");
			msgCD.setH04TIMSYS(getTimeStamp());
			msgCD.setH04SCRCOD("01");
			msgCD.setH04OPECOD(opecode);
	
			// all the fields here
			java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	
			//msgRT.send();
			mc.sendMessage(msgCD);
			msgCD.destroy();
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
	
			if (newmessage.getFormatName().equals("ECD0004DS")) {
				msgCD = (ECD0004DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0004?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);

					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_new.jsp");
							callPage(LangPath + "ECD0004_plastic_param_new.jsp", req, res);
						} catch (Exception e) {
								flexLog("Exception calling page " + e);
						}						
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_maint.jsp");
							callPage(LangPath + "ECD0004_plastic_param_maint.jsp", req, res);
						} catch (Exception e) {
								flexLog("Exception calling page " + e);
						}
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
	
	protected void procReqDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0004DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		appList = (JBObjList) ses.getAttribute("appList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		appList.setCurrentRow(row);		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0004DSMessage) appList.getRecord();
			msgCD.setH04USERID(user.getH01USR());
			msgCD.setH04PROGRM("ECD0004");
			msgCD.setH04TIMSYS(getTimeStamp());
			msgCD.setH04SCRCOD("01");
			msgCD.setH04OPECOD("0004");
			
			//msgRT.send();
			mc.sendMessage(msgCD);
			//msgCD.destroy();
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
	
			if (newmessage.getFormatName().equals("ECD0004DS")) {
				msgCD = (ECD0004DSMessage) newmessage;

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx
											+ "/servlet/datapro.eibs.products.JSECD0004?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCD", msgCD);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECD0004_plastic_param_list.jsp");
						callPage(LangPath + "ECD0004_plastic_param_list.jsp", req, res);
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

			int screen = R_MAINT;

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
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;											
					case A_LIST :					    
						procActionList(mc, msgUser, req, res, session);
						break;	
					case R_NEW:
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINT:
						procReqMaintenance(mc, msgUser, req, res, session);
						break;
					case R_INQ:
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case R_DELETE:
						procReqDelete(mc, msgUser, req, res, session);
						break;
					case A_MAINT:
						procActionMaintenance(mc, msgUser, req, res, session);
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


}