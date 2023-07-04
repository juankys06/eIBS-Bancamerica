package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (12/09/04 5:20:44 PM)
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

public class JSEVL0080 extends datapro.eibs.master.SuperServlet {

	protected static final int R_LIST_UPDATE = 300;
	protected static final int A_UPDATE = 600;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0080() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0080");

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
	protected void procReqListUpdate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL008001Message msgSearch = null;
		EVL008001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL008001Message) mc.getMessageRecord("EVL008001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EVL0080");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
	
		    	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL008001 Message Sent");
		
	
		// Receive Message
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("EVL008001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";
	
				while (true) {
	
					msgList = (EVL008001Message) newmessage;
	
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
					
					flexLog("About to call Page: " + LangPath + "EVL0090_value_paper_stock_update.jsp");
					callPage(LangPath + "EVL0080_value_paper_stock_update.jsp", req, res);						
				
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
	
		EVL008001Message msgMT = null;
		JBObjList beanList= null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		try {
		
			flexLog("Send Initial Data");
			beanList = (JBObjList) ses.getAttribute("appList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			msgMT = (EVL008001Message) beanList.getRecord();			
			msgMT.setH01OPECOD("0005");
						
			msgMT.setE01BALAIN(req.getParameter("E01BALAIN"));
			msgMT.setE01BALAFI(req.getParameter("E01BALAFI"));
			msgMT.setE01BALMOT(req.getParameter("E01BALMOT"));
				
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
	
			if (newmessage.getFormatName().equals("EVL008001")) {
				
				msgMT = (EVL008001Message) newmessage;
				
				if (IsNotError) {
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.valuepaper.JSEVL0080?SCREEN=300'";
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
						flexLog("About to call Page: " + LangPath + "EVL0080_value_paper_stock_update_detail.jsp");
						res.sendRedirect(req.getContextPath() + LangPath + "/EVL0080_value_paper_stock_update_detail.jsp?ROW=" + row);								
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

			int screen = R_LIST_UPDATE;

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
						case A_UPDATE :
							procActionUpdate(mc,msgUser, req, res, session);
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