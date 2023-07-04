package datapro.eibs.products;   

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSEPR1060 extends datapro.eibs.master.SuperServlet {

	protected static final int A_MAINTENANCE = 4;
	protected static final int A_SEARCH = 5;
	protected static final int A_SEARCH_T = 10;
	protected static final int R_INQUIRY_MAINT = 3; 
	protected static final int R_SEARCH = 1;
	protected static final int R_LIST = 2;
	protected static final int R_LOGS = 7;
	protected static final int R_LIST_INCOMING = 9;
	protected static final int R_LIST_PAYMENT = 13;
	protected static final int R_LIST_OPENING_CD = 15;
	protected static final int R_LIST_OPENING_RT = 17;
	protected static final int R_LIST_OPENING_SV = 19;
	protected static final int R_LIST_OPENING_LN = 21;	
	protected static final int R_INCOMING = 11;	
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEPR1060() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0130");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
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

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					mc = new MessageContext(super.getMessageHandler("EPR1060", req));
				
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// BEGIN PR
					// Request
					case R_SEARCH :
						procReqSearch(mc, msgUser, req, res, session);
						break;
					case R_INQUIRY_MAINT :
						procReqInqMaint(mc, msgUser, req, res, session);
						break;
					case R_INCOMING :
						procReqIncomingMaint(mc, msgUser, req, res, session);
						break;	
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_LIST_INCOMING :
						procReqListIncoming(mc, msgUser, req, res, session);
						break;	
					case R_LIST_PAYMENT :
						procReqListPayment(mc, msgUser, req, res, session);
						break;	
					case R_LIST_OPENING_CD :
						procReqListOpeningCD(mc, msgUser, req, res, session);
						break;	
					case R_LIST_OPENING_RT :
						procReqListOpeningRT(mc, msgUser, req, res, session);
						break;	
					case R_LIST_OPENING_LN :
						procReqListOpeningLN(mc, msgUser, req, res, session);
						break;	
					case R_LIST_OPENING_SV :
						procReqListOpeningSV(mc, msgUser, req, res, session);
						break;	
						
						// Action
					case A_SEARCH :
						procActionSearch(mc, msgUser, req, res, session);
						break;
					case A_SEARCH_T :
						procActionSearchT(mc, msgUser, req, res, session);
						break;
						
					case R_LOGS :
						procReqLogsInquiry(mc, msgUser, req, res, session);
						break;						
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
			} finally {
				if(mc != null) mc.close();
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

protected void procReqLogsInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	MessageRecord newmessage = null;
	EPR020001Message msgList = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;


	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	int colNum = 17;
	try {
		beanList = new datapro.eibs.beans.JBListRec();
		beanList.init(colNum);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
  	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EPR020001Message)mc.getMessageRecord("EPR020001");
		msgList.setH01USERID(user.getH01USR());
		msgList.setH01FLGWK1("2");
        
		try {
			  msgList.setE01REFNUM(req.getParameter("REFNUM"));
		}
		catch (Exception e)	{
		  msgList.setE01REFNUM("0");
		}
        
		msgList.send();	
		msgList.destroy();
		flexLog("EPR020001 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Data
	try
	{
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);
			beanList.setNoResult(true);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("docList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EFE0000_enter_inquiry.jsp");
				callPage(LangPath + "EFE0000_enter_inquiry.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EPR020001")) {

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			String VDate = "";
			String VCCY  = "";
			
			while (true) {
				
				

				msgList = (EPR020001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					break;
				}
				else {


						myFlag = msgList.getE01PRLFMT();
						
						myRow[0] = msgList.getE01ACCNUM();
						myRow[1] = msgList.getE01REFNUM();						
						myRow[2] = msgList.getE01PRLFMT();
						myRow[3] = msgList.getE01PRLCCY();
						myRow[4] = Util.fcolorCCY(msgList.getE01AMOUNT());
						myRow[5] = msgList.getE01PRLSRR();
						myRow[6] = msgList.getE01PRLUSR();								
						myRow[7] = msgList.getE01PRLRID();
						myRow[8] = msgList.getE01DSCTST();
						myRow[9] = msgList.getE01DSCMOD();
						myRow[10] = msgList.getE01DSCPRT();
						myRow[11] = Util.formatDate(msgList.getE01PRLSY1(),msgList.getE01PRLSY2(),msgList.getE01PRLSY3());
						myRow[12] = msgList.getE01PRLSYT();


																							
						  beanList.addRow(myFlag, myRow);
						}
						newmessage = mc.receiveMessage();
					}

			flexLog("Putting java beans into the session");
			ses.setAttribute("logs", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EPR0200_logs_pr.jsp");
				callPage(LangPath + "EPR0200_logs_pr.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
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
	

protected void procActionSearchT(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

		EPR106001Message msgSch = null;

	try {
		msgSch = (datapro.eibs.beans.EPR106001Message) ses.getAttribute("schBean");
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		msgSch = new EPR106001Message();
		msgSch.setH01USERID(user.getH01USR());
		msgSch.setH01PROGRM("EPR1060");
		msgSch.setH01TIMSYS(getTimeStamp());
		msgSch.setH01SCRCOD("01");
		msgSch.setH01OPECOD("0004");
		//msgSch.setE01WRTTYP("B");
			
		// all the fields here
		java.util.Enumeration enu = msgSch.fieldEnumeration();
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

		flexLog("Putting java bean EPR106001Message into the session");
		ses.setAttribute("schBean", msgSch);

		procReqList(mc, user, req, res, ses);

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EPR106001Message msgSch = null;

		try {
			msgSch = (datapro.eibs.beans.EPR106001Message) ses.getAttribute("schBean");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgSch = new EPR106001Message();
			msgSch.setH01USERID(user.getH01USR());
			msgSch.setH01PROGRM("EPR1060");
			msgSch.setH01TIMSYS(getTimeStamp());
			msgSch.setH01SCRCOD("01");
			msgSch.setH01OPECOD("9999");

			// all the fields here
			java.util.Enumeration enu = msgSch.fieldEnumeration();
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

			flexLog("Putting java bean EPR106001Message into the session");
			ses.setAttribute("schBean", msgSch);

			procReqList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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
		EPR106001Message msgList = null;
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
			msgList = (datapro.eibs.beans.EPR106001Message) ses.getAttribute("schBean");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));				
			} catch (Exception ex) {
				
			}
// THIS IS THE NEW CODE			
			try {
				msgList.setH01OPECOD("9999");				
			} catch (Exception ex) {
				
			}
			msgList.setH01FLGWK2("I");
			
			//msgList.send();
			mc.sendMessage(msgList);
			msgList.destroy();
			flexLog("EPR106001 Message Sent");
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
				if (newmessage.getFormatName().equals("EPR106001")) {

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
					String priNum = req.getParameter("PRINUM");
					String TransTyp = "";
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
					//if (priNum == null)
					//	firstTime = true;
					//else
					//	firstTime = false;

					int indexRow = 0;
					while (true) {

						msgList = (EPR106001Message) newmessage;

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
							
							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01SCHNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}
							
							////if(msgList.getE01WRTTYP().equals("I")){
							////	TransTyp = "Incoming";
							////}
							////else if(msgList.getE01WRTTYP().equals("O")){
							////TransTyp = "Outgoing";
							////} else TransTyp = "Internal";

							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ msgList.getE01WRTSRF()
								+ "</A></TD>");							
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ msgList.getE01WRTCCY()
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTSTS())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatDate(
									msgList.getE01WRTPD1(),
									msgList.getE01WRTPD2(),
									msgList.getE01WRTPD3())
								+ "</A>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTTCD())
								+ "</A>");
								
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "\"></TD>");
							//myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");							
							//myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_inq_list.jsp");
							callPage(LangPath + "EPR1060_pr_inq_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						if (!(req.getParameter("E01SCHNUM") == null)){
						  userPO.setIdentifier(req.getParameter("E01SCHNUM"));
						  ses.setAttribute("userPO", userPO);						  
						}

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_inq_maint.jsp");
							callPage(LangPath + "EPR0000_pr_inq_maint.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListIncoming(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0000");
			msgList.setH01FLGWK2("R");
			
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
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
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

						msgList = (EPR106001Message) newmessage;

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

							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01WRTNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}

							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_incoming_list.jsp");
							callPage(LangPath + "EPR1060_pr_incoming_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListOpeningCD(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0002");
			msgList.setH01FLGWK2("O");
			
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
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
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

						msgList = (EPR106001Message) newmessage;

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

							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01WRTNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}

							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "','"
								+ msgList.getE01WRTAMT()								
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_opening_cd_list.jsp");
							callPage(LangPath + "EPR1060_pr_opening_cd_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListPayment(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0001");
			msgList.setH01FLGWK2("P");
			
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
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
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

						msgList = (EPR106001Message) newmessage;

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

							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01WRTNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}
							

							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "','"
								+ msgList.getE01WRTAMT()								
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_payment_list.jsp");
							callPage(LangPath + "EPR1060_pr_payment_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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



	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListOpeningRT(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0002");
			msgList.setH01FLGWK2("O");
			
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
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
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

						msgList = (EPR106001Message) newmessage;

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

							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01WRTNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}


							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "','"
								+ msgList.getE01WRTAMT()								
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_opening_rt_list.jsp");
							callPage(LangPath + "EPR1060_pr_opening_rt_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListOpeningSV(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0002");
			msgList.setH01FLGWK2("O");
			
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
					String warnImg= "";
					String txtAlt="";
					String warnFlag= "";
					String bklFlag="";
					String ofacImg = "";
					String ofacFlag = "";
					
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

						msgList = (EPR106001Message) newmessage;

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

							if (msgList.getH01FLGWK3().trim().equals("3")) {
								ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01WRTNUM() + "')\">";
								ofacFlag = msgList.getH01FLGWK3().trim();
							}else{
								ofacImg= "";
								ofacFlag = "";
							}

							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "','"
								+ msgList.getE01WRTAMT()								
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"LEFT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A>" + warnImg + ofacImg + "</TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_opening_sv_list.jsp");
							callPage(LangPath + "EPR1060_pr_opening_sv_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */

	protected void procReqListOpeningLN(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR106001Message msgList = null;
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
			msgList = new datapro.eibs.beans.EPR106001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			try {
				msgList.setE01NUMREC(req.getParameter("Pos"));
			} catch (Exception ex) {
				
			}
			
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0002");
			msgList.setH01FLGWK2("O");
			
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

						msgList = (EPR106001Message) newmessage;

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
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRINUM\" value=\""
								+ msgList.getE01WRTNUM()
								+ "\" "
								+ chk
								+ " onclick=\"showAddInfo('"
								+ msgList.getE01WRTNUM()
								+ "','"
								+ msgList.getE01WRTAMT()								
								+ "',"
								+ indexRow
								+ ")\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.formatCell(msgList.getE01WRTNUM())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:goAction('"
								+ msgList.getE01WRTNUM()
								+ "')\">"
								+ Util.fcolorCCY(msgList.getE01WRTAMT())
								+ "</A></TD>");
							
							////myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:goAction('"
							////	+ msgList.getE01WRTNUM()
							////	+ "')\">"
							////	+ Util.formatCell(msgList.getE01REMARK())
							////	+ "</A></TD>");
							myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"
								+ indexRow
								+ "\" VALUE=\""
								+ Util.formatCell(msgList.getE01WRTDAC())
								+ "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTCAC()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBYO()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBNF()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTBBK()) + "<br>");
							myRow.append(Util.formatCell(msgList.getE01WRTORI()) + "\"></TD>");
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

					userPO = new datapro.eibs.beans.UserPos();
					userPO.setPurpose("INQUIRY");
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("appList", beanList);
					ses.setAttribute("error", msgError);

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
							flexLog("About to call Page: " + LangPath + "EPR1060_pr_opening_ln_list.jsp");
							callPage(LangPath + "EPR1060_pr_opening_ln_list.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					if (newmessage.getFormatName().equals("EPR010001")) {

						try {
							msgPR = new datapro.eibs.beans.EPR010001Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgPR = (EPR010001Message) newmessage;
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("prMant", msgPR);

						try {
							flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
							callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
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
	
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR010001Message msgPR = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (!(req.getParameter("REFNUM") == null)){
		  userPO.setIdentifier(req.getParameter("REFNUM"));
		}
	    
		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try {
			msgPR = (EPR010001Message) mc.getMessageRecord("EPR010001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EPR0000");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD(opCode);
			msgPR.setE01PRINUM(userPO.getIdentifier());
			msgPR.send();
			msgPR.destroy();
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
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR010001")) {
				try {
					msgPR = new datapro.eibs.beans.EPR010001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EPR010001Message) newmessage;
				
				userPO.setIdentifier(msgPR.getE01PRINUM());
				userPO.setPurpose("MAINTENANCE");
				userPO.setOption("PR");
			    
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prMant", msgPR);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EPR0000_pr_inq_maint.jsp");
						callPage(LangPath + "EPR0000_pr_inq_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors

					try {
						flexLog("About to call Page4: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqIncomingMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPR010001Message msgPR = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (!(req.getParameter("REFNUM") == null)){
		  userPO.setIdentifier(req.getParameter("REFNUM"));
		}
	    
		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try {
			msgPR = (EPR010001Message) mc.getMessageRecord("EPR010001");
			msgPR.setH01USERID(user.getH01USR());
			msgPR.setH01PROGRM("EPR0000");
			msgPR.setH01TIMSYS(getTimeStamp());
			msgPR.setH01SCRCOD("01");
			msgPR.setH01OPECOD(opCode);
			msgPR.setE01PRINUM(userPO.getIdentifier());
			msgPR.send();
			msgPR.destroy();
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
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPR010001")) {
				try {
					msgPR = new datapro.eibs.beans.EPR010001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPR = (EPR010001Message) newmessage;
				
				userPO.setIdentifier(msgPR.getE01PRINUM());
				userPO.setPurpose("MAINTENANCE");
				userPO.setOption("PR");
			    
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prMant", msgPR);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EPR0000_pr_incoming_maint.jsp");
						callPage(LangPath + "EPR0000_pr_incoming_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors

					try {
						flexLog("About to call Page4: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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



	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = new UserPos();
		userPO.setPurpose("INQUIRY");

		msgError = new ELEERRMessage();

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page3: " + LangPath + "EPR1060_pr_inq_search.jsp");
			callPage(LangPath + "EPR1060_pr_inq_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
}