package datapro.eibs.forex;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEWD0335 extends datapro.eibs.master.SuperServlet {
	
/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSEWD0335() {
	super();	
}
/**
 * This method was created by David Mavilla
public void destroy() {

	flexLog("free resources used by JSEWD0335");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	Socket s = null;
	MessageContext mc = null;
  	HttpSession session;

	MessageRecord newmessage = null;
	EWD0335DSMessage msgHelp = null;
	ESS0030DSMessage msgUser = null;
	JBList beanList = null;
	UserPos userPO = null;

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

		msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");
		String Language = msgUser.getE01LAN();
		String LangPath = super.rootPath + Language + "/";
		int posi = 0;
		userPO = (datapro.eibs.beans.UserPos)session.getAttribute("userPO");
		
		try
		{
			flexLog("Opennig Socket Connection");
			s = new Socket(super.hostIP, getInitSocket(req) + 1);
			s.setSoTimeout(super.sckTimeOut);
		  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
						      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
								    "datapro.eibs.beans");
			 
		  

	  	try {
	  		
	  		String Pos = "";
	  		Pos = req.getParameter("Pos");
	  		 
			msgHelp = (EWD0335DSMessage)mc.getMessageRecord("EWD0335DS");
			msgHelp.setRWDUSR(msgUser.getH01USR());
			msgHelp.setRWDTYP("A");
			
				
			try{
			 	msgHelp.setRWDFRC(Pos);
			}
			catch (Exception e){
			 	msgHelp.setRWDFRC("0");	
			}
			
			try{
			 	msgHelp.setSWDCUN(req.getParameter("CUSTOMER"));
			 	
			}
			catch (Exception e){
			}
			
			if(Pos == null ){
			 try{
			 	
			 	msgHelp.setRWDCUN(req.getParameter("CUSTOMER"));
			 }
			  catch (Exception e){
				
			 }
			}
			else {
				try{
			 	
			 	msgHelp.setRWDCUN(req.getParameter("RWDCUN"));
			 }
			  catch (Exception e){
				
			 }
			}
			
			try{
			 	msgHelp.setSWDDSC(req.getParameter("NAME"));
			}
			catch (Exception e){
			}
			
			try{
			 	msgHelp.setRWDTYP(req.getParameter("DEALSTS"));
			}
			catch (Exception e){
			}
			
			try{
			 	msgHelp.setRWDTYD(req.getParameter("DATET"));
			}
			catch (Exception e){ 
			}
			
			try{
			 	msgHelp.setRWDFD1(req.getParameter("DATEF1"));
			 	msgHelp.setRWDFD2(req.getParameter("DATEF2"));
			 	msgHelp.setRWDFD3(req.getParameter("DATEF3"));
			}
			catch (Exception e){
			}
			
			try{
			 	msgHelp.setRWDTD1(req.getParameter("DATET1"));
			 	msgHelp.setRWDTD2(req.getParameter("DATET2"));
			 	msgHelp.setRWDTD3(req.getParameter("DATET3"));
			}
			catch (Exception e){
			}
			
			try{
			 	msgHelp.setRWDFDL(req.getParameter("REFERENCE1"));
			}
			catch (Exception e){ 
			}
			
			try{
			 	msgHelp.setRWDTDL(req.getParameter("REFERENCE2"));
			}
			catch (Exception e){ 
			}
			
			msgHelp.send();	
		 	msgHelp.destroy();
		 	flexLog("EWD0335DS Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
			
		// Receiving
		try
		{
	  		newmessage = mc.receiveMessage();
	  
	  		if (newmessage.getFormatName().equals("EWD0335DS")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
					flexLog("EWD0333DS Message Received");
			  	} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String myDesc = "";
				boolean firstTime = true;
				
				while (true) {

					msgHelp = (EWD0335DSMessage)newmessage;

					marker = msgHelp.getSWDOPE();
					
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(
						Integer.parseInt(msgHelp.getSWDREC()));
									
								} 

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						myRow = new StringBuffer("<TR>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + msgHelp.getSWDACC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=LEFT><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + msgHelp.getSWDCUN() + " - " + msgHelp.getSWDDSC() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=LEFT><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + msgHelp.getSWDTYP() + "</A></td>");	
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + msgHelp.getSWDCCY() + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + Util.fcolorCCY(msgHelp.getSWDAMT()) + "</A></td>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + Util.formatDate(msgHelp.getSWDTRA()) + "</A></td>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + Util.formatDate(msgHelp.getSWDVAL()) + "</A></td>");
						myRow.append("<td nowrap  ALIGN=CENTER><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + Util.formatDate(msgHelp.getSWDMAT()) + "</A></td>");
						myRow.append("<td nowrap  ALIGN=RIGHT><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + Util.fcolorCCY(msgHelp.getSWDOAM()) + "</A></td>");						
						myRow.append("<td nowrap  ALIGN=LEFT><A HREF=\"javascript:showFxDeals('"+ msgHelp.getSWDACC() + "','" + msgHelp.getSWDIPR() + "')\">" + msgHelp.getSWDSTS() + "</A></td>");
						myRow.append("</TR>");
						
						userPO.setIdentifier(msgHelp.getRWDCUN());
						
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
				flexLog("Putting java beans into the session");
				session.setAttribute("EWD0335Help", beanList);
				session.setAttribute("userPO", userPO);

				try {
					 flexLog("About to call Page: " + LangPath + "EWD0335_fe_acc.jsp");
					 callPage(LangPath + "EWD0335_fe_acc.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	  		}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	
		}
		catch (Exception e)
		{
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

}
}