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

public class JSEIE0060 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final  int A_MAINTENANCE		= 2;
	protected static final  int A_Q_LIST			= 4;
	protected static final  int A_POSITION			= 6;
	
	protected static final  int A_ENTER_NEW	 	= 200;
	protected static final  int A_ENTER_MAINT	 	= 400;
	protected static final  int A_ENTER_DELETE	 	= 600;
	protected static final  int A_ENTER_INQUIRY 	= 800;
	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEIE0060() {
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
	EIE006001Message msgInst = null;
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
		msgInst = (EIE006001Message)mc.getMessageRecord("EIE006001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0001");

		try {
		 	  msgInst.setE01QUOIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgInst.setE01QUOIIC("BND");
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE006001 Message Sent");
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

		if (newmessage.getFormatName().equals("EIE006001")) {
			try {
				msgInst = new EIE006001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgInst = (EIE006001Message)newmessage;
			
			userPO.setPurpose("N");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0060_inv_quote_basic.jsp");
						callPage(LangPath + "EIE0060_inv_quote_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308Q?SCREEN=100");			}
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
	EIE006001Message msgInst = null;
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
		msgInst = (EIE006001Message)mc.getMessageRecord("EIE006001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0002");


		try {
		 	  msgInst.setE01QUOIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE1(req.getParameter("DATE1"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE2(req.getParameter("DATE2"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE3(req.getParameter("DATE3"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTET(req.getParameter("TIMET"));
		}
		catch (Exception e)	{
	 	  
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE006001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE006001")) {
			try {
				msgInst = new EIE006001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE006001Message)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0060_inv_quote_basic.jsp");
						callPage(LangPath + "EIE0060_inv_quote_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308Q?SCREEN=100");			}
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

protected  void procActionEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE006001Message msgInst = null;
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
		msgInst = (EIE006001Message)mc.getMessageRecord("EIE006001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0003");


		try {
		 	  msgInst.setE01QUOIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE1(req.getParameter("DATE1"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE2(req.getParameter("DATE2"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE3(req.getParameter("DATE3"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTET(req.getParameter("TIMET"));
		}
		catch (Exception e)	{
	 	  
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE006001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE006001")) {
			try {
				msgInst = new EIE006001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE006001Message)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EIE0060_inv_inq_quote_basic.jsp");
						callPage(LangPath + "EIE0060_inv_inq_quote_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308Q?SCREEN=100");			}
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

protected  void procActionEnterDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE006001Message msgInst = null;
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
		msgInst = (EIE006001Message)mc.getMessageRecord("EIE006001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0004");


		try {
		 	  msgInst.setE01QUOIIC(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE1(req.getParameter("DATE1"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE2(req.getParameter("DATE2"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTE3(req.getParameter("DATE3"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgInst.setE01QUOTET(req.getParameter("TIMET"));
		}
		catch (Exception e)	{
	 	  
		}
		
	 	msgInst.send();
	 	msgInst.destroy();
	 	flexLog("EIE006001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE006001")) {
			try {
				msgInst = new EIE006001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgInst = (EIE006001Message)newmessage;
			
			userPO.setPurpose("M");	
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("invInst", msgInst);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors

            res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=4" + "&CODE=" + msgInst.getE01QUOIIC());								
					/*try {
						flexLog("About to call Page: " + LangPath + "EIE0060_inv_quote_del_confirm.jsp");
						callPage(LangPath + "EIE0060_inv_quote_del_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}*/
			}
			else {  // There are errors
            res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=4" + "&CODE=" + msgInst.getE01QUOIIC());								
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
protected  void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EIE006001Message msgInst = null;
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
	 	msgInst = (EIE006001Message)ses.getAttribute("invInst");
		//msgInst = (EFE0120DSMessage)mc.getMessageRecord("EFE0120DS");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE000001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");

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
					msgInst = new EIE006001Message();
					flexLog("EIE006001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EIE006001Message)newmessage;
				// showESD008004(msgInst);
				
				userPO.setIdentifier(msgInst.getE01QUOIIC());
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("invInst", msgInst);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=4"+ "&CODE=" + userPO.getIdentifier());
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EIE0060_inv_quote_basic.jsp");
							callPage(LangPath + "EIE0060_inv_quote_basic.jsp", req, res);	
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

	protected  void procActionPos(
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


		String CODE = req.getParameter("CODE");
		String DATE1 = req.getParameter("DATE1");
		String DATE2 = req.getParameter("DATE2");
		String DATE3 = req.getParameter("DATE3");
		String TIMET = req.getParameter("TIMET");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=200" + "&CODE=" + CODE);		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=400" + "&CODE=" + CODE 
					+ "&DATE1=" + DATE1 + "&DATE2=" + DATE2 + "&DATE3=" + DATE3 
					+ "&TIMET=" + TIMET);
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=600" + "&CODE=" + CODE 
					+ "&DATE1=" + DATE1 + "&DATE2=" + DATE2 + "&DATE3=" + DATE3 
					+ "&TIMET=" + TIMET);
				break;
				
			case 4 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=800" + "&CODE=" + CODE 
					+ "&DATE1=" + DATE1 + "&DATE2=" + DATE2 + "&DATE3=" + DATE3 
					+ "&TIMET=" + TIMET);
				break;
			case 10 : //Action Main List
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0050?SCREEN=400" + "&CODE=" + userPO.getIdentifier());		    
				break;	
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=200" + "&CODE=" + CODE);
		}
	}




	protected  void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


		MessageRecord newmessage = null;
		EIE006001Message msgInst = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		// Send Initial data
		try {
			
		msgInst = (EIE006001Message)mc.getMessageRecord("EIE006001");
	 	msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EIE0000");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0015");


			try { // Search By Symbol
				msgInst.setE01QUOIIC(req.getParameter("CODE"));
				
			} catch (Exception e) {
				msgInst.setE01QUOIIC(userPO.getIdentifier());
			}

			
			msgInst.send();
			msgInst.destroy();
			flexLog("EIE006001 Message Sent");
		} catch (Exception e) {
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
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);


				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308Q?SCREEN=100");


			} else
				if (newmessage.getFormatName().equals("EIE006001")) {


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


						msgInst = (EIE006001Message) newmessage;


						marker = msgInst.getH01FLGMAS();


						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgInst.getE01QUOIIC()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgInst.getE01QUOTE1()
								+ "', '"
								+ msgInst.getE01QUOTE2()
								+ "', '"
								+ msgInst.getE01QUOTE3()
								+ "', '"
								+ msgInst.getE01QUOTET()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" +   Util.formatDate(msgInst.getE01QUOTE1()
																	,msgInst.getE01QUOTE2()
																	,msgInst.getE01QUOTE3())
									   + "</td>");														
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatTime(msgInst.getE01QUOTET()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.fcolorCCY(msgInst.getE01QUOMID()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.fcolorCCY(msgInst.getE01QUOMIP()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.fcolorCCY(msgInst.getE01QUOBIP()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.fcolorCCY(msgInst.getE01QUOASP()) + "</td>");
							myRow.append("</TR>");
							
							userPO.setIdentifier(msgInst.getE01QUOIIC());
							userPO.setHeader1(msgInst.getD01ISIDSC());
							userPO.setHeader2(msgInst.getD01ISIPTY());
							userPO.setHeader3(msgInst.getD01ISICCY());							


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
						flexLog("About to call Page: " + LangPath + "EIE0060_sel_list_quotes.jsp");
						callPage(LangPath + "EIE0060_sel_list_quotes.jsp", req, res);
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
				case A_Q_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;				
				case A_POSITION :
					procActionPos(mc, msgUser, req, res, session);
					break;
										
				case A_ENTER_NEW : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;
				case A_ENTER_DELETE : 
					procActionEnterDelete(mc, msgUser, req, res, session);
					break;		
				case A_ENTER_INQUIRY : 
					procActionEnterInquiry(mc, msgUser, req, res, session);
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
