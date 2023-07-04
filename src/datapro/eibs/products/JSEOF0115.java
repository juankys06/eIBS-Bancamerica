package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EOF011501Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETL051001Message;
import datapro.eibs.beans.EWD0115DSMessage;
import datapro.eibs.beans.EWD0120DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEOF0115 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit
	
	protected static final int R_BASIC		    = 1;
	protected static final int A_BASIC		    = 2;
	protected static final int R_LIST_HELP		= 3;
	protected static final int R_LIST_LEDGER	= 5;
	protected static final int R_LIST_PRINT		= 7;
	protected static final int R_CHANGE_NUMBER	= 9;
	protected static final int A_CHANGE_NUMBER	= 10;
	protected static final int R_OTHERS_TRANS	= 11;
	protected static final int R_BASIC_INQ	    = 12;
	protected static final int R_REV		    = 101;
	
	// entering options
	protected static final int R_LIST_NEW		    = 100;
	protected static final int R_ENTER_MAINT		= 300;

	protected static final int A_LIST_NEW		    = 200;
	protected static final int A_ENTER_MAINT		= 400;
	protected static final int R_ENTER_REV		= 500;
	protected static final int A_ENTER_REV		= 600;
	
	protected static final int A_ENTER_INQ_PAR  = 1600;
	protected static final int R_BASIC_PAR_INQ  = 113;

	protected static final int R_LIST_NEW_PAR	= 700;
	protected static final int A_LIST_NEW_PAR   = 800;
	protected static final int A_BASIC_PAR	    = 900;
	protected static final int A_ENTER_MAINT_PAR= 1400;
	protected static final int A_ENTER_MAINT_DEL= 1500;
	protected static final int A_BASIC_PAR_DEL  = 1900;
	protected static final int R_BASIC_PAR	    = 111;	
	protected static final int R_BASIC_PAR_DEL  = 112;
		
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEOF0115() {
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
protected void procActionBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		
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
protected void procActionBasicPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		
		procReqListLedgerPar(mc,user,req,res,ses);
	
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
protected void procActionBasicParDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		
		procReqListLedgerParDel(mc,user,req,res,ses);
	
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
protected void procActionChangeNumber(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
	 	msgOffChk.setH01FLGWK1("C");
		try {
			msgOffChk.setE01OFMCKN(userPO.getIdentifier());
		}
		catch (Exception e)	{
		}
		
		try {
	 		msgOffChk.setE01NEWCKN(req.getParameter("E01NEWCKN"));
		}
		catch (Exception e)	{
		}
		
		try {
	 		msgOffChk.setE01OFMCCY(userPO.getCurrency().toUpperCase());
		}
		catch (Exception e)	{
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();

	 	flexLog("EOF011501 Message Sent");
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
				flexLog("EOF011501 Message Received");
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;


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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_change.jsp");
					callPage(LangPath + "EOF0115_of_chk_change.jsp", req, res);	
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
protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		try {
		 	if (req.getParameter("E01OFMCKN") != null)
		 		userPO.setIdentifier(req.getParameter("E01OFMCKN"));
		}
		catch (Exception e)	{
	 		userPO.setIdentifier("0");
		}
		try {
			if (req.getParameter("E01OFMCCY") != null)
		 		userPO.setCurrency(req.getParameter("E01OFMCCY").toUpperCase());
		}
		catch (Exception e)	{
	 	  	userPO.setCurrency("");
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		
		flexLog("About to redirect request to the procReqTr with specific parameters: ");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1");
		
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
protected void procActionEnterMaintPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	boolean IsNotError = false;
	String Numchk="";
	String Loanacc="";

	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{

		try {
			Numchk = (req.getParameter("CHK"));
			userPO.setHeader22(req.getParameter("CHK"));
		} catch (Exception e) {userPO.setHeader22(req.getParameter("0"));}

		try {
			Loanacc = (req.getParameter("LNSACC"));
			userPO.setHeader23(req.getParameter("LNSACC"));
		} catch (Exception e) {userPO.setHeader23(req.getParameter("0"));}

		


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		
		flexLog("About to redirect request to the procReqTr with specific parameters: ");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=111");
		
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
protected void procActionEnterMaintDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	
	boolean IsNotError = false;
	String Numchk="";
	String Loanacc="";

	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{

		try {
			Numchk = (req.getParameter("CHK"));
			userPO.setHeader22(req.getParameter("CHK"));
		} catch (Exception e) {userPO.setHeader22(req.getParameter("0"));}

		try {
			Loanacc = (req.getParameter("LNSACC"));
			userPO.setHeader23(req.getParameter("LNSACC"));
		} catch (Exception e) {userPO.setHeader23(req.getParameter("0"));}

		


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		
		flexLog("About to redirect request to the procReqTr with specific parameters: ");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=112");
		
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
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
	 	//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0001");
		flexLog("Header has been sent");
		try {
		  if (req.getParameter("E01OFMFTY") != null)
		 	  msgOffChk.setE01OFMFTY(req.getParameter("E01OFMFTY").trim());		 	  
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMFTY("0");
		  
		}
		try {
		  if (req.getParameter("E01OFMCKN") != null)
			  msgOffChk.setE01OFMCKN(req.getParameter("E01OFMCKN").trim());		 	  
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCKN("");
  
		}
		try {
		 	if (req.getParameter("E01OFMCCY") != null)
		 	   msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY"));
		 	  
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCCY("0");
		  
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();

	 	flexLog("EOF011501 Message Sent");
	
		
	// Receive Error Message
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	
	// Receive Data
	
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) {
			
			msgOffChk = (EOF011501Message)newmessage;


			userPO.setIdentifier(msgOffChk.getE01OFMCKN());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (msgOffChk.getE01SELFMT().equals("1")) {
	
				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
						callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new.jsp");
						callPage(LangPath + "EOF0115_of_chk_new.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}

			} else {
	
				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_giro.jsp");
						callPage(LangPath + "EOF0115_of_chk_basic_giro.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new.jsp");
						callPage(LangPath + "EOF0115_of_chk_new.jsp", req, res);	
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
protected void procActionEnterNewPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
		msgOffChk.setH01USERID(user.getH01USR());
		msgOffChk.setH01PROGRM("EOF0115");
		msgOffChk.setH01TIMSYS(getTimeStamp());
		msgOffChk.setH01SCRCOD("01");
		msgOffChk.setH01OPECOD("0001");
		flexLog("Header has been sent");
		try {
		  if (req.getParameter("E01OFMFTY") != null)
			  msgOffChk.setE01OFMFTY(req.getParameter("E01OFMFTY").trim());		 	  
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMFTY("0");
		  
		}
		try {
		  if (req.getParameter("E01OFMCKN") != null)
			  msgOffChk.setE01OFMCKN(req.getParameter("E01OFMCKN").trim());		 	  
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCKN("");
  
		}
		try {
			if (req.getParameter("E01OFMCCY") != null)
			   msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY"));
		 	  
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCCY("0");
		  
		}

		try {
		  if (userPO.getHeader2() != null)
			  msgOffChk.setE01OFMCUN(userPO.getHeader2().trim());		 	  
		}
		catch (Exception e)	{
		}

		try {
		  if (userPO.getHeader22() != null)
			  msgOffChk.setE01OFMAMT(userPO.getHeader22().trim());		 	  
		}
		catch (Exception e)	{
		}

		try {
		  if (userPO.getHeader23() != null)
			  msgOffChk.setE01DEBACC(userPO.getHeader23().trim());		 	  
		}
		catch (Exception e)	{
		}

		try {
		  if (userPO.getHeader23() != null)
			  msgOffChk.setE01OFMPTH(userPO.getHeader23().trim());		 	  
		}
		catch (Exception e)	{
		}


		msgOffChk.send();	
		msgOffChk.destroy();

		flexLog("EOF011501 Message Sent");
	
		
	// Receive Error Message
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	
	// Receive Data
	
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) {
			
			msgOffChk = (EOF011501Message)newmessage;


			userPO.setHeader23(msgOffChk.getE01OFMCKN());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

	
				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_lns.jsp");
						callPage(LangPath + "EOF0115_of_chk_basic_lns.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new_lns.jsp");
						callPage(LangPath + "EOF0115_of_chk_new_lns.jsp", req, res);	
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
protected void procReqChangeNumber(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_change.jsp");
		callPage(LangPath + "EOF0115_of_chk_change.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
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
protected void procReqListHelp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ETL051001Message msgSearch = null;
	ETL051001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ETL051001Message)mc.getMessageRecord("ETL051001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("ETL0510");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	msgSearch.setE01SELSCH("D");
	 	
	 	
		try{
			try{
			 	posi= Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	posi=0;	
				flexLog("E01NUMREC");
			}

	
			try{
			 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	msgSearch.setE01NUMREC("0");	
				flexLog("E01NUMREC");
			}
			
		
			try{
			 	msgSearch.setE01SELDTY("1");
			}
			catch (Exception e){
			 	flexLog("E01SELDTY");
			}

			try{
			 	msgSearch.setE01SELSCH("");
			}
			catch (Exception e){
			 	flexLog("E01SELSCH");
			}

		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  flexLog("Input data error " + e);
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();

	 	flexLog("ETL051001 Message Sent");
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
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
		else if (newmessage.getFormatName().equals("ETL051001")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
				flexLog("ETL051001 Message Received");
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

				msgList = (ETL051001Message)newmessage;

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
					
					
					String showRef = "enter('" + msgList.getE01OFMNCH() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatDate(msgList.getE01OFMID1(),msgList.getE01OFMID2(),msgList.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBNF()) + "</A></TD>");
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
			ses.setAttribute("dvList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_help.jsp");
				 callPage(LangPath + "EOF0115_of_chk_help.jsp", req, res);
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
	 	msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		//msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
		msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0005");
		// all the fields here

	 	//Made to verify if the original check number obtained from th as400 was changed
	 	String chkNumChanged = "N" ;
	 	if ( ! msgOffChk.getE01OFMCKN().equals( req.getParameter("E01OFMCKN").trim() )
	 			|| msgOffChk.getH01FLGWK2().equals("Y") ) {
	 		chkNumChanged = "Y" ;
		}
	 	
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
		
		msgOffChk.setH01FLGWK2( chkNumChanged ) ;

	 	mc.sendMessage(msgOffChk);	
	 	// msgOffChk.destroy(); <--- because I need to keep the bean ( offBasic) in the session
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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
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
					ses.setAttribute("offBasic", msgOffChk);
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
protected void procReqListLedgerPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
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
		// msgOffChk.destroy(); <--- because I need to keep the bean ( offBasic) in the session
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

			userPO.setHeader4(msgOffChk.getE01OFMCKN());
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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
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


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_cont_lns.jsp");
					callPage(LangPath + "EOF0115_of_chk_cont_lns.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					ses.setAttribute("offBasic", msgOffChk);
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_lns.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic_lns.jsp", req, res);	
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
protected void procReqListLedgerParDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		msgOffChk.setH01USERID(user.getH01USR());
		msgOffChk.setH01PROGRM("EOF0115");
		msgOffChk.setH01TIMSYS(getTimeStamp());
		msgOffChk.setH01SCRCOD("01");
		msgOffChk.setH01OPECOD("0008");


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
		// msgOffChk.destroy(); <--- because I need to keep the bean ( offBasic) in the session
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

			userPO.setHeader4(msgOffChk.getE01OFMCKN());
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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
	}
	catch (Exception e) {
	}

	try {
		flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_cont_lns_del.jsp");
		callPage(LangPath + "EOF0115_of_chk_cont_lns_del.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}
}



/**
 * This method was created in VisualAge.
 */
protected void procReqListNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0115DSMessage msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	userPO.setOption("OCK");
	userPO.setPurpose("NEW");
	ses.setAttribute("userPO", userPO);
	String opt="";

	int posi = 0;
	// Send Initial data
	  	try {
			msgList = (EWD0115DSMessage)mc.getMessageRecord("EWD0115DS");

			try {
				opt = (req.getParameter("OPTION"));
				msgList.setEWDOPE(req.getParameter("OPTION"));
			} catch (Exception e) {}

			msgList.send();	
		 	msgList.destroy();
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
			
		// Receiving
		try
		{
	  		newmessage = mc.receiveMessage();
	  
	  		if (newmessage.getFormatName().equals("EWD0115DS")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgList = (EWD0115DSMessage)newmessage;

					marker = msgList.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						myRow = new StringBuffer("<TR>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDBNK() + "</a></td>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDCCY() + "</a></td>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDFTY() + "</a></td>");
						myRow.append("<td><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDDSC() + "</a></td>");
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
				ses.setAttribute("ewd0115Help", beanList);
					
				try {
					 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new.jsp");
					 callPage(LangPath + "EOF0115_of_chk_new.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
				} else {
					
					try {
						 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new_giro.jsp");
						 callPage(LangPath + "EOF0115_of_chk_new_giro.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	

	}


	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListNewPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EWD0115DSMessage msgList = null;
		JBList beanList = null;
		UserPos	userPO = null;

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		userPO.setOption("OCK");
		userPO.setPurpose("NEW");
		ses.setAttribute("userPO", userPO);
		String opt="";
		String Valchk="";
		String Lnsacc="";
		
		int posi = 0;
		// Send Initial data
			try {
				msgList = (EWD0115DSMessage)mc.getMessageRecord("EWD0115DS");

				try {
					opt = (req.getParameter("OPTION"));
					msgList.setEWDOPE(req.getParameter("OPTION"));
				} catch (Exception e) {}

				try {
					Valchk = (req.getParameter("VAL"));
					userPO.setHeader22(req.getParameter("VAL"));
				} catch (Exception e) {userPO.setHeader22(req.getParameter("0"));}

				try {
					Lnsacc = (req.getParameter("LNSACC"));
					userPO.setHeader23(req.getParameter("LNSACC"));
				} catch (Exception e) {userPO.setHeader23(req.getParameter("0"));}


				ses.setAttribute("userPO", userPO);

				msgList.send();	
				msgList.destroy();
			}		
			catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				return;
			}
			
			// Receiving
			try
			{
				newmessage = mc.receiveMessage();
	  
				if (newmessage.getFormatName().equals("EWD0115DS")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}

					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;

					while (true) {

						msgList = (EWD0115DSMessage)newmessage;

						marker = msgList.getEWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						}
						else {
							myRow = new StringBuffer("<TR>");
							myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDBNK() + "</a></td>");
							myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDCCY() + "</a></td>");
							myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDFTY() + "</a></td>");
							myRow.append("<td><a href=\"javascript:enter('" + msgList.getEWDFTY() + "', '" + msgList.getEWDCCY() + "')\">" + msgList.getEWDDSC() + "</a></td>");
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
					ses.setAttribute("ewd0115Help", beanList);
					
					try {
						 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new_lns.jsp");
						 callPage(LangPath + "EOF0115_of_chk_new_lns.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				
					} else {
					
						try {
							 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new_lns.jsp");
							 callPage(LangPath + "EOF0115_of_chk_new_lns.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

				}
			}
			catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
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

/*
 *  Reversion
 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */

 protected void procReqEnterRev(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			 throws ServletException, IOException {

	 ELEERRMessage msgError = null;
	 UserPos	userPO = null;	

	 try {

		 msgError = new datapro.eibs.beans.ELEERRMessage();
		 userPO = new datapro.eibs.beans.UserPos();
		 userPO.setOption("OCK");
		 userPO.setPurpose("REVERSION");
		 ses.setAttribute("error", msgError);
		 ses.setAttribute("userPO", userPO);
		
	 } catch (Exception ex) {
		 flexLog("Error: " + ex); 
	 }

	 try {
		 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_rev.jsp");
		 callPage(LangPath + "EOF0115_of_chk_enter_rev.jsp", req, res);	
	 }
	 catch (Exception e) {
		 flexLog("Exception calling page " + e);
	 }
 }


 protected void procActionEnterRev(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			 throws ServletException, IOException {

	 UserPos	userPO = null;	
	 boolean IsNotError = false;
	
	 userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	 // Send Initial data
	 try
	 {
		 try {
			 if (req.getParameter("E01OFMCKN") != null)
				 userPO.setIdentifier(req.getParameter("E01OFMCKN"));
		 }
		 catch (Exception e)	{
			 userPO.setIdentifier("0");
		 }
		 try {
			 if (req.getParameter("E01OFMCCY") != null)
				 userPO.setCurrency(req.getParameter("E01OFMCCY").toUpperCase());
		 }
		 catch (Exception e)	{
			 userPO.setCurrency("");
		 }

		 flexLog("Putting java beans into the session");
		 ses.setAttribute("userPO", userPO);
		
		 flexLog("About to redirect request to the procReqTr with specific parameters: ");
		 procReqRev(mc, user, req, res, ses);
		
	 } catch (Exception e) {
		 e.printStackTrace();
		 flexLog("Error: " + e);
		 throw new RuntimeException("Socket Communication Error");
	 }	
 }


 protected void procReqRev(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			 throws ServletException, IOException {

	 MessageRecord newmessage = null;
	 EOF011501Message msgOffChk = null;
	 ELEERRMessage msgError = null;
	 UserPos	userPO = null;	
	 boolean IsNotError = false;
	
	 try {
		 msgError = new datapro.eibs.beans.ELEERRMessage();
	 } catch (Exception ex) {
		 flexLog("Error: " + ex); 
	 }

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
		 msgOffChk.setH01OPECOD("0002");// antes 1002
		 flexLog("Header has been sended");
		 try {
			msgOffChk.setE01OFMCKN(userPO.getIdentifier());
		 } catch (Exception e)	{
			msgOffChk.setE01OFMCKN("0");
		 }
		 try {
			msgOffChk.setE01OFMCCY(userPO.getCurrency());
		 } catch (Exception e)	{
		 }
		msgOffChk.setH01FLGWK1("R");

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

		 if (newmessage.getFormatName().equals("EOF011501")) {
			 msgOffChk = (EOF011501Message)newmessage;

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
			 userPO.setHeader19(msgOffChk.getE01OFMAPV());
			 userPO.setHeader20(msgOffChk.getE01OFMBTH());


			 flexLog("Putting java beans into the session");
			 ses.setAttribute("error", msgError);
			 ses.setAttribute("userPO", userPO);
			 ses.setAttribute("offBasic", msgOffChk);

			 if (IsNotError) {  // There are no errors
				 try {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEOF0120?SCREEN=20");
				 }
				 catch (Exception e) {
					 flexLog("Exception calling page " + e);
				 }
			 }
			 else {  // There are errors
				 try {
					 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_rev.jsp");
					 callPage(LangPath + "EOF0115_of_chk_enter_rev.jsp", req, res);	
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
					procReqMaint(mc, msgUser, req, res, session);
					break;	
				case R_BASIC_INQ :
					procReqInquiry(mc, msgUser, req, res, session);
					break;	
					
				case R_BASIC_PAR :
					procReqMaintPar(mc, msgUser, req, res, session);
					break;	
				case R_BASIC_PAR_DEL :
					procReqMaintParDel(mc, msgUser, req, res, session);
					break;	
					
				case R_BASIC_PAR_INQ :
					procReqInqPar(mc, msgUser, req, res, session);
					break;	

				
				case R_LIST_HELP :
					procReqListHelp(mc, msgUser, req, res, session);
					break;	
				case R_LIST_LEDGER :
					procReqListLedger(mc, msgUser, req, res, session);
					break;
				case R_LIST_PRINT : 
					procReqPrint(msgUser, req, res, session);
					break;
				case R_CHANGE_NUMBER : 
					procReqChangeNumber(msgUser, req, res, session);
					break;
				case R_OTHERS_TRANS : 
					procReqOthersTransaction(mc,msgUser, req, res, session);
					break;
				case R_REV :
					procReqRev (mc, msgUser, req, res, session);
					break;		
						
				// Action
				case A_BASIC :
					procActionBasic(mc, msgUser, req, res, session);
					break;
				case A_CHANGE_NUMBER : 
					procActionChangeNumber(mc, msgUser, req, res, session);
					break;	
				case A_BASIC_PAR :
					procActionBasicPar(mc, msgUser, req, res, session);
					break;
				case A_BASIC_PAR_DEL :
					procActionBasicParDel(mc, msgUser, req, res, session);
					break;

				
				// END Off. Check

				// BEGIN Entering
				// Request
				case R_LIST_NEW : 
					procReqListNew(mc, msgUser, req, res, session);
					break;
				case R_LIST_NEW_PAR : 
					procReqListNewPar(mc, msgUser, req, res, session);
					break;
				
				case R_ENTER_MAINT : 
					procReqEnterMaint(msgUser, req, res, session);
					break;
				
				// Action 
				case A_ENTER_MAINT : 
					procActionEnterMaint(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_MAINT_PAR : 
					procActionEnterMaintPar(mc, msgUser, req, res, session);
					break;	
				case A_ENTER_MAINT_DEL : 
					procActionEnterMaintDel(mc, msgUser, req, res, session);
					break;	

				case A_LIST_NEW :
				    procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case A_LIST_NEW_PAR :
					procActionEnterNewPar(mc, msgUser, req, res, session);
					break;

				case R_ENTER_REV : 
					procReqEnterRev(msgUser, req, res, session);
					break;	
				case A_ENTER_REV : 
					procActionEnterRev(mc, msgUser, req, res, session);	
					break;		
					
				// END Entering
					
				case A_ENTER_INQ_PAR : 
					procActionEnterInqPar(mc, msgUser, req, res, session);
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


/**
 * This method was created in VisualAge.
 */
protected void procReqMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		flexLog("Header has been sended");
		try {
		 	//if (req.getParameter("E01OFMCKN") != null)
		 	  msgOffChk.setE01OFMCKN(userPO.getIdentifier());
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
		 	//if (req.getParameter("E01OFMCCY") != null)
		 	   msgOffChk.setE01OFMCCY(userPO.getCurrency());
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCCY("0");
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;


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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
					callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
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
protected void procReqMaintPar(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		msgOffChk.setH01OPECOD("0006");
		flexLog("Header has been sent");
		try {
			  msgOffChk.setE01OFMCKN(userPO.getHeader22());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
			   msgOffChk.setE01OFMCCY(userPO.getCurrency());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCCY("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
			  msgOffChk.setE01OFMPTH(userPO.getHeader23());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMPTH(" ");
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgOffChk = (EOF011501Message)newmessage;


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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_lns_mnt.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic_lns_mnt.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
					callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
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
protected void procReqMaintParDel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
		msgOffChk.setH01OPECOD("0006");
		flexLog("Header has been sent");
		try {
			  msgOffChk.setE01OFMCKN(userPO.getHeader22());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
			   msgOffChk.setE01OFMCCY(userPO.getCurrency());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMCCY("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
			  msgOffChk.setE01OFMPTH(userPO.getHeader23());
			  flexLog("Product Sent");
		}
		catch (Exception e)	{
		  msgOffChk.setE01OFMPTH(" ");
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgOffChk = (EOF011501Message)newmessage;


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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());


			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_lns_del.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic_lns_del.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_maint.jsp");
					callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp", req, res);	
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else
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
protected void procReqInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	if ( req.getParameter("PURPOSE") == null ) {
		userPO.setPurpose("APPROVAL_INQ");
	} else {
		userPO.setPurpose("PRINT");
	}
	

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
		msgOffChk.setH01OPECOD("0004");
		flexLog("Header has been sended");
		try {
			//if (req.getParameter("E01OFMCKN") != null)
			  msgOffChk.setE01OFMCKN(userPO.getIdentifier());
			  flexLog("Product Sent");
		} catch (Exception e)	{
			msgOffChk.setE01OFMCKN("");
		}
		try {
			//if (req.getParameter("E01OFMCCY") != null)
			   msgOffChk.setE01OFMCCY(userPO.getCurrency());
			  flexLog("Product Sent");
		} catch (Exception e)	{
		  msgOffChk.setE01OFMCCY("0");
		}

		msgOffChk.send();	
		msgOffChk.destroy();
		
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try	{
		newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			msgOffChk = (EOF011501Message)newmessage;

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
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic_inquiry.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic_inquiry.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {  // There are errors

				try {
					if (userPO.getPurpose().equals("PRINT")) {
						flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_print.jsp");
						callPage(LangPath + "EOF0115_of_chk_print.jsp", req, res);	
					} else {
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);	
					}
					
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}	

}

/**
 * This method was created in VisualAge.
 */
protected void procReqOthersTransaction(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	UserPos	userPO = null;
	DataTransaction transData = null;
	EOF011501Message msgOffChk = null;	
	boolean IsNotError = false;
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	try {

		transData = new datapro.eibs.beans.DataTransaction();
		msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		
		if (userPO.getHeader19().equals("1")) {
			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());
		} else {
			transData.setWrkFile("3");
			transData.setBthnum(userPO.getHeader20());
			transData.setBank(msgOffChk.getE01OFMBNK());
			transData.setBranch(msgOffChk.getE01OFMBRN());
		}
		String purpose = req.getParameter("PURPOSE");
		
		if (purpose == null) purpose = "";
		if (purpose.equals("APPROVAL")){
			userPO.setPurpose("APPROVAL");
			purpose = "READONLY";
		}else if (purpose.equals("PRINT")){
			userPO.setPurpose("PRINT");
			userPO.setHeader19("p"); //Print
			purpose = "READONLY";
		}
		
		if (purpose.equals("READONLY"))
			transData.setWrkFile("4");			     
		transData.setChkNum(userPO.getIdentifier());
		userPO.setOption("OCK");
		
		flexLog("Putting java beans into the session");
		ses.setAttribute("transData", transData);
		ses.setAttribute("userPO", userPO);
		
		flexLog("About to redirect request to the procReqTr with specific parameters: ");
		try
		{
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1&PURPOSE=" + purpose);
		}
		catch (Exception e)
		{
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1");
		}
		
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

}


	protected void procActionEnterInqPar(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		UserPos userPO = null;
		boolean IsNotError = false;
		String Numchk = "";
		String Loanacc = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {

			try {
				Numchk = (req.getParameter("CHK"));
				userPO.setHeader22(req.getParameter("CHK"));
			} catch (Exception e) {
				userPO.setHeader22(req.getParameter("0"));
			}

			try {
				Loanacc = (req.getParameter("LNSACC"));
				userPO.setHeader23(req.getParameter("LNSACC"));
			} catch (Exception e) {
				userPO.setHeader23(req.getParameter("0"));
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(super.srctx
					+ "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=113");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}


	protected void procReqInqPar(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF011501Message msgOffChk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Sending header");
			//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
			msgOffChk = (EOF011501Message) mc.getMessageRecord("EOF011501");
			msgOffChk.setH01USERID(user.getH01USR());
			msgOffChk.setH01PROGRM("EOF0115");
			msgOffChk.setH01TIMSYS(getTimeStamp());
			msgOffChk.setH01SCRCOD("01");
			msgOffChk.setH01OPECOD("0006");
			flexLog("Header has been sent");
			try {
				msgOffChk.setE01OFMCKN(userPO.getHeader22());
				flexLog("Product Sent");
			} catch (Exception e) {
				msgOffChk.setE01OFMCKN("0");
				flexLog(" error " + e);
				flexLog(" Error Sent");
			}
			try {
				msgOffChk.setE01OFMCCY(userPO.getCurrency());
				flexLog("Product Sent");
			} catch (Exception e) {
				msgOffChk.setE01OFMCCY("0");
				flexLog(" error " + e);
				flexLog(" Error Sent");
			}
			try {
				msgOffChk.setE01OFMPTH(userPO.getHeader23());
				flexLog("Product Sent");
			} catch (Exception e) {
				msgOffChk.setE01OFMPTH(" ");
				flexLog(" error " + e);
				flexLog(" Error Sent");
			}

			msgOffChk.send();
			msgOffChk.destroy();
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

			if (newmessage.getFormatName().equals("EOF011501")) {
				try {
					msgOffChk = new datapro.eibs.beans.EOF011501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgOffChk = (EOF011501Message) newmessage;

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
				userPO.setHeader19(msgOffChk.getE01OFMAPV());
				userPO.setHeader20(msgOffChk.getE01OFMBTH());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("offBasic", msgOffChk);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EOF0115_of_chk_basic_lns_inq.jsp");
						callPage(LangPath + "EOF0115_of_chk_basic_lns_inq.jsp",
								req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath
								+ "EOF0115_of_chk_enter_maint.jsp");
						callPage(LangPath + "EOF0115_of_chk_enter_maint.jsp",
								req, res);
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



	
}