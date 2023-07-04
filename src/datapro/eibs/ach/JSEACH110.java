package datapro.eibs.ach;
/**
 * Maintenance of ACH Operators 
 * Creation date: (04/21/08)
 * @author: Carlos Castillo
 * ACH Parameters
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send List of ACH Operators
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

public class JSEACH110 extends JSEIBSServlet { 
	 
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
		try {
			mp = new MessageProcessor("EACH110");
			EACH11001Message msg = (EACH11001Message) mp.getMessageRecord("EACH11001");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH110");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			try {
				msg.setE01NUMREC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msg.setE01NUMREC("0"); 
			}
			try {
				msg.setE01ACOCDE(req.getParameter("SEARCHCDE"));
			} catch (Exception e) {
				msg.setE01ACOCDE("");
			}
			userPO.setProdCode(msg.getE01ACOCDE());
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("EACH110List", list);
			}
			forward("EACH110_ach_operators_list.jsp", req, res);
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
			mp = new MessageProcessor("EACH110");
			EACH11001Message msg = (EACH11001Message) mp.getMessageRecord("EACH11001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH110");
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
			// Get key field
			try {
				msg.setE01ACOCDE(req.getParameter("E01ACOCDE"));
			} catch (Exception e) {
				msg.setE01ACOCDE("");
			}
			if (screen == 5) {
				// Get all fields in page
				try {
					super.setMessageRecord(req, msg);
				} catch (Exception e) {				
				}
				if (msg.getE01ACOTYP().equals(""))
					msg.setE01ACOTYP("O");
				if (msg.getE01ACOOCO().equals(""))
					msg.setE01ACOOCO("O");
				if (msg.getE01ACOUTC().equals(""))
					msg.setE01ACOUTC("1");
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
					forward("EACH110_ach_operators_list.jsp", req, res);
				} else {
					// Receive Data
					newmessage = mp.receiveMessageRecord();
					ses.setAttribute("EACH110Record", newmessage);
					forward("EACH110_ach_operators.jsp", req, res);
				}

			} else {
				if (screen == 4 || screen == 5) {
					redirectToPage("/servlet/datapro.eibs.ach.JSEACH110?SCREEN=1&FromRecord=0", res);
				} else {
					ses.setAttribute("EACH110Record", newmessage);
					forwardOnSuccess("EACH110_ach_operators.jsp", req, res);
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}