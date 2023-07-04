package datapro.eibs.invest;

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

public class JSEIE0130A extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final  int A_MAINTENANCE		= 2;
	protected static final  int A_MAINTENANCE_MP	= 10;
	
	protected static final  int A_ENTER_NEW	 	= 200;
	protected static final  int A_ENTER_MAINT	 	= 400;
	protected static final  int A_ENTER_DELETE	 	= 600;
	protected static final  int A_ENTER_INQUIRY 	= 800;
	
	protected static final  int R_LIST		 		= 900;
	protected static final  int A_LIST		 		= 1000;
	
	protected static final  int A_ENTER_NEW_MP	 	= 1200;
	protected static final  int A_ENTER_MAINT_MP 	= 1400;
	protected static final  int A_ENTER_DELETE_MP 	= 1600;
	protected static final  int R_TICKET	 		= 1800;
	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEIE0130A() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEIE00000");
	
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
protected  void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE013001Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE013001Message)mc.getMessageRecord("EIE013001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0001");
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE013001 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EIE013001")) {
			try {
				msgInst = new EIE013001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EIE013001Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invTrade", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets.jsp");
						callPage(LangPath + "EIE0130A_inv_trade_tickets.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=500");			}
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
protected  void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013001Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;





	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;

	// Send Initial data
	try
	{
		msgInst = (EIE013001Message)mc.getMessageRecord("EIE013001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0002");


		try {
		 	  msgInst.setE01ORDNUM(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	     msgInst.setE01ORDNUM(userPO.getIdentifier());
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE013001 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();


		if (newmessage.getFormatName().equals("EIE013001")) {
			try {
				msgInst = new EIE013001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE013001Message)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invTrade", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets.jsp");
						callPage(LangPath + "EIE0130A_inv_trade_tickets.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=500");			}
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
protected  void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE013001Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgInst = (EIE013001Message)ses.getAttribute("invTrade");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0003");

	 	// all the fields here
	 	java.util.Enumeration enu = msgInst.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase().trim();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	//msgInst.send();
	 	mc.sendMessage(msgInst);
	 	msgInst.destroy();
	 	flexLog("EIE000001 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();

				try {
					msgInst = new EIE013001Message();
					flexLog("EIE000001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EIE013001Message)newmessage;
				// showESD008004(msgInst);
				
				userPO.setIdentifier(msgInst.getE01ORDNUM());
				//Configuring Header
				userPO.setHeader1(msgInst.getE01ORDMST());
				userPO.setHeader2(msgInst.getE01ORDCUN());
				userPO.setHeader3(msgInst.getE01ORDCTN());
				userPO.setHeader4(msgInst.getE01ORDIIC());
				userPO.setHeader5(msgInst.getE01ORDICN());
				userPO.setHeader6(msgInst.getE01ORDSCY());
				userPO.setHeader7(msgInst.getE01ORDPTY());
				userPO.setHeader8(msgInst.getE01ORDIN1());
				userPO.setHeader9(msgInst.getE01ORDIN2());
				userPO.setHeader10(msgInst.getE01ORDIN3());
				userPO.setHeader11(msgInst.getE01ORDST1());
				userPO.setHeader12(msgInst.getE01ORDST2());
				userPO.setHeader13(msgInst.getE01ORDST3());
				userPO.setHeader14(msgInst.getE01ORDMBA());
				userPO.setHeader15(msgInst.getE01ORDMTO());
							
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invTrade", msgInst);
				ses.setAttribute("userPO", userPO);
				
				String ORDNUM = msgInst.getE01ORDNUM();
							

				if (IsNotError) {  // There are no errors
					if(msgInst.getE01ORDTRN().equals("5")){
					   res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=900" + "&CODE=" + ORDNUM);
				
					}
					else {
					try {
						    flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets_confirm.jsp");
							callPage(LangPath + "EIE0130A_inv_trade_tickets_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					}
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets.jsp");
							callPage(LangPath + "EIE0130A_inv_trade_tickets.jsp", req, res);	
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

	protected  void procReqListMultiple(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	JBList beanList = null;
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;
	
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE013002Message)mc.getMessageRecord("EIE013002");
	 	msgInst.setH02USERID(user.getH01USR());
	 	msgInst.setH02PROGRM("EIE0000");
	 	msgInst.setH02TIMSYS(getTimeStamp());
	 	msgInst.setH02SCRCOD("01");
	 	msgInst.setH02OPECOD("0015");


		try {
		 	  msgInst.setE02ORDMNU(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	 
		}
		
			
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE005001 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();


			if (newmessage.getFormatName().equals("ELEERR")) {


				try {
					msgError = new ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=500");

			} else
				if (newmessage.getFormatName().equals("EIE013002")) {


					try {
						beanList = new JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}


					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {


						msgInst = (EIE013002Message) newmessage;


						marker = msgInst.getH02FLGMAS();
						
						userPO.setIdentifier(msgInst.getE02ORDMNU());
						
						
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							userPO.setHeader14(msgInst.getE02ORDMBA());
							userPO.setHeader15(msgInst.getE02ORDMTO());
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgInst.getE02ORDMNU()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgInst.getE02ORDNUM()
								+ "', '"
								+ msgInst.getE02ORDMNU()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgInst.getE02ORDNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgInst.getE02ORDCUN() + "-" + msgInst.getE02ORDCTN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgInst.getE02ORDVNO()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + msgInst.getE02ORDQTY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgInst.getE02ORDPRC()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgInst.getE02ORDIAM()) + "</td>");																
							myRow.append("</TR>");

							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("invList", beanList);
					ses.setAttribute("userPO", userPO);


					try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_sel_list_purchase.jsp");
						callPage(LangPath + "EIE0130A_sel_list_purchase.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}


				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


	}

	protected  void procActionListMultiple(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		int inptOPT = 0;


		inptOPT = Integer.parseInt(req.getParameter("opt"));


		String ORDNUM = req.getParameter("ORDNUM");
		String ORDMAST = req.getParameter("ORDMAST");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=1200" + "&ORDMAST=" + ORDMAST);		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=1400" + "&ORDMAST=" + ORDMAST + "&ORDNUM=" + ORDNUM);
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=1600" + "&ORDMAST=" + ORDMAST + "&ORDNUM=" + ORDNUM );
				break;
			case 10 : //Ticket Confirm
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=1800");
				break;	
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=1200" + "&ORDMAST=" + ORDMAST);
		}
	}


protected  void procActionEnterNewMP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;





	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}





	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");





	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE013002Message)mc.getMessageRecord("EIE013002");
	 	msgInst.setH02USERID(user.getH01USR());
	 	msgInst.setH02PROGRM("EIE0000");
	 	msgInst.setH02TIMSYS(getTimeStamp());
	 	msgInst.setH02SCRCOD("01");
	 	msgInst.setH02OPECOD("0003");
	 	
	 	try {
		 	  msgInst.setE02ORDMNU(req.getParameter("ORDMAST"));
		}
		catch (Exception e)	{
	 	  
		}
		
		//Sending Header
		
		try {
		 	  msgInst.setE02ORDMCU(userPO.getHeader2());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMCN(userPO.getHeader3());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMIC(userPO.getHeader4());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDICN(userPO.getHeader5());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMCY(userPO.getHeader6());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDPTY(userPO.getHeader7());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN1(userPO.getHeader8());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN2(userPO.getHeader9());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN3(userPO.getHeader10());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST1(userPO.getHeader11());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST2(userPO.getHeader12());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST3(userPO.getHeader13());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMBA(userPO.getHeader14());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMTO(userPO.getHeader15());
		}
		catch (Exception e)	{
	 	  
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE013002 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();


		if (newmessage.getFormatName().equals("EIE013002")) {
			try {
				msgInst = new EIE013002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE013002Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invMult", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets_mp.jsp");
						callPage(LangPath + "EIE0130A_inv_trade_tickets_mp.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_sel_list_purchase.jsp");
						callPage(LangPath + "EIE0130A_sel_list_purchase.jsp", req, res);	
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

protected  void procActionEnterMaintMP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE013002Message)mc.getMessageRecord("EIE013002");
	 	msgInst.setH02USERID(user.getH01USR());
	 	msgInst.setH02PROGRM("EIE0000");
	 	msgInst.setH02TIMSYS(getTimeStamp());
	 	msgInst.setH02SCRCOD("01");
	 	msgInst.setH02OPECOD("0003");
	 	
	 	try {
		 	  msgInst.setE02ORDMNU(req.getParameter("ORDMAST"));
		}
		catch (Exception e)	{
	 	      
		}
		
	 	try {
		 	  msgInst.setE02ORDNUM(req.getParameter("ORDNUM"));
		}
		catch (Exception e)	{
	 	  
		}

		//Sending Header
		
		try {
		 	  msgInst.setE02ORDMCU(userPO.getHeader2());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMCN(userPO.getHeader3());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMIC(userPO.getHeader4());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDICN(userPO.getHeader5());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMCY(userPO.getHeader6());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDPTY(userPO.getHeader7());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN1(userPO.getHeader8());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN2(userPO.getHeader9());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDIN3(userPO.getHeader10());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST1(userPO.getHeader11());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST2(userPO.getHeader12());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDST3(userPO.getHeader13());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMBA(userPO.getHeader14());
		}
		catch (Exception e)	{
	 	  
		}
		try {
		 	  msgInst.setE02ORDMTO(userPO.getHeader15());
		}
		catch (Exception e)	{
	 	  
		}

	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE013002 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();


		if (newmessage.getFormatName().equals("EIE013002")) {
			try {
				msgInst = new EIE013002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE013002Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invMult", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets_mp.jsp");
						callPage(LangPath + "EIE0130A_inv_trade_tickets_mp.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EIE0130A_sel_list_purchase.jsp");
						callPage(LangPath + "EIE0130A_sel_list_purchase.jsp", req, res);	
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

protected  void procActionEnterDelMP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;





	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;
	String CODE = "";
	
	
	// Send Initial data
	try
	{
		msgInst = (EIE013002Message)mc.getMessageRecord("EIE013002");
	 	msgInst.setH02USERID(user.getH01USR());
	 	msgInst.setH02PROGRM("EIE0000");
	 	msgInst.setH02TIMSYS(getTimeStamp());
	 	msgInst.setH02SCRCOD("01");
	 	msgInst.setH02OPECOD("0002");
	 	
	 	try {
		 	  msgInst.setE02ORDMNU(req.getParameter("ORDMAST"));
		}
		catch (Exception e)	{
	 	  
		}
		
	 	try {
		 	  msgInst.setE02ORDNUM(req.getParameter("ORDMNUM"));
		}
		catch (Exception e)	{
	 	  
		}

		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE012002 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();


		if (newmessage.getFormatName().equals("EIE013002")) {
			try {
				msgInst = new EIE013002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE013002Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invMult", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0130_inv_trade_tickets_mp_del.jsp");
						callPage(LangPath + "EIE0130_inv_trade_tickets_mp_del.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EIE0130_sel_list_purchase.jsp");
						callPage(LangPath + "EIE0130_sel_list_purchase.jsp", req, res);	
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

protected  void procActionMaintenanceMP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE013002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgInst = (EIE013002Message)ses.getAttribute("invMult");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH02USERID(user.getH01USR());
	 	msgInst.setH02PROGRM("EIE000001");
	 	msgInst.setH02TIMSYS(getTimeStamp());
	 	msgInst.setH02SCRCOD("01");
	 	msgInst.setH02OPECOD("0005");


	 	// all the fields here
	 	java.util.Enumeration enu = msgInst.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase().trim();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}


	 	//msgInst.send();
	 	mc.sendMessage(msgInst);
	 	msgInst.destroy();
	 	flexLog("EIE000001 Message Sent");
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	    newmessage = mc.receiveMessage();


				try {
					msgInst = new EIE013002Message();
					flexLog("EIE000002 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgInst = (EIE013002Message)newmessage;
				// showESD008004(msgInst);
				
				String CODE = msgInst.getE02ORDMNU();
				
				//userPO.setIdentifier(msgInst.getE02ORDNUM());
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invMult", msgInst);
				ses.setAttribute("userPO", userPO);

				
				
				if (IsNotError) {  // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=900"+ "&CODE=" + CODE);
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets_mp.jsp");
							callPage(LangPath + "EIE0130A_inv_trade_tickets_mp.jsp", req, res);	
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

	protected  void procReqTicketConf(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
					
			userPO.setHeader8(getFullTime());
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EIE0130A_inv_trade_tickets_confirm.jsp");
			callPage(LangPath + "EIE0130A_inv_trade_tickets_confirm.jsp", req, res);
		} catch (Exception e) {
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

		int screen = A_ENTER_MAINT;
		
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

				// Action
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
				case A_MAINTENANCE_MP :
					procActionMaintenanceMP(mc, msgUser, req, res, session);
					break;				
									
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
					
				case A_ENTER_NEW_MP : 
					procActionEnterNewMP(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT_MP : 
					procActionEnterMaintMP(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE_MP : 
					procActionEnterDelMP(mc, msgUser, req, res, session);
					break;		
				
				case R_LIST :
					procReqListMultiple(mc, msgUser, req, res, session);
					break;
				case A_LIST :
					procActionListMultiple(mc, msgUser, req, res, session);
					break;
					
				case R_TICKET :
				     procReqTicketConf(msgUser, req, res, session);
					 break;							
				// END Entering

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
