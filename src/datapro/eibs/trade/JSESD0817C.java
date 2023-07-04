package datapro.eibs.trade;

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

public class JSESD0817C extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_LIST 				= 1;
	protected static final int R_SEARCH 				= 2;
	protected static final int A_SEARCH 				= 3;
	protected static final int R_PRINT 				= 4;
	protected static final int R_DESC 				= 5;
	protected static final int R_IN_AN 				= 6;

	// entering options
	protected static final int R_ENTER 				= 100;
	protected static final int A_ENTER	 			= 200;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSESD0817C() {
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
 * This method was created in VisualAge.
 * by David Mavilla.
 * on 5/17/00.
 */
protected void procActionSTEnterSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDC010001Message msgLC = null;
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

	// Send Initial data
	try
	{
		flexLog("Sending header");
		msgLC = (EDC010001Message)mc.getMessageRecord("EDC010001");
	 	msgLC.setH01USERID(user.getH01USR());
	 	msgLC.setH01PROGRM("EDL0160");
	 	msgLC.setH01TIMSYS(getTimeStamp());
	 	msgLC.setH01SCRCOD("01");
	 	msgLC.setH01OPECOD("0002");
		try {
			msgLC.setE01DCMACC(req.getParameter("E01DCMACC"));
		}
		catch (Exception e)	{
	 	  	msgLC.setE01DCMACC("0");
		}

		msgLC.send();	
	 	msgLC.destroy();

	 	flexLog("EDC010001 Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);

			try {
				flexLog("About to call Page: " + LangPath + "ESD0817_coll_enter_stat.jsp");
				callPage(LangPath + "ESD0817_coll_enter_stat.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EDC010001")) {
			try {
				msgLC = new datapro.eibs.beans.EDC010001Message();
				flexLog("EDC010001 Received");
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLC = (EDC010001Message)newmessage;
			
			userPO.setIdentifier(msgLC.getE01DCMACC());
			userPO.setHeader1(msgLC.getE01DCMPRO());
			userPO.setHeader2(msgLC.getE01DCMCUN());
			userPO.setHeader3(msgLC.getE01CUSNA1());
			userPO.setCurrency(msgLC.getE01DCMCCY());
			userPO.setOfficer(msgLC.getE01DCMOFC() + " - " + msgLC.getE01DSCOFC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("Calling Request");
			    // procReqList(mc, user, req, res, ses);
			    res.sendRedirect(super.srctx + "/servlet/datapro.eibs.trade.JSESD0817C?SCREEN=1");
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
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESD081701Message msgSearch = null;
	ESD081701Message msgList = null;
	ESD081702Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ESD081701Message)mc.getMessageRecord("ESD081701");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDL0300");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	
		try{
		 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
		}
		catch (Exception e){
		}
	
		try{
		 	msgSearch.setE01TRAACC(userPO.getIdentifier());
		}
		catch (Exception e){
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
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
		
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (userPO.getPurpose().equals("INQUIRY")){
				if(userPO.getHeader21().equals("D")){
					try {
						flexLog("About to call Page: " + LangPath + "EDC0100_coll_doc_inq_basic.jsp");
						callPage(LangPath + "EDC0100_coll_doc_inq_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					try {
						flexLog("About to call Page: " + LangPath + "EDC0100_coll_simp_inq_basic.jsp");
						callPage(LangPath + "EDC0100_coll_simp_inq_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
		  		}	
			}
			else{
		 		try {
					flexLog("About to call Page: " + LangPath + "ESD0817_coll_enter_stat.jsp");
					callPage(LangPath + "ESD0817_coll_enter_stat.jsp", req, res);
		 		}
		 		catch (Exception e) {
					flexLog("Exception calling page " + e);
		 		}			
			}
		}
	  	else if (newmessage.getFormatName().equals("ESD081702")) {
			try {
		  		msgHeader = new datapro.eibs.beans.ESD081702Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (ESD081702Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("lcBalances", msgHeader);
	   
	  		newmessage = mc.receiveMessage();

	  		if (newmessage.getFormatName().equals("ESD081701")) {

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

				String strDebito = "";
				String strCredito = "";
				
				java.math.BigDecimal debito = new java.math.BigDecimal(0);
				java.math.BigDecimal credito = new java.math.BigDecimal(0);
		
				while (true) {

					msgList = (ESD081701Message)newmessage;

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
						
							
							if(msgList.getE01TRADCC().equals("0")){
							 strDebito = Util.fcolorCCY(msgList.getE01TRAAMT());
							 debito = debito.add(msgList.getBigDecimalE01TRAAMT());
							 strCredito ="&nbsp;";
							}
							else if(msgList.getE01TRADCC().equals("5")){
							 strCredito = Util.fcolorCCY(msgList.getE01TRAAMT());
							 credito = credito.add(msgList.getBigDecimalE01TRAAMT());
							 strDebito ="&nbsp;";
						    }
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msgList.getE01TRABD1(), msgList.getE01TRABD2() , msgList.getE01TRABD3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE01TRAVD1() , msgList.getE01TRAVD2() , msgList.getE01TRAVD3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msgList.getE01TRACDE()) + "</TD>");
						if(msgList.getE01TRADRR().equals("0")){
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>");}
						else {
						myRow.append("<TD NOWRAP><A HREF=\"javascript:GetStatDesc('" + msgList.getE01TRADRR() + "','" + msgList.getE01TRANAR() 
								+ "','" + Util.formatDate(msgList.getE01TRABD1(), msgList.getE01TRABD2() , msgList.getE01TRABD3()) + "','" + Util.formatCell(msgList.getE01TRACDE()) + "')\">" + Util.formatCell(msgList.getE01TRANAR()) + 
								"</A></TD>");
						}
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(strDebito) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(strCredito) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01TRABTH()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatTime(msgList.getE01TRATIM()) + "</TD>");				
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRAUSR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAOBK()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAOBR()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}
		
				userPO.setHeader19(Util.fcolorCCY(debito.toString()));
				userPO.setHeader20(Util.fcolorCCY(credito.toString()));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("cifList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					 flexLog("About to call Page: " + LangPath + "ESD0817_coll_list_fp.jsp");
					 callPage(LangPath + "ESD0817_coll_list_fp.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		
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
protected void procReqPrintList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESD081701Message msgSearch = null;
	ESD081701Message msgList = null;
	ESD081702Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ESD081701Message)mc.getMessageRecord("ESD081701");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDL0300");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	
		try{
		 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
		}
		catch (Exception e){
		}
	
		try{
		 	msgSearch.setE01TRAACC(userPO.getIdentifier());
		}
		catch (Exception e){
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
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
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

		 	try {
				flexLog("About to call Page: " + LangPath + "ESD0817_coll_list_fp.jsp");
				callPage(LangPath + "ESD0817_coll_list_fp.jsp", req, res);
		 	}
		 	catch (Exception e) {
				flexLog("Exception calling page " + e);
		 	}			
		}
	  	else if (newmessage.getFormatName().equals("ESD081702")) {
			try {
		  		msgHeader = new datapro.eibs.beans.ESD081702Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (ESD081702Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("lcBalances", msgHeader);
	   
	  		newmessage = mc.receiveMessage();

	  		if (newmessage.getFormatName().equals("ESD081701")) {

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

				String strDebito = "";
				String strCredito = "";
				
				java.math.BigDecimal debito = new java.math.BigDecimal(0);
				java.math.BigDecimal credito = new java.math.BigDecimal(0);
				
				while (true) {

					msgList = (ESD081701Message)newmessage;

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
						
						if(msgList.getE01TRADCC().equals("0")){
							 strDebito = Util.fcolorCCY(msgList.getE01TRAAMT());
							 debito = debito.add(msgList.getBigDecimalE01TRAAMT());
							 strCredito ="&nbsp;";
						}
						else if(msgList.getE01TRADCC().equals("5")){
							 strCredito = Util.fcolorCCY(msgList.getE01TRAAMT());
							 credito = credito.add(msgList.getBigDecimalE01TRAAMT());
							 strDebito ="&nbsp;";
						}
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msgList.getE01TRABD1(), msgList.getE01TRABD2() , msgList.getE01TRABD3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE01TRAVD1() , msgList.getE01TRAVD2() , msgList.getE01TRAVD3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msgList.getE01TRACDE()) + "</TD>");
						if(msgList.getE01NUMNAR().equals("0")) {
							myRow.append("<TD>" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("1")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"+ Util.formatCell(msgList.getE01TRANA1()) + "</TD>");
						}				   		
						else if(msgList.getE01NUMNAR().trim().equals("2")) {
							myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("3")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("4")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("5")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA5()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("6")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA5()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA6()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("7")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA5()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA6()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA7()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("8")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA5()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA6()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA7()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA8()) + "</TD>");
						}
						else if(msgList.getE01NUMNAR().trim().equals("9")) {
						    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA1()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA2()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA3()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA4()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA5()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA6()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA7()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA8()) + "<BR>"
							+ Util.formatCell(msgList.getE01TRANA9()) + "</TD>");
						}
						
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(strDebito) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(strCredito) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01TRABTH()) + "</TD>");
						myRow.append("</TR>");

						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}
			
				userPO.setHeader19(Util.fcolorCCY(debito.toString()));
				userPO.setHeader20(Util.fcolorCCY(credito.toString()));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("cifList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					 flexLog("About to call Page: " + LangPath + "ESD0817_coll_list_print_fp.jsp");
					 callPage(LangPath + "ESD0817_coll_list_print_fp.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
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
 * by David Mavilla.
 * on 5/17/00.
 */
protected void procReqSTEnterSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("COLL");
		userPO.setPurpose("STATEMENT");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ESD0817_coll_enter_stat.jsp");
		callPage(LangPath + "ESD0817_coll_enter_stat.jsp", req, res);	
	}
	catch (Exception e) {
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
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case R_PRINT :
					procReqPrintList(mc, msgUser, req, res, session);
					break;
				//entering options
				case R_ENTER :
					procReqSTEnterSearch(msgUser, req, res, session);
					break;
				case A_ENTER :
					procActionSTEnterSearch(mc,msgUser, req, res, session);
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