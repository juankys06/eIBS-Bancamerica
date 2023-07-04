package datapro.eibs.accounting;

/**
 * Insert the type's description here.
 * Creation date: (11/5/07 6:08:55 PM)
 * @author: R.Quimper
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEGL0750 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_SEARCH 				= 1;
	protected static final int R_LIST 					= 2;
	protected static final int R_OPTION					= 3;
	protected static final int R_AVERAGE				= 300;
	protected static final int R_BUDGET					= 200;
	protected static final int R_BALANCE				= 400;
	protected static final int R_ENTER_ACC				= 100;
	
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEGL0750() {
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
protected void procActionList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (UserPos) ses.getAttribute("userPO");

  		int option = Integer.parseInt(req.getParameter("opt"));
  		String clientNum = req.getParameter("CUSTOMER");

		userPO.setCusNum(clientNum);
		ses.setAttribute("userPO", userPO);

		switch (option) {
				case 1 : // Consulta GL.
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=0");
					break;
				case 2 : // Balances
				//	procReqPos(mc, user, req, res, ses);
					break;
				case 3 : // promedios
					procReqAverage(mc, user, req, res, ses);
					break;
				case 4 : // presupuestos
					procReqBudget(mc, user, req, res, ses);
					break;
				case 5 : // estado de cuenta historico
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=0");
					break;
				case 6 : // Customer Account Listing
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=0");
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");
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
protected void procReqEnterAcc(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("ACCOUNTS");
		userPO.setPurpose("INQUIRY");
		String opt = null;
		try {
			opt = req.getParameter("OPTION");
			if (opt == null) {
				opt = "";
			}
		}
		catch (Exception e) {
			opt = "";
		}
		userPO.setHeader8(opt);
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex);  
	}

	try {
		flexLog("About to call Page: " + LangPath + "EGL0750_inq_enter1.jsp");
		callPage(LangPath + "EGL0750_inq_enter1" +
			".jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EGL075001Message msgSearch = null;
	ELEERRMessage msgError = null;
	JBObjList beanList = null;
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
		msgSearch = (EGL075001Message)mc.getMessageRecord("EGL075001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EGL0750");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01FLGWK1(userPO.getHeader8());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	flexLog("EGL075001 Header Sent");
		try {
					msgSearch.setE01GLBBNK(req.getParameter("E01GLBBNK"));
					msgSearch.setE01GLBBRN(req.getParameter("E01GLBBRN"));
					msgSearch.setE01GLBCCY(req.getParameter("E01GLBCCY"));
					msgSearch.setE01GLBCLS(req.getParameter("E01GLBCLS"));
					msgSearch.setE01GLBDSC(req.getParameter("E01GLBDSC"));
					msgSearch.setE01GLBATY(req.getParameter("E01GLBATY"));
					msgSearch.setE01GLBCCN(req.getParameter("E01GLBCCN"));
					msgSearch.setE01GLBGLN(req.getParameter("E01GLBGLN"));
					msgSearch.setE01GLBNIV(req.getParameter("E01GLBNIV"));
				}
				catch (Exception e)	{
					msgSearch.setE01GLBBNK("0");
				}
	//	try{
	//	 	type = Integer.parseInt(req.getParameter("Type"));
	//	 	num = req.getParameter("NameSearch").toUpperCase();
				 	
	//	}
//		catch (Exception e)
//		{
//		  e.printStackTrace();
//		  flexLog("Input data error " + e);
//		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
	 	flexLog("EGL075001 Message Sent");
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

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_enter.jsp");
				callPage(LangPath + "EGL0750_inq_enter1.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EGL075001")) {
			try {
				beanList = new JBObjList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
            /*
			try{
			 	beanList.setSearchText(num);
			 	beanList.setSearchType(type + "");
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			 	beanList.setSearchText("A");
			 	beanList.setSearchType("3");
			  	flexLog("Input data error " + e);
			}
            */
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			int indexRow = 0;
			while (true) {

				msgSearch = (EGL075001Message)newmessage;

				marker = msgSearch.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgSearch.getE01NUMREC()));
						chk = "checked";
					}
					else {
						chk = "";
					}
                    /*
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgSearch.getE01GLBBRN() + "\" " + chk + " onclick=\"showAddInfo("+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBBRN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBCCY()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBGLN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBDSC()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBCLS()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBATY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgSearch.getE01GLBBAL()) + "</TD>");
					// myRow.append("<TD NOWRAP>" + Util.formatCell(msgSearch.getE01GLBBAL())); 
					
					// myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CLITYP()) + "</TD>");
					myRow.append("</TR>");
					
					beanList.addRow(myFlag, myRow.toString());
					*/
					beanList.addRow(msgSearch);
					indexRow ++;				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}

				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			req.setAttribute("cifList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_list.jsp");
				callPage(LangPath + "EGL0750_inq_list.jsp", req, res);

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
protected void procReqBalances(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EGL075002Message msgbalance = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0002";

	// Send Initial data
	try
	{
		msgbalance = (EGL075002Message)mc.getMessageRecord("EGL075002");
		msgbalance.setH02USERID(user.getH01USR());
		msgbalance.setH02PROGRM("EGL0750");
		msgbalance.setH02TIMSYS(getTimeStamp());
		msgbalance.setH02FLGWK1(userPO.getHeader8());
		msgbalance.setH02SCRCOD("01");
		msgbalance.setH02OPECOD(opCode);
	 	
		// all the fields here
		java.util.Enumeration enu = msgbalance.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		}
		msgbalance.send();	
		msgbalance.destroy();
	 	flexLog("EGL075002 Message Sent");
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

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_balances.jsp");
				callPage(LangPath + "EGL0750_inq_balances.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EGL075002")) {
			try {
				msgbalance = new datapro.eibs.beans.EGL075002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgbalance = (EGL075002Message)newmessage;
			// showECIF01003(msgTotal);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("balance", msgbalance);

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_balances.jsp");
				callPage(LangPath + "EGL0750_inq_balances.jsp", req, res);
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
protected void procReqAverage(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EGL075002Message msgaverage = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0003";

	// Send Initial data
	try
	{
		msgaverage = (EGL075002Message)mc.getMessageRecord("EGL075002");
	 	msgaverage.setH02USERID(user.getH01USR());
	 	msgaverage.setH02PROGRM("EGL0750");
	 	msgaverage.setH02TIMSYS(getTimeStamp());
	 	msgaverage.setH02FLGWK1(userPO.getHeader8());
	 	msgaverage.setH02SCRCOD("01");
	 	msgaverage.setH02OPECOD(opCode);
	 	
		// all the fields here
		java.util.Enumeration enu = msgaverage.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		} 	
		msgaverage.send();	
	 	msgaverage.destroy();
	 	flexLog("EGL075002 Message Sent");
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

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_promedios.jsp");
				callPage(LangPath + "EGL0750_inq_promedios.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EGL075002")) {
			try {
				msgaverage = new datapro.eibs.beans.EGL075002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgaverage = (EGL075002Message)newmessage;
			// showECIF01003(msgTotal);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("average", msgaverage);

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_promedios.jsp");
				callPage(LangPath + "EGL0750_inq_promedios.jsp", req, res);
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
protected void procReqBudget(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EGL075002Message msgbudget = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try
	{
		msgbudget = (EGL075002Message)mc.getMessageRecord("EGL075002");
	 	msgbudget.setH02USERID(user.getH01USR());
	 	msgbudget.setH02PROGRM("EGL0750");
	 	msgbudget.setH02TIMSYS(getTimeStamp());
	 	msgbudget.setH02FLGWK1(userPO.getHeader8());
	 	msgbudget.setH02SCRCOD("01");
	 	msgbudget.setH02OPECOD(opCode);
	 	
		// all the fields here
		java.util.Enumeration enu = msgbudget.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		} 	
	 	
		msgbudget.send();	
	 	msgbudget.destroy();
	 	flexLog("EGL075002 Message Sent");
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

			try {
				flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
				callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EGL075002")) {
			try {
				msgbudget = new datapro.eibs.beans.EGL075002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgbudget = (EGL075002Message)newmessage;
			// showECIF01003(msgTotal);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("budget", msgbudget);

			try {
				flexLog("About to call Page: " + LangPath + "EGL0750_inq_budget.jsp");
				callPage(LangPath + "EGL0750_inq_budget.jsp", req, res);
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

		int screen = R_SEARCH;
		
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
				// Requests
				case R_ENTER_ACC :
					procReqEnterAcc(msgUser, req, res, session);
					break;				
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case R_OPTION :
					procActionList(mc, msgUser, req, res, session);
					break;
				case R_AVERAGE :
					procReqAverage(mc, msgUser, req, res, session);
					break;
				case R_BUDGET :
					procReqBudget(mc, msgUser, req, res, session);
					break;
				case R_BALANCE:
				    procReqBalances(mc,msgUser,req,res,session);
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