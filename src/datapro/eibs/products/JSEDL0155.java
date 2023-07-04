package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0155 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_ENTER_ACCOUNT		= 1;
	protected static final int A_ENTER_ACCOUNT		= 2;	
	protected static final int A_MAINT_DELETE		= 3;

	protected static final int R_DETAIL				= 4;
	protected static final int A_NEW_PAYMENT		= 5;

	protected String LangPath = "S";

/**
 * JSEDL0910 constructor comment.
 */
public JSEDL0155() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0910");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

protected void procReqDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015502Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	int rowNum = 0;
	try{
		rowNum = Integer.parseInt(req.getParameter("Row"));
	} catch (Exception e) {}

	JBObjList list = (JBObjList) ses.getAttribute("list");
	list.setCurrentRow(rowNum);
	EDL015502Message record = (EDL015502Message) list.getRecord();
	ses.setAttribute("record", record);
	
	flexLog("About to call Page: " + LangPath + "EDL0155_ln_crn_pay_det.jsp");
	callPage(LangPath + "EDL0155_ln_crn_pay_det.jsp", req, res);			


}
/**
 * This method was created in VisualAge.
 */
protected void procActionMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015502Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
    String opcode="";
    String opt="";
    String recalc="";
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	try {
		opcode = req.getParameter("OPTION");
	} catch (Exception e) {}
	if (opcode == null) opcode = "";
	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgLN = (EDL015502Message)mc.getMessageRecord("EDL015502");
		msgLN.setH02USERID(user.getH01USR());
	 	msgLN.setH02PROGRM("EDL0155");
	 	msgLN.setH02TIMSYS(getTimeStamp());
	 	msgLN.setH02SCRCOD("01");
	 	msgLN.setH02OPECOD(opcode);		
		
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

	 	//mc.sendMessage(msgLN); 
	 	msgLN.send();	
	 	msgLN.destroy();

	 	flexLog("EDL015502 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error o data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
		
		ses.setAttribute("error", msgError);
		
	  } else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	  
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL015502")) {
	  	
			msgLN = (EDL015502Message)newmessage;
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("record", msgLN);
			
			if (IsNotError) { // There are no errors
				
				flexLog("About to call Page: /servlet/datapro.eibs.products.JSEDL0155?SCREEN=2");
				res.sendRedirect(
					super.srctx + "/servlet/datapro.eibs.products.JSEDL0155?SCREEN=2&E01DEAACC="+userPO.getAccNum());

			} else { // There are errors
				String rowNum = "";
				try{
					rowNum = req.getParameter("ROW");
				} catch (Exception e) {}
				try {
					flexLog("About to call Page: " + LangPath + "EDL0155_ln_crn_pay_det.jsp");
					res.sendRedirect(super.srctx + LangPath + "EDL0155_ln_crn_pay_det.jsp");
				}
				catch (Exception e) {
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

protected void procActionNewPayment(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req,	HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015502Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = true;
	String opcode = "";
	String opt = "";
	String recalc = "";
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	try {
		opcode = req.getParameter("OPTION");
	} catch (Exception e) {
	}
	if (opcode == null)
		opcode = "";

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgLN = (EDL015502Message) mc.getMessageRecord("EDL015502");
		msgLN.setH02USERID(user.getH01USR());
		msgLN.setH02PROGRM("EDL0155");
		msgLN.setH02TIMSYS(getTimeStamp());
		msgLN.setH02SCRCOD("01");  
		msgLN.setH02OPECOD("0001");

		msgLN.setE02DLPACC(userPO.getAccNum());

		mc.sendMessage(msgLN); //msgLN.send();	
		msgLN.destroy();

		flexLog("EDL015502 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error o data Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);

			ses.setAttribute("error", msgError);

		} else if (newmessage.getFormatName().equals("EDL015502")) {

			msgLN = (EDL015502Message) newmessage;
			msgLN.setH02OPECOD("0004");

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("record", msgLN);

		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
		if (IsNotError) { // There are no errors
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0155_ln_crn_pay_det.jsp");
				res.sendRedirect(
					super.srctx + LangPath + "EDL0155_ln_crn_pay_det.jsp");
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

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

		int screen = R_ENTER_ACCOUNT;
		
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
				
				// Request
				case R_ENTER_ACCOUNT : 
					procReqEnterAccount(msgUser, req, res, session);
					break;	

				// Action 
				case A_ENTER_ACCOUNT : 
					procActionEnterAccount(mc, msgUser, req, res, session);
					break;
				case A_MAINT_DELETE :
					procActionMaint(mc, msgUser, req, res, session);
					break;
				case A_NEW_PAYMENT :
					procActionNewPayment(mc, msgUser, req, res, session);
					break;
				case R_DETAIL :
					procReqDetail(mc, msgUser, req, res, session);
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
protected void procReqEnterAccount(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0155_ln_enter_account.jsp");
		callPage(LangPath + "EDL0155_ln_enter_account.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procActionEnterAccount(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
	MessageRecord newmessage = null;
	EDL015501Message msgHeader = null;
	ELEERRMessage msgError = null;
	EDL015502Message msgList = null;
	JBObjList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgHeader = (EDL015501Message)mc.getMessageRecord("EDL015501");
		msgHeader.setH01USERID(user.getH01USR());
		msgHeader.setH01PROGRM("EDL0155");
		msgHeader.setH01TIMSYS(getTimeStamp());
		msgHeader.setH01OPECOD(opCode);
		try {
			msgHeader.setE01DEAACC(req.getParameter("E01DEAACC"));
		}
		catch (Exception e) {
			msgHeader.setE01DEAACC("0");
		}
		msgHeader.send();	
		msgHeader.destroy();

		flexLog("EDL015501 Message Sent");
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

		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			// showERROR(msgError);
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDL0155_ln_enter_account.jsp");
				callPage(LangPath + "EDL0155_ln_enter_account.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		
		else if (newmessage.getFormatName().equals("EDL015501")) {
			try {
				msgHeader = new datapro.eibs.beans.EDL015501Message();

				flexLog("EDL015501 Message Received");

				msgHeader = (EDL015501Message)newmessage;

				//Header
				userPO.setAccNum(msgHeader.getE01DEAACC());
				userPO.setProdCode(msgHeader.getE01DEAPRO());
				userPO.setCusNum(msgHeader.getE01DEACUN());
				userPO.setCusName(msgHeader.getE01CUSNA1());
				userPO.setCurrency(msgHeader.getE01DEACCY());
				userPO.setHeader2(Util.formatCell(msgHeader.getH01SCRCOD())); // Country
				userPO.setHeader3(Util.formatCell(msgHeader.getH01FLGWK3())); // Revaluation
				userPO.setHeader10(Util.formatDate(msgHeader.getE01OPEND1(),msgHeader.getE01OPEND2(),msgHeader.getE01OPEND3())); //Opening Date
				userPO.setHeader11(Util.formatDate(msgHeader.getE01MATUR1(),msgHeader.getE01MATUR2(),msgHeader.getE01MATUR3())); //Maturity Date
				userPO.setHeader12(Util.formatCCY(msgHeader.getE01DEAOAM()));  // Original Amount
				userPO.setHeader13(Util.formatCell(msgHeader.getE01DEARTE())); // Rate
				userPO.setHeader14(Util.formatCell(msgHeader.getE01DEABAS())); //  Acrual Basis
				userPO.setHeader15(Util.formatCell(msgHeader.getE01DEAICT())); // Interest Type
				userPO.setHeader16(Util.formatDate(msgHeader.getE01LSTCL1(),msgHeader.getE01LSTCL2(),msgHeader.getE01LSTCL3())); //Last Calc Date
			}
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgHeader = (EDL015501Message)newmessage;

			char sel = ' ';
			String marker = "";
			String myFlag = "";

			beanList = new JBObjList();
			while (true) {

				newmessage = mc.receiveMessage();
				msgList = (EDL015502Message)newmessage;

				marker = msgList.getE02ENDFLD();
				if (marker.equals("*")) {
					break;
				} else {
					beanList.addRow(msgList);
				}
			}

			ses.setAttribute("list", beanList);
			ses.setAttribute("header", msgHeader);

			try {
				flexLog("About to call Page: " + LangPath + "EDL0155_ln_crn_pay.jsp");
				callPage(LangPath + "EDL0155_ln_crn_pay.jsp", req, res);
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

}