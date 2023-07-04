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

public class JSEGL0421 extends datapro.eibs.master.SuperServlet {

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
public JSEGL0421() {
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
	EGL042101Message msgSearch = null;
	EGL042101Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;
	DataNavTotals dataDC = null;
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;
 	
	try{
		if (req.getParameter("FlagMov") == null) { 
			dataDC = new datapro.eibs.beans.DataNavTotals();
			}
		else {
			dataDC = (datapro.eibs.beans.DataNavTotals)ses.getAttribute("dataBTH");
		    if(req.getParameter("FlagMov").equals("0")) dataDC.setIndex(dataDC.getIndex() - 1);
			 }
	}
	catch(Exception e){
			}
 	
 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EGL042101Message)mc.getMessageRecord("EGL042101");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EGL0421");
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
			 	msgSearch.setE01SELBTH(req.getParameter("BTH"));
			}
			catch (Exception e){
			 	flexLog("Batch");
			}
			try{
			 	msgSearch.setE01SELDT1(req.getParameter("DT1"));
			 	msgSearch.setE01SELDT2(req.getParameter("DT2"));
			 	msgSearch.setE01SELDT3(req.getParameter("DT3"));
			}
			catch (Exception e){
			 	flexLog("DATE 1");
			}
			try{
			 	msgSearch.setE01SELACR(req.getParameter("ACR"));
			}
			catch (Exception e){
			 	flexLog("E01TRAACR");
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
				flexLog("About to call Page: " + LangPath + "EGL0421_st_selection.jsp");
				callPage(LangPath + "EGL0421_st_selection.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EGL042101")) {

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
				int countTrans =0;
				
				while (true) {

					msgList = (EGL042101Message)newmessage;

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
								//userPO.setHeader5(msgList.getE01());
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

						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAOBK()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRABRN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRACCY()) + "</TD>");						
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRAGLN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRAACC()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRACCN()) + "</TD>");	
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE01TRACDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgList.getE01TRANAR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatDate(msgList.getE01TRAVD1(),msgList.getE01TRAVD2(),msgList.getE01TRAVD3()) + "</TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
						countTrans++;				
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}

				try{
				  if (countTrans > 0) {
									
		 	 	   if (dataDC.getMaxRow() == 0){
			    	  dataDC.addRow(""+debit,""+credit,""+countTrans);
			    	}
				   else if (req.getParameter("FlagMov").equals("1")) {
			 	 	   dataDC.setIndex(dataDC.getIndex() +1); 
			    	   if (dataDC.getIndex() >= dataDC.getMaxRow()) dataDC.addRow(""+debit,""+credit,""+countTrans);
			    	}
		     	  } 
				}
				catch (Exception e){
				}
				
				userPO.setHeader19(Util.fcolorCCY(debit.toString()));
				userPO.setHeader20(Util.fcolorCCY(credit.toString()));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("bthList", beanList);
				ses.setAttribute("dataBTH", dataDC);
				
				String params="";
				try {
					params="?Batch="+req.getParameter("BTH")+"&Date1="+req.getParameter("DT1")+"&Date2="+req.getParameter("DT2")+"&Date3="+req.getParameter("DT3")+"&Ref="+req.getParameter("ACR");
					flexLog("About to call Page: " + LangPath + "EGL0421_batch_detail.jsp"+params);
					res.sendRedirect(super.srctx + LangPath + "EGL0421_batch_detail.jsp"+params);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
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
	EGL042001Message msgSearch = null;
	EGL042001Message msgList = null;
	EGL042002Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;

 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EGL042001Message)mc.getMessageRecord("EGL042001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EGL0420");
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
				flexLog("About to call Page: " + LangPath + "EGL0420_st_selection.jsp");
				callPage(LangPath + "EGL0420_st_selection.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
	  	else if (newmessage.getFormatName().equals("EGL042002")) {
			try {
				msgHeader = new datapro.eibs.beans.EGL042002Message();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (EGL042002Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("stGLBal", msgHeader);
	   
			newmessage = mc.receiveMessage();

	  		if (newmessage.getFormatName().equals("EGL042001")) {

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

					msgList = (EGL042001Message)newmessage;

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
						flexLog("About to call Page: " + LangPath + "EGL0420_st_list_print_fv.jsp");
						callPage(LangPath + "EGL0420_st_list_print_fv.jsp", req, res);
					}
					else if(msgList.getE01VALBTH().equals("B")){
						flexLog("About to call Page: " + LangPath + "EGL0420_st_list_print_fp.jsp");
						callPage(LangPath + "EGL0420_st_list_print_fp.jsp", req, res);
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
		flexLog("About to call Page: " + LangPath + "EGL0420_st_selection.jsp");
		callPage(LangPath + "EGL0420_st_selection.jsp", req, res);	
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