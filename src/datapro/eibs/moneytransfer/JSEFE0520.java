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

public class JSEFE0520 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH = 100;
	protected static final int A_SEARCH = 200;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEFE0520() {
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE052001Message msgSearch = null;
		EFE052002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EFE052001Message) mc.getMessageRecord("EFE052001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EFE0520");
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
			flexLog("EFE052001 Message Sent");
		

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
		   
		    if (newmessage.getFormatName().equals("EFE052002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EFE052002Message) newmessage;

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
				ses.setAttribute("mtList", beanList);				
				
				try {
					if (IsNotError) { // There are no errors
						flexLog("About to call Page: " + LangPath + "EFE0520_money_transf_list.jsp");
						callPage(LangPath + "EFE0520_money_transf_list.jsp", req, res);						
					} else {
						flexLog("About to call Page: " + LangPath + "EFE0520_money_transf_enter_inq.jsp");
						callPage(LangPath + "EFE0520_money_transf_enter_inq.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else if (newmessage.getFormatName().equals("EFE052001")) {
				
				msgSearch = (EFE052001Message) newmessage;
				ses.setAttribute("msgMT", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EFE0520_money_transf_enter_inq.jsp");
					callPage(LangPath + "EFE0520_money_transf_enter_inq.jsp", req, res);
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
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EFE052001Message msgSearch = new EFE052001Message();
		ses.setAttribute("msgMT", msgSearch);
		
		try {
			
			flexLog("About to call Page: " + LangPath + "EFE0520_money_transf_enter_inq.jsp");
			callPage(LangPath + "EFE0520_money_transf_enter_inq.jsp", req, res);
		
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