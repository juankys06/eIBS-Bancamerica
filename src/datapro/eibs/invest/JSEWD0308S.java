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

public class JSEWD0308S extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIST   = 1;
	protected static final int A_LIST   = 2;

	protected static final int R_ENTER_LIST 	  = 100;
	protected static final int R_ENTER_ADV_LIST = 300;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0308S() {
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0308DSMessage msgList = null;
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
			msgList = (EWD0308DSMessage) mc.getMessageRecord("EWD0308DS");

			try { //Code search
					
				msgList.setRWDTYP(req.getParameter("SEARCHC"));
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			
			try { // Search By Symbol
				msgList.setSWDSYM(req.getParameter("SYMBOL").toUpperCase());
				
				
				
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}


			try { // Search By ISIN/Serial
				msgList.setSWDNUM(req.getParameter("ISIN").toUpperCase());
				msgList.setSWDSER(req.getParameter("SERIAL").toUpperCase());
				
				
				
			} catch (Exception e) {
				msgList.setSWDNUM("");
			}

			try { // Search By CUSIP
				
				msgList.setSWDCUS(req.getParameter("CUSIP").toUpperCase());
				
				
				
			} catch (Exception e) {
				msgList.setSWDCUS("");
			}
				
			try { // Search By Description
				
				msgList.setSWDDSC(req.getParameter("DESCRIPTION").toUpperCase());
				
				 
				
			} catch (Exception e) {
				msgList.setSWDDSC("");
			}
			
			try { // Search By Instrument Type
				msgList.setSWDPTY(req.getParameter("TYPE").toUpperCase());
			} catch (Exception e) {
				msgList.setSWDDSC("");
			}

			try { // Search By Issuer
				msgList.setSWDEMI(req.getParameter("ISSUER").toUpperCase());
				
				
				
			} catch (Exception e) {
				msgList.setSWDEMI("");
			}
			
			try { // Search By Instrument Code
				msgList.setSWDIIC(req.getParameter("INSTCODE").toUpperCase());
				
				
				
			} catch (Exception e) {
				
			}
			
						
			try { // Search By Status
				msgList.setSWDSTS(req.getParameter("STATUS").toUpperCase());
			} catch (Exception e) {
				msgList.setSWDSTS("");
			}
			
			try { // Search By Date from 
				msgList.setSWDFR1(req.getParameter("DATEF1").toUpperCase());
				msgList.setSWDFR2(req.getParameter("DATEF2").toUpperCase());
				msgList.setSWDFR3(req.getParameter("DATEF3").toUpperCase());
				
				 
				
			} catch (Exception e) {
				
			}
			
			try { // Search By Date to 
				msgList.setSWDTO1(req.getParameter("DATET1").toUpperCase());
				msgList.setSWDTO1(req.getParameter("DATET2").toUpperCase());
				msgList.setSWDTO1(req.getParameter("DATET3").toUpperCase());
				
				
				
			} catch (Exception e) {
				
			}
			
			try { // List Preferential Instruments
				msgList.setSWDSTS(req.getParameter("STATUS").toUpperCase());
			} catch (Exception e) {
				msgList.setSWDSTS("");
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0308DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308S?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0308DS")) {

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

						msgList = (EWD0308DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDIIC()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDIIC()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDIIC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>"   + msgList.getSWDDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSYM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCUS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDSTS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDEMI() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDSD1(),msgList.getSWDSD2(),msgList.getSWDSD3()) + "</td>");														
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
					ses.setAttribute("EWD0308Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0308S_sel_instruments.jsp");
						callPage(LangPath + "EWD0308S_sel_instruments.jsp", req, res);
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



	
	protected void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0070_inv_enter_inst.jsp");
			callPage(LangPath + "EIE0070_inv_enter_inst.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	protected void procReqEnterAdvSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {


		try {
			flexLog("About to call Page: " + LangPath + "EIE0050_inv_inst_adv_search.jsp");
			callPage(LangPath + "EIE0050_inv_inst_adv_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}


	}

	protected void procActionPos(
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
		String TYPE = req.getParameter("TYPE");
		String SYMBOL = req.getParameter("SYMBOL");
		String ISIN = req.getParameter("ISIN");
		String SERIAL = req.getParameter("SERIAL");
		String CUSIP = req.getParameter("CUSIP");
				
		switch (inptOPT) {
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0070?SCREEN=900" + "&CODE=" + CODE );
				break;
				
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0070?SCREEN=900" + "&CODE=" + CODE);
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

			int screen = R_LIST;

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
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_ENTER_LIST :
						procReqEnterSearch(mc, msgUser, req, res, session);
						break;
					case R_ENTER_ADV_LIST :
						procReqEnterAdvSearch(mc, msgUser, req, res, session);
						break;
						// Actions
					case A_LIST :
						procActionPos(mc, msgUser, req, res, session);
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