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

public class JSEDP0715 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	protected static final int ReqFilter = 100;
	// LIST RECORDS
	protected static final int ReqList = 150;
	// UPDATE
	protected static final int ActionList = 200;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0715() {
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
	

	/** Filtro */
	/************************************************************************************************/
	/************************************************************************************************/
	/************************************************************************************************/
	protected void procReqFilter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		req.getParameter("SCREEN");

		String mensaje_error = "";
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP074601Message msgList746 = null; //Productos
		JBList beanList746 = null;

		//====================================
		// Comunication to the Main parameters
		//====================================
		UserPos userPO = null;
				userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


				//RETRIEVE PARAMETERS - OPTION FOR PREPARE=1 OR PROCESS=2
				if (req.getParameter("OPTION") != null) {
					userPO.setOption(req.getParameter("OPTION"));
				}

		try {
			mensaje_error = "Error: ";
			msgError = new datapro.eibs.beans.ELEERRMessage();
			beanList746 = new datapro.eibs.beans.JBList();
			msgList746 = new datapro.eibs.beans.EDP074601Message(); //DDL3
		} catch (Exception ex) {
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
		}


		/*---------------------------------------------------*/
		// Send Initial data for DDL Producto
		/*---------------------------------------------------*/
		try {
			flexLog("Send Initial Data Productos");
			msgList746 = (EDP074601Message) mc.getMessageRecord("EDP074601");

			msgList746.setH01USERID(user.getH01USR());
			msgList746.setH01PROGRM("EDP0746");
			msgList746.setH01TIMSYS(getTimeStamp());
			msgList746.setH01OPECOD("0015");
			msgList746.send();
			msgList746.destroy();

		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Productos");
		}
		/*.................................................*/
		// Receive Error Message
		try {
			String myOption = "";
			String myFlag = "";
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDP074601")) {

				String marker = "";
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList746 = (EDP074601Message) newmessage;
					marker = msgList746.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\"" 	
							+ msgList746.getE01EUPUSR() + "\">"
								+ msgList746.getE01EUPUSR()
								+ " - " + msgList746.getE01EUPNME()
								+ "</option>";
						beanList746.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session Producto");
			ses.setAttribute("optList746", beanList746);
			ses.setAttribute("optList4", beanList746);
			ses.setAttribute("error", msgError);

		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + e);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Producto");
		}
		/*---------------------------------------------------------------*/

		/***********************************************/
		try {
			
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0715_ListAccess_filter.jsp");
			callPage(LangPath + "EDP0715_ListAccess_filter.jsp", req, res);
		} catch (Exception ex) {
			flexLog("Exception calling page " + ex);
		}
		/***********************************************/
	}


	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP071501Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// asigna variables para trabajar con cuentas del formato seleccionado

		if(req.getParameter("E01DPWRUT")!=null){
			   userPO.setHeader9(req.getParameter("E01DPWRUT"));
		   }
		if(req.getParameter("E01DPWUID")!=null){
				  userPO.setHeader10(req.getParameter("E01DPWUID"));
			  }
					
		// Send Initial data
		try {
			msgList = (EDP071501Message) mc.getMessageRecord("EDP071501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0715");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01DPWUID(req.getParameter("E01DPWUID"));
			msgList.setE01DPWRUT(req.getParameter("E01DPWRUT"));
//call 400
			msgList.send();
			msgList.destroy();
			flexLog("EDP071501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
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

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP071501")) {

				JBObjList beanList = new JBObjList();
//				JBListRec beanList = new JBListRec();

				boolean firstTime = true;
				String marker = "";

			   beanList.setNoResult(true);
				int ct = 0;
			   while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				   if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					   System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				   }

					msgList = (EDP071501Message) newmessage;

					marker = msgList.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
					} else {
					   beanList.setNoResult(false);
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
				ses.setAttribute("EDP071501Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0715_ListAccess_maint.jsp");
							callPage(
								LangPath + "EDP0715_ListAccess_maint.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0715_ListAccess_filter.jsp");

							callPage(
								LangPath + "EDP0715_ListAccess_filter.jsp",
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




	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		MessageRecord newmessage = null;
		EDP071501Message msgSearch = null;
		ELEERRMessage msgError = null;
		EDP071501Message msgList = null;
		JBObjList bl = (JBObjList) ses.getAttribute("EDP071501Help");
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		int numrec = 0;
		if(req.getParameter("RECNUM")!=null){
			   numrec = Integer.parseInt(req.getParameter("RECNUM"));
		   }
	try {
	   for (int i = 0; i < numrec; i++) {

		bl.setCurrentRow(i);

		try {
		msgList = (EDP071501Message) mc.getMessageRecord("EDP071501");
		  msgList.setH01USERID(user.getH01USR());
		  msgList.setH01PROGRM("EDP0715");
		  msgList.setH01TIMSYS(getTimeStamp());
		  msgList.setH01SCRCOD("01");
		  msgList.setH01OPECOD("0005");
		  
		  msgList.setE01DPWUID(userPO.getHeader10());
		  msgList.setE01DPWRUT(userPO.getHeader9());

		  msgList.setE01DPWSEC(req.getParameter("DPWSEC" + i));
		  msgList.setE01DPWACT(req.getParameter("DPWACT" + i));


		  msgList.setE01DPWFG2(req.getParameter("E01DPWFG2" + i));
		  msgList.setE01DPWTAC(req.getParameter("E01DPWTAC" + i));
		  msgList.setE01DPWINQ(req.getParameter("E01DPWINQ" + i));
		  msgList.setE01DPWFG1(req.getParameter("E01DPWFG1" + i));
		  msgList.setE01DPWFG3(req.getParameter("E01DPWFG3" + i));

		  msgList.send();
		  msgList.destroy();
		  flexLog("EDP071501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// Receive Error
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
//				ses.setAttribute("userPO", userPO);


				msgError = (ELEERRMessage) newmessage;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				if (IsNotError) {
//					beanList.setFlag("S", beanList.getCurrentRow());
				} else {
					msgError = (ELEERRMessage) newmessage;
					showERROR(msgError);
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
//					ses.setAttribute("chkList", beanList);


				try {

					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0715_ListAccess_maint.jsp");
					callPage(
						LangPath + "EDP0715_ListAccess_maint.jsp",
						req,
						res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				return;
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			}

			procReqFilter(mc, user, req, res, ses);
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

			int screen = 0;

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
						case ReqFilter :
							procReqFilter(mc, msgUser, req, res, session);
							break;
						case ReqList :
							procReqList(mc, msgUser, req, res, session);
							break;
						case ActionList :
							procActionList(mc, msgUser, req, res, session);
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
