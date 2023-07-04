package datapro.eibs.invest;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEIE0310 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_MATURE_LIST      	= 1;
	static final int R_NEXT_LIST        	= 3;
	static final int R_INT_LIST      	  	= 5;
	
	static final int A_REPORTS      	  	= 2;
	static final int R_ENTER_REPORT 	  	= 100;
	static final int R_ENTER_LIST 	  		= 110;
	static final int A_ENTER_LIST   	  	= 200;
	private String LangPath = "E";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIE0310() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	private synchronized void procReqMatureList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE031001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
			msgList = (EIE031001Message) mc.getMessageRecord("EIE031001");
			
			
			try { //Portfolio Type
					
				msgList.setE01QRYTPR(req.getParameter("PRFTYPE"));
				
			} catch (Exception e) {
				msgList.setE01QRYTPR("");
			}
			
			
			
			try { // Search By Date from 
				msgList.setE01QRYDF1(req.getParameter("DATEF1").toUpperCase());
				msgList.setE01QRYDF2(req.getParameter("DATEF2").toUpperCase());
				msgList.setE01QRYDF3(req.getParameter("DATEF3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setE01QRYDT1(req.getParameter("DATET1").toUpperCase());
				msgList.setE01QRYDT2(req.getParameter("DATET2").toUpperCase());
				msgList.setE01QRYDT3(req.getParameter("DATET3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Payment Type
				msgList.setE01QRYTPY(req.getParameter("PAYTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001");
			msgList.setH01FLGWK3("1"); //Mature Report
			
			msgList.send();
			msgList.destroy();
			flexLog("EIE031001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
						flexLog("About to call Page: " + LangPath + "EIE0310_enter_reports.jsp");
						callPage(LangPath + "EIE0310_enter_reports.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

			} else
				if (newmessage.getFormatName().equals("EIE031001")) {

					try {
						beanList =
							(datapro.eibs.beans.JBList) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.JBList");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow = "";
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EIE031001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						if(firstTime){
							firstTime = false;
							if(marker.equals("*")){
							beanList.setNoResult(true);
							break;
							}
						}
						
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = "<TR>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYCUN() + "-" + msgList.getE01QRYCNM() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYPRF() + "-" + msgList.getE01QRYPDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYTYP() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYIIC() + "-" + msgList.getE01QRYIDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYICY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCAC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCCY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYAMT()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + msgList.getE01QRYRTE() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYCAM()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCUT() + "</td>";
							myRow += "</TR>";
							
							userPO.setHeader1(msgList.getE01QRYDF1()); // Date From
							userPO.setHeader2(msgList.getE01QRYDF2());
							userPO.setHeader3(msgList.getE01QRYDF3());
							userPO.setHeader4(msgList.getE01QRYDT1()); // Date to
							userPO.setHeader5(msgList.getE01QRYDT2());
							userPO.setHeader6(msgList.getE01QRYDT3());
							userPO.setHeader7(msgList.getE01QRYTPR()); //Portfolio Type
							userPO.setHeader8(msgList.getE01QRYTPY()); //Payment Type
							
							
							beanList.addRow(myFlag, myRow);
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EIE0310Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0310_mature_report.jsp");
						callPage(LangPath + "EIE0310_mature_report.jsp", req, res);
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

	private synchronized void procReqNextCallList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE031001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
			msgList = (EIE031001Message) mc.getMessageRecord("EIE031001");
			
			
			try { //Portfolio Type
					
				msgList.setE01QRYTPR(req.getParameter("PRFTYPE"));
				
			} catch (Exception e) {
				msgList.setE01QRYTPR("");
			}
			
			
			
			try { // Search By Date from 
				msgList.setE01QRYDF1(req.getParameter("DATEF1").toUpperCase());
				msgList.setE01QRYDF2(req.getParameter("DATEF2").toUpperCase());
				msgList.setE01QRYDF3(req.getParameter("DATEF3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setE01QRYDT1(req.getParameter("DATET1").toUpperCase());
				msgList.setE01QRYDT2(req.getParameter("DATET2").toUpperCase());
				msgList.setE01QRYDT3(req.getParameter("DATET3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Payment Type
				msgList.setE01QRYTPY(req.getParameter("PAYTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001");
			msgList.setH01FLGWK3("2"); //Next Call
			
			msgList.send();
			msgList.destroy();
			flexLog("EIE031001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);


				try {
						flexLog("About to call Page: " + LangPath + "EIE0310_enter_reports.jsp");
						callPage(LangPath + "EIE0310_enter_reports.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

			} else
				if (newmessage.getFormatName().equals("EIE031001")) {

					try {
						beanList =
							(datapro.eibs.beans.JBList) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.JBList");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow = "";
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EIE031001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						
						if(firstTime){
							firstTime = false;
							if(marker.equals("*")){
							beanList.setNoResult(true);
							break;
							}
						}
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = "<TR>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYCUN() + "-" + msgList.getE01QRYCNM() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYPRF() + "-" + msgList.getE01QRYPDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYTYP() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYIIC() + "-" + msgList.getE01QRYIDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYICY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCAC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCCY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYAMT()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + msgList.getE01QRYRTE() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYCAM()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCUT() + "</td>";
							myRow += "</TR>";

							userPO.setHeader1(msgList.getE01QRYDF1()); // Date From
							userPO.setHeader2(msgList.getE01QRYDF2());
							userPO.setHeader3(msgList.getE01QRYDF3());
							userPO.setHeader4(msgList.getE01QRYDT1()); // Date to
							userPO.setHeader5(msgList.getE01QRYDT2());
							userPO.setHeader6(msgList.getE01QRYDT3());
							userPO.setHeader7(msgList.getE01QRYTPR()); //Portfolio Type
							userPO.setHeader8(msgList.getE01QRYTPY()); //Payment Type


							beanList.addRow(myFlag, myRow);
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EIE0310Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0310_next_call_report.jsp");
						callPage(LangPath + "EIE0310_next_call_report.jsp", req, res);
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

	private synchronized void procReqInterestList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE031001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
			msgList = (EIE031001Message) mc.getMessageRecord("EIE031001");
			
			
			try { //Portfolio Type
					
				msgList.setE01QRYTPR(req.getParameter("PRFTYPE"));
				
			} catch (Exception e) {
				msgList.setE01QRYTPR("");
			}
			
			
			
			try { // Search By Date from 
				msgList.setE01QRYDF1(req.getParameter("DATEF1").toUpperCase());
				msgList.setE01QRYDF2(req.getParameter("DATEF2").toUpperCase());
				msgList.setE01QRYDF3(req.getParameter("DATEF3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setE01QRYDT1(req.getParameter("DATET1").toUpperCase());
				msgList.setE01QRYDT2(req.getParameter("DATET2").toUpperCase());
				msgList.setE01QRYDT3(req.getParameter("DATET3").toUpperCase());

			} catch (Exception e) {
				
			}
			
			try { // Payment Type
				msgList.setE01QRYTPY(req.getParameter("PAYTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001");
			msgList.setH01FLGWK3("3"); //Next Call
			
			msgList.send();
			msgList.destroy();
			flexLog("EIE031001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);


				try {
						flexLog("About to call Page: " + LangPath + "EIE0310_enter_reports.jsp");
						callPage(LangPath + "EIE0310_enter_reports.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

			} else
				if (newmessage.getFormatName().equals("EIE031001")) {

					try {
						beanList =
							(datapro.eibs.beans.JBList) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.JBList");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow = "";
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EIE031001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						if(firstTime){
							firstTime = false;
							if(marker.equals("*")){
							beanList.setNoResult(true);
							break;
							}
						}
						
						
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = "<TR>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYCUN() + "-" + msgList.getE01QRYCNM() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYPRF() + "-" + msgList.getE01QRYPDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYTYP() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" + msgList.getE01QRYIIC() + "-" + msgList.getE01QRYIDC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYICY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCAC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCCY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYAMT()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + msgList.getE01QRYRTE() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYCAM()) + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCUT() + "</td>";
							myRow += "</TR>";


							userPO.setHeader1(msgList.getE01QRYDF1()); // Date From
							userPO.setHeader2(msgList.getE01QRYDF2());
							userPO.setHeader3(msgList.getE01QRYDF3());
							userPO.setHeader4(msgList.getE01QRYDT1()); // Date to
							userPO.setHeader5(msgList.getE01QRYDT2());
							userPO.setHeader6(msgList.getE01QRYDT3());
							userPO.setHeader7(msgList.getE01QRYTPR()); //Portfolio Type
							userPO.setHeader8(msgList.getE01QRYTPY()); //Payment Type


							beanList.addRow(myFlag, myRow);
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EIE0310Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0310_interest_report.jsp");
						callPage(LangPath + "EIE0310_interest_report.jsp", req, res);
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

	private synchronized void procActionReports(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE031001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
			msgList = (EIE031001Message) mc.getMessageRecord("EIE031001");
			
			
			try { //Portfolio Type
					
				msgList.setE01QRYTPR(userPO.getHeader7());
				
			} catch (Exception e) {
				
			}
			
			
			
			try { // Search By Date from 
				msgList.setE01QRYDF1(userPO.getHeader1());
				msgList.setE01QRYDF2(userPO.getHeader2());
				msgList.setE01QRYDF3(userPO.getHeader3());

			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setE01QRYDT1(userPO.getHeader4());
				msgList.setE01QRYDT2(userPO.getHeader5());
				msgList.setE01QRYDT3(userPO.getHeader6());

			} catch (Exception e) {
				
			}
			
			try { // Payment Type
				msgList.setE01QRYTPY(userPO.getHeader8());
			} catch (Exception e) {
				
			}
			
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0015");
			msgList.setH01FLGWK3(req.getParameter("REPORT")); //Report Type
			msgList.setH01FLGWK2("P");
			
			msgList.send();
			msgList.destroy();
			flexLog("EIE031001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EIE031001")) {


					try {
						flexLog("About to call Page: " + LangPath + "EIE0310_report_confirm.jsp");
						callPage(LangPath + "EIE0310_report_confirm.jsp", req, res);
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


	private synchronized void procReqEnterReport(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0500_enter_reports.jsp");
			callPage(LangPath + "EIE0500_enter_reports.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	private synchronized void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0310_enter_reports.jsp");
			callPage(LangPath + "EIE0310_enter_reports.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	private synchronized void procActionEnterReports(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("CREPTYP"));

		String PRFTYP = ""; 
		String DATEF1 = "";
		String DATEF2 = "";
		String DATEF3 = "";
		String DATET1 = "";
		String DATET2 = "";
		String DATET3 = "";
		String PAYTYPE = "";
		
		
         try{		
		  PRFTYP = req.getParameter("PRFTYP");
         }
         catch (Exception e) {
		 } 
		 try{
		  DATEF1 = req.getParameter("DATEF1");
		 }
         catch (Exception e) {
		 } 
		 try{
		  DATEF2 = req.getParameter("DATEF2");
		 }
         catch (Exception e) {
		 } 
		 try{
		  DATEF3 = req.getParameter("DATEF3");
		 }
         catch (Exception e) {
		 } 
		 try{ 
		  DATET1 = req.getParameter("DATET1");
		 }
         catch (Exception e) {
		 } 
		 try{ 
		  DATET2 = req.getParameter("DATET2");
		 }
         catch (Exception e) {
		 } 
		 try{ 
		  DATET3 = req.getParameter("DATET3");
		 }
         catch (Exception e) {
		 } 
		 try{ 
		  PAYTYPE = req.getParameter("PAYTYPE");
		 }
         catch (Exception e) {
		 }  
				
		switch (inptOPT) {
			case 1 : //Mature Report
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=1" + "&PRFTYP=" + PRFTYP 
					 + "&DATEF1=" + DATEF1 + "&DATEF2=" + DATEF2 + "&DATEF3=" + DATEF3);		    
				break;
			case 2 : //Next Call Put
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=3" + "&PRFTYP=" + PRFTYP 
					 + "&DATEF1=" + DATEF1 + "&DATEF2=" + DATEF2 + "&DATEF3=" + DATEF3);		    
				break;
			case 3 : //Interest Payment
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=5" + "&PRFTYP=" + PRFTYP 
					 + "&DATEF1=" + DATEF1 + "&DATEF2=" + DATEF2 + "&DATEF3=" + DATEF3 
					 + "&PAYTYPE=" + PAYTYPE);		    
				break;	
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=1" + "&PRFTYP=" + PRFTYP 
					 + "&DATEF1=" + DATEF1 + "&DATEF2=" + DATEF2 + "&DATEF3=" + DATEF3);
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

			int screen = R_MATURE_LIST;

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
					// Requests
					case R_MATURE_LIST :
						procReqMatureList(mc, msgUser, req, res, session);
						break;	
					case R_NEXT_LIST :
						procReqNextCallList(mc, msgUser, req, res, session);
						break;	
					case R_INT_LIST :
						procReqInterestList(mc, msgUser, req, res, session);
						break;		
					case R_ENTER_LIST :
						procReqEnterSearch(mc, msgUser, req, res, session);
						break;
					case R_ENTER_REPORT :
						procReqEnterReport(mc, msgUser, req, res, session);
						break;						
					
						// Actions
					case A_ENTER_LIST :
						procActionEnterReports(mc, msgUser, req, res, session);
						break;
					case A_REPORTS :
						procActionReports(mc, msgUser, req, res, session);
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
				}
				finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	private synchronized void showERROR(ELEERRMessage m) {
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