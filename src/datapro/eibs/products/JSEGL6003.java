/*
 * Created on Apr 2, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EGL600301Message;
import datapro.eibs.beans.EGL600302Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;


/**
 * @author datapro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEGL6003 extends JSEIBSServlet {

	protected static final int R_PASSWORD = 1;
	protected static final int R_APPROVAL = 5;
	protected static final int A_APPROVAL = 2;
	protected static final int R_APPROVAL_INQ = 3;

	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession session,
		int screen)
		throws ServletException, IOException {

			switch (screen) {
				case R_PASSWORD :
					procReqPassword(user, req, res, session);
					break;
				case R_APPROVAL :
					procReqApproval(user, req, res, session);
					break;
				case A_APPROVAL :
					procActionApproval(user, req, res, session);
					break;
				case R_APPROVAL_INQ :
					procReqApprovalInq(user, req, res, session);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
			}
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqApprovalInq(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		int appCode = 0;
		try {
			appCode = Integer.parseInt(req.getParameter("appCode"));
		} catch (Exception e) {
			appCode = 0;
		}
		String bthNum = req.getParameter("BTHNUM");
		String typeCode = req.getParameter("typeCode");

		datapro.eibs.products.JOActionRedirect red =
			new datapro.eibs.products.JOActionRedirect(
				typeCode,
				ACC_APPROVAL_INQ,
				appCode,
				bthNum,
				getLangPath(user),
				session);
				
		redirectToPage(red.action(), res);
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionApproval(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		try {
			mp = getMessageProcessor("EGL6003", req);
			EGL600302Message msg = (EGL600302Message) mp.getMessageRecord("EGL600302", user.getH01USR(), "");
			msg.setE02BTHNUM(req.getParameter("BTHNUM"));
			msg.setE02ACTION(req.getParameter("action"));
			msg.setE02MSGTXT(req.getParameter("reason"));
			try {
				msg.setH02FLGWK2(req.getParameter("OPCION"));
			} catch (Exception e) {
				msg.setH02FLGWK2(" ");
			}	
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			if (mp.hasError(msgError)) {
				forward("EGL6003_approval_list.jsp", req, res);
			} else {
				redirectToPage("/servlet/datapro.eibs.products.JSEGL6003?SCREEN=5", res);
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
	private void procReqApproval(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			
			try {
				mp = getMessageProcessor("EGL6003", req);
			} catch (IOException e) {
				forward("MISC_not_available.jsp", req, res);
				return;
			}			
			
			
			EGL600301Message msg = (EGL600301Message) mp.getMessageRecord("EGL600301", user.getH01USR(), "");
			try {
				msg.setH01FLGWK2(req.getParameter("OPCION"));
			} catch (Exception e) {
				msg.setH01FLGWK2(" ");
			}	
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = new ELEERRMessage();
			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				flexLog("Putting java beans into the session");
				session.setAttribute("error", msgError);
				PageToCall = "error_viewer.jsp";
			} else {
				list.initRow();
				String chk = "";
				String bthNum = req.getParameter("BTHNUM");
				boolean firstTime = (bthNum == null);
				userPO.setPurpose("APPROVAL");
				flexLog("Putting java beans into the session");
				session.setAttribute("userPO", userPO);
				session.setAttribute("appList", list);
				session.setAttribute("error", msgError);
				PageToCall = list.getNoResult() ? "MISC_no_result.jsp" : "EGL6003_approval_list.jsp"; 
			}
			forward(PageToCall, req, res);
		
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
	private void procReqPassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		userPO.setRedirect(
			"/servlet/datapro.eibs.products.JSEGL6003?SCREEN="
				+ R_APPROVAL
				+ "&appCode="
				+ req.getParameter("appCode")
				+ "&PORT="
				+ req.getParameter("PORT"));
		session.setAttribute("userPO", userPO);
		redirectToPage("/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7", res);
	}

}
