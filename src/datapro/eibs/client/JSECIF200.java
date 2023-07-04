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

public class JSECIF200 extends datapro.eibs.master.SuperServlet {


	// entering options
	protected static final int R_ENTER_ACC			= 100;
	protected static final int A_ENTER_ACC			= 400;
	protected static final int R_ACC_LIST			= 300;
	protected static final int R_ACC_LIST_DEB			= 500;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECIF200() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECIF200");
	
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
protected void procActionEnterAcc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF20001Message msgAcc = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	java.math.BigDecimal garTot = new java.math.BigDecimal(0);
	java.math.BigDecimal bdbTot = new java.math.BigDecimal(0);
	java.math.BigDecimal bibTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal dbbTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal bdeTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal bieTot = new java.math.BigDecimal(0);		
	java.math.BigDecimal dbiTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal sdbTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal sibTot = new java.math.BigDecimal(0);
	java.math.BigDecimal siiTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal sdeTot = new java.math.BigDecimal(0);	
	java.math.BigDecimal sieTot = new java.math.BigDecimal(0);		
	java.math.BigDecimal seeTot = new java.math.BigDecimal(0);		
		
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{   
		msgAcc = (ECIF20001Message)mc.getMessageRecord("ECIF20001");
	 	msgAcc.setH01USERID(user.getH01USR());
	 	msgAcc.setH01PROGRM("ECIF200");
	 	msgAcc.setH01TIMSYS(getTimeStamp());
	 	msgAcc.setH01SCRCOD("01");
	 	msgAcc.setH01OPECOD("0004");
	 	flexLog("ECIF20001 Enter Account Header Sent");
	 	
		try {
			if(userPO.getCusNum().trim().equals("")){
			   msgAcc.setE01CUSCUN(req.getParameter("E01CUN"));
			}
			else {
			   msgAcc.setE01CUSCUN(userPO.getCusNum());	
			}
		}
		catch (Exception e)	{
			
		}
		try {
			msgAcc.setE01CUSIDN(req.getParameter("E01IDN"));
		}
		catch (Exception e)	{
		}
		
		msgAcc.send();	
	 	msgAcc.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("ECIF200 Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			//showERROR(msgError);
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_enter_basic.jsp");
				callPage(LangPath + "ECIF200_cr_enter_basic.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	  	}
	  	else if (newmessage.getFormatName().equals("ECIF20001")) {
			try {
				msgAcc = new datapro.eibs.beans.ECIF20001Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgAcc = (ECIF20001Message)newmessage;

			garTot = garTot.add(msgAcc.getBigDecimalE01GARACC());
			garTot = garTot.add(msgAcc.getBigDecimalE01GARFIA());			
			garTot = garTot.add(msgAcc.getBigDecimalE01GARHIP());
			garTot = garTot.add(msgAcc.getBigDecimalE01GARLET());
			garTot = garTot.add(msgAcc.getBigDecimalE01GAROTH());
			garTot = garTot.add(msgAcc.getBigDecimalE01GARPRE());
			garTot = garTot.add(msgAcc.getBigDecimalE01GARVFI());
			garTot = garTot.add(msgAcc.getBigDecimalE01GARWAR());												

			bdbTot = bdbTot.add(msgAcc.getBigDecimalE01BDBCAN());
			bdbTot = bdbTot.add(msgAcc.getBigDecimalE01BDBCAS());
			bdbTot = bdbTot.add(msgAcc.getBigDecimalE01BDBMOR());
			bdbTot = bdbTot.add(msgAcc.getBigDecimalE01BDBVEN());
			bdbTot = bdbTot.add(msgAcc.getBigDecimalE01BDBVIG());

			bibTot = bibTot.add(msgAcc.getBigDecimalE01BIBCAN());
			bibTot = bibTot.add(msgAcc.getBigDecimalE01BIBCAS());
			bibTot = bibTot.add(msgAcc.getBigDecimalE01BIBMOR());
			bibTot = bibTot.add(msgAcc.getBigDecimalE01BIBVEN());
			bibTot = bibTot.add(msgAcc.getBigDecimalE01BIBVIG());

			dbbTot = dbbTot.add(bdbTot);
			dbbTot = dbbTot.add(bibTot);

			bdeTot = bdeTot.add(msgAcc.getBigDecimalE01BDECAN());
			bdeTot = bdeTot.add(msgAcc.getBigDecimalE01BDECAS());
			bdeTot = bdeTot.add(msgAcc.getBigDecimalE01BDEMOR());
			bdeTot = bdeTot.add(msgAcc.getBigDecimalE01BDEVEN());
			bdeTot = bdeTot.add(msgAcc.getBigDecimalE01BDEVIG());

			bieTot = bieTot.add(msgAcc.getBigDecimalE01BIECAN());
			bieTot = bieTot.add(msgAcc.getBigDecimalE01BIECAS());
			bieTot = bieTot.add(msgAcc.getBigDecimalE01BIEMOR());
			bieTot = bieTot.add(msgAcc.getBigDecimalE01BIEVEN());
			bieTot = bieTot.add(msgAcc.getBigDecimalE01BIEVIG());

			dbiTot = dbiTot.add(bdeTot);
			dbiTot = dbiTot.add(bieTot);
			
			sdbTot = sdbTot.add(msgAcc.getBigDecimalE01SDBCAN());
			sdbTot = sdbTot.add(msgAcc.getBigDecimalE01SDBCAS());
			sdbTot = sdbTot.add(msgAcc.getBigDecimalE01SDBMOR());
			sdbTot = sdbTot.add(msgAcc.getBigDecimalE01SDBVEN());
			sdbTot = sdbTot.add(msgAcc.getBigDecimalE01SDBVIG());

			sibTot = sibTot.add(msgAcc.getBigDecimalE01SIBCAN());
			sibTot = sibTot.add(msgAcc.getBigDecimalE01SIBCAS());
			sibTot = sibTot.add(msgAcc.getBigDecimalE01SIBMOR());
			sibTot = sibTot.add(msgAcc.getBigDecimalE01SIBVEN());
			sibTot = sibTot.add(msgAcc.getBigDecimalE01SIBVIG());

			siiTot = siiTot.add(bdeTot);
			siiTot = siiTot.add(bieTot);

			sdeTot = sdeTot.add(msgAcc.getBigDecimalE01SDECAN());
			sdeTot = sdeTot.add(msgAcc.getBigDecimalE01SDECAS());
			sdeTot = sdeTot.add(msgAcc.getBigDecimalE01SDEMOR());
			sdeTot = sdeTot.add(msgAcc.getBigDecimalE01SDEVEN());
			sdeTot = sdeTot.add(msgAcc.getBigDecimalE01SDEVIG());
			
			sieTot = sieTot.add(msgAcc.getBigDecimalE01SIECAN());
			sieTot = sieTot.add(msgAcc.getBigDecimalE01SIECAS());
			sieTot = sieTot.add(msgAcc.getBigDecimalE01SIEMOR());
			sieTot = sieTot.add(msgAcc.getBigDecimalE01SIEVEN());
			sieTot = sieTot.add(msgAcc.getBigDecimalE01SIEVIG());

			seeTot = siiTot.add(sdeTot);
			seeTot = siiTot.add(sieTot);
			
								
			userPO.setCusNum(msgAcc.getE01CUSCUN());
			userPO.setID(msgAcc.getE01CUSIDN());
			userPO.setCusName(msgAcc.getE01CUSNME());
			userPO.setHeader1(Util.fcolorCCY(garTot.toString()));
			userPO.setHeader2(Util.fcolorCCY(bdbTot.toString()));
			userPO.setHeader3(Util.fcolorCCY(bibTot.toString()));
			userPO.setHeader4(Util.fcolorCCY(dbbTot.toString()));
			userPO.setHeader5(Util.fcolorCCY(bdeTot.toString()));
			userPO.setHeader6(Util.fcolorCCY(bieTot.toString()));
			userPO.setHeader7(Util.fcolorCCY(dbiTot.toString()));
			userPO.setHeader15(Util.fcolorCCY(sdbTot.toString()));
			userPO.setHeader9(Util.fcolorCCY(sibTot.toString()));			
			userPO.setHeader10(Util.fcolorCCY(siiTot.toString()));
			userPO.setHeader11(Util.fcolorCCY(sdeTot.toString()));
			userPO.setHeader12(Util.fcolorCCY(sieTot.toString()));			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("custinf", msgAcc);
			
			
			try {
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_basic.jsp");
				callPage(LangPath + "ECIF200_cr_basic.jsp", req, res);	
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
protected void procReqEnterAcc(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();

		
  	} catch (Exception ex) {
		flexLog("Error: " + ex);  
  	}

			try {
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_enter.jsp");
				callPage(LangPath + "ECIF200_cr_enter.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqAcc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF01006Message msgList = null;
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

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF01006Message)mc.getMessageRecord("ECIF01006");
		msgList.setH06USERID(user.getH01USR());
	 	msgList.setH06PROGRM("ECIF010");
	 	msgList.setH06TIMSYS(getTimeStamp());
	 	msgList.setH06SCRCOD("01");
	 	msgList.setH06OPECOD("0004");
	 	msgList.setE06SELCUN(userPO.getCusNum());
	 	flexLog("ECIF01006 Header Sent");
		try {
	 		String s = 	req.getParameter("appCode");
			msgList.setE06SELACD(s);
		}
		catch (Exception ex) {
	 		msgList.setE06SELACD("");
		}
		try {
	 		String s = 	req.getParameter("flag");
			msgList.setE06SELFLG(s);
		}
		catch (Exception ex) {
	 		msgList.setE06SELFLG("");
		}
		try {
	 		String s = 	req.getParameter("prodType");
			msgList.setE06SELTYP(s);
		}
		catch (Exception ex) {
	 		msgList.setE06SELTYP("");
		}
		try {
	 		String s = 	req.getParameter("prodCode");
			msgList.setE06SELPRO(s);
		}
		catch (Exception ex) {
	 		msgList.setE06SELPRO("");
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
				// callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
				// flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
				res.sendRedirect(super.srctx + LangPath + "Under_construction.jsp");
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF01006")) {
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
			String chk = "";
			int indexRow = 0;
			java.math.BigDecimal sumTotal = new java.math.BigDecimal("0");
			while (true) {

				msgList = (ECIF01006Message)newmessage;

				marker = msgList.getE06INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {

					if ( firstTime ) {
						firstTime = false;
						chk = "checked";
					}
					else {
						chk = "";
					}
					
					myFlag = "";
					myRow =  new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE06ACCNUM() + "\" " + chk + " onclick=\"showAddInfo("+indexRow+")\"></TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE06ACCNUM()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06ACCSTS()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06PROCDE()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06CCYCDE()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.fcolorCCY(msgList.getE06PRIAMN()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06OFFICR()));
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE06BNKNUM()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE06BRNNUM()) + "<br>");
					myRow.append(Util.formatDate(msgList.getE06OPEDT1(), msgList.getE06OPEDT2(), msgList.getE06OPEDT3()) + "<br>");
					myRow.append(Util.fcolorCCY(msgList.getE06PRIAMN()) + "<br>");
					myRow.append(Util.fcolorCCY(msgList.getE06INTAMN()) + "<br>");
					myRow.append(Util.fcolorCCY(msgList.getE06OTHAMN()) + "<br>");
					myRow.append(Util.fcolorCCY(msgList.getE06TOTAMN()) + "\">");
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"CODACD"+indexRow+"\" VALUE=\"" + Util.formatCell(msgList.getE06SELACD()) + "\"></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;
					sumTotal= sumTotal.add(msgList.getBigDecimalE06PRIAMN());	
				}

				newmessage = mc.receiveMessage();
			}
			
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("cifAcc", beanList);

			try {
				req.setAttribute("Total",""+ sumTotal);
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_accounts.jsp");
				callPage(LangPath + "ECIF200_cr_accounts.jsp", req, res);

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

protected void procReqListDebits(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECIF20002Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String inqtyp = null;
		
		int flgsts = 0;
		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECIF20002Message) mc.getMessageRecord("ECIF20002");
			
			msgList.setH02USERID(user.getH01USR());
			
			try { // Customer 
				msgList.setE02CUSCUN(req.getParameter("E01CUSCUN"));
				
			} catch (Exception e) {
				
			}

			
			try { // Debit Type
				msgList.setE02FLGTYP(req.getParameter("DEBTYP"));
				
			} catch (Exception e) {
			}
			
			try { // STATUS
				msgList.setE02FLGSTS(req.getParameter("DEBSTS"));
				
			} catch (Exception e) {
				
			}
			
			inqtyp = req.getParameter("DEBTYP") + req.getParameter("DEBSTS");
			userPO.setHeader20("");
			userPO.setHeader20(inqtyp);
			
			msgList.send();
			msgList.destroy();
			flexLog("ECIF20002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", beanList);
				ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECIF200_cr_basic.jsp");
						callPage(LangPath + "ECIF200_cr_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}


			} else
				if (newmessage.getFormatName().equals("ECIF20002")) {

					try {
						beanList =new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					
					int indexRow = 0;
					while (true) {

						msgList = (ECIF20002Message) newmessage;
						
							/* if(msgList.getE02FLGSTS().equals("")){
								flgsts = 0;
							} else {
								flgsts = Integer.parseInt(msgList.getE02FLGSTS());
							}
							
							if(msgList.getE02FLGTYP().equals("D")){
								switch (flgsts){
									case 0  : userPO.setHeader20("D");
									case 1 : userPO.setHeader20("D1");
									case 2 : userPO.setHeader20("D2");
									case 3 : userPO.setHeader20("D3");
									case 4 : userPO.setHeader20("D4");
									case 5 : userPO.setHeader20("D5");

								}
							} 
							if(msgList.getE02FLGTYP().equals("I")){
								switch (flgsts){
									case 0 : userPO.setHeader20("I");
									case 1 : userPO.setHeader20("I1");
									case 2 : userPO.setHeader20("I2");
									case 3 : userPO.setHeader20("I3");
									case 4 : userPO.setHeader20("I4");
									case 5 : userPO.setHeader20("I5");
									
							}



							}
						
*/
						marker = msgList.getH02FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgList.getE02DEAACC()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgList.getE02DEAACC() 
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02DEAPRD() + "</td>"); //Product								
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02DEAACC() + "</td>"); //Account Number
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + msgList.getE02DEACCY() + "</td>"); //Account Currency
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02DEAPRI()) + "</td>"); //Principal Amount							
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02DEAINT()) + "</td>"); //Interest Amount	
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE02DEAREA()) + "</td>"); // Monto de Reajuste
							myRow.append("<TD NOWRAP  ALIGN=CENTER>" + Util.formatDate(msgList.getE02DEAMD1(),msgList.getE02DEAMD2(),msgList.getE02DEAMD3()) + "</td>"); //Fecha de Vencimiento																																									
							
							
							
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
					ses.setAttribute("ECIF20002", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECIF200_debit_list.jsp");
						callPage(LangPath + "ECIF200_debit_list.jsp", req, res);
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

		int screen = R_ENTER_ACC;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
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
				case R_ENTER_ACC :
					procReqEnterAcc(msgUser, req, res, session);
					break;
				case R_ACC_LIST :
					procReqAcc(mc,msgUser, req, res, session);
					break;
				case R_ACC_LIST_DEB :
					procReqListDebits(mc,msgUser, req, res, session);
					break;
	
				// Actions
				case A_ENTER_ACC :
					procActionEnterAcc(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
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