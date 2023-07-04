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
import java.util.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSESD3000 extends datapro.eibs.master.SuperServlet {

	
	static final int R_NEW_PRODUCT 	= 1;
	static final int R_NEW_LEDGER  	= 2;
	
	static final int R_CONFIRM	 	= 100;
	static final int A_CONFIRM	 	= 200;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD3000() {
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
	
	protected void procReqNewProduct(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = new UserPos();

		String type = req.getParameter("Type");
		userPO.setType(type);
		ses.setAttribute("userPO", userPO);
		
		try {
			flexLog("About to call Page: " + LangPath + "ESD3000_acc_product_change.jsp");
			callPage(LangPath + "ESD3000_acc_product_change.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqNewLedger(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = new UserPos();

		String type = req.getParameter("Type");
		userPO.setType(type);
		ses.setAttribute("userPO", userPO);

		if (userPO.getType().equals("92")) {
			try {
				flexLog("About to call Page: " + LangPath + "ESD3000_amort_ledger_change.jsp");
				callPage(LangPath + "ESD3000_amort_ledger_change.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else {
			try {
				flexLog("About to call Page: " + LangPath + "ESD3000_acc_ledger_change.jsp");
				callPage(LangPath + "ESD3000_acc_ledger_change.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}

	protected void procReqConfirmation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD300001Message msgAcc = null;
		ELEERRMessage msgError = null;
		String flag = "";
		UserPos userPO = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		ses.setAttribute("userPO", userPO);
		
		// Send Initial data
		try
		{   
			msgAcc = (ESD300001Message)mc.getMessageRecord("ESD300001");
			msgAcc.setH01USERID(user.getH01USR());
			msgAcc.setH01PROGRM("ESD3000");
			msgAcc.setH01TIMSYS(getTimeStamp());
			msgAcc.setH01SCRCOD("01");
			msgAcc.setH01OPECOD("0002");
			
			try {
				msgAcc.setH01FLGWK1(req.getParameter("H01FLGWK1"));
				flag = req.getParameter("H01FLGWK1");
			}
			catch (Exception e)	{
				msgAcc.setH01FLGWK1("0");
			}

			Enumeration enu = msgAcc.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while(enu.hasMoreElements())  {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if(value != null)
						field.setString(value);
				}
				catch(Exception exception) {}
			}

			try {
				msgAcc.setE01OLDACD(userPO.getType());
			}
			catch (Exception e)	{
				msgAcc.setE01OLDACD("0");
			}

			msgAcc.send();	
			msgAcc.destroy();

			flexLog("ESD300001 Sent");
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
	  
		  	if (newmessage.getFormatName().equals("ESD300001")) {
				try {
					msgAcc = new datapro.eibs.beans.ESD300001Message();
					flexLog("ESD300001 Received");
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgAcc = (ESD300001Message)newmessage;
			
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
				if (flag.equals("1")) {
					flexLog("About to call Page: " + LangPath + "ESD3000_acc_product_change_confirmation.jsp");
					callPage(LangPath + "ESD3000_acc_product_change_confirmation.jsp", req, res);	
				} else if (flag.equals("2")) {
					if (userPO.getType().equals("92")) {
						flexLog("About to call Page: " + LangPath + "ESD3000_amort_ledger_change_confirmation.jsp");
						callPage(LangPath + "ESD3000_amort_ledger_change_confirmation.jsp", req, res);	
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD3000_acc_ledger_change_confirmation.jsp");
						callPage(LangPath + "ESD3000_acc_ledger_change_confirmation.jsp", req, res);	
					}
				}
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else {
			try {
				if (flag.equals("1")) {
					flexLog("About to call Page: " + LangPath + "ESD3000_acc_product_change.jsp");
					callPage(LangPath + "ESD3000_acc_product_change.jsp", req, res);	
				} else if (flag.equals("2")) {
					if (userPO.getType().equals("92")) {
						flexLog("About to call Page: " + LangPath + "ESD3000_amort_ledger_change.jsp");
						callPage(LangPath + "ESD3000_amort_ledger_change.jsp", req, res);	
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD3000_acc_ledger_change.jsp");
						callPage(LangPath + "ESD3000_acc_ledger_change.jsp", req, res);	
					}
				}
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}

	protected void procActionConfirmation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			ESD300001Message msgAcc = null;
			ELEERRMessage msgError = null;
			String flag = "";
			UserPos userPO = null;
			boolean IsNotError = false;
		
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			ses.setAttribute("userPO", userPO);
		
			// Send Initial data
			try
			{   
				msgAcc = (ESD300001Message)mc.getMessageRecord("ESD300001");
				msgAcc.setH01USERID(user.getH01USR());
				msgAcc.setH01PROGRM("ESD3000");
				msgAcc.setH01TIMSYS(getTimeStamp());
				msgAcc.setH01SCRCOD("01");
				msgAcc.setH01OPECOD("0005");
			
				try {
					msgAcc.setH01FLGWK1(req.getParameter("H01FLGWK1"));
					flag = req.getParameter("H01FLGWK1");
				}
				catch (Exception e)	{
					msgAcc.setH01FLGWK1("0");
				}

				Enumeration enu = msgAcc.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while(enu.hasMoreElements())  {
					field = (MessageField)enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if(value != null)
							field.setString(value);
					}
					catch(Exception exception) {}
				}

				try {
					msgAcc.setE01OLDACD(userPO.getType());
				}
				catch (Exception e)	{
					msgAcc.setE01OLDACD("0");
				}

				msgAcc.send();	
				msgAcc.destroy();

				flexLog("ESD300001 Sent");
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
	  
				if (newmessage.getFormatName().equals("ESD300001")) {
					try {
						msgAcc = new datapro.eibs.beans.ESD300001Message();
						flexLog("ESD300001 Received");
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}

					msgAcc = (ESD300001Message)newmessage;
			
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
				msgAcc = new ESD300001Message();
				msgError = new ELEERRMessage ();
				
				ses.setAttribute("account", msgAcc);
				ses.setAttribute("error", msgError);
				try {
					if (flag.equals("1")) {
						flexLog("About to call Page: " + LangPath + "ESD3000_acc_product_change.jsp");
						callPage(LangPath + "ESD3000_acc_product_change.jsp", req, res);	
					} else if (flag.equals("2")) {
						if (userPO.getType().equals("92")) {
							flexLog("About to call Page: " + LangPath + "ESD3000_amort_ledger_change.jsp");
							callPage(LangPath + "ESD3000_amort_ledger_change.jsp", req, res);	
						}
						else {
							flexLog("About to call Page: " + LangPath + "ESD3000_acc_ledger_change.jsp");
							callPage(LangPath + "ESD3000_acc_ledger_change.jsp", req, res);	
						}
					}
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				try {
					if (flag.equals("1")) {
						flexLog("About to call Page: " + LangPath + "ESD3000_acc_product_change_confirmation.jsp");
						callPage(LangPath + "ESD3000_acc_product_change_confirmation.jsp", req, res);	
					} else if (flag.equals("2")) {
						if (userPO.getType().equals("92")) {
							flexLog("About to call Page: " + LangPath + "ESD3000_amort_ledger_change_confirmation.jsp");
							callPage(LangPath + "ESD3000_amort_ledger_change_confirmation.jsp", req, res);	
						}
						else {
							flexLog("About to call Page: " + LangPath + "ESD3000_acc_ledger_change_confirmation.jsp");
							callPage(LangPath + "ESD3000_acc_ledger_change_confirmation.jsp", req, res);	
						}
					}
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

			int screen = R_NEW_PRODUCT;

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
					case R_NEW_PRODUCT :
						procReqNewProduct(mc, msgUser, req, res, session);
						break;
					case R_NEW_LEDGER :
						procReqNewLedger(mc, msgUser, req, res, session);
						break;
					case R_CONFIRM :
						procReqConfirmation(mc, msgUser, req, res, session);
						break;	
						// Actions
					case A_CONFIRM :
						procActionConfirmation(mc, msgUser, req, res, session);
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