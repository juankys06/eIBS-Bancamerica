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

public class JSEIV0000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_PARAM			= 100;
	
	protected static final int A_ENTER_PARAM			= 200;

	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEIV0000() {
	super();
	}

/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEIV0000(int logType) {
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
	

		inptOPT = Integer.parseInt(req.getParameter("INPTOPT"));


		switch (inptOPT) {
				case 1 : //Commissions
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0312P?SCREEN=1");
					break;	
				case 2 : //Brokers
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=1");
					break;
				case 3 : //Issuers
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=3");
					break;	
				case 5 : //General Parameters
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEIE0015?SCREEN=1");
					break;
				case 6 : //Agent of Custody
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.invest.JSEWD0306P?SCREEN=5");
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
			flexLog("About to call Page: " + LangPath + "EIV0000_inv_enter_param.jsp");
			callPage(LangPath + "EIV0000_inv_enter_param.jsp", req, res);
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
			}
			catch (Exception e) {
				//e.printStackTrace();
				//int sck = getInitSocket(req) + 1;
				//flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				//res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
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