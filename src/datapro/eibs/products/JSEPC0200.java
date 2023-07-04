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

public class JSEPC0200 extends datapro.eibs.master.SuperServlet {

	protected static final int R_SEARCH_PC		= 1;
	protected static final int A_SCHEME_PC		= 2;
	protected static final int R_SCHEME_PC_LIST	= 3;

	// entering options

	protected static final int R_ENTER_SCHEME_PC	= 100;
	protected static final int R_ENTER_SCHEME_PC_DET= 104;	
	protected static final int A_ENTER_SCHEME_PC	= 200;
	protected static final int A_ENTER_SCHEME_PC_INQ= 201;
	
	// enquiry options
	
	protected static final int R_ENTER_INQ = 300;
	protected static final int A_ENTER_INQ = 400;
	protected static final int A_INQ_SCHEME= 500;	

	
	protected String LangPath = "S";

/**
 *  constructor comment.
 */
public JSEPC0200() {
	super();
}
/**
 * This method was created by Author
 */
public void destroy() {

	flexLog("free resources used by JSEPC0200");
	
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
protected void procReqSearch(
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	try {
		flexLog(
			"About to call Page: " + LangPath + "EPC0200_pc_enter_sch.jsp");
		callPage(LangPath + "EPC0200_pc_enter_sch.jsp", req, res);
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
	}

}




/**
 * This method was created in VisualAge.
 */
protected void procReqSchemePCList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EPC020001Message msgPC = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	int type = 0;
	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	

	try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		type = Integer.parseInt(req.getParameter("Type"));


		try {
			userPO.setHeader18(req.getParameter("Type"));
		}
		catch (Exception e)	{
		}

		ses.setAttribute("userPO", userPO);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	
	// Send Initial data
	try
	{
		msgPC = (EPC020001Message)mc.getMessageRecord("EPC020001");
	 	msgPC.setH01USERID(user.getH01USR());
	 	msgPC.setH01PROGRM("EPC0200");
	 	msgPC.setH01TIMSYS(getTimeStamp());
	 	msgPC.setH01SCRCOD("01");
	 	msgPC.setH01OPECOD("0015");

		switch (type) {
			case 1 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3E");			
				break;
			case 2 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("15");			
				break;
			case 3 :
			msgPC.setH01OPECOD("0035");
				break;
			case 4 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("04");			
				break;
			case 5 :
			msgPC.setH01OPECOD("0025");
				break;
			case 6 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3D");			
				break;
			case 7 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3A");			
				break;
			case 8 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3D");			
				break;
			case 9 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("38");			
				break;
			case 10 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3C");			
				break;
			case 11 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("09");			
				break;
			case 12 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("03");			
				break;
			case 13 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("18");			
				break;
			case 14 :
			msgPC.setH01OPECOD("0045");
				break;
			default :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3E");			

		}

	 	// Get Parameters here		
		try	{ 	
	 		msgPC.setE01PCMACC(userPO.getIdentifier());
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
					flexLog("About to call Page: " + LangPath + "EPC0200_pc_enter_sch.jsp");
					callPage(LangPath + "EPC0200_pc_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EPC020001")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgPC = (EPC020001Message)newmessage;
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
				userPO.setCusNum(msgPC.getE01PCMCUN());
			}
			catch (Exception e)	{
			}

			try {
				userPO.setCusName(msgPC.getD01PCMCUN());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
				 	flexLog("About to call Page: " + LangPath + "EPC0200_pc_list_sch.jsp");
				 	callPage(LangPath + "EPC0200_pc_list_sch.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EPC0200_pc_list_sch.jsp");
					callPage(LangPath + "EPC0200_pc_list_sch.jsp", req, res);					


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



/**
 * This method was created in VisualAge.
 */
protected void procReqSchemePCListDet(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EPC020001Message msgPC = null;
	JBObjList extList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	int type = -1;
	String prcd = "";
	String pccy = "";
	int pbrn = -1;	
	int pccn = -1;
	
	try {
		prcd = req.getParameter("RCD");
	}
	catch (Exception e)	{
	}

	try {
		pccy = req.getParameter("CCY");
	}
	catch (Exception e)	{
	}


	try {
		pbrn = Integer.parseInt(req.getParameter("BRN"));
	}
	catch (Exception e)	{
	}

	try {
		pccn = Integer.parseInt(req.getParameter("CCN"));
	}
	catch (Exception e)	{
	}

	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	

	try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

//		type = Integer.parseInt(req.getParameter("Type"));
		type = Integer.parseInt(userPO.getHeader18());

		ses.setAttribute("userPO", userPO);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	flexLog("Rcd: " + req.getParameter("RCD"));
	flexLog("Ccy: " + req.getParameter("CCY"));	
	flexLog("Brn: " + req.getParameter("BRN"));
	flexLog("Ccn: " + req.getParameter("CCN"));

	// Send Initial data
	try
	{
		msgPC = (EPC020001Message)mc.getMessageRecord("EPC020001");
		msgPC.setH01USERID(user.getH01USR());
		msgPC.setH01PROGRM("EPC0200");
		msgPC.setH01TIMSYS(getTimeStamp());
		msgPC.setH01SCRCOD("05");
		msgPC.setH01OPECOD("0015");

		switch (type) {
			case 1 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3E");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 2 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("15");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 3 :
			msgPC.setH01OPECOD("0035");
			try {
				msgPC.setE01PCMBRN(req.getParameter("BRN"));
			}
			catch (Exception e)	{
			}
				break;
			case 4 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("04");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 5 :
			msgPC.setH01OPECOD("0025");
			try {
				msgPC.setE01PCMCCY(req.getParameter("CCY"));
			}
			catch (Exception e)	{
			}
				break;
			case 6 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3D");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 7 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3A");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 8 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3B");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 9 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("38");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 10 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3C");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 11 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("09");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 12 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("03");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 13 :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("18");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
				break;
			case 14 :
			msgPC.setH01OPECOD("0045");
			try {
				msgPC.setE01PCMCCN(req.getParameter("CCN"));
			}
			catch (Exception e)	{
			}
				break;
			default :
			msgPC.setH01OPECOD("0015");
			msgPC.setE01CNOCFL("3E");			
			try {
				msgPC.setE01CNORCD(req.getParameter("RCD"));
			}
			catch (Exception e)	{
			}
		}


		// Get Parameters here		
		try	{ 	
			msgPC.setE01PCMACC(userPO.getIdentifier());
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
					flexLog("About to call Page: " + LangPath + "EPC0200_pc_enter_sch.jsp");
					callPage(LangPath + "EPC0200_pc_enter_sch.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		} 
		else if (newmessage.getFormatName().equals("EPC020001")) {

			String marker = "";
			
			boolean myFirstRow = false;
			
			while (true) {

				msgPC = (EPC020001Message)newmessage;
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
				userPO.setCusNum(msgPC.getE01PCMCUN());
			}
			catch (Exception e)	{
			}

			try {
				userPO.setCusName(msgPC.getD01PCMCUN());
			}
			catch (Exception e)	{
			}



			ses.setAttribute("error", msgError);
			ses.setAttribute("extList", extList);
//ojo
		  if (userPO.getHeader23() == null) {

			try {
					flexLog("About to call Page: " + LangPath + "EPC0200_pc_list_det_sch.jsp");
					callPage(LangPath + "EPC0200_pc_list_det_sch.jsp", req, res);					
			}
			catch (Exception e) {
					flexLog("Exception calling page " + e);
			}			
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EPC0200_pc_list_det_sch.jsp");
					callPage(LangPath + "EPC0200_pc_list_det_sch.jsp", req, res);					


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

		int screen = R_SEARCH_PC;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opening Socket Connection ");
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
				case R_SEARCH_PC :
					procReqSearch(msgUser, req, res, session);
					break;
				case R_ENTER_SCHEME_PC :
					procReqSchemePCList(mc, msgUser, req, res, session);
					break;			
				case R_ENTER_SCHEME_PC_DET :
					procReqSchemePCListDet(mc, msgUser, req, res, session);
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