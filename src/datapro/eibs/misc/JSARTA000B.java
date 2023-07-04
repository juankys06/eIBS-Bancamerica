package datapro.eibs.misc;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.datapro.generic.beanutil.BeanList;

import datapro.eibs.beans.ARTA00101Message;
import datapro.eibs.beans.ARTA00102Message;
import datapro.eibs.beans.ARTA00103Message;
import datapro.eibs.beans.ARTA00109Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBArtaFees;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import com.jspsmart.upload.SmartUpload;

public class JSARTA000B extends datapro.eibs.master.SuperServlet {

	private ServletConfig config = null;
	
	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_ENTER	 		= 200;
	
	// loan type
	protected static final int LT_COMMERCIAL    = 1;
	protected static final int LT_MORTGAGE		= 2;
	protected static final int LT_HOMEEQUITY	= 3;
		
	protected String LangPath = "S";
	protected String loanNumber = "";
	protected byte loanType = 0;
	
	
/**
 * JSECLI001 constructor comment.
 */
public JSARTA000B() {
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
	this.config = config;
}
/**
 * This method was created in VisualAge.
 */

protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ARTA00101Message msgArta01 = null;
	ARTA00102Message msgArta02 = null;
	ARTA00109Message msgArta09 = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	DocumentBuilderFactory docBuilderFactory = null;
	DocumentBuilder docBuilder = null;
	Document doc = null;
	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	//Read XML File
	try	{	
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		//doc = docBuilder.parse (new File(req.getParameter("FILENAME")));
		
		SmartUpload mySmartUpload = new SmartUpload();
		// upload file
		// Initialization
		mySmartUpload.initialize(config, req, res);
		// Upload
		mySmartUpload.upload();
		// Retreive the current file
	  	com.jspsmart.upload.File myFile =  mySmartUpload.getFiles().getFile(0);
		if (myFile.getSize() > 0) {
			byte[] bd = new byte[myFile.getSize()];
			for (int i = 0; i < myFile.getSize(); i++) {
				bd[i] = myFile.getBinaryData(i);
			}
			String st = new String(bd);
			doc = docBuilder.parse(new java.io.ByteArrayInputStream(st.getBytes()));
		}
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("XML reading Error");
	}
	
