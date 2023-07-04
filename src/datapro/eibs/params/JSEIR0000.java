package datapro.eibs.params;

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

public class JSEIR0000 extends datapro.eibs.master.SuperServlet {

	static final int R_ENTER  	= 1;
	static final int R_LIST  	= 11;
	
	static final int R_NEW 	 	= 100;
	static final int R_MAINT  	= 200;
	static final int R_INQ  	= 300;	
	static final int A_DELETE  	= 400;
	static final int A_MAINT  	= 500;

	private String LangPath = "E";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEIR0000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0156");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnter(
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
					+ "EIR0000_ira_account_type_enter.jsp");
			callPage(LangPath + "EIR0000_ira_account_type_enter.jsp", req, res);
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
		EIR000001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			msgList = (EIR000001Message) mc.getMessageRecord("EIR000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EIR0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");

			msgList.setE01IRATYP(req.getParameter("E01IRATYP"));

			//if (req.getParameter("E01IRATYP").equals("")) {
			//	msgList.setE01IRATYP(userPO.getHeader1());
			//} else {			
			//	msgList.setE01IRATYP(req.getParameter("E01IRATYP"));
			//}
							
			msgList.send();
			msgList.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Or Data
		try {
			newmessage = mc.receiveMessage();
			// Error
			if (newmessage.getFormatName().equals("ELEERR")) {
				try {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
								
				showERROR(msgError);
				beanList.setNoResult(true);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_type_enter.jsp");
					callPage(LangPath + "EIR0000_ira_account_type_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			// Data
			} else if (newmessage.getFormatName().equals("EIR000001")) {
					beanList = new JBList();
					String marker = "";
					boolean firstTime =true;
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {
						msgList = (EIR000001Message) newmessage;
						marker = msgList.getH01FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							userPO.setHeader1(msgList.getE01IRATYP());
							userPO.setHeader2(msgList.getD01IRADSC());
							chk = "checked";
							}
						else {
							chk = "";
						}						
			
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"RAD_IND\" value=\""
								+ indexRow	+ "\" "	+ chk
								+ " onclick=\"getParams('"	+ msgList.getE01IRAYEA() + "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>"  + msgList.getE01IRAYEA() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>"  + msgList.getE01IRAAC1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>"  + msgList.getE01IRAAG1() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>"  + msgList.getE01IRAAC2() + "</td>");
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
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("partList", beanList);

					try {
						flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_list.jsp");
						callPage(LangPath + "EIR0000_ira_account_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
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
		EIR000001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		// Send Initial data
		try {
			msgPart = (EIR000001Message) mc.getMessageRecord("EIR000001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIR0000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0001");
			
			try{
				msgPart.setE01IRATYP(req.getParameter("E01IRATYP"));
				msgPart.setE01IRAYEA(req.getParameter("E01IRAYEA"));
			} catch (Exception e) {
			}	

			msgPart.send();
			msgPart.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Y Data
		try {
			// Error
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
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
		
		try {
			// Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EIR000001")) {
				msgPart = new datapro.eibs.beans.EIR000001Message();
				msgPart = (EIR000001Message) newmessage;
				flexLog("EIR000001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("iraAcc", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_basic.jsp");
						//res.sendRedirect(super.srctx + LangPath + "EIR0000_ira_account_basic.jsp");
						callPage(LangPath + "EIR0000_ira_account_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_type_enter.jsp");
							callPage(LangPath + "EIR0000_ira_account_list.jsp", req, res);
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

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			EIR000001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			msgPart = (EIR000001Message) mc.getMessageRecord("EIR000001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIR0000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0003");
		
			try{
				msgPart.setE01IRATYP(req.getParameter("E01IRATYP"));
				msgPart.setE01IRAYEA(req.getParameter("E01IRAYEA"));
			} catch (Exception e) {
			}
			
			msgPart.send();
			msgPart.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// Receive Error Y Data
		// Error
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
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
			if (newmessage.getFormatName().equals("EIR000001")) {
				msgPart = new datapro.eibs.beans.EIR000001Message();
				msgPart = (EIR000001Message) newmessage;
				flexLog("EIR000001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("iraAcc", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_basic_readonly.jsp");
						callPage(LangPath + "EIR0000_ira_account_basic_readonly.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.params.JSEIR0000?SCREEN=11" );
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

	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			EIR000001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		
		// Send Initial data
		try {
			msgPart = (EIR000001Message) mc.getMessageRecord("EIR000001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIR0000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0002");
		
			try{
				msgPart.setE01IRATYP(req.getParameter("E01IRATYP"));
				msgPart.setE01IRAYEA(req.getParameter("E01IRAYEA"));
			} catch (Exception e) {
			}
			
			msgPart.send();
			msgPart.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// Receive Error Y Data
		// Error
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
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
			if (newmessage.getFormatName().equals("EIR000001")) {
				msgPart = new datapro.eibs.beans.EIR000001Message();
				msgPart = (EIR000001Message) newmessage;
				flexLog("EIR000001 Message Received");
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("iraAcc", msgPart);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_basic.jsp");
						callPage(LangPath + "EIR0000_ira_account_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.params.JSEIR0000?SCREEN=11" );
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

	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EIR000001Message msgPart = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		String opCode = "";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (userPO.getPurpose().equals("NEW")) {
			opCode = "0001";
		} else {
			opCode = "0005";
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPart = (EIR000001Message) mc.getMessageRecord("EIR000001");
			//msgPart = (EIR000001Message) ses.getAttribute("iraAcc");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIR0000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgPart.fieldEnumeration();
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
			
			msgPart.send();
			msgPart.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Error Message
		try {
			// Error
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
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
			// Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EIR000001")) {
				msgPart = new datapro.eibs.beans.EIR000001Message();
				msgPart = (EIR000001Message) newmessage;
				flexLog("EIR000001 Message Received");
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				//ses.setAttribute("iraAcc", msgPart);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + 
												"/servlet/datapro.eibs.params.JSEIR0000?SCREEN=11"
												+ "&E01IRATYP="
												+ req.getParameter("E01IRATYP"));
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Exception ocurred. Exception = " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EIR0000_ira_account_basic.jsp");
						callPage(LangPath + "EIR0000_ira_account_basic.jsp", req, res);
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

	protected void procActionDel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
			
			MessageRecord newmessage = null;
			EIR000001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			msgPart = (EIR000001Message) mc.getMessageRecord("EIR000001");
			msgPart.setH01USERID(user.getH01USR());
			msgPart.setH01PROGRM("EIR0000");
			msgPart.setH01TIMSYS(getTimeStamp());
			msgPart.setH01SCRCOD("01");
			msgPart.setH01OPECOD("0004");
		
			try{
				msgPart.setE01IRATYP(req.getParameter("E01IRATYP"));
				msgPart.setE01IRAYEA(req.getParameter("E01IRAYEA"));
			} catch (Exception e) {
			}
			
			msgPart.send();
			msgPart.destroy();
			flexLog("EIR000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		try {
			// Receive Error
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				try {
					res.sendRedirect(super.srctx + 
									"/servlet/datapro.eibs.params.JSEIR0000?SCREEN=11"
									+ "&E01IRATYP="
									+ req.getParameter("E01IRATYP"));
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
					// Requests
					case R_ENTER :
						procReqEnter(msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;	
					case R_INQ :
						procReqInquiry(mc, msgUser, req, res, session);
						break;					
					// Actions
					case A_MAINT :
						procActionMaint(mc, msgUser, req, res, session);
						break;
					case A_DELETE :
						procActionDel(mc, msgUser, req, res, session);
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