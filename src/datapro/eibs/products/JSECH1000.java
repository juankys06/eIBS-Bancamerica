package datapro.eibs.products;

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

public class JSECH1000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_INFO = 1;
	protected static final int A_ENTER_INFO = 2;

	protected String LangPath = "S";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterInfo(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ECH100001Message phConfir = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			phConfir = new ECH100001Message();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		//userPO.setPurpose("MAINTENANCE");
		//userPO.setOption("RT");
		//ses.setAttribute("msgCHKB", msgCHKB);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ECH1000_enter_phone_confirm.jsp");
			callPage(LangPath + "ECH1000_enter_phone_confirm.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	private void procActionEnterInfo(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECH100001Message phConfir = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		try
		{
			flexLog("Send Initial Data");
		
		
			phConfir = (ECH100001Message)mc.getMessageRecord("ECH100001");
		
			phConfir.setH01USERID(user.getH01USR());
			phConfir.setH01PROGRM("ECH1000");
			phConfir.setH01TIMSYS(getTimeStamp());
			phConfir.setH01SCRCOD("01");
			phConfir.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = phConfir.fieldEnumeration();
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

			mc.sendMessage(phConfir);
			phConfir.destroy();
			flexLog("JSECH1000 Message Sent");
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
	  
		  if (newmessage.getFormatName().equals("ELEERR")) 
		  {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("ECH100001")) 
			{

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("phConfir", phConfir);
				try {
						if (IsNotError)
						{  
							try 
							{
								flexLog("About to call Page: " + LangPath + "ECH1000_confirm_phone_confirm.jsp");
								callPage(LangPath + "ECH1000_confirm_phone_confirm.jsp", req, res);
							}
							catch (Exception e) 
							{
								flexLog("Exception calling page " + e);
							}
						}
						else
						{
						  flexLog("About to call Page: " + LangPath + "ECH1000_enter_phone_confirm.jsp");
						  callPage(LangPath + "ECH1000_enter_phone_confirm.jsp", req, res);	
						}

					}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
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


	}

	
	/**
	 * service method comment.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = R_ENTER_INFO;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

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
					case R_ENTER_INFO :
						procReqEnterInfo(msgUser, req, res, session);
						break;
					case A_ENTER_INFO :
						procActionEnterInfo(mc, msgUser, req, res, session);
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


}