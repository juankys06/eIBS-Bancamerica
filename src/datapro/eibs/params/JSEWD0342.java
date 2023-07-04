package datapro.eibs.params;

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

public class JSEWD0342 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	protected static final int R_LIMIT     = 1;
	protected static final int A_LIMIT     = 2;
	
	protected static final int R_DETAIL     = 3;
	protected static final int A_DETAIL     = 4;

	protected static final int R_ENTER_PROC = 5;
	protected static final int A_ENTER_PROC = 6;

	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0342() {
		super();
	}
	/**
	 * This method was created by David Mavilla.
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
	protected void procReqPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0342DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try{
			msgList = (EWD0342DSMessage)mc.getMessageRecord("EWD0342DS");
			
			msgList.setRWDUSR(user.getH01USR());
			msgList.setRWDTYP(""); //All Bloomberg	
			
	 	try {
		 	msgList.setRWDFRC(req.getParameter("Pos"));
		 	}
		catch (Exception ex) {
			msgList.setRWDFRC("0");
		 	}	 				
			
			msgList.send();	
		 	msgList.destroy();
			flexLog("EWD0341DS Message Sent");
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
					msgError =new datapro.eibs.beans.ELEERRMessage();
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

					try {
					    flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);;
					}
					catch (Exception e) {
					    flexLog("Exception calling page " + e);
					}


			} else
				if (newmessage.getFormatName().equals("EWD0342DS")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

						boolean firstTime;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";
						//var for ofac status
						//var for Warning status
						String chkOfac = "";
						String chkWarn = "";
						String Stat = "";
						int indexRow = 0;
					
					while (true) {

							msgList = (EWD0342DSMessage) newmessage;
	
							marker = msgList.getSWDOPE();
	
							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							}
							
							
								myRow = new StringBuffer("<TR>");
								myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:goAction('" + msgList.getSWDBLM() + "', '" + msgList.getSWDTYP() + "')\">"  + msgList.getSWDBLM() + "</A></TD>"); //BLOOMBERG CODE
								myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:goAction('" + msgList.getSWDBLM() + "', '" + msgList.getSWDTYP() + "')\">"  + msgList.getSWDTYP() + "</A></TD>"); //TYPE
								myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:goAction('" + msgList.getSWDBLM() + "', '" + msgList.getSWDTYP() + "')\">"  + msgList.getSWDFLD() + "</A></TD>"); //BLOOMBERG FIELD NAME
								myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:goAction('" + msgList.getSWDBLM() + "', '" + msgList.getSWDTYP() + "')\">"  + msgList.getSWDIBS() + "</A></TD>"); //IBS CODE
								myRow.append("<TD NOWRAP  ALIGN=LEFT><A HREF=\"javascript:goAction('" + msgList.getSWDBLM() + "', '" + msgList.getSWDTYP() + "')\">"  + msgList.getSWDDSC() + "</A></TD>"); //DESCRIPTION																								
								myRow.append("</TR>");
								 
								beanList.addRow(myFlag, myRow.toString());
								indexRow++;
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							newmessage = mc.receiveMessage();
						}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0342Help", beanList);
					ses.setAttribute("userPO", userPO);

						try {
							flexLog("About to call Page: " + LangPath + "EWD0342_bloomberg_list.jsp");
							callPage(LangPath + "EWD0342_bloomberg_list.jsp", req, res);
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
	
protected void procReqDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	EFE0222DSMessage msgBloomberg = null;
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

	String opCode = null;
	String purpose = "";
	
	
	  opCode = "0002";
	
	// Send Initial data
	try
	{
		msgBloomberg = (EFE0222DSMessage)mc.getMessageRecord("EFE0222DS");
	 	msgBloomberg.setH01USERID(user.getH01USR());
	 	msgBloomberg.setH01PROGRM("EFE0222");
	 	msgBloomberg.setH01TIMSYS(getTimeStamp());
	 	msgBloomberg.setH01SCRCOD("01");
	 	
	 	try{
	 	 purpose = req.getParameter("BLOOMCOD");
	 	}
	 	catch (Exception e){
	 	 purpose ="";
	 	}
	 	
	 	if(purpose.trim().equals("")){
	 		opCode = "0001";
	 	}
	 	else {
	 		opCode = "0002";
	 	}
	 	
	 	msgBloomberg.setH01OPECOD(opCode);
	 			
		try {
		 	  msgBloomberg.setE01FLOBLM(req.getParameter("BLOOMCOD"));
		}
		catch (Exception e)	{
	 	  
		}
		
		try {
		 	  msgBloomberg.setE01FLOTYP(req.getParameter("BLOOMTYP"));
		}
		catch (Exception e)	{
	 	  
		}
		
	
	 	msgBloomberg.send();
	 	msgBloomberg.destroy();
	 	flexLog("EFE0222 Message Sent");
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




		if (newmessage.getFormatName().equals("EFE0222DS")) {
			try {
				msgBloomberg = new EFE0222DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBloomberg = (EFE0222DSMessage)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bloom", msgBloomberg);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0222_bloomberg_detail.jsp");
						callPage(LangPath + "EFE0222_bloomberg_detail.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);;
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

protected void procReqEnterProcess(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0222_enter_process.jsp");
			callPage(LangPath + "EFE0222_enter_process.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}




}



protected void procActionDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	EFE0222DSMessage msgBloomberg = null;
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
		flexLog("Send Initial Data");
		msgBloomberg = (EFE0222DSMessage)ses.getAttribute("bloom");
	 	msgBloomberg.setH01USERID(user.getH01USR());
	 	msgBloomberg.setH01PROGRM("EFE0222");
	 	msgBloomberg.setH01TIMSYS(getTimeStamp());
	 	msgBloomberg.setH01SCRCOD("01");
	 	
	 	
	 	if(req.getParameter("DELACTION").equals("D")){
	 		msgBloomberg.setH01OPECOD("0004");
	 	}
	 	else {
	 		msgBloomberg.setH01OPECOD("0005");
	 	}


	 	// all the fields here
	 	java.util.Enumeration enu = msgBloomberg.fieldEnumeration();
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




	 	//msgBloomberg.send();
	 	mc.sendMessage(msgBloomberg);
	 	msgBloomberg.destroy();
	 	flexLog("EFE0222DS Message Sent");
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
					msgBloomberg = new EFE0222DSMessage();
					flexLog("EFE0222DSMessage Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgBloomberg = (EFE0222DSMessage)newmessage;
				userPO.setIdentifier(msgBloomberg.getE01FLOBLM());
				userPO.setHeader1(msgBloomberg.getE01RATDSC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bloom", msgBloomberg);
				ses.setAttribute("userPO", userPO);




				if (IsNotError) {  // There are no errors
						try {
							res.setContentType("text/html");
							printCloseAndRefreshOpener(res.getWriter());
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0222_bloomberg_detail.jsp");
							callPage(LangPath + "EFE0222_bloomberg_detail.jsp", req, res);	
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
protected  void procActionEnterProcess(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0222DSMessage msgBloomberg = null;
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
		msgBloomberg = (EFE0222DSMessage)mc.getMessageRecord("EFE0222DS");
	 	msgBloomberg.setH01USERID(user.getH01USR());
	 	msgBloomberg.setH01PROGRM("EPR0015");
	 	msgBloomberg.setH01TIMSYS(getTimeStamp());
	 	msgBloomberg.setH01SCRCOD("01");
	 	msgBloomberg.setH01OPECOD(req.getParameter("H01OPECOD"));
	 	
	 	try{
	 	 msgBloomberg.setE01LIB(req.getParameter("LIBRARY"));
	 	}
	 	catch (Exception e){
	 	}
	 	
	 	
	 	try{
	 	 msgBloomberg.setE01FILE(req.getParameter("FILE"));
	 	}
	 	catch (Exception e){
	 	}
		
	 	msgBloomberg.send();
	 	msgBloomberg.destroy();
	 	flexLog("EIE011001 Message Sent");
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

		if (newmessage.getFormatName().equals("EFE0222DS")) {
			try {
				msgBloomberg = new EFE0222DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgBloomberg = (EFE0222DSMessage)newmessage;
			
			userPO.setPurpose("M");		
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("process", msgBloomberg);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
								
					try {
						flexLog("About to call Page: " + LangPath + "EFE0222_process_confirm.jsp");
						callPage(LangPath + "EFE0222_process_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EFE0222_enter_process.jsp");
						callPage(LangPath + "EFE0222_enter_process.jsp", req, res);	
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

			int screen = R_LIMIT;

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
					case R_LIMIT :
						procReqPos(mc, msgUser, req, res, session);
						break;
						
					case R_DETAIL : 
						procReqDetail(mc, msgUser, req, res, session);
						break;					
					case A_DETAIL : 
						procActionDetail(mc, msgUser, req, res, session);
						break;	
						
					case R_ENTER_PROC : 
						procReqEnterProcess(mc, msgUser, req, res, session);
						break;

					case A_ENTER_PROC : 
						procActionEnterProcess(mc, msgUser, req, res, session);
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
	protected void showERROR(ELEERRMessage m) {
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