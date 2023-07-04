package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EOF011502Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETL051001Message;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEOF0115A extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL				= 9;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;
	protected static final int R_LIST_PRINT				= 5;
	protected static final int A_LIST_PRINT		    	= 8;
	protected static final int R_PASSWORD				= 1;
	
	protected static final int R_APPROVAL_LIST			= 10;
	protected static final int A_APPROVAL_LIST			= 11;
	protected static final int A_APPROVAL_FROM_MENU		= 12;


	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEOF0115A() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEOF0115A(int logType) {
	super(logType);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EOF011502Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EOF011502Message)mc.getMessageRecord("EOF011502");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ESS0090");
	 	msgList.setH02TIMSYS(getTimeStamp());

	 	try {	
		 	String chk_ccy = req.getParameter("ACCNUM");
			String chkNum = Util.leftValue(chk_ccy);
			String ccy = Util.rightValue(chk_ccy);
		 	msgList.setE02OFMCKN(chkNum);
		 	msgList.setE02OFMCCY(ccy);
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
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=1");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_approval_list.jsp");
					 callPage(LangPath + "EOF0115_of_chk_approval_list.jsp", req, res);
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
	ETL051001Message msgSearch = null;
	ETL051001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ETL051001Message)mc.getMessageRecord("ETL051001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("ETL0510");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	msgSearch.setH01FLGWK1("A");
	 	
	 	
		try{
			try{
			 	posi= Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	posi=0;	
				flexLog("E01NUMREC");
			}

	
			try{
			 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	msgSearch.setE01NUMREC("0");	
				flexLog("E01NUMREC");
			}
			
		
			try{
			 	msgSearch.setE01SELDTY("1");
			}
			catch (Exception e){
			 	flexLog("E01SELDTY");
			}

			try{
			 	msgSearch.setE01SELSCH("");
			}
			catch (Exception e){
			 	flexLog("E01SELSCH");
			}

		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  flexLog("Input data error " + e);
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
	}		
	catch (Exception e)
	{
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
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
		else if (newmessage.getFormatName().equals("ETL051001")) {

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
			String accNum = req.getParameter("ACCNUM");
			int indexRow = 0;
			
			while (true) {

				msgList = (ETL051001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
						if (beanList.getFirstRec() > 50)
						  beanList.setShowPrev(true);
						else
						  beanList.setShowPrev(false);   
						chk = "checked";
					}
					else {
						chk = "";
					}
					
					String strPurpose = "I";
					String showRef = "showOffChkApproval('" + msgList.getE01OFMNCH() + "', '" + msgList.getE01OFMCCY() + "', '" + strPurpose + "')";
					
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE01OFMNCH() + "_" + msgList.getE01OFMCCY() + "\" " + chk + "></TD>");
					//myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE01OFMNCH() + "_" + msgList.getE01OFMCCY() + "\" " + chk + "\"></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatDate(msgList.getE01OFMID1(),msgList.getE01OFMID2(),msgList.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBNF()) + "</A></TD>");
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
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("dvList", beanList);
			ses.setAttribute("userPO", userPO);

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
				 	flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_approval_list.jsp");
				 	callPage(LangPath + "EOF0115_of_chk_approval_list.jsp", req, res);
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
protected void procReqApprovalList(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ETL051001Message msgSearch = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	int posi = 0;
	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgSearch = (ETL051001Message) mc.getMessageRecord("ETL051001");
		msgSearch.setH01USERID(user.getH01USR());
		msgSearch.setH01PROGRM("ETL0510");
		msgSearch.setH01TIMSYS(getTimeStamp());
		msgSearch.setH01SCRCOD("01");
		msgSearch.setH01OPECOD("0004");
		msgSearch.setH01FLGWK1("A");

		try {
			try {
				posi = Integer.parseInt(req.getParameter("Pos"));
			} catch (Exception e) {
				posi = 0;
				flexLog("E01NUMREC");
			}

			try {
				msgSearch.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception e) {
				msgSearch.setE01NUMREC("0");
				flexLog("E01NUMREC");
			}

			try {
				msgSearch.setE01SELDTY("1");
			} catch (Exception e) {
				flexLog("E01SELDTY");
			}

			try {
				msgSearch.setE01SELSCH("");
			} catch (Exception e) {
				flexLog("E01SELSCH");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		msgSearch.send();
		msgSearch.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgError = (ELEERRMessage) newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EOF0115_of_chk_enter_maint.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} else if (newmessage.getFormatName().equals("ETL051001")) {

			ArrayList msgList = new ArrayList();
			boolean noResultFound = true;
			while (true) {
				msgSearch = (ETL051001Message) newmessage;
				if (msgSearch.getE01INDOPE().equals("*")) {
					flexLog("Putting java beans into the session");
					ses.setAttribute("msgList", msgList);
					ses.setAttribute("userPO", userPO);
					break;
				} else {
					noResultFound = false;
					msgList.add(msgSearch);
					newmessage = mc.receiveMessage();
				}

			}

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EOF0115_of_chk_apr_list.jsp");
				res.sendRedirect(
					super.srctx + LangPath + "EOF0115_of_chk_apr_list.jsp");
			} catch (Exception e) {
				flexLog("Exception calling page " + ": " + e);
			}
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
protected void procActionApprovalFromMenu(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
		

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ArrayList errorList = new ArrayList();
	ArrayList recordList = new ArrayList();

	UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	try {
		ses.setAttribute("oldMsgList", (ArrayList) ses.getAttribute("msgList"));
	} catch (Exception e) {
	}

	//				Send Initial data
	try {
		flexLog("Send Initial Data");
		EOF011502Message msg02 =
			(EOF011502Message) mc.getMessageRecord("EOF011502");
		msg02.setH02USERID(user.getH01USR());
		msg02.setH02PROGRM("ESS0090");
		msg02.setH02TIMSYS(getTimeStamp());

		try {
			msg02.setE02OFMCKN(userPO.getIdentifier());
			msg02.setE02OFMCCY(userPO.getCurrency());
		} catch (Exception ex) {
		}

		msg02.setE02ACTION("A");
		//msg02.setE02MSGTXT(req.getParameter("reason"));

		msg02.send();
		recordList.add(msg02);
	} catch (Exception e) {
		recordList.add("error");
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
			try {
				msgError = (ELEERRMessage) newmessage;
				errorList.add(msgError);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
				errorList.add("error");
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		errorList.add("error");
		throw new RuntimeException("Socket Communication Error");
	}

	ses.setAttribute("CONFIRM", "CONFIRM");
	ses.setAttribute("recordList", recordList);
	ses.setAttribute("errorList", errorList);
	procReqApprovalList(mc, user, req, res, ses);
}

/**
 * This method was created in VisualAge.
 */
protected void procActionApprovalList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
{
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;

	UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	boolean IsNotError = false, isDelete = false;
	ArrayList errorList = new ArrayList();
	ArrayList recordList = new ArrayList();
		
	try{
		ses.setAttribute("oldMsgList" ,(ArrayList) ses.getAttribute("msgList"));
	} catch (Exception e){}

	String redirectScreen = req.getParameter("RD");
	if(redirectScreen == null) redirectScreen = "1";
	try
	{
		int totalRecords = Integer.parseInt(req.getParameter("total_records"));
		for (int i = 0; i < totalRecords; i++)
		{
			//				Send Initial data
			try
			{
				flexLog("Send Initial Data");
				EOF011502Message msg02 = (EOF011502Message) mc.getMessageRecord("EOF011502");
				msg02.setH02USERID(user.getH01USR());
				msg02.setH02PROGRM("ESS0090");
				msg02.setH02TIMSYS(getTimeStamp());

				try
				{
					String chk_ccy = req.getParameter("RECNUM" + i);
					if (chk_ccy == null || chk_ccy.equals(""))
						continue;
					String chkNum = Util.leftValue(chk_ccy);
					String ccy = Util.rightValue(chk_ccy);
					msg02.setE02OFMCKN(chkNum);
					msg02.setE02OFMCCY(ccy);
				}
				catch (Exception ex)
				{
				}
					
				msg02.setE02ACTION(req.getParameter("action"));
				msg02.setE02MSGTXT(req.getParameter("reason"));
				isDelete = msg02.getE02ACTION().equals("D");

				msg02.send();
				recordList.add(msg02);
			}
			catch (Exception e)
			{
				recordList.add("error");
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Message
			try
			{
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR"))
				{
					try
					{
						msgError = (ELEERRMessage) newmessage;
						errorList.add(msgError);
							
						if (isDelete)
						{
							ses.setAttribute("error", msgError);
							ses.setAttribute("CONFIRM", "NO");
							if (redirectScreen.equals("1")) 
								procReqApproval(mc, user, req, res, ses);
							else
								procReqApprovalList(mc, user, req, res, ses);
							return;
						}
					}
					catch (Exception ex)
					{
						flexLog("Error: " + ex);
						errorList.add("error");
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				flexLog("Error: " + e);
				errorList.add("error");
				throw new RuntimeException("Socket Communication Error");
			}
		}
		ses.setAttribute("recordList", recordList);
		ses.setAttribute("errorList", errorList);
		ses.setAttribute("CONFIRM", "CONFIRM");
		if (redirectScreen.equals("1")) 
			procReqApproval(mc, user, req, res, ses);
		else
			procReqApprovalList(mc, user, req, res, ses);
	}
	catch (Exception e)
	{
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

		int appCode = 87;
		String accNum = req.getParameter("ACCNUM");
		String ccy = req.getParameter("Currency");
		
		userPO.setIdentifier(accNum);
		userPO.setCurrency(ccy);
		userPO.setPurpose("APPROVAL");
		ses.setAttribute("userPO", userPO);

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(ccy, ACC_APPROVAL_INQ, appCode, accNum, LangPath, ses);
		res.sendRedirect(super.srctx + red.action());
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
protected void procReqPrint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_print.jsp");
		callPage(LangPath + "EOF0115_of_chk_print.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
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
				case R_LIST_PRINT : 
					procReqPrint(msgUser, req, res, session);
					break;
					
				case R_APPROVAL_LIST : 
					procReqApprovalList(mc, msgUser, req, res, session);
					break;
				case A_APPROVAL_LIST : 
					procActionApprovalList(mc, msgUser, req, res, session);
					break;
				case A_APPROVAL_FROM_MENU : 
					procActionApprovalFromMenu(mc, msgUser, req, res, session);
					break;
				

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
			} finally {
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
		int screen = R_APPROVAL ;
		try{
			screen = Integer.parseInt(req.getParameter("RD"));
		}
		catch (Exception e){
		}
		
		int Pos = 0 ;
		try{
			Pos = Integer.parseInt(req.getParameter("Pos"));
		}
		catch (Exception e){
		}
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=" + screen + "&Pos="+ Pos);
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