package datapro.eibs.cleaning;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEDD0932 extends datapro.eibs.master.SuperServlet {

    // entering options
	protected static final int R_ENTER				= 100;	
	protected static final int A_ENTER	 			= 200;
	
	protected static final int R_LOAD				= 1;	
	protected static final int A_LOAD	 			= 2;
	protected String LangPath = "S";

/**
 * JSEDD0932 constructor comment.
 */
public JSEDD0932() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDD0932");
	
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

		int screen = R_ENTER;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
			
			if (screen == R_ENTER) {
				procReqEnter(msgUser, req, res, session);				
			} else { 
			
				try
				{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 29);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
		
					switch (screen) {
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_LOAD :
							procActionLoad(mc, msgUser, req, res, session);
							break;				
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 29;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//return;
				}
				finally {
					s.close();
				}
			
			}
		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	}

}

/**
 * This method was created in VisualAge.
 */
protected void  procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("CLEANING");
		userPO.setPurpose("LOAD");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0932_enter_cleaning_load.jsp");
		callPage(LangPath + "EDD0932_enter_cleaning_load.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD093201Message msgLOAD = null;
	EDD093202Message msgList = null;
	JBObjList beanList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgLOAD = (EDD093201Message)mc.getMessageRecord("EDD093201");
		msgLOAD.setH01USERID(user.getH01USR());
	 	msgLOAD.setH01PROGRM("EDD0932");
	 	msgLOAD.setH01TIMSYS(getTimeStamp());
	 	msgLOAD.setH01SCRCOD("01");
	 	msgLOAD.setH01OPECOD("0004");

		// all the fields here
	 	try {
				msgLOAD.setE01CNTFE1(req.getParameter("E01CNTFE1"));
		} catch (Exception e) {
				msgLOAD.setE01CNTFE1("0");
		}
		try {
				msgLOAD.setE01CNTFE2(req.getParameter("E01CNTFE2"));
		} catch (Exception e) {
				msgLOAD.setE01CNTFE2("0");
		}
		try {
				msgLOAD.setE01CNTFE3(req.getParameter("E01CNTFE3"));
		} catch (Exception e) {
				msgLOAD.setE01CNTFE3("0");
		}
	 	msgLOAD.send();
	 	msgLOAD.destroy();
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
		String marker = "";
		
		if (newmessage.getFormatName().equals("EDD093202")) {
			
			beanList =  new JBObjList(); 
			while (true) {
	
				msgList = (EDD093202Message)newmessage;
				marker = msgList.getH02FLGMAS();
				msgList.setHandler(null);
				if (marker.equals("*")) {					
					break;
				}
				else {
					beanList.addRow(msgList);
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			if (IsNotError) {  // There are no errors
				//date of process
				userPO.setHeader10(req.getParameter("E01CNTFE1"));
				userPO.setHeader11(req.getParameter("E01CNTFE2"));
				userPO.setHeader12(req.getParameter("E01CNTFE3"));
				
				ses.setAttribute("loadList", beanList);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0932_cleaning_load_list.jsp");
					callPage(LangPath + "EDD0932_cleaning_load_list.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors												
				try {
					flexLog("About to call Page: " + LangPath + "EDD0932_enter_cleaning_load.jsp");
					callPage(LangPath + "EDD0932_enter_cleaning_load.jsp", req, res);	
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
protected void procActionLoad(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD093203Message msgLOAD = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgLOAD = (EDD093203Message)mc.getMessageRecord("EDD093203");
		msgLOAD.setH03USERID(user.getH01USR());
	 	msgLOAD.setH03PROGRM("EDD0933");
	 	msgLOAD.setH03TIMSYS(getTimeStamp());
	 	msgLOAD.setH03SCRCOD("01");
	 	msgLOAD.setH03OPECOD("0005");

		try {
				msgLOAD.setE03CNTFE1(req.getParameter("E03CNTFE1"));
		} catch (Exception e) {
				msgLOAD.setE03CNTFE1("0");
		}
		try {
				msgLOAD.setE03CNTFE2(req.getParameter("E03CNTFE2"));
		} catch (Exception e) {
				msgLOAD.setE03CNTFE2("0");
		}
		try {
				msgLOAD.setE03CNTFE3(req.getParameter("E03CNTFE3"));
		} catch (Exception e) {
				msgLOAD.setE03CNTFE3("0");
		}
	 	try {
				msgLOAD.setE03ACTION(req.getParameter("E03ACTION"));
		} catch (Exception e) {
				msgLOAD.setE03ACTION("0");
		}

	 	msgLOAD.send();
	 	msgLOAD.destroy();
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

		if (newmessage.getFormatName().equals("EDD093203")) {
			
			msgLOAD = (EDD093203Message)newmessage;
			

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDD0932_cleaning_load_confirm.jsp");
					callPage(LangPath + "EDD0932_cleaning_load_confirm.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				//ses.setAttribute("cleaningCNTRL", msgCTRL);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0932_cleaning_load_list.jsp");
					callPage(LangPath + "EDD0932_cleaning_load_list.jsp", req, res);	
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

}