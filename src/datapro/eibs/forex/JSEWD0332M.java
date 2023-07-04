package datapro.eibs.forex;

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

public class JSEWD0332M extends datapro.eibs.master.SuperServlet {

	// Limits Inquiry
	static final int R_LIMIT = 1;
	static final int A_LIMIT = 2;
 
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEWD0332M() {
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
	
	
	 	
	
	/**
	 * This method was created in VisualAge.
	 */
	private void procReqPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0332DSMessage msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
flexLog("JSEWD0332M-->procReqPos");
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try{
			msgList = (EWD0332DSMessage)mc.getMessageRecord("EWD0332DS");
			msgList.setRWDTYP("S");
			msgList.send();	
flexLog("msgList= " +  msgList );			
		 	msgList.destroy();
			flexLog("EWD0332DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
flexLog("Receive Data");			
flexLog("newmessage= " +  newmessage );
			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);
// cual deberia ser el correcto redirect para FRA ????
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1100");

			} else
				if (newmessage.getFormatName().equals("EWD0332DS")) {

					try {
						beanList =
							(datapro.eibs.beans.JBList) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.JBList");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					 
					StringBuffer myRow = null;
					String chk = "";
					String custNum = req.getParameter("cust");
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					if (custNum == null)
						firstTime = true;
					else
						firstTime = false;
					int indexRow = 0;
					while (true) {

						msgList = (EWD0332DSMessage) newmessage;

						marker = msgList.getSWDOPE();
						
						if (firstTime) {
							firstTime = false;
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
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"ACC\" value=\"" + msgList.getSWDACC() + "\" "
								+ chk + "></TD>");
								
                            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDACC() + "</A></td>");				 
			                myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDCUN() + "</A></td>");
                            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDSBT() + "</A></td>");
                            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDITP() + "</A></td>");
       			            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDCCY() + "</A></td>");       
       	     	            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDPRI() + "</A></td>");  
     	     	            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDOAM() + "</A></td>");
         	                myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDRTE() + "</A></td>");
         	  	            myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDVAL() + "</A></td>");
         	                myRow.append("<TD NOWRAP  ALIGN=CENTER><A HREF=\"javascript:showFRAAccInq('"+ msgList.getSWDACC() + "')\">" + msgList.getSWDSTS() + "</A></td>");							

						    myRow.append("</TR>");
							myRow.append("</TR>");
flexLog("myRow =" +   myRow  );
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
flexLog("WHILE-->newmessage= " +  newmessage );						
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("ewd0332Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EWD0332M_fe_acc.jsp");
						callPage(LangPath + "EWD0332M_fe_acc.jsp", req, res);
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
	

	private void procActionPos(
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
flexLog("JSEWD0332M-->procActionPos");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		String RefNumb = req.getParameter("ACC");
//		String TYPE = req.getParameter("typ");
//		String Bank = user.getE01UBK();
//flexLog("E01FRAACC= *" + RefNumb + "*  TYPE= *" + TYPE + "* Bank= *"  + Bank);
flexLog("E01FRAACC= *" + RefNumb);	
		switch (inptOPT) {
			case 2 : //Forex
flexLog("JSEWD0332M-->procActionPos-->JSETR0150?SCREEN=70");			
flexLog("super.srctx = *" + super.srctx + "*");
				res.sendRedirect(super.srctx +   "/servlet/datapro.eibs.forex.JSETR0150?SCREEN=70&E01FRAACC=" + RefNumb);
	 			    
				break;
			default :
flexLog("JSEWD0332M-->procActionPos-->JSEFE0140B?SCREEN=40");				
				res.sendRedirect(super.srctx + 	"/servlet/datapro.eibs.forex.JSEFE0140B?SCREEN=40&E01WKFACC=" + RefNumb);
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

			int screen = R_LIMIT;

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
flexLog("screen" + "*" + screen +"*");
				switch (screen) {
					// Requests
					case R_LIMIT :
						procReqPos(mc, msgUser, req, res, session);
						break;
						
					
					
						// Actions
					case A_LIMIT :
						procActionPos(mc, msgUser, req, res, session);
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
	private void showERROR(ELEERRMessage m) {
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