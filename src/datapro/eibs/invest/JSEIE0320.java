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
import javax.sound.sampled.spi.FormatConversionProvider;

import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEIE0320 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	
	protected static final int R_LIST_CUST     = 100;
	protected static final int R_LIST_INST     = 300;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIE0320() {
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
	protected void procReqListInstrumentPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE032002Message msgList = null;
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
			msgList = (EIE032002Message) mc.getMessageRecord("EIE032002");
			
			msgList.setH02USERID(user.getH01USR());
			
			
			try { // Search By Instrument
				msgList.setE02MSTIIC(req.getParameter("CODE"));
				
			} catch (Exception e) {
				
			}
			try { // Search By Custodian
				msgList.setE02MSTCUC(req.getParameter("CUSTODIAN"));
				
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

				msgError = (ELEERRMessage) newmessage;

				showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000I?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EIE032002")) {

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
					String QTYVNO = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (EIE032002Message) newmessage;

						marker = msgList.getH02FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							if(msgList.getE02ISIPTY().equals("BND") || msgList.getE02ISIPTY().equals("PFS")){
							   userPO.setHeader16(msgList.getE02TOTVNO());
							   
							} else {
								userPO.setHeader16(msgList.getE02TOTQTY());
								
							}							

							break;
						} else {
							
							userPO.setHeader20(msgList.getD02ISIDSC());//Instrument Description
							userPO.setHeader21(msgList.getE02CSTNME());//Custodian
							userPO.setHeader22(msgList.getE02MSTIIC());//Instrument
							userPO.setHeader17(msgList.getD02ISIDES());//Instrument Type Description
							userPO.setHeader15(msgList.getE02MSTCCY());//Instrument Currency
							//Pending NetOutstanding Balance
							
							if(msgList.getE02ISIPTY().equals("MUT") || msgList.getE02ISIPTY().equals("EQT")){
							   userPO.setHeader18("Quantity");
							   
							} else {
							userPO.setHeader18("Nominal Value");
							
							}
							
							myRow = new StringBuffer("<TR>");
							
							if(msgList.getE02TOTCOA().equals("0.00")){
								myRow.append("<TD NOWRAP  ALIGN=RIGHT><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getE02MSTCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02MSTCUN() 
								+ "','" 
								+ msgList.getE02MSTNUM()
								+ "','" 
								+ msgList.getE02PRFVCY()
								+ "','" 
								+ msgList.getE02TOTVNO()
								+ "','"
								+ msgList.getE02TOTQTY()
								+ "','" 
								+ msgList.getE02ISIPTY()
								+ "','"
								+ msgList.getE02MSTCUC()
								+ "')\"></TD>");
	
							}
							else {	
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/pledge.gif\" alt=\"Pledge\" align=\"absmiddle\" border=\"0\" ><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getE02MSTCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02MSTCUN() 
								+ "','" 
								+ msgList.getE02MSTNUM()
								+ "','" 
								+ msgList.getE02PRFVCY()
								+ "','" 
								+ msgList.getE02TOTVNO()
								+ "','"
								+ msgList.getE02TOTQTY()
								+ "','" 
								+ msgList.getE02ISIPTY()
								+ "','"
								+ msgList.getE02MSTCUC()
								+ "')\"></TD>");
							}	
							myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showCustomerInq('" + msgList.getE02MSTCUN() + "')\">" 
										 +  msgList.getE02CUSNME() +"</A></td>");	
							myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showPortfolioInq('" + msgList.getE02MSTCUN() + "','" + msgList.getE02MSTNUM() + "')\">"
									   + msgList.getE02MSTNUM()
									   + "-" + msgList.getE02PRFDSC() + "</A></td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02TYPDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02PRFVCY() + "</td>");
							
							//Conditional Caption
							if(msgList.getE02ISIPTY().equals("BND") || msgList.getE02ISIPTY().equals("PFS")){
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTVNO()) + "</td>");
							}
							else {
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + msgList.getE02TOTQTY() + "</td>");
							}					
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02LSTPRI()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02PURPRI()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTCOA()) + "</td>"); 
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" 
							+ Util.formatDate(msgList.getE02LSTPU1(),msgList.getE02LSTPU2(),msgList.getE02LSTPU3()) + "</td>");

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
					ses.setAttribute("EIE003202Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0320_sel_inst.jsp");
						callPage(LangPath + "EIE0320_sel_inst.jsp", req, res);
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

	protected void procReqListCustodianPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE032001Message msgList = null;
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
			msgList = (EIE032001Message) mc.getMessageRecord("EIE032001");
			
			msgList.setH01USERID(user.getH01USR());
			
			
			try { // Search By Instrument
				msgList.setE01MSTIIC(req.getParameter("CODE"));
				
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

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000I?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EIE032001")) {

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

						msgList = (EIE032001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							if(msgList.getE01ISIPTY().equals("BND") || msgList.getE01ISIPTY().equals("PFS")){
							   userPO.setHeader16(msgList.getE01TOTVNO());
							} else {
							userPO.setHeader16(msgList.getE01TOTQTY());
							}							
							break;
						} else {
							
												
							userPO.setHeader20(msgList.getD01ISIDSC());//Instrument Description
							userPO.setHeader21(msgList.getE01MSTCCY());//CURRENCY
							userPO.setHeader22(msgList.getE01MSTIIC());//Instrument
							userPO.setHeader17(msgList.getD01ISIDES());//Instrument Type Description
							
							if(msgList.getE01ISIPTY().equals("BND") || msgList.getE01ISIPTY().equals("PFS")){
							   userPO.setHeader18("Nominal Value");
							} else {
							userPO.setHeader18("Quantity");
							}
							//Pending NetOutstanding Balance
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getE01MSTCUC()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE01MSTCUC()  
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01CSTNME() + "</td>");
							
							if(msgList.getE01ISIPTY().equals("BND") || msgList.getE01ISIPTY().equals("PFS")){
							
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01TOTVNO()) + "</td>");
							
							}
							else {
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + msgList.getE01TOTQTY() + "</td>");
							}
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
					ses.setAttribute("EIE003201Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0320_sel_custodian.jsp");
						callPage(LangPath + "EIE0320_sel_custodian.jsp", req, res);
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

			int screen = R_LIST_INST;

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
					case R_LIST_INST :
						procReqListInstrumentPos(mc, msgUser, req, res, session);
						break;
					case R_LIST_CUST :
						procReqListCustodianPos(mc, msgUser, req, res, session);
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