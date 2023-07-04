package datapro.eibs.forex;

/**
 * Treasury FRA Inquiry deals.
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

public class JSETR0120I extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";

/**
 * Constructor.
 */
public JSETR0120I() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSETR0120I");
	
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
protected void procActionInqDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ETR0120DSMessage msgFex = null;
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
		msgFex = (ETR0120DSMessage)mc.getMessageRecord("ETR0120DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("ETR0120");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0002");	
	 	
		try {
		 	if (req.getParameter("E01WFRCUN") != null)
		 	  msgFex.setE01WFRCUN(req.getParameter("E01WFRCUN"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRCUN("0");
		}

		try {
		 	if (req.getParameter("E01WFRPRO") != null)
		 	  msgFex.setE01WFRPRO(req.getParameter("E01WFRPRO"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRPRO("FRA");
		}
		
		try {
		 	if (req.getParameter("E01WFRREF") != null)
		 	  msgFex.setE01WFRREF(req.getParameter("E01WFRREF"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRREF("0");
		}
		
		try {
		 	if (req.getParameter("E01WFRDID") != null)
		 	  msgFex.setE01WFRDID(req.getParameter("E01WFRDID"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01WFRDID("");
		}
								
	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("ETR0120DS Message Sent");
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
		if (newmessage.getFormatName().equals("ETR0120DS")) {
			try {
				msgFex = new ETR0120DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (ETR0120DSMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fra", msgFex);
			
 
			if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ETR0120T_fra_basic.jsp");
						callPage(LangPath + "ETR0120T_fra_basic.jsp", req, res);	
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

		int screen = 1;
		
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
				case 100 : 
					procActionInqDeal(mc, msgUser, req, res, session);
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