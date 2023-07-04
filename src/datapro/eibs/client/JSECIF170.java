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

public class JSECIF170 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_SEARCH 				= 1;
	protected static final int A_SEARCH 				= 2;
	protected static final int R_LIST 				    = 3;
	protected static final int A_ACCOUNT			    = 4;
    protected static final int A_TOTAL			   		= 5;
	protected static final int A_ACC				    = 6;
	protected static final int A_PRODUCT			    = 7;
	
	protected String LangPath = "S";


/**
 * JSECIF170 constructor comment.
 */
public JSECIF170() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECIF170");
	
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
protected void procActionEnterAcc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF17007Message msgAcc = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

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
		msgAcc = (ECIF17007Message)mc.getMessageRecord("ECIF17007");
		msgAcc.setH07USERID(user.getH01USR());
		msgAcc.setH07PROGRM("EDL0160");
		msgAcc.setH07TIMSYS(getTimeStamp());
		try{
		msgAcc.setH07FLGWK1(userPO.getHeader8());
		}
		catch (Exception e)	{	  	
		}
		msgAcc.setH07SCRCOD("01");
		msgAcc.setH07OPECOD("0004");
		flexLog("ECIF170 Enter Account Header Sent");
		try {
			msgAcc.setE07ACCNUM(req.getParameter("ACCNUM"));
		}
		catch (Exception e)	{
			msgAcc.setE07ACCNUM("0");
		}
		msgAcc.send();	
		msgAcc.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("ECIF170 Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
		newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			//showERROR(msgError);
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "ECIF170_cif_enter_acc.jsp");
				callPage(LangPath + "ECIF170_cif_enter_acc.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else if (newmessage.getFormatName().equals("ECIF17007")) {
			try {
				msgAcc = new datapro.eibs.beans.ECIF17007Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgAcc = (ECIF17007Message)newmessage;
			
			int option = Integer.parseInt(req.getParameter("opt"));
			int appCode = Integer.parseInt(msgAcc.getE07APCCDE());
			String accNum = msgAcc.getE07ACCNUM();

			datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(option, appCode, accNum, LangPath, ses);
			res.sendRedirect(super.srctx + red.action());
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
 * 
 */
protected void procReqPosAccount(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	    MessageRecord newmessage = null;
		ECIF17005Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		//String clientNum="";
		String strClientNum = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setType("");
		String str = userPO.getHeader1();
		
		/*try{ 
			
			clientNum = req.getParameter("ACC");	
		}
		catch (Exception e)
	    {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
    	}
    	*/
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF17005Message)mc.getMessageRecord("ECIF17005");
		msgList.setH05USERID(user.getH01USR());
		msgList.setH05PROGRM("ECIF170");
		msgList.setH05TIMSYS(getTimeStamp());
		msgList.setH05SCRCOD("01");
		msgList.setH05OPECOD("0005");
	
		
		strClientNum = (String)ses.getAttribute("clientNum");
		
		if(strClientNum==null || strClientNum.trim().equals(""))
		{ 
			if(!(userPO.getCusNum()==null || userPO.getCusNum().trim().equals("")))
			{
				msgList.setE05OFICDE(userPO.getCusNum());
			}
			else
			{
				 if(!(str==null || str.trim().equals("")))
			   	 {	 
				    msgList.setE05OFICDE(str);
			  	 }
			  	 else
			   	 {
					if(!(userPO.getCusNum()==null || userPO.getCusNum().trim().equals("")))
					{
						msgList.setE05OFICDE(userPO.getCusNum());
					}
					else
					{
						msgList.setE05OFICDE(""); 	 
			    	}
			  	 }
			}
			   
		}
		else 
		{
				msgList.setE05OFICDE(strClientNum);
		}
		
		//new Code to get parameters from showProdAcc2(offCode,typ,prod)
		try {
			if(req.getAttribute("offCode")!=null)
			{
				 msgList.setE05OFICDE((String)(req.getAttribute("offCode")));  
			}
			if(req.getAttribute("typ")!=null)
			{
				msgList.setE05OFICDE((String)(req.getAttribute("typ"))); 
			}
			if(req.getAttribute("prod")!=null)
			{
				msgList.setE05OFICDE((String)(req.getAttribute("prod")));
			}
			
			
		} 
		catch (Exception ex) {
				flexLog("Error: " + ex); 
		}
		
		//msgList.setE05OFICDE(str);
		userPO.setHeader10(str); 
		
		msgList.send();	
		msgList.destroy();
		flexLog("ECIF17005 Message Sent");
				
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
				flexLog("About to call Page: " + LangPath + "ECIF170_cif_client_search.jsp");
				callPage(LangPath + "ECIF170_cif_client_search.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF17005")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			boolean firstTime = true;
			String chk = "";
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			java.math.BigDecimal totalAssets = new java.math.BigDecimal(0);
			java.math.BigDecimal totalLiabilities = new java.math.BigDecimal(0);
			java.math.BigDecimal netPosition = new java.math.BigDecimal(0);
			
			while (true) {

				msgList = (ECIF17005Message)newmessage;

				marker = msgList.getE05INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader1(msgList.getE05CUSNA1().trim());
						userPO.setHeader2(msgList.getE05CUSSTS().trim());
					}
					else {
						chk = "";
					}
					
					myFlag = msgList.getE05CLSACC();
					if ( myFlag.trim().equals("1") ) {
						totalAssets = totalAssets.add(msgList.getBigDecimalE05BSETOT());
					
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE05ACCNUM() + "\" " + chk + "></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CUSCUN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CUSNA1()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCNUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCTYP()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05TYPDSC()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCSTS()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CCYCDE()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE05OPEDT1(), msgList.getE05OPEDT2(), msgList.getE05OPEDT3()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEPRI()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEINT()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEOTH()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSETOT()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					
					}
					
				
					else if ( myFlag.trim().equals("2") ){
						totalLiabilities = totalLiabilities.add(msgList.getBigDecimalE05BSETOT());
						
						myRow =  new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE05ACCNUM() + "\" " + chk + "></TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CUSCUN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CUSNA1()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCNUM()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCTYP()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05TYPDSC()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCSTS()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CCYCDE()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE05OPEDT1(), msgList.getE05OPEDT2(), msgList.getE05OPEDT3()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEPRI()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEINT()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSEOTH()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.fcolorCCY(msgList.getE05BSETOT()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
					}
									
				}

				newmessage = mc.receiveMessage();
			}

			userPO.setHeader3(msgList.getH05OPECOD());
			userPO.setHeader4(msgList.getE05ACCNUM());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifTotal", msgList); 
			ses.setAttribute("cifPos", beanList);
			ses.setAttribute("userPO", userPO);
			
			try {
				flexLog("About to call Page: " + LangPath + "ECIF170_cust_exec_position.jsp");
				callPage(LangPath + "ECIF170_cust_exec_position.jsp", req, res);

			}
			catch (Exception e) {
 
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
protected void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	UserPos	userPO = null;	

	try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		int type = Integer.parseInt(req.getParameter("Type"));
		String clientNum = req.getParameter("E01SELNME");

		userPO.setType(new Integer(type).toString());
		userPO.setCusNum(clientNum);
		
		ses.setAttribute("userPO", userPO);

		switch (type) {
			case 1 :
				procReqTotal(mc, user, req, res, ses);
				break;
			case 2 :
				procReqList2(mc, user, req, res, ses);
				break;
			default :
		
		 
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}


}


