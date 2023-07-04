package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (08/31/04 11:52:30 AM)
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

public class JSEEJ0010 extends datapro.eibs.master.SuperServlet {

	static final int R_LIST  	= 1;
	static final int R_DETAIL  	= 100;
	static final int A_ACTION  	= 200;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEEJ0010() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEEJ0010");

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
		EEJ001001Message msgList = null;
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
			msgList = (EEJ001001Message) mc.getMessageRecord("EEJ001001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EEJ0010");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			msgList.send();
			msgList.destroy();
			flexLog("EEJ001001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			  newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EEJ001001")) {

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

						msgList = (EEJ001001Message) newmessage;

						marker = msgList.getH01FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							userPO.setHeader10(msgList.getE01EJLGRT());
							userPO.setHeader11(msgList.getE01EJLGRC());
							userPO.setHeader12(msgList.getE01EJLJRQ());
							userPO.setHeader13(msgList.getE01EJLCCY());
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
								+ " onclick=\"setParameters('"	+ msgList.getE01EJLGRT() + "','" + msgList.getE01EJLGRC() + "','" + msgList.getE01EJLJRQ() + "','" + msgList.getE01EJLCCY() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJLGRT() + " - " + msgList.getE01EJLGRN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJLGRC() + " - " + msgList.getE01EJLGRM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJLJRQ() + " - " + msgList.getE01EJLJRN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + msgList.getE01EJLCCY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJLSTS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER >" + Util.formatDate(msgList.getE01EJLLD1(),msgList.getE01EJLLD2(),msgList.getE01EJLLD3())  + " - " + Util.formatTime(msgList.getE01EJLLDT()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT >" + msgList.getE01EJLUSR() + "</td>");
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
					ses.setAttribute("clasList", beanList);

					try {
						flexLog("About to call Page: " + LangPath + "EEJ0010_params_approv_limits_list.jsp");
						callPage(LangPath + "EEJ0010_params_approv_limits_list.jsp", req, res);
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
		EEJ001001Message msgClas = null;
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
			msgClas = (EEJ001001Message) mc.getMessageRecord("EEJ001001");
			msgClas.setH01USERID(user.getH01USR());
			msgClas.setH01PROGRM("EEJ0010");
			msgClas.setH01TIMSYS(getTimeStamp());
			msgClas.setH01SCRCOD("01");
			msgClas.setH01OPECOD("0004");
			
			try{
				msgClas.setE01EJLGRT(req.getParameter("GroupType"));
			} catch (Exception e) {
			}	
	
			try{
				msgClas.setE01EJLGRC(req.getParameter("GroupCode"));
			} catch (Exception e) {
			}
						
			try{
				msgClas.setE01EJLJRQ(req.getParameter("Jerar"));
			} catch (Exception e) {
			}	
	
			try{
				msgClas.setE01EJLCCY(req.getParameter("Curren"));
			} catch (Exception e) {
			}
			
			msgClas.send();
			msgClas.destroy();
			flexLog("EEJ001001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EEJ001001")) {
				try {
					msgClas = new EEJ001001Message();
					flexLog("EEJ001001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgClas = (EEJ001001Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("clasific", msgClas);
	
				try {
					flexLog("About to call Page: " + LangPath + "EEJ0010_params_approv_limits_detail.jsp");
					callPage(LangPath + "EEJ0010_params_approv_limits_detail.jsp", req, res);
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
		EEJ001001Message msgClas = null;
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
			msgClas = (EEJ001001Message) mc.getMessageRecord("EEJ001001");
			msgClas.setH01USERID(user.getH01USR());
			msgClas.setH01PROGRM("EEJ0010");
			msgClas.setH01TIMSYS(getTimeStamp());
			msgClas.setH01SCRCOD("01");
			msgClas.setH01OPECOD("0002");
			msgClas.setH01FLGWK1(option);
	
			try{
				msgClas.setE01EJLGRT(req.getParameter("GroupType"));
			} catch (Exception e) {
			}	
	
			try{
				msgClas.setE01EJLGRC(req.getParameter("GroupCode"));
			} catch (Exception e) {
			}
						
			try{
				msgClas.setE01EJLJRQ(req.getParameter("Jerar"));
			} catch (Exception e) {
			}	
	
			try{
				msgClas.setE01EJLCCY(req.getParameter("Curren"));
			} catch (Exception e) {
			}
			
			mc.sendMessage(msgClas);
			msgClas.destroy();
			flexLog("EEJ001001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("EEJ001001")) {
				try {
					msgClas = new datapro.eibs.beans.EEJ001001Message();
					flexLog("EEJ001001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgClas = (EEJ001001Message) newmessage;
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("clasific", msgClas);
				ses.setAttribute("userPO", userPO);
	
				try {
					res.sendRedirect(super.srctx + 
										"/servlet/datapro.eibs.params.JSEEJ0010?SCREEN=1" );
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