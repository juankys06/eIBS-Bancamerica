package datapro.eibs.teller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ETI054001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.BaseServlet;
import datapro.eibs.sockets.MessageContext;

public class JSETI0540 extends BaseServlet {   

	protected void processRequest(MessageContext mc, ESS0030DSMessage msgUser,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {
		
		ETI054001Message msg = new ETI054001Message();
		try {
			msg = (ETI054001Message) mc.getMessageRecord("ETI054001");
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
		case 100: // Maintenance List
			userPO.setBank(req.getParameter("E01CKCBNK"));
			userPO.setAccNum(req.getParameter("E01CKCACC"));
			session.setAttribute("userPO", userPO);
			msg.setH01OPECOD("0002");
			msg.setH01PROGRM("ETI0540");
			msg.setH01OPECOD("0015");
			msg.setE01CKCBNK(req.getParameter("E01CKCBNK").toUpperCase());
			msg.setE01CKCACC(req.getParameter("E01CKCACC").toUpperCase());
			
			processList(mc, msgUser, req, res, session, msg,
					"ETI0540_chk_list.jsp",
					"ETI0540_chk_list.jsp", ERROR_AND_DATA);
			break;

		case 200: //List Option New						
			userPO.setPurpose("NEW");
			userPO.setCusName(req.getParameter("E01CKCNME"));		
			pageRedirect = "ETI0540_chk_maint.jsp";		
	   		session.setAttribute("userPO", userPO);
	   		msg = null;
	   		session.setAttribute("msgData", msg);
	   		pageRedirect(req, res, session, pageRedirect);

			break;
			
		case 300: //List option Maintenance
			

			userPO.setPurpose("MAINTENANCE");
			pageRedirect = "ETI0540_chk_maint.jsp";
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				flexLog("EXCEPTION:" + e.getMessage());
				e.printStackTrace();
			}
			 appList.setCurrentRow(row);
			 msg = (ETI054001Message) appList.getRecord();
			 userPO.setBank(msg.getE01CKCBNK());
			 userPO.setAccNum(msg.getE01CKCACC());			 
			 session.setAttribute("msgData", msg);
 	   		 session.setAttribute("userPO", userPO);
		   	 pageRedirect(req, res, session, pageRedirect);
			break;
        
        case 600: // Action maintenance 	
			String opecode = "0005";
			pageError = "ETI0540_chk_maint.jsp";							
			
			try {
					msg = (ETI054001Message) mc.getMessageRecord("ETI054001");
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
			userPO.setBank(req.getParameter("E01CKCBNK"));
			userPO.setAccNum(req.getParameter("E01CKCACC"));
			msg.setH01PROGRM("ETI0540");
			msg.setH01OPECOD(opecode); 
			msg.setE01CKCBNK(req.getParameter("E01CKCBNK").toUpperCase());
			msg.setE01CKCACC(req.getParameter("E01CKCACC").toUpperCase());
			session.setAttribute("userPO", userPO);
			processOption(mc, msgUser, req, res, session, msg,
					"/servlet/datapro.eibs.teller.JSETI0540?SCREEN=100"+ "&E01CKCBNK=" + msg.getE01CKCBNK() + "&E01CKCACC=" + msg.getE01CKCACC(),
					pageError, ERROR_AND_DATA);			
			break;
		case 1000: //Enter parameters
			pageRedirect = "ETI0540_enter_param.jsp";
		   	 pageRedirect(req, res, session, pageRedirect);
		default:
			System.out.println("DEFAULT OPTION");
			break;
		}
	}


}
