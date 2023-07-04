package datapro.eibs.bolgaran;

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

public class JSELC5250 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL				= 5;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;
	protected static final int R_PASSWORD 			= 1;
	protected static final int PRINT_BG				= 11;
	protected static final int INF_CLIENTE_BG			= 12;

	
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSELC5250() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSELC5250(int logType) {
	super(logType);
	
}

/*********************************************
 * Trae data de boleta de garantia
 */
protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELC500001Message msgBol = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	Socket s2 = null;
	
	try	{
		
		flexLog("Reabriendo el socket");
		s2 = new Socket(super.hostIP, super.iniSocket + 7);
		s2.setSoTimeout(super.sckTimeOut);
		flexLog("Reasignando el MessageContext");
		mc = null;
	  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s2.getInputStream())),
					      	    new DataOutputStream(new BufferedOutputStream(s2.getOutputStream())),
							    "datapro.eibs.beans");
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("MessageContext reassignment Error");
	}

	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{	
		msgBol = (ELC500001Message)mc.getMessageRecord("ELC500001");
	 	msgBol.setH01USERID(user.getH01USR());
	 	msgBol.setH01PROGRM("ELC5000");
	 	msgBol.setH01TIMSYS(getTimeStamp());
	 	msgBol.setH01SCRCOD("01");
	 	msgBol.setH01OPECOD("0002");
	 	msgBol.setE01LCMACD("43");		
		msgBol.setE01LCMACC(req.getParameter("NUM"));		
		msgBol.send();	
	 	msgBol.destroy();
	 	flexLog("ELC500001 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);

	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELC500001")) {
			try {
				msgBol = new datapro.eibs.beans.ELC500001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			
			try {
				msgBol = (ELC500001Message)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("bolgaran", msgBol);
  			} 
			catch (Exception e) { 
				e.printStackTrace();
				flexLog("Error: " + e);
  			}

			try {
				flexLog("About to call Page: " + LangPath + "ELC5250_det_boletag.jsp");
				callPage(LangPath + "ELC5250_det_boletag.jsp", req, res);	
			}
			catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
			}
			

		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	
	s2.close();
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
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ELC525002Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	String boleta = req.getParameter("ACCNUM");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ELC525002Message)mc.getMessageRecord("ELC525002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ESS0090");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setE02LCMACC(req.getParameter("ACCNUM"));
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
				//mod emat 01/24/2002
				//send draft parameter
				String typCode ="";
				flexLog("typCode: " + req.getParameter("typCode"));
				if (req.getParameter("typCode") != null) {
					typCode = req.getParameter("typCode");
				}
				// mod emat 01/24/2002
				//res.sendRedirect(super.srctx +"/servlet/datapro.eibs.products.JSELC5250?SCREEN=1&appCode=" + req.getParameter("appCode"));
				res.sendRedirect(super.srctx +"/servlet/datapro.eibs.bolgaran.JSELC5250?SCREEN=5&appCode=" + req.getParameter("appCode") + "&typCode="+typCode);
				
				//res.sendRedirect(super.srctx +"/servlet/datapro.eibs.bolgaran.JSELC5250?SCREEN=11&NUM=" + boleta);
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "ELC5250_approval_list.jsp");
					 callPage(LangPath + "ELC5250_approval_list.jsp", req, res);
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
	ELC525001Message msgList = null;
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
		msgList = (ELC525001Message)mc.getMessageRecord("ELC525001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ELC5250");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setE01LCMACD("43");
	 	try {
	 		//msgList.setH01FLGWK1(req.getParameter("typCode").trim());
	 	}
	 	catch (Exception e)	{
	 	}
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
	  	else if (newmessage.getFormatName().equals("ELC525001")) {

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
			//var for ofac status
			//var for Warning status
			String chkOfac = "";
			String chkWarn = "";
			if (accNum == null) 
				firstTime = true;
			else				
				firstTime = false;
			int indexRow = 0;
			int ct = 0;
while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
				}


				msgList = (ELC525001Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					}
					else {
						if (msgList.getE01LCMACC().trim().equals(accNum))
							chk = "checked";
						else 
							chk = "";
					}
					
					myRow = new StringBuffer("<TR>");
					// mod EMAT 10/01/2001
					// add ofac status : H01FLGWK3 = '3'
					// mod EMAT 10/25/2001
					// add warning status : H01FLGWK2 = 'A'
					chkOfac = (msgList.getH01FLGWK3().equals("3") ? "<a href=\"javascript:showInqOFAC('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() +"')\"><img src=\"../images/warning_16.jpg\" alt=\"OFAC Match List\" align=\"absmiddle\" border=\"0\" ></a>" : "");
					chkWarn = (msgList.getH01FLGWK2().equals("A") ? "<a href=\"javascript:showInqWarn('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() + "')\"><img src=\"../images/warning01.gif\" alt=\"Warnings\" align=\"absmiddle\" border=\"0\" ></a>" : "");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE01LCMACC() + "\" " + chk + " onclick=\"showAddInfo("+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalBG('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() + "')\">" + Util.formatCell(msgList.getE01LCMACC()) + "</A>"+chkOfac+chkWarn+"</TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBG('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() + "')\">" + Util.formatCell(msgList.getE01LCMCUN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqApprovalBG('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() + "')\">" + Util.formatCell(msgList.getE01CUSNA1())  + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBG('" + msgList.getE01LCMACC() + "','" + msgList.getH01FLGWK1() + "')\">" + Util.formatCell(msgList.getE01LCMPRO()) + "</A>");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"STSOFAC"+indexRow+"\" VALUE=\"" + msgList.getH01FLGWK3() + "\">");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"STSWARN"+indexRow+"\" VALUE=\"" + msgList.getH01FLGWK2() + "\">");
					
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE01LCMRMK()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMAMT()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMCCY()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMBNK()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMBRN()) + "<br>");					
					myRow.append(Util.formatCell(msgList.getE01LCMGLN()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMCCN()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMUBT()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01LCMUSR()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01FLGBUS()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01FLGIBF()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01FLGTYP()) + "\"></TD>");
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
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("appList", beanList);
			ses.setAttribute("error", msgError);
			
			if (beanList.getNoResult()){
				try {
					flexLog("About to call Page: " + LangPath + "MISC_no_result.htm");
				 	res.sendRedirect(super.srctx +LangPath + "MISC_no_result.htm");
			   	}
			    catch (Exception e) {
				    	flexLog("Exception calling page " + e);
			    }
			}    
			else {

			   	try {
				   	flexLog("About to call Page: " + LangPath + "ELC5250_approval_list.jsp");
				   	callPage(LangPath + "ELC5250_approval_list.jsp", req, res);
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

		int appCode = 43; //Integer.parseInt(req.getParameter("appCode"));
		String accNum = req.getParameter("ACCNUM");
		String typeCode = req.getParameter("typeCode");

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(typeCode,ACC_APPROVAL_INQ, appCode, accNum, LangPath, ses);
		res.sendRedirect(super.srctx +red.action());
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
				s = new Socket(super.hostIP, super.iniSocket + 1);
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
				case PRINT_BG :
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				//case INF_CLIENTE_BG :
				//	procActionClienteDirec(mc, msgUser, req, res, session);
				//	break;										
				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}

			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
//				return;
			}

			finally {
				s.close();
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
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
		//mod emat 01/24/2002
		//send draft parameter
						
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.bolgaran.JSELC5250?SCREEN=" + R_APPROVAL + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
		ses.setAttribute("userPO", userPO);
		res.sendRedirect(super.srctx +"/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}

}