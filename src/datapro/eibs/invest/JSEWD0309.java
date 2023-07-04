package datapro.eibs.invest;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0309 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIST_ORDERS     = 1;
	protected static final int A_LIST_ORDERS     = 2;
	protected static final int R_LIST_TICKETS    = 3;
	protected static final int A_LIST_TICKETS    = 4;
	protected static final int R_LIST_BO		 = 5;
	protected static final int A_LIST_BO    	 = 6;
	protected static final int R_LIST_INQUIRY	 = 11;
	protected static final int A_LIST_INQUIRY    = 12;
	protected static final int R_LIST_MAINT		 = 13;
	protected static final int A_LIST_MAINT   	 = 14;

	protected static final int R_ENTER_ORDERS	  = 100;
	protected static final int A_ENTER_ORDERS	  = 200;
	protected static final int R_ENTER_TICKETS    = 300;
	protected static final int A_ENTER_TICKETS    = 400;
	protected static final int R_ENTER_BO		  = 500;
	protected static final int A_ENTER_BO		  = 600;					
	protected static final int R_ENTER_INQUIRY	  = 700;
	protected static final int A_ENTER_INQUIRY	  = 800;
	protected static final int R_ENTER_MAINT	  = 900;
	protected static final int A_ENTER_MAINT	  = 1000;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0309() {
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
	protected void procReqListOrders(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0309DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EWD0309DSMessage) mc.getMessageRecord("EWD0309DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			
			try { //Level
				msgList.setRWDLVL("1");
			} catch (Exception e) {
				
			}
			try { //Code search				
				msgList.setRWDTYP(req.getParameter("SEARCHC"));				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			try { // Search By Order Number
				msgList.setSWDNUM(req.getParameter("ORDERNUM").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Customer
				msgList.setSWDCUN(req.getParameter("CUSTOMER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}

			try { // Search By Portfolio
				msgList.setSWDPRF(req.getParameter("PORTFOLIO").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}

			try { // Search By Broker
				msgList.setSWDBRK(req.getParameter("BROKER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}

			try { // Search By Instrument Type
				msgList.setSWDPTY(req.getParameter("TYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Status
				msgList.setSWDSTS("P");
			} catch (Exception e) {
				
			}
			
			try { // Search By Transaction Type
				msgList.setSWDTRN(req.getParameter("TRANSTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Issuer
				msgList.setSWDENM(req.getParameter("ISSUER").toUpperCase());
			} catch (Exception e) {
				
			}
						
			try { // Search By Date from 
				msgList.setSWDIN1(req.getParameter("DATEF1").toUpperCase());
				msgList.setSWDIN2(req.getParameter("DATEF2").toUpperCase());
				msgList.setSWDIN3(req.getParameter("DATEF3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setSWDST1(req.getParameter("DATET1").toUpperCase());
				msgList.setSWDST2(req.getParameter("DATET2").toUpperCase());
				msgList.setSWDST3(req.getParameter("DATET3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0309DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0309DS")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EWD0309DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDIDS() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDIN1(),msgList.getSWDIN2(),msgList.getSWDIN3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDST1(),msgList.getSWDST2(),msgList.getSWDST3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSDS() + "</td>");
							myRow.append("</TR>");

							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0309Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0309_sel_orders.jsp");
						callPage(LangPath + "EWD0309_sel_orders.jsp", req, res);
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
	protected void procReqListTickets(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		EWD0309DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		// Send Initial data
		try {
			msgList = (EWD0309DSMessage) mc.getMessageRecord("EWD0309DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			try { //Level
				msgList.setRWDLVL("2");
			} catch (Exception e) {
				
			}
			
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0309DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=100");


			} else
				if (newmessage.getFormatName().equals("EWD0309DS")) {


					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}


					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {


						msgList = (EWD0309DSMessage) newmessage;


						marker = msgList.getSWDOPE();


						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDIDS() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDIN1(),msgList.getSWDIN2(),msgList.getSWDIN3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDST1(),msgList.getSWDST2(),msgList.getSWDST3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSDS() + "</td>");
							myRow.append("</TR>");


							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0309Help", beanList);
					ses.setAttribute("userPO", userPO);


					try {
						flexLog("About to call Page: " + LangPath + "EWD0309_sel_tickets.jsp");
						callPage(LangPath + "EWD0309_sel_tickets.jsp", req, res);
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

	protected void procReqListBO(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		EWD0309DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		// Send Initial data
		try {
			msgList = (EWD0309DSMessage) mc.getMessageRecord("EWD0309DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			
			try { //Level
				msgList.setRWDLVL("3");
			} catch (Exception e) {
				
			}
			
			try { //Status
				msgList.setSWDSTS("P");
			} catch (Exception e) {
				
			}
			msgList.send();
			msgList.destroy();
			flexLog("EWD0309DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=100");


			} else
				if (newmessage.getFormatName().equals("EWD0309DS")) {


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
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {


						msgList = (EWD0309DSMessage) newmessage;


						marker = msgList.getSWDOPE();


						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDIDS() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDIN1(),msgList.getSWDIN2(),msgList.getSWDIN3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDST1(),msgList.getSWDST2(),msgList.getSWDST3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSDS() + "</td>");
							myRow.append("</TR>");


							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0309Help", beanList);
					ses.setAttribute("userPO", userPO);


					try {
						flexLog("About to call Page: " + LangPath + "EWD0309_sel_back_off.jsp");
						callPage(LangPath + "EWD0309_sel_back_off.jsp", req, res);
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

	protected void procReqListMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		EWD0309DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		// Send Initial data
		try {
			msgList = (EWD0309DSMessage) mc.getMessageRecord("EWD0309DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			
			try { //Level
				msgList.setRWDLVL("4");
			} catch (Exception e) {
				
			}
			try { //Code search				
				msgList.setRWDTYP(req.getParameter("SEARCHC"));				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			try { // Search By Order Number
				msgList.setSWDNUM(req.getParameter("ORDERNUM").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Customer
				msgList.setSWDCUN(req.getParameter("CUSTOMER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}


			try { // Search By Portfolio
				msgList.setSWDPRF(req.getParameter("PORTFOLIO").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}


			try { // Search By Broker
				msgList.setSWDBRK(req.getParameter("BROKER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Instrument Code
				msgList.setSWDIIC(req.getParameter("INSTCODE").toUpperCase());
			} catch (Exception e) {
				
			}

			try { // Search By Instrument Type
				msgList.setSWDPTY(req.getParameter("TYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Status
				msgList.setSWDSTS("C");
			} catch (Exception e) {
				
			}
			
			try { // Search By Transaction Type
				msgList.setSWDTRN(req.getParameter("TRANSTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Issuer
				msgList.setSWDENM(req.getParameter("ISSUER").toUpperCase());
			} catch (Exception e) {
				
			}
						
			try { // Search By Date from 
				msgList.setSWDIN1(req.getParameter("DATEF1").toUpperCase());
				msgList.setSWDIN2(req.getParameter("DATEF2").toUpperCase());
				msgList.setSWDIN3(req.getParameter("DATEF3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setSWDST1(req.getParameter("DATET1").toUpperCase());
				msgList.setSWDST2(req.getParameter("DATET2").toUpperCase());
				msgList.setSWDST3(req.getParameter("DATET3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0309DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=900");


			} else
				if (newmessage.getFormatName().equals("EWD0309DS")) {


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
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {


						msgList = (EWD0309DSMessage) newmessage;


						marker = msgList.getSWDOPE();


						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							
							myRow = new StringBuffer("<TR>");
							if(msgList.getSWDOPE().equals("M")){
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/maintenance.gif\" alt=\"Pending\" align=\"absmiddle\" border=\"0\" ><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							}
							else {	
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							}	
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDIDS() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDIN1(),msgList.getSWDIN2(),msgList.getSWDIN3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDST1(),msgList.getSWDST2(),msgList.getSWDST3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDVNO()) + "</td>");																												
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.formatCell(msgList.getSWDORDQTY()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDPRC()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDPRB()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDIAM()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDIAL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDOCV()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDTVL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSDS() + "</td>");
							myRow.append("</TR>");


							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0309Help", beanList);
					ses.setAttribute("userPO", userPO);


					try {
						flexLog("About to call Page: " + LangPath + "EWD0309M_sel_ticket.jsp");
						callPage(LangPath + "EWD0309M_sel_ticket.jsp", req, res);
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




	





	
	protected void procReqEnterOrders(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0110_inv_enter_orders.jsp");
			callPage(LangPath + "EIE0110_inv_enter_orders.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqListTradeInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		EWD0309DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		// Send Initial data
		try {
			msgList = (EWD0309DSMessage) mc.getMessageRecord("EWD0309DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			
			try { //Level
				msgList.setRWDLVL("");
			} catch (Exception e) {
				
			}
			try { //Code search				
				msgList.setRWDTYP(req.getParameter("SEARCHC"));				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			try { // Search By Order Number
				msgList.setSWDNUM(req.getParameter("ORDERNUM").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Customer
				msgList.setSWDCUN(req.getParameter("CUSTOMER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Instrument Code
				msgList.setSWDIIC(req.getParameter("INSTCODE").toUpperCase());
			} catch (Exception e) {
				
			}

			try { // Search By Portfolio
				msgList.setSWDPRF(req.getParameter("PORTFOLIO").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}


			try { // Search By Broker
				msgList.setSWDBRK(req.getParameter("BROKER").toUpperCase());
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}

			try { // Search By Instrument Type
				msgList.setSWDPTY(req.getParameter("TYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Status
				msgList.setSWDSTS(req.getParameter("STATUS").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Transaction Type
				msgList.setSWDTRN(req.getParameter("TRANSTYPE").toUpperCase());
			} catch (Exception e) {
				
			}
			
			try { // Search By Issuer
				msgList.setSWDENM(req.getParameter("ISSUER").toUpperCase());
			} catch (Exception e) {
				
			}
						
			try { // Search By Date from 
				msgList.setSWDIN1(req.getParameter("DATEF1").toUpperCase());
				msgList.setSWDIN2(req.getParameter("DATEF2").toUpperCase());
				msgList.setSWDIN3(req.getParameter("DATEF3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setSWDST1(req.getParameter("DATET1").toUpperCase());
				msgList.setSWDST2(req.getParameter("DATET2").toUpperCase());
				msgList.setSWDST3(req.getParameter("DATET3").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0309DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=700");


			} else
				if (newmessage.getFormatName().equals("EWD0309DS")) {


					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}


					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {


						msgList = (EWD0309DSMessage) newmessage;


						marker = msgList.getSWDOPE();


						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDIDS() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDIN1(),msgList.getSWDIN2(),msgList.getSWDIN3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDST1(),msgList.getSWDST2(),msgList.getSWDST3()) + "</td>");																					
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDVNO()) + "</td>");																												
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.formatCell(msgList.getSWDORDQTY()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDPRC()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDPRB()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDIAM()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDIAL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDOCV()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDORDTVL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSDS() + "</td>");
							myRow.append("</TR>");


							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0309Help", beanList);
					ses.setAttribute("userPO", userPO);


					try {
						flexLog("About to call Page: " + LangPath + "EWD0309I_sel_ticket.jsp");
						callPage(LangPath + "EWD0309I_sel_ticket.jsp", req, res);
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




	

	
	protected void procReqEnterTickets(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {


		try {
			flexLog("About to call Page: " + LangPath + "EIE0120_inv_enter_tickets.jsp");
			callPage(LangPath + "EIE0120_inv_enter_tickets.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}
	
	protected void procReqEnterBackOffice(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {


		try {
			flexLog("About to call Page: " + LangPath + "EIE0130_inv_enter_tickets.jsp");
			callPage(LangPath + "EIE0130_inv_enter_tickets.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}

	protected void procReqEnterMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {


		try {
			flexLog("About to call Page: " + LangPath + "EIE0130M_inv_enter_tickets.jsp");
			callPage(LangPath + "EIE0130M_inv_enter_tickets.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}

	protected void procReqEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {


		try {
			flexLog("About to call Page: " + LangPath + "EIE0130I_inv_enter_tickets.jsp");
			callPage(LangPath + "EIE0130I_inv_enter_tickets.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}
	

	

	

	
	protected void procActionListOrders(
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

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		String CODE = req.getParameter("CODE");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0110?SCREEN=200");		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0110?SCREEN=400" + "&CODE=" + CODE );
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0110?SCREEN=600" + "&CODE=" + CODE );
				break;
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0110?SCREEN=800" + "&CODE=" + CODE );
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0110?SCREEN=800" + "&CODE=" + CODE);
		}
	}
	protected void procActionListTickets(
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


		inptOPT = Integer.parseInt(req.getParameter("opt"));


		String CODE = req.getParameter("CODE");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0120?SCREEN=200");		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0120?SCREEN=400" + "&CODE=" + CODE );
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0120?SCREEN=600" + "&CODE=" + CODE );
				break;
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0120?SCREEN=800" + "&CODE=" + CODE );
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0120?SCREEN=800" + "&CODE=" + CODE);
		}
	}

	protected void procActionListBO(
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


		inptOPT = Integer.parseInt(req.getParameter("opt"));


		String CODE = req.getParameter("CODE");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=200");		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=400" + "&CODE=" + CODE );
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=600" + "&CODE=" + CODE );
				break;
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=800" + "&CODE=" + CODE );
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=800" + "&CODE=" + CODE);
		}
	}

	protected void procActionListInquiry(
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


		inptOPT = Integer.parseInt(req.getParameter("opt"));


		String CODE = req.getParameter("CODE");
				
		switch (inptOPT) {
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130I?SCREEN=400" + "&CODE=" + CODE );
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130I?SCREEN=400" + "&CODE=" + CODE);
		}
	}



	protected void procActionListMaintenance(
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


		inptOPT = Integer.parseInt(req.getParameter("opt"));


		String CODE = req.getParameter("CODE");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130M?SCREEN=200");		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130M?SCREEN=400" + "&CODE=" + CODE );
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130M?SCREEN=600" + "&CODE=" + CODE );
				break;
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130M?SCREEN=800" + "&CODE=" + CODE );
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130M?SCREEN=800" + "&CODE=" + CODE);
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

			int screen = R_LIST_ORDERS;

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
					case R_LIST_ORDERS :
						procReqListOrders(mc, msgUser, req, res, session);
						break;
					case R_LIST_TICKETS :
						procReqListTickets(mc, msgUser, req, res, session);
						break;	
					case R_LIST_BO :
						procReqListBO(mc, msgUser, req, res, session);
						break;	
					case R_LIST_INQUIRY :
						procReqListTradeInquiry(mc, msgUser, req, res, session);
						break;
					case R_LIST_MAINT :
						procReqListMaintenance(mc, msgUser, req, res, session);
						break;
								
					case R_ENTER_ORDERS :
						procReqEnterOrders(mc, msgUser, req, res, session);
						break;
					case R_ENTER_TICKETS :
						procReqEnterTickets(mc, msgUser, req, res, session);
						break;	
					case R_ENTER_BO :
						procReqEnterBackOffice(mc, msgUser, req, res, session);
						break;	
					case R_ENTER_INQUIRY :
						procReqEnterInquiry(mc, msgUser, req, res, session);
						break;	
					case R_ENTER_MAINT :
						procReqEnterMaintenance(mc, msgUser, req, res, session);
						break;			
						// Actions
					case A_LIST_ORDERS :
						procActionListOrders(mc, msgUser, req, res, session);
						break;
					case A_LIST_TICKETS :
						procActionListTickets(mc, msgUser, req, res, session);
						break;
					case A_LIST_BO :
						procActionListBO(mc, msgUser, req, res, session);
						break;	
					case A_LIST_INQUIRY :
						procActionListInquiry(mc, msgUser, req, res, session);
						break;	
					case A_LIST_MAINT :
						procActionListMaintenance(mc, msgUser, req, res, session);
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