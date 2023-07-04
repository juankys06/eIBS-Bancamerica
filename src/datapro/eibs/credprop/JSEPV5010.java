package datapro.eibs.credprop;

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

public class JSEPV5010 extends datapro.eibs.master.SuperServlet {


	static final int R_ENTER_SEARCH_PROP = 100;
	static final int R_LIST  			 = 200;
	static final int R_MAINT_PROP		 = 700;
	
	static final int A_MAINT_CUSTOMER	 = 400;
	static final int A_MAINT_PROPOSAL	 = 800;
	
	
	private String LangPath = "S";

	/**
	 * JSEPV5000 constructor comment.
	 */
	public JSEPV5010() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPV5000");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	
	
		protected void procActionMaintProposal(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EPV501002Message msgProp = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int acctype = 0;
			String source = null;
				
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgProp = (EPV501002Message) ses.getAttribute("proposal");
				msgProp.setH02USR(user.getH01USR());
				msgProp.setH02PGM("EPV501002");
				msgProp.setH02TIM(getTimeStamp());
				msgProp.setH02SCR("01");
				msgProp.setH02OPE("0005");
	
				// all the fields here
				java.util.Enumeration enu = msgProp.fieldEnumeration();
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
	
				//msgProp.send();
				mc.sendMessage(msgProp);
				msgProp.destroy();
				flexLog("EPV501002 Message Sent");
			
	
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
	
				if (newmessage.getFormatName().equals("EPV501002")) {
						
					msgProp = (EPV501002Message) newmessage;
					
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("proposal", msgProp);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) {  // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EPV5010_proposal_selection.jsp");
							callPage(LangPath + "EPV5010_proposal_selection.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else {  // There are errors
						try {
							flexLog("About to call Page: /pages/" + LangPath + "EPV5010_proposal_verification.jsp");
							callPage(LangPath + "EPV5010_proposal_verification.jsp", req, res);	
						}
						catch (Exception e) {
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

	protected void procReqSearchProposal(
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

		
		msgError = new ELEERRMessage();
		  
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

					try {
						flexLog("About to call Page: " + LangPath + "EPV5010_proposal_verif_enter.jsp");
						callPage(LangPath + "EPV5010_proposal_verif_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

	}
	
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV500002Message msgList = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		msgError = new ELEERRMessage();

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EPV500002Message) mc.getMessageRecord("EPV500002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EPV500002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			

			try { 
				msgList.setE02SELTYP(req.getParameter("TYPSEL").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			try { 
				msgList.setE02SELIDE(req.getParameter("E01PVMIDN").toUpperCase());
				userPO.setCusNum(req.getParameter("E01PVMIDN"));
			} catch (Exception e) {
				
			}

			try { 
				msgList.setE02SELUSR(user.getH01USR());
				msgList.setE02PVMSTS(req.getParameter("E02PVMSTS"));				
			} catch (Exception e) {
				
			}
			
			
			msgList.send();
			msgList.destroy();
			flexLog("ESD020501 Message Sent");
		

		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
			else if (newmessage.getFormatName().equals("EPV500002")) {

					beanList = new JBObjList();

					String marker = "";
					
					while (true) {

						msgList = (EPV500002Message) newmessage;

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
					ses.setAttribute("EPV5000Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EPV5010_proposal_selection.jsp");
						callPage(LangPath + "EPV5010_proposal_selection.jsp", req, res);
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


	protected void procReqVerifProp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV501002Message msgProp = null;
		EPV500002Message msgList = null;
		JBObjList beanList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		
		msgError = new ELEERRMessage();
		

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (JBObjList) ses.getAttribute("EPV5000Help");
		int pos = Integer.parseInt(req.getParameter("ACCNUM"));
		beanList.setCurrentRow(pos);
		msgList = (EPV500002Message) beanList.getRecord();
		userPO.setCusName(msgList.getE02PVMNME());
		userPO.setIdentifier(msgList.getE02PVMNUM());
		
//		flexLog("JSEDL0110L-->procReqMaint");

		// Send Initial data
		try {
			msgProp = (EPV501002Message) mc.getMessageRecord("EPV501002");
			msgProp.setH02USR(user.getH01USR());
			msgProp.setH02PGM("EPV5010");
			msgProp.setH02TIM(getTimeStamp());
			msgProp.setH02SCR("01");
			msgProp.setH02OPE("0002");
			
			
			try{
				msgProp.setE02PVVIDN(msgList.getE02PVMCUN());
			} catch (Exception e) {
			}	
			
			try{
				msgProp.setE02PVVNUM(msgList.getE02PVMNUM());
			} catch (Exception e) {
			}
							
			msgProp.send();
			msgProp.destroy();
 		
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

			if (newmessage.getFormatName().equals("EPV501002")) {
				
				msgProp = (EPV501002Message) newmessage;
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("proposal", msgProp);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5010_proposal_verification.jsp");
						callPage(LangPath + "EPV5010_proposal_verification.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5010_proposal_selection.jsp");
						callPage(LangPath + "EPV5010_proposal_selection.jsp", req, res);
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

			int screen = A_MAINT_CUSTOMER;

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
                
                flexLog("service-->screen= " +  screen );
                
				switch (screen) {
					// Requests
					
					case R_ENTER_SEARCH_PROP :					
						procReqSearchProposal(mc, msgUser, req, res, session);
						break;						
					case A_MAINT_PROPOSAL : 
						procActionMaintProposal(mc, msgUser, req, res, session);
						break;	
					case R_LIST :					
						procReqList(mc, msgUser, req, res, session);
						break;	
					case R_MAINT_PROP :					
						procReqVerifProp(mc, msgUser, req, res, session);
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
}