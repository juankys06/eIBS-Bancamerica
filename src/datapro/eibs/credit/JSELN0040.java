package datapro.eibs.credit;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSELN0040 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL				= 5;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;
	protected static final int R_PASSWORD				= 1;
	
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSELN0040() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSELN0040(int logType) {
	super(logType);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ELN004002Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN004002Message)mc.getMessageRecord("ELN004002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ELN0040");
	 	msgList.setH02TIMSYS(getTimeStamp());
  		try {	
		 	String cus_line = req.getParameter("ACCNUM");
			String cusNum = Util.leftValue(cus_line);
			String lnNum = Util.rightValue(cus_line);
		 	msgList.setE02LNECUN(cusNum);
		 	msgList.setE02LNENUM(lnNum);
  		}
  		catch (Exception ex) {
  		}
	 	msgList.setE02ACTION(req.getParameter("action"));
	 	msgList.setE02MSGTXT(req.getParameter("reason"));
	 	msgList.send();	
	 	msgList.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There is no error
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0040?SCREEN=1");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "ELN0040_approval_list.jsp");
					 callPage(LangPath + "ELN0040_approval_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}		
	  	}
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}
/**
 * This method was created in VisualAge.
 */
protected void procReqApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ELN004001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELN004001Message)mc.getMessageRecord("ELN004001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ESS0090");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	// msgList.setE01DEAACD(req.getParameter("appCode"));
	 	msgList.send();	
	 	msgList.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}


	  	}
	  	else if (newmessage.getFormatName().equals("ELN004001")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String accNum = req.getParameter("ACCNUM");
			String key = "";

			if (accNum == null) 
				firstTime = true;
			else				
				firstTime = false;
			int indexRow = 0;
			while (true) {

				msgList = (ELN004001Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					key = msgList.getE01LNECUN().trim() + "_" + msgList.getE01LNENUM().trim();
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					}
					else {
						if (key.equals(accNum))
							chk = "checked";
						else 
							chk = "";
					}
					
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + key + "\" " + chk + " onclick=\"showAddInfo("+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalCL('" + msgList.getE01LNEACD() + "', '" + key + "')\">" + Util.formatCell(msgList.getE01LNECUN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalCL('" + msgList.getE01LNEACD() + "', '" + key + "')\">" + Util.formatCell(msgList.getE01LNENUM()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalCL('" + msgList.getE01LNEACD() + "', '" + key + "')\">" + Util.formatCell(msgList.getE01CUSNA1()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalCL('" + msgList.getE01LNEACD() + "', '" + key + "')\">" + Util.formatCell(msgList.getE01OPETYP()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:showInqApprovalCL('" + msgList.getE01LNEACD() + "', '" + key + "')\">" + Util.fcolorCCY(msgList.getE01LNEAMN()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
					
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setPurpose("APPROVAL");
			userPO.setOption("CL");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("appList", beanList);
			ses.setAttribute("error", msgError);

			if (beanList.getNoResult()){
				try {
					flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
				 	res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
			   	}
			    catch (Exception e) {
				    flexLog("Exception calling page " + e);
			   	}
			}    
			else {
				try {
				 	flexLog("About to call Page: " + LangPath + "ELN0040_approval_list.jsp");
				 	callPage(LangPath + "ELN0040_approval_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}	
		
	  	}
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}
/**
 * This method was created in VisualAge.
 */
protected void procReqApprovalInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	

	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		int appCode = Integer.parseInt(req.getParameter("appCode"));
  		String cus_line = req.getParameter("ACCNUM");

		String cusNum = Util.leftValue(cus_line);
		String lnNum = Util.rightValue(cus_line);

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(lnNum, ACC_APPROVAL_INQ, appCode, cusNum, LangPath, ses);
		res.sendRedirect(super.srctx + red.action());
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	
}
/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;

	session = (HttpSession)req.getSession(false); 
	
	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	}
	else {

		int screen = R_APPROVAL;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				case R_PASSWORD :
					procReqPassword(req, res, session);
					break;
				case R_APPROVAL :
					procReqApproval(mc, msgUser, req, res, session);
					break;
				case A_APPROVAL :
					procActionApproval(mc, msgUser, req, res, session);
					break;
				case R_APPROVAL_INQ :
					procReqApprovalInq(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}			

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}



/**
 * This method was created in VisualAge.
 */
protected void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	try {
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.credit.JSELN0040?SCREEN=" + R_APPROVAL + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
		ses.setAttribute("userPO", userPO);
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}
}