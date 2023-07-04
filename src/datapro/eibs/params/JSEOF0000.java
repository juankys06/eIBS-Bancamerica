package datapro.eibs.params; 

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
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEOF0000 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;
	protected static final int A_MAINTENANCE = 600;
	protected static final int A_CHECK = 1000;
	
	protected static final int R_PARAMETER_LIST = 100;
	
	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;

	
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEOF0000() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEOF0000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	protected void procReqEnterParameter(
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
					+ "EOF0000_of_chk_enter_parameter.jsp");
			callPage(LangPath + "EOF0000_of_chk_enter_parameter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionPos(
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

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSEOF0000?SCREEN=300");
				break;
			case 2 : //Maintenance
				procReqMaintenance(mc, user, req, res, ses);
				break;
			

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSEOF0000?SCREEN=300");
				break;
		}
	}

	protected void procReqParameterList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF000001Message msgList = null;
		ELEERRMessage msgError = null;
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
		userPO.setBank(req.getParameter("E01OFCBNK"));
		userPO.setCurrency(req.getParameter("E01OFCCCY"));
		
		// Send Initial data
		try {
			msgList = (EOF000001Message) mc.getMessageRecord("EOF000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EOF000001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01OFCBNK(req.getParameter("E01OFCBNK"));
			msgList.setE01OFCCCY(req.getParameter("E01OFCCCY"));
			msgList.send();
			msgList.destroy();
			flexLog("EOF000001 Message Sent");
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
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");				
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EOF000001")) {

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
				while (true) {

					msgList = (EOF000001Message) newmessage;

					marker = msgList.getE01OFCOPE();

					if (firstTime) {
						firstTime = false;
						chk = "checked";

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
				ses.setAttribute("error", msgError);
				ses.setAttribute("EOF000001Help", beanList);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0000_of_chk_parameter_list.jsp");
						callPage(
							LangPath + "EOF0000_of_chk_parameter_list.jsp",
							req,
							res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0000_of_chk_enter_parameter.jsp");
						callPage(LangPath + "EOF0000_of_chk_enter_parameter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF000001Message msgRT = null;
		EOF000002Message msgList = null;
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
			msgRT = (EOF000001Message) ses.getAttribute("checksParam");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EOF000001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");

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
			flexLog("EDD050501 Message Sent");
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

			if (newmessage.getFormatName().equals("EOF000001")) {
				try {
					msgRT = new datapro.eibs.beans.EOF000001Message();
					flexLog("EOF000001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EOF000001Message) newmessage;

				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("checksParam", msgRT);
				ses.setAttribute("userPO", userPO);

				try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EOF0000_of_chk_parameters_details.jsp");
						callPage(
							LangPath + "EOF0000_of_chk_parameters_details.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}


				else if (newmessage.getFormatName().equals("EOF000002")) {

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
							String chkTyp = "";
							int compar = 0;
							int indexRow = 0;
							while (true) {

								msgList = (EOF000002Message) newmessage;

								marker = msgList.getE02OFNOPE();

								if (firstTime) {
									firstTime = false;
									chk = "checked";
									chkTyp = msgList.getE02OFCNXO();
									userPO.setBank(msgList.getE02OFNBNK());
									userPO.setType(msgList.getE02OFNFTY());
									userPO.setHeader2(msgList.getE02OFNNUM());

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
							userPO.setHeader1(chkTyp);
							flexLog("Putting java beans into the session");
							ses.setAttribute("EOF000002Help", beanList);
							ses.setAttribute("userPO", userPO);

							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EOF0000_of_chk_parameters_last_check.jsp");
								callPage(
									LangPath + "EOF0000_of_chk_parameters_last_check.jsp",
									req,
									res);
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

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EOF000001Message msgRT = null;
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

		msgRT = new EOF000001Message();
		msgRT.setE01OFCBNK(userPO.getBank());
		msgRT.setE01OFCCCY(userPO.getCurrency());
		ses.setAttribute("checksParam", msgRT);

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EOF0000_of_chk_parameters_details.jsp");
			callPage(LangPath + "EOF0000_of_chk_parameters_details.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EOF000001Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("EOF000001Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgDoc = (EOF000001Message) bl.getRecord();

			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("checksParam", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EOF0000_of_chk_parameters_details.jsp");
				callPage(
					LangPath + "EOF0000_of_chk_parameters_details.jsp",
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

			int screen = A_POSITION;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

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
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {

						//Request
						case R_ENTER :
							 procReqEnterParameter(msgUser, req, res, session);							
							 break;
						case R_PARAMETER_LIST :
							procReqParameterList(mc,msgUser, req, res, session);
							break;
						case R_NEW :
							 procReqNew(mc, msgUser, req, res, session);							
							break;	
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);							
							break;
						
						
							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							 procActionMaintenance(mc, msgUser, req, res, session);							
							break;
						case A_CHECK :
							 procActionCheck(mc, msgUser, req, res, session);							
							break;

							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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

	protected void procActionCheck(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		MessageRecord newmessage = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		userPO = (UserPos) ses.getAttribute("userPO");
		beanList = (JBObjList) ses.getAttribute("EOF000002Help");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			EOF000002Message msg = (EOF000002Message) mc.getMessageRecord("EOF000002");
			if (userPO.getHeader1().equals("3")) {
				beanList.initRow();
				while (beanList.getNextRow()) {
					msg = (EOF000002Message) beanList.getRecord();
					msg.setH02USERID(user.getH01USR());
					msg.setH02PROGRM("EOF0000");
					msg.setH02TIMSYS(getTimeStamp());
					msg.setE02OFNNUM(req.getParameter("E02OFNNUM_" + beanList.getCurrentRow()));
					mc.sendMessage(msg);
					//msg.send();
				}
			} else {
				msg.setH02USERID(user.getH01USR());
				msg.setH02PROGRM("EOF0000");
				msg.setH02TIMSYS(getTimeStamp());
				// TODO set other fields
				msg.setE02OFNBNK(req.getParameter("E02OFNBNK"));
				msg.setE02OFNNUM(req.getParameter("E02OFNNUM"));
				msg.send();
			}
			// send end of list
			msg = (EOF000002Message) mc.getMessageRecord("EOF000002");
			msg.setH02USERID(user.getH01USR());
			msg.setH02PROGRM("EOF0000");
			msg.setH02TIMSYS(getTimeStamp());
			msg.setH02FLGMAS("*");
			//mc.sendMessage(msg);
			msg.send();
			msg.destroy();
			flexLog("Sent Dummy");
			
			msg = (EOF000002Message) mc.getMessageRecord("EOF000002");
			msg.setH02USERID(user.getH01USR());
			msg.setH02PROGRM("EOF0000");
			msg.setH02TIMSYS(getTimeStamp());
			msg.setH02FLGMAS("*");
			msg.send();
			msg.destroy();
			flexLog("Sent Last Message");

			// Receive Error Message
			// TODO Receive Error Message
			try {
				newmessage = mc.receiveMessage(); 
				if (newmessage.getFormatName().equals("ELEERR")) {

					msgError = (ELEERRMessage) newmessage;

					IsNotError = msgError.getERRNUM().equals("0");
					if (!IsNotError) {
						// TODO sendredirect if error
					} else {
						// TODO sendredirect if not error
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
			res.sendRedirect(
								super.srctx
									+ "/servlet/datapro.eibs.params.JSEOF0000?SCREEN=100&E01OFCBNK="+
			userPO.getBank()+"&E01OFCCCY="+userPO.getCurrency());
			

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}
