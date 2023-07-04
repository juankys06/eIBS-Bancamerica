package datapro.eibs.ach;
/**
 * Maintenance of ACH Customers Authorizations 
 * Creation date: (04/21/08)
 * @author: Carlos Castillo
 * ACH Authorizations
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send List of ACH Customer Authorizations
 *	// SCREEN =    2    Request new record
 *	// SCREEN =    3    Request old record
 *	// SCREEN =    4	Delete record  
 *	// SCREEN =    5	Submit the record for Update
 */
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEACH300 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		switch (screen) {
			// Requests List 
			case 1 :
				procReqList(user, req, res, session);
				break;
			case 2 : // Request new Record
			case 3 : // Request old Record
			case 4 : // Delete record	
			case 5 : // Submit the Record for update
				procActionRec(user, req, res, session, screen);
				break;
			default :
				forward(SuperServlet.devPage, req, res);
				break;
		}
	}

	// SCREEN = 1 
	// Send List of ACH Operators  
	//
	protected void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		String searchType = "";
		try {
			mp = new MessageProcessor("EACH300");
			EACH30001Message msg = (EACH30001Message) mp.getMessageRecord("EACH30001");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH300");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			
			try {
				msg.setE01NUMREC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msg.setE01NUMREC("0"); 
			}
			try {
				searchType = req.getParameter("SEARCHTYPE");
			} catch (Exception e) { }
			if (searchType == null) {
				searchType = "A";
			}
			if (searchType.equals("C")) {
				try {
					msg.setE01ACACUN(req.getParameter("SEARCHCDE"));
				} catch (Exception e) { 
					msg.setE01ACACUN("");
				}
			} else if (searchType.equals("O")) {
				try {
					msg.setE01ACAOCD(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {	
					msg.setE01ACAOCD("");
				}
			} else if(searchType.equals("N")) {
				try {
					msg.setE01OCDDSC(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {
					msg.setE01OCDDSC("");
				}
			} else if(searchType.equals("A")) {
				try {
					msg.setE01ACAACC(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {
					msg.setE01ACAACC("");
				}
			}
			msg.setH01FLGWK1(searchType);
			userPO.setType(msg.getH01FLGWK1());
			try {
				userPO.setHeader11(req.getParameter("SEARCHCDE"));
			} catch (Exception e) { }	
			userPO.setCusNum(msg.getE01ACACUN());
			userPO.setProdCode(msg.getE01ACAOCD());
			userPO.setHeader10(msg.getE01OCDDSC());
			userPO.setAccNum(msg.getE01ACAACC());
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("EACH300List", list);
			}
			forwardOnSuccess("EACH300_ach_authorizations_list.jsp", req, res);
		} finally {
			if (mp != null)
				mp.close();
		}
	}

	// SCREEN = 2,3,5
	// Get or Process the Record  
	//
	protected void procActionRec(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Send data
		try {
			mp = new MessageProcessor("EACH300");
			EACH30001Message msg = (EACH30001Message) mp.getMessageRecord("EACH30001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH300");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			switch (screen) {
				case 2 : // Request new Record
					msg.setH01OPECOD("0002");
					userPO.setPurpose("NEW");
					break;
				case 3 : // Request old Record
					msg.setH01OPECOD("0003");
					break;
				case 4 : // Delete Record
					msg.setH01OPECOD("0004");
					break;
				case 5 : // Submit the Record for update
					msg.setH01OPECOD("0005");
					break;
				default :
					forward(SuperServlet.devPage, req, res);
					break;
			}
			//	Get key fields
			try {
				msg.setE01ACAACC(req.getParameter("E01ACAACC"));
			} catch (Exception e) {
				msg.setE01ACAACC("");
			}
			try {
				msg.setE01ACAOCD(req.getParameter("E01ACAOCD"));
			} catch (Exception e) {
				msg.setE01ACAOCD("");
			}
			if (screen == 5) {
				// Get all fields in page
				try {
					super.setMessageRecord(req, msg);
				} catch (Exception e) {				
				}
				if (msg.getE01ACASTS().equals(""))
					msg.setE01ACASTS("A");
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
				if (screen == 2 || screen == 3 || screen == 4) {
					forward("EACH300_ach_authorizations_list.jsp", req, res);
				} else {
					//	Receive Data
					newmessage = mp.receiveMessageRecord();
					ses.setAttribute("EACH300Record", newmessage);
					forward("EACH300_ach_authorizations.jsp", req, res);
				}

			} else {
				if (screen == 4 || screen == 5) {
					redirectToPage("/servlet/datapro.eibs.ach.JSEACH300?SCREEN=1&FromRecord=0", res);
				} else {
					ses.setAttribute("EACH300Record", newmessage);
					forwardOnSuccess("EACH300_ach_authorizations.jsp", req, res);
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}