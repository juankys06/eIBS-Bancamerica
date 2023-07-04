package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import java.util.Calendar;

import datapro.eibs.sockets.*;

public class JSEDL0150 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINT_TO_BEAN = 500;
	protected static final int A_MAINT_TO_SCK = 502;
	protected static final int A_MAINT_DED = 504;
	protected static final int A_MAINT_COLL = 506;
	protected static final int A_MAINT_PMNT = 508;
	protected static final int A_MAINT_PMNT_PLUS = 509;
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

	protected static final int R_NEW_PARTICIPATION = 31;
	protected static final int A_NEW_PARTICIPATION = 32;

	protected static final int R_CANCEL_DEBIT = 33;
	protected static final int A_CANCEL_DEBIT = 34;
	
	protected static final int R_ADDCODES = 35;
	protected static final int A_ADDCODES = 36;
	
	protected static final int R_ACCOUNT_TITLE = 57;
	protected static final int A_ACCOUNT_TITLE = 58;
	
	protected static final int A_REFRESH_CYCLE = 60;
	
	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 300;
	protected static final int R_ENTER_CANCEL = 500;
	protected static final int R_ENTER_TRANSAC = 700;
	protected static final int R_ENTER_PRINT = 900;
	protected static final int R_ENTER_CALC = 1100;

	protected static final int A_ENTER_NEW = 200;
	protected static final int A_ENTER_MAINT = 400;
	protected static final int A_ENTER_CANCEL = 600;
	protected static final int A_ENTER_TRANSAC = 800;
	protected static final int A_ENTER_PRINT = 1000;
	protected static final int A_ENTER_CALC = 1200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0150() {
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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
       
		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
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
				msgLN.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}
			try {
				msgLN.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}
			msgLN.send();
			msgLN.destroy();
			flexLog("EDL015001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
					callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
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
		EDL015001Message msgLN = null;
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
			msgLN.setH01OPECOD("0001");
			msgLN.setE01DEAACD("10");

			// all the fields here
			java.util.Enumeration enu = msgLN.fieldEnumeration();
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
			
			msgLN.send();
			msgLN.destroy();
			flexLog("EDL015001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
				try {
					//Add client id and name
					if (ses.getAttribute("currClient")!= null && ses.getAttribute("lnBasic") != null ){
						ESD080001Message currClient = (ESD080001Message)ses.getAttribute("currClient");
						EDL015001Message lnBasic = (EDL015001Message) ses.getAttribute("lnBasic");
						lnBasic.setE01DEACUN(currClient.getE01CUSCUN());
						lnBasic.setE01CUSNA1(currClient.getE01CUSNA1());
						ses.setAttribute("lnBasic",lnBasic);
					}
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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
					printProdFrame(out, firstLink, LangPath);
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
		ESD000005Message msgLN = null;
		ELEERRMessage msgError = null;
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
			msgLN = (ESD000005Message) ses.getAttribute("lnInst");
			//msgLN = (ESD000005Message)mc.getMessageRecord("ESD000005");
			msgLN.setH05USR(user.getH01USR());
			msgLN.setH05PGM("EDL0150");
			msgLN.setH05TIM(""); //getTimeStamp()
			msgLN.setH05SCR("01");
			msgLN.setH05OPE("0005");
			msgLN.setE05ACC(userPO.getIdentifier());
			msgLN.setE05TYP("2");

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
				//showERROR(msgError);
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
					msgLN = new ESD000005Message();
					flexLog("ESD000005 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000005Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnInst", msgLN);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_special_inst.jsp");
						callPage(LangPath + "EDL0150_ln_special_inst.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
				//showERROR(msgError);
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
					msgLN = new EDL015206Message();
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
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_xchg_rate.jsp");
						callPage(LangPath + "EDL0150_ln_xchg_rate.jsp", req, res);
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
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_cancel_auto_debit.jsp");
						callPage(LangPath + "EDL0150_ln_cancel_auto_debit.jsp", req, res);
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

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		EDL015002Message msgDed = null;
		EDL015003Message msgPmnt = null;
		EDL015004Message msgColl = null;
		EDL015005Message msgPmntP = null;
		EDL015009Message msgEnd = null;
		JBListRec dedList = null;
		JBListRec collList = null;
		JBListRec pmntList = null;
		JBListRec pmntPList = null;

		EFT000020Message msgLNNew = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String recalc="";
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLN = (EDL015001Message) ses.getAttribute("lnBasic");
			// msgLN = (EDL015001Message)mc.getMessageRecord("EDL015001");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL0150");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
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

			//msgLN.send();
			
			mc.sendMessage(msgLN);
			msgLN.destroy();
			flexLog("EDL015001 Message Sent to Sockets");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Sending EDL015002
		try	{
	 	
		msgDed = (EDL015002Message)mc.getMessageRecord("EDL015002");
		String subname ="";
		
		for (int i=1; i<6;i++) {
			
		  if ( i==1) {
		  	dedList = (JBListRec)ses.getAttribute("tax");
		  	subname = "T";
		  } else if ( i==2) {
		  	dedList = (JBListRec)ses.getAttribute("com");
		  	subname = "C";
		  } else if ( i==3) {
		  	dedList = (JBListRec)ses.getAttribute("ins");
		  	subname = "I";
		  } else if ( i==4) {
		  	dedList = (JBListRec)ses.getAttribute("iva");
		  	subname = "V";
		  }	else dedList = (JBListRec)ses.getAttribute("ded");
		  
		  dedList.initRow();
		  while (dedList.getNextRow()) {
		  	
			msgDed.setH02USERID(user.getH01USR());
		 	msgDed.setH02PROGRM("EDL0150");
		 	msgDed.setH02TIMSYS(getTimeStamp());
		 	msgDed.setH02SCRCOD("01");
		 	msgDed.setH02OPECOD("0005");
						 
			try{ msgDed.setE02DEDAMT(dedList.getRecord(2)); } catch (Exception e) {}
			try{ msgDed.setE02DLIPBC(dedList.getRecord(21)); } catch (Exception e) {}
			try{ msgDed.setE02BSEAMT(dedList.getRecord(0)); } catch (Exception e) {}
			
			
			try{ msgDed.setE02DLIAPC(dedList.getRecord(0)); } catch (Exception e) {} 
			
			try{
				msgDed.setE02DLIPBC(req.getParameter("E02DLIPBC"));
			}
			catch (Exception e)	{}
			
			try{
				msgDed.setE02DLIDAC(req.getParameter("E02DLIDAC"));
			}catch (Exception e){}
			
			if (i<5 ) { 	
				try{
			 		msgDed.setE02DEDAMT(req.getParameter("E02DEDAMT"+subname+dedList.getCurrentRow()));
			 		
		 		}
				catch (Exception e)	{}
				
				if (i==3) {
					try{
			 			msgDed.setE02DLIPBC(req.getParameter("E02DLIPBC"+subname+dedList.getCurrentRow()));
		 			}
					catch (Exception e)	{}
					if (dedList.getRecord(22).equals("3")) {
						try{
			 				msgDed.setE02BSEAMT(req.getParameter("E02BSEAMT"+subname+dedList.getCurrentRow()));
		 				}
						catch (Exception e)	{}
					}
				} 							
			}

			try{ msgDed.setE02DEAACC(dedList.getRecord(1)); } catch (Exception e) {}	//			 
			try{ msgDed.setE02DLIAPC(dedList.getRecord(3)); } catch (Exception e) {} // 
			try{ msgDed.setE02DLICDE(dedList.getRecord(4)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIDPM(dedList.getRecord(5)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIFAC(dedList.getRecord(6)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIIVA(dedList.getRecord(7)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIIVB(dedList.getRecord(8)); } catch (Exception e) {}	// 
			try{
				if (i==2){
					msgDed.setE02DLIIVP(req.getParameter("E02DLIIVP"+subname+dedList.getCurrentRow()));
					System.out.println("COM % :"+msgDed.getE02DLIIVP());
				}
				else { 
				  msgDed.setE02DLIIVP(dedList.getRecord(9));
				}
		    } catch (Exception e) {}	// 
			try{ msgDed.setE02DLINME(dedList.getRecord(10)); } catch (Exception e) {}	//CHARGE NAME 
			try{ msgDed.setE02DLIOAC(dedList.getRecord(11)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIOBK(dedList.getRecord(12)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIOBR(dedList.getRecord(13)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIOCY(dedList.getRecord(14)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIOGL(dedList.getRecord(15)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIPLZ(dedList.getRecord(16)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLIPRO(dedList.getRecord(17)); } catch (Exception e) {}	// 
			try{ msgDed.setE02FACTOR(dedList.getRecord(18)); } catch (Exception e) {}	// 
			try{ msgDed.setE02MAXAMT(dedList.getRecord(19)); } catch (Exception e) {}	// 
			try{ msgDed.setE02MINAMT(dedList.getRecord(20)); } catch (Exception e) {}	//
			try{ msgDed.setE02DLISEG(dedList.getRecord(22)); } catch (Exception e) {}	// 
			try{ msgDed.setE02DLISEL(dedList.getRecord(23)); } catch (Exception e) {}	//
			try{ msgDed.setE02DLIAPC(dedList.getRecord(24)); } catch (Exception e) {}	//
			try{ msgDed.setE02DLINPM(dedList.getRecord(26)); } catch (Exception e) {}
			try{ msgDed.setE02DLINPD(dedList.getRecord(27)); } catch (Exception e) {}
			try{ msgDed.setE02DLINPY(dedList.getRecord(28)); } catch (Exception e) {}
			
		 	//msgDed.send();
			mc.sendMessage(msgDed);
		  }
		
		}
	 	msgDed.setH02FLGMAS("*");
		//msgDed.send();
	 	mc.sendMessage(msgDed);
	 	msgDed.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

		//Sending EDL015003
		try {
			pmntList = (JBListRec) ses.getAttribute("pmnt");
			msgPmnt = (EDL015003Message) mc.getMessageRecord("EDL015003");

			pmntList.initRow();
			while (pmntList.getNextRow()) {
				msgPmnt.setH03USERID(user.getH01USR());
				msgPmnt.setH03PROGRM("EDL0150");
				msgPmnt.setH03TIMSYS(getTimeStamp());
				msgPmnt.setH03SCRCOD("01");
				msgPmnt.setH03OPECOD("0005");

				try {
					msgPmnt.setE03DEAACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E03DEAACC");
				}

				try {
					msgPmnt.setE03DLPPNU(pmntList.getRecord(0)); //Quota
				} catch (Exception e) {
					flexLog("E03DLPPNU");
				}
				try {
					msgPmnt.setE03DLPPD1(pmntList.getRecord(1)); //Date
				} catch (Exception e) {
					flexLog("E03DLPPD1");
				}

				try {
					msgPmnt.setE03DLPPD2(pmntList.getRecord(2)); //Date
				} catch (Exception e) {
					flexLog("E03DLPPD1");
				}

				try {
					msgPmnt.setE03DLPPD3(pmntList.getRecord(3)); //Date
				} catch (Exception e) {
					flexLog("E03DLPPD1");
				}

				try {
					msgPmnt.setE03DLPPRI(pmntList.getRecord(4)); //Principal
				} catch (Exception e) {
					flexLog("E03DLPPRI");
				}
				try {
					msgPmnt.setE03DLPINT(pmntList.getRecord(5)); //Interest
				} catch (Exception e) {
					flexLog("E03DLPINT");
				}
				try {
					msgPmnt.setE03DLPIIP(pmntList.getRecord(6)); //Interest Included
				} catch (Exception e) {
					flexLog("E03DLPIIP");
				}

				msgPmnt.send();
			}

			msgPmnt.setH03FLGMAS("*");
			msgPmnt.send();
			// mc.sendMessage(msgDed);
			msgPmnt.destroy();
			flexLog("EDL015003 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Sending EDL015004
		try {
			collList = (JBListRec) ses.getAttribute("coll");
			msgColl = (EDL015004Message) mc.getMessageRecord("EDL015004");

			collList.initRow();
			while (collList.getNextRow()) {
				msgColl.setH04USERID(user.getH01USR());
				msgColl.setH04PROGRM("EDL0150");
				msgColl.setH04TIMSYS(getTimeStamp());
				msgColl.setH04SCRCOD("01");
				msgColl.setH04OPECOD("0005");

				try {
					msgColl.setE04DEAACC(userPO.getIdentifier());
				} catch (Exception e) {
					flexLog("E04DEAACC");
				}

				try {
					msgColl.setE04RCLACB(Util.formatBlank(collList.getRecord(0).toUpperCase()));
					//Code
				} catch (Exception e) {
					flexLog("E04RLCACB");
				}
				try {
					msgColl.setE04RCLTYB(Util.formatBlank(collList.getRecord(1).toUpperCase()));
					//Name
				} catch (Exception e) {
					flexLog("E04RLCTYB");
				}
				try {
					msgColl.setE04RCLCCY(collList.getRecord(2).toUpperCase()); //Police
				} catch (Exception e) {
					flexLog("E04RCLCCY");
				}
				try {
					msgColl.setE04RCLAMT(collList.getRecord(3)); //Deduction
				} catch (Exception e) {
					flexLog("E04RCLAMT");
				}
				try {
					msgColl.setE04RCLF04(collList.getRecord(4)); //FOGAPE
				} catch (Exception e) {
					flexLog("E04RCLF04");
				}
				msgColl.send();
			}

			msgColl.setH04FLGMAS("*");
			msgColl.send();
			// mc.sendMessage(msgDed);
			msgColl.destroy();
			flexLog("EDL015004 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		pmntPList = (JBListRec) ses.getAttribute("pmntPlus");
		if (pmntPList != null) {
			//Sending EDL015005
			try {
				msgPmntP = (EDL015005Message) mc.getMessageRecord("EDL015005");

				msgPmntP.setH05USERID(user.getH01USR());
				msgPmntP.setH05PROGRM("EDL0150");
				msgPmntP.setH05TIMSYS(getTimeStamp());
				msgPmntP.setH05SCRCOD("01");
				msgPmntP.setH05OPECOD("0005");

				pmntPList.initRow();
				while (pmntPList.getNextRow()) {

					try {
						msgPmntP.setE05DEAACC(userPO.getIdentifier());
					} catch (Exception e) {
						flexLog("E05DEAACC");
					}

					try {
						msgPmntP.setE05DLPPNU(pmntPList.getRecord(0)); //Quota
					} catch (Exception e) {
						flexLog("E05DLPPNU");
					}

					try {
						msgPmntP.setE05DLPVEX(pmntPList.getRecord(1)); //Principal
					} catch (Exception e) {
						flexLog("E05DLPVEX");
					}

					msgPmntP.send();
				}

				msgPmntP.setH05FLGMAS("*");
				msgPmntP.send();
				// mc.sendMessage(msgPmntP);
				msgPmntP.destroy();
				flexLog("EDL015005 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}	
		}

		


		//Sending EDL015009
		try {
			msgEnd = (EDL015009Message) mc.getMessageRecord("EDL015009");

			msgEnd.setH09USERID(user.getH01USR());
			msgEnd.setH09PROGRM("EDL0150");
			msgEnd.setH09TIMSYS(getTimeStamp());
			msgEnd.setH09SCRCOD("01");
			msgEnd.setH09OPECOD("0005");

			try {
				msgEnd.setE09DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				flexLog("E09DEAACC");
			}

			msgEnd.send();
			// mc.sendMessage(msgDed);
			msgEnd.destroy();
			flexLog("EDL015009 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses)) {  // There are no errors			
			recalc= req.getParameter("E01RCLFLC");
			if(recalc ==null){
				recalc = " ";
			}
			if (recalc.trim().equals("X")) {
				try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
			} else {			
				if(userPO.getPurpose().equals("MAINTENANCE") && !(userPO.getHeader10().equals("CONF"))){												
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
						callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if (userPO.getHeader10().equals("CONF")) {
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
						callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}	
				}
				else {
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}	
				}
			}
		}
		else {  // There are errors
 			
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);	
				}
				catch (Exception e) {
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
	protected void procActionMaintColl(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec collList = null;
		int colNum = 5;

		try {
			collList = new JBListRec();
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
			try{
				myRow[4] = req.getParameter("RCLF04_" + row).toUpperCase();
			}
			catch (Exception e) {}
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
			dedList.setRecord(sel, 23, row);
			dedList.setRecord(req.getParameter("DLICDE_" + row), 4, row);
			dedList.setRecord(req.getParameter("DLINME_" + row).toUpperCase(), 10, row);
			dedList.setRecord(req.getParameter("DLIPLZ_" + row).toUpperCase(), 16, row);
			dedList.setRecord(req.getParameter("DLIDPM_" + row), 5, row);
			dedList.setRecord(req.getParameter("DLIFAC_" + row), 6, row);

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
			pmntList = new JBListRec();
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

		for (int row = 1; row < 360; row++) {
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
					out.println("		top.opener.document.forms[0].SCREEN.value = 500");
					out.println("		top.opener.document.forms[0].submit();");
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
							pymNumLast = Integer.parseInt(pmntList.getRecord(0)) + 1;
						}
						int number = Integer.parseInt(req.getParameter("NUMBER"));
						int month = Integer.parseInt(req.getParameter("DATE1"));
						int day = Integer.parseInt(req.getParameter("DATE2"));
						int year = 2000 + Integer.parseInt(req.getParameter("DATE3"));
						int feq = Integer.parseInt(req.getParameter("FREQUENCY"));
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
								myRow[1] = (myDate.get(Calendar.MONTH) + 1) + "";
								myRow[2] = myDate.get(Calendar.DAY_OF_MONTH) + "";
								String y = myDate.get(Calendar.YEAR) + "";
								myRow[3] = y.substring(2);
							} else
								if (user.getE01DTF().equals("DMY")) {
									myRow[1] = myDate.get(Calendar.DAY_OF_MONTH) + "";
									myRow[2] = (myDate.get(Calendar.MONTH) + 1) + "";
									String y = myDate.get(Calendar.YEAR) + "";
									myRow[3] = y.substring(2);
								} else {
									String y = myDate.get(Calendar.YEAR) + "";
									myRow[1] = y.substring(2);
									myRow[2] = (myDate.get(Calendar.MONTH) + 1) + "";
									myRow[3] = myDate.get(Calendar.DAY_OF_MONTH) + "";
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
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_pay_pln_det.jsp");
						callPage(LangPath + "EDL0150_ln_pay_pln_det.jsp", req, res);
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
							"About to call Page: " + LangPath + "EDL0150_ln_pay_pln_det.jsp?ROW=" + row);
						res.sendRedirect(super.srctx + LangPath + "EDL0150_ln_pay_pln_det.jsp?ROW=" + row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					break;
				}
		}

	}
	protected void procActionMaintPayIrreg(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBListRec pmntPList = null;
		int colNum = 2;

		try {
			pmntPList = new JBListRec();
			pmntPList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String sel = " ";
		String myFlag = "";
		String myRow[] = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			myRow[i] = "";
		}

		for (int row = 1; row < 31; row++) {
			try {
				sel = req.getParameter("DLPPNU_" + row).toUpperCase();
				if (sel.equals(""))
					break;
			} catch (Exception e) {
				break;
			}

			myRow[0] = req.getParameter("DLPPNU_" + row);
			myRow[1] = req.getParameter("DLPPRI_" + row);

			pmntPList.addRow(myFlag, myRow);

		}

		flexLog("Putting java beans into the session...pmntPlus.rowsNumber ");
		ses.setAttribute("pmntPlus", pmntPList);

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
					out.println("		top.opener.document.forms[0].SCREEN.value = 500");
					out.println("		top.opener.document.forms[0].submit();");
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
						pmntPList.setLastRow();
						int pymNumLast = 1;
						if (!pmntPList.getNoResult()) {
							pymNumLast = Integer.parseInt(pmntPList.getRecord(0)) + 1;
						}
						int number = Integer.parseInt(req.getParameter("NUMBER"));

						for (int row = 0; row < number; row++) {

							// Payment Number
							myRow[0] = pymNumLast + row + "";
							// Amount
							myRow[1] = req.getParameter("AMOUNT");

							pmntPList.addRow(myFlag, myRow);

						}

						flexLog("Putting java beans into the session");
						ses.setAttribute("pmntPlus", pmntPList);
					} catch (Exception e) {
					}

					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_pay_pln_irreg.jsp");
						callPage(LangPath + "EDL0150_ln_pay_pln_irreg.jsp", req, res);
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
							"About to call Page: " + LangPath + "EDL0150_ln_pay_pln_irreg.jsp?ROW=" + row);
						res.sendRedirect(super.srctx + LangPath + "EDL0150_ln_pay_pln_irreg.jsp?ROW=" + row);
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
		EDL015230Message msgGenInf = null;

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		String recalc="";
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgGenInf = (EDL015230Message) ses.getAttribute("lnGenTran");
			//msgGenInf = (EDL015230Message)mc.getMessageRecord("EDL015230");
			msgGenInf.setH30USERID(user.getH01USR());
			msgGenInf.setH30PROGRM("EDL0150");
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
			flexLog("EDL015230 Message Sent");
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
				//showERROR(msgError);
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

//			Control de emision cheques de gerencia Banesco GAP-B60


			if (newmessage.getFormatName().equals("EDL015230")) {
				try {
					msgGenInf = new EDL015230Message();
					flexLog("EDL015230 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGenInf = (EDL015230Message) newmessage;
				// showESD008004(msgGenInf);

				userPO.setIdentifier(msgGenInf.getE30DEAACC());
				userPO.setHeader1(msgGenInf.getE30DEAPRO());
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

// Control de emision cheques de gerencia Banesco GAP-B60

						  recalc= msgGenInf.getH30FLGWK2().trim();
						  if(recalc ==null){
							  recalc = " ";
						  }
						  if (recalc.trim().equals("X")) {
							  try {
									  flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
									  callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);	
								  }
								  catch (Exception e) {
									  flexLog("Exception calling page " + e);
								  }
						  } else {			


								if (userPO.getPurpose().equals("MAINTENANCE")) {
									try {
										flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
										callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
								} else
									if (userPO.getPurpose().equals("NEW")) {
										try {
											userPO.setPurpose("MAINTENANCE");
											flexLog("About to call Page: " + LangPath + "EDL0150_ln_confirm.jsp");
											callPage(LangPath + "EDL0150_ln_confirm.jsp", req, res);
										} catch (Exception e) {
											flexLog("Exception calling page " + e);
										}
								}

						  }

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
						callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
				//showERROR(msgError);
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
					msgLN = new EDL015001Message();
					flexLog("EDL015001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (EDL015001Message) newmessage;

				userPO.setIdentifier(msgLN.getE01DEAACC());
				userPO.setHeader1(msgLN.getE01DEAPRO());
				userPO.setHeader2(msgLN.getE01DEACUN());
				userPO.setHeader3(msgLN.getE01CUSNA1());
				userPO.setCurrency(msgLN.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnBasic", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
						callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
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
		ESD000002Message msgLN = null;
		ELEERRMessage msgError = null;
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
			msgLN = (ESD000002Message) ses.getAttribute("lnCodes");
			//msgLN = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgLN.setH02USR(user.getH01USR());
			msgLN.setH02PGM("EDL0130");
			msgLN.setH02TIM(getTimeStamp());
			msgLN.setH02SCR("01");
			msgLN.setH02OPE("0005");

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
				//showERROR(msgError);
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
					msgLN = new ESD000002Message();
					flexLog("ESD000002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000002Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_codes.jsp");
						callPage(LangPath + "EDL0150_ln_codes.jsp", req, res);
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
	
	protected void procActionAdditionalCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD001401Message msgLN = null;
		ELEERRMessage msgError = null;
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
			msgLN = (ESD001401Message) ses.getAttribute("lnCodes");
			//msgLN = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgLN.setH14USR(user.getH01USR());
			msgLN.setH14PGM("ESD0014");
			msgLN.setH14TIM(getTimeStamp());
			msgLN.setH14SCR("01");
			msgLN.setH14OPE("0005");

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
				//showERROR(msgError);
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
					msgLN = new ESD001401Message();
					flexLog("ESD001401 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD001401Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_addcodes.jsp");
						callPage(LangPath + "EDL0150_ln_addcodes.jsp", req, res);
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
		ESD000006Message msgLN = null;
		ELEERRMessage msgError = null;
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
			//msgLN = (ESD000006Message)ses.getAttribute("cdTit");
			msgLN = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgLN.setH06USR(user.getH01USR());
			msgLN.setH06PGM("EDL0150");
			msgLN.setH06TIM(""); //getTimeStamp()
			msgLN.setH06SCR("01");
			msgLN.setH06OPE("0005");
			msgLN.setE06ACC(userPO.getIdentifier());
			msgLN.setE06RTP("H");

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

			msgLN.send();
			//mc.sendMessage(msgLN);
			msgLN.destroy();

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
				//showERROR(msgError);
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
					msgLN = new ESD000006Message();
					flexLog("ESD000006 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000006Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTit", msgLN);

				if (IsNotError) { // There are no errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_tit.jsp");
						callPage(LangPath + "EDL0150_ln_tit.jsp", req, res);
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

		MessageRecord newmessage = null;
	EDL015001Message msgLN = null;
	EDL015002Message msgDed = null;
	EDL015003Message msgPmnt = null;
	EDL015004Message msgColl = null;
	EDL015005Message msgPmntP = null;	
	EDL015230Message msgNewOK = null;
	JBListRec taxList = null;
	JBListRec comList = null;
	JBListRec ivaList = null;
	JBListRec insList = null;
	JBListRec dedList = null;
	JBListRec collList = null;
	JBListRec pmntList = null;
	JBListRec pmntPList = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	flexLog("Initializing java beans into the session");
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		msgLN = new datapro.eibs.beans.EDL015001Message();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		int colNum = 29;
		dedList = new datapro.eibs.beans.JBListRec();
		dedList.init(colNum);
		taxList = new datapro.eibs.beans.JBListRec();
		taxList.init(colNum);
		ivaList = new datapro.eibs.beans.JBListRec();
		ivaList.init(colNum);
		comList = new datapro.eibs.beans.JBListRec();
		comList.init(colNum);
		insList = new datapro.eibs.beans.JBListRec();
		insList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		int colNum = 7;
		pmntList = new datapro.eibs.beans.JBListRec();
		pmntList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		int colNum = 5;
		collList = new datapro.eibs.beans.JBListRec();
		collList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
	try {
		int colNum = 2;
		pmntPList = new datapro.eibs.beans.JBListRec();
		pmntPList.init(colNum);
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
	}  	
	
	ses.setAttribute("error", msgError);
	ses.setAttribute("userPO", userPO);
	ses.setAttribute("lnBasic", msgLN);
	ses.setAttribute("ded", dedList);
	ses.setAttribute("pmnt", pmntList);
	ses.setAttribute("pmntPlus", pmntPList);
	ses.setAttribute("coll", collList);
		
	// Receive Error Message
	try
	{
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ELEERR")) {
		msgError = (ELEERRMessage)newmessage;
		IsNotError = msgError.getERRNUM().equals("0");
		flexLog("IsNotError = " + IsNotError);
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");
		
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
	// Receive Data
	try
	{
	 	while (true) {   

		 	newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL015001")) {

				msgLN = (EDL015001Message)newmessage;
				flexLog("EDL015001 Message Received");

				userPO.setIdentifier(msgLN.getE01DEAACC());
				userPO.setHeader1(msgLN.getE01DEAPRO());
				userPO.setHeader2(msgLN.getE01DEACUN());
				userPO.setHeader3(msgLN.getE01CUSNA1());
				userPO.setHeader4(msgLN.getE01DSCPRO());
				userPO.setCurrency(msgLN.getE01DEACCY());
				userPO.setBank(msgLN.getE01DEABNK());
				userPO.setBranch(msgLN.getE01DEABRN());
				userPO.setType(msgLN.getE01DEATYP());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnBasic", msgLN);

			}
			else if (newmessage.getFormatName().equals("EDL015009")) {
				flexLog("EDL015009 Message Received");
				userPO.setHeader10("BASIC");
				ses.setAttribute("userPO", userPO);
				break;
			}
			else if (newmessage.getFormatName().equals("EDL015002")) {
			    //Deductions List
				
				flexLog("EDL015002 Message Received");
				int colNum = 29;
				int typ;
				
				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
				
				while (true) {

					msgDed = (EDL015002Message)newmessage;

					marker = msgDed.getH02FLGMAS();

					if (marker.equals("*")) {
						break;
					}
					else {
						try{
						  typ = Integer.parseInt(msgDed.getE02DLIAPC());
						}
						catch (Exception e) {
						  typ = 0;
						}
						//Quote List
						myRow[0] =  msgDed.getE02BSEAMT().trim();	// 
						myRow[1] =  msgDed.getE02DEAACC().trim();	// 
						myRow[2] =  msgDed.getE02DEDAMT().trim();	// 
						myRow[3] =  msgDed.getE02DLIAPC().trim();	// 
						myRow[4] =  msgDed.getE02DLICDE().trim();	// 
						myRow[5] =  msgDed.getE02DLIDPM().trim();	// 
						myRow[6] =  msgDed.getE02DLIFAC().trim();	// 
						myRow[7] =  msgDed.getE02DLIIVA().trim();	// 
						myRow[8] =  msgDed.getE02DLIIVB().trim();	// 
						myRow[9] =  msgDed.getE02DLIIVP().trim();	// 
						myRow[10] =  msgDed.getE02DLINME().trim();	//CHARGE NAME 
						myRow[11] =  msgDed.getE02DLIOAC().trim();	// 
						myRow[12] =  msgDed.getE02DLIOBK().trim();	// 
						myRow[13] =  msgDed.getE02DLIOBR().trim();	// 
						myRow[14] =  msgDed.getE02DLIOCY().trim();	// 
						myRow[15] =  msgDed.getE02DLIOGL().trim();	// 
						myRow[16] =  msgDed.getE02DLIPLZ().trim();	// 
						myRow[17] =  msgDed.getE02DLIPRO().trim();	// 
						myRow[18] =  msgDed.getE02FACTOR().trim();	// 
						myRow[19] =  msgDed.getE02MAXAMT().trim();	// 
						myRow[20] =  msgDed.getE02MINAMT().trim();	// 
						myRow[21] =  msgDed.getE02DLIPBC().trim();	//
						myRow[22] =  msgDed.getE02DLISEG().trim();	//
						myRow[23] =  msgDed.getE02DLISEL().trim();	//
						myRow[24] =  msgDed.getE02DLIAPC().trim();	//
						myRow[25] =  msgDed.getE02DLIDAC().trim();	//
						myRow[26] =  msgDed.getE02DLINPM().trim();	//
						myRow[27] =  msgDed.getE02DLINPD().trim();	//
						myRow[28] =  msgDed.getE02DLINPY().trim();	//
						
						if (msgDed.getE02DLIPRO().trim().equals("L")) {
							dedList.addRow(myFlag, myRow);
						} else  {
							switch (typ) {				
								case 1 : //taxes
										taxList.addRow(myFlag, myRow);
										break;
								case 2 ://commisions
										comList.addRow(myFlag, myRow);
										break;
								case 3 ://insurance
										insList.addRow(myFlag, myRow);
										break;
								default ://other charges
										ivaList.addRow(myFlag, myRow);
										break;
							}
						}				
					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("ded", dedList);
				ses.setAttribute("tax", taxList);
				ses.setAttribute("ins", insList);
				ses.setAttribute("com", comList);
				ses.setAttribute("iva", ivaList);
			}
			else if (newmessage.getFormatName().equals("EDL015003")) {
			    //Payments List
				// Fill List bean

				flexLog("EDL015003 Message Received");
				
				int colNum = 7;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
				
				while (true) {

					msgPmnt = (EDL015003Message)newmessage;

					marker = msgPmnt.getH03FLGMAS();

					if (marker.equals("*")) {
						break;
					}
					else {
						//Quote List
						myRow[0] =  Util.trim(msgPmnt.getE03DLPPNU());	// Quote Number
						myRow[1] =  Util.trim(msgPmnt.getE03DLPPD1());	// Date
						myRow[2] =  Util.trim(msgPmnt.getE03DLPPD2());	// Date
						myRow[3] =  Util.trim(msgPmnt.getE03DLPPD3());	// Date
						myRow[4] =  Util.trim(msgPmnt.getE03DLPPRI());	// Principal
						myRow[5] =  Util.trim(msgPmnt.getE03DLPINT());	// Interest
						myRow[6] =  Util.trim(msgPmnt.getE03DLPIIP());	// Interest Included
							
						pmntList.addRow(myFlag, myRow);
										
					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("pmnt", pmntList);
			}
			else if (newmessage.getFormatName().equals("EDL015004")) {
			   //Deductions List
				// Fill List bean

				flexLog("EDL015004 Message Received");
				
				int colNum = 5;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
				
				while (true) {

					msgColl = (EDL015004Message)newmessage;

					marker = msgColl.getH04FLGMAS();

					if (marker.equals("*")) {
						break;
					}
					else {
						//Quote List
						myRow[0] =  Util.formatCell(msgColl.getE04RCLACB());	// Collateral Number
						myRow[1] =  Util.formatCell(msgColl.getE04RCLTYB());	// Type
						myRow[2] =  Util.formatCell(msgColl.getE04RCLCCY());	   // Currency
						myRow[3] =  Util.formatCell(msgColl.getE04RCLAMT());	// Amount
						myRow[4] =  Util.formatCell(msgColl.getE04RCLF04());	// FOGAPE
							
						collList.addRow(myFlag, myRow);
										
					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("coll", collList);
			}
			else if (newmessage.getFormatName().equals("EDL015005")) {
				// Irregular Payments List
				// Fill List bean

				flexLog("EDL015005 Message Received");
				
				int colNum = 2;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
				
				while (true) {

					msgPmntP = (EDL015005Message)newmessage;

					marker = msgPmntP.getH05FLGMAS();

					if (marker.equals("*")) {
						break;
					}
					else {
						//Quote List
						myRow[0] =  Util.trim(msgPmntP.getE05DLPPNU());	// Quote Number
						myRow[1] =  Util.trim(msgPmntP.getE05DLPVEX());	// Principal
							
						pmntPList.addRow(myFlag, myRow);
					}
					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("pmntPlus", pmntPList);
			}			
			else if (newmessage.getFormatName().equals("EDL015230")) {
				
				msgNewOK = (EDL015230Message)newmessage;

				flexLog("EDL015230 Message Received");
				// mod: EMAT 9/4/2001
				// to capture the new account number into userPo
				userPO.setIdentifier(msgNewOK.getE30DEAACC());
				//
				userPO.setHeader10("CONF");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnGenTran", msgNewOK);
				break;
			}
			
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
	 	}  

	}
	catch (Exception e)	{
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

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
			callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqEnterNewParticipation(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("LN");
			userPO.setPurpose("NEW_PARTICIPATION");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_new_part.jsp");
			callPage(LangPath + "EDL0150_ln_enter_new_part.jsp", req, res);
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

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("LN");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_new.jsp");
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
		ESD000005Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgLN = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgLN.setH05USR(user.getH01USR());
			msgLN.setH05PGM("EDL0150");
			msgLN.setH05TIM(""); //getTimeStamp()
			msgLN.setH05SCR("01");
			msgLN.setH05OPE(opCode);
			msgLN.setE05ACC(userPO.getIdentifier());
			msgLN.setE05TYP("2");
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
				//showERROR(msgError);
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
					msgLN = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000005Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnInst", msgLN);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_special_inst.jsp");
						callPage(LangPath + "EDL0150_ln_special_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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
			msgError = new ELEERRMessage();
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
				//showERROR(msgError);
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
					msgLN = new EDL015206Message();
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
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_xchg_rate.jsp");
						callPage(LangPath + "EDL0150_ln_xchg_rate.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
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
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_cancel_auto_debit.jsp");
						callPage(LangPath + "EDL0150_ln_cancel_auto_debit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
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
			msgError = new ELEERRMessage();
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
				//showERROR(msgError);
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
					msgLN = new EDL015009Message();
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
						flexLog("About to call Page: " + LangPath + "EDL0130_cd_prep_int.jsp");
						callPage(LangPath + "EDL0130_cd_prep_int.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0130_cd_finish.jsp");
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
		EDL015001Message msgLN = null;
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
			msgLN.setH01FLGWK1("A");
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

		try {
			if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
					callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
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
		EDL015005Message msgPmntP = null;
		JBListRec dedList = null;
		JBListRec collList = null;
		JBListRec pmntList = null;
		JBListRec pmntPList = null;
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
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgLN = new EDL015001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 6;
			dedList = new JBListRec();
			dedList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 5;
			pmntList = new JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 4;
			collList = new JBListRec();
			collList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			int colNum = 2;
			pmntPList = new JBListRec();
			pmntPList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}		

		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("lnBasic", msgLN);
		ses.setAttribute("ded", dedList);
		ses.setAttribute("pmnt", pmntList);
		ses.setAttribute("pmntPlus", pmntPList);
		ses.setAttribute("coll", collList);

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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
					userPO.setHeader2(msgLN.getE01DEACUN());
					userPO.setHeader3(msgLN.getE01CUSNA1());
					userPO.setCurrency(msgLN.getE01DEACCY());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("lnMant", msgLN);

				} else
					if (newmessage.getFormatName().equals("EDL015009")) {

						if (IsNotError) { // There are no errors
							try {
								flexLog("About to call Page: " + LangPath + "EDL0150_ln_mant.jsp");
								callPage(LangPath + "EDL0150_ln_mant.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else { // There are errors
							try {
								flexLog("About to call Page: " + LangPath + "EDL0150_ln_enter_maint.jsp");
								callPage(LangPath + "EDL0150_ln_enter_maint.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}

						break;
					} else
						if (newmessage.getFormatName().equals("EDL015002")) {
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
									myRow[0] = Util.formatCell(msgDed.getE02DLISEL()); // Select
									myRow[1] = Util.formatCell(msgDed.getE02DLICDE()); // Code
									myRow[2] = Util.formatCell(msgDed.getE02DLINME()); // Company Name
									myRow[3] = Util.formatCell(msgDed.getE02DLIPLZ()); // Police
									myRow[4] = Util.formatCCY(msgDed.getE02DLIDPM()); // Deductions
									myRow[5] = Util.formatCell(msgDed.getE02DLIFAC()); // Factor

									dedList.addRow(myFlag, myRow);

								}

								newmessage = mc.receiveMessage();

							}

							ses.setAttribute("ded", dedList);
						} else
							if (newmessage.getFormatName().equals("EDL015003")) {
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
										myRow[0] = Util.formatCell(msgPmnt.getE03DLPPNU()); // Quote Number
										myRow[1] =
											Util.formatDate(
												msgPmnt.getE03DLPPD1(),
												msgPmnt.getE03DLPPD2(),
												msgPmnt.getE03DLPPD3());
										// Date
										myRow[2] = Util.formatCCY(msgPmnt.getE03DLPPRI()); // Principal
										myRow[3] = Util.formatCell(msgPmnt.getE03DLPINT()); // Interest
										myRow[4] = Util.formatCell(msgPmnt.getE03DLPIIP());
										// Interest Included in Payments

										pmntList.addRow(myFlag, myRow);

									}

									newmessage = mc.receiveMessage();

								}

								ses.setAttribute("pmnt", pmntList);
							} else
								if (newmessage.getFormatName().equals("EDL015004")) {
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
											myRow[0] = Util.formatCell(msgColl.getE04RCLACB()); // Collateral Number
											myRow[1] = Util.formatCell(msgColl.getE04RCLTYB()); // Type
											myRow[2] = Util.formatCCY(msgColl.getE04RCLCCY()); // Currency
											myRow[3] = Util.formatCell(msgColl.getE04RCLAMT()); // Amount

											collList.addRow(myFlag, myRow);

										}

										newmessage = mc.receiveMessage();

									}

									ses.setAttribute("coll", collList);
								} 
								else if (newmessage.getFormatName().equals("EDL015005")) {
								// Irregular Payments List
								// Fill List bean

								flexLog("EDL015005 Message Received");
				
								int colNum = 2;

								char sel = ' ';
								String marker = "";
								String myFlag = "";
								String myRow[] = new String[colNum];
								for (int i=0; i<colNum; i++) {
									myRow[i] = "";
								}
				
								while (true) {

									msgPmntP = (EDL015005Message)newmessage;

									marker = msgPmntP.getH05FLGMAS();

									if (marker.equals("*")) {
										break;
									}
									else {
										//Quote List
										myRow[0] =  Util.trim(msgPmntP.getE05DLPPNU());	// Quote Number
										myRow[1] =  Util.trim(msgPmntP.getE05DLPVEX());	// Principal
							
										pmntPList.addRow(myFlag, myRow);
									}
									newmessage = mc.receiveMessage();

								}

								ses.setAttribute("pmntPlus", pmntPList);
							}			
								else
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
	protected void procReqNewTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		EDL015230Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0004";

		// Send Initial data
		try {
			msgLN = (EDL015230Message) mc.getMessageRecord("EDL015230");
			msgLN.setH30USERID(user.getH01USR());
			msgLN.setH30PROGRM("EDL0150");
			msgLN.setH30TIMSYS(getTimeStamp());
			msgLN.setH30SCRCOD("01");
			msgLN.setH30OPECOD(opCode);
			msgLN.setE30DEAACC(userPO.getIdentifier());
			msgLN.setH30FLGWK1("A");
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
				//showERROR(msgError);
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
			if (newmessage.getFormatName().equals("EDL015230")) {
				try {
					msgLN = new EDL015230Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgLN = (EDL015230Message) newmessage;
				// showESD008004(msgLN);

				userPO.setIdentifier(msgLN.getE30DEAACC());
				userPO.setHeader1(msgLN.getE30DEAPRO());
				userPO.setHeader2(msgLN.getE30DEACUN());
				userPO.setHeader3(msgLN.getE30CUSNA1());
				userPO.setCurrency(msgLN.getE30DEACCY());
				userPO.setPurpose("NEW");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnGenTran", msgLN);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
						callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
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
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgLN = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgLN.setH02USR(user.getH01USR());
			msgLN.setH02PGM("EDL0150");
			msgLN.setH02TIM(""); //getTimeStamp()
			msgLN.setH02SCR("01");
			msgLN.setH02OPE(opCode);
			msgLN.setE02ACC(userPO.getIdentifier());
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
				//MOD : EMAT 9/5/2001
				// to display the page of special codes ...
				IsNotError = true;
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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
					msgLN = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000002Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_codes.jsp");
						callPage(LangPath + "EDL0150_ln_codes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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
	
	protected void procReqAdditionalsCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD001401Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgLN = (ESD001401Message) mc.getMessageRecord("ESD001401");
			msgLN.setH14USR(user.getH01USR());
			msgLN.setH14PGM("ESD0014");
			msgLN.setH14TIM(getTimeStamp()); //getTimeStamp()
			msgLN.setH14SCR("01");
			msgLN.setH14OPE(opCode);
			msgLN.setE14ACC(userPO.getIdentifier());
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
				//MOD : EMAT 9/5/2001
				// to display the page of special codes ...
				IsNotError = true;
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
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
					msgLN = new ESD001401Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD001401Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCodes", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_addcodes.jsp");
						callPage(LangPath + "EDL0150_ln_addcodes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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

		try {

			transData = new DataTransaction();

			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());
            transData.setProdtype(userPO.getType());
			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			String purpose = "";
			try {
				purpose = req.getParameter("PURPOSE");
				}
			catch (Exception e) {
				purpose = "";
				}
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=1&PURPOSE=" + purpose);

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
		ESD000006Message msgLN = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgLN = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgLN.setH06USR(user.getH01USR());
			msgLN.setH06PGM("EDL0130");
			msgLN.setH06TIM(""); //getTimeStamp()
			msgLN.setH06SCR("01");
			msgLN.setH06OPE(opCode);
			msgLN.setE06ACC(userPO.getIdentifier());
			msgLN.setE06RTP("H");
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
				//showERROR(msgError);
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
					msgLN = new ESD000006Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLN = (ESD000006Message) newmessage;
				// showESD008004(msgLN);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnTit", msgLN);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0150_ln_tit.jsp");
						callPage(LangPath + "EDL0150_ln_tit.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_new_transac.jsp");
							callPage(LangPath + "EDL0150_ln_new_transac.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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

				flexLog("Screen  Number: " + screen);

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 5);
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


                switch(screen){
					// BEGIN CD
					// Request
					case R_MAINTENANCE :
						procReqMaint(mc, msgUser, req, res, session);
						break;
					case R_SPECIAL_INST :
						procReqEspInst(mc, msgUser, req, res, session);
						break;
					case R_TITULARES :
						procReqTit(mc, msgUser, req, res, session);
						break;
					case R_EXCHANGE :
						procReqExchangeRate(mc, msgUser, req, res, session);
						break;
					case R_ACCOUNT_TITLE :
						procReqAccountTitle(mc, msgUser, req, res, session);
						break;																								
					case R_CODES :
						procReqSpecialCodes(mc, msgUser, req, res, session);
						break;
					case R_ADDCODES :
						procReqAdditionalsCodes(mc, msgUser, req, res, session);
						break;						
					case R_INT_PREP :
						procReqIntPrep(mc, msgUser, req, res, session);
						break;
					case R_OTHERS_TRANS :
						procReqOthersTransaction(mc, msgUser, req, res, session);
						break;
					case R_CANCEL_DEBIT :
						procReqCancelDebit(mc, msgUser, req, res, session);
						break;	
						// Action
					case A_SPECIAL_INST :
						procActionEspInst(mc, msgUser, req, res, session);
						break;
					case A_MAINT_TO_BEAN :
						procActionMaintPageToBeans(req, res, session);
						break;
					case A_MAINT_TO_SCK :
						procActionMaintBeansToSck(mc, msgUser, req, res, session);
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
					case A_MAINT_PMNT_PLUS :
						procActionMaintPayIrreg(msgUser, req, res, session);
						break;
					case A_TITULARES :
						procActionTit(mc, msgUser, req, res, session);
						break;
					case A_EXCHANGE :
						procActionExchangeRate(mc, msgUser, req, res, session);
						break;
					case A_CODES :
						procActionSpecialCodes(mc, msgUser, req, res, session);
						break;
					case A_ADDCODES :
						procActionAdditionalCodes(mc, msgUser, req, res, session);
						break;						
					case A_PRINT_NEW :
						procActionPrintNew(mc, msgUser, req, res, session);
						break;
					case A_NEW_TRAN :
						procActionNewTran(mc, msgUser, req, res, session);
						break;
					case A_REFRESH_CYCLE :
						//procActionRefreshCycle(mc, msgUser, req, res, session);
						break;
					
					case A_CANCEL_DEBIT :
						procActionCancelDebit(mc, msgUser, req, res, session);
						break;
						// END LN

						// BEGIN Entering
						// Request
					case R_ENTER_NEW :
						procReqEnterNew(msgUser, req, res, session);
						break;
					case R_ENTER_MAINT :
						procReqEnterMaint(msgUser, req, res, session);
						break;
						// Action 
					case A_ENTER_NEW :
						procActionEnterNew(mc, msgUser, req, res, session);
						break;
					case A_ENTER_MAINT :
						procActionEnterMaint(mc, msgUser, req, res, session);
						break;
					case A_ACCOUNT_TITLE :
						procActionAccountTitle(mc, msgUser, req, res, session);
						break;												

						// END Entering

					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 5;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	protected void procReqAccountTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgMailA = null;
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
		if ( userPO.getPurpose().equals("NEW") )
			opCode = "0001";
		else
			opCode = "0002";

		// Send Initial data
		try
		{
			msgMailA = (ESD000004Message)mc.getMessageRecord("ESD000004");
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
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
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
	
		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgMailA = new datapro.eibs.beans.ESD000004Message(); 
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgMailA = (ESD000004Message)newmessage;
				// showESD000004(msgMailA);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailA);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0150_ln_account_title.jsp");
						callPage(LangPath + "EDL0150_ln_account_title.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	

	}
		
	protected void procActionAccountTitle(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000004Message msgMailAddress = null;
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

		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgMailAddress = (ESD000004Message)mc.getMessageRecord("ESD000004");
			// msgMailAddress = (ESD000004Message)ses.getAttribute("mailA");
			msgMailAddress.setH04USR(user.getH01USR());
			msgMailAddress.setH04PGM("ESD0080");
			msgMailAddress.setH04TIM(getTimeStamp());
			msgMailAddress.setH04SCR("01");
			msgMailAddress.setH04OPE("0005");
			msgMailAddress.setE04CUN(userPO.getIdentifier());
			msgMailAddress.setE04RTP("1");
			msgMailAddress.setH04WK1("T");
			msgMailAddress.setH04WK3("2");
		
			// all the fields here
			java.util.Enumeration enu = msgMailAddress.fieldEnumeration();
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

			msgMailAddress.send();	
			msgMailAddress.destroy();
			flexLog("ESD000004 Message Sent");
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
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
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
	
		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000004")) {
				try {
					msgMailAddress = new datapro.eibs.beans.ESD000004Message();
					flexLog("ESD000004 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgMailAddress = (ESD000004Message)newmessage;
				// showESD000004(msgMailAddress);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailAddress);

				if (IsNotError) {  // There are no errors
	
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
							callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

				}
				else {  // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EDL0150_ln_account_title.jsp");
							callPage(LangPath + "EDL0150_ln_account_title.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	


	}			

}