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

public class JSECD0011 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int R_ENTER			= 1;
	
	static final int R_LIST 			= 10;
	static final int R_LIST_BY_USER 	= 11;
	
	static final int R_INQUIRY		 	= 20;

	protected String LangPath = "S";

	/**
	 * JSECD0003 constructor comment.
	 */
	public JSECD0011() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0003");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqListByUser(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0011DSMessage msgList = null;
		JBObjList audList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0011DSMessage) mc.getMessageRecord("ECD0011DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0011");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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
			
			userPO.setHeader1(msgList.getE01TDDUSR());
			userPO.setHeader2(
				Util.formatDate(
					msgList.getE01TDDDAD(),
					msgList.getE01TDDMOD(),
					msgList.getE01TDDYED()));
			userPO.setHeader3(
				Util.formatDate(
					msgList.getE01TDDDAH(),
					msgList.getE01TDDMOH(),
					msgList.getE01TDDYEH()));
			

			msgList.send();
			msgList.destroy();
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_enter.jsp");
					callPage(LangPath + "ECD0011_plastic_audit_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0011DS")) {
					audList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0011DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								audList.setShowNext(false);
								break;
						} else {
							audList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								audList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("audList", audList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_list_byuser.jsp");
						callPage(LangPath + "ECD0011_plastic_audit_list_byuser.jsp", req, res);
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
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0011DSMessage msgList = null;
		JBObjList audList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0011DSMessage) mc.getMessageRecord("ECD0011DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0011");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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
			
			userPO.setHeader2(
				Util.formatDate(
					msgList.getE01TDDDAD(),
					msgList.getE01TDDMOD(),
					msgList.getE01TDDDAD()));
			userPO.setHeader3(
				Util.formatDate(
					msgList.getE01TDDDAH(),
					msgList.getE01TDDMOH(),
					msgList.getE01TDDDAH()));
			userPO.setHeader4(msgList.getE01TDDCED());
			userPO.setHeader5(msgList.getE01TDDCUN());
			userPO.setHeader6(msgList.getE01TDDACC());
			userPO.setHeader7(msgList.getE01TDDTDD());

			msgList.send();
			msgList.destroy();
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_enter.jsp");
					callPage(LangPath + "ECD0011_plastic_audit_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0011DS")) {
					audList = new JBObjList();
					boolean firstTime = true;
					String marker = "";
					
					if (firstTime) {
						firstTime = false;
						userPO.setHeader1(msgList.getE01TDDNAM());
					}

					while (true) {
						msgList = (ECD0011DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								audList.setShowNext(false);
								break;
						} else {
							audList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								audList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("audList", audList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_list.jsp");
						callPage(LangPath + "ECD0011_plastic_audit_list.jsp", req, res);
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

	
	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0011DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList audList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_enter.jsp");
				callPage(LangPath + "ECD0011_plastic_audit_enter.jsp", req, res);
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
		ECD0011DSMessage msgAudit = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList audList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("INQUIRY");
		audList = (JBObjList) ses.getAttribute("audList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		audList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgAudit = (ECD0011DSMessage) audList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgAudit", msgAudit);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0011_plastic_audit_inq.jsp");
				callPage(LangPath + "ECD0011_plastic_audit_inq.jsp", req, res);
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
					case R_ENTER:
						procReqEnter(mc, msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;			
					case R_LIST_BY_USER :
						procReqListByUser(mc, msgUser, req, res, session);
						break;
						
					case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
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