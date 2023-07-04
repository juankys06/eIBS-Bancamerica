package datapro.eibs.params;

/** 
 * Insert the type's description here.
 * Creation date: (3/13/07 9:58:37 PM)
 * @author: Gustavo Adolfo Villarroel
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSESD0206 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_LIST = 100;

	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;
	protected static final int R_DELETE = 700;

	protected static final int A_MAINTENANCE = 600;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESD0206() {
		super();
	}

	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0206");

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterBranch(
		MessageContext mc,
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
						+ "ESD0206_changeprod_enter.jsp");
				callPage(LangPath + "ESD0206_changeprod_enter.jsp", req, res);
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

		ESD020601Message msgRT = null;

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		//crear variable para que se pueda leer en las pantallas			
		userPO.setHeader16(req.getParameter("opt"));
		
		switch (inptOPT) {
			case 1 : //New
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.params.JSESD0206?SCREEN=300");
				break;
			case 2 : //Maintenance
			
				procReqMaintenance(mc, user, req, res, ses);
				break;
			case 3 : //Delete
				procReqListDelete(mc, user, req, res, ses);
				break;

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSESD0206?SCREEN=500") ;
				break;
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
		ESD020601Message msgList = null;
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

		// Send Initial data
		try {
			msgList = (ESD020601Message) mc.getMessageRecord("ESD020601");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0206");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.send();
			msgList.destroy();
			flexLog("ESD020601 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
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

				showERROR(msgError);

				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
			}
			else if (newmessage.getFormatName().equals("ESD020601")) {

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

					msgList = (ESD020601Message) newmessage;

					marker = msgList.getH01FLGMAS();

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
				ses.setAttribute("ESD020601Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0206_penalization_list.jsp");
							callPage(
								LangPath + "ESD0206_penalization_list.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0206_penalization_list.jsp");

							callPage(
								LangPath + "ESD0206_penalization_list.jsp",
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

	protected void procReqListDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD020601Message msgList = null;
		ESD020601Message msgRec = null;
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

		// Send Initial data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ESD020601Help");
			int idx = Integer.parseInt(req.getParameter("CODE"));
			bl.setCurrentRow(idx);

			msgRec = (ESD020601Message) bl.getRecord();
			
			msgList = (ESD020601Message) mc.getMessageRecord("ESD020601");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0206");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");
			msgList.setE01RTEMIN(msgRec.getE01RTEMIN());
			msgList.setE01RTEMAX(msgRec.getE01RTEMAX());
			msgList.setE01RTEC03(msgRec.getE01RTEC03());
			msgList.setE01RTEC06(msgRec.getE01RTEC06());
			msgList.setE01RTEC09(msgRec.getE01RTEC09());
			msgList.setE01RTEC12(msgRec.getE01RTEC12());
			msgList.setE01RTEC15(msgRec.getE01RTEC15());
			msgList.setE01RTEMINA(msgRec.getE01RTEMINA());
			msgList.setE01RTEMAXA(msgRec.getE01RTEMAXA());
			
			msgList.send();
			msgList.destroy();
			flexLog("ESD020601 Message Sent");
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
				//beanList.setNoResult(true);

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

			if (newmessage.getFormatName().equals("ESD020601")) {

				msgList = (ESD020601Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSESD0206?SCREEN=100");
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
	   ESD020601Message penalDet = null;
	   UserPos userPO = null;
	   boolean IsNotError = false;

	   try {
		   msgError = new ELEERRMessage();

			userPO = new UserPos();  //    //

	   } catch (Exception ex) {
		   flexLog("Error: " + ex);
	   }

	   userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	   userPO.setPurpose("NEW");
	   ses.setAttribute("penalDet", penalDet);

	   try {
		   flexLog(
			   "About to call Page: "
				   + LangPath
				   + "ESD0206_penalization_maint.jsp");
		   callPage(LangPath + "ESD0206_penalization_maint.jsp", req, res);
	   } catch (Exception e) {
		   flexLog("Exception calling page " + e);
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
		ESD020601Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String opcode = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (userPO.getPurpose().equals("NEW"))
			opcode = "0001";
		else
			opcode = "0005";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ESD020601Message) ses.getAttribute("penalDet");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD0206");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opcode);

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
			flexLog("ESD020601 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD020601")) {
				try {
					msgRT = new datapro.eibs.beans.ESD020601Message();
					flexLog("ESD020601 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD020601Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("penalDet", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.params.JSESD0206?SCREEN=100");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0206_penalization_maint.jsp");
							callPage(
								LangPath + "ESD0206_penalization_maint.jsp",
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

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD020601Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ESD020601Help");
			int idx = Integer.parseInt(req.getParameter("CODE"));
			bl.setCurrentRow(idx);

			msgDoc = (ESD020601Message) bl.getRecord();

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("penalDet", msgDoc);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0206_penalization_maint.jsp");
					callPage(
						LangPath + "ESD0206_penalization_maint.jsp",
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
						case R_ENTER :
							procReqEnterBranch(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							procReqNew(mc, msgUser, req, res, session);							
							break;								
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_DELETE :
							procReqListDelete(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// END Entering

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
