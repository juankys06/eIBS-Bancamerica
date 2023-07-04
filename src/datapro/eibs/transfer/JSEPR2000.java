package datapro.eibs.transfer;

/**
 * Insert the type's description here.
 * Creation date: (7/03/03 6:08:55 PM)
 * @author: Ramses
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSEPR2000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_MAINT = 100;	
	protected static final int R_LIST = 200;
	protected static final int A_LIST = 300;
	protected static final int R_SET_OPT = 400;
	
	protected static final int R_MAINT = 1;
	protected static final int A_MAINT = 2;
	
	protected static final int R_ENTER_INQ = 500;
	protected static final int A_ENTER_INQ = 600;
	
	protected String LangPath = "S";

	/**
	 * JSEPR2000 constructor comment.
	 */
	public JSEPR2000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPR2000");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EPR200001Message msgVG = null;
		JBObjList prList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		int row =0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception ex) {
			row = 0;
		}
		
		try {
			flexLog("Send Initial Data");
			
			prList = (JBObjList) ses.getAttribute("prList");
    		prList.setCurrentRow(row);	 
			msgVG = (EPR200001Message) prList.getRecord();	
			msgVG.setH01USERID(user.getH01USR());
			msgVG.setH01PROGRM("EPR0000");
			msgVG.setH01TIMSYS(getTimeStamp());
			msgVG.setH01SCRCOD("01");
			msgVG.setH01OPECOD("0009");
			
			mc.sendMessage(msgVG);
			msgVG.destroy();

			flexLog("EPR200001 Message Sent");
		
		// Receive Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
			}
			
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR200001")) {

				msgVG = (EPR200001Message) newmessage;
			}
			
			flexLog("Putting java beans into the session");
			prList.setRecord(msgVG,row);
			ses.setAttribute("prList", prList);
			ses.setAttribute("error", msgError);
			try {
						flexLog(
						"About to call Page: "
							+ LangPath
							+ "EPR2000_pr_list.jsp");
						callPage(
						LangPath + "EPR2000_pr_list.jsp",
						req,
						res);

				} catch (Exception e) {
						flexLog("Exception calling page " + e);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR200001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBObjList beanList =  null;
		UserPos userPO = null;
		
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EPR200001Message)ses.getAttribute("cdRate");
			msgPR = (EPR200001Message) mc.getMessageRecord("EPR200001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EPR2000");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0015");
			try {
				msgPR.setE01PRPDAC(req.getParameter("E01PRPDAC"));
				userPO.setAccNum(req.getParameter("E01PRPDAC"));
			} catch (Exception e){
				msgPR.setE01PRPDAC("0");
			}
			
			msgPR.send();
			msgPR.destroy();
			flexLog("EPR200001 Message Sent");
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
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EPR200001")) {
				
				msgPR = (EPR200001Message) newmessage;
				beanList =  new JBObjList();
				String marker = ""; 
				while (true) {
					
					msgPR = (EPR200001Message)newmessage;
					marker = msgPR.getH01FLGWK3();
					msgPR.setHandler(null);
					if (marker.equals("*")) {					
						break;
					}
					else {
						beanList.addRow(msgPR);
					}
					newmessage = mc.receiveMessage();
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				if (IsNotError) { // There are no errors
					ses.setAttribute("prHeader",msgPR);
					ses.setAttribute("prList", beanList);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPR2000_pr_list.jsp");
						callPage(LangPath + "EPR2000_pr_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */
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

			int screen = R_ENTER_MAINT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				
				try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
						flexLog("Screen set to default value");
				}
				
				switch (screen) {
				case R_ENTER_MAINT :					
					procReqEnterMaint(msgUser, req, res, session);
					break;
									
				case R_SET_OPT :					
					procSetOption(msgUser, req, res, session);
					break;
				
				case R_ENTER_INQ :					
					procReqEnterInq(msgUser, req, res, session);
					break;	
					
				default : 
						
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

					

					switch (screen) {
						
						case A_MAINT :
							procActionMaint(mc, msgUser, req, res, session);
							break;
						case A_LIST :
							procActionList(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc,msgUser,req,res,session);
							break;
						case A_ENTER_INQ :
							procActionEnterInq(mc, msgUser, req, res, session);
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
				  } finally {
					s.close();
				  }
				  break;
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR200001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPR = (EPR200001Message) ses.getAttribute("prBasic");
			//msgPR = (EPR200001Message)mc.getMessageRecord("EPR200001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EPR2000");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0005");
			
			// all the fields here
			java.util.Enumeration enu = msgPR.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgPR.send();
			mc.sendMessage(msgPR);
			msgPR.destroy();
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");


		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR200001")) {
				
				msgPR = (EPR200001Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPR2000_pr_confirm.jsp");
						callPage(LangPath + "EPR2000_pr_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPR2000_pr_maint.jsp");
						callPage(LangPath + "EPR2000_pr_maint.jsp",req,res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos	userPO = null;
		ELEERRMessage msgError = null;


		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("PR_OP");
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.transfer.JSEPR2000?SCREEN=200");
			userPO.setProdCode("RA");
			//Others Parameters
			userPO.setHeader1("E01PRPDAC");
			userPO.setHeader2("E01PRPNUM");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		

	}
    /**
	 * This method was created in VisualAge.
	 */
	protected void procSetOption(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos	userPO = null;
		ELEERRMessage msgError = null;
		EPR200001Message prBasic = null;
		EPR200001Message prHeader = null;
		JBObjList prList = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (UserPos) ses.getAttribute("userPO");
			
 			int row = 0;
 			if (req.getParameter("opt").equals("M") || req.getParameter("opt").equals("I")) {	
 				row = Integer.parseInt(req.getParameter("ROW"));
 				prList = (JBObjList) ses.getAttribute("prList");
    			prList.setCurrentRow(row);	 
				prBasic = (EPR200001Message) prList.getRecord();
				if (req.getParameter("opt").equals("M")) userPO.setPurpose("MAINTENANCE"); else userPO.setPurpose("INQUIRY");						 
 			} else {
 				prHeader = (EPR200001Message) ses.getAttribute("prHeader");
 				prBasic = new EPR200001Message();
 				prBasic.setE01PRPDBK(prHeader.getE01PRPDBK());
 				prBasic.setE01PRPDBR(prHeader.getE01PRPDBR());
 				prBasic.setE01PRPDCY(prHeader.getE01PRPDCY());
 				prBasic.setE01PRPDGL(prHeader.getE01PRPDGL());
 				prBasic.setE01PRPDSA(prHeader.getE01PRPDSA());
 				prBasic.setE01PRPDCC(prHeader.getE01PRPDCC());
 				userPO.setPurpose("NEW");
 			}
			ses.setAttribute("prBasic", prBasic);			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			if (req.getParameter("opt").equals("I")) {
				flexLog("About to call Page: " + LangPath + "EPR2000_pr_inquiry.jsp");
				callPage(LangPath + "EPR2000_pr_inquiry.jsp", req, res);
			} else {
				flexLog("About to call Page: " + LangPath + "EPR2000_pr_maint.jsp");
				callPage(LangPath + "EPR2000_pr_maint.jsp", req, res);
			}
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterInq(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos	userPO = null;
		ELEERRMessage msgError = null;


		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("CC");
			userPO.setPurpose("INQUIRY");
			userPO.setRedirect("/servlet/datapro.eibs.transfer.JSEPR2000?SCREEN=600");
			userPO.setProdCode("RA");
			//Others Parameters
			userPO.setHeader1("E02SELACC");
			userPO.setHeader2("E02PRPNUM");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR200002Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBObjList beanList =  null;
		UserPos userPO = null;
		
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EPR200001Message)ses.getAttribute("cdRate");
			msgPR = (EPR200002Message) mc.getMessageRecord("EPR200002");
			msgPR.setH02USERID(user.getH01USR());
			msgPR.setH02PROGRM("EPR2000");
			msgPR.setH02TIMSYS(getTimeStamp());
			msgPR.setH02SCRCOD("01");
			msgPR.setH02OPECOD("0015");
			try {
				msgPR.setE02SELACC(req.getParameter("E02SELACC"));
				userPO.setAccNum(req.getParameter("E02SELACC"));
			} catch (Exception e){
				msgPR.setE02SELACC("0");
			}
			
			msgPR.send();
			msgPR.destroy();
			flexLog("EPR200002 Message Sent");
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		
		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR200002")) {
				
				msgPR = (EPR200002Message) newmessage;
				beanList =  new JBObjList();
				String marker = ""; 
				while (true) {
	
					msgPR = (EPR200002Message)newmessage;
					marker = msgPR.getH02FLGWK3();
					msgPR.setHandler(null);
					if (marker.equals("*")) {					
						break;
					}
					else {
						beanList.addRow(msgPR);
					}
					newmessage = mc.receiveMessage();
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				if (IsNotError) { // There are no errors
					ses.setAttribute("prHeader",msgPR);
					ses.setAttribute("prList", beanList);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EPR2000_pr_deb_list.jsp");
						callPage(LangPath + "EPR2000_pr_deb_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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


}