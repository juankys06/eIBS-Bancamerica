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

public class JSEWD0302 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_LIST   = 1;
	static final int A_LIST   = 2;
	static final int R_I_LIST = 3;
	static final int R_ENTER_LIST 	= 100;
	static final int R_ENTER_ADV_LIST = 300;
	static final int R_I_ENTER_LIST   = 500;
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0302() {
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
	private synchronized void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0302DSMessage msgList = null;
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
			msgList = (EWD0302DSMessage) mc.getMessageRecord("EWD0302DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			try { //Customer
					
				msgList.setSWDCUN(req.getParameter("CUSTOMER"));
				userPO.setHeader20("C");
			} catch (Exception e) {
				userPO.setHeader20("");
			}
			
			
			try { //Officer
					
				msgList.setSWDOFC(req.getParameter("OFFICER"));
				
			} catch (Exception e) {
				
			}
			
			try { //Portfolio Type
					
				msgList.setSWDTYP(req.getParameter("SWDTYP"));
				
			} catch (Exception e) {
				
			}
			
			try { //From Date
					
				msgList.setSWDOP1(req.getParameter("SWDOP1"));
				msgList.setSWDOP2(req.getParameter("SWDOP2"));
				msgList.setSWDOP3(req.getParameter("SWDOP3"));
				
			} catch (Exception e) {
				
			}
			
			try { //To Date
					
				msgList.setSWDUP1(req.getParameter("SWDUP1"));
				msgList.setSWDUP2(req.getParameter("SWDUP2"));
				msgList.setSWDUP3(req.getParameter("SWDUP3"));
				
			} catch (Exception e) {
				
			}
			
			try { //Search
					
				msgList.setRWDTYP(req.getParameter("RWDTYP"));
				
			} catch (Exception e) {
				
			}
			
			try { //Search by Status 
					
				msgList.setSWDPRFFL1(req.getParameter("SWDSTS"));
				
			} catch (Exception e) {
				
			}
			
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0302DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0302DS")) {

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
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EWD0302DSMessage) newmessage;

						marker = msgList.getSWDOPE();
						
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getSWDREC()));
							chk = "checked";
						} 
						else {			
							chk = "";
						} 

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							if(msgList.getSWDPRFOPE().trim().equals("")){
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><input type=\"radio\" name=\"PORTNUM\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "', '"
								+ msgList.getSWDCUN()
								+ "', "
							    + indexRow
								+ ")\"></TD>");
							}
							else {
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/1bori.gif\" alt=\"Pending Approval\" align=\"absmiddle\" border=\"0\" ><input type=\"radio\" name=\"PORTNUM\" value=\""
								+ msgList.getSWDNUM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDNUM()
								+ "', '"
								+ msgList.getSWDCUN()
								+ "', "
							    + indexRow
								+ ")\"></TD>");
							}	
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");		
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDDSC() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDTDS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDOP1(),msgList.getSWDOP2(),msgList.getSWDOP3()) + "</td>");														
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCCY());
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + msgList.getSWDOFC() + "<br><div nowrap>" + msgList.getSWDOFN() + "</div><br>" + msgList.getSWDLEGNME()+ "\"></TD>");
							// myRow.append("<textarea cols=\"40\" rows=\"9\" readonly NAME=\"areadata" +indexRow+"\" VALUE=\"" + msgList.getSWDLEGNME() + "</textarea></TD>");
							myRow.append("</TR>");
							
							userPO.setCusNum(msgList.getSWDCUN());
							userPO.setCusName(msgList.getSWDNME());
							
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
					ses.setAttribute("EWD0302Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0302_sel_portfolio.jsp");
						callPage(LangPath + "EWD0302_sel_portfolio.jsp", req, res);
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

	private synchronized void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EIE0010I_inv_enter_portfolio.jsp");
			callPage(LangPath + "EIE0010I_inv_enter_portfolio.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	private synchronized void procActionPos(
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
		String CUSTOMER = req.getParameter("CUSTOMER");
		
				
		switch (inptOPT) {
			
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0010I?SCREEN=200" + "&CODE=" + CODE + "&CUSTOMER=" + CUSTOMER);
				break;
			
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0010I?SCREEN=200" + "&CODE=" + CODE + "&CUSTOMER=" + CUSTOMER);
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