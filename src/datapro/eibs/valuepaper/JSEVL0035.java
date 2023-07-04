package datapro.eibs.valuepaper; 

/**
 * Insert the type's description here.
 * Creation date: (3/22/06 6:25:00 PM)
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

public class JSEVL0035 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_LIST = 100;
	protected static final int A_DELETE = 200;
	protected static final int R_LIST = 500;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEVL0035() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEVL0035");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EVL003501Message msgSearch = new EVL003501Message();
		UserPos userPO = new UserPos();
		
		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		userPO.setBank(user.getE01UBK());
		userPO.setBranch(user.getE01UBR());
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "EVL0035_value_paper_mov_rev_inq.jsp");
			callPage(LangPath + "EVL0035_value_paper_mov_rev_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EVL003501Message msgSearch = null;
		EVL003501Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EVL003501Message) mc.getMessageRecord("EVL003501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EVL0035");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			//all the fields here
			try { 
				msgSearch.setE01MOVBNK(req.getParameter("E01MOVBNK").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVBNK(userPO.getBank());
			}
			try { 
				msgSearch.setE01MOVBRN(req.getParameter("E01MOVBRN").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVBRN(userPO.getBranch());
			}
			try { 
				msgSearch.setE01MOVTIP(req.getParameter("E01MOVTIP").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVTIP(userPO.getProdCode());
			}
			try { 
				msgSearch.setE01MOVSUB(req.getParameter("E01MOVSUB").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVSUB(userPO.getType());
			}
			try { 
				msgSearch.setE01MOVFD1(req.getParameter("E01MOVFD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVFD1(userPO.getHeader10());
			}
			try { 
				msgSearch.setE01MOVFD2(req.getParameter("E01MOVFD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVFD2(userPO.getHeader11());
			}
			try { 
				msgSearch.setE01MOVFD3(req.getParameter("E01MOVFD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVFD3(userPO.getHeader12());
			}
			try { 
				msgSearch.setE01MOVTD1(req.getParameter("E01MOVTD1").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVTD1(userPO.getHeader13());
			}
			try { 
				msgSearch.setE01MOVTD2(req.getParameter("E01MOVTD2").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVTD2(userPO.getHeader14());
			}
			try { 
				msgSearch.setE01MOVTD3(req.getParameter("E01MOVTD3").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01MOVTD3(userPO.getHeader15());
			}
		    	
			userPO.setBank(msgSearch.getE01MOVBNK());
			userPO.setBranch(msgSearch.getE01MOVBRN());
			userPO.setProdCode(msgSearch.getE01MOVTIP());
			userPO.setType(msgSearch.getE01MOVSUB());
			userPO.setHeader10(msgSearch.getE01MOVFD1());
			userPO.setHeader11(msgSearch.getE01MOVFD2());
			userPO.setHeader12(msgSearch.getE01MOVFD3());
			userPO.setHeader13(msgSearch.getE01MOVTD1());
			userPO.setHeader14(msgSearch.getE01MOVTD2());
			userPO.setHeader15(msgSearch.getE01MOVTD3());    

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EVL003501 Message Sent");
		
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
		   
		    if (newmessage.getFormatName().equals("EVL003501")) {
		    	
		    	beanList = new JBObjList();
				String marker = "";

				while (true) {

					msgList = (EVL003501Message) newmessage;

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
						flexLog("About to call Page: " + LangPath + "EVL0035_value_paper_mov_rev_list.jsp");
						callPage(LangPath + "EVL0035_value_paper_mov_rev_list.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "EVL0035_value_paper_mov_rev_inq.jsp");
						callPage(LangPath + "EVL0000_value_paper_mov_rev_inq.jsp", req, res);						
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
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		JBObjList beanList = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		try {
			
			beanList = (JBObjList) ses.getAttribute("mtList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
			
			EVL003501Message msgMT = (EVL003501Message) beanList.getRecord();		
			msgMT.setH01OPECOD("0005");
			mc.sendMessage(msgMT);
			//msgMT.destroy();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
		
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EVL003501")) {
		
				msgMT = (EVL003501Message) newmessage;
		
				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.valuepaper.JSEVL0035?SCREEN=100");
				}
			    else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
	
						flexLog("About to call Page: " + LangPath + "EVL0035_value_paper_mov_rev_list.jsp");
						callPage(LangPath + "EVL0035_value_paper_mov_rev_list.jsp", req, res);						

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
						case A_DELETE :
							procActionDelete(mc,msgUser, req, res, session);
							break;
						case A_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;	
						case R_LIST :
							procReqSearch(msgUser, req, res, session);
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