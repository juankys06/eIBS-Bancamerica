package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (7/28/04 10:18:55 AM)
 * @author: Manuel Justo
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD0575 extends datapro.eibs.master.SuperServlet {

	
	static final int R_NEW 			= 1;
	
	static final int R_MAINTENANCE 	= 100;
	static final int A_MAINTENANCE 	= 200;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0575() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = new UserPos();

		ses.setAttribute("userPO", userPO);
		
		try {
			flexLog("About to call Page: " + LangPath + "EDD0575_iota_enter.jsp");
			callPage(LangPath + "EDD0575_iota_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD057501Message msgAcc = null;
		ELEERRMessage msgError = null;
		String flag = "";
		UserPos userPO = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		ses.setAttribute("userPO", userPO);
		
		// Send Initial data
		try
		{   
			msgAcc = (EDD057501Message)mc.getMessageRecord("EDD057501");
			msgAcc.setH01USERID(user.getH01USR());
			msgAcc.setH01PROGRM("EDD0575");
			msgAcc.setH01TIMSYS(getTimeStamp());
			msgAcc.setH01SCRCOD("01");
			msgAcc.setH01OPECOD("0002");
			
			try {
				msgAcc.setE01FRMACC(req.getParameter("E01FRMACC"));
			}
			catch (Exception e)	{
				msgAcc.setE01FRMACC("0");
			}

			try {
				msgAcc.setE01TOACC(req.getParameter("E01TOACC"));
			}
			catch (Exception e)	{
				msgAcc.setE01TOACC("0");
			}

			msgAcc.send();	
			//msgAcc.destroy();

			flexLog("EDD057501 Sent");
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("account", msgAcc);
				ses.setAttribute("userPO", userPO);
		  	} 		
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
	  
		  	if (newmessage.getFormatName().equals("EDD057501")) {
				try {
					msgAcc = new datapro.eibs.beans.EDD057501Message();
					flexLog("EDD057501 Received");
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgAcc = (EDD057501Message)newmessage;
			
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("account", msgAcc);
			}
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	

		if (IsNotError) {
			try {
				flexLog("About to call Page: " + LangPath + "EDD0575_iota_maintenance.jsp");
				callPage(LangPath + "EDD0575_iota_maintenance.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else {
			try {
				flexLog("About to call Page: " + LangPath + "EDD0575_iota_enter.jsp");
				callPage(LangPath + "EDD0575_iota_enter.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
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
			EDD057501Message msgAcc = null;
			ELEERRMessage msgError = null;
			String flag = "";
			UserPos userPO = null;
			boolean IsNotError = false;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			ses.setAttribute("userPO", userPO);
		
			// Send Initial data
			try
			{   
				msgAcc = (EDD057501Message)mc.getMessageRecord("EDD057501");
				msgAcc.setH01USERID(user.getH01USR());
				msgAcc.setH01PROGRM("EDD0575");
				msgAcc.setH01TIMSYS(getTimeStamp());
				msgAcc.setH01SCRCOD("01");
				msgAcc.setH01OPECOD("0005");
			
				// all the fields here
				java.util.Enumeration enu = msgAcc.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase().trim();
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}

				msgAcc.send();	
				msgAcc.destroy();

				flexLog("EDD057501 Sent");
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

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
			  	} 
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
	  
				if (newmessage.getFormatName().equals("EDD057501")) {
					try {
						msgAcc = new datapro.eibs.beans.EDD057501Message();
						flexLog("EDD057501 Received");
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}

					msgAcc = (EDD057501Message)newmessage;
			
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("account", msgAcc);
				} 
			}
			catch (Exception e)	{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}	

			if (IsNotError) {
				msgAcc = new EDD057501Message();
				msgError = new ELEERRMessage ();
				
				ses.setAttribute("account", msgAcc);
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EDD0575_iota_enter.jsp");
					callPage(LangPath + "EDD0575_iota_enter.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EDD0575_iota_maintenance.jsp");
					callPage(LangPath + "EDD0575_iota_maintenance.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_NEW;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Requests
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINTENANCE :
						procReqMaintenance(mc, msgUser, req, res, session);
						break;	
						// Actions
					case A_MAINTENANCE :
						procActionMaintenance(mc, msgUser, req, res, session);
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
				}
				finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	private synchronized void showERROR(ELEERRMessage m) {
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