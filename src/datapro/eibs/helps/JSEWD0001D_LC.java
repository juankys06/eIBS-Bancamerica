package datapro.eibs.helps;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEWD0001D_LC extends SuperServlet {

	MessageContext mc = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	EWD0001RMessage msgHelpR = null;
	EWD0001SMessage msgHelpS = null;
	JBObjList jbList = null;
	
	String LangPath = "e/";
	
	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0001D_LC() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (mc != null) mc.close();
			flexLog("free resources used by JSEWD0001D_LC");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		if (session == null) {
			try {
				res.setContentType("text/html");
				super.printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {
			user = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			// Here we should get the path from the user profile
			LangPath = super.rootPath + user.getE01LAN() + "/";
			
			userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
			
			try {
				mc =
					new MessageContext(getMessageHandler("EWD0001"));
						
				main(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				if(mc != null) mc.close();
				flexLog("Socket used by JSEWD0001D_LC closed.");
			}
		}	
	}
	
	private void main(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			msgHelpR = (EWD0001RMessage) msgHandle.initMessage("EWD0001R", user.getH01USR(), "");
			String name = req.getParameter("NameSearch").toUpperCase();
			msgHelpR.setRWDSHR(name);
			String type = req.getParameter("Type");
			msgHelpR.setRWDTYP(type);
			msgHelpR.setRWDFRC(req.getParameter("FromRecord"));
			String Custype = "";
			try {
				Custype = (req.getParameter("CusType") == null)	? "" : req.getParameter("CusType");
			} catch (Exception e) {
				Custype = "";
			}
			msgHelpR.setRWDSEL(Custype);
			msgHandle.sendMessage(msgHelpR);
			
			boolean firstTime = true;
			String marker = "";
			StringBuffer myRow = null;
			msgHelpS = (EWD0001SMessage) msgHandle.receiveMessage();
			jbList = new JBObjList();
			while (true) {
				marker = msgHelpS.getSWDOPE();
				if (marker.equals("*")) {
					jbList.setShowNext(false);
					userPO.setHeader1(msgHelpS.getSWDMST());
					break;
				} else {
					if (firstTime) {
						firstTime = false;
						jbList.setFirstRec(Integer.parseInt(msgHelpS.getSWDREC()));
					}
					myRow = new StringBuffer("<TR>");
					myRow.append(
						"<td nowrap><a href=\"javascript:enter('"
							+ msgHelpS.getSWDCUN()
							+ "','"
							+ msgHelpS.getSWDNA1()
							+ "','"
							+ msgHelpS.getSWDNA2()
							+ "','"
							+ msgHelpS.getSWDNA3()
							+ "','"
							+ msgHelpS.getSWDCTY()
							+ "','"
							+ msgHelpS.getSWDSTE()
							+ "','"
							+ msgHelpS.getSWDZPC()
							+ "','"
							+ msgHelpS.getSWDCTR()
							+ "')\">"
							+ msgHelpS.getSWDCUN()
							+ "</a></td>");
					myRow.append(
						"<td nowrap><a href=\"javascript:enter('"
							+ msgHelpS.getSWDCUN()
							+ "','"
							+ msgHelpS.getSWDNA1()
							+ "','"
							+ msgHelpS.getSWDNA2()
							+ "','"
							+ msgHelpS.getSWDNA3()
							+ "','"
							+ msgHelpS.getSWDCTY()
							+ "','"
							+ msgHelpS.getSWDSTE()
							+ "','"
							+ msgHelpS.getSWDZPC()
							+ "','"
							+ msgHelpS.getSWDCTR()
							+ "')\">"
							+ msgHelpS.getSWDNA1()
							+ "</a></td>");
					myRow.append(
						"<td nowrap><a href=\"javascript:enter('"
							+ msgHelpS.getSWDCUN()
							+ "','"
							+ msgHelpS.getSWDNA1()
							+ "','"
							+ msgHelpS.getSWDNA2()
							+ "','"
							+ msgHelpS.getSWDNA3()
							+ "','"
							+ msgHelpS.getSWDCTY()
							+ "','"
							+ msgHelpS.getSWDSTE()
							+ "','"
							+ msgHelpS.getSWDZPC()
							+ "','"
							+ msgHelpS.getSWDCTR()
							+ "')\">"
							+ msgHelpS.getSWDCTY()
							+ "</a></td>");
					myRow.append(
						"<td nowrap><a href=\"javascript:enter('"
							+ msgHelpS.getSWDCUN()
							+ "','"
							+ msgHelpS.getSWDNA1()
							+ "','"
							+ msgHelpS.getSWDNA2()
							+ "','"
							+ msgHelpS.getSWDNA3()
							+ "','"
							+ msgHelpS.getSWDCTY()
							+ "','"
							+ msgHelpS.getSWDSTE()
							+ "','"
							+ msgHelpS.getSWDZPC()
							+ "','"
							+ msgHelpS.getSWDCTR()
							+ "')\">"
							+ msgHelpS.getSWDCTR()
							+ "</a></td>");
					myRow.append(
						"<td nowrap><a href=\"javascript:enter('"
							+ msgHelpS.getSWDCUN()
							+ "','"
							+ msgHelpS.getSWDNA1()
							+ "','"
							+ msgHelpS.getSWDNA2()
							+ "','"
							+ msgHelpS.getSWDNA3()
							+ "','"
							+ msgHelpS.getSWDCTY()
							+ "','"
							+ msgHelpS.getSWDSTE()
							+ "','"
							+ msgHelpS.getSWDZPC()
							+ "','"
							+ msgHelpS.getSWDCTR()
							+ "')\">"
							+ msgHelpS.getSWDIDN()
							+ "</a></td>");
					myRow.append("</TR>");
					jbList.addRow(myRow.toString());
					if (marker.equals("+")) {
						jbList.setShowNext(true);
						break;
					}
				}
				msgHelpS = (EWD0001SMessage) msgHandle.receiveMessage();
			}
			
			putDataInSession(session, req);
			
			String PageToCall = "EWD0001_client_lc_help_helpmessage.jsp";
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}	
	
	public void callPage(String page, HttpServletRequest req, HttpServletResponse res) {
		try {
			super.callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e.toString() + e.getMessage());
		}
		return; 
	}
	
	private void putDataInSession(HttpSession session, HttpServletRequest req) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			session.setAttribute("NameSearch", req.getParameter("NameSearch"));
			session.setAttribute("Type", req.getParameter("Type"));
			session.setAttribute("CusType", req.getParameter("CusType"));
			if (jbList != null) session.setAttribute("ewd0001Help", jbList);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}