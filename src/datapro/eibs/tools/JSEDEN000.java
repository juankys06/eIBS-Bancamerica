package datapro.eibs.tools;

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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import java.math.BigDecimal;

import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDEN011DSMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.beans.JBObjList;

import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import com.datapro.generic.beanutil.BeanList;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

public class JSEDEN000 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	// entering options
	protected static final int R_SEARCH_BY_NAME = 100;
	protected static final int A_SEARCH_BY_NAME = 200;
	
	protected static final int R_SEARCH_BY_COUNTRY_CODE = 300;
	protected static final int A_SEARCH_BY_COUNTRY_CODE = 400;
	
	protected static final int R_SEARCH_BY_DENIAL_CODE = 500;
	protected static final int A_SEARCH_BY_DENIAL_CODE = 600;
	
	protected static final int R_PRINT_BY_NAME = 700;
	protected static final int R_PRINT_BY_COUNTRY_CODE = 800;
	protected static final int R_PRINT_BY_DENIAL_CODE = 900;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDEN000() {
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
	}

	/**
	 * This method was created in VisualAge.
	 */

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

	
		int screen = R_SEARCH_BY_NAME;

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
					// BEGIN CD
					// Request
					case R_SEARCH_BY_NAME :
						procReqSearchByname( msgUser, req, res, session);
						break;
					case R_SEARCH_BY_COUNTRY_CODE :
						procReqSearchByCountryCode(msgUser, req, res, session);
						break;
					case R_SEARCH_BY_DENIAL_CODE :
						procReqSearchByDenialCode( msgUser, req, res, session);
						break;
					case R_PRINT_BY_NAME :
						procReqPDF(mc, msgUser, req, res, session);
						break;
					case R_PRINT_BY_COUNTRY_CODE :
						procReqPDFCountry(mc, msgUser, req, res, session);
						break;
					case R_PRINT_BY_DENIAL_CODE :
						procReqPDFDenial(mc, msgUser, req, res, session);
						break;

						// Action
					case A_SEARCH_BY_NAME :
						procActionSearchByname(mc, msgUser, req, res, session);
						break;
					case A_SEARCH_BY_COUNTRY_CODE :
						procActionSearchByCountryCode(mc, msgUser, req, res, session);
						break;
					case A_SEARCH_BY_DENIAL_CODE :
						procActionSearchByDenialCode(mc, msgUser, req, res, session);
						break;

						// END Entering

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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearchByname(
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_name_enter.jsp");
			callPage(LangPath + "EDEN000_ofac_search_by_name_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqSearchByCountryCode(
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_country_enter.jsp");
			callPage(LangPath + "EDEN000_ofac_search_by_country_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqSearchByDenialCode(
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_denial_enter.jsp");
			callPage(LangPath + "EDEN000_ofac_search_by_denial_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionSearchByname(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDEN011DSMessage msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDEN011DSMessage) mc.getMessageRecord("EDEN011DS");
			msgList.setRWDUSR(user.getH01USR());
			
			try {
				msgList.setRWDTYP(req.getParameter("RWDTYP"));
			} catch (Exception e) {
				msgList.setRWDTYP("");
			}
			try {
				msgList.setRWDNAM(req.getParameter("RWDNAM"));
			} catch (Exception e) {
				msgList.setRWDNAM("");
			}
			try {
				msgList.setRWDCON(req.getParameter("RWDCON"));
			} catch (Exception e) {
				msgList.setRWDCON("");
			}
			try {
				msgList.setRWDFRC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msgList.setRWDFRC("");
			}
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();
			
			 if (newmessage.getFormatName().equals("EDEN011DS")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				boolean firstTime = true;

				while (true) {

					msgList = (EDEN011DSMessage) newmessage;
					msgList.setHandler(null);
					marker = msgList.getSWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getSWDREC()));
						}						
						beanList.addRow(msgList);
						
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}						
					}
					newmessage = mc.receiveMessage();
				}
				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("shrList", beanList);
				ses.setAttribute("error", msgError);

				try {
					ses.setAttribute("RWDTYP", req.getParameter("RWDTYP"));
					ses.setAttribute("RWDNAM", req.getParameter("RWDNAM"));
					ses.setAttribute("RWDCON", req.getParameter("RWDCON"));
					
					flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_name_list.jsp");
					callPage(LangPath + "EDEN000_ofac_search_by_name_list.jsp",req,res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procActionSearchByCountryCode(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDEN011DSMessage msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDEN011DSMessage) mc.getMessageRecord("EDEN011DS");
			msgList.setRWDUSR(user.getH01USR());
			
			msgList.setRWDTYP("C");

			try {
				msgList.setRWDNAM(req.getParameter("RWDNAM"));
			} catch (Exception e) {
				msgList.setRWDNAM("");
			}
			try {
				msgList.setRWDFRC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msgList.setRWDFRC("");
			}
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();
			
			 if (newmessage.getFormatName().equals("EDEN011DS")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				boolean firstTime = true;

				while (true) {

					msgList = (EDEN011DSMessage) newmessage;
					msgList.setHandler(null);
					marker = msgList.getSWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getSWDREC()));
						}						
						beanList.addRow(msgList);
						
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}						
					}
					newmessage = mc.receiveMessage();
				}
				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("shrList", beanList);
				ses.setAttribute("error", msgError);

				try {
					ses.setAttribute("RWDNAM", req.getParameter("RWDNAM"));
					
					flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_country_list.jsp");
					callPage(LangPath + "EDEN000_ofac_search_by_country_list.jsp",req,res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	
	
	protected void procActionSearchByDenialCode(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			ELEERRMessage msgError = null;
			EDEN011DSMessage msgList = null;
			JBObjList beanList = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			try {
				msgError = new ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgList = (EDEN011DSMessage) mc.getMessageRecord("EDEN011DS");
				msgList.setRWDUSR(user.getH01USR());
			
				msgList.setRWDTYP("D");

				try {
					msgList.setRWDNAM(req.getParameter("RWDNAM"));
				} catch (Exception e) {
					msgList.setRWDNAM("");
				}
				try {
					msgList.setRWDFRC(req.getParameter("FromRecord"));
				} catch (Exception e) {
					msgList.setRWDFRC("");
				}
			
				msgList.send();
				msgList.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Message
			try {
				newmessage = mc.receiveMessage();
			
				 if (newmessage.getFormatName().equals("EDEN011DS")) {

					try {
						beanList = new JBObjList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					String marker = "";
					boolean firstTime = true;

					while (true) {

						msgList = (EDEN011DSMessage) newmessage;
						msgList.setHandler(null);
						marker = msgList.getSWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(Integer.parseInt(msgList.getSWDREC()));
							}						
							beanList.addRow(msgList);
						
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}						
						}
						newmessage = mc.receiveMessage();
					}
					//
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("shrList", beanList);
					ses.setAttribute("error", msgError);

					try {
						ses.setAttribute("RWDNAM", req.getParameter("RWDNAM"));
					
						flexLog("About to call Page: " + LangPath + "EDEN000_ofac_search_by_denial_list.jsp");
						callPage(LangPath + "EDEN000_ofac_search_by_denial_list.jsp",req,res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				
				}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}		
	protected void procReqPDF(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		String senderror = "1";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		DocumentException ex = null;
		ByteArrayOutputStream baosPDF = null;

		try {
			baosPDF =
				generatePDFDocumentBytes(
					req,
					this.getServletContext(),
					ses,
					false);

			StringBuffer sbFilename = new StringBuffer();
			//sbFilename.append(msgHeader.getBigDecimalE02ACMACC());
			//sbFilename.append(System.currentTimeMillis());
			String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
			fn = Util.replace(fn,":","-");
			fn = Util.replace(fn,".","-");
			sbFilename.append(fn);
			sbFilename.append(".pdf");

			res.setHeader("Cache-Control", "s-maxage=0");

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
		
		} catch (DocumentException dex) {
			res.setContentType("text/html");
			PrintWriter writer = res.getWriter();
			writer.println(
				this.getClass().getName()
					+ " caught an exception: "
					+ dex.getClass().getName()
					+ "<br>");
			writer.println("<pre>");
			dex.printStackTrace(writer);
			writer.println("</pre>");
		} finally {
			if (baosPDF != null) {
				baosPDF.reset();
			}
		}

		return;

	}

	protected ByteArrayOutputStream generatePDFDocumentBytes(
		final HttpServletRequest req,
		final ServletContext ctx,
		HttpSession session,
		boolean FLG)
		throws DocumentException {

		ESS0030DSMessage msgUser = null;
		msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
		String LNG = msgUser.getE01LAN();

		EDEN011DSMessage msgSearch = null;
		JBObjList beanList = null;
		
		beanList = (JBObjList) session.getAttribute("shrList");

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
				
		String title = "Office of Foreign and Assets Control (OFAC Search)";

		String header01 = "Name: ";
		String header02 = "Search by: ";
		String header03 = "Coincidences: ";
		
		String detail01 = "Name";
		String detail02 = "Count";

		String page = "Page Number";

		String name = "";
		try {
			name = Util.unformatHTML(req.getParameter("RWDNAM"));
		} catch (Exception e) {}
		String typ = "";
		try {
			typ = Util.unformatHTML(req.getParameter("RWDTYP"));
			if (typ.equals("S")) {
				typ = "Sounds";
			} if (typ.equals("W")) {
				typ = "Words";
			} else {
				typ = "Full Name";
			}
		} catch (Exception e) {}
		String coinc = "";
		try {
			coinc = Util.unformatHTML(req.getParameter("RWDCON"));
		} catch (Exception e) {}

		Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try {
			docWriter = PdfWriter.getInstance(doc, baosPDF);

			doc.addAuthor("eIBS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addTitle(title);
			doc.addKeywords("pdf, itext, Java, open source, http");

			Font normalFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
			Font normalBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
			Font normalBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					8,
					Font.BOLD | Font.UNDERLINE);
			Font headerFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
			Font headerBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
			Font headerBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					10,
					Font.BOLD | Font.UNDERLINE);
			
			Paragraph TITLE = new Paragraph(title, headerBoldFont);

			Paragraph RAYA =
				new Paragraph(
					"____________________________________________________________________________________________________________________",
					normalBoldFont);

			Paragraph HEADER01 =
				new Paragraph(header01 + "  " + name, headerBoldFont);
			Paragraph HEADER02 =
				new Paragraph(header02 + "  " + typ, headerBoldFont);
			Paragraph HEADER03 =
				new Paragraph(header03 + "  " + coinc, headerBoldFont);
			
			Paragraph BLANK = new Paragraph("", headerBoldFont);

			Paragraph DETAIL1 = new Paragraph(detail01, headerBoldFont);
			Paragraph DETAIL2 = new Paragraph(detail02, headerBoldFont);

			HeaderFooter header = new HeaderFooter(TITLE, false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(Element.ALIGN_CENTER);
			doc.setHeader(header);

			HeaderFooter footer = new HeaderFooter(new Phrase(page), false);
			footer.setBorder(Rectangle.NO_BORDER);
			doc.setFooter(footer);

			doc.open();

			Table table = new Table(1, 3);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			Cell cell = new Cell(HEADER01);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER02);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER03);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

			int NumColumns = 2;
			PdfPTable datatable = new PdfPTable(NumColumns);

			datatable.getDefaultCell().setPadding(3);
			int headerwidths[] = { 80, 20}; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage

			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.getDefaultCell().setGrayFill(0.9f);
			datatable.addCell(DETAIL1);
			datatable.addCell(DETAIL2);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);

			beanList.initRow();

			int i = 0;
			String dit1 = "";
			String dit2 = "";

			while (beanList.getNextRow()) {
				i++;
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				} else {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}

				msgSearch = new EDEN011DSMessage();
				msgSearch = (EDEN011DSMessage) beanList.getRecord();

				dit1 = msgSearch.getSWDNAM();
				dit2 = msgSearch.getSWDCOU();

				Paragraph DIT11 = new Paragraph(dit1, normalFont);
				Paragraph DIT21 = new Paragraph(dit2, normalFont);

				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_LEFT);
				datatable.addCell(DIT11);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT21);
				
			}

			doc.add(datatable);

			table = new Table(1, 1);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

		} catch (DocumentException dex) {
			baosPDF.reset();
			throw dex;
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}

		if (baosPDF.size() < 1) {
			throw new DocumentException(
				"document has " + baosPDF.size() + " bytes");
		}

		return baosPDF;
	}
	protected void procReqPDFCountry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		String senderror = "1";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		DocumentException ex = null;
		ByteArrayOutputStream baosPDF = null;

		try {
			baosPDF =
				generatePDFCountryDocumentBytes(
					req,
					this.getServletContext(),
					ses,
					false);

			StringBuffer sbFilename = new StringBuffer();
			//sbFilename.append(msgHeader.getBigDecimalE02ACMACC());
			//sbFilename.append(System.currentTimeMillis());
			String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
			fn = Util.replace(fn,":","-");
			fn = Util.replace(fn,".","-");
			sbFilename.append(fn);
			sbFilename.append(".pdf");

			res.setHeader("Cache-Control", "s-maxage=0");

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
		
		} catch (DocumentException dex) {
			res.setContentType("text/html");
			PrintWriter writer = res.getWriter();
			writer.println(
				this.getClass().getName()
					+ " caught an exception: "
					+ dex.getClass().getName()
					+ "<br>");
			writer.println("<pre>");
			dex.printStackTrace(writer);
			writer.println("</pre>");
		} finally {
			if (baosPDF != null) {
				baosPDF.reset();
			}
		}

		return;

	}

	protected ByteArrayOutputStream generatePDFCountryDocumentBytes(
		final HttpServletRequest req,
		final ServletContext ctx,
		HttpSession session,
		boolean FLG)
		throws DocumentException {

		ESS0030DSMessage msgUser = null;
		msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
		String LNG = msgUser.getE01LAN();

		EDEN011DSMessage msgSearch = null;
		JBObjList beanList = null;
		
		beanList = (JBObjList) session.getAttribute("shrList");

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
				
		String title = "Office of Foreign and Assets Control (OFAC Search)";

		String header01 = "Country Code: ";
		
		String detail01 = "Name";
		String detail02 = "Count";

		String page = "Page Number";

		String name = "";
		try {
			name = Util.unformatHTML(req.getParameter("RWDNAM"));
		} catch (Exception e) {}

		Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try {
			docWriter = PdfWriter.getInstance(doc, baosPDF);

			doc.addAuthor("eIBS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addTitle(title);
			doc.addKeywords("pdf, itext, Java, open source, http");

			Font normalFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
			Font normalBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
			Font normalBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					8,
					Font.BOLD | Font.UNDERLINE);
			Font headerFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
			Font headerBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
			Font headerBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					10,
					Font.BOLD | Font.UNDERLINE);
			
			Paragraph TITLE = new Paragraph(title, headerBoldFont);

			Paragraph RAYA =
				new Paragraph(
					"____________________________________________________________________________________________________________________",
					normalBoldFont);

			Paragraph HEADER01 =
				new Paragraph(header01 + "  " + name, headerBoldFont);
			
			Paragraph BLANK = new Paragraph("", headerBoldFont);

			Paragraph DETAIL1 = new Paragraph(detail01, headerBoldFont);
			Paragraph DETAIL2 = new Paragraph(detail02, headerBoldFont);

			HeaderFooter header = new HeaderFooter(TITLE, false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(Element.ALIGN_CENTER);
			doc.setHeader(header);

			HeaderFooter footer = new HeaderFooter(new Phrase(page), false);
			footer.setBorder(Rectangle.NO_BORDER);
			doc.setFooter(footer);

			doc.open();

			Table table = new Table(1, 3);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			Cell cell = new Cell(HEADER01);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

			int NumColumns = 2;
			PdfPTable datatable = new PdfPTable(NumColumns);

			datatable.getDefaultCell().setPadding(3);
			int headerwidths[] = { 80, 20}; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage

			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.getDefaultCell().setGrayFill(0.9f);
			datatable.addCell(DETAIL1);
			datatable.addCell(DETAIL2);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);

			beanList.initRow();

			int i = 0;
			String dit1 = "";
			String dit2 = "";

			while (beanList.getNextRow()) {
				i++;
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				} else {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}

				msgSearch = new EDEN011DSMessage();
				msgSearch = (EDEN011DSMessage) beanList.getRecord();

				dit1 = msgSearch.getSWDNAM();
				dit2 = msgSearch.getSWDCOU();

				Paragraph DIT11 = new Paragraph(dit1, normalFont);
				Paragraph DIT21 = new Paragraph(dit2, normalFont);

				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_LEFT);
				datatable.addCell(DIT11);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT21);
				
			}

			doc.add(datatable);

			table = new Table(1, 1);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

		} catch (DocumentException dex) {
			baosPDF.reset();
			throw dex;
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}

		if (baosPDF.size() < 1) {
			throw new DocumentException(
				"document has " + baosPDF.size() + " bytes");
		}

		return baosPDF;
	}

	protected void procReqPDFDenial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		String senderror = "1";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		DocumentException ex = null;
		ByteArrayOutputStream baosPDF = null;

		try {
			baosPDF =
				generatePDFDenialDocumentBytes(
					req,
					this.getServletContext(),
					ses,
					false);

			StringBuffer sbFilename = new StringBuffer();
			//sbFilename.append(msgHeader.getBigDecimalE02ACMACC());
			//sbFilename.append(System.currentTimeMillis());
			String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
			fn = Util.replace(fn,":","-");
			fn = Util.replace(fn,".","-");
			sbFilename.append(fn);
			sbFilename.append(".pdf");

			res.setHeader("Cache-Control", "s-maxage=0");

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
		
		} catch (DocumentException dex) {
			res.setContentType("text/html");
			PrintWriter writer = res.getWriter();
			writer.println(
				this.getClass().getName()
					+ " caught an exception: "
					+ dex.getClass().getName()
					+ "<br>");
			writer.println("<pre>");
			dex.printStackTrace(writer);
			writer.println("</pre>");
		} finally {
			if (baosPDF != null) {
				baosPDF.reset();
			}
		}

		return;

	}

	protected ByteArrayOutputStream generatePDFDenialDocumentBytes(
		final HttpServletRequest req,
		final ServletContext ctx,
		HttpSession session,
		boolean FLG)
		throws DocumentException {

		ESS0030DSMessage msgUser = null;
		msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
		String LNG = msgUser.getE01LAN();

		EDEN011DSMessage msgSearch = null;
		JBObjList beanList = null;
		
		beanList = (JBObjList) session.getAttribute("shrList");

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
				
		String title = "Office of Foreign and Assets Control (OFAC Search)";

		String header01 = "Denial Code: ";
		
		String detail01 = "Name";
		String detail02 = "Count";

		String page = "Page Number";

		String name = "";
		try {
			name = Util.unformatHTML(req.getParameter("RWDNAM"));
		} catch (Exception e) {}

		Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try {
			docWriter = PdfWriter.getInstance(doc, baosPDF);

			doc.addAuthor("eIBS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addTitle(title);
			doc.addKeywords("pdf, itext, Java, open source, http");

			Font normalFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
			Font normalBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
			Font normalBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					8,
					Font.BOLD | Font.UNDERLINE);
			Font headerFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
			Font headerBoldFont =
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
			Font headerBoldUnderFont =
				FontFactory.getFont(
					FontFactory.HELVETICA,
					10,
					Font.BOLD | Font.UNDERLINE);
			
			Paragraph TITLE = new Paragraph(title, headerBoldFont);

			Paragraph RAYA =
				new Paragraph(
					"____________________________________________________________________________________________________________________",
					normalBoldFont);

			Paragraph HEADER01 =
				new Paragraph(header01 + "  " + name, headerBoldFont);
			
			Paragraph BLANK = new Paragraph("", headerBoldFont);

			Paragraph DETAIL1 = new Paragraph(detail01, headerBoldFont);
			Paragraph DETAIL2 = new Paragraph(detail02, headerBoldFont);

			HeaderFooter header = new HeaderFooter(TITLE, false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(Element.ALIGN_CENTER);
			doc.setHeader(header);

			HeaderFooter footer = new HeaderFooter(new Phrase(page), false);
			footer.setBorder(Rectangle.NO_BORDER);
			doc.setFooter(footer);

			doc.open();

			Table table = new Table(1, 3);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			Cell cell = new Cell(HEADER01);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

			int NumColumns = 2;
			PdfPTable datatable = new PdfPTable(NumColumns);

			datatable.getDefaultCell().setPadding(3);
			int headerwidths[] = { 80, 20}; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage

			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.getDefaultCell().setGrayFill(0.9f);
			datatable.addCell(DETAIL1);
			datatable.addCell(DETAIL2);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);

			beanList.initRow();

			int i = 0;
			String dit1 = "";
			String dit2 = "";

			while (beanList.getNextRow()) {
				i++;
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				} else {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}

				msgSearch = new EDEN011DSMessage();
				msgSearch = (EDEN011DSMessage) beanList.getRecord();

				dit1 = msgSearch.getSWDNAM();
				dit2 = msgSearch.getSWDCOU();

				Paragraph DIT11 = new Paragraph(dit1, normalFont);
				Paragraph DIT21 = new Paragraph(dit2, normalFont);

				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_LEFT);
				datatable.addCell(DIT11);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT21);
				
			}

			doc.add(datatable);

			table = new Table(1, 1);
			table.setBorderWidth(0);
			table.setCellsFitPage(true);
			table.setPadding(1);
			table.setSpacing(1);
			table.setWidth(100);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			doc.add(table);

		} catch (DocumentException dex) {
			baosPDF.reset();
			throw dex;
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}

		if (baosPDF.size() < 1) {
			throw new DocumentException(
				"document has " + baosPDF.size() + " bytes");
		}

		return baosPDF;
	}

}