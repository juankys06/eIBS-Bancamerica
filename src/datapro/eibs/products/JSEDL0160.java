package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.HashMap;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

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

public class JSEDL0160 extends datapro.eibs.master.SuperServlet {

    // certificate of deposit

    // inquiry options
    protected static final int R_INQUIRY = 1;

    protected static final int R_BASIC_INQ = 2;

    protected static final int R_CODES_INQ = 3;

    protected static final int R_TITULARES_INQ = 4;

    protected static final int R_SPECIAL_INST_INQ = 5;

    protected static final int R_INT_AN_INQ = 6;

    protected static final int R_ST_SELECTION = 7;

    protected static final int R_CRN_PAY = 8;

    protected static final int R_CALC = 9;

    protected static final int R_PRODUCTS = 10;

    protected static final int R_DEDUCTIONS = 11;

    protected static final int R_GARANT_INQ = 12;

    protected static final int R_TABLES_INQ = 13;

    protected static final int R_BASIC_CONTR = 14;

    protected static final int R_CREDIT_LINES = 15;

    protected static final int R_HIST_STATUS = 16;

    protected static final int R_CRN_PAYAP = 17;

    protected static final int R_ADDCODES_INQ = 18;

    protected static final int R_PROJECTED_PAYOFF_SEL = 19;

    protected static final int R_PROJECTED_PAYOFF_INQ = 20;

    protected static final int R_PROJECTED_PAYOFF = 21;

    protected static final int R_PROJECTED_PDF = 22;

    protected static final int R_CANCEL_DEBIT = 33;

    protected static final int R_ACCOUNT_TITLE = 57;

    protected static final int A_ACCOUNT_TITLE = 58;

    protected static final int R_DISPLAY_TITLE = 59;

    // entering options
    protected static final int R_ENTER_INQUIRY = 100;

    protected static final int R_ENTER_PRINT = 300;

    protected static final int A_ENTER_INQUIRY = 200;

    protected static final int A_ENTER_PRINT = 400;

    protected String LangPath = "S";

