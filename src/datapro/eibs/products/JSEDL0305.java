package datapro.eibs.products;

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

public class JSEDL0305 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_DED 		= 1;
	protected static final int A_DETAIL	 	= 2;
	protected static final int R_REL 		= 3;


	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDL0305() {
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
protected void procReqDed(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL030501Message msgDed = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	String chk = "";
	String marker = "";
	String myFlag = "";
	StringBuffer myRow = null;	
	String dedtype = "";
	String factor = "";

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data EDL030501
	try
	{
		flexLog("Send Initial Data");
		msgDed = (EDL030501Message)mc.getMessageRecord("EDL030501");
		msgDed.setH01USERID(user.getH01USR());
	 	msgDed.setH01PROGRM("EDL0305");
	 	msgDed.setH01TIMSYS(getTimeStamp());
	 	msgDed.setH01SCRCOD("01");
	 	msgDed.setH01OPECOD("0004");
	 	msgDed.setE01DLIACC(userPO.getIdentifier());

	 	msgDed.send();	
	 	msgDed.destroy();
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

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "EDL0160_ln_balances.jsp");
				callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EDL030501")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			while (true) {

				msgDed = (EDL030501Message)newmessage;

				marker = msgDed.getE01ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					myFlag = "1";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.formatCell(msgDed.getE01DLICDE()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.formatCell(msgDed.getE01DEDNME()) + "</A></TD>");
					if (msgDed.getE01DLITYP().equals("I")) {
						dedtype = "Insurance";
					} else if (msgDed.getE01DLITYP().equals("T")) {
						dedtype = "Tax";
					} else {
						dedtype = "Other";
					}
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.formatCell(dedtype) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.formatCell(msgDed.getE01DLIREF()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.fcolorCCY(msgDed.getE01DLIPAM()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.fcolorCCY(msgDed.getE01DLIDPM()) + "</A></TD>");
					if (msgDed.getE01DLIFAC().equals("F")) {
						factor = "Fixed Amount";
					} else if (msgDed.getE01DLIFAC().equals("1")) {
						factor = "% of Original Amount";
					} else if (msgDed.getE01DLIFAC().equals("2")) {
						factor = "% of Principal Balance";
					} else if (msgDed.getE01DLIFAC().equals("3")) {
						factor = "% of Payment Amount";
					}
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showDeductionDetail('" + msgDed.getE01DLICDE() + "','" + msgDed.getE01DLITYP() +"','" + msgDed.getE01DLIACC() +"')\">" 
					+ Util.formatCell(factor) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}

	    		newmessage = mc.receiveMessage();

			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifPos", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EDL0305_ln_ded.jsp");
				callPage(LangPath + "EDL0305_ln_ded.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
				
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
}
		

/**
 * This method was created in VisualAge.
 */
protected void procReqRel(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL030502Message msgRel = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	String chk = "";
	String marker = "";
	String myFlag = "";
	StringBuffer myRow = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data EDL030502
	try
	{
		flexLog("Send Initial Data");
		msgRel = (EDL030502Message)mc.getMessageRecord("EDL030502");
		msgRel.setH02USERID(user.getH01USR());
	 	msgRel.setH02PROGRM("EDL0305");
	 	msgRel.setH02TIMSYS(getTimeStamp());
	 	msgRel.setH02SCRCOD("01");
	 	msgRel.setH02OPECOD("0004");
	 	msgRel.setE02PRIACC(userPO.getIdentifier());

	 	msgRel.send();	
	 	msgRel.destroy();
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (userPO.getPurpose().equals("APPROVAL_INQ")) {
				try {
					flexLog("About to call Page: " + LangPath + "EDL0140_ln_ap_basic.jsp");
					callPage(LangPath + "EDL0140_ln_ap_basic.jsp", req, res);

				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}				
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_ln_balances.jsp");
					callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);

				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
	  	}
		else if (newmessage.getFormatName().equals("EDL030502")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			String typ = "";
			boolean firstTime = true;
			while (true) {
				
				msgRel = (EDL030502Message)newmessage;
				
				marker = msgRel.getE02ENDFLD();

				if (marker.equals("*")) {
					userPO.setHeader7(msgRel.getE02RELRTE());
					userPO.setHeader8(msgRel.getE02RELPRI());
					userPO.setHeader9(msgRel.getE02RELINT());
					beanList.setShowNext(false);
					break;
				}
				else {
					
					if (firstTime == true) {
						firstTime = false;
						//userPO.setHeader4(msgRel.getE02TAMOAM());
						//userPO.setHeader5(msgRel.getE02TAMACA());
						//userPO.setHeader6(msgRel.getE02TAMSAL());
					}
					if (msgRel.getE02RELTYP().equals("P")) {
						typ = "Participation";
					} else if (msgRel.getE02RELTYP().equals("")) {
						typ = "Participated";
					} else {
						typ = "";
					}
					myFlag = "2";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.formatCell(msgRel.getE02RELACC()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.formatCell(msgRel.getE02RELNME()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.formatDate(msgRel.getE02RELMA1(), msgRel.getE02RELMA2(), msgRel.getE02RELMA3()) + "</A></TD>");				
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.fcolorCCY(msgRel.getE02RELRTE()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.fcolorCCY(msgRel.getE02RELPRI()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.fcolorCCY(msgRel.getE02RELINT()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:showInqAcc('" + msgRel.getE02RELACC()  +"')\">" 
					+ Util.formatCell(typ) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();

			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifPos", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EDL0305_ln_rel.jsp");
				callPage(LangPath + "EDL0305_ln_rel.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
}

/**
 * This method was created in VisualAge.
 */
protected void procRequestDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL030503Message msgDetail = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = true;
      
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String opCode = "0004";
	
	// Send Initial data
	try
	{
		msgDetail = (EDL030503Message)mc.getMessageRecord("EDL030503");
	 	msgDetail.setH03USERID(user.getH01USR());
	 	msgDetail.setH03PROGRM("EDL0305");
	 	msgDetail.setH03TIMSYS(getTimeStamp());
	 	msgDetail.setH03SCRCOD("01");
	 	msgDetail.setH03OPECOD(opCode);
	 	msgDetail.setE03DLICDE(req.getParameter("CODE"));
	 	msgDetail.setE03DLITYP(req.getParameter("TYPE"));
	 	msgDetail.setE03DLIACC(req.getParameter("ACCOUNT"));
		msgDetail.send();	
	 	msgDetail.destroy();
	}		
	catch (Exception e)	{
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
		ses.setAttribute("error", msgError);
		try {
			flexLog("About to call Page4: " + LangPath + "error_viewer.jsp");
			callPage(LangPath + "error_viewer.jsp", req, res);	
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	  }
	  else {
	  	flexLog("Message " + newmessage.getFormatName() + " received.");

		if (newmessage.getFormatName().equals("EDL030503")) {
			try {
				msgDetail = new datapro.eibs.beans.EDL030503Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			msgDetail = (EDL030503Message)newmessage; 
			// showESD008004(msgDetail);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("lnDedDet", msgDetail);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page3: " + LangPath + "EDL0305_ln_ded_det.jsp");
					callPage(LangPath + "EDL0305_ln_ded_det.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page4: " + LangPath + "EDL0305_ln_ded.jsp");
					callPage(LangPath + "EDL0305_ln_ded.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");		
		
		
	  }	
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
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

		int screen = R_DED;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
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
				// Requests
				case R_DED :
					procReqDed(mc, msgUser, req, res, session);
					break;
				case R_REL :
					procReqRel(mc, msgUser, req, res, session);
					break;
				case A_DETAIL :
					procRequestDetail(mc, msgUser, req, res, session);
					break;
				// Actions
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

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

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