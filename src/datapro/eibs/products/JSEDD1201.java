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

public class JSEDD1201 extends datapro.eibs.master.SuperServlet {

	// credit card 
	protected static final int R_NEW = 100;
	protected static final int A_NEW = 200;
	protected static final int R_MAINTENANCE = 300;
	protected static final int A_MAINTENANCE = 400;
	protected static final int R_PASSWORD = 500;
	protected static final int R_APPROVAL = 700;
	protected static final int A_APPROVAL = 800;
	protected static final int R_APPROVAL_INQ = 900;
	
	protected static final int R_LIST = 1;
	protected static final int A_LIST = 2;
	protected static final int A_NEW_CHECK = 3;
	protected static final int A_MAINT_CHECK = 4;
	protected static final int R_SP_CHECK = 5;
	protected static final int A_SP_CHECK = 6;
	
	
	protected String LangPath = "S";

	/**
	 * JSEDD1201 constructor comment.
	 */
	public JSEDD1201() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD1201");

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
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog("About to call Page: " + LangPath + "EDD1200_rdc_enter_new.jsp");
			callPage(LangPath + "EDD1200_rdc_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgChk = new EDD120001Message();
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0001");

			try {
				msgChk.setE01WTRARF(req.getParameter("E01WTRARF"));
			} catch (Exception e) {}
			
			try {
				msgChk.setE01OFFBNK(req.getParameter("E01OFFBNK"));
			} catch (Exception e) {}
			
			try {
				msgChk.setE01OFFBRN(req.getParameter("E01OFFBRN"));
			} catch (Exception e) {}
			
			try {
				msgChk.setE01OFFOPE(req.getParameter("E01OFFOPE"));
			} catch (Exception e) {}
			
			try {
				msgChk.setE01OFFCON(req.getParameter("E01OFFCON"));
			} catch (Exception e) {}
			

			// msgCD.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
			flexLog("EDD120001 Message Sent");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
		try {
			appList = new datapro.eibs.beans.JBObjList();
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD1200_rdc_enter_new.jsp");
					callPage(LangPath + "EDD1200_rdc_enter_new.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EDD120001")) {
				
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				
				while (true) {

					msgChk = (EDD120001Message) newmessage;

					marker = msgChk.getH01FLGMAS();

					if (marker.equals("*")) {
						
						userPO.setIdentifier(msgChk.getE01WTRARF());
						userPO.setBank(msgChk.getE01OFFBNK());
						userPO.setBranch(msgChk.getE01OFFBRN());
						userPO.setHeader1(msgChk.getE01OFFOPE());
						userPO.setHeader2(msgChk.getE01OFFCON());
						userPO.setHeader3(msgChk.getE01OFFCCY());
						userPO.setHeader4(msgChk.getE01OFFGLN());
						
						appList.setShowNext(false);
						break;
					} else {
												
						appList.addRow(msgChk);
							
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
					flexLog("About to call Page: " + LangPath + "EDD1200_rdc_list.jsp");
					callPage(LangPath + "EDD1200_rdc_list.jsp", req, res);

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

	 *	 */

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {
		
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		try {
			flexLog("About to call Page: " + LangPath + "EDD1200_rdc_enter_maint.jsp");
			callPage(LangPath + "EDD1200_rdc_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgChk = new EDD120001Message();
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0002");
	
			try {
				msgChk.setE01WTRARF(req.getParameter("E01WTRARF"));
			} catch (Exception e) {}
				
			// msgCD.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
			flexLog("EDD120001 Message Sent");
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
			
		try {
			appList = new datapro.eibs.beans.JBObjList();
				
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "EDD1200_rdc_enter_maint.jsp");
					callPage(LangPath + "EDD1200_rdc_enter_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
					
			} else if (newmessage.getFormatName().equals("EDD120001")) {
					
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
					
				while (true) {
	
					msgChk = (EDD120001Message) newmessage;
	
					marker = msgChk.getH01FLGMAS();
	
					if (marker.equals("*")) {
							
						userPO.setIdentifier(msgChk.getE01WTRARF());
						userPO.setBank(msgChk.getE01OFFBNK());
						userPO.setBranch(msgChk.getE01OFFBRN());
						userPO.setHeader1(msgChk.getE01OFFOPE());
						userPO.setHeader2(msgChk.getE01OFFCON());
						userPO.setHeader3(msgChk.getE01OFFCCY());
						userPO.setHeader4(msgChk.getE01OFFGLN());
													
						appList.setShowNext(false);
						break;
					} else {
															
						appList.addRow(msgChk);
								
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
					flexLog("About to call Page: " + LangPath + "EDD1200_rdc_list.jsp");
					callPage(LangPath + "EDD1200_rdc_list.jsp", req, res);
	
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
		JBObjList appList = null;

		appList = (JBObjList) ses.getAttribute("appList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
				appList.setCurrentRow(row);
			} catch (Exception e) {
			}

			switch (option) {
				case 1 : // New
					EDD120001Message msgChk = (EDD120001Message) mc.getMessageRecord("EDD120001");										
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(super.webAppPath + LangPath + "EDD1200_rdc_new.jsp?num=");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgChk", msgChk);
					res.sendRedirect(super.srctx + LangPath + "EDD1200_rdc_list.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
				
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "EDD1200_rdc_maint.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD1200_rdc_list.jsp?SEL=" + row);
					break;
				case 3 : // Delete
					procActionDelete( row, appList, mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionDelete(
		int row,
		JBObjList appList,
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		datapro.eibs.beans.EDD120001Message msgC = (datapro.eibs.beans.EDD120001Message)appList.getRecord();
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));
	
			flexLog("Send Initial Data");
			msgChk = (EDD120001Message) mc.getMessageRecord("EDD120001");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0009");
	
			try {
				msgChk.setE01WTRARF(userPO.getIdentifier());
			}
			catch (Exception e){
				msgChk.setE01WTRARF("");
			}
			
			try {
		   		appList.setCurrentRow(row);
		   		datapro.eibs.beans.EDD120001Message msgCh = (datapro.eibs.beans.EDD120001Message)appList.getRecord();
			
				msgChk.setE01WTRSEQ(msgCh.getE01WTRSEQ());
			}
			catch (Exception e){
				msgChk.setE01WTRSEQ("");
			}			
	
			//msgRT.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
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
				
				if (IsNotError) { // There are no errors
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDD1201?SCREEN=1&E01WTRARF=" + userPO.getIdentifier());
				} else {
					try {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						res.sendRedirect(super.srctx + LangPath + "EDD1200_rdc_list.jsp?ROW=" + row);
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

	protected void procActionNewCheck(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgChk = (EDD120001Message) mc.getMessageRecord("EDD120001");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgChk.fieldEnumeration();
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
	
			msgChk.setE01OVDFLG("");
			msgChk.setE01INSFLG("");
			msgChk.setE01SEEFLG("");
			msgChk.setE01WTRSEQ("0");
			
			//msgRT.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
			
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
			if (newmessage.getFormatName().equals("EDD120001")) {
				try {
					msgChk = new datapro.eibs.beans.EDD120001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgChk = (EDD120001Message) newmessage;
				// showESD008004(msgRT);
	
				if (IsNotError) { // There are no errors
					if (msgChk.getE01SEEFLG().equals("Y")) 
					{
						// Show Special Instruction &/|| Overdraft
						procReqSpecInst(msgChk, mc, user, req, res, ses);
						
					}
					else 
					{
						// refresh list
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Refresh</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println("		top.opener.window.location.href='"
								+ super.webAppPath
								+ "/servlet/datapro.eibs.products.JSEDD1201?SCREEN=1&E01WTRARF=" + userPO.getIdentifier() + "'");
						out.println("		window.location.href='"
								+ super.webAppPath + LangPath
								+ "/EDD1200_rdc_new.jsp"  + "'");
						out.println("</SCRIPT>");
						out.println("<P>Refresh it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
					}
					
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgChk", msgChk);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "EDD1200_rdc_new.jsp");
						callPage(LangPath + "EDD1200_rdc_new.jsp", req, res);
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
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgChk = new EDD120001Message();
		msgChk.setH01USERID(user.getH01USR());
		msgChk.setH01PROGRM("EDD1200");
		msgChk.setH01TIMSYS(getTimeStamp());
		msgChk.setH01SCRCOD("01");
		msgChk.setH01OPECOD("0015");
	
		try {
			msgChk.setE01WTRARF(req.getParameter("E01WTRARF"));
		} catch (Exception e) {}
				
		// msgCD.send();
		mc.sendMessage(msgChk);
		msgChk.destroy();
		flexLog("EDD120001 Message Sent");
				
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	
			
	try {
		appList = new datapro.eibs.beans.JBObjList();
				
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	
	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();
	
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
					
			try {
				flexLog("About to call Page: " + LangPath + "EDD1200_rdc_enter_maint.jsp");
				callPage(LangPath + "EDD1200_rdc_enter_maint.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
					
		} else if (newmessage.getFormatName().equals("EDD120001")) {
					
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
					
			while (true) {
	
				msgChk = (EDD120001Message) newmessage;
	
				marker = msgChk.getH01FLGMAS();
	
				if (marker.equals("*")) {
							
					userPO.setIdentifier(msgChk.getE01WTRARF());
					userPO.setBank(msgChk.getE01OFFBNK());
					userPO.setBranch(msgChk.getE01OFFBRN());
					userPO.setHeader1(msgChk.getE01OFFOPE());
					userPO.setHeader2(msgChk.getE01OFFCON());
					userPO.setHeader3(msgChk.getE01OFFCCY());
					userPO.setHeader4(msgChk.getE01OFFGLN());					
							
					appList.setShowNext(false);
					break;
				} else {
														
					appList.addRow(msgChk);
								
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
				flexLog("About to call Page: " + LangPath + "EDD1200_rdc_list.jsp");
				callPage(LangPath + "EDD1200_rdc_list.jsp", req, res);

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
	
	protected void procActionMaintCheck(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgChk = (EDD120001Message) mc.getMessageRecord("EDD120001");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgChk.fieldEnumeration();
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
	
			msgChk.setE01OVDFLG("");
			msgChk.setE01INSFLG("");
			msgChk.setE01SEEFLG("");
			
			//msgRT.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
			
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
	
			if (newmessage.getFormatName().equals("EDD120001")) {
				try {
					msgChk = new datapro.eibs.beans.EDD120001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgChk = (EDD120001Message) newmessage;
				// showESD008004(msgRT);
	
				if (IsNotError) { // There are no errors
					
					if (msgChk.getE01SEEFLG().equals("Y")) {
						// Show Special Instruction &/|| Overdraft
						procReqSpecInst(msgChk, mc, user, req, res, ses);
						
					} else {
						// show list
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println("		top.opener.window.location.href='"
								+ super.webAppPath
								+ "/servlet/datapro.eibs.products.JSEDD1201?SCREEN=1&E01WTRARF=" + userPO.getIdentifier() + "'");
								 
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("<P>Close it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
						
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgChk", msgChk);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "EDD1200_rdc_maint.jsp");
						callPage(LangPath + "EDD1200_rdc_maint.jsp", req, res);
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
	
	protected void procReqSpecInst(
		EDD120001Message msgChk,
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {
	
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			msgError = new datapro.eibs.beans.ELEERRMessage();

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("msgChk", msgChk);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog("About to call Page: " + LangPath + "EDD1200_rdc_spec_inst.jsp");
			callPage(LangPath + "EDD1200_rdc_spec_inst.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionSpecInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDD120001Message msgChk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
	
			flexLog("Send Initial Data");
			msgChk = (EDD120001Message) mc.getMessageRecord("EDD120001");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("EDD1200");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgChk.fieldEnumeration();
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
	
			msgChk.setE01SEEFLG("P");
			
			//msgRT.send();
			mc.sendMessage(msgChk);
			msgChk.destroy();
			
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
	
			if (newmessage.getFormatName().equals("EDD120001")) {
				try {
					msgChk = new datapro.eibs.beans.EDD120001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgChk = (EDD120001Message) newmessage;
				// showESD008004(msgRT);
	
				if (IsNotError) { // There are no errors
					
					// show list
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.products.JSEDD1201?SCREEN=1&E01WTRARF=" + userPO.getIdentifier() + "'");
							 
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();

				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgChk", msgChk);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "EDD1200_rdc_spec_inst.jsp");
						callPage(LangPath + "EDD1200_rdc_spec_inst.jsp", req, res);
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
		
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEDD1201?SCREEN="
					+ R_APPROVAL);
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD121201Message msgList = null;
		JBList beanList = null;
		JBObjList objList = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			objList = new datapro.eibs.beans.JBObjList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDD121201Message) mc.getMessageRecord("EDD121201");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD1212");
			msgList.setH01TIMSYS(getTimeStamp());

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDD121201")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String refNum = "";
				
				firstTime = true;
				int indexRow = 0;
				while (true) {

					msgList = (EDD121201Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} 
						
						if (!msgList.getE01WTRSEQ().equals("9999")) {
							
							objList.addRow(msgList);
							
						} else {
						
							myRow = new StringBuffer("<TR>");
							myRow.append(
								"<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\""
									+ msgList.getE01WTRARF()
									+ "\" "
									+ chk
									+ "></TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalBatch('"
									+ msgList.getE01WTRARF()
									+ "', '"
									+ msgList.getE01OFFBNK()
									+ "', '"
									+ msgList.getE01OFFBRN()
									+ "', '"
									+ msgList.getE01OFFOPE()
									+ "', '"
									+ msgList.getE01OFFCON()
									+ "', '"
									+ msgList.getE01AMOUNT()
									+ "')\">"
									+ Util.formatCell(msgList.getE01WTRARF())
									+ "</A></TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalBatch('"
									+ msgList.getE01WTRARF()
									+ "', '"
									+ msgList.getE01OFFBNK()
									+ "', '"
									+ msgList.getE01OFFBRN()
									+ "', '"
									+ msgList.getE01OFFOPE()
									+ "', '"
									+ msgList.getE01OFFCON()
									+ "', '"
									+ msgList.getE01AMOUNT()
									+ "')\">"
									+ Util.formatCell(msgList.getE01OFFBNK())
									+ "</A></TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalBatch('"
									+ msgList.getE01WTRARF()
									+ "', '"
									+ msgList.getE01OFFBNK()
									+ "', '"
									+ msgList.getE01OFFBRN()
									+ "', '"
									+ msgList.getE01OFFOPE()
									+ "', '"
									+ msgList.getE01OFFCON()
									+ "', '"
									+ msgList.getE01AMOUNT()									
									+ "')\">"
									+ Util.formatCell(msgList.getE01OFFBRN())
									+ "</A></TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalBatch('"
									+ msgList.getE01WTRARF()
									+ "', '"
									+ msgList.getE01OFFBNK()
									+ "', '"
									+ msgList.getE01OFFBRN()
									+ "', '"
									+ msgList.getE01OFFOPE()
									+ "', '"
									+ msgList.getE01OFFCON()
									+ "', '"
									+ msgList.getE01AMOUNT()									
									+ "')\">"
									+ Util.formatCell(msgList.getE01OFFOPE() + " " + msgList.getE01OFFCON())
									+ "</A></TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:showInqApprovalBatch('"
									+ msgList.getE01WTRARF()
									+ "', '"
									+ msgList.getE01OFFBNK()
									+ "', '"
									+ msgList.getE01OFFBRN()
									+ "', '"
									+ msgList.getE01OFFOPE()
									+ "', '"
									+ msgList.getE01OFFCON()
									+ "', '"
									+ msgList.getE01AMOUNT()									
									+ "')\">"
									+ Util.formatCell(msgList.getE01AMOUNT())
									+ "</A></TD>");									
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
					}
					newmessage = mc.receiveMessage();
				}

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setPurpose("APPROVAL");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("appList", beanList);
				ses.setAttribute("objList", objList);
				ses.setAttribute("error", msgError);

				if (beanList.getNoResult()) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "MISC_no_result.jsp");
						res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1200_rdc_ap_list.jsp");
						callPage(
							LangPath + "EDD1200_rdc_ap_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD121202Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDD121202Message) mc.getMessageRecord("EDD121202");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EDD1212");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02WTRARF(req.getParameter("REFNUM"));
			msgList.setE02ACTION(req.getParameter("action"));
			msgList.setE02MSGTXT(req.getParameter("reason"));
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
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
				flexLog("IsNotError = " + IsNotError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There is no error
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSEDD1201?SCREEN=700");
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD1200_rdc_ap_list.jsp");
						callPage(
							LangPath + "EDD1200_rdc_ap_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procReqApprovalInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			userPO = new datapro.eibs.beans.UserPos();
			
			userPO.setPurpose("APPROVAL_INQ");

			try {
				userPO.setIdentifier(req.getParameter("REFNUM"));
			} catch (Exception e) {}
			try {
				userPO.setBank(req.getParameter("BANK"));
			} catch (Exception e) {}
			try {
				userPO.setBranch(req.getParameter("BRANCH"));
			} catch (Exception e) {}
			try {
				userPO.setHeader1(req.getParameter("OPE"));
			} catch (Exception e) {}
			try {
				userPO.setHeader2(req.getParameter("CON"));
			} catch (Exception e) {}
			try {
				userPO.setHeader3(req.getParameter("AMOUNT"));
			} catch (Exception e) {}			
			
			userPO.setAccOpt("");

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
		 									
			/*userPO.setHeader20("DO_INQ_AP");
			userPO.setHeader21(super.webAppPath + LangPath + "EDD1200_rdc_ap_inq.jsp");
			res.sendRedirect(super.srctx + LangPath + "EDD1200_rdc_ap_list.jsp");*/
			
			flexLog("About to call Page: " + LangPath + "EDD1200_rdc_ap_inq.jsp");
			callPage(LangPath + "EDD1200_rdc_ap_inq.jsp", req, res);		
				
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

			int screen = R_MAINTENANCE;

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
					// Request
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINTENANCE :
						procReqMaintenance(mc, msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_PASSWORD :
						procReqPassword(req, res, session);
						break;
					case R_APPROVAL :
						procReqApproval(mc, msgUser, req, res, session);
						break;
					case R_APPROVAL_INQ :
						procReqApprovalInq(mc, msgUser, req, res, session);
						break;
						
					// Action
					case A_NEW :
						procActionNew(mc, msgUser, req, res, session);
						break;
					case A_MAINTENANCE :
						procActionMaintenance(mc, msgUser, req, res, session);
						break;
					case A_LIST :
						procActionList(mc, msgUser, req, res, session);
						break;
					case A_NEW_CHECK :
						procActionNewCheck(mc, msgUser, req, res, session);
						break;
					case A_MAINT_CHECK :
						procActionMaintCheck(mc, msgUser, req, res, session);
						break;
					case A_SP_CHECK :
						procActionSpecInst(mc, msgUser, req, res, session);
						break;
					case A_APPROVAL :
						procActionApproval(mc, msgUser, req, res, session);
						break;
					
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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