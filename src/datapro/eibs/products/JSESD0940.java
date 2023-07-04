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

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSESD0940 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;
	protected static final int R_PRODUCT_LIST = 100;
	protected static final int R_DELETE = 700;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESD0940() {
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

	protected void procActionPos(
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

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		switch (inptOPT) {
			case 1 : // Approval
				procReqProductChangeListApprov(mc, user, req, res, ses);
				break;
			case 3 : // Delete
				procReqProductChangeListDelete(mc, user, req, res, ses);
				break;

		}
	}

	protected void procReqProductChangeList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD094001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ESD094001Message) mc.getMessageRecord("ESD094001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0940");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.send();
			msgList.destroy();
			flexLog("ESD094001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
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

				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD094001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (ESD094001Message) newmessage;

					marker = msgList.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
						chk = "checked";

					} else {
						chk = "";
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						beanList.addRow(msgList);

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ESD094001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog("About to call Page: " + LangPath + "ESD0940_product_change_approval.jsp");
							callPage(LangPath + "ESD0940_product_change_approval.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "ESD0940_product_change_approval.jsp");
							callPage(LangPath + "ESD0940_product_change_approval.jsp", req, res);
						}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procReqProductChangeListDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD094001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (req.getParameter("APL")!=null){
			userPO.setHeader9(req.getParameter("APL"));
		}
		if (req.getParameter("ACC")!=null){
			userPO.setHeader12(req.getParameter("ACC"));
		}
		if (req.getParameter("TYPE")!=null){
			userPO.setHeader21(req.getParameter("TYPE"));
		}
			
	
		// Send Initial data
		try {
			msgList = (ESD094001Message) mc.getMessageRecord("ESD094001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0940");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0003");
			msgList.setE01CHGAPL(userPO.getHeader9());
			msgList.setE01CHGACC(userPO.getHeader12());  
			msgList.setE01CHGTYP(userPO.getHeader21());
			msgList.send();
			msgList.destroy();
			flexLog("ESD094001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
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
	
				// showERROR(msgError);
				//beanList.setNoResult(true);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ESD094001")) {
	
				msgList = (ESD094001Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
	
				try {
					flexLog("About to redirect: /servlet/datapro.eibs.products.JSESD0940?SCREEN=100");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0940?SCREEN=100");
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	
	}


// NEW RG

	protected void procReqProductChangeListApprov(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD094001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (req.getParameter("APL")!=null){
			userPO.setHeader9(req.getParameter("APL"));
		}
		if (req.getParameter("ACC")!=null){
			userPO.setHeader12(req.getParameter("ACC"));
		}
		if (req.getParameter("TYPE")!=null){
			userPO.setHeader21(req.getParameter("TYPE"));
		}
			
	
		// Send Initial data
		try {
			msgList = (ESD094001Message) mc.getMessageRecord("ESD094001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0940");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0001");
			msgList.setE01CHGAPL(userPO.getHeader9());
			msgList.setE01CHGACC(userPO.getHeader12());
			msgList.setE01CHGTYP(userPO.getHeader21());
			msgList.send();
			msgList.destroy();
			flexLog("ESD094001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
	
				try {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ESD094001")) {
	
				msgList = (ESD094001Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError) {
					try {
						flexLog("About to redirect: /servlet/datapro.eibs.products.JSESD0940?SCREEN=100");
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0940?SCREEN=100");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					try {
						flexLog("About to call Page: " + LangPath + "ESD0940_product_change_approval.jsp");
						callPage(LangPath + "ESD0940_product_change_approval.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
	
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
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

			int screen = A_POSITION;

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
						case R_PRODUCT_LIST :
							procReqProductChangeList(mc, msgUser, req, res, session);
							break;
						case R_DELETE :
							procReqProductChangeListDelete(mc, msgUser, req, res, session);
							break;

							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;

							// END Entering

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
