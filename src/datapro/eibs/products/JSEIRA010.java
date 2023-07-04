package datapro.eibs.products;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import datapro.eibs.master.SuperServlet;

public class JSEIRA010 extends SuperServlet {

	protected static final int R_ENTER 		= 1;
	protected static final int R_LIST 		= 11;
	protected static final int R_NEW 		= 100;
	protected static final int R_MAINT 		= 200;
	protected static final int A_DELETE 	= 400;
	protected static final int A_MAINT 		= 500;

	protected String LangPath = "E";
	/**
	 * JSEXEDD0000 constructor comment.
	 */
	public JSEIRA010() {
		super();
	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		flexLog("free resources used by JSEIRA010");
	}


	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		EDL013001Message msgGet = null;

		try {
			msgError = new ELEERRMessage();

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			msgGet = (EDL013001Message) ses.getAttribute("cdMant");
			userPO.setHeader1(msgGet.getE01DEACUN());
			userPO.setHeader2(msgGet.getE01CUSNA1());
			userPO.setHeader3(msgGet.getE01DEAACC());		

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		
			//userPO.setHeader4(msgGet.getD01IRADSC());			
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EIRA010_ira_activity_corrections_enter.jsp");
			callPage(LangPath + "EIRA010_ira_activity_corrections_enter.jsp", req, res);
		} catch (Exception e) {
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
		EIRA01001Message msgSearch = null;
		EIRA01001Message msgList = null;
				
		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EIRA01001Message) mc.getMessageRecord("EIRA01001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EIRA010");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			msgSearch.setE01IRDACC(req.getParameter("E01IRDACC"));
			msgSearch.setE01IRDTXY(req.getParameter("E01IRDTXY"));

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EIRA01001 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				//ses.setAttribute("msgPart", msgSearch);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("EIRA01001")) {
				beanList = new JBObjList();
				String marker = "";
				boolean firstTime = true;

				while (true) {
					msgList = (EIRA01001Message) newmessage;
					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						if (firstTime == false) {
							userPO.setHeader7(msgList.getD01ENDBAL());
						}			
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getD01DEACUN());
								userPO.setHeader2(msgList.getD01CUSNA1());
								userPO.setHeader3(msgList.getE01IRDACC());
								userPO.setHeader4(msgList.getD01IRADSC());
								userPO.setHeader5(msgList.getE01IRDYEA());
								userPO.setHeader6(msgList.getD01BEGBAL());
						}						
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", beanList);
				ses.setAttribute("userPO", userPO);	
				
				try {
					flexLog("About to call Page: " + LangPath + "EIRA010_ira_activity_corrections_list.jsp");
					callPage(LangPath + "EIRA010_ira_activity_corrections_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
				
			} else {
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
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EIRA01001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
		// Send Initial data
		try {
			
			//Get the message from the row 
			beanList = (JBObjList) ses.getAttribute("msgList");
			beanList.setCurrentRow(Integer.parseInt(req.getParameter("CURRENTROW")));
			msgPart = (EIRA01001Message) beanList.getRecord();
		
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIRA010");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0005");
			
			msgPart.setE01IRDNTC(req.getParameter("E01IRDNTC"));

			mc.sendMessage(msgPart);
			//msgPart.send();
			msgPart.destroy();
			flexLog("EIRA01001 Message Sent");
	
		// Receive Error Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else 
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		// Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EIRA01001")) {
				msgPart = new datapro.eibs.beans.EIRA01001Message();
				msgPart = (EIRA01001Message) newmessage;
				flexLog("EIRA01001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgPart", msgPart);
				ses.setAttribute("userPO", userPO);
	
				try {
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSEIRA010?SCREEN=11" + 
						"&E01IRDACC=" + msgPart.getE01IRDACC() +
						"&E01IRDTXY=" + msgPart.getE01IRDTXY());
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
						case R_ENTER:
							procReqEnter(mc, msgUser, req, res, session);
							break;						
						case R_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;	
						case A_MAINT :
							procActionMaint(mc,msgUser, req, res, session);
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

	protected void showERROR(ELEERRMessage m){
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