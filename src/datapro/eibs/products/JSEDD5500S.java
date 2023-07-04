package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDD550001Message;
import datapro.eibs.beans.ELEERRMessage;
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
public class JSEDD5500S extends JSEIBSServlet {

	protected static final int R_RT_SIGNERS = 19;
	
	protected void processRequest(ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {

		switch (screen) {
			
			// Request Signers List
			case R_RT_SIGNERS :
				procReqSignerList(user, req, res, session);
				break;

			default :
				forward("MISC_not_available.jsp", req, res);
				break;
		}
	}

	private void procReqSignerList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException, ServletException {
		UserPos userPO = getUserPos(session);
		String account = req.getParameter("ACCNUM") == null ? userPO.getIdentifier() : req.getParameter("ACCNUM"); 
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EDD5500", req);
			EDD550001Message msgRT = (EDD550001Message) mp.getMessageRecord("EDD550001", user.getH01USR(), "0004");
			msgRT.setH01USR(user.getH01USR());
			msgRT.setH01PGM("EDD5500");
			msgRT.setH01TIM(getTimeStamp());
			msgRT.setH01SCR("01");
			msgRT.setH01OPE("0004");
			msgRT.setE01CUN(account);
			msgRT.setE01RTP("S");
			
			mp.sendMessage(msgRT);
			
			JBObjList list = mp.receiveMessageRecordList("H01MAS");
			
			if (mp.hasError(list)) {
				ELEERRMessage msgError = (ELEERRMessage) mp.getError(list);
				flexLog("Putting java beans into the session"); 
				session.setAttribute("error", msgError);
				forward("error_viewer.jsp", req, res);
			} else {
				flexLog("Putting java beans into the session"); 
				session.setAttribute("signatures", list);
				forward("Signature_viewer_blank.jsp", req, res);
			}
		
		} finally {
			if (mp != null)	mp.close();
		}
	}

}
