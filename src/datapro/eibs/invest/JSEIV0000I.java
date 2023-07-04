package datapro.eibs.invest;

/**
 * Creation date: (04/30/2002 6:02:17 PM)
 * @author: David Mavilla
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEIV0000I extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_PARAM			= 100;
	
	protected static final int A_ENTER_PARAM			= 200;

	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEIV0000I() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEIV0000I(int logType) {
	super(logType);
	
}
/**
 * This method was created in WSAD.
 * 
 */
protected void procActionEnterParam(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	UserPos	userPO = null;
	TrOption trOption = null;
	boolean IsNotError = false;

	userPO = new UserPos();
	trOption= new TrOption();
	
	int inptOPT = 0;
	String ACCOUNT ="";
	String CUSTOMER = "";
	String DATE1 = "";
	String DATE2 = "";
	String DATE3 = "";
	String CODE = "";
	String EVENT = "";
	String DATEF1 = "";
	String DATEF2 = "";
	String DATEF3 = "";
	String DATET1 = "";
	String DATET2 = "";
	String DATET3 = "";
    String PRFTYPE = "";


		inptOPT = Integer.parseInt(req.getParameter("INPTOPT"));
		
		try{
		   CUSTOMER = req.getParameter("CUSTOMER");
		} catch (Exception e) {
			
		}
		
		try{
	       ACCOUNT = req.getParameter("ACCOUNT");
		} catch (Exception e) {
			
		}
		try{
	       DATE1 = req.getParameter("DATE1");
		} catch (Exception e) {
			
		}
		try{
	       DATE2 = req.getParameter("DATE2");
		} catch (Exception e) {
			
		}
		try{
	       DATE3 = req.getParameter("DATE3");
		} catch (Exception e) {
			
		}
		try{
		   CODE = req.getParameter("CODE");
		} catch (Exception e) {
			
		}
		try{
		   EVENT = req.getParameter("EVENT");
		} catch (Exception e) {
			
		}
		try{
	       DATEF1 = req.getParameter("DATEF1");
		} catch (Exception e) {
			
		}
		try{
	       DATEF2 = req.getParameter("DATEF2");
		} catch (Exception e) {
			
		}
		try{
	       DATEF3 = req.getParameter("DATEF3");
		} catch (Exception e) {
			
		}
        try{
	       DATET1 = req.getParameter("DATET1");
		} catch (Exception e) {
			
		}
		try{
	       DATET2 = req.getParameter("DATET2");
		} catch (Exception e) {
			
		}
		try{
	       DATET3 = req.getParameter("DATET3");
		} catch (Exception e) {
			
		}
		try{
	       PRFTYPE = req.getParameter("PRFTYPE");
		} catch (Exception e) {
			
		}

		switch (inptOPT) {
				case 1 : //Open Position
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=1&CUSTOMER=" + CUSTOMER + "&H01FLGWK1=" + "");
					break;	
				case 2 : //Holds and Uncollected
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0317?SCREEN=1&CUSTOMER=" + CUSTOMER 
					+ "&ACCOUNT=" + ACCOUNT + "&DATE1=" + DATE1 + "&DATE2=" + DATE2 + "&DATE3=" + DATE3);
					break;	
				case 3 : //Customer Portfolio
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=1&CUSTOMER=" + CUSTOMER);
					break;
				case 5 : //Costumer Details
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + CUSTOMER);
					break;	
				case 6 : //Trade Orders
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=700");
					break;	
				case 7 : //Instruments
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0308P?SCREEN=100");
					break;	
				case 8 : //Coupons
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0315I?SCREEN=100");
					break;
				case 9 : //Instrument Open Position
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0320?SCREEN=100&CODE=" + CODE);
					break;	
				case 10 : //Next Event
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0310V?SCREEN=100&EVENT=" + EVENT
					+ "&DATEF1=" + DATEF1 + "&DATEF2=" + DATEF2 + "&DATEF3=" + DATEF3
					+ "&DATET1=" + DATET1 + "&DATET2=" + DATET2 + "&DATET3=" + DATET3
					+ "&PRFTYPE=" + PRFTYPE);
					break;	
				case 12 : //Historical Position
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=1&CUSTOMER=" + CUSTOMER + "&H01FLGWK1=" + "Y");
					break;								
				default :
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0312P?SCREEN=1");
  		}
  		
}


/**
 * service method comment.
 */

	protected void procReqEnterParam(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =new datapro.eibs.beans.ELEERRMessage();
			userPO =new datapro.eibs.beans.UserPos();
			userPO.setOption("INV");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EIV0000I_inv_enter_inquiry.jsp");
			callPage(LangPath + "EIV0000I_inv_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
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

		int screen = R_ENTER_PARAM;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				//flexLog("Opennig Socket Connection");
				//s = new Socket(super.hostIP, getInitSocket(req) + 1);
				//s.setSoTimeout(super.sckTimeOut);
			  	//mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
				//			      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
				//					    "datapro.eibs.beans");
						
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
					
				case A_ENTER_PARAM :
					procActionEnterParam(mc, msgUser, req, res, session);
					break;
					
				case R_ENTER_PARAM :
					 procReqEnterParam(msgUser, req, res, session);
					 break;	
					
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			} catch (Exception e) {
					//e.printStackTrace();
					//int sck = getInitSocket(req) + 1;
					//flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					//res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					//s.close();
				}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}

}