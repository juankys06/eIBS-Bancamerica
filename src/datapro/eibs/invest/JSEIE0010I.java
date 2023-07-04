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

 
import datapro.eibs.sockets.*;

public class JSEIE0010I extends datapro.eibs.master.SuperServlet {

	// Request	
	protected static final  int R_ENTER_NEW_UPD 	= 100;
	protected static final  int R_PORTFOLIO_MGMT	= 1;
	protected static final  int R_PORTFOLIO_CASH	= 3;
	protected static final  int R_PORTFOLIO_BENEF	= 5;
	protected static final  int R_ACC_HOLDERS	= 7;
	// Action 
	protected static final  int A_ENTER_NEW_UPD 	= 200;
	protected static final  int A_PORTFOLIO_MGMT	= 2;
	protected static final  int A_PORTFOLIO_CASH	= 4;
	protected static final  int A_PORTFOLIO_BENEF	= 6;
	
	protected String LangPath = "S";

/**
 * JSEIE00100 constructor comment.
 */
public JSEIE0010I() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEIE0010");
	
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
	EIE001001Message msgPortfolio = null;
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
		msgPortfolio = (EIE001001Message)mc.getMessageRecord("EIE001001");
	 	msgPortfolio.setH01USERID(user.getH01USR());
	 	msgPortfolio.setH01PROGRM("EIE0010");
	 	msgPortfolio.setH01TIMSYS(getTimeStamp());
	 	msgPortfolio.setH01SCRCOD("01");
	 	msgPortfolio.setH01OPECOD("0002");

		try {
		 	  msgPortfolio.setE01PRFCUN(req.getParameter("CUSTOMER"));
		}
		catch (Exception e)	{
	 	  
		}
		
				
		try {
		 	  msgPortfolio.setE01PRFNUM(req.getParameter("CODE"));
		}
		catch (Exception e)	{
	 	  msgPortfolio.setE01PRFNUM("0");
		}
		
	 	msgPortfolio.send();
	 	msgPortfolio.destroy();
	 	flexLog("EIE001001 Message Sent");
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

