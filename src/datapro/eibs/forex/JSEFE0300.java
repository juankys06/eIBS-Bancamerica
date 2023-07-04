package datapro.eibs.forex;

/**
 * Creation date: (04/30/2002 6:02:17 PM)
 * @author: David Mavilla / 02/21/08 C. Castillo Adjust to R48 
 **/

/**
 * This class drives all the process for Treasury
 * linking the following classes :
 * 
 * JSEDL0120  ----- Foreign Exchange Deals
 * JSEFE0120P ----- Foreign Exchange Maintenance
 * JSEFE0325  ----- Customer Limits Maintenance
 * JSELN0000T ----- Credit Lines Maintenance
 * JSELN0115  ----- Credit Lines Incidence
 * JSETR0120  ----- Forward Rate Agreements
 * JSEWD0012F ----- Currency Rates Inquiry
 * JSEWD0015T ----- Credit Lines Inquiry Help
 * JSEWD0321I ----- Today Deals Inquiry
 * JSEWD0322T ----- Customer Limits Inquiry
 * JSEWD0322S ----- Customer Limits Maintenance Selection
 * JSEWD0323  ----- Currency Position
 * JSEWD0324  ----- Credit Lines Incidence Selection
 * JSEWD0321  ----- Today Deals Help 
 *  
 * This class utilized the following classes itself :
 * 
 *  JSPs :
 * 
 *  EFE0000_fe_enter_opt.jsp     -----  Dealer Slip Management
 *  EFE0000_fe_enter_delete.jsp	 -----  Entering Deletion  
 *  EFE0000_fe_enter_inquiry.jsp -----  Entering Inquiry
 *	EFE0000_fe_enter_prof.jsp	 -----  Entering Profitability
 *	EFE0000_fe_enter_set.jsp	 -----  Entering Settlement Limits
 *  EFE0000_fe_enter_lin.jsp	 -----  Entering Credit Lines
 *	EFE0120_del_confirm.jsp		 -----  Deletion Confirmation
 * 
 *  Beans :
 * 
 *  ELEERRMessage    			 ----- 	Error Message
 *  EFE0120DSMessage			 ----- 	Foreign Exchange Data Bean
 *  EWD0001RMessage     		 ----- 	Customer Search Send
 *  EWD0001SMessage				 -----  Customer Search Send
 *  JBList						 -----  Data Transfer Auxiliar (Multiple)
 *  UserPos						 -----  Data Transfer Auxiliar (Single)
 *  
 **/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import datapro.eibs.beans.EDL0120DSMessage;
