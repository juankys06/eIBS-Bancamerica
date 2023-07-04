package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (9/07/04 4:01:22 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSECIF100 extends datapro.eibs.master.SuperServlet {

	protected static final int R_LIST 		= 1;
	protected static final int R_ACCOUNT 	= 3;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECIF100() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECIF100");
	
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
	ECIF10001Message msgSearch = null;
	ECIF10001Message msgList = null;
	JBList beanList = null;
	UserPos	userPOLevel = null;
	userPOLevel = (datapro.eibs.beans.UserPos)ses.getAttribute("userPOLevel");

 	int startPos = 0;
 	
 	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ECIF10001Message)mc.getMessageRecord("ECIF10001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("ECIF100");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
		try{

			try{
				msgSearch.setE01EWDREC(req.getParameter("Pos"));
			 	startPos = 	Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
				flexLog("E01EWDREC");
			}
		
			try{
			 	msgSearch.setE01OFCCDE(req.getParameter("OFFICER"));
			}
			catch (Exception e){
			 	flexLog("OFFICER");
			}
			try{
			 	msgSearch.setE01PRDCDE(req.getParameter("PRODUCT"));
			}
			catch (Exception e){
			 	flexLog("PRODUCT");
			}
			try{
				msgSearch.setE01INQCLS(req.getParameter("CLASE"));
			}
			catch (Exception e){
				flexLog("CLASE");
			}
			
		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  flexLog("Input data error " + e);
		}

	 	msgSearch.send();	
	 	msgSearch.destroy();
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
	  
	  	 if (newmessage.getFormatName().equals("ECIF10001")) {

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

				while (true) {

					msgList = (ECIF10001Message)newmessage;

					marker = msgList.getE01EWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							int pos = Integer.parseInt(msgList.getE01EWDREC());
							beanList.setFirstRec(pos);

						}
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01ACDCDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01CUSNUM()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=LEFT>" 
									+ "<A HREF=\"javascript:CenterWindow('" + req.getContextPath() + "/servlet/datapro.eibs.client.JSECIF100?SCREEN=3&App=" + msgList.getE01ACDCDE() + "&Acc=" + msgList.getE01ACCNUM()  
									+ "',600,500,2)"
									+ "\" >"
									+ Util.formatCell(msgList.getE01CUSNA1()) + "</A></TD>");						
						myRow.append("<TD NOWRAP ALIGN=CENTER>" 
									+ "<A HREF=\"javascript:CenterWindow('" + req.getContextPath() + "/servlet/datapro.eibs.client.JSECIF100?SCREEN=3&App=" + msgList.getE01ACDCDE() + "&Acc=" + msgList.getE01ACCNUM()  
									+ "',600,500,2)"
									+ "\" >"
									+ Util.formatCell(msgList.getE01ACCNUM()) + "</A></TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01PRNAMT()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01INTAMT()) + "</TD>");	
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01PENAMT()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01HLDAMT()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE01NMEGRP()) + "</TD>");
						beanList.addRow(myFlag, myRow.toString());
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPOLevel", userPOLevel);
				ses.setAttribute("prodList", beanList);
				
				String params="";
				try {
					params="?Officer="+req.getParameter("OFFICER")+"&Name="+req.getParameter("NAME")+"&Product="+req.getParameter("PRODUCT")+"&Descri="+req.getParameter("DESCRI")+"&Clase="+req.getParameter("CLASE");
					flexLog("About to call Page: " + LangPath + "ECIF100_officerproduct_detail.jsp"+params);
					res.sendRedirect(super.srctx + LangPath + "ECIF100_officerproduct_detail.jsp"+params);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
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

protected void procReqAcc(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
		
	try {
		int appCode = Integer.parseInt(req.getParameter("App"));;
		String accNum = req.getParameter("Acc");
		
		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(1, appCode, accNum, LangPath, ses);
		res.sendRedirect(super.srctx + red.action());
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
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
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, super.iniSocket + 1);
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
				case R_ACCOUNT :
					procReqAcc(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
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