		if (newmessage.getFormatName().equals("EIE001001")) {
			try {
				msgPortfolio = new EIE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgPortfolio = (EIE001001Message)newmessage;
					
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);			
 
			if (IsNotError) {  // There are no errors
				
					userPO.setCusNum(msgPortfolio.getE01PRFCUN());
					userPO.setIdentifier(msgPortfolio.getE01PRFNUM());
				
				ses.setAttribute("userPO", userPO);	
				ses.setAttribute("invPort", msgPortfolio);
				try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_basic.jsp");
						callPage(LangPath + "EIE0010I_inv_port_basic.jsp", req, res);	
				}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
				}								
			}
			else {  // There are errors
				 res.sendRedirect(super.srctx + 
				 "/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=100" 
				 + "&CODE=" + msgPortfolio.getE01PRFNUM() 
				 + "&CUSTOMER=" + msgPortfolio.getE01PRFCUN()
				 );
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
protected  void procReqMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE001001Message msgPortfolio = null;
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
		msgPortfolio = (EIE001001Message)mc.getMessageRecord("EIE001001");
	 	msgPortfolio.setH01USERID(user.getH01USR());
	 	msgPortfolio.setH01PROGRM("EIE0010");
	 	msgPortfolio.setH01TIMSYS(getTimeStamp());
	 	msgPortfolio.setH01SCRCOD("01");
	 	msgPortfolio.setH01OPECOD("0002");

		try {
		 	  msgPortfolio.setE01PRFCUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  msgPortfolio.setE01PRFCUN("0");
		}
				
		try {
		 	  msgPortfolio.setE01PRFNUM(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  msgPortfolio.setE01PRFNUM("0");
		}
		
	 	msgPortfolio.send();
	 	msgPortfolio.destroy();
	 	flexLog("EIE001001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE001001")) {
			try {
				msgPortfolio = new EIE001001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgPortfolio = (EIE001001Message)newmessage;
					
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);			
 
			if (IsNotError) {  // There are no errors
				ses.setAttribute("userPO", userPO);	
				ses.setAttribute("invPort", msgPortfolio);
				try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_basic.jsp");
						callPage(LangPath + "EIE0010I_inv_port_basic.jsp", req, res);	
				}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
				}								
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + 
				 "/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=100" 
				 + "&CODE=" + msgPortfolio.getE01PRFNUM() 
				 + "&CUSTOMER=" + msgPortfolio.getE01PRFCUN()
				 );
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
protected  void procReqCashAccount(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EIE001101Message msgPortAcc = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;




	try {
		msgError =  new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgPortAcc = (EIE001101Message)mc.getMessageRecord("EIE001101");
	 	msgPortAcc.setH01USERID(user.getH01USR());
	 	msgPortAcc.setH01PROGRM("EIE0011");
	 	msgPortAcc.setH01TIMSYS(getTimeStamp());
	 	msgPortAcc.setH01SCRCOD("01");
	 	msgPortAcc.setH01OPECOD("0015");

		try {
		 	  msgPortAcc.setE01PCACUN(userPO.getCusNum());
		}
		catch (Exception e)	{
	 	  
		}
				
		try {
		 	  msgPortAcc.setE01PCANUM(userPO.getIdentifier());
		}
		catch (Exception e)	{
	 	  
		}
		
	 	msgPortAcc.send();
	 	msgPortAcc.destroy();
	 	flexLog("EIE001001 Message Sent");
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


		if (newmessage.getFormatName().equals("EIE001101")) {
			try {
				msgPortAcc =  new EIE001101Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}


			msgPortAcc = (EIE001101Message)newmessage;
					
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);			
 
			if (IsNotError) {  // There are no errors
				ses.setAttribute("userPO", userPO);	
				ses.setAttribute("invAcc", msgPortAcc);
				try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_acc.jsp");
						callPage(LangPath + "EIE0010I_inv_port_acc.jsp", req, res);	
				}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
				}								
			}
			else {  // There are errors
				try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_basic.jsp");
						callPage(LangPath + "EIE0010I_inv_port_basic.jsp", req, res);	
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
	protected  void procReqPortBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
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
		opCode = "0002";

		// Send Initial data
		try {
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDD0000");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("I");
			msgBene.send();
			msgBene.destroy();
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

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgBene = new ESD000004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_bene.jsp");
						callPage(LangPath + "EIE0010I_inv_port_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					
					try {
						flexLog("About to call Page: " + LangPath + "EIE0010I_inv_port_basic.jsp");
						callPage(LangPath + "EIE0010I_inv_port_basic.jsp", req, res);
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

		int screen = R_ENTER_NEW_UPD;
		
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

				
				case R_PORTFOLIO_MGMT :
					procReqMaintenance(mc, msgUser, req, res, session);
					break;
																	
				case A_ENTER_NEW_UPD : 
					procActionEnterNew(mc, msgUser, req, res, session);
					break;
				case R_PORTFOLIO_CASH : 
					procReqCashAccount(mc, msgUser, req, res, session);
					break;													
				case R_PORTFOLIO_BENEF :
					procReqPortBene(mc, msgUser, req, res, session);
					break;
				case R_ACC_HOLDERS :
					procReqInqTit(mc, msgUser, req, res, session);
					break;	

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
				return;
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

/**
 * This method was created in VisualAge.
 */
protected void procReqInqTit(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000006Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgRT = (ESD000006Message)mc.getMessageRecord("ESD000006");
	 	msgRT.setH06USR(user.getH01USR());
	 	msgRT.setH06PGM("EDD0000");
	 	msgRT.setH06TIM(getTimeStamp());
	 	msgRT.setH06SCR("01");
	 	msgRT.setH06OPE(opCode);
	 	msgRT.setE06ACC(req.getParameter("HOLDERS"));
	 	msgRT.setE06RTP("H");
		msgRT.send();	
	 	msgRT.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
				callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ESD000006")) {
			try {
				msgRT = new datapro.eibs.beans.ESD000006Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ESD000006Message)newmessage; 
			// showESD008004(msgRT);
			
			userPO.setAccNum(msgRT.getE06ACC());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtTit", msgRT);

			try {
				flexLog("About to call Page3: " + LangPath + "EIE0010_inv_acc_holders.jsp");
				callPage(LangPath + "EIE0010_inv_acc_holders.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
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
