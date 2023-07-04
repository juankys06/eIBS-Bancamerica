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

public class JSEWD0306P extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIST_BROKER 	= 1;
	protected static final int A_LIST_BROKER 	= 2;

	protected static final int R_LIST_ISSUERS 	= 3;
	protected static final int A_LIST_ISSUERS 	= 4;
	
	protected static final int R_LIST_CUSTODY 	= 5;
	protected static final int A_LIST_CUSTODY 	= 6;

	protected static final int R_ENTER_LIST = 100;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0306P() {
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListBroker(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0306DSMessage msgList = null;
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
			msgList = (EWD0306DSMessage) mc.getMessageRecord("EWD0306DS");

			// Search By Broker
			msgList.setSWDTYP("");
			
			String code = req.getParameter("CODES");
			String name = req.getParameter("NAMES");
			String typ = "";
			
			if (code != null && name != null) {
				
				if (code.trim().equals("")) {
					// Search By Name
					msgList.setRWDTYP("N");
					msgList.setSWDNME(name);
				}
				else {
					// Search By Code
					msgList.setRWDTYP("T");
					msgList.setSWDCOD(code);
				}

			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0306DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0306DS")) {

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

						msgList = (EWD0306DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDCOD()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDCOD() + "')\"></TD>");

							String sType = "";
							if (msgList.getSWDTYP().equals("1")){sType = "BOOTH";} 
							else if (msgList.getSWDTYP().equals("2")){sType = "BROKER";}
							else if (msgList.getSWDTYP().equals("3")){sType = "CUSTODIAN";}
							else if (msgList.getSWDTYP().equals("4")){sType = "ISSUER";}
							else if (msgList.getSWDTYP().equals("5")){sType = "INSURANCE COMPANY";}
							else if (msgList.getSWDTYP().equals("6")){sType = "LAWYER";}
							else if (msgList.getSWDTYP().equals("7")){sType = "INSURANCE AGENT";}
							else {sType = "BROKER";}

							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCOD() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + sType + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDPH1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDCTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDSTE() + "</td>");
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
					ses.setAttribute("EWD0306Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0306P_sel_broker.jsp");
						callPage(LangPath + "EWD0306P_sel_broker.jsp", req, res);
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

	protected void procReqListCustody(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0306DSMessage msgList = null;
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
			msgList = (EWD0306DSMessage) mc.getMessageRecord("EWD0306DS");

			// Search By Custody
			msgList.setSWDTYP("3");

			String code = req.getParameter("CODES");
			String name = req.getParameter("NAMES");
			String typ = "";
			
			if (code != null && name != null) {
				
				if (code.trim().equals("")) {
					// Search By Name
					msgList.setRWDTYP("N");
					msgList.setSWDNME(name);
				}
				else {
					// Search By Code
					msgList.setRWDTYP("T");
					msgList.setSWDCOD(code);
				}

			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0306DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0306DS")) {

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

						msgList = (EWD0306DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
												
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDCOD()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDCOD() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCOD() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDPH1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDCTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDSTE() + "</td>");
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
					ses.setAttribute("EWD0306Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0306P_sel_custody.jsp");
						callPage(LangPath + "EWD0306P_sel_custody.jsp", req, res);
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

	protected void procReqListIssuers(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0306DSMessage msgList = null;
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
			msgList = (EWD0306DSMessage) mc.getMessageRecord("EWD0306DS");

			// Search By Custody
			msgList.setSWDTYP("5");

			String code = req.getParameter("CODES");
			String name = req.getParameter("NAMES");
			String typ = "";
			
			if (code != null && name != null) {
				
				if (code.trim().equals("")) {
					// Search By Name
					msgList.setRWDTYP("N");
					msgList.setSWDNME(name);
				}
				else {
					// Search By Code
					msgList.setRWDTYP("T");
					msgList.setSWDCOD(code);
				}

			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0306DS Message Sent");
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

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIV0000?SCREEN=100");

			} else
				if (newmessage.getFormatName().equals("EWD0306DS")) {

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

						msgList = (EWD0306DSMessage) newmessage;

						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
												
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getSWDCOD()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getSWDCOD() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getSWDCOD() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDPH1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDCTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgList.getSWDSTE() + "</td>");
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
					ses.setAttribute("EWD0306Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0306P_sel_issuers.jsp");
						callPage(LangPath + "EWD0306P_sel_issuers.jsp", req, res);
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
			flexLog("About to call Page: " + LangPath + "EIV0000_enter_comm_search.jsp");
			callPage(LangPath + "EIV0000_enter_comm_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	protected void procActionListBroker(
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

		
		String code = req.getParameter("CODE");
		
		String codeNew = req.getParameter("CODEN");
		String type = req.getParameter("TYPE");
		
		String codeSearch = req.getParameter("CODES");
		String nameSearch = req.getParameter("NAMES");

		switch (inptOPT) {
			case 1 : //New
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=200" + "&CODE=" + codeNew + "&TYPE=" + type);
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=400" + "&CODE=" + code);
				break;
				
			case 3 : //Delete
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=600" + "&CODE=" + code);
				break;
				
			case 4 : //Inquiry
			   res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=800" + "&CODE=" + code);
				break;	
			case 5 : // Search	
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1" + "&CODES=" + codeSearch + "&NAMES=" + nameSearch);
		}
	}

	protected void procActionListCustody(
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

		
		String code = req.getParameter("CODE");
		
		String codeNew = req.getParameter("CODEN");

		String codeSearch = req.getParameter("CODES");
		String nameSearch = req.getParameter("NAMES");

		switch (inptOPT) {
			case 1 : //New
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=1000" + "&CODE=" + codeNew);
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=1200" + "&CODE=" + code);
				break;
				
			case 3 : //Delete
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=1400" + "&CODE=" + code);
				break;
				
			case 4 : //Inquiry
			   res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=1600" + "&CODE=" + code);
				break;	
				
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5" + "&CODES=" + codeSearch + "&NAMES=" + nameSearch);
		}
	}

	protected void procActionListIssuers(
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

		
		String code = req.getParameter("CODE");
		
		String codeNew = req.getParameter("CODEN");

		String codeSearch = req.getParameter("CODES");
		String nameSearch = req.getParameter("NAMES");

		switch (inptOPT) {
			case 1 : //New
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=2000" + "&CODE=" + codeNew);
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=2200" + "&CODE=" + code);
				break;
				
			case 3 : //Delete
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=2400" + "&CODE=" + code);
				break;
				
			case 4 : //Inquiry
			   res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0005?SCREEN=2600" + "&CODE=" + code);
				break;	
				
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=3" + "&CODES=" + codeSearch + "&NAMES=" + nameSearch);
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

			int screen = R_LIST_BROKER;

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
					case R_LIST_BROKER :
						procReqListBroker(mc, msgUser, req, res, session);
						break;
					case R_LIST_CUSTODY :
						procReqListCustody(mc, msgUser, req, res, session);
						break;	
					case R_ENTER_LIST :
						procReqEnterSearch(mc, msgUser, req, res, session);
						break;
					case R_LIST_ISSUERS :
						procReqListIssuers(mc, msgUser, req, res, session);
						break;
						// Actions
					case A_LIST_BROKER :
						procActionListBroker(mc, msgUser, req, res, session);
						break;
					case A_LIST_CUSTODY :
						procActionListCustody(mc, msgUser, req, res, session);
						break;	
					case A_LIST_ISSUERS :
						procActionListIssuers(mc, msgUser, req, res, session);
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