package datapro.eibs.forex;

/**
 * Treasury - Pending Contracts Inquiry.
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

public class JSEWD0325BI extends datapro.eibs.master.SuperServlet {

	// Contracts Inquiry
	
	private String LangPath = "S";

	/**
	 * Constructor
	 */
	public JSEWD0325BI() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWS0325BI");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
	HttpSession session = null;

	session = (HttpSession) req.getSession(false);

	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	} else {

		int screen = 1;

		try {

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch (Exception e) {
				flexLog("Screen set to default value");
			}

			switch (screen) {
				
					// Actions
				case 2 :
					procActionPos(mc, msgUser, req, res, session);
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
				return;
			}
			finally {
				s.close();
			}
		} catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}
	
	private void procActionPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 2;
		String RefNumb = req.getParameter("ref");
		String DeaID = req.getParameter("dealer");
		String TYPE = req.getParameter("typ");
		String ACD = req.getParameter("acd");
		String Bank = user.getE01UBK();
		
		switch (inptOPT) {
			case 2 : // Contract Inquiry
			    if(ACD.equals("31") || ACD.equals("34")||
			    		ACD.equals("32") || ACD.equals("NDF")||
			    		ACD.equals("33")){ //Foreign Exchange
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0120I?SCREEN=40&E01FESREF="
						+ RefNumb
						+ "&E01FESDID="
						+ DeaID+
						"&ACD=" + ACD);
			    }
			    else if(ACD.equals("12") || ACD.equals("CDP") ||
			    		ACD.equals("11") || ACD.equals("TDP") ||
			    		ACD.equals("15") || ACD.equals("FFP") ||
			    		ACD.equals("TPS") || ACD.equals("MMS") || 
			    		ACD.equals("MMP") ||
			    		ACD.equals("13") || ACD.equals("PLS") ||
			    		ACD.equals("14") || ACD.equals("ACS") || 
			    		ACD.equals("ACP")){ // Deals
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEDL0120I?SCREEN=14&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID+
						"&ACD=" + ACD);
			    }
			    else if(ACD.equals("35")){ //FRA
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSETR0120I?SCREEN=100&E01DLSREF="
						+ RefNumb
						+ "&E01DLSDID="
						+ DeaID+
						"&ACD=" + ACD);
			    }				    
				break;
			 default :
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.forex.JSEFE0120P?SCREEN=600&E01FESTYP=" + ACD
						+ "&E01FESREF="
						+ RefNumb);
		}
	}

	private void showERROR(ELEERRMessage m) {
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