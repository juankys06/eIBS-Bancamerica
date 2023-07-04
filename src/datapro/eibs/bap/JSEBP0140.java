package datapro.eibs.bap;
/**
 * Accounts Payable - Payments Selection
 * Creation date: (03/02/10)
 * @author: C. Castillo
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Send screen for selection parameters
 *	// SCREEN =    2    Send List with bills selected
 *	// SCREEN =    3    Modify Payment
 *  // SCREEN =    4    Confirm / Deny Payment
 *  // SCREEN =    5    Confirm All
 *  // SCREEN =    6    Validate & Modify Payment 
 */

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JSEBP0140 extends JSEIBSServlet { 
	 
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
		case 2 : // Send list with results for Process
			procReqList(user, req, res, session, screen);
			break;
		case 3 : // Request to Modify Payment
       	case 4 : // Confirm / Deny Payment
		case 5 : // Confirm All	
		case 6 : // Validate and modify Payment
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
			mp = new MessageProcessor("EBP0140");
			EBP014001Message msg = (EBP014001Message) mp.getMessageRecord("EBP014001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0140");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			try {
				msg.setE01REQTYP(req.getParameter("E01REQTYP"));
			} catch (Exception e) { 
				msg.setE01REQTYP("V");
			}
			ses.setAttribute("msgList", msg);
			forwardOnSuccess("EBP0140_pay_search.jsp", req, res);
		} finally {
			if (mp != null)
				mp.close();
		}
	}
	
	// SCREEN = 2 
	// Send List with Search Results  
	//
	protected void procReqList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		UserPos userPO = null;
		MessageProcessor mp = null;
		try {
			mp = new MessageProcessor("EBP0140");
			EBP014001Message msg = (EBP014001Message) mp.getMessageRecord("EBP014001");
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			// Send Initial data
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0140");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
			userPO.setPurpose("PAY");
						
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
				msg.setE01REQBNK(req.getParameter("E01REQBNK"));
			} catch (Exception e) { 
				msg.setE01REQBNK("01");
			}
			try {
				msg.setE01REQBRN(req.getParameter("E01REQBRN"));
			} catch (Exception e) { 
				msg.setE01REQBRN("");
			}
			try {
				msg.setE01REQPVI(req.getParameter("E01REQPVI"));
			} catch (Exception e) { 
				msg.setE01REQPVI("T");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDFM(req.getParameter("E01REQDF1"));
				} else {
					msg.setE01REQDFM(req.getParameter("E01REQDF2"));
				} 
			} catch (Exception e) { 
				msg.setE01REQDFM("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDFD(req.getParameter("E01REQDF2"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQDFD(req.getParameter("E01REQDF1"));
				} else {
					msg.setE01REQDFD(req.getParameter("E01REQDF3"));
				}
			} catch (Exception e) { 
				msg.setE01REQDFD("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDFY(req.getParameter("E01REQDF3"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQDFY(req.getParameter("E01REQDF3"));
				} else {
					msg.setE01REQDFY(req.getParameter("E01REQDF1"));
				}
			} catch (Exception e) { 
				msg.setE01REQDFY("0");
			}
			
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDTM(req.getParameter("E01REQDT1"));
				} else {
					msg.setE01REQDTM(req.getParameter("E01REQDT2"));
				} 
			} catch (Exception e) { 
				msg.setE01REQDTM("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDTD(req.getParameter("E01REQDT2"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQDTD(req.getParameter("E01REQDT1"));
				} else {
					msg.setE01REQDTD(req.getParameter("E01REQDT3"));
				}
			} catch (Exception e) { 
				msg.setE01REQDTD("0");
			}
			try {
				if (user.getE01DTF().equals("MDY")) {
				    msg.setE01REQDTY(req.getParameter("E01REQDT3"));
				} else if (user.getE01DTF().equals("DMY")) {
					msg.setE01REQDTY(req.getParameter("E01REQDT3"));
				} else {
					msg.setE01REQDTY(req.getParameter("E01REQDT1"));
				}
			} catch (Exception e) { 
				msg.setE01REQDFY("0");
			}
			
			userPO.setBank(msg.getE01REQBNK());
			userPO.setBranch(msg.getE01REQBRN());
			userPO.setType(msg.getE01REQTYP());
			userPO.setHeader1(msg.getE01REQPVI());
			userPO.setHeader2(req.getParameter("E01REQDF1"));
			userPO.setHeader3(req.getParameter("E01REQDF2"));
			userPO.setHeader4(req.getParameter("E01REQDF3"));
			userPO.setHeader5(req.getParameter("E01REQDT1"));
			userPO.setHeader6(req.getParameter("E01REQDT2"));
			userPO.setHeader7(req.getParameter("E01REQDT3"));
			userPO.setHeader8(msg.getE01REQDFY());
			userPO.setHeader9(msg.getE01REQDFM());
			userPO.setHeader10(msg.getE01REQDFD());
			userPO.setHeader11(msg.getE01REQDTY());
			userPO.setHeader12(msg.getE01REQDTM());
			userPO.setHeader13(msg.getE01REQDTD());
			
			// Send Message	
			mp.sendMessage(msg);
			ses.setAttribute("userPO", userPO);
			// Receive List
			JBObjList list =
				(JBObjList) mp.receiveMessageRecordList("E01INDOPE","E01NUMREC");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
				forward("EBP0140_pay_search.jsp", req, res);
			} else {
				ses.setAttribute("EBP0140List", list);
				forwardOnSuccess("EBP0140_pay_list.jsp", req, res);
			}
		} finally {
			if (mp != null)
				mp.close();
		}
	}
	
	//	 SCREEN = 3,4,5,6
	// Get or Process Record  
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
			mp = new MessageProcessor("EBP0140");
			EBP014001Message msg = (EBP014001Message) mp.getMessageRecord("EBP014001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EBP0140");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			
			msg.setE01REQBNK(userPO.getBank());
			msg.setE01REQBRN(userPO.getBranch());
			msg.setE01REQTYP(userPO.getType());
			msg.setE01REQPVI(userPO.getHeader1());
			msg.setE01REQDFY(userPO.getHeader8());
			msg.setE01REQDFM(userPO.getHeader9());
			msg.setE01REQDFD(userPO.getHeader10());
			msg.setE01REQDTY(userPO.getHeader11());
			msg.setE01REQDTM(userPO.getHeader12());
			msg.setE01REQDTD(userPO.getHeader13());
			
			switch (screen) {
			    case 3 : // Get Payment to modify 	    
			        msg.setH01OPECOD("0003");
			        break;
			    case 4 : // Confirm / Deny Payment
					msg.setH01OPECOD("0004");
					break;
			    case 5 : // Confirm All              		
					msg.setH01OPECOD("0005");
					break;
			    case 6 : // Validate and Update Payment              		
					msg.setH01OPECOD("0006");
					try {
						msg.setE01BPPPDM(req.getParameter("E01BPPPDM"));
					} catch (Exception e) { 
						msg.setE01BPPPDM("0");
					}
					try {
						msg.setE01BPPPDD(req.getParameter("E01BPPPDD"));
					} catch (Exception e) { 
						msg.setE01BPPPDD("0");
					}
					try {
						msg.setE01BPPPDY(req.getParameter("E01BPPPDY"));
					} catch (Exception e) { 
						msg.setE01BPPPDY("0");
					}
					try {
						msg.setE01BPPAMT(req.getParameter("E01BPPAMT"));
					} catch (Exception e) { 
						msg.setE01BPPAMT("0");
					}
					try {
						msg.setE01BPPPVI(req.getParameter("E01BPPPVI"));
					} catch (Exception e) { 
						msg.setE01BPPPVI("C");
					}
					try {
						msg.setE01BPPSTS(req.getParameter("E01BPPSTS"));
					} catch (Exception e) { 
						msg.setE01BPPSTS("C");
					}
					try {
						msg.setE01BPGAMT1(req.getParameter("E01BPGAMT1"));
					} catch (Exception e) { 
						msg.setE01BPGAMT1("0");
					}
					try {
						msg.setE01BPGAMT2(req.getParameter("E01BPGAMT2"));
					} catch (Exception e) { 
						msg.setE01BPGAMT2("0");
					}
					try {
						msg.setE01BPGAMT3(req.getParameter("E01BPGAMT3"));
					} catch (Exception e) { 
						msg.setE01BPGAMT3("0");
					}
					break;	
				default :
					forward(SuperServlet.devPage, req, res);
					break;
			}
			// Get page field
			try {
				msg.setE01BPBNUM(req.getParameter("E01BPBNUM"));
			} catch (Exception e) {
				msg.setE01BPBNUM("0");
			}
			try {
				msg.setE01BPBSEQ(req.getParameter("E01BPBSEQ"));
			} catch (Exception e) {
				msg.setE01BPBSEQ("000");
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
				if (screen == 6 ) {
					forward("EBP0140_pay_detail.jsp", req, res);
				} else {
					forward("EBP0140_pay_list.jsp", req, res);
				}
			} else {
				if (screen == 3 ) {
					ses.setAttribute("EBP0140Record", newmessage);
					forwardOnSuccess("EBP0140_pay_detail.jsp", req, res);
				} else {	
					redirectToPage("/servlet/datapro.eibs.bap.JSEBP0140?SCREEN=2&FromRecord=0" +
						"&E01REQBNK=" + userPO.getBank() +
						"&E01REQBRN=" + userPO.getBranch() +
				        "&E01REQTYP=" + userPO.getType() +
				        "&E01REQPVI=" + userPO.getHeader1() +
			  			"&E01REQDF1=" + userPO.getHeader2() +
			  			"&E01REQDF2=" + userPO.getHeader3() +
			  			"&E01REQDF3=" + userPO.getHeader4() +
			  			"&E01REQDT1=" + userPO.getHeader5() +
			  			"&E01REQDT2=" + userPO.getHeader6() +
			  			"&E01REQDT3=" + userPO.getHeader7() +
						"", res);
				}
			}

		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}