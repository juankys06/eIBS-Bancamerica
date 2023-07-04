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

public class JSEFE0140A extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange 


	protected static final int A_SPOT				= 2;
	protected static final int A_FAST_SPOT		    = 10;
	protected static final int A_OPTION			     = 12;
	protected static final int A_FORWARD			= 14;
	protected static final int A_SWAP				= 16;
	protected static final int A_NDF				= 18;	
	protected static final int A_MAINT_DEAL       = 40;
	protected static final int A_FRA_DEAL		    = 80;
	
	protected static final int A_SPOT_MOD			= 20;
	protected static final int A_FORWARD_MOD		= 22;
	protected static final int A_OPTION_MOD		= 24;
	protected static final int A_NDF_MOD			= 26;
	protected static final int A_SWAP_MOD			= 28;
	
	protected static final int R_SPOT				= 1;
	protected static final int R_FORWARD			= 3;
	protected static final int R_NDF				= 5;
	protected static final int R_OPTION			= 7;
	protected static final int R_SWAP				= 9;
	protected static final int R_MAINT_DEAL		= 100;
	
	protected static final int R_S_SPOT			= 31;
	protected static final int R_S_FORWARD		= 33;
	protected static final int R_S_NDF			= 35;	
	protected static final int R_S_OPT			= 37;
	protected static final int R_S_SWAP			= 39;
	
	protected static final int A_S_SPOT			= 32;
	protected static final int A_S_FORWARD		= 34;
	protected static final int A_S_NDF			= 36;	
	protected static final int A_S_OPT			= 38;
	protected static final int A_S_SWAP			= 42;
	
	protected static final int R_SC_SPOT			= 41;
	protected static final int R_SC_FORWARD		= 43;
	protected static final int R_SC_NDF			= 45;	
	protected static final int R_SC_OPT			= 47;
	protected static final int R_SC_SWAP			= 49;
	protected static final int R_SC_SWAP_1		= 59;
	protected static final int A_ENTER_BLK		= 2000;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0140A() {
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

protected void procActionMaintDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0140DSMessage msgFex = null;
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
		msgFex = (EFE0140DSMessage)mc.getMessageRecord("EFE0140DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0140");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0002");

		try {
		 	msgFex.setE01WKFACC(req.getParameter("E01WKFACC"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WKFACC("0");
		}

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0140DS Message Sent");
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

		if (newmessage.getFormatName().equals("EFE0140DS")) {
			try {
				msgFex = new EFE0140DSMessage(); 
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0140DSMessage)newmessage;
			userPO.setIdentifier(msgFex.getE01WKFACC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);
 
			if (IsNotError) {  // There are no errors
				if(msgFex.getE01WKFTYP().equals("OPTI")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_opt.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_opt.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("SPOT")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_sf.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_sf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("FWRD")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_forward.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_forward.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("SWAP")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_swap.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_swap.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01WKFTYP().equals("NDF")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_ndf.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_ndf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100");			}
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		}
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}




	protected void procReqSpotMod(ESS0030DSMessage user,
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
			flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_sf.jsp");
			callPage(LangPath + "EFE0140A_fe_basic_sf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqForwardMod(ESS0030DSMessage user,
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
			flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_forward.jsp");
			callPage(LangPath + "EFE0140A_fe_basic_forward.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqNDMod(ESS0030DSMessage user,
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
			flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_ndf.jsp");
			callPage(LangPath + "EFE0140A_fe_basic_ndf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

	protected void procReqSwapMod(ESS0030DSMessage user,
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
			flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_swap.jsp");
			callPage(LangPath + "EFE0140A_fe_basic_swap.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	
	protected void procReqOptionMod(ESS0030DSMessage user,
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
			flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_opt.jsp");
			callPage(LangPath + "EFE0140A_fe_basic_opt.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

protected void procReqSwift100SPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
						flexLog("About to call Page: " + LangPath + "ESWF100A_fe_sf_format.jsp");
						callPage(LangPath + "ESWF100A_fe_sf_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_sf_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_sf_mod.jsp", req, res);
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

protected void procReqSwift100FWR(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
						flexLog("About to call Page: " + LangPath + "ESWF100A_fe_fw_format.jsp");
						callPage(LangPath + "ESWF100A_fe_fw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_forward_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_forward_mod.jsp", req, res);
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

protected void procReqSwift100NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
						flexLog("About to call Page: " + LangPath + "ESWF100A_fe_nd_format.jsp");
						callPage(LangPath + "ESWF100A_fe_nd_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_ndf_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_ndf_mod.jsp", req, res);
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

protected void procReqSwift100OPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
						flexLog("About to call Page: " + LangPath + "ESWF100A_fe_op_format.jsp");
						callPage(LangPath + "ESWF100A_fe_op_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_opt_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_opt_mod.jsp", req, res);
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


protected void procReqSwift100SWP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10001Message msgSwift = null;
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
		msgSwift = (ESWF10001Message)mc.getMessageRecord("ESWF10001");
	 	msgSwift.setH01USR(user.getH01USR());
	 	msgSwift.setH01PGM("ESWF10001");
	 	msgSwift.setH01TIM(getTimeStamp());
	 	msgSwift.setH01SCR("01");
	 	msgSwift.setH01OPE(opCode);
	 	msgSwift.setH01WK1("1");
		
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
						flexLog("About to call Page: " + LangPath + "ESWF100A_fe_sw_format.jsp");
						callPage(LangPath + "ESWF100A_fe_sw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_swap_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_swap_mod.jsp", req, res);
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


protected void procReqSwift300SPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_sf_format.jsp");
						callPage(LangPath + "ESWF300A_fe_sf_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_sf_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_sf_mod.jsp", req, res);
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

protected void procReqSwift300FWR(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_fw_format.jsp");
						callPage(LangPath + "ESWF300A_fe_fw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_forward_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_forward_mod.jsp", req, res);
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

protected void procReqSwift300NDF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_nd_format.jsp");
						callPage(LangPath + "ESWF300A_fe_nd_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_ndf_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_ndf_mod.jsp", req, res);
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

protected void procReqSwift300OPT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("01");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_op_format.jsp");
						callPage(LangPath + "ESWF300A_fe_op_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_option_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_option_mod.jsp", req, res);
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

protected void procReqSwift300SWP(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("S");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}

		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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


		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_sw_format.jsp");
						callPage(LangPath + "ESWF300A_fe_sw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_swap_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_swap_mod.jsp", req, res);
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

protected void procReqSwift300SWPFor(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	ESWF10003Message msgSwift = null;
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
		msgSwift = (ESWF10003Message)mc.getMessageRecord("ESWF10003");
	 	msgSwift.setH03USR(user.getH01USR());
	 	msgSwift.setH03PGM("ESWF10003");
	 	msgSwift.setH03TIM(getTimeStamp());
	 	msgSwift.setH03SCR("F");
	 	msgSwift.setH03OPE(opCode);
		
		try {
		 	  msgSwift.setE03SWFACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFACC("0");
		}
		
		try {
		 	  msgSwift.setE03SWFBNK(userPO.getBank());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFBNK("0");
		}


		try {
		 	  msgSwift.setE03SWFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgSwift.setE03SWFCUN("0");
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




		if (newmessage.getFormatName().equals("ESWF10003")) {
			try {
				msgSwift = new ESWF10003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgSwift = (ESWF10003Message)newmessage;
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("swift", msgSwift);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESWF300A_fe_sw_format.jsp");
						callPage(LangPath + "ESWF300A_fe_sw_format.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
		 
			}
			else {  // There are errors
					try {
					    flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_swap_mod.jsp");
						callPage(LangPath + "EFE0140A_fe_basic_swap_mod.jsp", req, res);
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

protected void procActionEnterFRA(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	ETR0130DSMessage msgFRA = null;
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


	  opCode = "0003";
	
	// Send Initial data
	try
	{
		msgFRA = (ETR0130DSMessage)mc.getMessageRecord("ETR0130DS");
	 	msgFRA.setH01USERID(user.getH01USR());
	 	msgFRA.setH01PROGRM("ETR0130");
	 	msgFRA.setH01TIMSYS(getTimeStamp());
	 	msgFRA.setH01SCRCOD("01");
	 	msgFRA.setH01OPECOD(opCode);
	 	msgFRA.setH01FLGWK2("2");
		
		try {
		 	msgFRA.setE01WFRACC(req.getParameter("E01FRAACC"));
		}
		catch (Exception e)	{
	 	  msgFRA.setE01WFRACC("");
		}


	 	msgFRA.send();
	 	msgFRA.destroy();
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


		if (newmessage.getFormatName().equals("ETR0130DS")) {
			try {
				msgFRA = new ETR0130DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgFRA = (ETR0130DSMessage)newmessage;
			


			userPO.setIdentifier(msgFRA.getE01WFRACC());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fra", msgFRA);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ETR0160A_fra_acc.jsp");
						callPage(LangPath + "ETR0160A_fra_acc.jsp", req, res);	
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


protected void procActionEnterBLK(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {




	MessageRecord newmessage = null;
	EDL013011Message msgDeal = null;
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
	


	  opCode = "0002";
	
	// Send Initial data
	try
	{
		msgDeal = (EDL013011Message)mc.getMessageRecord("EDL013011");
	 	msgDeal.setH11USERID(user.getH01USR());
	 	msgDeal.setH11PROGRM("EDL0130");
	 	msgDeal.setH11TIMSYS(getTimeStamp());
	 	msgDeal.setH11SCRCOD("01");
	 	msgDeal.setH11OPECOD(opCode);
		
		try {
		 	
		 	  msgDeal.setE11DEAACC(req.getParameter("E01DLSREF"));
		}
		catch (Exception e)	{
	 	  msgDeal.setE11DEAACC("0");
		}
		
	 	msgDeal.send();
	 	msgDeal.destroy();
	 	flexLog("EDL0120DS Message Sent");
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


		if (newmessage.getFormatName().equals("EDL013011")) {
			try {
				msgDeal = new EDL013011Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}




			msgDeal = (EDL013011Message)newmessage;


			userPO.setIdentifier(msgDeal.getE11DEAACC());
			userPO.setHeader16(msgDeal.getE11DEAPVI());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("deal", msgDeal);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EFE0140A_fe_basic_blk");
						callPage(LangPath + "EFE0140A_fe_basic_blk.jsp", req, res);	
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

		int screen = A_SPOT;
		
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
				case A_MAINT_DEAL :
					procActionMaintDeal(mc, msgUser, req, res, session);
					break;
				case A_FRA_DEAL :
					procActionEnterFRA(mc, msgUser, req, res, session);
					break;	
					
				case R_SPOT :
				     procReqSpotMod(msgUser, req, res, session);
					 break;
				case R_FORWARD :
				     procReqForwardMod(msgUser, req, res, session);
					 break;
				case R_NDF :
				     procReqNDMod(msgUser, req, res, session);
					 break;
				case R_OPTION :
				     procReqOptionMod(msgUser, req, res, session);
					 break;
				case R_SWAP :
				     procReqSwapMod(msgUser, req, res, session);
					 break;
   				case R_S_SPOT : 
					procReqSwift100SPT(mc, msgUser, req, res, session);
					break;					
   				case R_S_FORWARD : 
					procReqSwift100FWR(mc, msgUser, req, res, session);
					break;					
   				case R_S_NDF : 
					procReqSwift100NDF(mc, msgUser, req, res, session);
					break;					
   				case R_S_OPT : 
					procReqSwift100OPT(mc, msgUser, req, res, session);
					break;					
   				case R_S_SWAP : 
					procReqSwift100SWP(mc, msgUser, req, res, session);
					break;					

   				case R_SC_SPOT : 
					procReqSwift300SPT(mc, msgUser, req, res, session);
					break;					
   				case R_SC_FORWARD : 
					procReqSwift300FWR(mc, msgUser, req, res, session);
					break;					
   				case R_SC_NDF : 
					procReqSwift300NDF(mc, msgUser, req, res, session);
					break;					
   				case R_SC_OPT : 
					procReqSwift300OPT(mc, msgUser, req, res, session);
					break;					
   				case R_SC_SWAP : 
					procReqSwift300SWP(mc, msgUser, req, res, session);
					break;					
   				case R_SC_SWAP_1 : 
					procReqSwift300SWPFor(mc, msgUser, req, res, session);
					break;					
				
				case A_ENTER_BLK : 
					procActionEnterBLK(mc, msgUser, req, res, session);
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