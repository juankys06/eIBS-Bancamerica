package datapro.eibs.transfer;

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

public class JSEDD0610 extends datapro.eibs.master.SuperServlet {

	// Transfer options
	protected static final int R_SEARCH 				= 1;
	protected static final int A_SEARCH 				= 2;
	protected static final int R_LIST 				= 3;
	
	protected static final int R_DET	 				= 5;
	protected static final int R_SWIFT 				= 7;
	protected static final int R_FED				    = 9;
	protected static final int R_TELEX			    = 11;

	

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDD0610() {
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
protected void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	UserPos	userPO = null;	

	try{
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		String typeTransf = "";
		try {
			typeTransf = req.getParameter("E01SCHTYA").toUpperCase();
		}
		catch (Exception e) {
		}
		String NoTransf = "";
		try {
			NoTransf = req.getParameter("E01SCHNUM").toUpperCase();
		}
		catch (Exception e) {
		}
		String FD1 = "";
		try {
			FD1 = req.getParameter("E01SCHFD1").toUpperCase();
		}
		catch (Exception e) {
		}
		String FD2 = "";
		try {
			FD2 = req.getParameter("E01SCHFD2").toUpperCase();
		}
		catch (Exception e) {
		}
		String FD3 = "";
		try {
			FD3 = req.getParameter("E01SCHFD3").toUpperCase();
		}
		catch (Exception e) {
		}
		String TD1 = "";
		try {
			TD1 = req.getParameter("E01SCHTD1").toUpperCase();
		}
		catch (Exception e) {
		}
		String TD2 = "";
		try {
			TD2 = req.getParameter("E01SCHTD2").toUpperCase();
		}
		catch (Exception e) {
		}
		String TD3 = "";
		try {
			TD3 = req.getParameter("E01SCHTD3").toUpperCase();
		}
		catch (Exception e) {
		}
		String GLDebit = "";
		try {
			GLDebit = req.getParameter("E01SCHDGL").toUpperCase();
		}
		catch (Exception e) {
		}
		String CtaDebit = "";
		try {
			CtaDebit = req.getParameter("E01SCHDAC").toUpperCase();
		}
		catch (Exception e) {
		}
		String GLCredit = "";
		try {
			GLCredit = req.getParameter("E01SCHCGL").toUpperCase();
		}
		catch (Exception e) {
		}
		String CtaCredit = "";
		try {
			CtaCredit = req.getParameter("E01SCHCAC").toUpperCase();
		}
		catch (Exception e) {
		}
		String MontoI = "";
		try {
			MontoI = req.getParameter("E01SCHAMF").toUpperCase();
		}
		catch (Exception e) {
		}
		String MontoF = "";
		try {
			MontoF = req.getParameter("E01SCHAMT").toUpperCase();
		}
		catch (Exception e) {
		}
		String Ref = "";
		try {
			Ref = req.getParameter("E01SCHORF").toUpperCase();
		}
		catch (Exception e) {
		}
		String Ord = "";
		try {
			Ord = req.getParameter("E01SCHBYO").toUpperCase();
		}
		catch (Exception e) {
		}
		String Bene = "";
		try {
			Bene = req.getParameter("E01SCHBNF").toUpperCase();
		}
		catch (Exception e) {
		}
		String Ing = "";
		try {
			Ing = req.getParameter("E01SCHOPR").toUpperCase();
		}
		catch (Exception e) {
		}
		String Lib = "";
		try {
			Lib = req.getParameter("E01SCHREL").toUpperCase();
		}
		catch (Exception e) {
		}

		userPO.setHeader1(typeTransf);
		userPO.setHeader2(NoTransf);
		userPO.setHeader3(FD1);
		userPO.setHeader4(FD2);
		userPO.setHeader5(FD3);
		userPO.setHeader6(TD1);
		userPO.setHeader7(TD2);
		userPO.setHeader8(TD3);
		userPO.setHeader9(GLDebit);
		userPO.setHeader10(CtaDebit);
		userPO.setHeader11(GLCredit);
		userPO.setHeader12(CtaDebit);
		userPO.setHeader13(MontoI);
		userPO.setHeader14(MontoF);
		userPO.setHeader15(Ref);
		userPO.setHeader16(Ord);
		userPO.setHeader17(Bene);
		userPO.setHeader18(Ing);
		userPO.setHeader19(Lib);
				
		
		ses.setAttribute("userPO", userPO);

		if(!NoTransf.equals("")) {
		 	 procRecTransfDet(mc, user, req, res, ses);
 	 	}
	 	else {
		 	 procReqList(mc, user, req, res, ses);
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
protected void procRecNoTrans(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061005Message msgCont = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgCont = (EDD061005Message)mc.getMessageRecord("EDD061005");
	 	msgCont.setH05USERID(user.getH01USR());
	 	msgCont.setH05PROGRM("EDD0610");
	 	msgCont.setH05TIMSYS(getTimeStamp());
	 	msgCont.setH05SCRCOD("05");
	 	msgCont.setH05OPECOD(opCode);
	 	//msgCont.setE05SCHBNK(req.getParameter("BNK"));
		msgCont.setE05SCHNUM(userPO.getHeader2()); 
		//msgCont.setE05SCHTYA(userPO.getHeader1());	
		msgCont.send();	
	 	msgCont.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive  Messages
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_search.jsp");
				callPage(LangPath + "EDD0610_tr_search.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("EDD061005")) {
			try {
				msgCont = new datapro.eibs.beans.EDD061005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCont = (EDD061005Message)newmessage; 
			// showESD008004(msgLC);

			userPO.setBank(msgCont.getE05SCHBNK());
			userPO.setHeader1(msgCont.getE05SCHNUM());
			userPO.setHeader2(msgCont.getE05SCHTYA());
			userPO.setHeader4(msgCont.getE05WRTBYO());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("inqContab", msgCont);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_contab.jsp");
				callPage(LangPath + "EDD0610_tr_inq_contab.jsp", req, res);	
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
protected void procRecTransfDet(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061005Message msgCont = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgCont = (EDD061005Message)mc.getMessageRecord("EDD061005");
	 	msgCont.setH05USERID(user.getH01USR());
	 	msgCont.setH05PROGRM("EDD0610");
	 	msgCont.setH05TIMSYS(getTimeStamp());
	 	msgCont.setH05SCRCOD("05");
	 	msgCont.setH05OPECOD(opCode);
	 	if (!userPO.getHeader2().equals("")){
		 	msgCont.setE05SCHNUM(userPO.getHeader2());
	 	   }
		else {		 	
	 	   msgCont.setE05SCHBNK(req.getParameter("BNK"));
		   msgCont.setE05SCHNUM(req.getParameter("NMR")); 
		   msgCont.setE05SCHTYA(req.getParameter("TPY"));
		     }	
		   msgCont.send();	
	 	   msgCont.destroy();
		   
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive  Messages
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_list.jsp");
				callPage(LangPath + "EDD0610_tr_list.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("EDD061005")) {
			try {
				msgCont = new datapro.eibs.beans.EDD061005Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgCont = (EDD061005Message)newmessage; 
			// showESD008004(msgLC);

			userPO.setBank(msgCont.getE05WRTBNK());
			userPO.setHeader20(msgCont.getE05SCHNUM());
			userPO.setHeader21(msgCont.getE05SCHTYA());
			userPO.setHeader22(msgCont.getE05WRTBYO());
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("inqContab", msgCont);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_contab.jsp");
				callPage(LangPath + "EDD0610_tr_inq_contab.jsp", req, res);	
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
protected void procRecTransfFed(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061002Message msgFed = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgFed = (EDD061002Message)mc.getMessageRecord("EDD061002");
	 	msgFed.setH02USERID(user.getH01USR());
	 	msgFed.setH02PROGRM("EDD0610");
	 	msgFed.setH02TIMSYS(getTimeStamp());
	 	msgFed.setH02SCRCOD("03");
	 	msgFed.setH02OPECOD(opCode);
	 	msgFed.setE02SCHBNK(userPO.getBank());
		msgFed.setE02SCHNUM(userPO.getHeader20()); 
		msgFed.setE02SCHTYA(userPO.getHeader21());	
		msgFed.send();	
	 	msgFed.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive  Messages
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_contab.jsp");
				callPage(LangPath + "EDD0610_tr_inq_contab.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("EDD061002")) {
			try {
				msgFed = new datapro.eibs.beans.EDD061002Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFed = (EDD061002Message)newmessage; 
			// showESD008004(msgLC);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inqFed", msgFed);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_fed.jsp");
				callPage(LangPath + "EDD0610_tr_inq_fed.jsp", req, res);	
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
protected void procRecTransfSwift(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061003Message msgSwift = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgSwift = (EDD061003Message)mc.getMessageRecord("EDD061003");
	 	msgSwift.setH03USERID(user.getH01USR());
	 	msgSwift.setH03PROGRM("EDD0610");
	 	msgSwift.setH03TIMSYS(getTimeStamp());
	 	msgSwift.setH03SCRCOD("03");
	 	msgSwift.setH03OPECOD(opCode);
	 	msgSwift.setE03SCHBNK(userPO.getBank());
		msgSwift.setE03SCHNUM(userPO.getHeader20()); 
		msgSwift.setE03SCHTYA(userPO.getHeader21());	
		msgSwift.send();	
	 	msgSwift.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive  Messages
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_contab.jsp");
				callPage(LangPath + "EDD0610_tr_inq_contab.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("EDD061003")) {
			try {
				msgSwift = new datapro.eibs.beans.EDD061003Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgSwift = (EDD061003Message)newmessage; 
			// showESD008004(msgLC);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inqSwift", msgSwift);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_swift.jsp");
				callPage(LangPath + "EDD0610_tr_inq_swift.jsp", req, res);	
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
protected void procRecTransfTelex(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061004Message msgTelex = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

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
		msgTelex = (EDD061004Message)mc.getMessageRecord("EDD061004");
	 	msgTelex.setH04USERID(user.getH01USR());
	 	msgTelex.setH04PROGRM("EDD0610");
	 	msgTelex.setH04TIMSYS(getTimeStamp());
	 	msgTelex.setH04SCRCOD("03");
	 	msgTelex.setH04OPECOD(opCode);
	 	msgTelex.setE04SCHBNK(userPO.getBank());
		msgTelex.setE04SCHNUM(userPO.getHeader20()); 
		msgTelex.setE04SCHTYA(userPO.getHeader21());	
		msgTelex.send();	
	 	msgTelex.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive  Messages
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			showERROR(msgError);
			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_contab.jsp");
				callPage(LangPath + "EDD0610_tr_inq_contab.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}	
   		else if (newmessage.getFormatName().equals("EDD061004")) {
			try {
				msgTelex = new datapro.eibs.beans.EDD061004Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgTelex = (EDD061004Message)newmessage; 
			// showESD008004(msgLC);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("inqTelex", msgTelex);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_inq_telex.jsp");
				callPage(LangPath + "EDD0610_tr_inq_telex.jsp", req, res);	
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
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD061001Message msgSearch = null;
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
	//String num = "";
 	String num1 = "";
 	String num2 = "";
 	String num3 = "";
 	String num4 = "";
 	String num5 = "";
 	String num6 = "";
 	String num7 = "";
 	String num8 = "";
 	String num9 = "";
 	String num10 = "";
 	String num11 = "";
 	String num12 = "";
 	String num13 = "";
 	String num14 = "";
 	String num15 = "";
 	String num16 = "";
 	String num17 = "";
 	String num18 = "";
 	String num19 = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (EDD061001Message)mc.getMessageRecord("EDD061001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDD0610");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	try{
		 	num1 = userPO.getHeader1();
		 	num2 = userPO.getHeader2();
		 	num3 = userPO.getHeader3();
		 	num4 = userPO.getHeader4();
		 	num5 = userPO.getHeader5();
		 	num6 = userPO.getHeader6();
		 	num7 = userPO.getHeader7();
		 	num8 = userPO.getHeader8();
		 	num9 = userPO.getHeader9();
		 	num10 = userPO.getHeader10();
		 	num11 = userPO.getHeader11();
		 	num12 = userPO.getHeader12();
		 	num13 = userPO.getHeader13();
		 	num14 = userPO.getHeader14();
		 	num15 = userPO.getHeader15();
		 	num16 = userPO.getHeader16();
		 	num17 = userPO.getHeader17();
		 	num18 = userPO.getHeader18();
		 	num19 = userPO.getHeader19();
		 	
			try{
		 	msgSearch.setE01SCHTYA(num1);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHTYA("0");	
			flexLog("E01SCHTYA");
			}
			try{
		 	msgSearch.setE01SCHFD1(num3);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHFD1("0");	
			flexLog("E01SCHFD1");
			}
			try{
		 	msgSearch.setE01SCHFD2(num4);;
			}
			catch (Exception e){
		 		msgSearch.setE01SCHFD2("0");	
			flexLog("E01SCHFD2");
			}
			try{
		 	msgSearch.setE01SCHFD3(num5);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHFD3("0");	
			flexLog("E01SCHFD3");
			}
			try{
		 	msgSearch.setE01SCHTD1(num6);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHTD1("0");	
			flexLog("E01SCHTD1");
			}
			try{
		 	msgSearch.setE01SCHTD2(num7);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHTD2("0");	
			flexLog("E01SCHTD2");
			}
			try{
		 	msgSearch.setE01SCHTD3(num8);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHFD3("0");	
			flexLog("E01SCHFD3");
			}
			try{
		 	msgSearch.setE01SCHDGL(num9);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHDGL("0");	
			flexLog("E01SCHDGL");
			}
			try{
		 	msgSearch.setE01SCHDAC(num10);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHDAC("0");	
			flexLog("E01SCHDAC");
			}
			try{
		 	msgSearch.setE01SCHCGL(num11);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHCGL("0");	
			flexLog("E01SCHCGL");
			}
			try{
		 	msgSearch.setE01SCHCAC(num12);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHCAC("0");	
			flexLog("E01SCHCAC");
			}
			try{
		 	msgSearch.setE01SCHAMF(num13);
		 	}
			catch (Exception e){
		 		msgSearch.setE01SCHAMF("0");
		 	flexLog("");
			}
			try{
		 	msgSearch.setE01SCHAMT(num14);;
			}
			catch (Exception e){
		 		msgSearch.setE01SCHAMT("0");
		 	flexLog("");
			}
			try{
		 	msgSearch.setE01SCHORF(num15);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHORF("0");
		 	flexLog("");
			}
		 	try{
		 	msgSearch.setE01SCHBYO(num16);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHBYO("0");
		 	flexLog("");
			}
		 	try{
		 	msgSearch.setE01SCHBNF(num17);
			}
			catch (Exception e){
		 		msgSearch.setE01SCHBNF("0");
		 	flexLog("");
			}
		 	try{
		 	msgSearch.setE01SCHOPR(num18);
		    }
		    catch (Exception e){
		 	    msgSearch.setE01SCHOPR("0");
		 	flexLog("");
		    }
		 	try{
		 	msgSearch.setE01SCHREL(num19);
		    }
		    catch (Exception e){
		 		msgSearch.setE01SCHREL("0");
		 	flexLog("");
		    } 	
			   	 	
		 	try {
		 		msgSearch.setE01NUMREC(req.getParameter("Pos"));
		 	}
		 	catch (Exception ex) {
			 	msgSearch.setE01NUMREC("0");
		 	}
		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  flexLog("Input data error " + e);
		}	

	 		msgSearch.send();	
	 		msgSearch.destroy();
		
	
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
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_search.jsp");
				callPage(LangPath + "EDD0610_tr_search.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EDD061001")) {
			try {
				beanList = new datapro.eibs.beans.JBList();
		  	} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			try{
			 	beanList.setSearchType(type + "");
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			 	beanList.setSearchType("R");
			  	flexLog("Input data error " + e);
			}

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			
			while (true) {

				msgSearch = (EDD061001Message)newmessage;

				marker = msgSearch.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgSearch.getE01NUMREC()));
						chk = "checked";
					}
					else {
						chk = "";
					}
					String showTransfFunc = "showTransfDet('" + msgSearch.getE01SCHTYA() + "', '" + msgSearch.getE01WRTNUM() + "', '" + msgSearch.getE01SCHBNK() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showTransfFunc + "\">" + Util.formatCell(msgSearch.getE01WRTNUM()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showTransfFunc + "\">" + Util.formatCell(msgSearch.getE01WRTSTS()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT><A HREF=\"javascript:" + showTransfFunc + "\">" + Util.formatCell(msgSearch.getE01WRTBNF()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:" + showTransfFunc + "\">" + Util.formatDate(msgSearch.getE01WRTVT1(),msgSearch.getE01WRTVT2(),msgSearch.getE01WRTVT3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT><A HREF=\"javascript:" + showTransfFunc + "\">" + Util.formatCCY(msgSearch.getE01WRTAMT()) + "</TD>");
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
			ses.setAttribute("transList", beanList);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0610_tr_list.jsp");
				callPage(LangPath + "EDD0610_tr_list.jsp", req, res);

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
protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("TRAN");
		userPO.setPurpose("");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);

  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EDD0610_tr_search.jsp");
		callPage(LangPath + "EDD0610_tr_search.jsp", req, res);
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Exception calling page " + e);
	}

}
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
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

		int screen = R_SEARCH;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				mc = new MessageContext(super.getMessageHandler("EDD0610", req));
			
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				// Requests
				case R_SEARCH :
					procReqSearch(msgUser, req, res, session);
					break;
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case R_DET :
					procRecTransfDet(mc, msgUser, req, res, session);
					break;
				case R_SWIFT :
					procRecTransfSwift(mc, msgUser, req, res, session);
					break;
				case R_FED :
					procRecTransfFed(mc, msgUser, req, res, session);
					break;
				case R_TELEX :
					procRecTransfTelex(mc, msgUser, req, res, session);
					break;			
				// Actions
				case A_SEARCH :
					procActionSearch(mc, msgUser, req, res, session);
					break;
				
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				if(mc != null) mc.close();
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