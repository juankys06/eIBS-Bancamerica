package datapro.eibs.moneytransfer;

/**
 * Insert the type's description here.
 * Creation date: (4/22/04 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEFE0510 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_LIST_APPROVAL = 100;
	protected static final int R_LIST_RECEIVE = 200;
	protected static final int R_LIST_SEND = 300;
	protected static final int R_LIST_REV = 350;
	protected static final int A_APPROVAL = 400;
	protected static final int A_RECEIVE = 500;
	protected static final int A_SEND = 600;
	protected static final int A_REV = 650;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEFE0510() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEFE0520");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE051001Message msgSearch = null;
		EFE051001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EFE051001Message) mc.getMessageRecord("EFE051001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EFE0510");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EFE051001 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EFE051001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EFE051001Message) newmessage;
	
					marker = msgList.getH01FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("appList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_approval.jsp");
					callPage(LangPath + "EFE0510_money_transf_approval.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListSend(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE051002Message msgSearch = null;
		EFE051002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EFE051002Message) mc.getMessageRecord("EFE051002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EFE0510");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EFE051002 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EFE051002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EFE051002Message) newmessage;
	
					marker = msgList.getH02FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("sendList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_send.jsp");
					callPage(LangPath + "EFE0510_money_transf_send.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListReceive(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE051003Message msgSearch = null;
		EFE051003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EFE051003Message) mc.getMessageRecord("EFE051003");
			msgSearch.setH03USERID(user.getH01USR());
			msgSearch.setH03PROGRM("EFE0510");
			msgSearch.setH03TIMSYS(getTimeStamp());
			msgSearch.setH03SCRCOD("01");
			msgSearch.setH03OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EFE051003 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EFE051003")) {
		    	
				beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EFE051003Message) newmessage;
	
					marker = msgList.getH03FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
				newmessage = mc.receiveMessage();
				}
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("receiveList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_receive.jsp");
					callPage(LangPath + "EFE0510_money_transf_receive.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListRever(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE051004Message msgSearch = null;
		EFE051004Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EFE051004Message) mc.getMessageRecord("EFE051004");
			msgSearch.setH04USERID(user.getH01USR());
			msgSearch.setH04PROGRM("EFE0510");
			msgSearch.setH04TIMSYS(getTimeStamp());
			msgSearch.setH04SCRCOD("01");
			msgSearch.setH04OPECOD("0015");
	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EFE051004 Message Sent");
		
	
			// Receive Message
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EFE051004")) {
		    	
				beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EFE051004Message) newmessage;
	
					marker = msgList.getH04FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
				newmessage = mc.receiveMessage();
				}
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("reverList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_reverse.jsp");
					callPage(LangPath + "EFE0510_money_transf_reverse.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EFE051001Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			String action = req.getParameter("H01FLGWK1");			
			msgMT = (EFE051001Message) beanList.getRecord();			
			msgMT.setH01OPECOD("0005");
			msgMT.setH01FLGWK1(action);
			
			if (action.equals("A")) {
				msgMT.setE01REMAAM(req.getParameter("E01REMAAM"));
			} else {
				msgMT.setE01REMRR1(req.getParameter("E01REMRR1"));
				msgMT.setE01REMRR2(req.getParameter("E01REMRR2"));
			}
				
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EFE051001")) {
				
				msgMT = (EFE051001Message) newmessage;
				
				if (IsNotError) {
					if (action.equals("A")) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=100'";
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println("		top.opener.location.href = \""+ href + "\";");
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("<P>Close it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
					} else {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=100");
					}
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					if (action.equals("A")) {
						beanList.setRecord(msgMT,row);
						ses.setAttribute("appList", beanList);
						try {
							flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_app_detail.jsp");
							res.sendRedirect(req.getContextPath() + LangPath + "/EFE0510_money_transf_app_detail.jsp?ROW=" + row);								
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_approval.jsp");
							callPage(LangPath + "EFE0510_money_transf_approval.jsp", req, res);						

						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionSend(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EFE051002Message msgMT = null;
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		
		try {
			
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("sendList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EFE051002Message) beanList.getRecord();	
			msgMT.setH02OPECOD("0005");
			
			msgMT.setE02REMPOR(req.getParameter("E02REMPOR"));
			msgMT.setE02REMPLO(req.getParameter("E02REMPLO"));
			msgMT.setE02REMD01(req.getParameter("E02REMD01"));
			msgMT.setE02REMD02(req.getParameter("E02REMD02"));
			msgMT.setE02REMD03(req.getParameter("E02REMD03"));
			msgMT.setE02REMD04(req.getParameter("E02REMD04"));
			msgMT.setE02REMD05(req.getParameter("E02REMD05"));
			msgMT.setE02REMD06(req.getParameter("E02REMD06"));
			msgMT.setE02REMD07(req.getParameter("E02REMD07"));
			msgMT.setE02REMD08(req.getParameter("E02REMD08"));
			msgMT.setE02REMD09(req.getParameter("E02REMD09"));
			msgMT.setE02REMD10(req.getParameter("E02REMD10"));
			msgMT.setE02REMD11(req.getParameter("E02REMD11"));
			msgMT.setE02REMD12(req.getParameter("E02REMD12"));
			msgMT.setE02REMD13(req.getParameter("E02REMD13"));
			msgMT.setE02REMD14(req.getParameter("E02REMD14"));
			msgMT.setE02REMD15(req.getParameter("E02REMD15"));
			msgMT.setE02REMD16(req.getParameter("E02REMD16"));
			msgMT.setE02REMD17(req.getParameter("E02REMD17"));
			msgMT.setE02REMD18(req.getParameter("E02REMD18"));
			msgMT.setE02REMD19(req.getParameter("E02REMD19"));
			msgMT.setE02REMD20(req.getParameter("E02REMD20"));
			msgMT.setE02RESQ01(req.getParameter("E02RESQ01"));
			msgMT.setE02RESQ02(req.getParameter("E02RESQ02"));
			msgMT.setE02RESQ03(req.getParameter("E02RESQ03"));
			msgMT.setE02RESQ04(req.getParameter("E02RESQ04"));
			msgMT.setE02RESQ05(req.getParameter("E02RESQ05"));
			msgMT.setE02RESQ06(req.getParameter("E02RESQ06"));
			msgMT.setE02RESQ07(req.getParameter("E02RESQ07"));
			msgMT.setE02RESQ08(req.getParameter("E02RESQ08"));
			msgMT.setE02RESQ09(req.getParameter("E02RESQ09"));
			msgMT.setE02RESQ10(req.getParameter("E02RESQ10"));
			msgMT.setE02RESQ11(req.getParameter("E02RESQ11"));
			msgMT.setE02RESQ12(req.getParameter("E02RESQ12"));
			msgMT.setE02RESQ13(req.getParameter("E02RESQ13"));
			msgMT.setE02RESQ14(req.getParameter("E02RESQ14"));
			msgMT.setE02RESQ15(req.getParameter("E02RESQ15"));
			msgMT.setE02RESQ16(req.getParameter("E02RESQ16"));
			msgMT.setE02RESQ17(req.getParameter("E02RESQ17"));
			msgMT.setE02RESQ18(req.getParameter("E02RESQ18"));
			msgMT.setE02RESQ19(req.getParameter("E02RESQ19"));
			msgMT.setE02RESQ20(req.getParameter("E02RESQ20"));
			msgMT.setE02RESV01(req.getParameter("E02RESV01"));
			msgMT.setE02RESV02(req.getParameter("E02RESV02"));
			msgMT.setE02RESV03(req.getParameter("E02RESV03"));
			msgMT.setE02RESV04(req.getParameter("E02RESV04"));
			msgMT.setE02RESV05(req.getParameter("E02RESV05"));
			msgMT.setE02RESV06(req.getParameter("E02RESV06"));
			msgMT.setE02RESV07(req.getParameter("E02RESV07"));
			msgMT.setE02RESV08(req.getParameter("E02RESV08"));
			msgMT.setE02RESV09(req.getParameter("E02RESV09"));
			msgMT.setE02RESV10(req.getParameter("E02RESV10"));
			msgMT.setE02RESV11(req.getParameter("E02RESV11"));
			msgMT.setE02RESV12(req.getParameter("E02RESV12"));
			msgMT.setE02RESV13(req.getParameter("E02RESV13"));
			msgMT.setE02RESV14(req.getParameter("E02RESV14"));
			msgMT.setE02RESV15(req.getParameter("E02RESV15"));
			msgMT.setE02RESV16(req.getParameter("E02RESV16"));
			msgMT.setE02RESV17(req.getParameter("E02RESV17"));
			msgMT.setE02RESV18(req.getParameter("E02RESV18"));
			msgMT.setE02RESV19(req.getParameter("E02RESV19"));
			msgMT.setE02RESV20(req.getParameter("E02RESV20"));
			
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EFE051002")) {
				
				msgMT = (EFE051002Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=300'";
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println("		top.opener.location.href = \""+ href + "\";");
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("<P>Close it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					
					beanList.setRecord(msgMT,row);
					ses.setAttribute("sendList", beanList);
					try {
						flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_snd_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EFE0510_money_transf_snd_detail.jsp?ROW=" + row);								
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionReceive(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EFE051003Message msgMT = null;
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		
		try {
			
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("receiveList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EFE051003Message) beanList.getRecord();	
			msgMT.setH03OPECOD("0005");
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EFE051003")) {
				
				msgMT = (EFE051003Message) newmessage;
				
				if (IsNotError) {
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=200'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.location.href = \""+ href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					
					beanList.setRecord(msgMT,row);
					ses.setAttribute("receiveList", beanList);
					try {
						flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_rec_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EFE0510_money_transf_rec_detail.jsp?ROW=" + row);								
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionRever(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EFE051004Message msgMT = null;
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
			
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("reverList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EFE051004Message) beanList.getRecord();	
			msgMT.setH04OPECOD("0005");
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EFE051004")) {
				
				msgMT = (EFE051004Message) newmessage;
				
				if (IsNotError) {
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=350'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.location.href = \""+ href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					
					beanList.setRecord(msgMT,row);
					ses.setAttribute("reverList", beanList);
					try {
						flexLog("About to call Page: " + LangPath + "EFE0510_money_transf_rev_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EFE0510_money_transf_rev_detail.jsp?ROW=" + row);								
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_LIST_APPROVAL;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case A_APPROVAL :
							procActionApproval(mc,msgUser, req, res, session);
							break;
						case A_SEND :
							procActionSend(mc,msgUser, req, res, session);
							break;
						case A_RECEIVE :
							procActionReceive(mc,msgUser, req, res, session);
							break;
						case A_REV :
							procActionRever(mc,msgUser, req, res, session);
							break;
						case R_LIST_APPROVAL :							
							procReqListApproval(mc, msgUser, req, res, session);
							break;	
						case R_LIST_SEND :							
							procReqListSend(mc, msgUser, req, res, session);
							break;
						case R_LIST_RECEIVE :							
							procReqListReceive(mc, msgUser, req, res, session);
							break;
						case R_LIST_REV :							
							procReqListRever(mc, msgUser, req, res, session);
							break;					
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
}