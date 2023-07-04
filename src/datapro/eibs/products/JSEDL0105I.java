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

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.EDL010501Message;
import datapro.eibs.beans.EDL010506Message;
import datapro.eibs.beans.EDL010508Message;
import datapro.eibs.beans.EDL010512Message;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.EDL030701Message;
import datapro.eibs.beans.EDL016001Message;
import datapro.eibs.beans.EDL030001Message;
import datapro.eibs.beans.EDL030002Message;

import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0105I extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_INQ = 3;
	protected static final int A_INQ = 4;
	
	protected static final int R_RENOV_MANT = 7;
	protected static final int R_REPRICING = 9;
	protected static final int R_RECAP = 11;
	protected static final int R_STATEMENT = 13;
	protected static final int A_SEARCH = 15;


	// entering options
	
	protected static final int R_ENTER_INQ = 300;
	protected static final int A_ENTER_INQ = 400;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0105I() {
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

	
		int screen = R_ENTER_INQ;

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
					case R_ENTER_INQ :
						procReqEnterInq( msgUser, req, res, session);
						break;
					case R_INQ :
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case R_RENOV_MANT :
						procReqRenovations(mc, msgUser, req, res, session);
						break;
					case R_REPRICING :
						procReqRepricing(mc, msgUser, req, res, session);
						break;
					case R_RECAP :
						procReqRecap(mc, msgUser, req, res, session);
						break;
					case R_STATEMENT :
						procActionSTEnterSearch(mc,	msgUser, req, res, session);
						break;
					
					// Action
					case A_ENTER_INQ :
						procActionEnterInquiry(mc, msgUser, req, res, session);
						break;
					case A_SEARCH :
						procActionSearch(mc, msgUser, req, res, session);
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

	protected void procReqEnterInq(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		EDL010501Message msgCP = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CP");
			userPO.setPurpose("INQUIRY");
			msgCP = new EDL010501Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("cdMant", msgCP);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0105_cp_enter_inquiry.jsp");
			callPage(LangPath + "EDL0105_cp_enter_inquiry.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010501Message msgCP = null;
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
			msgCP = (EDL010501Message) mc.getMessageRecord("EDL010501");
			msgCP.setH01USERID(user.getH01USR());
			msgCP.setH01PROGRM("EDL0105");
			msgCP.setH01TIMSYS(getTimeStamp());
			msgCP.setH01SCRCOD("01");
			msgCP.setH01OPECOD("0004");
			try {
				msgCP.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
				msgCP.setE01DEAACC(userPO.getIdentifier());
			}

			msgCP.send();
			msgCP.destroy();
			flexLog("EDL010501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL010501")) {
				try {
					msgCP = new EDL010501Message();
					flexLog("EDL010501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010501Message) newmessage;
				

				userPO.setIdentifier(msgCP.getE01DEAACC());
				userPO.setProdCode(msgCP.getE01DEAPRO());
				userPO.setCusNum(msgCP.getE01DEACUN());
				userPO.setCusName(msgCP.getE01CUSNA1());
				userPO.setCurrency(msgCP.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_inq_maint.jsp");
						callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_enter_inquiry.jsp");
						callPage(LangPath + "EDL0105_cp_enter_inquiry.jsp", req, res);
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
	
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010501Message msgCP = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCP = (EDL010501Message) mc.getMessageRecord("EDL010501");
			msgCP.setH01USERID(user.getH01USR());
			msgCP.setH01PROGRM("EDL0105");
			msgCP.setH01TIMSYS(getTimeStamp());
			msgCP.setH01SCRCOD("01");
			msgCP.setH01OPECOD(opCode);
			msgCP.setE01DEAACC(userPO.getIdentifier());
			msgCP.send();
			msgCP.destroy();
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

			if (newmessage.getFormatName().equals("EDL010501")) {
				try {
					msgCP = new EDL010501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010501Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMant", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_inq_maint.jsp");
						callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_enter_inquiry.jsp");
						callPage(LangPath + "EDL0105_cp_enter_inquiry.jsp", req, res);
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
	

	

	
	protected void procReqRepricing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010512Message msgCP = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCP = (EDL010512Message) mc.getMessageRecord("EDL010512");
			msgCP.setH12USERID(user.getH01USR());
			msgCP.setH12PROGRM("EDL0105");
			msgCP.setH12TIMSYS(getTimeStamp());
			msgCP.setH12SCRCOD("01");
			msgCP.setH12OPECOD(opCode);
			msgCP.setE12DEAACC(userPO.getIdentifier());
			msgCP.send();
			msgCP.destroy();
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

			if (newmessage.getFormatName().equals("EDL010512")) {
				try {
					msgCP = new EDL010512Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010512Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRep", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_inq_repricing.jsp");
						callPage(LangPath + "EDL0105_cp_inq_repricing.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_inq_maint.jsp");
						callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
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
	
			

	protected void procReqRenovations(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010508Message msgCP = null;
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
		opCode = "0004";

		// Send Initial data
		try {
			msgCP = (EDL010508Message) mc.getMessageRecord("EDL010508");
			msgCP.setH08USERID(user.getH01USR());
			msgCP.setH08PROGRM("EDL0105");
			msgCP.setH08TIMSYS(getTimeStamp());
			msgCP.setH08SCRCOD("01");
			msgCP.setH08OPECOD(opCode);
			msgCP.setE08DEAACC(userPO.getIdentifier());
			msgCP.send();
			msgCP.destroy();
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

			if (newmessage.getFormatName().equals("EDL010508")) {
				try {
					msgCP = new EDL010508Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010508Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_inq_renov_options.jsp");
						callPage(LangPath + "EDL0105_cp_inq_renov_options.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_inq_maint.jsp");
						callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
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

	protected void procReqRecap(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL030701Message msgList = null;
		ELEERRMessage msgError = null;
		JBObjList objList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String Nexte ="";
		// Send Initial data
		try {
			msgList = (EDL030701Message) mc.getMessageRecord("EDL030701");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL0307");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");

			try { //Account				
				msgList.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
			}	
					
			msgList.send();
			msgList.destroy();
			flexLog("EDL030701 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0105_cp_inq_maint.jsp");
					callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}


			} else
				if (newmessage.getFormatName().equals("EDL030701")) {

						try {
							objList =
								(datapro.eibs.beans.JBObjList) Beans.instantiate(
									getClass().getClassLoader(),
									"datapro.eibs.beans.JBObjList");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}
	
						boolean firstTime=true;
						String marker = "";

						while (true) {
	
							msgList = (EDL030701Message) newmessage;
							
							marker = msgList.getE01INDOPE();
							if (marker.equals("*")) {

								userPO.setHeader1(msgList.getE01CUSNAM());
								userPO.setHeader2(msgList.getE01DEAACC());
								userPO.setHeader3(msgList.getE01DEAPRI());
								userPO.setHeader4(msgList.getE01MKTADJ());	
								userPO.setHeader5(msgList.getE01UNAMOR());
								userPO.setHeader6(msgList.getE01BOOKVL());
								userPO.setHeader7(msgList.getE01DEAFEE());
								userPO.setHeader8(msgList.getE01DEAIAL());
								userPO.setHeader9(msgList.getE01DEARRT());
								userPO.setHeader10(msgList.getE01BIDPRC());
								userPO.setHeader11(msgList.getE01DEAMVL());
								userPO.setHeader12(msgList.getE01DEAMD1());
								userPO.setHeader13(msgList.getE01DEAMD2());
								userPO.setHeader14(msgList.getE01DEAMD3());
								userPO.setHeader15(msgList.getE01DEANX1());
								userPO.setHeader16(msgList.getE01DEANX2());
								userPO.setHeader17(msgList.getE01DEANX3());
								userPO.setHeader18(msgList.getE01DEARTE());
								userPO.setHeader19(msgList.getE01DEAFTB());
								userPO.setHeader20(msgList.getE01DEAFTY());
								userPO.setHeader21(msgList.getE01DEAFRT());
								userPO.setHeader22(msgList.getE01TOTRTE());
								
								objList.setShowNext(false);
								break;
							} else {
															
								objList.addRow(msgList);
								
								if (marker.equals("+")) {
									objList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}
	
						flexLog("Putting java beans into the session");
						ses.setAttribute("EDL030701Help", objList);
						ses.setAttribute("userPO", userPO);
	
						try {
							flexLog("About to call Page: " + LangPath + "EDL0105_cp_inq_recap.jsp");
							callPage(LangPath + "EDL0105_cp_inq_recap.jsp", req, res);
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
	protected void procActionSTEnterSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgRT = null;
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
			msgRT = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDL0160");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			try {
				msgRT.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgRT.setE01DEAACC("");
			}

			msgRT.send();
			msgRT.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0105_cp_inq_maint.jsp");
					callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgRT = new datapro.eibs.beans.EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDL016001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				flexLog("Calling Request");
				try {
					flexLog(
						"About to call Page: " + LangPath + "EDL0105_cp_inq_st_selection.jsp");
					callPage(LangPath + "EDL0105_cp_inq_st_selection.jsp", req, res);
				} catch (Exception e) {
					e.printStackTrace();
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
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDL030001Message msgSearch = null;
		EDL030001Message msgList = null;
		EDL030002Message msgHeader = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDL030001Message) mc.getMessageRecord("EDL030001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDL0300");
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
							+ "EDL0105_cp_inq_maint.jsp");
					callPage(LangPath + "EDL0105_cp_inq_maint.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDL030002")) {
				try {
					msgHeader = new datapro.eibs.beans.EDL030002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgHeader = (EDL030002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("stBalances", msgHeader);

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDL030001")) {

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

					String strPrincipal = "";
					String strInterest = "";
					String strMora = "";
					String strOther = "";
					String trIType = "";
					String trPType = "";
					String trLType = "";
					String trOType = "";

					java.math.BigDecimal principal =
						new java.math.BigDecimal(0);
					java.math.BigDecimal interest = new java.math.BigDecimal(0);
					java.math.BigDecimal mora = new java.math.BigDecimal(0);
					java.math.BigDecimal other = new java.math.BigDecimal(0);

					String DT1 = "";
					String DT2 = "";
					String DT3 = "";

					while (true) {

						msgList = (EDL030001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(
									Integer.parseInt(msgList.getE01NUMREC()));
								chk = "checked";
							} else {
								chk = "";
							}

							if (msgList.getE01TRAAPC().equals("P")) {
								if (msgList.getE01TRADCC().equals("0")) {
									strPrincipal =
										Util.fcolorCCY(msgList.getE01TRAAMT());
									principal =
										principal.subtract(
											msgList.getBigDecimalE01TRAAMT());
									trOType = "&nbsp;";
									trIType = "&nbsp;";
									trPType = "&nbsp;";
									trLType = "&nbsp;";

								} else if (
									msgList.getE01TRADCC().equals("5")) {
									strPrincipal =
										"  "
											+ Util.fcolorCCY(
												msgList.getE01TRAAMT());
									trPType = "CR";
									trIType = "&nbsp;";
									trOType = "&nbsp;";
									trLType = "&nbsp;";

									principal =
										principal.add(
											msgList.getBigDecimalE01TRAAMT());
								}
								strInterest = "&nbsp;";
								strMora = "&nbsp;";
								strOther = "&nbsp;";

							} else if (msgList.getE01TRAAPC().equals("I")) {
								if (msgList.getE01TRADCC().equals("0")) {
									strInterest =
										Util.fcolorCCY(msgList.getE01TRAAMT());
									interest =
										interest.subtract(
											msgList.getBigDecimalE01TRAAMT());
									trOType = "&nbsp;";
									trIType = "&nbsp;";
									trPType = "&nbsp;";
									trLType = "&nbsp;";

								} else if (
									msgList.getE01TRADCC().equals("5")) {
									strInterest =
										"  "
											+ Util.fcolorCCY(
												msgList.getE01TRAAMT());
									interest =
										interest.add(
											msgList.getBigDecimalE01TRAAMT());
									trIType = "CR";
									trPType = "&nbsp;";
									trOType = "&nbsp;";
									trLType = "&nbsp;";

								}
								strPrincipal = "&nbsp;";
								strMora = "&nbsp;";
								strOther = "&nbsp;";

							} else if (msgList.getE01TRAAPC().equals("L")) {
								if (msgList.getE01TRADCC().equals("0")) {
									strMora =
										Util.fcolorCCY(msgList.getE01TRAAMT());
									mora =
										mora.subtract(
											msgList.getBigDecimalE01TRAAMT());
									trOType = "&nbsp;";
									trIType = "&nbsp;";
									trPType = "&nbsp;";
									trLType = "&nbsp;";

								} else if (
									msgList.getE01TRADCC().equals("5")) {
									strMora =
										"  "
											+ Util.fcolorCCY(
												msgList.getE01TRAAMT());
									mora =
										mora.add(
											msgList.getBigDecimalE01TRAAMT());
									trLType = "CR";
									trIType = "&nbsp;";
									trOType = "&nbsp;";
									trPType = "&nbsp;";

								}
								strPrincipal = "&nbsp;";
								strInterest = "&nbsp;";
								strOther = "&nbsp;";

							} else if (msgList.getE01TRAAPC().equals("O")) {
								if (msgList.getE01TRADCC().equals("0")) {
									strOther =
										Util.fcolorCCY(msgList.getE01TRAAMT());
									other =
										other.subtract(
											msgList.getBigDecimalE01TRAAMT());
									trOType = "&nbsp;";
									trIType = "&nbsp;";
									trPType = "&nbsp;";
									trLType = "&nbsp;";

								} else if (
									msgList.getE01TRADCC().equals("5")) {
									strOther =
										"  "
											+ Util.fcolorCCY(
												msgList.getE01TRAAMT());
									other =
										other.add(
											msgList.getBigDecimalE01TRAAMT());
									trOType = "CR";
									trIType = "&nbsp;";
									trPType = "&nbsp;";
									trLType = "&nbsp;";

								}
								strPrincipal = "&nbsp;";
								strInterest = "&nbsp;";
								strMora = "&nbsp;";
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
								myRow.append(
									"<TD NOWRAP ALIGN=CENTER>"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23())
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ Util.formatDate(
											msgList.getE01DATE11(),
											msgList.getE01DATE12(),
											msgList.getE01DATE13())
										+ "</TD>");
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
							}
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\">"
									+ Util.formatDate(
										msgList.getE01TRAPD1(),
										msgList.getE01TRAPD2(),
										msgList.getE01TRAPD3())
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\">"
									+ Util.formatCell(msgList.getE01TRACDE())
									+ "</TD>");
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
								"<TD NOWRAP ALIGN=RIGHT>"
									+ Util.formatCell(strPrincipal)
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=LEFT>"
									+ Util.formatCell(trPType)
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=RIGHT>"
									+ Util.formatCell(strInterest)
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=LEFT>"
									+ Util.formatCell(trIType)
									+ "</TD>");
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:GetBatchDet('"
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
							//myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01TRAACR()) + "</TD>");
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader19(Util.fcolorCCY(principal.toString()));
					userPO.setHeader20(Util.fcolorCCY(interest.toString()));

					flexLog("Putting java beans into the session");
					ses.setAttribute("cifList", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0105_cp_inq_st_list_fp.jsp");
						callPage(LangPath + "EDL0105_cp_inq_st_list_fp.jsp", req, res);
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
}