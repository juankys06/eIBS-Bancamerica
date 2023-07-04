package datapro.eibs.general;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.sockets.*;
import datapro.eibs.tools.JSEODPDFSend;
import datapro.eibs.master.*;

public class JSEWD0900 extends SuperServlet {

	// Limits Inquiry
	static final int R_LIST   = 1;
	static final int A_LIST   = 2;
	static final int D_LIST   = 3;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0900() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0900DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String Nexte ="";
		// Send Initial data
		try {
			msgList = (EWD0900DSMessage) mc.getMessageRecord("EWD0900DS");
			
				
			try { //From Pos
					
				msgList.setRWDFRC(req.getParameter("Pos"));
				
			} catch (Exception e) {
				
			}
			msgList.setRWDTYP("L");
				
			 // User
			msgList.setRWDUSR(user.getH01USR());
			
			
			msgList.send();
			msgList.destroy();
			flexLog("EWD0900DS Message Sent");
		
		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				
				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.general.JSEWD0900?SCREEN=100");

			} else if (newmessage.getFormatName().equals("EWD0900DS")) {

				    msgError = new ELEERRMessage();
					beanList = new JBObjList();
					boolean firstTime = true;
					String marker = "";
					
					while (true) {

						msgList = (EWD0900DSMessage) newmessage;

						marker = msgList.getSWDOPE();
						
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(Integer.parseInt(msgList.getSWDREC()));
							}
							
							beanList.addRow(msgList);
														
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								//beanList.setLastRec(Integer.parseInt(msgList.getSWDREC()));
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EWD0900Help", beanList);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("error", msgError);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0900_sel_spool.jsp");
						callPage(LangPath + "EWD0900_sel_spool.jsp", req, res);
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
		EWD0900DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
        beanList = (JBObjList) ses.getAttribute("EWD0900Help");
        
        beanList.initRow();
        
		String checked = "";
		String path = "";
		int k = 0;
		int opt = 2;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		}
		catch (Exception e) {}
        
		try {
			while (beanList.getNextRow()) {
				try {
					flexLog(req.getParameter("ROW_" + k));
					checked = req.getParameter("ROW_" + k);
					if (checked == null) { checked = ""; }
				} catch (Exception e) {
					checked = "";
				}
				
				if (opt == 1) checked = req.getParameter("row");
				flexLog(checked);
				
				// Send Initial Data
				if (!checked.equals("")) {
			        if (opt == 2) beanList.setCurrentRow(k);
			        else beanList.setCurrentRow(Integer.parseInt(checked));
			        	
					msgList = (EWD0900DSMessage) beanList.getRecord();
					flexLog("File = " + msgList.getSWDFIL());

					if (opt == 2) msgList.setRWDTYP("D");
					else msgList.setRWDTYP("I");
					
					mc.sendMessage(msgList);
					flexLog("EWD0900DS Message Sent");
				
					// Receive Data
				
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage)newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						ses.setAttribute("error", msgError);
					}
					
					newmessage = mc.receiveMessage();
					
					if (newmessage.getFormatName().equals("EWD0900DS")) {
						msgList = (EWD0900DSMessage) newmessage;
						flexLog("EWD0900DS Message Received");

						path = msgList.getSWDPTH();

					} else
						flexLog("Message " + newmessage.getFormatName() + " received.");
				}
				
				k++;
				msgList = null;
				if (opt == 1) break;
			}				
			
			if (IsNotError) {
				if (opt == 2)
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.general.JSEWD0900?SCREEN=1");
				else { //Inquiry -- getPDF
					String pdfPath = JSEIBSProp.getEODPDFURL() + "/" + path;					
					if (JSEIBSProp.getEODDirectUrl()) {
						res.sendRedirect(pdfPath);
						
					} else {
						JSEODPDFSend send = new JSEODPDFSend();
	                	
	                	req.setAttribute("REPNAME",pdfPath );
	                	send.service(req,res);
					}
				}
			} else { //errors
				try {
					if (opt == 2) {
						flexLog("About to call Page: " + LangPath + "EWD0900_sel_spool.jsp");
						callPage(LangPath + "EWD0900_sel_spool.jsp", req, res);
					} else {
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
					}
					
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
	protected void procActionDelete(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			EWD0900DSMessage msgList = null;
			ELEERRMessage msgError = null;
			JBObjList beanList = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	        
			try {
							
				msgList = (EWD0900DSMessage) mc.getMessageRecord("EWD0900DS");
				msgList.setRWDUSR(user.getH01USR());
				msgList.setRWDTYP("T");
				mc.sendMessage(msgList);
				//msgList.destroy();
				flexLog("EWD0900DS Message Sent");
			
			// Receive Data
			
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {

					
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					ses.setAttribute("error", msgError);
				
				}
				
				newmessage = mc.receiveMessage();
				
				if (newmessage.getFormatName().equals("EWD0900DS")) {
						
						msgList = (EWD0900DSMessage)newmessage;
						
						if (IsNotError) {
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.general.JSEWD0900?SCREEN=1");
						}	else { 
								flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
								callPage(LangPath + "error_viewer.jsp", req, res);
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
		MessageProcessor mp = null;

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
					flexLog("Opening Socket Connection");
					mc = new MessageContext(getMessageHandler("EWD0900", req));

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
							break;
						case D_LIST :
							procActionDelete(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
			   } catch (Exception e) {
			   		e.printStackTrace();
			   		flexLog("Error: " + e);
			   		res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
			   } finally {
					mc.close();
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