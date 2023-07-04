package datapro.eibs.helps;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EPR010001Message;
import datapro.eibs.beans.EPR011001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;



public class JSEPR0110 extends datapro.eibs.master.SuperServlet {

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	
	protected static final int R_LIST_INCOMING_OPENING = 1;
	protected static final int R_LIST_INCOMING_PAYMENT = 2;	
	
	protected String LangPath = "S";
	
	public JSEPR0110() {
		super();
	}
	/**
	 * This method was created by David Mavilla
	public void destroy() {
	
		flexLog("free resources used by JSESS00    40");
		
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

	protected void procReqListIncomingOpening(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR011001Message msgList = null;
		EPR010001Message msgPR = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			msgList = new datapro.eibs.beans.EPR011001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

		
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0002"); //Opening
			msgList.setH01FLGWK2("I");
			
			//msgList.send();
			mc.sendMessage(msgList);
			msgList.destroy();
			flexLog("EPR011001 Message Sent");
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
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page3: " + LangPath + "EPR1060_pr_inq_search.jsp");
					callPage(LangPath + "EPR1060_pr_inq_search.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("EPR011001")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String priNum = "";
					 try{
					 	priNum = req.getParameter("PRINUM");
					 }
					 catch(Exception e){
					 }

					//if (priNum == null)
					//	firstTime = true;
					//else
					//	firstTime = false;

					int indexRow = 0;
					while (true) {

						msgList = (EPR011001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
								chk = "checked";
							} else {
								if (msgList.getE01WRTNUM().trim().equals(priNum))
									chk = "checked";
								else
									chk = "";
							}

							myRow = new StringBuffer("<TR>");
							
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ msgList.getE01WRTNUM()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ Util.fcolorCCY(msgList.getE01WRTAMT())
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ msgList.getE01REMARK()
										+ "</a></td>");
							
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

						flexLog("Putting java beans into the session");
						ses.setAttribute("epr0110Help", beanList);

					if (beanList.getNoResult()) {
						try {
							flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
							res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						flexLog("Putting java beans into the session");
						try {
							flexLog("About to call Page: " + LangPath + "EPR0110_wires_help.jsp");
							callPage(LangPath + "EPR0110_wires_help.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListIncomingPayment(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR011001Message msgList = null;
		EPR010001Message msgPR = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			msgList = new datapro.eibs.beans.EPR011001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
		
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001"); //Payment
			msgList.setH01FLGWK2("I");
			
			//msgList.send();
			mc.sendMessage(msgList);
			msgList.destroy();
			flexLog("EPR011001 Message Sent");
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
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page3: " + LangPath + "EPR1060_pr_inq_search.jsp");
					callPage(LangPath + "EPR1060_pr_inq_search.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("EPR011001")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String priNum = "";
					 try{
					 	priNum = req.getParameter("PRINUM");
					 }
					 catch(Exception e){
					 }

					//if (priNum == null)
					//	firstTime = true;
					//else
					//	firstTime = false;

					int indexRow = 0;
					while (true) {

						msgList = (EPR011001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
								chk = "checked";
							} else {
								if (msgList.getE01WRTNUM().trim().equals(priNum))
									chk = "checked";
								else
									chk = "";
							}

							myRow = new StringBuffer("<TR>");
							
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ msgList.getE01WRTNUM()
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ Util.fcolorCCY(msgList.getE01WRTAMT())
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgList.getE01WRTNUM()
										+ "','"
										+ msgList.getE01WRTAMT()
										+ "')\">"
										+ msgList.getE01REMARK()
										+ "</a></td>");
							
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

						flexLog("Putting java beans into the session");
						ses.setAttribute("epr0110Help", beanList);

					if (beanList.getNoResult()) {
						try {
							flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
							res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						flexLog("Putting java beans into the session");
						try {
							flexLog("About to call Page: " + LangPath + "EPR0110_wires_help.jsp");
							callPage(LangPath + "EPR0110_wires_help.jsp", req, res);
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

			int screen = R_LIST_INCOMING_OPENING;

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

				switch (screen) {
					// BEGIN PR
					// Request
					case R_LIST_INCOMING_OPENING :
						procReqListIncomingOpening(mc, msgUser, req, res, session);
						break;
					case R_LIST_INCOMING_PAYMENT :
						procReqListIncomingPayment(mc, msgUser, req, res, session);
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


}