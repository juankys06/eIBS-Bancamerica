package datapro.eibs.products;

/**
 * Insert the type's description here.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class JSEDL1111 extends datapro.eibs.master.SuperServlet {

	protected static final int R_SCHEME_LN		= 1;
	protected static final int A_SCHEME_LN		= 2;
	protected static final int R_SCHEME_LN_LIST	= 3;
	protected static final int A_SCHEME_LN_APP	= 4;
	protected static final int A_SCHEME_LN_ACP	= 5;
	protected static final int A_SCHEME_LN_APD	= 6;
	protected static final int A_SCHEME_LN_ACD	= 7;
	
	// entering options

	protected static final int R_ENTER_SCHEME_LN	= 100;
	protected static final int A_ENTER_SCHEME_LN	= 200;

	protected static final int R_ENTER_SCHEME_LN_APP= 600;
	protected static final int A_ENTER_SCHEME_LN_APP= 700;

	protected static final int R_ENTER_SCHEME_LN_ACP= 800;
	protected static final int A_ENTER_SCHEME_LN_ACP= 900;



	// enquiry options
	
	protected static final int R_ENTER_INQ = 300;
	protected static final int A_ENTER_INQ = 400;
	protected static final int A_INQ_SCHEME= 500;	

	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL1111() {
	super();
}
/**
 * This method was created by Author.
 */
public void destroy() {

	flexLog("free resources used by JSEDL1111");
	
}
/**
 * This method was created by Author.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}


/**
 * This method was created in VisualAge.
 */
