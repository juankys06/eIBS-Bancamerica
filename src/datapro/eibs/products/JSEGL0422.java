package datapro.eibs.products;

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

public class JSEGL0422 extends datapro.eibs.master.SuperServlet {







	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEGL0422() {
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

		int screen = R_FUTURE_DEBITS;
		
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
				case R_FUTURE_DEBITS :
					procReqFutureDebits(mc, msgUser, req, res, session);
					break;
				case R_FUTURE_CREDITS :
					procReqFutureCredits(mc, msgUser, req, res, session);
					break;
				// Entering Options
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

	protected static final int R_FUTURE_CREDITS		= 3;
	// Future options
	protected static final int R_FUTURE_DEBITS		= 1;

/**
 * This method was created in VisualAge.
 */
protected void procReqFutureCredits(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EGL042102Message msgSearch = null;
	EGL042102Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;
	DataNavTotals dataDC = null;
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;
 	
	try{
		if (req.getParameter("FlagMov") == null) { 
			dataDC =new datapro.eibs.beans.DataNavTotals();
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
		msgSearch = (EGL042102Message)mc.getMessageRecord("EGL042102");
		msgSearch.setH02USERID(user.getH01USR());
	 	msgSearch.setH02PROGRM("EGL0421");
	 	msgSearch.setH02TIMSYS(getTimeStamp());
	 	msgSearch.setH02SCRCOD("01");
	 	msgSearch.setH02OPECOD("0004");
		try{

			try{
				msgSearch.setE02NUMREC(req.getParameter("Pos"));
			 	startPos = 	Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
				flexLog("E02NUMPOS");
			}
		
			try{
			 	msgSearch.setE02SELACC(req.getParameter("NUMACC"));
			}
			catch (Exception e){
			 	flexLog("NUMACC");
			}
			try{
			 	msgSearch.setE02SELDCF(req.getParameter("TR"));
			}
			catch (Exception e){
			 	flexLog("TR");
			}
			try{
			 	msgSearch.setE02SELMOD(req.getParameter("MODE"));
			}
			catch (Exception e){
			 	flexLog("MODE");
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
	  	else if (newmessage.getFormatName().equals("EGL042102")) {

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

					msgList = (EGL042102Message)newmessage;

					marker = msgList.getE02INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							int pos = Integer.parseInt(msgList.getE02NUMREC());
							beanList.setFirstRec(pos);
							if (startPos == 0) {
								//userPO.setHeader5(msgList.getE02());
							}
						}
						
						if(msgList.getE02TRADCC().equals("0")){
							debit = debit.add(msgList.getBigDecimalE02TRAAMT());
							strDebit = Util.fcolorCCY(msgList.getE02TRAAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE02TRADCC().equals("5")){
							credit = credit.add(msgList.getBigDecimalE02TRAAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE02TRAAMT());
						}

						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRAOBK()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRAOBR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRACCY()) + "</TD>");						
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRAGLN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRAACC()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRACCN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRABTH()) + "</TD>");							
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRACDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgList.getE02TRANAR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatDate(msgList.getE02TRAPD1(),msgList.getE02TRAPD2(),msgList.getE02TRAPD3()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatDate(msgList.getE02TRAVD1(),msgList.getE02TRAVD2(),msgList.getE02TRAVD3()) + "</TD>");
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
					flexLog("About to call Page: " + LangPath + "EGL0422_future_credits.jsp"+params);
					res.sendRedirect(super.srctx + LangPath + "EGL0422_future_credits.jsp"+params);
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
protected void procReqFutureDebits(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EGL042102Message msgSearch = null;
	EGL042102Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;
	DataNavTotals dataDC = null;
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int startPos = 0;
 	
	try{
		if (req.getParameter("FlagMov") == null) { 
			dataDC =new datapro.eibs.beans.DataNavTotals();
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
		msgSearch = (EGL042102Message)mc.getMessageRecord("EGL042102");
		msgSearch.setH02USERID(user.getH01USR());
	 	msgSearch.setH02PROGRM("EGL0421");
	 	msgSearch.setH02TIMSYS(getTimeStamp());
	 	msgSearch.setH02SCRCOD("01");
	 	msgSearch.setH02OPECOD("0004");
		try{

			try{
				msgSearch.setE02NUMREC(req.getParameter("Pos"));
			 	startPos = 	Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
				flexLog("E02NUMPOS");
			}
		
			try{
			 	msgSearch.setE02SELACC(req.getParameter("NUMACC"));
			}
			catch (Exception e){
			 	flexLog("NUMACC");
			}
			
			try{
			 	msgSearch.setE02SELDCF(req.getParameter("TR"));
			}
			catch (Exception e){
			 	flexLog("TR");
			}
			
			try{
			 	msgSearch.setE02SELMOD(req.getParameter("MODE"));
			}
			catch (Exception e){
			 	flexLog("MODE");
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
	  	else if (newmessage.getFormatName().equals("EGL042102")) {

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

					msgList = (EGL042102Message)newmessage;

					marker = msgList.getE02INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							int pos = Integer.parseInt(msgList.getE02NUMREC());
							beanList.setFirstRec(pos);
							if (startPos == 0) {
								//userPO.setHeader5(msgList.getE02());
							}
						}
						
						if(msgList.getE02TRADCC().equals("0")){
							debit = debit.add(msgList.getBigDecimalE02TRAAMT());
							strDebit = Util.fcolorCCY(msgList.getE02TRAAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE02TRADCC().equals("5")){
							credit = credit.add(msgList.getBigDecimalE02TRAAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE02TRAAMT());
						}

						myRow =  new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRAOBK()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRAOBR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02TRACCY()) + "</TD>");						
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRAGLN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRAACC()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRACCN()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRABTH()) + "</TD>");						
						myRow.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msgList.getE02TRACDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=\"LEFT\">" + Util.formatCell(msgList.getE02TRANAR()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatDate(msgList.getE02TRAPD1(),msgList.getE02TRAPD2(),msgList.getE02TRAPD3()) + "</TD>");						
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatDate(msgList.getE02TRAVD1(),msgList.getE02TRAVD2(),msgList.getE02TRAVD3()) + "</TD>");
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
					flexLog("About to call Page: " + LangPath + "EGL0422_future_debits.jsp"+params);
					res.sendRedirect(super.srctx + LangPath + "EGL0422_future_debits.jsp"+params);
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
}