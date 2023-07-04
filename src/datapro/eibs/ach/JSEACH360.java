package datapro.eibs.ach;
/**
 * Maintenance of ACH Returns  
 * Creation date: (06/23/08)
 * @author: Carlos Castillo
 * 
 *	// ACH Returns
 *	// List of Options
 *	//
 *	// SCREEN =    1	Send List of ACH Returns
 *	// SCREEN =    5	Submit the list for Update 
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEACH360 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
	
			switch (screen) {
			// Requests List 
			case 1 :
				procReqList(user, req, res, session, screen);
				break;	
			//  Submit the list for update
			case 2 :	// Mark as accepted
			case 4 :    // Mark as deleted
				procActionList(user, req, res, session, screen);
				break;	
			case 5 :	// Process All marked returns
				procActionUpdate(user, req, res, session);
				break;
			default :
				redirectToPage(SuperServlet.devPage, res);
				break;
				}
	}
	
	// SCREEN = 1 
	// Send List of ACH Returns  
	//
	private synchronized void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
			HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EACH360");
			EACH36001Message msg = (EACH36001Message) mp.getMessageRecord("EACH36001"); 
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH360");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			
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
				ses.setAttribute("EACH360List", list);
			}
			forward("EACH360_ach_returns_list.jsp", req, res);
			
		} finally {
			if (mp != null)
				mp.close();
		}
	}

	// SCREEN = 2 and 4
	// Process the List (Sending all rows to Socket) 
	//
	protected void procActionList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		EACH36001Message msg = null;
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
			mp = new MessageProcessor("EACH360");
			msg = (EACH36001Message) mp.getMessageRecord("EACH36001");
			
			for (int row = 0; row < totRows ; row++) {
				msg.setH01USERID(user.getH01USR());
				msg.setH01PROGRM("EACH360");
				msg.setH01TIMSYS(getTimeStamp());  
				msg.setH01SCRCOD("01");
				
				switch (screen) {
				case 2 : // Mark returns as accepted
					msg.setH01OPECOD("0002");
					break;
				case 4 : // Exclude Record from Returns
					msg.setH01OPECOD("0004");
					break;
				default :
					msg.setH01OPECOD("0002");  
				break;
			    }	
						
				msg.setE01ACRNUM(req.getParameter("E01ACRNUM_" + row));
				if (req.getParameter("E01ACT_" + row)== null) {
					msg.setE01ACT(" ");
				} else {
					msg.setE01ACT("S");   // Selected Record
				}	
				msg.setH01FLGWK1("");
				// Send Message
				mp.sendMessage(msg);
			} // EndFor
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH360");
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
				forward("EACH360_ach_returns_list.jsp", req, res);
			} else {
				redirectToPage("/servlet/datapro.eibs.ach.JSEACH360?SCREEN=1&FromRecord=0", res);
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

	// SCREEN = 5
	// Process All the Records marked in the file  
	//
	protected void procActionUpdate(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession ses)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		EACH36001Message msg = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		String nextrows = "0";
		String currrows = "0"; 
			
		// Send data
		try { 
			mp = new MessageProcessor("EACH360");
			msg = (EACH36001Message) mp.getMessageRecord("EACH36001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH360");
			msg.setH01TIMSYS(getTimeStamp());  
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0005");  
			msg.setE01ACT(" ");
			msg.setH01FLGWK1("");
				// Send Message
			mp.sendMessage(msg);
			
			// Receive Error Message or Data Message (Optional)
			ses.setAttribute("userPO", userPO);
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("error", newmessage);
				forward("EACH360_ach_returns_list.jsp", req, res);
			} else {
				redirectToPage("/servlet/datapro.eibs.ach.JSEACH360?SCREEN=1&FromRecord=0", res);
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

	
}