package datapro.eibs.forex;

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

public class JSEGL0590 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_ENTER 			= 1;
	protected static final int R_LIST 			= 100;
	protected static final int R_DETAIL	 		= 300;
	
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEGL0590() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEGL0590");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
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
				"About to call Page: " + LangPath + "EGL0590_gl_enter.jsp");
			callPage(LangPath + "EGL0590_gl_enter.jsp", req, res);
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
		EGL059001Message msgList = null;
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
		
		if(req.getParameter("E01GLMBNK")!=null){
			userPO.setBank(req.getParameter("E01GLMBNK"));
		}
		
		// Send Initial data
		try {
			msgList = (EGL059001Message) mc.getMessageRecord("EGL059001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EGL0590");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01GLMBNK(userPO.getBank());

			msgList.send();
			msgList.destroy();
			flexLog("EGL059001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EGL059001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
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

					msgList = (EGL059001Message) newmessage;

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
				ses.setAttribute("glList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: " + LangPath + "EGL0590_gl_list.jsp");
					callPage(
						LangPath + "EGL0590_gl_list.jsp", req, res);
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

		EGL059001Message msgRow = null;
		EGL059002Message msgDet = null;
		MessageRecord newmessage = null;
		EGL059002Message msgList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Receive Record Data
		try {
			String opt = (String) req.getParameter("opt");
			
			JBObjList bl = (JBObjList) ses.getAttribute("glList");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgRow = (EGL059001Message) bl.getRecord();

			// Send Initial data
			try {
				msgDet = (EGL059002Message) mc.getMessageRecord("EGL059002");
				msgDet.setH02USERID(user.getH01USR());
				msgDet.setH02PROGRM("EGL0590");
				msgDet.setH02TIMSYS(getTimeStamp());
				msgDet.setH02SCRCOD("01");
				msgDet.setH02OPECOD("0015");
				
				userPO.setHeader10(msgRow.getE01GLMGLN());
				userPO.setHeader20(msgRow.getE01GLMBBL());
				
				msgDet.setE02GLMBNK(msgRow.getE01GLMBNK());
				msgDet.setE02GLMCCY(msgRow.getE01GLMCCY());
				msgDet.setE02GLMGLN(msgRow.getE01GLMGLN());
				msgDet.setE02GLMBBL(msgRow.getE01GLMBBL());
				msgDet.setE02FLGENV(opt);
				
				msgDet.send();
				msgDet.destroy();
				flexLog("EGL059002 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Data
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EGL059002")) {

					JBObjList beanList = new JBObjList();

					boolean firstTime = true;
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

						msgList = (EGL059002Message) newmessage;

						marker = msgList.getH02FLGMAS();

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
					ses.setAttribute("glDetail", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: " + LangPath + "EGL0590_gl_detail.jsp");
						callPage(LangPath + "EGL0590_gl_detail.jsp", req, res);
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

			int screen = R_ENTER;

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
							 procReqEnter(msgUser, req, res, session);							
							 break;
						case R_LIST :
							procReqList(mc,msgUser, req, res, session);
							break;
						case R_DETAIL :
							procReqDetail(mc, msgUser, req, res, session);							
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

}
