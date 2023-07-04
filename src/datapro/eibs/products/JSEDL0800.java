package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM) x
 * @author: Enrique M. Almonte
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util; 
import java.util.Calendar;
import java.math.*;

import datapro.eibs.sockets.*;

public class JSEDL0800 extends datapro.eibs.master.SuperServlet {
	
	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int R_TRANSACTION = 5;
	protected static final int A_TRANSACTION = 6;
	protected static final int R_TITULARES = 7;
	protected static final int A_TITULARES = 8;
	protected static final int R_PRECANCEL = 9;
	protected static final int A_PRECANCEL = 10;
	protected static final int R_SPECIAL_INST = 11;
	protected static final int A_SPECIAL_INST = 12;
	protected static final int R_EXCHANGE = 13;
	protected static final int A_EXCHANGE = 14;
	protected static final int R_CODES = 15;
	protected static final int A_CODES = 16;
	protected static final int R_FINISH = 17;
	protected static final int A_FINISH = 18;
	protected static final int R_INT_PREP = 19;
	protected static final int A_INT_PREP = 20;
	protected static final int R_CANCEL_DET = 21;
	protected static final int A_CANCEL_DET = 22;
	protected static final int R_PRINT_NEW = 21;
	protected static final int A_PRINT_NEW = 22;
	protected static final int R_GEN_INF = 23;
	protected static final int A_GEN_INF = 24;
	protected static final int R_NEW_TRAN = 25;
	protected static final int A_NEW_TRAN = 26;
	protected static final int R_OTHERS_TRANS = 27;
	protected static final int R_INQ = 28;
	protected static final int R_INQ_TITULARES = 29;
	protected static final int R_INQ_SPECIAL_INST = 30;
	protected static final int R_INQ_CODES = 31;
	protected static final int R_INQ_OTHERS_TRANS = 32;
	protected static final int R_AP_TITULARES = 33;
	protected static final int R_AP_SPECIAL_INST = 34;
	protected static final int R_AP_CODES = 35;
	protected static final int R_AP_OTHERS_TRANS = 36;
	protected static final int R_INQ_NEW_TRAN = 37;
	protected static final int R_AP_NEW_TRAN = 38;
	protected static final int R_INQ_BALANCES = 39;
	
	protected static final int R_CANCEL_DEBIT = 41;
	protected static final int A_CANCEL_DEBIT = 42;

	protected static final int A_MAINT_TO_BEAN = 500;
	protected static final int A_MAINT_TO_SCK = 502;
	protected static final int A_MAINT_DED = 504;
	protected static final int A_MAINT_COLL = 506;
	protected static final int A_MAINT_PMNT = 508;

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int A_ENTER_NEW = 200;
	protected static final int R_ENTER_MAINT = 300;
	protected static final int A_ENTER_MAINT = 400;
	protected static final int R_ENTER_ACC_MAINT = 500;
	protected static final int A_ENTER_ACC_MAINT = 600;
	protected static final int R_ENTER_DOCUMENT = 700;
	protected static final int A_ENTER_DOCUMENT = 800;
	protected static final int R_ENTER_PRINT = 900;
	protected static final int A_ENTER_GEN_DOCS = 1000;
	protected static final int R_INQ_APPROVAL = 1200;
	protected static final int R_INQUIRY = 1400;
	protected static final int A_INQUIRY = 1600;
	protected static final int R_ENTER_DOC_DET = 1700;

	private String LangPath = "S";

	/**
	 * JSEDL0800 constructor comment.
	 */
	public JSEDL0800() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0800");

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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		JBListRec lstAcceptors = new JBListRec();
		ses.setAttribute("lstAcceptors", lstAcceptors);

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftBasic = (EDL080001Message) mc.getMessageRecord("EDL080001");
			dftBasic.setH01USERID(user.getH01USR());
			dftBasic.setH01PROGRM("EDL0800");
			dftBasic.setH01TIMSYS(getTimeStamp());
			dftBasic.setH01SCRCOD("01");
			dftBasic.setH01OPECOD("0002");
			dftBasic.setE01DEAACD("10");
			try {
				dftBasic.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}
			try {
				dftBasic.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}
			mc.sendMessage(dftBasic);

