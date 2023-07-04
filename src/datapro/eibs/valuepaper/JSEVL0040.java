package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (6/30/04 1:20:00 PM)
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

public class JSEVL0040 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_LIST_ASSIGN = 100;
	protected static final int R_LIST_REASSIGN = 200;
	protected static final int R_LIST_UPDATE = 300;
	protected static final int A_ASSIGN = 400;
	protected static final int A_REASSIGN = 500;
	protected static final int A_UPDATE = 600;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0040() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0040");

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
	protected void procReqListAssign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL004001Message msgSearch = null;
		EVL004001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL004001Message) mc.getMessageRecord("EVL004001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EVL0040");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL004001 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL004001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL004001Message) newmessage;
	
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
					
					flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_assign.jsp");
					callPage(LangPath + "EVL0040_value_paper_stock_assign.jsp", req, res);						
				
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
	protected void procActionAssign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EVL004001Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL004001Message) beanList.getRecord();			
			msgMT.setH01OPECOD("0005");
						
			msgMT.setE01MOVUSR(req.getParameter("E01MOVUSR"));
			msgMT.setE01MOVQTY(req.getParameter("E01MOVQTY"));
			msgMT.setE01MOVINI(req.getParameter("E01MOVINI"));
			msgMT.setE01MOVFIN(req.getParameter("E01MOVFIN"));
				
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
	
			if (newmessage.getFormatName().equals("EVL004001")) {
				
				msgMT = (EVL004001Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0040?SCREEN=100'";
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
						flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_assign_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0040_value_paper_stock_assign_detail.jsp?ROW=" + row);								
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
	protected void procReqListReassign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL004002Message msgSearch = null;
		EVL004002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL004002Message) mc.getMessageRecord("EVL004002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EVL0040");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL004002 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL004002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL004002Message) newmessage;
	
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
				ses.setAttribute("appList", beanList);				
				
				try {
					
					flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_reassign.jsp");
					callPage(LangPath + "EVL0040_value_paper_stock_reassign.jsp", req, res);						
				
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
	protected void procActionReassign(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EVL004002Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL004002Message) beanList.getRecord();			
			msgMT.setH02OPECOD("0005");
						
			msgMT.setE02MOVUSR(req.getParameter("E02MOVUSR"));
			msgMT.setE02MOVQTY(req.getParameter("E02MOVQTY"));
			msgMT.setE02MOVINI(req.getParameter("E02MOVINI"));
			msgMT.setE02MOVFIN(req.getParameter("E02MOVFIN"));
				
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
	
			if (newmessage.getFormatName().equals("EVL004002")) {
				
				msgMT = (EVL004002Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0040?SCREEN=200'";
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
						flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_reassign_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0040_value_paper_stock_reassign_detail.jsp?ROW=" + row);								
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
		EVL004003Message msgSearch = null;
		EVL004003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL004003Message) mc.getMessageRecord("EVL004003");
			msgSearch.setH03USERID(user.getH01USR());
			msgSearch.setH03PROGRM("EVL0040");
			msgSearch.setH03TIMSYS(getTimeStamp());
			msgSearch.setH03SCRCOD("01");
			msgSearch.setH03OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL004003 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL004003")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL004003Message) newmessage;
	
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
					
					flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_update.jsp");
					callPage(LangPath + "EVL0040_value_paper_stock_update.jsp", req, res);						
				
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
	
		EVL004003Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL004003Message) beanList.getRecord();			
			msgMT.setH03OPECOD("0005");
						
			msgMT.setE03DOCAQT(req.getParameter("E03DOCAQT"));
			msgMT.setE03DOCAIN(req.getParameter("E03DOCAIN"));
			msgMT.setE03DOCAFI(req.getParameter("E03DOCAFI"));
			msgMT.setE03DOCMOT(req.getParameter("E03DOCMOT"));
				
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
	
			if (newmessage.getFormatName().equals("EVL004003")) {
				
				msgMT = (EVL004003Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0040?SCREEN=300'";
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
						flexLog("About to call Page: " + LangPath + "EVL0040_value_paper_stock_update_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0040_value_paper_stock_update_detail.jsp?ROW=" + row);								
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
						case A_UPDATE :
							procActionUpdate(mc,msgUser, req, res, session);
							break;
						case A_REASSIGN :
							procActionReassign(mc,msgUser, req, res, session);
							break;
						case R_LIST_ASSIGN :							
							procReqListAssign(mc, msgUser, req, res, session);
							break;	
						case R_LIST_UPDATE :							
							procReqListUpdate(mc, msgUser, req, res, session);
							break;
						case R_LIST_REASSIGN :							
							procReqListReassign(mc, msgUser, req, res, session);
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