protected void procActionEnterSchemeLN(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
	InqOption = userPO.getIdentifier();
			
	try {
			
		try {
			userPO.setHeader6(req.getParameter("E01DESCTO"));
		} catch (Exception e) {
			userPO.setHeader6(" ");
		}

		try {
			userPO.setHeader7(req.getParameter("E01CNOMID"));
		} catch (Exception e) {
			userPO.setHeader7(" ");
		}

		try {
			userPO.setHeader8(req.getParameter("E01ORDLST"));
		} catch (Exception e) {
			userPO.setHeader8(" ");
		}

		try {
			userPO.setHeader9(req.getParameter("E01NARRAT"));
		} catch (Exception e) {
			userPO.setHeader9(" ");
		}

		try {
			userPO.setHeader10(req.getParameter("E01OFFOPC"));
		} catch (Exception e) {
			userPO.setHeader10(" ");
		}

		try {
			userPO.setHeader11(req.getParameter("E01OFFGLN"));
		} catch (Exception e) {
			userPO.setHeader11(" ");
		}

		try {
			userPO.setHeader12(req.getParameter("E01OFFCON"));
		} catch (Exception e) {
			userPO.setHeader12(" ");
		}

		try {
			userPO.setHeader13(req.getParameter("E01OFFBNK"));
		} catch (Exception e) {
			userPO.setHeader13(" ");
		}

		try {
			userPO.setHeader14(req.getParameter("E01OFFBRN"));
		} catch (Exception e) {
			userPO.setHeader14(" ");
		}

		try {
			userPO.setHeader15(req.getParameter("E01OFFCCY"));
		} catch (Exception e) {
			userPO.setHeader15(" ");
		}

		try {
			userPO.setHeader16(req.getParameter("E01OFFACC"));
		} catch (Exception e) {
			userPO.setHeader16(" ");
		}


		if (userPO.getIdentifier() == null) {
			userPO.setIdentifier(InqOption);
			userPO.setHeader23(InqOption);
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemeLNList(mc, user, req, res, ses);

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
protected void procActionEnterSchemeLNApp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
			
	try {

		try {
			userPO.setHeader6(req.getParameter("E01DESCTO"));
		} catch (Exception e) {
			userPO.setHeader6(" ");
		}

		try {
			userPO.setHeader7(req.getParameter("E01CNOMID"));
		} catch (Exception e) {
			userPO.setHeader7(" ");
		}

		try {
			userPO.setHeader8(req.getParameter("E01ORDLST"));
		} catch (Exception e) {
			userPO.setHeader8(" ");
		}

		try {
			userPO.setHeader9(req.getParameter("E01NARRAT"));
		} catch (Exception e) {
			userPO.setHeader9(" ");
		}

		try {
			userPO.setHeader10(req.getParameter("E01OFFOPC"));
		} catch (Exception e) {
			userPO.setHeader10(" ");
		}

		try {
			userPO.setHeader11(req.getParameter("E01OFFGLN"));
		} catch (Exception e) {
			userPO.setHeader11(" ");
		}

		try {
			userPO.setHeader12(req.getParameter("E01OFFCON"));
		} catch (Exception e) {
			userPO.setHeader12(" ");
		}

		try {
			userPO.setHeader13(req.getParameter("E01OFFBNK"));
		} catch (Exception e) {
			userPO.setHeader13(" ");
		}

		try {
			userPO.setHeader14(req.getParameter("E01OFFBRN"));
		} catch (Exception e) {
			userPO.setHeader14(" ");
		}

		try {
			userPO.setHeader15(req.getParameter("E01OFFCCY"));
		} catch (Exception e) {
			userPO.setHeader15(" ");
		}

		try {
			userPO.setHeader16(req.getParameter("E01OFFACC"));
		} catch (Exception e) {
			userPO.setHeader16(" ");
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemeLNListApp(mc, user, req, res, ses);

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
protected void procActionEnterSchemeLNAcp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
			
	try {

		try {
			userPO.setHeader6(req.getParameter("E01DESCTO"));
		} catch (Exception e) {
			userPO.setHeader6(" ");
		}

		try {
			userPO.setHeader7(req.getParameter("E01CNOMID"));
		} catch (Exception e) {
			userPO.setHeader7(" ");
		}

		try {
			userPO.setHeader8(req.getParameter("E01ORDLST"));
		} catch (Exception e) {
			userPO.setHeader8(" ");
		}

		try {
			userPO.setHeader9(req.getParameter("E01NARRAT"));
		} catch (Exception e) {
			userPO.setHeader9(" ");
		}

		try {
			userPO.setHeader10(req.getParameter("E01OFFOPC"));
		} catch (Exception e) {
			userPO.setHeader10(" ");
		}

		try {
			userPO.setHeader11(req.getParameter("E01OFFGLN"));
		} catch (Exception e) {
			userPO.setHeader11(" ");
		}

		try {
			userPO.setHeader12(req.getParameter("E01OFFCON"));
		} catch (Exception e) {
			userPO.setHeader12(" ");
		}

		try {
			userPO.setHeader13(req.getParameter("E01OFFBNK"));
		} catch (Exception e) {
			userPO.setHeader13(" ");
		}

		try {
			userPO.setHeader14(req.getParameter("E01OFFBRN"));
		} catch (Exception e) {
			userPO.setHeader14(" ");
		}

		try {
			userPO.setHeader15(req.getParameter("E01OFFCCY"));
		} catch (Exception e) {
			userPO.setHeader15(" ");
		}

		try {
			userPO.setHeader16(req.getParameter("E01OFFACC"));
		} catch (Exception e) {
			userPO.setHeader16(" ");
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemeLNListAcp(mc, user, req, res, ses);

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
protected void procActionInqScheme(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
	InqOption = userPO.getIdentifier();
			
	try {
		try {
			userPO.setIdentifier(req.getParameter("E01DEAACC"));
			userPO.setHeader23(null);
		}
		catch (Exception e)	{
		}

		if (userPO.getIdentifier() == null) {
			userPO.setIdentifier(InqOption);
			userPO.setHeader23(InqOption);
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemeList(mc, user, req, res, ses);

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
protected void procActionSchemeLN(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EDL111101Message msgLN = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgLN = new datapro.eibs.beans.EDL111101Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("lnScheme", msgLN);

	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	
	option = req.getParameter("OPT");
	if (option.equals("1")) {
		opcode="0001"; //new
		msgLN = (EDL111101Message)ses.getAttribute("msgLN");
	}
	else {
		row = Integer.parseInt(req.getParameter("ROW"));
		extList = (JBObjList)ses.getAttribute("extList");
		extList.setCurrentRow(row);
		msgLN = (EDL111101Message) extList.getRecord();
		if (option.equals("2")) { //maintenance
		  opcode="0002";		  
		} 
		else if (option.equals("4")) opcode="0004";  //delete
	}	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL1111");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01OPECOD(opcode);
		
		// all the fields here
	 	java.util.Enumeration enu = msgLN.fieldEnumeration();
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
	 	//msgLN.send();
	 	mc.sendMessage(msgLN);
	 	msgLN.destroy();

	 	flexLog("EDL111101 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL111101")) {
			try {
				msgLN = new datapro.eibs.beans.EDL111101Message();
				flexLog("EDL111101 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgLN = (EDL111101Message)newmessage;
			
			userPO.setIdentifier(msgLN.getE01DEAACC()); 

			userPO.setHeader7(msgLN.getE01CNOMID());
			userPO.setHeader8(msgLN.getE01ORDLST());
			userPO.setHeader9(msgLN.getE01NARRAT());
			userPO.setHeader10(msgLN.getE01OFFOPC());
			userPO.setHeader11(msgLN.getE01OFFGLN());
			userPO.setHeader12(msgLN.getE01OFFCON());
			userPO.setHeader13(msgLN.getE01OFFBNK());																														
			userPO.setHeader14(msgLN.getE01OFFBRN());
			userPO.setHeader15(msgLN.getE01OFFCCY());						
			userPO.setHeader16(msgLN.getE01OFFACC());
			userPO.setHeader17(msgLN.getE01BTHUBK());					
			userPO.setHeader18(msgLN.getE01BTHUBR());
			userPO.setHeader19(msgLN.getE01BTHDIB());
			userPO.setHeader20(msgLN.getE01CNODSC());						
			userPO.setHeader21(msgLN.getE01GLMDSC());
				


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
			  
			  	if (option.equals("1")) ses.removeAttribute("lnScheme");
				res.setContentType("text/html");
				PrintWriter  out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Close</TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				out.println("		top.opener.window.location.href='" + super.webAppPath + "/servlet/datapro.eibs.products.JSEDL1111?SCREEN=3'");
				out.println("		top.close();");
				out.println("</SCRIPT>");
				out.println("<P>Close it!!!</P>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			  
			}
			else {  // There are errors
				if (option.equals("1")) { //new
					ses.setAttribute("lnScheme", msgLN);
					try {
						flexLog("About to call Page: " + LangPath + "EDL1111_ln_new_sch.jsp");
						callPage(LangPath + "EDL1111_ln_new_sch.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (option.equals("2")) { //maintenance					
					
		  			extList.setRecord(msgLN,row);  // Certificate Number
					ses.setAttribute("extList", extList);
					try {
						flexLog("About to call Page: " + LangPath + "EDL1111_ln_maint_sch.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EDL1111_ln_maint_sch.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} 
				else if (option.equals("4")) {  //delete 
					try {
						flexLog("About to call Page: " + LangPath + "EDL1111_ln_delete_sch.jsp?ROW"+ row);
						res.sendRedirect(super.srctx + LangPath + "EDL1111_ln_delete_sch.jsp?ROW"+ row);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
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
protected void procActionSchemeLNApp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EDL111101Message msgLN = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgLN = new datapro.eibs.beans.EDL111101Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("lnScheme", msgLN);

		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0035");

		   try {
			   userPO.setHeader7(req.getParameter("E01CNOMID"));
		   } catch (Exception e) {
			   userPO.setHeader7(" ");
		   }
			try {		
			msgLN.setE01CNOMID(userPO.getHeader7());
			} catch (Exception e) {
				msgLN.setE01CNOMID(" ");
			}

		//msgLN.send();
		mc.sendMessage(msgLN);
		msgLN.destroy();

		flexLog("EDL111101 Message Sent");
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
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
	
	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_conf_sch.jsp");
		callPage(LangPath + "EDL1111_ln_conf_sch.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}



}



/**
 * This method was created in VisualAge.
 */
protected void procActionSchemeLNAppDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EDL111101Message msgLN = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgLN = new datapro.eibs.beans.EDL111101Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("lnScheme", msgLN);

		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0135");

		   try {
			   userPO.setHeader7(req.getParameter("E01CNOMID"));
		   } catch (Exception e) {
			   userPO.setHeader7(" ");
		   }
			try {		
			msgLN.setE01CNOMID(userPO.getHeader7());
			} catch (Exception e) {
				msgLN.setE01CNOMID(" ");
			}

		//msgLN.send();
		mc.sendMessage(msgLN);
		msgLN.destroy();

		flexLog("EDL111101 Message Sent");
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
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
	
	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_app.jsp");
		callPage(LangPath + "EDL1111_ln_enter_sch_app.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}



}




/**
 * This method was created in VisualAge.
 */
protected void procActionSchemeLNAcp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EDL111101Message msgLN = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgLN = new datapro.eibs.beans.EDL111101Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("lnScheme", msgLN);

		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0055");

		   try {
			   userPO.setHeader7(req.getParameter("E01CNOMID"));
		   } catch (Exception e) {
			   userPO.setHeader7(" ");
		   }
			try {		
			msgLN.setE01CNOMID(userPO.getHeader7());
			} catch (Exception e) {
				msgLN.setE01CNOMID(" ");
			}

		//msgLN.send();
		mc.sendMessage(msgLN);
		msgLN.destroy();

		flexLog("EDL111101 Message Sent");
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
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
	
	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_conf_sch.jsp");
		callPage(LangPath + "EDL1111_ln_conf_sch.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}



}


/**
 * This method was created in VisualAge.
 */
protected void procActionSchemeLNAcpDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

MessageRecord newmessage = null;
EDL111101Message msgLN = null;
JBObjList extList = null;
ELEERRMessage msgError = null;
UserPos	userPO = null;	
boolean IsNotError = false;
String opcode = "";
String option = "";
int row =0;
    
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
/////
  msgLN = new datapro.eibs.beans.EDL111101Message();
  userPO = new datapro.eibs.beans.UserPos();
  userPO.setOption("cd_EXTRA");
  userPO.setPurpose("MAINTENANCE");
  ses.setAttribute("error", msgError);
  ses.setAttribute("userPO", userPO);
  ses.setAttribute("lnScheme", msgLN);

		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0155");

		   try {
			   userPO.setHeader7(req.getParameter("E01CNOMID"));
		   } catch (Exception e) {
			   userPO.setHeader7(" ");
		   }
			try {		
			msgLN.setE01CNOMID(userPO.getHeader7());
			} catch (Exception e) {
				msgLN.setE01CNOMID(" ");
			}

		//msgLN.send();
		mc.sendMessage(msgLN);
		msgLN.destroy();

		flexLog("EDL111101 Message Sent");
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
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	
	
	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_acp.jsp");
		callPage(LangPath + "EDL1111_ln_enter_sch_acp.jsp", req, res);
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}



}



/**
 * This method was created in VisualAge.
 */
protected void procReqEnterSchemeLN(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
    EDL111101Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgLN = new datapro.eibs.beans.EDL111101Message();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("cd_EXTRA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnScheme", msgLN);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch.jsp");
		callPage(LangPath + "EDL1111_ln_enter_sch.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqEnterSchemeLNApp(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
	EDL111101Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgLN = new datapro.eibs.beans.EDL111101Message();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("cd_EXTRA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnScheme", msgLN);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex);  
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_app.jsp");
		callPage(LangPath + "EDL1111_ln_enter_sch_app.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqEnterSchemeLNAcp(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
	EDL111101Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgLN = new datapro.eibs.beans.EDL111101Message();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("cd_EXTRA");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnScheme", msgLN);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex);  
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_acp.jsp");
		callPage(LangPath + "EDL1111_ln_enter_sch_acp.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procReqSchemeLNList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL111101Message msgLN = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	int startPos = 0;
		
	// Send Initial data
	try
	{
		msgLN = (EDL111101Message)mc.getMessageRecord("EDL111101");
	 	msgLN.setH01USERID(user.getH01USR());
	 	msgLN.setH01PROGRM("EDL1111");
	 	msgLN.setH01TIMSYS(getTimeStamp());
	 	msgLN.setH01OPECOD("0015");
	 	// Get Parameters here		


		try	{ 	
	 		msgLN.setE01DEAACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgLN.setH01PROGRM(userPO.getHeader6());
		msgLN.setE01CNOMID(userPO.getHeader7());
//		msgLN.setE01ORDLST(userPO.getHeader8());
		msgLN.setE01NARRAT(userPO.getHeader9());
		msgLN.setE01OFFOPC(userPO.getHeader10());
		msgLN.setE01OFFGLN(userPO.getHeader11());
		msgLN.setE01OFFCON(userPO.getHeader12());
		msgLN.setE01OFFBNK(userPO.getHeader13());
		msgLN.setE01OFFBRN(userPO.getHeader14());
		msgLN.setE01OFFCCY(userPO.getHeader15());
		msgLN.setE01OFFACC(userPO.getHeader16());


		try {

			try {
				msgLN.setE01NUMREC(req.getParameter("Pos"));
				startPos = Integer.parseInt(req.getParameter("Pos"));
			} catch (Exception e) {
				flexLog("E01NUMPOS");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}


		
		msgLN.send();	
		msgLN.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch.jsp");
					callPage(LangPath + "EDL1111_ln_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EDL111101")) {

			String marker = "";
		
			boolean myFirstRow = false;
			
			while (true) {

				msgLN = (EDL111101Message)newmessage;

				userPO.setHeader6(msgLN.getH01PROGRM());
				userPO.setHeader7(msgLN.getE01CNOMID());
				userPO.setHeader8(msgLN.getE01ORDLST());
				userPO.setHeader9(msgLN.getE01NARRAT());
				userPO.setHeader10(msgLN.getE01OFFOPC());
				userPO.setHeader11(msgLN.getE01OFFGLN());
				userPO.setHeader12(msgLN.getE01OFFCON());
				userPO.setHeader13(msgLN.getE01OFFBNK());																														
				userPO.setHeader14(msgLN.getE01OFFBRN());
				userPO.setHeader15(msgLN.getE01OFFCCY());						
				userPO.setHeader16(msgLN.getE01OFFACC());
				userPO.setHeader17(msgLN.getE01BTHUBK());					
				userPO.setHeader18(msgLN.getE01BTHUBR());
				userPO.setHeader19(msgLN.getE01BTHDIB());
				userPO.setHeader20(msgLN.getE01CNODSC());						
				userPO.setHeader21(msgLN.getE01GLMDSC());
				
				marker = msgLN.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgLN);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusName(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}


			try {
				userPO.setIdentifier(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}

			try {			
			userPO.setHeader22(msgLN.getE01TOTAMT());
			}
			catch (Exception e)	{
			}


			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
				 	flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch.jsp");
				 	callPage(LangPath + "EDL1111_ln_list_sch.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
//						flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_inq_sch.jsp");
//						callPage(LangPath + "EDL1111_ln_list_inq_sch.jsp", req, res);					
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch.jsp");
					callPage(LangPath + "EDL1111_ln_list_sch.jsp", req, res);					


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
protected void procReqSchemeLNListApp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL111101Message msgLN = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	int startPos = 0;
		
	// Send Initial data
	try
	{
		msgLN = (EDL111101Message)mc.getMessageRecord("EDL111101");
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0025");
		// Get Parameters here		


		try	{ 	
			msgLN.setE01DEAACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgLN.setE01CNOMID(userPO.getHeader7());
		msgLN.setH01PROGRM(userPO.getHeader6());

		try {

			try {
				msgLN.setE01NUMREC(req.getParameter("Pos"));
				startPos = Integer.parseInt(req.getParameter("Pos"));
			} catch (Exception e) {
				flexLog("E01NUMPOS");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}


		
		msgLN.send();	
		msgLN.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_app.jsp");
					callPage(LangPath + "EDL1111_ln_enter_sch_app.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EDL111101")) {

			String marker = "";
		
			boolean myFirstRow = false;
			
			while (true) {

				msgLN = (EDL111101Message)newmessage;

				userPO.setHeader6(msgLN.getH01PROGRM());
				userPO.setHeader7(msgLN.getE01CNOMID());
				userPO.setHeader9(msgLN.getE01NARRAT());
				userPO.setHeader10(msgLN.getE01OFFOPC());
				userPO.setHeader11(msgLN.getE01OFFGLN());
				userPO.setHeader12(msgLN.getE01OFFCON());
				userPO.setHeader13(msgLN.getE01OFFBNK());																														
				userPO.setHeader14(msgLN.getE01OFFBRN());
				userPO.setHeader15(msgLN.getE01OFFCCY());						
				userPO.setHeader16(msgLN.getE01OFFACC());
				userPO.setHeader17(msgLN.getE01BTHUBK());					
				userPO.setHeader18(msgLN.getE01BTHUBR());
				userPO.setHeader19(msgLN.getE01BTHDIB());
				userPO.setHeader20(msgLN.getE01CNODSC());						
				userPO.setHeader21(msgLN.getE01GLMDSC());
				
				marker = msgLN.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgLN);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusName(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}


			try {
				userPO.setIdentifier(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}

			try {			
			userPO.setHeader22(msgLN.getE01TOTAMT());
			}
			catch (Exception e)	{
			}


			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch_app.jsp");
					callPage(LangPath + "EDL1111_ln_list_sch_app.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch_app.jsp");
					callPage(LangPath + "EDL1111_ln_list_sch_app.jsp", req, res);					


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
protected void procReqSchemeLNListAcp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL111101Message msgLN = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	int startPos = 0;
		
	// Send Initial data
	try
	{
		msgLN = (EDL111101Message)mc.getMessageRecord("EDL111101");
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0045");
		// Get Parameters here		


		try	{ 	
			msgLN.setE01DEAACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgLN.setE01CNOMID(userPO.getHeader7());
		msgLN.setH01PROGRM(userPO.getHeader6());
		
		try {

			try {
				msgLN.setE01NUMREC(req.getParameter("Pos"));
				startPos = Integer.parseInt(req.getParameter("Pos"));
			} catch (Exception e) {
				flexLog("E01NUMPOS");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}
	
		msgLN.send();	
		msgLN.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch_acp.jsp");
					callPage(LangPath + "EDL1111_ln_enter_sch_acp.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EDL111101")) {

			String marker = "";
		
			boolean myFirstRow = false;
			
			while (true) {

				msgLN = (EDL111101Message)newmessage;

				userPO.setHeader6(msgLN.getH01PROGRM());
				userPO.setHeader7(msgLN.getE01CNOMID());
				userPO.setHeader9(msgLN.getE01NARRAT());
				userPO.setHeader10(msgLN.getE01OFFOPC());
				userPO.setHeader11(msgLN.getE01OFFGLN());
				userPO.setHeader12(msgLN.getE01OFFCON());
				userPO.setHeader13(msgLN.getE01OFFBNK());																														
				userPO.setHeader14(msgLN.getE01OFFBRN());
				userPO.setHeader15(msgLN.getE01OFFCCY());						
				userPO.setHeader16(msgLN.getE01OFFACC());
				userPO.setHeader17(msgLN.getE01BTHUBK());					
				userPO.setHeader18(msgLN.getE01BTHUBR());
				userPO.setHeader19(msgLN.getE01BTHDIB());
				userPO.setHeader20(msgLN.getE01CNODSC());						
				userPO.setHeader21(msgLN.getE01GLMDSC());
				
				marker = msgLN.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgLN);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusName(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}


			try {
				userPO.setIdentifier(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}

			try {			
			userPO.setHeader22(msgLN.getE01TOTAMT());
			}
			catch (Exception e)	{
			}


			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);

		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch_acp.jsp");
					callPage(LangPath + "EDL1111_ln_list_sch_acp.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_sch_acp.jsp");
					callPage(LangPath + "EDL1111_ln_list_sch_acp.jsp", req, res);					


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
protected void procReqSchemeList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL111101Message msgLN = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgLN = (EDL111101Message)mc.getMessageRecord("EDL111101");
		msgLN.setH01USERID(user.getH01USR());
		msgLN.setH01PROGRM("EDL1111");
		msgLN.setH01TIMSYS(getTimeStamp());
		msgLN.setH01OPECOD("0015");
		// Get Parameters here		
		try	{ 	
			msgLN.setE01DEAACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgLN.send();	
		msgLN.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_enter_sch.jsp");
					callPage(LangPath + "EDL1111_ln_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EDL111101")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgLN = (EDL111101Message)newmessage;
				marker = msgLN.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgLN);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusName(msgLN.getE01CUSNA1());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_inq.jsp");
					callPage(LangPath + "EDL1111_ln_list_inq.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
//						flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_inq_sch.jsp");
//						callPage(LangPath + "EDL1111_ln_list_inq_sch.jsp", req, res);					
					flexLog("About to call Page: " + LangPath + "EDL1111_ln_list_inq.jsp");
					callPage(LangPath + "EDL1111_ln_list_inq.jsp", req, res);					


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



protected void procReqEnterInq(
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;
	EDL010501Message msgCP = null;

	try {

		msgError = new ELEERRMessage();
		userPO = new UserPos();
		userPO.setOption("CP");
		userPO.setPurpose("INQUIRY");
		msgCP = new EDL010501Message();
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("cdMant", msgCP);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL1111_cp_enter_inquiry.jsp");
		callPage(LangPath + "EDL1111_cp_enter_inquiry.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionEnterInquiry(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL010501Message msgCP = null;
	ELEERRMessage msgError = null;
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
		msgCP = (EDL010501Message) mc.getMessageRecord("EDL010501");
		msgCP.setH01USERID(user.getH01USR());
		msgCP.setH01PROGRM("EDL0105");
		msgCP.setH01TIMSYS(getTimeStamp());
		msgCP.setH01OPECOD("0004");
		try {
			msgCP.setE01DEAACC(req.getParameter("E01DEAACC"));
		} catch (Exception e) {
			msgCP.setE01DEAACC(userPO.getIdentifier());
		}

		msgCP.send();
		msgCP.destroy();
		flexLog("EDL010501 Message Sent");
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

		if (newmessage.getFormatName().equals("EDL010501")) {
			try {
				msgCP = new EDL010501Message();
				flexLog("EDL010501 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgCP = (EDL010501Message) newmessage;
				

			userPO.setIdentifier(msgCP.getE01DEAACC());
			userPO.setProdCode(msgCP.getE01DEAPRO());
			userPO.setCusNum(msgCP.getE01DEACUN());
			userPO.setCusName(msgCP.getE01CUSNA1());
			userPO.setCurrency(msgCP.getE01DEACCY());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("cdMant", msgCP);

			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_cp_inq_maint.jsp");
					callPage(LangPath + "EDL1111_cp_inq_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL1111_cp_enter_inquiry.jsp");
					callPage(LangPath + "EDL1111_cp_enter_inquiry.jsp", req, res);
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

		int screen = R_ENTER_SCHEME_LN;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Open Socket Connection ");
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
				case R_SCHEME_LN_LIST :
					procReqSchemeLNList(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_LN :
					procActionSchemeLN(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_LN_APP :
					procActionSchemeLNApp(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_LN_APD :
					procActionSchemeLNAppDel(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_LN_ACP :
					procActionSchemeLNAcp(mc, msgUser, req, res, session);
					break;			
				case A_SCHEME_LN_ACD :
					procActionSchemeLNAcpDel(mc, msgUser, req, res, session);
					break;			
				case R_ENTER_SCHEME_LN : 
					procReqEnterSchemeLN(msgUser, req, res, session);
					break;
				case R_ENTER_SCHEME_LN_APP : 
					procReqEnterSchemeLNApp(msgUser, req, res, session);
					break;
				case R_ENTER_SCHEME_LN_ACP : 
					procReqEnterSchemeLNAcp(msgUser, req, res, session);
					break;
				case A_ENTER_SCHEME_LN : 
					procActionEnterSchemeLN(mc, msgUser, req, res, session);
					break;
				case A_ENTER_SCHEME_LN_APP : 
					procActionEnterSchemeLNApp(mc, msgUser, req, res, session);
					break;
				case A_ENTER_SCHEME_LN_ACP : 
					procActionEnterSchemeLNAcp(mc, msgUser, req, res, session);
					break;
				case A_INQ_SCHEME : 
					procActionInqScheme(mc, msgUser, req, res, session);
					break;
				case R_ENTER_INQ :
					procReqEnterInq( msgUser, req, res, session);
					break;
				case A_ENTER_INQ :
					procActionEnterInquiry(mc, msgUser, req, res, session);
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