// BP Help for Vendors
// M. Riaño  17/02/2010
// Source File Name:   JSEWD0560.java 

package datapro.eibs.bap;

import datapro.eibs.beans.*;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEWD0560 extends JSEIBSServlet {
	
	protected void processRequest(ESS0030DSMessage msgUser, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen) 
		throws ServletException, IOException {
			
		switch (screen) {
			case 1 :
				reqList(msgUser, req, res, session);
				break;
			default :
				forward(super.devPage, req, res);
				break;
		}
	}
     

	protected void reqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
		
		MessageProcessor mp = null;
		UserPos userPO = null;
		try {
			mp = new MessageProcessor("EWD0560");				
			EWD0560DSMessage msg = (EWD0560DSMessage) mp.getMessageRecord("EWD0560DS");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
			msg.setRWDUSR(user.getH01USR());
			try {
				msg.setRWDTYP(req.getParameter("Type"));
			} catch (Exception e) {
			}
			try {
            	msg.setSWDCOD(req.getParameter("SelNew"));
            } catch (Exception e) { }
           	
			try {
				msg.setRWDFRC(req.getParameter("FromRecord"));
			} catch (Exception e) {	}
			
			userPO.setType(msg.getRWDTYP().trim());
			userPO.setHeader18(msg.getSWDCOD().trim());
			
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			JBObjList list = (JBObjList) mp.receiveMessageRecordList("SWDOPE", "SWDREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("msgHelp", list);
			}
			forward("EWD0560_bp_vendors_helpmessage.jsp", req, res);

		} finally {
			if(mp != null) mp.close();
		}
	}	
}	
