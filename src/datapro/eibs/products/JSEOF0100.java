package datapro.eibs.products;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSEOF0100 extends SuperServlet {

	protected static final int R_ENTER 	= 1;
	protected static final int A_ENTER 	= 5;
	protected static final int A_LIST   = 3;
	protected static final int A_SP_R   = 4;
	protected static final int R_LIST 	= 2;
	
	protected static final int R_CHK_STOP 	= 7;
	protected static final int A_STOP 		= 8;
	protected static final int A_CHK_SPT 	= 10;	

	protected String LangPath = "E";

	public JSEOF0100() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSEOF0100");
	}
	
	protected void procReqChkStop(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EOF0100_of_inq_det.jsp");
			callPage(LangPath + "EOF0100_of_inq_det.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}
	}

	protected void procReqEnter(
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EOF0100_enter_selection.jsp");
			callPage(LangPath + "EOF0100_enter_selection.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procAction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (userPO.getIdentifier().equals("C")) {
			procActionSpCheck(mc, user, req, res, ses);
		} else {
			procActionRSp(mc, user, req, res, ses);
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
		ETL051001Message msgChk = null;
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
		msgChk = new ETL051001Message();
		msgChk.setH01USERID(user.getH01USR());
		msgChk.setH01PROGRM("ETL0510");
		msgChk.setH01TIMSYS(getTimeStamp());
		msgChk.setH01SCRCOD("01");
		msgChk.setH01OPECOD("0015");
	
		try {
			msgChk.setE01SELSCH(req.getParameter("E01SELSCH"));
		} catch (Exception e) {}
		try {
			msgChk.setE01OFMNCH(req.getParameter("E01OFMNCH"));
		} catch (Exception e) {
			msgChk.setE01OFMNCH("");
		}	
		try {
			userPO.setIdentifier(req.getParameter("E01SELSCH"));	
		} catch (Exception e) {
			userPO.setIdentifier("");
		}
		try {
			userPO.setHeader1(req.getParameter("E01SELSCH"));	
		} catch (Exception e) {
			userPO.setHeader1("");
		}
		
		msgChk.setE01SELDTY("1");		
		// msgCD.send();
		mc.sendMessage(msgChk);
		msgChk.destroy();
		flexLog("ETL051001 Message Sent");
				
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
				flexLog("About to call Page: " + LangPath + "EOF0100_enter_selection.jsp");
				callPage(LangPath + "EOF0100_enter_selection.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
					
		} else if (newmessage.getFormatName().equals("ETL051001")) {
					
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
					
			while (true) {
	
				msgChk = (ETL051001Message) newmessage;
	
				marker = msgChk.getE01INDOPE();
	
				if (marker.equals("*")) {
												
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
			ses.setAttribute("dvList", appList);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
			try {
				flexLog("About to call Page: " + LangPath + "EOF0100_checks_list.jsp");
				callPage(LangPath + "EOF0100_checks_list.jsp", req, res);

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
	JBObjList dvList = null;
	EOF010001Message msgCC = null;
	boolean IsNotError = false;

	dvList = (JBObjList) ses.getAttribute("dvList");
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	datapro.eibs.beans.ETL051001Message msgCh = null;
	
	try {
		int row = -1;
		row = Integer.parseInt(req.getParameter("ROW"));
		dvList.setCurrentRow(row);
		msgCh = (datapro.eibs.beans.ETL051001Message)dvList.getRecord();
		
	} catch (Exception e) {
	}

	try {
			
		flexLog("Send Initial Data");
		msgCC = (EOF010001Message) mc.getMessageRecord("EOF010001");
		msgCC.setH01USERID(user.getH01USR());
		msgCC.setH01PROGRM("EOF0100");
		msgCC.setH01TIMSYS(getTimeStamp());
		msgCC.setH01SCRCOD("01");
    	msgCC.setH01OPECOD("0001");
	
		try {
			msgCC.setE01STPCKN(msgCh.getE01OFMNCH());
		} catch (Exception ex) {
			msgCC.setE01STPCKN("");
		}
		//msgRT.send();
		mc.sendMessage(msgCC);
		msgCC.destroy();
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
	
		if (newmessage.getFormatName().equals("EOF010001")) {
			try {
				msgCC = new datapro.eibs.beans.EOF010001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgCC = (EOF010001Message) newmessage;
	
			if (IsNotError) { // There are no errors
				try {
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "EOF0100_sp_release_action.jsp");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCC", msgCC);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0100?SCREEN=2&E01SELSCH=" + userPO.getIdentifier());
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			} else { // There are errors
				ses.setAttribute("error", msgError);
				userPO.setHeader21("");
				ses.setAttribute("userPO", userPO);
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0100?SCREEN=2&E01SELSCH=" + userPO.getIdentifier());
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	
}		

protected void procActionRSp(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	
	MessageRecord newmessage = null;
	EOF010001Message msgCC = null;
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
		msgCC = (EOF010001Message) mc.getMessageRecord("EOF010001");
		msgCC.setH01USERID(user.getH01USR());
		msgCC.setH01PROGRM("EOF0100");
		msgCC.setH01TIMSYS(getTimeStamp());
		msgCC.setH01SCRCOD("01");
		
		if (userPO.getIdentifier().equals("D"))
			msgCC.setH01OPECOD("0005");
		else
			msgCC.setH01OPECOD("0009");
	
		// all the fields here
		java.util.Enumeration enu = msgCC.fieldEnumeration();
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
		mc.sendMessage(msgCC);
		msgCC.destroy();
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
	
		if (newmessage.getFormatName().equals("EOF010001")) {
			try {
				msgCC = new datapro.eibs.beans.EOF010001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgCC = (EOF010001Message) newmessage;
			// showESD008004(msgRT);
	
			if (IsNotError) { // There are no errors
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println(
					"		top.opener.window.location.href='"
						+ super.webAppPath
						+ "/servlet/datapro.eibs.products.JSEOF0100?SCREEN=2&E01SELSCH=" + userPO.getIdentifier() + "'");
							 
				out.println("		top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			} else { // There are errors
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "EOF0100_sp_release_action.jsp");
					callPage(LangPath + "EOF0100_sp_release_action.jsp", req, res);
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

protected void procActionSpCheck(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	
	MessageRecord newmessage = null;
	EOF010001Message msgCC = null;
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
		msgCC = (EOF010001Message) mc.getMessageRecord("EOF010001");
		msgCC.setH01USERID(user.getH01USR());
		msgCC.setH01PROGRM("EOF0100");
		msgCC.setH01TIMSYS(getTimeStamp());
		msgCC.setH01SCRCOD("01");
		if (userPO.getHeader1().equals("D")) {
			msgCC.setH01OPECOD("0005");
		} else {
			msgCC.setH01OPECOD("0009");	
		}
	
		// all the fields here
		java.util.Enumeration enu = msgCC.fieldEnumeration();
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
		mc.sendMessage(msgCC);
		msgCC.destroy();
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
	
		if (newmessage.getFormatName().equals("EOF010001")) {
			try {
				msgCC = new datapro.eibs.beans.EOF010001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgCC = (EOF010001Message) newmessage;
	
			if (IsNotError) { // There are no errors

				flexLog("About to call Page: " + LangPath + "EOF0100_enter_selection.jsp");
				callPage(LangPath + "EOF0100_enter_selection.jsp", req, res);

			} else { // There are errors
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "EOF0100_sp_release_action.jsp");
					callPage(LangPath + "EOF0100_sp_release_action.jsp", req, res);
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

protected void procReqCheck(
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
	EOF010001Message msgCC = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	datapro.eibs.beans.ETL051001Message msgCh = null;
	
	try {
			
		flexLog("Send Initial Data");
		msgCC = (EOF010001Message) mc.getMessageRecord("EOF010001");
		msgCC.setH01USERID(user.getH01USR());
		msgCC.setH01PROGRM("EOF0100");
		msgCC.setH01TIMSYS(getTimeStamp());
		msgCC.setH01SCRCOD("01");
		msgCC.setH01OPECOD("0001");
	
		try {
			msgCC.setE01STPCKN(req.getParameter("E01OFMNCH"));
		} catch (Exception ex) {
			msgCC.setE01STPCKN("");
		}
		try {
			userPO.setIdentifier(req.getParameter("E01SELSCH"));	
		} catch (Exception e) {
			userPO.setIdentifier("");
		}
		try {
			userPO.setHeader1(req.getParameter("E01SELSCH"));	
		} catch (Exception e) {
			userPO.setHeader1("");
		}		
		//msgRT.send();
		mc.sendMessage(msgCC);
		msgCC.destroy();
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
	
		if (newmessage.getFormatName().equals("EOF010001")) {
			try {
				msgCC = new datapro.eibs.beans.EOF010001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			msgCC = (EOF010001Message) newmessage;
	
			if (IsNotError) { // There are no errors
				try {
					if (msgCC.getE01STPSEQ().equals("0")) {
						userPO.setHeader1("D");					
					} else {
						userPO.setHeader1("S");
					}

					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCC", msgCC);
						
					flexLog("About to call Page: " + LangPath + "EOF0100_sp_release_action.jsp");
					callPage(LangPath + "EOF0100_sp_release_action.jsp", req, res);

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			} else { // There are errors
				ses.setAttribute("error", msgError);

				flexLog("About to call Page: " + LangPath + "EOF0100_enter_selection.jsp");
				callPage(LangPath + "EOF0100_enter_selection.jsp", req, res);

			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	
}		

	protected void procActionChkDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF010001Message msgCD = null;
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
			msgCD = (EOF010001Message) mc.getMessageRecord("EOF010001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EOF010001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0001");

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

			msgCD.send();
			msgCD.destroy();
			flexLog("EOF010001 Message Sent");
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

			if (newmessage.getFormatName().equals("EOF010001")) {

				msgCD = (EOF010001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("stpchk", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0100_of_stop_pay_det.jsp");
						callPage(
							LangPath + "EOF0100_of_stop_pay_det.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(
							LangPath + "error_viewer.jsp",
							req,
							res);
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

	protected void procActionStopSel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF010001Message msgCD = null;
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
			msgCD = (EOF010001Message) mc.getMessageRecord("EOF010001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EOF010001");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0001");

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

			msgCD.send();
			msgCD.destroy();
			flexLog("EOF010001 Message Sent");
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

			if (newmessage.getFormatName().equals("EOF010001")) {

				msgCD = (EOF010001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("stpchk", msgCD);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0100_of_stop_pay_det.jsp");
						callPage(
							LangPath + "EOF0100_of_stop_pay_det.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0100_of_chk_sel.jsp");
						callPage(LangPath + "EOF0100_of_chk_sel.jsp", req, res);
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

	protected void procActionChkStp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF010001Message msgDeal = null;
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
			msgDeal = (EOF010001Message) ses.getAttribute("stpchk");
			msgDeal.setH01USERID(user.getH01USR());
			msgDeal.setH01PROGRM("EOF010001");
			msgDeal.setH01TIMSYS(getTimeStamp());
			msgDeal.setH01SCRCOD("01");
			msgDeal.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgDeal.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgDeal.send();
			mc.sendMessage(msgDeal);
			msgDeal.destroy();
			flexLog("EOF010001 Message Sent");
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

			msgDeal = (EOF010001Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("stpchk", msgDeal);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EOF0100_of_stop_pay_det_confirm.jsp");
					callPage(
						LangPath + "EOF0100_of_stop_pay_det_confirm.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EOF0100_of_stop_pay_det.jsp");
					callPage(
						LangPath + "EOF0100_of_stop_pay_det.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
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
						case R_ENTER:
							procReqEnter(mc, msgUser, req, res, session);
							break;	
						case A_ENTER:
							procReqCheck(mc, msgUser, req, res, session);
							break;					
						case R_LIST:
							procReqList(mc, msgUser, req, res, session);
							break;												
											
						case A_LIST:
							procActionList(mc, msgUser, req, res, session);
							break;						
						case A_SP_R:
							procAction(mc, msgUser, req, res, session);
							break;
							
						case R_CHK_STOP :
							 procReqChkStop(mc, msgUser, req, res, session);
							 break;
						case A_STOP :
							 procActionStopSel(mc, msgUser, req, res, session);
							 break;
						case A_CHK_SPT :
							 procActionChkStp(mc, msgUser, req, res, session);
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