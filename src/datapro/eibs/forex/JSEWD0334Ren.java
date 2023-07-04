package datapro.eibs.forex;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEWD0334Ren extends datapro.eibs.master.SuperServlet {
	
	protected static final int R_ENTER_BASIC	= 1;
	protected static final int A_ENTER_BASIC	= 2;

	protected String LangPath = "S";

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSEWD0334Ren() {
	super();	
}
/**
 * This method was created by David Mavilla
public void destroy() {

	flexLog("free resources used by JSESS00    40");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);

}

protected void procReqEnterBasic(
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "EWD0334_cancel_renov_enter_basic.jsp");
		callPage(LangPath + "EWD0334_cancel_renov_enter_basic.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionEnterBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0334DSMessage msgHelp = null;
	JBList beanList = null;
	UserPos	userPO = null;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	try {
		msgHelp = (EWD0334DSMessage)mc.getMessageRecord("EWD0334DS");
		msgHelp.setRWDUSR(user.getH01USR());
		msgHelp.setRWDTYP("R");
				
		try{
			msgHelp.setSWDACC(req.getParameter("SWDACC"));
		}
		catch (Exception e){
		}
		try{
			msgHelp.setSWDCUN(req.getParameter("SWDCUN"));
		}
		catch (Exception e){
		}
		try{
			msgHelp.setSWDCCY(req.getParameter("SWDCCY"));
		}
		catch (Exception e){
		}
		try{
			msgHelp.setRWDDAY(req.getParameter("RWDDAY"));
		}
		catch (Exception e){
		}
			
		msgHelp.send();	
		msgHelp.destroy();
		flexLog("EWD0334DS Message Sent");
	}		
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		return;
	}
			
	// Receiving
	try
	{
		newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("EWD0334DS")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
				flexLog("EWD0334DS Message Received");
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String myDesc = "";
			String chk = "";
			boolean firstTime =true;
			while (true) {

				msgHelp = (EWD0334DSMessage)newmessage;

				marker = msgHelp.getSWDOPE();
					
				if (firstTime) {
					firstTime = false;
					beanList.setFirstRec(
					Integer.parseInt(msgHelp.getSWDREC()));
					chk = "checked";			
				} else {
					chk = "";
				}
					
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					myRow = new StringBuffer("<TR>");
					if (msgHelp.getSWDTYP().equals("PLP") || msgHelp.getSWDTYP().equals("PLS")) {
						myRow.append("<td nowrap  ALIGN=CENTER><input type=\"radio\" name=\"ACC\" value=\"" + msgHelp.getSWDACC() + "\" " + chk + "></TD>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDACC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=LEFT><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDCUN() + " - " + msgHelp.getSWDDSC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDPRO() + "</A></td>");						
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDTYP() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + Util.fcolorCCY(msgHelp.getSWDAMT()) + " : " + msgHelp.getSWDCCY() + "</A></td>");										
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDVAL() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDSTS() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showInqPlp('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDMAT() + "</A></td>");
					} else {
						myRow.append("<td nowrap  ALIGN=CENTER><input type=\"radio\" name=\"ACC\" value=\"" + msgHelp.getSWDACC() + "\" " + chk + "></TD>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDACC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=LEFT><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDCUN() + " - " + msgHelp.getSWDDSC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDPRO() + "</A></td>");						
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDTYP() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + Util.fcolorCCY(msgHelp.getSWDAMT()) + " : " + msgHelp.getSWDCCY() + "</A></td>");										
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + Util.formatDate(msgHelp.getSWDVAL()) + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + msgHelp.getSWDSTS() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showDeaAcc('"+ msgHelp.getSWDACC() + "')\">" + Util.formatDate(msgHelp.getSWDMAT()) + "</A></td>");
					}
					myRow.append( "</TR>");
					beanList.addRow(myFlag, myRow.toString());
										
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
					
			userPO.setAccNum(req.getParameter("SWDACC"));
			userPO.setCusNum(req.getParameter("SWDCUN"));
			userPO.setCurrency(req.getParameter("SWDCCY"));	
			userPO.setHeader1(req.getParameter("RWDDAY"));	
					
			flexLog("Putting java beans into the session");		
			ses.setAttribute("EWD0334Help", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "EWD0334_cancel_renov_list.jsp");
				 callPage(LangPath + "EWD0334_cancel_renov_list.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}	
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
	}	
}


/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
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

		int screen = R_ENTER_BASIC;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

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
				case R_ENTER_BASIC :
					procReqEnterBasic( msgUser, req, res, session);
					break;
					
					
				case A_ENTER_BASIC :
					procActionEnterBasic(mc, msgUser, req, res, session);
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

protected void showERROR(ELEERRMessage m) {
	if (logType != NONE) {

		flexLog("ERROR received.");

		flexLog("ERROR number:" + m.getERRNUM());
		flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
		flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
		flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
		flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
		flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
		flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
		flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
		flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
		flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
		flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());

	}
}

}