package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EOF011501Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETL051001Message;
import datapro.eibs.beans.EWD0120DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.tools.pdf.XMLPDFDocument;

public class JSEOF0115P extends datapro.eibs.master.SuperServlet {

	// certificate of deposit
	
	protected static final int R_BASIC		    = 1;
	protected static final int A_BASIC		    = 2;
	protected static final int R_LIST_HELP		= 3;
	protected static final int R_LIST_LEDGER	= 5;
	protected static final int R_LIST_PRINT		= 7;
	protected static final int R_CHANGE_NUMBER	= 9;
	protected static final int A_CHANGE_NUMBER	= 10;	
	
	// entering options
	protected static final int R_LIST_PENDING	    = 100;
	protected static final int R_ENTER_PRINT		= 300;

	protected static final int A_LIST_PENDING	    = 200;
	protected static final int A_ENTER_PRINT		= 400;
	
	protected static final int R_MULTIPLE_PRINT		= 500;
	protected static final int A_MULTIPLE_PRINT		= 600;
	
	protected String LangPath = "S";

/**
 * JSEOF0115P constructor comment.
 */
public JSEOF0115P() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEOF0115P");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

protected ByteArrayOutputStream generatePDFDocumentBytes(
		HttpServletRequest req,
		ESS0030DSMessage user,
		EOF011501Message msgOffChk,
		JBObjList transList,
		boolean FLG)
		throws DocumentException, IOException {

		XMLPDFDocument XMLPDFdoc = null;
			
		try {
			
			XMLPDFdoc = new XMLPDFDocument();
			if( msgOffChk.getE01SELTIP().equals("3") ){
				//GIROS
				XMLPDFdoc.readXMLDoc("printformat.giro.xml");
			}else{
				//CHEQUES OFICIALES
				XMLPDFdoc.readXMLDoc("printformat.check.xml");
			}
			
			
		} catch (IOException ex){
			System.err.println(ex.getMessage());
		}

		XMLPDFdoc.setPDFDocumentHeader();
		com.lowagie.text.Document doc = XMLPDFdoc.getPDFDoc();
		System.out.println("Page size: "+doc.getPageSize().width()+ ", "+doc.getPageSize().height());
		doc.addCreator(msgOffChk.getE01OFMCKN());
		
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;
		PdfContentByte cb = null;
		PdfPTable myTable = null;

		try {
			docWriter = PdfWriter.getInstance(doc, baosPDF);
			doc.open();
			
			if( req.getParameter("SCREEN").toString().equals("600") ){ //600 means Multiple Checks Printing option
				docWriter.addJavaScript("this.print(false);", false);
			}
			
			XMLPDFdoc.setPDFcb(docWriter, transList, user, msgOffChk);
		}
		catch (DocumentException dex) {
			baosPDF.reset();
			throw dex;
		}
		finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}
		if (baosPDF.size() < 1) {
			throw new DocumentException("document has " + baosPDF.size() + " bytes");
		}

		return baosPDF;
	}

/**
 * This method was created in VisualAge.
 */
