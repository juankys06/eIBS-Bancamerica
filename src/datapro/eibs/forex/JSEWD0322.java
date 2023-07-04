package datapro.eibs.forex;

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

public class JSEWD0322 extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_LIMIT 			= 1;

	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEWD0322() {
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
private void procReqPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0322DSMessage msgList = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
    int colNum = 17;
	try {
		beanList = new datapro.eibs.beans.JBListRec();
		beanList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
  	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EWD0322DSMessage)mc.getMessageRecord("EWD0322DS");
		msgList.setRWDUSR(user.getH01USR());
        msgList.setRWDTYP("D");
        
		try {
		 	if (req.getParameter("E01FESCUN") != null)
		 	  msgList.setSWDCUN(req.getParameter("E01FESCUN"));
		}
		catch (Exception e)	{
	 	  msgList.setSWDCUN("0");
		}
        
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("EWD0322DS Message Sent");
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
			beanList.setNoResult(true);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("docList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EFE0000_enter_opt.jsp");
				callPage(LangPath + "EFE0000_enter_opt.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EWD0322DS")) {

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			String VDate = "";
			String VCCY  = "";
			
			while (true) {

				msgList = (EWD0322DSMessage)newmessage;

				marker = msgList.getSWDOPE();

				if (marker.equals("*")) {
					break;
				}
				else {

    					userPO.setCusNum(Util.formatCell(msgList.getSWDCUN()));
						userPO.setCusName(Util.formatCell(msgList.getSWDDSC()));

						myFlag = msgList.getSWDTYL();
						
						
						if(myFlag.equals("SET")){
							if (Util.formatDate(msgList.getSWDVA1(),msgList.getSWDVA2(),msgList.getSWDVA3()).equals(VDate) && msgList.getSWDCCY().equals(VCCY)) {
								myRow[0] = "&nbsp;";
								myRow[1] = "&nbsp;";
								myRow[2] = "&nbsp;";
								myRow[3] = "&nbsp;";
								myRow[4] = "&nbsp;";
								myRow[5] = "&nbsp;";
							}
							else {
								myRow[0] = msgList.getSWDTYL();
								myRow[1] = Util.formatDate(msgList.getSWDVA1(),msgList.getSWDVA2(),msgList.getSWDVA3());
								myRow[2] = msgList.getSWDCCY();
								myRow[3] = Util.fcolorCCY(msgList.getSWDAMN());
								myRow[4] = Util.fcolorCCY(msgList.getSWDAMU());
								myRow[5] = Util.fcolorCCY(msgList.getSWDAMA());								
							}
							//here
							myRow[6] = msgList.getSWDTYP();
							myRow[7] = msgList.getSWDDID();
							myRow[8] = msgList.getSWDREF();
							myRow[9] = Util.fcolorCCY(msgList.getSWDEQV());
							myRow[10] = msgList.getSWDSTS();
						}
						else{
							myRow[11] = Util.formatDate(msgList.getSWDVA1(),msgList.getSWDVA2(),msgList.getSWDVA3());
							myRow[12] = msgList.getSWDCCY();
							myRow[13] = Util.fcolorCCY(msgList.getSWDAMN());
							myRow[14] = Util.fcolorCCY(msgList.getSWDAMU());
							myRow[15] = Util.fcolorCCY(msgList.getSWDAMA());
							myRow[16] = msgList.getSWDTYL();
							
						}
						VDate = Util.formatDate(msgList.getSWDVA1(),msgList.getSWDVA2(),msgList.getSWDVA3());
						VCCY  = msgList.getSWDCCY();
												
						beanList.addRow(myFlag, myRow);
					}
									

				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("limPos", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EWD0322_client_limits.jsp");
				callPage(LangPath + "EWD0322_client_limits.jsp", req, res);
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

		int screen = R_LIMIT;
		
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
				// Requests
				case R_LIMIT :
					procReqPos(mc, msgUser, req, res, session);
					break;
				// Actions
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
private void showERROR(ELEERRMessage m)
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