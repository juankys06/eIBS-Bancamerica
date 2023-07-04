package datapro.eibs.params;

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

public class JSETLR100 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final  int R_ENTER_MAINTENANCE	    = 1;
	protected static final  int R_LIST					= 2;
	protected static final  int A_LIST	   				= 3;
	protected static final  int A_MAINTENANCE	    	= 4;

	
	protected String LangPath = "S";

/**
 * JSETLR100 constructor comment.
 */
public JSETLR100() {
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
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "ETLR100_enter_currency.jsp");
		callPage(LangPath + "ETLR100_enter_currency.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procReqList(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ETLR10001Message msgList = null;
	JBObjList ppList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opt = req.getParameter("opt");
	if (opt == null) opt = "";

	// Send Initial data
	try {
		msgList = (ETLR10001Message) mc.getMessageRecord("ETLR10001");
		msgList.setH01USERID(user.getH01USR());
		msgList.setH01PROGRM("ETLR100");
		msgList.setH01TIMSYS(getTimeStamp());
		msgList.setH01SCRCOD("01");
		msgList.setH01OPECOD("0015");

		try {
			msgList.setE01TLDCCY(req.getParameter("E01TLDCCY"));
		}
		catch (Exception e){
			msgList.setE01TLDCCY("");
		}
		
		msgList.send();
		msgList.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
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

		if (newmessage.getFormatName().equals("ETLR10001")) {
				ppList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
				
				userPO.setCurrency(req.getParameter("E01TLDCCY"));
			
				while (true) {
					msgList = (ETLR10001Message) newmessage;
					marker = msgList.getE01OPECDE();

					if (marker.equals("*")) {
						userPO.setHeader1(msgList.getE01RATDSC());
						ppList.setShowNext(false);
						break;
					} else {
						ppList.addRow(msgList);
						if (marker.equals("+")) {
							ppList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ppList", ppList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ETLR100_denominations_list.jsp");
					callPage(LangPath + "ETLR100_denominations_list.jsp" , req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}

protected void procActionList(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	ESS0030DSMessage msgUser = null;
	ETLR10001Message msgDen = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	msgError = new datapro.eibs.beans.ELEERRMessage();

	try {
		int option = Integer.parseInt(req.getParameter("opt"));

		msgDen = null;
		msgDen = (ETLR10001Message) mc.getMessageRecord("ETLR10001");

		switch (option) {
			case 1 : // New
				msgDen.setE01TLDCCY(userPO.getCurrency());
				msgDen.setE01RATDSC(userPO.getHeader1());
				break;

			case 2 : // Maintenance
				msgDen.setE01TLDCCY(req.getParameter("CCY"));
				msgDen.setE01RATDSC(req.getParameter("DSC"));
				msgDen.setE01TLDNME(req.getParameter("NME"));
				msgDen.setE01TLDVLU(req.getParameter("VAL"));
				msgDen.setE01TLDPCK(req.getParameter("PAQ"));
				msgDen.setE01TLDTYP(req.getParameter("TYP"));
				break;

		}

		ses.setAttribute("userPO", userPO);
		ses.setAttribute("msgDen", msgDen);
		ses.setAttribute("error", msgError);
				
		try {
			flexLog("About to call Page: " + LangPath + "ETLR100_denominations_basic.jsp");
			callPage(LangPath + "ETLR100_denominations_basic.jsp" , req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}




	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}


protected void procActionMaintenance(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ETLR10001Message msgDen = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	int acctype = 0;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgDen = (ETLR10001Message) mc.getMessageRecord("ETLR10001");
		msgDen.setH01USERID(user.getH01USR());
		msgDen.setH01PROGRM("ETLR100");
		msgDen.setH01TIMSYS(getTimeStamp());
		msgDen.setH01OPECOD("0005");

		// all the fields here
		java.util.Enumeration enu = msgDen.fieldEnumeration();
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
			
		mc.sendMessage(msgDen);
		msgDen.destroy();
		flexLog("ETLR10001 Message Sent");
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

		if (newmessage.getFormatName().equals("ETLR10001")) {
			try {
				msgDen = new ETLR10001Message();
				flexLog("ETLR10001 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgDen = (ETLR10001Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgDen", msgDen);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSETLR100?SCREEN=2&E01TLDCCY=" + userPO.getCurrency());
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ETLR100_denominations_basic.jsp");
					callPage(LangPath
						+ "ETLR100_denominations_basic.jsp", req, res);
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

		
				//Request
									
				case R_ENTER_MAINTENANCE :
					procReqEnterMaintenance(msgUser, req, res, session);
					break;			
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
			
				//Action
				case A_LIST :
					procActionList(mc, msgUser, req, res, session);
					break;
				case A_MAINTENANCE :
					procActionMaintenance(mc, msgUser, req, res, session);
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
