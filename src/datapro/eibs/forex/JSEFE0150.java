package datapro.eibs.forex;

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

 
import datapro.eibs.sockets.*;

public class JSEFE0150 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange fras
	
	protected static final int R_NDF				= 1;
	protected static final int A_NDF				= 4;
	protected static final int A_NDF_MOD			= 6;
	protected static final int A_NDF_TCK			= 65;
	
	protected static final int R_S_NDF			= 7;
	protected static final int A_S_NDF			= 8;	
	
	// entering options

	protected static final int A_ENTER_NDF		= 100;
	protected static final int R_ENTER_NDF		= 90;
	
	// FRASettlement
	protected static final int R_NDF_STL    	= 70;
	protected static final int A_NDF_STL		= 80;
	
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0150() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0150");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}


protected void procActionEnterNDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0150DSMessage msgNDF = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	flexLog("JSEFE0150-->procActionEnterNDF");
	try {
			msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}


	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = null;

	  opCode = "0002";
	
	// Send Initial data
	try
	{
		msgNDF = (EFE0150DSMessage)mc.getMessageRecord("EFE0150DS");
	 	msgNDF.setH01USERID(user.getH01USR());
	 	msgNDF.setH01PROGRM("EFE0150");
	 	msgNDF.setH01TIMSYS(getTimeStamp());
	 	msgNDF.setH01SCRCOD("01");
	 	msgNDF.setH01OPECOD(opCode);
	 	msgNDF.setH01FLGWK2("2");
		
		try {
		 	msgNDF.setE01NDFACC(req.getParameter("E01NDFACC"));
		}
		catch (Exception e)	{
	 	  msgNDF.setE01NDFACC("");
		}

	 	msgNDF.send();
	 	msgNDF.destroy();
	 	flexLog("EFE0150DS Message Sent");
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

		if (newmessage.getFormatName().equals("EFE0150DS")) {
			try {
				msgNDF = new EFE0150DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgNDF = (EFE0150DSMessage)newmessage;

			userPO.setIdentifier(msgNDF.getE01NDFACC());
			userPO.setCusNum(msgNDF.getE01NDFCUN());
			userPO.setCusName(msgNDF.getD01NDFCP1());
			userPO.setCurrency(msgNDF.getE01NDFCCY());
			userPO.setProdCode(msgNDF.getE01NDFPRO());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("ndf", msgNDF);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
					try {
						// TICKET PAGE
						flexLog("About to call Page: " + LangPath + "EFE0150_ndf_acc.jsp");
						callPage(LangPath + "EFE0150_ndf_acc.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000B?SCREEN=100");			}
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
protected void procActionNDFSettlement(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0150DSMessage msgNDF = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	flexLog("JSEFE0150-->procActionNDFSettlement");

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
		msgNDF = (EFE0150DSMessage)ses.getAttribute("ndf");
		msgNDF.setH01USERID(user.getH01USR());
		msgNDF.setH01PROGRM("EFE0150");
		msgNDF.setH01TIMSYS(getTimeStamp());
		msgNDF.setH01SCRCOD("01");
		msgNDF.setH01OPECOD("0005");
		try {
		     msgNDF.setE01NDFNRT(req.getParameter("E01NDFNRT"));
		}
		catch (Exception e) {  flexLog("Error: " + e);}	
		
		flexLog("E01NDFNRT"	+ req.getParameter("E01NDFNRT") );		


		// all the fields here
		java.util.Enumeration enu = msgNDF.fieldEnumeration();
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

		flexLog("msgNDF" + msgNDF );
		//msgNDF.send();
		mc.sendMessage(msgNDF);
		msgNDF.destroy();
		flexLog("EFE0150DS Message Sent");
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
		flexLog("Receive Error Message");
		flexLog("ERROR =" + newmessage);
	  
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
		flexLog("Receive Data");
		flexLog("Data =" + newmessage);

				try {
					msgNDF = new EFE0150DSMessage();
					flexLog("EFE0150DS Message Received");
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}


				msgNDF = (EFE0150DSMessage)newmessage;

				userPO.setIdentifier(msgNDF.getE01NDFACC());
				userPO.setCusNum(msgNDF.getE01NDFCUN());
				userPO.setBank(user.getE01UBK());				
	//			userPO.setHeader1("");
	//			userPO.setHeader2("2");	
	//			userPO.setHeader3("NDF");


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ndf", msgNDF);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
							flexLog("About to call Page: " + LangPath + "EFE0150_ndf_ticket.jsp");
							callPage(LangPath + "EFE0150_ndf_ticket.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0150_ndf_stl.jsp");
							callPage(LangPath + "EFE0150_ndf_stl.jsp", req, res);	
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



protected void procActionNDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EFE0150DSMessage msgNDF = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	flexLog("JSEFE0150-->procActionNDF");

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
	 	msgNDF = (ETR0130DSMessage)ses.getAttribute("ndf");
		msgNDF.setH01USERID(user.getH01USR());
	 	msgNDF.setH01PROGRM("ETR0130");
	 	msgNDF.setH01TIMSYS(getTimeStamp());
	 	msgNDF.setH01SCRCOD("01");
	 	msgNDF.setH01OPECOD("0005");


	 	// all the fields here
	 	java.util.Enumeration enu = msgNDF.fieldEnumeration();
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


	 	//msgNDF.send();
	 	mc.sendMessage(msgNDF);
	 	msgNDF.destroy();
	 	flexLog("ETR0130DS Message Sent");
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
					msgNDF = new ETR0130DSMessage();
					flexLog("ETR0130DS Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}


				msgNDF = (ETR0130DSMessage)newmessage;
				userPO.setIdentifier(msgNDF.getE01WFRACC());
				userPO.setCusNum(msgNDF.getE01WFRCUN());
				userPO.setBank(user.getE01UBK());
				userPO.setHeader1("");
				userPO.setHeader2("2");	
				userPO.setHeader3("NDF");


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ndf", msgNDF);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFE0150_fe_ndf_mod.jsp");
							callPage(LangPath + "ETR0130_fe_ndf_mod.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EFE0150_fe_ndf_basic.jsp");
							callPage(LangPath + "ETR0130_fe_ndf_basic.jsp", req, res);	
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
protected void procActionNDFTck(ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;
	flexLog("JSEFE0150-->procActionNDFTck");
	try {

		msgError = new ELEERRMessage();
		userPO = new UserPos();
		userPO.setOption("NDF");
		userPO.setPurpose("SETTLEMENT");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog("About to call Page: " + LangPath + "EWD0332M_fe_acc.jsp");
		callPage(LangPath + "EWD0332M_fe_acc.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

	protected void procActionNDFMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		flexLog("JSEFE0150-->procActionNDFMod");
		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ETR0130_fe_ndf_conf.jsp");
			callPage(LangPath + "ETR0130_fe_ndf_conf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqNDF(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		flexLog("JSEFE0150-->procReqNDF");
		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ETR0130_fe_ndf_mod.jsp");
			callPage(LangPath + "ETR0130_fe_ndf_mod.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	
	protected void procReqNDFMaint(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		flexLog("JSEFE0150-->procReqNDFMaint");
		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ETR0130_ndf_enter_maint.jsp");
			callPage(LangPath + "ETR0130_ndf_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}	
	
	
	
	             
	protected void procReqNDFSettlement(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0150DSMessage msgNDF = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		flexLog("JSEFE0150-->procReqNDFSettlement");
		try {
				msgError = new ELEERRMessage();
			} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}


		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		String opCode = null;

		  opCode = "0002";
	
		// Send Initial data
		try
		{
			msgNDF = (EFE0150DSMessage)mc.getMessageRecord("EFE0150DS");
			msgNDF.setH01USERID(user.getH01USR());
			msgNDF.setH01PROGRM("EFE0150");
			msgNDF.setH01TIMSYS(getTimeStamp());
	//		msgNDF.setH01SCRCOD("01");
			msgNDF.setH01OPECOD(opCode);
	//		msgNDF.setH01FLGWK2("2");
		
			try {                                     
				msgNDF.setE01NDFACC(req.getParameter("E01NDFACC"));
			}
			catch (Exception e)	{
	                 flexLog("inside exception E01WRACC");		
				try {   
					msgNDF.setE01NDFACC(userPO.getAccOpt());      
				}
            		
				catch (Exception e1)	{ msgNDF.setE01NDFACC(""); }
			}

			msgNDF.send();
			msgNDF.destroy();
			flexLog("EFE0150DS Message Sent");
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

			if (newmessage.getFormatName().equals("EFE0150DS")) {
				try {
					msgNDF = new EFE0150DSMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgNDF = (EFE0150DSMessage)newmessage;

				userPO.setIdentifier(msgNDF.getE01NDFACC());
				userPO.setCusNum(msgNDF.getE01NDFCUN());
				userPO.setCusName(msgNDF.getD01NDFCP1());
				userPO.setCurrency(msgNDF.getE01NDFCCY());
				userPO.setProdCode(msgNDF.getE01NDFPRO());
			
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("ndf", msgNDF);
				if (!msgNDF.getE01NDFACC().equals(""))	
					userPO.setAccOpt(msgNDF.getE01NDFACC());
				//	userPO.setE01NDFACC(msgNDF.getE01NDFACC());
				ses.setAttribute("userPO", userPO);			 
				if (IsNotError) {  // There are no errors
						try {					
						
							flexLog("About to call Page: " + LangPath + "EFE0150_ndf_stl.jsp");
							callPage(LangPath + "EFE0150_ndf_stl.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}					
		 
				}
				else {  // There are errors
					        flexLog("About to call Page: " + LangPath + "EWD0332M_fe_acc.jsp");
					        callPage(LangPath + "EWD0332M_fe_acc.jsp", req, res);					 
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
	
	
	 
	
protected void procReqSwift100NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	flexLog("JSEFE0150-->procReqSwift100NDF");

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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1(userPO.getHeader2());
		
		try {
		 	  msgSwift.setE01SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE01SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFBNK("0");
		}

		try {
		 	  msgSwift.setE01SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
		}

		try {
		 	  msgSwift.setE01SWFAMT(userPO.getHeader1());
		}
		catch (Exception e)	{
	 	  msgSwift.setE01SWFCUN("0");
		}

	 	msgSwift.send();
	 	msgSwift.destroy();
	 	flexLog("ESWF10001 Message Sent");
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


		if (newmessage.getFormatName().equals("ESWF10001")) {
			try {
				msgSwift = new ESWF10001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10001Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF100_fe_fr_format.jsp");
						callPage(LangPath + "ESWF100_fe_fr_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "ETR0130_fe_ndf_mod.jsp");
						callPage(LangPath + "ETR0130_fe_ndf_mod.jsp", req, res);
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

protected void procActionSwift100NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	flexLog("JSEFE0150-->procActionSwift100NDF");	
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
	 	msgSwift = (ESWF10001Message)ses.getAttribute("swift");
		msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE("0005");
	 	msgSwift.setH01WK1(userPO.getHeader2());

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
	 	flexLog("ESWF10001 Message Sent");
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
					msgSwift = new ESWF10001Message();
					flexLog("ESWF10001 Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgSwift = (ESWF10001Message)newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);


				if (IsNotError) {  // There are no errors
						try {
							    flexLog("About to call Page: " + LangPath + "ETR0130_fe_ndf_mod.jsp");
								callPage(LangPath + "ETR0130_fe_ndf_mod.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESWF100_fe_fr_format.jsp");
							callPage(LangPath + "ESWF100_fe_fr_format.jsp", req, res);	
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

	
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;
	flexLog("JSEFE0150-->service");
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

		int screen = A_NDF;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opening Socket Connection");
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
				// BEGIN Fex
				// Action
				case A_NDF :
					procActionNDF(mc, msgUser, req, res, session);
					break;	
									
				case R_NDF :
				     procReqNDF(msgUser, req, res, session);
					 break;		
				case A_NDF_MOD :
				     procActionNDFMod(msgUser, req, res, session);
					 break;		
				case A_NDF_TCK :
					 procActionNDFTck(msgUser, req, res, session);
					 break;	
					 													
									
			// END CD

				// BEGIN Entering

				// Action 
				case A_ENTER_NDF : 
					procActionEnterNDF(mc, msgUser, req, res, session);
					break;	
				case R_ENTER_NDF :
					 procReqNDFMaint(msgUser, req, res, session);
					 break;						
				case R_NDF_STL :				 
				     procReqNDFSettlement(mc, msgUser, req, res, session);
					 break;	
				case A_NDF_STL :				 
					 procActionNDFSettlement(mc, msgUser, req, res, session);
					 break;						 
				case R_S_NDF : 
					procReqSwift100NDF(mc, msgUser, req, res, session);
					break;					
				case A_S_NDF : 
					procActionSwift100NDF(mc, msgUser, req, res, session);
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