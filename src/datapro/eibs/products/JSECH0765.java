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

public class JSECH0765 extends datapro.eibs.master.SuperServlet {

	// RETAIL ACCOUNTS
	protected static final int A_RT_INF_CHEQUERA	     = 404;
	protected final int A_RT_ENTER_INF_CHEQUERA   = 405;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECH0765() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECH0765");
	
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

protected void  procActionRTInfChequera(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	ESS0030DSMessage msgUser = null;
	

	try {

		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		msgUser = (datapro.eibs.beans.ESS0030DSMessage)ses.getAttribute("currUser");
		
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("currUser", msgUser);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH0765_enter_opcion.jsp");
		callPage(LangPath + "ECH0765_enter_opcion.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


protected void  procAction_req_InfChequera(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	
	ECH076501Message msgRT = null;
	
	JBList beanList = null;
	UserPos	userPO = null;	
	
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
	Socket s = null;
	MessageContext mc = null;
	
	try 
	{
		flexLog("Opennig Socket Connection");
		s = new Socket(super.hostIP, getInitSocket(req) + 4);
		s.setSoTimeout(super.sckTimeOut);
		mc =
			new MessageContext(
			new DataInputStream(new BufferedInputStream(s.getInputStream())),
			new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
			"datapro.eibs.beans");
			
		try 
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
		catch (Exception ex) 
		{
			flexLog("Error: " + ex); 
	  	}

		// Send Initial data
		try
		{
		
			msgRT = (ECH076501Message)mc.getMessageRecord("ECH076501");
		 	msgRT.setH01USERID(user.getH01USR());
		 	msgRT.setH01PROGRM("ECH0535");
		 	msgRT.setH01TIMSYS(getTimeStamp());
		 	msgRT.setH01SCRCOD("01");
		 	msgRT.setH01OPECOD("0001");
			msgRT.setE01CHMACC(req.getParameter("E01CHMACC"));
			msgRT.setE01CHMFEY(req.getParameter("E01CHMFEY"));
			msgRT.setE01CHMFEM(req.getParameter("E01CHMFEM"));		
			msgRT.setE01CHMFED(req.getParameter("E01CHMFED"));
			msgRT.setE01CHMBRN(req.getParameter("E01CHMBRN"));
			msgRT.setE01ESTADO(req.getParameter("E01ESTADO"));
			msgRT.setE01CHMTCB(req.getParameter("E01CHMTCB"));
			msgRT.send();	
		 	msgRT.destroy();
		 	flexLog("ECHO76501 Message Sent");
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

			if (newmessage.getFormatName().equals("ECH076501")) 
			{
				try 
				{
					beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
					flexLog("ECH076501 Message Received");
			  	} 
				catch (Exception ex) 
				{
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				String myRow = "";
				String codSuc = "";
				int indexRow = 0;
				userPO.setHeader22(req.getParameter("E01ESTADO"));	
				
				String Estado3 = "";

				while (true) 
				{
						msgRT = (ECH076501Message)newmessage;
						marker = msgRT.getH01FLGMAS();	
						
						Estado3 = msgRT.getE01ESTADO();	

						if (marker.equals("*")) 
						{
							beanList.setShowNext(false);
							break;
						}
						else 
						{
							userPO.setHeader23(msgRT.getE01BRNNME());						
							myRow =  "<TR>";
							
							//if (req.getParameter("E01ESTADO").equals("1"))
							if (Estado3.equals("1"))
							{
								myRow +="<td nowrap>I</td>";
								myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHMRQD(),msgRT.getE01CHMRQM(), msgRT.getE01CHMRQY()) +"</td>";
							}
							//if (req.getParameter("E01ESTADO").equals("2"))							
							if (Estado3.equals("2"))
							{
								myRow +="<td nowrap>R</td>";
								myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHMRCD(),msgRT.getE01CHMRCM(), msgRT.getE01CHMRCY()) +"</td>";
							}
							//if (req.getParameter("E01ESTADO").equals("3"))
							if (Estado3.equals("3"))
							{
								myRow +="<td nowrap>V</td>";
								myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHMACD(),msgRT.getE01CHMACM(), msgRT.getE01CHMACY()) +"</td>";
							}	
							myRow +="<td nowrap>" + msgRT.getE01CHMACC()+"</td>";
							myRow +="<td nowrap>" + msgRT.getE01CUSNA1()+"</td>";
							myRow +="<td nowrap>" + msgRT.getE01CUSIDN()+"</td>";
							myRow +="<td nowrap>" + msgRT.getE01CHMTCB()+" " + msgRT.getE01TCBNOM() + "</td>";

							int resta = (Integer.parseInt(msgRT.getE01CHMFCK()) - Integer.parseInt(msgRT.getE01CHMICK())+ 1 );
							myRow +="<td nowrap>" + msgRT.getE01CHMNCB()+ "</td>";
							myRow +="<td nowrap>" +  resta + "</td>";
							myRow +="<td nowrap>" + msgRT.getE01CHMICK()+"</td>";
							myRow +="<td nowrap>" + msgRT.getE01CHMFCK()+"</td>";
							myRow +="<td nowrap>" + msgRT.getE01CHEIDN()+"</td>";							
							myRow +="<td nowrap>" + msgRT.getE01CHENA1()+"</td>";
							myRow += "</TR>";

							beanList.addRow(myFlag, myRow);
							indexRow ++;	
										
							if (marker.equals("+")) 
							{
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
				}
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("rtBasic", beanList);
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError)
				{  // There are no errors
					try 
					{
						flexLog("About to call Page: " + LangPath + "ECH0765_rt_list_chequera.jsp");
						callPage(LangPath + "ECH0765_rt_list_chequera.jsp", req, res);	
					}
					catch (Exception e) 
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{
 				  flexLog("About to call Page: " + LangPath + "ECH0765_rt_list_chequera.jsp");
				  callPage(LangPath + "ECH0765_rt_list_chequera.jsp", req, res);	
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
	catch (Exception e) 
	{
			e.printStackTrace();
			int sck = getInitSocket(req) + 4;
			flexLog("Socket not Open(Port " + sck + "). Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
			return;
	}

	finally 
	{
			s.close();
	}

}




public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   

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

		int screen = A_RT_INF_CHEQUERA;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try 
			{
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {


				case A_RT_INF_CHEQUERA : 	
					procActionRTInfChequera(msgUser,req,res,session);
					break;					
					
				case A_RT_ENTER_INF_CHEQUERA : 	
					procAction_req_InfChequera(msgUser,req,res,session);
					break;					

				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
		
	}
	
}

}