package datapro.eibs.params;
/**
 * Maintenance of Insurance Calculations Table 
 * Creation date: (08/13/08)
 * @author: Catalina Sepulveda
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send List of ACH Batches 
 *	// SCREEN =    2    Request new record
 *	// SCREEN =    3    Request old record
 *	// SCREEN =    4	Delete record  
 *	// SCREEN =    5	Submit the record for Update
 */
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EACH31001Message;
import datapro.eibs.beans.EDL037501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0375 extends JSEIBSServlet { 
	 
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
			case 3 : // Request Maintenance Record
			case 4 : // Request Inquiry Record	
				procActionRec(user, req, res, session, screen);
				break;
			case 5 : // Request Inquiry Record	
				procSubmitRec(user, req, res, session, screen);
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
		MessageRecord record = null;
		ELEERRMessage error = null;
		try {
			mp = new MessageProcessor("EDL037501");
			EDL037501Message msg = (EDL037501Message) mp.getMessageRecord("EDL037501");
/*			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");*/
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EDL0375");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0015");

			// Send Message	
			mp.sendMessage(msg);
		
			//Receive Data
			JBObjList list =(JBObjList) mp.receiveMessageRecordList("H01FLGMAS");

			ses.setAttribute("tableList", list);
			
			if(mp.hasError(list)) {
				forward("Under_construction.jsp", req, res);				
			} else {
				forward("EDL0375_insurance_tables_list.jsp", req, res);
			}
			
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
			mp = new MessageProcessor("EDL037501");
			EDL037501Message msg = (EDL037501Message) mp.getMessageRecord("EDL037501");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EDL0375");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0004");
			
			switch (screen) {
				case 2 : // Request new Record
					userPO.setPurpose("NEW");
					break;
				case 3 : // Request old Record
					userPO.setPurpose("MAINT");
					break;
				case 4 : // Delete Record
					userPO.setPurpose("INQUIRY");
					break;

			}
			if(screen != 2){
//				Get key fields
				try {
					msg.setE01INSCOD(req.getParameter("KEY"));
				} catch (Exception e) {
					msg.setE01INSCOD("");
				}
				/*			if (screen == 5) {
				// Get all fields in page
				try {
					super.setMessageRecord(req, msg);
				} catch (Exception e) {	}
				}	*/			
				// Send Message 
				mp.sendMessage(msg);
	
				// Receive Error Message or Data Message (Optional)
				newmessage = mp.receiveMessageRecord();
				if(newmessage.getFormatName().equals("EDL037501")) {
					ses.setAttribute("EDL0375Record", newmessage);
					forwardOnSuccess("EDL0375_insurance_table.jsp", req, res);
	
				}
				
				newmessage = mp.receiveMessageRecord();
				if (mp.hasError(newmessage)) {
					ses.setAttribute("error", newmessage);
					forward("EDL0375_insurance_tables_list.jsp", req, res);
				} 
			}else{
				EDL037501Message record = new EDL037501Message();
				ses.setAttribute("EDL0375Record", record);
				forward("EDL0375_insurance_table.jsp", req, res);				
			}
			
			ses.setAttribute("userPO", userPO);


		} finally {
			if (mp != null) 
				mp.close();
		}
	}
	
//	 SCREEN = 2,3,5
	// Get or Process the Record  
	//
	protected void procSubmitRec(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean success = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send data
		try {
			mp = new MessageProcessor("EDL037501");
			EDL037501Message msg = (EDL037501Message) mp.getMessageRecord("EDL037501");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EDL0375");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0005");
		
			// Get all fields in page
			try {
				super.setMessageRecord(req, msg);
			} catch (Exception e) {	}
					
			ses.setAttribute("EDL0375Record", msg);
			// Send Message 
			mp.sendMessage(msg);

			// Receive Error Message or Data Message (Optional)
			ses.setAttribute("userPO", userPO);
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("error", newmessage);
				ses.setAttribute("EDL0375Record", msg);
				success = false;				
			}
				
			newmessage = mp.receiveMessageRecord();
			if(newmessage.getFormatName().equals("EDL037501")){
				ses.setAttribute("EDL0375Record", newmessage);	
			}
			
			if(success){				
				forward("EDL0375_insurance_tables_list.jsp", req, res);
			}else{
				forward("EDL0375_insurance_table.jsp", req, res);
			}
	
			

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}