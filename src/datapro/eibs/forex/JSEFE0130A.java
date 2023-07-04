package datapro.eibs.forex;

  
import java.io.*;
import java.net.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

 

public class JSEFE0130A extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD 		= 1;		// procReqPassword
	protected static final int R_APPROVAL 		= 5;		// procReqApproval - Send initial list
	protected static final int A_APPROVAL 		= 3;		// procActionApproval
	protected static final int R_APPROVAL_INQ 	= 2;		// procReqApprovalInq - inquire


 
	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSEFE0130A() {
		super();
	}

	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEFE0130A(int logType) {
		super(logType);

	}
	

	 
	  // R_APPROVAL = 2;  approval list
	  
	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE013001Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EFE013001Message) mc.getMessageRecord("EFE013001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EFE0130");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0001");			 
			 
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
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EFE013001")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String accNum = req.getParameter("ACCNUM");				 
				String chkOfac = "";
				String chkWarn = "";
				if (accNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;

				userPO = new datapro.eibs.beans.UserPos();

				while (true) {

					msgList = (EFE013001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) { 
							firstTime = false;
							chk = "checked";
						} 
						else {
							chk = "";							 
						}
						
					  
						myRow = new StringBuffer("<TR>");
						
						 
						chkWarn = (msgList.getH01FLGWK2().equals("A") ? "<a href=\"javascript:showInqWarn('" + msgList.getE01SWDREF() + "')\"><img src=\"../images/warning01.gif\" alt=\"Warnings\" align=\"absmiddle\" border=\"0\" ></a>" : "");						 
						
						myRow.append(
						"<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\""	
						+ indexRow + "\" " + chk + "></TD>");
						 
												
						myRow.append("<input type=\"hidden\" name=\"E01SWDREF"+indexRow+"\" value=\""+msgList.getE01SWDREF()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E01SWDDID"+indexRow+"\" value=\""+msgList.getE01SWDDID()+"\">");	 
						 
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDREF()) + "</A>"+ chkWarn + "</TD>"); //REFERENCIA
 
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDDSC()) + "</A></TD>");	 //CLIENTE
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDTYP()) + "</A></TD>");	 // PRODUCTO
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDCCY()) + "</A></TD>");	 // MONEDA
								
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDAMN()) + "</A></TD>");	 //MONTO
								
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showFxRang('"
								+ msgList.getE01SWDREF() + "', '" + msgList.getE01SWDDID() + "')\">"
								+ Util.formatCell(msgList.getE01SWDDID()) + "</A></TD>");	 //USUARIO
 
						myRow.append(
							"<INPUT TYPE=HIDDEN NAME=\"STSWARN"
								+ indexRow
								+ "\" VALUE=\""
								+ msgList.getH01FLGWK2()
								+ "\">");

								
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

				userPO.setPurpose("APPROVAL");
				 
				 
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("appList", beanList);
				ses.setAttribute("error", msgError);

				if (beanList.getNoResult()) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "MISC_no_result.jsp");
						res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EFE0130A_rango_approval_list.jsp");
						callPage(
							LangPath + "EFE0130A_rango_approval_list.jsp",
							req,
							res);
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
	 
	 
	
	// A_APPROVAL 	= 3 Approval, delete, 
		
protected void procActionApproval(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE013001Message msgList = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgList = (EFE013001Message) mc.getMessageRecord("EFE013001");
		msgList.setH01USERID(user.getH01USR());
		msgList.setH01PROGRM("EFE0130");
		msgList.setH01TIMSYS(getTimeStamp());
		msgList.setH01OPECOD("0003");		
				
		String index = req.getParameter("ACCNUM");
		
		/*System.out.println("ACCNUM ::::" + req.getParameter("ACCNUM"));
		System.out.println("REF    ::::" + req.getParameter("E01SWDREF"+index));
		System.out.println("UID    ::::" + req.getParameter("E01SWDDID"+index));*/
				 
		msgList.setE01SWDREF(req.getParameter("E01SWDREF"+index));
		msgList.setE01SWDDID(req.getParameter("E01SWDDID"+index));
		msgList.setE02ACTION(req.getParameter("action"));
		msgList.setE02MSGTXT(req.getParameter("reason"));

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
				
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0130A?SCREEN=5");
				 
						
			} else {
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EFE0130A_rango_approval_list.jsp");
					callPage(
						LangPath + "EFE0130A_rango_approval_list.jsp",
						req,
						res);
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
	 
	 
	protected void procReqApprovalInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
 
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFE0120DSMessage msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
				msgError = new ELEERRMessage();
				} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}
		//userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		try{
		 userPO = new UserPos();
		}
		catch (Exception e)	{
		}		 
		
//		Send Initial data

		try {			
			 
			flexLog("Send Initial Data");
			msgList = (EFE0120DSMessage) mc.getMessageRecord("EFE0120DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EFE0120");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("22");
			msgList.setH01OPECOD("0003");			
					
			msgList.setE01FESREF(req.getParameter("E01FESREF"));			 
			msgList.setE01FESDID(req.getParameter("E01FESDID")); 
		
			msgList.send();
			msgList.destroy(); 
			 
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
		try
		{
			newmessage = mc.receiveMessage();

					try {
						msgList = new EFE0120DSMessage();
						flexLog("EDL00120DS Message Received");
					} 
					catch (Exception ex) {
						flexLog("Error: " + ex); 
					}

					msgList = (EFE0120DSMessage)newmessage;
					 
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("fex", msgList);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) {  // There are no errors
						try {
								flexLog("About to call Page: " + LangPath + "EFE0130A_rango_approval_inq.jsp");
								callPage(LangPath + "EFE0130A_rango_approval_inq.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else {  // There are errors
							try {
								flexLog("About to call Page: " + LangPath + "EFE0130A_rango_approval_list.jsp");
								callPage(LangPath + "EFE0130A_rango_approval_list.jsp", req, res);	
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

			int screen = R_APPROVAL;

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
						case R_PASSWORD :
							procReqPassword(req, res, session);
							break;
						case R_APPROVAL :
							procReqApproval(mc, msgUser, req, res, session);
							break;
						case A_APPROVAL :
							procActionApproval(mc, msgUser, req, res, session);
							break;
						case R_APPROVAL_INQ :
							procReqApprovalInq(mc, msgUser, req, res, session);
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

	 
	 // R_PASSWORD 		= 1;
	  
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {
			 
			String typCode = "";
			flexLog("typCode: " + req.getParameter("typCode"));
			if (req.getParameter("typCode") != null) {
				typCode = req.getParameter("typCode");
			}

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect("/servlet/datapro.eibs.forex.JSEFE0130A?SCREEN=" + R_APPROVAL );
			 
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	
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