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

public class JSEIE0300 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIST_PORT     = 1;
	
	protected static final int R_LIST_POS    = 3;
	
	protected static final int R_LIST_TRAN    = 5;
	
	protected static final int R_PRINT        = 15;
	
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIE0300() {
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
	protected void procReqListPortfolios(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE030001Message msgList = null;
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
			msgList = (EIE030001Message) mc.getMessageRecord("EIE030001");
			
			msgList.setH01USERID(user.getH01USR());
			
			
			try { // Search By Customer
				msgList.setE01PRFCUN(req.getParameter("CUSTOMER"));
				
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
				if (newmessage.getFormatName().equals("EIE030001")) {

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

						msgList = (EIE030001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							userPO.setCusNum(msgList.getE01PRFCUN());
							userPO.setCusName(msgList.getE01CUSNME());
							userPO.setHeader1(Util.fcolorCCY(msgList.getE01TOTDIS()));//Total  Disc MV
							userPO.setHeader2(Util.fcolorCCY(msgList.getE01TOTNDI()));//Total N Disc MV
							userPO.setHeader3(Util.fcolorCCY(msgList.getE01TOTBAL()));//Total Pos							
							userPO.setHeader4(Util.fcolorCCY(msgList.getE01DISINI()));//Total Disc MV
							userPO.setHeader5(Util.fcolorCCY(msgList.getE01NDSINI()));//Total n Disc MV
							userPO.setHeader6(Util.fcolorCCY(msgList.getE01TOTBOK()));//Total Pos
							userPO.setHeader7(msgList.getE01BSECCY());//Base Currency
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getE01PRFCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE01PRFCUN() 
								+ "','" 
								+ msgList.getE01PRFNUM()
								+ "','" 
								+ msgList.getE01PRFVCY() 
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showPortfolioInq('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
									   + msgList.getE01PRFNUM()
									   + "-" + msgList.getE01PRFDSC() + "</A></td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01TYPDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE01PRFVCY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01TOTBAL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01TOTBOK()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BSEBAL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01BSEBOK()) + "</td>");

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
					ses.setAttribute("EIE003001Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0300_sel_portfolio.jsp");
						callPage(LangPath + "EIE0300_sel_portfolio.jsp", req, res);
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

	protected void procReqListOpen(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE030002Message msgList = null;
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
			msgList = (EIE030002Message) mc.getMessageRecord("EIE030002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02FLGWK3("");
			
			try { // Customer
				msgList.setE02PRFCUN(req.getParameter("CUSTOMER"));
				
			} catch (Exception e) {
			}
			
			try { // Portfolio
				msgList.setE02PRFNUM(req.getParameter("PORTFOLIO"));
				
			} catch (Exception e) {
			}
			
			try { // Currency
				msgList.setE02PRFVCY(req.getParameter("CURRENCY"));
				
			} catch (Exception e) {
				
			}
			
			try { // Type
				msgList.setE02PRFTYP(req.getParameter("TYPEC"));
				
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=2" + "&E01PRFCUN=" + userPO.getCusNum());

			} else
				if (newmessage.getFormatName().equals("EIE030002")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
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

						msgList = (EIE030002Message) newmessage;

						marker = msgList.getH02FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							userPO.setHeader17(msgList.getE02CURINV()); //Market Value
							break;
						} else {
							if (firstTime) {
								firstTime= false;
								chk = "checked";
							} else chk="";
							
							String Frec = msgList.getD02ISIPFC();
							
							if(Frec.equals("D")){
							    Frec = msgList.getD02ISIPFV() + "-" + msgList.getD02ISIPFC();
							}
							else {Frec = msgList.getD02ISIPFC();}
							
							myRow = new StringBuffer("<TR>");
							
							if(msgList.getE02TOTCOA().equals("0.00")){
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><input type=\"radio\" name=\"PORTF\" value=\""
								+ msgList.getE02PRFCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02ORDIIC()
								+ "'," + indexRow 
								+ ")\"></TD>");
							}
							else {
							 myRow.append("<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/pledge.gif\" alt=\"Pledge\" align=\"absmiddle\" border=\"0\" ><input type=\"radio\" name=\"PORTF\" value=\""
								+ msgList.getE02PRFCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02ORDIIC()
								+ "'," + indexRow 
								+ ")\"></TD>");
							} 
							myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:showInstrumentInq('" + msgList.getE02ORDIIC() + "')\">"
									 + msgList.getD02ISIDSC() 
									 + "</A></td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02ORDPCY() + "</td>");
							
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTVNO()) + "</td>");
						    myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTQTY()) + "</td>");
						    myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02LSTPRI()));
						    //br 
						    myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.fcolorCCY(msgList.getE02PURPRI()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTINV()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTIAL()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTPUR()) + "<br>");
							myRow.append(Util.formatDate(msgList.getE02LSTPU1(),msgList.getE02LSTPU2(),msgList.getE02LSTPU3()) + "<br><br>");
							//br
							myRow.append(Util.fcolorCCY(msgList.getE02CURMKV()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02CURIAL()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02CURINV()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02CURDYS()) + "<br><br>");
							//br
							myRow.append(Util.formatCell(msgList.getE02INCOME()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02YIELD()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02FXRTE()) + "<br><br>");
							//br
							myRow.append(Frec + "<br><br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTCOA()) +"<br>\"></TD>");
							//br
							
							myRow.append("</TR>");
							
							userPO.setHeader4(msgList.getE02PRFTYP()); //Portfolio Type
							userPO.setHeader5(msgList.getE02OFCNME()); //Account Officer
							userPO.setHeader6(Util.formatDate(msgList.getE02PRFOP1(),msgList.getE02PRFOP2(),msgList.getE02PRFOP3()));
							userPO.setHeader7(msgList.getE02PRFVCY()); //Valuation Currency
							userPO.setHeader8(msgList.getE02PRFNUM()); //Portfolio Number
							userPO.setHeader9(msgList.getE02PRFDSC()); //Portfolio Name
							
							userPO.setHeader20(""  ); //Portfolio Name

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
					ses.setAttribute("EIE003002Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0300_transactions_inquiry.jsp");
						callPage(LangPath + "EIE0300_transactions_inquiry.jsp", req, res);
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

	protected void procReqPrint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE030002Message msgList = null;
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
			msgList = (EIE030002Message) mc.getMessageRecord("EIE030002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02FLGWK3("P");
			
			try { // Customer
				msgList.setE02PRFCUN(req.getParameter("CUSTOMER"));
				
			} catch (Exception e) {
			}
			
			try { // Portfolio
				msgList.setE02PRFNUM(req.getParameter("PORTFOLIO"));
				
			} catch (Exception e) {
			}
			
			try { // Currency
				msgList.setE02PRFVCY(req.getParameter("CURRENCY"));
				
			} catch (Exception e) {
				
			}
			
			try { // Type
				msgList.setE02PRFTYP(req.getParameter("TYPEC"));
				
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=2" + "&E01PRFCUN=" + userPO.getCusNum());

			} else
				if (newmessage.getFormatName().equals("EIE030002")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
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

						msgList = (EIE030002Message) newmessage;

						marker = msgList.getH02FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							
							break;
						} else {
							if (firstTime) {
								firstTime= false;
								chk = "checked";
							} else chk="";
							
							String Frec = msgList.getD02ISIPFC();
							
							if(Frec.equals("D")){
							    Frec = msgList.getD02ISIPFV() + "-" + msgList.getD02ISIPFC();
							}
							else {Frec = msgList.getD02ISIPFC();}
							
							myRow = new StringBuffer("<TR>");
							if(msgList.getE02TOTCOA().equals("0.00")){
							myRow.append("<TD NOWRAP  ALIGN=RIGHT><input type=\"radio\" name=\"PORTF\" value=\""
								+ msgList.getE02PRFCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02ORDIIC()
								+ "'," + indexRow 
								+ ")\"></TD>");
							}
							else {
							 myRow.append("<TD NOWRAP  ALIGN=RIGHT><img src=\"../images/pledge.gif\" alt=\"Pledge\" align=\"absmiddle\" border=\"0\" ><input type=\"radio\" name=\"PORTF\" value=\""
								+ msgList.getE02PRFCUN()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02ORDIIC()
								+ "'," + indexRow 
								+ ")\"></TD>");
							} 

							myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:showInstrumentInq('" + msgList.getE02ORDIIC() + "')\">"
									 + msgList.getD02ISIDSC() 
									 + "</A></td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02ORDPCY() + "</td>");
							
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTVNO()) + "</td>");
						    myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02TOTQTY()) + "</td>");
						    myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02LSTPRI()));
						    //br 
						    myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.fcolorCCY(msgList.getE02PURPRI()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTINV()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTIAL()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTPUR()) + "<br>");
							myRow.append(Util.formatDate(msgList.getE02LSTPU1(),msgList.getE02LSTPU2(),msgList.getE02LSTPU3()) + "<br><br>");
							//br
							myRow.append(Util.fcolorCCY(msgList.getE02CURMKV()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02CURIAL()) + "<br>");
							myRow.append(Util.fcolorCCY(msgList.getE02CURINV()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02CURDYS()) + "<br><br>");
							//br
							myRow.append(Util.formatCell(msgList.getE02INCOME()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02YIELD()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE02FXRTE()) + "<br><br>");
							//br
							myRow.append(Frec + "<br><br>");
							myRow.append(Util.fcolorCCY(msgList.getE02TOTCOA()) +"<br>\"></TD>");
							//br
							
							myRow.append("</TR>");
							
							userPO.setHeader4(msgList.getE02PRFTYP()); //Portfolio Type
							userPO.setHeader5(msgList.getE02OFCNME()); //Account Officer
							userPO.setHeader6(Util.formatDate(msgList.getE02PRFOP1(),msgList.getE02PRFOP2(),msgList.getE02PRFOP3()));
							userPO.setHeader7(msgList.getE02PRFVCY()); //Valuation Currency
							userPO.setHeader8(msgList.getE02PRFNUM()); //Portfolio Number
							userPO.setHeader9(msgList.getE02PRFDSC()); //Portfolio Name

							if(msgList.getH02FLGWK3().equals("P")){
							   userPO.setHeader20("EIE0301 Report has been printed, please check your spool file");				   
							   msgList.setH02FLGWK3("");
							} else {
							userPO.setHeader20("");
							}

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
					ses.setAttribute("EIE003002Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0300_transactions_inquiry.jsp");
						callPage(LangPath + "EIE0300_transactions_inquiry.jsp", req, res);
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



	protected void procReqListTransact(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EIE030003Message msgList = null;
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
			msgList = (EIE030003Message) mc.getMessageRecord("EIE030003");
			msgList.setH03USERID(user.getH01USR());
			
			try { // Customer
				msgList.setE03PRFCUN(req.getParameter("CUSTOMER"));
				
			} catch (Exception e) {
			}
			
			try { // Portfolio
				msgList.setE03PRFNUM(req.getParameter("PORTFOLIO"));
				
			} catch (Exception e) {
			}
			
			try { // Currency
				msgList.setE03PRFVCY(req.getParameter("CURRENCY"));
				
			} catch (Exception e) {
				
			}
			
			try { // Type
				msgList.setE03PRFTYP(req.getParameter("TYPEC"));
				
			} catch (Exception e) {
				
			}
			
			try { // Instrument
				msgList.setE03ORDIIC(req.getParameter("CODE"));
				
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=3" + "&CUSTOMER=" + userPO.getCusNum()
								+ "&PORTFOLIO=" + userPO.getHeader8() + "&CURRENCY=" + userPO.getHeader7()
								);

			} else
				if (newmessage.getFormatName().equals("EIE030003")) {

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

						msgList = (EIE030003Message) newmessage;

						marker = msgList.getH03FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showOrdersInq('" + msgList.getE03ORDNUM() + "')\">" 
									 + msgList.getE03ORDNUM() + "</A></td>");
							
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getE03TRNDSC() +"</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" 
									 + Util.formatDate(msgList.getE03ORDIN1(),msgList.getE03ORDIN2(),msgList.getE03ORDIN3()) 
									 + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" 
									 + Util.formatDate(msgList.getE03ORDST1(),msgList.getE03ORDST2(),msgList.getE03ORDST3()) 
									 + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDVNO()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDQTY()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDPRC()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDIAM()) + "</td>");	
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDIAL()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDOCV()) + "</td>");		 
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDTVL()) + "</td>");		 
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE03ORDCOA()) + "</td>");		 
							myRow.append("</TR>");
							
							userPO.setCusNum(msgList.getE03PRFCUN()); // Customer Number
							userPO.setCusName(msgList.getE03CUSNME());//Customer Name
							userPO.setHeader8(msgList.getE03PRFNUM()); //Portfolio Number
							userPO.setHeader9(msgList.getE03PRFDSC()); //Portfolio description
							userPO.setHeader4(msgList.getE03PRFTYP()); // Portfolio Type
							userPO.setHeader5(msgList.getE03ORDIIC()); //Inst
							userPO.setHeader6(msgList.getD03ISIDSC()); //Descr
							
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
					ses.setAttribute("EIE003003Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EIE0300_position_inquiry.jsp");
						callPage(LangPath + "EIE0300_position_inquiry.jsp", req, res);
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

			int screen = R_LIST_PORT;

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
					case R_LIST_PORT :
						procReqListPortfolios(mc, msgUser, req, res, session);
						break;
						
					case R_LIST_POS :
						procReqListOpen(mc, msgUser, req, res, session);
						break;
					
					case R_LIST_TRAN :
						procReqListTransact(mc, msgUser, req, res, session);
						break;
						
					case R_PRINT :
						procReqPrint(mc, msgUser, req, res, session);
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