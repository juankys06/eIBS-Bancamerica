package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSERA0000 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_COLL_ASSETS					= 1;
	protected static final int A_COLLATERAL					= 2;
	protected static final int A_INQ_COLLATERAL					= 4;
	protected static final int R_COLL_LIABILITIES				= 3;
	protected static final int R_COLL_ASSETS_BY_CUSTOMER		= 5;
	protected static final int R_COLL_LIABILITIES_BY_CUSTOMER	= 7;

	// entering options
	protected static final int R_ENTER 						= 100;
	protected static final int A_ENTER	 					= 200;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSERA0000() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSESD0080");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procActionListColl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (UserPos) ses.getAttribute("userPO");

  		int appCode = Integer.parseInt(req.getParameter("APPCODE"));
  		String clientNum = req.getParameter("CUSNUM");
  		String accNum = req.getParameter("REF");

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(ACC_INQUIRY, appCode, accNum, LangPath, ses);
		res.sendRedirect(super.srctx + red.action());

	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procActionInqListColl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (UserPos) ses.getAttribute("userPO");

  		int appCode = Integer.parseInt(req.getParameter("APPCODE"));
  		String clientNum ="";
  		String accNum =req.getParameter("ACCNUM");
  		
  		if (appCode == 90) {
  			String lnNum = accNum.substring(accNum.length() - 4,accNum.length());
  			clientNum = accNum.substring(0,accNum.length() - 4);
  			datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(lnNum,ACC_INQUIRY, appCode, clientNum, LangPath, ses);
			res.sendRedirect(super.srctx + red.action());
  		} else {
			datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(ACC_INQUIRY, appCode, accNum, LangPath, ses);
  			res.sendRedirect(super.srctx + red.action());
  		}
		

	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}
