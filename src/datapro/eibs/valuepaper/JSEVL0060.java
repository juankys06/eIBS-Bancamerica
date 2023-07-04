package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (7/01/04 11:07:25 AM)
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

public class JSEVL0060 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH = 100;
	protected static final int A_SEARCH = 200;
	protected static final int A_MOV = 300;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0060() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0060");

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
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EVL006001Message msgSearch = new EVL006001Message();
		ses.setAttribute("msgMT", msgSearch);
		
		try {
			
			flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_bal_inq.jsp");
			callPage(LangPath + "EVL0060_value_paper_agent_bal_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL006001Message msgSearch = null;
		EVL006002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (JBObjList) ses.getAttribute("mtListAgent");

		if (beanList == null) {
			int posi = 0;
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgSearch = (EVL006001Message) mc.getMessageRecord("EVL006001");
				msgSearch.setH01USERID(user.getH01USR());
				msgSearch.setH01PROGRM("EVL0060");
				msgSearch.setH01TIMSYS(getTimeStamp());
				msgSearch.setH01SCRCOD("01");
				msgSearch.setH01OPECOD("0015");
				//all the fields here
				 java.util.Enumeration enu = msgSearch.fieldEnumeration();
				 MessageField field = null;
				 String value = null;
				 while (enu.hasMoreElements()) {
					 field = (MessageField) enu.nextElement();
					 try {
						 value = req.getParameter(field.getTag()).toUpperCase().trim();
						 if (value != null) {
							 field.setString(value);
						 }
					 } catch (Exception e) {
					 }
				 }
			    
				msgSearch.send();
				msgSearch.destroy();
				flexLog("EVL006001 Message Sent");
			
	
				// Receive Message
			
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ELEERR")) {
	
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					ses.setAttribute("error", msgError);
				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				newmessage = mc.receiveMessage();
			   
			    if (newmessage.getFormatName().equals("EVL006002")) {
			    	
			    	beanList = new JBObjList();
					String marker = "";
	
					while (true) {
	
						msgList = (EVL006002Message) newmessage;
	
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
					ses.setAttribute("mtListAgent", beanList);				
					
					try {
						if (IsNotError) { // There are no errors
							flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_bal_list.jsp");
							callPage(LangPath + "EVL0060_value_paper_agent_bal_list.jsp", req, res);						
						} else {
							flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_bal_inq.jsp");
							callPage(LangPath + "EVL0060_value_paper_agent_bal_inq.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (newmessage.getFormatName().equals("EVL006001")) {
					
					msgSearch = (EVL006001Message) newmessage;
					ses.setAttribute("msgMT", msgSearch);
					
					try {
						flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_bal_inq.jsp");
						callPage(LangPath + "EVL0060_value_paper_agent_bal_inq.jsp", req, res);
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
		} else {
			flexLog("Putting java beans into the session");
			ses.setAttribute("mtList", beanList);				
	 				
			try {
				flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_bal_list.jsp");
				callPage(LangPath + "EVL0060_value_paper_agent_bal_list.jsp", req, res);						
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListMov(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL006002Message msgSearch = null;
		EVL006003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		JBObjList bl = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
	
			bl = (JBObjList) ses.getAttribute("mtListAgent");

			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);
	
			msgSearch = (EVL006002Message) bl.getRecord();
	
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EVL0060");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");
	
			mc.sendMessage(msgSearch);
			//msgSearch.destroy();
			flexLog("EVL006002 Message Sent");
	
			// Receive Message
			newmessage = mc.receiveMessage();
			   
			if (newmessage.getFormatName().equals("EVL006003")) {
			    	
				beanList = new JBObjList();
				String marker = "";
				boolean firstRec = true;
	
				while (true) {
	
					msgList = (EVL006003Message) newmessage;
	
					marker = msgList.getH03FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstRec) {
								//sets type and subtype as headers
								userPO.setHeader20(msgList.getE03MOVTIP());
								userPO.setHeader21(msgList.getE03MOVSUB());
								userPO.setHeader22(msgList.getE03MOVTIN());
								userPO.setHeader23(msgList.getE03MOVSUN());
								firstRec = false;
						}
							
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
									
				flexLog("Putting java beans into the session");
				ses.setAttribute("mtListAgent", bl);
				ses.setAttribute("mtListMov", beanList);				
					
				try {
					flexLog("About to call Page: " + LangPath + "EVL0060_value_paper_agent_mov_list.jsp");
					callPage(LangPath + "EVL0060_value_paper_agent_mov_list.jsp", req, res);						
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

			int screen = R_SEARCH;

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
						case R_SEARCH :
							procReqSearch(msgUser, req, res, session);
							break;
						case A_SEARCH :							
							procReqList(mc, msgUser, req, res, session);
							break;
						case A_MOV :							
							procReqListMov(mc, msgUser, req, res, session);
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