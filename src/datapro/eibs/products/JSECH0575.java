package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
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

public class JSECH0575 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_MANUAL_BCHEQ   = 100;
	protected static final int A_ENTER_MANUAL_BCHEQ   = 200;
	
	protected static final int A_MANUAL_BCHEQ    = 1;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECH0575() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECH0575");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created in VisualAge.
 * Rescata datos de Chequera
 * 
 */


protected void  procReqEnterManCheckbook(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("CHB");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH0575_chb_enter_manual.jsp");
		callPage(LangPath + "ECH0575_chb_enter_manual.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


protected void  procActionEnterManCheckbook(MessageContext mc,ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException 
{

	MessageRecord newmessage = null;
	ECH057502Message msgRT = null;
	ECH057501Message msgCHB = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

			
		msgError = new datapro.eibs.beans.ELEERRMessage();
			
		// Send Initial data
		try 
		{
			msgRT = (ECH057502Message) mc.getMessageRecord("ECH057502");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("ECH0575");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			//msgRT.setH02FLGWK1("R");
			msgRT.setE02ACMACC(req.getParameter("E02ACMACC"));
			msgRT.send();
			msgRT.destroy();
			flexLog("ECH057502 Message Sent");
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
		}
		
		try 
		{
			newmessage = mc.receiveMessage();
			// Receive Error Message
			if (newmessage.getFormatName().equals("ELEERR")) 
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);				
			} 

			newmessage = mc.receiveMessage();
			// Receive Data Message
			if (newmessage.getFormatName().equals("ECH057502")) 
			{					
				msgRT = (ECH057502Message) newmessage;
				msgRT.setHandler(null);
				msgCHB = new ECH057501Message();
				msgCHB.setE01CHMACC(msgRT.getE02ACMACC());					
			}
			
			ses.setAttribute("error", msgError);
			
			if (IsNotError){ 
				ses.setAttribute("rtBasic",msgRT);
				ses.setAttribute("chbBasic",msgCHB);
				flexLog("About to call Page: " + LangPath + "ECH0575_chb_manual.jsp");
				callPage(LangPath + "ECH0575_chb_manual.jsp", req, res);
				
			}
			else
			{					
				flexLog("About to call Page: " + LangPath + "ECH0575_chb_enter_manual.jsp");
				callPage(LangPath + "ECH0575_chb_enter_manual.jsp", req, res);							
			} 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
}


protected void  procActionManCheckbook(MessageContext mc,ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	ECH057502Message msgRT = null;
	ECH057501Message msgCHB = null;
	
	
	try 
	{
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) 
	{
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	
		// Send Initial data
		try 
		{
			msgCHB = (ECH057501Message) mc.getMessageRecord("ECH057501");
			msgCHB.setH01USERID(user.getH01USR());
			msgCHB.setH01PROGRM("ECH0000");
			msgCHB.setH01TIMSYS(getTimeStamp());
			msgCHB.setH01SCRCOD("01");
				
			msgCHB.setE01CHMACC(req.getParameter("E02ACMACC"));
			msgCHB.setE01CHMNTC(req.getParameter("E01CHMNTC"));
			msgCHB.setE01CHMTCB(req.getParameter("E02ACMTCB"));
			msgCHB.setE01CHMRQM(req.getParameter("E01CHMRQM"));
			msgCHB.setE01CHMRQD(req.getParameter("E01CHMRQD"));
			msgCHB.setE01CHMRQY(req.getParameter("E01CHMRQY"));
			msgCHB.setE01CHMICK(req.getParameter("E01CHMICK"));
			msgCHB.setE01CHMICK(req.getParameter("E01CHMICK"));
			msgCHB.setE01CHMCCY(req.getParameter("E01CHMCCY"));
			msgCHB.send();
			msgCHB.destroy();
			flexLog("ECH057501 Message Sent");
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
			// Receive Error Message
			if (newmessage.getFormatName().equals("ELEERR")) 
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//ses.setAttribute("error", msgError);
			} 

			newmessage = mc.receiveMessage();
			// Receive Data Message
			if (newmessage.getFormatName().equals("ECH057501")) 
			{				
				msgCHB = (ECH057501Message)newmessage;
				msgCHB.setHandler(null);	
			}
			
			if (IsNotError){ 
				ses.removeAttribute("rtBasic");
				ses.removeAttribute("chbBasic");
				flexLog("About to call Page: " + LangPath + "ECH0575_chb_enter_manual.jsp");
				callPage(LangPath + "ECH0575_chb_enter_manual.jsp", req, res);
			}
			else
			{	
				msgRT = (ECH057502Message) ses.getAttribute("rtBasic");
				msgRT.setE02ACMTCB(msgCHB.getE01CHMTCB());				
				ses.setAttribute("error", msgError);
				ses.setAttribute("chbBasic", msgCHB);
				ses.setAttribute("rtBasic",msgRT);
				flexLog("About to call Page: " + LangPath + "ECH0575_chb_cheq_manual.jsp");
				callPage(LangPath + "ECH0575_chb_manual.jsp", req, res);							
			} 
			
			
		}
		catch (Exception e) 
		{
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

			int screen = R_ENTER_MANUAL_BCHEQ;

			try {
				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
                 				
				screen = Integer.parseInt(req.getParameter("SCREEN"));
				
				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
					
				if (screen == R_ENTER_MANUAL_BCHEQ) {
					procReqEnterManCheckbook(msgUser, req, res, session);
				}
				else {
				

					try {
						flexLog("Opennig Socket Connection");
						s = new Socket(super.hostIP, getInitSocket(req) + 1);
						s.setSoTimeout(super.sckTimeOut);
						mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				
						switch (screen) {
					
							case A_ENTER_MANUAL_BCHEQ :
								procActionEnterManCheckbook(mc,msgUser, req, res, session);
								break;
							case A_MANUAL_BCHEQ :
								procActionManCheckbook(mc, msgUser, req, res, session);
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
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}
	
}

}