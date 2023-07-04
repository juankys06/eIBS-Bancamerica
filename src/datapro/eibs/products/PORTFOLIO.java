package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

public class PORTFOLIO extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	// entering options
	protected static final int R_ENTER_LIST 		= 100;
	protected static final int R_ENTER_LIST_ALT	= 300;
	protected static final int R_ENTER_DETAIL		= 500;
	protected static final int R_ENTER_TRAN		= 700;
	protected static final int R_ENTER_INQUIRY	= 900;

	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public PORTFOLIO() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0130");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created in VisualAge.
 */
protected void procReqEnterList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		flexLog("About to call Page: " + LangPath + "PORT_pos_list.jsp");
		callPage(LangPath + "PORT_pos_list.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procReqEnterListAlt(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	try {
		flexLog("About to call Page: " + LangPath + "PORT_pos_alt_list.jsp");
		callPage(LangPath + "PORT_pos_alt_list.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}


}

protected void procReqEnterTran(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	try {
		flexLog("About to call Page: " + LangPath + "PORT_tran_det.jsp");
		callPage(LangPath + "PORT_tran_det.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}


}


protected void procReqEnterDetail(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	try {
		flexLog("About to call Page: " + LangPath + "PORT_titles_app.jsp");
		callPage(LangPath + "PORT_titles_app.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

  
}

protected void procReqEnterInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	try {
		flexLog("About to call Page: " + LangPath + "PORT_rt_enter_inquiry.jsp");
		callPage(LangPath + "PORT_rt_enter_inquiry.jsp", req, res);	
	}
	catch (Exception e) {
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

		int screen = R_ENTER_LIST;
		
		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 3);
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
				// BEGIN CD
				// Request

				// BEGIN Entering
				// Request
				case R_ENTER_LIST : 
					procReqEnterList(msgUser, req, res, session);
					break;
				case R_ENTER_LIST_ALT : 
					procReqEnterListAlt(msgUser, req, res, session);
					break;
				case R_ENTER_DETAIL : 
					procReqEnterDetail(msgUser, req, res, session);
					break;
				case R_ENTER_TRAN : 
					procReqEnterTran(msgUser, req, res, session);
					break;
				case R_ENTER_INQUIRY : 
					procReqEnterInquiry(msgUser, req, res, session);
					break;
					
				// END Entering

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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