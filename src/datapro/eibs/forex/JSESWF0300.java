package datapro.eibs.forex;

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

public class JSESWF0300 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final  int R_LIST_MAINT = 1;
	protected static final  int R_LIST_INQ = 11;
	
	protected static final  int R_SWIFT_MAINT = 3;
	protected static final  int R_SWIFT_NEW = 7;
	protected static final  int R_SWIFT_COP = 5;
	protected static final  int R_SWIFT_INQ = 9;
	
	protected static final  int A_LIMIT = 2;
	protected static final  int A_LIST = 12;
	
	protected static final  int R_ENTER_MAINT = 100;
	protected static final  int R_ENTER_INQUIRY = 300;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESWF0300() {
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
	protected void procReqTemplateNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF003001Message msgDV = null;
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
	  opCode = "0001";
	
	// Send Initial data
	try
	{
		msgDV = (ESWF003001Message)mc.getMessageRecord("ESWF003001");
	 		    
	    try{ 
	    msgDV.setH01USR(user.getH01USR()); 
	    msgDV.setH01OPE("0001"); 
	    }		
	    catch (Exception e)	{
	
	    }    	   	 	
		msgDV.send();	
	 	msgDV.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF003001")) {
				try {
					msgDV = new ESWF003001Message();
					flexLog("ESWF003001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDV = (ESWF003001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("swff", msgDV);
			    ses.setAttribute("error", msgError);
			    ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath
									+ "ESWF300_swift_free_format.jsp");
							callPage(
								LangPath + "ESWF300_swift_free_format.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} 
				 else { // There are errors
					
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESWF300_sel.jsp");
							callPage(
								LangPath + "ESWF300_sel.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
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
	protected void procReqTemplateMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF003001Message msgDV = null;
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
		msgDV = (ESWF003001Message)mc.getMessageRecord("ESWF003001");
	 		    
	    try{ 
	    msgDV.setH01USR(user.getH01USR()); 
	    msgDV.setH01OPE("0002"); 
	    }		
	    catch (Exception e)	{
	
	    } 
	    
	    try{
	 	msgDV.setESWFFMT(req.getParameter("FORMAT"));
	    }		
	    catch (Exception e)	{

	    }
	 	
	 	try{
	 	msgDV.setESWFTPN(req.getParameter("TEMPLATE"));
	    }		
	    catch (Exception e)	{
	
	    }
	    
	    try{
	 	msgDV.setESWFSWI(req.getParameter("SWIFTID"));
	    }		
	    catch (Exception e)	{
	
	    }   	   	 	
		msgDV.send();	
	 	msgDV.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF003001")) {
				try {
					msgDV = new ESWF003001Message();
					flexLog("ESWF003001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDV = (ESWF003001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("swff", msgDV);
			    ses.setAttribute("error", msgError);
			    ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath
									+ "ESWF300_swift_free_format.jsp");
							callPage(
								LangPath + "ESWF300_swift_free_format.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} 
				 else { // There are errors
					
						res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=1");	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
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
	protected void procReqTemplateInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF003001Message msgDV = null;
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
		msgDV = (ESWF003001Message)mc.getMessageRecord("ESWF003001");
	 		    
	    try{ 
	    msgDV.setH01USR(user.getH01USR()); 
	    msgDV.setH01OPE("0003"); 
	    }		
	    catch (Exception e)	{
	
	    } 
	    
	    try{
	 	msgDV.setESWFFMT(req.getParameter("FORMAT"));
	    }		
	    catch (Exception e)	{

	    }
	 	
	 	try{
	 	msgDV.setESWFTPN(req.getParameter("TEMPLATE"));
	    }		
	    catch (Exception e)	{
	
	    }
	    
	    try{
	 	msgDV.setESWFSWI(req.getParameter("SWIFTID"));
	    }		
	    catch (Exception e)	{
	
	    }   	   	 	
		msgDV.send();	
	 	msgDV.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF003001")) {
				try {
					msgDV = new ESWF003001Message();
					flexLog("ESWF003001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDV = (ESWF003001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("swff", msgDV);
			    ses.setAttribute("error", msgError);
			    ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath
									+ "ESWF300_inq_swift_free_format.jsp");
							callPage(
								LangPath + "ESWF300_inq_swift_free_format.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} 
				 else { // There are errors
					
						res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=11");	
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}











	protected  void procActionList(
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


		String FORMAT = req.getParameter("FORMAT");
		String TEMPLATE = req.getParameter("TEMPLATE");
		String SWIFTID = req.getParameter("SWIFTID");
				
		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=7");		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=3" + "&FORMAT=" + FORMAT + "&TEMPLATE=" + TEMPLATE 
					+ "&SWIFTID=" + SWIFTID);
				break;	
			case 3 : //Inquiry
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=9" + "&FORMAT=" + FORMAT + "&TEMPLATE=" + TEMPLATE
					+ "&SWIFTID=" + SWIFTID);
				break;				
			case 9 : //Delete
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=3" + "&FORMAT=" + FORMAT + "&TEMPLATE=" + TEMPLATE);
				break;
			
					
			default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSESWF0300?SCREEN=7");		    
		}
	}



	protected void procActionTemplate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF003001Message msgSwift = null;
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
	 	msgSwift = (ESWF003001Message)ses.getAttribute("swff");
		msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF003001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01OPE("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgSwift.fieldEnumeration();
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


	 	//msgSwift.send();
	 	mc.sendMessage(msgSwift);
	 	msgSwift.destroy();
	 	flexLog("EFE0120DS Message Sent");
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
					msgSwift = new ESWF003001Message();
					flexLog("ESWF003001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgSwift = (ESWF003001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "ESWF300_swift_confirm.jsp");
							callPage(LangPath + "ESWF300_swift_confirm.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF300_swift_free_format.jsp");
							callPage(LangPath + "ESWF300_swift_free_format.jsp", req, res);	
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
	
protected  void procReqEnterMaint(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ESWF300_enter_search.jsp");
			callPage(LangPath + "ESWF300_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}	

protected  void procReqEnterInquiry(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ESWF300I_enter_search.jsp");
			callPage(LangPath + "ESWF300I_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

protected  void procReqListMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESWF30001Message msgSearch = null;
	ESWF30001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ESWF30001Message)mc.getMessageRecord("ESWF30001");
		msgSearch.setESW1OPE("4");	
	 		 	
	 	
		try{		 
		 msgSearch.setESW1SWI(req.getParameter("SWIFTID"));						
		}
		catch (Exception e)
		{
		  msgSearch.setESW1SWI("");
		}
	 	
	 	try{
		 msgSearch.setESW1TPN(req.getParameter("TEMPLATE"));				
		}
		catch (Exception e)
		{
		  
		}	
		
		try{
		 msgSearch.setESW1FTM(req.getParameter("FORMAT"));				
		}
		catch (Exception e)
		{
		  msgSearch.setESW1FTM("0");
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
	  
			if (newmessage.getFormatName().equals("ESWF30001")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";


			
			while (true) {


				msgList = (ESWF30001Message)newmessage;


				marker = msgList.getESW1OPE();


				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						
					}
					else {
						chk = "";
					}
										
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getESW1FTM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getESW1FTM()
								+ "', '"
								+ msgList.getESW1TPN()
								+ "', '"
								+ msgList.getESW1SWI()
								+ "')\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1FTM()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1TPN()) + "</TD>");					
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1SWI()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1TXT()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}


				newmessage = mc.receiveMessage();
			}
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("ESWF0300Help", beanList);
			ses.setAttribute("userPO", userPO);


			try {
				 flexLog("About to call Page: " + LangPath + "ESWF300_sel.jsp");
				 callPage(LangPath + "ESWF300_sel.jsp", req, res);
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

	
	
protected  void procReqListInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESWF30001Message msgSearch = null;
	ESWF30001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ESWF30001Message)mc.getMessageRecord("ESWF30001");
		msgSearch.setESW1OPE("4");	
	 		 	
	 	
		try{		 
		 msgSearch.setESW1SWI(req.getParameter("SWIFTID"));						
		}
		catch (Exception e)
		{
		  msgSearch.setESW1SWI("");
		}
	 	
	 	try{
		 msgSearch.setESW1TPN(req.getParameter("TEMPLATE"));				
		}
		catch (Exception e)
		{
		  
		}	
		
		try{
		 msgSearch.setESW1FTM(req.getParameter("FORMAT"));				
		}
		catch (Exception e)
		{
		  msgSearch.setESW1FTM("0");
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
	  
			if (newmessage.getFormatName().equals("ESWF30001")) {


			try {
				beanList = new JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";


			
			while (true) {


				msgList = (ESWF30001Message)newmessage;


				marker = msgList.getESW1OPE();


				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						
					}
					else {
						chk = "";
					}
										
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getESW1FTM()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getESW1FTM()
								+ "', '"
								+ msgList.getESW1TPN()
								+ "', '"
								+ msgList.getESW1SWI()
								+ "')\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1FTM()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1TPN()) + "</TD>");					
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1SWI()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getESW1TXT()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}


				newmessage = mc.receiveMessage();
			}
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("ESWF0300Help", beanList);
			ses.setAttribute("userPO", userPO);


			try {
				 flexLog("About to call Page: " + LangPath + "ESWF300I_sel.jsp");
				 callPage(LangPath + "ESWF300I_sel.jsp", req, res);
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

protected  void procReqTemplateInquiry1(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESWF003001Message msgDV = null;
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
	  opCode = "0004";
	
	// Send Initial data
	try
	{
		msgDV = (ESWF003001Message)mc.getMessageRecord("ESWF003001");
	 	
	 	try{
	 	msgDV.setESWFFMT(req.getParameter("FORMAT"));
	    }		
	    catch (Exception e)	{

	    }
	 	
	 	try{
	 	msgDV.setESWFTPN(req.getParameter("TEMPLATE"));
	    }		
	    catch (Exception e)	{
	
	    }
	    
	    
	    try{ 
	    msgDV.setH01USR(user.getH01USR()); 
	    }		
	    catch (Exception e)	{
	
	    } 
	    msgDV.setH01OPE("0003"); 
	      	   	 	
		msgDV.send();	
	 	msgDV.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	//Receive Error Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			try {
				msgError = new ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			
			//res.setContentType("text/html");
			//printClose(res.getWriter());
			
	  	}	
   		else if (newmessage.getFormatName().equals("ESWF003001")) {
			try {
				msgDV = new ESWF003001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgDV = (ESWF003001Message)newmessage; 

			flexLog("Putting java beans into the session");
			ses.setAttribute("swff", msgDV);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ESWF300_inq_swift_free_format.jsp");
				callPage(LangPath + "ESWF300_inq_swift_free_format.jsp", req, res);	
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



	protected  void procReqCopy(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF00001Message msgSWFF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;


		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		String opCode = null;
		opCode = "0001";


		// Send Initial data
		try {
			msgSWFF = (ESWF00001Message) mc.getMessageRecord("ESWF00001");
			msgSWFF.setH01USR(user.getH01USR());
			msgSWFF.setH01PGM("ESWF00001");
			msgSWFF.setH01TIM(getTimeStamp());
			msgSWFF.setH01SCR("01");
			msgSWFF.setH01OPE(opCode);
			//msgSWFF.setE01ACMPRO(userPO.getIdentifier());
			
			userPO.setHeader1(req.getParameter("ESWFSWI"));
			userPO.setHeader2(req.getParameter("ESWFFTM"));
			
			msgSWFF.send();
			msgSWFF.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();


			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


		// Receive Data
		try {
			newmessage = mc.receiveMessage();


			if (newmessage.getFormatName().equals("ESWF00001")) {
				try {
					msgSWFF = new ESWF00001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}


				msgSWFF = (ESWF00001Message) newmessage;

				//String[] rows = Util.splitField(req.getParameter("ESW2REC"), 35, 50 );
				msgSWFF.setESWF01(req.getParameter("ESWF01"));	
				msgSWFF.setESWF02(req.getParameter("ESWF02"));	
				msgSWFF.setESWF03(req.getParameter("ESWF03"));
				msgSWFF.setESWF04(req.getParameter("ESWF04"));
				msgSWFF.setESWF05(req.getParameter("ESWF05"));
				msgSWFF.setESWF06(req.getParameter("ESWF06"));
				msgSWFF.setESWF07(req.getParameter("ESWF07"));
				msgSWFF.setESWF08(req.getParameter("ESWF08"));				
				msgSWFF.setESWF09(req.getParameter("ESWF09"));	
				msgSWFF.setESWF10(req.getParameter("ESWF10"));	
				msgSWFF.setESWF11(req.getParameter("ESWF11"));
				msgSWFF.setESWF12(req.getParameter("ESWF12"));
				msgSWFF.setESWF13(req.getParameter("ESWF13"));
				msgSWFF.setESWF14(req.getParameter("ESWF14"));
				msgSWFF.setESWF15(req.getParameter("ESWF15"));
				msgSWFF.setESWF16(req.getParameter("ESWF16"));				
				msgSWFF.setESWF17(req.getParameter("ESWF17"));	
				msgSWFF.setESWF18(req.getParameter("ESWF18"));	
				msgSWFF.setESWF19(req.getParameter("ESWF19"));
				msgSWFF.setESWF20(req.getParameter("ESWF20"));
				msgSWFF.setESWF21(req.getParameter("ESWF21"));
				msgSWFF.setESWF22(req.getParameter("ESWF22"));
				msgSWFF.setESWF23(req.getParameter("ESWF23"));
				msgSWFF.setESWF24(req.getParameter("ESWF24"));				
				msgSWFF.setESWF25(req.getParameter("ESWF25"));	
				msgSWFF.setESWF26(req.getParameter("ESWF26"));	
				msgSWFF.setESWF27(req.getParameter("ESWF27"));
				msgSWFF.setESWF28(req.getParameter("ESWF28"));
				msgSWFF.setESWF29(req.getParameter("ESWF29"));
				msgSWFF.setESWF30(req.getParameter("ESWF30"));
				msgSWFF.setESWF31(req.getParameter("ESWF31"));
				msgSWFF.setESWF32(req.getParameter("ESWF32"));				
				msgSWFF.setESWF33(req.getParameter("ESWF33")) ;
				msgSWFF.setESWF34(req.getParameter("ESWF34")) ;
				msgSWFF.setESWF35(req.getParameter("ESWF35")) ;	
				msgSWFF.setESWFFMT(req.getParameter("ESWFFMT")) ;	
				msgSWFF.setESWFSWI(req.getParameter("ESWFSWI")) ;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);


				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF000_swift_free_format_new.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF300_inq_swift_free_format.jsp");
						callPage(LangPath + "ESWF300_inq_swift_free_format.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_LIST_MAINT;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Requests
					case R_LIST_INQ :
						procReqListInquiry(mc, msgUser, req, res, session);
						break;
					case R_LIST_MAINT :
						procReqListMaint(mc, msgUser, req, res, session);
						break;
					case A_LIST :
						procActionList(mc, msgUser, req, res, session);
						break;		
											
					case R_ENTER_MAINT :
				     	procReqEnterMaint(msgUser, req, res, session);
						 break;
					case R_ENTER_INQUIRY :
				     	procReqEnterInquiry(msgUser, req, res, session);
						 break;
						 	 
					case R_SWIFT_MAINT :
						procReqTemplateMaint(mc, msgUser, req, res, session);
						break;
					case R_SWIFT_INQ :
						procReqTemplateInquiry(mc, msgUser, req, res, session);
						break;	
					case R_SWIFT_NEW :
						procReqTemplateNew(mc, msgUser, req, res, session);
						break;
							
					case R_SWIFT_COP :
						procReqCopy(mc, msgUser, req, res, session);
						break;	
					case A_LIMIT :
						procActionTemplate(mc, msgUser, req, res, session);
						break;	
						
						// Actions
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
				}
				finally {
					s.close();
				} 
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	protected  void showERROR(ELEERRMessage m) {
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