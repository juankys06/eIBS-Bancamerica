package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EDL002001Message;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.BaseServlet;
import datapro.eibs.sockets.MessageContext;

public class JSEDL0020 extends BaseServlet {    

	protected void processRequest(MessageContext mc, ESS0030DSMessage msgUser,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {
		
		EDL002001Message msg = new EDL002001Message();
		try {
			msg = (EDL002001Message) mc.getMessageRecord("EDL002001");
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
		case 100: //Enter parameters						
					
			pageRedirect = "EDL0020_online_loan_payment.jsp";	   		
	   		msg = null;
	   		session.setAttribute("msgData", msg);
	   		pageRedirect(req, res, session, pageRedirect);

			break;
			
		case 200: // process Option
			msg.setH01OPECOD("0001");
			msg.setH01PROGRM("EDL0020");
			msg.setE01FRMDTD(req.getParameter("E01FRMDTD").toUpperCase());
			msg.setE01FRMDTM(req.getParameter("E01FRMDTM").toUpperCase());
			msg.setE01FRMDTY(req.getParameter("E01FRMDTY").toUpperCase());
			msg.setE01TODATD(req.getParameter("E01TODATD").toUpperCase());
			msg.setE01TODATM(req.getParameter("E01TODATM").toUpperCase());
			msg.setE01TODATY(req.getParameter("E01TODATY").toUpperCase());
			pageError = "EDL0020_online_loan_payment.jsp";							
			
			processOption(mc, msgUser, req, res, session, msg,
					"/servlet/datapro.eibs.products.JSEDL0020?SCREEN=100",
					pageError, ERROR_AND_DATA);			
			break;		
		default:
			System.out.println("DEFAULT OPTION, NOT CODE FOR SCREEN..:"+ screen);
			break;
		}
	}


}
