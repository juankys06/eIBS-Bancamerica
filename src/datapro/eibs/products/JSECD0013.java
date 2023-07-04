package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (28/2/07 14:38:20 PM)
 * @author: Gustavo Adolfo Villarroel
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECD0013 extends datapro.eibs.master.SuperServlet {

	static final int R_ENTER			= 1;
	static final int R_LIST 			= 2;
	static final int R_INQUIRY		 	= 3;

	protected String LangPath = "S";

	/**
	 * JSECD0003 constructor comment.
	 */
	public JSECD0013() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0013");

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnter(
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
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
	
			try {
				flexLog("About to call Page: " + LangPath + "ECD0013_transac_inq_enter.jsp");
				callPage(LangPath + "ECD0013_transac_inq_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD001302Message msgTrs = null;
		ECD001301Message msgTrs2 = null;
		JBObjList trsList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data for ECD001302
		try {
			msgTrs = (ECD001302Message) mc.getMessageRecord("ECD001302");
			msgTrs.setH02USERID(user.getH01USR());
			msgTrs.setH02PROGRM("ECD0013");
			msgTrs.setH02TIMSYS(getTimeStamp());
			msgTrs.setH02SCRCOD("01");
			msgTrs.setH02OPECOD("0002");
			
			// all the fields here
			msgTrs.setE02CDRTAR(req.getParameter("E02CDRTAR"));
			msgTrs.setE02FDESDE(req.getParameter("E02FDESDE3") + req.getParameter("E02FDESDE2") + req.getParameter("E02FDESDE1"));
			msgTrs.setE02FHASTA(req.getParameter("E02FHASTA3") + req.getParameter("E02FHASTA2") + req.getParameter("E02FHASTA1"));
			
			msgTrs.send();
			msgTrs.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		// Receive Error Message for ECD001302
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data for ECD001302
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECD001302"))
			{
				try
				{
					msgTrs = new ECD001302Message();
					flexLog("ECD001302 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgTrs = (ECD001302Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgTrs", msgTrs);
				ses.setAttribute("userPO", userPO);

			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Send Initial data for ECD001301
		try {
			msgTrs2 = (ECD001301Message) mc.getMessageRecord("ECD001301");
			msgTrs2.setH01USERID(user.getH01USR());
			msgTrs2.setH01PROGRM("ECD0013");
			msgTrs2.setH01TIMSYS(getTimeStamp());
			msgTrs2.setH01SCRCOD("01");
			msgTrs2.setH01OPECOD("0015");
			
			// all the fields here
			msgTrs2.setE01TARJETA(req.getParameter("E02CDRTAR"));
			msgTrs2.setE01FDESDE(req.getParameter("E02FDESDE3") + req.getParameter("E02FDESDE2") + req.getParameter("E02FDESDE1"));
			msgTrs2.setE01FHASTA(req.getParameter("E02FHASTA3") + req.getParameter("E02FHASTA2") + req.getParameter("E02FHASTA1"));
			
			msgTrs2.send();
			msgTrs2.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECD0013_transac_inq_enter.jsp");
					callPage(LangPath + "ECD0013_transac_inq_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			} else if (newmessage.getFormatName().equals("ECD001301")) {
				trsList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
				
				if (firstTime) {
					firstTime = false;
				}

				while (true) {
					msgTrs2 = (ECD001301Message) newmessage;
					marker = msgTrs2.getH01FLGMAS();

					if (marker.equals("*")) {
							trsList.setShowNext(false);
							break;
					} else {
						trsList.addRow(msgTrs2);
						if (firstTime) {
							firstTime = false;
						}
						if (marker.equals("+")) {
							trsList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("trsList", trsList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0013_transac_inq_list.jsp");
					callPage(LangPath + "ECD0013_transac_inq_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
					
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD001301Message msgTransac = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList trsList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("INQUIRY");
		trsList = (JBObjList) ses.getAttribute("trsList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		trsList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgTransac = (ECD001301Message) trsList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgTransac", msgTransac);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0013_transac_inq_detail.jsp");
				callPage(LangPath + "ECD0013_transac_inq_detail.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
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

			int screen = R_ENTER;

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
					case R_ENTER:
						procReqEnter(mc, msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;			
					case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
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