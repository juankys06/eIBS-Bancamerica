package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.*;
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

public class JSECC0060A extends datapro.eibs.master.SuperServlet {

	// credit card 
	protected static final int R_INQ_SEC_CARD = 3;
	protected static final int R_PRIMARY_CARD = 5;

	protected static final int R_SEC_CARD = 13;
	protected static final int A_SEC_CARD = 14;
	protected static final int R_SEC_CARD_DEBIT = 15;
	protected static final int A_SEC_CARD_DEBIT = 16;	
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECC0060A() {
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

	protected void procReqSecondaryCardsList(
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

		// Send Initial data
		try {
			msgList = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setH01FLGWK1("A");
			
			try {
				msgList.setE01TARTYP(userPO.getHeader2());
			}
			catch (Exception e){
				msgList.setE01TARTYP("");	
			}
		
			try {
				msgList.setE01CCRCRA(userPO.getAccNum());
			}
			catch (Exception e){
				msgList.setE01CCRCRA("");
			}
			try {
				msgList.setE01CCRTCL("S");
			} catch (Exception e) {
			}		
			
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
					flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_basic.jsp");
					callPage(LangPath + "ECC0140_cc_ap_basic.jsp", req, res);

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
								userPO.setHeader2(msgList.getE01TARTYP());				
							
							appList.setShowNext(false);
							break;
						} else {
							
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCRNUM()));
								userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setCusNum(msgList.getE01PRICUN());
								userPO.setCusName(msgList.getE01PRINA1());
								userPO.setHeader1(msgList.getE01CCRNUM());
								userPO.setHeader2(msgList.getE01TARTYP());				
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							
							appList.addRow(msgList);
							
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
						flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_additional_cards_list.jsp");
						callPage(LangPath + "ECC0140_cc_ap_additional_cards_list.jsp", req, res);

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

	protected void procActionSecondaryCardsList(
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

				case 2 : // Inquiry
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0140_cc_ap_aditional_card.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCC", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0140_cc_ap_additional_cards_list.jsp?SEL=" + row + "&opt=" + option);
					break;
			}

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

			int screen = R_PRIMARY_CARD;

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
					case R_INQ_SEC_CARD :
						procReqSecondaryCard(mc, msgUser, req, res, session);
						break;
					case R_PRIMARY_CARD :
						procReqPrimaryCard(mc, msgUser, req, res, session);
						break;
					case R_SEC_CARD :
						procReqSecondaryCardsList(mc, msgUser, req, res, session);
						break;					
					case R_SEC_CARD_DEBIT :
						procReqSecondaryCardsListDebit(mc, msgUser, req, res, session);
						break;					
												
					// Action
					case A_SEC_CARD :
						procActionSecondaryCardsList(mc, msgUser, req, res, session);
						break;	
					case A_SEC_CARD_DEBIT :
						procActionSecondaryCardsListDebit(mc, msgUser, req, res, session);
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

	protected void procReqSecondaryCardsListDebit(
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

		// Send Initial data
		try {
			msgList = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setH01FLGWK1("A");
			
			try {
				msgList.setE01TARTYP("D");
			}
			catch (Exception e){
				msgList.setE01TARTYP("");	
			}
		
			try {
				msgList.setE01CCRCRA(userPO.getAccNum());
			}
			catch (Exception e){
				msgList.setE01CCRCRA("");
			}
			try {
				msgList.setE01CCRTCL("S");
			} catch (Exception e) {
			}		
			
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
					flexLog("About to call Page: " + LangPath + "EDD1000_rt_ap_basic.jsp");
					callPage(LangPath + "EDD1000_rt_ap_basic.jsp", req, res);

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
							userPO.setHeader5(msgList.getE01CCRCRA());
							userPO.setHeader6(msgList.getE01PRICUN());
							userPO.setHeader7(msgList.getE01PRINA1());
							userPO.setHeader8(msgList.getE01CCRNUM());
						
							appList.setShowNext(false);
							break;
						} else {
							
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCRNUM()));
								userPO.setAccNum(msgList.getE01CCRCRA());
								userPO.setIdentifier(msgList.getE01CCRCRA());
								userPO.setHeader5(msgList.getE01CCRCRA());
								userPO.setHeader6(msgList.getE01PRICUN());
								userPO.setHeader7(msgList.getE01PRINA1());
								userPO.setHeader8(msgList.getE01CCRNUM());
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							appList.addRow(msgList);
							
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
						flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_additional_cards_list_debit.jsp");
						callPage(LangPath + "ECC0140_cc_ap_additional_cards_list_debit.jsp", req, res);

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

	protected void procActionSecondaryCardsListDebit(
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

				case 2 : // Inquiry
					msgCC = (ECC006001Message) appList.getRecord();
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(super.webAppPath + LangPath + "ECC0140_cc_ap_aditional_card_debit.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgCC", msgCC);
					res.sendRedirect(super.srctx + LangPath + "ECC0140_cc_ap_additional_cards_list_debit.jsp?SEL=" + row + "&opt=" + option);
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
		userPO.setPurpose("APPROVAL_INQ");
		
		String opCode = null;
		opCode = "0004";

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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_basic.jsp");
					callPage(LangPath + "ECC0140_cc_ap_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				if (newmessage.getFormatName().equals("ECC006001")) {
					try {
						msgPrim = new ECC006001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}
	
					msgPrim = (ECC006001Message) newmessage;
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("msgCard", msgPrim);
					ses.setAttribute("userPO", userPO);
	
					try {
						flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_primary_card.jsp");
						callPage(LangPath + "ECC0140_cc_ap_primary_card.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqSecondaryCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESS0030DSMessage msgUser = null;
		ELEERRMessage msgError = null;
		ECC006001Message msgCC = null;
		JBObjList appList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		int option = 0;
		int row = -1;
		
		appList = (JBObjList) ses.getAttribute("appList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgCC = (ECC006001Message) mc.getMessageRecord("ECC006001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ECC0060");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0002");
			
			msgCC.setE01CCRCRA(userPO.getAccNum());
			msgCC.setE01CCRNUM(userPO.getIdentifier());
			
			msgCC.send();
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			} 
			
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ECC006001")) {
					try {
						msgCC = new ECC006001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}
	
					msgCC = (ECC006001Message) newmessage;
	
					userPO.setHeader2(msgCC.getE01TARTYP());
					userPO.setHeader5(msgCC.getE01CCRCRA());
					userPO.setHeader6(msgCC.getE01PRICUN());
					userPO.setHeader7(msgCC.getE01PRINA1());
					userPO.setHeader8(msgCC.getE01CCRNUM());
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("msgCC", msgCC);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "ECC0140_cc_ap_aditional_card.jsp");
							callPage(LangPath + "ECC0140_cc_ap_aditional_card.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ECC0140_approval_list.jsp");
							callPage(LangPath + "ECC0140_approval_list.jsp", req, res);
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
	
}