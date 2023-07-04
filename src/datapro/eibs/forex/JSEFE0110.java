package datapro.eibs.forex;
/**
 * Maintenance of FX Fees  
 * Creation date: (08/15/08)
 * @author: Carlos Castillo
 * 
 *	// FX Fees
 *	// List of Options
 *	//
 *	// SCREEN =    1	Send List of FX Fees
 *	// SCREEN =    2    Request new record 
 *	// SCREEN =    5	Submit the list for Update
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEFE0110 extends JSEIBSServlet { 
	 
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
	// Send List of FX Fees  
	//
	private synchronized void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
			HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EFE0110");
			EFE011001Message msg = (EFE011001Message) mp.getMessageRecord("EFE011001"); 
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setPurpose("MAINTENANCE");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EFE0110");
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
				ses.setAttribute("EFE0110List", list);
			}
			forward("EFE0110_fx_fees_list.jsp", req, res);
			
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
		EFE011001Message msg = null;
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
			mp = new MessageProcessor("EFE0110");
			msg = (EFE011001Message) mp.getMessageRecord("EFE011001");
			
			for (int row = 0; row < totRows ; row++) {
				msg.setH01USERID(user.getH01USR());
				msg.setH01PROGRM("EFE0110");
				msg.setH01TIMSYS(getTimeStamp());  
				msg.setH01SCRCOD("01");
				msg.setH01OPECOD("0005");
						
				msg.setE01FEFBNK(req.getParameter("E01FEFBNK_" + row));
				msg.setE01FEFCOD(req.getParameter("E01FEFCOD_" + row));
				try {
					msg.setE01FEFFT1(req.getParameter("E01FEFFT1_" + row));
				} catch (Exception e) {
					msg.setE01FEFCCY(" ");
				}
				try {
					msg.setE01FEFFT2(req.getParameter("E01FEFFT2_" + row));
				} catch (Exception e) {
					msg.setE01FEFFT2(" ");
				}
				try {
					msg.setE01FEFCCY(req.getParameter("E01FEFCCY_" + row));
				} catch (Exception e) {
					msg.setE01FEFFA1("");
				}
				try {
				msg.setE01FEFCY1(req.getParameter("E01FEFCY1_" + row));
				} catch (Exception e) {
					msg.setE01FEFFA1("");
				}
				try {
				msg.setE01FEFFA1(req.getParameter("E01FEFFA1_" + row));
				} catch (Exception e) {
					msg.setE01FEFFA1("");
				}
				try {
					msg.setE01FEFBR1(req.getParameter("E01FEFBR1_" + row));
				} catch (Exception e) {
					msg.setE01FEFBR1(" ");
				}
				try {
					msg.setE01FEFBP1(req.getParameter("E01FEFBP1_" + row));
				} catch (Exception e) {
					msg.setE01FEFBP1("");
				}
				try {
					msg.setE01FEFMI1(req.getParameter("E01FEFMI1_" + row));
				} catch (Exception e) {
					msg.setE01FEFMI1("");
				}
				try {
					msg.setE01FEFMA1(req.getParameter("E01FEFMA1_" + row));
				} catch (Exception e) {
					msg.setE01FEFMA1("");
				}
				try {
					msg.setE01FEFGL1(req.getParameter("E01FEFGL1_" + row));
				} catch (Exception e) {
					msg.setE01FEFGL1("");
				}
				try {
					msg.setE01FEFCY2(req.getParameter("E01FEFCY2_" + row));
				} catch (Exception e) {
					msg.setE01FEFCY2("");
				}
				try {
					msg.setE01FEFFA2(req.getParameter("E01FEFFA2_" + row));
				} catch (Exception e) {
					msg.setE01FEFFA2("");
				}
				try {
					msg.setE01FEFBR2(req.getParameter("E01FEFBR2_" + row));
				} catch (Exception e) {
					msg.setE01FEFBR2("");
				}
				try {
					msg.setE01FEFBP2(req.getParameter("E01FEFBP2_" + row));
				} catch (Exception e) {
					msg.setE01FEFBP2("");
				}
				try {
					msg.setE01FEFMI2(req.getParameter("E01FEFMI2_" + row));
				} catch (Exception e) {
					msg.setE01FEFMI2("");
				}
				try {
					msg.setE01FEFMA2(req.getParameter("E01FEFMA2_" + row));
				} catch (Exception e) {
					msg.setE01FEFMA2("");
				}
				try {
					msg.setE01FEFGL2(req.getParameter("E01FEFGL2_" + row));
				} catch (Exception e) {
					msg.setE01FEFGL2("");
				}
				try {
					msg.setE01FEFTNM(req.getParameter("E01FEFTNM_" + row));
				} catch (Exception e) {
					msg.setE01FEFTNM("");
				}
				
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
			msg.setH01PROGRM("EFE0110");
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
				forward("EFE0110_fx_fees_list.jsp", req, res);
			} else {
				redirect("datapro.eibs.forex.JSEFE0110?SCREEN=1&FromRecord=0", res);
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}