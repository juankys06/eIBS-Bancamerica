package datapro.eibs.bap;
/**
 * Maintenance of BP Parameters  
 * Creation date: (10/20/09)
 * @author: Luis I Soucarre
 * 
 *	// BP Parameters
 *	// List of Options
 *	//
 *	// SCREEN =    1	Send List of BAP Parameters
 *	// SCREEN =    2    Request new record 
 *	// SCREEN =    5	Submit the list for Update
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEBP0180 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
	
			switch (screen) {
			// Requests List 
			case 1 :
			case 2 :    // With new Row
				procReqList(user, req, res, session, screen);
				break;	
			//  Submit the list for update
			case 5 :
				procActionList(user, req, res, session);
				break;
			default :
				redirectToPage(SuperServlet.devPage, res);
				break;
				}
	}
	
	// SCREEN = 1 or 2
	// Send List of BP Parameters  
	//
	private synchronized void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
			HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EBP0180");
			EBP018001Message msg = (EBP018001Message) mp.getMessageRecord("EBP018001"); 
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data  
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0180");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			switch (screen) { 
				case 2 : // Request new Record
					msg.setH01OPECOD("0001");
					break;
				default : 
					msg.setH01OPECOD("0002");
					break;
			}	
			try {
				msg.setE01NUMREC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msg.setE01NUMREC("0");
			}
			// Send Message	
			mp.sendMessage(msg);
			// Receive List
			JBObjList list = (JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("EBP0180List", list);
			}
			forward("EBP0180_bp_parameters_list.jsp", req, res);
			
		} finally {
			if (mp != null)
				mp.close();
		}
	}

	// SCREEN = 5
	// Process the List (Sending all rows to Socket) 
	//
	protected void procActionList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession ses)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		EBP018001Message msg = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		String nextrows = "0";
		String currrows = "0"; 
		int totRows = Integer.parseInt(req.getParameter("TOTROWS"));
		try{
			nextrows = req.getParameter("NEXTROWS");
		} catch (Exception e) {}
		try{
			currrows = req.getParameter("CURRROWS");
		} catch (Exception e) {}
		
		// Send data
		try { 
			mp = new MessageProcessor("EBP0180");
			msg = (EBP018001Message) mp.getMessageRecord("EBP018001");
			
			for (int row = 0; row < totRows ; row++) {
				msg.setH01USERID(user.getH01USR());
				msg.setH01PROGRM("EBP0180");
				msg.setH01TIMSYS(getTimeStamp());  
				msg.setH01SCRCOD("01");
				msg.setH01OPECOD("0005");
						
				msg.setE01BPABNK(req.getParameter("E01BPABNK_" + row));
				try {
					msg.setE01BPATCD(req.getParameter("E01BPATCD_" + row));
				} catch (Exception e) {
					msg.setE01BPATCD("MC");
				}
				msg.setE01BPADAD(req.getParameter("E01BPADAD_" + row));
				msg.setE01BPABTH(req.getParameter("E01BPABTH_" + row));
				msg.setE01BPAGLC(req.getParameter("E01BPAGLC_" + row));
				msg.setE01BPAFTY(req.getParameter("E01BPAFTY_" + row));
				msg.setE01BPAGLP(req.getParameter("E01BPAGLP_" + row));
				msg.setE01BPAGLR(req.getParameter("E01BPAGLR_" + row));
				msg.setE01BPAGLG(req.getParameter("E01BPAGLG_" + row));
				msg.setE01BPAGLA(req.getParameter("E01BPAGLA_" + row));
				msg.setE01BPAGLS(req.getParameter("E01BPAGLS_" + row));
				msg.setE01BPAGLW(req.getParameter("E01BPAGLW_" + row));
				msg.setE01BPAPOW(req.getParameter("E01BPAPOW_" + row));
				msg.setE01BPAAMT(req.getParameter("E01BPAAMT_" + row));
				if (req.getParameter("E01ACT_" + row)== null) {
					msg.setE01ACT(" ");
				} else { 
					msg.setE01ACT("D");   // Delete Record
				}	
				msg.setH01FLGWK1("");
				// Send Message
				mp.sendMessage(msg);
			} // EndFor
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0180");
			msg.setH01TIMSYS(getTimeStamp());  
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0005");
			msg.setH01FLGWK1("S");  // End of List
			mp.sendMessage(msg);
			// Receive Error Message or Data Message (Optional)
			ses.setAttribute("userPO", userPO);
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("error", newmessage);
				forward("EBP0180_bp_parameters_list.jsp", req, res);
			} else {
				redirectToPage("/servlet/datapro.eibs.bap.JSEBP0180?SCREEN=1&FromRecord=0", res);
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}