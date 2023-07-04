/*
 * Created on May 27, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.approval;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR010001Message;
import datapro.eibs.beans.EPR012001Message;
import datapro.eibs.beans.EPR012002Message;
import datapro.eibs.beans.EPR040001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.JBSortList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.Util;
import datapro.eibs.products.JOActionRedirect;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEPR1080A extends JSEIBSServlet {

	private static final int R_APPROVAL			= 5;
	private static final int A_APPROVAL			= 2;
	private static final int R_APPROVAL_INQ		= 3;
	private static final int A_REVERSAL			= 7;	
	private static final int R_PASSWORD			= 1;
	private static final int R_PASSWORD_FILTRO	= 15;
	private static final int R_REVERSAL			= 6;
	private static final int C_SWIFT			= 8;
	private static final int A_APPROVAL_BANK	= 9;
	private static final int R_APPROVAL_BANK	= 10;
	private static final int R_PASSWORD_BANK	= 11;
	private static final int R_REVERSAL_BANK	= 12;
	private static final int R_ENTER_BANK		= 20;
	
	private static final int R_APP_INQ_ADD_TR	= 200;
	private static final int R_APP_INQ_MAINT	= 100;
	private static final int R_PRIORITY			= 1000;

	private static final int PREVPAGE 			= 500;
	private static final int NEXTPAGE 			= 501;
	private static final int PREVPAGE_BANK 		= 600;
	private static final int NEXTPAGE_BANK 		= 601;

	private static final int A_SORT 			= 502;
	private static final int A_SORT_DATE 		= 503;
	
	
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
				case R_PASSWORD_FILTRO :
					procReqPasswordFiltro(req, res);
					break;
				case R_PASSWORD :
					procReqPassword(req, res, session);
					break;
				case R_PASSWORD_BANK :
					procReqPasswordBank(req, res, session);
					break;
				case R_PRIORITY :
					procReqChangePriority(req, res);
					break;
				case R_APPROVAL :
					procReqApproval(user, req, res, session);
					break;
				case R_ENTER_BANK :
					procReqEnterBankAction(req, res);
					break;
				case R_APPROVAL_BANK :
					procReqApprovalBank(user, req, res, session);
					break;
				case A_APPROVAL :
					procActionApproval(user, req, res, session);
					break;
				case A_APPROVAL_BANK:
					procActionApprovalBank(user, req, res, session);
					break;
				case R_REVERSAL :
					procReqReversal(user, req, res, session);
					break;
				case R_REVERSAL_BANK :
					procReqReversalBank(user, req, res, session);
					break;
				case A_REVERSAL :
					procActionReversal(user, req, res, session);
					break;
				case R_APPROVAL_INQ :
					procReqApprovalInq(user, req, res, session);
					break;
				case R_APP_INQ_MAINT :
					procReqAppMaint(user, req, res, session);
					break;
				case R_APP_INQ_ADD_TR :
					procReqAddTransaction(user, req, res, session);
					break;
				case C_SWIFT :
					procReqConsultaSwift(user, req, res, session);
					break;
				case PREVPAGE :
					procPrevPage(req, res, session);
					break;
				case NEXTPAGE :
					procNextPage(req, res, session);
					break;
				case PREVPAGE_BANK :
					procPrevPageBank(req, res, session);
					break;
				case NEXTPAGE_BANK :
					procNextPageBank(req, res, session);
					break;
				case A_SORT :
					sort(req, res, session);
					break;
				case A_SORT_DATE :
					sortDate(req, res, session);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
			}
	}
	/**
	 * @param req
	 * @param res
	 */
	private void sortDate(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		String  Approv = (String) session.getAttribute("Approv");
		String dd = (req.getParameter("DAY") == null) ? "" : req.getParameter("DAY");
		String mm = (req.getParameter("MONTH") == null) ? "" : req.getParameter("MONTH");
		String yy = (req.getParameter("YEAR") == null) ? "" : req.getParameter("YEAR");		
		if (!dd.equals("")) {
			lista.setDateKey(dd,mm,yy);
			int orden = lista.getSortOrder();
			if (orden == JBSortList.ASCENDENTE) {
				lista.setSortOrder(JBSortList.DESCENDENTE);
			} else {
				lista.setSortOrder(JBSortList.ASCENDENTE);
			}
			lista.sort();
		}
		session.setAttribute("lista", lista); 
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list_bank.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param req
	 * @param res
	 */
	private void sort(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		String  Approv = (String) session.getAttribute("Approv");
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
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list_bank.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procNextPageBank(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setNextPage();
		String  Approv = (String) session.getAttribute("Approv");
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list_bank.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procPrevPageBank(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setPrevPage();
		String  Approv = (String) session.getAttribute("Approv");
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list_bank.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procNextPage(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setNextPage();
		String  Approv = (String) session.getAttribute("Approv");
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procPrevPage(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		JBSortList lista = (JBSortList) session.getAttribute("lista");
		lista.setPrevPage();
		String  Approv = (String) session.getAttribute("Approv");
		String PageToCall = "";
		if (Approv.equals("A")) {
			PageToCall = "EPR1080_pr_approval_list_autor.jsp";
		} else 
			if (Approv.equals("B")) {
				PageToCall = "EPR1080_pr_approval_list_liber.jsp";
			} else {
				PageToCall = "EPR1080_pr_approval_list.jsp";
			}
		forward(PageToCall, req, res);
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqConsultaSwift(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0400", req);
			EPR040001Message msg = (EPR040001Message) mp.getMessageRecord("EPR040001", user.getH01USR(), "");
			try {
				msg.setE01SWOACC(req.getParameter("TRANSREFNUM"));
			} catch (Exception e) {
				try{
					msg.setE01SWOACC(userPO.getAccNum());
				}catch(Exception e2){}
			}
			mp.sendMessage(msg);
		
			MessageRecord newmsg = mp.receiveMessageRecord();
			ELEERRMessage msgError = new ELEERRMessage();
			if (mp.hasError(newmsg)) {
				msgError = (ELEERRMessage) newmsg;
			} 
			newmsg = mp.receiveMessageRecord();
			JBList list = new JBList();
			StringBuffer myRow = null;
			int indexRow = 0;
			while (true) {
				msg = (EPR040001Message) newmsg;
				if (msg.getH01FLGWK1().equals("*") || msg.getE01INDOPE().equals("*")) {
					break;
				} else {
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP>" + msg.getE01SWOTXT()+" </TD>");
					list.addRow(msg.getH01FLGWK1(),myRow.toString());				
				}
				newmsg = mp.receiveMessageRecord();
			}
			session.setAttribute("listaswift", list);
			session.setAttribute("error", msgError);
			
			forward("EPR04000_msg_swift.jsp", req, res);
				
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
	private void procReqAddTransaction(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		DataTransaction transData = new DataTransaction();
		transData.setWrkFile("4");
		transData.setAccNum(userPO.getIdentifier());
		
		flexLog("Putting java beans into the session");
		session.setAttribute("transData", transData);
		
		redirect("/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=1", res);						
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqAppMaint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		if (!(req.getParameter("REFNUM") == null)) {
		 	userPO.setIdentifier(req.getParameter("REFNUM"));
		}  
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message msg = (EPR010001Message) mp.getMessageRecord("EPR010001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01PRINUM(userPO.getIdentifier());
			mp.sendMessage(msg);
		
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord("EPR010001");
			
			if (mp.hasError(msgError)) {
				PageToCall = "error_viewer.jsp";
			} else {
				session.setAttribute("prMant", msg);
				PageToCall = "EPR0000_pr_ap_maint.jsp";
			}
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			
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
	private void procReqApprovalInq(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String refNum = (req.getParameter("REFNUM") == null) ? "" : req.getParameter("REFNUM");
		JOActionRedirect red =
						new datapro.eibs.products.JOActionRedirect(
							ACC_APPROVAL_INQ,
							6,
							refNum,
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
	private void procActionReversal(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		MessageProcessor mp = null;
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012002Message msg = (EPR012002Message) mp.getMessageRecord("EPR012002", user.getH01USR(), "");
			try {
				msg.setE02PRINUM(req.getParameter("REFNUM"));
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
			msg.setH02FLGWK3("R");
			
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			if (mp.hasError(msgError)) {
				forward("EPR1080_pr_reversal_list.jsp", req, res);
			} else {
				redirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&OPT=R", res);
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
	private void procReqReversalBank(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String cheker = (req.getParameter("DATES") == null) ? "" : req.getParameter("DATES");
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "");
			msg.setH01SCRCOD("R");
			msg.setH01FLGWK1("X");
			
			if (cheker.equals("")) {
				msg.setE01PRITDM("0");
				msg.setE01PRITDD("0");
				msg.setE01PRITDY("0");
				msg.setH01OPECOD("");
			} else {
				try{	
					msg.setE01PRITDM(req.getParameter("E01PRITDM"));
					msg.setE01PRITDD(req.getParameter("E01PRITDD"));
					msg.setE01PRITDY(req.getParameter("E01PRITDY"));
					msg.setH01OPECOD("0001");
				} catch (Exception ex) {
					msg.setE01PRITDM("0");
					msg.setE01PRITDD("0");
					msg.setE01PRITDY("0");
					msg.setH01OPECOD("");
				}
			}
			try {
				msg.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				msg.setE01NUMREC("0");
			}
		 	
			userPO.setHeader1(msg.getE01PRITDM()); //Month
			userPO.setHeader2(msg.getE01PRITDD()); //Day
			userPO.setHeader3(msg.getE01PRITDY()); //Year
		
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			ELEERRMessage msgError = new ELEERRMessage();
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				boolean firstTime = true;
				String marker = "";
				StringBuffer myRow = null;
				String chk = "";
				String priNum = req.getParameter("PRINUM");
				String chkOfac = "";
				int indexRow = 0;
				JBObjList beanList = new JBObjList();
				list.initRow();
				while (list.getNextRow()) {
					msg = (EPR012001Message) list.getRecord();
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msg.getE01NUMREC()));
						chk = "checked";
					} else {
						if (msg.getE01PRINUM().trim().equals(priNum))
							chk = "checked";
						else 
							chk = "";
					}
					// Add OFAC status : H01FLGWK3 = '3'
					chkOfac = (msg.getH01FLGWK3().equals("3") ? "<a href=\"javascript:showInqOFAC('" + msg.getE01PRINUM() + "')\"><img src=\"../images/warning_16.jpg\" alt=\"OFAC Posible Coincidencia\" align=\"middle\" border=\"0\" ></a>" : "");					
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\"" + msg.getE01PRINUM() + "\" " + chk + " onclick=\"showAddInfo('"+ msg.getE01PRINUM() +"',"+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01PRINUM()) + "</A>" + chkOfac +"</TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01PRIOCU()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01CUSNA1()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01REMARK()) + "</A>");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.formatDate(msg.getE01PRITDM(),msg.getE01PRITDD(),msg.getE01PRITDY()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIDAC()) + "<br>");
					myRow.append(Util.fcolorCCY(msg.getE01PRIAMT()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRICCY()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIOBN()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIOBR()) + "<br>");					
					myRow.append(Util.formatCell(msg.getE01BTHNUM()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIUSR()) + "\"></TD>");
					myRow.append("</TR>");
					beanList.addRow(myRow.toString());
					indexRow ++;
				}
				beanList.setShowNext(list.getShowNext());
				if (beanList.getNoResult()) {
					PageToCall = "MISC_no_result.jsp";
				} else {
					userPO.setPurpose("REVERSAL");
					userPO.setOption("PR");
					session.setAttribute("appList", beanList);
					PageToCall = "EPR1080_pr_reversal_list.jsp";
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
	private void procReqReversal(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String cheker = (req.getParameter("DATES") == null) ? "" : req.getParameter("DATES");
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "");
			msg.setH01SCRCOD("R");
			if (cheker.equals("")) {
			  	msg.setE01PRITDM("0");
			  	msg.setE01PRITDD("0");
			  	msg.setE01PRITDY("0");
			  	msg.setH01OPECOD("");
			} else {
			  	try{	
					msg.setE01PRITDM(req.getParameter("E01PRITDM"));
				  	msg.setE01PRITDD(req.getParameter("E01PRITDD"));
				  	msg.setE01PRITDY(req.getParameter("E01PRITDY"));
				  	msg.setH01OPECOD("0001");
				} catch (Exception ex) {
				  	msg.setE01PRITDM("0");
				  	msg.setE01PRITDD("0");
				  	msg.setE01PRITDY("0");
				  	msg.setH01OPECOD("");
				}
			}
			try {
				msg.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				msg.setE01NUMREC("0");
			}
		 	
			userPO.setHeader1(msg.getE01PRITDM()); //Month
			userPO.setHeader2(msg.getE01PRITDD()); //Day
			userPO.setHeader3(msg.getE01PRITDY()); //Year
		
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			ELEERRMessage msgError = new ELEERRMessage();
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				boolean firstTime = true;
				String marker = "";
				StringBuffer myRow = null;
				String chk = "";
				String priNum = req.getParameter("PRINUM");
				String chkOfac = "";
				int indexRow = 0;
				JBObjList beanList = new JBObjList();
				list.initRow();
				while (list.getNextRow()) {
					msg = (EPR012001Message) list.getRecord();
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msg.getE01NUMREC()));
						chk = "checked";
					} else {
						if (msg.getE01PRINUM().trim().equals(priNum))
							chk = "checked";
						else 
							chk = "";
					}
					// Add OFAC status : H01FLGWK3 = '3'
					chkOfac = (msg.getH01FLGWK3().equals("3") ? "<a href=\"javascript:showInqOFAC('" + msg.getE01PRINUM() + "')\"><img src=\"../images/warning_16.jpg\" alt=\"OFAC Posible Coincidencia\" align=\"middle\" border=\"0\" ></a>" : "");					
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\"" + msg.getE01PRINUM() + "\" " + chk + " onclick=\"showAddInfo('"+ msg.getE01PRINUM() +"',"+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01PRINUM()) + "</A>" + chkOfac +"</TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01PRIOCU()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01CUSNA1()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPR('"+ msg.getE01PRINUM() +"')\">" + Util.formatCell(msg.getE01REMARK()) + "</A>");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.formatDate(msg.getE01PRITDM(),msg.getE01PRITDD(),msg.getE01PRITDY()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIDAC()) + "<br>");
					myRow.append(Util.fcolorCCY(msg.getE01PRIAMT()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRICCY()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIOBN()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIOBR()) + "<br>");					
					myRow.append(Util.formatCell(msg.getE01BTHNUM()) + "<br>");
					myRow.append(Util.formatCell(msg.getE01PRIUSR()) + "\"></TD>");
					myRow.append("</TR>");
					beanList.addRow(myRow.toString());
					indexRow ++;
				}
				beanList.setShowNext(list.getShowNext());
				if (beanList.getNoResult()) {
					PageToCall = "MISC_no_result.jsp";
				} else {
					userPO.setPurpose("REVERSAL");
					userPO.setOption("PR");
					session.setAttribute("appList", beanList);
					PageToCall = "EPR1080_pr_reversal_list.jsp";
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
	private void procActionApprovalBank(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012002Message msg = (EPR012002Message) mp.getMessageRecord("EPR012002", user.getH01USR(), "");
			setMessageRecord(req, msg);
			try {
				msg.setE02PRISID(req.getParameter("TRANSREFNUM"));
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
			try {
				msg.setH02FLGWK3(req.getParameter("approv"));
			} catch (Exception e) {
				msg.setH02FLGWK3("");
			}
			try {
				msg.setH02FLGWK2("X");
			} catch (Exception e) {
			
			}
			try {
				msg.setH02FLGWK1(req.getParameter("H02FLGWK1"));
			} catch (Exception e) {
			}
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			String approv = (req.getParameter("approv") == null) ? "" : req.getParameter("approv");
			if (mp.hasError(msgError)) {
				String erwrng= (msgError.getERWRNG().equals("Y") ||msgError.getERNU01().equals("6026")) ? "S" : "";
				if (approv.equals("A")){
					PageToCall = "EPR1080_pr_approval_list_autor.jsp";
					userPO.setPurpose("AUTOR");
				} else if (approv.equals("B")){
					PageToCall = "EPR1080_pr_approval_list_liber.jsp";
					userPO.setPurpose("LIBER");
				} else {
					PageToCall = "EPR1080_pr_approval_list_bank.jsp?ACCNUM=" + req.getParameter("TRANSREFNUM") + "&ERWRNG=" + erwrng;
					userPO.setPurpose("APPROVAL");
					userPO.setOption("PR");						
				}
				forward(PageToCall, req, res);
			} else {
				redirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=11&approv="+approv, res);
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
	private void procActionApproval(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012002Message msg = (EPR012002Message) mp.getMessageRecord("EPR012002", user.getH01USR(), "");
			try {
				msg.setE02PRINUM(req.getParameter("TRANSREFNUM"));
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
			try {
				msg.setH02FLGWK3(req.getParameter("approv"));
			} catch (Exception e) {
				msg.setH02FLGWK3("");
			}
			try {
				msg.setH02FLGWK1(req.getParameter("H02FLGWK1"));
			} catch (Exception e) {
			}
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			String approv = (req.getParameter("approv") == null) ? "" : req.getParameter("approv");
			
			flexLog(msgError.toString());
			session.setAttribute("error", msgError);
			
			if (mp.hasError(msgError)) {
				String erwrng= (msgError.getERWRNG().equals("Y") ||msgError.getERNU01().equals("6026")) ? "S" : "";
				if (approv.equals("A")){
					PageToCall = "EPR1080_pr_approval_list_autor.jsp";
					userPO.setPurpose("AUTOR"); 
				} else if (approv.equals("B")){
					PageToCall = "EPR1080_pr_approval_list_liber.jsp";
					userPO.setPurpose("LIBER");
				} else {
					PageToCall = "EPR1080_pr_approval_list.jsp?ACCNUM=" + req.getParameter("TRANSREFNUM") + "&ERWRNG=" + erwrng;
					userPO.setPurpose("APPROVAL");
					userPO.setOption("PR");						
				}
				forward(PageToCall, req, res);
			} else {
			     redirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&approv="+approv+"&TYP="+type, res);
				if (approv.equals("A")){
					PageToCall = "EPR1080_pr_approval_list_autor.jsp";
					userPO.setPurpose("AUTOR");
				} else if (approv.equals("B")){
					PageToCall = "EPR1080_pr_approval_list_liber.jsp";
					userPO.setPurpose("LIBER");
				} else {
					PageToCall = "EPR1080_pr_approval_list.jsp?ACCNUM=" + req.getParameter("TRANSREFNUM");
					userPO.setPurpose("APPROVAL");
					userPO.setOption("PR");						
				}
				// forward(PageToCall, req, res);
				// redirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&approv="+approv+"&TYP="+type, res);
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
	private void procReqApprovalBank(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		
		String sMes = (req.getParameter("E01CUTDTM") == null) ? "" : req.getParameter("E01CUTDTM");
		String sAno = (req.getParameter("E01CUTDTY") == null) ? "" : req.getParameter("E01CUTDTY");
		String sDia = (req.getParameter("E01CUTDTD") == null) ? "" : req.getParameter("E01CUTDTD");
		String sTime = (req.getParameter("E01CUTTIM") == null) ? "" : req.getParameter("E01CUTTIM");
		String account = (req.getParameter("ACCNUM1") == null) ? "" : req.getParameter("ACCNUM1");	
		String Approv = (req.getParameter("approv") == null) ? "" : req.getParameter("approv");
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "0015");
			msg.setE01CUTDTD(sDia);
			msg.setE01CUTDTM(sMes);
			msg.setE01CUTDTY(sAno);
			msg.setE01CUTTIM(sTime);
			msg.setH01SCRCOD(Approv);
			msg.setH01FLGWK1("X");
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
					if (Approv.equals("A")){
						PageToCall = "EPR1080_pr_approval_list_autor.jsp";
						userPO.setPurpose("AUTOR");
					} else if (Approv.equals("B")){
						PageToCall = "EPR1080_pr_approval_list_liber.jsp";
						userPO.setPurpose("LIBER");
					} else {
						PageToCall = "EPR1080_pr_approval_list_bank.jsp";
						userPO.setPurpose("APPROVAL");
						userPO.setOption("PR");						
					}
					lista.setDisplaySize(24);
					session.setAttribute("lista", lista);
					session.setAttribute("Approv", Approv);
					session.setAttribute("day", sDia);
					session.setAttribute("month", sMes);
					session.setAttribute("year", sAno);
					session.setAttribute("time", sTime);
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
	private void procReqEnterBankAction(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String Param1 = (req.getParameter("ACCNUM") == null) ? "" : "&ACCNUM=" + req.getParameter("ACCNUM");
		String Param2 = (req.getParameter("approv") == null) ? "" : "&approv=" + req.getParameter("approv");
		String Screen = (req.getParameter("OPT") == null) ? String.valueOf(R_APPROVAL_BANK) : "" + req.getParameter("OPT");
		String PageToCall = "EPR1080_pr_approval_enter.jsp?SCREEN=" + Screen + "&ACCNUM=" + Param1 + "&approv=" + Param2;
		forward(PageToCall, req, res);
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
		
		String type = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP");
		String account = (req.getParameter("ACCNUM1") == null) ? "" : req.getParameter("ACCNUM1");
		String flg = (req.getParameter("FLG") == null) ? "" : req.getParameter("FLG");
		String Approv = (req.getParameter("approv") == null) ? "" : req.getParameter("approv");;
		
		try {
			mp = getMessageProcessor("EPR0120", req);
			EPR012001Message msg = (EPR012001Message) mp.getMessageRecord("EPR012001", user.getH01USR(), "");
			msg.setE01PRITYP(type);
			if (flg.equals("L")) {
				String sid = (req.getParameter("SID") == null) ? "" : req.getParameter("SID");
				msg.setE01PRISID(sid);
				msg.setH01FLGWK1(flg);
			}
			msg.setH01SCRCOD(Approv);
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
					if (Approv.equals("A")){
						PageToCall = "EPR1080_pr_approval_list_autor.jsp";
						userPO.setPurpose("AUTOR");
					} else if (Approv.equals("B")){
						PageToCall = "EPR1080_pr_approval_list_liber.jsp";
						userPO.setPurpose("LIBER");
					} else {
						PageToCall = "EPR1080_pr_approval_list.jsp";
						userPO.setPurpose("APPROVAL");
						userPO.setOption("PR");						
					}
					lista.setDisplaySize(24);
					session.setAttribute("lista", lista);
					session.setAttribute("Approv", Approv);
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
	private void procReqChangePriority(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String refnum = (req.getParameter("REFNUM") == null) ? "" : req.getParameter("REFNUM");
		String pri = (req.getParameter("E01PRIPTY") == null) ? "" : req.getParameter("E01PRIPTY");
		
		redirect("/servlet/datapro.eibs.products.JSEPR0000?SCREEN=1000&TRANSREFNUM=" + refnum + "&E01PRIPTY=" + pri, res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqPasswordBank(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		String Param1 = (req.getParameter("ACCNUM") == null) ? "" : "&ACCNUM=" + req.getParameter("ACCNUM");
		String Param2 = (req.getParameter("approv") == null) ? "" : "&approv=" + req.getParameter("approv");
		String Param3 = (req.getParameter("OPT") == null) ? "&OPT=" + R_APPROVAL_BANK : "&OPT=" + R_REVERSAL_BANK;
		String Screen = String.valueOf(R_ENTER_BANK);
		
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=" + Screen + Param1 + Param2 + Param3);
		session.setAttribute("userPO", userPO);
		
		redirect("/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7", res);
	}
	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		String Param1 = (req.getParameter("ACCNUM") == null) ? "" : "&ACCNUM=" + req.getParameter("ACCNUM");
		String Param2 = (req.getParameter("approv") == null) ? "" : "&approv=" + req.getParameter("approv");
		String Param3 = (req.getParameter("FLG") == null) ? "" : "&FLG=" + req.getParameter("FLG");
		String Param4 = (req.getParameter("SID") == null) ? "" : "&SID=" + req.getParameter("SID");
		String Screen = (req.getParameter("OPT") == null) ? "" + R_APPROVAL : "" + R_REVERSAL;
		String Tipo = (req.getParameter("TYP") == null) ? "" : "&TYP=" + req.getParameter("TYP");
		
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=" + Screen + Param1 + Param2 + Param3 + Param4 + Tipo);
		session.setAttribute("userPO", userPO);
		
		redirect("/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7", res);
	}
	/**
	 * @param req
	 * @param res
	 */
	private void procReqPasswordFiltro(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		forward("EPR1080_pr_ap_enter.jsp", req, res);
	}

}
