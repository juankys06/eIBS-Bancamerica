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

public class JSEDD0930 extends datapro.eibs.master.SuperServlet {

    // entering options
	protected static final int R_ENTER				= 100;	
	protected static final int A_ENTER	 			= 200;
	
	protected static final int R_APPLAY				= 1;	
	protected static final int A_APPLAY	 			= 2;
	protected String LangPath = "S";

/**
 * JSEDD0931 constructor comment.
 */
public JSEDD0930() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDD0931");
	
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
						case A_APPLAY :
							procActionApplay(mc, msgUser, req, res, session);
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
		userPO.setPurpose("APPLAY");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0930_enter_cleaning_applay.jsp");
		callPage(LangPath + "EDD0930_enter_cleaning_applay.jsp", req, res);	
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
	EDD093001Message msgAPP = null;
	EDD093002Message msgList = null;
	JBObjList beanList = null;
	JBList chkList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
	 	msgAPP = (EDD093001Message)mc.getMessageRecord("EDD093001");
		msgAPP.setH01USERID(user.getH01USR());
	 	msgAPP.setH01PROGRM("EDD0930");
	 	msgAPP.setH01TIMSYS(getTimeStamp());
	 	msgAPP.setH01SCRCOD("01");
	 	msgAPP.setH01OPECOD("0004");

		// all the fields here
	 	try {
				msgAPP.setE01CNTFE1(req.getParameter("E01CNTFE1"));
		} catch (Exception e) {
				msgAPP.setE01CNTFE1("0");
		}
		try {
				msgAPP.setE01CNTFE2(req.getParameter("E01CNTFE2"));
		} catch (Exception e) {
				msgAPP.setE01CNTFE2("0");
		}
		try {
				msgAPP.setE01CNTFE3(req.getParameter("E01CNTFE3"));
		} catch (Exception e) {
				msgAPP.setE01CNTFE3("0");
		}
	 	msgAPP.send();
	 	msgAPP.destroy();
	
		
	// Receive Error Message
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	
	// Receive Data
	
	   newmessage = mc.receiveMessage();
	   String marker = "";
		
	  if (newmessage.getFormatName().equals("EDD093002")) {
			
			beanList =  new JBObjList();
			chkList =  new JBList(); 
			
			while (true) {
	
				msgList = (EDD093002Message)newmessage;
				marker = msgList.getH02FLGMAS();
				msgList.setHandler(null);
				if (marker.equals("*")) {					
					break;
				}
				else {
					beanList.addRow(msgList);
					chkList.addRow("","");
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
				
				ses.setAttribute("applayList", beanList);
				ses.setAttribute("chkList", chkList);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0930_cleaning_applay_list.jsp");
					callPage(LangPath + "EDD0930_cleaning_applay_list.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors												
				try {
					flexLog("About to call Page: " + LangPath + "EDD0930_enter_cleaning_applay.jsp");
					callPage(LangPath + "EDD0930_enter_cleaning_applay.jsp", req, res);	
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
protected ELEERRMessage procSendData(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, EDD093002Message msgList)
			throws ServletException, IOException {
	
	ELEERRMessage msgError = null;
	EDD093003Message msgAPP = null;
	MessageRecord newmessage = null;
	
	try {
	  		msgAPP = (EDD093003Message)mc.getMessageRecord("EDD093003");
			msgAPP.setH03USERID(user.getH01USR());
	 		msgAPP.setH03PROGRM("EDD0933");
	 		msgAPP.setH03TIMSYS(getTimeStamp());
	 		msgAPP.setH03SCRCOD("01");
	 		msgAPP.setH03OPECOD("0005");

			try {
				msgAPP.setE03CNTFE1(req.getParameter("E03CNTFE1"));
			} catch (Exception e) {
				msgAPP.setE03CNTFE1("0");
			}
			try {
				msgAPP.setE03CNTFE2(req.getParameter("E03CNTFE2"));
			} catch (Exception e) {
				msgAPP.setE03CNTFE2("0");
			}
			try {
				msgAPP.setE03CNTFE3(req.getParameter("E03CNTFE3"));
			} catch (Exception e) {
				msgAPP.setE03CNTFE3("0");
			}
	 		try {
				msgAPP.setE03ACTION(req.getParameter("E03ACTION"));
			} catch (Exception e) {
				msgAPP.setE03ACTION("0");
			}
			try {
				msgAPP.setE03CODBNK(msgList.getE02CODBNK());
			} catch (Exception e) {
				msgAPP.setE03CODBNK("0");
			}
			try {
				msgAPP.setE03CODSUC(msgList.getE02CODSUC());
			} catch (Exception e) {
				msgAPP.setE03CODSUC("0");
			}
			try {
				msgAPP.setE03CODCCY(msgList.getE02CODCCY());
			} catch (Exception e) {
				msgAPP.setE03CODCCY("0");
			}
	 		msgAPP.send();
	 		msgAPP.destroy();
	
		
			// Receive Error Message
	
	  		newmessage = mc.receiveMessage();
	  
	  		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;					
	  		}
	  		else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}			
	return msgError;		
}
			
/**
 * This method was created in VisualAge.
 */
protected void procActionApplay(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	
	
	EDD093002Message msgList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	JBList chkList =  null;
	JBObjList beanList =  null;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	chkList = (datapro.eibs.beans.JBList)ses.getAttribute("chkList");
	beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("applayList");
	
	// Send Initial data
	try
	{
		//get selected elements
		for (int i=0;i <= chkList.getLastRec();i++) {
			if (req.getParameter("SEL"+i) != null) {
				chkList.setRecord("checked",i);
			}
		}
		// send selected elements
		int i=0;		
		while (i <= chkList.getLastRec()) {
			
			chkList.setCurrentRow(i);
			if (chkList.getRecord().equals("checked")) { //selected
				
				beanList.setCurrentRow(i);
				msgList = (EDD093002Message) beanList.getRecord();
				
				msgError = procSendData(mc,user,req,msgList);
				if (msgError != null) {	
	 		    	IsNotError = msgError.getERRNUM().equals("0");
	 		    	if (IsNotError) {  // There are no errors
	 		    		beanList.removeRow(i);
	 		    		chkList.removeRow(i);
	 		    	} else break;
				} else break;
			} else i++;
		} //end for
	
	    flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("chkList", chkList);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("applayList", beanList);

		if (IsNotError) {  // There are no errors
			
				try {
					flexLog("About to call Page: " + LangPath + "EDD0930_cleaning_applay_confirm.jsp");
					callPage(LangPath + "EDD0930_cleaning_applay_confirm.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
		}
		else {  // There are errors
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0930_cleaning_applay_list.jsp");
					callPage(LangPath + "EDD0930_cleaning_applay_list.jsp", req, res);	
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

}