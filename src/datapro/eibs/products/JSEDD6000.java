package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (10/13/04 11:40:03 AM)
 * @author: Antonio Blanco
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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import datapro.eibs.beans.EDD600001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.tools.pdf.XMLPDFDocument;

public class JSEDD6000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_FILT 			= 100;
	protected static final int A_PROC 			= 200;
	protected static final int A_PRINT 			= 300;
	protected static final int A_SND_PRINT_PDF	= 400;
	protected static final int A_PRINT_PDF 		= 500;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD6000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD6000");

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
	protected void procReqFilter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		
		EDD600001Message msgSearch = new EDD600001Message();
		UserPos userPO = new UserPos();
		
		//ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "EDD6000_savings_printer_enter_inq.jsp");
			callPage(LangPath + "EDD6000_savings_printer_enter_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procSndInf(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD600001Message msgSearch = null;
		EDD600001Message msgMT = new EDD600001Message();
		
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD600001Message) mc.getMessageRecord("EDD600001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD6000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0003");

			//all the fields here
			
			try {  
				msgSearch.setE01CUSACC(req.getParameter("E01CUSACC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSACC(userPO.getHeader10());
			}
			
			userPO.setHeader10(msgSearch.getE01CUSACC());

			try { 
				msgSearch.setE01CUSF01(req.getParameter("E01CUSF01").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSF01(userPO.getHeader11());
			}
			
			userPO.setHeader11(msgSearch.getE01CUSF01());
			
			try { 
				msgSearch.setE01CUSPBN(req.getParameter("E01CUSPBN").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSPBN(userPO.getHeader11());
			}
			
			userPO.setHeader11(msgSearch.getE01CUSPBN());

			try { 
				msgSearch.setE01CUSMOT(req.getParameter("E01CUSMOT").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSMOT(userPO.getHeader11());
			}
			
			userPO.setHeader11(msgSearch.getE01CUSMOT());

			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD600001 Message Sent");
		
			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
		   
			if (newmessage.getFormatName().equals("EDD600001")) {
				
				msgMT = (EDD600001Message) newmessage;
				System.out.println(msgMT.toString());
  				ses.setAttribute("msgMT", msgMT);
		   
				if (IsNotError) {
					try {
						flexLog("About to call Page: " + LangPath + "EDD6000_savings_printer_detail.jsp");
						callPage(LangPath + "EDD6000_savings_printer_detail.jsp", req, res);						
				
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);				
					try {
						flexLog("About to call Page: " + LangPath + "EDD6000_savings_printer_enter_inq.jsp");
						callPage(LangPath + "EDD6000_savings_printer_enter_inq.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
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
	protected void procSndPrt(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD600001Message msgSearch = null;
		boolean IsNotError = false;
		
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD600001Message) mc.getMessageRecord("EDD600001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD6000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0010");

			//all the fields here
			try {
				msgSearch.setE01CUSACC(userPO.getHeader10()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSNA1(userPO.getHeader11()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSNA2(userPO.getHeader12()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSNA3(userPO.getHeader13()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSNA4(userPO.getHeader14()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSIDN(userPO.getHeader15()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSTI1(userPO.getHeader16()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSTI2(userPO.getHeader17()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSTI3(userPO.getHeader18()); 
			} catch (Exception e) {
			}
			
			try {
				msgSearch.setE01CUSTI4(userPO.getHeader19()); 
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD600001 Message Sent");
			
			//Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD6000_savings_printer_enter_inq.jsp");
					callPage(LangPath + "EDD6000_savings_printer_enter_inq.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
			else
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
	protected void procSndPrtPdf(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD600001Message msgSearch = null;
		EDD600001Message msgMT = new EDD600001Message();
	
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD600001Message) mc.getMessageRecord("EDD600001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD6000");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0003");

			//all the fields here
		
			try { 
				msgSearch.setE01CUSACC(req.getParameter("E01CUSACC").toUpperCase());
			} catch (Exception e) {
				msgSearch.setE01CUSACC(userPO.getHeader10());
			}
			
			userPO.setHeader10(msgSearch.getE01CUSACC());
	
			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD600001 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
				newmessage = mc.receiveMessage();
	   
				if (newmessage.getFormatName().equals("EDD600001")) {
			
					msgMT = (EDD600001Message) newmessage;
					ses.setAttribute("msgMT", msgMT);
	   
					if (IsNotError) {
						DocumentException ex = null;
						ByteArrayOutputStream baosPDF = null;
			
						try {
	
							baosPDF = generatePDFDocumentBytes(req, user, msgMT, beanList, false);

							StringBuffer sbFilename = new StringBuffer();
							sbFilename.append(msgMT.getE01CUSACC());
							sbFilename.append(System.currentTimeMillis());
							sbFilename.append(".pdf");

							StringBuffer sbContentDispValue = new StringBuffer();
							sbContentDispValue.append("inline");
							sbContentDispValue.append("; filename=");
							sbContentDispValue.append(sbFilename);
							
							res.setHeader("Content-disposition", sbContentDispValue.toString());
							res.setHeader("Cache-Control", "max-age=30");
							res.setContentType("application/pdf");
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
					else {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);				
						try {
							flexLog("About to call Page: " + LangPath + "EDD6000_savings_printer_enter_inq.jsp");
							callPage(LangPath + "EDD6000_savings_printer_enter_inq.jsp", req, res);						
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}
				else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
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
	protected void procActionPrtPdf(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws ServletException, IOException {

		EDD600001Message msgMT = new EDD600001Message();
		JBObjList beanList = null;
      
		// all the fields here
		java.util.Enumeration enu = msgMT.fieldEnumeration();
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
	    System.out.println(msgMT.toString());
		DocumentException ex = null;
		ByteArrayOutputStream baosPDF = null;
		
		try {
			baosPDF = generatePDFDocumentBytes(req, user, msgMT, beanList, false);

			StringBuffer sbFilename = new StringBuffer();
			sbFilename.append(msgMT.getE01CUSACC());
			sbFilename.append(System.currentTimeMillis());
			sbFilename.append(".pdf");

			StringBuffer sbContentDispValue = new StringBuffer();
			sbContentDispValue.append("inline");
			sbContentDispValue.append("; filename=");
			sbContentDispValue.append(sbFilename);
							
			res.setHeader("Content-disposition", sbContentDispValue.toString());
			res.setHeader("Cache-Control", "max-age=30");
			res.setContentType("application/pdf");
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
	
	protected ByteArrayOutputStream generatePDFDocumentBytes(
			HttpServletRequest req,
			ESS0030DSMessage user,
			EDD600001Message msgMT,
			JBObjList transList,
			boolean FLG)
			throws DocumentException, IOException {

			XMLPDFDocument XMLPDFdoc = null;
			
			try {
				XMLPDFdoc = new XMLPDFDocument();
				XMLPDFdoc.readXMLDoc("printformat.libreta.xml"); 
			} catch (IOException ex){
				System.err.println(ex.getMessage());
			}

			XMLPDFdoc.setPDFDocumentHeader();
			com.lowagie.text.Document doc = XMLPDFdoc.getPDFDoc();
			doc.addCreator(msgMT.getE01CUSNA1());
		
			ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
			PdfWriter docWriter = null;
			PdfContentByte cb = null;
			PdfPTable myTable = null;

			try {
				docWriter = PdfWriter.getInstance(doc, baosPDF);
				doc.open();
				XMLPDFdoc.setPDFcb(docWriter, transList, user, msgMT);
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

			int screen = R_FILT;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case A_PROC :							
							procSndInf(mc, msgUser, req, res, session);
							break;
						case A_PRINT :							
							procSndPrt(mc, msgUser, req, res, session);
							break;	
						case A_PRINT_PDF :							
							procActionPrtPdf(mc, msgUser, req, res, session);
							break;	
						case R_FILT :
							procReqFilter(msgUser, req, res, session);
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

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
}