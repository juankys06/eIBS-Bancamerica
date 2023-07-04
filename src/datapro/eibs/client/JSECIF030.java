package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import datapro.eibs.beans.ECIF03001Message;
import datapro.eibs.beans.ECIF03002Message;
import datapro.eibs.beans.EDD009001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

import datapro.eibs.generic.SimpleAthenticator;

import java.util.Hashtable;
import java.util.ArrayList;

public class JSECIF030 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_LIST = 1;
	protected static final int R_SEARCH = 2;
	protected static final int A_SEARCH = 3;
	protected static final int R_PRINT = 4;
	protected static final int R_DESC = 5;
	protected static final int R_PDF = 6;
	protected static final int R_EMAIL = 7;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECIF030() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

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
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		userPO.setHeader7(req.getParameter("E01HISCYC"));
		userPO.setHeader8(req.getParameter("E01VALBTH"));

		try {
			userPO.setHeader9(req.getParameter("E01FRDTE1"));
			userPO.setHeader10(req.getParameter("E01FRDTE2"));
			userPO.setHeader11(req.getParameter("E01FRDTE3"));
		} catch (Exception e) {
			flexLog("DATE 1");
		}
		try {
			userPO.setHeader12(req.getParameter("E01TODTE1"));
			userPO.setHeader13(req.getParameter("E01TODTE2"));
			userPO.setHeader14(req.getParameter("E01TODTE3"));
		} catch (Exception e) {
			flexLog("DATE 2");
		}
		try {
			userPO.setHeader15(req.getParameter("E01FRCHKN"));
		} catch (Exception e) {
			flexLog("E01FRCHKN");
		}
		try {
			userPO.setHeader16(req.getParameter("E01TOCHKN"));
		} catch (Exception e) {
			flexLog("E01TOCHKN");
		}

		try {
			userPO.setHeader17(req.getParameter("E01FRAMNT"));
		} catch (Exception e) {
			flexLog("E01FRAMNT");
		}
		try {
			userPO.setHeader18(req.getParameter("E01TOAMNT"));
		} catch (Exception e) {
			flexLog("E01TOAMNT");
		}

		ses.setAttribute("userPO", userPO);

		procReqList(mc, user, req, res, ses);
	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionSTEnterSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD009001Message msgRT = null;
		ELEERRMessage msgError = null;
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
			msgRT = (EDD009001Message) mc.getMessageRecord("EDD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01ACMACC(req.getParameter("E01ACMACC"));
			} catch (Exception e) {
				msgRT.setE01ACMACC(userPO.getIdentifier());
			}

			msgRT.send();
			msgRT.destroy();
			flexLog("EDD09001 Message Sent");
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
			flexLog("Read error " + e);
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD009001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD009001Message();
					flexLog("EDD09001 Message Received");
				} catch (Exception ex) {
					flexLog("EDD09001 Error: " + ex);
				}

				msgRT = (EDD009001Message) newmessage;

				if (IsNotError) { // There are no errors
					try {
						userPO.setIdentifier(msgRT.getE01ACMACC());
						userPO.setHeader1(msgRT.getE01ACMPRO());
						userPO.setCusNum(msgRT.getE01ACMCUN());
						userPO.setHeader2(msgRT.getE01ACMCUN());
						userPO.setHeader3(msgRT.getE01CUSNA1());
						userPO.setCurrency(msgRT.getE01ACMCCY());
						userPO.setOfficer(
							msgRT.getE01ACMOFC()
								+ " - "
								+ msgRT.getE01DSCOFC());

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("userPO", userPO);

						flexLog("Calling Request");
						procReqSearch(user, req, res, ses);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						ses.setAttribute("error", msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECIF030_st_enter_stat.jsp");
						callPage(
							LangPath + "ECIF030_st_enter_stat.jsp",
							req,
							res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECIF03001Message msgSearch = null;
		ECIF03001Message msgList = null;
		ECIF03002Message msgHeader = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECIF03001Message) mc.getMessageRecord("ECIF03001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECIF030");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0004");

			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgSearch.setE01NUMREC(req.getParameter("Pos"));
				} catch (Exception e) {
					msgSearch.setE01NUMREC("0");
					flexLog("E01NUMPOS");
				}

				try {
					msgSearch.setE01NUMACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E01NUMACC");
				}
				msgSearch.setE01HISCYC(userPO.getHeader7());
				msgSearch.setE01VALBTH(userPO.getHeader8());

				try {
					msgSearch.setE01FRDTE1(userPO.getHeader9());
					msgSearch.setE01FRDTE2(userPO.getHeader10());
					msgSearch.setE01FRDTE3(userPO.getHeader11());
				} catch (Exception e) {
					flexLog("DATE 1");
				}
				try {
					msgSearch.setE01TODTE1(userPO.getHeader12());
					msgSearch.setE01TODTE2(userPO.getHeader13());
					msgSearch.setE01TODTE3(userPO.getHeader14());
				} catch (Exception e) {
					flexLog("DATE 2");
				}
				try {
					msgSearch.setE01FRCHKN(userPO.getHeader15());
				} catch (Exception e) {
					flexLog("E01FRCHKN");
				}
				try {
					msgSearch.setE01TOCHKN(userPO.getHeader16());
				} catch (Exception e) {
					flexLog("E01TOCHKN");
				}

				try {
					msgSearch.setE01FRAMNT(userPO.getHeader17());
				} catch (Exception e) {
					flexLog("E01FRAMNT");
				}
				try {
					msgSearch.setE01TOAMNT(userPO.getHeader18());
				} catch (Exception e) {
					flexLog("E01TOAMNT");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECIF03001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECIF030_st_selection.jsp");
					callPage(LangPath + "ECIF030_st_selection.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECIF03002")) {
				try {
					msgHeader = new datapro.eibs.beans.ECIF03002Message();
				} catch (Exception ex) {
					flexLog("ECIF0302 Error: " + ex);
				}

				msgHeader = (ECIF03002Message) newmessage;
				
				ses.setAttribute("interestTable",formatInterestTable(msgHeader));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("stBalances", msgHeader);

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ECIF03001")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("ECIF03001 Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String grpData = "";
					String grpDate = "";
					String acnu = "";
					String checkgraph = "";

					String strDebit = "";
					String strCredit = "";
					String chknum = "";
					String DT1 = "";
					String DT2 = "";
					String DT3 = "";
					java.math.BigDecimal debit = new java.math.BigDecimal("0");
					java.math.BigDecimal credit = new java.math.BigDecimal("0");
					int row = 0;
					while (true) {

						msgList = (ECIF03001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(
									Integer.parseInt(msgList.getE01NUMREC()));
								grpData = msgList.getE01ENDBAL();
								grpDate =
									Util.formatDate(
										msgList.getE01DATE21(),
										msgList.getE01DATE22(),
										msgList.getE01DATE23());
								if (posi == 0) {
									userPO.setHeader5(msgList.getE01BEGBAL());
								}
								chk = "checked";
							} else {
								chk = "";
								grpData =
									grpData + "|" + msgList.getE01ENDBAL();
								grpDate =
									grpDate
										+ "|"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23());

							}

							if (msgList.getE01TRADCC().equals("0")) {
								debit =
									debit.add(msgList.getBigDecimalE01TRAAMT());
								strDebit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
								strCredit = "&nbsp;";
							} else if (msgList.getE01TRADCC().equals("5")) {
								credit =
									credit.add(
										msgList.getBigDecimalE01TRAAMT());
								strDebit = "&nbsp;";
								strCredit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
							}

							DT1 =
								(userPO.getHeader8().equals("B"))
									? msgList.getE01DATE11()
									: msgList.getE01DATE21();
							DT2 =
								(userPO.getHeader8().equals("B"))
									? msgList.getE01DATE12()
									: msgList.getE01DATE22();
							DT3 =
								(userPO.getHeader8().equals("B"))
									? msgList.getE01DATE13()
									: msgList.getE01DATE23();

							try {
								chknum =
									Util.addLeftChar(
										'0',
										6,
										msgList.getE01TRACKN());
								acnu =
									Util.addLeftChar(
										'0',
										9,
										msgList.getE01NUMACC());
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}

							if (chknum.equals("0")) {
								checkgraph = " ";
							} else {

								checkgraph =
									"<A HREF=\"javascript:showCheckImage('"
										+ acnu
										+ "','"
										+ chknum
										+ "','"
										+ msgList.getE01TRAPD1()
										+ "','"
										+ msgList.getE01TRAPD2()
										+ "','"
										+ msgList.getE01TRAPD3()
										+ "','"
										+ msgList.getE01TRAAMT()
										+ "','"
										+ row
										+ "')\">"
										+ chknum
										+ "</A>";
							}

							myRow = new StringBuffer("<TR>");
							if (userPO.getHeader8().equals("B")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:GetBatchDet('"
										+ msgList.getE01DATE11()
										+ "','"
										+ msgList.getE01DATE12()
										+ "','"
										+ msgList.getE01DATE13()
										+ "','"
										+ msgList.getE01TRABTH()
										+ "','"
										+ msgList.getE01TRAACR()
										+ "')\">"
										+ Util.formatDate(
											msgList.getE01DATE11(),
											msgList.getE01DATE12(),
											msgList.getE01DATE13())
										+ "</A></TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ Util.formatDate(
											msgList.getE01DATE11(),
											msgList.getE01DATE12(),
											msgList.getE01DATE13())
										+ "</TD>");
							}
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\">"
									+ Util.formatDate(
										msgList.getE01TRAPD1(),
										msgList.getE01TRAPD2(),
										msgList.getE01TRAPD3())
									+ "</TD>");

							if (msgList.getE01TRACDE().equals("CK")
								|| msgList.getE01TRACDE().equals("K1")
								|| msgList.getE01TRACDE().equals("K5")
								|| msgList.getE01TRACDE().equals("CQ")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"RIGHT\">"
										+ checkgraph
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"RIGHT\"><a href=\"javascript:showTransfer('"
										+ msgList.getH01FLGWK3()
										+ Util.justifyRight(
											msgList.getE01TRACKN(),
											6)
										+ "')\">"
										+ msgList.getH01FLGWK3()
										+ " - "
										+ msgList.getE01TRACKN()
										+ "</a></TD>");
							}

							if (msgList.getE01TRACDE().equalsIgnoreCase("CK")
								|| msgList.getE01TRACDE().equalsIgnoreCase("OF")
								|| msgList.getE01TRACDE().equalsIgnoreCase(
									"DP")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ checkgraph
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ Util.formatCell(msgList.getE01TRACDE())
										+ "</TD>");
							}
							if (msgList.getE01TRADRR().equals("0")) {
								myRow.append(
									"<TD NOWRAP>"
										+ Util.formatCell(msgList.getE01TRANAR())
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP><A HREF=\"javascript:GetStatDesc('"
										+ msgList.getE01TRADRR()
										+ "','"
										+ msgList.getE01TRANAR()
										+ "','"
										+ Util.formatDate(
											msgList.getE01DATE11(),
											msgList.getE01DATE12(),
											msgList.getE01DATE13())
										+ "','"
										+ Util.formatCell(msgList.getE01TRACDE())
										+ "')\">"
										+ Util.formatCell(msgList.getE01TRANAR())
										+ "</A></TD>");
							}

							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>"
									+ strCredit
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>"
									+ Util.fcolorCCY(msgList.getE01ENDBAL())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:GetBatchDet('"
									+ DT1
									+ "','"
									+ DT2
									+ "','"
									+ DT3
									+ "','"
									+ msgList.getE01TRABTH()
									+ "','"
									+ msgList.getE01TRAACR()
									+ "')\">"
									+ Util.formatCell(msgList.getE01TRABTH())
									+ "</A></TD>");
							if (userPO.getHeader8().equals("V")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:GetBatchDet('"
										+ msgList.getE01DATE21()
										+ "','"
										+ msgList.getE01DATE22()
										+ "','"
										+ msgList.getE01DATE23()
										+ "','"
										+ msgList.getE01TRABTH()
										+ "','"
										+ msgList.getE01TRAACR()
										+ "')\">"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23())
										+ "</A></TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=CENTER>"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23())
										+ "</TD>");
							}
							myRow.append(
								"<TD NOWRAP ALIGN=CENTER>"
									+ Util.formatTime(msgList.getE01TRATIM())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP>"
									+ Util.formatCell(msgList.getE01TRAUSR())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=CENTER>"
									+ Util.formatCell(msgList.getE01TRAOBK())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=CENTER>"
									+ Util.formatCell(msgList.getE01TRAOBR())
									+ "</TD>");
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());
							row++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader19(Util.fcolorCCY(debit.toString()));
					userPO.setHeader20(Util.fcolorCCY(credit.toString()));
					userPO.setHeader21(grpData);
					userPO.setHeader22(grpDate);

					flexLog("Putting java beans into the session");
					ses.setAttribute("cifList", beanList);

					try {
						if (msgList.getE01VALBTH().equals("V")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECIF030_st_list_fv.jsp");
							callPage(
								LangPath + "ECIF030_st_list_fv.jsp",
								req,
								res);
						} else if (msgList.getE01VALBTH().equals("B")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECIF030_st_list_fp.jsp");
							callPage(
								LangPath + "ECIF030_st_list_fp.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}
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
	protected void procReqPrintList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECIF03001Message msgSearch = null;
		ECIF03001Message msgList = null;
		ECIF03002Message msgHeader = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECIF03001Message) mc.getMessageRecord("ECIF03001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECIF030");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0004");
			msgSearch.setH01FLGWK1("P");
			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgSearch.setE01NUMREC(req.getParameter("Pos"));
				} catch (Exception e) {
					msgSearch.setE01NUMREC("0");
					flexLog("E01NUMPOS");
				}
				try {
					msgSearch.setE01NUMACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E01NUMACC");
				}
				msgSearch.setE01HISCYC(userPO.getHeader7());
				msgSearch.setE01VALBTH(userPO.getHeader8());

				try {
					msgSearch.setE01FRDTE1(userPO.getHeader9());
					msgSearch.setE01FRDTE2(userPO.getHeader10());
					msgSearch.setE01FRDTE3(userPO.getHeader11());
				} catch (Exception e) {
					flexLog("DATE 1");
				}
				try {
					msgSearch.setE01TODTE1(userPO.getHeader12());
					msgSearch.setE01TODTE2(userPO.getHeader13());
					msgSearch.setE01TODTE3(userPO.getHeader14());
				} catch (Exception e) {
					flexLog("DATE 2");
				}
				try {
					msgSearch.setE01FRCHKN(userPO.getHeader15());
				} catch (Exception e) {
					flexLog("E01FRCHKN");
				}
				try {
					msgSearch.setE01TOCHKN(userPO.getHeader16());
				} catch (Exception e) {
					flexLog("E01TOCHKN");
				}

				try {
					msgSearch.setE01FRAMNT(userPO.getHeader17());
				} catch (Exception e) {
					flexLog("E01FRAMNT");
				}
				try {
					msgSearch.setE01TOAMNT(userPO.getHeader18());
				} catch (Exception e) {
					flexLog("E01TOAMNT");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECIF030_st_selection.jsp");
					callPage(LangPath + "ECIF030_st_selection.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECIF03002")) {
				try {
					msgHeader = new datapro.eibs.beans.ECIF03002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgHeader = (ECIF03002Message) newmessage;
				
				ses.setAttribute("interestTable",formatInterestTable(msgHeader));
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("stBalances", msgHeader);

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ECIF03001")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";

					String strDebit = "";
					String strCredit = "";
					java.math.BigDecimal debit = new java.math.BigDecimal("0");
					java.math.BigDecimal credit = new java.math.BigDecimal("0");
					int countDebit = 0;
					int countCredit = 0;

					while (true) {

						msgList = (ECIF03001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(
									Integer.parseInt(msgList.getE01NUMREC()));
								if (posi == 0) {
									userPO.setHeader5(msgList.getE01BEGBAL());
								}
								chk = "checked";
							} else {
								chk = "";
							}

							if (msgList.getE01TRADCC().equals("0")) {
								debit =
									debit.add(msgList.getBigDecimalE01TRAAMT());
								countDebit++;
								strDebit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
								strCredit = "&nbsp;";
							} else if (msgList.getE01TRADCC().equals("5")) {
								credit =
									credit.add(
										msgList.getBigDecimalE01TRAAMT());
								countCredit++;
								strDebit = "&nbsp;";
								strCredit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
							}

							myRow = new StringBuffer("<TR>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\">"
									+ Util.formatDate(
										msgList.getE01DATE11(),
										msgList.getE01DATE12(),
										msgList.getE01DATE13())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"RIGHT\">"
									+ Util.formatCell(msgList.getE01TRACKN())
									+ "</TD>");
							if (msgList.getE01NUMNAR().equals("0")) {
								myRow.append(
									"<TD>"
										+ Util.formatCell(msgList.getE01TRANAR())
										+ "</TD>");
							} else {
								if (msgList
									.getE01NUMNAR()
									.trim()
									.equals("1")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"2")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"3")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"4")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"5")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA5())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"6")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA5())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA6())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"7")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA5())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA6())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA7())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"8")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA5())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA6())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA7())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA8())
											+ "</TD>");
								} else if (
									msgList.getE01NUMNAR().trim().equals(
										"9")) {
									myRow.append(
										"<TD NOWRAP>"
											+ Util.formatCell(
												msgList.getE01TRANAR())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA1())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA2())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA3())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA4())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA5())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA6())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA7())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA8())
											+ "<BR>"
											+ Util.formatCell(
												msgList.getE01TRANA9())
											+ "</TD>");
								}
							}

							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>" + strDebit + "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>"
									+ strCredit
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>"
									+ Util.fcolorCCY(msgList.getE01ENDBAL())
									+ "</TD>");
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader19(Util.fcolorCCY(debit.toString()));
					userPO.setHeader20(Util.fcolorCCY(credit.toString()));
					userPO.setHeader21(msgList.getE01ENDBAL());
					userPO.setHeader22(countDebit + "");
					userPO.setHeader23(countCredit + "");
					flexLog("Putting java beans into the session");
					ses.setAttribute("cifList", beanList);

					try {
						if (msgList.getE01VALBTH().equals("V")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECIF030_st_list_print_fv.jsp");
							callPage(
								LangPath + "ECIF030_st_list_print_fv.jsp",
								req,
								res);
						} else if (msgList.getE01VALBTH().equals("B")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECIF030_st_list_print_fp.jsp");
							callPage(
								LangPath + "ECIF030_st_list_print_fp.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	// START PDF
	protected void procReqPDF(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECIF03001Message msgSearch = null;
		ECIF03001Message msgList = null;
		ECIF03002Message msgHeader = null;
		JBList beanList = null;
		BeanList lstTransactions = null;
		UserPos userPO = null;

		String senderror = "1";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		ESS0030DSMessage msgUser = null;
		msgUser = (ESS0030DSMessage) ses.getAttribute("currUser");
		String LNG = msgUser.getE01LAN();

		int screen = R_SEARCH; // PDF = 6 ** EMAIL = 7

		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		} catch (Exception e) {
			flexLog("Screen set to default value");
		}

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (ECIF03001Message) mc.getMessageRecord("ECIF03001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECIF030");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0004");
			msgSearch.setH01FLGWK1("P");
			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgSearch.setE01NUMREC(req.getParameter("Pos"));
				} catch (Exception e) {
					msgSearch.setE01NUMREC("0");
					flexLog("E01NUMPOS");
				}
				try {
					msgSearch.setE01NUMACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E01NUMACC");
				}
				msgSearch.setE01HISCYC(userPO.getHeader7());
				msgSearch.setE01VALBTH(userPO.getHeader8());

				try {
					msgSearch.setE01FRDTE1(userPO.getHeader9());
					msgSearch.setE01FRDTE2(userPO.getHeader10());
					msgSearch.setE01FRDTE3(userPO.getHeader11());
				} catch (Exception e) {
					flexLog("DATE 1");
				}
				try {
					msgSearch.setE01TODTE1(userPO.getHeader12());
					msgSearch.setE01TODTE2(userPO.getHeader13());
					msgSearch.setE01TODTE3(userPO.getHeader14());
				} catch (Exception e) {
					flexLog("DATE 2");
				}
				try {
					msgSearch.setE01FRCHKN(userPO.getHeader15());
				} catch (Exception e) {
					flexLog("E01FRCHKN");
				}
				try {
					msgSearch.setE01TOCHKN(userPO.getHeader16());
				} catch (Exception e) {
					flexLog("E01TOCHKN");
				}

				try {
					msgSearch.setE01FRAMNT(userPO.getHeader17());
				} catch (Exception e) {
					flexLog("E01FRAMNT");
				}
				try {
					msgSearch.setE01TOAMNT(userPO.getHeader18());
				} catch (Exception e) {
					flexLog("E01TOAMNT");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECIF030_st_selection.jsp");
					callPage(LangPath + "ECIF030_st_selection.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECIF03002")) {
				try {
					msgHeader = new datapro.eibs.beans.ECIF03002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgHeader = (ECIF03002Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("msgHeader", msgHeader);

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ECIF03001")) {

					try {
						lstTransactions = new BeanList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";

					String strDebit = "";
					String strCredit = "";
					java.math.BigDecimal debit = new java.math.BigDecimal("0");
					java.math.BigDecimal credit = new java.math.BigDecimal("0");
					int countDebit = 0;
					int countCredit = 0;

					while (true) {

						msgList = (ECIF03001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							break;
						}

						lstTransactions.addRow(msgList);

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader19(Util.fcolorCCY(debit.toString()));
					userPO.setHeader20(Util.fcolorCCY(credit.toString()));
					userPO.setHeader21(msgList.getE01ENDBAL());
					userPO.setHeader22(countDebit + "");
					userPO.setHeader23(countCredit + "");
					flexLog("Putting java beans into the session");
					ses.setAttribute("lstTransactions", lstTransactions);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		DocumentException ex = null;
		ByteArrayOutputStream baosPDF = null;

		boolean ACT = false;
		if (screen == 7) {
			ACT = true;
		}

		try {
			baosPDF =
				generatePDFDocumentBytes(
					req,
					this.getServletContext(),
					ses,
					ACT);

			if (!ACT) {
				
				StringBuffer sbFilename = new StringBuffer();
				//sbFilename.append(msgHeader.getBigDecimalE02ACMACC());
				//sbFilename.append(System.currentTimeMillis());
				String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
				fn = Util.replace(fn,":","-");
				fn = Util.replace(fn,".","-");
				sbFilename.append(fn);
				sbFilename.append(".pdf");

				res.setHeader("Cache-Control", "max-age=30");

				res.setContentType("application/pdf");

				StringBuffer sbContentDispValue = new StringBuffer();
				sbContentDispValue.append("inline");
				sbContentDispValue.append("; filename=");
				sbContentDispValue.append(sbFilename);

				res.setHeader(
					"Content-disposition",
					sbContentDispValue.toString());

				res.setContentLength(baosPDF.size());

				ServletOutputStream sos;

				sos = res.getOutputStream();

				baosPDF.writeTo(sos);

				sos.flush();
			} else {
				senderror = procSendEmail(baosPDF, ses);
				String msg = "";
				if (senderror.equals("0")) {
					if (LNG.equals("e")) {
						msg = "Your email send OK";
					}
					if (LNG.equals("s")) {
						msg = "Su email enviado OK";
					}
				} else {
					if (LNG.equals("e")) {
						msg = "Your email did not send";
					}
					if (LNG.equals("s")) {
						msg = "Su email no fue enviado";
					}
				}

				userPO.setHeader3(msg);

				ses.setAttribute("userPO", userPO);

				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECIF030_email_confirm.jsp");
				callPage(LangPath + "ECIF030_email_confirm.jsp", req, res);

			}
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

		BeanList lstTransactions;
		lstTransactions = (BeanList) session.getAttribute("lstTransactions");

		ECIF03002Message msgHeader = null;
		msgHeader = (ECIF03002Message) session.getAttribute("msgHeader");
		
		//get interest table values
		Hashtable ht = formatInterestTable(msgHeader);
		
		ECIF03001Message msgList = null;

		UserPos userPO = (UserPos) session.getAttribute("userPO");
				
		String title = "";

		String header01 = "";
		String header02 = "";
		String header03 = "";
		String header04 = "";
		String header05 = "";
		String header06 = "";
		String header07 = "";
		String header08 = "";
		String header09 = "";
		String header10 = "";
		String header11 = "";
		String header12 = "";
		String header13 = "";

		String detail1 = "";
		String detail2 = "";
		String detail3 = "";
		String detail4 = "";
		String detail5 = "";
		String detail6 = "";
		String detail7 = "";
		String detail8 = "";

		String footer0 = "";
		String footer1 = "";
		String footer2 = "";
		String footer3 = "";

		String page = "";

		String cuscun = msgHeader.getE02ACMCUN();

		if (LNG.equals("e")) {
			title = "Account Statement";

			header01 = "Account";
			header02 = "       "; //Name
			header03 = "       "; //Address
			header04 = "Product";
			header05 = "Gross";
			header06 = "Balance";
			header07 = "Uncollected Balance";
			header08 = "Hold Amount";
			header09 = "Credit Limit";
			header10 = "Available Balance";
			header11 = "Gross Average";
			header12 = "Balance as of";
			header13 = "Purpose of Account";

			detail1 = "Process Date";
			detail2 = "Reference";
			detail3 = "Description";
			detail4 = "Debit";
			detail5 = "Credit";
			detail6 = "Balance";
			detail7 = "Amount";
			detail8 = "Interest";

			footer0 = "ITBMS";
			footer1 = "Total Debits";
			footer2 = "Total Credits";
			footer3 = "Ending Balance";

			page = "Page Number";
		}

		if (LNG.equals("s")) {
			title = "Estado de Cuentas";

			header01 = "Cuenta";
			header02 = "      "; //Nombre
			header03 = "      "; //Direccion
			header04 = "Producto";
			header05 = "Saldo Global";
			header06 = "Saldo";
			header07 = "Saldo Diferido";
			header08 = "Monto Retenido";
			header09 = "Limite de Credito";
			header10 = "Balance Disponible";
			header11 = "Promedio Global";
			header12 = "Saldo a la Fecha";
			header13 = "Proposito de la Cuenta";

			detail1 = "Fecha Proceso";
			detail2 = "Referencia";
			detail3 = "Descripción";
			detail4 = "Debito";
			detail5 = "Credito";
			detail6 = "Balance";
			detail7 = "Monto";
			detail8 = "Tasa";

			footer0 = "ITBMS";
			footer1 = "Total Debitos";
			footer2 = "Total Creditos";
			footer3 = "Saldo Final";

			page = "Numero de Pagina";
		}

		String accnum = msgHeader.getE02ACMACC();
		String name = Util.unformatHTML(
			msgHeader.getE02LGNA01()
				+ " "
				+ msgHeader.getE02LGNA02()
				+ " "
				+ msgHeader.getE02LGNA03()
				+ " "
				+ msgHeader.getE02LGNA04()
				+ " "
				+ msgHeader.getE02LGNA05()
				+ " "
				+ msgHeader.getE02LGNA06()
				+ " "
				+ msgHeader.getE02LGNA07()
				+ " "
				+ msgHeader.getE02LGNA08()
				+ " "
				+ msgHeader.getE02LGNA09());
		String add = Util.unformatHTML(msgHeader.getE02CUMMA2());
		String add1 = Util.unformatHTML(msgHeader.getE02CUMMA3());
		String add2 = Util.unformatHTML(msgHeader.getE02CUMMA4() + " " + msgHeader.getE02CUMMA5());
		String pro = Util.unformatHTML(msgHeader.getE02ACMPRO());
		String ccy = msgHeader.getE02ACMCCY();
		String bal = msgHeader.getE02ACMMGB();
		String uncol = msgHeader.getE02UNCBAL();
		String hold = msgHeader.getE02ACMHAM();
		String climit = msgHeader.getE02ACMCLI();
		String avabal = msgHeader.getE02AVALBL();
		String grobal = msgHeader.getE02ACMGAV();
		String purAcc = Util.unformatHTML(msgHeader.getE02LGNA10());
		String dd1 = msgHeader.getE02LSSTM1();
		while (dd1.length() < 2) {
			dd1 = "0" + dd1;
		}
		String dd2 = msgHeader.getE02LSSTM2();
		while (dd2.length() < 2) {
			dd2 = "0" + dd2;
		}
		String dd3 = msgHeader.getE02LSSTM3();
		while (dd3.length() < 2) {
			dd3 = "0" + dd3;
		}
		String baldat = dd1 + "/" + dd2 + "/" + dd3;

		Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try {
			docWriter = PdfWriter.getInstance(doc, baosPDF);

			if (FLG) {
				docWriter.setEncryption(
					PdfWriter.STRENGTH128BITS,
					accnum,
					cuscun,
					PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
			}

			doc.addAuthor("eIBS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator(msgHeader.getE02ACMACC());
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
				new Paragraph(header01 + ": " + accnum, headerBoldFont);
			Paragraph HEADER02 =
				new Paragraph(header02 + "  " + name, headerBoldFont);
			Paragraph HEADER03 =
				new Paragraph(header03 + "  " + add, headerBoldFont);
			Paragraph HEADER031 =
				new Paragraph(header03 + "  " + add1, headerBoldFont);
			Paragraph HEADER032 =
				new Paragraph(header03 + "  " + add2, headerBoldFont);
			Paragraph HEADER04 =
				new Paragraph(header04 + ": " + pro, headerBoldFont);
			Paragraph HEADER05 =
				new Paragraph(
					header05 + " " + ccy + " " + header06 + " " + bal,
					headerBoldFont);
			//Paragraph HEADER06 = new Paragraph(header06 + ": " + bal, headerBoldFont);
			Paragraph HEADER07 =
				new Paragraph(header07 + ": " + uncol, headerBoldFont);
			Paragraph HEADER08 =
				new Paragraph(header08 + ": " + hold, headerBoldFont);
			Paragraph HEADER09 =
				new Paragraph(header09 + ": " + climit, headerBoldFont);
			Paragraph HEADER10 =
				new Paragraph(header10 + ": " + avabal, headerBoldFont);
			Paragraph HEADER11 =
				new Paragraph(header11 + ": " + grobal, headerBoldFont);
			Paragraph HEADER12 =
				new Paragraph(header12 + ": " + baldat, headerBoldFont);
			Paragraph HEADER13 =
				new Paragraph(header13 + ": " + purAcc, headerBoldFont);
			Paragraph BLANK = new Paragraph("", headerBoldFont);

			Paragraph DETAIL1 = new Paragraph(detail1, headerBoldFont);
			Paragraph DETAIL2 = new Paragraph(detail2, headerBoldFont);
			Paragraph DETAIL3 = new Paragraph(detail3, headerBoldFont);
			Paragraph DETAIL4 = new Paragraph(detail4, headerBoldFont);
			Paragraph DETAIL5 = new Paragraph(detail5, headerBoldFont);
			Paragraph DETAIL6 = new Paragraph(detail6, headerBoldFont);
			Paragraph DETAIL7 = new Paragraph(detail7, headerBoldFont);
			Paragraph DETAIL8 = new Paragraph(detail8, headerBoldFont);

			HeaderFooter header = new HeaderFooter(TITLE, false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(Element.ALIGN_CENTER);
			doc.setHeader(header);

			HeaderFooter footer = new HeaderFooter(new Phrase(page), false);
			footer.setBorder(Rectangle.NO_BORDER);
			doc.setFooter(footer);

			doc.open();

			Table table = new Table(2, 5);
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

			cell = new Cell(HEADER05);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER02);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER07);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER03);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER08);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER031);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER032);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER04);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER09);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER13);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER10);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER11);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(BLANK);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new Cell(HEADER12);
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
			
			//Interest Table
			if(ht.size()>0){

				PdfPTable interestTable = new PdfPTable(2); //two columns
				interestTable.getDefaultCell().setPadding(3);
				int headwidths[] = { 50, 50}; // percentage
				interestTable.setWidths(headwidths);
				interestTable.setWidthPercentage(100); // percentage

				interestTable.getDefaultCell().setBorderWidth(1);
				interestTable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
				interestTable.getDefaultCell().setGrayFill(0.9f);
				interestTable.addCell(DETAIL7);
				interestTable.addCell(DETAIL8);
				interestTable.setHeaderRows(1); // this is the end of the table header

				interestTable.getDefaultCell().setBorderWidth(1);
				interestTable.getDefaultCell().setGrayFill(0.0f);
				
				
			
				
				ArrayList interestValues =(ArrayList)ht.keys().nextElement();
				ArrayList amountValues = (ArrayList)ht.elements().nextElement();
				
				
				for(int i = 0; i < interestValues.size();i++)
				{
					interestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					interestTable.addCell(new Paragraph((String)amountValues.get(i), normalFont));
						
					interestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					interestTable.addCell(new Paragraph((String)interestValues.get(i), normalFont));
				}
				
				doc.add(interestTable);
			}
			
			
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

			int NumColumns = 6;
			PdfPTable datatable = new PdfPTable(NumColumns);

			datatable.getDefaultCell().setPadding(3);
			int headerwidths[] = { 10, 14, 35, 13, 13, 15 }; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage

			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.getDefaultCell().setGrayFill(0.9f);
			datatable.addCell(DETAIL1);
			datatable.addCell(DETAIL2);
			datatable.addCell(DETAIL3);
			datatable.addCell(DETAIL4);
			datatable.addCell(DETAIL5);
			datatable.addCell(DETAIL6);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);

			lstTransactions.initRow();

			int i = 0;
			int countDebit = 0;
			int countCredit = 0;
			BigDecimal debit = new BigDecimal("0");
			BigDecimal credit = new BigDecimal("0");

			String dit11 = "";
			String dit21 = "";
			String dit31 = "";
			String dit41 = "";
			String dit51 = "";
			String dit61 = "";

			while (lstTransactions.getNextRow()) {
				i++;
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				} else {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}

				msgList = new ECIF03001Message();

				msgList = (ECIF03001Message) lstTransactions.getRecord();

				String dt1 = msgList.getE01DATE11();
				while (dt1.length() < 2) {
					dt1 = "0" + dt1;
				}
				String dt2 = msgList.getE01DATE12();
				while (dt2.length() < 2) {
					dt2 = "0" + dt2;
				}
				String dt3 = msgList.getE01DATE13();
				while (dt3.length() < 2) {
					dt3 = "0" + dt3;
				}

				dit11 = dt1 + "/" + dt2 + "/" + dt3; // PDate
				dit21 = msgList.getE01FRCHKN(); // Reference
				if (dit21.equals("&nbsp;") || dit21.equals("0")) {
					dit21 = "";
				}
				dit31 = msgList.getE01TRANAR();
				dit41 = "";
				dit51 = "";

				if (msgList.getE01TRADCC().equals("0")) {
					debit = debit.add(msgList.getBigDecimalE01TRAAMT());
					countDebit++;
					dit41 = msgList.getE01TRAAMT();
				} else if (msgList.getE01TRADCC().equals("5")) {
					credit = credit.add(msgList.getBigDecimalE01TRAAMT());
					countCredit++;
					dit51 = msgList.getE01TRAAMT();
				}

				dit61 = msgList.getE01ENDBAL(); // Balance

				Paragraph DIT11 = new Paragraph(dit11, normalFont);
				Paragraph DIT21 = new Paragraph(dit21, normalFont);
				Paragraph DIT31 = new Paragraph(dit31, normalFont);
				Paragraph DIT41 = new Paragraph(dit41, normalFont);
				Paragraph DIT51 = new Paragraph(dit51, normalFont);
				Paragraph DIT61 = new Paragraph(dit61, normalFont);

				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
				datatable.addCell(DIT11);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
				datatable.addCell(DIT21);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_LEFT);
				datatable.addCell(DIT31);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT41);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT51);
				datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
				datatable.addCell(DIT61);
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
			
			String E01NUTIBMS=msgList.getE01NUITBMS().trim();
			String E01SUITBMS=msgList.getE01SUITBMS().trim();
			
			Paragraph FOOTER0 = new Paragraph(footer0, headerBoldFont);
			Paragraph FOOTER1 = new Paragraph(footer1, headerBoldFont);
			Paragraph FOOTER2 = new Paragraph(footer2, headerBoldFont);
			Paragraph FOOTER3 = new Paragraph(footer3, headerBoldFont);
			
			Paragraph test = new Paragraph(E01NUTIBMS+"                 "+E01SUITBMS, headerBoldFont);

			Paragraph TDEBIT =
				new Paragraph(
					Util.formatCCY(String.valueOf(debit))
						+ " ("
						+ countDebit
						+ ")",
					headerBoldFont);
			Paragraph TCREDIT =
				new Paragraph(
					Util.formatCCY(String.valueOf(credit))
						+ "( "
						+ countCredit
						+ ")",
					headerBoldFont);
			Paragraph ENDBAL = new Paragraph(dit61, headerBoldFont);

			NumColumns = 4;
			datatable = new PdfPTable(NumColumns);

			datatable.getDefaultCell().setPadding(3);
			int headerwidths2[] = { 25, 25, 25,25 }; // percentage
			datatable.setWidths(headerwidths2);
			datatable.setWidthPercentage(100); // percentage

			datatable.getDefaultCell().setBorderWidth(1);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.addCell(FOOTER0);
			datatable.addCell(FOOTER1);
			datatable.addCell(FOOTER2);
			datatable.addCell(FOOTER3);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
				datatable.addCell(test);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.addCell(TDEBIT);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.addCell(TCREDIT);
			datatable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_CENTER);
			datatable.addCell(ENDBAL);

			doc.add(datatable);

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

	public String procSendEmail(ByteArrayOutputStream baosPDF, HttpSession ses)
		throws ServletException, IOException {

		ECIF03002Message msgHeader = null;
		msgHeader = (ECIF03002Message) ses.getAttribute("msgHeader");

		String senderror = "0";

		String host = "";
		String protocol = "";

		String to = "";
		String toerror = "";
		String from = "";
		String fromalias = "";
		String subject = "";
		String text = "";

		String user = "";
		String password = "";

		String path = "";
		String filename = "";

		to = msgHeader.getE02CUSIAD();
		//filename = msgHeader.getE02ACMACC() + ".pdf";

		String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
		fn = Util.replace(fn,":","-");
		fn = Util.replace(fn,".","-");
		filename = fn  + ".pdf";
						
		// Getting Properties
		String propertyFileName = "email";
		PropertyResourceBundle appProp = null;

		try {

			appProp =
				(PropertyResourceBundle) PropertyResourceBundle.getBundle(
					propertyFileName);

			// Read email pool sender properties
			try {
				host = appProp.getString("email.server.host");
			} catch (Exception e) {
				host = "";
			}

			try {
				protocol = appProp.getString("email.server.protocol");
			} catch (Exception e) {
				protocol = "";
			}

			try {
				from = appProp.getString("email.from");
			} catch (Exception e) {
				from = "";
			}

			try {
				fromalias = appProp.getString("email.fromalias");
			} catch (Exception e) {
				fromalias = "";
			}

			try {
				subject = appProp.getString("email.subject");
			} catch (Exception e) {
				subject = "";
			}

			try {
				text = appProp.getString("email.message");
			} catch (Exception e) {
				text = "";
			}

			try {
				user = appProp.getString("email.username");
			} catch (Exception e) {
				user = "";
			}

			try {
				password = appProp.getString("email.password");
			} catch (Exception e) {
				password = "";
			}

			try {
				path = appProp.getString("email.path");
			} catch (Exception e) {
				path = "";
			}

			try {
				toerror = appProp.getString("email.toerror");
			} catch (Exception e) {
				toerror = "";
			}

		} catch (MissingResourceException e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			senderror = "1";
			throw new RuntimeException("Properties File Error");
		}

		if (to.equals("")) {
			to = toerror;
		}
				
		// Writing file to disk
		OutputStream fo = new FileOutputStream(path + filename);
		byte buf[] = baosPDF.toByteArray();
		fo.write(buf);
		fo.close();

		try {
			File filetoattach = new File(path + filename);
			//System.out.println("1");
	
			// Get system properties
			//Properties props = System.getProperties();
			Properties props = new Properties();
			
			//System.out.println("2");
			// Properties props = new Properties();
	
			// Setup mail server
			props.put("mail." + protocol.toLowerCase().trim() + ".host", host);
			//System.out.println("3");
	
			// Athentication
			SimpleAthenticator sa = null;
			//
			if (!user.equals("")) {
				sa = new SimpleAthenticator(user, password);
				//System.out.println("4");
			}
			// Get session
			Session session = Session.getDefaultInstance(props, sa);
			//System.out.println("5");

			// Define message
			MimeMessage message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress(from, fromalias));
			//System.out.println("6");
			// Set the to address
			message.addRecipient(
				Message.RecipientType.TO,
				new InternetAddress(to));
			//System.out.println("7");
			// Set the subject
			message.setSubject(subject);
			//System.out.println("8");
			// Set the content
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(text);
			//System.out.println("9");
			// Set attachment
			MimeBodyPart mbp2 = new MimeBodyPart();

			FileDataSource fds = new FileDataSource(filetoattach);
			//System.out.println("10");
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			message.setContent(mp);

			// Send message
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("Send error " + e);
			senderror = "1";
		}

		try {

			File file;

			file = new File(path + filename);
			boolean isdeleted = file.delete();
		} catch (Exception e) {
			System.out.println("Error deleting: " + e);
		}
		return (senderror);
	}

	// END PDF

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog(
				"About to call Page: " + LangPath + "ECIF030_st_selection.jsp");
			callPage(LangPath + "ECIF030_st_selection.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqSTEnterSearch(
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
			String opt = "RT";
			String prd = "RT";
			if (req.getParameter("Type") != null) {
				if (req.getParameter("Type").equals("SV")) {
					opt = "SV";
					prd = "04";
				} else {
					prd = "RA";
				}
			}
			userPO.setOption(opt);
			userPO.setPurpose("STATEMENT");
			userPO.setRedirect(
				"/servlet/datapro.eibs.client.JSECIF030?SCREEN=200");
			userPO.setProdCode(prd);
			//Others Parameters
			userPO.setHeader1("E01ACMACC");
			userPO.setHeader2("H01FLGWK2");
			//msgCD = new EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			//ses.setAttribute("cdMant", msgCD);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

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

			int screen = R_SEARCH;

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
						case R_SEARCH :
							procReqSearch(msgUser, req, res, session);
							break;
						case A_SEARCH :
							procActionSearch(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_PRINT :
							procReqPrintList(mc, msgUser, req, res, session);
							break;
						case R_PDF :
							procReqPDF(mc, msgUser, req, res, session);
							break;
						case R_EMAIL :
							procReqPDF(mc, msgUser, req, res, session);
							break;
						case R_ENTER :
							procReqSTEnterSearch(msgUser, req, res, session);
							break;
						case A_ENTER :
							procActionSTEnterSearch(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}
				} // end try
				catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} // end try
			catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}

		} // end else

		return;

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
	
	protected Hashtable formatInterestTable(ECIF03002Message ms)
	{
		DecimalFormat interestFormatter = new DecimalFormat("#,##0.000000");
		DecimalFormat rangeFormatter = new DecimalFormat("#,##0");
		String[] keys = {"L","1","2","3","4","5","6","7","8","9","X","Y"};
		final String MESSAGE_MAX = " en adelante";
		String interest;
		String amountRange;
		ArrayList amountColumn = new ArrayList();
		ArrayList interestColumn = new ArrayList();
		double rangeMin;
		double rangeMax;
		final double RANGE_MAX_FACTOR= 1.00;
		Hashtable ht = new Hashtable();
		try{//set starting interest rate
			interest = ms.getField("E02STHMKR").getString();
		}
		catch(Exception ex){
			interest = null;
		}
		
		if(interest != null && Double.parseDouble(interest)>0)
		{
			
			for(int i = 0; i < keys.length;i++) 
			{
				if(i != 0){
					if(i+1 < keys.length){ //protect against array out of bounds ex.
						interest = ms.getField("E02STHMR"+keys[i]).getString();
					}
					else{
						interest = null;
					}
				}
				
				if(interest != null && Double.parseDouble(interest)>0)
				{
					rangeMax = Double.parseDouble(ms.getField("E02STHMB"+keys[i+1]).getString())- RANGE_MAX_FACTOR;
					if(i == 0){
						if(rangeMax != (-RANGE_MAX_FACTOR)){
							rangeMin = Double.parseDouble(ms.getField("E02STHMK"+keys[i]).getString());
							amountRange = rangeFormatter.format(rangeMin)+" a "+ rangeFormatter.format(rangeMax);
						}else{
							rangeMin = Double.parseDouble( ms.getField("E02STHMK"+keys[i]).getString());
							amountRange = rangeFormatter.format(rangeMin)+ MESSAGE_MAX;
						}						
					}
					else{
						if(rangeMax == (-RANGE_MAX_FACTOR)){//make sure maximum amount is not zero
							rangeMin = Double.parseDouble( ms.getField("E02STHMB"+keys[i]).getString());
							amountRange = rangeFormatter.format(rangeMin)+ MESSAGE_MAX;
							
						}
						else{
							rangeMin = Double.parseDouble( ms.getField("E02STHMB"+keys[i]).getString());
							amountRange = rangeFormatter.format(rangeMin)+" a "+ rangeFormatter.format(rangeMax);
						}
						 
					}
					
					//add interest/amount to corresponding column objects
					interestColumn.add(interestFormatter.format(Double.parseDouble(interest))+"%");
					amountColumn.add(amountRange);
				}
				
				
			}
			ht.put(interestColumn, amountColumn);
			
		}
		return ht;
	}
	
}