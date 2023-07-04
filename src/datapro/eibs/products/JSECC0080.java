package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import datapro.eibs.tools.pdf.*;

public class JSECC0080 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int A_NEW  			= 200;	
	static final int A_MAINT  			= 400;
	static final int A_DELETE  			= 600;
	
	static final int R_NEW 	 			= 100;
	static final int R_MAINT  			= 300;
	static final int R_DELETE 			= 500;
	
	static final int A_LIST 			= 2;
	static final int A_NEW_MAINT		= 3;
		
	static final int R_PRINT_PIN 		= 20;

	static final int R_ENTER_CARDS_ASSIGN = 700;
	static final int A_ENTER_CARDS_ASSIGN = 800;
			
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECC0080() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECC0010");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	protected void procReqEnterAssignCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		String opt = req.getParameter("OPT");
		if (opt == null) opt = "";

		try {
			flexLog("About to call Page: " + LangPath + "ECC0080_cc_enter_card.jsp");
			callPage(LangPath + "ECC0080_cc_enter_card.jsp?opt=" + opt, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnterAssignCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC008001Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0080");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				msgList.setE01CCANUM(req.getParameter("E01CCANUM"));
			} catch (Exception e) {
				msgList.setE01CCANUM("0");
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECC008001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		int colNum = 18;
		try {
			appList = new datapro.eibs.beans.JBListRec();
			appList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECC0080_cc_enter_card.jsp");
					callPage(LangPath + "ECC0080_cc_enter_card.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("ECC008001")) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgList = (ECC008001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								userPO.setIdentifier(msgList.getE01CCANUM());
								userPO.setCusNum(msgList.getE01CARCUN());
								userPO.setCusName(msgList.getE01CARNA1());
								userPO.setHeader1(msgList.getE01CCANUM());
								userPO.setHeader2(msgList.getE01CCASTA());
								userPO.setHeader3(msgList.getE01CCADSC());
								userPO.setHeader20("");
								userPO.setHeader21("");
							
							appList.setShowNext(false);
							break;
						} else {
							
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCANUM()));
								userPO.setIdentifier(msgList.getE01CCANUM());
								userPO.setCusNum(msgList.getE01CARCUN());
								userPO.setCusName(msgList.getE01CARNA1());
								userPO.setHeader1(msgList.getE01CCANUM());
								userPO.setHeader2(msgList.getE01CCASTA());
								userPO.setHeader3(msgList.getE01CCADSC());
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							
							// Quote List
							myRow[0] = msgList.getE01CCANUM();   // card number
							myRow[1] = msgList.getE01CARCUN();   // customer number card
							myRow[2] = msgList.getE01CARNA1();   // customer name card
							myRow[3] = msgList.getE01CCAATY();   // account type
							myRow[4] = msgList.getE01CCAACC();   // account number
							myRow[5] = msgList.getE01CCAFAU();   // from authority 
							myRow[6] = msgList.getE01CCAIAU();   // inquiry authority
							myRow[7] = msgList.getE01CCATAU();   // transfer authority
							myRow[8] = msgList.getE01CCADAU();   // default authority 
							myRow[9] = msgList.getE01CCARAU();   // ready reserve authority
							myRow[10] = msgList.getE01CCAIND();  // Account index
							myRow[11] = msgList.getE01CCACWL();  // cash withdrawal limit
							myRow[12] = msgList.getE01CCAFBL();  // Found back limit
							myRow[13] = msgList.getE01CCAODL();  // overdraft limit
							myRow[14] = msgList.getE01CCATWL();  // teller withdrawal limit
							myRow[15] = msgList.getE01CTACUN();   // customer number account
							myRow[16] = msgList.getE01CTANA1();   // customer name account
							myRow[17] = msgList.getE01CCAPRI();   // account and card type relation
							//myRow[18] = msgList.getE01CCACID();   // Client ID 
							
														
							appList.addRow(myFlag, myRow);
							
							if (marker.equals("+")) {
								appList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();

					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("appList", appList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECC0080_cc_accounts_list.jsp");
						callPage(LangPath + "ECC0080_cc_accounts_list.jsp", req, res);

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

	/**
	 * This method was created in VisualAge.
	 */

	protected void procActionAditionalCardsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
		JBListRec appList = null;

		appList = (JBListRec) ses.getAttribute("appList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
				appList.setCurrentRow(row);
			} catch (Exception e) {
			}
			ECC008001Message msgCC = (ECC008001Message) mc.getMessageRecord("ECC008001");

			switch (option) {
				case 1 : // New
					int num = 0;
					String sNum = "";
					if (appList.getNoResult()) {
						num = 1;
						sNum = String.valueOf(num);
					} else {
						appList.setLastRow();
						//num = Integer.parseInt(appList.getRecord(0)) + 1;
						sNum = appList.getRecord(0);
					}
					
					//ECC008001Message msgCC = (ECC008001Message) mc.getMessageRecord("ECC008001");
					userPO.setHeader19(sNum + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0080_cc_account_new.jsp?num=" + sNum);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgAcc", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0080_cc_accounts_list.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0080_cc_account.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "ECC0080_cc_accounts_list.jsp?SEL=" + row);
					break;
			    case 3 : // Delete
					procActionDelete( row, appList, mc, user, req, res, ses);
					break;
				case 4: // Consulta	
			
				   userPO.setHeader20("DO_QUERY");
				   userPO.setHeader21(super.webAppPath + LangPath + "ECC0080_cc_inq_account.jsp?ROW=" + row);
				   ses.setAttribute("userPO", userPO);
				   res.sendRedirect(super.srctx + LangPath + "ECC0080_cc_inq_account.jsp?ROW=" + row);
                   break; 
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionDelete(
		int row,
		JBListRec appList,
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECC008001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));
	
			flexLog("Send Initial Data");
			msgCC = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECC0080");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
  			msgCC.setH01OPECOD("0009");
	
			try {
				msgCC.setE01CCAACC(appList.getRecord(4));
			}
			catch (Exception e){
				msgCC.setE01CCAACC("");
			}
			try {
				msgCC.setE01CCANUM(userPO.getIdentifier());
			}
			catch (Exception e){
				msgCC.setE01CCANUM("");
			}			

			//msgRT.send();
			mc.sendMessage(msgCC);
			msgCC.destroy();
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
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				
				if (IsNotError) { // There are no errors
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0080?SCREEN=800&E01CCANUM=" 
												 + userPO.getIdentifier());
				} else {
					try {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						res.sendRedirect(super.srctx + LangPath + "ECC0080_cc_accounts_list.jsp?ROW=" + row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionNewMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECC008001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
		int row = -1;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));
	
			flexLog("Send Initial Data");
			msgCC = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECC0080");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			if (option == 1)
				msgCC.setH01OPECOD("0001");
			else
				msgCC.setH01OPECOD("0005");

			
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}
						
			// all the fields here
			java.util.Enumeration enu = msgCC.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			//msgRT.send();
			mc.sendMessage(msgCC);
			msgCC.destroy();
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
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECC008001")) {
				try {
					msgCC = new datapro.eibs.beans.ECC008001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgCC = (ECC008001Message) newmessage;
				// showESD008004(msgRT);
	
				if (IsNotError) { // There are no errors
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.products.JSECC0080?SCREEN=800&E01CCANUM=" 
							+ msgCC.getE01CCANUM() + "'");
							 
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgAcc", msgCC);
					ses.setAttribute("userPO", userPO);
					if (option == 1) {
						try {
							flexLog("About to call Page: " + LangPath + "ECC0080_cc_account_new.jsp");
							callPage(LangPath + "ECC0080_cc_account_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "ECC0080_cc_account.jsp");
							callPage(LangPath + "ECC0080_cc_account.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqPrintPIN(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECC008001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
		int row = -1;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));
	
			flexLog("Send Initial Data");
			msgCC = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECC0080");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0020");
			
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}
						
			// all the fields here
			java.util.Enumeration enu = msgCC.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
	
			//msgRT.send();
			mc.sendMessage(msgCC);
			msgCC.destroy();
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
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECC008001")) {
				try {
					msgCC = new datapro.eibs.beans.ECC008001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgCC = (ECC008001Message) newmessage;
				// showESD008004(msgRT);
	
				if (IsNotError) { // There are no errors
					DocumentException ex = null;
					ByteArrayOutputStream baosPDF = null;
				
					try {
						baosPDF = generatePDFDocumentBytes(req, user, msgCC, null, false);
	
						String path = new LocateMe().getClassesRoot();
						StringBuffer sbFilename = new StringBuffer();
						sbFilename.append(path + "ecc0080.pdf");
						
						/*
						sbFilename.append(System.currentTimeMillis());
						sbFilename.append(".pdf");
						StringBuffer sbContentDispValue = new StringBuffer();
						sbContentDispValue.append("inline");
						sbContentDispValue.append("; filename=");
						sbContentDispValue.append(sbFilename);

						res.setContentType("application/pdf");
						res.setHeader("Content-disposition", sbContentDispValue.toString());
						//res.setHeader("Cache-Control", "max-age=30");
						res.setContentLength(baosPDF.size());
						
						ServletOutputStream sos = res.getOutputStream();
						*/
						FileOutputStream sos = new FileOutputStream(sbFilename.toString());
						
						baosPDF.writeTo(sos);
						sos.flush();
						sos.close();
				
						try {
							flexLog("About to call Page: " + LangPath + "ECC0080_cc_card_pdf_print.jsp");
							callPage(LangPath + "ECC0080_cc_card_pdf_print.jsp?path=" + path, req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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
							baosPDF.close();
						}
					}
	
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgAcc", msgCC);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ECC0080_cc_accounts_list.jsp");
						callPage(LangPath + "ECC0080_cc_accounts_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected ByteArrayOutputStream generatePDFDocumentBytes(
			HttpServletRequest req,
			ESS0030DSMessage user,
			ECC008001Message msgToPDF,
			JBObjList transList,
			boolean FLG)
			throws DocumentException, IOException {

			XMLPDFDocument XMLPDFdoc = null;
			
			try {
				XMLPDFdoc = new XMLPDFDocument();
				XMLPDFdoc.readXMLDoc("printformat.debitcard.xml"); 
			} catch (IOException ex){
				System.err.println(ex.getMessage());
			}

			XMLPDFdoc.setPDFDocumentHeader();
			Document doc = XMLPDFdoc.getPDFDoc();
		
			ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
			PdfWriter docWriter = null;
			PdfContentByte cb = null;

			try {
				docWriter = PdfWriter.getInstance(doc, baosPDF);
				docWriter.setViewerPreferences(PdfWriter.HideToolbar | PdfWriter.HideMenubar );
				doc.open();

				//docWriter.addJavaScript("this.print(false);", false);
				
				XMLPDFdoc.setPDFcb(docWriter, transList, user, msgToPDF);
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

			int screen = R_MAINT;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

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
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Request
					case A_LIST :					    
						procActionAditionalCardsList(mc, msgUser, req, res, session);
						break;	
					case A_NEW_MAINT:
						procActionNewMaint(mc, msgUser, req, res, session);
						break;
					case R_PRINT_PIN:
						procReqPrintPIN(mc, msgUser, req, res, session);
						break;	

					case R_ENTER_CARDS_ASSIGN :
						procReqEnterAssignCard(mc, msgUser, req, res, session);
						break;
					case A_ENTER_CARDS_ASSIGN :
						procActionEnterAssignCard(mc, msgUser, req, res, session);
						break;
					
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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


}