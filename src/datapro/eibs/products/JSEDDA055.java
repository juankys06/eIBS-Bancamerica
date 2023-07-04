package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDDA05501Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.BaseServlet;
import datapro.eibs.sockets.MessageContext;

public class JSEDDA055 extends BaseServlet {   

	protected void processRequest(MessageContext mc, ESS0030DSMessage msgUser,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {
		
		EDDA05501Message msg = new EDDA05501Message();
		try {
			msg = (EDDA05501Message) mc.getMessageRecord("EDDA05501");
		} catch (ClassNotFoundException e1) {		
			e1.printStackTrace();
			flexLog("EXCEPTION:" + e1.getMessage());			
		} catch (IllegalAccessException e1) {		
			e1.printStackTrace();
			flexLog("EXCEPTION:" + e1.getMessage());			
		} catch (InstantiationException e1) {		
			e1.printStackTrace();
			flexLog("EXCEPTION:" + e1.getMessage());
		}
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		JBObjList appList = null;
		appList = (JBObjList) session.getAttribute("appList");
		String pageOk = null;
		String pageError = null;
		String pageRedirect = null;
		int row = -1;

		switch (screen) {
		 
        case 600: // Submit process 	
			String opecode = "0005";
			pageError = "EDDA055_Uncollected_release_parm.jsp";							
			
			try {
					msg = (EDDA05501Message) mc.getMessageRecord("EDDA05501");
				} catch (ClassNotFoundException e2) {
					flexLog("EXCEPTION:" + e2.getMessage());
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					flexLog("EXCEPTION:" + e2.getMessage());
					e2.printStackTrace();
				} catch (InstantiationException e2) {
					flexLog("EXCEPTION:" + e2.getMessage());					
					e2.printStackTrace();
				}
			msg.setH01PROGRM("EDDA055");
			msg.setH01OPECOD(opecode); 
			msg.setE01UNCBRN(req.getParameter("E01UNCBRN").toUpperCase());			
			processOption(mc, msgUser, req, res, session, msg,
					"EDDA055_transaction_confirm.jsp",
					pageError, ERROR_AND_DATA);			
			break;
		case 1000: //Enter parameters
			pageRedirect = "EDDA055_Uncollected_release_parm.jsp";
		   	 pageRedirect(req, res, session, pageRedirect);
		default:
			System.out.println("DEFAULT OPTION");
			break;
		}
	}


}
