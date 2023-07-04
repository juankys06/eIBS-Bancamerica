package datapro.eibs.invest;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEIE0500 extends datapro.eibs.master.SuperServlet {

	protected static final  int R_REPORT				= 1;
	protected static final  int R_C_REPORT				= 3;

	protected String LangPath = "E";

/**
 * JSReportManager constructor comment.
 */
public JSEIE0500() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEIE0500(int logType) {
	super(logType);

}
/**
 * This method was created in VisualAge.
 */
protected void procReqReport(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EIE050001Message msgRep = null;
		
	JBObjList beanList = null;
	UserPos userPO = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgRep = (EIE050001Message) mc.getMessageRecord("EIE050001");
		msgRep.setH01USERID(user.getH01USR());
		msgRep.setH01PROGRM("EIE0500");
		msgRep.setH01TIMSYS(getTimeStamp());
		msgRep.setH01SCRCOD("01");
		//msgRep.setH01OPECOD("0015");

		// all the fields here
		java.util.Enumeration enu = msgRep.fieldEnumeration();
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

		msgRep.send();
		msgRep.destroy();
		flexLog("EIE050001 Message Sent");

		// Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = new ELEERRMessage();
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
				
		} else 
			flexLog("Message " + newmessage.getFormatName() + " received.");

		// Receive Data
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EIE050001")) {
			msgRep = new datapro.eibs.beans.EIE050001Message();
			msgRep = (EIE050001Message) newmessage;
			flexLog("EIE050001 Message Received");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgPart", msgRep );
			ses.setAttribute("userPO", userPO);
			
			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EIE0310_report_confirm.jsp");
					callPage(LangPath + "EIE0310_report_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EIE0500_enter_reports.jsp");
					callPage(LangPath + "EIE0500_enter_reports.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} else {
			flexLog("Message " + newmessage.getFormatName() + " received.");
		}
			
		
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}


protected void procReqCReport(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	
		try {
			try {
				flexLog("About to call Page: " + LangPath + "EIE0500_redirect.jsp");
				callPage(LangPath + "EIE0500_redirect.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}						
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		
}

/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
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

		int screen = R_REPORT;

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
				case R_REPORT :
					procReqReport(mc, msgUser, req, res, session);
					break;
				case R_C_REPORT :
					procReqCReport(mc, msgUser, req, res, session);
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

protected void showERROR(ELEERRMessage m){
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