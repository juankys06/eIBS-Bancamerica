package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECC0060 extends datapro.eibs.master.SuperServlet {

	// credit card
	static final int A_NEW  			= 200;
	static final int A_MAINT  			= 400;
	static final int A_DELETE  			= 800;

	static final int R_NEW 	 			= 100;
	static final int R_MAINT  			= 300;
	static final int R_DELETE 			= 700;

	static final int R_LIST 			= 1;
	static final int A_LIST 			= 2;
	static final int A_NEW_MAINT		= 3;
	static final int A_CHANGE_STATUS	= 4;
	static final int R_PRIMARY_CARD 	= 5;
	static final int A_PRIMARY_CARD 	= 6;
	static final int A_ASSIGN_PIN 		= 7;

	static final int R_ENTER_ADDITIONAL_CARDS = 500;
	static final int A_ENTER_ADDITIONAL_CARDS = 600;


	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECC0060() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECC0060");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterAditionalCard(
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

			String type = req.getParameter("Type");
			if (type == null) {
				type = "";
				userPO.setOption("");
			} else if (type.equals("D")) {
				userPO.setOption("RT");
				userPO.setHeader2("D");
			} else if (type.equals("C")) {
				userPO.setOption("CC");
				userPO.setHeader2("C");
			}

			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String opt = req.getParameter("OPT");
		if (opt == null) opt = "";


		if (opt.equals("4")) {
			try {
				flexLog("About to call Page: " + LangPath + "ECC0060_cc_enter_card.jsp");
				callPage(LangPath + "ECC0060_cc_enter_card.jsp?opt=" + opt,  req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else {
			try {
				flexLog("About to call Page: " + LangPath + "ECC0060_cc_enter_aditional_cards.jsp");
				callPage(LangPath + "ECC0060_cc_enter_aditional_cards.jsp?opt=" + opt,  req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}

	protected void procReqAdditionalCardsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC006001Message msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String Type = req.getParameter("Type");
		if (Type == null) Type = userPO.getHeader2();

		String opt = req.getParameter("opt");
		if (opt == null) opt = "";

		// Send Initial data
		try {
			msgList = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");

			msgList.setE01TARTYP(Type);

			try {
				msgList.setE01CCRCRA(req.getParameter("E01CCRCRA"));
			}
			catch (Exception e){
				msgList.setE01CCRCRA(userPO.getAccNum());
			}
			msgList.setE01CCRTCL("S");
			
			userPO.setAccNum(msgList.getE01CCRCRA());

			msgList.send();
			msgList.destroy();
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

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					if (!opt.equals("4")) {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_enter_aditional_cards.jsp");
						callPage(LangPath + "ECC0060_cc_enter_aditional_cards.jsp"
													 + "?opt=" + opt
													 + "&Type=" + Type,
													 req, res);
					} else {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_enter_card.jsp");
						callPage(LangPath + "ECC0060_cc_enter_card.jsp"
													 + "?opt=" + opt
													 + "&Type=" + Type,
													 req, res);
					}

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("ECC006001")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECC006001Message) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								//userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setCusNum(msgList.getE01PRICUN());
								userPO.setCusName(msgList.getE01PRINA1());
								userPO.setHeader1(msgList.getE01CCRNUM());
								userPO.setHeader2(msgList.getE01TARTYP());
								userPO.setID( msgList.getE01CCRCID() ) ;
								appList.setShowNext(false);
								break;
						} else {
							appList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCRNUM()));
								//userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setCusNum(msgList.getE01PRICUN());
								userPO.setCusName(msgList.getE01PRINA1());
								userPO.setHeader1(msgList.getE01CCRNUM());
								userPO.setHeader2(msgList.getE01TARTYP());
								userPO.setID( msgList.getE01CCRCID() ) ;
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
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
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_additional_cards_list.jsp");
						callPage(LangPath + "ECC0060_cc_additional_cards_list.jsp"
													 + "?opt=" + opt,
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

	protected void procActionPrimaryCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC006001Message ccPrim = null;
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
			ccPrim = (ECC006001Message) ses.getAttribute("msgCard");
			ccPrim.setH01USERID(user.getH01USR());
			ccPrim.setH01PROGRM("ECC0060");
			ccPrim.setH01TIMSYS(getTimeStamp());
			ccPrim.setH01SCRCOD("01");
			ccPrim.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = ccPrim.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			//msgCD.send();
			mc.sendMessage(ccPrim);
			ccPrim.destroy();
			flexLog("ECC006001 Message Sent");
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

			if (newmessage.getFormatName().equals("ECC006001")) {
				try {
					ccPrim = new ECC006001Message();
					flexLog("ECC006001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				ccPrim = (ECC006001Message) newmessage;

				userPO.setPurpose("MAINTENANCE");
				userPO.setAccNum(ccPrim.getE01CCRCRA());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCard", ccPrim);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0010?SCREEN=3");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "ECC0060_cc_primary_card.jsp");
						callPage(LangPath + "ECC0060_cc_primary_card.jsp", req, res);
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

	protected void procActionChangeStatus(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		if (req.getParameter("search").equals("A") ) {
			procChangeStatusByAccNumber( mc , user, req, res, ses  ) ;

		} else {
			procChangeStatusByCardNumber( mc , user, req, res, ses  ) ;

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
		ECC006001Message msgCC = null;
		JBObjList appList = null;

		appList = (JBObjList) ses.getAttribute("appList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}
			appList.setCurrentRow(row); 

			switch (option) {
				case 1 : // New
					long num = 0;
					if (appList.getNoResult()) {
						num = 1;
					} else {
						appList.getLastRec();
						msgCC = (ECC006001Message) appList.getRecord();
						num = Long.parseLong(msgCC.getE01CCRNUM()) + 1;
					}
					msgCC = null;
					msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
					userPO.setHeader19(num + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0060_cc_aditional_card_new.jsp?num=" + num);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCard", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_additional_cards_list.jsp?SEL=" + row + "&opt=" + option);
					break;
				case 2 : // Maintenance
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0060_cc_aditional_card.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCard", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_additional_cards_list.jsp?SEL=" + row + "&opt=" + option);
					break;
				case 3 : // Delete
					msgCC = (ECC006001Message) appList.getRecord();
					procActionDelete(row, msgCC, mc, user, req, res, ses);
					break;
				case 4 : // Change Status
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0060_cc_aditional_card_status.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCard", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_additional_cards_list.jsp?SEL=" + row + "&opt=" + option);
					break;
				case 5 : // ASSIGN PIN
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_ASSIGN_PIN");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0060_cc_aditional_card_assign_pin.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCard", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_aditional_card_assign_pin.jsp?SEL=" + row + "&opt=" + option);
					break;
				case 6 : // ADD ACCOUNT
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_ADD_ACCOUNT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0060_cc_aditional_card_asign.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCard", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_aditional_card_asign.jsp?SEL=" + row + "&opt=" + option);
					break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.

	 *	 */
	protected void procReqPrimaryCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC006001Message msgPrim = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgPrim = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgPrim.setH01USERID(user.getH01USR());
			msgPrim.setH01PROGRM("ECC0060");
			msgPrim.setH01TIMSYS(getTimeStamp());
			msgPrim.setH01SCRCOD("01");
			msgPrim.setH01OPECOD(opCode);

			try {
				msgPrim.setE01CCRCRA(userPO.getAccNum());
			} catch (Exception e) {
			}
			try {
				msgPrim.setE01CCRNUM(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgPrim.setE01CCRTCL("P");
			} catch (Exception e) {
			}

			msgPrim.send();
			msgPrim.destroy();
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

			if (newmessage.getFormatName().equals("ECC006001")) {
				try {
					msgPrim = new ECC006001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPrim = (ECC006001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCard", msgPrim);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_primary_card.jsp");
						callPage(LangPath + "ECC0060_cc_primary_card.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0010?SCREEN=3");
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

	protected void procActionDelete(
		int row,
		ECC006001Message msgOrig,
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC006001Message msgCC = null;
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
			msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECC0060");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
  			msgCC.setH01OPECOD("0009");

			try {
				msgCC.setE01CCRCRA( msgOrig.getE01CCRCRA() ) ; // userPO.getAccNum()
			}
			catch (Exception e){
				msgCC.setE01CCRCRA("");
			}
			try {
				msgCC.setE01CCRNUM(msgOrig.getE01CCRNUM());
			}
			catch (Exception e){
				msgCC.setE01CCRNUM("");
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
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&E01CCRCRA="
														+ userPO.getAccNum()
														+ "&E01TARTYP=" + userPO.getHeader2()
														+ "&opt=" + option
														+ "&E01CCRTCL=S");
				} else {
					try {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						res.sendRedirect(super.srctx + LangPath + "ECC0060_cc_additional_cards_list.jsp?ROW=" + row + "&opt=" + option);
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
		ECC006001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
		String numCta = null ;

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
			msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("EDD0060");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");

			if (option == 1){
				msgCC.setH01OPECOD("0001");
			}else if ( option == 6 ) {
				msgCC.setH01OPECOD("0101");
			}else{
				msgCC.setH01OPECOD("0005");  
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

			numCta = msgCC.getE01CCRCRA();
			userPO.setHeader23( msgCC.getE01CCRNUM() ) ;
			
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

			if (newmessage.getFormatName().equals("ECC006001")) {
				try {
					msgCC = new datapro.eibs.beans.ECC006001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (ECC006001Message) newmessage;
				// showESD008004(msgRT);

				if (IsNotError) { // There are no errors

					if( option == 40 ) { //CAMBIO DE STATUS

						flexLog("About to call:" 
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&Type=D&OPT=4"
								 );
						res.sendRedirect(super.srctx
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&Type=D&OPT=4"
								);						
						
					}else if( option == 1 ){// ASIGNACION DE TARJETAS
						
				
						/*
						flexLog("About to call:" 
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&"
								+ "&Type=D&E01CCRCRA=" + numCta );
						res.sendRedirect(super.srctx
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&"
								+ "&Type=D&E01CCRCRA=" + numCta );  */

								
						try {
							flexLog("Card Asigned. Returning to Page for Printing" );
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_new.jsp" );
							callPage(LangPath + "ECC0060_cc_aditional_card_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						
						
					} else if (option == 6) {
						
						flexLog("About to call:" 
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&"
								+ "&Type=D" );
						res.sendRedirect(super.srctx
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&"
								+ "&Type=D" );						
					}
					
					else {
						res.sendRedirect(super.srctx
						+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&E01CCRCRA="
						+ msgCC.getE01CCRCRA()
						+ "&E01TARTYP="	+ msgCC.getE01TARTYP()
						+ "&opt=" + option
						+ "&E01CCRTCL=S");						
						
					}
					
					
					
					
					
/*					res.sendRedirect(super.srctx
							+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&E01CCRCRA="
							+ msgCC.getE01CCRCRA()
							+ "&E01TARTYP="	+ msgCC.getE01TARTYP()
							+ "&opt=" + option
							+ "&E01CCRTCL=S");
*/					/*
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
							+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&E01CCRCRA="
							+ msgCC.getE01CCRCRA()
							+ "&E01TARTYP="	+ msgCC.getE01TARTYP()
							+ "&opt=" + option
							+ "&E01CCRTCL=S"
							+ "'");

					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
					*/
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgCard", msgCC);
					ses.setAttribute("userPO", userPO);
					if (option == 1) {
						try {
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_new.jsp");
							callPage(LangPath + "ECC0060_cc_aditional_card_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (option == 2) {
						try {
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card.jsp");
							callPage(LangPath + "ECC0060_cc_aditional_card.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (option == 40) { //CAMBIO DE STATUS
						try {
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_status.jsp");
							callPage(LangPath + "ECC0060_cc_aditional_card_status.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if ( option == 6 ) { //ASIGNACION BANVALOR
						try {
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_asign.jsp");
							callPage(LangPath + "ECC0060_cc_aditional_card_asign.jsp", req, res);
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
					case R_ENTER_ADDITIONAL_CARDS :
						procReqEnterAditionalCard(mc, msgUser, req, res, session);
						break;
					case A_ENTER_ADDITIONAL_CARDS :
					case R_LIST :
						procReqAdditionalCardsList(mc, msgUser, req, res, session);
						break;
					case R_PRIMARY_CARD :
						procReqPrimaryCard(mc, msgUser, req, res, session);
						break;

					case A_LIST :
						procActionAditionalCardsList(mc, msgUser, req, res, session);
						break;
					case A_NEW_MAINT:
						procActionNewMaint(mc, msgUser, req, res, session);
						break;
					case A_CHANGE_STATUS :
						procActionChangeStatus(mc, msgUser, req, res, session);
						break;
					case A_PRIMARY_CARD :
						procActionPrimaryCard(mc, msgUser, req, res, session);
						break;

					case A_ASSIGN_PIN :
						procActionAssignPIN(mc, msgUser, req, res, session);
						break ;

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

	protected void procActionAssignPIN(MessageContext mc,
			ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession session)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			ECC006001Message msgCC = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int option;
			String numCta = null ;

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");

			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
				msgCC.setH01USERID(user.getH01USR());
				msgCC.setH01PROGRM("EDD0060");
				msgCC.setH01TIMSYS(getTimeStamp());
				msgCC.setH01SCRCOD("01");
				msgCC.setH01OPECOD("0020");

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

				numCta = msgCC.getE01CCRCRA();
				
				mc.sendMessage(msgCC);
				flexLog("ECC006001 Message Sent");
				flexLog( msgCC.toString() );
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

				if (newmessage.getFormatName().equals("ECC006001")) {
					try {
						msgCC = new datapro.eibs.beans.ECC006001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCC = (ECC006001Message) newmessage;

					if (IsNotError) { // There are no errors
						/*res.sendRedirect(super.srctx
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&"
								+ "&Type=D&OPT=4");
						*/

						flexLog("About to call:" 
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&"
								+ "&Type=D&E01CCRCRA=" + numCta );
						res.sendRedirect(super.srctx
								+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&"
								+ "&Type=D&E01CCRCRA=" + numCta );
						
					
					} else { // There are errors
						flexLog("Putting java beans into the session");
						session.setAttribute("error", msgError);
						session.setAttribute("msgCard", msgCC);
						session.setAttribute("userPO", userPO);
						try {
							flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_assign_pin.jsp");
							callPage(LangPath + "ECC0060_cc_aditional_card_assign_pin.jsp", req, res);
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

	protected void procChangeStatusByCardNumber(
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
		ECC006001Message msgCC = null;
		JBObjList appList = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//int option = Integer.parseInt(req.getParameter("opt"));
		String Type = userPO.getHeader2();

		/*
		String card =  req.getParameter("E01CCRNUM");
		if (card != null && card.equals("")) {
			res.sendRedirect(super.srctx
				+ "/servlet/datapro.eibs.products.JSECC0060?SCREEN=600"
				+ "&E01TARTYP="	+ Type
				+ "&opt=4");
		} else if (card != null){
		*/
		try {
			flexLog("Send Initial Data");
			msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("EDD0060");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0002");

			msgCC.setE01CCRNUM(req.getParameter("E01CCRNUM"));

			msgCC.send();
			//mc.sendMessage(msgCC);
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

			if (newmessage.getFormatName().equals("ECC006001")) {
				try {
					msgCC = new datapro.eibs.beans.ECC006001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (ECC006001Message) newmessage;

				userPO.setAccNum(msgCC.getE01CCRCRA());
				userPO.setIdentifier(msgCC.getE01CCRNUM());
				userPO.setCusNum(msgCC.getE01PRICUN());
				userPO.setCusName(msgCC.getE01PRINA1());
				userPO.setHeader1(msgCC.getE01CCRNUM());
				//userPO.setHeader2(msgCC.getE01TARTYP());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					ses.setAttribute("msgCard", msgCC);
					
/*					try {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_additional_cards_list.jsp");
						callPage(LangPath + "ECC0060_cc_additional_cards_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
*/					
					
					try {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_aditional_card_status.jsp");
						callPage(LangPath + "ECC0060_cc_aditional_card_status.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_enter_card.jsp");
						callPage(LangPath + "ECC0060_cc_enter_card.jsp?Type=" + Type + "&OPT=4", req, res);
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

	protected void procChangeStatusByAccNumber(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC006001Message msgList = null;
		JBObjList appList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String Type = req.getParameter("Type");
		if (Type == null) {
			Type = userPO.getHeader2();
		} else {
			userPO.setHeader2(Type);
		}

		// Send Initial data
		try {
			msgList = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");

			msgList.setE01TARTYP(Type);

			try {
				msgList.setE01CCRCRA(req.getParameter("E01CCRCRA"));
			} catch (Exception e) {
				msgList.setE01CCRCRA(userPO.getAccNum());
			}
			msgList.setE01CCRTCL("S");

			msgList.send();
			msgList.destroy();
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

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);


				try {
					flexLog("About to call Page: " + LangPath + "ECC0060_cc_inq_enter_aditional_cards.jsp");
					callPage(LangPath + "ECC0060_cc_inq_enter_aditional_cards.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("ECC006001")) {
					appList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECC006001Message) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setCusNum(msgList.getE01PRICUN());
								userPO.setCusName(msgList.getE01PRINA1());
								userPO.setHeader1(msgList.getE01CCRNUM());
								//userPO.setHeader2(msgList.getE01TARTYP());
								appList.setShowNext(false);
								break;
						} else {
							appList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCRNUM()));
								userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setCusNum(msgList.getE01PRICUN());
								userPO.setCusName(msgList.getE01PRINA1());
								userPO.setHeader1(msgList.getE01CCRNUM());
								//userPO.setHeader2(msgList.getE01TARTYP());
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
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
						flexLog("About to call Page: " + LangPath + "ECC0060_cc_inq_additional_cards_list.jsp");
						callPage(LangPath + "ECC0060_sc_inq_additional_cards_list.jsp", req, res);

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

}




