package datapro.eibs.bap;
/**
 * Accounts Payable - Payments Selection
 * Creation date: (03/02/10)
 * @author: C. Castillo
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send Payment List
 *	// SCREEN =    2    Approval Payment
 *	// SCREEN =    3    Reject Payment    
 */

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEBP0145 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		switch (screen) {
			//  
		case 1 :  // Send list with results for Process
			procReqList(user, req, res, session, screen);
			break;
		case 2 : // Approval Payment
       	case 3 : // Reject Payment	
            procActionRec(user, req, res, session, screen);
			break;	
		default :
			forward(SuperServlet.devPage, req, res);
			break;
		}
	}

	// SCREEN = 1 
	// Send Payment List  
	//
	protected void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EBP0145");
			EBP014501Message msg = (EBP014501Message) mp.getMessageRecord("EBP014501");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0145");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			userPO.setPurpose("LIST");
						
			try {
				msg.setE01NUMREC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msg.setE01NUMREC("0"); 
			}
			try {
				msg.setE01REQTYP(req.getParameter("E01REQTYP"));
			} catch (Exception e) { 
				msg.setE01REQTYP("V");
			}
			userPO.setType(msg.getE01REQTYP());
			
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
				forward("EBP0145_payment_approval_list.jsp", req, res);
			} else {
				ses.setAttribute("EBP0145List", list);
				forwardOnSuccess("EBP0145_payment_approval_list.jsp", req, res);
			}
		} finally {
			if (mp != null)
				mp.close();
		}
	}
	
	//	 SCREEN = 2,3,
	// Get or Process Record  
	//
	protected void procActionRec(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("INQUIRY");
		
		// Send data
		try {
			mp = new MessageProcessor("EBP0145");
			EBP014501Message msg = (EBP014501Message) mp.getMessageRecord("EBP014501");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0145");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			
			switch (screen) {
			    case 2 : // Approval Payment    
			        msg.setH01OPECOD("0002");
			        userPO.setPurpose("APPROVAL");
				    break;
			    case 3 : // Reject Payment
					msg.setH01OPECOD("0003");
					userPO.setPurpose("REJECT");
					break;    
				default :
					forward(SuperServlet.devPage, req, res);
					break;
			}
			// Get page field
			try {
				msg.setE01BPPNUM(req.getParameter("E01BPPNUM"));
			} catch (Exception e) {
				msg.setE01BPPNUM("0");
			}
			try {
				msg.setE01BPPSEQ(req.getParameter("E01BPPSEQ"));
			} catch (Exception e) {
				msg.setE01BPPSEQ("0");
			}
			
			msg.setE01ACT(" ");
			msg.setH01FLGWK1(""); 
			// Send Message 
			mp.sendMessage(msg);

			// Receive Error Message or Data Message (Optional)
			ses.setAttribute("userPO", userPO);
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("error", newmessage);
				forward("EBP0145_payment_approval_list.jsp", req, res);
			} else {
				if (screen == 2 || screen == 3) {
				    redirectToPage("/servlet/datapro.eibs.bap.JSEBP0145?SCREEN=1&FromRecord=0" +
			        		"&E01REQTYP=" + userPO.getType() + "", res);
				} else {	
				    ses.setAttribute("EBP0145Record", newmessage);
					forwardOnSuccess("EBP0145_payment_approval_list.jsp", req, res);
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}