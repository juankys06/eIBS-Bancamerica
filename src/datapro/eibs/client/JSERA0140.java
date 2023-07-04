package datapro.eibs.client;

 

import java.io.*;
import java.net.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSERA0140 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD 		= 1;		// procReqPassword
	protected static final int A_APPROVAL 		= 2;		// procActionApproval
	protected static final int R_APPROVAL_INQ 	= 3;		// procReqApprovalInq - inquire
	protected static final int R_APPROVAL 		= 5;		// procReqApproval - Send initial list

 
	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSERA0140() {
		super();
	}

	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSERA0140(int logType) {
		super(logType);

	}
	

	 
	  // R_APPROVAL = 5;  approval list
	  
	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ERA014001Message msgList = null;
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
			msgList = (ERA014001Message) mc.getMessageRecord("ERA014001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERA0140");
			msgList.setH01TIMSYS(getTimeStamp());			 
			 
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

			} else if (newmessage.getFormatName().equals("ERA014001")) {

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
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				if (accNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;

				userPO = new datapro.eibs.beans.UserPos();

				while (true) {

					msgList = (ERA014001Message) newmessage;

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
							//if (msgList.getE01RCLAACC().trim().equals(accNum))
							//	chk = "checked";
							//else
							//	chk = "";
						}
						
					  
						myRow = new StringBuffer("<TR>");						 
						myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\""	
							+ indexRow
							+ "\" "
							+ chk
							+ "></TD>");
						
						myRow.append("<input type=\"hidden\" name=\"E02RCLBBNK"+indexRow+"\" value=\""+msgList.getE01RCLBBNK()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCLAACC"+indexRow+"\" value=\""+msgList.getE01RCLAACC()+"\">");	 
						myRow.append("<input type=\"hidden\" name=\"E02RCLBACC"+indexRow+"\" value=\""+msgList.getE01RCLBACC()+"\">");
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLBBNK())
								+ "</A></TD>");	
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLAACC())
								+ "</A></TD>");	
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLATYP())
								+ "</A></TD>");	
						
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLBACC())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLBTYP())
								+ "</A></TD>");
								
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLBCUN())
								+ "</A></TD>");
								
						myRow.append(
							"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01CUSNA1B())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:showInqApprovalDespig('"
								+ msgList.getE01RCLBBNK()
								+ "', '"
								+ msgList.getE01RCLAACC()
								+ "', '"
								+ msgList.getE01RCLBACC()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCLDESC())
								+ "</A></TD>");
								
  
								
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
				 
				//mod emat 01/24/2002
				//draft title
				 
				//
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
								+ "ERA0140_collat_approval_list.jsp");
						callPage(
							LangPath + "ERA0140_collat_approval_list.jsp",
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
	 
	 
	
	// A_APPROVAL 	= 2 Approval, delete, 
		
protected void procActionApproval(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ERA014002Message msgList = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgList = (ERA014002Message) mc.getMessageRecord("ERA014002");
		msgList.setH02USERID(user.getH01USR());
		msgList.setH02PROGRM("ERA0140");
		msgList.setH02TIMSYS(getTimeStamp());		
		String index = req.getParameter("ACCNUM");
		
		//System.out.println(
		//					"ACCNUM ::::" + req.getParameter("ACCNUM"));
		//System.out.println(
		//					"E02RCLBBNK ::::" + req.getParameter("E02RCLBBNK"+index ));
		//System.out.println(
		//					"E02RCLAACC ::::" + req.getParameter("E02RCLAACC"+index ));
		//System.out.println(
		//					"E02RCLBACC ::::" + req.getParameter("E02RCLBACC"+index ));
		
		msgList.setE02RCLAACC(req.getParameter("E02RCLAACC"+index));
		msgList.setE02RCLBACC(req.getParameter("E02RCLBACC"+index));
		msgList.setE02RCLBBNK(req.getParameter("E02RCLBBNK"+index));
		
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
				//flexLog("About to call Page: " + LangPath + "ERA0140_collat_approval_list.jsp");
				//procReqApproval(mc,user,req,res,ses);			 
				//res.sendRedirect(super.srctx + LangPath + "MISC_search_wait.jsp?URL='" + super.srctx + "/servlet/datapro.eibs.client.JSERA0140?SCREEN=5'");
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0140?SCREEN=5");
				/*res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.products.JSERA0140?SCREEN=5&appCode="
						+ req.getParameter("appCode")
						+ "&typCode="
						+ typCode); */
						
				//callPage(LangPath + "ERA0140_collat_approval_list.jsp?refresh=\"refresh\"",
				//							  req,
				//							  res);
						
						
			} else {
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERA0140_collat_approval_list.jsp");
					callPage(
						LangPath + "ERA0140_collat_approval_list.jsp",
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
		ERA014001Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO"); 
		
//		Send Initial data

		try {			
			 
			flexLog("Send Initial Data");
			msgList = (ERA014001Message) mc.getMessageRecord("ERA014001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERA0140");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0005");		
			 
			msgList.setE01RCLBBNK(req.getParameter("BNKNUM"));
			msgList.setE01RCLAACC(req.getParameter("ACCNUMA"));
			msgList.setE01RCLBACC(req.getParameter("ACCNUMB"));
		
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

			if (newmessage.getFormatName().equals("ERA014001")) {
				try {
					msgList = new datapro.eibs.beans.ERA014001Message();
					flexLog("ERA014001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgList = (ERA014001Message)newmessage;


				userPO.setIdentifier(msgList.getE01RCLAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gaBasic", msgList);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ERA0140_collat_inq_pigdes.jsp");
						callPage(LangPath + "ERA0140_collat_inq_pigdes.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					 }

				}
				else {  // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ERA0140_collat_approval_list.jsp");
						callPage(LangPath + "ERA0140_collat_approval_list.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

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
			//mod emat 01/24/2002
			//send draft parameter
			String typCode = "";
			flexLog("typCode: " + req.getParameter("typCode"));
			if (req.getParameter("typCode") != null) {
				typCode = req.getParameter("typCode");
			}

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect("/servlet/datapro.eibs.client.JSERA0140?SCREEN=" + R_APPROVAL );
			 
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