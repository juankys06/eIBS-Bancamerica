package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (10/18/04 6:53:31 PM)
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

public class JSEEJ0040 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH = 100;
	protected static final int A_LIST = 200;
	protected static final int A_RETURN = 300;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEEJ0040() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEEJ0040");

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
		
		EEJ004001Message msgSearch = new EEJ004001Message();
		ses.setAttribute("msgMT", msgSearch);
		
		try {
			flexLog("About to call Page: " + LangPath + "EEJ0040_officers_levels_enter_inq.jsp");
			callPage(LangPath + "EEJ0040_officers_levels_enter_inq.jsp", req, res);
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

		EEJ004001Message msgMTLvl = new EEJ004001Message();
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EEJ004001Message msgSearch = null;
		EEJ004001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		beanList = (JBObjList) ses.getAttribute("mtListLvl");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EEJ004001Message) mc.getMessageRecord("EEJ004001");
			
			//all the fields here
			if (beanList != null) {
				int idx = 0;
				idx = Integer.parseInt(req.getParameter("CURRCODE"));

				beanList.setCurrentRow(idx);
				datapro.eibs.beans.EEJ004001Message msgMTList = (datapro.eibs.beans.EEJ004001Message) beanList.getRecord();
				
				msgSearch.setE01EJVOFC(msgMTList.getE01EJVOFC());

			} else {
				try {
					msgSearch.setE01EJVOFC(req.getParameter("E01EJVOFC").toUpperCase());
				} catch (Exception e) {}
			}
			
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EEJ0040");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EEJ004001 Message Sent");
		

			// Receive Error Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			// Receive Officer Information
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EEJ004001"))
				ses.setAttribute("msgMTLvl", newmessage); 
					
			// Receive Officer Dependents List
			if (IsNotError) {
				
				//Save officer's codes
				
				if (userPO.getHeader1().equals("")) {
					userPO.setHeader1(msgSearch.getE01EJVOFC());
				} else {
					if (userPO.getHeader2().equals("")) {
						userPO.setHeader2(msgSearch.getE01EJVOFC());
					} else {
						if (userPO.getHeader3().equals("")) {
							userPO.setHeader3(msgSearch.getE01EJVOFC());
						} else {
							if (userPO.getHeader4().equals("")) {
								userPO.setHeader4(msgSearch.getE01EJVOFC());
							} else {
								if (userPO.getHeader5().equals("")) {
									userPO.setHeader5(msgSearch.getE01EJVOFC());
								} else {
									if (userPO.getHeader6().equals("")) {
										userPO.setHeader6(msgSearch.getE01EJVOFC());
									} else {
										if (userPO.getHeader7().equals("")) {
											userPO.setHeader7(msgSearch.getE01EJVOFC());
										} else {
											if (userPO.getHeader8().equals("")) {
												userPO.setHeader8(msgSearch.getE01EJVOFC());
											} else {
												if (userPO.getHeader9().equals("")) {
													userPO.setHeader9(msgSearch.getE01EJVOFC());
												} else {
													if (userPO.getHeader10().equals("")) {
														userPO.setHeader10(msgSearch.getE01EJVOFC());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				newmessage = mc.receiveMessage();
		   
				if (newmessage.getFormatName().equals("EEJ004001")) {
		    	
					beanList = new JBObjList();
					String marker = "";

					while (true) {

						msgList = (EEJ004001Message) newmessage;

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
					ses.setAttribute("mtListLvl", beanList);
				
				}				
				
				try {
						flexLog("About to call Page: " + LangPath + "EEJ0040_officers_levels_inq_list.jsp");
						callPage(LangPath + "EEJ0040_officers_levels_inq_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				ses.setAttribute("error", msgError);
				flexLog("About to call Page: " + LangPath + "EEJ0040_officers_levels_enter_inq.jsp");
				callPage(LangPath + "EEJ0040_officers_levels_enter_inq.jsp", req, res);
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
	protected void procActionReturn(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EEJ004001Message msgMTLvl = new EEJ004001Message();
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EEJ004001Message msgSearch = null;
		EEJ004001Message msgList = null;
		
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		boolean ExitFlag = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			msgSearch = (EEJ004001Message) mc.getMessageRecord("EEJ004001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EEJ0040");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");
			
			//all the fields here
			if (userPO.getHeader10()!= "") {
				msgSearch.setE01EJVOFC(userPO.getHeader9());
				userPO.setHeader10("");
			} else {
				if (userPO.getHeader9()!= "") {
					msgSearch.setE01EJVOFC(userPO.getHeader8());
					userPO.setHeader9("");
				} else {
					if (userPO.getHeader8()!= "") {
						msgSearch.setE01EJVOFC(userPO.getHeader7());
						userPO.setHeader8("");
					} else {
						if (userPO.getHeader7()!= "") {
							msgSearch.setE01EJVOFC(userPO.getHeader6());
							userPO.setHeader7("");
						} else {
							if (userPO.getHeader6()!= "") {
								msgSearch.setE01EJVOFC(userPO.getHeader5());
								userPO.setHeader6("");
							} else {
								if (userPO.getHeader5()!= "") {
									msgSearch.setE01EJVOFC(userPO.getHeader4());
									userPO.setHeader5("");
								} else {
									if (userPO.getHeader4()!= "") {
										msgSearch.setE01EJVOFC(userPO.getHeader3());
										userPO.setHeader4("");
									} else {
										if (userPO.getHeader3()!= "") {
											msgSearch.setE01EJVOFC(userPO.getHeader2());
											userPO.setHeader3("");
										} else {
											if (userPO.getHeader2()!= "") {
												msgSearch.setE01EJVOFC(userPO.getHeader1());
												userPO.setHeader2("");
											} else {
												if (userPO.getHeader1()!= "") {
													userPO.setHeader1("");
													ses.setAttribute("msgMT", null);
													ses.setAttribute("mtListLvl", null);
													ses.setAttribute("msgMTLvl", null);
													callPage(LangPath + "EEJ0040_officers_levels_enter_inq.jsp", req, res);
													ExitFlag = true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		    
			if (!ExitFlag) {
		    
				msgSearch.send();
				msgSearch.destroy();
				flexLog("EEJ004001 Message Sent");

				// Receive Error Message
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {

					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					ses.setAttribute("error", msgError);
				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				// Receive Officer Information
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EEJ004001"))
					ses.setAttribute("msgMTLvl", newmessage); 
					
					// Receive Officer Dependents List
					newmessage = mc.receiveMessage();
		   
				if (newmessage.getFormatName().equals("EEJ004001")) {
		    	
					beanList = new JBObjList();
					String marker = "";

					while (true) {

						msgList = (EEJ004001Message) newmessage;

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
					ses.setAttribute("mtListLvl", beanList);				
				
					try {
						if (IsNotError) { // There are no errors
							flexLog("About to call Page: " + LangPath + "EEJ0040_officers_levels_inq_list.jsp");
							callPage(LangPath + "EEJ0040_officers_levels_inq_list.jsp", req, res);						
						} 
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				
			}
			
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

			int screen = R_SEARCH;

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
						case R_SEARCH :
							procReqSearch(msgUser, req, res, session);
							break;
						case A_LIST :							
							procReqList(mc, msgUser, req, res, session);
							break;
						case A_RETURN :							
							procActionReturn(mc, msgUser, req, res, session);
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