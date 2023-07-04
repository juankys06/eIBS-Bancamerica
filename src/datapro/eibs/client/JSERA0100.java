package datapro.eibs.client;

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

public class JSERA0100 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_COLL_BASIC 		= 1;
	protected static final int A_COLL_BASIC 		= 2;
	protected static final int R_COLL_DETAIL 		= 3;
	protected static final int A_COLL_DETAIL 		= 4;
	protected static final int R_COLL_INSURANCE 	= 5;
	protected static final int A_COLL_INSURANCE 	= 6;
	protected static final int R_COLL_NONE_ACC 		= 7;
	protected static final int A_COLL_NONE_ACC 		= 8;
	protected static final int R_ENTER_DETAIL_INS	= 9;
	protected static final int R_COLL_POLIZA_DETAIL = 10;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";
/**
 * JSECLI001 constructor comment.
 */
public JSERA0100() {
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
protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	


	try
	{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		userPO.setPurpose("INQUIRY");
	 	try {
	 		userPO.setCusNum(req.getParameter("CUSNUM").trim());
	 	}
	 	catch (Exception e) {
		 	userPO.setCusNum("0");
	 	}
	 	try {
	 		userPO.setIdentifier(req.getParameter("REF").trim());
	 	}
	 	catch (Exception e) {
		 	userPO.setIdentifier("0");
	 	}


		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);

		if (userPO.getIdentifier().equals("")) {
		 	procReqListNoneAccColl(mc, user, req, res, ses);
	 	}
	 	else {
		 	procReqCollBasic(mc, user, req, res, ses);
	 	}

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
/**
 * This method was created in VisualAge.
 */
protected void procReqCollBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010001Message msgColl = null;
	ERA010003Message msgList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	JBObjList beanList = null;
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
		msgColl = (ERA010001Message)mc.getMessageRecord("ERA010001");
	 	msgColl.setH01USERID(user.getH01USR());
	 	msgColl.setH01PROGRM("EDD0000");
	 	msgColl.setH01TIMSYS(getTimeStamp());
	 	msgColl.setH01SCRCOD("01");
	 	msgColl.setH01OPECOD(opCode);
	 	try {
	 		msgColl.setE01ROCCUN(userPO.getCusNum());
	 	}
	 	catch (Exception e) {
	 	}
	 	try {
	 		msgColl.setE01ROCREF(userPO.getIdentifier());
	 	}
	 	catch (Exception e) {
	 	}
	 	msgColl.send();	
	 	msgColl.destroy();
	 	flexLog("ERA010001 Message Sent");
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	
	/*	
	try
	{
		msgList = (ERA010003Message)mc.getMessageRecord("ERA010003");
		msgList.setH03USERID(user.getH01USR());
		msgList.setH03PROGRM("ERA0100");
		msgList.setH03TIMSYS(getTimeStamp());
		msgList.setH03SCRCOD("01");
		msgList.setH03OPECOD("0015");
		try {
			msgList.setE03ROCCUN(userPO.getCusNum());
		}
		catch (Exception e) {
		}
		try {
			msgList.setE03ROCREF(userPO.getIdentifier());
		}
		catch (Exception e) {
		}
		msgList.send();	
		msgList.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	*/

	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
	  } else if (newmessage.getFormatName().equals("ERA010001")) {
			try {
				msgColl = new datapro.eibs.beans.ERA010001Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgColl = (ERA010001Message)newmessage;
			
			userPO.setCusName(msgColl.getE01CUSNA1());
			userPO.setCusNum(msgColl.getE01ROCCUN());
			userPO.setBank(msgColl.getE01ROCBNK());
			userPO.setBranch(msgColl.getE01ROCBRN());
			userPO.setCurrency(msgColl.getE01ROCCCY());
			userPO.setGenLedger(msgColl.getE01ROCGLN());
			userPO.setType(msgColl.getE01ROCTYP());
			//Set collateral description on header1
			userPO.setHeader1(msgColl.getE01ROCDES());

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("collBasic", msgColl);
			ses.setAttribute("error", msgError);

		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

	/*
	if (IsNotError) {
		// Receive List
		try
		{
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ERA010003")) {
				try {
					beanList = new datapro.eibs.beans.JBObjList();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}
	
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String ref = "";
				String client = "";
				String chk = "";
				String seq = "0";
	
				if (seq.equals("0")) 
					firstTime = true;
				else				
					firstTime = false;
		
				while (true) {
	
					msgList = (ERA010003Message)newmessage;
	
					marker = msgList.getH03FLGMAS();
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else 
					{
						if ( firstTime ) {
							firstTime = false;
							chk = "checked";
						}
						else {
							if (msgList.getE03ROCSEQ().trim().equals(seq)){
								chk = "checked";
							}    
							else 
								chk = "";
						}
	
						myFlag = "";
				
						seq = msgList.getE03ROCSEQ();
						beanList.addRow(msgList);
					}
					newmessage = mc.receiveMessage();
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("insList", beanList);
				ses.setAttribute("error", msgError);
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
  */
	if (IsNotError) {
		try {
			flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_basic.jsp");
			callPage(LangPath + "ERA0100_collateral_none_acc_basic.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	} else {
		try {
			flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_enter.jsp");
			callPage(LangPath + "ERA0100_collateral_none_acc_enter.jsp", req, res);
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
			
}

/**
 * This method was created in VisualAge.
 */
protected void procReqCollListDet(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	MessageRecord newmessage = null;
	ERA001102Message msgColl = null;
	JBObjList trList = null;
	JBList beanList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		msgColl = (ERA001102Message) mc.getMessageRecord("ERA001102");
		msgColl.setH02USERID(user.getH01USR());
		msgColl.setH02PROGRM("ERA0011");
		msgColl.setH02TIMSYS(getTimeStamp());
		msgColl.setH02SCRCOD("01");
		msgColl.setH02OPECOD("0006");
		// Get Parameters here	

		try {
			msgColl.setE02RODREF(userPO.getIdentifier());
		} catch (Exception e) {
		}
		try {
			msgColl.setE02RODBNK(userPO.getBank());
		} catch (Exception e) {
		}
		try {
			msgColl.setE02RODCUN(userPO.getCusNum());
		} catch (Exception e) {
		}

		msgColl.send();
		msgColl.destroy();

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	flexLog("Initializing java beans into the session");
	try {
		msgError =
			(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
				getClass().getClassLoader(),
				"datapro.eibs.beans.ELEERRMessage");
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		trList = new JBObjList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ERA001102")) {

			char sel = ' ';
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			beanList = new datapro.eibs.beans.JBList();
			int counter = 0;
			
			while (true) {

				msgColl = (ERA001102Message) newmessage;
				marker = msgColl.getH02FLGMAS();

				if (marker.equals("*")) {
					break;
				} else {
					trList.addRow(msgColl);
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqColl('" + counter + "')\">" + Util.formatCell(msgColl.getE02RODSEQ()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqColl('" + counter + "')\">" + Util.formatCell(msgColl.getE02RODCUN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgColl.getE02RODOFN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgColl.getE02RODUC1()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.fcolorCCY(msgColl.getE02RODAMT()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + (msgColl.getE02RODUC2().equals("S") ? "SI" : "NO") + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgColl.getE02RODEST()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					counter++;
				}
				newmessage = mc.receiveMessage();
			}

			flexLog("Putting java beans into the session");
			msgColl = null;
			ses.setAttribute("error", msgError);
			ses.setAttribute("collList", trList);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_detail.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_detail.jsp", req, res);

			}
			catch (Exception e) {
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


/**
 * This method was created in VisualAge.
 */
protected void procReqCollDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010002Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA010002Message)mc.getMessageRecord("ERA010002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ERA0100");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setH02SCRCOD("01");
	 	msgList.setH02OPECOD("0004");
	 	msgList.setE02RODCUN(userPO.getCusNum());
		msgList.setE02RODREF(userPO.getIdentifier());
		try{
		msgList.setE02RODBNK(req.getParameter("BNKNUM"));
		}
		catch (Exception ex) {
		msgList.setE02RODBNK("01"); 
		}
		
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ERA010002 Message Sent");
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
			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");

		}
		else if (newmessage.getFormatName().equals("ERA010002")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			
			while (true) {

				msgList = (ERA010002Message)newmessage;

				marker = msgList.getH02FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02RODSEQ()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02RODCUN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE02RODDSC()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02RODNBR()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE02RODOD1(), msgList.getE02RODOD2(), msgList.getE02RODOD3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatDate(msgList.getE02RODMD1(), msgList.getE02RODMD2(), msgList.getE02RODMD3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.fcolorCCY(msgList.getE02RODAMT()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
				}
				newmessage = mc.receiveMessage();
			}
			
			//userPO.setPurpose("INQUIRY");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("clList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_detail.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_detail.jsp", req, res);

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

protected void procReqPolizalListDet(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	MessageRecord newmessage = null;
	ERA001103Message msgColl = null;
	JBObjList trList = null;
	JBList beanList = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		msgColl = (ERA001103Message) mc.getMessageRecord("ERA001103");
		msgColl.setH03USERID(user.getH01USR());
		msgColl.setH03PROGRM("ERA0011");
		msgColl.setH03TIMSYS(getTimeStamp());
		msgColl.setH03SCRCOD("01");
		msgColl.setH03OPECOD("0006");
		// Get Parameters here	

		try {
			msgColl.setE03ROCREF(userPO.getIdentifier());
		} catch (Exception e) {
		}
		try {
			msgColl.setE03ROCBNK(userPO.getBank());
		} catch (Exception e) {
		}
		try {
			msgColl.setE03ROCCUN(userPO.getCusNum());
		} catch (Exception e) {
		}

		msgColl.send();
		msgColl.destroy();

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	flexLog("Initializing java beans into the session");
	try {
		msgError =
			(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
				getClass().getClassLoader(),
				"datapro.eibs.beans.ELEERRMessage");
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		trList = new JBObjList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	// Receive Data
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ERA001103")) {

			char sel = ' ';
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			beanList = new datapro.eibs.beans.JBList();
			int counter = 0;

			while (true) {

				msgColl = (ERA001103Message) newmessage;
				marker = msgColl.getH03FLGMAS();

				if (marker.equals("*")) {
					break;
				} else {
					trList.addRow(msgColl);
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqPoliza('" + counter + "')\">" + Util.formatCell(msgColl.getE03ROCICN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:showInqPoliza('" + counter + "')\">" + Util.formatCell(msgColl.getE03ROCICM()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgColl.getE03ROCCIN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgColl.getE03ROCICY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.fcolorCCY(msgColl.getE03ROCIPA()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					counter++;
				}
				newmessage = mc.receiveMessage();
			}

			flexLog("Putting java beans into the session");
			msgColl = null;
			ses.setAttribute("error", msgError);
			ses.setAttribute("collList", trList);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("collIns", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_poliza_detail.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_poliza_detail.jsp", req, res);
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

protected void procReqCollPolizaDetail(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010003Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA010003Message)mc.getMessageRecord("ERA010003");
		msgList.setH03USERID(user.getH01USR());
		msgList.setH03PROGRM("ERA0100");
		msgList.setH03TIMSYS(getTimeStamp());
		msgList.setH03SCRCOD("01");
		msgList.setH03OPECOD("0004");
		msgList.setE03ROCCUN(userPO.getCusNum());
		msgList.setE03ROCREF(userPO.getIdentifier());
		try{
		msgList.setE03ROCBNK(userPO.getBank().trim());
		}
		catch (Exception ex) {
		msgList.setE03ROCBNK("01"); 
		}
		
		msgList.send();	
		msgList.destroy();
		flexLog("ERA010003 Message Sent");
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
			res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");

		}
		else if (newmessage.getFormatName().equals("ERA010003")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}

			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			
			while (true) {

				msgList = (ERA010003Message)newmessage;

				marker = msgList.getH03FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE03ROCICN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getE03ROCICM()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE03ROCCIN()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE03ROCICY()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CNTER>" + Util.fcolorCCY(msgList.getE03ROCIPA()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}
				newmessage = mc.receiveMessage();
			}
			
			//userPO.setPurpose("INQUIRY");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("collIns", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_poliza_detail.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_poliza_detail.jsp", req, res);

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



/**
 * This method was created in VisualAge.
 */
protected void procReqCollInsurance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010003Message msgColl = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

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
		msgColl = (ERA010003Message)mc.getMessageRecord("ERA010003");
	 	msgColl.setH03USERID(user.getH01USR());
	 	msgColl.setH03PROGRM("ERA0100");
	 	msgColl.setH03TIMSYS(getTimeStamp());
	 	msgColl.setH03SCRCOD("01");
	 	msgColl.setH03OPECOD(opCode);
	 	msgColl.setE03ROCCUN(userPO.getCusNum());
	 	msgColl.setE03ROCREF(userPO.getIdentifier());
		try{
		msgColl.setE03ROCBNK(req.getParameter("BNKNUM"));
		}
		catch (Exception ex) {
		msgColl.setE03ROCBNK((user.getE01UBK())); 
		}
	 	
	 	msgColl.send();	
	 	msgColl.destroy();
	}		
	catch (Exception e)
	{
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
			//showERROR(msgError);
			 flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_enter.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_enter.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
	  }
	  else if (newmessage.getFormatName().equals("ERA010003")) {
			try {
				msgColl = new datapro.eibs.beans.ERA010003Message();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgColl = (ERA010003Message)newmessage;

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("collIns", msgColl);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_insurance.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_insurance.jsp", req, res);
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

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterDetailIns(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;
	JBObjList beanList = null;
	ERA010001Message msgGA = null;
	ERA010003Message msgINS = null;	
	ERA001001Message msgGa = new ERA001001Message();
	ERA001003Message msgIns = new ERA001003Message();	
	
	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		
		msgGA = (ERA010001Message)mc.getMessageRecord("ERA010001");
		msgGA.setE01CUSNA1(userPO.getCusName());
		msgGA.setE01CUSNA1(userPO.getCusName());
		msgGA.setE01ROCBRN(userPO.getBranch());
		msgGA.setE01ROCCCY(userPO.getCurrency());
		msgGA.setE01ROCGLN(userPO.getGenLedger());
		msgGA.setE01ROCTYP(userPO.getType());
		msgGA.setE01ROCDES(userPO.getHeader1());
		
		// trasfer fields from ERA010001 to ERA001001
		java.util.Enumeration enu = msgGA.fieldEnumeration();
		java.util.Enumeration enu2 = msgGa.fieldEnumeration();
		MessageField field = null;
		MessageField field2 = null;
		String value = null;
		String tag = null;
		String tag2 = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				tag = field.getTag();
				value = field.getString();
				if (value != null) {
					while (enu2.hasMoreElements()) {
						field2 = (MessageField)enu2.nextElement();
						try {
							tag2 = field2.getTag();
							if (tag2.equals(tag)) {
								field2.setString(value);
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}
			catch (Exception e) {
			}	
		}

		beanList = (datapro.eibs.beans.JBObjList)ses.getAttribute("insList");
		int row =0;
		try{
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch(Exception ex){
			row = 0;
		}
		
		beanList.setCurrentRow(row);
		msgINS = new datapro.eibs.beans.ERA010003Message(); 
		msgINS = (ERA010003Message)beanList.getRecord();
		msgINS.setE03ROCBNK(userPO.getBank());
		msgINS.setE03ROCCUN(userPO.getCusNum());
		
		try{
			userPO.setIdentifier(msgINS.getE03ROCREF());
		}
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		// trasfer fields from ERA010003 to ERA001003
		enu = msgINS.fieldEnumeration();
		enu2 = msgIns.fieldEnumeration();
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				tag = field.getTag();
				value = field.getString();
				if (value != null) {
					while (enu2.hasMoreElements()) {
						field2 = (MessageField)enu2.nextElement();
						try {
							tag2 = field2.getTag();
							if (tag2.equals(tag)) {
								field2.setString(value);
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}
			catch (Exception e) {
			}	
		}

		userPO.setPurpose("READONLY");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("gaMant", msgGa);
		ses.setAttribute("insMant", msgIns);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0010_ga_maint_ins_detail.jsp");
		callPage(LangPath + "ERA0010_ga_maint_ins_detail.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("NON_ACC_COLL");
		userPO.setPurpose("");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_enter.jsp");
		callPage(LangPath + "ERA0100_collateral_none_acc_enter.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}
/**
 * This method was created in VisualAge.
 */
protected void procReqListNoneAccColl(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ERA010004Message msgList = null;
	ELEERRMessage msgError = null;
	JBList beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ERA010004Message)mc.getMessageRecord("ERA010004");
		msgList.setH04USERID(user.getH01USR());
	 	msgList.setH04PROGRM("ERA0100");
	 	msgList.setH04TIMSYS(getTimeStamp());
	 	msgList.setH04SCRCOD("01");
	 	msgList.setH04OPECOD("0004");
		try {
	 		msgList.setE04ROCCUN(userPO.getCusNum());
	 	}
	 	catch (Exception e) {
	 	}
	 	msgList.send();	
	 	msgList.destroy();
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
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);

			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_enter.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_enter.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else if (newmessage.getFormatName().equals("ERA010004")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String ref = "";
			java.math.BigDecimal totalGross = new java.math.BigDecimal(0);
			java.math.BigDecimal totalUsed = new java.math.BigDecimal(0);
			java.math.BigDecimal totalAvailable = new java.math.BigDecimal(0);
			
			while (true) {

				msgList = (ERA010004Message)newmessage;

				marker = msgList.getE04ENDFLD();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						userPO.setCusNum(msgList.getE04ROCCUN().trim());
						userPO.setCusName(msgList.getE04CUSNA1().trim());
					}
					
					myFlag = "";
					totalGross = totalGross.add(msgList.getBigDecimalE04ROCNBL());
					totalUsed = totalUsed.add(msgList.getBigDecimalE04ROCCOP());
					totalAvailable = totalAvailable.add(msgList.getBigDecimalE04DISPON());
					
					myRow = new StringBuffer("<TR>");
					ref = msgList.getE04ROCREF();
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.formatCell(ref) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.formatCell(msgList.getE04ROCTYP()) + "</TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.formatDate(msgList.getE04ROCMT1(), msgList.getE04ROCMT2(), msgList.getE04ROCMT3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.formatCell(msgList.getE04ROCCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.fcolorCCY(msgList.getE04ROCNBL()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.fcolorCCY(msgList.getE04ROCCOP()) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:goCollBasic('" + ref + "')\">" + Util.fcolorCCY(msgList.getE04DISPON()) + "</A></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
									
				}

				newmessage = mc.receiveMessage();
			}
			
			userPO.setHeader3(Util.fcolorCCY(totalGross.toString()));
			userPO.setHeader4(Util.fcolorCCY(totalUsed.toString()));
			userPO.setHeader5(Util.fcolorCCY(totalAvailable.toString()));
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("collList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ERA0100_collateral_none_acc_list.jsp");
				callPage(LangPath + "ERA0100_collateral_none_acc_list.jsp", req, res);

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

		int screen = R_ENTER;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				mc = new MessageContext(super.getMessageHandler("ERA0100", req));
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				// Request
				case R_COLL_BASIC :
					procReqCollBasic(mc, msgUser, req, res, session);
					break;
				case R_COLL_DETAIL : 
					//procReqCollDetail(mc, msgUser, req, res, session);
					procReqCollListDet(mc, msgUser, req, res, session);
					break;
				case R_COLL_POLIZA_DETAIL : 
					//procReqCollPolizaDetail(mc, msgUser, req, res, session);
					procReqPolizalListDet(mc, msgUser, req, res, session);
					break;
				case R_COLL_INSURANCE : 
					procReqCollInsurance(mc, msgUser, req, res, session);
					break;
				case R_COLL_NONE_ACC :
					procReqListNoneAccColl(mc, msgUser, req, res, session);
					break;
				case R_ENTER_DETAIL_INS : 
					procReqEnterDetailIns(mc, msgUser, req, res, session);
					break;
				//entering options
				case R_ENTER : 
					procReqEnter(msgUser, req, res, session);
					break;
				case A_ENTER :
				case A_COLL_NONE_ACC :
					procActionEnter(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
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