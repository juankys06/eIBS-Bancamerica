/*
 * Created on Jun 11, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.reports;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.PropertyResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adobe.fdf.FDFDoc;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import datapro.eibs.beans.EGL003001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EREPORTSTDMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.Util;
import datapro.eibs.tools.SendEMail;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEGL0030 extends JSEIBSServlet {

	private static final int R_TRANS_LIST	 = 1;
	private static final int A_TRANS_LIST	 = 2;
	private static final int SHOW_REPORT	 = 3;
	private static final int R_REPORT_DEMAND = 4;
	
	private static final String REJECTED	= "R";
	private static final String APPROVED	= "A";
	private static final String ERROR		= "E";
	private static final String PENDING		= "P";
	private static final String FORCED		= "F";
	
	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession session,
		int screen)
		throws ServletException, IOException {

			switch (screen) {
				case R_TRANS_LIST :
					procRequestTransList(user, req, res, session);
					break;
				case A_TRANS_LIST :
					procActionTransList(user, req, res, session);
					break;
				case SHOW_REPORT :
					showReport(req, res);
					break;
				case R_REPORT_DEMAND :
					procReqReportDemand(user, req, res, session);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
			}
	}

	private void procReqReportDemand(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		String target = (req.getParameter("PORT") == null) ? "ERPTSTD" : req.getParameter("PORT");
		session.setAttribute("TARGET", target);
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor(target, req);
			EREPORTSTDMessage msg = (EREPORTSTDMessage) mp.getMessageRecord("EREPORTSTD", user.getH01USR(), "0010");
			msg.setH01SCRCOD("01");
			msg.setE01REPNME("");
			msg.setE01APLCOD("GL");		
			
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			ELEERRMessage msgError = new ELEERRMessage();
			list.initRow();
			if (mp.hasError(list)) {
				msgError = (ELEERRMessage) list.getRecord();
				PageToCall = "error_viewer.jsp";
			} else {
				JBObjList reports = new JBObjList();
				list.initRow();
				while (list.getNextRow()) {
					msg = (EREPORTSTDMessage) list.getRecord();
					if (msg.getE01REPNME().equals("GL0020")) {
						reports.add(msg);
						break;
					}
				}
				list.initRow();
				while (list.getNextRow()) {
					msg = (EREPORTSTDMessage) list.getRecord();
					if (msg.getE01REPNME().equals("GL0030")) {
						reports.add(msg);
						break;
					}
				}
				session.setAttribute("dvList", reports);
				PageToCall = "EDD0924_report_demand.jsp";
			}	
			flexLog("Putting java beans into the session");
			session.setAttribute("userPO", userPO);
			session.setAttribute("error", msgError);
			forward(PageToCall, req, res);
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionTransList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		String action = (req.getParameter("ACTION") == null) ? "" : req.getParameter("ACTION"); 
		String text = (req.getParameter("TEXTTO") == null) ? "" : req.getParameter("TEXTTO"); 
		String batch = (req.getParameter("BATCH") == null) ? "" : req.getParameter("BATCH"); 
		String email = (req.getParameter("USERTO") == null) ? "" : req.getParameter("USERTO"); 
		flexLog("Putting batch number into the session");
		session.setAttribute("batch", batch);
		String bank = (req.getParameter("BANK") == null) ? "" : req.getParameter("BANK"); 
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EGL0030", req);
			EGL003001Message msg = (EGL003001Message) mp.getMessageRecord("EGL003001", user.getH01USR(), "0005");
			msg.setE01ACTION(action);
			msg.setE01BTHNUM(batch);
			msg.setE01ORGBNK(bank);
			
			mp.sendMessage(msg);
			
			if (action.equals(APPROVED)) {
				//Send Report By Email
				ByteArrayOutputStream report = null;
				try {
					JBObjList list = (JBObjList) session.getAttribute("transList");
					report = getReport(user, list);
					File pdf = new File(JSEIBSProp.getImgTempPath() + "report.pdf");
					FileOutputStream outpdf = new FileOutputStream(pdf);
					outpdf.write(report.toByteArray());
					outpdf.close();
					String error = "";
					error = sendEMail(pdf.getPath(), email, text); 
					if (!error.equals("")) {
						ELEERRMessage msgError = new ELEERRMessage();
						msgError.setERRNUM("1");
						msgError.setERNU01("0001");
						msgError.setERDS01(error);
						flexLog("Putting java beans into the session");
						session.setAttribute("error", msgError);
						PageToCall = "error_viewer.jsp";
					} else {
						PageToCall = "EGL0030_email_confirm.jsp";
					}
				} catch (DocumentException e) {
					res.setContentType("text/html");
					PrintWriter writer = res.getWriter();
					writer.println(
						this.getClass().getName()
							+ " caught an exception: "
							+ e.getClass().getName()
							+ "<br>");
					writer.println("<pre>");
					e.printStackTrace(writer);
					writer.println("</pre>");
				} finally {
					if (report != null) report.reset();
				}
			} else {
				//Confirmation Screen for Rejection
				PageToCall = "EGL0030_rejected_confirm.jsp";
			}
		
			forward(PageToCall, req, res);
		} finally {
			if (mp != null)	mp.close();
		}
	}
	
	private void showReport(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedOutputStream output = null;
		try {
			
			File fPdf = new File(JSEIBSProp.getImgTempPath() + "report.pdf"); //
			FileInputStream inPdf = new FileInputStream(fPdf);
			int lenPdf = (int) fPdf.length();
			byte[] buffer = new byte[lenPdf];
			inPdf.read(buffer);
			
			res.reset();
			res.setContentType("application/pdf");
			//res.setContentType("image/gif");
			res.setContentLength(buffer.length);
			//res.setHeader("Content-disposition", "attachment; filename=\"" + fPdf.getName() + "\"");
		
			output = new BufferedOutputStream(res.getOutputStream());
			output.write(buffer);
			output.flush();
		} finally {
			//pdf.delete();
			output.close();
		}
	}
	
	private ByteArrayOutputStream getReport(ESS0030DSMessage user, JBObjList list) throws DocumentException {
		String title = "Aprobación de Manifiesto de Transmisión de Transacciones";
		
		String headerA = "Transacciones Aprobadas";
		String headerP = "Transacciones Pendientes";
		
		String header01 = "Lote";
		String header02 = "Fecha";
		String header03 = "Debito";
		String header04 = "Credito";
		String header05 = "Número";
		String header06 = "Estado";
		String header07 = "Usuario";
		
		String headerT = "Totales";
		
		String page = "Pagina: ";
		
		String approved = "Aprobado por: ";
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		Document doc = new Document(PageSize.LETTER, 36, 36, 36, 36);
		PdfWriter docWriter = PdfWriter.getInstance(doc, result);
		
		try {
			doc.addAuthor("eIBS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator(user.getH01USR());
			doc.addTitle(title);
			doc.addKeywords("pdf, itext, Java, open source, http");
			
			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
			Font normalBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
			Font normalBoldUnderFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
			Font headerBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
			Font headerBoldUnderFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE);
			
			Paragraph TITLE = new Paragraph(title, headerBoldFont);
			
			Image image = null;
			try {
				//image = Image.getInstance("C:/workspaces/eIBS_r04m08/eIBS_WebApp_R04M08_Datapro/WebContent/images/logo.gif");
				image = Image.getInstance(JSEIBSProp.getImgTempPath() + "logo.gif");
				image.setAlignment(Element.ALIGN_LEFT);
				image.scalePercent(75);
			} catch (BadElementException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			String rundate = "Fecha de Proceso: ";
			if ( user.getE01DTF().equals("MDY") ) {
				rundate = rundate + Util.formatDate(user.getE01RDM(), user.getE01RDD(), user.getE01RDY());
			} else if ( user.getE01DTF().equals("DMY") ) {
						rundate = rundate + Util.formatDate(user.getE01RDD(), user.getE01RDM(), user.getE01RDY());
				   } else {
						rundate = rundate + Util.formatDate(user.getE01RDD(), user.getE01RDY(), user.getE01RDM());
				   }
			Paragraph DATE = new Paragraph(rundate, headerBoldFont);
			
			String runtime = "Hora de Proceso: ";
			Calendar calendar = Calendar.getInstance();
			String AMPM = calendar.get(Calendar.AM_PM) == 1 ? "PM" : "AM"; 
			runtime = runtime + (calendar.get(Calendar.HOUR) < 10 ? "0" + calendar.get(Calendar.HOUR) : "" + calendar.get(Calendar.HOUR)) + ":" + 
								(calendar.get(Calendar.MINUTE) < 10 ? "0" + calendar.get(Calendar.MINUTE) : "" + calendar.get(Calendar.MINUTE)) + ":" + 
								(calendar.get(Calendar.SECOND) < 10 ? "0" + calendar.get(Calendar.SECOND) : "" + calendar.get(Calendar.SECOND)) + " " + 
								AMPM;
			Paragraph TIME = new Paragraph(runtime, headerBoldFont);
			
			Paragraph RAYA =
				new Paragraph(
					"_________________________________________________________________________________________________________________________",
					normalBoldFont);
			Paragraph HEADERA =	new Paragraph(headerA, headerBoldFont);
			Paragraph HEADERP =	new Paragraph(headerP, headerBoldFont);
			Paragraph HEADER01 = new Paragraph(header01, headerBoldFont);
			Paragraph HEADER02 = new Paragraph(header02, headerBoldFont);
			Paragraph HEADER03 = new Paragraph(header03, headerBoldFont);
			Paragraph HEADER04 = new Paragraph(header04, headerBoldFont);
			Paragraph HEADER05 = new Paragraph(header05, headerBoldFont);
			Paragraph HEADER06 = new Paragraph(header06, headerBoldFont);
			Paragraph HEADER07 = new Paragraph(header07, headerBoldFont);
			Paragraph HEADERT =	new Paragraph(headerT, headerBoldFont);
			Paragraph APPROVED = new Paragraph(approved + user.getH01USR() + ", " + user.getE01NME().trim(), headerBoldFont);
	
			HeaderFooter header = new HeaderFooter(TITLE, false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(Element.ALIGN_CENTER);
			doc.setHeader(header);
	
			HeaderFooter footer = new HeaderFooter(new Phrase(page), true);
			footer.setBorder(Rectangle.TOP);
			footer.setAlignment(Element.ALIGN_RIGHT);
			doc.setFooter(footer);

			doc.open();
			
			if (image != null) doc.add(image);
			
			DATE.setAlignment(Element.ALIGN_RIGHT);
			doc.add(DATE);
			TIME.setAlignment(Element.ALIGN_RIGHT);
			doc.add(TIME);
			RAYA.setAlignment(Element.ALIGN_CENTER);
			doc.add(RAYA);
			
			int NumColumns = 7;
			
			Table table = new Table(NumColumns);
			int headerwidths[] = { 10, 10, 10, 20, 20, 10, 10 }; 	// columns width
			table.setWidths(headerwidths);
			table.setBorderWidth(0);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);
			table.setCellsFitPage(true);
	
			Cell cell = new Cell(HEADERA);
			cell.setHeader(true);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(NumColumns);
			table.addCell(cell);
			table.endHeaders();
			
			//Table Header
			cell = new Cell(HEADER01);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER02);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER07);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER03);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER04);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER05);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new Cell(HEADER06);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			doc.add(table);
			
			PdfPTable datatable = new PdfPTable(NumColumns);
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); 					// percentage
			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.getDefaultCell().setGrayFill(0.9f);
			
			String dit1 = "";
			String dit2 = "";
			String dit3 = "";
			String dit4 = "";
			String dit5 = "";
			String dit6 = "";
			String dit7 = "";
	
			int i = 0;
			JBObjList pendingList = new JBObjList();
			list.initRow();
			BigDecimal totalPndDebit = new BigDecimal(0);
			BigDecimal totalPndCredit = new BigDecimal(0);
			BigDecimal totalPndTrans = new BigDecimal(0);
			BigDecimal totalPndLotes = new BigDecimal(0);


			BigDecimal totalAprDebit = new BigDecimal(0);
			BigDecimal totalAprCredit = new BigDecimal(0);
			BigDecimal totalAprTrans = new BigDecimal(0);
			BigDecimal totalAprLotes = new BigDecimal(0);
				
			String total = "0.00";
			while (list.getNextRow()) {
				i++;
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				} else {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				EGL003001Message msg = (EGL003001Message) list.getRecord();
				if (msg.getH01FLGWK1().equals(PENDING)) {
					pendingList.add(msg);
					total = Util.takeComa(msg.getE01TOTDEB().toString());
					totalPndDebit = totalPndDebit.add(new BigDecimal(total));
					total = Util.takeComa(msg.getE01TOTCRE().toString());
					totalPndCredit = totalPndCredit.add(new BigDecimal(total));
					totalPndTrans = totalPndTrans.add(new BigDecimal(msg.getE01TOTTRN().toString()));
					totalPndLotes = totalPndLotes.add(new BigDecimal(1));
				} else {
					dit1 = msg.getE01BTHNUM().trim();
					Paragraph DIT1 = new Paragraph(dit1, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT1);
					dit2 = Util.formatDate(msg.getE01RUNDT1(),msg.getE01RUNDT2(),msg.getE01RUNDT3());
					Paragraph DIT2 = new Paragraph(dit2, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT2);
					dit7 = msg.getE01USERID().trim();
					Paragraph DIT7 = new Paragraph(dit7, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT7);
					dit3 = Util.formatCCY(msg.getE01TOTDEB());
					Paragraph DIT3 = new Paragraph(dit3, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT3);
					dit4 = Util.formatCCY(msg.getE01TOTCRE());
					Paragraph DIT4 = new Paragraph(dit4, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT4);
					dit5 = msg.getE01BTHNUM().trim();
					Paragraph DIT5 = new Paragraph(dit5, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT5);
					if (msg.getE01ACTION().equals(REJECTED)) {
						dit6 = "Rechazada";
					} else {
						if (msg.getE01ACTION().equals(FORCED)) {
							dit6 = "Forzada";
						} else {
							dit6 = "En Proceso";
						}
					}
					Paragraph DIT6 = new Paragraph(dit6, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT6);
					total = Util.takeComa(msg.getE01TOTDEB().toString());
					totalAprDebit = totalAprDebit.add(new BigDecimal(total));
					total = Util.takeComa(msg.getE01TOTCRE().toString());
					totalAprCredit = totalAprCredit.add(new BigDecimal(total));
					totalAprTrans = totalAprTrans.add(new BigDecimal(msg.getE01TOTTRN().toString()));
					totalAprLotes = totalAprLotes.add(new BigDecimal(1));
				}
			}
			HEADERT.setAlignment(Element.ALIGN_CENTER);
			PdfPCell pdfcell = new PdfPCell(HEADERT);
			pdfcell.setColspan(7);
			pdfcell.setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(pdfcell);

			dit1 = totalAprLotes.toString().trim();
			Paragraph TOL1 = new Paragraph(dit1, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL1);
			dit2 = "";
			Paragraph TOL2 = new Paragraph(dit2, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL2);
			dit7 = "";
			Paragraph TOL7 = new Paragraph(dit7, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL7);
			dit3 = Util.formatCCY(totalAprDebit.toString());
			Paragraph TOL3 = new Paragraph(dit3, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL3);
			dit4 = Util.formatCCY(totalAprCredit.toString());
			Paragraph TOL4 = new Paragraph(dit4, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL4);
			dit5 = totalAprTrans.toString().trim();
			Paragraph TOL5 = new Paragraph(dit5, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL5);
			dit6 = "";
			Paragraph TOL6 = new Paragraph(dit6, normalFont);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(TOL6);
			
			doc.add(datatable);
			
			if (!pendingList.isEmpty()) {
				table = new Table(NumColumns);
				table.setWidths(headerwidths);
				table.setBorderWidth(0);
				table.setPadding(1);
				table.setSpacing(1);
				table.setWidth(100);
				table.setCellsFitPage(true);
	
				cell = new Cell(HEADERP);
				cell.setHeader(true);
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setColspan(7);
				table.addCell(cell);
				table.endHeaders();
			
				cell = new Cell(HEADER01);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER02);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER07);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER03);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER04);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER05);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				cell = new Cell(HEADER06);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				doc.add(table);
			
				datatable = new PdfPTable(NumColumns);
				datatable.setWidths(headerwidths);
				datatable.setWidthPercentage(100); 					// percentage
				datatable.getDefaultCell().setBorderWidth(1);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.getDefaultCell().setGrayFill(0.9f);
			
				i = 0;
				pendingList.initRow();
				while (pendingList.getNextRow()) {
					i++;
					if (i % 2 == 1) {
						datatable.getDefaultCell().setGrayFill(0.0f);
					} else {
						datatable.getDefaultCell().setGrayFill(0.9f);
					}
					EGL003001Message msg = (EGL003001Message) pendingList.getRecord();
					dit1 = msg.getE01BTHNUM().trim();
					Paragraph DIT1 = new Paragraph(dit1, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT1);
					dit2 = Util.formatDate(msg.getE01RUNDT1(),msg.getE01RUNDT2(),msg.getE01RUNDT3());
					Paragraph DIT2 = new Paragraph(dit2, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT2);
					dit7 = msg.getE01USERID().trim();
					Paragraph DIT7 = new Paragraph(dit7, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT7);
					dit3 = Util.formatCCY(msg.getE01TOTDEB());
					Paragraph DIT3 = new Paragraph(dit3, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT3);
					dit4 = Util.formatCCY(msg.getE01TOTCRE());
					Paragraph DIT4 = new Paragraph(dit4, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT4);
					dit5 = msg.getE01BTHNUM().trim();
					Paragraph DIT5 = new Paragraph(dit5, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT5);
					if (msg.getE01ACTION().equals(REJECTED)) {
						dit6 = "Rechazada";
					} else {
						if (msg.getE01ACTION().equals(FORCED)) {
							dit6 = "Forzada";
						} else {
							dit6 = "En Proceso";
						}
					}
					Paragraph DIT6 = new Paragraph(dit6, normalFont);
					datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					datatable.addCell(DIT6);
				}
			
				HEADERT.setAlignment(Element.ALIGN_CENTER);
				pdfcell = new PdfPCell(HEADERT);
				pdfcell.setColspan(7);
				pdfcell.setBorderWidth(1);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(pdfcell);

				dit1 = totalPndLotes.toString().trim();
				TOL1 = new Paragraph(dit1, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL1);
				dit2 = "";
				TOL2 = new Paragraph(dit2, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL2);
				dit7 = "";
				TOL7 = new Paragraph(dit7, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL7);
				dit3 = Util.formatCCY(totalPndDebit.toString());
				TOL3 = new Paragraph(dit3, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL3);
				dit4 = Util.formatCCY(totalPndCredit.toString());
				TOL4 = new Paragraph(dit4, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL4);
				dit5 = totalPndTrans.toString().trim();
				TOL5 = new Paragraph(dit5, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL5);
				dit6 = "";
				TOL6 = new Paragraph(dit6, normalFont);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(TOL6);
				
				doc.add(datatable);
				
				RAYA.setAlignment(Element.ALIGN_CENTER);
				doc.add(RAYA);
				APPROVED.setAlignment(Element.ALIGN_LEFT);
				doc.add(APPROVED);
			}
		} finally {
			if (doc != null) doc.close();
			if (docWriter != null) docWriter.close();
		}
		
		return result;
	}
	
	private String sendEMail(String report, String user, String text){
		String result = "";
		SendEMail mail = new SendEMail(report);
		mail.setProperties("email");
		if (!user.equals("")) mail.setTo(user);
		if (!text.equals("")) mail.setText(text);
		mail.setSubject("Aprobación de Manifiesto de Transmisión de Transacciones");
		mail.send();
		if (mail.hasError()) {
			result = mail.getError();
		}
		return result;
	}
	
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procRequestTransList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		String PageToCall = "";
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EGL0030", req);
			EGL003001Message msg = (EGL003001Message) mp.getMessageRecord("EGL003001", user.getH01USR(), "0015");
			
			mp.sendMessage(msg);
			
			JBObjList list = mp.receiveMessageRecordList("H01FLGMAS");
			list.initRow();
			if (mp.hasError(list)) {
				ELEERRMessage msgError = (ELEERRMessage) list.getRecord();
				flexLog("Putting java beans into the session");
				session.setAttribute("error", msgError);
				PageToCall = "error_viewer.jsp";
			} else {
				msg = (EGL003001Message) list.getRecord();
				userPO.setBank(msg.getE01ORGBNK());
				userPO.setCusNum(user.getH01USR());
				userPO.setCusName(user.getE01NME());
				
				StringBuffer html = null;
				// Pending List
				JBObjList pendingList = new JBObjList();
				// Total Pending List
				JBObjList totalPend = new JBObjList();
				// Approval List
				JBObjList approvalList = new JBObjList();
				// Approval Pending List
				JBObjList totalAppr = new JBObjList();
				
				list.initRow();

				BigDecimal totalPndDebit = new BigDecimal(0);
				BigDecimal totalPndCredit = new BigDecimal(0);
				BigDecimal totalPndTrans = new BigDecimal(0);
				BigDecimal totalPndLotes = new BigDecimal(0);


				BigDecimal totalAprDebit = new BigDecimal(0);
				BigDecimal totalAprCredit = new BigDecimal(0);
				BigDecimal totalAprTrans = new BigDecimal(0);
				BigDecimal totalAprLotes = new BigDecimal(0);
				
				String total = "0.00";
				String batch = "0";
				int counter = 0;

				while (list.getNextRow()) {
					msg = (EGL003001Message) list.getRecord();
					batch = (msg.getE01BTHNUM() == null) ? "0" : msg.getE01BTHNUM();
					html = new StringBuffer("<TR>");
					html.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(msg.getE01BTHNUM()) + "</TD>");
					html.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msg.getE01RUNDT1(),msg.getE01RUNDT2(),msg.getE01RUNDT3()) + "</TD>");
					html.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msg.getE01USERID()) + "</TD>");
					html.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(msg.getE01TOTDEB()) + "</TD>");
					html.append("<TD NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(msg.getE01TOTCRE()) + "</TD>");
					html.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(msg.getE01TOTTRN()) + "</TD>");
					String status = "";
					if (msg.getE01ACTION().equals(REJECTED)) {
						status = "Rechazada";
					} else {
						if (msg.getE01ACTION().equals(FORCED)) {
							status = "Forzada";
						} else {
							status = "En Proceso";
						}
					}
					if (msg.getE01ACTION().equals(REJECTED)) counter++;
					html.append("<TD NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(status) + "</TD>");
					html.append("</TR>");
					if (msg.getH01FLGWK1().equals(PENDING)) {
						pendingList.add(html);
						total = Util.takeComa(msg.getE01TOTDEB().toString());
						totalPndDebit = totalPndDebit.add(new BigDecimal(total));
						total = Util.takeComa(msg.getE01TOTCRE().toString());
						totalPndCredit = totalPndCredit.add(new BigDecimal(total));
						totalPndTrans = totalPndTrans.add(new BigDecimal(msg.getE01TOTTRN().toString()));
						totalPndLotes = totalPndLotes.add(new BigDecimal(1));
					} else {
						approvalList.add(html);
						total = Util.takeComa(msg.getE01TOTDEB().toString());
						totalAprDebit = totalAprDebit.add(new BigDecimal(total));
						total = Util.takeComa(msg.getE01TOTCRE().toString());
						totalAprCredit = totalAprCredit.add(new BigDecimal(total));
						totalAprTrans = totalAprTrans.add(new BigDecimal(msg.getE01TOTTRN().toString()));
						totalAprLotes = totalAprLotes.add(new BigDecimal(1));
					}
				}
				//Total Approval
				html = new StringBuffer("<TR id=\"trdark\" >" );
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(totalAprLotes.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msg.getE01RUNDT1(),msg.getE01RUNDT2(),msg.getE01RUNDT3()) + "</TH>");
				html.append("<TD NOWRAP ALIGN=\"CENTER\"></TD>");
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(totalAprDebit.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(totalAprCredit.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(totalAprTrans.toString()) + "</TH>");
				String status = "";
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(status) + "</TH>");
				html.append("</TR>");
				totalAppr.add(html);
								
				//Total Pending
				html = new StringBuffer("<TR id=\"trdark\" >" );
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCell(totalPndLotes.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatDate(msg.getE01RUNDT1(),msg.getE01RUNDT2(),msg.getE01RUNDT3()) + "</TH>");
				html.append("<TD NOWRAP ALIGN=\"CENTER\"></TD>");
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(totalPndDebit.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"RIGHT\">" + Util.formatCCY(totalPndCredit.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(totalPndTrans.toString()) + "</TH>");
				html.append("<TH NOWRAP ALIGN=\"CENTER\">" + Util.formatCell(status) + "</TH>");
				html.append("</TR>");
				totalPend.add(html);

				flexLog("Putting java beans into the session");
				session.setAttribute("userPO", userPO);
				session.setAttribute("transList", list);
				session.setAttribute("pendingList", pendingList);
				session.setAttribute("approvalList", approvalList);
				session.setAttribute("totalPend", totalPend);
				session.setAttribute("totalAppr", totalAppr);
				req.setAttribute("rejected", new Integer(counter));
				req.setAttribute("email", getDefaultAddress());
				req.setAttribute("batch", batch);
				PageToCall = "EGL0030_aproval_trans_list.jsp";
			}
			
			forward(PageToCall, req, res);
		} finally {
			if (mp != null)	mp.close();
		}
	}
	
	private String getDefaultAddress() {
		PropertyResourceBundle appProp = (PropertyResourceBundle) PropertyResourceBundle.getBundle("email");
		return (appProp.getString("email.to") == null) ? "" : appProp.getString("email.to");
	}

}
