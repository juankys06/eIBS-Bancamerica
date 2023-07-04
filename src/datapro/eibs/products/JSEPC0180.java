package datapro.eibs.products;

/**
 * Insert the type's description here.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class JSEPC0180 extends datapro.eibs.master.SuperServlet {

	// entering options

	protected static final int A_ENTER_SCHEME_PC_INQ= 201;
	
	protected String LangPath = "S";

/**
 *  constructor comment.
 */
public JSEPC0180() {
	super();
}
/**
 * This method was created by Author
 */
public void destroy() {

	flexLog("free resources used by JSEPC0180");
	
}
/**
 * This method was created by Author
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}




/**
 * This method was created in VisualAge.
 */
protected void procActionEnterSchemePCInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;		
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	String InqOption = null;
	InqOption = userPO.getIdentifier();
			
	try {
		try {
			userPO.setIdentifier(req.getParameter("E01PCDACC"));
			userPO.setHeader23(null);
		}
		catch (Exception e)	{
		}

		if (userPO.getIdentifier() == null) {
			userPO.setIdentifier(InqOption);
			userPO.setHeader23(InqOption);
		}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		procReqSchemePCListInq(mc, user, req, res, ses);

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
protected void procReqSchemePCListInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EPC018001Message msgPC = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try
	{
		msgPC = (EPC018001Message)mc.getMessageRecord("EPC018001");
		msgPC.setH01USERID(user.getH01USR());
		msgPC.setH01PROGRM("EPC0180");
		msgPC.setH01TIMSYS(getTimeStamp());
		msgPC.setH01SCRCOD("01");
		msgPC.setH01OPECOD("0015");
		// Get Parameters here		
		try	{ 	
			msgPC.setE01PCDACC(userPO.getIdentifier());
		}
		catch (Exception e)	{
		} 	
		msgPC.send();	
		msgPC.destroy();
	
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	try {
		extList = new datapro.eibs.beans.JBObjList();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	// Receive Error or Data Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "EPC0180_pc_list_sch_inq.jsp");
					callPage(LangPath + "EPC0180_pc_list_sch_inq.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EPC018001")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgPC = (EPC018001Message)newmessage;
				marker = msgPC.getH01FLGMAS();
				if (marker.equals("*")) {
					break;
				}
				else {					
					extList.addRow(msgPC);									
				}
				newmessage = mc.receiveMessage();
			}
			
			flexLog("Putting java beans into the session");			

			try {
				userPO.setCusNum(msgPC.getE01PCDCUN());
			}
			catch (Exception e)	{
			}

			try {
				userPO.setCusName(msgPC.getD01PCDCUN());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EPC0180_pc_list_sch_inq.jsp");
					callPage(LangPath + "EPC0180_pc_list_sch_inq.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EPC0180_pc_list_sch_inq.jsp");
					callPage(LangPath + "EPC0180_pc_list_sch_inq.jsp", req, res);					


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

		int screen = A_ENTER_SCHEME_PC_INQ;
		
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
				case A_ENTER_SCHEME_PC_INQ : 
					procActionEnterSchemePCInq(mc, msgUser, req, res, session);
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