import datapro.eibs.beans.EFE010001Message;
import datapro.eibs.beans.EFE0120DSMessage;
import datapro.eibs.beans.EFE0300DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ETR0120DSMessage;
import datapro.eibs.beans.EWD0001RMessage;
import datapro.eibs.beans.EWD0001SMessage;
import datapro.eibs.beans.EWD0301DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.TrOption;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEFE0300 extends datapro.eibs.master.SuperServlet {

    protected static final int R_SEARCH = 1;

    protected static final int A_SUBMIT = 2;

    protected static final int R_INFO = 3;

    protected static final int R_LIST = 4;

    protected static final int R_ENTER_BASIC = 100;

    protected static final int R_ENTER_INQUIRY = 300;

    protected static final int R_ENTER_PROF = 500;

    protected static final int R_ENTER_CALC = 700;

    protected static final int R_ENTER_LIN = 900;

    protected static final int R_ENTER_SET = 1100;

    protected static final int R_ENTER_DELETE = 1300;

    protected static final int R_ENTER_CUST_DEALS = 1500;

    protected static final int A_ENTER_BASIC = 200;

    protected static final int A_ENTER_DELETE = 400;

    protected static final int A_ENTER_DELETE_DEALS = 1800;

    protected static final int A_ENTER_DELETE_FRA = 2000;

    protected static final int A_ENTER_CUST_DEALS = 2200;

    protected static final int R_PRODUCTS_BASIC = 3000;

    protected String LangPath = "S";

    /**
         * Constructor without params
         */
    public JSEFE0300() {
	super();
    }

    /**
         * Constructor with 1 param.
         * 
         * @param logType
         *                int
         */
    public JSEFE0300(int logType) {
	super(logType);

    }

    /**
         * Menu
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

	    int screen = R_ENTER_BASIC;

	    try {
		msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
			.getAttribute("currUser");

		// Here we should get the path from the user profile
		LangPath = super.rootPath + msgUser.getE01LAN() + "/";

		try {
		    flexLog("Opening Socket Connection");
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
		    case R_SEARCH:
			_procReqEnterBasic(mc, msgUser, req, res, session);
			break;
		    case R_LIST:
			_procActionEnterMonExt(mc, msgUser, req, res, session);
			break;
		    case A_SUBMIT:
			procReqList(mc, msgUser, req, res, session);
			break;
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

    protected void _procActionEnterMonExt(MessageContext mc,
	    ESS0030DSMessage user, HttpServletRequest req,
	    HttpServletResponse res, HttpSession ses) throws ServletException,
	    IOException {

	MessageRecord newmessage = null;
	ETR0120DSMessage msgFex = null;
	EFE0300DSMessage msgMod = null;
	ELEERRMessage msgError = null;
	JBObjList beanList = null;
	UserPos userPO = null;
	int OpT = 0;
	boolean IsNotError = false;

	try {
	    msgError = new ELEERRMessage();
	    OpT = Integer.parseInt(req.getParameter("OPTION"));
	    beanList = (JBObjList) ses.getAttribute("beanList");
	    msgMod = (EFE0300DSMessage) beanList.get(OpT);
	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}
	// Send Initial data
	ses.setAttribute("fex", msgMod);

	try {
	    flexLog("About to call Page: " + LangPath
		    + "EFE0300_fe_basic_ModExt.jsp");
	    callPage(LangPath + "EFE0300_fe_basic_ModExt.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page EFE0300_fe_basic_ModExt.jsp" + e);
	}

    }

    /**
         * This method was created in VisualAge.
         */
    protected void _procReqEnterBasic(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EWD0001RMessage msgCust = null;
	EWD0001SMessage msgCustS = null;
	EFE0300DSMessage msgModExt = null;
	JBList beanList = null;
	UserPos userPO = null;

	try {

	    userPO = new UserPos();
	    userPO.setOption("FEX");
	    userPO.setPurpose("MAINTENANCE");
	    ses.setAttribute("userPO", userPO);

	} catch (Exception ex) {
	    flexLog("Error: " + ex);
	}

	try {
	    flexLog("About to call Page: " + LangPath
		    + "EFE0300_pr_inq_search.jsp");
	    callPage(LangPath + "EFE0300_pr_inq_search.jsp", req, res);
	} catch (Exception e) {
	    flexLog("Exception calling page " + e);
	}
    }

    protected void procReqList(MessageContext mc, ESS0030DSMessage user,
	    HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	    throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	// EFE010001Message msgSearch = null;
	// EFE010001Message msgList = null;
	// ETR0120DSMessage msgFex = null;
	EFE0300DSMessage msgMod = null;
	UserPos userPO = null;
	JBObjList beanList = null;
	JBObjList beanListTOT = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
	    flexLog("Send Initial Data");
	    msgMod = (EFE0300DSMessage) mc.getMessageRecord("EFE0300DS");
	    msgMod.setH01USERID(user.getH01USR());
	    msgMod.setH01PROGRM("EFE0300");
	    msgMod.setH01TIMSYS(getTimeStamp());
	    msgMod.setH01SCRCOD("01");
	    msgMod.setH01OPECOD("0002");

	    // Setting parameters
	    try {
		msgMod.setE01FEIDDI(new java.math.BigDecimal(req
			.getParameter("E01FEIDDI")));
	    } catch (Exception e) {
		msgMod.setE01FEIDDI("0");
	    }
	    try {
		msgMod.setE01FEIMMI(new java.math.BigDecimal(req
			.getParameter("E01FEIMMI")));
	    } catch (Exception e) {
		msgMod.setE01FEIMMI("0");
	    }
	    try {
		String iyyi = req.getParameter("E01FEIYYI");
		if (iyyi.trim().length() == 2)
		    iyyi = "20".concat(iyyi);
		msgMod.setE01FEIYYI(new java.math.BigDecimal(iyyi));
	    } catch (Exception e) {
		msgMod.setE01FEIYYI("0");
	    }
	    try {
		msgMod.setE01FEIDDF(new java.math.BigDecimal(req
			.getParameter("E01FEIDDF")));
	    } catch (Exception e) {
		msgMod.setE01FEIDDF("0");
	    }
	    try {
		msgMod.setE01FEIMMF(new java.math.BigDecimal(req
			.getParameter("E01FEIMMF")));
	    } catch (Exception e) {
		msgMod.setE01FEIMMF("0");
	    }
	    try {
		String iyyf = req.getParameter("E01FEIYYF");
		if (iyyf.trim().length() == 2)
		    iyyf = "20".concat(iyyf);
		msgMod.setE01FEIYYF(new java.math.BigDecimal(iyyf));
	    } catch (Exception e) {
		msgMod.setE01FEIYYF("0");
	    }

	    try {
		msgMod.setE01FEICUN(new java.math.BigDecimal(req
			.getParameter("E01FEICUN")));
	    } catch (Exception e) {
		msgMod.setE01FEICUN("0");
	    }

	    try {
		msgMod.setE01FEICCY(req.getParameter("E01FEICCY").trim());
	    } catch (Exception e) {
		msgMod.setE01FEICCY("");
	    }

	    try {
		msgMod.setE01FEICID(req.getParameter("E01FEICID").trim());
	    } catch (Exception e) {
		msgMod.setE01FEICID("");
	    }

	    try {
		msgMod.setE01FEIBRN(new java.math.BigDecimal(req
			.getParameter("E01FEIBRN")));
	    } catch (Exception e) {
		msgMod.setE01FEIBRN("0");
	    }
	    try {
		msgMod.setE01FEIUSR(req.getParameter("E01FEIUSR"));
	    } catch (Exception e) {
		msgMod.setE01FEIUSR("");
	    }
	    msgMod.setE01FEIREV(req.getParameter("E01FEIREV") != null ? req
		    .getParameter("E01FEIREV") : "");

	    EFE0300DSMessage qryForexSearchParams = new EFE0300DSMessage();
	    BeanUtils.copyProperties(qryForexSearchParams, msgMod);
	    ses.setAttribute("qryForexSearchParams", qryForexSearchParams);
	    msgMod.send();
	    msgMod.destroy();
	    flexLog("EFE0300DS Message Sent");

	    // Receive Message
	    newmessage = mc.receiveMessage();
	    if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = new ELEERRMessage();
		msgError = (ELEERRMessage) newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
		showERROR(msgError);
		ses.setAttribute("error", msgError);
		try {
		    flexLog("About to call Page: " + LangPath
			    + "EFE0300_pr_inq_search.jsp");
		    callPage(LangPath + "EFE0300_pr_inq_search.jsp", req, res);
		} catch (Exception e) {
		    flexLog("Exception calling page " + e);
		}
	    } else if (newmessage.getFormatName().equals("EFE0300DS")) {
		beanList = new JBObjList();
		beanListTOT = new JBObjList();
		String marker = "";

		while (true) {
		    msgMod = (EFE0300DSMessage) newmessage;
		    marker = msgMod.getH01FLGMAS();

		    if (marker.equals("*")) {
			beanList.setShowNext(false);
			break;
		    } else {
			// Registro Detalle
			if (msgMod.getE01FEIINF().equals("D"))
			    beanList.addRow(msgMod);
			// Registro Total
			if (msgMod.getE01FEIINF().equals("R"))
			    beanListTOT.addRow(msgMod);
			if (marker.equals("+")) {
			    beanList.setShowNext(true);
			    break;
			}
		    }
		    newmessage = mc.receiveMessage();
		}
		userPO.setBank(req.getParameter("E01FEICUN"));

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("beanList", beanList);
		ses.setAttribute("beanListTOT", beanListTOT);
		ses.setAttribute("userPO", userPO);

		if (IsNotError) { // There are no errors
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EFE0300_fx_mod_ext_list.jsp");
			callPage(LangPath + "EFE0300_fx_mod_ext_list.jsp", req,
				res);
		    } catch (Exception e) {
			flexLog("Exception calling page " + e);
		    }

		} else {
		    try {
			flexLog("About to call Page: " + LangPath
				+ "EFE0300_pr_inq_search.jsp");
			callPage(LangPath + "EFE0300_pr_inq_search.jsp", req,
				res);
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