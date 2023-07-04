package datapro.eibs.products;

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

public class JSEDL0816 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_LIST 				= 1;


	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0816() {
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
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL081602Message msgSearch = null;
	EDL081602Message msgList = null;
	EDL081601Message msgHeader = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

 	int type = 0;
 	String num = "";
	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgHeader = (EDL081601Message)mc.getMessageRecord("EDL081601");
		msgHeader.setH01USERID(user.getH01USR());
	 	msgHeader.setH01PROGRM("EDL0816");
	 	msgHeader.setH01TIMSYS(getTimeStamp());
	 	msgHeader.setH01SCRCOD("01");
	 	msgHeader.setH01OPECOD("0004");
	 	
	 	
		try{
			try{
			 	msgHeader.setE01DEAACC(userPO.getIdentifier());
			}
			catch (Exception e){
			 	flexLog("E01DEAACC");
			}

		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  flexLog("Input data error " + e);
		}

	 	msgHeader.send();	
	 	msgHeader.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
	try
	{
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

		try {
			flexLog("About to call Page: " + LangPath + "EDL0160_ln_balances.jsp");
			callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);

		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	  }
	  else if (newmessage.getFormatName().equals("EDL081601")) {
		try {
		  msgHeader = new datapro.eibs.beans.EDL081601Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

			msgHeader = (EDL081601Message)newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("girHeader", msgHeader);
	   
	  newmessage = mc.receiveMessage();

	  if (newmessage.getFormatName().equals("EDL081602")) {

		try {
			beanList = new datapro.eibs.beans.JBList();
	  	} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
	  	}

		try{
		 	beanList.setSearchText(num);
		 	beanList.setSearchType(type + "");
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		 	beanList.setSearchText("A");
		 	beanList.setSearchType("3");
		  	flexLog("Input data error " + e);
		}

		boolean firstTime = true;
		String marker = "";
		String myFlag = "";
		StringBuffer myRow = null;
		String chk = "";

		
		while (true) {

			msgList = (EDL081602Message)newmessage;

			marker = msgList.getE02ENDFLD();

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
				myRow.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msgList.getE02DLDNDR()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE02DLDMA1() , msgList.getE02DLDMA2() , msgList.getE02DLDMA3()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE02DLDNME()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02DLDNAM()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02DLDMOR()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE02DLDSTS()) + "</TD>");				
				myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE02DLDDID()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE02DLDPY1() , msgList.getE02DLDPY2() , msgList.getE02DLDPY3()) + "</TD>");				
				myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.fcolorCCY(msgList.getE02DLDINT()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.fcolorCCY(msgList.getE02DLDARC()) + "</TD>");
				myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02DLDCTA()) + "</TD>");
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

		try {
			 flexLog("About to call Page: " + LangPath + "EDL0816_ln_inq_gir.jsp");
			 callPage(LangPath + "EDL0816_ln_inq_gir.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		
	  }
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

		int screen = R_LIST;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
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
			} finally {
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