package datapro.eibs.bap;
/**
 * Maintenance of BAP Purchase Order
 * Creation date: (01/15/10)
 * @author: Luis I Soucarre
 * Purchase Order
 * 	// List of Options
 * 	//
 *  // SCREEN =    1	Send Screen for Parameters
 *	// SCREEN =    2	Send List of Purchase Orders
 *	// SCREEN =    3    Request new record
 *	// SCREEN =    4    Request old record
 *	// SCREEN =    5	Delete record  
 *	// SCREEN =    6	Submit the record for Update
 *  // SCREEN =    7	Send List Purchase Order for Inquiry
 */
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEBP0120 extends JSEIBSServlet { 
	 
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		switch (screen) {
			// Requests List 
			case 1 :
			case 6 :
				procReqList(user, req, res, session, screen);
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
	// Send List of BAP Operators  
	//
	protected void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		String searchType = "";
		try {
			mp = new MessageProcessor("EBP0120");
			EBP012001Message msg = (EBP012001Message) mp.getMessageRecord("EBP012001");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0120");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			if (screen == 1) {
				userPO.setPurpose("MAINTENANCE");
			} else {
				userPO.setPurpose("INQUIRY");
			}
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
					msg.setE01BPONUM(req.getParameter("SEARCHCDE"));
				} catch (Exception e) { 
					msg.setE01BPONUM("");
				}
			} else if (searchType.equals("O")) {
				try {
					msg.setE01BPODCA(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {	
					msg.setE01BPODCA("");
				}
			} else if(searchType.equals("N")) {
				try {
					msg.setE01BPOCCY(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {
					msg.setE01BPOCCY("");
				}
			} else if(searchType.equals("A")) {
				try {
					msg.setE01BPOVCO(req.getParameter("SEARCHCDE"));
				} catch (Exception e) {
					msg.setE01BPOVCO("");
				}
			}
			msg.setH01FLGWK1(searchType);
			userPO.setType(msg.getH01FLGWK1());
			try {
				userPO.setHeader11(req.getParameter("SEARCHCDE"));
			} catch (Exception e) { }	
			userPO.setCusNum(msg.getE01BPONUM());
			userPO.setProdCode(msg.getE01BPODCA());
			userPO.setHeader10(msg.getE01BPVNM1());
			userPO.setAccNum(msg.getE01BPOVCO());
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("EBP0120List", list);
			}
			forwardOnSuccess("EBP0120_purchase_order_list.jsp", req, res);
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

		// Send data
		try {
			mp = new MessageProcessor("EBP0120");
			EBP012001Message msg = (EBP012001Message) mp.getMessageRecord("EBP012001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0120");
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
				msg.setE01BPONUM(req.getParameter("E01BPONUM"));
			} catch (Exception e) {
				msg.setE01BPONUM("");
			}
			try {
				msg.setE01BPOVCO(req.getParameter("E01BPOVCO"));
			} catch (Exception e) {
				msg.setE01BPOVCO("");
			}
			try {
				msg.setE01BPODCA(req.getParameter("E01BPODCA"));
			} catch (Exception e) {
				msg.setE01BPODCA("");
			}
			if (screen == 5) {
				// Get all fields in page
				try {
					super.setMessageRecord(req, msg);
				} catch (Exception e) {				
				}
				if (msg.getE01BPOPST().equals(""))
					msg.setE01BPOPST("A");
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
					forward("EBP0120_purchase_order_list.jsp", req, res);
				} else {
					//	Receive Data
					newmessage = mp.receiveMessageRecord();
					ses.setAttribute("EBP0120Record", newmessage);
					forward("EBP0120_purchase_order.jsp", req, res);
				}

			} else {
				if (screen == 4 || screen == 5) {
					redirectToPage("/servlet/datapro.eibs.bap.JSEBP0120?SCREEN=1&FromRecord=0", res);
				} else {
					ses.setAttribute("EBP0120Record", newmessage);
					forwardOnSuccess("EBP0120_purchase_order.jsp", req, res);
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}