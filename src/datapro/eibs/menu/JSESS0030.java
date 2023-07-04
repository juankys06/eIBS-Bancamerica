/*
 * Created on Nov 17, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.menu;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.interfaces.InitSessionPlugin;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS003002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageHandler;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.routers.TOSocketMessageRouter;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSESS0030 extends SuperServlet {

	protected static final int A_LOGIN = 2;
	protected static final int R_LOGIN_CHANGE_PASSWORD = 3;
	protected static final int A_LOGIN_CHANGE_PASSWORD = 4;
	protected static final int R_CHANGE_PASSWORD = 5;
	protected static final int A_CHANGE_PASSWORD = 6;
	protected static final int R_CHECK_APP_PASSWORD = 7;
	protected static final int A_CHECK_APP_PASSWORD = 8;
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = req.getSession(false);

		int screen ;
		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		} catch (Exception e) {
			screen = A_LOGIN;
		}
		if (session == null) {
			session = req.getSession(true);
			if (screen != A_LOGIN) {
				try {
					res.setContentType("text/html");
					printLogInAgain(res.getWriter());
				}
				catch(Exception e) {
					flexLog("Exception = " + e);
				}
			}
		} else {
			if (screen != 2) {
				msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
			}
			try {
			// Here we should get the path from the user profile
				processRequest(msgUser, req, res, session, screen);
			} catch (Exception e) {
				flexLog("Error: " + e);
				forward(SuperServlet.sckNotOpenPage, req, res);
			}
		}
	}

	protected UserPos getUserPos(HttpSession session) {
		return (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
	}
	
	protected String getLangPath(HttpServletRequest req) {
		ESS0030DSMessage msgUser =
			(ESS0030DSMessage) req.getSession(false).getAttribute("currUser");
		if (msgUser == null) {			
			return SuperServlet.rootPath + req.getParameter("Language").toLowerCase() + "/";
		} else {
			return SuperServlet.rootPath + msgUser.getE01LAN() + "/";
		}
	}

	protected void forward(String pageName,
		HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		callPage(getLangPath(req) + pageName, req, res);
	}
	
	/**
	 * @param string
	 * @param req
	 * @return
	 */
	private MessageProcessor getMessageProcessor(String targetProgram, HttpServletRequest req) throws IOException {
		MessageProcessor mp = null;
		MessageHandler mh =	new MessageHandler(
								new TOSocketMessageRouter(
									super.hostIP,
									getInitSocket(req),
									super.sckTimeOut),
									"datapro.eibs.beans");
		
		mp = new MessageProcessor(mh);
		return mp;
	}

	/**
	 * @param msgUser
	 * @param req
	 * @param res
	 * @param session
	 * @param screen
	 */
	private void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen) throws ServletException, IOException {
		switch (screen) {
			case A_LOGIN:
				procActionLogIn(user, req, res, session);
				break;
			case R_CHANGE_PASSWORD:
				procReqChangePassword(user, req, res, session);
				break;
			case A_LOGIN_CHANGE_PASSWORD:
				procActionLogInChangePassword(user, req, res, session);
				break;	
			case A_CHANGE_PASSWORD:
				procActionChangePassword(user, req, res, session);
				break;	
			case R_CHECK_APP_PASSWORD:
				procReqChkAppPassword(user, req, res, session);
				break;	
			case A_CHECK_APP_PASSWORD:
				procActionChkAppPassword(user, req, res, session);
				break;	
			case R_LOGIN_CHANGE_PASSWORD:	
			default :
				forward(SuperServlet.devPage, req, res);
				break;
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionLogInChangePassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		ELEERRMessage msgError = new ELEERRMessage();
		String password = (req.getParameter("NewPassword_1") == null) ? "" : req.getParameter("NewPassword_1").toUpperCase();
		MessageProcessor mp = null;
		try {
			//System.out.println("Info: Invoking method for password exchange.");
			mp = getMessageProcessor("ESS0030", req);
			ESS003002Message msgPsw = (ESS003002Message) mp.getMessageRecord("ESS003002", null, "");
			msgPsw.setH02USR(user.getH01USR());
			msgPsw.setE02OPR("1");
			msgPsw.setE02APW(user.getE01PSW());
			msgPsw.setE02PSW(password);
			try {
				msgPsw.setH02WK1(req.getParameter("SOURCE"));
			} catch(Exception e) {
				msgPsw.setH02WK1("");
			}
			mp.sendMessage(msgPsw);
			
			msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			session.setAttribute("error", msgError);
			
			if (mp.hasError(msgError)) {
				forward("ESS0030_LogIn_change_password.jsp", req, res);
			} else {
				user.setE01PSW(password);
				session.setAttribute("currUser", user);
				forward("ESS0030_LogIn.jsp", req, res);
			}
			
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionChkAppPassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String password = (req.getParameter("AppPassword") == null) ? "" : req.getParameter("AppPassword");
		
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("ESS0030", req);
			
			ESS0030DSMessage auxuser = (ESS0030DSMessage) mp.getMessageRecord("ESS0030DS", null, "");
			auxuser.setH01USR(user.getH01USR());
			auxuser.setH01PGM("ESS0030");
			auxuser.setE01PSW(password);
			auxuser.setH01TIM(getTimeStamp());
			auxuser.setH01SCR("01");
			auxuser.setH01OPE("0004");
			auxuser.setE01LAN(user.getE01LAN());
			
			mp.sendMessage(auxuser);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			session.setAttribute("error", msgError);
			
			if (mp.hasError(msgError)) {
				forward("ESS0030_app_enter_password.jsp", req, res);
			} else {
				UserPos userPO = getUserPos(session);
				flexLog("About to call Page: " + userPO.getRedirect());
				res.sendRedirect(super.srctx + userPO.getRedirect());
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
		
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionChangePassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		ELEERRMessage msgError = new ELEERRMessage();
		
		String newLoginPsw = (req.getParameter("LoginPassword_1") == null) ? "" : req.getParameter("LoginPassword_1").toUpperCase();
		String oldLoginPsw = (req.getParameter("LoginPassword") == null) ? "" : req.getParameter("LoginPassword").toUpperCase();
		String newAppPsw = (req.getParameter("AppPassword_1") == null) ? "" : req.getParameter("AppPassword_1").toUpperCase();
		String oldAppPsw = (req.getParameter("AppPassword") == null) ? "" : req.getParameter("AppPassword").toUpperCase();
		
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("ESS0030", req);
			ESS003002Message msgPsw = (ESS003002Message) mp.getMessageRecord("ESS003002", null, "");
			msgPsw.setH02USR(user.getH01USR());
			msgPsw.setE02OPR(user.getE01OPR());
			msgPsw.setE02APW(oldLoginPsw);
			msgPsw.setE02PSW(newLoginPsw);
			msgPsw.setE02APS(oldAppPsw);
			msgPsw.setE02PSE(newAppPsw);
			try {
				msgPsw.setH02WK1(req.getParameter("SOURCE"));
			} catch(Exception e) {
				msgPsw.setH02WK1("");
			}
			mp.sendMessage(msgPsw);
			
			msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			session.setAttribute("error", msgError);
			
			if (mp.hasError(msgError)) {
				forward("ESS0030_change_password.jsp", req, res);
			} else {
				user.setE01PSW(newLoginPsw);
				user.setE01PSE(newAppPsw);
				session.setAttribute("currUser", user);
				flexLog("About to call Page: " + SuperServlet.bgPage);
				res.sendRedirect(SuperServlet.srctx + SuperServlet.bgPage);
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionLogIn(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		//user = null;
		ELEERRMessage msgError = new ELEERRMessage();
		UserPos userPO = new UserPos();
		
		MessageProcessor mp = null;
		try {
			//System.out.println("Info: Invoking method for Login action.");
			mp = getMessageProcessor("ESS0030", req);
			user = (ESS0030DSMessage) mp.getMessageRecord("ESS0030DS", null, "");
			String userid = (req.getParameter("UserId") == null) ? "" : req.getParameter("UserId").toUpperCase();
			user.setH01USR(userid);
			String password = (req.getParameter("Password") == null) ? "" : req.getParameter("Password").toUpperCase();
			user.setE01PSW(password);
			user.setH01PGM("ESS0030");
			user.setH01TIM(getTimeStamp());
			user.setH01SCR("01");
			user.setH01OPE("0004");
			String language = (req.getParameter("Language") == null) ? "" : req.getParameter("Language").toLowerCase(); 
			user.setE01LAN(language);
			try {
				user.setH01WK1(req.getParameter("SOURCE"));
			} catch(Exception e) {
				user.setH01WK1("");
			}
			mp.sendMessage(user);
			//flexLog(mp.getMessageHandler().toString());
			
			msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			boolean succeed = !mp.hasError(msgError);
			
			user = (ESS0030DSMessage) mp.receiveMessageRecord("ESS0030DS");
			
			session.setAttribute("currUser", user);
			session.setAttribute("userPO", userPO);
			session.setAttribute("error", msgError);
			
			if (succeed) {
				initPlugin(session, user);
				forward("LOOK_eIBS.jsp", req, res);
			} else {
				if (user.getE01OPR().equals("")) {
					forward("ESS0030_LogIn.jsp", req, res);
				} else {
					forward("ESS0030_LogIn_change_password.jsp", req, res);
				}
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param session
	 * @param user
	 */
	private void initPlugin(HttpSession session, ESS0030DSMessage user) {
		String className = JSEIBSProp.getSessionPluginClass();
		if (!className.equals("")) {
			Map map = new HashMap();
			Enumeration enu = user.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while(enu.hasMoreElements())  {
				field = (MessageField)enu.nextElement();
				try {
					map.put(new String(field.getTag()), new String(field.getString()));
				} catch(Exception exception) { 
				}
			}

			try {
				Class c = Class.forName(className);
				InitSessionPlugin sp = (InitSessionPlugin)c.newInstance();
				sp.init(session, map);
			}
			catch(Exception e) {
				flexLog("Error: " + e);
			}
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqChkAppPassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		ELEERRMessage msgError = new ELEERRMessage();
		
		if (user.getE01SEC().equals("Y")) {
			forward("ESS0030_app_enter_password.jsp", req, res);
		} else {
			UserPos userPO = getUserPos(session);
			res.sendRedirect(super.srctx + userPO.getRedirect());
		}
		
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqChangePassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = new UserPos();
		userPO.setOption("PASSWORD");
		userPO.setPurpose("MAINTENANCE");
		session.setAttribute("userPO", userPO);
		
		ELEERRMessage msgError = new ELEERRMessage();
		session.setAttribute("error", msgError);
		
		forward("ESS0030_change_password.jsp", req, res);
	}

}
