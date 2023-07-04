package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (7/28/04 5:26:55 PM)
 * @author: Manuel Justo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEGL0425 extends datapro.eibs.master.SuperServlet {

	// GL Statement options
	protected static final int R_LIST 				= 1;
	protected static final int R_PRINT 				= 3;

	// entering options
	protected static final int R_SELECTION			= 100;
	protected static final int A_SELECTION 			= 200;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEGL0425() {
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
 */
protected void procActionSelection(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		userPO.setBank(req.getParameter("E01TRABNK"));
	}
	catch (Exception e) {
	 	flexLog("Bank");
	}
	try {
		userPO.setBranch(req.getParameter("E01TRABRN"));
	}
	catch (Exception e) {
	 	flexLog("Branch");
	}
	try {
		userPO.setCurrency(req.getParameter("E01TRACCY").toUpperCase());
	}
	catch (Exception e) {
	 	flexLog("Currency");
	}
	try {
		userPO.setAccNum(req.getParameter("E01TRAGLN"));
	}
	catch (Exception e) {
	 	flexLog("General Ledger");
	}
	
	userPO.setHeader7(req.getParameter("E01HISCYC"));
	userPO.setHeader8(req.getParameter("E01VALBTH"));

	try{
		userPO.setHeader9(req.getParameter("E01FRDTE1"));
	 	userPO.setHeader10(req.getParameter("E01FRDTE2"));
	 	userPO.setHeader11(req.getParameter("E01FRDTE3"));
	}
	catch (Exception e){
	 	flexLog("ERROR DATE 1");
	}
	try{
	 	userPO.setHeader12(req.getParameter("E01TODTE1"));
	 	userPO.setHeader13(req.getParameter("E01TODTE2"));
	 	userPO.setHeader14(req.getParameter("E01TODTE3"));
	}
	catch (Exception e){
	 	flexLog("ERROR DATE 2");
	}
	try{
	 	userPO.setHeader15(req.getParameter("E01FRREFN"));
	}
	catch (Exception e){
	 	flexLog("ERROR START REFERENCE");
	}
	try{
	 	userPO.setHeader16(req.getParameter("E01TOREFN"));
	}
	catch (Exception e){
	 	flexLog("ERROR END REFRENCE");
	}

	try{
	 	userPO.setHeader17(req.getParameter("E01FRAMNT"));
	}
	catch (Exception e){
	 	flexLog("ERROR START AMOUNT");
	}
	try{
	 	userPO.setHeader18(req.getParameter("E01TOAMNT"));
	}
	catch (Exception e){
	 	flexLog("ERROR END AMOUNT");
	}

	ses.setAttribute("userPO", userPO);
	
	procReqList(mc, user, req, res, ses);
}
/**
 * This method was created in VisualAge.
 */
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EGL042501Message msgSearch = null;
	EGL042501Message msgList = null;
	EGL042502Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;

 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EGL042501Message)mc.getMessageRecord("EGL042501");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EGL0425");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	
		try{

			try{
				msgSearch.setE01NUMREC(req.getParameter("Pos"));
			 	startPos = 	Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
				flexLog("E01NUMPOS");
			}
		
			try{
			 	msgSearch.setE01TRABNK(userPO.getBank());
			}
			catch (Exception e){
			 	flexLog("Bank");
			}
			try{
			 	msgSearch.setE01TRABRN(userPO.getBranch());
			}
			catch (Exception e){
			 	flexLog("Branch");
			}
			try{
			 	msgSearch.setE01TRACCY(userPO.getCurrency());
			}
			catch (Exception e){
			 	flexLog("CCY");
			}
			try{
			 	msgSearch.setE01TRAGLN(userPO.getAccNum());
			}
			catch (Exception e){
			 	flexLog("GL");
			}
			msgSearch.setE01HISCYC(userPO.getHeader7());
			msgSearch.setE01VALBTH(userPO.getHeader8());

			try{
			 	msgSearch.setE01FRDTE1(userPO.getHeader9());
			 	msgSearch.setE01FRDTE2(userPO.getHeader10());
			 	msgSearch.setE01FRDTE3(userPO.getHeader11());
			}
			catch (Exception e){
			 	flexLog("DATE 1");
			}
			try{
			 	msgSearch.setE01TODTE1(userPO.getHeader12());
			 	msgSearch.setE01TODTE2(userPO.getHeader13());
			 	msgSearch.setE01TODTE3(userPO.getHeader14());
			}
			catch (Exception e){
			 	flexLog("DATE 2");
			}
			try{
			 	msgSearch.setE01FRREFN(userPO.getHeader15());
			}
			catch (Exception e){
			 	flexLog("E01FRREFN");
			}
			try{
			 	msgSearch.setE01TOREFN(userPO.getHeader16());
			}
			catch (Exception e){
			 	flexLog("E01TOREFN");
			}

			try{
			 	msgSearch.setE01FRAMNT(userPO.getHeader17());
			}
			catch (Exception e){
			 	flexLog("E01FRAMNT");
			}
			try{
			 	msgSearch.setE01TOAMNT(userPO.getHeader18());
			}
			catch (Exception e){
			 	flexLog("E01TOAMNT");
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
				flexLog("About to call Page: " + LangPath + "EGL0425_st_selection.jsp");
				callPage(LangPath + "EGL0425_st_selection.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EGL042502")) {
			try {
				msgHeader = new datapro.eibs.beans.EGL042502Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (EGL042502Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("stGLBal", msgHeader);
	   
		  	newmessage = mc.receiveMessage();

		  	if (newmessage.getFormatName().equals("EGL042501")) {

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
				String DT1 = "";
				String DT2 = "";
				String DT3 = "";
				String strDebit = "";
				String strCredit = "";
				java.math.BigDecimal debit = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				
				while (true) {

					msgList = (EGL042501Message)newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							int pos = Integer.parseInt(msgList.getE01NUMREC());
							beanList.setFirstRec(pos);
							if (startPos == 0) {
								userPO.setHeader5(msgList.getE01BEGBAL());
							}
						}
						
						if(msgList.getE01TRADCC().equals("0")){
							debit = debit.add(msgList.getBigDecimalE01TRAAMT());
							strDebit = Util.fcolorCCY(msgList.getE01TRAAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01TRADCC().equals("5")){
							credit = credit.add(msgList.getBigDecimalE01TRAAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01TRAAMT());
						}

						DT1 = (userPO.getHeader8().equals("B")) ? msgList.getE01DATE11():msgList.getE01DATE21();
						DT2 = (userPO.getHeader8().equals("B")) ? msgList.getE01DATE12():msgList.getE01DATE22();
						DT3 = (userPO.getHeader8().equals("B")) ? msgList.getE01DATE13():msgList.getE01DATE23();
						
						String Future = "";
						
						if(msgList.getE01TRACNU().equals("1")){
							Future = "<Font Color='BLACK'>" +  "Future" + "</Font>";
						}
						else{
						   Future = ""; 	
						}
							
						myRow =  new StringBuffer("<TR>");
						if (userPO.getHeader8().equals("B")) {
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:GetBatchDet('"+msgList.getE01DATE11()+"','"+msgList.getE01DATE12()+"','"+msgList.getE01DATE13()+"','"+msgList.getE01TRABTH()+"','"+msgList.getE01TRAACR()+ "')\">"
						      + Util.formatDate(msgList.getE01DATE11(), msgList.getE01DATE12() , msgList.getE01DATE13()) + "</A></TD>");
						} else {
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msgList.getE01DATE11(), msgList.getE01DATE12() , msgList.getE01DATE13()) + "</TD>");
						}
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msgList.getE01TRABRN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRAACC()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msgList.getE01TRACDE()) + "</TD>");
						if(msgList.getE01TRADRR().equals("0")){
							myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>");}
						else {
							myRow.append("<TD NOWRAP><A HREF=\"javascript:GetStatDesc('" + msgList.getE01TRADRR() + "','" + msgList.getE01TRANAR()
								  + "','" + Util.formatDate(msgList.getE01DATE11(), msgList.getE01DATE12() , msgList.getE01DATE13()) + "','" + Util.formatCell(msgList.getE01TRACDE()) + "')\">" + Util.formatCell(msgList.getE01TRANAR())
								  + "</A></TD>");
						}
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01ENDBAL()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:GetBatchDet('"+DT1+"','"+DT2+"','"+DT3+"','"+msgList.getE01TRABTH()+"','"+msgList.getE01TRAACR()+ "')\">"
							  + Util.formatCell(msgList.getE01TRABTH()) + "</A></TD>");
					    /*
					    if (userPO.getHeader8().equals("V")) {
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:GetBatchDet('"+msgList.getE01DATE21()+"','"+msgList.getE01DATE22()+"','"+msgList.getE01DATE23()+"','"+msgList.getE01TRABTH()+"','"+msgList.getE01TRAACR()+ "')\">"
						      + Util.formatDate(msgList.getE01DATE21(), msgList.getE01DATE22() , msgList.getE01DATE23()) + "</A></TD>");
					    } else {
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE01DATE21() , msgList.getE01DATE22() , msgList.getE01DATE23()) + "</TD>");
					    }
					    */
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
				
				userPO.setHeader19(Util.fcolorCCY(debit.toString()));
				userPO.setHeader20(Util.fcolorCCY(credit.toString()));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("glList", beanList);

				try {
					if(msgList.getE01VALBTH().equals("V")){
						flexLog("About to call Page: " + LangPath + "EGL0425_st_list_fv.jsp");
						callPage(LangPath + "EGL0425_st_list_fv.jsp", req, res);
					}
					else if(msgList.getE01VALBTH().equals("B")){
						flexLog("About to call Page: " + LangPath + "EGL0425_st_list_fp.jsp");
						callPage(LangPath + "EGL0425_st_list_fp.jsp", req, res);
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
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
protected void procReqPrintList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EGL042501Message msgSearch = null;
	EGL042501Message msgList = null;
	EGL042502Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;

 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EGL042501Message)mc.getMessageRecord("EGL042501");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EGL0425");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	msgSearch.setH01FLGWK1("P");
		try{

			try{
			 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
			 	startPos = 	Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
				flexLog("E01NUMPOS");
			}
		
			try{
			 	msgSearch.setE01TRABNK(userPO.getBank());
			}
			catch (Exception e){
			 	flexLog("Bank");
			}
			try{
			 	msgSearch.setE01TRABRN(userPO.getBranch());
			}
			catch (Exception e){
			 	flexLog("Branch");
			}
			try{
			 	msgSearch.setE01TRACCY(userPO.getCurrency());
			}
			catch (Exception e){
			 	flexLog("CCY");
			}
			try{
			 	msgSearch.setE01TRAGLN(userPO.getAccNum());
			}
			catch (Exception e){
			 	flexLog("GL");
			}
			msgSearch.setE01HISCYC(userPO.getHeader7());
			msgSearch.setE01VALBTH(userPO.getHeader8());

			try{
			 	msgSearch.setE01FRDTE1(userPO.getHeader9());
			 	msgSearch.setE01FRDTE2(userPO.getHeader10());
			 	msgSearch.setE01FRDTE3(userPO.getHeader11());
			}
			catch (Exception e){
			 	flexLog("DATE 1");
			}
			try{
			 	msgSearch.setE01TODTE1(userPO.getHeader12());
			 	msgSearch.setE01TODTE2(userPO.getHeader13());
			 	msgSearch.setE01TODTE3(userPO.getHeader14());
			}
			catch (Exception e){
			 	flexLog("DATE 2");
			}
			try{
			 	msgSearch.setE01FRREFN(userPO.getHeader15());
			}
			catch (Exception e){
			 	flexLog("E01FRREFN");
			}
			try{
			 	msgSearch.setE01TOREFN(userPO.getHeader16());
			}
			catch (Exception e){
			 	flexLog("E01TOREFN");
			}

			try{
			 	msgSearch.setE01FRAMNT(userPO.getHeader17());
			}
			catch (Exception e){
			 	flexLog("E01FRAMNT");
			}
			try{
			 	msgSearch.setE01TOAMNT(userPO.getHeader18());
			}
			catch (Exception e){
			 	flexLog("E01TOAMNT");
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
				flexLog("About to call Page: " + LangPath + "EGL0425_st_selection.jsp");
				callPage(LangPath + "EGL0425_st_selection.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EGL042502")) {
			try {
				msgHeader = new datapro.eibs.beans.EGL042502Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (EGL042502Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("stGLBal", msgHeader);
	   
			newmessage = mc.receiveMessage();

	  		if (newmessage.getFormatName().equals("EGL042501")) {

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

				String strDebit = "";
				String strCredit = "";
				java.math.BigDecimal debit = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				int countDebit = 0;
				int countCredit = 0;
		
				while (true) {

					msgList = (EGL042501Message)newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							int pos = Integer.parseInt(msgList.getE01NUMREC());
							beanList.setFirstRec(pos);
							if (startPos == 0) {
								userPO.setHeader5(msgList.getE01BEGBAL());}
						}
						
						if(msgList.getE01TRADCC().equals("0")){
							debit = debit.add(msgList.getBigDecimalE01TRAAMT());
							countDebit ++;
							strDebit = Util.fcolorCCY(msgList.getE01TRAAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01TRADCC().equals("5")){
							credit = credit.add(msgList.getBigDecimalE01TRAAMT());
							countCredit ++;
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01TRAAMT());
						}

						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgList.getE01TRABRN() + "-" + msgList.getE01TRABTH()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msgList.getE01DATE11(), msgList.getE01DATE12() , msgList.getE01DATE13()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRAACC()) + "</TD>");
						if(msgList.getE01NUMNAR().equals("0")){
						   myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>");}
						else {
						    if(msgList.getE01NUMNAR().trim().equals("1")) {
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
						}

						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE01ENDBAL()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}
		
				userPO.setHeader19(Util.fcolorCCY(debit.toString()));
				userPO.setHeader20(Util.fcolorCCY(credit.toString()));
				userPO.setHeader21(msgList.getE01ENDBAL());
				userPO.setHeader22(countDebit +"");
				userPO.setHeader23(countCredit + "");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("glList", beanList);

				try {
					if(msgList.getE01VALBTH().equals("V")){
						flexLog("About to call Page: " + LangPath + "EGL0425_st_list_print_fv.jsp");
						callPage(LangPath + "EGL0425_st_list_print_fv.jsp", req, res);
					}
					else if(msgList.getE01VALBTH().equals("B")){
						flexLog("About to call Page: " + LangPath + "EGL0425_st_list_print_fp.jsp");
						callPage(LangPath + "EGL0425_st_list_print_fp.jsp", req, res);
					}
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
 * by David Mavilla.
 * on 5/17/00.
 */
protected void procReqSelection(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("GL");
		userPO.setPurpose("STATEMENT");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EGL0425_st_selection.jsp");
		callPage(LangPath + "EGL0425_st_selection.jsp", req, res);	
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

		int screen = R_SELECTION;
		
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
				// Entering Options
				case R_SELECTION :
					procReqSelection(msgUser, req, res, session);
					break;
				case A_SELECTION :
					procActionSelection(mc, msgUser, req, res, session);
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