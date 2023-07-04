package datapro.eibs.reports;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adobe.fdf.FDFDoc;
import com.adobe.fdf.exceptions.FDFException;

import datapro.eibs.beans.EFRM00001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EOF011501Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0120DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.generic.JODOM;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEFRM000PDF extends JSEFRM000 {

	static final int R_FORM_LIST = 1;
	static final int R_OFFICIAL_CHECK = 2;
	static final int R_FORM = 3;
	static final int R_APPLICATION = 5;

	protected JODOM dstXML = null;
	
	/**
	 * JSReportManager constructor comment.
	 */
	public JSEFRM000PDF() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//initDataStructure();
	}
	
	public void initDataStructure() {
		dstXML = new JODOM(JSEIBSProp.getFORMPDFURL() + "eIBSForms_DST.xml");		
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (9/25/2000 11:17:32 AM)
	 */
	private void procReqFormList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFRM00001Message msgHeader = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		String appCode = null;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgHeader = (EFRM00001Message) mc.getMessageRecord("EFRM00001");
			msgHeader.setH01USERID(user.getH01USR());
			msgHeader.setH01PROGRM("EFRM000");
			msgHeader.setH01TIMSYS(getTimeStamp());

			try {
				// nothing - Datapro Forms & B - Bankers Forms & P - Datapro PDF Forms
				msgHeader.setH01FLGWK1(req.getParameter("INTERFACE"));
			} catch (Exception ex) {
				msgHeader.setH01FLGWK1("P");
			}
			try {
				// 01 - Opening
				msgHeader.setE01SELFTY(req.getParameter("OPE_CODE"));
			} catch (Exception ex) {
			}

			try {
				appCode = req.getParameter("APP_CODE");
				if (appCode == null)
					appCode = "";
			} catch (Exception e) {
				appCode = "";
			}

			if (appCode.equalsIgnoreCase("XX")) {
				try {
					msgHeader.setH01OPECOD(req.getParameter("ACCOUNT").toUpperCase());
				} catch (Exception e) {
				}
			} else {
				msgHeader.setE01SELACD(appCode);
				try {
					msgHeader.setE01SELACC(req.getParameter("ACCOUNT"));
				} catch (Exception e) {
					if (appCode.equals("00")) {
						msgHeader.setE01SELACC(userPO.getCusNum());
					} else {
						msgHeader.setE01SELACC(userPO.getIdentifier());
					}
				}
				try {
					msgHeader.setH01OPECOD(Util.justifyRight(req.getParameter("SEQ"), 4));
				} catch (Exception e) {
				}
			}

			msgHeader.send();
			msgHeader.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				String err = msgError.getERNU01() + " - " + msgError.getERDS01();
				ses.setAttribute("error_msg", err);

				try {
					flexLog("About to call Page: " + LangPath + "EFRM000_pdf_forms_error.jsp");
					callPage(LangPath + "EFRM000_pdf_forms_error.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EFRM00001")) {
				boolean nothing = true;
				beanList = new JBObjList();

				String marker = "";

				while (true) {

					msgHeader = (EFRM00001Message) newmessage;

					marker = msgHeader.getE01MORFRM();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {						
						if (nothing) nothing = false;
						beanList.addRow(msgHeader);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();

				}
				if (!nothing) {
					FDFDoc outputFDF = new FDFDoc();

					if (!appCode.equalsIgnoreCase("XX")) {
                        newmessage = mc.receiveMessage();
                        int index = 1;
                        String prefix = "";
                        String ddsName = newmessage.getFormatName();
						while (true) {
							if (!ddsName.equals(newmessage.getFormatName())
								|| !prefix.equals(getPrefix(newmessage))) {
								index = 1;
							}
							prefix = getPrefix(newmessage);
	                    	ddsName = newmessage.getFormatName();
	                        if(ddsName.equals("EFRM00001"))
	                            break;
	                        else                             	
	                    		buildFormList(mc, outputFDF, newmessage, "." + prefix, index++);
	                        newmessage = mc.receiveMessage();

						}

					}
					// Output the document, use standard formatter
					try {
						if (beanList.getLastRec() == 0) {
							String urlPDF = datapro.eibs.master.JSEIBSProp.getFORMPDFURL() + msgHeader.getE01APFPTH();
							sendPdf(
								req, 
								res, 
								urlPDF, 
								outputFDF, 
								msgHeader.getE01APFOPE(), 
								msgHeader.getE01APFCPI(), 
								msgHeader.getE01APFDDS());
						} else {
							flexLog("Putting java beans into the session");
							ses.setAttribute("pdfData", outputFDF);							
							ses.setAttribute("pdfList", beanList);
							
							res.setContentType("text/html");
							PrintWriter out = res.getWriter();
							String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + LangPath + "EFRM000_pdf_list.jsp'";
							out.println("<HTML>");
							out.println("<HEAD>");
							out.println("<TITLE>PDF</TITLE>");
							out.println("</HEAD>");
							out.println("<BODY>");
							out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
							out.println("		top.opener.location.href = \""+ href + "\";");
							out.println("		top.close();");
							out.println("</SCRIPT>");
							out.println("<P>PDF</P>");
							out.println("</BODY>");
							out.println("</HTML>");
						}

					} catch (Exception e) {
						flexLog("Error: " + e);
					}
				} else {
					// if (!req.getParameter("SHOW_NO_ERROR").equalsIgnoreCase("TRUE")) {
					// There is no form attached to this action
					flexLog("Putting java beans into the session");
					String err = "";
					ses.setAttribute("error_msg", err);
					try {
						flexLog("About to call Page: " + LangPath + "EFRM000_pdf_forms_error.jsp");
						callPage(LangPath + "EFRM000_pdf_forms_error.jsp", req, res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					// }
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (9/25/2000 11:17:32 AM)
	 */
	private void procReqOfficialCheck(
		MessageContext mc, 
		ESS0030DSMessage user, 
		HttpServletRequest req, 
		HttpServletResponse res, 
		HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EOF011501Message msgHeader = null;
		EWD0120DSMessage msgTr = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try
		{
			msgHeader = (EOF011501Message)mc.getMessageRecord("EOF011501");
			msgHeader.setH01USERID(user.getH01USR());
			msgHeader.setH01FLGWK1("P");
			msgHeader.setH01OPECOD("0002");
			try {
				if (req.getParameter("E01OFMCKN") != null)
				  msgHeader.setE01OFMCKN(req.getParameter("E01OFMCKN"));
				  flexLog("Product Sent");
			}
			catch (Exception e)	{
			  msgHeader.setE01OFMCKN("0");
			  flexLog(" error " + e);
			  flexLog(" Error Sent");
			}
			try {
				if (req.getParameter("E01OFMCCY") != null)
				   msgHeader.setE01OFMCCY(req.getParameter("E01OFMCCY").toUpperCase());
				  flexLog("Product Sent");
			}
			catch (Exception e)	{
			  msgHeader.setE01OFMCCY("0");
			  flexLog(" error " + e);
			  flexLog(" Error Sent");
			}
	 	
			msgHeader.send();	
			msgHeader.destroy();
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
			
				flexLog("Putting java beans into the session");
				String err = msgError.getERNU01() + " - " + msgError.getERDS01();
				ses.setAttribute("error_msg", err);

				try {
					flexLog("About to call Page: " + LangPath + "EFRM000_pdf_forms_error.jsp");
					callPage(LangPath + "EFRM000_pdf_forms_error.jsp", req, res);

				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
			else if (newmessage.getFormatName().equals("EOF011501")) {

				String operation = null;
				boolean nothing = true;
			
				msgHeader = (EOF011501Message)newmessage;
				

				newmessage = mc.receiveMessage();

				FDFDoc data = new FDFDoc();
				//Header
				addDataNode(data, "OfficialCheck.CheckNumber", msgHeader.getE01OFMCKN());
				addDataNode(data, "OfficialCheck.Date", Util.formatDateFull(user.getE01DTF(),user.getE01LAN(),msgHeader.getE01OFMEM1(),msgHeader.getE01OFMEM2(),msgHeader.getE01OFMEM3()));
				addDataNode(data, "OfficialCheck.Amount", Util.addLeftChar('*',15,Util.fcolorCCY(msgHeader.getE01OFMAMT())));
				addDataNode(data, "OfficialCheck.Beneficiary1", msgHeader.getE01OFMBNF());
				addDataNode(data, "OfficialCheck.Beneficiary2", msgHeader.getE01OFMBN1());
				addDataNode(data, "OfficialCheck.AmountText", msgHeader.getE01LETAMT());
				addDataNode(data, "OfficialCheck.Concept1", msgHeader.getE01OFMCO1());
				addDataNode(data, "OfficialCheck.Concept2", msgHeader.getE01OFMCO2());
				addDataNode(data, "OfficialCheck.Concept3", msgHeader.getE01OFMCO3());
				addDataNode(data, "OfficialCheck.Applicant1", msgHeader.getE01OFMAPL());
				addDataNode(data, "OfficialCheck.Applicant2", msgHeader.getE01OFMAP1());
				addDataNode(data, "OfficialCheck.Address1", msgHeader.getE01OFMAD1());
				addDataNode(data, "OfficialCheck.Address2", msgHeader.getE01OFMAD2());
				addDataNode(data, "OfficialCheck.Address1", msgHeader.getE01OFMAD3());
			
				int pos = 0;
			
				while (true) { 	

					String ddsName = newmessage.getFormatName();

					if (ddsName.equals("EWD0120DS")) {  		// Official Check Transactions
						msgTr = (EWD0120DSMessage)newmessage;
						if (msgTr.getE01INDOPE().equals("*")) 
							break;
						else
							getTRDataForDataproForms(data, msgTr, ++pos);
					}
					else {
					}

					newmessage = mc.receiveMessage();

				}
				
				// Output the document, use standard formatter
				try {
					String urlPDF = datapro.eibs.master.JSEIBSProp.getFORMPDFURL() + msgHeader.getE01OFMPTH().trim();
					sendPdf(req, res, urlPDF, data, "1", "1", "");
				}
				catch (Exception e) {
					flexLog("Error: " + e);
				}

			}
			else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
	 	
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	

	}
	
	/**
	 * service method comment.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {

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

			try {

				int screen = R_FORM;

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
				userLanguage = msgUser.getE01LAN().equals("s") ? "es" : "en";
				ftmEibs=msgUser.getE01DTF();

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
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					return;
				}

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_FORM_LIST :
						procReqFormList(mc, msgUser, req, res, session);
						break;
					case R_OFFICIAL_CHECK :
						procReqOfficialCheck(mc, msgUser, req, res, session);
						break;
					case R_FORM :
						procReqForm(req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				try {
					s.close();
				} catch (Exception e) {
					flexLog("Error closing socket connection " + e);
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}

	public void addDataNode(
		Object obj,
		String name,
		String dataItem) {

		try {
			FDFDoc outputFDF = (FDFDoc) obj;
			if (!(dataItem.trim().equals("")
				|| dataItem.trim().equalsIgnoreCase("&nbsp;"))) {
				boolean isOK = true;
				java.math.BigDecimal dataNum;
				try {
					dataNum = new java.math.BigDecimal(dataItem);
					if (dataNum.compareTo(new java.math.BigDecimal("0")) == 0)
						isOK = false;
					else
						isOK = true;
				} catch (Exception ex) {
					isOK = true;
				}
				if (isOK) {
					outputFDF.SetValue(dstXML != null ? dstXML
							.getElementCode(name) : name, dataItem);
				}
			}
		} catch (Exception e) {
		}

	}


	protected void procReqForm(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) 
		throws ServletException, IOException {

		String urlPDF = datapro.eibs.master.JSEIBSProp.getFORMPDFURL() + req.getParameter("pdfName");

		FDFDoc outputFDF = (FDFDoc)ses.getAttribute("pdfData");
		
		sendPdf(
			req, 
			res, 
			urlPDF, 
			outputFDF, 
			req.getParameter("action"),
			req.getParameter("copies"),
			req.getParameter("printerName"));
	}
	
	protected void sendPdf(
		HttpServletRequest req,
		HttpServletResponse res,
		String urlPDF,
		Object pdfData,
		String operation,
		String copies,
		String printerName) 
		throws ServletException, IOException {

		try {
			FDFDoc outputFDF = (FDFDoc)pdfData;
			
			outputFDF.SetFile(urlPDF);
			
			int action = 0;
			try {
				action = Integer.parseInt(operation);
			} catch (Exception e) {
			}
			String recallPage = 
				req.getRequestURL().substring(0, req.getRequestURL().toString()
					.indexOf(req.getServletPath())) + "/pages/close.html";
			switch (action) {
				case 3: 
					//Prepare
					break;
				case 2:
					//Print
					outputFDF.AddDocJavaScript(
						"onLoadPrint", 
						"function onLoadPrint(){" +
						"	var pp = this.getPrintParams();" +
						"	pp.NumCopies = " + copies + ";" +
						"	pp.interactive = 2;" +
						"	pp.printerName = \"" + printerName + "\";" +
						"	this.print(pp);" +
						//"	this.print({bUI: false, bSilent: true});" +
						"	this.resetForm();" +
						"	this.getURL(\"" + recallPage + "\");" +
						//"	this.setAction(\"DidPrint\", this.getURL(\"" + recallPage + "\"));" +
						"}");
					outputFDF.SetOnImportJavaScript("this.onLoadPrint();", false);
					break;
				case 1:
				default:
					//Preview
					//outputFDF.SetOnImportJavaScript("this.setAction(\"DidPrint\", this.getURL(\""  + recallPage + "\"));", false);
			}
			
			// outputFDF.SetTargetFrame("pdf");
			/*
				Next we'll do three things:

				1)  Set everything up to emit the correct HTTP header for the MIME type. In
					the case with FDF, the MIME needs to be set to "application/vnd.fdf".
				2)	Obtain the OutputStream to which FDF data will be written
				3)	Write the FDF data to the OutputStream
			*/

			// 1
			res.setContentType("application/vnd.fdf");

			// 2
			OutputStream out = res.getOutputStream();

			// 3
			outputFDF.Save(out);

			out.close();

		} catch(FDFException e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		} catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	
	protected void sendPdf(
			HttpServletRequest req,
			HttpServletResponse res,
			String urlPDF,
			Object pdfData) 
			throws ServletException, IOException {
		sendPdf(req, res, urlPDF, pdfData, null, null, null);
	}

}