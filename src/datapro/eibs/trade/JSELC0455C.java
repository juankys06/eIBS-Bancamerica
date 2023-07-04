package datapro.eibs.trade;

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

public class JSELC0455C extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_LIST 				= 1;
	protected static final int R_DOC	 				= 2;


	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSELC0455C() {
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
/**
 * This method was created in VisualAge.
 */
protected void procReqDocDet(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ELC045502Message msgSearch = null;
	ELC045502Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ELC045502Message)mc.getMessageRecord("ELC045502");
		msgSearch.setH02USERID(user.getH01USR());
	 	msgSearch.setH02PROGRM("EDL0455");
	 	msgSearch.setH02TIMSYS(getTimeStamp());
	 	msgSearch.setH02SCRCOD("01");
	 	msgSearch.setH02OPECOD("0004");
	 	
		try{
		 	msgSearch.setE02LCMACC(userPO.getIdentifier());
		}
		catch (Exception e){
		 	flexLog("E01LCMACC");
		}
		try{
		 	msgSearch.setE02LCDTYP(userPO.getHeader5());
		}
		catch (Exception e){
		 	flexLog("E01LCMACC");
		}
		try{
		 	msgSearch.setE02LCDBNK(userPO.getBank());
		}
		catch (Exception e){
		 	flexLog("E01LCMACC");
		}
		try{
		 	msgSearch.setE02LCDDNO(userPO.getHeader4());
		}
		catch (Exception e){
		 	flexLog("E01LCMACC");
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try {
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if(userPO.getHeader21().equals("D")){
				try {
					flexLog("About to call Page: " + LangPath + "EDC0100_coll_doc_inq_basic.jsp");
					callPage(LangPath + "EDC0100_coll_doc_inq_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				try {
					flexLog("About to call Page: " + LangPath + "EDC0100_coll_simp_inq_basic.jsp");
					callPage(LangPath + "EDC0100_coll_simp_inq_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}			
			}
		}
	  	else if (newmessage.getFormatName().equals("ELC045502")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String space = "&nbsp;";
			
			while (true) {

				msgList = (ELC045502Message)newmessage;

				marker = msgList.getE02INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					}
					else {
						chk = "";
					}
								
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE02LCDLNO()) + "</TD>");
					myRow.append("<TD NOWRAP >" + space + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE02LCDLIN()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}

				newmessage = mc.receiveMessage();
			}
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "ELC0455_coll_list_doc_det.jsp");
				 callPage(LangPath + "ELC0455_coll_list_doc_det.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		
		}
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}
/**
 * This method was created in VisualAge.
 */
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ELC045501Message msgSearch = null;
	ELC045501Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ELC045501Message)mc.getMessageRecord("ELC045501");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDL0455");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	
		try{
		 	msgSearch.setE01LCMACC(userPO.getIdentifier());
		}
		catch (Exception e){
		 	flexLog("E01LCMACC");
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if(userPO.getHeader21().equals("D")){
				try {
					flexLog("About to call Page: " + LangPath + "EDC0100_coll_doc_inq_basic.jsp");
					callPage(LangPath + "EDC0100_coll_doc_inq_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				try {
					flexLog("About to call Page: " + LangPath + "EDC0100_coll_simp_inq_basic.jsp");
					callPage(LangPath + "EDC0100_coll_simp_inq_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}			
			}
		}
		else if (newmessage.getFormatName().equals("ELC045501")) {

			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
		
			while (true) {

				msgList = (ELC045501Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						userPO.setIdentifier(msgList.getE01LCMACC());
						userPO.setBank(msgList.getE01LCMBNK());
						userPO.setHeader1(msgList.getE01LCMPRO());
						userPO.setHeader2(msgList.getE01LCMCUN());
						userPO.setHeader3(msgList.getE01CUSNA1());
						userPO.setHeader4(msgList.getE01LCDDNO());
						userPO.setHeader5(msgList.getE01LCDTYP());
						userPO.setHeader6(msgList.getE01LCMBRN());
						userPO.setCurrency(msgList.getE01LCMCCY());
						firstTime = false;
						chk = "checked";
					}
					else {
						chk = "";
					}
								
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDocDesC()\">" + Util.formatCell(msgList.getE01LCDDNO()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDocDesC()\">" + Util.formatCell(msgList.getE01LCDTYP()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDocDesC()\">" + Util.formatCell(msgList.getE01LCDLIN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDocDesC()\">" + Util.formatCell(msgList.getE01LCDTXF()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}

				newmessage = mc.receiveMessage();
			}
					
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "ELC0455_coll_list_doc.jsp");
				 callPage(LangPath + "ELC0455_coll_list_doc.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		
		}
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

		int screen = R_DOC;
		
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
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case R_DOC :
					procReqDocDet(mc, msgUser, req, res, session);
					break;

				//entering options

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