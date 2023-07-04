package datapro.eibs.params;

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

public class JSESD0009A extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_REFERENCE_LIST = 100;
	protected static final int R_CODES_LIST = 400;

	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;
	protected static final int A_MAINTENANCE = 600;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSESD0009A() {
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

	protected void procReqEnterParameter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0009_taxes_enter.jsp");
			callPage(LangPath + "ESD0009_taxes_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

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
		String taxcode = "";
		taxcode = req.getParameter("TAXCODE");
		
		if(taxcode == null){
			taxcode = "1";
			
		}
		
		inptOPT = Integer.parseInt(req.getParameter("opt"));

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSESD0009A?SCREEN=300" +"&TAXCODE=" + taxcode);
				break;
			case 2 : //Maintenance
				procReqMaintenance(mc, user, req, res, ses);
				break;

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSESD0009A?SCREEN=300" +"&TAXCODE=" + taxcode);
				break;
		}
	}

	protected void procReqTaxesList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000901Message msgList = null;
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
			msgList = (ESD000901Message) mc.getMessageRecord("ESD000901");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD000901");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01TAXBNK(req.getParameter("E01TAXBNK"));
			msgList.send();
			msgList.destroy();
			flexLog("ESD003001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000901")) {

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

					msgList = (ESD000901Message) newmessage;

					marker = msgList.getE01TAXOPE();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader10(msgList.getE01TAXBNK());
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
				ses.setAttribute("ESD000901Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0009_taxes_list.jsp");
					callPage(
						LangPath + "ESD0009_taxes_list.jsp",
						req,
						res);
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

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000901Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ESD000901Message) ses.getAttribute("taxes");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECH011001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ESD003002 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000901")) {
				try {
					msgRT = new ESD000901Message();
					flexLog("ESD000901 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD000901Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("taxes", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSESD0009A?SCREEN=100" 
							+ "&E01TAXBNK=" + msgRT.getE01TAXBNK());
				} else { // There are errors
					int screencode = Integer.parseInt(msgRT.getE01TAXTTP());
					
					callPage(getRedirectPage(screencode), req, res);

				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESD000901Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgRT = new ESD000901Message();
		msgRT.setE01TAXBNK(userPO.getHeader10());
		ses.setAttribute("taxes", msgRT);

		int screencode = Integer.parseInt(req.getParameter("TAXCODE"));
		

		callPage(getRedirectPage(screencode), req, res);

	}


	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			ESD000901Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ESD000901Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgDoc = (ESD000901Message) bl.getRecord();

			flexLog("Putting java beans into the session");
			
			ses.setAttribute("taxes", msgDoc);

			int screencode = Integer.parseInt(req.getParameter("TAXCODE"));

			callPage(getRedirectPage(screencode), req, res);


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}




	private String getRedirectPage(int screencode) {
		//Screen Code Selection
		String ret = "";
		switch (screencode) {

			case 1 : //Interest Witholding
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0009_taxes_001_interest.jsp");
					ret = LangPath + "ESD0009_taxes_001_interest.jsp";
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				break;

			case 500 : 
			case 501 : 
			case 502 : 
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0009_taxes_500_iva.jsp");
					ret = LangPath + "ESD0009_taxes_500_iva.jsp";
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				break;

			case 700 : 
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0009_taxes_700_leasing.jsp");
					ret = LangPath + "ESD0009_taxes_700_leasing.jsp";
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				break;

			default :
				try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0009_taxes_001_interest.jsp");
						ret =
							LangPath
								+ "ESD0009_taxes_001_interest.jsp";
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

		}
		return ret;
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

						//Request

						case R_REFERENCE_LIST :
							procReqTaxesList(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						
						case R_NEW :
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_ENTER :
							procReqEnterParameter(msgUser, req, res, session);
							break;
							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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
