package datapro.eibs.bastanteo;

/**
 * Insert the type's description here.
 * Creation date: (9/14/04 4:05:17 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSECU0000 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_LIST_CUS_MAINT = 10;
	protected static final int A_MAINT_CUST = 12;
	protected static final int A_LIST_CUST_INQ = 20;
	protected static final int A_LIST_ACCOUNTS = 30;
	protected static final int A_REQ_ACCOUNT = 31;
	protected static final int A_DELETE_ACCOUNTS = 32;
	protected static final int A_MAINT_ACCOUNTS = 33;
	protected static final int A_NEW_ACCOUNTS = 34;
	protected static final int A_INQ_ACCOUNTS = 35;
	protected static final int A_LIST_REFORMS = 40;
	protected static final int A_REQ_REFORM = 41;
	protected static final int A_DELETE_REFORMS = 42;
	protected static final int A_MAINT_REFORMS = 43;
	protected static final int A_NEW_REFORMS = 44;
	protected static final int A_INQ_REFORMS = 45;
	protected static final int A_LIST_MEMBERS = 50;
	protected static final int A_REQ_MEMBER = 51;
	protected static final int A_DELETE_MEMBERS = 52;
	protected static final int A_MAINT_MEMBERS = 53;
	protected static final int A_NEW_MEMBERS = 54;
	protected static final int A_INQ_MEMBERS = 55;
	protected static final int A_LIST_AUTORIZ = 60;
	protected static final int A_REQ_AUTORIZ = 61;
	protected static final int A_DELETE_AUTORIZ = 62;
	protected static final int A_MAINT_AUTORIZ = 63;
	protected static final int A_NEW_AUTORIZ = 64;
	protected static final int A_INQ_AUTORIZ = 65;
	protected static final int A_LIST_FACULT = 70;
	protected static final int A_REQ_FACULT = 71;
	protected static final int A_DELETE_FACULT = 72;
	protected static final int A_MAINT_FACULT = 73;
	protected static final int A_NEW_FACULT = 74;
	protected static final int A_INQ_FACULT = 75;
	protected static final int A_LIST_SIGNATURES = 80;
	protected static final int A_REQ_SIGNATURE = 81;
	protected static final int A_DELETE_SIGNATURES = 82;
	protected static final int A_MAINT_SIGNATURES = 83;
	protected static final int A_NEW_SIGNATURES = 84;
	protected static final int A_INQ_SIGNATURES = 85;
	protected static final int R_LIST_CUS_MAINT = 500;
	protected static final int R_LIST_CUS_INQ = 600;
	protected static final int R_CUS_INQ = 700;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECU0000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECU0000");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	// Customers basic information
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearchMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		ECU000001Message msgSearch = new ECU000001Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_enter_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_enter_maint.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearchInq(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		ECU000001Message msgSearch = new ECU000001Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_enter_inq.jsp");
			callPage(LangPath + "ECU0000_bastanteo_enter_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListCustMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000001Message msgSearch = null;
		ECU000002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
				
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000001Message) mc.getMessageRecord("ECU000001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECU0000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
			try { 
				msgSearch.setE01CUFABO(req.getParameter("E01CUFABO").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFABO(userPO.getHeader1());
			}
			try { 
				msgSearch.setE01CUFFIR(req.getParameter("E01CUFFIR").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFFIR(userPO.getHeader2());
			}
			try { 
				msgSearch.setE01CUSBRA(req.getParameter("E01CUSBRA").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSBRA(userPO.getHeader7());
			}
			try { 
				msgSearch.setE01CUSOFC(req.getParameter("E01CUSOFC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSOFC(userPO.getHeader8());
			}
			try { 
				msgSearch.setE01CUFLGL(req.getParameter("E01CUFLGL").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFLGL(userPO.getHeader9());
			}
			try { 
				msgSearch.setE01CUSIF1(req.getParameter("E01CUSIF1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF1(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01CUSIF2(req.getParameter("E01CUSIF2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF2(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01CUSIF3(req.getParameter("E01CUSIF3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF3(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01CUSIT1(req.getParameter("E01CUSIT1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT1(userPO.getHeader13());
			}
			try { 
				msgSearch.setE01CUSIT2(req.getParameter("E01CUSIT2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT2(userPO.getHeader14());
			}
			try { 
				msgSearch.setE01CUSIT3(req.getParameter("E01CUSIT3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT3(userPO.getHeader15());
			}
			try { 
				 msgSearch.setE01CUSSEL(req.getParameter("E01CUSSEL"));
			} catch (Exception e) {
				if (!userPO.getHeader18().equals(null))
				{
				msgSearch.setE01CUSSEL(userPO.getHeader18());
				}
				else
				{
					msgSearch.setE01CUSSEL("0");
				}
			}
			
			userPO.setHeader1(msgSearch.getE01CUFABO());
			userPO.setHeader2(msgSearch.getE01CUFFIR());
			userPO.setHeader7(msgSearch.getE01CUSBRA());
		    userPO.setHeader8(msgSearch.getE01CUSOFC());	
			userPO.setHeader9(msgSearch.getE01CUFLGL());
			userPO.setHeader10(msgSearch.getE01CUSIF1());
			userPO.setHeader11(msgSearch.getE01CUSIF2());
			userPO.setHeader12(msgSearch.getE01CUSIF3());
			userPO.setHeader13(msgSearch.getE01CUSIT1());
			userPO.setHeader14(msgSearch.getE01CUSIT2());
			userPO.setHeader15(msgSearch.getE01CUSIT3());
			userPO.setHeader18(msgSearch.getE01CUSSEL());
						
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000001 Message Sent");
			
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("ECU000002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000002Message) newmessage;

					marker = msgList.getH02FLGMAS();

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
				ses.setAttribute("mtList", beanList);				
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError) {
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_customers_maint_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_customers_maint_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_enter_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_enter_maint.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintCust(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000002Message msgMT = new ECU000002Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMT = (ECU000002Message)mc.getMessageRecord("ECU000002");
			msgMT.setH02USERID(user.getH01USR());
			msgMT.setH02PROGRM("ECU0000");
			msgMT.setH02TIMSYS(getTimeStamp());
			msgMT.setH02OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMT.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMT.send();
			msgMT.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000002")) {
				
				msgMT = (ECU000002Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=10'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.location.href = \""+ href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMT", msgMT);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_customers_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_customers_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListCustInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000001Message msgSearch = null;
		ECU000002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000001Message) mc.getMessageRecord("ECU000001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECU0000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
			try { 
				msgSearch.setE01CUFABO(req.getParameter("E01CUFABO").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFABO(userPO.getHeader1());
			}
			try { 
				msgSearch.setE01CUFFIR(req.getParameter("E01CUFFIR").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFFIR(userPO.getHeader2());
			}
			try { 
				msgSearch.setE01CUSBRA(req.getParameter("E01CUSBRA").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSBRA(userPO.getHeader7());
			}
			try { 
				msgSearch.setE01CUSOFC(req.getParameter("E01CUSOFC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSOFC(userPO.getHeader8());
			}
			try { 
				msgSearch.setE01CUFLGL(req.getParameter("E01CUFLGL").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUFLGL(userPO.getHeader9());
			}
			try { 
				msgSearch.setE01CUSIF1(req.getParameter("E01CUSIF1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF1(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01CUSIF2(req.getParameter("E01CUSIF2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF2(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01CUSIF3(req.getParameter("E01CUSIF3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIF3(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01CUSIT1(req.getParameter("E01CUSIT1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT1(userPO.getHeader13());
			}
			try { 
				msgSearch.setE01CUSIT2(req.getParameter("E01CUSIT2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT2(userPO.getHeader14());
			}
			try { 
				msgSearch.setE01CUSIT3(req.getParameter("E01CUSIT3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSIT3(userPO.getHeader15());
			}
			try { 
				msgSearch.setE01CUSSEL(req.getParameter("E01CUSSEL"));
			} catch (Exception e) {
				if (!userPO.getHeader18().equals(null))
				{
				msgSearch.setE01CUSSEL(userPO.getHeader18());
				}
				else
				{
				msgSearch.setE01CUSSEL("0");
				}

			}

			
			userPO.setHeader1(msgSearch.getE01CUFABO());
			userPO.setHeader2(msgSearch.getE01CUFFIR());
			userPO.setHeader7(msgSearch.getE01CUSBRA());
		    userPO.setHeader8(msgSearch.getE01CUSOFC());	
			userPO.setHeader9(msgSearch.getE01CUFLGL());
			userPO.setHeader10(msgSearch.getE01CUSIF1());
			userPO.setHeader11(msgSearch.getE01CUSIF2());
			userPO.setHeader12(msgSearch.getE01CUSIF3());
			userPO.setHeader13(msgSearch.getE01CUSIT1());
			userPO.setHeader14(msgSearch.getE01CUSIT2());
			userPO.setHeader15(msgSearch.getE01CUSIT3());
			userPO.setHeader18(msgSearch.getE01CUSSEL());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000001 Message Sent");
			
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
		   
		    if (newmessage.getFormatName().equals("ECU000002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000002Message) newmessage;

					marker = msgList.getH02FLGMAS();

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
				ses.setAttribute("mtList", beanList);				
				ses.setAttribute("userPO", userPO);
				
				if (IsNotError) {
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_customers_inq_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_customers_inq_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_enter_inq.jsp");
						callPage(LangPath + "ECU0000_bastanteo_enter_inq.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
     */
	protected void procReqCustInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000001Message msgSearch = null;
		ECU000002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000001Message) mc.getMessageRecord("ECU000001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECU0000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
				
			try { 
				msgSearch.setE01CUSCUN(userPO.getHeader16());
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000001 Message Sent");
			
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("ECU000002")) {
		    	
				beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000002Message) newmessage;

					marker = msgList.getH02FLGMAS();

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
				ses.setAttribute("mtList", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_customers_inq.jsp");
					callPage(LangPath + "ECU0000_bastanteo_customers_inq.jsp?ROW=0", req, res);					
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
		
	// Cuentas Juridicas Relacionadas
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000003Message msgSearch = null;
		ECU000003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000003Message) mc.getMessageRecord("ECU000003");
			msgSearch.setH03USERID(user.getH01USR());
			msgSearch.setH03PROGRM("ECU0000");
			msgSearch.setH03TIMSYS(getTimeStamp());
			msgSearch.setH03SCRCOD("01");
			msgSearch.setH03OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE03CRLCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000003 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000003")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000003Message) newmessage;

					marker = msgList.getH03FLGMAS();

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
				ses.setAttribute("mtListAcc", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_accounts_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListAcc");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000003Message msgMTAcc = (ECU000003Message) beanList.getRecord();		
			msgMTAcc.setH03OPECOD("0009");
			mc.sendMessage(msgMTAcc);
			//msgMTAcc.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000003")) {
		
				msgMTAcc = (ECU000003Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=30");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_accounts_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000003Message msgMTAcc = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListAcc");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTAcc = (datapro.eibs.beans.ECU000003Message) beanList.getRecord();
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTAcc", msgMTAcc);				
			ses.setAttribute("userPO", userPO);

			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_accounts_maint.jsp", req, res);						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000003Message msgMTAcc = new ECU000003Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTAcc = (ECU000003Message)mc.getMessageRecord("ECU000003");
			msgMTAcc.setH03USERID(user.getH01USR());
			msgMTAcc.setH03PROGRM("ECU0000");
			msgMTAcc.setH03TIMSYS(getTimeStamp());
			msgMTAcc.setH03OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTAcc.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTAcc.send();
			msgMTAcc.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000003")) {
				
				msgMTAcc = (ECU000003Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=30");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTAcc", msgMTAcc);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_accounts_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000003Message msgAcc = new ECU000003Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgAcc = (ECU000003Message)mc.getMessageRecord("ECU000003");
			msgAcc.setH03USERID(user.getH01USR());
			msgAcc.setH03PROGRM("ECU0000");
			msgAcc.setH03TIMSYS(getTimeStamp());
			msgAcc.setH03OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgAcc.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgAcc.send();
			msgAcc.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000003")) {
				
				msgAcc = (ECU000003Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=30");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgAcc", msgAcc);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_new.jsp");
						callPage(LangPath + "ECU0000_bastanteo_accounts_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000003Message msgSearch = null;
		ECU000003Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000003Message) mc.getMessageRecord("ECU000003");
			msgSearch.setH03USERID(user.getH01USR());
			msgSearch.setH03PROGRM("ECU0000");
			msgSearch.setH03TIMSYS(getTimeStamp());
			msgSearch.setH03SCRCOD("01");
			msgSearch.setH03OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE03CRLCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000003 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000003")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000003Message) newmessage;

					marker = msgList.getH03FLGMAS();

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
				ses.setAttribute("mtListAcc", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_accounts_inq_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_accounts_inq_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Reformas al Documento Constitutivo
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000004Message msgSearch = null;
		ECU000004Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000004Message) mc.getMessageRecord("ECU000004");
			msgSearch.setH04USERID(user.getH01USR());
			msgSearch.setH04PROGRM("ECU0000");
			msgSearch.setH04TIMSYS(getTimeStamp());
			msgSearch.setH04SCRCOD("01");
			msgSearch.setH04OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE04CURCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000004 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000004")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000004Message) newmessage;

					marker = msgList.getH04FLGMAS();

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
				ses.setAttribute("mtListRef", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_reforms_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListRef");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000004Message msgMTRef = (ECU000004Message) beanList.getRecord();		
			msgMTRef.setH04OPECOD("0009");
			mc.sendMessage(msgMTRef);
			//msgMTRef.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000004")) {
		
				msgMTRef = (ECU000004Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=40");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_reforms_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000004Message msgMTRef = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListRef");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTRef = (datapro.eibs.beans.ECU000004Message) beanList.getRecord();
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTRef", msgMTRef);				
			ses.setAttribute("userPO", userPO);

			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_reforms_maint.jsp", req, res);						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000004Message msgMTRef = new ECU000004Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTRef = (ECU000004Message)mc.getMessageRecord("ECU000004");
			msgMTRef.setH04USERID(user.getH01USR());
			msgMTRef.setH04PROGRM("ECU0000");
			msgMTRef.setH04TIMSYS(getTimeStamp());
			msgMTRef.setH04OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTRef.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTRef.send();
			msgMTRef.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000004")) {
				
				msgMTRef = (ECU000004Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=40");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTRef", msgMTRef);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_reforms_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000004Message msgRef = new ECU000004Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgRef = (ECU000004Message)mc.getMessageRecord("ECU000004");
			msgRef.setH04USERID(user.getH01USR());
			msgRef.setH04PROGRM("ECU0000");
			msgRef.setH04TIMSYS(getTimeStamp());
			msgRef.setH04OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgRef.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgRef.send();
			msgRef.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000004")) {
				
				msgRef = (ECU000004Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("error", null);
					ses.setAttribute("msgRef", null);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=40");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgRef", msgRef);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_new.jsp");
						callPage(LangPath + "ECU0000_bastanteo_reforms_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqRef(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000004Message msgSearch = null;
		ECU000004Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000004Message) mc.getMessageRecord("ECU000004");
			msgSearch.setH04USERID(user.getH01USR());
			msgSearch.setH04PROGRM("ECU0000");
			msgSearch.setH04TIMSYS(getTimeStamp());
			msgSearch.setH04SCRCOD("01");
			msgSearch.setH04OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE04CURCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000004 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000004")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000004Message) newmessage;

					marker = msgList.getH04FLGMAS();

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
				ses.setAttribute("mtListRef", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_reforms_inq_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_reforms_inq_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Integrantes de la Junta Directiva
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000005Message msgSearch = null;
		ECU000005Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000005Message) mc.getMessageRecord("ECU000005");
			msgSearch.setH05USERID(user.getH01USR());
			msgSearch.setH05PROGRM("ECU0000");
			msgSearch.setH05TIMSYS(getTimeStamp());
			msgSearch.setH05SCRCOD("01");
			msgSearch.setH05OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE05CUMCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000005 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000005")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000005Message) newmessage;

					marker = msgList.getH05FLGMAS();

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
				ses.setAttribute("mtListMem", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					if (userPO.getHeader3().equals("Y")) {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_attorney_list.jsp", req, res);
					} else {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_members_list.jsp", req, res);
					}						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListMem");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000005Message msgMTMem = (ECU000005Message) beanList.getRecord();		
			msgMTMem.setH05OPECOD("0009");
			mc.sendMessage(msgMTMem);
			//msgMTMem.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000005")) {
		
				msgMTMem = (ECU000005Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=50");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						if (userPO.getHeader3().equals("Y")) {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_list.jsp");
							callPage(LangPath + "ECU0000_bastanteo_attorney_list.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_list.jsp");
							callPage(LangPath + "ECU0000_bastanteo_members_list.jsp", req, res);
						}							
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000005Message msgMTMem = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListMem");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTMem = (datapro.eibs.beans.ECU000005Message) beanList.getRecord();
		userPO.setHeader18(msgMTMem.getE05CUMMAN());
		userPO.setHeader19(msgMTMem.getE05CUMMA1());
		userPO.setHeader20(msgMTMem.getE05CUMUC5());
		userPO.setHeader21(msgMTMem.getE05CUMUCN());
		userPO.setHeader5(req.getParameter("CURRENTROW"));
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTMem", msgMTMem);				
			ses.setAttribute("userPO", userPO);

			if (userPO.getHeader3().equals("Y")) {
				flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_maint.jsp");
				callPage(LangPath + "ECU0000_bastanteo_attorney_maint.jsp", req, res);
			} else {
				flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_maint.jsp");
				callPage(LangPath + "ECU0000_bastanteo_members_maint.jsp", req, res);
			}						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000005Message msgMTMem = new ECU000005Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTMem = (ECU000005Message)mc.getMessageRecord("ECU000005");
			msgMTMem.setH05USERID(user.getH01USR());
			msgMTMem.setH05PROGRM("ECU0000");
			msgMTMem.setH05TIMSYS(getTimeStamp());
			msgMTMem.setH05OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTMem.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTMem.setH05FLGWK3(userPO.getHeader3());
	
			msgMTMem.send();
			msgMTMem.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000005")) {
				
				msgMTMem = (ECU000005Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=50");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTMem", msgMTMem);
					try {
						if (userPO.getHeader3().equals("Y")) {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_maint.jsp");
							callPage(LangPath + "ECU0000_bastanteo_attorney_maint.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_maint.jsp");
							callPage(LangPath + "ECU0000_bastanteo_members_maint.jsp", req, res);
						}						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000005Message msgMem = new ECU000005Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMem = (ECU000005Message)mc.getMessageRecord("ECU000005");
			msgMem.setH05USERID(user.getH01USR());
			msgMem.setH05PROGRM("ECU0000");
			msgMem.setH05TIMSYS(getTimeStamp());
			msgMem.setH05OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgMem.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgMem.setH05FLGWK3(userPO.getHeader3());

			msgMem.send();
			msgMem.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000005")) {
				
				msgMem = (ECU000005Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=50");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMem", msgMem);
					try {
						
						if (userPO.getHeader3().equals("Y")) {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_new.jsp");
							callPage(LangPath + "ECU0000_bastanteo_attorney_new.jsp", req, res);
						} else {
							flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_new.jsp");
							callPage(LangPath + "ECU0000_bastanteo_members_new.jsp", req, res);
						}					

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqMem(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000005Message msgSearch = null;
		ECU000005Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000005Message) mc.getMessageRecord("ECU000005");
			msgSearch.setH05USERID(user.getH01USR());
			msgSearch.setH05PROGRM("ECU0000");
			msgSearch.setH05TIMSYS(getTimeStamp());
			msgSearch.setH05SCRCOD("01");
			msgSearch.setH05OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE05CUMCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000005 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000005")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000005Message) newmessage;

					marker = msgList.getH05FLGMAS();

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
				ses.setAttribute("mtListMem", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					
					if (userPO.getHeader3().equals("Y")) {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_attorney_inq_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_attorney_inq_list.jsp", req, res);
					} else {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_members_inq_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_members_inq_list.jsp", req, res);
					}						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Autorizaciones Especiales
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000006Message msgSearch = null;
		ECU000006Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000006Message) mc.getMessageRecord("ECU000006");
			msgSearch.setH06USERID(user.getH01USR());
			msgSearch.setH06PROGRM("ECU0000");
			msgSearch.setH06TIMSYS(getTimeStamp());
			msgSearch.setH06SCRCOD("01");
			msgSearch.setH06OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE06CUACUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}

			try {
				msgSearch.setE06CUAMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000006 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000006")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000006Message) newmessage;

					marker = msgList.getH06FLGMAS();

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
				ses.setAttribute("mtListAut", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_autoriz_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListAut");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000006Message msgMTAut = (ECU000006Message) beanList.getRecord();		
			msgMTAut.setH06OPECOD("0009");
			mc.sendMessage(msgMTAut);
			//msgMTAut.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000006")) {
		
				msgMTAut = (ECU000006Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=60");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_autoriz_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000006Message msgMTAut = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListAut");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTAut = (datapro.eibs.beans.ECU000006Message) beanList.getRecord();
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTAut", msgMTAut);				
			ses.setAttribute("userPO", userPO);

			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_autoriz_maint.jsp", req, res);						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000006Message msgMTAut = new ECU000006Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTAut = (ECU000006Message)mc.getMessageRecord("ECU000006");
			msgMTAut.setH06USERID(user.getH01USR());
			msgMTAut.setH06PROGRM("ECU0000");
			msgMTAut.setH06TIMSYS(getTimeStamp());
			msgMTAut.setH06OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTAut.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTAut.send();
			msgMTAut.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000006")) {
				
				msgMTAut = (ECU000006Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=60");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTAut", msgMTAut);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_autoriz_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000006Message msgAut = new ECU000006Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgAut = (ECU000006Message)mc.getMessageRecord("ECU000006");
			msgAut.setH06USERID(user.getH01USR());
			msgAut.setH06PROGRM("ECU0000");
			msgAut.setH06TIMSYS(getTimeStamp());
			msgAut.setH06OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgAut.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgAut.send();
			msgAut.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000006")) {
				
				msgAut = (ECU000006Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=60");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgAut", msgAut);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_new.jsp");
						callPage(LangPath + "ECU0000_bastanteo_autoriz_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqAut(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000006Message msgSearch = null;
		ECU000006Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000006Message) mc.getMessageRecord("ECU000006");
			msgSearch.setH06USERID(user.getH01USR());
			msgSearch.setH06PROGRM("ECU0000");
			msgSearch.setH06TIMSYS(getTimeStamp());
			msgSearch.setH06SCRCOD("01");
			msgSearch.setH06OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE06CUACUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE06CUAMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000006 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000006")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000006Message) newmessage;

					marker = msgList.getH06FLGMAS();

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
				ses.setAttribute("mtListAut", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_autoriz_inq_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_autoriz_inq_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Facultades Limitadas
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000007Message msgSearch = null;
		ECU000007Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000007Message) mc.getMessageRecord("ECU000007");
			msgSearch.setH07USERID(user.getH01USR());
			msgSearch.setH07PROGRM("ECU0000");
			msgSearch.setH07TIMSYS(getTimeStamp());
			msgSearch.setH07SCRCOD("01");
			msgSearch.setH07OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE07CULCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE07CULMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000007 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000007")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000007Message) newmessage;

					marker = msgList.getH07FLGMAS();

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
				ses.setAttribute("mtListFac", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_facult_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListFac");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000007Message msgMTFac = (ECU000007Message) beanList.getRecord();		
			msgMTFac.setH07OPECOD("0009");
			mc.sendMessage(msgMTFac);
			//msgMTFac.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000007")) {
		
				msgMTFac = (ECU000007Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=70");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_facult_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000007Message msgMTFac = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListFac");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTFac = (datapro.eibs.beans.ECU000007Message) beanList.getRecord();
		userPO.setHeader22(msgMTFac.getE07CULFAC());
   		userPO.setHeader23(msgMTFac.getE07CULFAN());
		userPO.setHeader6(req.getParameter("CURRENTROW"));
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTFac", msgMTFac);				
			ses.setAttribute("userPO", userPO);

			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_facult_maint.jsp", req, res);						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000007Message msgMTFac = new ECU000007Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTFac = (ECU000007Message)mc.getMessageRecord("ECU000007");
			msgMTFac.setH07USERID(user.getH01USR());
			msgMTFac.setH07PROGRM("ECU0000");
			msgMTFac.setH07TIMSYS(getTimeStamp());
			msgMTFac.setH07OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTFac.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTFac.send();
			msgMTFac.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000007")) {
				
				msgMTFac = (ECU000007Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=70");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTFac", msgMTFac);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_facult_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000007Message msgFac = new ECU000007Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgFac = (ECU000007Message)mc.getMessageRecord("ECU000007");
			msgFac.setH07USERID(user.getH01USR());
			msgFac.setH07PROGRM("ECU0000");
			msgFac.setH07TIMSYS(getTimeStamp());
			msgFac.setH07OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgFac.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgFac.send();
			msgFac.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000007")) {
				
				msgFac = (ECU000007Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("error", null);
					ses.setAttribute("msgFac", null);

					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=70");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgFac", msgFac);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_new.jsp");
						callPage(LangPath + "ECU0000_bastanteo_facult_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqFac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000007Message msgSearch = null;
		ECU000007Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000007Message) mc.getMessageRecord("ECU000007");
			msgSearch.setH07USERID(user.getH01USR());
			msgSearch.setH07PROGRM("ECU0000");
			msgSearch.setH07TIMSYS(getTimeStamp());
			msgSearch.setH07SCRCOD("01");
			msgSearch.setH07OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE07CULCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE07CULMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000007 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000007")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000007Message) newmessage;

					marker = msgList.getH07FLGMAS();

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
				ses.setAttribute("mtListFac", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_facult_inq_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_facult_inq_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Firmas Conjuntas Especificas
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000008Message msgSearch = null;
		ECU000008Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000008Message) mc.getMessageRecord("ECU000008");
			msgSearch.setH08USERID(user.getH01USR());
			msgSearch.setH08PROGRM("ECU0000");
			msgSearch.setH08TIMSYS(getTimeStamp());
			msgSearch.setH08SCRCOD("01");
			msgSearch.setH08OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE08CUFCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE08CUFMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			try {
				msgSearch.setE08CUFFAC(userPO.getHeader22()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000008 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000008")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000008Message) newmessage;

					marker = msgList.getH08FLGMAS();

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
				ses.setAttribute("mtListSig", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_signatures_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionDeleteSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtListSig");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			ECU000008Message msgMTSig = (ECU000008Message) beanList.getRecord();		
			msgMTSig.setH08OPECOD("0009");
			mc.sendMessage(msgMTSig);
			//msgMTSig.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000008")) {
		
				msgMTSig = (ECU000008Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=80");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_list.jsp");
						callPage(LangPath + "ECU0000_bastanteo_signatures_list.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000008Message msgMTSig = null;
		JBObjList beanList = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtListSig");
		
		int row = 0;
		try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
		beanList.setCurrentRow(row);
		msgMTSig = (datapro.eibs.beans.ECU000008Message) beanList.getRecord();
		
		try {
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMTSig", msgMTSig);				
			ses.setAttribute("userPO", userPO);

			flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_maint.jsp");
			callPage(LangPath + "ECU0000_bastanteo_signatures_maint.jsp", req, res);						
		
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000008Message msgMTSig = new ECU000008Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMTSig = (ECU000008Message)mc.getMessageRecord("ECU000008");
			msgMTSig.setH08USERID(user.getH01USR());
			msgMTSig.setH08PROGRM("ECU0000");
			msgMTSig.setH08TIMSYS(getTimeStamp());
			msgMTSig.setH08OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgMTSig.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			msgMTSig.send();
			msgMTSig.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECU000008")) {
				
				msgMTSig = (ECU000008Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=80");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMTSig", msgMTSig);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_maint.jsp");
						callPage(LangPath + "ECU0000_bastanteo_signatures_maint.jsp", req, res);						
	
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ECU000008Message msgSig = new ECU000008Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgSig = (ECU000008Message)mc.getMessageRecord("ECU000008");
			msgSig.setH08USERID(user.getH01USR());
			msgSig.setH08PROGRM("ECU0000");
			msgSig.setH08TIMSYS(getTimeStamp());
			msgSig.setH08OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgSig.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msgSig.send();
			msgSig.destroy();
			
			// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECU000008")) {
				
				msgSig = (ECU000008Message) newmessage;
				
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=80");
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgSig", msgSig);
					try {
						flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_new.jsp");
						callPage(LangPath + "ECU0000_bastanteo_signatures_new.jsp", req, res);						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionInqSig(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU000008Message msgSearch = null;
		ECU000008Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU000008Message) mc.getMessageRecord("ECU000008");
			msgSearch.setH08USERID(user.getH01USR());
			msgSearch.setH08PROGRM("ECU0000");
			msgSearch.setH08TIMSYS(getTimeStamp());
			msgSearch.setH08SCRCOD("01");
			msgSearch.setH08OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE08CUFCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE08CUFMAN(userPO.getHeader18()); 
			} catch (Exception e) {
			}

			try {
				msgSearch.setE08CUFFAC(userPO.getHeader22()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU000008 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU000008")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU000008Message) newmessage;

					marker = msgList.getH08FLGMAS();

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
				ses.setAttribute("mtListSig", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0000_bastanteo_signatures_inq_list.jsp");
					callPage(LangPath + "ECU0000_bastanteo_signatures_inq_list.jsp", req, res);						
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	// Service
	
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

			int screen = R_LIST_CUS_MAINT;

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
						// Informacion Basica del Cliente
						case A_LIST_CUS_MAINT :							
							procActionListCustMaint(mc, msgUser, req, res, session);
							break;
						case A_MAINT_CUST :
							procActionMaintCust(mc,msgUser, req, res, session);
							break;
						case A_LIST_CUST_INQ :							
							procActionListCustInq(mc, msgUser, req, res, session);
							break;
						// Cuentas Juridicas Relacionadas
						case A_LIST_ACCOUNTS :
							procActionListAcc(mc, msgUser, req, res, session);
							break;
						case A_DELETE_ACCOUNTS :
							procActionDeleteAcc(mc,msgUser, req, res, session);
							break;
						case A_REQ_ACCOUNT :
							procReqAcc(mc, msgUser, req, res, session);
							break;
						case A_MAINT_ACCOUNTS :
							procActionMaintAcc(mc,msgUser, req, res, session);
							break;
						case A_NEW_ACCOUNTS :
							procActionNewAcc(mc,msgUser, req, res, session);
							break;
						case A_INQ_ACCOUNTS :
							procActionInqAcc(mc, msgUser, req, res, session);
							break;
						// Reformas al Documento Constitutivo
						case A_LIST_REFORMS :
							procActionListRef(mc, msgUser, req, res, session);
							break;
						case A_DELETE_REFORMS :
							procActionDeleteRef(mc,msgUser, req, res, session);
							break;
						case A_REQ_REFORM :
							procReqRef(mc, msgUser, req, res, session);
							break;
						case A_MAINT_REFORMS :
							procActionMaintRef(mc,msgUser, req, res, session);
							break;
						case A_NEW_REFORMS :
							procActionNewRef(mc,msgUser, req, res, session);
							break;
						case A_INQ_REFORMS :
							procActionInqRef(mc, msgUser, req, res, session);
							break;
						// Miembros de la Junta Directiva
						case A_LIST_MEMBERS :
							procActionListMem(mc, msgUser, req, res, session);
							break;
						case A_DELETE_MEMBERS :
							procActionDeleteMem(mc,msgUser, req, res, session);
							break;
						case A_REQ_MEMBER :
							procReqMem(mc, msgUser, req, res, session);
							break;
						case A_MAINT_MEMBERS :
							procActionMaintMem(mc,msgUser, req, res, session);
							break;
						case A_NEW_MEMBERS :
							procActionNewMem(mc,msgUser, req, res, session);
							break;
						case A_INQ_MEMBERS :
							procActionInqMem(mc, msgUser, req, res, session);
							break;
						// Autorizaciones Especiales
						case A_LIST_AUTORIZ :
							procActionListAut(mc, msgUser, req, res, session);
							break;
						case A_DELETE_AUTORIZ :
							procActionDeleteAut(mc,msgUser, req, res, session);
							break;
						case A_REQ_AUTORIZ :
							procReqAut(mc, msgUser, req, res, session);
							break;
						case A_MAINT_AUTORIZ :
							procActionMaintAut(mc,msgUser, req, res, session);
							break;
						case A_NEW_AUTORIZ :
							procActionNewAut(mc,msgUser, req, res, session);
							break;
						case A_INQ_AUTORIZ :
							procActionInqAut(mc, msgUser, req, res, session);
							break;
						// Facultades Limitadas
						case A_LIST_FACULT :
							procActionListFac(mc, msgUser, req, res, session);
							break;
						case A_DELETE_FACULT :
							procActionDeleteFac(mc,msgUser, req, res, session);
							break;
						case A_REQ_FACULT :
							procReqFac(mc, msgUser, req, res, session);
							break;
						case A_MAINT_FACULT :
							procActionMaintFac(mc,msgUser, req, res, session);
							break;
						case A_NEW_FACULT :
							procActionNewFac(mc,msgUser, req, res, session);
							break;
						case A_INQ_FACULT :
							procActionInqFac(mc, msgUser, req, res, session);
							break;
						// Firmas Conjuntas Especificas
						case A_LIST_SIGNATURES :
							procActionListSig(mc, msgUser, req, res, session);
							break;
						case A_DELETE_SIGNATURES :
							procActionDeleteSig(mc,msgUser, req, res, session);
							break;
						case A_REQ_SIGNATURE :
							procReqSig(mc, msgUser, req, res, session);
							break;
						case A_MAINT_SIGNATURES :
							procActionMaintSig(mc,msgUser, req, res, session);
							break;
						case A_NEW_SIGNATURES :
							procActionNewSig(mc,msgUser, req, res, session);
							break;
						case A_INQ_SIGNATURES :
							procActionInqSig(mc, msgUser, req, res, session);
							break;
						// Solicitud de Filtros de Seleccion
						case R_LIST_CUS_MAINT :
							procReqSearchMaint(msgUser, req, res, session);
							break;
						case R_LIST_CUS_INQ :
							procReqSearchInq(msgUser, req, res, session);
							break;
						case R_CUS_INQ :
							procReqCustInq(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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
	
}