package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (6/29/04 5:30:00 PM)
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

public class JSEVL0070 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH_B = 100;
	protected static final int R_SEARCH_A = 200;
	protected static final int A_SEARCH_B = 300;
	protected static final int A_SEARCH_A = 400;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0070() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0070");

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
	protected void procReqListB(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL007001Message msgSearch = null;
		EVL007003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL007001Message) mc.getMessageRecord("EVL007001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EVL0070");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
//			all the fields here
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
			flexLog("EVL007001 Message Sent");

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
		   
		    if (newmessage.getFormatName().equals("EVL007003")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EVL007003Message) newmessage;

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
				ses.setAttribute("mtList", beanList);				
				
				try {
					if (IsNotError) { // There are no errors
						flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_branch_mov_list.jsp");
						callPage(LangPath + "EVL0070_value_paper_branch_mov_list.jsp", req, res);						
					} else {
						flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_branch_mov_inq.jsp");
						callPage(LangPath + "EVL0070_value_paper_branch_mov_inq.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else if (newmessage.getFormatName().equals("EVL007001")) {
				
				msgSearch = (EVL007001Message) newmessage;
				ses.setAttribute("msgMT", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_branch_mov_inq.jsp");
					callPage(LangPath + "EVL0070_value_paper_branch_mov_inq.jsp", req, res);
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
	protected void procReqSearchB(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EVL007001Message msgSearch = new EVL007001Message();
		ses.setAttribute("msgMT", msgSearch);
		
		try {
			flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_branch_mov_inq.jsp");
			callPage(LangPath + "EVL0070_value_paper_branch_mov_inq.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

		/**
		 * This method was created in VisualAge.
		 */
		protected void procReqListA(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			ELEERRMessage msgError = null;
			EVL007002Message msgSearch = null;
			EVL007003Message msgList = null;
			
			JBObjList beanList = null;
			UserPos userPO = null;
			boolean IsNotError = false;
	
			//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
			int posi = 0;
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgSearch = (EVL007002Message) mc.getMessageRecord("EVL007002");
				msgSearch.setH02USERID(user.getH01USR());
				msgSearch.setH02PROGRM("EVL0070");
				msgSearch.setH02TIMSYS(getTimeStamp());
				msgSearch.setH02SCRCOD("01");
				msgSearch.setH02OPECOD("0015");
	//			all the fields here
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
						flexLog("Exception reading field " + field.getTag());
					 }
				 }
			    
				msgSearch.send();
				msgSearch.destroy();
				flexLog("EVL007002 Message Sent");
	
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
			   
			    if (newmessage.getFormatName().equals("EVL007003")) {
			    	
			    	beanList = new JBObjList();
					String marker = "";
	
					while (true) {
	
						msgList = (EVL007003Message) newmessage;
	
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
					ses.setAttribute("mtList", beanList);				
					
					try {
						if (IsNotError) { // There are no errors
							flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_agent_mov_list.jsp");
							callPage(LangPath + "EVL0070_value_paper_agent_mov_list.jsp", req, res);						
						} else {
							flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_agent_mov_inq.jsp");
							callPage(LangPath + "EVL0070_value_paper_agent_mov_inq.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (newmessage.getFormatName().equals("EVL007002")) {
					
					msgSearch = (EVL007002Message) newmessage;
					ses.setAttribute("msgMT", msgSearch);
					
					try {
						flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_agent_mov_inq.jsp");
						callPage(LangPath + "EVL0070_value_paper_agent_mov_inq.jsp", req, res);
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
	protected void procReqSearchA(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EVL007002Message msgSearch = new EVL007002Message();
		ses.setAttribute("msgMT", msgSearch);
		
		try {
			flexLog("About to call Page: " + LangPath + "EVL0070_value_paper_agent_mov_inq.jsp");
			callPage(LangPath + "EVL0070_value_paper_agent_mov_inq.jsp", req, res);
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

			int screen = R_SEARCH_B;

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
						case R_SEARCH_B :
							procReqSearchB(msgUser, req, res, session);
							break;
						case A_SEARCH_B :							
							procReqListB(mc, msgUser, req, res, session);
							break;
						case R_SEARCH_A :
							procReqSearchA(msgUser, req, res, session);
							break;
						case A_SEARCH_A :							
							procReqListA(mc, msgUser, req, res, session);
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