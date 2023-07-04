package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSEDL1110 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL_LIST		= 1;

	protected static final int A_ACTION				= 2;
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSEDL1110() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSEDL1110(int logType) {
	super(logType);
}

protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL111001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		JBList beanList_ld = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
		try {
			msgList = (EDL111001Message) mc.getMessageRecord("EDL111001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL111001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			flexLog("Send Initial data");
            
			msgList.send();
			msgList.destroy();
			flexLog("EDL111001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

        //Receive Data
		try {
			  newmessage = mc.receiveMessage();
			  
				if (newmessage.getFormatName().equals("EDL111001")) {

					beanList = new JBList();
					beanList_ld = new JBList();
					
					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					String typeRt= "";
					String typeFL= "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (EDL111001Message) newmessage;
						marker = msgList.getH01FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							chk = "checked";
							userPO.setHeader10(msgList.getE01PRRTBL());
							}
					    else {
						  	chk = "";
						}						
					
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							myRow = new StringBuffer("<TR>");
							
							typeRt= msgList.getE01PRRTYP();
							if ( typeRt.equals("1") )
							      typeFL="Efectiva";
							else   
							      typeFL="Nominal";
							      
      						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"TBL\" value=\""
								+ msgList.getE01PRRTBL()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE01PRRTBL()   
							+ "')\"></TD>");
							//table
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01PRRTBL() + "</td>");
							//description
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE01PRRNME() + "</td>");
							//only for leader
							if (!msgList.getE01PRRTSL().equals("")) {
								//initial value
								myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE01PRRTFR() + "</td>");
								//final value
								myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE01PRRTTO() + "</td>");
							}
							//type
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + typeFL + "</td>");
							typeRt= "";
							typeFL= "";							
							// primary rate  
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getE01PRRPRT() + "</td>");	
		                    // secondary rate  
						    myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getE01PRRSRT() + "</td>");						
							// date
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + Util.formatDate(msgList.getE01PRREFM(), msgList.getE01PRREFD(), msgList.getE01PRREFY())  + "</td>");	 							
							myRow.append("</TR>");
												
							//fl/ld type
							if (!msgList.getE01PRRTSL().equals("")) {
								beanList_ld.addRow(myFlag, myRow.toString());
							} else {
								beanList.addRow(myFlag, myRow.toString());
							}

							indexRow++;
							
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								beanList_ld.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("ftList", beanList);
					ses.setAttribute("ldList", beanList_ld);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDL1110_rt_approval_list.jsp");
						callPage(LangPath + "EDL1110_rt_approval_list.jsp", req, res);
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
/**
 * This method was created in VisualAge.
 */
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL111002Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EDL111002Message)mc.getMessageRecord("EDL111002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("EDL1110");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setE02PRRTBL(req.getParameter("TBLNUM"));
	 	msgList.setE02ACTION(req.getParameter("action"));
	 	msgList.send();	
	 	msgList.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Message
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
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There is no error
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSEDL1110?SCREEN=1");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "EDL1110_rt_approval_list.jsp");
					 callPage(LangPath + "EDL1110_rt_approval_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}		
	  	}
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}

/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;

	session = (HttpSession)req.getSession(false); 
	
	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	}
	else {

		int screen = R_APPROVAL_LIST;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, super.iniSocket + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				case R_APPROVAL_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case A_ACTION :
					procActionApproval(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}


}