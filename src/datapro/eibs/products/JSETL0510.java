/*
 * Created on Nov 24, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETL051001Message;
import datapro.eibs.beans.ETL051002Message;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSETL0510 extends JSEIBSServlet {

	// CIF options
	protected static final int R_LIST 				= 1;
	protected static final int R_DET	 			= 3;
	protected static final int R_STOP				= 5;
	protected static final int R_LIST_STOP			= 6;
	protected static final int R_DET_CHK 			= 7;	

	protected static final int R_LIST_CANCEL		= 10;	
	
	// entering options
	protected static final int R_ENTER 				= 100;
	protected static final int R_CHK 				= 300;
	
	protected static final int A_ENTER 				= 200;
	protected static final int A_CHK 				= 400;
	
	protected static final int R_LIST_HELP 			= 1000;
	
	
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
				case R_LIST :
					procReqList(user, req, res, session);
					break;
				case R_DET :
					procReqDocDet(user, req, res, session);
					break;
				case R_DET_CHK :
					procReqChkDet(user, req, res, session);
					break;
					
				//entering options
				case R_ENTER :
					procReqSTEnterSearch(user, req, res, session);
					break;
				case R_CHK :
					procReqOFEnterSearch(user, req, res, session);
					break;
				case R_STOP :
					procReqStopSel(user, req, res, session);
					break;
				case R_LIST_STOP :
					procReqListStop(user, req, res, session);
					break;
				case R_LIST_CANCEL :
					procReqListCancel(user, req, res, session);
					break;
				case R_LIST_HELP :
					procReqListHelp(user, req, res, session);
					break;
	
				case A_ENTER :
					procActionSTEnterSearch(user, req, res, session);
					break;
				case A_CHK :
					procActionOFEnterSearch(user, req, res, session);
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
	private void procActionOFEnterSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos	userPO = getUserPos(session);
		try {
			userPO.setHeader1("1");
		} catch (Exception e){
			userPO.setHeader1("");
		}
		try {
			userPO.setHeader2(req.getParameter("E01SELSCH").toUpperCase());
		} catch (Exception e){
			userPO.setHeader2("");
		}
		try {
			userPO.setHeader3(req.getParameter("E01SELNCH"));
		} catch (Exception e){
			userPO.setHeader3("");
		}
		try {
			userPO.setHeader4(req.getParameter("E01SELACC"));
		} catch (Exception e){
			userPO.setHeader4("");
		}
		try {
			userPO.setHeader5(req.getParameter("E01SELBNF").toUpperCase());
		} catch (Exception e){
			userPO.setHeader5("");
		}
		try {
			userPO.setHeader6(req.getParameter("E01SELAPL").toUpperCase());
		} catch (Exception e){
			userPO.setHeader6("");
		}
		
		flexLog("Putting java beans into the session");
		session.setAttribute("userPO", userPO);


		procReqList(user, req, res, session);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionSTEnterSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos	userPO = getUserPos(session);
		try {
			userPO.setHeader1(req.getParameter("E01SELDTY").toUpperCase());
		} catch (Exception e){
			userPO.setHeader1("");
		}
		try {
			userPO.setHeader2(req.getParameter("E01SELSCH").toUpperCase());
		} catch (Exception e){
			userPO.setHeader2("");
		}
		try {
			userPO.setHeader3(req.getParameter("E01SELNCH"));
		} catch (Exception e){
			userPO.setHeader3("");
		}
		try {
			userPO.setHeader4(req.getParameter("E01SELACC"));
		} catch (Exception e){
			userPO.setHeader4("");
		}
		try {
			userPO.setHeader5(req.getParameter("E01SELBNF").toUpperCase());
		} catch (Exception e){
			userPO.setHeader5("");
		}
		try {
			userPO.setHeader6(req.getParameter("E01SELAPL").toUpperCase());
		} catch (Exception e){
			userPO.setHeader6("");
		}
		
		flexLog("Putting java beans into the session");
		session.setAttribute("userPO", userPO);

		procReqList(user, req, res, session);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqListHelp(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "";
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051001Message msg = (ETL051001Message) mp.getMessageRecord("ETL051001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01OFMBNK(user.getE01UBK());
			String type = (req.getParameter("E01SELDTY") == null || req.getParameter("E01SELDTY").equals("")) ? "1" : req.getParameter("E01SELDTY");
			msg.setE01SELDTY(type);
			String status = (req.getParameter("E01SELSCH") == null || req.getParameter("E01SELSCH").equals("")) ? "D" : req.getParameter("E01SELSCH"); 	 	
			msg.setE01SELSCH(status);
			String position = (req.getParameter("Pos") == null || req.getParameter("Pos").equals("")) ? "0" : req.getParameter("Pos");
			msg.setE01NUMREC(position);
			String account = (req.getParameter("E01SELACC") == null) ? "" : req.getParameter("E01SELACC");
			msg.setE01SELACC(account);
		
			mp.sendMessage(msg);
		
			JBObjList list = mp.receiveMessageRecordList("E01INDOPE");
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				session.setAttribute("dvList", list);
				req.setAttribute("E01SELDTY", type);
				req.setAttribute("E01SELSCH", status);
				PageToCall = "ETL0510_chk_off_help.jsp";
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
	private void procReqListCancel(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "";
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051001Message msg = (ETL051001Message) mp.getMessageRecord("ETL051001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01OFMBNK(user.getE01UBK());
			msg.setE01SELDTY("1");	 	
			msg.setE01SELSCH("D");
			String position = (req.getParameter("Pos") == null) ? "0" : req.getParameter("Pos");
			msg.setE01NUMREC(position);
		
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("E01INDOPE");
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				list.initRow();
				JBList beanList = new JBList();
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				while (list.getNextRow()) {
					msg = (ETL051001Message) list.getRecord();
					marker = msg.getE01INDOPE();
					if (firstTime) {
						firstTime = false;
						position = (msg.getE01NUMREC().equals("")) ? "0" : msg.getE01NUMREC();
						beanList.setFirstRec(Integer.parseInt(position));
						chk = "checked";
					} else {
						chk = "";
					}				
					String showStopOff = "showChkCanDet('" + msg.getE01OFMBNK() + "', '" + msg.getE01OFMBRN() + "', '" + msg.getE01OFMCCY() + "', '" + msg.getE01OFMMCH() + "', '" + msg.getE01OFMNCH() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatDate(msg.getE01OFMID1(), msg.getE01OFMID2(), msg.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMBNF()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
				}
				beanList.setShowNext(marker.equals("+"));
				session.setAttribute("dvList", beanList);
				PageToCall = "ETL0510_of_chk_list_cancel.jsp";
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
	private void procReqListStop(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "";
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051001Message msg = (ETL051001Message) mp.getMessageRecord("ETL051001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setE01OFMBNK(user.getE01UBK());
			msg.setE01SELDTY("1");
			msg.setE01SELSCH("D");
			String position = (req.getParameter("Pos") == null) ? "0" : req.getParameter("Pos");
			msg.setE01NUMREC(position);
			
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("E01INDOPE");
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				list.initRow();
				JBList beanList = new JBList();
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				while (list.getNextRow()) {
					msg = (ETL051001Message) list.getRecord();
					marker = msg.getE01INDOPE();
					if (firstTime) {
						firstTime = false;
						position = (msg.getE01NUMREC().equals("")) ? "0" : msg.getE01NUMREC();
						beanList.setFirstRec(Integer.parseInt(position));
						chk = "checked";
					} else {
						chk = "";
					}				
					String showStopOff = "setChkDet('" + msg.getE01OFMBNK() + "', '" + msg.getE01OFMBRN() + "', '" + msg.getE01OFMCCY() + "', '" + msg.getE01OFMMCH() + "', '" + msg.getE01OFMNCH() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatDate(msg.getE01OFMID1(), msg.getE01OFMID2(), msg.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showStopOff + "\">" + Util.formatCell(msg.getE01OFMBNF()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
				}
				beanList.setShowNext(marker.equals("+"));
				session.setAttribute("dvList", beanList);
				PageToCall = "ETL0510_of_chk_list.jsp";
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
	private void procReqStopSel(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		userPO.setOption("OCK");
		userPO.setPurpose("STOP_PAYMENT");
		session.setAttribute("error", msgError);
		session.setAttribute("userPO", userPO);
		forward("EFE01000_off_enter_stop_pay.jsp", req, res);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqOFEnterSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		userPO.setOption("OF");
		userPO.setPurpose("INQUIRY");
		session.setAttribute("error", msgError);
		session.setAttribute("userPO", userPO);
		forward("ETL0510_chk_off_inq_sel.jsp", req, res);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqSTEnterSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		userPO.setOption("DV");
		userPO.setPurpose("INQUIRY");
		session.setAttribute("error", msgError);
		session.setAttribute("userPO", userPO);
		forward("ETL0510_dv_inq_sel.jsp", req, res);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqChkDet(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051002Message msg = (ETL051002Message) mp.getMessageRecord("ETL051002", user.getH01USR(), "0004");
		
			String bank = (req.getParameter("BNK") == null) ? "01" : req.getParameter("BNK");
			msg.setE02OFMBNK(bank);
			String branch = (req.getParameter("BRN") == null) ? "0" : req.getParameter("BRN");
			msg.setE02OFMBRN(branch);
			String currency = (req.getParameter("CCY") == null) ? "" : req.getParameter("CCY");
			msg.setE02OFMCCY(currency);
			String amount = (req.getParameter("AMT") == null) ? "0.00" : req.getParameter("AMT");
			msg.setE02OFMMCH(amount);
			String check = (req.getParameter("CHK") == null) ? "" : req.getParameter("CHK");
			msg.setE02OFMNCH(check);
			
			mp.sendMessage(msg);
			
			MessageRecord newmsg = mp.receiveMessageRecord();
			
			if (mp.hasError(newmsg)) {
				msgError = (ELEERRMessage) newmsg;
				session.setAttribute("error", msgError);
				res.setContentType("text/html");
				printClose(res.getWriter());
			} else {
				msg = (ETL051002Message) newmsg; 
				session.setAttribute("dvDocDet", msg);
				session.setAttribute("error", msgError);
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0100?SCREEN=7");	
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
	private void procReqDocDet(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "";
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051002Message msg = (ETL051002Message) mp.getMessageRecord("ETL051002", user.getH01USR(), "0004");
			
			String bank = (req.getParameter("BNK") == null) ? "01" : req.getParameter("BNK");
			msg.setE02OFMBNK(bank);
			String branch = (req.getParameter("BRN") == null) ? "0" : req.getParameter("BRN");
			msg.setE02OFMBRN(branch);
			String currency = (req.getParameter("CCY") == null) ? "" : req.getParameter("CCY");
			msg.setE02OFMCCY(currency);
			String amount = (req.getParameter("AMT") == null) ? "0.00" : req.getParameter("AMT");
			msg.setE02OFMMCH(amount);
			String check = (req.getParameter("CHK") == null) ? "" : req.getParameter("CHK");
			msg.setE02OFMNCH(check);
			
			mp.sendMessage(msg);
			
			MessageRecord newmsg = mp.receiveMessageRecord();
			
			if (mp.hasError(newmsg)) {
				msgError = (ELEERRMessage) newmsg;
				session.setAttribute("error", msgError);
				res.setContentType("text/html");
				printClose(res.getWriter());
			} else {
				msg = (ETL051002Message) newmsg; 
				session.setAttribute("dvDocDet", msg);
				session.setAttribute("error", msgError);
				if (msg.getE02OFMDTY().equals("1")) { //Offical Check
					PageToCall = "ETL0510_chk_off_inq_det.jsp";	
				} else {			
					PageToCall = "ETL0510_dv_inq_doc_det.jsp";	
				}
				forward(PageToCall, req, res);
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
	private void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "";
		UserPos	userPO = getUserPos(session);
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("ETL0510");
			ETL051001Message msg = (ETL051001Message) mp.getMessageRecord("ETL051001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			String position = (req.getParameter("Pos") == null) ? "0" : req.getParameter("Pos");
			msg.setE01NUMREC(position);
			if (userPO.getHeader1() != null) msg.setE01SELDTY(userPO.getHeader1());
			if (userPO.getHeader2() != null) msg.setE01SELSCH(userPO.getHeader2());
			if (userPO.getHeader3() != null) msg.setE01SELNCH(userPO.getHeader3());
			if (userPO.getHeader4() != null) msg.setE01SELACC(userPO.getHeader4());
			if (userPO.getHeader5() != null) msg.setE01SELBNF(userPO.getHeader5());
			if (userPO.getHeader6() != null) msg.setE01SELAPL(userPO.getHeader6());
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("E01INDOPE");
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				if (userPO.getOption().equals("OF")) {
					PageToCall = "ETL0510_chk_off_inq_sel.jsp";
				} else { 
					PageToCall = "ETL0510_dv_inq_sel.jsp";
				}
			} else {
				list.initRow();
				JBList beanList = new JBList();
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				while (list.getNextRow()) {
					msg = (ETL051001Message) list.getRecord();
					marker = msg.getE01INDOPE();
					if (firstTime) {
						firstTime = false;
						position = (msg.getE01NUMREC().equals("")) ? "0" : msg.getE01NUMREC();
						beanList.setFirstRec(Integer.parseInt(position));
						chk = "checked";
					} else {
						chk = "";
					}				
					String showDocFunc = "showDocDet('" + msg.getE01OFMBNK() + "', '" + msg.getE01OFMBRN() + "', '" + msg.getE01OFMCCY() + "', '" + msg.getE01OFMMCH() + "', '" + msg.getE01OFMNCH() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatDate(msg.getE01OFMID1(), msg.getE01OFMID2(), msg.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showDocFunc + "\">" + Util.formatCell(msg.getE01OFMBNF()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
				}
				beanList.setShowNext(marker.equals("+"));
				session.setAttribute("dvList", beanList);
				
				if (position.equals("0") && beanList.getLastRec() == 1) {
					
					String page = "/servlet/datapro.eibs.products.JSETL0510?SCREEN=3&BNK=" + msg.getE01OFMBNK() + "&BRN=" + msg.getE01OFMBRN() + "&CCY=" + msg.getE01OFMCCY() + "&AMT=" + msg.getE01OFMMCH() + "&CHK=" + msg.getE01OFMNCH();
					res.setContentType("text/html");
					PrintWriter  out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		window.location.href='" + super.webAppPath + page + "'");					
					out.println("</SCRIPT>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
					
				} else {
					PageToCall = "ETL0510_dv_inq_list_doc.jsp";	
				}
			}
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			
			forward(PageToCall, req, res);
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

}
