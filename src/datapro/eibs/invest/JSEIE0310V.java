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

public class JSEIE0310V extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_INT_LIST      	  = 100;
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIE0310V() {
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
					
				msgList.setH01FLGWK3(req.getParameter("EVENT"));
				
			} catch (Exception e) {
				msgList.setH01FLGWK3("");
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

			try { //Portfolio Type
					
				msgList.setE01QRYTPR(req.getParameter("PRFTYPE"));
				
			} catch (Exception e) {
				msgList.setE01QRYTPR("");
			}
									
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001");
		
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


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000I?SCREEN=100");

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
					String eventVar = "";
					String prfTyp = "";
					String instVar = "Pending";
					int indexRow = 0;
					while (true) {

						msgList = (EIE031001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						if(firstTime){
							firstTime = false;						
						}
										
						
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							if(msgList.getH01FLGWK3().equals("")){ 
							eventVar= "All";
							}
							else if(msgList.getH01FLGWK3().equals("1")){ 
							eventVar= "Interest";
							}
							else if(msgList.getH01FLGWK3().equals("2")){ 
							eventVar= "Maturity";
							} 
							else if(msgList.getH01FLGWK3().equals("3")){ 
							eventVar= "Call";
							}
							else if(msgList.getH01FLGWK3().equals("4")){ 
							eventVar= "Put";
							}
							else if(msgList.getH01FLGWK3().equals("5")){ 
							eventVar= "Dividends";
							}
							
							if(msgList.getE01QRYTYP().equals("N")){
								prfTyp= "Non Discretionary";
							}
							else if(msgList.getE01QRYTYP().equals("D")){
								prfTyp= "Discretionary";
							}
							else {
								prfTyp="";
							}
							
							myRow = "<TR>";
							myRow += "<TD NOWRAP  ALIGN=LEFT>" 
								+ Util.formatDate(msgList.getE01QRYPY1(),msgList.getE01QRYPY2(),msgList.getE01QRYPY3()) 
								+ "</td>"; //Event Date
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + eventVar +  "</td>"; //Event Description
							myRow += "<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:showInstrumentInq('" + msgList.getE01QRYIIC() + "')\">" + msgList.getE01QRYIDC() + "</a></td>";//Instrument
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYICY() + "</td>";//Instrument Currency
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYPTY() +"</td>";//Instrument Type
							myRow += "<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:showCustomerInq('" + msgList.getE01QRYCUN() + "')\">" + msgList.getE01QRYCNM() + "</a></td>";//Customer
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCCY() + "</td>";//Account CCY
							myRow += "<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showRetAccountInq('" + msgList.getE01QRYCAC() + "')\">" + msgList.getE01QRYCAC() + "</a></td>";//Cash Account
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYFEX() + "</td>";//Foreign Exchange
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYAMT()) + "</td>";//Amount to be paid
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYCAM()) + "</td>";//Cash Account Equivalent
							myRow += "<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01QRYBED()) + "</td>";//Balance at Maturity
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYCUT() + "</td>";//Broker
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + prfTyp + "</td>";//Portfolio Type
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01QRYPGD() + "</td>";//Pledge						
							myRow += "</TR>";

	  
							


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
						flexLog("About to call Page: " + LangPath + "EIE0310_next_event.jsp");
						callPage(LangPath + "EIE0310_next_event.jsp", req, res);
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

			int screen = R_INT_LIST;

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
					
					case R_INT_LIST :
						procReqInterestList(mc, msgUser, req, res, session);
						break;		
					
					
						// Actions
					
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