package datapro.eibs.reports;


import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.Socket;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import datapro.eibs.beans.EDL090002Message;
import datapro.eibs.beans.EFRM00001Message;
import datapro.eibs.beans.EFRM00005Message;
import datapro.eibs.beans.EFRM00007Message;
import datapro.eibs.beans.EFRM00008Message;
import datapro.eibs.beans.EFRM00010Message;
import datapro.eibs.beans.EFRM00043Message;
import datapro.eibs.beans.EFRM00080Message;
import datapro.eibs.beans.EFRM00095Message;
import datapro.eibs.beans.EFRM00098Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ERA000001Message;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD008001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0600DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.generic.JODOM;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.math.BigDecimal;
/**
 * @version 	1.0
 * @author
 */
public class JSEFRM000REF extends JSEFRM000 {

	private final static int CLIENT_SELECTION = 1;
	private final static int ACCOUNT_SELECTION = 2;
	private final static int PRINT_REFERENCE = 3;
	
	private String[] descNumbers =  {"Cero", "Una", "Dos", "Tres","Cuatro","Cinco", 
										 "Seis","Siete","Ocho","Nueve","Diez",
										 "Once","Doce","Trece","Catorce", "Quince"};

	/**
	* Redirecciona a la accion seleccionada por el usuario
	*/
	public void service(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;
		int screen = 1;

		session = (HttpSession) request.getSession(false);

		if (session == null) {
			try {
				response.setContentType("text/html");
				printLogInAgain(response.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			try {

				screen = CLIENT_SELECTION;

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				userLanguage = msgUser.getE01LAN().equals("s") ? "es" : "en";
				ftmEibs = msgUser.getE01DTF();
				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(request) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(request) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					response.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					return;
				}

				try {
					screen = Integer.parseInt(request.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				// Ejecuta la accion seleccionada por el usuario

				switch (screen) {
					case CLIENT_SELECTION :
						clientSelection(
							mc,
							msgUser,
							request,
							response,
							session);
						break;
					case ACCOUNT_SELECTION :
						accountSelection(
							mc,
							msgUser,
							request,
							response,
							session);
						break;
					case PRINT_REFERENCE :
						printReference(mc, msgUser, request, response, session);
						break;
					default :
						clientSelection(
							mc,
							msgUser,
							request,
							response,
							session);
				}

				try {
					s.close();
				} catch (Exception e) {
					flexLog("Error closing socket connection " + e);
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				response.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}

	private synchronized void clientSelection(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD008001Message msgClient = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgClient = new datapro.eibs.beans.ESD008001Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("BANK_REFERENCE");
			userPO.setPurpose("PRINT_BANK_REF");
			ses.setAttribute("client", msgClient);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0080_client_both_enter.jsp");
			callPage(LangPath + "ESD0080_client_both_enter.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	private synchronized void accountSelection(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession session)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0600DSMessage msgHelp = null;
		JBObjList beanList = null;
		ESD008001Message client = null;
		UserPos userPO = (UserPos) session.getAttribute("userPO");

		try {
			//ESD008001Message client = (ESD008001Message) session.getAttribute("client");

			msgHelp = (EWD0600DSMessage) mc.getMessageRecord("EWD0600DS");
			msgHelp.setEWDUSR(user.getH01USR());
			msgHelp.setEWDSHR(req.getParameter("NameSearch").toUpperCase());
			//msgHelp.setEWDSBK(req.getParameter("shrBank"));

			if (user.getE01SEC().equals("Y")) {
				msgHelp.setEWDSBK(user.getE01UBK());
			} else {
				msgHelp.setEWDSBK("");
			}

			//Lee client code or ID number

			try {
				if (req.getParameter("E01CUN") != null
					&& req.getParameter("E01CUN").length() > 0
					&& !req.getParameter("E01CUN").equals("0")) {
					msgHelp.setEWDCUN(req.getParameter("E01CUN"));
					msgHelp.setEWDSEL("N");
				} else {
					msgHelp.setEWDSHR(req.getParameter("E01IDN"));
					msgHelp.setEWDSEL("I");

				}
			} catch (Exception e) {
			}

			msgHelp.setEWDREC("0");

			msgHelp.send();
			msgHelp.destroy();
			flexLog("EWD0600DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0600DS")) {

				try {
					beanList = new JBObjList();
					flexLog("EWD0600DS Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";

				while (true) {

					msgHelp = (EWD0600DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();
					msgHelp.setHandler(null);

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(
								Integer.parseInt(msgHelp.getEWDREC()));
							chk = "checked";
							userPO.setCusName(
								msgHelp.getEWDFNA()
									+ " "
									+ msgHelp.getEWDFN2()
									+ " "
									+ msgHelp.getEWDLN1()
									+ " "
									+ msgHelp.getEWDLN2());
							userPO.setCusNum(msgHelp.getEWDCUN());
							userPO.setID(msgHelp.getEWDIDN());
							userPO.setHeader20("");

						} else {
							chk = "";
						}

						beanList.addRow(msgHelp);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				session.setAttribute("accounts", beanList);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EFRM000REF_acc_list.jsp");
					callPage(LangPath + "EFRM000REF_acc_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Error: No match found");
				clientSelection(mc, user, req, res, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

	private synchronized void printReference(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
        int ValueDateMonth =0;
        int ValueDateDay = 0;
        int ValueDateYear =0;
		String account = req.getParameter("account"); // numero de cuenta
		String sendTo = req.getParameter("sendTo"); // Destinatario
		String eForm = req.getParameter("eForm"); // Nombre del formulario
		try{
		 ValueDateMonth =
			Integer.parseInt(req.getParameter("valueDateMonth"));
		// Fecha valor mes de la solicitud
		ValueDateDay = Integer.parseInt(req.getParameter("valueDateDay"));
		// Fecha valor dia
		ValueDateYear = 2000 + Integer.parseInt(req.getParameter("valueDateYear"));
		// Fecha valor anio
		}catch (Exception e){
			  // Toma la fecha del dia
			  Calendar calendar = Calendar.getInstance();
			  ValueDateMonth= calendar.get(Calendar.MONTH);
			  ValueDateDay= calendar.get(Calendar.DAY_OF_MONTH);
			  ValueDateYear=calendar.get(Calendar.YEAR);ValueDateDay=0;ValueDateMonth=0;ValueDateYear=0;
        }
		
		int openDteMonth = Integer.parseInt(req.getParameter("openDteMonth"));
		// Fecha apertura cta mes
		int openDteDay = Integer.parseInt(req.getParameter("openDteDay"));
		// Fecha apertura cta dia
		int openDteYear = Integer.parseInt(req.getParameter("openDteYear"));
		// Fecha apertura cta anio
		if (openDteYear > 60 )
		  openDteYear = openDteYear + 1999;
		else
		  openDteYear = openDteYear + 2000;
		
		boolean onRange = false;
		// check difference of days between Runtime date and open date
		try {
			Calendar endDate = Calendar.getInstance();
			endDate.set(ValueDateYear, ValueDateMonth, ValueDateDay);
			Calendar startDate = Calendar.getInstance();
			startDate.set(openDteYear, openDteMonth, openDteDay);
			startDate.add(Calendar.DAY_OF_YEAR, 90);
			onRange = !startDate.after(endDate);

		} catch (Exception e) {
		}

		// call form
		if (onRange) {
			// sendForm
			try {
				res.setHeader("Cache-Control", "max-age=30");

				res.setContentType("application/pdf");
				PdfReader pdfReader =
					new PdfReader(
						new URL(
							datapro.eibs.master.JSEIBSProp.getFORMPDFURL()
								+ eForm));
				PdfStamper pdfStamper =
					new PdfStamper(pdfReader, res.getOutputStream());
				AcroFields fields = pdfStamper.getAcroFields();
				prepareForm(mc, user, req, res, ses, fields);

				// Add Actions

				// Add User Image Sign

				try {
					//datapro.eibs.tools.ScanImageReader imgReader = 
						//datapro.eibs.tools.ScanImageReader.getInstance();
					ESS0030DSMessage currUser =
						(ESS0030DSMessage) ses.getAttribute("currUser");
					String agency = currUser.getBigDecimalE01UBR().toString();
					
					byte[] imgBytes = null;
						//imgReader.getImage("B", agency, "20", "1");
					
					if (imgBytes != null) {
						Image image = Image.getInstance(imgBytes);
						PdfContentByte pdfContent =
							pdfStamper.getOverContent(1);
						float[] signArea =
							fields.getFieldPositions(
								"topmostSubform[0].Page1[0].img_acc_of[0]");
						
						if (signArea != null) {
							Rectangle signRect =
								new Rectangle(
									signArea[1],
									signArea[2],
									signArea[3],
									signArea[4]);
							image.scaleToFit(
								signRect.width(),
								signRect.height());
							image.setAbsolutePosition(
								signArea[1]	+ ((signRect.width() - image.scaledWidth())/ 2),
								signArea[2]	+ ((signRect.height() - image.scaledHeight())/ 2));
							 //image.setAbsolutePosition( signArea[0],signArea[3]);			

							//				   Add image
							pdfContent.addImage(image);
						}
					}
				} catch (IOException e) {
					flexLog(e.getMessage());
				}

				// Add Input user fields
				if (sendTo != null)
				   fields.setField("Destinatario",sendTo);
				if (ValueDateDay != 0){
					fields.setField("Dia", String.valueOf(ValueDateDay));
					fields.setField("Annio",String.valueOf(ValueDateYear));
					switch (ValueDateMonth) {
						case 1 : fields.setField("Mes","Enero");
						         break;
						case 2 : fields.setField("Mes","Febrero");
						         break;
						case 3 : fields.setField("Mes","Marzo");
						         break;
						case 4 : fields.setField("Mes","Abril");
						         break;
						case 5 : fields.setField("Mes","Mayo");
						         break;
						case 6 : fields.setField("Mes","Junio");
						         break;
						case 7 : fields.setField("Mes","Julio");
						         break;
						case 8 : fields.setField("Mes","Agosto");
						         break;
						case 9 : fields.setField("Mes","Septiembre");
						         break;
						case 10 : fields.setField("Mes","Octubre");
						         break;
						case 11: fields.setField("Mes","Noviembre");
						         break;
						case 12: fields.setField("Mes","Diciembre");
						         break;
						default: fields.setField("Mes", " ");
					}
				}
				   

				// close streams
				pdfStamper.setFormFlattening(true);
				res.getOutputStream().flush();
				pdfStamper.close();
				res.getOutputStream().close();

			} catch (DocumentException e) {
			} catch (IOException e) {
			} catch (Exception e) {
				flexLog(e.getMessage());
			}

		} else {
			// Send errro message back...
			ELEERRMessage error = new ELEERRMessage();
			error.setERDF01("account");
			error.setERNU01("9999");
			error.setERDR01("9999");
			error.setERDS01("Cuenta no posee antiguedad requerida");
			error.setERWF01("Y");
			
			ses.setAttribute("error",error);
			callPage(LangPath + "error_viewer.jsp", req, res);
		}

	}

	private void setAction(
		HttpServletRequest req,
		HttpServletResponse res,
		Object pdfWriter,
		String operation,
		String copies,
		String printerName)
		throws ServletException, IOException {

		try {
			PdfWriter writer = (PdfWriter) pdfWriter;

			int action = 0;
			try {
				action = Integer.parseInt(operation);
			} catch (Exception e) {
			}
			String recallPage =
				req.getRequestURL().substring(
					0,
					req.getRequestURL().toString().indexOf(
						req.getServletPath()))
					+ "/pages/close.html";
			switch (action) {
				case 3 :
					//Prepare
					break;
				case 2 :
					//Print
					writer
						.addJavaScript(
							"function onLoadPrint(){"
							+ "	var pp = this.getPrintParams();"
							+ "	pp.NumCopies = "
							+ copies
							+ ";"
							+ "	pp.interactive = 2;"
							+ "	pp.printerName = \""
							+ printerName
							+ "\";"
							+ "	this.print(pp);"
							+ 
					//"	this.print({bUI: false, bSilent: true});" +
					"	this.resetForm();"
						+ "	this.getURL(\""
						+ recallPage
						+ "\");"
						+ 
					//"	this.setAction(\"DidPrint\", this.getURL(\"" + recallPage + "\"));" +
					"}" + "this.onLoadPrint();");

					/*outputFDF.AddDocJavaScript(
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
						"}");*/
					//outputFDF.SetOnImportJavaScript("this.onLoadPrint();", false);
					break;
				case 1 :
				default :
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
			//OutputStream out = res.getOutputStream();

			// 3
			//outputFDF.Save(out);

			//out.close();

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
	private void prepareForm(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		AcroFields fields)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFRM00001Message msgHeader = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		String appCode = null;
		//FDFDoc outputFDF = null;

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
					msgHeader.setH01OPECOD(
						req.getParameter("account").toUpperCase());
				} catch (Exception e) {
				}
			} else {
				msgHeader.setE01SELACD(appCode);
				try {
					msgHeader.setE01SELACC(req.getParameter("account"));
				} catch (Exception e) {
					if (appCode.equals("00")) {
						msgHeader.setE01SELACC(userPO.getCusNum());
					} else {
						msgHeader.setE01SELACC(userPO.getIdentifier());
					}
				}
				try {
					msgHeader.setH01OPECOD(
						datapro.eibs.master.Util.justifyRight(
							req.getParameter("SEQ"),
							4));
				} catch (Exception e) {
				}
			}
			flexLog("Message Send: " + msgHeader.toString());
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
				String err =
					msgError.getERNU01() + " - " + msgError.getERDS01();
				flexLog("JSEFRM000REF.prepareForm Error: " + err);

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
						if (nothing)
							nothing = false;
						beanList.addRow(msgHeader);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();

				}
				if (!nothing) {

					if (!appCode.equalsIgnoreCase("XX")) {
						int titularCount = 1;
						int signerCount = 1;
						int repLegalCount = 1;

						// Receive Data
						newmessage = mc.receiveMessage();
						while (true) {
							String ddsName = newmessage.getFormatName();

							if (ddsName.equals("EFRM00010")) { // CDs
								getCDDataForDataproForms(
									fields,
									(EFRM00010Message) newmessage);
							} else if (ddsName.equals("EFRM00080")) { // Loans
								getLNDataForDataproForms(
									fields,
									(EFRM00080Message) newmessage);
							} else if (
								ddsName.equals(
									"ESD000006")) { // Account Holders
								getAccHoldersForDataproForms(
									fields,
									(ESD000006Message) newmessage);
							} else if (
								ddsName.equals("ESD000002")) { // Special Codes
								getAccSpecialCodesForDataproForms(
									fields,
									(ESD000002Message) newmessage);
							} else if (
								ddsName.equals(
									"ESD000005")) { // Special Instructions
								getAccSpecialInstForDataproForms(
									fields,
									(ESD000005Message) newmessage);
							} else if (
								ddsName.equals("ERA000001")) { // Collaterals
								getAccCollateralsForDataproForms(
									mc,
									fields,
									(ERA000001Message) newmessage);
							} else if (
								ddsName.equals(
									"EDL090002")) { // Payment Schedule
								getAccPayScheduleForDataproForms(
									mc,
									fields,
									(EDL090002Message) newmessage);
							} else if (ddsName.equals("EFRM00005")) { // Retail
								getRTDataForDataproForms(
									fields,
									(EFRM00005Message) newmessage);
							} else if (ddsName.equals("EFRM00007")) {
								EFRM00007Message msg =
									(EFRM00007Message) newmessage;
								if (msg.getE07CUMRTP().equals("H"))
									getRTTitulars(
										fields,
										msg,
										"Cuenta.Titular" + titularCount++);
								else if (msg.getE07CUMRTP().equals("S"))
									getRTSigners(
										fields,
										msg,
										"Cuenta.Firmante" + signerCount++);
								else if (msg.getE07CUMRTP().equals("I"))
									getRTRepLegals(
										fields,
										msg,
										"Cuenta.RepLegal" + repLegalCount++);
							} else if (ddsName.equals("EFRM00008")) {
								getRTLDInfo(
									fields,
									(EFRM00008Message) newmessage);
								// laundry money
							} else if (
								ddsName.equals("EFRM00098")) { // Customers
								getCUSTDataForDataproForms(
									fields,
									(EFRM00098Message) newmessage);
							} else if (ddsName.equals("ESD000004")) {
								// BankRef, Beneficiary, Board, CommercialRef, LegalRep, PersonalRef, StockHolder
								getAddCUSTDataForDataproForms(
									fields,
									(ESD000004Message) newmessage);
							} else if (
								ddsName.equals("EFRM00006")) { // Stop Payment
								// getSPDataForBankersForms(outputFDF, (EFRM00006Message)newmessage);
							} else if (
								ddsName.equals(
									"EFRM00043")) { // Boletas de Garantias
								getLCStandByDataForDataproForms(
									fields,
									(EFRM00043Message) newmessage);
							} else if (ddsName.equals("EFRM00095")) { // dda
								getDDADataForBankersForms(
									fields,
									(EFRM00095Message) newmessage);
							} else if (ddsName.equals("EFRM00001")) { // End
								break;
							} else {
							}

							newmessage = mc.receiveMessage();

						}

					}
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//return outputFDF;

	}

	public void addDataNode(
		Object obj,
		String name,
		String dataItem) {

		try {
			AcroFields fields = (AcroFields) obj;
			if (!(dataItem.trim().equals("")
				|| dataItem.trim().equalsIgnoreCase("&nbsp;"))) {
				boolean isOK = true;
				java.math.BigDecimal dataNum;
				try {
					dataNum = new java.math.BigDecimal(dataItem);
					if (dataNum.compareTo(new java.math.BigDecimal(0)) == 0)
						isOK = false;
					else
						isOK = true;
				} catch (Exception ex) {
					isOK = true;
				}
				if (isOK) {
					fields.setField(
						/*"topmostSubform[0].Page1[0]."
							+ */
							 dstXML.getElementCode(name)
							/*+ "[0]" */,
						dataItem);
				}
			}
		} catch (Exception e) {
			flexLog("JSEFRM000.addDataNode: "+e.getMessage());
		}

	}

	private JODOM dstXML = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dstXML = new JODOM(JSEIBSProp.getFORMPDFURL() + "eIBSForms_DST.xml");
	}
	
	protected synchronized void getCDDataForDataproForms(Object data, EFRM00010Message msg) {
		super.getCDDataForDataproForms(data,msg);
		addDataNode(data,"RetailAccount.NAVCifras" , toCifras(msg.getBigDecimalE10DEPAMT()));
	}
	
	protected String toCifras(BigDecimal dividendo){ 
		//BigDecimal dividendo = msg.getBigDecimalE05AVGBAL();
		int numCounter = 1;
		BigDecimal divisor = new BigDecimal(10);
		String descNum = "";
		while (dividendo.compareTo(divisor) == 1){
			numCounter++;
			dividendo = dividendo.divide(divisor,BigDecimal.ROUND_HALF_EVEN);
		}
    
		if (numCounter > 0 && numCounter < 16)
			descNum = descNumbers[numCounter];
   
		if (dividendo.intValue() > 6)
		 return descNum+ " ("+numCounter+ ") cifras altas" ;
		else if(dividendo.intValue() > 3)  	
		  return descNum+ " ("+numCounter+ ") cifras medias" ;
		else 
		  return descNum+ " ("+numCounter+ ") cifras bajas" ;
	}

}
