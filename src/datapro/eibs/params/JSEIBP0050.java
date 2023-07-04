/*
 * Created on Sep 19, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package datapro.eibs.params;

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

import datapro.eibs.beans.EIBP005001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author erodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JSEIBP0050 extends JSEIBSServlet {

	protected static final int R_LIST = 1;
	protected static final int A_INQUIRY = 2;
	protected static final int A_APPROVAL = 5;
	
	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {

		switch (screen) {
			case R_LIST :
				reqList(user, req, res, session, screen);
				break;
			case A_APPROVAL :
				actApproval(user, req, res, session, screen);
				break;
			case A_INQUIRY :
				actInquiry(user, req, res, session, screen);
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
	 * @param screen
	 * @throws IOException
	 * @throws ServletException
	 */
	private void actInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen) throws ServletException, IOException {
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		initTransaction(userPO, String.valueOf(screen), "INQUIRY");
		EIBP005001Message msg = (EIBP005001Message) getMessageRecord(req, session);
		msg.setH01OPECOD("0002"); //Inquiry
		String PageToCall = "EIBP0050_vendor_basic.jsp";
		putDataInSession(session, null, userPO, "vendor", msg);
		forward(PageToCall, req, res);
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 * @param screen
	 * @throws IOException
	 * @throws ServletException
	 */
	private void actApproval(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen) throws ServletException, IOException {
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		ELEERRMessage msgError = null;
		JBObjList list = (JBObjList) session.getAttribute("appList");
		MessageProcessor mp = new MessageProcessor(getMessageContext(req, 1));
		try {
			initTransaction(userPO, String.valueOf(screen), "APPROVAL");
			list.initRow();
			while (list.getNextRow()) {
				EIBP005001Message msg = (EIBP005001Message) list.getRecord();
				msg.setH01OPECOD("0005"); //Approval
				mp.sendMessage(msg);
				msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			}
			putDataInSession(session, msgError, userPO, null, null);
			reqList(user, req, res, session, screen);
		} finally {
			if(mp != null) mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 * @param screen
	 * @throws IOException
	 * @throws ServletException
	 */
	private void reqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen) throws ServletException, IOException {
		UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		ELEERRMessage msgError = null;
		try {
			msgError = (ELEERRMessage) session.getAttribute("error");
			if (msgError == null) msgError = new ELEERRMessage();
		} catch (Exception e) {
			msgError = new ELEERRMessage();
		}
		
		MessageProcessor mp = new MessageProcessor(getMessageContext(req, 1));
		try {
			initTransaction(userPO, String.valueOf(screen), "");
			EIBP005001Message msg = (EIBP005001Message) mp.getMessageRecord("EIBP005001", user.getH01USR(), "0015");
			mp.sendMessage(msg);
			JBObjList list = (JBObjList) mp.receiveMessageRecordList("H01FLGMAS");
			putDataInSession(session, msgError, userPO, "appList", list);
			forward("EIBP0050_approval_list.jsp", req, res);
		
		} finally {
			if(mp != null) mp.close();
		}
	}

	private MessageRecord getMessageRecord(HttpServletRequest req, HttpSession session) {
		MessageRecord result = null;
		JBObjList list = (JBObjList) session.getAttribute("appList");
		list.initRow();
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		if (row <= list.getLastRec()) {
			list.setCurrentRow(row);
			result = (MessageRecord) list.getRecord();
		}
		return result;
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
