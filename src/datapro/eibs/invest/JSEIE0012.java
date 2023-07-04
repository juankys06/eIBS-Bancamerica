package datapro.eibs.invest;

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

public class JSEIE0012 extends datapro.eibs.master.SuperServlet {

	protected static final  int R_APPROVAL				= 5;
	protected static final  int R_PASSWORD				= 1;
	protected static final  int A_APPROVAL				= 2;
	protected static final  int R_APPROVAL_INQ			= 3;

	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEIE0012() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEIE0012(int logType) {
	super(logType);
	
}
/**
 * This method was created in VisualAge.
 */
protected  void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EIE001202Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EIE001202Message)mc.getMessageRecord("EIE001202");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ESS0090");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setE02PRFNUM(req.getParameter("PORTFOLIO"));
	 	msgList.setE02PRFCUN(req.getParameter("CUSTOMER"));
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
				msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			try {
			 flexLog("About to call Page: " + LangPath + "EIE0012_approval_list.jsp");
			 callPage(LangPath + "EIE0012_approval_list.jsp", req, res);
			}
			catch (Exception e) {
			flexLog("Exception calling page " + e);
			}
			//if (IsNotError) { // There is no error
				//mod emat 01/24/2002
				//send draft parameter
				//String typCode ="";
				//flexLog("typCode: " + req.getParameter("typCode"));
				//if (req.getParameter("typCode") != null) {
				//	typCode = req.getParameter("typCode");
				//}
				// mod emat 01/24/2002
				//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0140?SCREEN=1&appCode=" + req.getParameter("appCode"));
				//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0012?SCREEN=5&appCode=" + req.getParameter("appCode") + "&typCode="+typCode);
			//}
		//	else {
				
			//}		
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
protected  void procReqApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EIE001201Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}



	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EIE001201Message)mc.getMessageRecord("EIE001201");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("EIE001201");
	 	msgList.setH01TIMSYS(getTimeStamp());

	 	//mod: emat 02/24/2002
	 	//to extract drafts
	 	flexLog("typCode: " + req.getParameter("typCode"));
	 	if (req.getParameter("typCode") != null && !req.getParameter("typCode").equalsIgnoreCase("null")) {
	 		msgList.setH01FLGWK1(req.getParameter("typCode").trim());
	 	}
	 	//
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
	  	else if (newmessage.getFormatName().equals("EIE001201")) {

			try {
				 beanList = new JBList();
				//beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String ordNum = "";
			
			if (ordNum == null){ 
				firstTime = true;
				ordNum = "1"; }
			else				
				firstTime = false;
			int indexRow = 0;
			
			//userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
			userPO = new UserPos();
			
			while (true) {

				msgList = (EIE001201Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
									
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ORDNUM\" value=\"" + msgList.getE01PRFNUM() + "\" " + chk +
							 " onclick=\"getPortfClient('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\"></TD>");
							 
					myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01CUSNME()) 
							 + "</A></TD>");
							 
					myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01PRFNUM() + "-" + msgList.getE01PRFDSC())
							 + "</A></TD>");		 
							 
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01TYPDSC())  
							 + "</A></TD>");
							 
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01PRFDLT())
							 + "</A></TD>");
					
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01OFCNME())
							 + "</A></TD>");
							 
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01PRFCCY())
							 + "</A></TD>");
					
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatDate(msgList.getE01PRFOP1(),msgList.getE01PRFOP2(),msgList.getE01PRFOP3())
							 + "</A></TD>");
							 
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalPortfolio('" + msgList.getE01PRFCUN() + "','" + msgList.getE01PRFNUM() + "')\">"
							 + Util.formatCell(msgList.getE01OPEDSC())
							 + "</A></TD>");		 		 		 
					
					/*myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE01PRFOFC() + " / " + msgList.getE01OFCNME()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01PRFCCY()) + "<br>");
					myRow.append(Util.formatDate(msgList.getE01PRFOP1(),msgList.getE01PRFOP2(),msgList.getE01PRFOP3()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01OPEDSC()) + "\"></TD>");*/
					
					myRow.append("</TR>");
		 
							 
					
					
					
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
					newmessage = mc.receiveMessage();
				}
				
			} 
					
			
			userPO.setPurpose("APPROVAL");
			
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
				   	flexLog("About to call Page: " + LangPath + "EIE0012_approval_list.jsp");
				   	callPage(LangPath + "EIE0012_approval_list.jsp", req, res);
			    }
			    catch (Exception e) {
				   flexLog("Exception calling page " + e);
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
protected  void procReqApprovalInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	

	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		int appCode = 180;
		String customer = req.getParameter("CUSTOMER");
		String portfolio = req.getParameter("PORTFOLIO");

		res.sendRedirect(super.srctx + 
			"/servlet/datapro.eibs.invest.JSEIE0010A?SCREEN=200" + "&CUSTOMER=" + customer 
			+ "&CODE=" + portfolio);		    
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
protected  void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	try {
		//mod emat 01/24/2002
		//send draft parameter
		String typCode ="";
		flexLog("typCode: " + req.getParameter("typCode"));
		if (req.getParameter("typCode") != null) {
			typCode = req.getParameter("typCode");
		}
				
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.invest.JSEIE0012?SCREEN=" + R_APPROVAL + "&appCode=" + req.getParameter("appCode") + "&typCode=" + req.getParameter("typCode") + (req.getParameter("CUSTOMER") == null ? "" : "&CUSTOMER=" + req.getParameter("CUSTOMER")));
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