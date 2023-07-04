package datapro.eibs.products;

/**========================================================
 * Payment Schedule - Quotation
 * Creation date: 05/23/2007
 * @author: Henry Guamantica
 ==========================================================*/
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0900 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_SCH_QUOTE		= 1;
	protected static final int A_SCH_QUOTE		= 2;	

	protected String LangPath = "S";

/**
 * JSEDL0900 constructor comment.
 */
public JSEDL0900() {
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
/**
 * This method was created in VisualAge.
 */


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

		int screen = R_SCH_QUOTE;
		
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
				case R_SCH_QUOTE : 
					procReqSchQuote(msgUser, req, res, session);
					break;	

				// Action 
				case A_SCH_QUOTE : 
					procActionSchQuote(mc, msgUser, req, res, session);
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
protected void procReqSchQuote(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("LN");
		userPO.setPurpose("QUOTATION");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
	} catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "EDL0900_ln_crn_quote.jsp");
		callPage(LangPath + "EDL0900_ln_crn_quote.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


/**
 * This method was created in VisualAge.
 */
protected void procActionSchQuote(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
	MessageRecord newmessage = null;
	EDL090004Message QLoans = null;
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

	String opCode = "0004";

	// Send Initial data
	try
	{
		//QLoans = (EDL090004Message)ses.getAttribute("QuoteLoans");
		QLoans = (EDL090004Message)mc.getMessageRecord("EDL090004");
		QLoans.setH04USERID(user.getH01USR());
		QLoans.setH04PROGRM("EDL0900");
		QLoans.setH04TIMSYS(getTimeStamp());
		QLoans.setH04OPECOD(opCode);
		QLoans.setE04DEAACC("0");
	// Get Parameters from page
		try {
			QLoans.setE04DEAOAM(req.getParameter("E04DEAOAM"));
			QLoans.setE04DEAOD1(req.getParameter("E04DEAOD1"));
			QLoans.setE04DEAOD2(req.getParameter("E04DEAOD2"));
			QLoans.setE04DEAOD3(req.getParameter("E04DEAOD3"));
			QLoans.setE04DEAMD1(req.getParameter("E04DEAMD1"));
			QLoans.setE04DEAMD2(req.getParameter("E04DEAMD2"));
			QLoans.setE04DEAMD3(req.getParameter("E04DEAMD3"));
			QLoans.setE04DEATRM(req.getParameter("E04DEATRM"));
			QLoans.setE04DEATRC(req.getParameter("E04DEATRC"));
			QLoans.setE04DEARTE(req.getParameter("E04DEARTE"));
			QLoans.setE04DEABAS(req.getParameter("E04DEABAS"));
			QLoans.setE04DEAICT(req.getParameter("E04DEAICT"));
		} catch (Exception e1) {
			flexLog("Error Receiving Parameters: " + e1);			
		} 
	
		
	// Sending data to host
		QLoans.send();	
		QLoans.destroy();

		flexLog("EDL090004 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data from host
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
				flexLog("About to call Page: " + LangPath + "EDL0900_ln_crn_quote.jsp");
				callPage(LangPath + "EDL0900_ln_crn_quote.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		
		else if (newmessage.getFormatName().equals("EDL090004")) {
			try {
				QLoans = new datapro.eibs.beans.EDL090004Message();

				flexLog("EDL090004 Message Received");

				QLoans = (EDL090004Message)newmessage;

				//Header
				userPO.setIdentifier(QLoans.getE04DEAACC());
				userPO.setHeader1(QLoans.getE04DEAPRO());
				userPO.setHeader2(QLoans.getE04DEACUN());
				userPO.setHeader3(QLoans.getE04CUSNA1());
				userPO.setCurrency(QLoans.getE04DEACCY());
				
				userPO.setHeader10(Util.formatDate(QLoans.getE04DEAOD1(),QLoans.getE04DEAOD2(),QLoans.getE04DEAOD3())); //Opening Date
				userPO.setHeader11(Util.formatDate(QLoans.getE04DEAMD1(),QLoans.getE04DEAMD2(),QLoans.getE04DEAMD3())); //Maturity Date
				userPO.setHeader12(Util.formatCCY(QLoans.getE04DEAOAM()));  // Original Amount
				userPO.setHeader13(Util.formatCell(QLoans.getE04DEARTE())); // Rate
				userPO.setHeader14(Util.formatCell(QLoans.getE04DEABAS())); // Basis
				userPO.setHeader15(Util.formatCell(QLoans.getE04DEAICT())); // Interest Type
				userPO.setPurpose("QUOTATION");
			}
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			//QuoteLoans = (EDL090004Message)newmessage;
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Servlet: JSEDL0900");
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.products.JSEDL0160?SCREEN=8&Type=Q");
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