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

public class JSEWD0317 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIST   = 1;
	protected static final int A_LIST   = 2;
	protected static final int R_I_LIST = 3;
	protected static final int R_ENTER_LIST 	= 100;
	protected static final int R_ENTER_ADV_LIST = 300;
	protected static final int R_I_ENTER_LIST   = 500;
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0317() {
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
		EWD0317DSMessage msgList = null;
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
			msgList = (EWD0317DSMessage) mc.getMessageRecord("EWD0317DS");
			
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			try { //Customer
					
				msgList.setSWDORDCUN(req.getParameter("CUSTOMER"));
				
			} catch (Exception e) {
				msgList.setSWDORDCUN(userPO.getCusNum());
			}
			
			try { //Cash Account
					
				msgList.setSWDORDHAC(req.getParameter("ACCOUNT"));
				
			} catch (Exception e) {
			}
			
			try { //Settlement Date
					
				msgList.setSWDORDST1(req.getParameter("DATE1"));
				
			} catch (Exception e) {
			}
			try { //Settlement Date
					
				msgList.setSWDORDST2(req.getParameter("DATE2"));
				
			} catch (Exception e) {
			}
			try { //Settlement Date
					
				msgList.setSWDORDST3(req.getParameter("DATE3"));
				
			} catch (Exception e) {
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0317DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0317?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0317DS")) {

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

						msgList = (EWD0317DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							userPO.setHeader18(Util.fcolorCCY(msgList.getSWDTOTHLD())); //Total Holds
							userPO.setHeader19(Util.fcolorCCY(msgList.getSWDTOTUNC())); //Total Uncollected
							userPO.setHeader20(Util.fcolorCCY(msgList.getSWDTOTFUT())); //Total Future
							userPO.setHeader21(Util.fcolorCCY(msgList.getSWDSETBAL())); //Settlement Balance
							beanList.setShowNext(false);
							
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDUNCREF() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDUNCRSN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDUNCRD1(),msgList.getSWDUNCRD2(),msgList.getSWDUNCRD3()) + "</td>");														
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getSWDUNCMD1(),msgList.getSWDUNCMD2(),msgList.getSWDUNCMD3()) + "</td>");														
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDUNCDYS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDFLDDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDUNCAMN()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getSWDSETBAL()) + "</td>");
							myRow.append("</TR>");
							
							//Header
							userPO.setCusNum(msgList.getSWDORDCUN());
							userPO.setCusName(msgList.getSWDCUSNME());
							userPO.setBank(msgList.getSWDORDHBK());
							userPO.setBranch(msgList.getSWDORDHBR());
							userPO.setCurrency(msgList.getSWDORDHCY());
							userPO.setGenLedger(msgList.getSWDORDHGL()); 
							userPO.setAccNum(msgList.getSWDORDHAC());
							//Totals
							userPO.setHeader17(Util.formatDate(msgList.getSWDORDST1(),msgList.getSWDORDST2(),msgList.getSWDORDST3()));
							
							
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
					ses.setAttribute("EWD0317Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0317_inquiry_holds.jsp");
						callPage(LangPath + "EWD0317_inquiry_holds.jsp", req, res);
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