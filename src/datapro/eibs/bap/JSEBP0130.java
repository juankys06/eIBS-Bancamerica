package datapro.eibs.bap;
/**
 * Bills - Accounts Payable 
 * Creation date: (10/20/09)
 * @author: Linet Riaño
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send screen for selection parameters
 *	// SCREEN =    2    Send List with results for inquiry
 *	// SCREEN =    3    Send Bills Detail - old record
 *	// SCREEN =    4    Delete record
 *  // SCREEN =    5    Send New record
 *  // SCREEN =    6    Submit Record for update
 *  // SCREEN =    7    Inquiry
 *  // SCREEN =   15    Approval / Rejected /Suspended
 */

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEBP0130 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		switch (screen) {
			//  
		case 1 : // Request Search Parameters 
			procReqSrhPrm(user, req, res, session);
			break;
		case 2 : // Send list with results for inquery
		case 20: // Send list for Approval / Rejected /Suspended
		    procReqList(user, req, res, session, screen);
			break;  
		case 3 : // Send Bill Detail - Request old record
		case 4 : // Delete record	
		case 5 : // New Record
        case 6 : // Submit Record for update
        case 7 : // Inquiry
        case 15: // Approval / Rejected / Suspended
            procActionRec(user, req, res, session, screen);
			break;
		default :
			forward(SuperServlet.devPage, req, res);
			break;
		}
	}

	// SCREEN = 1 
	// Send Screen for Search Parameters  
	//
	protected void procReqSrhPrm(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("ENTER");
		ses.setAttribute("userPO", userPO);
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EBP0130");
			EBP013001Message msg = (EBP013001Message) mp.getMessageRecord("EBP013001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0130");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			try {
				msg.setE01REQTYP(req.getParameter("E01REQTYP"));
			} catch (Exception e) { 
				msg.setE01REQTYP("V");
			}
			ses.setAttribute("msgList", msg);
			forwardOnSuccess("EBP0130_bills_search.jsp", req, res);
		} finally {
			if (mp != null)
				mp.close();
		}
		
	}
	
	// SCREEN = 2, 20 
	// Send List with Search Results  
	//
	protected void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EBP0130");
			EBP013001Message msg = (EBP013001Message) mp.getMessageRecord("EBP013001");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0130");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			
			switch (screen) {
		    case 2 : // List Bills
		        msg.setH01OPECOD("0001");
				userPO.setPurpose("INQUIRY");
			    break;
		    case 20 : // List bills approval
		        msg.setH01OPECOD("0020");
				userPO.setPurpose("APPROVAL");
				break;
			default :
				forward(SuperServlet.devPage, req, res);
				break;
		}			
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
			try {
				msg.setE01REQORD(req.getParameter("E01REQORD"));
			} catch (Exception e) { 
				msg.setE01REQORD("D");
			}
			try {
				msg.setE01REQSTS(req.getParameter("E01REQSTS"));
			} catch (Exception e) { 
				msg.setE01REQSTS("W");
			}
			try {
				msg.setE01REQFRM(req.getParameter("E01REQFRM"));
			} catch (Exception e) { 
				msg.setE01REQFRM("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQPDM(req.getParameter("E01REQDA1"));
				} else {
					msg.setE01REQPDM(req.getParameter("E01REQDA2"));
				} 
			} catch (Exception e) { 
				msg.setE01REQPDM("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQPDD(req.getParameter("E01REQDA2"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQPDD(req.getParameter("E01REQDA1"));
				} else {
					msg.setE01REQPDD(req.getParameter("E01REQDA3"));
				}
			} catch (Exception e) { 
				msg.setE01REQPDD("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQPDY(req.getParameter("E01REQDA3"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQPDY(req.getParameter("E01REQDA3"));
				} else {
					msg.setE01REQPDY(req.getParameter("E01REQDA1"));
				}
			} catch (Exception e) { 
				msg.setE01REQPDY("0");
			}
			
			userPO.setHeader1(msg.getE01REQTYP());
			userPO.setHeader2(msg.getE01REQORD());
			userPO.setHeader3(msg.getE01REQSTS());
			userPO.setHeader4(msg.getE01REQFRM());
			userPO.setHeader5(msg.getE01REQPDM());
			userPO.setHeader6(msg.getE01REQPDD());
			userPO.setHeader7(msg.getE01REQPDY());
			
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("EBP0130List", list);
			}
			forwardOnSuccess("EBP0130_bills_list.jsp", req, res);
		} finally {
			if (mp != null)
				mp.close();
		}
	}

	//	 SCREEN = 3,4,5,6,15
	// Get or Process Record  
	//
	protected void procActionRec(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send data
		try {
			mp = new MessageProcessor("EBP0130");
			EBP013001Message msg = (EBP013001Message) mp.getMessageRecord("EBP013001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0130");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			
			msg.setE01REQTYP(userPO.getHeader1());
			msg.setE01REQORD(userPO.getHeader2());
			msg.setE01REQSTS(userPO.getHeader3());
			msg.setE01REQFRM(userPO.getHeader4());
			msg.setE01REQPDM(userPO.getHeader5());
			msg.setE01REQPDD(userPO.getHeader6());
			msg.setE01REQPDY(userPO.getHeader7());
			
			switch (screen) {
			    case 3 : // Request old Record
			        msg.setH01OPECOD("0003");
			        userPO.setPurpose("MAINTENANCE");
				    break;
			    case 4 : // Delete Record
					msg.setH01OPECOD("0004");
					break;
			    case 5 : // Request new Record
					msg.setH01OPECOD("0002");
					userPO.setPurpose("NEW");
					break;
				case 6 : // Submit the Record for update
					msg.setH01OPECOD("0005");
					break;
				case 7 : // Request old Record
			        msg.setH01OPECOD("0003");
			        userPO.setPurpose("INQUIRY");
				    break;
				case 15 : // 
			        msg.setH01OPECOD("0015");
			        userPO.setPurpose("APPROVAL");
				    break;
				default :
					forward(SuperServlet.devPage, req, res);
					break;
			}
			// Get page field
			try {
				msg.setE01BPBNUM(req.getParameter("E01BPBNUM"));
			} catch (Exception e) {
				msg.setE01BPBNUM("888");
			}			
			try {
				msg.setE01ACT(req.getParameter("E01ACT"));
			} catch (Exception e) {
				msg.setE01ACT("W");
			}
			if (screen == 6) {
				// Get all fields in page
				try {
					super.setMessageRecord(req, msg);
				} catch (Exception e) {				
				}
			}
			
			msg.setH01FLGWK1(""); 
			// Send Message 
			mp.sendMessage(msg);

			// Receive Error Message or Data Message (Optional)
			ses.setAttribute("userPO", userPO);
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("error", newmessage);
				if (screen == 3 || screen == 4 || screen == 5) {
					redirectToPage("/servlet/datapro.eibs.bap.JSEBP0130?SCREEN=2&FromRecord=0" +
					        "&E01REQTYP=" + userPO.getHeader1() + 
				  			"&E01REQORD=" + userPO.getHeader2() +
				  			"&E01REQSTS=" + userPO.getHeader3() +
				  			"&E01REQFRM=" + userPO.getHeader4() +
				  			"&E01REQPDM=" + userPO.getHeader5() +
				  			"&E01REQPDD=" + userPO.getHeader6() +
				  			"&E01REQPDY=" + userPO.getHeader7() +
							"", res);
				} else {
					// Receive Data Error
					newmessage = mp.receiveMessageRecord();
					ses.setAttribute("EBP0130Record", newmessage);
					forward("EBP0130_bills_detail.jsp", req, res);
				}

			} else {
				if (screen == 4 || screen == 6) {			
					redirectToPage("/servlet/datapro.eibs.bap.JSEBP0130?SCREEN=2&FromRecord=0"+
					        "&E01REQTYP=" + userPO.getHeader1() + 
				  			"&E01REQORD=" + userPO.getHeader2() +
				  			"&E01REQSTS=" + userPO.getHeader3() +
				  			"&E01REQFRM=" + userPO.getHeader4() +
				  			"&E01REQPDM=" + userPO.getHeader5() +
				  			"&E01REQPDD=" + userPO.getHeader6() +
				  			"&E01REQPDY=" + userPO.getHeader7() +
							"", res);
				} else {
				    if (screen == 15) {
				        redirectToPage("/servlet/datapro.eibs.bap.JSEBP0130?SCREEN=20" +
				        		"&E01REQTYP=" + userPO.getHeader1() + "", res);
				    }
				    else{
				    // Receive Data
					ses.setAttribute("EBP0130Record", newmessage);
					forwardOnSuccess("EBP0130_bills_detail.jsp", req, res);
				    }
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}