package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSECC0000 extends datapro.eibs.master.SuperServlet {

	
	static final int R_ENTER  	= 1;
	static final int R_LIST  	= 2;
	static final int A_LIST	 	= 3;
	static final int A_MAINT 	= 4;
	static final int A_DELETE	= 5;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECC0000() {
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

			int screen = R_ENTER;

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
					case R_ENTER :
						procReqEnterBank( mc, msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;

					case A_LIST :
						procActionList(mc, msgUser, req, res, session);
						break;
					case A_MAINT :
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
	protected void showERROR(ELEERRMessage m) {
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
	
	protected void procReqEnterBank(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("About to call Page: " + LangPath + "ECC0000_enter_param.jsp");
			callPage(LangPath + "ECC0000_enter_param.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC000001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
	
		userPO = new datapro.eibs.beans.UserPos();

		// Send Initial data
		try
		{
			msgList = (ECC000001Message)mc.getMessageRecord("ECC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");			
			if(req.getParameter("E01CCDBNC")!=null){
				msgList.setE01CCDBNK(req.getParameter("E01CCDBNC"));
				userPO.setBank(req.getParameter("E01CCDBNC"));
			}
	 	
			msgList.send();	
			msgList.destroy();
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
			} else
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

			if (newmessage.getFormatName().equals("ECC000001")) {
			
				String marker = "";
				JBObjList beanList = new JBObjList();
			
				while (true) {

					msgList = (ECC000001Message) newmessage;

					marker = msgList.getE01CCDOPE();
					msgList.setHandler(null);

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {			
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ccList", beanList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
					
				if (IsNotError)	 { //there are not error
					try {
							flexLog("About to call Page: " + LangPath + "ECC0000_cost_center_list.jsp");
							callPage(LangPath + "ECC0000_cost_center_list.jsp", req, res);	
					}
					catch (Exception e) {
							flexLog("Exception calling page " + e);				
					}					
				} else {
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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		UserPos	userPO = null;
		ELEERRMessage msgError = null;
		ECC000001Message msgList = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		String opt = req.getParameter("opt");
		try
		{	
			msgList = (ECC000001Message)mc.getMessageRecord("ECC000001");
			
			if (opt.equals("N")) {
			
				userPO.setPurpose("N");
										
			} else if (opt.equals("M")) {
					
				userPO.setPurpose("M");
				try {
					msgList.setE01CCDCCN(req.getParameter("CCN"));
				} catch(Exception e){}
				try {
					msgList.setE01CCDCCD(req.getParameter("DSC"));
				} catch(Exception e){}
				try {
					msgList.setE01CCDGRP(req.getParameter("GRP"));
				} catch(Exception e){}
				try {
					msgList.setE01CCDSBR(req.getParameter("SBR"));
				} catch(Exception e){}
			
			}
			
			try {
				msgList.setE01CCDBNK(req.getParameter("BNK"));
			} catch(Exception e){}

			flexLog("Putting java beans into the session");
			ses.setAttribute("cc", msgList);
			ses.setAttribute("userPO", userPO);
			
			try {	
					flexLog("About to call Page: " + LangPath + "ECC0000_cost_center_basic.jsp");
					callPage(LangPath + "ECC0000_cost_center_basic.jsp", req, res);				
				
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
	
	protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC000001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		String bnk = "";
		String opeCode = "0005";

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		String opt = req.getParameter("OPT");
		
		if (opt.equals("N")) {
			opeCode = "0001";
		} else if (opt.equals("M")) {
			opeCode = "0005";
		}
			
		// Send Initial data
		try
		{	
			bnk = req.getParameter("E01CCDBNK");
			
			msgList = (ECC000001Message)mc.getMessageRecord("ECC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD(opeCode);

			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				}
				catch (Exception e) {
				}	
			}

			msgList.send();
			//mc.sendMessage(msgRate2);
			msgList.destroy();
			flexLog("ECC000001 Message Sent");
		
		// Receive Error Message
	
		  newmessage = mc.receiveMessage();
	  
		  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		  }
		  else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
		// Receive Data
	
		  newmessage = mc.receiveMessage();

		  if (newmessage.getFormatName().equals("ECC000001")) {
				
				msgList = (ECC000001Message)newmessage;
				flexLog("Putting java beans into the session");
	
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);	
				try {	
					if (IsNotError) {						
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.params.JSECC0000?SCREEN=2"  + "&E01CCDBNC=" + bnk );		    
						
	
					} else {
						ses.setAttribute("cc", msgList);
						flexLog("About to call Page: " + LangPath + "ECC0000_cost_center_basic.jsp");
						callPage(LangPath + "ECC0000_cost_center_basic.jsp", req, res);				
					
					}
					
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