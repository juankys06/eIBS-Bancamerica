package datapro.eibs.ach;
/**
 * Maintenance of ACH Parameters  
 * Creation date: (04/15/08)
 * @author: Carlos Castillo
 * 
 *	// ACH Parameters
 *	// List of Options
 *	//
 *	// SCREEN =    1	Send List of ACH Parameters
 *	// SCREEN =    2    Request new record 
 *	// SCREEN =    5	Submit the list for Update
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEACH140 extends JSEIBSServlet { 
	 
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
	// Send List of ACH Parameters  
	//
	private synchronized void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
			HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EACH140");
			EACH14001Message msg = (EACH14001Message) mp.getMessageRecord("EACH14001"); 
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH140");
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
				ses.setAttribute("EACH140List", list);
			}
			forward("EACH140_ach_parameters_list.jsp", req, res);
			
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
		EACH14001Message msg = null;
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
			mp = new MessageProcessor("EACH140");
			msg = (EACH14001Message) mp.getMessageRecord("EACH14001");
			
			for (int row = 0; row < totRows ; row++) {
				msg.setH01USERID(user.getH01USR());
				msg.setH01PROGRM("EACH140");
				msg.setH01TIMSYS(getTimeStamp());  
				msg.setH01SCRCOD("01");
				msg.setH01OPECOD("0005");
						
				msg.setE01ACHBNK(req.getParameter("E01ACHBNK_" + row));
				msg.setE01ACHOCD(req.getParameter("E01ACHOCD_" + row));
				try {
					msg.setE01ACHNSF(req.getParameter("E01ACHNSF_" + row));
				} catch (Exception e) {
					msg.setE01ACHNSF("N");
				}
				try {
					msg.setE01ACHBAA(req.getParameter("E01ACHBAA_" + row));
				} catch (Exception e) {
					msg.setE01ACHBAA("N");
				}
				try {
					msg.setE01ACHREP(req.getParameter("E01ACHREP_" + row));
				} catch (Exception e) {
					msg.setE01ACHREP("M");
				}
				msg.setE01ACHBAI(req.getParameter("E01ACHBAI_" + row));
				msg.setE01ACHBAT(req.getParameter("E01ACHBAT_" + row));
				msg.setE01ACHIGL(req.getParameter("E01ACHIGL_" + row));
				msg.setE01ACHOGL(req.getParameter("E01ACHOGL_" + row));
				msg.setE01ACHGLN(req.getParameter("E01ACHGLN_" + row));
				msg.setE01ACHNUM(req.getParameter("E01ACHNUM_" + row));
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
			msg.setH01PROGRM("EACH140");
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
				forward("EACH140_ach_parameters_list.jsp", req, res);
			} else {
				redirectToPage("/servlet/datapro.eibs.ach.JSEACH140?SCREEN=1&FromRecord=0", res);
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}