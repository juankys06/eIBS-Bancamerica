package datapro.eibs.reports;

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

public class JSELS0100 extends datapro.eibs.master.SuperServlet {

    // entering options
	protected static final int A_ENTER	= 100;	

	protected static final int A_SUBMIT	= 200;	
	
	protected String LangPath = "S";

/**
 * JSEDD0931 constructor comment.
 */
public JSELS0100() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSELS0100");
	
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

		int screen = A_ENTER;
		
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
			
			
			
			try
				{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, super.iniSocket + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
		
					switch (screen) {
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_SUBMIT :
							procActionSubmit(mc, msgUser, req, res, session);
							break;			
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				}
			catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
					s.close();
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
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELS010001Message msgBATCH = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgBATCH = (ELS010001Message) mc.getMessageRecord("ELS010001");
	 	
		// all the fields here
	 	try {
				msgBATCH.setE01COLATR(req.getParameter("COLA"));
		} catch (Exception e) {
				msgBATCH.setE01COLATR("0");
		}
		try {
				msgBATCH.setE01NOMPRG(req.getParameter("PROG"));
		} catch (Exception e) {
				msgBATCH.setE01NOMPRG("");
		}
		try {
				msgBATCH.setE01NOMTRA(req.getParameter("TRAB"));
		} catch (Exception e) {
				msgBATCH.setE01NOMTRA("0");
		}
		try {
				msgBATCH.setE01ACTION(req.getParameter("ACTION"));
		} catch (Exception e) {
				msgBATCH.setE01ACTION("0");
		}
		try {
				msgBATCH.setE01DESPRG(req.getParameter("DESPRG"));
		} catch (Exception e) {
				msgBATCH.setE01DESPRG("");
		}
		try {
				msgBATCH.setE01DESDT1(req.getParameter("DESDT1"));
		} catch (Exception e) {
				msgBATCH.setE01DESDT1("");
		}
		try {
				msgBATCH.setE01DESDT2(req.getParameter("DESDT2"));
		} catch (Exception e) {
				msgBATCH.setE01DESDT2("");
		}
		try {
				msgBATCH.setE01DESCU1(req.getParameter("CUSCU1"));
		} catch (Exception e) {
				msgBATCH.setE01DESCU1("");
		}
		try {
				msgBATCH.setE01DESSE1(req.getParameter("SECUE1"));
		} catch (Exception e) {
				msgBATCH.setE01DESSE1("");
		}
		try {
				msgBATCH.setE01DESSE2(req.getParameter("SECUE2"));
		} catch (Exception e) {
				msgBATCH.setE01DESSE2("");
		}
		try {
				msgBATCH.setE01CODCN1(req.getParameter("CODTB1"));
		} catch (Exception e) {
				msgBATCH.setE01CODCN1("");
		}
		try {
				msgBATCH.setE01DESTB1(req.getParameter("DESTB1"));
		} catch (Exception e) {
				msgBATCH.setE01DESTB1("");
		}
		try {
				msgBATCH.setE01CODCN2(req.getParameter("CODTB2"));
		} catch (Exception e) {
				msgBATCH.setE01CODCN2("");
		}
		try {
				msgBATCH.setE01DESTB2(req.getParameter("DESTB2"));
		} catch (Exception e) {
				msgBATCH.setE01DESTB2("");
		}
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("batch", msgBATCH);
								
				try {
					flexLog("About to call Page: " + LangPath + "ELS0100_submit_report.jsp");
					callPage(LangPath + "ELS0100_submit_report.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
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
protected void procActionSubmit(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELS010001Message msgBATCH = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		
		flexLog("Send Initial Data");
		msgBATCH = (ELS010001Message)mc.getMessageRecord("ELS010001");
		msgBATCH.setH01USERID(user.getH01USR());
		msgBATCH.setH01PROGRM("ELS0100");
		msgBATCH.setH01TIMSYS(getTimeStamp());
		msgBATCH.setH01SCRCOD("01");
	 	
		// all the fields here
		try {
				msgBATCH.setE01COLATR(req.getParameter("COLA"));
		} catch (Exception e) {
				msgBATCH.setE01COLATR("0");
		}
		try {
				msgBATCH.setE01NOMPRG(req.getParameter("PROG"));
		} catch (Exception e) {
				msgBATCH.setE01NOMPRG("");
		}
		try {
				msgBATCH.setE01NOMTRA(req.getParameter("TRAB"));
		} catch (Exception e) {
				msgBATCH.setE01NOMTRA("0");
		}
		try {
				msgBATCH.setE01ACTION(req.getParameter("ACTION"));
		} catch (Exception e) {
				msgBATCH.setE01ACTION("0");
		}
		try {
				msgBATCH.setE01DESPRG(req.getParameter("DESPRG"));
		} catch (Exception e) {
				msgBATCH.setE01DESPRG("");
		}
		try {
				msgBATCH.setE01DESDT1(req.getParameter("DESDT1"));
		} catch (Exception e) {
				msgBATCH.setE01DESDT1("");
		}
		try {
				msgBATCH.setE01DESDT2(req.getParameter("DESDT2"));
		} catch (Exception e) {
				msgBATCH.setE01DESDT2("");
		}
		try {
				msgBATCH.setE01DESSE1(req.getParameter("DESSE1"));
		} catch (Exception e) {
				msgBATCH.setE01DESSE1("");
		}
		try {
				msgBATCH.setE01DESSE2(req.getParameter("DESSE2"));
		} catch (Exception e) {
				msgBATCH.setE01DESSE2("");
		}

		// all the fields here
		java.util.Enumeration enu = msgBATCH.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		}


		msgBATCH.send();
		msgBATCH.destroy();
	
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
	   		
	   if (newmessage.getFormatName().equals("ELS010001")) {
			
						
			msgBATCH = (ELS010001Message)newmessage;
				
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("batch", msgBATCH);
			
			if (IsNotError) {  // There are no errors
								
				try {
					flexLog("About to call Page: " + LangPath + "ELS0100_cleaning_confirm.jsp");
					callPage(LangPath + "ELS0100_cleaning_confirm.jsp", req, res);	
					}
				catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			}
			else {  // There are errors												
				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);	
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