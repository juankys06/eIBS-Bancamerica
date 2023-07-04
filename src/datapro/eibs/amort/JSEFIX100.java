package datapro.eibs.amort;

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
import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEFIX100 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final  int R_ENTER_MAINTENANCE	    = 100;
	
	protected static final  int A_ENTER_MAINTENANCE		= 200;
	protected static final  int A_MAINTENANCE			= 400;	

	
	protected String LangPath = "S";

/**
 * JSEIE00000 constructor comment.
 */
public JSEFIX100() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEIE00000");
	
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

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterMaintenance(
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("RT");
		userPO.setPurpose("INQUIRY");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "EFIX100_fix_enter_parameters.jsp");
		callPage(LangPath + "EFIX100_fix_enter_parameters.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionEnterMaintenance(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFIX10001Message msgRT = null;
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
		msgRT = (EFIX10001Message) mc.getMessageRecord("EFIX10001");
		msgRT.setH01USERID(user.getH01USR());
		msgRT.setH01PROGRM("EFIX10001");
		msgRT.setH01TIMSYS(getTimeStamp());
		msgRT.setH01SCRCOD("01");
		msgRT.setH01OPECOD("0002");
		
		try {
			msgRT.setE01FIXBNK(req.getParameter("E01FIXBNK"));
		} catch (Exception e) {
		}


		msgRT.send();
		msgRT.destroy();
		flexLog("EDD009001 Message Sent");
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

		if (newmessage.getFormatName().equals("EFIX10001")) {
			try {
				msgRT = new EFIX10001Message();
				flexLog("Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgRT = (EFIX10001Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fix", msgRT);
			if (IsNotError) { // There are no errors
			try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EFIX100_fix_parameters.jsp");
					callPage(
						LangPath + "EFIX100_fix_parameters.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EFIX100_fix_enter_parameters.jsp");
						callPage(
							LangPath + "EFIX100_fix_enter_parameters.jsp",
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

protected  void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFIX10001Message msgInst = null;
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
	 	msgInst = (EFIX10001Message)ses.getAttribute("fix");
		msgInst.setH01USERID(user.getH01USR());
	 	msgInst.setH01PROGRM("EFIX00001");
	 	msgInst.setH01TIMSYS(getTimeStamp());
	 	msgInst.setH01SCRCOD("01");
	 	msgInst.setH01OPECOD("0005");

	 	// all the fields here
	 	java.util.Enumeration enu = msgInst.fieldEnumeration();
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

	 	//msgInst.send();
	 	mc.sendMessage(msgInst);
	 	msgInst.destroy();
	 	flexLog("EFIX03001 Message Sent");
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
					msgInst = new EFIX10001Message();
					
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				msgInst = (EFIX10001Message)newmessage;
				// showESD008004(msgInst);
				
							
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("fix", msgInst);
				ses.setAttribute("userPO", userPO);
				
								

				if (IsNotError) {  // There are no errors
					try {
						    flexLog("About to call Page: " + LangPath + "EFIX100_fix_enter_parameters.jsp");
							callPage(LangPath + "EFIX100_fix_enter_parameters.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
									
						try {
							flexLog("About to call Page: " + LangPath + "EFIX100_fix_parameters.jsp");
							callPage(LangPath + "EFIX100_fix_parameters.jsp", req, res);	
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

		int screen = R_ENTER_MAINTENANCE;
		
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
				
				case A_ENTER_MAINTENANCE :
					procActionEnterMaintenance(mc, msgUser, req, res, session);
					break;
				
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
					break;
				//Request
									
				case R_ENTER_MAINTENANCE :
					procReqEnterMaintenance(msgUser, req, res, session);
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
