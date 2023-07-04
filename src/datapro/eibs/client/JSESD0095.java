package datapro.eibs.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EGL003501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD008001Message;
import datapro.eibs.beans.ESD008002Message;
import datapro.eibs.beans.ESD0095DSMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;

public class JSESD0095 extends JSEIBSServlet {

    protected static final int R_IVR_PIN_ASSIGN_PER = 10;
    protected static final int R_IVR_PIN_ASSIGN_COR = 11;
    protected static final int A_IVR_PIN_ASSIGN_PER = 20;
    protected static final int A_IVR_PIN_ASSIGN_COR = 21;    
    
    protected void processRequest(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res,
	    HttpSession session, int screen) throws ServletException,
	    IOException {

	switch (screen) {
	    // BEGIN Personal
	    // Request
	    case R_IVR_PIN_ASSIGN_PER:
		procReqIvrPinAssignPers(user, req, res, session);
		break;
	    case R_IVR_PIN_ASSIGN_COR:
		procReqIvrPinAssignCorp(user, req, res, session);
		break;		
	    case A_IVR_PIN_ASSIGN_PER:
		procActionIvrPinAssignPers(user, req, res, session);
		break;
	    case A_IVR_PIN_ASSIGN_COR:
		procActionIvrPinAssignCorp(user, req, res, session);
		break;		
       	   default :
           	forward("MISC_not_available.jsp", req, res);
           	break;
        }
    }

    private void procActionIvrPinAssignPers(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException, ServletException {
	UserPos userPO = getUserPos(session);
	ESD008001Message msgClientPersonal = (ESD008001Message) session.getAttribute("client");
	String opecod = req.getParameter("H01OPECOD") == null ? "0001" : req.getParameter("H01OPECOD"); 
	MessageProcessor mp = null;
	try {
		mp = getMessageProcessor("ESD0095", req);
		ESD0095DSMessage ivrData = (ESD0095DSMessage) mp.getMessageRecord("ESD0095DS", user.getH01USR(), opecod);
		ivrData.setH01SCRCOD("01");
		String identi = msgClientPersonal.getE01IDN().trim().length() > 0 ? msgClientPersonal
			    .getE01IDN().trim()
			    : msgClientPersonal.getE01IDF().trim().length() > 0 ? msgClientPersonal
				    .getE01IDF().trim() : msgClientPersonal.getE01LN3().trim();
		ivrData.setE01IDENTI(identi);
		ivrData.setE01PINNUM(req.getParameter("E01PINNUM"));
		
		mp.sendMessage(ivrData);
		
		ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
		ivrData = (ESD0095DSMessage) mp.receiveMessageRecord("ESD0095DS");
		
		userPO.setHeader1(msgClientPersonal.getE01CUN());
		userPO.setHeader2(msgClientPersonal.getE01IDN());
		userPO.setHeader3(msgClientPersonal.getE01NA1());

		flexLog("Putting java beans into the session");
		session.setAttribute("error", msgError);
		//session.setAttribute("client", msgClientPersonal);
		session.setAttribute("ivrData", ivrData);
		session.setAttribute("userPO", userPO);
		
		forward("ESD0095_ivr_pin_maint.jsp", req, res);
		
	} finally {
		if (mp != null)	mp.close();
	}
    }

    private void procActionIvrPinAssignCorp(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException, ServletException {
	UserPos userPO = getUserPos(session);
	ESD008002Message msgClientCorporativo = (ESD008002Message) session.getAttribute("client");
	String opecod = req.getParameter("H01OPECOD") == null ? "0001" : req.getParameter("H01OPECOD"); 
	MessageProcessor mp = null;
	try {
		mp = getMessageProcessor("ESD0095", req);
		ESD0095DSMessage ivrData = (ESD0095DSMessage) mp.getMessageRecord("ESD0095DS", user.getH01USR(), opecod);
		ivrData.setH01SCRCOD("01");
		String identi = msgClientCorporativo.getE02IDN().trim().length() > 0 ? msgClientCorporativo
			    .getE02IDN().trim()
			    : msgClientCorporativo.getE02IDF().trim().length() > 0 ? msgClientCorporativo
				    .getE02IDF().trim() : msgClientCorporativo.getE02LN3().trim();
		ivrData.setE01IDENTI(identi);
		ivrData.setE01PINNUM(req.getParameter("E01PINNUM"));
		
		mp.sendMessage(ivrData);
		
		ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
		ivrData = (ESD0095DSMessage) mp.receiveMessageRecord("ESD0095DS");
		
		userPO.setHeader1(msgClientCorporativo.getE02CUN());
		userPO.setHeader2(msgClientCorporativo.getE02IDN());
		userPO.setHeader3(msgClientCorporativo.getE02NA1());

		flexLog("Putting java beans into the session");
		session.setAttribute("error", msgError);
		//session.setAttribute("client", msgClientPersonal);
		session.setAttribute("ivrData", ivrData);
		session.setAttribute("userPO", userPO);
		
		forward("ESD0095_ivr_pin_maint_corp.jsp", req, res);
		
	} finally {
		if (mp != null)	mp.close();
	}
    }    
    
    private void procReqIvrPinAssignPers(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
	forward("ESD0095_ivr_pin_maint.jsp", req, res);
    }

    private void procReqIvrPinAssignCorp(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
	forward("ESD0095_ivr_pin_maint_corp.jsp", req, res);
    }    
}
