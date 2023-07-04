/*
 * Created on Oct 31, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDD000010Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.sockets.MessageContext;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEAA0100 extends JSEIBSServlet {
	

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
		// TODO Auto-generated method stub

		final int R_ENTER = 1;
		final int A_ENTER = 2;
		final int S_ENTER = 3;

		switch (screen) {
			case R_ENTER :
				procReqEnter(user, req, res, session);
				break;
			case A_ENTER :
				procActEnter(user, req, res, session);
				break;
			case S_ENTER :
				procActionAccountAnalysis(user, req, res, session);
				break;
			default :
				forward(super.devPage, req, res);
				break;
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionAccountAnalysis(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = new MessageProcessor(getMessageContext(req, 4));
		try {
			EDD000010Message msg = (EDD000010Message) session.getAttribute("accAnalysis");
			msg.setH10SCRCOD("01");
			msg.setH10OPECOD("0005");
			try {
				setMessageRecord(req, msg);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EDD000010Message) mp.receiveMessageRecord();
			if (mp.hasError(msgError)) { // There are errors
				PageToCall = "EDD0000_rt_account_analysis_maint.jsp";
			} else {
				PageToCall = "EDD0000_rt_enter_account_analysis.jsp";
			}
			forward(PageToCall, req, res);
		
		} finally {
			if(mp != null) mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session)  throws ServletException, IOException {
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = new MessageProcessor(getMessageContext(req, 4));
		try {
			EDD000010Message msg = (EDD000010Message) mp.getMessageRecord("EDD000010", user.getH01USR(), "0002");
			msg.setE10ACMACC(req.getParameter("E01ACMACC").trim());
			msg.setH10SCRCOD("01");
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			msg = (EDD000010Message) mp.receiveMessageRecord();
			putDataInSession(session, msgError, userPO, "accAnalysis", msg);
			if (mp.hasError(msgError)) { // There are errors
				PageToCall = "EDD0000_rt_enter_account_analysis.jsp";
			} else {
				PageToCall = "EDD0000_rt_account_analysis_maint.jsp";
			}
			forward(PageToCall, req, res);
		
		} finally {
			if(mp != null) mp.close();
		}
	}
 
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String PageToCall = "EDD0000_rt_enter_account_analysis.jsp";
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		ELEERRMessage msgError = new ELEERRMessage();
		putDataInSession(session, msgError, userPO, null, null);
		forward(PageToCall, req, res);
	}

	private MessageContext getMessageContext(HttpServletRequest req, int port) throws IOException {
		
		Socket s = new Socket(super.hostIP, getInitSocket(req) + port);
		s.setSoTimeout(super.sckTimeOut);
		MessageContext mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
		return mc;		
	}
	
	private void initTransaction(UserPos userPO, String optMenu, String purpose) {
		try {
			if (!optMenu.equals("")) userPO.setOption(optMenu);
			if (!purpose.equals("")) userPO.setPurpose(purpose);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
		} catch (Exception ex) {
			flexLog("Error getting userPO from session: " + ex);
		}
	}
	
	private void putDataInSession(HttpSession session, ELEERRMessage msgError, UserPos user, String name, Object obj) {
		try {
			flexLog("Putting java beans into the session");
			if (msgError != null) session.setAttribute("error", msgError);
			if (user != null) session.setAttribute("userPO", user);
			if (obj != null) session.setAttribute(name, obj);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
}
