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

public class JSECC0010I extends datapro.eibs.master.SuperServlet {

	// credit card 

	protected static final int R_INQUIRY = 3;

	protected static final int R_SPECIAL_CODES = 7;
	protected static final int R_SPECIAL_INST = 9;
	
	protected static final int R_ENTER_INQUIRY = 100;
	protected static final int A_ENTER_INQUIRY = 200;
			
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECC0010I() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECC0010I");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterInquiry(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos	userPO = null;	

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();

			userPO.setOption("CC");
			userPO.setHeader2("C");
		
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		try {
			flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_enter_cards.jsp");
			callPage(LangPath + "ECC0010_cc_inq_enter_cards.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
/**
 * This method was created in VisualAge.
*/
protected void procActionEnterInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECC001001Message msgCC = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex); 
	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	String search = req.getParameter("search");
	if (search.equals("A")) {
		try {
			userPO.setAccNum(req.getParameter("E01CCMACC"));
		} catch (Exception e) {
			userPO.setAccNum("");
		}
		ses.setAttribute("userPO", userPO);
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0010I?SCREEN=3");
	} else {
		try {
			userPO.setIdentifier(req.getParameter("E01CCMPCN"));	
		} catch (Exception e) {
			userPO.setIdentifier("");
		}
		ses.setAttribute("userPO", userPO);
		//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0060I?SCREEN=4");
		procReqInquiry( mc, user, req, res, ses );
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
	ECC001001Message msgCC = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("INQUIRY");

	String opCode = null;
	opCode = "0004";

	// Send Initial data
	try {
		msgCC = (ECC001001Message) mc.getMessageRecord("ECC001001");
		msgCC.setH01USERID(user.getH01USR());
		msgCC.setH01PROGRM("ECC0010");
		msgCC.setH01TIMSYS(getTimeStamp());
		msgCC.setH01SCRCOD("01");
		msgCC.setH01OPECOD(opCode);
		
		msgCC.setE01CCMACC(userPO.getAccNum());
		msgCC.setE01CCMPCN(userPO.getIdentifier());
		
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
			
			try {
				flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_enter_cards.jsp");
				callPage(LangPath + "ECC0010_cc_inq_enter_cards.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}			
		} else {
			if (newmessage.getFormatName().equals("ECC001001")) {
				try {
					msgCC = new ECC001001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgCC = (ECC001001Message) newmessage;

				userPO.setBank(msgCC.getE01CCMBNK());
				userPO.setAccNum(msgCC.getE01CCMACC());
				userPO.setIdentifier(msgCC.getE01CCMPCN());
				userPO.setCusNum(msgCC.getE01CCMCUN());
				userPO.setCusName(msgCC.getE01CUSNA1());
				userPO.setHeader1(msgCC.getE01CCMPCN());
				userPO.setHeader5(msgCC.getE01CCMPRO());					
				userPO.setCurrency(msgCC.getE01CCMCCY());
					
				flexLog("Putting java beans into the session");
				ses.setAttribute("ccNew", msgCC);
				ses.setAttribute("userPO", userPO);
	
				try {
					flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_card.jsp");
					callPage(LangPath + "ECC0010_cc_inq_card.jsp", req, res);
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

	// Send Initial data
	try {
		msgLN = (ESD000002Message) mc.getMessageRecord("ESD000002");
		msgLN.setH02USR(user.getH01USR());
		msgLN.setH02PGM("ECC0010");
		msgLN.setH02TIM(""); //getTimeStamp()
		msgLN.setH02SCR("01");
		msgLN.setH02OPE("0004");
		msgLN.setE02ACC(userPO.getAccNum());
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
			IsNotError = true;
			flexLog("IsNotError = " + IsNotError);
			//showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_card.jsp");
				callPage(LangPath + "ECC0010_cc_inq_card.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}			
		} else {	
			if (newmessage.getFormatName().equals("ESD000002")) {
				try {
					msgLN = new ESD000002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgLN = (ESD000002Message) newmessage;
				// showESD008004(msgLN);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("lnCodes", msgLN);
				ses.setAttribute("userPO", userPO);
	
				try {
					flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_codes.jsp");
					callPage(LangPath + "ECC0010_cc_inq_codes.jsp", req, res);
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

protected void procReqEspInst(
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
		msgCD.setH05PGM("ECC0010");
		msgCD.setH05TIM(getTimeStamp());
		msgCD.setH05SCR("01");
		msgCD.setH05OPE(opCode);
		msgCD.setE05ACC(userPO.getAccNum());
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
				flexLog("About to call Page: " + LangPath + "ECC0010_cc_inq_card.jsp");
				callPage(LangPath + "ECC0010_cc_inq_card.jsp", req, res);
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

			flexLog("Putting java beans into the session");
			ses.setAttribute("cdInst", msgCD);
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: "	+ LangPath	+ "ECC0010_cc_inq_special_inst.jsp");
				callPage(LangPath + "ECC0010_cc_inq_special_inst.jsp", req, res);
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

					case R_ENTER_INQUIRY :
						procReqEnterInquiry(msgUser, req, res, session);
						break;	
					case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case R_SPECIAL_CODES :
						procReqSpecialCodes(mc, msgUser, req, res, session);
						break;						
					case R_SPECIAL_INST :
						procReqEspInst(mc, msgUser, req, res, session);
						break;	
																	
					// Action
					case A_ENTER_INQUIRY :
						procActionEnterInquiry(mc, msgUser, req, res, session);
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


}