package datapro.eibs.products;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;

import datapro.eibs.generic.JBParseTree;

public class JSESD0711 extends datapro.eibs.master.SuperServlet {

	protected static final int R_P_AUDIO 				= 2;
	protected static final int R_P_VIDEO 				= 4;
	protected static final int R_P_HTML 				= 6;
	protected static final int R_P_INQUIRY			= 8;
	protected static final int R_P_NEW 				= 10;

	protected static final int R_P_SHOW 				= 100;

	protected String LangPath = "S";

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSESD0711() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("Destroying servlet JSESD0711");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

  		int appCode = Integer.parseInt(req.getParameter("appcode"));

		userPO.setOption("PRODUCTS");
		userPO.setPurpose("");
		ses.setAttribute("userPO", userPO);

		switch (appCode) {
				case 11 :
				case 12 :	
				case 14 :
				case 15 : // CD
					procReqProductCD(mc, user, req, res, ses);
					break;
				case 1  :	// DDA Accounts
				case 2  :	
				case 3  :	
				case 4  :	
					procReqProductDDA(mc, user, req, res, ses);
					break;
				case 10 :
					procReqProductLN(mc, user, req, res, ses);
					break;
				case 50 :
				case 51 :	// Collections
					procReqProductCOL(mc, user, req, res, ses);
					break;
				case 13 :	// Inv 
					procReqProductINV(mc, user, req, res, ses);
					break;
				case 40 :
				case 41 :	// LC
				case 42 :
					procReqProductLC(mc, user, req, res, ses);
					break;
				case 19 :	// Proy 
					procReqProductPRY(mc, user, req, res, ses);
					break;	
				default :
					procReqProductDetails(req, res);
  		}
  			

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqMedia(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses, int screen)
			throws ServletException, IOException {
/*
					try {
						flexLog("About to call Page: " + LangPath + "media/phrase_01.au");
						String firstLink = LangPath + "ESD0711_products_detail.jsp?appcode=" + req.getParameter("appcode").trim() + "&typecode=" + req.getParameter("typecode").trim() + "&generic=&title=" + req.getParameter("title").trim() + "&sel=" + req.getParameter("PROD_CODE").trim();
						res.sendRedirect(super.srctx + firstLink);
						// res.sendRedirect(super.srctx + LangPath + "media/phrase_01.au");
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
*/				
	MessageRecord newmessage = null;
	ESD071150Message msgMedia = null;
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

  	String appCode = req.getParameter("appcode");
  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");
  	String typeCode = req.getParameter("typecode");
  	String generic = req.getParameter("generic");
  	String title = req.getParameter("title");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgMedia = (ESD071150Message)mc.getMessageRecord("ESD071150");
	 	msgMedia.setE50USERID(user.getH01USR());
	 	msgMedia.setE50APECDE(prodCode);
	 	msgMedia.setE50APETYP(typeCode);
	 	msgMedia.setE50APEBNK(bank);
		msgMedia.send();	
	 	msgMedia.destroy();

	 	flexLog("ESD071150 Message Sent");
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

			try {
				procReqProductDetails(req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		
	  	}
	  	else if (newmessage.getFormatName().equals("ESD071150")) {

			msgMedia = (ESD071150Message)newmessage;

			flexLog("ESD071150 Message Received");

			String fileName = null;

			switch (screen) {
				case R_P_AUDIO :
					fileName = msgMedia.getE50APEAUD().trim(); // "phrase_01.au"; //msgMedia.getE50APEAUD().trim();
					break;
				case R_P_VIDEO :
					fileName = msgMedia.getE50APEVID().trim();
					break;
				case R_P_HTML :
					fileName = msgMedia.getE50APEHTM().trim();
					if (!fileName.equals("")) {
						userPO.setHeader2("DO_HTML");
						userPO.setHeader3(super.webAppPath + LangPath + "media/" + fileName);
						ses.setAttribute("userPO", userPO);
						fileName = "";
					}
					break;
				default :
			}

			try {
				URL url = new URL(getServerRoot(req) + super.webAppPath + LangPath + "media/" + fileName);
				InputStream inputstream = url.openStream();
 			}
			catch (Exception e) {
				fileName = "";
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (fileName.equals("")) {
				try {
					procReqProductDetails(req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {
				try {
					flexLog("About to call Page: " + LangPath + "media/" + fileName);
					res.sendRedirect(super.srctx + LangPath + "media/" + fileName);
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
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqNew(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	UserPos	userPO = null;	

	try {

  		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

  		int appCode = 0;
  		try {
	  		appCode = Integer.parseInt(req.getParameter("appcode"));
  		}
  		catch (Exception e) {
  			if (req.getParameter("appcode").equals("B1")) {
  				appCode = 999;
  			}
  		}
  		String bank = req.getParameter("bank");
  		String prodCode = req.getParameter("PROD_CODE");
  		String typeCode = req.getParameter("typecode");
  		String generic = req.getParameter("generic");
  		String title = req.getParameter("title");
		String accnum = req.getParameter("accnum");
		String deapac = req.getParameter("deapac");
		String refnum = userPO.getHeader15();
		String amount = userPO.getHeader16();		
		
		switch (appCode) {
				case 13 :
					try {
						userPO.setOption("CP");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0105?SCREEN=200&E01DEAPRO=" + prodCode + "&E01DEAACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title +"&E01OFFAC1=" + refnum +"&E01DEAAMT=" + amount + "&bank=" + bank);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product CP Requested");
					} catch (Exception ex) {
						flexLog("Error: " + ex); 
					}
					break;
				case 11 :
				case 12 :  	
				case 14 : // CD
					try {
						userPO.setOption("CD");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0130?SCREEN=400&E01DEAPRO=" + prodCode + "&E01DEAACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title +"&E01OFFAC1=" + refnum +"&E01DEAAMT=" + amount + "&bank=" + bank);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product CD Requested");
				  	} catch (Exception ex) {
						flexLog("Error: " + ex); 
				  	}
					break;
				case 1 :	// Retail Accounts
				case 2 :
				case 3 :
				case 5 :
					try {
						userPO.setOption("RT");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDD0000?SCREEN=200&E01ACMPRO=" + prodCode + "&E01ACMACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank +"&E01OFFAC1=" + refnum +"&E01ACMAMT=" + amount);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product Retail Account Requested");
				  	} catch (Exception ex) {
						flexLog("Error: " + ex); 
				  	}
					break;
				case 4 :	// Saving Accounts
					try {
						userPO.setOption("SV");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDD0000?SCREEN=600&E01ACMPRO=" + prodCode + "&E01ACMACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank +"&E01OFFAC1=" + refnum +"&E01ACMAMT=" + amount);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product Saving Account Requested");
				  	} catch (Exception ex) {
						flexLog("Error: " + ex); 
				  	}
					break;
				case 10 :	// Loan Accounts
					try {
						userPO.setOption("LN");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						//mod EMAT (5/12/2001)
						//redirect to Drafts Servlet if generic = 'G' // valores al cobro 'V'
						if (generic.equals("G")) {
							userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=200&E01DEAPRO=" + prodCode + "&E01DEAACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank +"&E01OFFAC1=" + refnum +"&E01ACMAMT=" + amount);
						} else if (generic.equals("V")){
							userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=200&E01DLHPRD=" + prodCode + "&E01DLHNRO=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank);
						} else {
							userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSEDL0150?SCREEN=200&E01DEAPRO=" + prodCode + "&E01DEAACC=" + accnum + "&E01DEAPAC=" + deapac + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank +"&E01OFFAC1=" + refnum +"&E01ACMAMT=" + amount);
						}
						
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product Loan Requested");
				  	} catch (Exception ex) {
						flexLog("Error: " + ex); 
				  	}
					break;
			case 40 : // Letters of Credit
					try {
						userPO.setOption("40");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSELC0500?SCREEN=1&E01LCMPRO=" + prodCode + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product Letter of Credit Requested");
					} catch (Exception ex) {
						flexLog("Error: " + ex); 
					}
					break;	
				case 43 :	// Boleta Garantia
					try {
						userPO.setOption("BG");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=100&E01LCMPRO=" + prodCode + "&E01LCMACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&bank=" + bank);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product Boleta Garantia Requested");
				  	} catch (Exception ex) {
						flexLog("Error: " + ex); 
				  	}
					break;
				case 94 : // Credit Card
					try {
						userPO.setOption("CC");
						userPO.setPurpose("NEW");
						userPO.setHeader2("DO_NEW");
						userPO.setHeader3(super.webAppPath + "/servlet/datapro.eibs.products.JSECC0010?SCREEN=1&E01CCMPRO=" + prodCode + "&setE01CCMACC=" + accnum + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title +"&E01OFFAC1=" + refnum +"&E01DEAAMT=" + amount + "&bank=" + bank);
						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);
						flexLog("Product CC Requested");
					} catch (Exception ex) {
						flexLog("Error: " + ex); 
					}
					break;		
				case 95 :  // MMP 
				case 999 : // FRA
					try {
						procMessageError(req, res, ses);
					} catch (Exception ex) {
						flexLog("Error: " + ex); 
					}
					break;					

				default :
					procReqProductDetails(req, res);
  		}
  			

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductCD(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071111Message msgProdCD = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdCD = (ESD071111Message)mc.getMessageRecord("ESD071111");
	 	msgProdCD.setH11USERID(user.getH01USR());
	 	msgProdCD.setH11PROGRM("ESD0711");
	 	msgProdCD.setH11TIMSYS(getTimeStamp());
	 	msgProdCD.setH11SCRCOD("01");
	 	msgProdCD.setH11OPECOD(opCode);
	 	msgProdCD.setE11APCCDE(prodCode);
	 	msgProdCD.setE11APCBNK(bank);
		msgProdCD.send();	
	 	msgProdCD.destroy();
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
			showERROR(msgError);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071111")) {
			try {
				msgProdCD = new datapro.eibs.beans.ESD071111Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdCD = (ESD071111Message)newmessage;
			// showESD071111(msgProdCD);

			flexLog("Putting java beans into the session");
			ses.setAttribute("cdProdInq", msgProdCD);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_cd.jsp");
				// callPage(LangPath + "ESD0711_products_inq_cd.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_cd.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductCOL(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071105Message msgProdCOL = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdCOL = (ESD071105Message)mc.getMessageRecord("ESD071105");
	 	msgProdCOL.setH05USERID(user.getH01USR());
	 	msgProdCOL.setH05PROGRM("ESD0711");
	 	msgProdCOL.setH05TIMSYS(getTimeStamp());
	 	msgProdCOL.setH05SCRCOD("01");
	 	msgProdCOL.setH05OPECOD(opCode);
	 	msgProdCOL.setE05APCCDE(prodCode);
	 	msgProdCOL.setE05APCBNK(bank);
		msgProdCOL.send();	
	 	msgProdCOL.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071105")) {
			try {
				msgProdCOL = new datapro.eibs.beans.ESD071105Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdCOL = (ESD071105Message)newmessage;
			// showESD071105(msgProdCOL);

			flexLog("Putting java beans into the session");
			ses.setAttribute("colProdInq", msgProdCOL);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_ln.jsp");
				// callPage(LangPath + "ESD0711_products_inq_ln.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_col.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductDDA(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071103Message msgProdDDA = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdDDA = (ESD071103Message)mc.getMessageRecord("ESD071103");
	 	msgProdDDA.setH03USERID(user.getH01USR());
	 	msgProdDDA.setH03PROGRM("ESD0711");
	 	msgProdDDA.setH03TIMSYS(getTimeStamp());
	 	msgProdDDA.setH03SCRCOD("01");
	 	msgProdDDA.setH03OPECOD(opCode);
	 	msgProdDDA.setE03APCCDE(prodCode);
	 	msgProdDDA.setE03APCBNK(bank);
		msgProdDDA.send();	
	 	msgProdDDA.destroy();
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
			showERROR(msgError);
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071103")) {
			try {
				msgProdDDA = new datapro.eibs.beans.ESD071103Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdDDA = (ESD071103Message)newmessage;
			// showESD071103(msgProdDDA);

			flexLog("Putting java beans into the session");
			ses.setAttribute("ddaProdInq", msgProdDDA);

			try {
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_dda.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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

protected void procMessageError(HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	ELEERRMessage msgError = null;
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		msgError.setERRNUM("1");
		msgError.setERNU01("");
		msgError.setERWF01("Y");
		msgError.setERDS01("This product must entered using the Treasury Module.");
		ses.setAttribute("error", msgError);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}
	
	procReqProductDetails(req, res);

}

/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductDetails(HttpServletRequest req, HttpServletResponse res) {

	try {

  		String appCode = req.getParameter("appcode");
  		String bank = req.getParameter("bank");
  		String prodCode = req.getParameter("PROD_CODE");
  		String typeCode = req.getParameter("typecode");
  		String generic = req.getParameter("generic");
  		String title = req.getParameter("title");

		String firstLink = LangPath + "ESD0711_products_detail.jsp?bank=" + bank + "&appcode=" + appCode + "&typecode=" + typeCode + "&generic=" + generic + "&title=" + title + "&sel=" + prodCode;
		flexLog("About to call Page: " + firstLink);
		res.sendRedirect(super.srctx + firstLink);

	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductINV(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071113Message msgProdINV = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdINV = (ESD071113Message)mc.getMessageRecord("ESD071113");
	 	msgProdINV.setH13USERID(user.getH01USR());
	 	msgProdINV.setH13PROGRM("ESD0711");
	 	msgProdINV.setH13TIMSYS(getTimeStamp());
	 	msgProdINV.setH13SCRCOD("01");
	 	msgProdINV.setH13OPECOD(opCode);
	 	msgProdINV.setE13APCCDE(prodCode);
	 	msgProdINV.setE13APCBNK(bank);
		msgProdINV.send();	
	 	msgProdINV.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071113")) {
			try {
				msgProdINV = new datapro.eibs.beans.ESD071113Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdINV = (ESD071113Message)newmessage;
			// showESD071113(msgProdINV);

			flexLog("Putting java beans into the session");
			ses.setAttribute("invProdInq", msgProdINV);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_ln.jsp");
				// callPage(LangPath + "ESD0711_products_inq_ln.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_inv.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductLC(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071104Message msgProdLC = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdLC = (ESD071104Message)mc.getMessageRecord("ESD071104");
	 	msgProdLC.setH04USERID(user.getH01USR());
	 	msgProdLC.setH04PROGRM("ESD0711");
	 	msgProdLC.setH04TIMSYS(getTimeStamp());
	 	msgProdLC.setH04SCRCOD("01");
	 	msgProdLC.setH04OPECOD(opCode);
	 	msgProdLC.setE04APCCDE(prodCode);
	 	msgProdLC.setE04APCBNK(bank);
		msgProdLC.send();	
	 	msgProdLC.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071104")) {
			try {
				msgProdLC = new datapro.eibs.beans.ESD071104Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdLC = (ESD071104Message)newmessage;
			// showESD071104(msgProdLC);

			flexLog("Putting java beans into the session");
			ses.setAttribute("lcProdInq", msgProdLC);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_ln.jsp");
				// callPage(LangPath + "ESD0711_products_inq_ln.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_lc.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductLN(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071110Message msgProdLN = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdLN = (ESD071110Message)mc.getMessageRecord("ESD071110");
	 	msgProdLN.setH10USERID(user.getH01USR());
	 	msgProdLN.setH10PROGRM("ESD0711");
	 	msgProdLN.setH10TIMSYS(getTimeStamp());
	 	msgProdLN.setH10SCRCOD("01");
	 	msgProdLN.setH10OPECOD(opCode);
	 	msgProdLN.setE10APCCDE(prodCode);
	 	msgProdLN.setE10APCBNK(bank);
		msgProdLN.send();	
	 	msgProdLN.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071110")) {
			try {
				msgProdLN = new datapro.eibs.beans.ESD071110Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdLN = (ESD071110Message)newmessage;
			// showESD071110(msgProdLN);

			flexLog("Putting java beans into the session");
			ses.setAttribute("lnProdInq", msgProdLN);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_ln.jsp");
				// callPage(LangPath + "ESD0711_products_inq_ln.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_ln.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProductPRY(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071119Message msgProdPRY = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

  	String bank = req.getParameter("bank");
  	String prodCode = req.getParameter("PROD_CODE");

  	String opCode = "0004";

	// Send Initial data
	try
	{
		msgProdPRY = (ESD071119Message)mc.getMessageRecord("ESD071119");
	 	msgProdPRY.setH19USERID(user.getH01USR());
	 	msgProdPRY.setH19PROGRM("ESD0711");
	 	msgProdPRY.setH19TIMSYS(getTimeStamp());
	 	msgProdPRY.setH19SCRCOD("01");
	 	msgProdPRY.setH19OPECOD(opCode);
	 	msgProdPRY.setE19APCCDE(prodCode);
	 	msgProdPRY.setE19APCBNK(bank);
		msgProdPRY.send();	
	 	msgProdPRY.destroy();
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
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			
			procReqProductDetails(req, res);
	  }
	  else if (newmessage.getFormatName().equals("ESD071119")) {
			try {
				msgProdPRY = new datapro.eibs.beans.ESD071119Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgProdPRY = (ESD071119Message)newmessage;
			// showESD071119(msgProdPRY);

			flexLog("Putting java beans into the session");
			ses.setAttribute("pryProdInq", msgProdPRY);

			try {
				// flexLog("About to call Page: " + LangPath + "ESD0711_products_inq_ln.jsp");
				// callPage(LangPath + "ESD0711_products_inq_ln.jsp", req, res);
				userPO.setHeader2("DO_INQ");
				userPO.setHeader3(super.webAppPath + LangPath + "ESD0711_products_inq_proy.jsp");
				ses.setAttribute("userPO", userPO);
				procReqProductDetails(req, res);
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
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
protected void procReqProducts(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

	MessageRecord newmessage = null;
	ESD071101Message msgTree = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;

	//JBParseTree dataTree = new JBParseTree();
	JBObjList prodList = null;
	JBObjList subProdList = null;
	JBObjList elemList = null;	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	String outParams = "";
	String firstLink = "";

	String appCode = "";
	String genCode = "";
	String refnum = "";
	String amount = "";
	
	try {
		String code = req.getParameter("TYPE");
		appCode = code.equals("ALL") ? "" : code;
		//mod EMAT 11/16/2001
		//parameter GENERIC = 'U' debts
		genCode = req.getParameter("GENERIC");
		if (genCode == null) genCode = "";
	}
	catch (Exception e) {
		flexLog("Error getting requesting parameters");
	}
	
	try{
		refnum = req.getParameter("REFNUM");
		userPO.setHeader15(refnum);
	}
	catch (Exception e) {
		flexLog("Error getting requesting parameters");
	}

	try{
		amount = req.getParameter("AMOUNT");
		userPO.setHeader16(amount);
	}
	catch (Exception e) {
		flexLog("Error getting requesting parameters");
	}

	
	// Send Request
  	try {
		msgTree = (ESD071101Message)mc.getMessageRecord("ESD071101");
	 	msgTree.setE01USERID(user.getH01USR());
	 	msgTree.setE01SELACD(appCode);
	 	//mod EMAT 11/16/2001
	 	//parameter GENERIC = 'U' debts
	 	msgTree.setE01GENERI(genCode);
		msgTree.send();	
	 	msgTree.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receiving
	try
	{
	  flexLog("About to receive data");
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ESD071101")) {
		msgTree = (ESD071101Message)newmessage; 
		
		if (msgTree.getE01RECTYP().equals("0")) {

			String titleFolder = msgTree.getE01DESCRI().trim();
			prodList = new JBObjList();
			subProdList = new JBObjList();
			elemList = new JBObjList();
			
			ses.setAttribute("title",titleFolder);
			boolean firstTime = false;
			boolean firstfolder = true;
			while (true) {
				newmessage = mc.receiveMessage();
				msgTree = (ESD071101Message)newmessage; 
				
				if ( msgTree.getE01RECTYP().equals("1") ) {
					
					prodList.addRow(msgTree);
					if (!elemList.getNoResult()) {
					  subProdList.addRow(elemList);
					  elemList = new JBObjList();
					}
					if (firstfolder) {
					   firstTime = true;
					   firstfolder = false;
					}
					//folder = msgTree.getE01DESCRI().trim();
					//folderLink = getServerRoot(req) + super.webAppPath + LangPath + "ESD0711_products_detail.jsp?appcode=" + msgTree.getE01APCACD().trim() + "&typecode=&generic=" + msgTree.getE01GENERI().trim() + "&typecode=&title=" + msgTree.getE01DESCRI().trim() + "&bank=" + msgTree.getE01APCBNK();
					
				}
				else if ( msgTree.getE01RECTYP().equals("2") ) {
					if (firstTime) {
						firstLink = super.webAppPath + LangPath + "ESD0711_products_detail.jsp?appcode=" + msgTree.getE01APCACD().trim() + "&typecode=" + msgTree.getE01APCTYP().trim() + "&generic=" + msgTree.getE01GENERI().trim() + "&title=" + msgTree.getE01DESCRI().trim() + "&bank=" + msgTree.getE01APCBNK();
						firstTime = false;
					}
					//firstLink = super.webAppPath + LangPath + "ESD0711_products_detail.jsp?appcode=" + msgTree.getE01APCACD().trim() + "&typecode=" + msgTree.getE01APCTYP().trim() + "&generic=" + msgTree.getE01GENERI().trim() + "&title=" + msgTree.getE01DESCRI().trim() + "&bank=" + msgTree.getE01APCBNK();
					//itemLink = getServerRoot(req) + super.webAppPath + LangPath + "ESD0711_products_detail.jsp?appcode=" + msgTree.getE01APCACD().trim() + "&typecode=" + msgTree.getE01APCTYP().trim() + "&generic=" + msgTree.getE01GENERI().trim() + "&title=" + msgTree.getE01DESCRI().trim() + "&bank=" + msgTree.getE01APCBNK();
					elemList.addRow(msgTree);

				}
				else {
					if (!elemList.getNoResult()) {
					  subProdList.addRow(elemList);
					  elemList = new JBObjList();
					}
					if ( msgTree.getE01RECTYP().equals("*") ) break;
				}
			}
			
			//outParams += "		</folder>\n";
			//outParams += "</treecontrol>";
			
		  }
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");

  	
	  msgError.setERRNUM("0");
	  userPO.setHeader2("");
	  userPO.setType(genCode);
	  //outParams = dataTree.getTree();
	  ses.setAttribute("error", msgError);
  	  ses.setAttribute("userPO", userPO);
	  ses.setAttribute("prodList", prodList);
	  ses.setAttribute("subProdList", subProdList);

//	  flexLog("About to call Page: " + LangPath + "ESD0711_products_offer_frame.jsp");
//	  res.sendRedirect(super.srctx +LangPath + "ESD0711_products_offer_frame.jsp");	

	  res.setContentType("text/html");
	  res.setHeader("Pragma", "No-cache");
	  res.setDateHeader ("Expires", 0);
	  res.setHeader("Cache-Control", "no-cache");
	  PrintWriter out = res.getWriter();
	  printProdFrame(out, firstLink, LangPath);
/*
	  out.println("<!-- frames -->");
	  out.println("<frameset  rows=\"30%,*\">");
	  out.println("<frame name=\"list\" src=\"" + super.webAppPath + LangPath + "ESD0711_products_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
	  out.println("<frame name=\"detail\" src=\"" + firstLink + "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
	  out.println("</frameset>");
	  out.close();
*/
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
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

		int screen = R_P_SHOW;
		
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
				case R_P_SHOW :
					procReqProducts(mc, msgUser, req, res, session);
					break;
				case R_P_NEW :
					procReqNew(msgUser, req, res, session);
					break;
				case R_P_INQUIRY :
					procReqInq(mc, msgUser, req, res, session);
					break;
				case R_P_AUDIO :
				case R_P_VIDEO :
				case R_P_HTML :
					procReqMedia(mc, msgUser, req, res, session, screen);
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
protected void showESD071101(ESD071101Message m)
{
	if (logType != NONE) {
		
		flexLog("ESD071101 received.");
		
		flexLog("User ID 		= " + m.getE01USERID());
		flexLog("Product Code 	= " + m.getE01SELACD());
		flexLog("Type of Record = " + m.getE01RECTYP());
		flexLog("Generic 		= " + m.getE01GENERI());
		flexLog("Products Code  = " + m.getE01APCACD());
		flexLog("Produts Type   = " + m.getE01APCTYP());
		flexLog("Description 	= " + m.getE01DESCRI());
		
	}
}
}