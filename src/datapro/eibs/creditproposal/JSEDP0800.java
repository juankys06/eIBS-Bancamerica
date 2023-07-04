package datapro.eibs.creditproposal;

/** 
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla???????????????????????????
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDP0800 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	protected static final int R_GET_CUSTOMER = 100;
	// second screen. list of deals 
	protected static final int R_GET_LIST = 200;
	// THIRD screen. NEW 
	protected static final int R_GET_NEW = 300;
	// GET RECORD
	protected static final int R_GET_REC = 400;
	// MAINTENANCE
	protected static final int A_MAINT = 500;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0800() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	
	// first screen list of formats
	protected void procGetCustomer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP073501Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0800_customer_enter.jsp");
							callPage(
								LangPath + "EDP0800_customer_enter.jsp",
								req,
								res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	}
	
//	list
	 protected void procGetList(
		 MessageContext mc,
		 ESS0030DSMessage user,
		 HttpServletRequest req,
		 HttpServletResponse res,
		 HttpSession ses)
		 throws ServletException, IOException {

		 MessageRecord newmessage = null;
		 EDP080001Message msgList = null;
		 ELEERRMessage msgError = null;
		 UserPos userPO = null;
		 boolean IsNotError = false;

		 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if(req.getParameter("E01DUECUN")!=null){
				userPO.setCusNum(req.getParameter("E01DUECUN"));
			}
		if(req.getParameter("E01DUENA1")!=null){
				   userPO.setCusName(req.getParameter("E01DUENA1"));
			}
		if(userPO.getOption().equals("1")){
			userPO.setPurpose("MAINTENANCE");
		} 
		if(req.getParameter("OPTION")!=null){
			if(req.getParameter("OPTION").equals("1")){
				userPO.setOption(req.getParameter("OPTION"));
				userPO.setPurpose("MAINTENANCE");
			} else {
				userPO.setPurpose("INQUIRY");
				}
			} 

			// Send Initial data
			try {
				msgList = (EDP080001Message) mc.getMessageRecord("EDP080001");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("EDP0800");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0015");
				msgList.setE01DUECUN(userPO.getCusNum());
			msgList.send();
				msgList.destroy();
				flexLog("EDP080001 Message Sent");
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

					showERROR(msgError);

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e + newmessage);
				throw new RuntimeException("Socket Communication Error Receiving");
			}

			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDP080001")) {

					JBObjList beanList = new JBObjList();

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
					int compar = 0;
					int indexRow = 0;
					int ct = 0;
					while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
						}

						msgList = (EDP080001Message) newmessage;

						marker = msgList.getE01OPECDE();

						if (firstTime) {
							firstTime = false;
							chk = "checked";
							if(req.getParameter("E01DUENA1")!=null){
									   userPO.setCusName(msgList.getE01DUENA1());
								}
						} else {
							chk = "";
						}

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {

							beanList.addRow(msgList);

							if (marker.equals("+")) {
								beanList.setShowNext(true);

								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

				 flexLog("Putting java beans into the session");
				 ses.setAttribute("EDP080001Help", beanList);
				 ses.setAttribute("userPO", userPO);

				 try {

						 if (msgError.getERRNUM().equals("0")) {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0800_entity_list.jsp");
							 callPage(
								 LangPath + "EDP0800_entity_list.jsp",
								 req,
								 res);
						 } else {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0800_customer_enter.jsp");

							 callPage(
								 LangPath + "EDP0800_customer_enter.jsp",
								 req,
								 res);

						 }
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

	protected void procReqNew(
	 MessageContext mc,
	 ESS0030DSMessage user,
	 HttpServletRequest req,
	 HttpServletResponse res,
	 HttpSession ses)
	 throws ServletException, IOException {

	 MessageRecord newmessage = null;
	 ELEERRMessage msgError = null;
	 EDP080001Message msgRT = null;
	 UserPos userPO = null;
	 boolean IsNotError = false;

	 try {
		 msgError = new ELEERRMessage();
	 } catch (Exception ex) {
		 flexLog("Error: " + ex);
	 }

	 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	 userPO.setPurpose("NEW");
	 ses.setAttribute("userPO", userPO);

	 msgRT = new datapro.eibs.beans.EDP080001Message();
	
	 ses.setAttribute("brnDetails", msgRT);
	 try {
		 flexLog(
			 "About to call Page: "
				 + LangPath
				 + "EDP0800_customer_loan_maintenance.jsp");
		 callPage(LangPath + "EDP0800_customer_loan_maintenance.jsp", req, res);
	 } catch (Exception e) {
		 flexLog("Exception calling page " + e);
	 }

 }

 protected void procMaint(
		 MessageContext mc,
		 ESS0030DSMessage user,
		 HttpServletRequest req,
		 HttpServletResponse res,
		 HttpSession ses)
		 throws ServletException, IOException {

		 MessageRecord newmessage = null;
		 EDP080001Message msgRT = null;
		 ELEERRMessage msgError = null;
		 UserPos userPO = null;
		 boolean IsNotError = false;
		 int acctype = 0;

		 try {
			 msgError = new datapro.eibs.beans.ELEERRMessage();
		 } catch (Exception ex) {
			 flexLog("Error: " + ex);
		 }

		 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		 // Send Initial data
		 try {
			 flexLog("Send Initial Data");
			 msgRT = (EDP080001Message) ses.getAttribute("brnDetails");
			 msgRT.setH01USERID(user.getH01USR());
			 msgRT.setH01PROGRM("EDP0800");
			 msgRT.setH01TIMSYS(getTimeStamp());
			 msgRT.setH01SCRCOD("01");
			if(req.getParameter("E01DUECUN")!=null){
				msgRT.setE01DUECUN(req.getParameter("E01DUECUN"));
				}
			if (userPO.getPurpose().equals("NEW")) {
				msgRT.setH01OPECOD("0001");
				}
			if (userPO.getPurpose().equals("MAINTENANCE")) {
				msgRT.setH01OPECOD("0005");
				}
			if (userPO.getPurpose().equals("DELETE")) {
				msgRT.setH01OPECOD("0010");
				}
	
			 // all the fields here
			 java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			 mc.sendMessage(msgRT);
			 msgRT.destroy();
			 flexLog("EDP080001 Message Sent");
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

			 if (newmessage.getFormatName().equals("EDP080001")) {
				 try {
					 msgRT = new datapro.eibs.beans.EDP080001Message();
					 flexLog("EDP080001 Message Received");
				 } catch (Exception ex) {
					 flexLog("Error: " + ex);
				 }

				 msgRT = (EDP080001Message) newmessage;

				 flexLog("Putting java beans into the session");
				 ses.setAttribute("error", msgError);
				 ses.setAttribute("brnDetails", msgRT);
				 ses.setAttribute("userPO", userPO);

				 //
					 if (IsNotError) { // There are no errors
						 res.sendRedirect(
							 super.srctx
								 + "/servlet/datapro.eibs.creditproposal.JSEDP0800?SCREEN=200");
					 } else { // There are errors
						 try {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0800_customer_loan_maintenance.jsp");
							 callPage(
								 LangPath + "EDP0800_customer_loan_maintenance.jsp",
								 req,
								 res);
						 } catch (Exception e) {
							 flexLog("Exception calling page " + e);
						 }

					 }

				 //				
			 } else
				 flexLog("Message " + newmessage.getFormatName() + " received.");

		 } catch (Exception e) {
			 e.printStackTrace();
			 flexLog("Error: " + e);
			 throw new RuntimeException("Socket Communication Error");
		 }
	 }

	protected void procGetRec(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	EDP080001Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	if(req.getParameter("PURPOSE")!=null){
		userPO.setPurpose(req.getParameter("PURPOSE"));
		}

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP080001Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP080001Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0800_customer_loan_maintenance.jsp");
				callPage(
					LangPath + "EDP0800_customer_loan_maintenance.jsp",
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

			int screen = R_GET_CUSTOMER;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

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

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {

						//Request
						// First screen, only list data
						case R_GET_CUSTOMER :
							procGetCustomer(mc, msgUser, req, res, session);
							break;
						// Second screen, only list data
						case R_GET_LIST :
							procGetList(mc, msgUser, req, res, session);
							break;
						// Second screen, only list data
						case R_GET_NEW :
							procReqNew(mc, msgUser, req, res, session);
							break;
						// Add record
						case A_MAINT :
							procMaint(mc, msgUser, req, res, session);
							break;
						// GET RECORD
						case R_GET_REC :
							procGetRec(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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
