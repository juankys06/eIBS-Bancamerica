package datapro.eibs.params;

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

public class JSEDL6010 extends datapro.eibs.master.SuperServlet {

	static final int R_LIST  	= 1;
	static final int R_ENTER  	= 11;
	
	static final int R_DETAIL  	= 100;
	static final int A_ACTION  	= 200;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL6010() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0156");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
	
			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
	
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL6010_ap_particip_enter.jsp");
			callPage(LangPath + "EDL6010_ap_particip_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL601001Message msgList = null;
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
		//userPO.setBank(req.getParameter("E01PARBNK"));

		// Send Initial data
		try {
			msgList = (EDL601001Message) mc.getMessageRecord("EDL601001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL601001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			/*
			try { 
				msgList.setE01PARBNK(userPO.getBank());
			} catch (Exception e) {
				
			}
			*/
				
			msgList.send();
			msgList.destroy();
			flexLog("EDL601001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			  newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EDL601001")) {

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

						msgList = (EDL601001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							userPO.setHeader10(msgList.getE01PARBNK());
							userPO.setHeader11(msgList.getE01PARPTY());
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
								+ " onclick=\"setParameters('"	+ msgList.getE01PARBNK() + "','" + msgList.getE01PARPTY() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"5%\">" + msgList.getE01PARBNK() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PARTYP() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"15%\">" + msgList.getE01PARCCY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PARPTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"15%\">" + Util.formatDate(msgList.getE01PARLD1(),msgList.getE01PARLD2(),msgList.getE01PARLD3())  + " - " + Util.formatTime(msgList.getE01PARLDT()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"15%\">" + msgList.getE01PARUSR() + "</td>");
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
					ses.setAttribute("partList", beanList);

					try {
						flexLog("About to call Page: " + LangPath + "EDL6010_ap_particip_list.jsp");
						callPage(LangPath + "EDL6010_ap_particip_list.jsp", req, res);
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
		EDL601001Message msgPart = null;
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
			msgPart = (EDL601001Message) mc.getMessageRecord("EDL601001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EDL601001");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0004");
			
			try{
				msgPart.setE01PARBNK(req.getParameter("Bank"));
			} catch (Exception e) {
			}	
	
			try{
				msgPart.setE01PARPTY(req.getParameter("Priority"));
			} catch (Exception e) {
			}	
	
			msgPart.send();
			msgPart.destroy();
			flexLog("EDL601001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EDL601001")) {
				try {
					msgPart = new EDL601001Message();
					flexLog("EDL601001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgPart = (EDL601001Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("participant", msgPart);
	
				try {
					flexLog("About to call Page: " + LangPath + "EDL6010_ap_particip_detail.jsp");
					callPage(LangPath + "EDL6010_ap_particip_detail.jsp", req, res);
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

	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL601001Message msgPart = null;
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
			msgPart = (EDL601001Message) mc.getMessageRecord("EDL601001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EDL6010");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");
			msgPart.setH01FLGWK1(option);
	
			try{
				msgPart.setE01PARBNK(req.getParameter("Bank"));
			} catch (Exception e) {
			}	
	
			try{
				msgPart.setE01PARPTY(req.getParameter("Priority"));
			} catch (Exception e) {
			}	

			mc.sendMessage(msgPart);
			msgPart.destroy();
			flexLog("EDL601001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("EDL601001")) {
				try {
					msgPart = new datapro.eibs.beans.EDL601001Message();
					flexLog("EDL601001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgPart = (EDL601001Message) newmessage;
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("participant", msgPart);
				ses.setAttribute("userPO", userPO);
	
				try {
					res.sendRedirect(super.srctx + 
										"/servlet/datapro.eibs.params.JSEDL6010?SCREEN=1" );
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
					case R_ENTER :
						procReqEnter(msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_DETAIL :
						procReqDetail(mc, msgUser, req, res, session);
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