/**
 * This method was created in VisualAge.
 */
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("COLLATERAL");
		userPO.setPurpose("");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0000_collateral_enter.jsp");
		callPage(LangPath + "ERA0000_collateral_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqListCollAssets(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA000001Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA000001Message)mc.getMessageRecord("ERA000001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ELN0110");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
		try{
			if (userPO.getIdentifier().length() < super.COLL_REF_A) 
				msgList.setE01RCLACA(Util.addRightChar('0', super.COLL_REF_A, Util.justifyRight(userPO.getIdentifier(), super.COLL_REF_A - super.CollFiller)));
			else 
				msgList.setE01RCLACA(userPO.getIdentifier());
		}
		catch (Exception e) {
		}

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ERA000001 Message Sent");
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");

		}
		else if (newmessage.getFormatName().equals("ERA000001")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String link = "";
			while (true) {

				msgList = (ERA000001Message)newmessage;

				marker = msgList.getE01ENDFLD();

				userPO.setHeader4("A");
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						userPO.setCusNum("");
						userPO.setCusName("");
						userPO.setAccNum(msgList.getE01RCLACA());
						userPO.setCurrency(msgList.getE01RCLACCY());
						userPO.setHeader5(msgList.getE01RCLATYP());
						userPO.setHeader6(msgList.getE01RCLAPRO());
						userPO.setHeader7(Util.fcolorCCY(msgList.getE01RCLABAL()));
						userPO.setHeader8(Util.fcolorCCY(msgList.getE01RCLADIS()));
					}

					myRow = new StringBuffer("<TR>");					
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.formatCell(msgList.getE01RCLACB()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.formatCell(msgList.getE01RCLBTYP()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.formatCell(msgList.getE01RCLBCCY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.fcolorCCY(msgList.getE01RCLBBAL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.fcolorCCY(msgList.getE01RCLBCOL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.fcolorCCY(msgList.getE01RCLOCOL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgList.getE01RCLACB()  +"')\">" + Util.fcolorCCY(msgList.getE01RCLBDIS()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list_assets.jsp");
				callPage(LangPath + "ERA0000_collateral_list_assets.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

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
protected void procReqListCollLiabilities(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA000002Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA000002Message)mc.getMessageRecord("ERA000002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ELN0110");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setH02SCRCOD("01");
	 	msgList.setH02OPECOD("0004");
		try{
			if (userPO.getIdentifier().length() < super.COLL_REF_B) 
				msgList.setE02RCLACB(Util.justifyRight(userPO.getIdentifier(), super.COLL_REF_B));
			else 
				msgList.setE02RCLACB(userPO.getIdentifier());
		}
		catch (Exception e) {
		}

	 	msgList.send();	
	 	msgList.destroy();
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");

		}
		else if (newmessage.getFormatName().equals("ERA000002")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			
			while (true) {

				msgList = (ERA000002Message)newmessage;

				marker = msgList.getE02ENDFLD();

				userPO.setHeader4("L");
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						userPO.setCusNum("");
						userPO.setCusName("");
						userPO.setAccNum(msgList.getE02RCLACB());
						userPO.setCurrency(msgList.getE02RCLBCCY());
						userPO.setHeader5(msgList.getE02RCLBTYP());
						userPO.setHeader6(msgList.getE02RCLBPRO());
						userPO.setHeader7(Util.fcolorCCY(msgList.getE02RCLBBAL()));
						userPO.setHeader8(Util.fcolorCCY(msgList.getE02RCLBDIS()));
					}

					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.formatCell(msgList.getE02RCLACA()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.formatCell(msgList.getE02RCLATYP()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.formatCell(msgList.getE02RCLACCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.fcolorCCY(msgList.getE02RCLABAL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.fcolorCCY(msgList.getE02RCLACOL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD()+"')\">" + Util.fcolorCCY(msgList.getE02RCLOCOL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqCollateral('" + msgList.getE02RCLACA()  +"','"+ msgList.getE02RCLAACD() +"')\">" + Util.fcolorCCY(msgList.getE02RCLAREM()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list_liabilities.jsp");
				callPage(LangPath + "ERA0000_collateral_list_liabilities.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

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
protected void procReqListCollLiabilitiesByCustomer(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA000003Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA000003Message)mc.getMessageRecord("ERA000003");
		msgList.setH03USERID(user.getH01USR());
	 	msgList.setH03PROGRM("ELN0110");
	 	msgList.setH03TIMSYS(getTimeStamp());
	 	msgList.setH03SCRCOD("01");
	 	msgList.setH03OPECOD("0004");
		try{
			msgList.setE03ROCCUN(userPO.getCusNum());
		}
		catch (Exception e) {
		}

	 	msgList.send();	
	 	msgList.destroy();
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");

		}
		else if (newmessage.getFormatName().equals("ERA000003")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String ref = "";
			String appCode = "";
			
			while (true) {

				msgList = (ERA000003Message)newmessage;

				marker = msgList.getE03ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
						userPO.setCusNum(msgList.getE03ROCCUN());
						userPO.setCusName(msgList.getE03CUSNA1());
					}
					else {
						chk = "checked";
					}

					myRow = new StringBuffer("<TR>");
					ref = msgList.getE03ROCREF();
					appCode = msgList.getE03CIFACD();
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.formatCell(ref) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.formatCell(msgList.getE03ROCTYP()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.formatCell(msgList.getE03ROCCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.fcolorCCY(msgList.getE03ROCAMT()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.fcolorCCY(msgList.getE03ROCUSE()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.fcolorCCY(msgList.getE03ROCBAL()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.formatDate(msgList.getE03ROCOD1(), msgList.getE03ROCOD2(), msgList.getE03ROCOD3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollInq('" + ref + "', '" + appCode + "')\">" + Util.formatDate(msgList.getE03ROCMD1(), msgList.getE03ROCMD2(), msgList.getE03ROCMD3()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("collList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0000_collateral_list_liabilities_by_client.jsp");
				callPage(LangPath + "ERA0000_collateral_list_liabilities_by_client.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
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

		int screen = R_ENTER;
		
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
				// Request
				case R_COLL_ASSETS :
					procReqListCollAssets(mc, msgUser, req, res, session);
					break;
				case R_COLL_LIABILITIES : 
					procReqListCollLiabilities(mc, msgUser, req, res, session);
					break;
				case R_COLL_LIABILITIES_BY_CUSTOMER :
					procReqListCollLiabilitiesByCustomer(mc, msgUser, req, res, session);
					break;
				// Action
				case A_COLLATERAL :
					procActionListColl(mc, msgUser, req, res, session);
					break;
				case A_INQ_COLLATERAL :
					procActionInqListColl(mc, msgUser, req, res, session);
					break;
				//entering options
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				case A_ENTER : 
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
protected void showERROR(ELEERRMessage m)
{
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