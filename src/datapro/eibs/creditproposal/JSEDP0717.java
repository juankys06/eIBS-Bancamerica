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

public class JSEDP0717 extends datapro.eibs.master.SuperServlet {

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
	public JSEDP0717() {
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

		//====================================
		// Comunication to the Main parameters
		//====================================
		UserPos userPO = null;
				userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		/*---------------------------------------------------------------*/

		/***********************************************/
		try {
			
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0717_ListRecDoc_filter.jsp");
			callPage(LangPath + "EDP0717_ListRecDoc_filter.jsp", req, res);
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
		EDP071701Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		if(req.getParameter("E01FILPRD")!=null){
			   userPO.setHeader9(req.getParameter("E01FILPRD"));
		   }
					
		// Send Initial data
		try {
			msgList = (EDP071701Message) mc.getMessageRecord("EDP071701");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0717");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01DPHPRD(req.getParameter("E01FILPRD"));
			msgList.send();
			msgList.destroy();
			flexLog("EDP071701 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP071701")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";

			   beanList.setNoResult(true);
				int ct = 0;
			   while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				   if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					   System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				   }

					msgList = (EDP071701Message) newmessage;

					marker = msgList.getE01OPECDE();
					userPO.setHeader10(msgList.getE01DPHTYP());
					userPO.setHeader11(msgList.getE01DPHPDE());

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
				ses.setAttribute("EDP071701Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0717_ListRecDoc_maint.jsp");
							callPage(
								LangPath + "EDP0717_ListRecDoc_maint.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0717_ListRecDoc_filter.jsp");

							callPage(
								LangPath + "EDP0717_ListRecDoc_filter.jsp",
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
		EDP071701Message msgSearch = null;
		ELEERRMessage msgError = null;
		EDP071701Message msgList = null;
		JBObjList bl = (JBObjList) ses.getAttribute("EDP071701Help");
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String prdP = "";

		int numrec = 0;
		if(req.getParameter("RECNUM")!=null){
			   numrec = Integer.parseInt(req.getParameter("RECNUM"));
		   }
	try {
	   for (int i = 0; i < numrec; i++) {

		bl.setCurrentRow(i);
		prdP = req.getParameter("DPHDOC" + i);
		if(!prdP.equals("0000")) {

		try {
		msgList = (EDP071701Message) mc.getMessageRecord("EDP071701");
		  msgList.setH01USERID(user.getH01USR());
		  msgList.setH01PROGRM("EDP0717");
		  msgList.setH01TIMSYS(getTimeStamp());
		  msgList.setH01SCRCOD("01");
		  msgList.setH01OPECOD("0005");
		  msgList.setE01DPHPRD(userPO.getHeader9());
		  msgList.setE01DPHDOC(req.getParameter("E01DPHDOC" + i));
		  msgList.setE01DPHTDO(req.getParameter("DPHTDO" + i));
		  msgList.setE01DPHCN1(req.getParameter("DPHCN1" + i));
//		  msgList.setE01DPHCND(req.getParameter("DPHCND" + i));
		  msgList.setE01DPHCN2(req.getParameter("DPHCN2" + i));
//		  msgList.setE01DPHC2D(req.getParameter("DPHC2D" + i));
		  msgList.setE01DPHEST(req.getParameter("DPHEST" + i));
		  msgList.send();
		  msgList.destroy();
		  
		  flexLog("EDP071701 Message Sent");
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
							+ "EDP0717_ListRecDoc_filter.jsp");
					callPage(
						LangPath + "EDP0717_ListRecDoc_filter.jsp",
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
