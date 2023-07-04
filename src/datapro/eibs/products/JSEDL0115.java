package datapro.eibs.products;

/**
 * Insert the type's description here.
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

public class JSEDL0115 extends datapro.eibs.master.SuperServlet {


	// Limits Inquiry
	static final int R_LIST   = 1;
	static final int A_LIST   = 2;
	
	private String LangPath = "S";

	/**
	 * JSEDL0115 constructor comment.
	 */
	public JSEDL0115() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESDL0115");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	private synchronized void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL011501Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		JBObjList objList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String fromRec = "0";
		
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String Nexte ="";
		// Send Initial data
		try {
			msgList = (EDL011501Message) mc.getMessageRecord("EDL011501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL0115");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0002");
			try {
				fromRec = req.getParameter("FromRecord");
				if (fromRec == null) fromRec="0";
			}
			catch (Exception e) {
			   fromRec = "0";              	  
			}
			msgList.setE01NUMREC(fromRec);
			
			msgList.send();
			msgList.destroy();
			flexLog("EDL011501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL011501")) {

				try {
					objList =
						(datapro.eibs.beans.JBObjList) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.JBObjList");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime=true;
				String marker = "";

				while (true) {

					msgList = (EDL011501Message) newmessage;
					
					marker = msgList.getE01INDOPE();
					if (marker.equals("*")) {					
						objList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							objList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
						}
													
						objList.addRow(msgList);
						
						if (marker.equals("+")) {
							objList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("prList", objList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0115_market_value_list.jsp");
					callPage(LangPath + "EDL0115_market_value_list.jsp", req, res);
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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL011501Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList beanList = null;
		JBObjList objList = null;
		String nextrows = "0";
		String currrows = "0"; 

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		int totRows = Integer.parseInt(req.getParameter("TOTROWS"));
		
		try{
			nextrows = req.getParameter("NEXTROWS");
		} catch (Exception e) {}
		try{
			currrows = req.getParameter("CURRROWS");
		} catch (Exception e) {}
		
		// Send Initial data
		try {
			
			for (int row = 0; row < totRows; row++) {

				msgCC = (EDL011501Message) mc.getMessageRecord("EDL011501");
				msgCC.setH01USERID(user.getH01USR());
				msgCC.setH01PROGRM("EDL0115");
				msgCC.setH01TIMSYS(getTimeStamp());
				msgCC.setH01SCRCOD("01");
				msgCC.setH01OPECOD("0005");
						
				msgCC.setE01DEAACC(req.getParameter("E01DEAACC_" + row));
				msgCC.setE01DEACUN(req.getParameter("E01DEACUN_" + row));
				msgCC.setE01CUSNA1(req.getParameter("E01CUSNA1_" + row));
				msgCC.setE01DEAMVL(req.getParameter("E01DEAMVL_" + row));
				try {
					msgCC.setE01DEANWV(req.getParameter("E01DEANWV_" + row));
				} catch (Exception e) {
					msgCC.setE01DEANWV("0");
				}
				msgCC.setE01DEAREF(req.getParameter("E01DEAREF_" + row));
				msgCC.setE01DEAUC6(req.getParameter("E01DEAUC6_" + row));
				msgCC.setH01FLGWK1("");
				
				msgCC.send();
				msgCC.destroy();
				flexLog("EDL011501 Message Sent");
			
			}

			msgCC = (EDL011501Message) mc.getMessageRecord("EDL011501");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("EDL0115");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0005");
				
			msgCC.setH01FLGWK1("S");
			
			msgCC.send();
			msgCC.destroy();
			flexLog("EDL011501 Message Sent");
		
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
				
				if (IsNotError) { //there are no errors 
					try {
						flexLog("About to call Page: "	+ super.srctx	+ "/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + nextrows);
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + nextrows);
					
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					
				} else { // there are errors
					try {
						flexLog("About to call Page: "	+ super.srctx	+ "/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + currrows);
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + currrows);
					
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}					
				}
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

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

			int screen = R_LIST;

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
					// Requests
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
						// Actions
					case A_LIST :
						procActionList(mc, msgUser, req, res, session);

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
	
	
	private synchronized void showERROR(ELEERRMessage m) {
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