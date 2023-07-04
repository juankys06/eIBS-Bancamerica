package datapro.eibs.products;

/** 
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: 
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEGL0600 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	protected static final int R_ENTER_PARAM = 100;
	// second screen
	protected static final int R_LIST = 200;
	// viene seleccionado el formato a trabajar	y mostrar cuentas del formato
	protected static final int A_POSITION = 800;
	
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEGL0600() {
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
	
	
	// first screen - PARAMETERS
	protected void procEnterParam(
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

	try {
		msgError = new ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	try {
		flexLog("About to call Page: " + LangPath + "EGL0600_enter_param.jsp");
		callPage(LangPath + "EGL0600_enter_param.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

	}



//	MANEJO DE LISTA DE CUENTAS POR FORMATO
	 protected void procList(
		 MessageContext mc,
		 ESS0030DSMessage user,
		 HttpServletRequest req,
		 HttpServletResponse res,
		 HttpSession ses)
		 throws ServletException, IOException {

		 MessageRecord newmessage = null;
		 EGL060001Message msgList = null;
		 ELEERRMessage msgError = null;
		 UserPos userPO = null;
		 JBListRec optList1 = null;
		 JBList beanList1 = null;
		 boolean IsNotError = false;

		 try {
			 beanList1 = new datapro.eibs.beans.JBList();
		 } catch (Exception ex) {
			 flexLog("Error: " + ex);
		 }

		 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		 if (req.getParameter("E01TLDBNK") != null) {
			 userPO.setBank(req.getParameter("E01TLDBNK"));
		 }
		 if (req.getParameter("E01TLDBRN") != null) {
			 userPO.setBranch(req.getParameter("E01TLDBRN"));
		 }
		 if (req.getParameter("E01TDRTMC") != null) {
			 userPO.setCurrency(req.getParameter("E01TDRTMC"));
		 }
		 if (req.getParameter("E01TDRTGL") != null) {
			 userPO.setAccNum(req.getParameter("E01TDRTGL"));
		 }

		 // Send Initial data
		 try {
			 msgList = (EGL060001Message) mc.getMessageRecord("EGL060001");
			 msgList.setH01USERID(user.getH01USR());
			 msgList.setH01PROGRM("EGL060001");
			 msgList.setH01TIMSYS(getTimeStamp());
			 msgList.setH01SCRCOD("01");
			 msgList.setH01OPECOD("0015");
			 msgList.setE01INPBNK(userPO.getBank());
			 msgList.setE01INPBRN(userPO.getBranch());
			 msgList.setE01INPCCY(userPO.getCurrency());
			 msgList.setE01INPGLN(userPO.getAccNum());
			if(req.getParameter("Pos")!=null){
				msgList.setE01RECPOS(req.getParameter("Pos"));
			}
			if(req.getParameter("E01INPLMI")!=null){
				msgList.setE01INPLMI(req.getParameter("E01INPLMI"));
			}
			if(req.getParameter("E01INPLMX")!=null){
				msgList.setE01INPLMX(req.getParameter("E01INPLMX"));
			}

			 msgList.send();
			 msgList.destroy();
			 flexLog("EGL060001 Message Sent");
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

				 showERROR(msgError);

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

			 if (newmessage.getFormatName().equals("EGL060001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";

				int numrec = 0;
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					} 

					 msgList = (EGL060001Message) newmessage;

					 marker = msgList.getE01OPECDE();

					 if (firstTime) {
						 firstTime = false;
						beanList.setFirstRec(
						Integer.parseInt(msgList.getE01RECPOS()));
						userPO.setCusName(msgList.getE01INPDSC());
					 }

					 if (marker.equals("*")) {
						 beanList.setShowNext(false);
						 break;
					 } else {

						beanList.addRow(msgList);

						 if (marker.equals("+")) {
							beanList.setShowNext(true);
							beanList.setLastRec(
							Integer.parseInt(msgList.getE01RECPOS()));

							 break;
						 }

						numrec = Integer.parseInt(msgList.getE01RECPOS());
						if (numrec == 21) {
							beanList.setShowPrev(true);
						}

					 }
					 
					 newmessage = mc.receiveMessage();
				 }

				 flexLog("Putting java beans into the session");
				 ses.setAttribute("EGL060001Help", beanList);
				ses.setAttribute("optList1", beanList1);
				ses.setAttribute("userPO", userPO);

				 try {

						 if (msgError.getERRNUM().equals("0")) {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EGL0600_list.jsp");
							 callPage(
								 LangPath + "EGL0600_list.jsp",
								 req,
								 res);
						 } else {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EGL0600_enter_param.jsp");

							 callPage(
								 LangPath + "EGL0600_enter_param.jsp",
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
						// First screen
						case R_ENTER_PARAM :
							procEnterParam(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procList(mc, msgUser, req, res, session);
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
