package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEOF0115IA extends datapro.eibs.master.SuperServlet {

	// certificate of deposit
	
	protected static final int R_BASIC		    = 1;
	protected static final int A_BASIC		    = 2;
	protected static final int R_LIST_HELP		= 3;
	protected static final int R_LIST_LEDGER		= 5;
	protected static final int R_LIST_PRINT		= 7;
	


	// entering options
	protected static final int R_LIST_NEW		    = 100;
	protected static final int R_ENTER_MAINT		= 300;

	protected static final int A_LIST_NEW		    = 200;
	protected static final int A_ENTER_MAINT		= 400;
	protected static final int A_INQUIRY			= 500;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEOF0115IA() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0130");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Sending header");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0002");
	 	msgOffChk. setH01FLGWK1("I");
		
		try {
			msgOffChk.setE01OFMCKN(userPO.getIdentifier());
		} catch (Exception e)	{
			msgOffChk.setE01OFMCKN("0");
		}
		try {
			msgOffChk.setE01OFMCCY(userPO.getCurrency());
		} catch (Exception e){
			msgOffChk.setE01OFMCCY("");
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();
	} catch (Exception e){
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
		procReqListLedger(mc,user,req,res,ses);
	} catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
}

protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
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
		//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
		msgOffChk.setH01USERID(user.getH01USR());
		msgOffChk.setH01PROGRM("EOF0115");
		msgOffChk.setH01TIMSYS(getTimeStamp());
		msgOffChk.setH01SCRCOD("01");
		msgOffChk.setH01OPECOD("0002");
		msgOffChk. setH01FLGWK1("I");
		flexLog("Header has been sended");
		try {
			  msgOffChk.setE01OFMCKN(req.getParameter("ACCNUM"));
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
			   msgOffChk.setE01OFMCCY(req.getParameter("Currency").toUpperCase());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCCY("");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}

		msgOffChk.send();	
		msgOffChk.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
		procReqListLedger(mc,user,req,res,ses);
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
protected void procReqEnterMaint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("OCK");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
		callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
	/**
 * This method was created in VisualAge.
 */
protected void procReqListLedger(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	EWD0120DSMessage msgList = null;
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

	int posi = 0;
		
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
		msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0005");
		// all the fields here

		try{
			// all the fields here
		 	java.util.Enumeration enu = msgOffChk.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				}
				catch (Exception e) {
				}	
			}
		}
		catch (Exception e) {
		}

	 	mc.sendMessage(msgOffChk);	
	 	msgOffChk.destroy();
	}		
	catch (Exception e)
	{
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
		ses.setAttribute("error", msgError);
		showERROR(msgError);
		
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
		
	try	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) { 
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;
			// showESD008004(msgOffChk);

			userPO.setIdentifier(msgOffChk.getE01OFMCKN());
			userPO.setCurrency(msgOffChk.getE01OFMCCY());
			userPO.setHeader5(msgOffChk.getE01OFMEM1());
			userPO.setHeader6(msgOffChk.getE01OFMEM2());
			userPO.setHeader7(msgOffChk.getE01OFMEM3());
			userPO.setHeader8(msgOffChk.getE01OFMAMT());
			userPO.setHeader9(msgOffChk.getE01OFMBNF());
			userPO.setHeader10(msgOffChk.getE01OFMBN1());
			userPO.setHeader11(msgOffChk.getE01LETAMT());
			userPO.setHeader12(msgOffChk.getE01OFMCO1());
			userPO.setHeader13(msgOffChk.getE01OFMCO2());
			userPO.setHeader14(msgOffChk.getE01OFMCO3());
			userPO.setHeader19("2");
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());
			
		}
	  	else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
	}
	catch (Exception e) {
	}
	
	try
	{
	  if(IsNotError){
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EWD0120DS")) {

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

				String strDebit = "";
				String strCredit = "";
				java.math.BigDecimal debit = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				

				while (true) {

					msgList = (EWD0120DSMessage)newmessage;

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

						if(msgList.getE01WRKDCC().equals("D")){
							debit = debit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = Util.fcolorCCY(msgList.getE01WRKAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01WRKDCC().equals("C")){
							credit = credit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01WRKAMT());
						}
						
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBNK()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBRN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCY()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKGLN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKACC()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");										
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKTDS()) + "</TD>");					
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

				 	newmessage = mc.receiveMessage();
				 	
				}
				ses.setAttribute("ledList", beanList);
		    }
					
		}
	    	else
			flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_cont.jsp");
					callPage(LangPath + "EOF0115_of_chk_cont.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					//ses.setAttribute("offBasic", msgOffChk);
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
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
protected void procReqPrint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		flexLog("Calling print report");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=3&E01OFMCKN=" + userPO.getIdentifier() + "&E01OFMCCY="+ userPO.getCurrency());
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

		int screen = R_ENTER_MAINT;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
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
				// BEGIN Off. Chk
				// Request
				case R_BASIC :
					break;	
				
				case R_LIST_LEDGER :
					procReqListLedger(mc, msgUser, req, res, session);
					break;	
					
				// END Off. Check

				// BEGIN Entering
				// Request
				case R_ENTER_MAINT : 
					procReqEnterMaint(msgUser, req, res, session);
					break;
				case R_LIST_PRINT : 
					procReqPrint(msgUser, req, res, session);
					break;	
					
				
				// Action 
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				case A_INQUIRY : 
					procActionInquiry(mc, msgUser, req, res, session);
					break;
				
					
				// END Entering

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
}