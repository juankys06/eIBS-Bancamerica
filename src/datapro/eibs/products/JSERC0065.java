package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.ArrayList;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSERC0065 extends datapro.eibs.master.SuperServlet {

	protected static final int RECONC_BANC 	= 100;  //procActReport 
	protected static final int GENER_REPORT = 200;  //ProcGenRepo
	 

	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSERC0065() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSERC0065(int logType) {
		super(logType);
	}
	
	protected void procActReport(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		callPage(LangPath + "ERC0065_reconc_search_criteria.jsp",req,res);
	}
	
 
	
	protected void ProcGenRepo(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;
		ERC006501Message msgList = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			
			msgList = (ERC006501Message) mc.getMessageRecord("ERC006501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0065");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01RCTCCY(req.getParameter("E01RCTCCY"));
			msgList.setE01RCTGLN(req.getParameter("E01RCTGLN"));
			msgList.setE01RCTACC(req.getParameter("E01RCTACC"));
			msgList.send();
			msgList.destroy();
			flexLog("ERC006501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
						
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			 
				callPage(LangPath + "ERC0065_reconc_search_criteria.jsp", req, res);
			 
		}


		 
	}
	
 
	 // service method comment.
	 
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = RECONC_BANC;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						
						case RECONC_BANC :
							procActReport(mc, msgUser, req, res, session);
							break;
						case GENER_REPORT :
							ProcGenRepo(mc, msgUser, req, res, session);
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

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

		
	protected void showERROR(ELEERRMessage m) {
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