/*
 * Created on Jun 5, 2009
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

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR010001Message;
import datapro.eibs.beans.EPR010002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEPR0000 extends JSEIBSServlet {

	private static final int R_NEW 			= 1;
	private static final int A_NEW 			= 2;
	private static final int R_MAINTENANCE 	= 3;
	private static final int A_MAINTENANCE 	= 4;
	private static final int R_ADD_TR 		= 5;
	private static final int A_INCOMING 	= 6;
	private static final int A_LIST_REF 	= 100;
	private static final int A_REQ_REF 		= 200;
	private static final int A_MAINT_REF 	= 201;
	private static final int A_REF 			= 202;
	private static final int A_REF_DETAIL 	= 203;
	private static final int R_PRIORITY 	= 1000;
	private static final int A_PRIORITY 	= 2000;

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
				// Request
				case R_NEW :
					procReqNew(user, req, res, session);
					break;
				case R_MAINTENANCE :
					procReqMaintenance(user, req, res, session);
					break;
				case R_PRIORITY :
					procReqChangePriority(user, req, res, session);
					break;								
					// Action
				case A_NEW :
					procActionNew(user, req, res, session);
					break;
				case A_PRIORITY :
					procActionChangePriority(user, req, res, session);
					break;							
				case A_MAINTENANCE :
					procActionMaintenance(user,	req, res, session);
				case A_INCOMING :
					procActionIncoming(user, req, res, session);								
					break;
				case R_ADD_TR :
					procReqAddTransaction(user, req, res, session);
					break;
				case A_LIST_REF :
					procActionListRef(user, req, res, session);
					break;
				case A_REQ_REF :
					procReqRef(user, req, res, session);
					break;	
				case A_MAINT_REF :
					procActionMaintRef(user, req, res, session);
					break;
				case A_REF :
					procActionRef(user, req, res, session);
					break;
				case A_REF_DETAIL :
					procActionRefDetail(user, req, res, session);
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
	private void procActionRefDetail(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		JBObjList beanList = (JBObjList) session.getAttribute("mtListRef");
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		beanList.setCurrentRow(row);
		EPR010002Message msg = (EPR010002Message) beanList.getRecord();
		flexLog("Putting java beans into the session");
		session.setAttribute("msgMTRef", msg);
		
		forward("EPR0000_references_inq.jsp", req, res);						
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionRef(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		JBObjList beanList = (JBObjList) session.getAttribute("mtListRef");
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		beanList.setCurrentRow(row);
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010002Message msg = (EPR010002Message) beanList.getRecord();
			String purpose = (req.getParameter("PURPOSE") == null) ? "" : req.getParameter("PURPOSE");
			if (purpose.equals("DEVOLVER")) {
				msg.setH02OPECOD("0008");
			} else {
				if (purpose.equals("RETURN")) {
					msg.setH02OPECOD("0018");
				} else {
					msg.setH02OPECOD("0009");
				}	
			}
			mp.sendMessage(msg);
		
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010002Message) mp.receiveMessageRecord();
			
			if (mp.hasError(msgError)) {
				PageToCall = "EPR0000_references_list.jsp";
			} else {
				redirectToPage("/servlet/datapro.eibs.products.JSEPR0000?SCREEN=100&FromRecord=0", res);
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
	private void procActionMaintRef(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010002Message msg = (EPR010002Message) mp.getMessageRecord("EPR010002", user.getH01USR(), "0005");
			setMessageRecord(req, msg);
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010002Message) mp.receiveMessageRecord();
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("msgMTRef", msg);
			
			if (mp.hasError(msgError)) {
				PageToCall = "EPR0000_references_maint.jsp";
			} else {
				redirectToPage("/servlet/datapro.eibs.products.JSEPR0000?SCREEN=100&FromRecord=0", res);
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
	private void procReqRef(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		JBObjList beanList = (JBObjList) session.getAttribute("mtListRef");
		EPR010002Message msg = null;
		if (req.getParameter("PURPOSE") != null && req.getParameter("PURPOSE").equals("NEW")) {
			MessageProcessor mp = null;
			try {
				mp = getMessageProcessor("EPR0100", req);
				msg = (EPR010002Message) mp.getMessageRecord("EPR010002", user.getH01USR(), "");
				PageToCall = "EPR0000_references_new.jsp";
			} finally {
				if (mp != null)	mp.close();
			}
		} else {
			int row = 0;
			try {
				row = Integer.parseInt(req.getParameter("CURRENTROW"));
			} catch (Exception e) {
				row = 0;
			}
			beanList.setCurrentRow(row);
			msg = (EPR010002Message) beanList.getRecord();
			PageToCall = "EPR0000_references_maint.jsp";
		}
		flexLog("Putting java beans into the session");
		session.setAttribute("msgMTRef", msg);				
		session.setAttribute("userPO", userPO);
		
		forward(PageToCall, req, res);
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionListRef(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		String purpose = (req.getParameter("PURPOSE") == null) ? "" : req.getParameter("PURPOSE");
		userPO.setPurpose(purpose);
		
		EPR010001Message msgrec = (EPR010001Message) session.getAttribute("prMant");
		userPO.setHeader16(msgrec.getE01PRINUM()); 
		userPO.setHeader17(msgrec.getE01PRIFMT()); 
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010002Message msg = (EPR010002Message) mp.getMessageRecord("EPR010002", user.getH01USR(), "0015");
			msg.setH02SCRCOD("01");
			String from = (req.getParameter("FromRecord") == null) ? "0" : req.getParameter("FromRecord");
			msg.setE02NUMREC(from);
			msg.setE02PRMNUM(msgrec.getE01PRINUM());
			 
			mp.sendMessage(msg);
		
			JBObjList list = mp.receiveMessageRecordList("H02FLGMAS");
			flexLog("Putting java beans into the session");
			session.setAttribute("mtListRef", list);				
			session.setAttribute("userPO", userPO);
			
			if (userPO.getPurpose().equals("INQUIRY") || userPO.getPurpose().equals("APPROVAL")) {
				PageToCall = "EPR0000_references_inq_list.jsp";
			} else {
				PageToCall = "EPR0000_references_list.jsp";	
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
	private void procReqAddTransaction(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		DataTransaction transData = new DataTransaction();
		transData.setWrkFile("4");
		transData.setAccNum(userPO.getIdentifier());

		flexLog("Putting java beans into the session");
		session.setAttribute("transData", transData);
		
		redirectToPage("/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1", res);
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionIncoming(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message  msg = (EPR010001Message) session.getAttribute("prMant");
			msg.setH01OPECOD("0006");
			setMessageRecord(req, msg);
			if ((req.getParameter("E01PRIDBK") != null) || req.getParameter("E01PRIDBK").trim().equals("0")) {
				msg.setE01PRIDBK("");
			}
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				forward("EPR0000_pr_incoming_maint.jsp", req, res);
			} else {
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println("top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.products.JSEPR1060?SCREEN=9" 
							+ "'");
				out.println("top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();					
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
	private void procActionMaintenance(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message  msg = (EPR010001Message) session.getAttribute("prMant");
			msg.setH01OPECOD("0005");
			setMessageRecord(req, msg);
			if ((req.getParameter("E01PRIDBK") != null) || req.getParameter("E01PRIDBK").trim().equals("0")) {
				msg.setE01PRIDBK("");
			}
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				PageToCall = "EPR0000_pr_maint.jsp";
			} else {
				PageToCall = "EPR0000_pr_confirm.jsp";
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
	private void procActionChangePriority(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String type = (req.getParameter("E01PRIPTY") == null) ? "" : req.getParameter("E01PRIPTY");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message  msg = (EPR010001Message) session.getAttribute("prMant");
			msg.setH01OPECOD("0005");
			msg.setE01PRIPTY(type);
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				PageToCall = "error_viewer.jsp";
			} else {
				PageToCall = "EPR1080_pr_confirm.jsp";
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
	private void procActionNew(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message  msg = (EPR010001Message) session.getAttribute("prMant");
			msg.setH01OPECOD("0005");
			setMessageRecord(req, msg);
			if ((req.getParameter("E01PRIDBK") != null) || req.getParameter("E01PRIDBK").trim().equals("0")) {
				msg.setE01PRIDBK("");
			}
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			userPO.setIdentifier(msg.getE01PRINUM());

			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				PageToCall = "EPR0000_pr_maint.jsp";
			} else {
				PageToCall = "EPR0000_pr_confirm.jsp";
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
	private void procReqChangePriority(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String refnum = (req.getParameter("TRANSREFNUM") == null) ? "" : req.getParameter("TRANSREFNUM");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message msg = (EPR010001Message) mp.getMessageRecord("EPR010001", user.getH01USR(), "0002");
			msg.setH01SCRCOD("01");
			msg.setE01PRINUM(refnum);
			msg.setH01FLGWK1("P");						
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				PageToCall = "error_viewer.jsp";
			} else {
				String type = (req.getParameter("E01PRIPTY") == null) ? "" : req.getParameter("E01PRIPTY"); 
				redirectToPage("/servlet/datapro.eibs.products.JSEPR0000?SCREEN=2000" + "&E01PRIPTY=" + type, res);
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
	private void procReqMaintenance(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String refnum = (req.getParameter("REFNUM") == null) ? "" : req.getParameter("REFNUM");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message msg = (EPR010001Message) mp.getMessageRecord("EPR010001", user.getH01USR(), "0002");
			msg.setH01SCRCOD("01");
			msg.setE01PRINUM(refnum);
			
			mp.sendMessage(msg);

			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EPR010001Message) mp.receiveMessageRecord();
			
			userPO.setIdentifier(msg.getE01PRINUM());
			userPO.setPurpose("MAINTENANCE");
			userPO.setOption("PR");

			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			session.setAttribute("userPO", userPO);
			
			if (mp.hasError(msgError)) {
				PageToCall = "error_viewer.jsp";
			} else {
				PageToCall = "EPR0000_pr_maint.jsp";
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
	private void procReqNew(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		ELEERRMessage msgError = new ELEERRMessage();
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EPR0100", req);
			EPR010001Message msg = (EPR010001Message) mp.getMessageRecord("EPR010001", user.getH01USR(), "0005");
			msg.setH01SCRCOD("01");
		
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("prMant", msg);
			
			forward("EPR0000_pr_maint.jsp", req, res);
		} finally {
			if (mp != null)	mp.close();
		}
	}

}