			flexLog("EDL080001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to redirect : "
							+ LangPath
							+ "EDL0800_dft_basic.jsp");
					//callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=1&E01DEAACC="
							+ req.getParameter("E01DEAACC"));
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_ln_enter_maint.jsp");
					callPage(
						LangPath + "EDL0800_dft_enter_maint.jsp",
						req,
						res);
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
	protected void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		JBListRec lstAcceptors = new JBListRec();
		ses.setAttribute("lstAcceptors", lstAcceptors);

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftBasic = (EDL080001Message) mc.getMessageRecord("EDL080001");
			dftBasic.setH01USERID(user.getH01USR());
			dftBasic.setH01PROGRM("EDL0800");
			dftBasic.setH01TIMSYS(getTimeStamp());
			dftBasic.setH01SCRCOD("01");
			dftBasic.setH01OPECOD("0001");
			dftBasic.setE01DEAACD("10");
			try {
				dftBasic.setE01DEAPRO(req.getParameter("E01DEAPRO"));
			} catch (Exception e) {
				dftBasic.setE01DEAPRO("0");
			}

			try {
				dftBasic.setE01DEARET(req.getParameter("E01DEARET"));
			} catch (Exception e) {
				dftBasic.setE01DEARET(" ");
			}

			try {
				dftBasic.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}

			try {
				dftBasic.setE01PAGACC(req.getParameter("E01OFFAC1"));
			} catch (Exception e) {
			}

			try {
				dftBasic.setE01DEAOAM(req.getParameter("E01ACMAMT"));
			} catch (Exception e) {
			}

			dftBasic.send();
			dftBasic.destroy();
			flexLog("EDL080001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_basic.jsp");
					callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: /servlet/datapro.eibs.products.JSESD0711?TYPE=10");
					//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESD0711?TYPE=10");
					String firstLink =
						super.webAppPath
							+ LangPath
							+ "ESD0711_products_detail.jsp?appcode="
							+ req.getParameter("appcode").trim()
							+ "&typecode="
							+ req.getParameter("typecode").trim()
							+ "&generic="
							+ req.getParameter("generic").trim()
							+ "&title="
							+ req.getParameter("title").trim()
							+ "&bank="
							+ req.getParameter("bank").trim();
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<!-- frames -->");
					out.println("<frameset  cols=\"28%,*\">");
					out.println(
						"<frame name=\"list\" src=\""
							+ super.webAppPath
							+ LangPath
							+ "ESD0711_products_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
					out.println(
						"<frame name=\"detail\" src=\""
							+ firstLink
							+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
					out.println("</frameset>");
					out.close();
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
	protected void procActionEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message dftBasic = null;
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
			flexLog("Send Initial Data");
			dftBasic = (ESD000005Message) ses.getAttribute("lnInst");
			//msgLN = (ESD000005Message)mc.getMessageRecord("ESD000005");
			dftBasic.setH05USR(user.getH01USR());
			dftBasic.setH05PGM("EDL0150");
			dftBasic.setH05TIM(""); //getTimeStamp()
			dftBasic.setH05SCR("01");
			dftBasic.setH05OPE("0005");
			dftBasic.setE05ACC(userPO.getIdentifier());
			dftBasic.setE05TYP("2");
			dftBasic.setH05WK1("M");
			// all the fields here
			java.util.Enumeration enu = dftBasic.fieldEnumeration();
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

			//msgLN.send();
			mc.sendMessage(dftBasic);
			dftBasic.destroy();

			flexLog("ESD000005 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000005")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000005Message();
					flexLog("ESD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000005Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnInst", dftBasic);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_special_inst.jsp");
						callPage(
							LangPath + "EDL0800_dft_special_inst.jsp",
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
	protected void procActionExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015206Message msgLN = null;
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
			flexLog("Send Initial Data");
			msgLN = (EDL015206Message) ses.getAttribute("lnRate");
			//msgLN = (EDL015206Message)mc.getMessageRecord("EDL015206");
			msgLN.setH06USERID(user.getH01USR());
			msgLN.setH06PROGRM("EDL0150");
			msgLN.setH06TIMSYS(getTimeStamp());
			msgLN.setH06SCRCOD("01");
			msgLN.setH06OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgLN.fieldEnumeration();
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

			//msgLN.send();
			mc.sendMessage(msgLN);
			msgLN.destroy();

			flexLog("EDL015206 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL015206")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015206Message();
					flexLog("EDL015206 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015206Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnRate", msgLN);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0150_ln_new_transac.jsp");
							callPage(
								LangPath + "EDL0150_ln_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0150_ln_basic.jsp");
							callPage(
								LangPath + "EDL0150_ln_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_xchg_rate.jsp");
						callPage(
							LangPath + "EDL0150_ln_xchg_rate.jsp",
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
	protected void procActionMaintBeansToSck(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		Socket s = null;
		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		EDL080003Message dftAcceptor = null;
		EDL080002Message dftDocument = null;
		EDL080030Message dftTrans = null;

		JBListRec lstAcceptors = null;
		JBListRec lstDocs = null;
		JBListRec lstTrans = null;

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
		//grp = 0 - basic data of draft
		//    = 1 - basic data of draft acceptor
		//    = 2 - document data 1 x 1
		//    = 3 - transac data
		//    = 4 - documents generation

		String grp = "";
		String genDoc = "";
		String act = "";
		try {
			grp = req.getParameter("GRP");
			if (grp == null)
				grp = "";
		} catch (Exception e) {
			grp = "0";
		}
		try {
			genDoc = req.getParameter("GENDOC");
			if (genDoc == null)
				genDoc = "";
		} catch (Exception e) {
			genDoc = "0";

		}
		try {
			act = req.getParameter("ACTION");
			if (act == null)
				act = "";
		} catch (Exception e) {
			act = "0";
		}

		if (grp.equals("0")) {
			try {
				dftBasic = (EDL080001Message) ses.getAttribute("dftBasic");
				dftBasic.setH01USERID(user.getH01USR());
				dftBasic.setH01PROGRM("EDL0800");
				dftBasic.setH01TIMSYS(getTimeStamp());
				dftBasic.setH01SCRCOD("01");
				dftBasic.setH01OPECOD("0005");

				// all the fields here
				java.util.Enumeration enu = dftBasic.fieldEnumeration();
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
				flexLog("EDL080001 Message " + dftBasic);
				mc.sendMessage(dftBasic);
				dftBasic.destroy();
				flexLog("EDL080001 Message Sent to Sockets");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}
		//Sending EDL080003
		if (grp.equals("1")) {
			try {
				dftAcceptor =
					(EDL080003Message) ses.getAttribute("dftAcceptor");
				dftAcceptor.setH03USERID(user.getH01USR());
				dftAcceptor.setH03PROGRM("EDL0800");
				dftAcceptor.setH03TIMSYS(getTimeStamp());
				dftAcceptor.setH03SCRCOD("01");
				dftAcceptor.setH03OPECOD("0005");

				// all the fields here
				java.util.Enumeration enu = dftAcceptor.fieldEnumeration();
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
				mc.sendMessage(dftAcceptor);
				dftAcceptor.destroy();
				flexLog("EDL080003 Message Sent to Sockets");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

		//Sending EDL080002
		if (grp.equals("2")) {
			try {
				dftDocument =
					(EDL080002Message) ses.getAttribute("dftDocument");
				dftDocument.setH02USERID(user.getH01USR());
				dftDocument.setH02PROGRM("EDL0800");
				dftDocument.setH02TIMSYS(getTimeStamp());
				dftDocument.setH02SCRCOD("01");
				dftDocument.setH02OPECOD("0005");

				// all the fields here
				java.util.Enumeration enu = dftDocument.fieldEnumeration();
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
				mc.sendMessage(dftDocument);
				dftDocument.destroy();
				flexLog("EDL080002 Message Sent to Sockets");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}
		// sending EDL080030
		if (grp.equals("3")) {
			try {
				dftTrans = (EDL080030Message) ses.getAttribute("dftTrans");
				dftTrans.setH30USERID(user.getH01USR());
				dftTrans.setH30PROGRM("EDL0800");
				dftTrans.setH30TIMSYS(getTimeStamp());
				dftTrans.setH30SCRCOD("01");
				dftTrans.setH30OPECOD("0005");

				// all the fields here
				java.util.Enumeration enu = dftTrans.fieldEnumeration();
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
				mc.sendMessage(dftTrans);
				dftTrans.destroy();
				flexLog("EDL080002 Message Sent to Sockets");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

		if (grp.equals("4")) {
			try {
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(
							new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(
							new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");

				dftDocument =
					(EDL080002Message) mc.getMessageRecord("EDL080002");
				dftDocument.setH02USERID(user.getH01USR());
				dftDocument.setH02PROGRM("EDL0800");
				dftDocument.setH02TIMSYS(getTimeStamp());
				dftDocument.setH02SCRCOD("01");
				dftDocument.setH02OPECOD("0015");
				dftDocument.setE02DLDNLN(req.getParameter("E01DEAACC"));
				dftDocument.setE02DLDDID(req.getParameter("E03NUMIDE"));

				dftDocument.send();
				dftDocument.destroy();
				flexLog("EDL080002 Message Sent");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				if (userPO.getPurpose().equals("MAINTENANCE")
					&& !(userPO.getHeader10().equals("CONF"))) {
					try {
						if (grp.equals("1")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_acceptor_docs.jsp");
							callPage(
								LangPath + "EDL0800_dft_acceptor_docs.jsp",
								req,
								res);
						} else {
							if (grp.equals("4")) {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_acceptor_docs_detail.jsp");
								callPage(
									LangPath
										+ "EDL0800_dft_acceptor_docs_detail.jsp",
									req,
									res);
							} else {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_enter_maint.jsp");
								res.sendRedirect(
									super.srctx
										+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=1&E01DEAACC="
										+ req.getParameter("E01DEAACC"));
								//callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
							}
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					//check for documents for acceptor
				} else {

					try {
						if (grp.equals("1")) {
							if (act.equals("A")) {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_enter_acc_maint.jsp");
								callPage(
									LangPath
										+ "EDL0800_dft_enter_acc_maint.jsp",
									req,
									res);
							} else {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_acceptor_docs.jsp");
								callPage(
									LangPath + "EDL0800_dft_acceptor_docs.jsp",
									req,
									res);
							}
						} else {
							if (grp.equals("4")) {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_acceptor_docs_detail.jsp");
								callPage(
									LangPath
										+ "EDL0800_dft_acceptor_docs_detail.jsp",
									req,
									res);
							} else {
								//emat 03/15/2002
								if (userPO.getHeader10().equals("CONF")) {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0800_dft_new_transac.jsp");
									callPage(
										LangPath
											+ "EDL0800_dft_new_transac.jsp",
										req,
										res);
								} else {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDL0800_dft_basic.jsp");
									res.sendRedirect(
										super.srctx
											+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=1&E01DEAACC="
											+ req.getParameter("E01DEAACC"));
									//callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
								}
							}
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else { // There are errors
				if (userPO.getPurpose().equals("MAINTENANCE")) {
					if (grp.equals("0")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (grp.equals("1")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_acceptor_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_acceptor_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else {
					try {
						if (grp.equals("0")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (grp.equals("1")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_acceptor_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_acceptor_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
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
	protected void procActionMaintColl(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec collList = null;
		int colNum = 5;

		try {
			collList = new datapro.eibs.beans.JBListRec();
			collList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String sel = " ";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			myRow[i] = "";
		}

		for (int row = 0; row < 10; row++) {
			try {
				sel = req.getParameter("RCLACB_" + row).toUpperCase();
				if (sel.equals(""))
					break;
			} catch (Exception e) {
				break;
			}
			myRow[0] = req.getParameter("RCLACB_" + row).toUpperCase();
			myRow[1] = req.getParameter("RCLTYB_" + row).toUpperCase();
			myRow[2] = req.getParameter("RCLCCY_" + row).toUpperCase();
			myRow[3] = req.getParameter("RCLAMT_" + row).toUpperCase();
			try {
				myRow[4] = req.getParameter("RCLF04_" + row).toUpperCase();
			} catch (Exception e) {
			}
			collList.addRow(myFlag, myRow);

		}

		ses.setAttribute("coll", collList);

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Close</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		out.println("		top.opener.document.forms[0].SCREEN.value = 500");
		out.println("		top.opener.document.forms[0].submit();");
		out.println("		top.close();");
		out.println("</SCRIPT>");
		out.println("<P>Close it!!!</P>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintDed(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec dedList = null;

		dedList = (JBListRec) ses.getAttribute("ded");

		dedList.initRow();
		int row = 0;
		String sel = " ";
		while (dedList.getNextRow()) {
			row = dedList.getCurrentRow();
			try {
				sel = req.getParameter("DLISEL_" + row);
				if (sel.equals("")) {
					sel = " ";
				}
			} catch (Exception e) {
				sel = " ";
			}
			dedList.setRecord(sel, 0, row);
			dedList.setRecord(req.getParameter("DLICDE_" + row), 1, row);
			dedList.setRecord(
				req.getParameter("DLINME_" + row).toUpperCase(),
				2,
				row);
			dedList.setRecord(
				req.getParameter("DLIPLZ_" + row).toUpperCase(),
				3,
				row);
			dedList.setRecord(req.getParameter("DLIDPM_" + row), 4, row);
			dedList.setRecord(req.getParameter("DLIFAC_" + row), 5, row);

		}

		ses.setAttribute("ded", dedList);

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Close</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		out.println("		top.opener.document.forms[0].SCREEN.value = 500");
		out.println("		top.opener.document.forms[0].submit();");
		out.println("		top.close();");
		out.println("</SCRIPT>");
		out.println("<P>Close it!!!</P>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintPageToBeans(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		UserPos userPO = null;

		userPO = (UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgLN = (EDL015001Message) ses.getAttribute("lnBasic");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgLN.fieldEnumeration();
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

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("lnBasic", msgLN);

		try {
			flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
			callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintPay(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec pmntList = null;
		int colNum = 7;

		try {
			pmntList = new datapro.eibs.beans.JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String sel = " ";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			myRow[i] = "";
		}

		for (int row = 0; row < 360; row++) {
			try {
				sel = req.getParameter("DLPPNU_" + row).toUpperCase();
				if (sel.equals(""))
					break;
			} catch (Exception e) {
				break;
			}

			myRow[0] = req.getParameter("DLPPNU_" + row);
			myRow[1] = req.getParameter("DLPPD1_" + row);
			myRow[2] = req.getParameter("DLPPD2_" + row);
			myRow[3] = req.getParameter("DLPPD3_" + row);
			myRow[4] = req.getParameter("DLPPRI_" + row);
			myRow[5] = req.getParameter("DLPINT_" + row);
			myRow[6] = req.getParameter("DLPIIP_" + row);

			pmntList.addRow(myFlag, myRow);

		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("pmnt", pmntList);

		int opt;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			opt = 1;
		}

		switch (opt) {
			case 1 :
				{
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.document.forms[0].SCREEN.value = 500");
					out.println(
						"		top.opener.document.forms[0].submit();");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
					break;
				}
			case 2 :
				{
					try {
						pmntList.setLastRow();
						int pymNumLast = 1;
						if (!pmntList.getNoResult()) {
							pymNumLast =
								Integer.parseInt(pmntList.getRecord(0)) + 1;
						}
						int number =
							Integer.parseInt(req.getParameter("NUMBER"));
						int month = Integer.parseInt(req.getParameter("DATE1"));
						int day = Integer.parseInt(req.getParameter("DATE2"));
						int year =
							2000 + Integer.parseInt(req.getParameter("DATE3"));
						int feq =
							Integer.parseInt(req.getParameter("FREQUENCY"));
						char code = req.getParameter("CODE").toCharArray()[0];
						java.util.Calendar myDate = Calendar.getInstance();
						myDate.set(year, month - 1, day);

						for (int row = 0; row < number; row++) {

							// Payment Number
							myRow[0] = pymNumLast + row + "";
							// Dates
							if (row > 0) {
								switch (code) {
									case 'D' :
										{
											myDate.add(Calendar.DATE, feq);
											break;
										}
									case 'M' :
										{
											myDate.add(Calendar.MONTH, feq);
											break;
										}
									case 'Y' :
										{
											myDate.add(Calendar.YEAR, feq);
											break;
										}
								}
							}

							if (user.getE01DTF().equals("MDY")) {
								myRow[1] =
									(myDate.get(Calendar.MONTH) + 1) + "";
								myRow[2] =
									myDate.get(Calendar.DAY_OF_MONTH) + "";
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[3] = y.substring(2);
							} else if (user.getE01DTF().equals("DMY")) {
								myRow[1] =
									myDate.get(Calendar.DAY_OF_MONTH) + "";
								myRow[2] =
									(myDate.get(Calendar.MONTH) + 1) + "";
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[3] = y.substring(2);
							} else {
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[1] = y.substring(2);
								myRow[2] =
									(myDate.get(Calendar.MONTH) + 1) + "";
								myRow[3] =
									myDate.get(Calendar.DAY_OF_MONTH) + "";
							}
							// Amount
							myRow[4] = req.getParameter("AMOUNT");
							// Interest
							myRow[5] = "";
							// Include Interest in Payments
							myRow[6] = req.getParameter("INTEREST");

							pmntList.addRow(myFlag, myRow);

						}

						flexLog("Putting java beans into the session");
						ses.setAttribute("pmnt", pmntList);
					} catch (Exception e) {
					}

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_pay_pln_det.jsp");
						callPage(
							LangPath + "EDL0150_ln_pay_pln_det.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
			case 3 :
				{
					String row = req.getParameter("ROW");
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_pay_pln_det.jsp?ROW="
								+ row);
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "EDL0150_ln_pay_pln_det.jsp?ROW="
								+ row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionNewTran(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080030Message msgGenInf = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		//flexLog("Por aqui");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgGenInf = (EDL080030Message) ses.getAttribute("lnGenTran");

			msgGenInf.setH30USERID(user.getH01USR());
			msgGenInf.setH30PROGRM("EDL0800");
			msgGenInf.setH30TIMSYS(getTimeStamp());
			msgGenInf.setH30SCRCOD("01");
			msgGenInf.setH30OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgGenInf.fieldEnumeration();
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

			//msgGenInf.send();
			mc.sendMessage(msgGenInf);
			msgGenInf.destroy();
			flexLog("EDL080030 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL080030")) {
				try {
					msgGenInf = new datapro.eibs.beans.EDL080030Message();
					flexLog("EDL080030 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGenInf = (EDL080030Message) newmessage;

				userPO.setIdentifier(msgGenInf.getE30DEAACC());
				userPO.setHeader1(msgGenInf.getE30DEAPRO());
				// userPO.setHeader8(msgGenInf.getE30DEARET());
				userPO.setHeader2(msgGenInf.getE30DEACUN());
				userPO.setHeader3(msgGenInf.getE30CUSNA1());
				userPO.setHeader5(msgGenInf.getE30TRNPRI());
				userPO.setHeader6(msgGenInf.getE30DEATRM());
				userPO.setHeader7(msgGenInf.getE30DEATRC());
				userPO.setCurrency(msgGenInf.getE30DEACCY());
				userPO.setBank(msgGenInf.getE30DEABNK());
				userPO.setBranch(msgGenInf.getE30DEABRN());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnGenTran", msgGenInf);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						if (userPO.getPurpose().equals("MAINTENANCE")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_enter_maint.jsp");
								callPage(
									LangPath + "EDL0800_dft_enter_maint.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else if (userPO.getPurpose().equals("NEW")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_confirm.jsp");
								callPage(
									LangPath + "EDL0800_dft_confirm.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_new_transac.jsp");
						callPage(
							LangPath + "EDL0800_dft_new_transac.jsp",
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
	protected void procActionPrintNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
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
			flexLog("header 1");
			msgLN = (EDL015001Message) mc.getMessageRecord("EDL015001");
			flexLog("header 2");
			msgLN.setH01USERID(user.getH01USR());
			flexLog("header 3");
			msgLN.setH01PROGRM("EDL0130");
			flexLog("header 4 timestamp = " + getTimeStamp());
			msgLN.setH01TIMSYS(""); //getTimeStamp()
			flexLog("header 5");
			msgLN.setH01SCRCOD("01");
			flexLog("header 6");
			msgLN.setH01OPECOD("0002");
			msgLN.setE01DEAACD("10");
			flexLog("Header has been sended");
			try {
				msgLN.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgLN.setE01DEAACC("0");
				flexLog(" error " + e);
			}
			flexLog("Send command");
			msgLN.send();
			flexLog("Destroy command");
			msgLN.destroy();
			flexLog("EDL015001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL015001")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015001Message();
					flexLog("EDL015001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015001Message) newmessage;

				userPO.setIdentifier(msgLN.getE01DEAACC());
				userPO.setHeader1(msgLN.getE01DEAPRO());
				userPO.setHeader8(msgLN.getE01DEARET());
				userPO.setHeader2(msgLN.getE01DEACUN());
				userPO.setHeader3(msgLN.getE01CUSNA1());
				userPO.setCurrency(msgLN.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnBasic", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_enter_maint.jsp");
						callPage(
							LangPath + "EDL0150_ln_enter_maint.jsp",
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
	protected void procActionSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message dftBasic = null;
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
			flexLog("Send Initial Data");
			dftBasic = (ESD000002Message) ses.getAttribute("lnCodes");
			//msgLN = (ESD000002Message)mc.getMessageRecord("ESD000002");
			dftBasic.setH02USR(user.getH01USR());
			dftBasic.setH02PGM("EDL0130");
			dftBasic.setH02TIM(getTimeStamp());
			dftBasic.setH02SCR("01");
			dftBasic.setH02OPE("0005");
			dftBasic.setH02WK1("M");
			// all the fields here
			java.util.Enumeration enu = dftBasic.fieldEnumeration();
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

			//msgLN.send();
			mc.sendMessage(dftBasic);
			dftBasic.destroy();
			flexLog("ESD000002 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000002Message();
					flexLog("ESD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000002Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", dftBasic);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_codes.jsp");
						callPage(LangPath + "EDL0800_dft_codes.jsp", req, res);
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
	protected void procActionTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message dftBasic = null;
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
			//msgLN = (ESD000006Message)ses.getAttribute("cdTit");
			dftBasic = (ESD000006Message) mc.getMessageRecord("ESD000006");
			dftBasic.setH06USR(user.getH01USR());
			dftBasic.setH06PGM("EDL0800");
			dftBasic.setH06TIM(""); //getTimeStamp()
			dftBasic.setH06SCR("01");
			dftBasic.setH06OPE("0005");
			dftBasic.setE06ACC(userPO.getIdentifier());
			dftBasic.setE06RTP("H");
			dftBasic.setH06WK1("M");
			// all the fields here
			java.util.Enumeration enu = dftBasic.fieldEnumeration();
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

			dftBasic.send();
			//mc.sendMessage(msgLN);
			dftBasic.destroy();

			flexLog("ESD000006 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000006Message();
					flexLog("ESD000006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000006Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTit", dftBasic);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_tit.jsp");
						callPage(LangPath + "EDL0800_dft_tit.jsp", req, res);
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
	protected void procActionCancelDebit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL015210Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
			
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgLN = (EDL015210Message) ses.getAttribute("lnAutoDebit");
			msgLN.setH10USERID(user.getH01USR());
			msgLN.setH10PROGRM("EDL0152");
			msgLN.setH10TIMSYS(getTimeStamp());
			msgLN.setH10SCRCOD("01");
			msgLN.setH10OPECOD("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgLN.fieldEnumeration();
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
	
			//msgLN.send();
			mc.sendMessage(msgLN);
			msgLN.destroy();
	
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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
						flexLog("About to call Page: " + LangPath + "EDL0800_dft_basic.jsp");
						callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0800_dft_cancel_auto_debit.jsp");
						callPage(LangPath + "EDL0800_dft_cancel_auto_debit.jsp", req, res);
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
	protected boolean procRecMaintData(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		Socket s = null;
		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		EDL080002Message dftDocument = null;
		EDL080003Message dftAcceptor = null;
		EDL080030Message dftTrans = null;

		JBListRec lstAcceptors = new JBListRec();
		JBListRec lstDocuments = new JBListRec();

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			dftBasic = new datapro.eibs.beans.EDL080001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		//message of acceptors
		try {
			dftAcceptor = new datapro.eibs.beans.EDL080003Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		//ses.setAttribute("dftBasic", dftBasic);
		ses.setAttribute("dftAcceptor", dftAcceptor);
		//ses.setAttribute("lstAcceptors", lstAcceptors);
		ses.setAttribute("lstDocuments", lstDocuments);

		int screen = Integer.parseInt(req.getParameter("SCREEN"));

		// Receive Error Message
		try {

			String grp = req.getParameter("GRP");

			if (grp == null)
				grp = "";
			if (!grp.equals("4")) {

				if (screen != 1700) {
					newmessage = mc.receiveMessage();
					flexLog("newmessage: " + newmessage);
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						showERROR(msgError);
					} else
						flexLog(
							"Message "
								+ newmessage.getFormatName()
								+ " received.");
				}
			} else
				IsNotError = true;

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// EDL080001  
		// Receive Data
		try {

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL080001")) {

				dftBasic = (EDL080001Message) newmessage;
				flexLog("EDL080001 Message Received");

				userPO.setIdentifier(dftBasic.getE01DEAACC());
				userPO.setHeader1(dftBasic.getE01DEAPRO());
				userPO.setHeader8(dftBasic.getE01DEARET());
				userPO.setHeader2(dftBasic.getE01DEACUN());
				userPO.setHeader3(dftBasic.getE01CUSNA1());
				userPO.setHeader4(dftBasic.getE01DSCPRO());
				userPO.setHeader23("DFT");
				userPO.setCurrency(dftBasic.getE01DEACCY());
				userPO.setBank(dftBasic.getE01DEABNK());
				userPO.setBranch(dftBasic.getE01DEABRN());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("dftBasic", dftBasic);
				
				if (screen != 400 && screen != 200 && screen != 3 && screen != 1200 && screen != 1600) {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EDL080030")) {
						dftTrans = (EDL080030Message) newmessage;
						flexLog("EDL080030 : " + dftTrans);
						flexLog("EDL080030 Message Received");

						if (screen != 502) {
							userPO.setIdentifier(dftTrans.getE30DEAACC());
							userPO.setHeader1(dftTrans.getE30DEAPRO());
							// userPO.setHeader8(dftTrans.getE30DEARET());
							userPO.setHeader2(dftTrans.getE30DEACUN());
							userPO.setHeader3(dftTrans.getE30CUSNA1());
							userPO.setHeader5(dftTrans.getE30TRNPRI());
							userPO.setHeader6(dftTrans.getE30DEATRM());
							userPO.setHeader7(dftTrans.getE30DEATRC());
							userPO.setCurrency(dftTrans.getE30DEACCY());
							userPO.setBank(dftTrans.getE30DEABNK());
							userPO.setBranch(dftTrans.getE30DEABRN());
						}

						ses.setAttribute("lnGenTran", dftTrans);
					}
					
				}
			}
			if (newmessage.getFormatName().equals("EDL080003")) {

				dftAcceptor = (EDL080003Message) newmessage;
				flexLog("EDL080003 : " + dftAcceptor);
				flexLog("EDL080003 Message Received");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("dftAcceptor", dftAcceptor);

			}
			if (newmessage.getFormatName().equals("EDL080002")) {

				lstAcceptors = new datapro.eibs.beans.JBListRec();
				lstDocuments = new datapro.eibs.beans.JBListRec();

				int colnum = 3;
				int colnumdocs = 22;
				lstAcceptors.init(colnum);
				lstDocuments.init(colnumdocs);
				String myRow[] = new String[colnum];
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnum; i++) {
					myRow[i] = "";
				}
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}

				dftDocument = (EDL080002Message) newmessage;

				boolean endList =
					(dftDocument.getE02ENDFLD().trim().equals("*")
						? true
						: false);
				String myFlag = "";
				String idAcceptor = "";
				String nameAcceptor = "";
				BigDecimal totalAcceptorDocs = new BigDecimal("0");
				if (!endList) {
					idAcceptor = dftDocument.getE02DLDDID();
					nameAcceptor = dftDocument.getE02DLDNME();
					totalAcceptorDocs = dftDocument.getBigDecimalE02DLDOAM();
				}
				boolean firstTime = true;
				int occurs = 0;

				//emat 03/15/2002
				BigDecimal totalDocs = new BigDecimal("0");

				while (!endList) {
					//capture documents data
					//emat 03/15/2002

					totalDocs =
						totalDocs.add(dftDocument.getBigDecimalE02DLDOAM());

					System.out.println("totalDocs = " + totalDocs.toString());
					occurs++;
					myRow2[0] = dftDocument.getE02DLDNDR();
					myRow2[1] = dftDocument.getE02DLDMA1();
					myRow2[2] = dftDocument.getE02DLDMA2();
					myRow2[3] = dftDocument.getE02DLDMA3();
					myRow2[4] = dftDocument.getE02DLDOAM();
					myRow2[5] = dftDocument.getE02DLDARC();
					myRow2[6] = dftDocument.getE02DLDACC();
					myRow2[7] = dftDocument.getE02DLDEXT();
					//id of acceptant
					myRow2[8] = dftDocument.getE02DLDDID();
					//inst cob
					myRow2[9] = dftDocument.getE02DLDCOI();
					myRow2[10] = dftDocument.getE02DLDREW();
					myRow2[11] = dftDocument.getE02SALINT();
					myRow2[12] = dftDocument.getE02SALMOR();
					myRow2[13] = dftDocument.getE02DLDRST();
					myRow2[14] = dftDocument.getE02DLDPY1();
					myRow2[15] = dftDocument.getE02DLDPY2();
					myRow2[16] = dftDocument.getE02DLDPY3();
					myRow2[17] = dftDocument.getE02DLDCOI();
					myRow2[18] = dftDocument.getE02DLDKBK();
					myRow2[19] = dftDocument.getE02DLDKBR();
					myRow2[20] = dftDocument.getE02DLDCTA();
					myRow2[21] = dftDocument.getE02DLDCHQ();
					lstDocuments.addRow(myFlag, myRow2);
					//capture acceptor data
					if (idAcceptor
						.trim()
						.equals(dftDocument.getE02DLDDID().trim())) {
						if (firstTime) {
							totalAcceptorDocs =
								dftDocument.getBigDecimalE02DLDOAM();
							firstTime = false;
						} else {
							totalAcceptorDocs =
								totalAcceptorDocs.add(
									dftDocument.getBigDecimalE02DLDOAM());
						}
					} else {
						myRow[0] = idAcceptor;
						myRow[1] = nameAcceptor;
						myRow[2] = totalAcceptorDocs.toString();
						lstAcceptors.addRow(myFlag, myRow);
						idAcceptor = dftDocument.getE02DLDDID();
						nameAcceptor = dftDocument.getE02DLDNME();
						totalAcceptorDocs =
							dftDocument.getBigDecimalE02DLDOAM();
					}
					newmessage = mc.receiveMessage();
					dftDocument = (EDL080002Message) newmessage;
					endList =
						(dftDocument.getE02ENDFLD().trim().equals("*")
							? true
							: false);
					if (endList) {
						myRow[0] = idAcceptor;
						myRow[1] = nameAcceptor;
						myRow[2] = totalAcceptorDocs.toString();
						lstAcceptors.addRow(myFlag, myRow);
					}
				}

				flexLog("EDL080002 Message Sent");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				String grp2 = req.getParameter("GRP");
				if (grp2 == null)
					grp2 = "";
				if (!grp2.equals("4")) {
					ses.setAttribute("lstAcceptors", lstAcceptors);
				}
				ses.setAttribute("lstDocuments", lstDocuments);

				//emat 03/15/2002
				//check if draft is new WRKFLG01 = 'N'
				//and if total of documents = total of draft , redirect to 
				//transactions
				dftBasic = (EDL080001Message) ses.getAttribute("dftBasic");
				BigDecimal totalDraft = null;
				totalDraft = dftBasic.getBigDecimalE01DEAOAM();

				if (dftBasic.getH01FLGWK1().trim().equals("N")) {
					try {

					} catch (Exception e) {
						System.out.println("error total draft ..");
						totalDraft = new BigDecimal("0");
					}
					if (totalDraft.equals(totalDocs)) {
						userPO.setHeader10("CONF");
						//get transaction 

						dftTrans = new datapro.eibs.beans.EDL080030Message();
						dftTrans.setH30USERID(user.getH01USR());
						dftTrans.setH30PROGRM("EDL0800");
						dftTrans.setH30TIMSYS(getTimeStamp());
						dftTrans.setH30SCRCOD("01");
						dftTrans.setH30OPECOD("0002");
						dftTrans.setE30DEAACC(dftBasic.getE01DEAACC());

						//s = new Socket(super.hostIP, getInitSocket(req) + 1);
						//s.setSoTimeout(super.sckTimeOut);
						//mc =
						//new MessageContext(
						//	new DataInputStream(new BufferedInputStream(s.getInputStream())),
						//	new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						//	"datapro.eibs.beans");
						mc.sendMessage(dftTrans);
						flexLog("EDL080030 Message Sent");
						dftTrans.destroy();
						//receive message
						newmessage = mc.receiveMessage();
						flexLog("Receiving error message: " + newmessage);
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
						} else {
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
						}

						newmessage = mc.receiveMessage();
						flexLog("Receiving EDL080030 message: " + newmessage);
						if (newmessage.getFormatName().equals("EDL080030")) {
							dftTrans = (EDL080030Message) newmessage;

							userPO.setIdentifier(dftTrans.getE30DEAACC());
							userPO.setHeader1(dftTrans.getE30DEAPRO());
							// userPO.setHeader8(dftTrans.getE30DEARET());
							userPO.setHeader2(dftTrans.getE30DEACUN());
							userPO.setHeader3(dftTrans.getE30CUSNA1());
							userPO.setHeader5(dftTrans.getE30TRNPRI());
							userPO.setHeader6(dftTrans.getE30DEATRM());
							userPO.setHeader7(dftTrans.getE30DEATRC());
							userPO.setCurrency(dftTrans.getE30DEACCY());
							userPO.setBank(dftTrans.getE30DEABNK());
							userPO.setBranch(dftTrans.getE30DEABRN());

							ses.setAttribute("lnGenTran", dftTrans);
							ses.setAttribute("userPO", userPO);
						}
					}

				}

				int flg = 0;
				try {
					flg = Integer.parseInt(req.getParameter("REQFLG"));
				} catch (Exception e) {
					flg = 0;
				}

				if (flg == 1) {
					if (!totalDraft.equals(totalDocs)) {
						if (!totalDraft.equals(new BigDecimal("0"))) {
							msgError = new datapro.eibs.beans.ELEERRMessage();
							msgError.setERRNUM("1");
							msgError.setERWF01("Y");
							msgError.setERNU01("xxxx");
							msgError.setERDS01(
								"Total Descuento diferente a Total Documentos");
							ses.setAttribute("error", msgError);
						}
					}
				}

				dftAcceptor.setH03USERID(user.getH01USR());
				dftAcceptor.setH03PROGRM("EDL0800");
				dftAcceptor.setH03TIMSYS(getTimeStamp());
				dftAcceptor.setE03NUMIDE(idAcceptor);
				dftAcceptor.setE03CUMMA1(nameAcceptor);
				ses.setAttribute("dftAcceptor", dftAcceptor);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		return IsNotError;
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
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
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0800_dft_enter_maint.jsp");
			callPage(LangPath + "EDL0800_dft_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterAcceptorMaint(
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
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0800_dft_enter_acc_maint.jsp");
			callPage(LangPath + "EDL0800_dft_enter_acc_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterNew(
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
			userPO.setOption("LN");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDL0150_ln_enter_new.jsp");
			callPage(LangPath + "EDL0150_ln_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message dftBasic = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String purpose = (String) ses.getAttribute("Purpose");

		String opCode = "0002";

		// Send Initial data
		try {
			dftBasic = (ESD000005Message) mc.getMessageRecord("ESD000005");
			dftBasic.setH05USR(user.getH01USR());
			dftBasic.setH05PGM("EDL0800");
			dftBasic.setH05TIM(""); //getTimeStamp()
			dftBasic.setH05SCR("01");
			dftBasic.setH05OPE(opCode);
			dftBasic.setE05ACC(userPO.getIdentifier());
			dftBasic.setE05TYP("2");
			dftBasic.setH05WK1("M");
			dftBasic.send();
			dftBasic.destroy();
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

			if (newmessage.getFormatName().equals("ESD000005")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000005Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnInst", dftBasic);

				if (IsNotError) { // There are no errors 
					if (purpose.equals("READONLY")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_inq_special_inst.jsp");
							callPage(
								LangPath + "EDL0800_dft_inq_special_inst.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (purpose.equals("APPROVAL")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_ap_special_inst.jsp");
							callPage(
								LangPath + "EDL0800_dft_ap_special_inst.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_special_inst.jsp");
							callPage(
								LangPath + "EDL0800_dft_special_inst.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						if (purpose.equals("READONLY")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_inq_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_inq_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015206Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgLN = (EDL015206Message) mc.getMessageRecord("EDL015206");
			msgLN.setH06USERID(user.getH01USR());
			msgLN.setH06PROGRM("EDL0150");
			msgLN.setH06TIMSYS(""); //getTimeStamp()
			msgLN.setH06SCRCOD("01");
			msgLN.setH06OPECOD(opCode);
			msgLN.setE06DEAACC(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDL015206")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015206Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015206Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnRate", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_xchg_rate.jsp");
						callPage(
							LangPath + "EDL0150_ln_xchg_rate.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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
	protected void procReqIntPrep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL015009Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0002";

		// Send Initial data
		try {
			msgLN = (EDL015009Message) mc.getMessageRecord("EDL015009");
			msgLN.setH09USERID(user.getH01USR());
			msgLN.setH09PROGRM("EDL0150");
			msgLN.setH09TIMSYS(""); //getTimeStamp()
			msgLN.setH09SCRCOD("01");
			msgLN.setH09OPECOD(opCode);
			msgLN.setE09DEAACC(userPO.getIdentifier());
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
			if (newmessage.getFormatName().equals("EDL015009")) {
				try {
					msgLN = new datapro.eibs.beans.EDL015009Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgLN = (EDL015009Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnIntPrep", msgLN);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_prep_int.jsp");
						callPage(
							LangPath + "EDL0130_cd_prep_int.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_finish.jsp");
						callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
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
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftBasic = (EDL080001Message) mc.getMessageRecord("EDL080001");
			dftBasic.setH01USERID(user.getH01USR());
			dftBasic.setH01PROGRM("EDL0800");
			dftBasic.setH01TIMSYS(getTimeStamp());
			dftBasic.setH01SCRCOD("01");
			dftBasic.setH01OPECOD("0002");
			dftBasic.setE01DEAACD("10");
			dftBasic.setH01FLGWK1("A");
			try {
				dftBasic.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				dftBasic.setE01DEAACC("0");
			}

			dftBasic.send();
			dftBasic.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_basic.jsp");
					callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_enter_maint.jsp");
					callPage(
						LangPath + "EDL0800_dft_enter_maint.jsp",
						req,
						res);
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
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		EDL015002Message msgDed = null;
		EDL015003Message msgPmnt = null;
		EDL015004Message msgColl = null;
		JBListRec dedList = null;
		JBListRec collList = null;
		JBListRec pmntList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLN = (EDL015001Message) mc.getMessageRecord("EDL015001");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL0150");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
			msgLN.setH01OPECOD("0002");
			msgLN.setE01DEAACD("10");
			try {
				msgLN.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgLN.setE01DEAACC("0");
			}

			msgLN.send();
			msgLN.destroy();
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
		try {
			msgLN = new datapro.eibs.beans.EDL015001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 6;
			dedList = new datapro.eibs.beans.JBListRec();
			dedList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 5;
			pmntList = new datapro.eibs.beans.JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 5;
			collList = new datapro.eibs.beans.JBListRec();
			collList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnBasic", msgLN);
		ses.setAttribute("ded", dedList);
		ses.setAttribute("pmnt", pmntList);
		ses.setAttribute("coll", collList);

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
			while (true) {

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL015001")) {

					msgLN = (EDL015001Message) newmessage;

					userPO.setIdentifier(msgLN.getE01DEAACC());
					userPO.setHeader1(msgLN.getE01DEAPRO());
					userPO.setHeader8(msgLN.getE01DEARET());
					userPO.setHeader2(msgLN.getE01DEACUN());
					userPO.setHeader3(msgLN.getE01CUSNA1());
					userPO.setCurrency(msgLN.getE01DEACCY());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("lnMant", msgLN);

				} else if (newmessage.getFormatName().equals("EDL015009")) {

					if (IsNotError) { // There are no errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0150_ln_mant.jsp");
							callPage(
								LangPath + "EDL0150_ln_mant.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0150_ln_enter_maint.jsp");
							callPage(
								LangPath + "EDL0150_ln_enter_maint.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

					break;
				} else if (newmessage.getFormatName().equals("EDL015002")) {
					//Deductions List
					// Fill List bean

					int colNum = 6;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgDed = (EDL015002Message) newmessage;

						marker = msgDed.getH02FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							//Quote List
							myRow[0] = Util.formatCell(msgDed.getE02DLISEL());
							// Select
							myRow[1] = Util.formatCell(msgDed.getE02DLICDE());
							// Code
							myRow[2] = Util.formatCell(msgDed.getE02DLINME());
							// Company Name
							myRow[3] = Util.formatCell(msgDed.getE02DLIPLZ());
							// Police
							myRow[4] = Util.formatCCY(msgDed.getE02DLIDPM());
							// Deductions
							myRow[5] = Util.formatCell(msgDed.getE02DLIFAC());
							// Factor

							dedList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("ded", dedList);
				} else if (newmessage.getFormatName().equals("EDL015003")) {
					//Deductions List
					// Fill List bean

					int colNum = 5;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgPmnt = (EDL015003Message) newmessage;

						marker = msgPmnt.getH03FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							//Quote List
							myRow[0] = Util.formatCell(msgPmnt.getE03DLPPNU());
							// Quote Number
							myRow[1] =
								Util.formatDate(
									msgPmnt.getE03DLPPD1(),
									msgPmnt.getE03DLPPD2(),
									msgPmnt.getE03DLPPD3());
							// Date
							myRow[2] = Util.formatCCY(msgPmnt.getE03DLPPRI());
							// Principal
							myRow[3] = Util.formatCell(msgPmnt.getE03DLPINT());
							// Interest
							myRow[4] = Util.formatCell(msgPmnt.getE03DLPIIP());
							// Interest Included in Payments

							pmntList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("pmnt", pmntList);
				} else if (newmessage.getFormatName().equals("EDL015004")) {
					//Deductions List
					// Fill List bean

					int colNum = 4;

					char sel = ' ';
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgColl = (EDL015004Message) newmessage;

						marker = msgColl.getH04FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {
							//Quote List
							myRow[0] = Util.formatCell(msgColl.getE04RCLACB());
							// Collateral Number
							myRow[1] = Util.formatCell(msgColl.getE04RCLTYB());
							// Type
							myRow[2] = Util.formatCCY(msgColl.getE04RCLCCY());
							// Currency
							myRow[3] = Util.formatCell(msgColl.getE04RCLAMT());
							// Amount

							collList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}

					ses.setAttribute("coll", collList);
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

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
	protected void procReqNewTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL080030Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String purpose = (String) ses.getAttribute("Purpose");

		// Send Initial data
		try {
			msgLN = (EDL080030Message) mc.getMessageRecord("EDL080030");
			msgLN.setH30USERID(user.getH01USR());
			msgLN.setH30PROGRM("EDL0800");
			msgLN.setH30TIMSYS(getTimeStamp());
			msgLN.setH30SCRCOD("01");
			msgLN.setH30OPECOD("0002");

			// all the fields here
			msgLN.setE30DEAACC(userPO.getIdentifier());

			mc.sendMessage(msgLN);
			msgLN.destroy();
			flexLog("EDL080030 Message Sent to Sockets");

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
			if (newmessage.getFormatName().equals("EDL080030")) {
				msgLN = (EDL080030Message) newmessage;
				flexLog("EDL080030 : " + msgLN);
				flexLog("EDL080030 Message Received");

				userPO.setIdentifier(msgLN.getE30DEAACC());
				userPO.setHeader1(msgLN.getE30DEAPRO());
				// userPO.setHeader8(msgLN.getE30DEARET());
				userPO.setHeader2(msgLN.getE30DEACUN());
				userPO.setHeader3(msgLN.getE30CUSNA1());
				userPO.setHeader5(msgLN.getE30TRNPRI());
				userPO.setHeader6(msgLN.getE30DEATRM());
				userPO.setHeader7(msgLN.getE30DEATRC());
				userPO.setCurrency(msgLN.getE30DEACCY());
				userPO.setBank(msgLN.getE30DEABNK());
				userPO.setBranch(msgLN.getE30DEABRN());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnGenTran", msgLN);
				if (IsNotError) { // There are no errors
					if (purpose.equals("READONLY")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_inq_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_inq_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (purpose.equals("APPROVAL")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_ap_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_ap_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					if (purpose.equals("READONLY")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_inq_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_inq_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (purpose.equals("APPROVAL")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_ap_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_ap_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_basic.jsp");
							callPage(
								LangPath + "EDL0800_dft_basic.jsp",
								req,
								res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message dftBasic = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String purpose = (String) ses.getAttribute("Purpose");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			dftBasic = (ESD000002Message) mc.getMessageRecord("ESD000002");
			dftBasic.setH02USR(user.getH01USR());
			dftBasic.setH02PGM("EDL0800");
			dftBasic.setH02TIM(""); //getTimeStamp()
			dftBasic.setH02SCR("01");
			dftBasic.setH02OPE(opCode);
			dftBasic.setE02ACC(userPO.getIdentifier());
			dftBasic.setH02WK1("M");
			dftBasic.send();
			dftBasic.destroy();
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
				//MOD : EMAT 9/5/2001
				// to display the page of special codes ...
				IsNotError = true;
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

			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000002Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", dftBasic);

				if (IsNotError) { // There are no errors
					if (purpose.equals("READONLY")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_inq_codes.jsp");
							callPage(
								LangPath + "EDL0800_dft_inq_codes.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (purpose.equals("APPROVAL")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_ap_codes.jsp");
							callPage(
								LangPath + "EDL0800_dft_ap_codes.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_codes.jsp");
							callPage(
								LangPath + "EDL0800_dft_codes.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						if (purpose.equals("READONLY")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_inq_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_inq_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000006Message dftBasic = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String purpose = (String) ses.getAttribute("Purpose");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			dftBasic = (ESD000006Message) mc.getMessageRecord("ESD000006");
			dftBasic.setH06USR(user.getH01USR());
			dftBasic.setH06PGM("EDL0130");
			dftBasic.setH06TIM(""); //getTimeStamp()
			dftBasic.setH06SCR("01");
			dftBasic.setH06OPE(opCode);
			dftBasic.setE06ACC(userPO.getIdentifier());
			dftBasic.setE06RTP("H");
			dftBasic.setH06WK1("M");

			dftBasic.send();
			dftBasic.destroy();
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

			if (newmessage.getFormatName().equals("ESD000006")) {
				try {
					dftBasic = new datapro.eibs.beans.ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (ESD000006Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTit", dftBasic);

				if (IsNotError) { // There are no errors
					if (purpose.equals("READONLY")) {
						try {
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDL0800_dft_inq_tit.jsp");
							callPage(
								LangPath + "EDL0800_dft_inq_tit.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (purpose.equals("APPROVAL")) {
						try {
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDL0800_dft_ap_tit.jsp");
							callPage(
								LangPath + "EDL0800_dft_ap_tit.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDL0800_dft_tit.jsp");
							callPage(
								LangPath + "EDL0800_dft_tit.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0800_dft_new_transac.jsp");
							callPage(
								LangPath + "EDL0800_dft_new_transac.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						if (purpose.equals("READONLY")) {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_inq_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_inq_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else {
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDL0800_dft_basic.jsp");
								callPage(
									LangPath + "EDL0800_dft_basic.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInqBalances(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
		EDL016002Message msgCD2 = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		  	} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
	  	}
	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	
		String opCode = null;
		opCode = "0004";
		
		// Send Initial data for EDL016002
		try
		{
			msgCD2 = (EDL016002Message)mc.getMessageRecord("EDL016002");
		 	msgCD2.setH02USERID(user.getH01USR());
		 	msgCD2.setH02PROGRM("EDL0160");
		 	msgCD2.setH02TIMSYS(getTimeStamp());
		 	msgCD2.setH02SCRCOD("01");
		 	msgCD2.setH02OPECOD(opCode);
		 	msgCD2.setE02DEAACC(userPO.getIdentifier());
		 	msgCD2.setE02DEAACD("10");
			
			msgCD2.send();	
		 	msgCD2.destroy();
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
		  	throw new RuntimeException("Socket Communication Error");
		}
			
		//Receive Error Message
		try
		{
		  	newmessage = mc.receiveMessage();
		  
		  	if (newmessage.getFormatName().equals("ELEERR")) {
				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}
	
				msgError = (ELEERRMessage)newmessage;
				showERROR(msgError);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
	
		  	}	
	   		else if (newmessage.getFormatName().equals("EDL016002")) {
				try {
					msgCD2 = new datapro.eibs.beans.EDL016002Message();
			  	} catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}
	
				msgCD2 = (EDL016002Message)newmessage; 
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("inqBasic", msgCD2);
	
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
	
		// Send Initial data for EDL016001
		try
		{
			msgCD = (EDL016001Message)mc.getMessageRecord("EDL016001");
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
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
		  	throw new RuntimeException("Socket Communication Error");
		}
	
		//Receive Error Message
		try
		{
		  	newmessage = mc.receiveMessage();
		  
		  	if (newmessage.getFormatName().equals("ELEERR")) {
				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}
	
				msgError = (ELEERRMessage)newmessage;
				showERROR(msgError);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
	
				try {
					flexLog("About to call Page: " + LangPath + "EDL0800_dft_enter_inq.jsp");
					callPage(LangPath + "EDL0800_dft_enter_inq.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
		  	}	
	   		else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new datapro.eibs.beans.EDL016001Message();
					flexLog("EDL016001 Message Received");
			  	} catch (Exception ex) {
					flexLog("Error: " + ex); 
			  	}
	
				msgCD = (EDL016001Message)newmessage; 
	
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("inqLoans", msgCD);
				ses.setAttribute("error", msgError);
	
				try {
					flexLog("About to call Page: " + LangPath + "EDL0800_dft_inq_balances.jsp");
					callPage(LangPath + "EDL0800_dft_inq_balances.jsp", req, res);	
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
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
	
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqCancelDebit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL015210Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		String opCode = "0002";
	
		// Send Initial data
		try {
			msgLN = (EDL015210Message) mc.getMessageRecord("EDL015210");
			msgLN.setH10USERID(user.getH01USR());
			msgLN.setH10PROGRM("EDL0150");
			msgLN.setH10TIMSYS(""); //getTimeStamp()
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
				//showERROR(msgError);
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
						flexLog("About to call Page: " + LangPath + "EDL0800_dft_cancel_auto_debit.jsp");
						callPage(LangPath + "EDL0800_dft_cancel_auto_debit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0800_dft_basic.jsp");
						callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
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

			int screen = R_ENTER_MAINT;

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

					flexLog("Screen Number: " + screen);

					switch (screen) {
						// BEGIN CD
						// Request
						case R_MAINTENANCE :
							procReqMaint(mc, msgUser, req, res, session);
							break;
						case R_SPECIAL_INST :
							session.setAttribute("Purpose", "");
							procReqEspInst(mc, msgUser, req, res, session);
							break;
						case R_INQ_SPECIAL_INST :
							session.setAttribute("Purpose", "READONLY");
							procReqEspInst(mc, msgUser, req, res, session);
							break;
						case R_AP_SPECIAL_INST :
							session.setAttribute("Purpose", "APPROVAL");
							procReqEspInst(mc, msgUser, req, res, session);
							break;
						case R_TITULARES :
							session.setAttribute("Purpose", "");
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_INQ_TITULARES :
							session.setAttribute("Purpose", "READONLY");
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_AP_TITULARES :
							session.setAttribute("Purpose", "APPROVAL");
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_INQ_NEW_TRAN :
							session.setAttribute("Purpose", "READONLY");
							procReqNewTransaction(mc, msgUser, req, res, session);
							break;
						case R_AP_NEW_TRAN :
							session.setAttribute("Purpose", "APPROVAL");
							procReqNewTransaction(mc, msgUser, req, res, session);
							break;
						case R_EXCHANGE :
							procReqExchangeRate(mc, msgUser, req, res, session);
							break;
						case R_CODES :
							session.setAttribute("Purpose", "");
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_INQ_CODES :
							session.setAttribute("Purpose", "READONLY");
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_AP_CODES :
							session.setAttribute("Purpose", "APPROVAL");
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_INT_PREP :
							procReqIntPrep(mc, msgUser, req, res, session);
							break;
						case R_OTHERS_TRANS :
							session.setAttribute("Purpose", "");
							procReqOthersTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_INQ_OTHERS_TRANS :
							session.setAttribute("Purpose", "READONLY");
							procReqOthersTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_INQ_BALANCES :
							procReqInqBalances(mc, msgUser, req, res, session);
							break;
							// Action
						case A_SPECIAL_INST :
							procActionEspInst(mc, msgUser, req, res, session);
							break;
						case A_MAINT_TO_BEAN :
							procReqEnterAcceptorMaint(
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINT_TO_SCK :
							procActionMaintBeansToSck(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINT_DED :
							procActionMaintDed(req, res, session);
							break;
						case A_MAINT_COLL :
							procActionMaintColl(req, res, session);
							break;
						case A_MAINT_PMNT :
							procActionMaintPay(msgUser, req, res, session);
							break;
						case A_TITULARES :
							procActionTit(mc, msgUser, req, res, session);
							break;
						case A_EXCHANGE :
							procActionExchangeRate(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_CODES :
							procActionSpecialCodes(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_PRINT_NEW :
							procActionPrintNew(mc, msgUser, req, res, session);
							break;
						case A_NEW_TRAN :
							procActionNewTran(mc, msgUser, req, res, session);
							break;
						case A_ENTER_ACC_MAINT :
							procActionEnterNewAcc(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_DOCUMENT :
							procActionEnterDocument(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							// END LN
							// BEGIN Entering
							// Request
						case R_ENTER_NEW :
							procReqEnterNew(msgUser, req, res, session);
							break;
						case R_INQ_APPROVAL :
							procReqInqAppDraft(mc, msgUser, req, res, session);
							break;
						case R_INQUIRY :
							procReqInquiryDraft(mc, msgUser, req, res, session);
							break;
						case A_INQUIRY :
							procActionInquiryDraft(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER_NEW :
							procActionEnterNew(mc, msgUser, req, res, session);
							break;
						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_GEN_DOCS :
							procActionGenDocs(mc, msgUser, req, res, session);
							break;
						case R_ENTER_DOC_DET :
							procReqDocDet(mc, msgUser, req, res, session);
							break;
						case R_CANCEL_DEBIT :
							procReqCancelDebit(mc, msgUser, req, res, session);
							break;	
						case A_CANCEL_DEBIT :
							procActionCancelDebit(mc, msgUser, req, res, session);
							break;
							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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
	protected void procReqOthersTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;
		boolean IsNotError = false;
		DataTransaction transData = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String purpose = (String) ses.getAttribute("Purpose");

		userPO.setOption("DFT");

		try {

			transData = new datapro.eibs.beans.DataTransaction();

			transData.setWrkFile("4");
			transData.setBank(userPO.getBank());
			transData.setBranch(userPO.getBranch());
			transData.setAccNum(userPO.getIdentifier());

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);
			ses.setAttribute("TARGET", "EGL0035");

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1&PURPOSE="
					+ purpose);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintAcc(
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
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0800_dft_enter_maint.jsp");
			callPage(LangPath + "EDL0800_dft_acceptor_basic.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterNewAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080003Message dftAcceptor = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		boolean newAcceptor = false;
		try {
			dftAcceptor = (EDL080003Message) mc.getMessageRecord("EDL080003");
			dftAcceptor.setH03USERID(user.getH01USR());
			dftAcceptor.setH03PROGRM("EDL0800");
			dftAcceptor.setH03TIMSYS(getTimeStamp());
			dftAcceptor.setH03SCRCOD("01");

			if (req.getParameter("ACTION") != null) {
				//inside drafts maintenance
				newAcceptor = true;
			} else {
				//inside acceptant maintenance
				newAcceptor = false;
			}
			dftAcceptor.setH03OPECOD("0002");
			if (req.getParameter("E01DEAACC") == null) {
				dftAcceptor.setE03NUMIDE("");

			} else {
				dftAcceptor.setE03NUMIDE(req.getParameter("E01DEAACC"));
			}
			dftAcceptor.send();
			dftAcceptor.destroy();
			flexLog("EDL080003 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_acceptor_basic.jsp");
					callPage(
						LangPath + "EDL0800_dft_acceptor_basic.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors

				try {
					if (newAcceptor) {
						msgError = new datapro.eibs.beans.ELEERRMessage();
						ses.setAttribute("error", msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_acceptor_basic.jsp");
						callPage(
							LangPath + "EDL0800_dft_acceptor_basic.jsp",
							req,
							res);
					} else {
						msgError = new datapro.eibs.beans.ELEERRMessage();
						//ses.setAttribute("dftAcceptor", dftAcceptor);
						ses.setAttribute("error", msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_enter_acc_maint.jsp");
						callPage(
							LangPath + "EDL0800_dft_enter_acc_maint.jsp",
							req,
							res);
					}
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

	protected void procActionEnterDocument(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080002Message dftDocument = null;
		JBListRec lstDocuments = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftDocument = (EDL080002Message) mc.getMessageRecord("EDL080002");
			dftDocument.setH02USERID(user.getH01USR());
			dftDocument.setH02PROGRM("EDL0800");
			dftDocument.setH02TIMSYS(getTimeStamp());
			dftDocument.setH02SCRCOD("01");
			dftDocument.setH02OPECOD("0015");
			try {
				dftDocument.setE02DLDNLN(req.getParameter("E01DEAACC"));
				dftDocument.setE02DLDDID(req.getParameter("IDEACC"));
			} catch (Exception e) {
			}
			flexLog("EDL080002 :" + dftDocument);
			dftDocument.send();
			dftDocument.destroy();
			flexLog("EDL080002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0800_dft_enter_maint.jsp",
						req,
						res);
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

	protected void procActionGenDocs(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080002Message dftDocument = null;
		JBListRec lstDocuments = null;
		JBListRec lstDocuments2 = null;

		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//lstDocuments2 = (datapro.eibs.beans.JBListRec) ses.getAttribute("lstDocuments");
		
		try {
			lstDocuments = new datapro.eibs.beans.JBListRec();
			ses.setAttribute("lstDocuments", lstDocuments);
			int docIni = 0;
			int docFin = 0;
			int docFreq = 0;
			int docMatDateMonth = 0;
			int docMatDateDay = 0;
			int docMatDateYear = 0;
			String docAmount = "";
			String docRate = "";
			String docAutDebitAcc = "";
			String docChgTyp = "";
			String docFreqTyp = "";
			String docInstCob = "";
			String action = "";
			String autgen = "N";
			try {
				action = req.getParameter("ACTION");
				if (action == null)
					action = "";
				autgen = req.getParameter("AUTGEN");
				if (autgen == null)
					autgen = "N";
			} catch (Exception ex) {
				action = "";
				autgen = "N";
			}
			if (action.equals("")) {
				if (autgen.trim().equals("Y")) {
					try {
						docIni =
							Integer.parseInt(req.getParameter("DFTINIDOC"));
					} catch (Exception e) {
						docIni = 0;
					}
					try {
						docFin =
							Integer.parseInt(req.getParameter("DFTFINDOC"));
					} catch (Exception e) {
						docFin = 0;
					}
					try {
						docFreq =
							Integer.parseInt(req.getParameter("DFTFRECUENCY"));
					} catch (Exception e) {
						docFreq = 0;
					}
					//type of frecuency : D - days , M - Months , Y - Years
					try {
						docFreqTyp = req.getParameter("INDFREC");
					} catch (Exception e) {
						docFreqTyp = " ";
					}

					if (user.getE01DTF().equals("MDY")) {
						docMatDateMonth =
							Integer.parseInt(req.getParameter("DFTDTMAT1"));
						docMatDateDay =
							Integer.parseInt(req.getParameter("DFTDTMAT2"));
						docMatDateYear =
							Integer.parseInt(req.getParameter("DFTDTMAT3"));
					}
					if (user.getE01DTF().equals("DMY")) {
						docMatDateDay =
							Integer.parseInt(req.getParameter("DFTDTMAT1"));
						docMatDateMonth =
							Integer.parseInt(req.getParameter("DFTDTMAT2"));
						docMatDateYear =
							Integer.parseInt(req.getParameter("DFTDTMAT3"));
					}
					if (user.getE01DTF().equals("YMD")) {
						docMatDateYear =
							Integer.parseInt(req.getParameter("DFTDTMAT1"));
						docMatDateMonth =
							Integer.parseInt(req.getParameter("DFTDTMAT2"));
						docMatDateDay =
							Integer.parseInt(req.getParameter("DFTDTMAT3"));
					}
					//check century
					if (docMatDateYear > 50) {
						docMatDateYear += 1900;
					} else {
						docMatDateYear += 2000;
					}
					try {
						docAmount = req.getParameter("DFTAMOUNT");
					} catch (Exception ex) {
						docAmount = "";
					}
					try {
						docRate = req.getParameter("DFTRATE");
					} catch (Exception ex) {
						docRate = "";
					}
					try {
						docAutDebitAcc = req.getParameter("DFTAUTDEBACCT");
					} catch (Exception ex) {
						docAutDebitAcc = "";
					}
					try {
						docChgTyp = req.getParameter("DFTCHGTYP");
					} catch (Exception ex) {
						docChgTyp = "";
					}
					int colnumdocs = 22;
					lstDocuments.init(colnumdocs);
					String myRow2[] = new String[colnumdocs];
					for (int i = 0; i < colnumdocs; i++) {
						myRow2[i] = "";
					}
					String myFlag = "";
					boolean endDocs = false;
					int seq = docIni;
					Calendar dateini = Calendar.getInstance();
					Calendar datetmp = Calendar.getInstance();
					dateini.set(
						docMatDateYear,
						--docMatDateMonth,
						docMatDateDay);
					datetmp = dateini;
					while (!endDocs) {
						myRow2[0] = String.valueOf(seq++);
						if (user.getE01DTF().equals("MDY")) {
							myRow2[1] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.DATE));
							myRow2[3] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
						}
						if (user.getE01DTF().equals("DMY")) {
							myRow2[1] =
								String.valueOf(datetmp.get(Calendar.DATE));
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[3] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
						}
						if (user.getE01DTF().equals("YMD")) {
							myRow2[1] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[3] =
								String.valueOf(datetmp.get(Calendar.DATE));
						}
						myRow2[4] = docAmount;
						myRow2[5] = docRate;
						myRow2[6] = docAutDebitAcc;
						myRow2[7] = docChgTyp;
						// add frecuency of documents
						if (docFreqTyp.equals("D"))
							dateini.add(Calendar.DATE, docFreq);
						if (docFreqTyp.equals("M"))
							dateini.add(Calendar.MONTH, docFreq);
						if (docFreqTyp.equals("Y"))
							dateini.add(Calendar.YEAR, docFreq);

						datetmp = dateini;
						// repeat while date is holiday
						// no more than 5 times
						int numTimes = 0;

						/*while (!procValidDate(mc,
							user,
							req,
							res,
							ses,
							String.valueOf(datetmp.get(Calendar.MONTH) + 1),
							String.valueOf(datetmp.get(Calendar.DATE)),
							String
								.valueOf(datetmp.get(Calendar.YEAR))
								.trim()
								.substring(
								2))) {
							//add 1 day to date
							datetmp.add(Calendar.DATE, 1);

							numTimes++;
							if (numTimes > 5) {
								//generate error
								IsNotError = false;
								break;
							}
						}*/
						lstDocuments.addRow(myFlag, myRow2);
						if (seq == docFin + 1)
							endDocs = true;
					}
					ses.setAttribute("lstDocuments", lstDocuments);
					if (IsNotError) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_acceptor_docs_detail.jsp");
						callPage(
							LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0800_dft_acceptor_docs.jsp");
						callPage(
							LangPath + "EDL0800_dft_acceptor_docs.jsp",
							req,
							res);
					}
				} else {
					int colnumdocs = 22;
					lstDocuments.init(colnumdocs);
					String myRow2[] = new String[colnumdocs];
					String myFlag = "";
					myRow2[0] = "1";
					myRow2[1] = " ";
					myRow2[2] = " ";
					myRow2[3] = " ";
					myRow2[4] = " ";
					myRow2[5] = " ";
					myRow2[6] = " ";
					myRow2[7] = " ";
					myRow2[9] = " ";
					myRow2[10] = " ";
					myRow2[11] = " ";
					myRow2[12] = " ";
					myRow2[13] = " ";
					myRow2[14] = " ";
					myRow2[15] = " ";
					myRow2[16] = " ";
					myRow2[17] = " ";
					myRow2[18] = " ";
					myRow2[19] = " ";
					myRow2[20] = " ";
					myRow2[21] = " ";

					lstDocuments.addRow(myFlag, myRow2);
					ses.setAttribute("lstDocuments", lstDocuments);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
						req,
						res);
				}
			} else {
				//ses.setAttribute("lstDocuments", lstDocuments2);
				
				procMaintDocs(mc, user, req, res, ses);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected boolean procValidDate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		String month,
		String day,
		String year) {
		boolean retVal = true;
		Socket s = null;
		MessageRecord newmessage = null;

		ESD000015Message msgValDate = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgValDate = new datapro.eibs.beans.ESD000015Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			//s = new Socket(super.hostIP, getInitSocket(req) + 1);
			//s.setSoTimeout(super.sckTimeOut);
			//mc =
			//	new MessageContext(
			//		new DataInputStream(new BufferedInputStream(s.getInputStream())),
			//		new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
			//		"datapro.eibs.beans");

			msgValDate = (ESD000015Message) mc.getMessageRecord("ESD000015");
			msgValDate.setH15USERID(user.getH01USR());
			msgValDate.setH15PROGRM("ESD0000");
			msgValDate.setH15TIMSYS(getTimeStamp());
			msgValDate.setH15SCRCOD("01");
			msgValDate.setH15OPECOD("0001");

			if (user.getE01DTF().equals("MDY")) {
				msgValDate.setE15HOLDT1(month);
				msgValDate.setE15HOLDT2(day);
				msgValDate.setE15HOLDT3(year);
			}
			if (user.getE01DTF().equals("DMY")) {
				msgValDate.setE15HOLDT1(day);
				msgValDate.setE15HOLDT2(month);
				msgValDate.setE15HOLDT3(year);
			}
			if (user.getE01DTF().equals("YMD")) {
				msgValDate.setE15HOLDT1(year);
				msgValDate.setE15HOLDT2(month);
				msgValDate.setE15HOLDT3(day);
			}
			msgValDate.send();
			msgValDate.destroy();
			flexLog("ESD000015 Message Sent");

			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				msgError.setERRNUM("0");
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
				msgValDate = (ESD000015Message) newmessage;
				if (msgValDate.getE15HOLFLG().equals("Y")) {
					retVal = false;
				} else {
					retVal = true;
				}
			}

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		return retVal;
	}

	protected void procMaintDocs(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		String action = "";
		int numdocini = 0;
		int numdocs = 0;
		int seq = 0;
		JBListRec lstDocuments = null;
		JBListRec lstDocuments2 = null;
		EDL080002Message dftDocument = null;
		ELEERRMessage msgError = null;
		Socket s = null;
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		boolean ItHasNoError = true;
		UserPos userPO = null;

		try {
			/*s = new Socket(super.hostIP, getInitSocket(req) + 1);
			s.setSoTimeout(super.sckTimeOut);
			mc =
				new MessageContext(
					new DataInputStream(new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
					"datapro.eibs.beans");*/

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			lstDocuments = new datapro.eibs.beans.JBListRec();
			lstDocuments2 = (datapro.eibs.beans.JBListRec) ses.getAttribute("lstDocuments");
			action = req.getParameter("ACTION");
			if (action.equals("D") || action.equals("A")) {
				//delete documents
				try
				{
					numdocini = Integer.parseInt(req.getParameter("NUMINI"));
				}
				catch (Exception e) {
					numdocini = 0;
										
				}
				
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));
				flexLog("docs = " + numdocs);
				int colnumdocs = 22;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";
				seq = numdocini;
				String checked = "";
				String numActualSeq = "";
				String numSeq = "";
				for (int i = 0; i < numdocs; i++) {
					numActualSeq = req.getParameter("E02DLDNDR" + i);
					numSeq = req.getParameter("CURSEQ" + i);
					checked = req.getParameter("NUMSEQ" + i);

					if (action.equals("D")) {
						
					//	if (!numActualSeq.trim().equals(numSeq.trim())) {
					if (checked == null	&& numActualSeq.trim().equals(numSeq.trim())){
					
							myRow2[0] = req.getParameter("E02DLDNDR" + i);
							myRow2[1] = req.getParameter("E02DLDMA1" + i);
							myRow2[2] = req.getParameter("E02DLDMA2" + i);
							myRow2[3] = req.getParameter("E02DLDMA3" + i);
							myRow2[4] = req.getParameter("E02DLDOAM" + i);
							myRow2[5] = req.getParameter("E02DLDARC" + i);
							myRow2[6] = req.getParameter("E02DLDACC" + i);
							myRow2[7] = req.getParameter("E02DLDEXT" + i);
							myRow2[9] = req.getParameter("E02DLDCOI" + i);
							myRow2[10] = req.getParameter("E02DLDREW" + i);
							myRow2[11] = req.getParameter("E02SALINT" + i);
							myRow2[12] = req.getParameter("E02SALMOR" + i);
							myRow2[13] = req.getParameter("E02DLDRST" + i);
							myRow2[14] = req.getParameter("E02DLDPY1" + i);
							myRow2[15] = req.getParameter("E02DLDPY2" + i);
							myRow2[16] = req.getParameter("E02DLDPY3" + i);
						
							// checks???
							
							if (userPO.getHeader8().equals("4"))
							
							{
								myRow2[17] = req.getParameter("E02DLDCOI" + i);
								myRow2[18] = req.getParameter("E02DLDKBK" + i);
								myRow2[19] = req.getParameter("E02DLDKBR" + i);
								myRow2[20] = req.getParameter("E02DLDCTA" + i);
								myRow2[21] = req.getParameter("E02DLDCHQ" + i);
							}
							
							
							
							
							
							lstDocuments.addRow(myFlag, myRow2);
							seq++;
						} else {
							dftDocument = (EDL080002Message) mc.getMessageRecord("EDL080002");
							dftDocument.setH02USERID(user.getH01USR());
							dftDocument.setH02PROGRM("EDL0800");
							dftDocument.setH02TIMSYS(getTimeStamp());
							dftDocument.setH02SCRCOD("01");
							dftDocument.setH02OPECOD("0009");
							dftDocument.setE02DLDNLN(userPO.getIdentifier());
							dftDocument.setE02DLDDID(req.getParameter("E03NUMIDE"));
							dftDocument.setE02DLDNDR(numSeq);

				//			if (numActualSeq.trim().equals(numSeq.trim())
				//				== false) {
				//				//deletes the previous number sequence
				//			dftDocument.setE02DLDNDR(
				//			req.getParameter("CURSEQ" + i));
				//			} else {
					//deletes the current number sequence
				//			dftDocument.setE02DLDNDR(
				//			req.getParameter("E02DLDNDR" + i));
				//			}

							dftDocument.send();
							//dftDocument.destroy();
							flexLog("EDL080002 Message Sent");
							
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
							showERROR(msgError);
							msgError.setERRNUM("0");
							//IsNotError = true;
							//newmessage = mc.receiveMessage();
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");

							
							
							
							
							
							
						}
					}
					else {
						dftDocument = (EDL080002Message) mc.getMessageRecord("EDL080002");
						dftDocument.setH02USERID(user.getH01USR());
						dftDocument.setH02PROGRM("EDL0800");
						dftDocument.setH02TIMSYS(getTimeStamp());
						dftDocument.setH02SCRCOD("01");
						dftDocument.setH02OPECOD("0009");
						dftDocument.setE02DLDNLN(userPO.getIdentifier());
						dftDocument.setE02DLDDID(req.getParameter("E03NUMIDE"));
						dftDocument.setE02DLDNDR("999999999");

						dftDocument.send();
						//dftDocument.destroy();
						flexLog("EDL080002 Message Sent");
						break;
					}
				}
				if (!action.equals("D")) {
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
					msgError.setERRNUM("0");
					//IsNotError = true;
					//newmessage = mc.receiveMessage();
				} else
					flexLog(
						"Message "
							+ newmessage.getFormatName()
							+ " received.");
				}
				
				ses.setAttribute("lstDocuments", lstDocuments);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_acceptor_docs_detail.jsp");
				callPage(
					LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
					req,
					res);
			}
			if (action.equals("R")) {
				//duplicate documents
			}
			if (action.equals("N")) {
				//new record
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));
				int colnumdocs = 22;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";
				for (int i = 0; i < numdocs; i++) {
					myRow2[0] = req.getParameter("E02DLDNDR" + i);
					myRow2[1] = req.getParameter("E02DLDMA1" + i);
					myRow2[2] = req.getParameter("E02DLDMA2" + i);
					myRow2[3] = req.getParameter("E02DLDMA3" + i);
					myRow2[4] = req.getParameter("E02DLDOAM" + i);
					myRow2[5] = req.getParameter("E02DLDARC" + i);
					myRow2[6] = req.getParameter("E02DLDACC" + i);
					myRow2[7] = req.getParameter("E02DLDEXT" + i);
					myRow2[9] = req.getParameter("E02DLDCOI" + i);
					myRow2[10] = req.getParameter("E02DLDREW" + i);
					myRow2[11] = req.getParameter("E02SALINT" + i);
					myRow2[12] = req.getParameter("E02SALMOR" + i);
					myRow2[13] = req.getParameter("E02DLDRST" + i);
					myRow2[14] = req.getParameter("E02DLDPY1" + i);
					myRow2[15] = req.getParameter("E02DLDPY2" + i);
					myRow2[16] = req.getParameter("E02DLDPY3" + i);
					// checks ???
					if (userPO.getHeader8().equals("4"))
					{
						myRow2[17] = req.getParameter("E02DLDCOI" + i);
						myRow2[18] = req.getParameter("E02DLDKBK" + i);
						myRow2[19] = req.getParameter("E02DLDKBR" + i);
						myRow2[20] = req.getParameter("E02DLDCTA" + i);
						myRow2[21] = req.getParameter("E02DLDCHQ" + i);
					}

					lstDocuments.addRow(myFlag, myRow2);
				}
				myRow2[0] = " ";
				myRow2[1] = " ";
				myRow2[2] = " ";
				myRow2[3] = " ";
				myRow2[4] = " ";
				myRow2[5] = " ";
				myRow2[6] = " ";
				myRow2[7] = " ";
				myRow2[9] = " ";
				myRow2[10] = " ";
				myRow2[11] = " ";
				myRow2[12] = " ";
				myRow2[13] = " ";
				myRow2[14] = " ";
				myRow2[15] = " ";
				myRow2[16] = " ";
				myRow2[17] = " ";
				myRow2[18] = " ";
				myRow2[19] = " ";
				myRow2[20] = " ";
				myRow2[21] = " ";



				lstDocuments.addRow(myFlag, myRow2);
				ses.setAttribute("lstDocuments", lstDocuments);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_acceptor_docs_detail.jsp");
				callPage(
					LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
					req,
					res);
			}

			if (action.equals("0")) {
				//save records
				//	numdocini = Integer.parseInt(req.getParameter("NUMINI"));
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));

				String curdoc = "";
				String dtini = "";

				int colnumdocs = 22;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";

				for (int i = 0; i < numdocs; i++) {
					dftDocument =
						(EDL080002Message) mc.getMessageRecord("EDL080002");
					dftDocument.setH02USERID(user.getH01USR());
					dftDocument.setH02PROGRM("EDL0800");
					dftDocument.setH02TIMSYS(getTimeStamp());
					dftDocument.setH02SCRCOD("01");
					dftDocument.setH02OPECOD("0005");
					dftDocument.setE02DLDNLN(userPO.getIdentifier());
					dftDocument.setE02DLDDID(req.getParameter("E03NUMIDE"));
					//saves only if numdoc != blank

					curdoc = req.getParameter("E02DLDNDR" + i);
					dtini = req.getParameter("E02DLDMA1" + i);

					if (curdoc == null)
						curdoc = "";
					else
						curdoc = curdoc.trim();
					if (dtini == null)
						dtini = "";
					else
						dtini = dtini.trim();
					if (!curdoc.equals("") && !dtini.equals("")) {
						dftDocument.setE02DLDNDR(
							req.getParameter("E02DLDNDR" + i).trim());

						dftDocument.setE02DLDMA1(
							req.getParameter("E02DLDMA1" + i).trim());
						dftDocument.setE02DLDMA2(
							req.getParameter("E02DLDMA2" + i).trim());
						dftDocument.setE02DLDMA3(
							req.getParameter("E02DLDMA3" + i).trim());
						try {
							if (req.getParameter("E02DLDOAM" + i) != null) {
								dftDocument.setE02DLDOAM(
									req.getParameter("E02DLDOAM" + i).trim());
							} else {
								dftDocument.setE02DLDOAM("0");
							}
						} catch (Exception e) {
							dftDocument.setE02DLDOAM("0");
						}
						try {
							if (req.getParameter("E02DLDARC" + i) != null) {
								dftDocument.setE02DLDARC(
									req.getParameter("E02DLDARC" + i).trim());
							} else {
								dftDocument.setE02DLDARC("0");
							}
						} catch (Exception e) {
							dftDocument.setE02DLDARC("0");
						}
						try {
							if (req.getParameter("E02DLDACC" + i).equals(""))
								dftDocument.setE02DLDACC("0");
							else
								dftDocument.setE02DLDACC(
									req.getParameter("E02DLDACC" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDACC: " + e);
							dftDocument.setE02DLDACC("0");
						}
						try {
							dftDocument.setE02DLDEXT(
								req.getParameter("E02DLDEXT" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDEXT : " + e);
							dftDocument.setE02DLDEXT("0");
						}
					
					
						try {
							dftDocument.setE02DLDCOI(
								req.getParameter("E02DLDCOI" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDCOI : " + e);
							dftDocument.setE02DLDCOI("");
						}
					
					// new fields for checks
					
					if (userPO.getHeader8().equals("4"))
				
					{	
					
					try {
						dftDocument.setE02DLDKBK(
						req.getParameter("E02DLDKBK" + i).trim());
					} catch (Exception e) {
						flexLog("Exception E02DLDKBK : " + e);
						dftDocument.setE02DLDKBK("");
					}

					try {
						dftDocument.setE02DLDKBR(
						req.getParameter("E02DLDKBR" + i).trim());
					} catch (Exception e) {
						flexLog("Exception E02DLDKBR : " + e);
						dftDocument.setE02DLDKBR("");
					}

					try {
						dftDocument.setE02DLDCTA(
						req.getParameter("E02DLDCTA" + i).trim());
					} catch (Exception e) {
						flexLog("Exception E02DLDCTA : " + e);
						dftDocument.setE02DLDCTA("0");
					}

					try {
						dftDocument.setE02DLDCHQ(
						req.getParameter("E02DLDCHQ" + i).trim());
					} catch (Exception e) {
						flexLog("Exception E02DLDCHQ : " + e);
						dftDocument.setE02DLDCHQ("0");
					}
					
					try {
					dftDocument.setE02DLDPY1(
						req.getParameter("E02DLDPY1" + i).trim());
					dftDocument.setE02DLDPY2(
						req.getParameter("E02DLDPY2" + i).trim());
					dftDocument.setE02DLDPY3(
						req.getParameter("E02DLDPY3" + i).trim());
					} catch (Exception e){
						dftDocument.setE02DLDPY1("0");
						dftDocument.setE02DLDPY2("0");
						dftDocument.setE02DLDPY3("0");
				
					}

					}	
						
						try {
							dftDocument.setE02DLDREW(
								req.getParameter("E02DLDREW" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDREW : " + e);
							dftDocument.setE02DLDREW("");
						}

						try {
							dftDocument.setE02SALINT(
								req.getParameter("E02SALINT" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02SALINT : " + e);
							dftDocument.setE02SALINT("");
						}

						try {
							dftDocument.setE02SALMOR(
								req.getParameter("E02SALMOR" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02SALMOR : " + e);
							dftDocument.setE02SALMOR("");
						}
						try {
							dftDocument.setE02DLDRST(
								req.getParameter("E02DLDRST" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDRST : " + e);
							dftDocument.setE02DLDRST("");
						}


						myRow2[0] = req.getParameter("E02DLDNDR" + i);
						myRow2[1] = req.getParameter("E02DLDMA1" + i);
						myRow2[2] = req.getParameter("E02DLDMA2" + i);
						myRow2[3] = req.getParameter("E02DLDMA3" + i);
						myRow2[4] = req.getParameter("E02DLDOAM" + i);
						myRow2[5] = req.getParameter("E02DLDARC" + i);
						myRow2[6] = req.getParameter("E02DLDACC" + i);
						myRow2[7] = req.getParameter("E02DLDEXT" + i);
						myRow2[9] = req.getParameter("E02DLDCOI" + i);
						myRow2[10]= req.getParameter("E02DLDREW" + i);
						myRow2[11]= req.getParameter("E02SALINT" + i);
						myRow2[12]= req.getParameter("E02SALMOR" + i);
						myRow2[13]= req.getParameter("E02DLDRST" + i);
						myRow2[14]= req.getParameter("E02DLDPY1" + i);
						myRow2[15]= req.getParameter("E02DLDPY2" + i);
						myRow2[16]= req.getParameter("E02DLDPY3" + i);
						
						if (userPO.getHeader8().equals("4"))
						{
						myRow2[17] = req.getParameter("E02DLDCOI" + i);
						myRow2[18] = req.getParameter("E02DLDKBK" + i);
						myRow2[19] = req.getParameter("E02DLDKBR" + i);
						myRow2[20] = req.getParameter("E02DLDCTA" + i);
						myRow2[21] = req.getParameter("E02DLDCHQ" + i);
						}

						lstDocuments.addRow(myFlag, myRow2);
						dftDocument.send();
						dftDocument.destroy();
						flexLog("EDL080002 Message Sent");
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
							//IsNotError = true;
							//msgError.setERRNUM("0");
							showERROR(msgError);
							newmessage = mc.receiveMessage();
							if (IsNotError == false && ItHasNoError == true) {
								ItHasNoError = false;
								ses.setAttribute("error", msgError);
							}
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
					}

				}
				if (ItHasNoError) {
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.document.forms[0].SCREEN.value = 400");
					out.println(
						"		top.opener.document.forms[0].E01DEAACC.value = "
							+ userPO.getIdentifier());
					out.println(
						"		top.opener.document.forms[0].submit();");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					ses.setAttribute("lstDocuments", lstDocuments);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0800_dft_acceptor_docs_detail.jsp",
						req,
						res);
				}

			}
		} catch (Exception ex) {
			flexLog("Error: " + ex);
			ex.printStackTrace();
		}
	}

	protected void procReqInqAppDraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftBasic = (EDL080001Message) mc.getMessageRecord("EDL080001");
			dftBasic.setH01USERID(user.getH01USR());
			dftBasic.setH01PROGRM("EDL0800");
			dftBasic.setH01TIMSYS(getTimeStamp());
			dftBasic.setH01SCRCOD("01");
			dftBasic.setH01OPECOD("0002");
			dftBasic.setE01DEAACD("10");
			try {
				dftBasic.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}
			try {
				dftBasic.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}
			dftBasic.send();
			dftBasic.destroy();
			flexLog("EDL080001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to redirect : "
							+ LangPath
							+ "EDL0800_dft_basic.jsp");
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=3&E01DEAACC="
							+ req.getParameter("E01DEAACC"));
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0800_dft_enter_maint.jsp");
					callPage(
						LangPath + "EDL0800_dft_enter_maint.jsp",
						req,
						res);
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

	protected void procReqInquiryDraft(
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
			userPO.setOption("DFT");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0800_dft_enter_inq.jsp");
			callPage(LangPath + "EDL0800_dft_enter_inq.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionInquiryDraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL080001Message dftBasic = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			dftBasic = (EDL080001Message) mc.getMessageRecord("EDL080001");
			dftBasic.setH01USERID(user.getH01USR());
			dftBasic.setH01PROGRM("EDL0800");
			dftBasic.setH01TIMSYS(getTimeStamp());
			dftBasic.setH01SCRCOD("01");
			dftBasic.setH01OPECOD("0004");
			dftBasic.setE01DEAACD("10");
			
			String Acc = req.getParameter("E01DEAACC");
			
			if (Acc==null) {
				dftBasic.setE01DEAACC(userPO.getIdentifier());

			}
			else
			{
			dftBasic.setE01DEAACC(Acc);
			userPO.setIdentifier(dftBasic.getE01DEAACC());
			}

		
		try {
			dftBasic.setH01FLGWK2(req.getParameter("H01FLGWK2"));
		} catch (Exception e) {
		}

		dftBasic.send();
		dftBasic.destroy();
		flexLog("EDL080001 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	try {
		if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
			try {
				flexLog(
					"About to redirect : "
						+ LangPath
						+ "EDL0800_dft_basic.jsp");
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=2&E01DEAACC="
						+ userPO.getIdentifier());
				//flexLog("About to call Page: " + LangPath + "EDL0800_dft_inq_basic.jsp");
				//callPage(LangPath + "EDL0800_dft_inq_basic.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else { // There are errors
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_enter_inq.jsp");
				callPage(LangPath + "EDL0800_dft_enter_inq.jsp", req, res);
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

protected void procReqDocDet(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;

	EDL080002Message dftDocument = null;

	UserPos userPO = null;
	boolean IsNotError = false;
	try {
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		dftDocument = (EDL080002Message) mc.getMessageRecord("EDL080002");
		dftDocument.setH02USERID(user.getH01USR());
		dftDocument.setH02PROGRM("EDL0800");
		dftDocument.setH02TIMSYS(getTimeStamp());
		dftDocument.setH02SCRCOD("01");
		dftDocument.setH02OPECOD("0015");
		try {
			dftDocument.setE02DLDNLN(req.getParameter("E01DEAACC"));
		}
		catch (Exception e) {
			dftDocument.setE02DLDNLN(userPO.getIdentifier());
		}
		mc.sendMessage(dftDocument);
		//dftDocument.destroy();
		flexLog("EDL080002 Message Sent");

		if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
			if (req.getParameter("REQFLG").equals("1")) {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_basic.jsp");
				callPage(LangPath + "EDL0800_dft_basic.jsp", req, res);
			} else if (req.getParameter("REQFLG").equals("2")) {
				userPO.setPurpose("INQUIRY");
				ses.setAttribute("userPO", userPO);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_inq_basic.jsp");
				callPage(LangPath + "EDL0800_dft_inq_basic.jsp", req, res);
			} else if (req.getParameter("REQFLG").equals("3")) {
				userPO.setPurpose("APPROVAL");
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_ap_basic.jsp");
				callPage(LangPath + "EDL0800_dft_ap_basic.jsp", req, res);
			}
		} else { // There are errors
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0800_dft_enter_inq.jsp");
				callPage(LangPath + "EDL0800_dft_enter_new.jsp", req, res);
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

}