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

public class JSESD0900 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_PRODUCT_LIST = 100;

	protected static final int R_ENTER = 1;
	protected static final int A_ENTER = 2;
	protected static final int R_MAINTENANCE = 500;
	protected static final int A_MAINTENANCE = 600;
	protected static final int R_DELETE = 700;


	protected String LangPath = "S";

	/**
	 * JSESD0900 constructor comment.
	 */
	public JSESD0900() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0900");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqProductChangeEnter(
		MessageContext mc,
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
			flexLog("About to call Page: " + LangPath + "ESD0900_product_change_enter.jsp");
			callPage(LangPath + "ESD0900_product_change_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}

	protected void procActionProductChangeEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ESD090001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try {
			msgRT = (ESD090001Message) mc.getMessageRecord("ESD090001");
			msgError = new ELEERRMessage();
			userPO = new UserPos();
			
			if (req.getParameter("changeType").equals("A"))
				msgRT.setE01CHGTYP("1");
			else if (req.getParameter("changeType").equals("M"))
				msgRT.setE01CHGTYP("2");
				
			userPO.setPurpose("NEW");
			
			ses.setAttribute("brnDetails", msgRT);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
	
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		if (req.getParameter("changeType").equals("A")){
			try {
				flexLog("About to call Page: " + LangPath + "ESD0900_product_change_account.jsp");
				callPage(LangPath + "ESD0900_product_change_account.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else if (req.getParameter("changeType").equals("M")){
			try {
				flexLog("About to call Page: " + LangPath + "ESD0900_product_change_massive.jsp");
				callPage(LangPath + "ESD0900_product_change_massive.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	
	}

	
	protected void procReqChangeAccount(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD090001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ESD090001Message) mc.getMessageRecord("ESD090001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ESD0900");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			//msgRT.setH01FLGWK1("N");

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
			flexLog("ESD090001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD090001")) {
				try {
					msgRT = new datapro.eibs.beans.ESD090001Message();
					flexLog("ESD090001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD090001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						flexLog("About to call Page: " + LangPath + "ESD0900_product_change_account_det.jsp");
						callPage(LangPath + "ESD0900_product_change_account_det.jsp", req, res);
					}
					else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESD0900_product_change_account.jsp");
							callPage(LangPath + "ESD0900_product_change_account.jsp", req, res);
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

		//crear variable para que se pueda leer en las pantallas			
		userPO.setHeader16(req.getParameter("opt"));

		switch (inptOPT) {
			case 0 : //New Individual Account Change
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0900?SCREEN=2&changeType=A");
				break;
			case 1 : //New Massive Change
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0900?SCREEN=2&changeType=M");
				break;
			case 2 : //Maintenance
				procReqMaintenance(mc, user, req, res, ses);
				break;
			case 3 : //Delete
				procReqProductChangeListDelete(mc, user, req, res, ses);
				break;

			default :
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0900?SCREEN=500") ;
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
		ESD090001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		//String changeType = "";
		//String massiveChange = "";

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		/*changeType = req.getParameter("CHANGE");
		
		if (changeType.equals("A"))
			massiveChange = "1";
		else if (changeType.equals("M"))
			massiveChange = "2";*/
		
		// Send Initial data
		try {
			msgList = (ESD090001Message) mc.getMessageRecord("ESD090001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0900");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setH01FLGWK1("N");
			msgList.send();
			msgList.destroy();
			flexLog("ESD090001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD090001")) {

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

					msgList = (ESD090001Message) newmessage;

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
				ses.setAttribute("ESD090001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					if (msgError.getERRNUM().equals("0")) {
						flexLog("About to call Page: " + LangPath + "ESD0900_product_change_list.jsp");
						callPage(LangPath + "ESD0900_product_change_list.jsp", req, res);
					}
					else {
						flexLog("About to call Page: " + LangPath + "ESD0900_product_change_enter.jsp");
						callPage(LangPath + "ESD0900_product_change_enter.jsp", req, res);
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
		ESD090001Message msgList = null;
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
			msgList = (ESD090001Message) mc.getMessageRecord("ESD090001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0900");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0004");
			msgList.setE01CHGAPL(userPO.getHeader9());
			msgList.setE01CHGACC(userPO.getHeader12());
			
			if (userPO.getHeader21().equals("1") || userPO.getHeader21().equals("2")) {
				msgList.setE01CHGPRO(req.getParameter("OLD"));
				msgList.setE01CHGPRC(req.getParameter("NEW"));
			}
			if (userPO.getHeader21().equals("3")) {
				msgList.setE01CHGOBR(req.getParameter("OLD"));
				msgList.setE01CHGNBR(req.getParameter("NEW"));
			}
			if (userPO.getHeader21().equals("4")) {
				msgList.setE01CHGOFC(req.getParameter("OLD"));
				msgList.setE01CHGNFC(req.getParameter("NEW"));
			}
			
			msgList.setE01CHGTYP(userPO.getHeader21());
			msgList.send();
			msgList.destroy();
			flexLog("ESD090001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD090001")) {

				msgList = (ESD090001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to redirect: /servlet/datapro.eibs.products.JSESD0900?SCREEN=100");
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0900?SCREEN=100");
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

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD090001Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ESD090001Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgDoc = (ESD090001Message) bl.getRecord();

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("brnDetails", msgDoc);

			if (msgDoc.getE01CHGTYP().equals("1")) {
				try {
					flexLog("About to call Page: " + LangPath + "ESD0900_product_change_account.jsp");
					callPage(LangPath + "ESD0900_product_change_account.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				try {
					flexLog("About to call Page: " + LangPath + "ESD0900_product_change_massive.jsp");
					callPage(LangPath + "ESD0900_product_change_massive.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
		ESD090001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String changeType = "";
		String massiveChange = "";
	
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = new UserPos();
		changeType = req.getParameter("CHANGE");
		massiveChange = req.getParameter("E01CHGTYP");
	
		if (changeType.equals("A")){
			if (req.getParameter("E01CHGAPL") != null) {
				userPO.setHeader9(req.getParameter("E01CHGAPL"));
			}
			if (req.getParameter("E01MODDSC") != null) {
				userPO.setHeader10(req.getParameter("E01MODDSC"));
			}
			if (req.getParameter("E01CHGPRT") != null) {
				userPO.setHeader11(req.getParameter("E01CHGPRT"));
			}
			if (req.getParameter("E01CHGACC") != null) {
				userPO.setHeader12(req.getParameter("E01CHGACC"));
			}
			if (req.getParameter("E01CHGCCY") != null) {
				userPO.setHeader21(req.getParameter("E01CHGCCY"));
			}
			if (req.getParameter("E01CHGPRC") != null) {
				userPO.setHeader13(req.getParameter("E01CHGPRC"));
			}
			if (req.getParameter("E01NEWPRD") != null) {
				userPO.setHeader14(req.getParameter("E01NEWPRD"));
			}
			if (req.getParameter("E01CHGPRO") != null) {
				userPO.setHeader16(req.getParameter("E01CHGPRO"));
			}
			if (req.getParameter("E01PRDDSC") != null) {
				userPO.setHeader17(req.getParameter("E01PRDDSC"));
			}
			if (req.getParameter("E01CUSNA1") != null) {
				userPO.setHeader18(req.getParameter("E01CUSNA1"));
			}
			if (req.getParameter("E01OLDGLD") != null) {
				userPO.setHeader19(req.getParameter("E01OLDGLD"));
			}
			if (req.getParameter("E01NEWGLD") != null) {
				userPO.setHeader20(req.getParameter("E01NEWGLD"));
			}
		}
		else if (changeType.equals("M")) {
			if (req.getParameter("E01CHGBNK") != null) {
				userPO.setHeader15(req.getParameter("E01CHGBNK"));
			}
			if (req.getParameter("E01CHGACC") != null) {
				userPO.setHeader12(req.getParameter("E01CHGACC"));
			}
			if (massiveChange.equals("2")) {
				if (req.getParameter("E01CHGAPL") != null) {
					userPO.setHeader9(req.getParameter("E01CHGAPL"));
				}
				if (req.getParameter("E01MODDSC") != null) {
					userPO.setHeader10(req.getParameter("E01MODDSC"));
				}
				if (req.getParameter("E01CHGPRT") != null) {
					userPO.setHeader11(req.getParameter("E01CHGPRT"));
				}
				if (req.getParameter("E01CHGPRO") != null) {
					userPO.setHeader16(req.getParameter("E01CHGPRO"));
				}
				if (req.getParameter("E01PRDDSC") != null) {
					userPO.setHeader17(req.getParameter("E01PRDDSC"));
				}
				if (req.getParameter("E01CHGPRC") != null) {
					userPO.setHeader13(req.getParameter("E01CHGPRC"));
				}
				if (req.getParameter("E01NEWPRD") != null) {
					userPO.setHeader14(req.getParameter("E01NEWPRD"));
				}
			}
			else if (massiveChange.equals("3")) {
				if (req.getParameter("E01CHGOBR") != null) {
					userPO.setHeader16(req.getParameter("E01CHGOBR"));
				}
				if (req.getParameter("E01OLDBRN") != null) {
					userPO.setHeader17(req.getParameter("E01OLDBRN"));
				}
				if (req.getParameter("E01CHGNBR") != null) {
					userPO.setHeader13(req.getParameter("E01CHGNBR"));
				}
				if (req.getParameter("E01NEWBRN") != null) {
					userPO.setHeader14(req.getParameter("E01NEWBRN"));
				}
			}
			else if (massiveChange.equals("4")) {
				if (req.getParameter("E01CHGOFC") != null) {
					userPO.setHeader16(req.getParameter("E01CHGOFC"));
				}
				if (req.getParameter("E01OLDOFN") != null) {
					userPO.setHeader17(req.getParameter("E01OLDOFN"));
				}
				if (req.getParameter("E01CHGNFC") != null) {
					userPO.setHeader13(req.getParameter("E01CHGNFC"));
				}
				if (req.getParameter("E01NEWOFN") != null) {
					userPO.setHeader14(req.getParameter("E01NEWOFN"));
				}
			}
		}
	
		// Send Initial data
		try {
			msgList = (ESD090001Message) mc.getMessageRecord("ESD090001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0900");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0005");
			msgList.setH01FLGWK1("N");
			
			if (changeType.equals("A")){
				msgList.setE01CHGAPL(userPO.getHeader9());
				msgList.setE01MODDSC(userPO.getHeader10());
				msgList.setE01CHGPRT(userPO.getHeader11());
				msgList.setE01CHGACC(userPO.getHeader12());
				msgList.setE01CHGCCY(userPO.getHeader21());
				msgList.setE01CHGPRC(userPO.getHeader13());			
				msgList.setE01NEWPRD(userPO.getHeader14());			
				msgList.setE01CHGPRO(userPO.getHeader16());			
				msgList.setE01PRDDSC(userPO.getHeader17());
				msgList.setE01CUSNA1(userPO.getHeader18());
				msgList.setE01OLDGLD(userPO.getHeader19());
				msgList.setE01NEWGLD(userPO.getHeader20());
				msgList.setE01CHGTYP("1");
			}
			else if (changeType.equals("M")){
				msgList.setE01CHGAPL(userPO.getHeader9());
				msgList.setE01MODDSC(userPO.getHeader10());
				msgList.setE01CHGPRT(userPO.getHeader11());
				msgList.setE01CHGBNK(userPO.getHeader15());
				msgList.setE01CHGACC(userPO.getHeader12());
				msgList.setE01CHGTYP(massiveChange);
				if (massiveChange.equals("2")){
					msgList.setE01CHGPRO(userPO.getHeader16());
					msgList.setE01PRDDSC(userPO.getHeader17());
					msgList.setE01CHGPRC(userPO.getHeader13());
					msgList.setE01NEWPRD(userPO.getHeader14());
				}
				else if (massiveChange.equals("3")){
					msgList.setE01CHGOBR(userPO.getHeader16());
					msgList.setE01OLDBRN(userPO.getHeader17());
					msgList.setE01CHGNBR(userPO.getHeader13());
					msgList.setE01NEWBRN(userPO.getHeader14());
				}
				else if (massiveChange.equals("4")){
					msgList.setE01CHGOFC(userPO.getHeader16());
					msgList.setE01OLDOFN(userPO.getHeader17());
					msgList.setE01CHGNFC(userPO.getHeader13());
					msgList.setE01NEWOFN(userPO.getHeader14());
				}
			}
	
			msgList.send();
			msgList.destroy();
			flexLog("ESD090001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ESD090001")) {
	
				msgList = (ESD090001Message) newmessage;
				
				try {
					if (msgError.getERRNUM().equals("0")) {
						flexLog("About to redirect: /servlet/datapro.eibs.products.JSESD0900?SCREEN=100");
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0900?SCREEN=100");
					}
					else {
						flexLog("Putting java beans into the session");
						ses.setAttribute("brnDetails", msgList);
	
						if (changeType.equals("A")){
							try {
								flexLog("About to call Page: " + LangPath + "ESD0900_product_change_account.jsp");
								callPage(LangPath + "ESD0900_product_change_account.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
						else if (changeType.equals("M")){
							try {
								flexLog("About to call Page: " + LangPath + "ESD0900_product_change_massive.jsp");
								callPage(LangPath + "ESD0900_product_change_massive.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
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
						case R_ENTER :
							procReqProductChangeEnter(mc, msgUser, req, res, session);
							break;
						case A_ENTER :
							procActionProductChangeEnter(mc, msgUser, req, res, session);
							break;
						case R_PRODUCT_LIST :
							procReqProductChangeList(mc, msgUser, req, res, session);
							break;
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(mc, msgUser, req, res, session);
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
