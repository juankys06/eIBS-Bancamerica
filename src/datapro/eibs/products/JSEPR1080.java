/*
 * Created on May 29, 2009
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

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR012001Message;
import datapro.eibs.beans.EPR012002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.JBSortList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEPR1080 extends JSEIBSServlet {

	private static final int A_MAIN_LIST		= 2;
	private static final int R_MAIN_LIST		= 1;
	private static final int R_ENTER_LIST		= 5;
	private static final int R_APPROVAL_INQ		= 3;
	private static final int PREVPAGE 			= 500;
	private static final int NEXTPAGE 			= 501;
	private static final int A_SORT 			= 502;
	
	private static final int R_ENTER_OFC		= 30;
	private static final int R_ENTER_AUT		= 31;
	private static final int A_ENTER_AUT		= 32;
	private static final int A_APPROVAL			= 33;
	
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
				case R_ENTER_LIST :
					procReqList(user, req, res, session);
					break;
				case R_MAIN_LIST :
					procReqMaintList(user, req, res, session);
					break;
				case A_MAIN_LIST :
					procActionMaintList(user, req, res, session);
					break;
				case R_APPROVAL_INQ :
					procReqApprovalInq(user, req, res, session);
					break;
				case PREVPAGE :
					procPrevPage(req, res, session);
					break;
				case NEXTPAGE :
					procNextPage(req, res, session);
					break;
				case A_SORT :
					sort(req, res, session);
					break;
				case R_ENTER_OFC :
					procReqOfficial(user, req, res, session);
					break;
				case R_ENTER_AUT :
					procReqPasswordOffcial(req, res, session);
					break;
				case A_ENTER_AUT :
					procReqListOfficial(user, req, res, session);
					break;
				case A_APPROVAL :
					procActionApproval(user, req, res, session);
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
	private void procActionApproval(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		String account = (req.getParameter("TRANSREFNUM") == null) ? "" : req.getParameter("TRANSREFNUM");
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012002Message msg = (EPR012002Message) mp.getMessageRecord("EPR012002", user.getH01USR(), "");
			msg.setE02PRINUM(account);
			try {
				msg.setE02ACTION(req.getParameter("action"));
			} catch (Exception e) {
			}
			try {
				msg.setE02MSGTXT(req.getParameter("reason"));
			} catch (Exception e) {
			}
			msg.setH02FLGWK3("C");
			
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			String official = (req.getParameter("OFC") == null) ? userPO.getOfficer() : req.getParameter("OFC");
			String branch = (req.getParameter("BRN") == null) ? userPO.getBranch() : req.getParameter("BRN");
			if (mp.hasError(msgError)) {
				String erwrng= (msgError.getERWRNG().equals("Y") ||msgError.getERNU01().equals("6026")) ? "S" : "";
				PageToCall = "EPR1080_pr_officers_list.jsp?ACCNUM=" + req.getParameter("TRANSREFNUM") + "&ERWRNG=" + erwrng;
				userPO.setPurpose("APPROVAL");
				userPO.setOption("PR");						
				forward(PageToCall, req, res);
			} else {
				redirect("datapro.eibs.products.JSEPR1080?SCREEN=" + A_ENTER_AUT + "&ACCNUM=" + account + "&OFC=" + official + "&BRN=" + branch + type, res);
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
	private void procReqListOfficial(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String account = (req.getParameter("ACCNUM1") == null) ? "" : req.getParameter("ACCNUM1");
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		String official = (req.getParameter("OFC") == null) ? "" : req.getParameter("OFC");
		userPO.setOfficer(official);
		String branch = (req.getParameter("BRN") == null) ? "0" : req.getParameter("BRN");
		userPO.setBranch(branch);
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "");
			msg.setE01PRITYP(type);
			msg.setH01FLGWK3("C");
			msg.setE01PRIOBR(branch);
			msg.setE01PRIDPT(official);
			try {
				msg.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				msg.setE01NUMREC("0");
			}
			mp.sendMessage(msg);

			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			ELEERRMessage msgError = new ELEERRMessage();
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				JBSortList lista = new JBSortList();
				String priNum = req.getParameter("REFNUM");
				boolean firstTime = (priNum == null);
				int indexRow = 0;
				lista.setDisplaySize(24);
				list.initRow();
				while (list.getNextRow()) {
					msg = (EPR012001Message) list.getRecord();
					if (account.equals("") || (account.equals(msg.getE01PRINUM()))) {
						lista.add(msg);
					}
				}
				if (lista.getSize() == 0) {
					PageToCall = "MISC_no_result.jsp";
				} else {
					PageToCall = "EPR1080_pr_officers_list.jsp";
					lista.setDisplaySize(24);
					session.setAttribute("lista", lista);
				}
			}
			flexLog("Putting java beans into the session");
			session.setAttribute("userPO", userPO);
			session.setAttribute("error", msgError);

			forward(PageToCall, req, res);
			
		} finally {
			if (mp != null)	mp.close();
		}
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqPasswordOffcial(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		String Param1 = (req.getParameter("ACCNUM") == null) ? "&ACCNUM=''" : "&ACCNUM=" + req.getParameter("ACCNUM");
		String Param2 = (req.getParameter("E01SELOFC") == null) ? "&OFC=0" : "&OFC=" + req.getParameter("E01SELOFC");
		String Param3 = (req.getParameter("E01SELBRN") == null) ? "&BRN=0" : "&BRN=" + req.getParameter("E01SELBRN");
		String Tipo = (req.getParameter("TYP") == null) ? "&TYP=I" : "&TYP=" + req.getParameter("TYP");
		
		userPO.setRedirect("/servlet/datapro.eibs.products.JSEPR1080?SCREEN=" + A_ENTER_AUT + Param1 + Param2 + Param3 + Tipo);
		session.setAttribute("userPO", userPO);
		
		redirect("datapro.eibs.menu.JSESS0030?SCREEN=7", res);
	}
	/**
	 * @param req
	 * @param res
	 */
	private void procReqOfficial(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		session.setAttribute("currUser", user);
		forward("EPR1080_pr_official_enter.jsp", req, res);
	}
	/**
	 * @param req
	 * @param res
	 */
	private void sort(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");

		String fieldName = (req.getParameter("FIELD") == null) ? "" : req.getParameter("FIELD");
		if (!fieldName.equals("")) {
			String[] sortFlds = { "" };
			sortFlds[0] = fieldName;
			lista.setSortKey(sortFlds);
			int orden = lista.getSortOrder();
			if (orden == JBSortList.ASCENDENTE) {
				lista.setSortOrder(JBSortList.DESCENDENTE);
			} else {
				lista.setSortOrder(JBSortList.ASCENDENTE);
			}
			lista.sort();
		}
		session.setAttribute("lista", lista); 
		forward("EPR1080_pr_maint_list.jsp", req, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procNextPage(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setNextPage();
		forward("EPR1080_pr_maint_list.jsp", req, res);	
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procPrevPage(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setPrevPage();
		forward("EPR1080_pr_maint_list.jsp", req, res);	
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
		String accNum = (req.getParameter("ACCNUM") == null) ? "" : req.getParameter("ACCNUM");
		String typeCode = (req.getParameter("typeCode") == null) ? "" : req.getParameter("typeCode");
		
		JOActionRedirect red =
						new datapro.eibs.products.JOActionRedirect(
							typeCode,
							ACC_APPROVAL_INQ,
							appCode,
							accNum,
							getLangPath(user),
							session);
		redirect(red.action(), res);						
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionMaintList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		MessageProcessor mp = null;
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012002Message msg = (EPR012002Message) mp.getMessageRecord("EPR012002", user.getH01USR(), "");
			try {
				msg.setE02PRINUM(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			try {
				msg.setE02ACTION(req.getParameter("action"));
			} catch (Exception e) {
			}
			try {
				msg.setE02MSGTXT(req.getParameter("reason"));
			} catch (Exception e) {
			}
			mp.sendMessage(msg);
		
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			if (mp.hasError(msgError)) {
				forward("EPR1080_pr_maint_list.jsp", req, res);
			} else {
				String appCode = (req.getParameter("appCode") == null) ? "" : req.getParameter("appCode");
				redirectToPage("/servlet/datapro.eibs.products.JSESD0140?SCREEN=1&appCode=" + appCode, res);
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
	private void procReqMaintList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		String account = (req.getParameter("ACCNUM1") == null) ? "" : req.getParameter("ACCNUM1");
		String flg = (req.getParameter("FLG") == null) ? "" : req.getParameter("FLG");

		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "");
			msg.setH01FLGWK3("M");		
			msg.setE01PRITYP(type);
			msg.setH01FLGWK1(flg);
			mp.sendMessage(msg);

			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			ELEERRMessage msgError = new ELEERRMessage();
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				JBSortList lista = new JBSortList();
				String priNum = req.getParameter("REFNUM");
				boolean firstTime = (priNum == null);
				int indexRow = 0;
				lista.setDisplaySize(24);
				list.initRow();
				while (list.getNextRow()) {
					msg = (EPR012001Message) list.getRecord();
					if (account.equals("") || (account.equals(msg.getE01PRINUM()))) {
						lista.add(msg);
					}
				}
				if (lista.getSize() == 0) {
					PageToCall = "MISC_no_result.jsp";
				} else {
					userPO.setPurpose("MAINTENANCE");
					userPO.setOption("PR");	
					lista.setDisplaySize(10);
					session.setAttribute("lista", lista);
					PageToCall = "EPR1080_pr_maint_list.jsp?TYP="+type;
				}	
			}	
			flexLog("Putting java beans into the session");
			session.setAttribute("userPO", userPO);
			session.setAttribute("error", msgError);
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
	private void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		forward("EPR1080_pr_enter.jsp", req, res);
	}

}