protected void procActionPrint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	EWD0120DSMessage msgList = null;
	JBObjList beanList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Sending header");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0002");
	 	msgOffChk.setH01FLGWK1("P");
	 	
		msgOffChk.setE01OFMCKN(userPO.getIdentifier()); 
		msgOffChk.setE01OFMCCY(userPO.getCurrency());
	 	
		msgOffChk.send();	
	 	msgOffChk.destroy();
		flexLog("EOF011501 Message Sent");
	
	//Receive Error
	  	newmessage = mc.receiveMessage();
	  
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			ses.setAttribute("error", msgError);
			//showERROR(msgError);
			try {
				ses.setAttribute("offBasic", msgOffChk);
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else if (newmessage.getFormatName().equals("EOF011501")) { 
			
			msgOffChk = (EOF011501Message)newmessage;			
			flexLog("EOF011501 Message received");					
	  	    // Receive Transactions
	  	    
			beanList = new JBObjList();
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EWD0120DS")) {
				flexLog("EWD0120DS Message received");

				String marker = "";
				
				while (true) {

					msgList = (EWD0120DSMessage)newmessage;
					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						
						beanList.addRow(msgList);
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

				 	newmessage = mc.receiveMessage();
				 	
				}
				
		    }
		    
			DocumentException ex = null;
			ByteArrayOutputStream baosPDF = null;
				
			try {
				
				baosPDF = generatePDFDocumentBytes(req, user,msgOffChk,beanList, false);

				StringBuffer sbFilename = new StringBuffer();
				sbFilename.append(msgOffChk.getE01OFMCKN());
				sbFilename.append(System.currentTimeMillis());
				sbFilename.append(".pdf");

				res.setHeader("Cache-Control", "max-age=30");

				res.setContentType("application/pdf");

				StringBuffer sbContentDispValue = new StringBuffer();
				sbContentDispValue.append("inline");
				sbContentDispValue.append("; filename=");
				sbContentDispValue.append(sbFilename);

				res.setHeader("Content-disposition", sbContentDispValue.toString());

				res.setContentLength(baosPDF.size());

				ServletOutputStream sos;

				sos = res.getOutputStream();

				baosPDF.writeTo(sos);

				sos.flush();	
			}
			catch (DocumentException dex) {
				res.setContentType("text/html");
				PrintWriter writer = res.getWriter();
				writer.println(this.getClass().getName() + " caught an exception: " + dex.getClass().getName() + "<br>");
				writer.println("<pre>");
				dex.printStackTrace(writer);
				writer.println("</pre>");
			}
			finally {
				if (baosPDF != null) {
					baosPDF.reset();
				}
			}
			
			return;		
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
protected void procActionPending(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	EWD0120DSMessage msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Sending header");
	 	//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0002");
	 	msgOffChk.setH01FLGWK1("I");
		flexLog("Header has been sended");
		try {
		 	if (req.getParameter("E01OFMCKN") != null)
		 	  msgOffChk.setE01OFMCKN(req.getParameter("E01OFMCKN"));
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
		 	if (req.getParameter("E01OFMCCY") != null)
		 	   msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY").toUpperCase());
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCCY("");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();
	
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		ses.setAttribute("error", msgError);
		showERROR(msgError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) { 
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;
			// showESD008004(msgOffChk);

			userPO.setIdentifier(msgOffChk.getE01OFMCKN());
			userPO.setCurrency(msgOffChk.getE01OFMCCY());
			userPO.setHeader5(msgOffChk.getE01OFMEM1());
			userPO.setHeader6(msgOffChk.getE01OFMEM2());
			userPO.setHeader7(msgOffChk.getE01OFMEM3());
			userPO.setHeader8(msgOffChk.getE01OFMAMT());
			userPO.setHeader9(msgOffChk.getE01OFMBNF());
			userPO.setHeader10(msgOffChk.getE01OFMBN1());
			userPO.setHeader11(msgOffChk.getE01LETAMT());
			userPO.setHeader12(msgOffChk.getE01OFMCO1());
			userPO.setHeader13(msgOffChk.getE01OFMCO2());
			userPO.setHeader14(msgOffChk.getE01OFMCO3());
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());
			
		}
	  	else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
	
	   if(IsNotError){
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EWD0120DS")) {

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

				String strDebit = "";
				String strCredit = "";
				java.math.BigDecimal debit = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				

				while (true) {

					msgList = (EWD0120DSMessage)newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
							chk = "checked";
						}
						else {
							chk = "";
						}

						if(msgList.getE01WRKDCC().equals("D")){
							debit = debit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = Util.fcolorCCY(msgList.getE01WRKAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01WRKDCC().equals("C")){
							credit = credit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01WRKAMT());
						}
						
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBNK()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBRN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCY()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKGLN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKACC()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");										
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKTDS()) + "</TD>");					
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

				 	newmessage = mc.receiveMessage();
				 	
				}
				ses.setAttribute("ledList", beanList);
		    }
					
		}
	    	else
			flexLog("Message " + newmessage.getFormatName() + " received.");

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);


		if (IsNotError) {  // There are no errors
			try {
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_print.jsp");
				callPage(LangPath + "EOF0115_of_chk_print.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else {  // There are errors
			try {
				ses.setAttribute("offBasic", msgOffChk);
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);	
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

/**
 * This method was created in VisualAge.
 */
protected void procActionBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	try {
		
		procReqListLedger(mc,user,req,res,ses);
	
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
protected void procActionEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
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

	// Send Initial data
	try
	{
		flexLog("Sending header");
	 	//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0001");
		flexLog("Header has been sended");
		try {
		 	if (req.getParameter("E01OFMFTY") != null)
		 	  msgOffChk.setE01OFMFTY(req.getParameter("E01OFMFTY").trim());
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMFTY("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
		 	if (req.getParameter("E01OFMCCY") != null)
		 	   msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY"));
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCCY("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();
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
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
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

		if (newmessage.getFormatName().equals("EOF011501")) {
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;


			userPO.setIdentifier(msgOffChk.getE01OFMCKN());

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("offBasic", msgOffChk);

			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_new.jsp");
					callPage(LangPath + "EOF0115_of_chk_new.jsp", req, res);	
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
 * This method was created in VisualAge.
 */
protected void procActionEnterPrint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	EWD0120DSMessage msgList = null;
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
		flexLog("Sending header");
	 	//msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
	 	msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
	 	msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0002");
	 	msgOffChk.setH01FLGWK1("I");
		flexLog("Header has been sended");
		try {
		 	if (req.getParameter("E01OFMCKN") != null)
		 	  msgOffChk.setE01OFMCKN(req.getParameter("E01OFMCKN"));
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCKN("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}
		try {
		 	if (req.getParameter("E01OFMCCY") != null)
		 	   msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY").toUpperCase());
		 	  flexLog("Product Sent");
		}
		catch (Exception e)	{
	 	  msgOffChk.setE01OFMCCY("0");
		  flexLog(" error " + e);
		  flexLog(" Error Sent");
		}

		msgOffChk.send();	
	 	msgOffChk.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		ses.setAttribute("error", msgError);
		showERROR(msgError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	try	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) { 
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;
			// showESD008004(msgOffChk);

			userPO.setIdentifier(msgOffChk.getE01OFMCKN());
			userPO.setCurrency(msgOffChk.getE01OFMCCY());
			userPO.setHeader5(msgOffChk.getE01OFMEM1());
			userPO.setHeader6(msgOffChk.getE01OFMEM2());
			userPO.setHeader7(msgOffChk.getE01OFMEM3());
			userPO.setHeader8(msgOffChk.getE01OFMAMT());
			userPO.setHeader9(msgOffChk.getE01OFMBNF());
			userPO.setHeader10(msgOffChk.getE01OFMBN1());
			userPO.setHeader11(msgOffChk.getE01LETAMT());
			userPO.setHeader12(msgOffChk.getE01OFMCO1());
			userPO.setHeader13(msgOffChk.getE01OFMCO2());
			userPO.setHeader14(msgOffChk.getE01OFMCO3());
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());
			
		}
	  	else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
	}
	catch (Exception e) {
	}
	
	try
	{
	  if(IsNotError){
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EWD0120DS")) {

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

				String strDebit = "";
				String strCredit = "";
				
				java.math.BigDecimal debit  = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				

				while (true) {

					msgList = (EWD0120DSMessage)newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
							chk = "checked";
						}
						else {
							chk = "";
						}

						if(msgList.getE01WRKDCC().equals("D")){
							debit = debit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = Util.fcolorCCY(msgList.getE01WRKAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01WRKDCC().equals("C")){
							credit = credit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01WRKAMT());
						}
						
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBNK()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBRN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCY()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKGLN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKACC()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");										
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKTDS()) + "</TD>");					
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

				 	newmessage = mc.receiveMessage();
				 	
				}
				ses.setAttribute("ledList", beanList);
		    }
					
		}
	    	else
			flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_print.jsp");
					callPage(LangPath + "EOF0115_of_chk_print.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					ses.setAttribute("offBasic", msgOffChk);
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
					callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);	
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

/**
 * This method was created in VisualAge.
 */
protected void procReqEnterPrint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
		userPO.setOption("OCK");
		userPO.setPurpose("PRINTER");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
		callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
protected void procReqListHelp(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ETL051001Message msgSearch = null;
	ETL051001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ETL051001Message)mc.getMessageRecord("ETL051001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("ETL0510");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	msgSearch.setH01FLGWK1("P");
	 	
	 	
		try{
			try{
			 	posi= Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	posi=0;	
				flexLog("E01NUMREC");
			}

	
			try{
			 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	msgSearch.setE01NUMREC("0");	
				flexLog("E01NUMREC");
			}
			
		
			try{
			 	msgSearch.setE01SELDTY("1");
			}
			catch (Exception e){
			 	flexLog("E01SELDTY");
			}

			try{
			 	msgSearch.setE01SELSCH("D");
			}
			catch (Exception e){
			 	flexLog("E01SELSCH");
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
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
		else if (newmessage.getFormatName().equals("ETL051001")) {

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

			
			while (true) {

				msgList = (ETL051001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
						chk = "checked";
					}
					else {
						chk = "";
					}
					
					
					String showRef = "enter('" + msgList.getE01OFMNCH() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatDate(msgList.getE01OFMID1(),msgList.getE01OFMID2(),msgList.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBNF()) + "</A></TD>");
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
			ses.setAttribute("dvList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_help.jsp");
				 callPage(LangPath + "EOF0115_of_chk_help.jsp", req, res);
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

/**
 * This method was created in VisualAge.
 */
protected void procReqListLedger(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EOF011501Message msgOffChk = null;
	ELEERRMessage msgError = null;
	EWD0120DSMessage msgList = null;
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

	int posi = 0;
		
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
	 	msgOffChk = (EOF011501Message)ses.getAttribute("offBasic");
		//msgOffChk = (EOF011501Message)mc.getMessageRecord("EOF011501");
		msgOffChk.setH01USERID(user.getH01USR());
	 	msgOffChk.setH01PROGRM("EOF0115");
	 	msgOffChk.setH01TIMSYS(getTimeStamp());
	 	msgOffChk.setH01SCRCOD("01");
	 	msgOffChk.setH01OPECOD("0005");
		// all the fields here

		try{
			// all the fields here
		 	java.util.Enumeration enu = msgOffChk.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				}
				catch (Exception e) {
				}	
			}
		}
		catch (Exception e) {
		}

	 	mc.sendMessage(msgOffChk);	
	 	msgOffChk.destroy();
	}		
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		ses.setAttribute("error", msgError);
		showERROR(msgError);
		
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	try	{
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EOF011501")) { 
			try {
				msgOffChk = new datapro.eibs.beans.EOF011501Message();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgOffChk = (EOF011501Message)newmessage;
			// showESD008004(msgOffChk);

			userPO.setIdentifier(msgOffChk.getE01OFMCKN());
			userPO.setCurrency(msgOffChk.getE01OFMCCY());
			userPO.setHeader5(msgOffChk.getE01OFMEM1());
			userPO.setHeader6(msgOffChk.getE01OFMEM2());
			userPO.setHeader7(msgOffChk.getE01OFMEM3());
			userPO.setHeader8(msgOffChk.getE01OFMAMT());
			userPO.setHeader9(msgOffChk.getE01OFMBNF());
			userPO.setHeader10(msgOffChk.getE01OFMBN1());
			userPO.setHeader11(msgOffChk.getE01LETAMT());
			userPO.setHeader12(msgOffChk.getE01OFMCO1());
			userPO.setHeader13(msgOffChk.getE01OFMCO2());
			userPO.setHeader14(msgOffChk.getE01OFMCO3());
			userPO.setHeader19(msgOffChk.getE01OFMAPV());
			userPO.setHeader20(msgOffChk.getE01OFMBTH());
			userPO.setHeader21(user.getE01DTF());
			userPO.setHeader22(user.getE01LAN());
			
		}
	  	else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
	}
	catch (Exception e) {
	}
	
	try
	{
	  if(IsNotError){
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EWD0120DS")) {

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

				String strDebit = "";
				String strCredit = "";
				
				java.math.BigDecimal debit = new java.math.BigDecimal(0);
				java.math.BigDecimal credit = new java.math.BigDecimal(0);
				

				while (true) {

					msgList = (EWD0120DSMessage)newmessage;

					marker = msgList.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
							chk = "checked";
						}
						else {
							chk = "";
						}

						if(msgList.getE01WRKDCC().equals("D")){
							debit = debit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = Util.fcolorCCY(msgList.getE01WRKAMT());
							strCredit = "&nbsp;";
						}
						else if(msgList.getE01WRKDCC().equals("C")){
							credit = credit.add(msgList.getBigDecimalE01WRKAMT());
							strDebit = "&nbsp;";
							strCredit = Util.fcolorCCY(msgList.getE01WRKAMT());
						}
						
						
						myRow = new StringBuffer("<TR>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBNK()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKBRN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCY()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKGLN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKACC()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCCN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKCDE()) + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
						myRow.append("<TD NOWRAP ALIGN=RIGHT>" + strCredit + "</TD>");										
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE01WRKTDS()) + "</TD>");					
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
										
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}

				 	newmessage = mc.receiveMessage();
				 	
				}
				ses.setAttribute("ledList", beanList);
		    }
					
		}
	    	else
			flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);


			if (IsNotError) {  // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_cont.jsp");
					callPage(LangPath + "EOF0115_of_chk_cont.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else {  // There are errors
				try {
					ses.setAttribute("offBasic", msgOffChk);
					flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_basic.jsp");
					callPage(LangPath + "EOF0115_of_chk_basic.jsp", req, res);	
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

/**
 * This method was created in VisualAge.
 */
protected void procReqListPending(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ETL051001Message msgSearch = null;
	ETL051001Message msgList = null;
	JBList beanList = null;
	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int posi = 0;
	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgSearch = (ETL051001Message)mc.getMessageRecord("ETL051001");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("ETL0510");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0004");
	 	msgSearch.setH01FLGWK1("P");
	 	
		try{
			try{
			 	posi= Integer.parseInt(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	posi=0;	
				flexLog("E01NUMREC");
			}

	
			try{
			 	msgSearch.setE01NUMREC(req.getParameter("Pos"));
			}
			catch (Exception e){
			 	msgSearch.setE01NUMREC("0");	
				flexLog("E01NUMREC");
			}
			
		
			try{
			 	msgSearch.setE01SELDTY("1");
			}
			catch (Exception e){
			 	flexLog("E01SELDTY");
			}

			try{
			 	msgSearch.setE01SELSCH("D");
			}
			catch (Exception e){
			 	flexLog("E01SELSCH");
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
			
			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_enter_print.jsp");
				callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

	  	}
		else if (newmessage.getFormatName().equals("ETL051001")) {

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

			
			while (true) {

				msgList = (ETL051001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				}
				else {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
						chk = "checked";
					}
					else {
						chk = "";
					}
					
					
					String showRef = "enter('" + msgList.getE01OFMNCH() + "', '" + msgList.getE01OFMCCY() + "')";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP><input type=\"checkbox\" name=\"ACCNUM\" value=\"" + msgList.getE01OFMNCH()+ "_" + msgList.getE01OFMCCY() + "\" " + "></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMNCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMCCY()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBRN()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMMCH()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMSTS()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatDate(msgList.getE01OFMID1(),msgList.getE01OFMID2(),msgList.getE01OFMID3()) + "</A></TD>");
					myRow.append("<TD NOWRAP><A HREF=\"javascript:" + showRef + "\">" + Util.formatCell(msgList.getE01OFMBNF()) + "</A></TD>");
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
			ses.setAttribute("dvList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				 flexLog("About to call Page: " + LangPath + "EOF0115_of_chk_print_list.jsp");
				 callPage(LangPath + "EOF0115_of_chk_print_list.jsp", req, res);
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
/**
 * This method was created in VisualAge.
 */
protected void procReqPrint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	UserPos	userPO = null;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try {
		flexLog("Calling print report");
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=3&E01OFMCKN=" + userPO.getIdentifier() + "&E01OFMCCY="+ userPO.getCurrency());
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
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

		int screen = R_ENTER_PRINT;
		
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
				// BEGIN Off. Chk
				// Request
				case R_BASIC :
					break;	
				
				case R_LIST_HELP :
					procReqListHelp(mc, msgUser, req, res, session);
					break;	
				case R_LIST_LEDGER :
					procReqListLedger(mc, msgUser, req, res, session);
					break;
				case R_LIST_PRINT : 
					//procReqPrint(msgUser, req, res, session);
					procActionPrint(mc, msgUser, req, res, session);
					break;

						
				// Action
				case A_BASIC :
					procActionBasic(mc, msgUser, req, res, session);
					break;			
					
				
				// END Off. Check

				// BEGIN Entering
				// Request
				case R_LIST_PENDING : 
					procReqListPending(mc, msgUser, req, res, session);
					break;
				
				case R_ENTER_PRINT : 
					procReqEnterPrint(msgUser, req, res, session);
					break;
				
				// Action 
				case A_ENTER_PRINT : 
					procActionEnterPrint(mc, msgUser, req, res, session);
					break;	
				case A_LIST_PENDING : 
					procActionPending(mc, msgUser, req, res, session);
					break;	
					
				case R_MULTIPLE_PRINT :
					procReqMultiplePrint(mc, msgUser, req, res, session);
					break ;
					
				case A_MULTIPLE_PRINT :
					procActionMultiPrint(mc, msgUser, req, res, session);
					break ;
				// END Entering

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
          
	private void procReqMultiplePrint(MessageContext mc,
			ESS0030DSMessage msgUser, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {

		
		try {
			 String page = "EOF0115_multi_chk_print.jsp" ;
			 flexLog( "About to call Page: " + LangPath + page );
			 callPage( LangPath + page, req, res );
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
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


protected synchronized void procActionMultiPrint(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF011501Message msgOffChk = null;
		ELEERRMessage msgError = null;
		EWD0120DSMessage msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Sending header");
			msgOffChk = (EOF011501Message) mc.getMessageRecord("EOF011501");
			msgOffChk.setH01USERID(user.getH01USR());
			msgOffChk.setH01PROGRM("EOF0115");
			msgOffChk.setH01TIMSYS(getTimeStamp());
			msgOffChk.setH01SCRCOD("01");
			msgOffChk.setH01OPECOD("0002");
			msgOffChk.setH01FLGWK1("P");

			msgOffChk.setE01OFMCKN(req.getParameter("E01OFMCKN"));
			msgOffChk.setE01OFMCCY(req.getParameter("E01OFMCCY"));

			msgOffChk.send();
			msgOffChk.destroy();
			flexLog("EOF011501 Message Sent");

			//Receive Error
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
				//showERROR(msgError);
				try {
					ses.setAttribute("offBasic", msgOffChk);
					flexLog("About to call Page: " + LangPath
							+ "EOF0115_of_chk_enter_print.jsp");
					callPage(LangPath + "EOF0115_of_chk_enter_print.jsp", req,
							res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EOF011501")) {

				msgOffChk = (EOF011501Message) newmessage;
				flexLog("EOF011501 Message received");
				// Receive Transactions

				beanList = new JBObjList();

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EWD0120DS")) {
					flexLog("EWD0120DS Message received");

					String marker = "";

					while (true) {

						msgList = (EWD0120DSMessage) newmessage;
						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {

							beanList.addRow(msgList);

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();

					}

				}

				DocumentException ex = null;
				ByteArrayOutputStream baosPDF = null;

				try {

					baosPDF = generatePDFDocumentBytes(req, user, msgOffChk,
							beanList, false);

					StringBuffer sbFilename = new StringBuffer();
					sbFilename.append(msgOffChk.getE01OFMCKN());
					sbFilename.append(System.currentTimeMillis());
					sbFilename.append(".pdf");

					res.setHeader("Cache-Control", "max-age=30");

					res.setContentType("application/pdf");

					StringBuffer sbContentDispValue = new StringBuffer();
					sbContentDispValue.append("inline");
					sbContentDispValue.append("; filename=");
					sbContentDispValue.append(sbFilename);

					res.setHeader("Content-disposition", sbContentDispValue
							.toString());

					res.setContentLength(baosPDF.size());

					ServletOutputStream sos;

					sos = res.getOutputStream();

					baosPDF.writeTo(sos);

					sos.flush();

				} catch (DocumentException dex) {
					res.setContentType("text/html");
					PrintWriter writer = res.getWriter();
					writer.println(this.getClass().getName()
							+ " caught an exception: "
							+ dex.getClass().getName() + "<br>");
					writer.println("<pre>");
					dex.printStackTrace(writer);
					writer.println("</pre>");
				} catch (ExceptionConverter ec) {
					res.setContentType("text/html");
					PrintWriter writer = res.getWriter();
					writer.println(this.getClass().getName()
							+ " caught an exception: "
							+ ec.getClass().getName() + "<br>");
					writer.println("<pre>");
					ec.printStackTrace(writer);
					writer.println("</pre>");
				} finally {
					if (baosPDF != null) {
						baosPDF.reset();
					}
				}

				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}