package datapro.eibs.products;

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

public class JSEDD5220 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_RT = 100;	
	protected static final int R_LIST = 200;
	protected static final int A_LIST = 300;
	protected static final int R_SET_OPT = 400;
	protected static final int R_ENTER_SV = 500;	
	
	protected static final int R_MAINT = 1;
	protected static final int A_MAINT = 2;
	

	protected String LangPath = "S";

	/**
	 * JSEDD5220 constructor comment.
	 */
	public JSEDD5220() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD5220");

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
		EDD522001Message msgVG = null;
		JBObjList chgList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		int row =0;
		String opt="";
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception ex) {
			row = 0;
		}
		
		try {
			opt = req.getParameter("opt");
		} catch (Exception ex) {
			opt = "N";
		}
		
		try {
			flexLog("Send Initial Data");
			
			if (opt.equals("D")) {
				chgList = (JBObjList) ses.getAttribute("chgList");
    			chgList.setCurrentRow(row);	 
				msgVG = (EDD522001Message) chgList.getRecord();
				msgVG.setH01OPECOD("0009");	
			} else {
				msgVG = new EDD522001Message();
				msgVG.setH01OPECOD("0001");
				msgVG.setE01CFMTTP(req.getParameter("E01CFMTTP"));
			}
						
			msgVG.setH01USERID(user.getH01USR());
			msgVG.setH01PROGRM("EDD5220");
			msgVG.setH01TIMSYS(getTimeStamp());
			msgVG.setH01SCRCOD("01");
						
			mc.sendMessage(msgVG);
			msgVG.destroy();

			flexLog("EDD522001 Message Sent");
		
		// Receive Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
			}
			
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD522001")) {

				msgVG = (EDD522001Message) newmessage;
			}
			
			if (opt.equals("D")) { 
				if (msgError.getERRNUM().equals("0")) {
					chgList.removeRow(row);
					ses.setAttribute("chgList", chgList);
				}				 
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
						flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD5220_chg_list.jsp");
						callPage(
						LangPath + "EDD5220_chg_list.jsp",
						req,
						res);

				} catch (Exception e) {
						flexLog("Exception calling page " + e);
				}
			} else { //new
				ses.setAttribute("chgBasic", msgVG);
				ses.setAttribute("userPO", userPO);
				userPO.setPurpose("NEW");
				flexLog("About to call Page: " + LangPath + "EDD5220_chg_maint.jsp");
				callPage(LangPath + "EDD5220_chg_maint.jsp", req, res);				
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
		EDD522001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBObjList beanList =  null;
		UserPos userPO = null;
		
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgPR = (EDD522001Message)ses.getAttribute("cdRate");
			msgPR = (EDD522001Message) mc.getMessageRecord("EDD522001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD5220");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD("0015");
			try {
				msgPR.setE01CFMACC(req.getParameter("E01CFMACC"));
				userPO.setAccNum(req.getParameter("E01CFMACC"));
			} catch (Exception e){
				msgPR.setE01CFMACC("0");
			}
			
			msgPR.send();
			msgPR.destroy();
			flexLog("EDD522001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD522001")) {
				
				msgPR = (EDD522001Message) newmessage;
				beanList =  new JBObjList();
				String marker = ""; 
				int ct = 0;
                while (true) {
					msgPR = (EDD522001Message)newmessage;
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
					ses.setAttribute("rtHeader",msgPR);
					ses.setAttribute("chgList", beanList);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD5220_chg_list.jsp");
						callPage(LangPath + "EDD5220_chg_list.jsp", req, res);
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

			int screen = R_ENTER_RT;

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
				
				if (screen == R_ENTER_RT || screen == R_ENTER_SV) {
					
					procReqEnterMaint(msgUser, req, res, session, screen);
									
				} else if (screen == R_SET_OPT) {
					
					procSetOption(msgUser, req, res, session);
					
				} else { 
						
				  try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
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
				  } finally {
					s.close();
				  }
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
		EDD522001Message msgPR = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		UserPos userPO = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		//String opcode = (userPO.getPurpose().equals("NEW")) ? "0001" : "0005";
		String opcode = "0005";
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPR = (EDD522001Message) ses.getAttribute("chgBasic");
			//msgPR = (EDD522001Message)mc.getMessageRecord("EDD522001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EDD5220");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD(opcode);
			
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

			if (newmessage.getFormatName().equals("EDD522001")) {
				
				msgPR = (EDD522001Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("chgBasic", msgPR);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD5220_chg_confirm.jsp");
						callPage(LangPath + "EDD5220_chg_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD5220_chg_maint.jsp");
						callPage(LangPath + "EDD5220_chg_maint.jsp",req,res);
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
		HttpSession ses,
		int screen)
		throws ServletException, IOException {

		UserPos	userPO = null;
		ELEERRMessage msgError = null;


		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			if (screen == 100) {
				userPO.setOption("RT_CHG");
				userPO.setProdCode("RA");
			} else {
				userPO.setOption("SV_CHG");
				userPO.setProdCode("04");
			}
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEDD5220?SCREEN=200");
			
			//Others Parameters
			userPO.setHeader1("E01CFMACC");
			userPO.setHeader2("E01CFMNUM");
			
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
		EDD522001Message chgBasic = null;
		EDD522001Message chgHeader = null;
		JBObjList chgList = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (UserPos) ses.getAttribute("userPO");
			
 			int row = 0;
 			if (req.getParameter("opt").equals("M")) {	
 				row = Integer.parseInt(req.getParameter("ROW"));
 				chgList = (JBObjList) ses.getAttribute("chgList");
    			chgList.setCurrentRow(row);	 
				chgBasic = (EDD522001Message) chgList.getRecord();
				userPO.setPurpose("MAINTENANCE");						 
 			} else {
 				chgHeader = (EDD522001Message) ses.getAttribute("rtHeader");
 				chgBasic = new EDD522001Message();
 				chgBasic.setE01CFMACC(chgHeader.getE01CFMACC());				
 				userPO.setPurpose("NEW");
 			}
			ses.setAttribute("chgBasic", chgBasic);			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD5220_chg_maint.jsp");
			callPage(LangPath + "EDD5220_chg_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		

	}
}