    /**
         * JSECLI001 constructor comment.
         */
    public JSEDL0160() {
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
    protected void procActionEnterInquiry(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	EDL016001Message msgCD = null;
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
	    msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
	    msgCD.setH01USERID(user.getH01USR());
	    msgCD.setH01PROGRM("EDL0160");
	    flexLog("header 4 timestamp = " + getTimeStamp());
	    msgCD.setH01TIMSYS(getTimeStamp());
	    msgCD.setH01SCRCOD("01");
	    msgCD.setH01OPECOD("0002");
	    msgCD.setE01DEAACD("10");
	    try {
		if (req.getParameter("E01DEAACC") != null)
		    msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
		flexLog("Certificate Sent");
	    } catch (Exception e) {
		msgCD.setE01DEAACC("0");
		flexLog(" error " + e);
		flexLog("Certificate Error Sent");
	    }
	    flexLog("Send command");
	    msgCD.send();
	    flexLog("Destroy command");
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_enter_inquiry.jsp");
		    callPage(LangPath + "EDL0160_ln_enter_inquiry.jsp", req,
			    res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016001")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016001Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016001Message) newmessage;

		userPO.setIdentifier(msgCD.getE01DEAACC());
		userPO.setBank(msgCD.getE01DEABNK());
		userPO.setHeader1(msgCD.getE01DEAPRO());
		userPO.setHeader2(msgCD.getE01DEACUN());
		userPO.setHeader3(msgCD.getE01CUSNA1());
		userPO.setHeader23(msgCD.getE01DEACLF());
		userPO.setHeader22(msgCD.getE01DEATLN());
		userPO.setHeader21(msgCD.getE01DEATYP());
		userPO.setCurrency(msgCD.getE01DEACCY());
		userPO.setOfficer(msgCD.getE01DEAOFC() + " - "
			+ msgCD.getE01DSCOFC());

		flexLog("Putting java beans into the session");
		ses.setAttribute("inqLoans", msgCD);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
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
    protected void procActionEnterPrint(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	EFT000020Message msgLN = null;
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
	    flexLog("Sending header");
	    msgLN = (EFT000020Message) mc.getMessageRecord("EFT000020");
	    msgLN.setH20USERID(user.getH01USR());
	    msgLN.setH20PROGRM("EDL0160");
	    msgLN.setH20TIMSYS(getTimeStamp());
	    msgLN.setH20SCRCOD("01");
	    msgLN.setH20OPECOD("0002");
	    flexLog("Header has been sended");
	    try {
		msgLN.setE20DEAACC(req.getParameter("E20DEAACC"));
	    } catch (Exception e) {
		msgLN.setE20DEAACC("0");
	    }

	    msgLN.send();
	    msgLN.destroy();
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
	    if (newmessage.getFormatName().equals("EFT000020")) {
		try {
		    msgLN = new datapro.eibs.beans.EFT000020Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgLN = (EFT000020Message) newmessage;

		userPO.setIdentifier(msgLN.getE20DEAACC());
		userPO.setHeader1(msgLN.getE20DEAPRO());
		userPO.setHeader2(msgLN.getE20DEACUN());
		userPO.setHeader3(msgLN.getE20CUSNA1());
		userPO.setHeader9(msgLN.getE20BANKNM());
		userPO.setHeader10(msgLN.getE20CUSIDN());
		userPO.setHeader11(msgLN.getE20CNTRD1());
		userPO.setHeader12(msgLN.getE20CNTRD2());
		userPO.setHeader13(msgLN.getE20CNTRD3());
		userPO.setHeader14(msgLN.getE20RATE());
		userPO.setHeader15(msgLN.getE20DEASPR());
		userPO.setHeader16(msgLN.getE20CUSTID());
		userPO.setHeader17(msgLN.getE20DSCPRO());
		userPO.setHeader18(msgLN.getE20BRNNME());

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnFinish", msgLN);

		if (IsNotError) { // There are no errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_finish.jsp");
			callPage(LangPath + "EDL0160_ln_finish.jsp", req, res);
		    } catch (Exception e) {
			flexLog("Exception calling page " + e);
		    }
		} else { // There are errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_enter_print.jsp");
			callPage(LangPath + "EDL0160_ln_enter_print.jsp", req,
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
    protected void procReqAnInt(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL030003Message msgSearch = null;
	EDL030003Message msgList = null;
	JBList beanList = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	int type = 0;
	String num = "";
	int posi = 0;
	// Send Initial data
	try {
	    flexLog("Send Initial Data");
	    msgSearch = (EDL030003Message) mc.getMessageRecord("EDL030003");
	    msgSearch.setH03USERID(user.getH01USR());
	    msgSearch.setH03PROGRM("EDL0300");
	    msgSearch.setH03TIMSYS(getTimeStamp());
	    msgSearch.setH03SCRCOD("01");
	    msgSearch.setH03OPECOD("0004");

	    try {
		try {
		    posi = Integer.parseInt(req.getParameter("Pos"));
		} catch (Exception e) {
		    posi = 0;
		    flexLog("E01NUMPOS");
		}

		try {
		    msgSearch.setE03NUMACC(userPO.getIdentifier());
		} catch (Exception e) {
		    flexLog("E03NUMACC");
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

	    }

	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("EDL030003")) {

		try {
		    beanList = new datapro.eibs.beans.JBList();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		try {
		    beanList.setSearchText(num);
		    beanList.setSearchType(type + "");
		} catch (Exception e) {
		    e.printStackTrace();
		    beanList.setSearchText("A");
		    beanList.setSearchType("3");
		    flexLog("Input data error " + e);
		}

		boolean firstTime = true;
		String marker = "";
		String myFlag = "";
		StringBuffer myRow = null;
		String chk = "";

		java.math.BigDecimal days = new java.math.BigDecimal(0);
		java.math.BigDecimal interest = new java.math.BigDecimal(0);

		while (true) {

		    msgList = (EDL030003Message) newmessage;

		    marker = msgList.getE03INDOPE();

		    if (marker.equals("*")) {
			beanList.setShowNext(false);
			break;
		    } else {
			if (firstTime) {
			    firstTime = false;
			    chk = "checked";
			} else {
			    chk = "";
			}

			interest = interest.add(msgList
				.getBigDecimalE03INTAMN());
			days = days.add(msgList.getBigDecimalE03NUMDYS());

			myRow = new StringBuffer("<TR>");
			myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"
				+ Util.formatDate(msgList.getE03INIDT1(),
					msgList.getE03INIDT2(), msgList
						.getE03INIDT3()) + "</TD>");
			myRow.append("<TD NOWRAP ALIGN=CENTER>"
				+ Util.formatDate(msgList.getE03ENDDT1(),
					msgList.getE03ENDDT2(), msgList
						.getE03ENDDT3()) + "</TD>");
			myRow.append("<TD NOWRAP ALIGN=\"CENTER\">"
				+ Util.formatCell(msgList.getE03TRACDE())
				+ "</TD>");
			myRow.append("<TD NOWRAP ALIGN=RIGHT>"
				+ Util.fcolorCCY(msgList.getE03PRIAMN())
				+ "</TD>");
			myRow.append("<TD NOWRAP ALIGN=RIGHT>"
				+ Util.fcolorCCY(msgList.getE03INTRTE())
				+ "</TD>");
			myRow.append("<TD NOWRAP ALIGN=RIGHT>"
				+ Util.formatCell(msgList.getE03NUMDYS())
				+ "</TD>");
			myRow.append("<TD NOWRAP ALIGN=RIGHT>"
				+ Util.fcolorCCY(msgList.getE03INTAMN())
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

		userPO.setHeader19(days.toString());
		userPO.setHeader20(Util.fcolorCCY(interest.toString()));

		flexLog("Putting java beans into the session");
		ses.setAttribute("cifList", beanList);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_int_an.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_int_an.jsp", req, res);
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

    /**
         * This method was created in VisualAge.
         */
    protected void procReqCollAssets(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	try {
	    UserPos userPO = (datapro.eibs.beans.UserPos) ses
		    .getAttribute("userPO");
	    flexLog("About to call Page: " + LangPath
		    + "ERA0000_collateral_list.jsp");
	    res.sendRedirect(super.srctx
		    + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=1");
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}

    }

    /**
         * This method was created in VisualAge.
         */
    protected void procReqContr(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
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
	    flexLog("About to call Page: " + LangPath
		    + "EDL0160_ln_rp_contr.jsp");
	    callPage(LangPath + "EDL0160_ln_rp_contr.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}

    }

    /**
         * This method was created in VisualAge.
         */
    protected void procReqEnterInquiry(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {

	    msgError = new datapro.eibs.beans.ELEERRMessage();
	    userPO = new datapro.eibs.beans.UserPos();
	    userPO.setOption("LN");
	    userPO.setPurpose("INQUIRY");
	    ses.setAttribute("error", msgError);
	    ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	try {
	    flexLog("About to call Page: " + LangPath
		    + "EDL0160_ln_enter_inquiry.jsp");
	    callPage(LangPath + "EDL0160_ln_enter_inquiry.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}

    }

    /**
         * This method was created in VisualAge.
         */
    protected void procReqEnterPrint(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {

	    msgError = new datapro.eibs.beans.ELEERRMessage();
	    userPO = new datapro.eibs.beans.UserPos();
	    userPO.setOption("LN");
	    userPO.setPurpose("PRINT");
	    ses.setAttribute("error", msgError);
	    ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	try {
	    flexLog("About to call Page: " + LangPath
		    + "EDL0160_ln_enter_print.jsp");
	    callPage(LangPath + "EDL0160_ln_enter_print.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}

    }

    /**
         * This method was created in VisualAge.
         */
    protected void procReqEspInstInq(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000005Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (ESD000005Message) mc.getMessageRecord("ESD000005");
	    msgCD.setH05USR(user.getH01USR());
	    msgCD.setH05PGM("EDL0130");
	    msgCD.setH05TIM(getTimeStamp());
	    msgCD.setH05SCR("01");
	    msgCD.setH05OPE(opCode);
	    msgCD.setE05ACC(userPO.getIdentifier());
	    msgCD.setE05TYP("2");
	    msgCD.send();
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Messages
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD000005")) {
		try {
		    msgCD = new datapro.eibs.beans.ESD000005Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (ESD000005Message) newmessage;
		// showESD008004(msgCD);

		flexLog("Putting java beans into the session");
		ses.setAttribute("cdInst", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_special_inst.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_special_inst.jsp", req,
			    res);
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
    protected void procReqInqBasic(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL016002Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (EDL016002Message) mc.getMessageRecord("EDL016002");
	    msgCD.setH02USERID(user.getH01USR());
	    msgCD.setH02PROGRM("EDL0160");
	    msgCD.setH02TIMSYS(getTimeStamp());
	    msgCD.setH02SCRCOD("01");
	    msgCD.setH02OPECOD(opCode);
	    msgCD.setE02DEAACC(userPO.getIdentifier());
	    msgCD.setE02DEAACD("10");

	    msgCD.send();
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016002")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016002Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016002Message) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("inqBasic", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_basic.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_basic.jsp", req, res);
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
    protected void procReqInqTable(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD0713DSMessage msgLNRat = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	String bank = userPO.getBank();
	String type = userPO.getHeader21();
	String table = userPO.getHeader22();

	// Send Initial data
	try {
	    msgLNRat = (ESD0713DSMessage) mc.getMessageRecord("ESD0713DS");
	    msgLNRat.setH01USERID(user.getH01USR());
	    msgLNRat.setH01PROGRM("ESD0713");
	    msgLNRat.setH01TIMSYS(getTimeStamp());
	    msgLNRat.setH01SCRCOD("01");
	    msgLNRat.setH01OPECOD(opCode);
	    msgLNRat.setE01SELBNK(bank);
	    msgLNRat.setE01SELTLN(table);
	    msgLNRat.setE01SELTYP(type);
	    msgLNRat.send();
	    msgLNRat.destroy();

	    flexLog("ESD0713DS Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD0713DS")) {
		try {
		    msgLNRat = new datapro.eibs.beans.ESD0713DSMessage();
		    flexLog("ESD0713DS Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgLNRat = (ESD0713DSMessage) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("lnServCharge", msgLNRat);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_ren_rate.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_ren_rate.jsp", req, res);
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
    protected void procReqInqHistSts(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL013503Message msgLNSts = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	JBObjList lnStatus = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgLNSts = (EDL013503Message) mc.getMessageRecord("EDL013503");
	    msgLNSts.setH03USERID(user.getH01USR());
	    msgLNSts.setH03PROGRM("EDL0135");
	    msgLNSts.setH03TIMSYS(getTimeStamp());
	    msgLNSts.setH03SCRCOD("01");
	    msgLNSts.setH03OPECOD(opCode);
	    msgLNSts.setE03DEAACC(userPO.getIdentifier());
	    msgLNSts.send();
	    msgLNSts.destroy();

	    flexLog("EDL013503 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL013503")) {
		try {
		    msgLNSts = new datapro.eibs.beans.EDL013503Message();
		    flexLog("EDL013503 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		String marker = "";
		lnStatus = new JBObjList();
		while (true) {

		    msgLNSts = (EDL013503Message) newmessage;

		    marker = msgLNSts.getH03FLGMAS();

		    if (marker.equals("*")) {
			break;
		    } else {
			lnStatus.addRow(msgLNSts);
		    }
		    newmessage = mc.receiveMessage();
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("lnStatus", lnStatus);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_hist_status.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_hist_status.jsp", req,
			    res);
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
    protected void procReqInquiry(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL016001Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
	    msgCD.setH01USERID(user.getH01USR());
	    msgCD.setH01PROGRM("EDL0160");
	    msgCD.setH01TIMSYS(getTimeStamp());
	    msgCD.setH01SCRCOD("01");
	    msgCD.setH01OPECOD(opCode);
	    msgCD.setE01DEAACC(userPO.getIdentifier());
	    msgCD.setE01DEAACD("10");
	    msgCD.send();
	    msgCD.destroy();

	    flexLog("EDL016001 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_enter_inquiry.jsp");
		    callPage(LangPath + "EDL0160_ln_enter_inquiry.jsp", req,
			    res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016001")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016001Message();
		    flexLog("EDL016001 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016001Message) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("inqLoans", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
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
    protected void procReqPaymPlan(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL090001Message msgHeader = null;
	ELEERRMessage msgError = null;
	EDL090002Message msgList = null;
	JBListRec beanList = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	String sType = "";

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgHeader = (EDL090001Message) mc.getMessageRecord("EDL090001");
	    msgHeader.setH01USERID(user.getH01USR());
	    msgHeader.setH01PROGRM("EDL0900");
	    msgHeader.setH01TIMSYS(getTimeStamp());
	    msgHeader.setH01OPECOD(opCode);
	    try {
		msgHeader.setE01DEAACC(userPO.getIdentifier());
	    } catch (Exception e) {
		msgHeader.setE01DEAACC("0");
	    }
	    if (req.getParameter("PROP") != null) {
		msgHeader.setE01DEAACC(req.getParameter("PROP"));
		userPO.setIdentifier(req.getParameter("PROP"));
	    }

	    try {
		String s = req.getParameter("Type");
		if (s.equals("M")) {
		    sType = "MAINTENANCE";
		} else {
		    if (s.equals("Q")) {
			msgHeader.setH01OPECOD("1500"); // Displays Schedule of
			msgHeader.setH01FLGWK3("P");
			// payments (Quotation
			// mode)
			sType = "QUOTATION";
		    } else {
			if (s.equals("P")) {
			    userPO.setOption("PROP");
			    msgHeader.setH01FLGWK3("P");
			    if (req.getParameter("CUN") != null) {
				userPO.setHeader2(req.getParameter("CUN"));
			    }
			    if (req.getParameter("NAM") != null) {
				userPO.setHeader3(req.getParameter("NAM"));
			    }
			    if (req.getParameter("PROD") != null) {
				userPO.setHeader1(req.getParameter("PROD"));
			    }
			}
			sType = "INQUIRY";
		    }
		}
	    } catch (Exception e) {
		sType = "INQUIRY";
	    }

	    msgHeader.send();
	    msgHeader.destroy();

	    flexLog("EDL090001 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		// showERROR(msgError);
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
	    } else if (newmessage.getFormatName().equals("EDL090001")) {
		try {
		    msgHeader = new datapro.eibs.beans.EDL090001Message();

		    flexLog("EDL090001 Message Received");

		    msgHeader = (EDL090001Message) newmessage;

		    // Header
		    userPO.setPurpose(sType);
		    userPO.setHeader10(Util.formatDate(
			    msgHeader.getE01OPEND1(), msgHeader.getE01OPEND2(),
			    msgHeader.getE01OPEND3())); // Opening Date
		    userPO.setHeader11(Util.formatDate(
			    msgHeader.getE01MATUR1(), msgHeader.getE01MATUR2(),
			    msgHeader.getE01MATUR3())); // Maturity Date
		    userPO
			    .setHeader12(Util.formatCCY(msgHeader
				    .getE01DEAOAM())); // Original Amount
		    userPO.setHeader13(Util
			    .formatCell(msgHeader.getE01DEARTE())); // Rate
		    userPO.setHeader14(Util
			    .formatCell(msgHeader.getE01DEABAS())); // Basis
		    userPO.setHeader15(Util
			    .formatCell(msgHeader.getE01DEAICT())); // Interest
		    // Type
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgHeader = (EDL090001Message) newmessage;

		// Fill List bean
		int colNum = 73;
		try {
		    beanList = new datapro.eibs.beans.JBListRec();
		    beanList.init(colNum);
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		char sel = ' ';
		String marker = "";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
		    myRow[i] = "";
		}

		BigDecimal principal = new BigDecimal("0");
		BigDecimal interest = new BigDecimal("0");
		BigDecimal other = new BigDecimal("0");
		BigDecimal payments = new BigDecimal("0");
		while (true) {
		    newmessage = mc.receiveMessage();
		    msgList = (EDL090002Message) newmessage;

		    marker = msgList.getE02ENDFLD();

		    if (marker.equals("*")) {
			break;
		    } else {
			principal = principal.add(msgList
				.getBigDecimalE02DLPPRN());
			interest = interest.add(msgList
				.getBigDecimalE02DLPINT());
			other = other.add(msgList.getBigDecimalE02DLPOTH());
			payments = payments.add(msgList
				.getBigDecimalE02DLPPAG());

			// Quote List
			myRow[0] = Util.formatCell(msgList.getE02DLPPNU()); // Quote
			// Number
			myRow[1] = Util.formatDate(msgList.getE02DLPPD1(),
				msgList.getE02DLPPD2(), msgList.getE02DLPPD3()); // Fecha
			// a
			// Pagar
			myRow[2] = Util.formatCCY(msgList.getE02DLPPRN()); // Principal
			// Pymt
			myRow[3] = Util.formatCCY(msgList.getE02DLPINT()); // Interest
			// Pymnt
			myRow[4] = Util.formatCCY(msgList.getE02DLPOTH()); // Other
			// Charges
			myRow[5] = Util.formatCell(msgList.getE02DLPTOT()); // Total
			// Quotes
			myRow[6] = Util.formatCell(msgList.getE02DLPBAL()); // Balance
			myRow[7] = Util.formatCell(msgList.getE02DLPSTS()); // Status
			myRow[8] = Util.formatCell(msgList.getE02DLPVEN()); // Mature
			myRow[9] = Util.formatDate(msgList.getE02DLPDT1(),
				msgList.getE02DLPDT2(), msgList.getE02DLPDT3()); // Fecha
			// a
			// Pagar
			myRow[10] = Util.formatCell(msgList.getE02DLPPAG()); // Total
			// Pymnt
			// Quote Detail
			myRow[11] = Util.formatCell(msgList.getE02DESC01()); // Description
			myRow[12] = Util.formatCell(msgList.getE02DESC02());
			myRow[13] = Util.formatCell(msgList.getE02DESC03());
			myRow[14] = Util.formatCell(msgList.getE02DESC04());
			myRow[15] = Util.formatCell(msgList.getE02DESC05());
			myRow[16] = Util.formatCell(msgList.getE02DESC06());
			myRow[17] = Util.formatCell(msgList.getE02DESC07());
			myRow[18] = Util.formatCell(msgList.getE02DESC08());
			myRow[19] = Util.formatCell(msgList.getE02DESC09());
			myRow[20] = Util.formatCell(msgList.getE02DESC10());
			myRow[21] = Util.formatCell(msgList.getE02DESC11());
			myRow[22] = Util.formatCell(msgList.getE02DESC12());
			myRow[23] = Util.formatCell(msgList.getE02DESC13());
			myRow[24] = Util.formatCell(msgList.getE02DESC14());
			myRow[25] = Util.formatCell(msgList.getE02DESC15());
			myRow[26] = Util.formatCell(msgList.getE02DESC16());
			myRow[27] = Util.formatCell(msgList.getE02DESC17());
			myRow[28] = Util.formatCell(msgList.getE02DESC18());
			myRow[29] = Util.formatCell(msgList.getE02DESC19());
			myRow[30] = Util.formatCell(msgList.getE02DESC20());
			myRow[31] = Util.formatCCY(msgList.getE02AMNT01()
				.trim()); // Amount
			myRow[32] = Util.formatCCY(msgList.getE02AMNT02()
				.trim());
			myRow[33] = Util.formatCCY(msgList.getE02AMNT03()
				.trim());
			myRow[34] = Util.formatCCY(msgList.getE02AMNT04()
				.trim());
			myRow[35] = Util.formatCCY(msgList.getE02AMNT05()
				.trim());
			myRow[36] = Util.formatCCY(msgList.getE02AMNT06()
				.trim());
			myRow[37] = Util.formatCCY(msgList.getE02AMNT07()
				.trim());
			myRow[38] = Util.formatCCY(msgList.getE02AMNT08()
				.trim());
			myRow[39] = Util.formatCCY(msgList.getE02AMNT09()
				.trim());
			myRow[40] = Util.formatCCY(msgList.getE02AMNT10()
				.trim());
			myRow[41] = Util.formatCCY(msgList.getE02AMNT11()
				.trim());
			myRow[42] = Util.formatCCY(msgList.getE02AMNT12()
				.trim());
			myRow[43] = Util.formatCCY(msgList.getE02AMNT13()
				.trim());
			myRow[44] = Util.formatCCY(msgList.getE02AMNT14()
				.trim());
			myRow[45] = Util.formatCCY(msgList.getE02AMNT15()
				.trim());
			myRow[46] = Util.formatCCY(msgList.getE02AMNT16()
				.trim());
			myRow[47] = Util.formatCCY(msgList.getE02AMNT17()
				.trim());
			myRow[48] = Util.formatCCY(msgList.getE02AMNT18()
				.trim());
			myRow[49] = Util.formatCCY(msgList.getE02AMNT19()
				.trim());
			myRow[50] = Util.formatCCY(msgList.getE02AMNT20()
				.trim());
			myRow[51] = Util.formatCCY(msgList.getE02PAID01()); // Paid
			myRow[52] = Util.formatCCY(msgList.getE02PAID02());
			myRow[53] = Util.formatCCY(msgList.getE02PAID03());
			myRow[54] = Util.formatCCY(msgList.getE02PAID04());
			myRow[55] = Util.formatCCY(msgList.getE02PAID05());
			myRow[56] = Util.formatCCY(msgList.getE02PAID06());
			myRow[57] = Util.formatCCY(msgList.getE02PAID07());
			myRow[58] = Util.formatCCY(msgList.getE02PAID08());
			myRow[59] = Util.formatCCY(msgList.getE02PAID09());
			myRow[60] = Util.formatCCY(msgList.getE02PAID10());
			myRow[61] = Util.formatCCY(msgList.getE02PAID11());
			myRow[62] = Util.formatCCY(msgList.getE02PAID12());
			myRow[63] = Util.formatCCY(msgList.getE02PAID13());
			myRow[64] = Util.formatCCY(msgList.getE02PAID14());
			myRow[65] = Util.formatCCY(msgList.getE02PAID15());
			myRow[66] = Util.formatCCY(msgList.getE02PAID16());
			myRow[67] = Util.formatCCY(msgList.getE02PAID17());
			myRow[68] = Util.formatCCY(msgList.getE02PAID18());
			myRow[69] = Util.formatCCY(msgList.getE02PAID19());
			myRow[70] = Util.formatCCY(msgList.getE02PAID20());
			myRow[71] = Util.formatCCY(msgList.getE02TOAMNT());
			myRow[72] = Util.formatCCY(msgList.getE02TOPAID());

			beanList.addRow(myFlag, myRow);

		    }

		}

		userPO.setHeader20(principal.toString());
		userPO.setHeader21(interest.toString());
		userPO.setHeader22(other.toString());
		userPO.setHeader23(payments.toString());
		userPO.setHeader19(principal.add(interest.add(other))
			.toString());

		ses.setAttribute("list", beanList);
		ses.setAttribute("header", msgHeader);
		ses.setAttribute("userPO", userPO);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0900_ln_crn_pay.jsp");
		    callPage(LangPath + "EDL0900_ln_crn_pay.jsp", req, res);
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
         * This method was created by Orestes Garcia.
         * 
         * @param request
         *                HttpServletRequest
         * @param response
         *                HttpServletResponse
         */
    protected void procReqProductDetails(HttpServletRequest req,
	    HttpServletResponse res) {

	try {

	    String appCode = req.getParameter("appcode");
	    String bank = req.getParameter("bank");
	    String prodCode = req.getParameter("PROD_CODE");
	    String typeCode = req.getParameter("typecode");
	    String generic = req.getParameter("generic");
	    String title = req.getParameter("title");

	    String firstLink = LangPath + "ESD0711_products_detail.jsp?bank="
		    + bank + "&appcode=" + appCode + "&typecode=" + typeCode
		    + "&generic=" + generic + "&title=" + title + "&sel="
		    + prodCode;
	    flexLog("About to call Page: " + firstLink);
	    res.sendRedirect(super.srctx + firstLink);

	} catch (Exception e) {
	    flexLog("Error " + e);
	}

    }

    /**
         * This method was created by Orestes Garcia.
         * 
         * @param request
         *                HttpServletRequest
         * @param response
         *                HttpServletResponse
         */
    protected void procReqProductLN(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD071110Message msgProdLN = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String bank = userPO.getBank();
	String prodCode = userPO.getHeader1();

	String opCode = "0004";

	// Send Initial data
	try {
	    msgProdLN = (ESD071110Message) mc.getMessageRecord("ESD071110");
	    msgProdLN.setH10USERID(user.getH01USR());
	    msgProdLN.setH10PROGRM("ESD0711");
	    msgProdLN.setH10TIMSYS(getTimeStamp());
	    msgProdLN.setH10SCRCOD("01");
	    msgProdLN.setH10OPECOD(opCode);
	    msgProdLN.setE10APCCDE(prodCode);
	    msgProdLN.setE10APCBNK(bank);
	    msgProdLN.send();
	    msgProdLN.destroy();

	    flexLog("ESD071110 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Messages
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD071110")) {
		try {
		    msgProdLN = new datapro.eibs.beans.ESD071110Message();
		    flexLog("ESD071110 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgProdLN = (ESD071110Message) newmessage;
		// showESD008004(msgCD);

		flexLog("Putting java beans into the session");
		ses.setAttribute("lnProdInq", msgProdLN);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_products_inq_ln.jsp");
		    callPage(LangPath + "EDL0160_products_inq_ln.jsp", req, res);
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
    protected void procReqSpecialCodesInq(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	ESD000002Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (ESD000002Message) mc.getMessageRecord("ESD000002");
	    msgCD.setH02USR(user.getH01USR());
	    msgCD.setH02PGM("EDL0130");
	    msgCD.setH02TIM(getTimeStamp());
	    msgCD.setH02SCR("01");
	    msgCD.setH02OPE(opCode);
	    msgCD.setE02ACC(userPO.getIdentifier());
	    msgCD.send();
	    msgCD.destroy();
	    flexLog("ESD000002 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Messages
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD000002")) {
		try {
		    msgCD = new datapro.eibs.beans.ESD000002Message();
		    flexLog("ESD000002 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (ESD000002Message) newmessage;
		// showESD008004(msgCD);

		flexLog("Putting java beans into the session");
		ses.setAttribute("cdCodes", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_codes.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_codes.jsp", req, res);
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

    protected void procReqAdditionalCodesInq(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	ESD001401Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (ESD001401Message) mc.getMessageRecord("ESD001401");
	    msgCD.setH14USR(user.getH01USR());
	    msgCD.setH14PGM("ESD0014");
	    msgCD.setH14TIM(getTimeStamp());
	    msgCD.setH14SCR("01");
	    msgCD.setH14OPE(opCode);
	    msgCD.setE14ACC(userPO.getIdentifier());
	    msgCD.send();
	    msgCD.destroy();
	    flexLog("ESD001401 Message Sent");
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

	    if (newmessage.getFormatName().equals("ESD001401")) {
		try {
		    msgCD = new datapro.eibs.beans.ESD001401Message();
		    flexLog("ESD001401 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (ESD001401Message) newmessage;
		// showESD008004(msgLN);

		flexLog("Putting java beans into the session");
		ses.setAttribute("cdCodes", msgCD);
		ses.setAttribute("error", msgError);

		if (IsNotError) { // There are no errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_inq_addcodes.jsp");
			callPage(LangPath + "EDL0160_ln_inq_addcodes.jsp", req,
				res);
		    } catch (Exception e) {
			flexLog("Exception calling page " + e);
		    }
		} else { // There are errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_balances.jsp");
			callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
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
    protected void procReqTitInq(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ESD000006Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
	    msgCD.setH06USR(user.getH01USR());
	    msgCD.setH06PGM("EDL0130");
	    msgCD.setH06TIM(getTimeStamp());
	    msgCD.setH06SCR("01");
	    msgCD.setH06OPE(opCode);
	    msgCD.setE06ACC(userPO.getIdentifier());
	    msgCD.setE06RTP("H");
	    msgCD.send();
	    msgCD.destroy();

	    flexLog("ESD000006 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Messages
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD000006")) {
		try {
		    msgCD = new datapro.eibs.beans.ESD000006Message();
		    flexLog("ESD000006 Message Received");
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (ESD000006Message) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("cdTit", msgCD);
		ses.setAttribute("error", msgError);
		getTitularsDescription(user, req, res, ses);
		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_tit.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_tit.jsp", req, res);
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

	    int screen = R_ENTER_INQUIRY;

	    try {

		msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
			.getAttribute("currUser");

		// Here we should get the path from the user profile
		LangPath = super.rootPath + msgUser.getE01LAN() + "/";

		try {
		    flexLog("Opennig Socket Connection ");
		    s = new Socket(super.hostIP, getInitSocket(req) + 1);
		    s.setSoTimeout(super.sckTimeOut);
		    mc = new MessageContext(new DataInputStream(
			    new BufferedInputStream(s.getInputStream())),
			    new DataOutputStream(new BufferedOutputStream(s
				    .getOutputStream())), "datapro.eibs.beans");

		    try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		    } catch (Exception e) {
			flexLog("Screen set to default value");
		    }

		    switch (screen) {
		    // BEGIN CD
		    // Request
		    case R_INQUIRY:
			procReqInquiry(mc, msgUser, req, res, session);
			break;

		    // Inquiry Options
		    case R_CODES_INQ:
			procReqSpecialCodesInq(mc, msgUser, req, res, session);
			break;
		    case R_ADDCODES_INQ:
			procReqAdditionalCodesInq(mc, msgUser, req, res,
				session);
			break;
		    case R_TITULARES_INQ:
			procReqTitInq(mc, msgUser, req, res, session);
			break;
		    case R_SPECIAL_INST_INQ:
			procReqEspInstInq(mc, msgUser, req, res, session);
			break;
		    case R_ACCOUNT_TITLE:
			procReqAccountTitle(mc, msgUser, req, res, session);
			break;
		    case R_DISPLAY_TITLE:
			procReqDisplayTitle(mc, msgUser, req, res, session);
			break;
		    case R_PROJECTED_PAYOFF_INQ:
			procReqInqProjectedPayOff(mc, msgUser, req, res,
				session);
			break;
		    case R_PROJECTED_PDF:
			procReqPDF(mc, msgUser, req, res, session);
			break;
		    case R_PROJECTED_PAYOFF:
			procReqSubmitProjectedPayOff(mc, msgUser, req, res,
				session);
			break;
		    case R_PROJECTED_PAYOFF_SEL:
			procReqProjectedPayOffSel(msgUser, req, res, session);
			break;
		    case R_BASIC_INQ:
			procReqInqBasic(mc, msgUser, req, res, session);
			break;
		    case R_INT_AN_INQ:
			procReqAnInt(mc, msgUser, req, res, session);
			break;
		    case R_PRODUCTS:
			procReqProductLN(mc, msgUser, req, res, session);
			break;
		    case R_CRN_PAY:
			procReqPaymPlan(mc, msgUser, req, res, session);
			break;
		    case R_CRN_PAYAP:
			procReqPaymPlanAp(mc, msgUser, req, res, session);
			break;

		    case R_GARANT_INQ:
			procReqCollAssets(mc, msgUser, req, res, session);
			break;
		    case R_TABLES_INQ:
			procReqInqTable(mc, msgUser, req, res, session);
			break;
		    case R_HIST_STATUS:
			procReqInqHistSts(mc, msgUser, req, res, session);
			break;
		    case R_BASIC_CONTR:
			procReqContr(mc, msgUser, req, res, session);
			break;
		    // BEGIN Entering
		    // Request
		    case R_ENTER_INQUIRY:
			procReqEnterInquiry(msgUser, req, res, session);
			break;
		    case R_ENTER_PRINT:
			procReqEnterPrint(msgUser, req, res, session);
			break;
		    case R_CREDIT_LINES:
			procReqCreditLines(msgUser, req, res, session);
			break;
		    case R_CANCEL_DEBIT:
			procReqCancelDebit(mc, msgUser, req, res, session);
			break;
		    // Action
		    case A_ENTER_INQUIRY:
			procActionEnterInquiry(mc, msgUser, req, res, session);
			break;
		    case A_ENTER_PRINT:
			procActionEnterPrint(mc, msgUser, req, res, session);
			break;
		    // END Entering

		    default:
			res
				.sendRedirect(super.srctx + LangPath
					+ super.devPage);
			break;
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		    int sck = getInitSocket(req) + 1;
		    flexLog("Socket not Open(Port " + sck + "). Error: " + e);
		    res.sendRedirect(super.srctx + LangPath
			    + super.sckNotOpenPage);
		    // return;
		} finally {
		    s.close();
		}

	    } catch (Exception e) {
		flexLog("Error: " + e);
		res.sendRedirect(super.srctx + LangPath
			+ super.sckNotRespondPage);
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
    protected void procReqCreditLines(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	UserPos userPO = null;

	try {

	    userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	    try {
		flexLog("About to call Page: "
			+ super.webAppPath
			+ "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=400&CUSNUM="
			+ userPO.getHeader2());
		res
			.sendRedirect(super.srctx
				+ "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=400&CUSNUM="
				+ userPO.getHeader2());
	    } catch (Exception e) {
		flexLog("Exception calling page " + e);
	    }

	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

    }

    protected void procReqPaymPlanAp(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL090001Message msgHeader = null;
	ELEERRMessage msgError = null;
	EDL090002Message msgList = null;
	JBListRec beanList = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	String sType = "";

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgHeader = (EDL090001Message) mc.getMessageRecord("EDL090001");
	    msgHeader.setH01USERID(user.getH01USR());
	    msgHeader.setH01PROGRM("EDL0900");
	    msgHeader.setH01TIMSYS(getTimeStamp());
	    msgHeader.setH01OPECOD(opCode);
	    try {
		msgHeader.setE01DEAACC(userPO.getIdentifier());
	    } catch (Exception e) {
		msgHeader.setE01DEAACC("0");
	    }
	    try {
		String s = req.getParameter("Type");
		if (s.equals("A")) {
		    sType = "APPROVAL";
		} else {
		    sType = "INQUIRY";
		}
	    } catch (Exception e) {
		sType = userPO.getPurpose().trim();
	    }

	    msgHeader.send();
	    msgHeader.destroy();

	    flexLog("EDL090001 Message Sent");
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Data
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		// showERROR(msgError);
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
	    } else if (newmessage.getFormatName().equals("EDL090001")) {
		try {
		    msgHeader = new datapro.eibs.beans.EDL090001Message();

		    flexLog("EDL090001 Message Received");

		    msgHeader = (EDL090001Message) newmessage;

		    // Header
		    userPO.setPurpose(sType);
		    userPO.setHeader10(Util.formatDate(
			    msgHeader.getE01OPEND1(), msgHeader.getE01OPEND2(),
			    msgHeader.getE01OPEND3())); // Opening Date
		    userPO.setHeader11(Util.formatDate(
			    msgHeader.getE01MATUR1(), msgHeader.getE01MATUR2(),
			    msgHeader.getE01MATUR3())); // Maturity Date
		    userPO
			    .setHeader12(Util.formatCCY(msgHeader
				    .getE01DEAOAM())); // Original Amount
		    userPO.setHeader13(Util
			    .formatCell(msgHeader.getE01DEARTE())); // Rate
		    userPO.setHeader14(Util
			    .formatCell(msgHeader.getE01DEABAS())); // Basis
		    userPO.setHeader15(Util
			    .formatCell(msgHeader.getE01DEAICT())); // Interest
		    // Type
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgHeader = (EDL090001Message) newmessage;

		// Fill List bean
		int colNum = 73;
		try {
		    beanList = new datapro.eibs.beans.JBListRec();
		    beanList.init(colNum);
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		char sel = ' ';
		String marker = "";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
		    myRow[i] = "";
		}

		BigDecimal principal = new BigDecimal("0");
		BigDecimal interest = new BigDecimal("0");
		BigDecimal other = new BigDecimal("0");
		BigDecimal payments = new BigDecimal("0");
		while (true) {

		    newmessage = mc.receiveMessage();
		    msgList = (EDL090002Message) newmessage;

		    marker = msgList.getE02ENDFLD();

		    if (marker.equals("*")) {
			break;
		    } else {
			// Quote List
			principal = principal.add(msgList
				.getBigDecimalE02DLPPRN());
			interest = interest.add(msgList
				.getBigDecimalE02DLPINT());
			other = other.add(msgList.getBigDecimalE02DLPOTH());
			payments = payments.add(msgList
				.getBigDecimalE02DLPPAG());

			myRow[0] = Util.formatCell(msgList.getE02DLPPNU()); // Quote
			// Number
			myRow[1] = Util.formatDate(msgList.getE02DLPPD1(),
				msgList.getE02DLPPD2(), msgList.getE02DLPPD3()); // Fecha
			// a
			// Pagar
			myRow[2] = Util.formatCCY(msgList.getE02DLPPRN()); // Principal
			// Pymt
			myRow[3] = Util.formatCCY(msgList.getE02DLPINT()); // Interest
			// Pymnt
			myRow[4] = Util.formatCCY(msgList.getE02DLPOTH()); // Other
			// Charges
			myRow[5] = Util.formatCell(msgList.getE02DLPTOT()); // Total
			// Quotes
			myRow[6] = Util.formatCell(msgList.getE02DLPBAL()); // Balance
			myRow[7] = Util.formatCell(msgList.getE02DLPSTS()); // Status
			myRow[8] = Util.formatCell(msgList.getE02DLPVEN()); // Mature
			myRow[9] = Util.formatDate(msgList.getE02DLPDT1(),
				msgList.getE02DLPDT2(), msgList.getE02DLPDT3()); // Fecha
			// a
			// Pagar
			myRow[10] = Util.formatCell(msgList.getE02DLPPAG()); // Total
			// Pymnt
			// Quote Detail
			myRow[11] = Util.formatCell(msgList.getE02DESC01()); // Description
			myRow[12] = Util.formatCell(msgList.getE02DESC02());
			myRow[13] = Util.formatCell(msgList.getE02DESC03());
			myRow[14] = Util.formatCell(msgList.getE02DESC04());
			myRow[15] = Util.formatCell(msgList.getE02DESC05());
			myRow[16] = Util.formatCell(msgList.getE02DESC06());
			myRow[17] = Util.formatCell(msgList.getE02DESC07());
			myRow[18] = Util.formatCell(msgList.getE02DESC08());
			myRow[19] = Util.formatCell(msgList.getE02DESC09());
			myRow[20] = Util.formatCell(msgList.getE02DESC10());
			myRow[21] = Util.formatCell(msgList.getE02DESC11());
			myRow[22] = Util.formatCell(msgList.getE02DESC12());
			myRow[23] = Util.formatCell(msgList.getE02DESC13());
			myRow[24] = Util.formatCell(msgList.getE02DESC14());
			myRow[25] = Util.formatCell(msgList.getE02DESC15());
			myRow[26] = Util.formatCell(msgList.getE02DESC16());
			myRow[27] = Util.formatCell(msgList.getE02DESC17());
			myRow[28] = Util.formatCell(msgList.getE02DESC18());
			myRow[29] = Util.formatCell(msgList.getE02DESC19());
			myRow[30] = Util.formatCell(msgList.getE02DESC20());
			myRow[31] = Util.formatCCY(msgList.getE02AMNT01()
				.trim()); // Amount
			myRow[32] = Util.formatCCY(msgList.getE02AMNT02()
				.trim());
			myRow[33] = Util.formatCCY(msgList.getE02AMNT03()
				.trim());
			myRow[34] = Util.formatCCY(msgList.getE02AMNT04()
				.trim());
			myRow[35] = Util.formatCCY(msgList.getE02AMNT05()
				.trim());
			myRow[36] = Util.formatCCY(msgList.getE02AMNT06()
				.trim());
			myRow[37] = Util.formatCCY(msgList.getE02AMNT07()
				.trim());
			myRow[38] = Util.formatCCY(msgList.getE02AMNT08()
				.trim());
			myRow[39] = Util.formatCCY(msgList.getE02AMNT09()
				.trim());
			myRow[40] = Util.formatCCY(msgList.getE02AMNT10()
				.trim());
			myRow[41] = Util.formatCCY(msgList.getE02AMNT11()
				.trim());
			myRow[42] = Util.formatCCY(msgList.getE02AMNT12()
				.trim());
			myRow[43] = Util.formatCCY(msgList.getE02AMNT13()
				.trim());
			myRow[44] = Util.formatCCY(msgList.getE02AMNT14()
				.trim());
			myRow[45] = Util.formatCCY(msgList.getE02AMNT15()
				.trim());
			myRow[46] = Util.formatCCY(msgList.getE02AMNT16()
				.trim());
			myRow[47] = Util.formatCCY(msgList.getE02AMNT17()
				.trim());
			myRow[48] = Util.formatCCY(msgList.getE02AMNT18()
				.trim());
			myRow[49] = Util.formatCCY(msgList.getE02AMNT19()
				.trim());
			myRow[50] = Util.formatCCY(msgList.getE02AMNT20()
				.trim());
			myRow[51] = Util.formatCCY(msgList.getE02PAID01()); // Paid
			myRow[52] = Util.formatCCY(msgList.getE02PAID02());
			myRow[53] = Util.formatCCY(msgList.getE02PAID03());
			myRow[54] = Util.formatCCY(msgList.getE02PAID04());
			myRow[55] = Util.formatCCY(msgList.getE02PAID05());
			myRow[56] = Util.formatCCY(msgList.getE02PAID06());
			myRow[57] = Util.formatCCY(msgList.getE02PAID07());
			myRow[58] = Util.formatCCY(msgList.getE02PAID08());
			myRow[59] = Util.formatCCY(msgList.getE02PAID09());
			myRow[60] = Util.formatCCY(msgList.getE02PAID10());
			myRow[61] = Util.formatCCY(msgList.getE02PAID11());
			myRow[62] = Util.formatCCY(msgList.getE02PAID12());
			myRow[63] = Util.formatCCY(msgList.getE02PAID13());
			myRow[64] = Util.formatCCY(msgList.getE02PAID14());
			myRow[65] = Util.formatCCY(msgList.getE02PAID15());
			myRow[66] = Util.formatCCY(msgList.getE02PAID16());
			myRow[67] = Util.formatCCY(msgList.getE02PAID17());
			myRow[68] = Util.formatCCY(msgList.getE02PAID18());
			myRow[69] = Util.formatCCY(msgList.getE02PAID19());
			myRow[70] = Util.formatCCY(msgList.getE02PAID20());
			myRow[71] = Util.formatCCY(msgList.getE02TOAMNT());
			myRow[72] = Util.formatCCY(msgList.getE02TOPAID());

			beanList.addRow(myFlag, myRow);

		    }

		}

		userPO.setHeader20(principal.toString());
		userPO.setHeader21(interest.toString());
		userPO.setHeader22(other.toString());
		userPO.setHeader23(payments.toString());
		userPO.setHeader19(principal.add(interest.add(other))
			.toString());

		ses.setAttribute("list", beanList);
		ses.setAttribute("header", msgHeader);
		ses.setAttribute("userPO", userPO);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0900_ln_ap_crn_pay.jsp");
		    callPage(LangPath + "EDL0900_ln_ap_crn_pay.jsp", req, res);
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
    protected void procReqCancelDebit(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDL015210Message msgLN = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgLN = (EDL015210Message) mc.getMessageRecord("EDL015210");
	    msgLN.setH10USERID(user.getH01USR());
	    msgLN.setH10PROGRM("EDL0150");
	    msgLN.setH10TIMSYS(""); // getTimeStamp()
	    msgLN.setH10SCRCOD("01");
	    msgLN.setH10OPECOD(opCode);
	    msgLN.setE10DEAACC(userPO.getIdentifier());
	    msgLN.send();
	    msgLN.destroy();

	    // Receive Error Message

	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage) newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		// showERROR(msgError);
	    } else
		flexLog("Message " + newmessage.getFormatName() + " received.");

	    // Receive Data

	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("EDL015210")) {

		msgLN = (EDL015210Message) newmessage;
		// showESD008004(msgLN);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("lnAutoDebit", msgLN);

		if (IsNotError) { // There are no errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_cancel_auto_debit.jsp");
			callPage(LangPath + "EDL0160_ln_cancel_auto_debit.jsp",
				req, res);
		    } catch (Exception e) {
			flexLog("Exception calling page " + e);
		    }
		} else { // There are errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EDL0160_ln_balances.jsp");
			callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
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

    protected void procReqAccountTitle(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	ESD000004Message msgMailA = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgMailA = (ESD000004Message) mc.getMessageRecord("ESD000004");
	    msgMailA.setH04USR(user.getH01USR());
	    msgMailA.setH04PGM("ESD0080");
	    msgMailA.setH04TIM(getTimeStamp());
	    msgMailA.setH04SCR("01");
	    msgMailA.setH04OPE(opCode);
	    msgMailA.setE04CUN(userPO.getIdentifier());
	    msgMailA.setE04RTP("1");
	    msgMailA.setH04WK1("T");
	    msgMailA.setH04WK3("2");
	    msgMailA.send();
	    msgMailA.destroy();
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
		ses.setAttribute("error", msgError);
		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_basic.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_basic.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("ESD000004")) {
		try {
		    msgMailA = new datapro.eibs.beans.ESD000004Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgMailA = (ESD000004Message) newmessage;
		// showESD000004(msgMailA);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("mailA", msgMailA);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_account_title.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_account_title.jsp",
			    req, res);
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

    protected void procReqInqProjectedPayOff(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	EDL016007Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (EDL016007Message) mc.getMessageRecord("EDL016007");
	    msgCD.setH07USERID(user.getH01USR());
	    msgCD.setH07PROGRM("EDL0160");
	    msgCD.setH07TIMSYS(getTimeStamp());
	    msgCD.setH07SCRCOD("01");
	    msgCD.setH07OPECOD(opCode);
	    msgCD.setE07DEAACC(userPO.getIdentifier());
	    msgCD.setH07FLGWK1("P");

	    try {
		msgCD.setE07TRNVD1(req.getParameter("E07TRNVD1"));
	    } catch (Exception e) {
	    }
	    try {
		msgCD.setE07TRNVD2(req.getParameter("E07TRNVD2"));
	    } catch (Exception e) {
	    }
	    try {
		msgCD.setE07TRNVD3(req.getParameter("E07TRNVD3"));
	    } catch (Exception e) {
	    }

	    msgCD.send();
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016007")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016007Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016007Message) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("inqProj", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_projected_payoff.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_projected_payoff.jsp",
			    req, res);
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

    protected void procReqSubmitProjectedPayOff(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	EDL016007Message msgCD = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
	    msgCD = (EDL016007Message) mc.getMessageRecord("EDL016007");
	    msgCD.setH07USERID(user.getH01USR());
	    msgCD.setH07PROGRM("EDL0160");
	    msgCD.setH07TIMSYS(getTimeStamp());
	    msgCD.setH07SCRCOD("01");
	    msgCD.setH07OPECOD(opCode);
	    msgCD.setE07DEAACC(userPO.getIdentifier());
	    msgCD.setH07FLGWK1("P");

	    // all the fields here
	    java.util.Enumeration enu = msgCD.fieldEnumeration();
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
	    msgCD.send();
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016007")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016007Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016007Message) newmessage;

		flexLog("Putting java beans into the session");
		ses.setAttribute("inqProj", msgCD);
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_projected_payoff.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_projected_payoff.jsp",
			    req, res);
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

    protected void procReqProjectedPayOffSel(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	ELEERRMessage msgError = null;

	try {

	    msgError = new ELEERRMessage();
	    ses.setAttribute("error", msgError);

	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	try {
	    flexLog("About to call Page: " + LangPath
		    + "EDL0160_ln_proj_sel.jsp");
	    callPage(LangPath + "EDL0160_ln_proj_sel.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}

    }

    protected void procReqDisplayTitle(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	EDL016099Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
	    msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String opCode = "0004";

	// Send Initial data
	try {
	    msgRT = (EDL016099Message) mc.getMessageRecord("EDL016099");
	    msgRT.setH99USERID(user.getH01USR());
	    msgRT.setH99PROGRM("ESD0000");
	    msgRT.setH99TIMSYS("");// getTimeStamp()
	    msgRT.setH99SCRCOD("01");
	    msgRT.setH99OPECOD(opCode);
	    msgRT.setE99ACCNUM(userPO.getIdentifier());
	    msgRT.send();
	    msgRT.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016099")) {
		try {
		    msgRT = new datapro.eibs.beans.EDL016099Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgRT = (EDL016099Message) newmessage;
		// showESD008004(msgRT);
		// userPO.setHeader10(msgRT.getE02ACMATY());
		// userPO.setHeader11(msgRT.getE02ACMACL());

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("cdTitle", msgRT);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_inq_display_title.jsp");
		    callPage(LangPath + "EDL0160_ln_inq_display_title.jsp",
			    req, res);
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

    protected void procReqPDF(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDL016007Message msgCD = null;
	UserPos userPO = null;

	String senderror = "1";

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	ESS0030DSMessage msgUser = null;
	msgUser = (ESS0030DSMessage) ses.getAttribute("currUser");

	// Send Initial data
	try {
	    msgCD = (EDL016007Message) mc.getMessageRecord("EDL016007");
	    msgCD.setH07USERID(user.getH01USR());
	    msgCD.setH07PROGRM("EDL0160");
	    msgCD.setH07TIMSYS(getTimeStamp());
	    msgCD.setH07SCRCOD("01");
	    msgCD.setH07OPECOD("0004");
	    msgCD.setE07DEAACC(userPO.getIdentifier());
	    msgCD.setH07FLGWK1("P");

	    try {
		msgCD.setE07TRNVD1(req.getParameter("E07TRNVD1"));
	    } catch (Exception e) {
	    }
	    try {
		msgCD.setE07TRNVD2(req.getParameter("E07TRNVD2"));
	    } catch (Exception e) {
	    }
	    try {
		msgCD.setE07TRNVD3(req.getParameter("E07TRNVD3"));
	    } catch (Exception e) {
	    }

	    msgCD.send();
	    msgCD.destroy();
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
	    newmessage = mc.receiveMessage();

	    if (newmessage.getFormatName().equals("ELEERR")) {
		try {
		    msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgError = (ELEERRMessage) newmessage;
		showERROR(msgError);

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		try {
		    flexLog("About to call Page: " + LangPath
			    + "EDL0160_ln_balances.jsp");
		    callPage(LangPath + "EDL0160_ln_balances.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EDL016007")) {
		try {
		    msgCD = new datapro.eibs.beans.EDL016007Message();
		} catch (Exception ex) {
		    flexLog("Error: " + ex);
		}

		msgCD = (EDL016007Message) newmessage;
		ses.setAttribute("inqProj", msgCD);
	    } else
		flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    throw new RuntimeException("Socket Communication Error");
	}

	int posi = 0;

	DocumentException ex = null;
	ByteArrayOutputStream baosPDF = null;

	try {
	    baosPDF = generatePDFDocumentBytes(req, this.getServletContext(),
		    ses);

	    StringBuffer sbFilename = new StringBuffer();
	    // sbFilename.append(msgHeader.getBigDecimalE02ACMACC());
	    // sbFilename.append(System.currentTimeMillis());
	    String fn = com.datapro.generic.tool.Util.getTimestamp().toString();
	    fn = Util.replace(fn, ":", "-");
	    fn = Util.replace(fn, ".", "-");
	    sbFilename.append(fn);
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

	} catch (DocumentException dex) {
	    res.setContentType("text/html");
	    PrintWriter writer = res.getWriter();
	    writer.println(this.getClass().getName() + " caught an exception: "
		    + dex.getClass().getName() + "<br>");
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
	    final HttpServletRequest req, final ServletContext ctx,
	    HttpSession session) throws DocumentException {

	ESS0030DSMessage msgUser = null;
	msgUser = (ESS0030DSMessage) session.getAttribute("currUser");

	EDL016007Message inqProj = null;
	inqProj = (EDL016007Message) session.getAttribute("inqProj");

	String title = "";

	String header01 = "";
	String header02 = "";
	String header03 = "";
	String header04 = "";
	String header05 = "";

	String detail01 = "";
	String detail02 = "";
	String detail03 = "";
	String detail04 = "";
	String detail05 = "";
	String detail06 = "";
	String detail07 = "";
	String detail08 = "";
	String detail09 = "";
	String detail10 = "";
	String detail11 = "";
	String detail12 = "";
	String detail13 = "";
	String detail14 = "";
	String detail15 = "";

	String footer1 = "";
	String footer2 = "";
	String footer3 = "";

	String page = "";

	title = "Projected Pay-Off";

	header01 = "Customer";
	header02 = "Name";
	header03 = "Account";
	header04 = "Product";
	header05 = "Currency";

	detail01 = "Requested Pay-off Date";
	detail02 = "Projected Net Pay-off";
	detail03 = "Current Net Pay-off";
	detail04 = "Principal Balance";
	detail05 = "Accrued Interest";
	detail06 = "Daily Factor";
	detail07 = "Uncollected Late Charges";
	detail08 = "Unaplied Funds (Partial/Early Payment)";
	detail09 = "Loan Fees Due";
	detail10 = "Early Pay-off Fees";
	detail11 = "Miscellaneous Fees";
	detail12 = "Escrow Balance";
	detail13 = "Projected Late Charge Date";
	detail14 = "Projected Late Charge Amount";
	detail15 = "Actual Rate";

	footer1 = "";
	footer2 = "";
	footer3 = "";

	page = "Page Number";

	String cuscun = inqProj.getE07DEACUN();
	String accnum = inqProj.getE07DEAACC();
	String name = inqProj.getE07CUSNA1();
	String prod = inqProj.getE07DEAPRO();
	String ccy = inqProj.getE07DEACCY();
	String reqPayOffDate1 = inqProj.getE07TRNVD1();
	while (reqPayOffDate1.length() < 2) {
	    reqPayOffDate1 = "0" + reqPayOffDate1;
	}
	String reqPayOffDate2 = inqProj.getE07TRNVD2();
	while (reqPayOffDate2.length() < 2) {
	    reqPayOffDate2 = "0" + reqPayOffDate2;
	}
	String reqPayOffDate3 = inqProj.getE07TRNVD3();
	while (reqPayOffDate3.length() < 2) {
	    reqPayOffDate3 = "0" + reqPayOffDate3;
	}
	String reqPayOffDate = reqPayOffDate1 + "/" + reqPayOffDate2 + "/"
		+ reqPayOffDate3;
	String projNetPayOff = "";
	try {
	    projNetPayOff = Util.formatCCY(req.getParameter("E07TRNTOT")); // inqProj.getE07TRNTOT();
	} catch (Exception e) {
	    projNetPayOff = "";
	}
	String currNetPayOff = inqProj.getE07TRNAVL();
	String ppalBalance = inqProj.getE07DEAMEP();
	String accruedInt = inqProj.getE07DEAMEI();
	String dailyFactor = inqProj.getE07DEAREA();
	String uncollLateCharge = inqProj.getE07TRNPEN();
	String unapFunds = inqProj.getE07DEATDA();
	String loanFeesDue = "";
	try {
	    loanFeesDue = Util.formatCCY(req.getParameter("E07TRNWHL")); // inqProj.getE07TRNWHL()
	} catch (Exception e) {
	    loanFeesDue = "";
	}
	String earlyPayOffFees = inqProj.getE07TRNADJ();
	String miscFees = "";
	try {
	    miscFees = Util.formatCCY(req.getParameter("E07TRNREA")); // inqProj.getE07TRNREA()
	} catch (Exception e) {
	    miscFees = "";
	}
	String escrowBalance = inqProj.getE07TRNTAX();
	String projLateChargeDate1 = inqProj.getE07TRNVD1();
	while (reqPayOffDate1.length() < 2) {
	    projLateChargeDate1 = "0" + projLateChargeDate1;
	}
	String projLateChargeDate2 = inqProj.getE07TRNVD2();
	while (projLateChargeDate2.length() < 2) {
	    projLateChargeDate2 = "0" + projLateChargeDate2;
	}
	String projLateChargeDate3 = inqProj.getE07TRNVD3();
	while (projLateChargeDate3.length() < 2) {
	    projLateChargeDate3 = "0" + projLateChargeDate3;
	}
	String projLateChargeDate = projLateChargeDate1 + "/"
		+ projLateChargeDate2 + "/" + projLateChargeDate3;
	String projLateChargeAmt = inqProj.getE07DEATAX();
	String accrualRate = inqProj.getE07DEARTE();

	Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

	ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
	PdfWriter docWriter = null;

	try {
	    docWriter = PdfWriter.getInstance(doc, baosPDF);

	    doc.addAuthor("eIBS");
	    doc.addCreationDate();
	    doc.addProducer();
	    doc.addCreator(accnum);
	    doc.addTitle(title);
	    doc.addKeywords("pdf, itext, Java, open source, http");

	    Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
		    Font.NORMAL);
	    Font normalBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
		    Font.BOLD);
	    Font normalBoldUnderFont = FontFactory.getFont(
		    FontFactory.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
	    Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10,
		    Font.NORMAL);
	    Font headerBoldFont = FontFactory.getFont(FontFactory.HELVETICA,
		    10, Font.BOLD);
	    Font headerBoldUnderFont = FontFactory.getFont(
		    FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE);

	    Paragraph TITLE = new Paragraph(title, headerBoldFont);

	    Paragraph RAYA = new Paragraph(
		    "____________________________________________________________________________________________________________________",
		    normalBoldFont);

	    Paragraph HEADER01 = new Paragraph(header01 + ": ", headerBoldFont);
	    Paragraph HEADER02 = new Paragraph(header02 + ": ", headerBoldFont);
	    Paragraph HEADER03 = new Paragraph(header03 + ": ", headerBoldFont);
	    Paragraph HEADER04 = new Paragraph(header04 + ": ", headerBoldFont);
	    Paragraph HEADER05 = new Paragraph(header05 + ": ", headerBoldFont);

	    Paragraph HEADER01DATA = new Paragraph(cuscun, headerBoldFont);
	    Paragraph HEADER02DATA = new Paragraph(name, headerBoldFont);
	    Paragraph HEADER03DATA = new Paragraph(accnum, headerBoldFont);
	    Paragraph HEADER04DATA = new Paragraph(prod, headerBoldFont);
	    Paragraph HEADER05DATA = new Paragraph(ccy, headerBoldFont);

	    Paragraph BLANK = new Paragraph("", headerBoldFont);

	    Paragraph DETAIL01 = new Paragraph(detail01 + ": ", headerBoldFont);
	    Paragraph DETAIL02 = new Paragraph(detail02 + ": ", headerBoldFont);
	    Paragraph DETAIL03 = new Paragraph(detail03 + ": ", headerBoldFont);
	    Paragraph DETAIL04 = new Paragraph(detail04 + ": ", headerBoldFont);
	    Paragraph DETAIL05 = new Paragraph(detail05 + ": ", headerBoldFont);
	    Paragraph DETAIL06 = new Paragraph(detail06 + ": ", headerBoldFont);
	    Paragraph DETAIL07 = new Paragraph(detail07 + ": ", headerBoldFont);
	    Paragraph DETAIL08 = new Paragraph(detail08 + ": ", headerBoldFont);
	    Paragraph DETAIL09 = new Paragraph(detail09 + ": ", headerBoldFont);
	    Paragraph DETAIL10 = new Paragraph(detail10 + ": ", headerBoldFont);
	    Paragraph DETAIL11 = new Paragraph(detail11 + ": ", headerBoldFont);
	    Paragraph DETAIL12 = new Paragraph(detail12 + ": ", headerBoldFont);
	    Paragraph DETAIL13 = new Paragraph(detail13 + ": ", headerBoldFont);
	    Paragraph DETAIL14 = new Paragraph(detail14 + ": ", headerBoldFont);
	    Paragraph DETAIL15 = new Paragraph(detail15 + ": ", headerBoldFont);

	    Paragraph DETAIL01DATA = new Paragraph(reqPayOffDate, headerFont);
	    Paragraph DETAIL02DATA = new Paragraph(projNetPayOff, headerFont);
	    Paragraph DETAIL03DATA = new Paragraph(currNetPayOff, headerFont);
	    Paragraph DETAIL04DATA = new Paragraph(ppalBalance, headerFont);
	    Paragraph DETAIL05DATA = new Paragraph(accruedInt, headerFont);
	    Paragraph DETAIL06DATA = new Paragraph(dailyFactor, headerFont);
	    Paragraph DETAIL07DATA = new Paragraph(uncollLateCharge, headerFont);
	    Paragraph DETAIL08DATA = new Paragraph(unapFunds, headerFont);
	    Paragraph DETAIL09DATA = new Paragraph(loanFeesDue, headerFont);
	    Paragraph DETAIL10DATA = new Paragraph(earlyPayOffFees, headerFont);
	    Paragraph DETAIL11DATA = new Paragraph(miscFees, headerFont);
	    Paragraph DETAIL12DATA = new Paragraph(escrowBalance, headerFont);
	    Paragraph DETAIL13DATA = new Paragraph(projLateChargeDate,
		    headerFont);
	    Paragraph DETAIL14DATA = new Paragraph(projLateChargeAmt,
		    headerFont);
	    Paragraph DETAIL15DATA = new Paragraph(accrualRate, headerFont);

	    HeaderFooter header = new HeaderFooter(TITLE, false);
	    header.setBorder(Rectangle.NO_BORDER);
	    header.setAlignment(Element.ALIGN_CENTER);
	    doc.setHeader(header);

	    HeaderFooter footer = new HeaderFooter(new Phrase(page), false);
	    footer.setBorder(Rectangle.NO_BORDER);
	    doc.setFooter(footer);

	    doc.open();

	    Table table = new Table(4, 3);
	    table.setBorderWidth(0);
	    table.setCellsFitPage(true);
	    table.setPadding(1);
	    table.setSpacing(1);
	    table.setWidth(100);

	    Cell cell = new Cell(HEADER01);
	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER01DATA);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER02);
	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER02DATA);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER03);
	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER03DATA);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER04);
	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER04DATA);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER05);
	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    cell = new Cell(HEADER05DATA);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    table.addCell(cell);

	    doc.add(table);

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

	    int NumColumns = 2;
	    PdfPTable datatable = new PdfPTable(NumColumns);

	    datatable.getDefaultCell().setPadding(3);
	    int headerwidths[] = { 70, 30 }; // percentage
	    datatable.setWidths(headerwidths);
	    datatable.setWidthPercentage(60); // percentage
	    datatable.setHorizontalAlignment(Element.ALIGN_CENTER);
	    datatable.getDefaultCell().setBorderWidth(1);
	    datatable.setHeaderRows(0);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL01);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL01DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL02);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL02DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL03);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL03DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL04);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL04DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL05);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL05DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL06);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL06DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL07);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL07DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL08);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL08DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL09);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL09DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL10);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL10DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL11);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL11DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL12);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL12DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL13);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL13DATA);

	    datatable.getDefaultCell().setGrayFill(0.0f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL14);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL14DATA);

	    datatable.getDefaultCell().setGrayFill(0.9f);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_LEFT);
	    datatable.addCell(DETAIL15);
	    datatable.getDefaultCell().setHorizontalAlignment(
		    Element.ALIGN_RIGHT);
	    datatable.addCell(DETAIL15DATA);

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
	    throw new DocumentException("document has " + baosPDF.size()
		    + " bytes");
	}

	return baosPDF;
    }

    protected void getTitularsDescription(ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	EWD0002DSMessage msgCnofc = null;
	JBObjList ObjList = null;
	MessageContext mc = null;

	try {
	    flexLog("Opennig Socket Connection");
	    mc = new MessageContext(getMessageHandler("EWD0002", req));
	    try {
		msgCnofc = (EWD0002DSMessage) mc.getMessageRecord("EWD0002DS");
		msgCnofc.setEWDREC("0");
		msgCnofc.setEWDTBL("T8");
		msgCnofc.send();
		msgCnofc.destroy();
		flexLog("EWD000202 Message Sent");
	    } catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		res.sendRedirect(super.srctx + LangPath
			+ super.sckNotRespondPage);
		return;
	    }
	    // Receiving
	    try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("EWD0002DS")) {
		    try {
			ObjList = new JBObjList();
		    } catch (Exception ex) {
			System.out.println("Error: " + ex);
		    }
		    try {
			StringBuffer myRow = null;
			// for(msgCnofc = (EWD0002DSMessage)newmessage;
			// !msgCnofc.getEWDOPE().equals("*"); msgCnofc =
			// (EWD0002DSMessage)newmessage)
			while (true) {
			    msgCnofc = (EWD0002DSMessage) newmessage;
			    if (!msgCnofc.getEWDOPE().equals("+"))
				break;
			    ObjList.addRow(msgCnofc);
			    newmessage = mc.receiveMessage();
			}
			ses.setAttribute("cnofcList", ObjList);
		    } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		    }
		}
	    } catch (Exception e) {
		e.printStackTrace();
		System.out.println("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    flexLog("Error: " + e);
	    res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
	}
    }

}