	// Send ARTA00101
	try {
		msgArta01 = new ARTA00101Message();
		loanType = GetLoanType(doc);
		if (loanType == LT_HOMEEQUITY) {
			msgArta01 = LoadArta01HE(doc, user);
		}
		else {
			msgArta01 = LoadArta01(doc, user);
		}
		mc.sendMessage(msgArta01);
		msgArta01.destroy();
		flexLog("ARTA00101 Message Sent to Sockets");
		
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	//	Receive Error Message
	try	 {
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
	 if (!IsNotError) {
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		try {
			flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
			callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
		}
		catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	else {
		// Send Arta00102
		try {
			IsNotError = ReadCustomers(mc, doc, user, req, res, ses);
		} catch (Exception e) {
			flexLog("Error: " + e);
		}
		if (IsNotError) {
			// Send Arta00103
			try {
				if (loanType == LT_COMMERCIAL) {
					IsNotError = ReadCommFees(mc, doc, user, req, res, ses);
				}
				else if (loanType == LT_MORTGAGE) {
					IsNotError = ReadMortFees(mc, doc, user, req, res, ses);
				}
				else if (loanType == LT_HOMEEQUITY) {
					IsNotError = ReadHomeEqFees(mc, doc, user, req, res, ses);
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
			}
			if (IsNotError) {
				//	Send ARTA00109
				 try {
					 msgArta09 = new ARTA00109Message();
					 msgArta09.setH09USERID(user.getH01USR());
					 msgArta09.setH09PROGRM("ARTA001");
					 msgArta09.setH09TIMSYS(getTimeStamp());
					 msgArta09.setH09SCRCOD("01");
					 msgArta09.setH09OPECOD("0005");
					 msgArta09.setE09DEAACC(loanNumber);		 
					 mc.sendMessage(msgArta09);
					 msgArta09.destroy();
					 flexLog("ARTA00109 Message Sent to Sockets");
				 } 
				 catch (Exception e) {
					 e.printStackTrace();
					 flexLog("Error: " + e);
					 throw new RuntimeException("Socket Communication Error");
				 }	
				//	Receive Error Message
				try	 {
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
				 if (!IsNotError) {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
						callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "ARTA0010_pr_confirm.jsp");
						callPage(LangPath + "ARTA0010_pr_confirm.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		}
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

		int screen = A_ENTER;
		
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

			switch (screen) {
				// BEGIN Entering

				// Action 
				case A_ENTER : 
					procActionEnter(mc, msgUser, req, res, session);
					break;
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


private ARTA00101Message LoadArta01(Document doc, ESS0030DSMessage user) {
	ARTA00101Message msgArt01 = new ARTA00101Message();
	String value = "";
	
	msgArt01.setH01USERID(user.getH01USR());
	msgArt01.setH01PROGRM("ARTA001");
	msgArt01.setH01TIMSYS(getTimeStamp());
	msgArt01.setH01SCRCOD("01");
	msgArt01.setH01OPECOD("0005");
	msgArt01.setE01DEAOVI("7");

	try {
		// Tipo Prestamo (mortgage o commercial)
		msgArt01.setE01DEALTY("" + loanType);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		// Producto
		value = GetProduct(doc);
		value = "" + value;
		msgArt01.setE01DEAPRO(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		// Loan Officer
		value = GetValue(doc, "12100", "", "", "Loan.LoanOfficer.FullName");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAOFI").getLength()) value = value.substring(0,msgArt01.getField("E01DEAOFI").getLength());
		msgArt01.setE01DEAOFI(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//Loan Processor
		value = GetValue(doc, "12100", "", "", "Loan.LoanProcessor.FullName");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPRU").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPRU").getLength());
		msgArt01.setE01DEAPRU(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		
	
	try {
		//Loan Number
		value = GetValue(doc, "12100", "", "", "Loan.Number");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAACC").getLength()) value = value.substring(0,msgArt01.getField("E01DEAACC").getLength());
		loanNumber = value;
		msgArt01.setE01DEAACC(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Repayment method
		String opt = "";
		value = GetValue(doc, "10000", "", "", "Loan.RepaymentMethod");
		value = "" + value;
		if (value.length() > 0) value = value.substring(0,3);
		if (value.equals("Ins")) opt = "1";
		else if (value.equals("Bal")) opt = "2";
		else if (value.equals("Sin")) opt = "3";
		else if (value.equals("Int")) opt = "4";
		else if (value.equals("Pri")) opt = "5";
		else if (value.equals("Two")) opt = "6";
		msgArt01.setE01DEARPM(opt);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Accrual method
		String opt = "";
		value = GetValue(doc, "12100", "", "", "Loan.Interest.Accrual.Basis");
		value = "" + value;
		if (value.equals("Actual/365")) opt = "1";
		else if (value.equals("Actual/360")) opt = "2";
		else if (value.equals("Actual/Actual")) opt = "3";
		else if (value.equals("Periodic")) opt = "4";
		msgArt01.setE01DEAACM(opt);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Amount requested
		value = GetValue(doc, "10100", "", "", "Payments.AmountRequested");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAOAM").getLength()) value = value.substring(0,msgArt01.getField("E01DEAOAM").getLength());
		msgArt01.setE01DEAOAM(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//payment regular amount		
		value = GetValue(doc, "12100", "", "", "Loan.Payment.Regular.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPAM").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPAM").getLength());
		msgArt01.setE01DEAPAM(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//Funding date
		String mm = "";
		String dd = "";
		String yy = "";
		value = GetValue(doc, "10100", "", "", "Loan.Funding.Date");
		value = "" + value;
		if (value.length() > 0) {
			mm = value.substring(0,2);
			dd = value.substring(2,4);
			yy = value.substring(4,8);
		}
		msgArt01.setE01DEAODM(mm);
		msgArt01.setE01DEAODD(dd);
		msgArt01.setE01DEAODY(yy);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//First payment date
		String mm = "";
		String dd = "";
		String yy = "";
		value = GetValue(doc, "10100", "", "", "Payments.FirstPaymentDate");
		value = "" + value;
		if (value.length() > 0) {
			mm = value.substring(0,2);
			dd = value.substring(2,4);
			yy = value.substring(4,8);
		}
		msgArt01.setE01DEAFDM(mm);
		msgArt01.setE01DEAFDD(dd);
		msgArt01.setE01DEAFDY(yy);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//Payment frequency
		String opt = "";
		value = GetValue(doc, "10100", "", "", "Payments.PaymentFrequency");
		value = "" + value;
		if (value.equals("52")) opt = "1";
		else if (value.equals("12")) opt = "5";
		/*else if (value.equals("-1")) opt = "2";
		lse if (value.equals("26")) opt = "3";
		else if (value.equals("24")) opt = "4";
		else if (value.equals("12")) opt = "5";
		else if (value.equals("4")) opt = "6";
		else if (value.equals("2")) opt = "7";
		else if (value.equals("1")) opt = "8";*/	
		msgArt01.setE01DEAFPG(opt);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//Number of Payments
		value = GetValue(doc, "10100", "", "", "Payments.NumberofPayments");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEANPG").getLength()) value = value.substring(0,msgArt01.getField("E01DEANPG").getLength());
		msgArt01.setE01DEANPG(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//Fixed Rate
		value = GetValue(doc, "10100", "", "", "Payments.FixedRate");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEARTE").getLength()) value = value.substring(0,msgArt01.getField("E01DEARTE").getLength());
		msgArt01.setE01DEARTE(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		

	try {
		//table Rate
		value = GetValue(doc, "10100", "", "", "Payments.RateType");
		value = "" + value.trim();
		if (value.length() > 0) value = value.substring(6,8);
		msgArt01.setE01DEAFTB(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	

	try {
		//variable Rate
		value = GetValue(doc, "10100", "", "", "Payments.ContractRate");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAFRT").getLength()) value = value.substring(0,msgArt01.getField("E01DEAFRT").getLength());
		msgArt01.setE01DEAFRT(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
			
	try {
		//Proceeds
		value = GetValue(doc, "10000", "", "", "Loan.Proceeds");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPCD").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPCD").getLength());
		msgArt01.setE01DEAPCD(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//Amount financed
		value = GetValue(doc, "10000", "", "", "Loan.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAAFI").getLength()) value = value.substring(0,msgArt01.getField("E01DEAAFI").getLength());
		msgArt01.setE01DEAAFI(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		
	
	try {
		//Total of payments
		value = GetValue(doc, "10000", "", "", "Loan.TotalOfPayments.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEATOP").getLength()) value = value.substring(0,msgArt01.getField("E01DEATOP").getLength());
		msgArt01.setE01DEATOP(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//APR
		value = GetValue(doc, "10000", "", "", "Loan.AnnualPercentage.Rate");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAAPR").getLength()) value = value.substring(0,msgArt01.getField("E01DEAAPR").getLength());
		msgArt01.setE01DEAAPR(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
		
	try {
		//Maturity date
		String mm = "";
		String dd = "";
		String yy = "";
		value = GetValue(doc, "10000", "", "", "Loan.Maturity.Date");
		value = "" + value;
		if (value.length() > 0) {
			mm = value.substring(0,2);
			dd = value.substring(2,4);
			yy = value.substring(4,8);
		}
		msgArt01.setE01DEAMDM(mm);
		msgArt01.setE01DEAMDD(dd);
		msgArt01.setE01DEAMDY(yy);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		
		
	try {
		//Total miscellaneous
		value = GetValue(doc, "10000", "", "", "Loan.TotalMiscFees");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEATMF").getLength()) value = value.substring(0,msgArt01.getField("E01DEATMF").getLength());
		msgArt01.setE01DEATMF(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//Prepaid fees
		value = GetValue(doc, "10000", "", "", "Loan.Paid.PrepaidFinance.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPFE").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPFE").getLength());
		msgArt01.setE01DEAPFE(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
				
	try {
		//Interest
		value = GetValue(doc, "10000", "", "", "Loan.Interest.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEATIN").getLength()) value = value.substring(0,msgArt01.getField("E01DEATIN").getLength());
		msgArt01.setE01DEATIN(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Total finance charge
		value = GetValue(doc, "10000", "", "", "Loan.FinanceCharge.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEATFC").getLength()) value = value.substring(0,msgArt01.getField("E01DEATFC").getLength());
		msgArt01.setE01DEATFC(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	return msgArt01;	
}

private ARTA00101Message LoadArta01HE(Document doc, ESS0030DSMessage user) {
	ARTA00101Message msgArt01 = new ARTA00101Message();
	String value = "";
	
	msgArt01.setH01USERID(user.getH01USR());
	msgArt01.setH01PROGRM("ARTA001");
	msgArt01.setH01TIMSYS(getTimeStamp());
	msgArt01.setH01SCRCOD("01");
	msgArt01.setH01OPECOD("0005");
	msgArt01.setE01DEAOVI("7");

	try {
		// Tipo Prestamo (mortgage o commercial)
		msgArt01.setE01DEALTY("" + loanType);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		// Producto
		value = GetProduct(doc);
		value = "" + value;
		msgArt01.setE01DEAPRO(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		// Loan Officer
		value = GetValue(doc, "12100", "", "", "Loan.LoanOfficer.FullName");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAOFI").getLength()) value = value.substring(0,msgArt01.getField("E01DEAOFI").getLength());
		msgArt01.setE01DEAOFI(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//Loan Processor
		value = GetValue(doc, "12100", "", "", "Loan.LoanProcessor.FullName");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPRU").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPRU").getLength());
		msgArt01.setE01DEAPRU(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		
	
	try {
		//Loan Number
		value = GetValue(doc, "12100", "", "", "Loan.Number");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAACC").getLength()) value = value.substring(0,msgArt01.getField("E01DEAACC").getLength());
		loanNumber = value;
		msgArt01.setE01DEAACC(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Rate Definition
		BeanList bltblRate = null;
		JBArtaFees bean = null;
		bltblRate = getTableRate();
		bltblRate.initRow();		
		value = GetValue(doc, "11000", "", "", "Index.SelectedIndex");
		value = "" + value;
		while (bltblRate.getNextRow()) {
			bean = (JBArtaFees) bltblRate.getRecord();
			if (value.indexOf(bean.getTagText()) > 0) {
				msgArt01.setE01DEAFTB(bean.getTagCode());
				break;
			}
		}
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Index Value		
		value = GetValue(doc, "11000", "", "", "Index.OpenEnd.Rate.IndexValue");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAFRT").getLength()) value = value.substring(0,msgArt01.getField("E01DEAFRT").getLength());
		msgArt01.setE01DEAFRT(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//if Initial rate not equal to market = selected 
		// send Interest rate
		//else send Margin
		 		
		value = GetValue(doc, "17100", "", "", "Loan.Rate.Index.InitialRate.NotEqualMarket.Yes");
		value = "" + value;
		if (value.equals("1")) {
			//Initial Interest rate	
			value = GetValue(doc, "17100", "", "", "Loan.FinanceCharge.InterestRate");
			value = "" + value;
			if (value.length() > msgArt01.getField("E01DEARTE").getLength()) value = value.substring(0,msgArt01.getField("E01DEARTE").getLength());
			msgArt01.setE01DEARTE(value);			
		} else{
			//Margin		
			value = GetValue(doc, "17104", "", "", "ARMHE.Rate.Margin");
			value = "" + value;
			if (value.length() > msgArt01.getField("E01DEARTE").getLength()) value = value.substring(0,msgArt01.getField("E01DEARTE").getLength());
			msgArt01.setE01DEARTE(value);			
		}
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}	
	
	try {
		//APR		
		value = GetValue(doc, "17100", "", "", "Loan.Interest.Variable.MarketRate.AllBalances.AnnualPercentage.Rate");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAAPR").getLength()) value = value.substring(0,msgArt01.getField("E01DEAAPR").getLength());
		msgArt01.setE01DEAAPR(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Initial Rate Period		
		value = GetValue(doc, "17104", "", "", "ARMHE.Rate.Index.InitialRate.Hold.Period");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAIRF").getLength()) value = value.substring(0,msgArt01.getField("E01DEAIRF").getLength());
		msgArt01.setE01DEAIRF(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//Initial Rate Hold		
		value = GetValue(doc, "17104", "", "", "ARMHE.Rate.Index.InitialRate.Hold.Term");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAIRH").getLength()) value = value.substring(0,msgArt01.getField("E01DEAIRH").getLength());
		msgArt01.setE01DEAIRH(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	
	try {
		//round rate	
		value = GetValue(doc, "17104", "", "", "ARMHE.Rate.Rounded.Rate");
		value = "" + value;
		String sRound = "";
		if (value.equals("1000")) { 		//.001
			sRound = "1";
		} else if (value.equals("100")) { 	//.01
			sRound = "2";
		} else if (value.equals("10")) {	//.1
			sRound = "3";
		} else if (value.equals("8")) {		//.125
			sRound = "4";	
		} else if (value.equals("4")) {		//.25
			sRound = "5";	
		} else if (value.equals("2")) {		//.5
			sRound = "6";
		} else if (value.equals("1")) {		//1
			sRound = "7";										
		}				
		msgArt01.setE01DLCRDT(sRound);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
		
	try {
		//Ceiling Margin		
		value = GetValue(doc, "17104", "", "", "ARMHE.Rate.Index.RateChange.CeilingMargin.Rate");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAMXR").getLength()) value = value.substring(0,msgArt01.getField("E01DEAMXR").getLength());
		msgArt01.setE01DEAMXR(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Credit Limit		
		value = GetValue(doc, "17100", "", "", "HE.Payments.CreditLimit.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAOAM").getLength()) value = value.substring(0,msgArt01.getField("E01DEAOAM").getLength());
		msgArt01.setE01DEAOAM(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Dollar Amount		
		value = GetValue(doc, "17300", "", "", "HEDraw.Dollar.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAPAM").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPAM").getLength());
		msgArt01.setE01DEAPAM(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//?		
		value = GetValue(doc, "17300", "", "", "HEDraw.PercentOutstandingPrincipalLastDayBillingCycle.Rate");
		value = "" + value;
		if (value.length() == 0) {
			value = GetValue(doc, "17300", "", "", "HEDraw.PercentOutstandingPrincipalLastDayAdvanceBillingCycle.Rate");
			value = "" + value;	
			if (value.length() == 0) {
				value = GetValue(doc, "17300", "", "", "HEDraw.InterestPlusPercentOutstandingPrincipalLastDayBillingCycle.Rate");
				value = "" + value;			
			}			
		}
		if (value.length() > msgArt01.getField("E01DEAPCT").getLength()) value = value.substring(0,msgArt01.getField("E01DEAPCT").getLength());
		msgArt01.setE01DEAPCT(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Initial Advance Amount		
		value = GetValue(doc, "17100", "", "", "Loan.Advance.InitialAdvance.Amount");
		value = "" + value;
		if (value.length() > msgArt01.getField("E01DEAAFI").getLength()) value = value.substring(0,msgArt01.getField("E01DEAAFI").getLength());
		msgArt01.setE01DEAAFI(value);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}

	try {
		//Maturity date
		String mm = "";
		String dd = "";
		String yy = "";
		value = GetValue(doc, "17100", "", "", "HE.Payments.Maturity.Date");
		value = "" + value;
		if (value.length() > 0) {
			mm = value.substring(0,2);
			dd = value.substring(2,4);
			yy = value.substring(4,8);
		}
		msgArt01.setE01DEAMDM(mm);
		msgArt01.setE01DEAMDD(dd);
		msgArt01.setE01DEAMDY(yy);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}		

	try {
		//Note date
		String mm = "";
		String dd = "";
		String yy = "";
		value = GetValue(doc, "12100", "", "", "Loan.Note.Date");
		value = "" + value;
		if (value.length() > 0) {
			mm = value.substring(0,2);
			dd = value.substring(2,4);
			yy = value.substring(4,8);
		}
		msgArt01.setE01DEAODM(mm);
		msgArt01.setE01DEAODD(dd);
		msgArt01.setE01DEAODY(yy);
	}
	catch (Exception e)	{
		flexLog("Input data error " + e);
	}
	return msgArt01;	
}

private boolean ReadCustomers(MessageContext mc, Document doc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {
	ARTA00102Message msgArt02 = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	

	boolean valueFound = false;
	int typCustomer;
	int typ;
	boolean IsNotError = false;
	
	NodeList listOfTables = doc.getElementsByTagName("Table");
	int totalTables = listOfTables.getLength();		
	for(int s=0;s<totalTables;s++) {
		if (valueFound) break;
		
		Node table = listOfTables.item(s);
		int length = (table.getAttributes() != null) ? table.getAttributes().getLength() : 0;
		Attr attrs[] = new Attr[length];
		
		for (int loopIndex = 0; loopIndex < length; loopIndex++) {
			attrs[loopIndex] = (Attr)table.getAttributes().item(loopIndex);
		}
			
		for (int loopIndex = 0; loopIndex < attrs.length; loopIndex++) {
			if (valueFound) break;
			Attr attr = attrs[loopIndex];
			String attName = attr.getNodeName();
			String attValue = attr.getNodeValue();

			if (attName.equals("ID") && attValue.equals("12000")) {
				valueFound = true;
				Element dataTable = (Element)table;
				NodeList listOfRecords = dataTable.getElementsByTagName("Record");
				int totalRecords = listOfRecords.getLength();
				int totalBorrowers = 0;
				int totalCoSigners = 0;
				for(int r=0;r<totalRecords;r++) {	
					Node record = listOfRecords.item(r);
					
					int reclength = (record.getAttributes() != null) ? record.getAttributes().getLength() : 0;
					Attr recAttrs[] = new Attr[reclength];

					 for (int index = 0; index < reclength; index++) {
						recAttrs[index] = (Attr)record.getAttributes().item(index);
					 }
					
					 typCustomer = 0;
					 typ = 0;
					 for (int index = 0; index < recAttrs.length; index++) {
						 Attr recAttr = recAttrs[index];
						 
						 String recAttName = recAttr.getNodeName();
						 String recAttValue = recAttr.getNodeValue();

						 if ((recAttName.equals("SubClass")) && ((recAttValue.equals("Business")) || (recAttValue.equals("Individual")))) {
							// Borrower
							typCustomer = 1;
							if (recAttValue.equals("Business")) typ = 1;
							else typ = 2;
							break;
						 }
						 else if ((recAttName.equals("SubClass")) && ((recAttValue.equals("BusinessCosigner")) || (recAttValue.equals("IndividualCosigner")))) {
							// Cosigner
							typCustomer = 2;
							if (recAttValue.equals("BusinessSigner")) typ = 1;
							else typ = 2;
							break;
						 }
					 }			
					 if ((typCustomer == 1) || (typCustomer == 2)) {
						Element recordItem = (Element)record;
						NodeList recordItemList = recordItem.getChildNodes();
						
						if (recordItemList != null) {	
							
							try {						
								msgArt02 = new ARTA00102Message();

								msgArt02.setH02USERID(user.getH01USR());
								msgArt02.setH02PROGRM("ARTA000");
								msgArt02.setH02TIMSYS(getTimeStamp());
								msgArt02.setH02SCRCOD("01");
								msgArt02.setH02OPECOD("0005");
								msgArt02.setE02DEAACC(loanNumber);	
							
								if (typCustomer == 1) {
									msgArt02.setE02RECTYP("1");
									totalBorrowers++;
									msgArt02.setE02NUMSEQ(totalBorrowers + "");
								}
								else {
									msgArt02.setE02RECTYP("1");
									totalCoSigners++;
									msgArt02.setE02NUMSEQ(totalCoSigners + "");  
								}
								msgArt02.setE02CUSLGT(typ + "");

								for (int recIndex = 0; recIndex < recordItemList.getLength(); recIndex++) {
									Node recordNode = recordItemList.item(recIndex);
	
									int nodetype = recordNode.getNodeType();
									String tagName = recordNode.getNodeName();
									String tagValue = "";		
									
									if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
										NodeList recChildNodes = recordNode.getChildNodes();
										
										if (recChildNodes != null) {
											Node recChildNode = recChildNodes.item(0);
											tagValue = recChildNode.getNodeValue();
	
											msgArt02 = GetFieldCustomer(msgArt02, tagName, tagValue, typ);
										}	
									}
								}
								mc.sendMessage(msgArt02);
							}	
							catch (Exception e) {
								e.printStackTrace();
								flexLog("Error: " + e);
								throw new RuntimeException("Socket Communication Error");
							}
							//	Receive Error Message
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
 							 if (!IsNotError) {
								flexLog("Putting java beans into the session");
								ses.setAttribute("error", msgError);	
								try {
									flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
									callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
								}
								catch (Exception e) {
									flexLog("Exception calling page " + e);
								}	
								return IsNotError;					
 							 }
						}
					 }
				}
			}
		}					
	}
	return IsNotError;
}

private ARTA00102Message GetFieldCustomer(ARTA00102Message msgArt02, String tagName, String tagValue, int typ){
	
	if (tagName.equals("Party.Name.LastOrBusiness")) {
		if (typ == 1) {try {msgArt02.setE02CUSNA1(tagValue);}  catch (Exception e) {} /*Entity Name*/}
		else {try {msgArt02.setE02CUSLN1(tagValue);} catch (Exception e) {} /*Last Name*/ }
	}	
	else if (tagName.equals("Party.Name.First")) {try {msgArt02.setE02CUSFNA(tagValue); } catch (Exception e) {} /*First Name*/}
	else if (tagName.equals("Party.Name.Middle")) {try {msgArt02.setE02CUSFN2(tagValue); } catch (Exception e) {} /*Middle Name*/}	
	else if (tagName.equals("Party.Address.Street")) {try {msgArt02.setE02CUSNA2(tagValue); } catch (Exception e) {} /*Address*/}
	else if (tagName.equals("Party.Address.City")) {try {msgArt02.setE02CUSCTY(tagValue); } catch (Exception e) {} /*City*/}
	else if (tagName.equals("Party.Address.State")) {try {msgArt02.setE02CUSSTE(tagValue); } catch (Exception e) {} /*State*/}
	else if (tagName.equals("Party.Address.Zip")) {try {msgArt02.setE02CUSZPC(tagValue); } catch (Exception e) {} /*Zip Code*/}
	else if (tagName.equals("Party.Address.County")) {try {msgArt02.setE02CUSCTR(tagValue); } catch (Exception e) {} /*County*/}
	else if (tagName.equals("Party.Phone")) {try {msgArt02.setE02CUSHPN(tagValue); } catch (Exception e) {} /*Telephone*/}
	else if (tagName.equals("Party.Home.Own")) {
		if (tagValue.equals("1")) {try {msgArt02.setE02CUSFL1("1"); } catch (Exception e) {} /*(Rent - Own)*/}
		else {try {msgArt02.setE02CUSFL1("2"); } catch (Exception e) {} /*(Rent - Own)*/}
	}
	else if (tagName.equals("Party.TaxId.Number")) {
		try {
			String SSN = replace(tagValue, "-", "");
			msgArt02.setE02CUSIDF(SSN); 
		} catch (Exception e) {} /*SSN*/}
	else if (tagName.equals("Party.DriversLicense.Number")) {try {msgArt02.setE02CUSIDN(tagValue); } catch (Exception e) {} /*Driver's License*/}
	else if (tagName.equals("Party.Id.Number")) {try {msgArt02.setE02CUSCUN(tagValue); } catch (Exception e) {} /*Customer #*/}
	else if (tagName.equals("Party.BirthDate")) {
		try {
			//Birth date
			String mm = "";
			String dd = "";
			String yy = "";
			if (tagValue.length() > 0) {
				mm = tagValue.substring(0,2);
				dd = tagValue.substring(2,4);
				yy = tagValue.substring(4,8);
			}
			msgArt02.setE02CUSBDM(mm);
			msgArt02.setE02CUSBDD(dd);
			msgArt02.setE02CUSBDY(yy);
		}
		catch (Exception e)	{} 
	}	
	else if (tagName.equals("Party.Organization.Type")) {try {msgArt02.setE02CUSCCL(tagValue); } catch (Exception e) {} /*Organization type*/}
	else if (tagName.equals("Party.NatureOfBusiness")) {try {msgArt02.setE02CUSBUC(tagValue); } catch (Exception e) {} /*Nature of Business*/}
	else if (tagName.equals("Party.LastFinancial.Date")) {
		try {
			//Date of last financial statement
			String mm = "";
			String dd = "";
			String yy = "";
			if (tagValue.length() > 0) {
				mm = tagValue.substring(0,2);
				dd = tagValue.substring(2,4);
				yy = tagValue.substring(4,8);
			}
			msgArt02.setE02CUSLDM(mm);
			msgArt02.setE02CUSLDD(dd);
			msgArt02.setE02CUSLDY(yy);
		}
		catch (Exception e)	{} 
	}	
	else if (tagName.equals("Party.LastAuthorization.Date")) {
		try {
			//Date of last authorization
			String mm = "";
			String dd = "";
			String yy = "";
			if (tagValue.length() > 0) {
				mm = tagValue.substring(0,2);
				dd = tagValue.substring(2,4);
				yy = tagValue.substring(4,8);
			}
			msgArt02.setE02CUSIDM(mm);
			msgArt02.setE02CUSIDD(dd);
			msgArt02.setE02CUSIDY(yy);
		}
		catch (Exception e)	{} 
	}	
	else if (tagName.equals("Party.Employer.Name")) {try {msgArt02.setE02CUSCP1(tagValue); } catch (Exception e) {} /*Employer Name*/}
	else if (tagName.equals("Party.Employer.Address.Street")) {try {msgArt02.setE02CUSCP2(tagValue); } catch (Exception e) {} /*Employer Address*/}
	else if (tagName.equals("Party.Employer.WorkPhone")) {try {msgArt02.setE02CUSPHN(tagValue); } catch (Exception e) {} /*Employer Work Telephone*/}
	else if (tagName.equals("Party.Employer.Title")) {try {msgArt02.setE02CUSCP3(tagValue); } catch (Exception e) {} /*Title/Position*/}
	else if (tagName.equals("Party.Employer.NumberOfYears")) {try {msgArt02.setE02CUSTIM(tagValue); } catch (Exception e) {} /*Number of Years*/}

	return msgArt02;
}

private boolean ReadCommFees(MessageContext mc, Document doc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {
	ARTA00103Message msgArt03 = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	
	BeanList blCommFees = null;
	JBArtaFees bean = null;
	String value = "";
	boolean IsNotError = true;
	boolean bsend = true;

	blCommFees = FillListCommFees();
	blCommFees.initRow();
	while (blCommFees.getNextRow()) {
		try {
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			bean = (JBArtaFees) blCommFees.getRecord();
			
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03NUMCOM(bean.getTagCode());} catch (Exception e) {}
			
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagText());
			value = "" + value;
			try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
			
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagType());
			value = "" + value;
			try { msgArt03.setE03PYMFRM(value);} catch (Exception e) {} 
	
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagAmount());
			value = "" + value;
			try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			
			if (!value.equals("")) {
				bsend = true;
				mc.sendMessage(msgArt03);
			}
			else {
				bsend = false;
			}
			
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		if (bsend) {
			//	Receive Error Message
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
			 if (!IsNotError) {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
					callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
				return IsNotError;					
			 }
		} 		
	}
	
	//	Other fees
	int numRecords = GetNumRecords(doc, "10005");
	int count = 5;
	String sCount = "";
	for(int i=0;i<numRecords;i++) {
		try {	
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			sCount = "0000" + count;
			try { msgArt03.setE03NUMCOM(sCount.substring(sCount.length() - 4, sCount.length()));} catch (Exception e) {}
			count++;
				
			value = GetValue(doc, "10005", "ID", "" + (i+1) , "OtherFee.Text");
			value = "" + value;
			try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
					
			value = GetValue(doc, "10005", "ID", "" + (i+1) , "OtherFee.Collected");
			value = "" + value;
			try { msgArt03.setE03PYMFRM(value);} catch (Exception e) {} 				
					
			value = GetValue(doc, "10005", "ID", "" + (i+1) , "OtherFee.Amount");
			value = "" + value;
			try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			
			if (!value.equals("")) {
				bsend = true;
				mc.sendMessage(msgArt03);
			}
			else {
				bsend = false;
			}		
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		if (bsend) {
			//	Receive Error Message
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
			 if (!IsNotError) {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
					callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
				return IsNotError;					
			 }	
		}								
	}	
	return IsNotError; 
}

private BeanList FillListCommFees() {
	BeanList blArta03 = new BeanList();
 	
 	//Public Officials
	JBArtaFees bean = new JBArtaFees();
	bean.setTableId("10003");
	bean.setRecordId("1");	
	bean.setTagCode("0001");
	bean.setTagText("MiscFee.Text");
	bean.setTagAmount("MiscFee.Amount");
	bean.setTagType("MiscFee.Collected");
	blArta03.addRow(bean);	
	bean = null;

	//Property insurance
	bean = new JBArtaFees();
	bean.setTableId("10003");
	bean.setRecordId("2");	
	bean.setTagCode("0002");
	bean.setTagText("MiscFee.Text");
	bean.setTagAmount("MiscFee.Amount");
	bean.setTagType("MiscFee.Collected");
	blArta03.addRow(bean);	
	bean = null;

	//(Vendors) Single Interest insurance
	bean = new JBArtaFees();
	bean.setTableId("10003");
	bean.setRecordId("3");	
	bean.setTagCode("0003");
	bean.setTagText("MiscFee.Text");
	bean.setTagAmount("MiscFee.Amount");
	bean.setTagType("MiscFee.Collected");
	blArta03.addRow(bean);	
	bean = null;

	//Loan fee
	bean = new JBArtaFees();
	bean.setTableId("10004");
	bean.setRecordId("1");	
	bean.setTagCode("0004");
	bean.setTagText("PrepaidFee.Text");
	bean.setTagAmount("PrepaidFee.Amount");
	bean.setTagType("PrepaidFee.Collected");
	blArta03.addRow(bean);	
	bean = null;		
		
	return blArta03;	
}
private BeanList getTableRate() {
	BeanList tblRate = new BeanList();
 	
	JBArtaFees bean = new JBArtaFees();
	bean.setTagCode("01");
	bean.setTagText("Great Florida Bank Prime");
	tblRate.addRow(bean);	
	bean = null;

	bean = new JBArtaFees();
	bean.setTagCode("02");
	bean.setTagText("Wall Street Journal Prime");
	tblRate.addRow(bean);	
	bean = null;
	
	bean = new JBArtaFees();
	bean.setTagCode("08");
	bean.setTagText("1 Year LIBOR");
	tblRate.addRow(bean);	
	bean = null;
	
	bean = new JBArtaFees();
	bean.setTagCode("14");
	bean.setTagText("3 Year Treasury Index");
	tblRate.addRow(bean);	
	bean = null;	
	
	bean = new JBArtaFees();
	bean.setTagCode("15");
	bean.setTagText("5 Year Treasury Index");
	tblRate.addRow(bean);	
	bean = null;	
	
	bean = new JBArtaFees();
	bean.setTagCode("16");
	bean.setTagText("10 Year Treasury Index");
	tblRate.addRow(bean);	
	bean = null;	
			
	return tblRate;	
}
private boolean ReadHomeEqFees(MessageContext mc, Document doc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {
	ARTA00103Message msgArt03 = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	
	BeanList blHomeEqFees = null;
	JBArtaFees bean = null;
	String value = "";
	boolean IsNotError = true;
	boolean bsend = true;

	blHomeEqFees = FillListHomeEqFees();
	blHomeEqFees.initRow();
	while (blHomeEqFees.getNextRow()) {
		try {
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			bean = (JBArtaFees) blHomeEqFees.getRecord();
			
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03NUMCOM(bean.getTagCode());} catch (Exception e) {}
			
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagText());
			value = "" + value;
			try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
			
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagType());
			value = "" + value;
			String sTyp = "";
			if (value.equals("Dollar amount")) {
				sTyp = "1";
			} else {
				sTyp = "2";
			}		
			try { msgArt03.setE03PYMFRM("1");} catch (Exception e) {} 
	
			value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagAmount());
			value = "" + value;
			try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			
			if (!value.equals("")) {
				bsend = true;
				mc.sendMessage(msgArt03);
			}
			else {
				bsend = false;
			}
			
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		if (bsend) {
			//	Receive Error Message
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
			 if (!IsNotError) {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
					callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
				return IsNotError;					
			 }
		} 		
	}
	
	//	Other fees
	int numRecords1 = GetNumRecords(doc, "17102");
	int count1 = 15;
	String sCount1 = "";
	for(int i=6;i<numRecords1;i++) {
		try {	
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			sCount1 = "10" + count1;
			try { msgArt03.setE03NUMCOM(sCount1);} catch (Exception e) {}
			count1++;
				
			value = GetValue(doc, "17102", "ID", "" + (i+1) , "HE.LenderFees.Text");
			value = "" + value;
			try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
					
			value = GetValue(doc, "17102", "ID", "" + (i+1) , "HE.LenderFees.Type");
			value = "" + value;
			String sTyp = "";
			if (value.equals("Dollar amount")) {
				sTyp = "1";
			} else {
				sTyp = "2";
			}				
			try { msgArt03.setE03PYMFRM(sTyp);} catch (Exception e) {} 				
					
			value = GetValue(doc, "17102", "ID", "" + (i+1) , "HE.LenderFees.Dollar.Amount");
			value = "" + value;
			try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			
			if (!value.equals("")) {
				bsend = true;
				mc.sendMessage(msgArt03);
			}
			else {
				bsend = false;
			}		
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		if (bsend) {
			//	Receive Error Message
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
			 if (!IsNotError) {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
					callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
				return IsNotError;					
			 }	
		}								
	}	

	//	Other fees
	int numRecords = GetNumRecords(doc, "17103");
	int count = count1 + 1;
	String sCount = "";
	for(int i=8;i<numRecords;i++) {
		try {	
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			sCount = "10" + count;
			try { msgArt03.setE03NUMCOM(sCount);} catch (Exception e) {}
			count++;
				
			value = GetValue(doc, "17103", "ID", "" + (i+1) , "HE.ThirdPartyFees.Text");
			value = "" + value;
			try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
					
			value = GetValue(doc, "17103", "ID", "" + (i+1) , "HE.ThirdPartyFees.Type");
			value = "" + value;
			String sTyp = "";
			if (value.equals("Dollar amount")) {
				sTyp = "1";
			} else {
				sTyp = "2";
			}				
			try { msgArt03.setE03PYMFRM(sTyp);} catch (Exception e) {} 				
					
			value = GetValue(doc, "17103", "ID", "" + (i+1) , "HE.ThirdPartyFees.Dollar.Amount");
			value = "" + value;
			try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			
			if (!value.equals("")) {
				bsend = true;
				mc.sendMessage(msgArt03);
			}
			else {
				bsend = false;
			}		
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		if (bsend) {
			//	Receive Error Message
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
			 if (!IsNotError) {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);	
				try {
					flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
					callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}	
				return IsNotError;					
			 }	
		}								
	}	
	return IsNotError; 
}

private BeanList FillListHomeEqFees() {
	BeanList blArta03 = new BeanList();
 	
	//Application
	JBArtaFees bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("1");	
	bean.setTagCode("1001");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//Points
	bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("2");	
	bean.setTagCode("1002");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//Maintenance
	bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("3");	
	bean.setTagCode("1003");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//Transanction
	bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("4");	
	bean.setTagCode("1004");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;		

	//
	bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("5");	
	bean.setTagCode("1005");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//
	bean = new JBArtaFees();
	bean.setTableId("17102");
	bean.setRecordId("6");	
	bean.setTagCode("1006");
	bean.setTagText("HE.LenderFees.Text");
	bean.setTagAmount("HE.LenderFees.Dollar.Amount");
	bean.setTagType("HE.LenderFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//Appraisal
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("1");	
	bean.setTagCode("1007");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;
	
	//Property survey
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("2");	
	bean.setTagCode("1008");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;

	//Credit Report
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("3");	
	bean.setTagCode("1009");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;	
	
	//Documentation
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("4");	
	bean.setTagCode("1010");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;	
	
	//Taxes
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("5");	
	bean.setTagCode("1011");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;	

	//Official
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("6");	
	bean.setTagCode("1012");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;
	
	//Title Search
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("7");	
	bean.setTagCode("1013");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;
	
	//Title insurance
	bean = new JBArtaFees();
	bean.setTableId("17103");
	bean.setRecordId("8");	
	bean.setTagCode("1014");
	bean.setTagText("HE.ThirdPartyFees.Text");
	bean.setTagAmount("HE.ThirdPartyFees.Dollar.Amountt");
	bean.setTagType("HE.ThirdPartyFees.Type");
	blArta03.addRow(bean);	
	bean = null;		
				
	return blArta03;	
}

private ARTA00103Message InitArta03(ARTA00103Message msgArt03, ESS0030DSMessage user) {

	msgArt03.setH03USERID(user.getH01USR());
	msgArt03.setH03PROGRM("ARTA000");
	msgArt03.setH03TIMSYS(getTimeStamp());
	msgArt03.setH03SCRCOD("01");
	msgArt03.setH03OPECOD("0005");
	return msgArt03;
}

private boolean ReadMortFees(MessageContext mc, Document doc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) {
	ARTA00103Message msgArt03 = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;	
	
	BeanList blCommFees = null;
	JBArtaFees bean = null;
	String value = "";
	boolean IsNotError = true;

	blCommFees = FillListMortFees();
	blCommFees.initRow();
	while (blCommFees.getNextRow()) {
		try {
			msgArt03 = new ARTA00103Message();
			msgArt03 = InitArta03(msgArt03, user);
			bean = (JBArtaFees) blCommFees.getRecord();
			
			try { msgArt03.setE03RECTYP("1");} catch (Exception e) {}
			try { msgArt03.setE03DEAACC(loanNumber);} catch (Exception e) {}
			try { msgArt03.setE03NUMCOM(bean.getTagCode());} catch (Exception e) {}
			
			if (!bean.getTagText().equals("")) {
				value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagText());
				value = "" + value;
				try { msgArt03.setE03DSCCOM(value);} catch (Exception e) {} 
			}
			if (!bean.getTagType().equals("")) {						
				value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagType());
				value = "" + value;
				try { msgArt03.setE03PYMFRM(value);} catch (Exception e) {} 
			}	
			if (!bean.getTagAmount().equals("")) {
				value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagAmount());
				value = "" + value.trim();
				if (value.equals("")) {
					if (!bean.getTagLender().equals("")) {
						value = GetValue(doc, bean.getTableId(), "ID", bean.getRecordId() , bean.getTagLender());
						value = "" + value;
					}
				}
				try { msgArt03.setE03COMAMT(value);} catch (Exception e) {} 
			}
			mc.sendMessage(msgArt03);
		}	
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		//	Receive Error Message
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
		 if (!IsNotError) {
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);	
			try {
				flexLog("About to call Page: " + LangPath + "ARTA0010_enter_file.jsp");
				callPage(LangPath + "ARTA0010_enter_file.jsp", req, res);	
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}	
			return IsNotError;					
		 }		
	}
	return IsNotError; 
}



private int GetNumRecords(Document doc, String tableID)
{
	boolean valueFound = false;
	int totalRecords = 0;
		
	NodeList listOfTables = doc.getElementsByTagName("Table");
	int totalTables = listOfTables.getLength();		
		
	for(int s=0;s<totalTables;s++) {				
		Node table = listOfTables.item(s);

		int length = (table.getAttributes() != null) ? table.getAttributes().getLength() : 0;
		Attr attrs[] = new Attr[length];

		 for (int loopIndex = 0; loopIndex < length; loopIndex++) {
			 attrs[loopIndex] = (Attr)table.getAttributes().item(loopIndex);
		 }

		 for (int loopIndex = 0; loopIndex < attrs.length; loopIndex++) {
			 Attr attr = attrs[loopIndex];
			 String attName = attr.getNodeName();
			 String attValue = attr.getNodeValue();

			 if (attName.equals("ID") && attValue.equals(tableID)) {
				Element dataTable = (Element)table;
				NodeList listOfRecords = dataTable.getElementsByTagName("Record");
				totalRecords = listOfRecords.getLength();
				return totalRecords;		
			 }
		 }
	}
	return totalRecords;
}

private String GetValue(Document doc, String tableID, String attributeName, String attributeValue, String nodeName)
{
	String value = "";
	boolean valueFound = false;
	boolean getItem = true;
	boolean getRecord = false;
		
	NodeList listOfTables = doc.getElementsByTagName("Table");
	int totalTables = listOfTables.getLength();		
		
	for(int s=0;s<totalTables;s++) {				
		if (valueFound) {
		   break;
		}
		Node table = listOfTables.item(s);

		int length = (table.getAttributes() != null) ? table.getAttributes().getLength() : 0;
		Attr attrs[] = new Attr[length];

		 for (int loopIndex = 0; loopIndex < length; loopIndex++) {
			 attrs[loopIndex] = (Attr)table.getAttributes().item(loopIndex);
		 }

		 for (int loopIndex = 0; loopIndex < attrs.length; loopIndex++) {
			 if (valueFound) {
				break;
			 }
			 Attr attr = attrs[loopIndex];
			 String attName = attr.getNodeName();
			 String attValue = attr.getNodeValue();

			 if (attName.equals("ID") && attValue.equals(tableID)) {
				Element dataTable = (Element)table;
				NodeList listOfRecords = dataTable.getElementsByTagName("Record");
				int totalRecords = listOfRecords.getLength();		

				for(int r=0;r<totalRecords;r++) {				
					Node record = listOfRecords.item(r);
					
					if (!attributeName.equals("")) {
						int reclength = (record.getAttributes() != null) ? record.getAttributes().getLength() : 0;
						Attr recAttrs[] = new Attr[reclength];

						 for (int index = 0; index < reclength; index++) {
							recAttrs[index] = (Attr)record.getAttributes().item(index);
						 }

						 for (int index = 0; index < recAttrs.length; index++) {
							 Attr recAttr = recAttrs[index];
							 
							 String recAttName = recAttr.getNodeName();
							 String recAttValue = recAttr.getNodeValue();

							 if ((recAttName.equals(attributeName)) && (recAttValue.equals(attributeValue))) {
								//getRecord = true;
								getItem = true;
								break;
							 } else {
								//getRecord = false;
								getItem = false;
							 }
						 }
					}
						
					if (getItem) {
						Element recordItem = (Element)record;
						NodeList recordItemList = recordItem.getElementsByTagName(nodeName);
					
						if (recordItemList != null) {
							Element firstElement = (Element)recordItemList.item(0);
							
							if (firstElement != null) {

								NodeList childNodes = firstElement.getChildNodes();
							
								if (childNodes != null) {
									try {
										value = childNodes.item(0).getNodeValue().trim();
									} 
									catch (Exception e) {value = "";}
									valueFound = true;
									break;
								}
							}
						}
					}
					if (getRecord) {
						Element recordItem = (Element)record;
						NodeList recordItemList = recordItem.getChildNodes();
							
						if (recordItemList != null) {
							for (int recIndex = 0; recIndex < recordItemList.getLength(); recIndex++) {
								Node recordNode = recordItemList.item(recIndex);
									
								int nodetype = recordNode.getNodeType();
								String tagName = recordNode.getNodeName();
								String tagValue = recordNode.getNodeValue();

								if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
									NodeList recChildNodes = recordNode.getChildNodes();

									if (recChildNodes != null) {
										for (int Index = 0; Index < recChildNodes.getLength(); Index++ ) {
											Node recChildNode = recChildNodes.item(Index);

											tagValue = recChildNode.getNodeValue();
										}

									}
								}
							}
						}
					}
				}
			 }
		 }
	}
	return value;
}

private byte GetLoanType(Document doc)
{
	NodeList listOfLoans = doc.getElementsByTagName("Loan");
	if (listOfLoans.getLength() > 0) {
		Node loan = listOfLoans.item(0);
		
		int length = (loan.getAttributes() != null) ? loan.getAttributes().getLength() : 0;
		Attr attrs[] = new Attr[length];
		
		for (int loopIndex = 0; loopIndex < length; loopIndex++) {
			attrs[loopIndex] = (Attr)loan.getAttributes().item(loopIndex);
		}

		for (int loopIndex = 0; loopIndex < attrs.length; loopIndex++) {
			Attr attr = attrs[loopIndex];
			String attName = attr.getNodeName();
			String attValue = attr.getNodeValue();
			
			if (attName.equals("LoanName")) {
				if (attValue.indexOf("Residential real estate") > 0) {
					return LT_MORTGAGE;
				}
				else if (attValue.indexOf("Open end") > 0){
					return LT_HOMEEQUITY;
				} else {	
					return LT_COMMERCIAL;
				}
			}
		}	
	}			
	return 0;
}

private String GetProduct(Document doc)
{
	NodeList listOfLoans = doc.getElementsByTagName("Loan");
	if (listOfLoans.getLength() > 0) {
		Node loan = listOfLoans.item(0);
		
		int length = (loan.getAttributes() != null) ? loan.getAttributes().getLength() : 0;
		Attr attrs[] = new Attr[length];
		
		for (int loopIndex = 0; loopIndex < length; loopIndex++) {
			attrs[loopIndex] = (Attr)loan.getAttributes().item(loopIndex);
		}

		for (int loopIndex = 0; loopIndex < attrs.length; loopIndex++) {
			Attr attr = attrs[loopIndex];
			String attName = attr.getNodeName();
			String attValue = attr.getNodeValue();
			
			if (attName.equals("TemplateName")) {		
				if ((attValue.length() > 0) && (attValue.length() > 13)) {
					if (attValue.substring(1,11).equals("apptemplate")){
						return attValue.substring(12,16);
					} else {
						return attValue.substring(9,13);
					}
					
				}
			}
		}	
	}			
	return "";
}

private BeanList FillListMortFees() {
	BeanList blArta03 = new BeanList();
	 	
	//703
	JBArtaFees bean = new JBArtaFees();
	bean.setTableId("10006");
	bean.setRecordId("1");	
	//bean.setTagCode("70300");
	bean.setTagCode("0070");
	bean.setTagText("RealEstate.CommissionPaid.Text");
	bean.setTagAmount("RealEstate.CommissionPaid.BorrowerAmount");
	bean.setTagType("RealEstate.CommissionPaid.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.CommissionPaid.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//704
	bean = new JBArtaFees();
	bean.setTableId("10006");
	bean.setRecordId("2");	
	//bean.setTagCode("70400");
	bean.setTagCode("0070");
	bean.setTagText("RealEstate.CommissionPaid.Text");
	bean.setTagAmount("RealEstate.CommissionPaid.BorrowerAmount");
	bean.setTagType("RealEstate.CommissionPaid.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.CommissionPaid.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//801 -1
	bean = new JBArtaFees();
	bean.setTableId("10007");
	bean.setRecordId("1");	
	//bean.setTagCode("80101");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItemLoan.Text");
	bean.setTagAmount("RealEstate.PayableItemLoan.CalculatedAmount");
	bean.setTagType("RealEstate.PayableItemLoan.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;	

	//801 - 2
	bean = new JBArtaFees();
	bean.setTableId("10007");
	bean.setRecordId("2");	
	//bean.setTagCode("80102");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItemLoan.Text");
	bean.setTagAmount("RealEstate.PayableItemLoan.CalculatedAmount");
	bean.setTagType("RealEstate.PayableItemLoan.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;
	
	//802 -1
	bean = new JBArtaFees();
	bean.setTableId("10007");
	bean.setRecordId("3");	
	//bean.setTagCode("80201");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItemLoan.Text");
	bean.setTagAmount("RealEstate.PayableItemLoan.CalculatedAmount");
	bean.setTagType("RealEstate.PayableItemLoan.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;	

	//802 - 2
	bean = new JBArtaFees();
	bean.setTableId("10007");
	bean.setRecordId("4");	
	//bean.setTagCode("80202");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItemLoan.Text");
	bean.setTagAmount("RealEstate.PayableItemLoan.CalculatedAmount");
	bean.setTagType("RealEstate.PayableItemLoan.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;	
	
	//803
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("1");	
	//bean.setTagCode("80300");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.Appraisal.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;		
	
	//804
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("2");	
	//bean.setTagCode("80400");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.CreditReporting.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;	
	
	//805
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("3");	
	//bean.setTagCode("80500");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.Inspection.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;	
	
	//806
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("4");	
	//bean.setTagCode("80600");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.MortgageInsuranceApplication.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;		
	
	//807
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("5");	
	//bean.setTagCode("80700");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.Assumption.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;	
	
	//8** Mortgage broker fee
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("6");	
	//bean.setTagCode("80800");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.MortgageBroker.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;		
	
	//8** CLO Access fee
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("7");	
	//bean.setTagCode("80900");
	bean.setTagCode("0080");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;		
	
	//8** tax related service fee
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("8");	
	//bean.setTagCode("81000");
	bean.setTagCode("0081");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other2.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;	

	//8** Flood determination
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("9");	
	//bean.setTagCode("81100");
	bean.setTagCode("0081");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other3.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//8** Flood monitoring
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("10");	
	//bean.setTagCode("81200");
	bean.setTagCode("0081");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other4.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//8** 
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("11");	
	//bean.setTagCode("81300");
	bean.setTagCode("0081");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other5.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//8** 
	bean = new JBArtaFees();
	bean.setTableId("10008");
	bean.setRecordId("12");	
	//bean.setTagCode("81400");
	bean.setTagCode("0081");
	bean.setTagText("RealEstate.PayableItem.Text");
	bean.setTagAmount("RealEstate.Fee.EightHundreds.Other6.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.PayableItem.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.PayableItem.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//902 
	bean = new JBArtaFees();
	bean.setTableId("10009");
	bean.setRecordId("1");	
	//bean.setTagCode("90200");
	bean.setTagCode("0090");
	bean.setTagText("RealEstate.ToBePaidInAdvance.Text");
	bean.setTagAmount("RealEstate.ToBePaidInAdvance.BorrowerAmount");
	bean.setTagType("RealEstate.ToBePaidInAdvance.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.ToBePaidInAdvance.LenderAmount"); 
	blArta03.addRow(bean);	
	bean = null;

	//903 
	bean = new JBArtaFees();
	bean.setTableId("10009");
	bean.setRecordId("2");	
	//bean.setTagCode("90300");
	bean.setTagCode("0090");
	bean.setTagText("RealEstate.ToBePaidInAdvance.Text");
	bean.setTagAmount("RealEstate.Fee.HazardInsurance.PaidByBorrower.Amount");
	bean.setTagType("RealEstate.ToBePaidInAdvance.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.ToBePaidInAdvance.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//904 
	bean = new JBArtaFees();
	bean.setTableId("10009");
	bean.setRecordId("3");	
	//bean.setTagCode("90400");
	bean.setTagCode("0090");
	bean.setTagText("RealEstate.Fee.NineHundreds.Other.FeeName");
	bean.setTagAmount("RealEstate.ToBePaidInAdvance.BorrowerAmount");
	bean.setTagType("RealEstate.ToBePaidInAdvance.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.ToBePaidInAdvance.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//905 
	bean = new JBArtaFees();
	bean.setTableId("10009");
	bean.setRecordId("4");	
	//bean.setTagCode("90500");
	bean.setTagCode("0090");
	bean.setTagText("RealEstate.Fee.NineHundreds.Other2.FeeName");
	bean.setTagAmount("RealEstate.ToBePaidInAdvance.BorrowerAmount");
	bean.setTagType("RealEstate.ToBePaidInAdvance.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.ToBePaidInAdvance.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//906 
	bean = new JBArtaFees();
	bean.setTableId("10009");
	bean.setRecordId("5");	
	//bean.setTagCode("90600");
	bean.setTagCode("0090");
	bean.setTagText("RealEstate.Fee.NineHundreds.Other3.FeeName");
	bean.setTagAmount("RealEstate.ToBePaidInAdvance.BorrowerAmount");
	bean.setTagType("RealEstate.ToBePaidInAdvance.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.ToBePaidInAdvance.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1001 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("1");	
	//bean.setTagCode("10010");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Hazard.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1002 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("2");	
	//bean.setTagCode("10020");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Mortgage.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1003 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("3");	
	//bean.setTagCode("10030");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Taxes.City.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1004 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("4");	
	//bean.setTagCode("10040");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Taxes.County.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1005 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("5");	
	//bean.setTagCode("10050");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Assessment.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1006 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("6");	
	//bean.setTagCode("10060");
	bean.setTagCode("0010");
	bean.setTagText("COE_HE.RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.OneThousands.Other.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1007 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("7");	
	//bean.setTagCode("10070");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Reserves.Text");
	bean.setTagAmount("RealEstate.Fee.OneThousands.Other2.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1008 
	bean = new JBArtaFees();
	bean.setTableId("10010");
	bean.setRecordId("8");	
	//bean.setTagCode("10080");
	bean.setTagCode("0010");
	bean.setTagText("RealEstate.Fee.AggregateAdjustment.Text");
	bean.setTagAmount("RealEstate.Fee.Reserves.Hazard.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1101 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("1");	
	//bean.setTagCode("11010");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Title.BorrowerAmount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1102 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("2");	
	//bean.setTagCode("11020");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.TitleSearch.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1103 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("3");	
	//bean.setTagCode("11030");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.TitleExamination.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1104 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("4");	
	//bean.setTagCode("11040");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.TitleInsuranceBinder.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1105 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("5");	
	//bean.setTagCode("11050");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.DocumentPreparation.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1106 
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("6");	
	//bean.setTagCode("11060");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.Notary.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1107  
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("7");	
	//bean.setTagCode("11070");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.Attorney.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1108  
	bean = new JBArtaFees();
	bean.setTableId("10011");
	bean.setRecordId("8");	
	//bean.setTagCode("11080");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title.Text");
	bean.setTagAmount("RealEstate.Fee.Closing.Amount");
	bean.setTagType("RealEstate.Title.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1109  
	bean = new JBArtaFees();
	bean.setTableId("10022");
	bean.setRecordId("1");	
	//bean.setTagCode("11090");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title2.Text");
	bean.setTagAmount("RealEstate.Fee.LenderCoverage.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1110  
	bean = new JBArtaFees();
	bean.setTableId("10022");
	bean.setRecordId("2");	
	//bean.setTagCode("11100");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Title2.Text");
	bean.setTagAmount("RealEstate.Fee.LenderCoverage.Amount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1111  
	bean = new JBArtaFees();
	bean.setTableId("10023");
	bean.setRecordId("1");	
	//bean.setTagCode("11110");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Fee.ElevenHundreds.Other.Text");
	bean.setTagAmount("RealEstate.Title3.BorrowerAmount");
	bean.setTagType("RealEstate.Title3.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1112  
	bean = new JBArtaFees();
	bean.setTableId("10023");
	bean.setRecordId("2");	
	//bean.setTagCode("11120");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Fee.ElevenHundreds.Other2.Textt");
	bean.setTagAmount("RealEstate.Title3.BorrowerAmount");
	bean.setTagType("RealEstate.Title3.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1113  
	bean = new JBArtaFees();
	bean.setTableId("10023");
	bean.setRecordId("3");	
	//bean.setTagCode("11130");
	bean.setTagCode("0011");
	bean.setTagText("RealEstate.Fee.ElevenHundreds.Other.Text");
	bean.setTagAmount("RealEstate.Title3.BorrowerAmount");
	bean.setTagType("RealEstate.Title3.BorrowerPaymentMethod");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1201 - 1
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("2");	
	//bean.setTagCode("12011");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1201 - 2
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("3");	
	//bean.setTagCode("12012");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1201 - 3
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("4");	
	//bean.setTagCode("12013");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1202 - 1
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("6");	
	//bean.setTagCode("12021");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1202 - 2
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("7");	
	//bean.setTagCode("12022");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1203 - 1
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("9");	
	//bean.setTagCode("12031");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1203 - 2
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("10");	
	//bean.setTagCode("12032");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1204
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("11");	
	//bean.setTagCode("12040");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1205
	bean = new JBArtaFees();
	bean.setTableId("10012");
	bean.setRecordId("12");	
	//bean.setTagCode("12040");
	bean.setTagCode("0012");
	bean.setTagText("RealEstate.RecordingTransfer.Text");
	bean.setTagAmount("RealEstate.RecordingTransfer.BorrowerAmount");
	bean.setTagType("RealEstate.RecordingTransfer.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.RecordingTransfer.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1301
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("1");	
	//bean.setTagCode("13010");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.AdditionalCharges.Text");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1302
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("2");	
	//bean.setTagCode("13020");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.AdditionalCharges.Text");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	
	//13** Arch / Eng. Services
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("3");	
	//bean.setTagCode("13030");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.Fee.ThirteenHundreds.Other.FeeName");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//13** Building permit
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("4");	
	//bean.setTagCode("13040");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.Fee.ThirteenHundreds.Other2.FeeName");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//13** 
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("5");	
	//bean.setTagCode("13050");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.Fee.ThirteenHundreds.Other3.Text");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//13** 
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("6");	
	//bean.setTagCode("13060");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.Fee.ThirteenHundreds.Other4.Text");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//13** 
	bean = new JBArtaFees();
	bean.setTableId("10013");
	bean.setRecordId("7");	
	//bean.setTagCode("13070");
	bean.setTagCode("0013");
	bean.setTagText("RealEstate.AdditionalCharges.Text");
	bean.setTagAmount("RealEstate.AdditionalCharges.BorrowerAmount");
	bean.setTagType("RealEstate.AdditionalCharges.BorrowerPaymentMethod");
	bean.setTagLender("RealEstate.AdditionalCharges.LenderAmount");
	blArta03.addRow(bean);	
	bean = null;

	//1400  - 1 
	bean = new JBArtaFees();
	bean.setTableId("10014");
	bean.setRecordId("1");	
	//bean.setTagCode("14001");
	bean.setTagCode("0014");
	bean.setTagText("RealEstate.Total.Text");
	bean.setTagAmount("RealEstate.Total.BorrowerAmount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	//1400  - 2 
	bean = new JBArtaFees();
	bean.setTableId("10014");
	bean.setRecordId("2");	
	//bean.setTagCode("14002");
	bean.setTagCode("0014");
	bean.setTagText("RealEstate.Total.Text");
	bean.setTagAmount("RealEstate.Total.BorrowerAmount");
	bean.setTagType("");
	bean.setTagLender("");
	blArta03.addRow(bean);	
	bean = null;

	return blArta03;	
}

public static String replace(String Text, String Old, String New){
		if (Old.length() == 0) return Text;
		StringBuffer buf = new StringBuffer();
		int i=0, j=0;
		while((i = Text.indexOf(Old, j)) > -1){
			buf.append(Text.substring(j,i) + New);
			j = i + Old.length();
		}
		if (j < Text.length())
			buf.append(Text.substring(j));
		return buf.toString();
}
}