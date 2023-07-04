package datapro.eibs.forex;

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

public class JSEWD0325BO extends datapro.eibs.master.SuperServlet {

	// BackOffice list for Maintenance
	static final int R_LIST = 1;
	static final int A_LIST = 2;
	static final int R_ENTER_LIST = 100;
	
	private String LangPath = "S";

	/**
	 * JSEWD0325BO constructor comment.
	 */
	public JSEWD0325BO() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0325BO");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	private void procReqPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0325DSMessage msgList = null;
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
			msgList = (EWD0325DSMessage) mc.getMessageRecord("EWD0325DS");

			try { // Search By Product
				msgList.setSWDTYP(req.getParameter("PRODUCT").toUpperCase());
			} catch (Exception e) {
				msgList.setSWDTYP("");
			}

			try { // Search By Reference Number
				msgList.setSWDREF(req.getParameter("REFERENCE"));
			} catch (Exception e) {
				msgList.setSWDREF("0");
			}

				if (!req.getParameter("REFERENCE").equals("")){
					try {
						msgList.setSWDDID(req.getParameter("DEAID").toUpperCase());
					} catch (Exception e) {
						msgList.setSWDDID("");
					}						
					try {
						msgList.setSWDTYP(req.getParameter("TYPE").toUpperCase());
					} catch (Exception e) {
						msgList.setSWDTYP("");
					}
				}
			
			try { // Search By Customer Number
				msgList.setSWDCUN(req.getParameter("CUSTOMER"));
			} catch (Exception e) {
				msgList.setSWDCUN("0");
			}
			
			try { // Search By Currency
				msgList.setSWDCCY(req.getParameter("CURRENCY").toUpperCase());
			} catch (Exception e) {
				msgList.setSWDCCY("");
			}
			
			try { // Search By Amount 
				msgList.setSWDAMN(req.getParameter("AMOUNT1"));
			} catch (Exception e) {
				msgList.setSWDAMN("0");
			}
			
			try { // Flag Amount (to be revisited)
				msgList.setSWDSBT(req.getParameter("FLAG"));
			} catch (Exception e) {
				msgList.setSWDSBT("");
			}

			try { 
				msgList.setRWDTYP("B");
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}

			try { 
				msgList.setRWDUSR(user.getH01USR());
			} catch (Exception e) {
				msgList.setRWDUSR("");
			}

			msgList.send();
			msgList.destroy();
			flexLog("EWD0325DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1100");

			} else
				if (newmessage.getFormatName().equals("EWD0325DS")) {

					try {
						beanList =
							(datapro.eibs.beans.JBList) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.JBList");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime;
					String marker = "";
					String myFlag = "";
					String myRow = "";
					String chk = "";
					String custNum = req.getParameter("cust");
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					if (custNum == null)
						firstTime = true;
					else
						firstTime = false;
					int indexRow = 0;
					while (true) {

						msgList = (EWD0325DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								chk = "checked";
							} else {
								chk = "";
							}

							myRow = "<TR>";
							myRow += "<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"REF\" value=\""
								+ msgList.getSWDREF()
								+ "\" "
								+ chk
								+ " onclick=\"getValor('"
								+ msgList.getSWDREF()
								+ "', '"
								+ msgList.getSWDDID()
								+ "', '"
								+ msgList.getSWDSBT()
								+ "', '"
								+ msgList.getSWDACD()
								+ "')\"></TD>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDREF() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDDSC() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDTYP() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCCY() + "</td>";
							myRow += "<TD NOWRAP  ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgList.getSWDAMN())
								+ "</td>";
							myRow += "<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDDID() + "</td>";
							myRow += "</TR>";
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
					ses.setAttribute("EWD0325Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0325BO_sel.jsp");
						callPage(LangPath + "EWD0325BO_sel.jsp", req, res);
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
	
	private void procReqEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog("About to call Page: " + LangPath + "EFE0000B_enter_search.jsp");
			callPage(LangPath + "EFE0000B_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	private void procActionPos(
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

		String RefNumb = req.getParameter("ref");
		String DeaID = req.getParameter("dealer");
		String TYPE = req.getParameter("typ");
		String Bank = user.getE01UBK();
		String actionTaken = req.getParameter("actiontaken");
		if (actionTaken == null) actionTaken = "";
		
		switch (inptOPT) {
			case 2 : //Maintenance
			    if(TYPE.equals("31") || TYPE.equals("32")||
			       TYPE.equals("33") || TYPE.trim().equals("34")||
			       TYPE.equals("SPOT") || TYPE.equals("FWRD")){ //Foreign Exchange
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=40&E01FESREF="
						+ RefNumb
						+ "&E01FESDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("12") || TYPE.equals("CDP")){ //Certificate
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1000&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("11") || TYPE.equals("TDP")){ //Time Deposits
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1200&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("15") || TYPE.equals("FFP")){ //Federal Funds
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1400&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("TPS") ){ //Placements
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1600&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }			    
			    else if(TYPE.equals("13") || TYPE.equals("PLS")){ //Commercial Papers
			    	if (actionTaken.equals("PU")) {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.forex.JSEDL0105B?SCREEN=1000&E02DEAREF="
								+ RefNumb
								+ "&E02DLSUSR="
								+ DeaID);			    	
			    	} else {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.forex.JSEDL0108B?SCREEN=1000&E02DLSREF="
								+ RefNumb
								+ "&E02DLSUSR="
								+ DeaID);			    		
			    	}
			    }
			    else if(TYPE.equals("14") || TYPE.equals("ACS") || TYPE.equals("ACP")){ //Acceptances
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1800&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("35")){ //Forward Rate Agreements
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSETR0130?SCREEN=100&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }
			    else if(TYPE.equals("MMP")|| TYPE.equals("95")){ //Placements
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=2000&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID);
			    }					    
				break;
				
			case 3 : //Delete
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=400&E01FESREF="
						+ RefNumb
						+ "&E01FESDID="
						+ DeaID);
				break;
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=FSP "
						+ "&E01FESREF="
						+ RefNumb);
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
						procReqPos(mc, msgUser, req, res, session);
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
	private void showERROR(ELEERRMessage m) {
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