/* Trial
 * 
 */
 protected void procReqList2(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			 throws ServletException, IOException {

	 MessageRecord newmessage = null;
	 ECIF17001Message msgList = null;
	 ECIF17001Message msgSearch = null;
	 ELEERRMessage msgError = null;
	 JBList beanList = null;
	 UserPos	userPO = null;	
	 boolean IsNotError = false;

	 try {
		 msgError = new datapro.eibs.beans.ELEERRMessage();
		 userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		 } 
	 catch (Exception ex) {
		 flexLog("Error: " + ex); 
	 }


	 // Send Initial data
	 try
	 {
		 flexLog("Send Initial Data");
		 msgSearch = (ECIF17001Message)mc.getMessageRecord("ECIF17001");
		 msgSearch.setH01USERID(user.getH01USR());
		 msgSearch.setH01PROGRM("ECIF170");
		 msgSearch.setH01TIMSYS(getTimeStamp());
		 msgSearch.setH01FLGWK1(userPO.getHeader8());
		 msgSearch.setH01SCRCOD("01");
		 msgSearch.setH01OPECOD("0004");
		 flexLog("ECIF17001 Header Sent");
		 
		 
		 msgSearch.setE01SELTYP(userPO.getType());
		 msgSearch.setE01SELNME(userPO.getCusNum());
		 String sel = msgSearch.getE01SELNME();
		 String typ = msgSearch.getE01SELTYP();
		 
		 msgSearch.send();	
		 msgSearch.destroy();
		 flexLog("ECIF17001 Message Sent");
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
				 flexLog("About to call Page: " + LangPath + "ECIF170_cust_search.jsp");
				 callPage(LangPath + "ECIF170_cust_search.jsp", req, res);

			 }
			 catch (Exception e) {
				 flexLog("Exception calling page " + e);
			 }

		 }
		 else if (newmessage.getFormatName().equals("ECIF17001")) {
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
			 int indexRow = 0;
			 while (true) {

				 msgList = (ECIF17001Message)newmessage;

				 String acc = msgList.getE01SELNME();
				 String officeCode = msgList.getE01OFICDE();
				
				 marker = msgList.getE01INDOPE();

				 if (marker.equals("*")) {
					 beanList.setShowNext(false);
					 break;
				 }
				 else {
					 if (firstTime) {
						 firstTime = false;
						 beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
						 chk = "checked";
					 }
					 else {
						 chk = "";
					 }
	
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ><input type=\"radio\" name=\"ACC\" value=\"" + msgList.getE01OFICDE() + "\" " + chk + " onclick=\"showAddInfo("+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP >" + Util.formatCell(msgList.getE01OFICDE()) + "</TD>");
					myRow.append("<TD NOWRAP >" + Util.formatCell(msgList.getE01OFINME()) + "</TD>");
					myRow.append("<TD NOWRAP >" + Util.formatCell(msgList.getE01OFIEML()) + "</TD>");
					myRow.append("<TD NOWRAP >" + Util.formatCell(msgList.getE01OFIPHN()) + "</TD>");
					myRow.append("<TD NOWRAP >" + Util.formatCell(msgList.getE01OFIPXT()) + "</TD>");
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
			 
			 userPO.setType("1");
			 userPO.setHeader2(msgList.getE01OFICDE()); //This is the acc. #
			 ses.setAttribute("userPO", userPO);
			 ses.setAttribute("cifList", beanList);
			 ses.setAttribute("cifTotal", msgList);
			 try {
				 flexLog("About to call Page: " + LangPath + "ECIF170_cust_list.jsp");
				 callPage(LangPath + "ECIF170_cust_list.jsp", req, res);

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
protected void procReqTotal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECIF17001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos	userPO = null;	
		ECIF17003Message msgTotal = null;
		boolean IsNotError = false;

		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgList = (ECIF17001Message)mc.getMessageRecord("ECIF17001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");
	 	
			flexLog("ECIF17001 Header Sent");
		
		try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		msgList.setE01SELTYP(userPO.getType());
		
		if(userPO.getHeader10().trim().equals(""))
		{
			if(!(userPO.getCusNum().trim().equals("")))
			{
			  msgList.setE01SELNME(userPO.getCusNum());
			  userPO.setHeader1("");
			}
			else
			{
				msgList.setE01SELNME("");	
			}
		}
		else
		{
			//msgList.setE01SELNME(userPO.getHeader2());
			msgList.setE01SELNME(userPO.getHeader10());
		}
		//msgList.setE01OFICDE(userPO.getHeader1());
		
		ses.setAttribute("userPO", userPO);
		
		msgList.send();	
		msgList.destroy();
		
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
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
				flexLog("About to call Page: " + LangPath + "ECIF170_cust_search_error_occurred.jsp");
				callPage(LangPath + "ECIF170_cust_search_error_occurred.jsp", req, res);	
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	  }
	  else if (newmessage.getFormatName().equals("ECIF17003")) {

		 try {
				 msgTotal = new datapro.eibs.beans.ECIF17003Message();
		 } 
		 catch (Exception ex) {
				 flexLog("Error: " + ex); 
		 }

		 msgTotal = (ECIF17003Message)newmessage;

		 flexLog("Putting java beans into the session");
		 ses.setAttribute("cifTotal", msgTotal);

		 try {
			 flexLog("About to call Page: " + LangPath + "ECIF170_cust_oper_summary.jsp");
			 callPage(LangPath + "ECIF170_cust_oper_summary.jsp", req, res);

		 }
		 catch (Exception e) {
			 flexLog("Exception calling page " + e);
		 }
		
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
		 	 
}

/**
 * This method was created in VisualAge.
 */
protected void procReqTotal2(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECIF17001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos	userPO = null;	
		ECIF17003Message msgTotal = null;
		boolean IsNotError = false;

		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgList = (ECIF17001Message)mc.getMessageRecord("ECIF17001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");
	 	
			flexLog("ECIF17001 Header Sent");
		
		try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		String strClientNum = (String)ses.getAttribute("clientNum");
		
		
		msgList.setE01SELTYP("1");
		
		if(strClientNum==null || strClientNum.trim().equals(""))
		{
		
		 if(!(userPO.getCusNum()== null || userPO.getCusNum().trim().equals("")))
		 {
			  msgList.setE01SELNME(userPO.getCusNum());
			
		 }
		 else
		 {
			//msgList.setE01SELNME(userPO.getHeader2());
			msgList.setE01SELNME("");
		 }
		}
		else
		{
			msgList.setE01SELNME(strClientNum);
		}
		
		//msgList.setE01OFICDE(userPO.getHeader1());
		
		
		ses.setAttribute("userPO", userPO);
		
		msgList.send();	
		msgList.destroy();
		
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
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
			flexLog("Page Under Construction");
			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	  }
	  else if (newmessage.getFormatName().equals("ECIF17003")) {

		 try {
				 msgTotal = new datapro.eibs.beans.ECIF17003Message();
		 } 
		 catch (Exception ex) {
				 flexLog("Error: " + ex); 
		 }

		 msgTotal = (ECIF17003Message)newmessage;

		 flexLog("Putting java beans into the session");
		 ses.setAttribute("cifTotal", msgTotal);

		 try {
			 flexLog("About to call Page: " + LangPath + "ECIF170_cust_oper_summary.jsp");
			 callPage(LangPath + "ECIF170_cust_oper_summary.jsp", req, res);

		 }
		 catch (Exception e) {
			 flexLog("Exception calling page " + e);
		 }
		
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
		 	 
}


/**
 * This method was created in VisualAge.
 */
protected void procReqPosProduct(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF17004Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
    //String clientNum = "";
	String strClientNum = "";

   
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	
	/*try{ 
			
		clientNum = req.getParameter("ACC");	
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	*/
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	userPO.setType("");
	String str = userPO.getHeader1();
    
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF17004Message)mc.getMessageRecord("ECIF17004");
		msgList.setH04USERID(user.getH01USR());
		msgList.setH04PROGRM("ECIF170");
		msgList.setH04TIMSYS(getTimeStamp());
		msgList.setH04SCRCOD("01");
		msgList.setH04OPECOD("0004");
		//msgList.setE04CUSCUN(userPO.getCusNum());

		
		strClientNum = (String)ses.getAttribute("clientNum");
	
	
		
			if(strClientNum==null || strClientNum.trim().equals(""))
			{
				
				if(!(userPO.getCusNum()==null || userPO.getCusNum().trim().equals("")))
				{
					msgList.setE04OFICDE(userPO.getCusNum());
				}
				else
				{
					msgList.setE04OFICDE("");	 
				}
			 }
			 else
			 {
				msgList.setE04OFICDE(strClientNum);
			 }
			 
	
		
	
		msgList.send();	
		msgList.destroy();
		flexLog("ECIF17004 Message Sent");
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
				flexLog("About to call Page: " + LangPath + "ECIF170_cust_search.jsp");
				callPage(LangPath + "ECIF170_cust_search.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF17004")) {
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
			java.math.BigDecimal totalAssets = new java.math.BigDecimal(0);
			java.math.BigDecimal totalLiabilities = new java.math.BigDecimal(0);
			java.math.BigDecimal netPosition = new java.math.BigDecimal(0);
			String graphAssets = "";
			String graphLiabilities = "";
			int colAssets = 0;
			int colLiabilities = 0;
			
			while (true) {

				msgList = (ECIF17004Message)newmessage;

				marker = msgList.getE04INDOPE();
				flexLog("marker = " + marker);
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						//userPO.setHeader1(msgList.getE04CUSNA1());
						//userPO.setHeader2(msgList.getE04CUSSTS());
					}
					
					myFlag = msgList.getE04CLSACC();
					if ( myFlag.trim().equals("1") ) {
						totalAssets = totalAssets.add(msgList.getBigDecimalE04BSETOT());
						colAssets ++;
						graphAssets += "<param name=c" + colAssets + "_label value=\"" + msgList.getE04PROCDE() + "\">";
						graphAssets += "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEPRI() + "\">";
						graphAssets += "<param name=c1" + colAssets + "color value=\"red\">";
						graphAssets += "<param name=c1" + colAssets + "style value=\"solid\">";
						colAssets ++;
						graphAssets += "<param name=c" + colAssets + "_label value=\"\">";
						graphAssets += "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEINT() + "\">";
						graphAssets += "<param name=c" + colAssets + "_color value=\"blue\">";
						graphAssets += "<param name=c" + colAssets + "_style value=\"solid\">";
					}
					else if ( myFlag.trim().equals("2") ){
						totalLiabilities = totalLiabilities.add(msgList.getBigDecimalE04BSETOT());
						colLiabilities ++;
						graphLiabilities += "<param name=c" + colLiabilities + "_label value=\"" + msgList.getE04PROCDE() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEPRI() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_color value=\"red\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_style value=\"solid\">";
						colLiabilities ++;
						graphLiabilities += "<param name=c" + colLiabilities + "_label value=\"\">";
						graphLiabilities += "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEINT() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_color value=\"blue\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_style value=\"solid\">";
					}
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showProdAcc2('" + msgList.getE04OFICDE() + "','" + msgList.getE04ACCTYP() + "','" + msgList.getE04PROCDE() + "')\">" + Util.formatCell(msgList.getE04PRODSC().trim()) + "</A></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04PROCDE()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04BNKNUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04CCYCDE()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04NUMOPE()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEPRI()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEINT()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEOTH()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSETOT()) + "</TD>");
					myRow.append("</TR>");
					//flexLog("Flag = " + myFlag);
					//flexLog("Row  = " + myRow);
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();
			}
			
			String appHeader = "<TR><TD colspan=\"9\"><applet code=\"datapro.eibs.applets.graph.Chart.class\" width=100% height=150 align=\"absmiddle\"  codebase=\"" + super.webAppPath + "/applets/\">";   
			if ( !graphAssets.equals("")) {
				graphAssets = appHeader + "<param name=title value=\"\"><param name=columns value=" + colAssets + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphAssets + "</applet></TD></TR>";
			}
			if ( !graphLiabilities.equals("")) {
				graphLiabilities = appHeader + "<param name=title value=\"\"><param name=columns value=" + colLiabilities + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphLiabilities + "</applet></TD></TR>";
			}
			
			netPosition = totalAssets.subtract(totalLiabilities);

			userPO.setHeader3(Util.fcolorCCY(totalAssets.toString()));
			userPO.setHeader4(Util.fcolorCCY(totalLiabilities.toString()));
			userPO.setHeader5(Util.fcolorCCY(netPosition.toString()));
			
			// userPO.setHeader6(graphAssets);
			// userPO.setHeader7(graphLiabilities);
			userPO.setHeader6("");
			userPO.setHeader7("");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifTotal", msgList);
			ses.setAttribute("cifProd", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF170_cust_products.jsp");
				callPage(LangPath + "ECIF170_cust_products.jsp", req, res);

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
protected void procReqProd(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF17004Message msgList = null;
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

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF17004Message)mc.getMessageRecord("ECIF17004");
		msgList.setH04USERID(user.getH01USR());
		msgList.setH04PROGRM("ECIF170");
		msgList.setH04TIMSYS(getTimeStamp());
		msgList.setH04SCRCOD("01");
		msgList.setH04OPECOD("0004");
		msgList.setE04CUSCUN(userPO.getCusNum());

		msgList.send();	
		msgList.destroy();
		flexLog("ECIF17004 Message Sent");
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
				flexLog("About to call Page: " + LangPath + "ECIF170_cif_client_search.jsp");
				callPage(LangPath + "ECIF170_cif_client_search.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF17004")) {
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
			java.math.BigDecimal totalAssets = new java.math.BigDecimal(0);
			java.math.BigDecimal totalLiabilities = new java.math.BigDecimal(0);
			java.math.BigDecimal netPosition = new java.math.BigDecimal(0);
			String graphAssets = "";
			String graphLiabilities = "";
			int colAssets = 0;
			int colLiabilities = 0;
			
			while (true) {

				msgList = (ECIF17004Message)newmessage;

				marker = msgList.getE04INDOPE();
				flexLog("marker = " + marker);
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						userPO.setHeader1(msgList.getE04CUSNA1());
						userPO.setHeader2(msgList.getE04CUSSTS());
					}
					
					myFlag = msgList.getE04CLSACC();
					if ( myFlag.trim().equals("1") ) {
						totalAssets = totalAssets.add(msgList.getBigDecimalE04BSETOT());
						colAssets ++;
						graphAssets += "<param name=c" + colAssets + "_label value=\"" + msgList.getE04PROCDE() + "\">";
						graphAssets += "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEPRI() + "\">";
						graphAssets += "<param name=c1" + colAssets + "color value=\"red\">";
						graphAssets += "<param name=c1" + colAssets + "style value=\"solid\">";
						colAssets ++;
						graphAssets += "<param name=c" + colAssets + "_label value=\"\">";
						graphAssets += "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEINT() + "\">";
						graphAssets += "<param name=c" + colAssets + "_color value=\"blue\">";
						graphAssets += "<param name=c" + colAssets + "_style value=\"solid\">";
					}
					else if ( myFlag.trim().equals("2") ){
						totalLiabilities = totalLiabilities.add(msgList.getBigDecimalE04BSETOT());
						colLiabilities ++;
						graphLiabilities += "<param name=c" + colLiabilities + "_label value=\"" + msgList.getE04PROCDE() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEPRI() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_color value=\"red\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_style value=\"solid\">";
						colLiabilities ++;
						graphLiabilities += "<param name=c" + colLiabilities + "_label value=\"\">";
						graphLiabilities += "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEINT() + "\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_color value=\"blue\">";
						graphLiabilities += "<param name=c" + colLiabilities + "_style value=\"solid\">";
					}
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showProdAcc2('" + msgList.getE04OFICDE() + "','" + msgList.getE04ACCTYP() + "','" + msgList.getE04PROCDE() + "')\">" + Util.formatCell(msgList.getE04PRODSC().trim()) + "</A></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04PROCDE()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04BNKNUM()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04CCYCDE()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04NUMOPE()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEPRI()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEINT()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEOTH()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSETOT()) + "</TD>");
					myRow.append("</TR>");
					//flexLog("Flag = " + myFlag);
					//flexLog("Row  = " + myRow);
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();
			}
			
			String appHeader = "<TR><TD colspan=\"9\"><applet code=\"datapro.eibs.applets.graph.Chart.class\" width=100% height=150 align=\"absmiddle\"  codebase=\"" + super.webAppPath + "/applets/\">";   
			if ( !graphAssets.equals("")) {
				graphAssets = appHeader + "<param name=title value=\"\"><param name=columns value=" + colAssets + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphAssets + "</applet></TD></TR>";
			}
			if ( !graphLiabilities.equals("")) {
				graphLiabilities = appHeader + "<param name=title value=\"\"><param name=columns value=" + colLiabilities + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphLiabilities + "</applet></TD></TR>";
			}
			
			netPosition = totalAssets.subtract(totalLiabilities);

			userPO.setHeader3(Util.fcolorCCY(totalAssets.toString()));
			userPO.setHeader4(Util.fcolorCCY(totalLiabilities.toString()));
			userPO.setHeader5(Util.fcolorCCY(netPosition.toString()));
			
			// userPO.setHeader6(graphAssets);
			// userPO.setHeader7(graphLiabilities);
			userPO.setHeader6("");
			userPO.setHeader7("");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifProd", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF170_cust_products.jsp");
				callPage(LangPath + "ECIF170_cust_products.jsp", req, res);

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
protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		//userPO.setOption("CIF");
		//userPO.setPurpose("INQUIRY");
		
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "ECIF170_cust_search.jsp");
		callPage(LangPath + "ECIF170_cust_search.jsp", req, res);
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
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
				case R_SEARCH :
					procReqSearch(msgUser, req, res, session);
					break;
				case A_SEARCH :
					procActionSearch(mc, msgUser, req, res, session);
					break;
				case R_LIST :
					procActionList(mc, msgUser, req, res, session);
					break;
				case A_ACCOUNT :
					procActionEnterAcc(mc, msgUser, req, res, session);
					break;
				case A_TOTAL :
					procReqTotal2(mc, msgUser, req, res, session);
				 	break;
				case A_ACC :
					procReqPosAccount(mc, msgUser, req, res, session);
					break;
				case A_PRODUCT :
					procReqPosProduct(mc, msgUser, req, res, session);
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



/* 
 * @param request HttpServletRequest
 * @param response HttpServletResponse
*/
protected void procActionList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	
	
	
	try {

		userPO = (UserPos) ses.getAttribute("userPO");
		
		String clientNum = req.getParameter("ACC");	
		
		userPO.setHeader1(clientNum);

		int option = Integer.parseInt(req.getParameter("opt"));
	
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("clientNum", clientNum);
       

		switch (option) {
				case 1 : 
					procReqTotal2(mc, user, req, res, ses);
					break;
				case 2 : 
					procReqPosAccount(mc, user, req, res, ses);
					break;
				case 3 :
					procReqPosProduct(mc, user, req, res, ses);
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