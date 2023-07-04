package datapro.eibs.creditproposal;

/** 
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla???????????????????????????
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDP0882 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	// second screen. list of deals 
	protected static final int R_GET_LIST = 100;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0882() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procGetRec(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP088201Message Edp088201 = null;
	EDP088202Message Edp088202 = null;
	JBListRec Grp088202 = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	DataTransaction transData = new DataTransaction();

	boolean IsNotError = false;	
	flexLog("Opennig Socket Connection Branch");

	if (req.getParameter("NPR") != null) {
		userPO.setIdentifier(req.getParameter("NPR"));
	}
	if (req.getParameter("CUN") != null) {
		userPO.setCusNum(req.getParameter("CUN"));
	}
	if (req.getParameter("CUNAM") != null) {
		userPO.setCusName(req.getParameter("CUNAM"));
	}
	if(req.getParameter("OPTION")!=null){
		if(req.getParameter("OPTION").equals("1")){
			userPO.setPurpose("MAINTENANCE");
		} else {
			userPO.setPurpose("INQUIRY");
			}
		}

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		Edp088201 = (EDP088201Message) mc.getMessageRecord("EDP088201");
		Edp088201.setH01USERID(user.getH01USR());
		Edp088201.setH01PROGRM("EDP0882");
		Edp088201.setH01TIMSYS(getTimeStamp());
		Edp088201.setH01SCRCOD("01");
		Edp088201.setH01OPECOD("0015");

		if(req.getParameter("NPR")!=null){
			Edp088201.setE01PLPNPR(req.getParameter("NPR"));
		}
		if(req.getParameter("CUN")!=null){
			Edp088201.setE01CUSCUN(req.getParameter("CUN"));
		}
	
		// all the fields here
		java.util.Enumeration enu = Edp088201.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		}

		mc.sendMessage(Edp088201);
		Edp088201.destroy();
		flexLog("Edp088201 Message Sent");
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
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDP088201")) {
				try {
					Edp088201 = new datapro.eibs.beans.EDP088201Message();
					flexLog("Edp088201 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				Edp088201 = (EDP088201Message) newmessage;
			} 

//lista de registros
	  try {
		  newmessage = mc.receiveMessage();

		  if (newmessage.getFormatName().equals("EDP088202")) {

			String marker = "";
			String myFlag = "";
			int idxOpt = -1;
			int idxGrp = -1;
			int idxGrpAcc = -1;
			int colNum = 5;
			int colNumAcc = 12;
			try {
				Grp088202 = new datapro.eibs.beans.JBListRec();
				Grp088202.init(colNumAcc);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			String myGrp[] = new String[colNum];
			String myGrpAcc[] = new String[colNumAcc];
			String myOption[] = new String[colNum];
			boolean firstTime = true;
			for (int i = 0; i < colNum; i++) {
				myGrp[i] = "";
				myOption[i] = "";
			}
			for (int i = 0; i < colNumAcc; i++) {
				myGrpAcc[i] = "";
			}
			transData.clear();
			int idxAcc = -1;

			while (true) {
				Edp088202 = (EDP088202Message) newmessage;
				marker = Edp088202.getE02OPECDE();
				if (marker.equals("*")) {
					break;
				} else {
						myFlag = "" + idxOpt + "" + idxGrp;
						idxAcc++;
						myGrpAcc[0] = Edp088202.getE02BNKFLG();
						myGrpAcc[1] = Edp088202.getE02DUEPDS();
						myGrpAcc[2] = Edp088202.getE02DUEAMT();
						myGrpAcc[3] = Edp088202.getE02DUERTE();
						myGrpAcc[4] = Edp088202.getE02DUERTM();
						myGrpAcc[5] = Edp088202.getE02DUETRM();
						myGrpAcc[6] = Edp088202.getE02DUEPYM();
						myGrpAcc[7] = Edp088202.getE02DUEPRD();
						Grp088202.addRow(myFlag, myGrpAcc);
				newmessage = mc.receiveMessage();
			}
			}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  	flexLog("Error: " + e);
		  	throw new RuntimeException("Socket Communication Error");
		  }

//fin lista de registros

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("Edp088201", Edp088201);
				ses.setAttribute("Edp088202", Grp088202);
				ses.setAttribute("userPO", userPO);

				//
			try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0882_amount_credit_scoring.jsp");
					callPage(
						LangPath + "EDP0882_amount_credit_scoring.jsp",
						req,
						res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

					//				
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

			int screen = R_GET_LIST;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
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

						//Request
						case R_GET_LIST :
							procGetRec(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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
