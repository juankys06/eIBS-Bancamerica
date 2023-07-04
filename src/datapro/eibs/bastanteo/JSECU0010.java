package datapro.eibs.bastanteo;

/**
 * Insert the type's description here.
 * Creation date: (9/17/04 12:47:41 PM)
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

public class JSECU0010 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_LIST_CUST_INQ = 10;
	protected static final int A_LIST_MEMBERS_INQ = 20;
	protected static final int A_LIST_MEMBERS = 100;
	protected static final int R_LIST_CUS_INQ = 500;
	protected static final int R_LIST_MEM_INQ = 600;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECU0010() {
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
	protected void procReqSearchCusInq(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		ECU001001Message msgSearch = new ECU001001Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_enter_cust_inq.jsp");
			callPage(LangPath + "ECU0010_bastanteo_enter_cust_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearchMemInq(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		ECU001001Message msgSearch = new ECU001001Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_enter_memb_inq.jsp");
			callPage(LangPath + "ECU0010_bastanteo_enter_memb_inq.jsp", req, res);
		
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
		ECU001001Message msgSearch = null;
		ECU001001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU001001Message) mc.getMessageRecord("ECU001001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECU0010");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
			msgSearch.setH01FLGWK1("C");

			//all the fields here
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
				msgSearch.setE01CUSVD1(req.getParameter("E01CUSVD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD1(userPO.getHeader9());
			}
			try { 
				msgSearch.setE01CUSVD2(req.getParameter("E01CUSVD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD2(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01CUSVD3(req.getParameter("E01CUSVD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD3(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01CUSTD1(req.getParameter("E01CUSTD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSTD1(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01CUSTD2(req.getParameter("E01CUSTD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSTD2(userPO.getHeader13());
			}
			try { 
				msgSearch.setE01CUSTD3(req.getParameter("E01CUSTD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSTD3(userPO.getHeader14());
			}
			
			userPO.setHeader1(msgSearch.getE01CUFABO());
			userPO.setHeader2(msgSearch.getE01CUFFIR());
			userPO.setHeader7(msgSearch.getE01CUSBRA());
		    userPO.setHeader8(msgSearch.getE01CUSOFC());
			userPO.setHeader9(msgSearch.getE01CUSVD1());
			userPO.setHeader10(msgSearch.getE01CUSVD2());
			userPO.setHeader11(msgSearch.getE01CUSVD3());
			userPO.setHeader12(msgSearch.getE01CUSTD1());
			userPO.setHeader13(msgSearch.getE01CUSTD2());
			userPO.setHeader14(msgSearch.getE01CUSTD3());	

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU001001 Message Sent");
		
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
		   
		    if (newmessage.getFormatName().equals("ECU001001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU001001Message) newmessage;

					marker = msgList.getH01FLGMAS();

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
						flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_customers_inq_list.jsp");
						callPage(LangPath + "ECU0010_bastanteo_customers_inq_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_enter_cust_inq.jsp");
						callPage(LangPath + "ECU0010_bastanteo_enter_cust_inq.jsp", req, res);						
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
	protected void procActionListMemInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECU001001Message msgSearch = null;
		ECU001001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU001001Message) mc.getMessageRecord("ECU001001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECU0010");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
			msgSearch.setH01FLGWK1("M");

			//all the fields here
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
				msgSearch.setE01CUSVD1(req.getParameter("E01CUSVD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD1(userPO.getHeader9());
			}
			try { 
				msgSearch.setE01CUSVD2(req.getParameter("E01CUSVD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD2(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01CUSVD3(req.getParameter("E01CUSVD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSVD3(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01CUSTD1(req.getParameter("E01CUSTD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSTD1(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01CUSTD2(req.getParameter("E01CUSTD2").toUpperCase());
			} catch (Exception e) {
			msgSearch.setE01CUSTD2(userPO.getHeader13());
			}
			try { 
				msgSearch.setE01CUSTD3(req.getParameter("E01CUSTD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSTD3(userPO.getHeader14());
			}
			
			userPO.setHeader1(msgSearch.getE01CUFABO());
			userPO.setHeader2(msgSearch.getE01CUFFIR());
			userPO.setHeader7(msgSearch.getE01CUSBRA());
		    userPO.setHeader8(msgSearch.getE01CUSOFC());
			userPO.setHeader9(msgSearch.getE01CUSVD1());
			userPO.setHeader10(msgSearch.getE01CUSVD2());
			userPO.setHeader11(msgSearch.getE01CUSVD3());
			userPO.setHeader12(msgSearch.getE01CUSTD1());
			userPO.setHeader13(msgSearch.getE01CUSTD2());
			userPO.setHeader14(msgSearch.getE01CUSTD3());	

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU001001 Message Sent");
		
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
		   
		    if (newmessage.getFormatName().equals("ECU001001")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU001001Message) newmessage;

					marker = msgList.getH01FLGMAS();

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
						flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_members_inq_list.jsp");
						callPage(LangPath + "ECU0010_bastanteo_members_inq_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_enter_memb_inq.jsp");
						callPage(LangPath + "ECU0010_bastanteo_enter_memb_inq.jsp", req, res);						
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
		ECU001001Message msgMT = null;
		ECU001002Message msgSearch = null;
		ECU001002Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (datapro.eibs.beans.JBObjList) ses.getAttribute("mtList");
		
		int row = 0;
				try { row = Integer.parseInt(req.getParameter("CURRENTROW"));} catch (Exception e) {}
				beanList.setCurrentRow(row);
				msgMT = (datapro.eibs.beans.ECU001001Message) beanList.getRecord();
				userPO.setHeader16(msgMT.getE01CUSCUN());
				userPO.setHeader17(msgMT.getE01CUSNA1());

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECU001002Message) mc.getMessageRecord("ECU001002");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("ECU0010");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");

			//all the fields here
			try {
				msgSearch.setE02CUMCUN(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSVD1(userPO.getHeader9());
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSVD2(userPO.getHeader10());
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSVD3(userPO.getHeader11());
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSTD1(userPO.getHeader12());
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSTD2(userPO.getHeader13());
			} catch (Exception e) {
			}
			try { 
				msgSearch.setE02CUSTD3(userPO.getHeader14());
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECU001002 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

		    if (newmessage.getFormatName().equals("ECU001002")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (ECU001002Message) newmessage;

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
				ses.setAttribute("mtListMem", beanList);				
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECU0010_bastanteo_members_list.jsp");
					callPage(LangPath + "ECU0010_bastanteo_members_list.jsp", req, res);						
				
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

			int screen = R_LIST_CUS_INQ;

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
						case A_LIST_CUST_INQ :							
							procActionListCustInq(mc, msgUser, req, res, session);
							break;
						// Miembros de la Junta Directiva
						case A_LIST_MEMBERS_INQ :
							procActionListMemInq(mc, msgUser, req, res, session);
							break;
						case A_LIST_MEMBERS :
							procActionListMem(mc,msgUser, req, res, session);
							break;
						// Solicitud de Filtros de Seleccion
						case R_LIST_CUS_INQ :
							procReqSearchCusInq(msgUser, req, res, session);
							break;
						case R_LIST_MEM_INQ :
							procReqSearchMemInq(msgUser, req, res, session);
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