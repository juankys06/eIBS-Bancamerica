package datapro.eibs.params; 

/**
 * Insert the type's description here.
 * Creation date: (10/22/04 6:08:05 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEEJ0051 extends datapro.eibs.master.SuperServlet {

	static final int R_LIST  	= 1;
	static final int R_DETAIL  	= 100;
	static final int A_ACTION  	= 200;
	static final int R_HISTORY  = 300;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEEJ0051() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEEJ0051");

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
		EEJ005101Message msgList = null;
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
			
			
			msgList = (EEJ005101Message) mc.getMessageRecord("EEJ005101");
			
			try {
							msgList.setE01EJAREF(req.getParameter("E01EJAREF"));
								}
								catch (Exception e)	{
									msgList.setE01EJAREF("0");
								}
							
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EEJ0051");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			msgList.send();
			msgList.destroy();
			flexLog("EEJ005101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			  newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EEJ005101")) {

					beanList = new JBList();

					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (EEJ005101Message) newmessage;

						marker = msgList.getH01FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							userPO.setHeader10(msgList.getE01EJASUP());
							userPO.setHeader11(msgList.getE01EJAREF());
							userPO.setHeader12(msgList.getE01EJACUS());
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
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"5%\"><input type=\"radio\" name=\"ACCNUM\" value=\""
								+ indexRow	+ "\" "	+ chk
								+ " onclick=\"setParameters('"	+ msgList.getE01EJASUP() + "','" + msgList.getE01EJAREF()+ "','"+ msgList.getE01EJACUS() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJAUSR() +'-'+msgList.getE01EJACD1()+"</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJASUP()+'-'+msgList.getE01EJACD2() + "</td>");
		//					myRow.append("<TD NOWRAP  ALIGN=CENTER >" + Util.formatDate(msgList.getE01EJARD1(),msgList.getE01EJARD2(),msgList.getE01EJARD3())  + " - " + Util.formatTime(msgList.getE01EJARTI()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJAREF() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJARTE() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJACUN() + "</td>");							
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01CODPRD()+ "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJAOPN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT >" + msgList.getE01EJAACR() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT >" + msgList.getE01EJAACA() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJASTN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJATYN() + "</td>");
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
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("autList", beanList);

					try {
						flexLog("About to call Page: " + LangPath + "EEJ0051_special_autoriz_list.jsp");
						callPage(LangPath + "EEJ0051_special_autoriz_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procReqDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EEJ005101Message msgAut = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		int acctype = 0;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("DETAIL");
		
		// Send Initial data
		try {
			msgAut = (EEJ005101Message) mc.getMessageRecord("EEJ005101");
			msgAut.setH01USERID(user.getH01USR());
			msgAut.setH01PROGRM("EEJ0051");
			msgAut.setH01TIMSYS(getTimeStamp());
			msgAut.setH01SCRCOD("01");
			msgAut.setH01OPECOD("0004");
			
			try{
				msgAut.setE01EJASUP(req.getParameter("Sup"));
			} catch (Exception e) {
			}	
	
			try{
				msgAut.setE01EJAREF(req.getParameter("Ref"));
			} catch (Exception e) {
			}
	
			msgAut.send();
			msgAut.destroy();
			flexLog("EEJ005101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EEJ005101")) {
				try {
					msgAut = new EEJ005101Message();
					flexLog("EEJ005101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgAut = (EEJ005101Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("autoriz", msgAut);
	
				try {
					flexLog("About to call Page: " + LangPath + "EEJ0051_special_autoriz_detail.jsp");
					callPage(LangPath + "EEJ0051_special_autoriz_detail.jsp", req, res);
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
	
	/**
	 * This method was created by Orestes Garcia.
	 */

	protected void procReqHistory(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EEJ005102Message msgList = null;
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
			msgList = (EEJ005102Message) mc.getMessageRecord("EEJ005102");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EEJ0051");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			
			try{
				msgList.setE02EJLREF(req.getParameter("Ref"));
			} catch (Exception e) {
			}
			
			msgList.send();
			msgList.destroy();
			flexLog("EEJ005102 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
				newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EEJ005102")) {

					beanList = new JBList();

					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (EEJ005102Message) newmessage;

						marker = msgList.getH02FLGMAS();						
					
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE02EJLUSR()+'-'+ msgList.getE02EJLCD1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + Util.formatDate(msgList.getE02EJLRD1(),msgList.getE02EJLRD2(),msgList.getE02EJLRD3())  + " - " + Util.formatTime(msgList.getE02EJLRTI()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE02EJLSUP()+'-'+ msgList.getE02EJLCD2() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + Util.formatDate(msgList.getE02EJLAD1(),msgList.getE02EJLAD2(),msgList.getE02EJLAD3())  + " - " + Util.formatTime(msgList.getE02EJLATI()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT >" + msgList.getE02EJLACR() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT >" + msgList.getE02EJLACA() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE02EJLTYN() + "</td>");
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
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("hisList", beanList);

					try {
						flexLog("About to call Page: " + LangPath + "EEJ0051_special_autoriz_history.jsp");
						callPage(LangPath + "EEJ0051_special_autoriz_history.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EEJ005101Message msgAut = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		int acctype = 0;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String option = (String) req.getParameter("option");
				
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgAut = (EEJ005101Message) mc.getMessageRecord("EEJ005101");
			msgAut.setH01USERID(user.getH01USR());
			msgAut.setH01PROGRM("EEJ0051");
			msgAut.setH01TIMSYS(getTimeStamp());
			msgAut.setH01SCRCOD("01");
			msgAut.setH01OPECOD("0002");
			msgAut.setH01FLGWK1(option);
	
			try{
				msgAut.setE01EJASUP(req.getParameter("Sup"));
			} catch (Exception e) {
			}	
	
			try{
				msgAut.setE01EJAREF(req.getParameter("Ref"));
			} catch (Exception e) {
			}
			
			if (option.equals("R")) {
				msgAut.setE01EJARM1(req.getParameter("E01EJARM1"));
				msgAut.setE01EJARM2(req.getParameter("E01EJARM2"));
				msgAut.setE01EJARM3(req.getParameter("E01EJARM3"));
			}
			
			msgAut.send();
			msgAut.destroy();
			flexLog("EEJ005101 Message Sent");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EEJ005101")) {
				try {
					msgAut = new datapro.eibs.beans.EEJ005101Message();
					flexLog("EEJ005101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgAut = (EEJ005101Message) newmessage;
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("autoriz", msgAut);
				ses.setAttribute("userPO", userPO);
				
				if ((IsNotError)|| (msgError.getERNU01().equals("0273"))|| (msgError.getERNU01().equals("0834"))) {
				
					try {
						res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.params.JSEEJ0051?SCREEN=1" );
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "EEJ0051_special_autoriz_list.jsp");
						callPage(LangPath + "EEJ0051_special_autoriz_list.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
					s = new Socket(super.hostIP, super.iniSocket + 1);
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
					case R_DETAIL :
						procReqDetail(mc, msgUser, req, res, session);
						break;
					case R_HISTORY :
						procReqHistory(mc, msgUser, req, res, session);
						break;
					// Actions
					case A_ACTION :
						procActionApproval(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
			   } catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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