package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: R.Amaro
 */

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSECIF260 extends datapro.eibs.master.SuperServlet {

	// CIF options
	
	protected static final int R_PROTEST 			= 1;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECIF260() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECIF250");
	
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
protected void procReqProtest(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF26001Message msgList = null;
	ELEERRMessage msgError = null;
	JBObjList listProt = null;
	JBObjList listProtSisVig = null;
	JBObjList listProtSisAcl = null;
	JBObjList listProtIntVig = null;
	JBObjList listProtIntAcl = null;
	JBAverage beanAve = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF26001Message)mc.getMessageRecord("ECIF26001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF250");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setE01CUSNUM(userPO.getCusNum());

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF250 Message Sent");
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_basic.jsp");
				callPage(LangPath + "ECIF200_cr_basic.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF26001")) {
			
			boolean firstTime = true;
			String marker = "";
			listProt = new JBObjList();
			listProtSisVig = new JBObjList();
			listProtSisAcl = new JBObjList();
			listProtIntVig = new JBObjList();
			listProtIntAcl = new JBObjList();			
			while (true) {

				msgList = (ECIF26001Message)newmessage;
				msgList.setHandler(null);
				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					break;
				}
				else {
					if(firstTime) {
						 //userPO.setCusNum(msgList.getE01CUSNUM());
						 ses.setAttribute("header", msgList);
						 firstTime = false;
					}
															
					listProt.addRow(msgList);
					
					if (msgList.getE01PROSTS().equals("A")) { 
						if (msgList.getE01PROTYP().equals("S")) {							
							listProtSisAcl.addRow(msgList);														
						} else {
							listProtIntAcl.addRow(msgList);
						}
					} else {
						if (msgList.getE01PROTYP().equals("S")) {
							listProtSisVig.addRow(msgList);
						} else {
							listProtIntVig.addRow(msgList);
						}
					}
				}
				
				newmessage = mc.receiveMessage();
			}
			
						
			flexLog("Putting java beans into the session");
			ses.setAttribute("listProt", listProt);
			ses.setAttribute("listProtSisAcl", listProtSisAcl);
			ses.setAttribute("listProtIntAcl", listProtIntAcl);
			ses.setAttribute("listProtSisVig", listProtSisVig);
			ses.setAttribute("listProtIntVig", listProtIntVig);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF260_client_protest.jsp");
				callPage(LangPath + "ECIF260_client_protest.jsp", req, res);
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

		int screen = R_PROTEST;
		
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
				case R_PROTEST :
					procReqProtest(mc, msgUser, req, res, session);
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
}