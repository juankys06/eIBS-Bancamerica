package datapro.eibs.forex;

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
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ECN0030DSMessage;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.EDL016004Message;
import datapro.eibs.beans.EDL016001Message;
import datapro.eibs.beans.EDL016002Message;
import datapro.eibs.beans.EDL016007Message;
import datapro.eibs.beans.EDL029001Message;
import datapro.eibs.beans.EDL030502Message;
import datapro.eibs.beans.EFT000010Message;
import datapro.eibs.beans.ELD000001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD000008Message;
import datapro.eibs.beans.ESD071111Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EDL030701Message;
import datapro.eibs.beans.EDL030001Message;
import datapro.eibs.beans.EDL030002Message;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0130IT extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	//inquiry options
	protected static final int R_INQUIRY = 13;
	protected static final int R_CODES_INQ = 27;
	protected static final int R_SPECIAL_INST_INQ = 31;
	protected static final int R_BASIC_INQ = 41;
	protected static final int R_RECAP = 56;
	protected static final int R_STATEMENT = 57;
	protected static final int A_STATEMENT = 58;
	protected static final int A_ENTER_INQUIRY = 600;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0130IT() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0130IT");

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
	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0160");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
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

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_enter_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setBank(msgCD.getE01DEABNK());
				userPO.setBranch(msgCD.getE01DEABRN());
				userPO.setCusNum(msgCD.getE01DEACUN());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(userPO.getCusNum());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setHeader23(msgCD.getE01DEARTB());
				userPO.setCurrency(msgCD.getE01DEACCY());
				userPO.setOfficer(msgCD.getE01DEAOFC() + " - " + msgCD.getE01DSCOFC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdInquiry", msgCD);
				ses.setAttribute("error", msgError);

				procReqMaintPay(user, req, res, ses);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
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
	protected void procReqEspInstInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
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

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000005")) {
				try {
					msgCD = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdInst", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_special_inst.jsp");
					callPage(LangPath + "EDL0160T_cd_special_inst.jsp", req, res);
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
	protected void procReqInqBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016002Message msgCD = null;
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
			msgCD = (EDL016002Message) mc.getMessageRecord("EDL016002");
			msgCD.setH02USERID(user.getH01USR());
			msgCD.setH02PROGRM("EDL0160");
			msgCD.setH02TIMSYS(getTimeStamp());
			msgCD.setH02SCRCOD("01");
			msgCD.setH02OPECOD(opCode);
			msgCD.setE02DEAACC(userPO.getIdentifier());
			msgCD.setE02DEAACD("CD");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016002")) {
				try {
					msgCD = new EDL016002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdMant", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_basic.jsp");
					callPage(LangPath + "EDL0160T_cd_basic.jsp", req, res);
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
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0160");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setE01DEAACC(userPO.getIdentifier());
			msgCD.setE01DEAACD("CD");
			msgCD.send();
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_enter_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_enter_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD = new EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdInquiry", msgCD);
				ses.setAttribute("error", msgError);

				procReqMaintPay(user, req, res, ses);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
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
	protected void procReqSpecialCodesInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new ELEERRMessage();
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
					flexLog("About to call Page: " + LangPath + "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgCD = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("cdCodes", msgCD);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_codes.jsp");
					callPage(LangPath + "EDL0160T_cd_codes.jsp", req, res);
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

	protected void procReqMaintPay(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageContext mc = null;
		Socket s = null;
		MessageRecord newmessage = null;
		EDL016004Message msgPmnt = null;
		JBListRec pmntList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Opennig Socket Connection");
			s = new Socket(super.hostIP, getInitSocket(req) + 1);
			s.setSoTimeout(super.sckTimeOut);
			mc =
				new MessageContext(
					new DataInputStream(new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
					"datapro.eibs.beans");
		} catch (Exception e) {
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
			return;
		}

		// Send Initial data
		try {
			msgPmnt = (EDL016004Message) mc.getMessageRecord("EDL016004");
			msgPmnt.setH04USERID(user.getH01USR());
			msgPmnt.setH04PROGRM("EDL0160");
			msgPmnt.setH04TIMSYS(getTimeStamp());
			msgPmnt.setH04SCRCOD("01");
			msgPmnt.setH04OPECOD("0002");
			try {
				msgPmnt.setE04DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
				msgPmnt.setE04DEAACC("0");
			}

			msgPmnt.send();
			msgPmnt.destroy();
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
			int colNum = 4;
			pmntList = new JBListRec();
			pmntList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else if (newmessage.getFormatName().equals("EDL016004")) {
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

					msgPmnt = (EDL016004Message) newmessage;

					marker = msgPmnt.getH04FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myRow[0] = Util.formatCell(msgPmnt.getE04DLPPNU()); // Quote Number
						myRow[1] =
							Util.formatDate(
								msgPmnt.getE04DLPPD1(),
								msgPmnt.getE04DLPPD2(),
								msgPmnt.getE04DLPPD3());
						// Date
						myRow[2] = Util.formatCCY(msgPmnt.getE04DLPPRI()); // Principal
						myRow[3] = Util.formatCell(msgPmnt.getE04DLPINT()); // Interest

						pmntList.addRow(myFlag, myRow);

					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("error", msgError);
				ses.setAttribute("pmnt", pmntList);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			s.close();
		} catch (Exception e) {
			flexLog("Error: " + e);
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

			int screen = 600;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opening Socket Connection");
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

					//Inquiry Options
					case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case R_CODES_INQ :
						procReqSpecialCodesInq(mc, msgUser, req, res, session);
						break;
					case R_SPECIAL_INST_INQ :
						procReqEspInstInq(mc, msgUser, req, res, session);
						break;
					case R_BASIC_INQ :
						procReqInqBasic(mc, msgUser, req, res, session);
						break;
					case R_RECAP :
						procReqRecap(mc, msgUser, req, res, session);
						break;
					case R_STATEMENT :
						procActionSTEnterSearch(mc,	msgUser, req, res, session);
						break;
					case A_ENTER_INQUIRY :
						procActionEnterInquiry(mc, msgUser, req, res, session);
						break;
					case A_STATEMENT :
						procActionSearch(mc, msgUser, req, res, session);
						break;
				
						// END Entering

					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				  }
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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
	protected void showEDL013001(EDL013001Message m) {
		if (logType != NONE) {

			flexLog("Client Information received (EDL013001).");

			flexLog("User ID: " + m.getH01USERID());
			flexLog("Program: " + m.getH01PROGRM());
			flexLog("Time date: " + m.getH01TIMSYS());
			flexLog("Screen code: " + m.getH01SCRCOD());
			flexLog("Operate code: " + m.getH01OPECOD());
			flexLog("More records" + m.getH01FLGMAS());
			flexLog("Flag 1: " + m.getH01FLGWK1());
			flexLog("Flag 2: " + m.getH01FLGWK2());
			flexLog("Flag 3: " + m.getH01FLGWK3());

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
					flexLog("About to call Page: " + LangPath + "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
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
						EDL030701Message msgRec = new EDL030701Message();
					
						while (true) {
	
							msgList = (EDL030701Message) newmessage;
							
							marker = msgList.getE01INDOPE();
							if (marker.equals("*")) {
								
								msgRec.setE01CUSNAM(msgList.getE01CUSNAM());
								msgRec.setE01DEAACC(msgList.getE01DEAACC());
								msgRec.setE01DEAPRI(msgList.getE01DEAPRI());
								msgRec.setE01MKTADJ(msgList.getE01MKTADJ());	
								msgRec.setE01UNAMOR(msgList.getE01UNAMOR());
								msgRec.setE01BOOKVL(msgList.getE01BOOKVL());
								msgRec.setE01DEAFEE(msgList.getE01DEAFEE());
								msgRec.setE01DEAIAL(msgList.getE01DEAIAL());
								msgRec.setE01DEARRT(msgList.getE01DEARRT());
								msgRec.setE01BIDPRC(msgList.getE01BIDPRC());
								msgRec.setE01DEAMVL(msgList.getE01DEAMVL());
								msgRec.setE01DEAMD1(msgList.getE01DEAMD1());
								msgRec.setE01DEAMD2(msgList.getE01DEAMD2());
								msgRec.setE01DEAMD3(msgList.getE01DEAMD3());
								msgRec.setE01DEANX1(msgList.getE01DEANX1());
								msgRec.setE01DEANX2(msgList.getE01DEANX2());
								msgRec.setE01DEANX3(msgList.getE01DEANX3());
								msgRec.setE01DEARTE(msgList.getE01DEARTE());
								msgRec.setE01DEAFTB(msgList.getE01DEAFTB());
								msgRec.setE01DEAFTY(msgList.getE01DEAFTY());
								msgRec.setE01DEAFRT(msgList.getE01DEAFRT());
								msgRec.setE01TOTRTE(msgList.getE01TOTRTE());
								
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
						ses.setAttribute("msgRec", msgRec);
						ses.setAttribute("userPO", userPO);
	
						try {
							flexLog("About to call Page: " + LangPath + "EDL0160T_cp_inq_recap.jsp");
							callPage(LangPath + "EDL0160T_cp_inq_recap.jsp", req, res);
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
							+ "EDL0160T_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160T_cd_inquiry.jsp", req, res);
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
						"About to call Page: " + LangPath + "EDL0160T_st_selection.jsp");
					callPage(LangPath + "EDL0160T_st_selection.jsp", req, res);
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
							+ "EDL0160T_st_selection.jsp");
					callPage(LangPath + "EDL0160T_st_selection.jsp", req, res);

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
								+ "EDL0160T_st_list_fp.jsp");
						callPage(LangPath + "EDL0160T_st_list_fp.jsp", req, res);
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