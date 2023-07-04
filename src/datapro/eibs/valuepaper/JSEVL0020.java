package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (6/25/04 3:37:15 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEVL0020 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_LIST_ASSIGN = 100;
	protected static final int R_LIST_RECEIVE = 200;
	protected static final int R_LIST_UPDATE = 300;
	protected static final int A_ASSIGN = 400;
	protected static final int A_RECEIVE = 500;
	protected static final int A_UPDATE = 600;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0020() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0020");

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
	protected void procReqListReceive(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL002002Message msgSearch = null;
		EVL002002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL002002Message) mc.getMessageRecord("EVL002002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EVL0020");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");

		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL002002 Message Sent");
		

		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL002002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EVL002002Message) newmessage;

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
				ses.setAttribute("receiveList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_req_receive.jsp");
					callPage(LangPath + "EVL0020_value_paper_req_receive.jsp", req, res);						
				
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
	protected void procReqListAssign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL002001Message msgSearch = null;
		EVL002001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
				
		userPO.setBank(user.getE01UBK());
		userPO.setBranch(user.getE01UBR());
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL002001Message) mc.getMessageRecord("EVL002001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EVL0020");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL002001 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL002001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL002001Message) newmessage;
	
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
					
					flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_req_assign.jsp");
					callPage(LangPath + "EVL0020_value_paper_req_assign.jsp", req, res);						
				
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
	protected void procActionReceive(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("receiveList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			EVL002002Message msgMT = (EVL002002Message) beanList.getRecord();		
			msgMT.setH02OPECOD("0005");
			mc.sendMessage(msgMT);
			msgMT.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
				if (IsNotError) {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.valuepaper.JSEVL0020?SCREEN=200");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_req_receive.jsp");
						callPage(LangPath + "EVL0020_value_paper_req_receive.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionAssign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EVL002001Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL002001Message) beanList.getRecord();			
			msgMT.setH01OPECOD("0005");
			msgMT.setE01REQAQT(req.getParameter("E01REQAQT"));
			msgMT.setE01BALLO1(req.getParameter("E01BALLO1"));
			msgMT.setE01BALBA1(req.getParameter("E01BALBA1"));
			msgMT.setE01BALIN1(req.getParameter("E01BALIN1"));
			msgMT.setE01BALFI1(req.getParameter("E01BALFI1"));
			msgMT.setE01BALAQ1(req.getParameter("E01BALAQ1"));
			msgMT.setE01BALAI1(req.getParameter("E01BALAI1"));
			msgMT.setE01BALAF1(req.getParameter("E01BALAF1"));
			msgMT.setE01BALLO2(req.getParameter("E01BALLO2"));
			msgMT.setE01BALBA2(req.getParameter("E01BALBA2"));
			msgMT.setE01BALIN2(req.getParameter("E01BALIN2"));
			msgMT.setE01BALFI2(req.getParameter("E01BALFI2"));
			msgMT.setE01BALAQ2(req.getParameter("E01BALAQ2"));
			msgMT.setE01BALAI2(req.getParameter("E01BALAI2"));
			msgMT.setE01BALAF2(req.getParameter("E01BALAF2"));
			msgMT.setE01BALLO3(req.getParameter("E01BALLO3"));
			msgMT.setE01BALBA3(req.getParameter("E01BALBA3"));
			msgMT.setE01BALIN3(req.getParameter("E01BALIN3"));
			msgMT.setE01BALFI3(req.getParameter("E01BALFI3"));
			msgMT.setE01BALAQ3(req.getParameter("E01BALAQ3"));
			msgMT.setE01BALAI3(req.getParameter("E01BALAI3"));
			msgMT.setE01BALAF3(req.getParameter("E01BALAF3"));
			msgMT.setE01BALLO4(req.getParameter("E01BALLO4"));
			msgMT.setE01BALBA4(req.getParameter("E01BALBA4"));
			msgMT.setE01BALIN4(req.getParameter("E01BALIN4"));
			msgMT.setE01BALFI4(req.getParameter("E01BALFI4"));
			msgMT.setE01BALAQ4(req.getParameter("E01BALAQ4"));
			msgMT.setE01BALAI4(req.getParameter("E01BALAI4"));
			msgMT.setE01BALAF4(req.getParameter("E01BALAF4"));
			msgMT.setE01BALLO5(req.getParameter("E01BALLO5"));
			msgMT.setE01BALBA5(req.getParameter("E01BALBA5"));
			msgMT.setE01BALIN5(req.getParameter("E01BALIN5"));
			msgMT.setE01BALFI5(req.getParameter("E01BALFI5"));
			msgMT.setE01BALAQ5(req.getParameter("E01BALAQ5"));
			msgMT.setE01BALAI5(req.getParameter("E01BALAI5"));
			msgMT.setE01BALAF5(req.getParameter("E01BALAF5"));
			msgMT.setE01BALLO6(req.getParameter("E01BALLO6"));
			msgMT.setE01BALBA6(req.getParameter("E01BALBA6"));
			msgMT.setE01BALIN6(req.getParameter("E01BALIN6"));
			msgMT.setE01BALFI6(req.getParameter("E01BALFI6"));
			msgMT.setE01BALAQ6(req.getParameter("E01BALAQ6"));
			msgMT.setE01BALAI6(req.getParameter("E01BALAI6"));
			msgMT.setE01BALAF6(req.getParameter("E01BALAF6"));
			msgMT.setE01BALLO7(req.getParameter("E01BALLO7"));
			msgMT.setE01BALBA7(req.getParameter("E01BALBA7"));
			msgMT.setE01BALIN7(req.getParameter("E01BALIN7"));
			msgMT.setE01BALFI7(req.getParameter("E01BALFI7"));
			msgMT.setE01BALAQ7(req.getParameter("E01BALAQ7"));
			msgMT.setE01BALAI7(req.getParameter("E01BALAI7"));
			msgMT.setE01BALAF7(req.getParameter("E01BALAF7"));
			msgMT.setE01BALLO8(req.getParameter("E01BALLO8"));
			msgMT.setE01BALBA8(req.getParameter("E01BALBA8"));
			msgMT.setE01BALIN8(req.getParameter("E01BALIN8"));
			msgMT.setE01BALFI8(req.getParameter("E01BALFI8"));
			msgMT.setE01BALAQ8(req.getParameter("E01BALAQ8"));
			msgMT.setE01BALAI8(req.getParameter("E01BALAI8"));
			msgMT.setE01BALAF8(req.getParameter("E01BALAF8"));
			msgMT.setE01BALLO9(req.getParameter("E01BALLO9"));
			msgMT.setE01BALBA9(req.getParameter("E01BALBA9"));
			msgMT.setE01BALIN9(req.getParameter("E01BALIN9"));
			msgMT.setE01BALFI9(req.getParameter("E01BALFI9"));
			msgMT.setE01BALAQ9(req.getParameter("E01BALAQ9"));
			msgMT.setE01BALAI9(req.getParameter("E01BALAI9"));
			msgMT.setE01BALAF9(req.getParameter("E01BALAF9"));
			msgMT.setE01BALLO0(req.getParameter("E01BALLO0"));
			msgMT.setE01BALBA0(req.getParameter("E01BALBA0"));
			msgMT.setE01BALIN0(req.getParameter("E01BALIN0"));
			msgMT.setE01BALFI0(req.getParameter("E01BALFI0"));
			msgMT.setE01BALAQ0(req.getParameter("E01BALAQ0"));
			msgMT.setE01BALAI0(req.getParameter("E01BALAI0"));
			msgMT.setE01BALAF0(req.getParameter("E01BALAF0"));
							
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
	
			if (newmessage.getFormatName().equals("EVL002001")) {
				
				msgMT = (EVL002001Message) newmessage;
				
				if (IsNotError) {
					
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0020?SCREEN=100'";
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
					ses.setAttribute("appList", beanList);
					try {
						flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_req_assign_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0020_value_paper_req_assign_detail.jsp?ROW=" + row);								
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
	protected void procReqListUpdate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL002003Message msgSearch = null;
		EVL002003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL002003Message) mc.getMessageRecord("EVL002003");
			msgSearch.setH03USERID(user.getH01USR());
			msgSearch.setH03PROGRM("EVL0020");
			msgSearch.setH03TIMSYS(getTimeStamp());
			msgSearch.setH03SCRCOD("01");
			msgSearch.setH03OPECOD("0015");
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL002003 Message Sent");
		
			// Receive Message
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EVL002003")) {
		    	
				beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL002003Message) newmessage;
	
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
				ses.setAttribute("appList", beanList);				
				
				try {
					flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_bal_update.jsp");
					callPage(LangPath + "EVL0020_value_paper_bal_update.jsp", req, res);						
				
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
	protected void procActionUpdate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EVL002003Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL002003Message) beanList.getRecord();			
			msgMT.setH03OPECOD("0005");
						
			msgMT.setE03BALAQT(req.getParameter("E03BALAQT"));
			msgMT.setE03BALAIN(req.getParameter("E03BALAIN"));
			msgMT.setE03BALAFI(req.getParameter("E03BALAFI"));
			msgMT.setE03BALMOT(req.getParameter("E03BALMOT"));
				
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
	
			if (newmessage.getFormatName().equals("EVL002003")) {
				
				msgMT = (EVL002003Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0020?SCREEN=300'";
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
					ses.setAttribute("appList", beanList);
					try {
						flexLog("About to call Page: " + LangPath + "EVL0020_value_paper_bal_update_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0020_value_paper_bal_update_detail.jsp?ROW=" + row);								
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

			int screen = R_LIST_ASSIGN;

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
						case A_ASSIGN :
							procActionAssign(mc,msgUser, req, res, session);
							break;
						case A_RECEIVE :
							procActionReceive(mc,msgUser, req, res, session);
							break;
						case A_UPDATE :
							procActionUpdate(mc,msgUser, req, res, session);
							break;
						case R_LIST_ASSIGN :							
							procReqListAssign(mc, msgUser, req, res, session);
							break;	
						case R_LIST_RECEIVE :							
							procReqListReceive(mc, msgUser, req, res, session);
							break;
						case R_LIST_UPDATE :							
							procReqListUpdate(mc, msgUser, req, res, session);
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