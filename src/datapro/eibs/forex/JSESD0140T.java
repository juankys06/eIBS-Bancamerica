package datapro.eibs.forex;

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
 
import datapro.eibs.master.JSEIBSMSGProp;
import datapro.eibs.sockets.*;

public class JSESD0140T extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL				= 5;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;

	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSESD0140T() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSESD0140T(int logType) {
	super(logType);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESD014002Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ESD014002Message)mc.getMessageRecord("ESD014002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ESD0140");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	try {	
		 	String cus_line = req.getParameter("ACCNUM");
			String cusNum = Util.leftValue(cus_line);
			String lnNum = Util.rightValue(cus_line);
			if (cus_line.indexOf("_") == -1) {
				msgList.setE02ACCNUM(cus_line);
			}else{	
		 		msgList.setE02ACCNUM(cusNum);
		 		msgList.setE02LNENUM(lnNum);		 		
			}
  		}
  		catch (Exception ex) {
  			msgList.setE02ACCNUM(req.getParameter("ACCNUM"));
  		}
	 	msgList.setE02ACTION(req.getParameter("action"));
	 	msgList.setE02FILCOD(req.getParameter("filcod"));
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
				msgError = new ELEERRMessage();
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
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSESD0140T?SCREEN=1");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "ESD0140T_approval_list.jsp");
					 callPage(LangPath + "ESD0140T_approval_list.jsp", req, res);
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
	ESD014001Message msgList = null;
	JBList beanList = null;
	JBList beanCustList = null;
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
		msgList = (ESD014001Message)mc.getMessageRecord("ESD014001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ESD0140");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01OPECOD("0001"); // Treasury
	 	//msgList.setE01APLCDE(req.getParameter("appCode"));
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
	  	else if (newmessage.getFormatName().equals("ESD014001")) {

			try {
				beanList = new JBList();
				beanCustList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			StringBuffer myCustRow = null;
			String chk = "";
			String accNum = req.getParameter("ACCNUM");
			String warnImg= "";
			String txtAlt="";
			String warnFlag= "";
			String bklFlag="";
			//mod EMAT 25/10/2001
			//add Ofac status H01FLGWK3 = '3'
			String ofacImg = "";
			String ofacFlag = "";
			
			try {
			 JSEIBSMSGProp.setPropertyFileLang(Lang);
			 txtAlt = JSEIBSMSGProp.getMSG0004();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			
			if (accNum == null) 
				firstTime = true;
			else				
				firstTime = false;
			int indexRow = 0;
			int indexCustRow = -1;
			while (true) {

				msgList = (ESD014001Message)newmessage;

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
						//if (msgList.getE01ACCNUM().trim().equals(accNum))
						//	chk = "checked";
						//else 
							chk = "";
					}
					
					if (!myFlag.equals(msgList.getE01CUSNUM())) {
						myFlag = msgList.getE01CUSNUM();
						indexCustRow++;
						myCustRow.append("<TR>");
						myCustRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSNUM\" value=\"" + msgList.getE01CUSNUM() + "\" " + chk + " onclick=\"showAccInfo(" + indexCustRow + ")\"></TD>");
						myCustRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:sendClick(" + msgList.getE01CUSNUM() + ")\">" + Util.formatCell(msgList.getE01CUSNUM()) + "</A></TD>");
						myCustRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:sendClick(" + msgList.getE01CUSNUM() + ")\">" + Util.formatCell(msgList.getE01CUSNA1()) + "</A></TD>");
						myCustRow.append("</TR>");
						beanCustList.addRow(myFlag, myCustRow.toString());
						chk = "checked";
						indexRow = 0;
					}

					if (msgList.getH01FLGWK2().trim().equals("A")) {
						warnImg= "<IMG border=\"0\" src=\"../images/warning01.gif\" ALT=\""+txtAlt+"\" onClick=\"showWarnings('" + msgList.getE01ACCNUM() + "','ACCNUM"+indexCustRow+"')\">";
						warnFlag = msgList.getH01FLGWK2().trim();
					}else{
						warnImg= "";
						warnFlag = "";
					}
					//mod EMAT 25/10/2001
			        //add Ofac status H01FLGWK3 = '3'
			        if (msgList.getH01FLGWK3().trim().equals("3")) {
						ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01ACCNUM() + "')\">";
						ofacFlag = msgList.getH01FLGWK3().trim();
					}else{
						ofacImg= "";
						ofacFlag = "";
					}
					String linkApp="";
					String accTemp="";
					if ( msgList.getE01LNENUM().equals("0")) {
					  linkApp="<A HREF=\"javascript:showNewInqApproval('" + msgList.getE01APLCDE() + "', '" + msgList.getE01ACCNUM() + "', '" + msgList.getH01FLGWK1() + "')\">";
				      accTemp = msgList.getE01ACCNUM();
					} else {
					  linkApp="<A HREF=\"javascript:showNewInqApproval('" + msgList.getE01APLCDE() + "', '" + msgList.getE01CUSNUM()+"_"+msgList.getE01LNENUM() + "', '" + msgList.getH01FLGWK1() + "')\">";
					  accTemp = msgList.getE01CUSNUM()+"_"+msgList.getE01LNENUM();					  
					}
					myRow = new StringBuffer();
					myRow.append("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM"+indexCustRow+"\" value=\"" + accTemp + "\" " + chk + " onclick=\"showAddInfo('" + msgList.getE01ACCNUM() + "',"+indexRow+",'"+warnFlag+"','"+bklFlag+"'"+",'"+ ofacFlag+ "')\"></TD>");
					if ( msgList.getE01LNENUM().equals("0")){
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"+linkApp + Util.formatCell(msgList.getE01ACCNUM()) + "</A>"+warnImg+ofacImg+"</TD>");
					} else {
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"+linkApp + Util.formatCell(msgList.getE01CUSNUM()+"_"+msgList.getE01LNENUM()) + "</A>"+warnImg+ofacImg+"</TD>");
					}
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"+linkApp + Util.formatCell(msgList.getE01CURRCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP>"+linkApp + Util.fcolorCCY(msgList.getE01BALAMN()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"+linkApp + Util.formatCell(msgList.getE01REMARK()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"+linkApp + Util.formatCell(msgList.getE01PRODUC()) + "</A>");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexCustRow+""+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE01BNKNUM()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01BRANCH()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01GLNUMB()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01CSTCNT()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01BTHNUM()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE01OPERID()) + "\">");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTFILCOD"+indexCustRow+""+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE01FILCOD()) + "\"></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;
					chk = "";	
				}
				newmessage = mc.receiveMessage();
			}
					
			userPO = new UserPos();
			userPO.setPurpose("APPROVAL");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("appList", beanList);
			ses.setAttribute("custList", beanCustList);
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
				   	flexLog("About to call Page: " + LangPath + "ESD0140T_approval_list.jsp");
				   	callPage(LangPath + "ESD0140T_approval_list.jsp", req, res);
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
		String accNum = req.getParameter("ACCNUM");
		String typeCode = req.getParameter("typeCode");

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(typeCode, ACC_APPROVAL_INQ, appCode, accNum, LangPath, ses);
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
			Lang = msgUser.getE01LAN();
			try
			{
				flexLog("Opening Socket Connection");
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

	protected String Lang = "S";
	protected static final int R_PASSWORD				= 1;

/**
 * This method was created in VisualAge.
 */
protected void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	try {
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSESD0140?SCREEN=" + R_APPROVAL + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
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