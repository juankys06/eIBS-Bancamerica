package datapro.eibs.forex;
  
import java.io.*;
import java.net.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
 
public class JSEFE0007 extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";
	/**
	 * FX Rate Tolerance by User and Currency.
	 */
	public JSEFE0007() {
		super();
	}

	/**
	 * .
	 * @param logType int
	 */
	public JSEFE0007(int logType) {
		super(logType);

	}
	// 
	//  Menu
	//	
	 
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = 1;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

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
						case  1:		// Request Users List
							procReqList(mc, msgUser, req, res, session);
							break;
						case  2:	 	// Request User-Currency to Update
						case 10:     	// Submit New
							procReqUpdate(mc, msgUser, req, res, session);
							break;
						case  4:     // Submit Delete
						case  5:	 // Submit Update
							procActionUpdate(mc, msgUser, req, res, session, screen);
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

	//
	// Send Users - Currency List	(SCREEN = 1)
	//  
	protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE000701Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;
		try{
			 userPO = new UserPos();
			}
			catch (Exception e)	{
			}
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EFE000701Message) mc.getMessageRecord("EFE000701");
			msgList.setRWDUSR(user.getH01USR());
			msgList.setRWDTYP("L");
			try {
				msgList.setSWDUSR(req.getParameter("FUSER"));
				userPO.setHeader1(req.getParameter("FUSER"));
			} catch (Exception e) {
				msgList.setSWDUSR(" ");
				userPO.setHeader1(" ");
			}
			String Pos = "";
			try {
				Pos = req.getParameter("Pos");
			} catch (Exception e) {}
			try{
			 	msgList.setRWDFRC(Pos);
			}
			catch (Exception e){
			 	msgList.setRWDFRC("0");	
			}
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EFE000701")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				boolean firstTime = true;
				
				while (true) {

					msgList = (EFE000701Message)newmessage;

					marker = msgList.getSWDOPE();
					
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(
						Integer.parseInt(msgList.getSWDREC()));
					} 

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						myRow = new StringBuffer("<TR>");
						myRow.append("<td nowrap  ALIGN=CENTER><input type=\"radio\" name=\"USRCCY\" value=\""
							+ msgList.getSWDUSR()+ msgList.getSWDCCY() + "\" "
							+ " onclick=\"getKey('"	+ msgList.getSWDUSR() + "', '" 	+ msgList.getSWDCCY() + "')\"></td>");
						myRow.append("<td nowrap  ALIGN=CENTER>" + msgList.getSWDUSR() + "</td>");
						myRow.append("<td nowrap  ALIGN=CENTER>" + msgList.getSWDDUS() + "</td>");
						myRow.append("<td nowrap  ALIGN=CENTER>" + msgList.getSWDCCY() + "</td>");
						myRow.append("<td nowrap  ALIGN=CENTER>" + msgList.getSWDDCY() + "</td>");
						myRow.append("<td nowrap  ALIGN=CENTER>" + msgList.getSWDPOR() + "</td>");
						myRow.append("</TR>");
						
												
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
				flexLog("Putting java beans into the session");
				ses.setAttribute("EFE0007List", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					 flexLog("About to call Page: " + LangPath + "EFE0007_fe_user_currency_list.jsp");
					 callPage(LangPath + "EFE0007_fe_user_currency_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	 
	//
	// Send User - Currency Detail (SCREEN = 2)
	//
	protected void procReqUpdate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
 
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE000702Message msgDtl = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
			} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}
		try{
		 userPO = new UserPos();
		}
		catch (Exception e)	{
		}		 
		
//		Send Initial data
		try {			
			flexLog("Send Initial Data");
			msgDtl = (EFE000702Message) mc.getMessageRecord("EFE000702");
			msgDtl.setH01USERID(user.getH01USR());
			msgDtl.setH01PROGRM("EFE0007");
			msgDtl.setH01TIMSYS(getTimeStamp());
			msgDtl.setH01SCRCOD("");
			msgDtl.setE01FEUUSR(req.getParameter("E01FEUUSR"));			 
			msgDtl.setE01FEUCCY(req.getParameter("E01FEUCCY"));
			try {
				msgDtl.setE01FEUPOR(req.getParameter("E01FEUPOR"));
			} catch (Exception e){
			 	msgDtl.setE01FEUPOR("0");	
			}
			if (msgDtl.getE01FEUUSR().equals("")) {
				userPO.setPurpose("NEW");
				msgDtl.setH01OPECOD("0001");			
			} else {
				userPO.setPurpose("MAINTENANCE");
				msgDtl.setH01OPECOD("0002");
			}
			msgDtl.send();
			msgDtl.destroy(); 
			 
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
		  newmessage = mc.receiveMessage();
	  
		  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		  }
		  else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			try {
				msgDtl = new EFE000702Message();
				flexLog("EFE000702 Message Received");
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgDtl = (EFE000702Message)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("Dtl", msgDtl);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EFE0007_fe_user_currency.jsp");
					callPage(LangPath + "EFE0007_fe_user_currency.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else {  // There are errors
				
				try {
					flexLog("About to call Page: " + LangPath + "EFE0007_fe_user_currency_list.jsp");
					callPage(LangPath + "EFE0007_fe_user_currency_list.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);	
				} 
			}
			
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
	}

	 
	//
	// Send User - Currency Detail for Update (SCREEN = 4 o 5) 
	//	
protected void procActionUpdate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
	HttpSession ses, int screen)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE000702Message msgDtl = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgDtl = (EFE000702Message) mc.getMessageRecord("EFE000702");
		msgDtl.setH01USERID(user.getH01USR());
		msgDtl.setH01PROGRM("EFE0007");
		msgDtl.setH01TIMSYS(getTimeStamp());
		if (screen == 4) {
			msgDtl.setH01OPECOD("0004");	
		} else {
			msgDtl.setH01OPECOD("0005");
		}
		userPO.setPurpose("MAINTENANCE");		
		msgDtl.setE01FEUUSR(req.getParameter("E01FEUUSR"));			 
		msgDtl.setE01FEUCCY(req.getParameter("E01FEUCCY"));
		msgDtl.setE01FEUPOR(req.getParameter("E01FEUPOR"));
		
		msgDtl.send();
		msgDtl.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}


	// Receive Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There is no error	
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0007?SCREEN=1");
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EFE0007_fe_user_currency.jsp");
					callPage(LangPath + "EFE0007_fe_user_currency.jsp", req, res);
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
	 
	//	
	//   Show Errors
	//
	protected void showERROR(ELEERRMessage m)
	{
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