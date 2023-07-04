package datapro.eibs.helps;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0318 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0318() {
		super();
	}
	/**
	 * This method was created by David Mavilla
	public void destroy() {
	
		flexLog("free resources used by JSESS00    40");
		
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0318DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;

		JBList beanList = null;

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

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
					"currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";

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
					msgHelp =
						(EWD0318DSMessage) mc.getMessageRecord("EWD0318DS");

					

					try { //Description
						msgHelp.setSWDMSTCUN(req.getParameter("CUSTOMER"));
					} catch (Exception e) {
					}
					
					msgHelp.setRWDUSR(msgUser.getH01USR());
					
					msgHelp.setRWDTYP("");
					

					try { //From Pos

						msgHelp.setRWDFRC(req.getParameter("Pos"));

					} catch (Exception e) {

					}

					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0308DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0318DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0318DS Message Recived");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String myDesc = "";
						int compar = 0;
						boolean firstTime = true;
						
						while (true) {

							msgHelp = (EWD0318DSMessage) newmessage;

							marker = msgHelp.getSWDOPE();			
							
							
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							}	
							 else {
							 	
							 	if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelp.getSWDREC()));
									
								} 
															 	
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ msgHelp.getSWDMSTPRF() //Portfolio Number
										+ "</a></td>");
								
								myRow.append(
									"<TD NOWRAP  ALIGN=LEFT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=LEFT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ msgHelp.getSWDCUCNME() // Custodian Name
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=CENTER><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ Util.fcolorCCY(msgHelp.getSWDMSTVNO()) //Nominal Value
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"
										+ Util.fcolorCCY(msgHelp.getSWDMSTCOA()) //Collateral Amount
										+ "</a></td>");
								myRow.append(
									"<TD NOWRAP  ALIGN=RIGHT><a href=\"javascript:enter('"
										+ msgHelp.getSWDMSTPRF() //Portfolio
										+ "','"
										+ msgHelp.getSWDMSTIIC() //Instrument Code
										+ "','"
										+ msgHelp.getSWDISIDSC() //Instrument Description
										+ "','"
										+ msgHelp.getSWDMSTCCY() //Instrument Currency
										+ "','"
										+ msgHelp.getSWDMSTCUC() // Custodian Code
										+ "','"
										+ msgHelp.getSWDCUCNME() //Custodian Name
										+ "','"
										+ msgHelp.getSWDISIPTY() //Account Type
										+ "','"
										+ msgHelp.getSWDMSTVNO() //Nominal Value
										+ "','"
										+ msgHelp.getSWDMSTQTY() //Quantity
										+ "','"
										+ msgHelp.getSWDISICUS() //CUSIP
										+ "','"
										+ msgHelp.getSWDISISYM() //Symbol
										+ "','"
										+ msgHelp.getSWDISINUM() //ISIN
										+ "','"
										+ msgHelp.getSWDISIATY() //Accrual Type
										+ "','"
										+ msgHelp.getSWDISIIN1() //Last Interest Payment 1
										+ "','"
										+ msgHelp.getSWDISIIN2() //Last Interest Payment 2
										+ "','"
										+ msgHelp.getSWDISIIN3() //Last Interest Payment 3
										+ "')\">"   
										+ Util.fcolorCCY(msgHelp.getSWDMSTMKP()) //Market Price
										+ "</a></td>");		
								myRow.append("</TR>");
								
								
								beanList.addRow(myFlag, myRow.toString());

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("EWD0318Help", beanList);
						try {
							
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0318_inv_customer_helpmessage.jsp");
							callPage(
								LangPath + "EWD0318_inv_customer_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
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
		}

	}
}