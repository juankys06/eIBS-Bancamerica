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

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0105 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;
	protected static final int R_EXCHANGE = 5;
	protected static final int A_EXCHANGE = 6;
	protected static final int R_RENOV_MANT = 7;
	protected static final int A_RENOV_MANT = 8;
	protected static final int R_REPRICING = 9;
	protected static final int A_REPRICING = 10;
	
	protected static final int A_PRINT_FIRST = 48;	

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int A_ENTER_NEW = 200;
	
	protected static final int R_ENTER_MAINT = 300;
	protected static final int A_ENTER_MAINT = 400;
	
	protected static final int R_ENTER_INQUIRY = 500;
	protected static final int A_ENTER_INQUIRY = 600;
	
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0105() {
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

	
		int screen = R_ENTER_MAINT;

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
					case R_NEW :
						//procReqNew(mc, msgUser, req, res, session);
						break;
					case R_MAINTENANCE :
						procReqMaintenance(mc, msgUser, req, res, session);
						break;
					case R_EXCHANGE :
						procReqExchangeRate(mc, msgUser, req, res, session);
						break;
					case R_RENOV_MANT :
						procReqRenovations(mc, msgUser, req, res, session);
						break;
					case R_REPRICING :
						procReqRepricing(mc, msgUser, req, res, session);
						break;
					case R_ENTER_MAINT :
						procReqEnterMaint( msgUser, req, res, session);
						break;
					
					// Action
					case A_ENTER_NEW :
						procActionEnterNew(mc, msgUser, req, res, session);
						break;
					case A_NEW :
						procActionNew(mc, msgUser, req, res, session);
						break;
					case A_MAINTENANCE :
						procActionMaintenance(mc, msgUser, req, res, session);
						break;						
					case A_ENTER_MAINT :
						procActionEnterMaint(mc, msgUser, req, res, session);
						break;
					case A_EXCHANGE :
						procActionExchangeRate(mc, msgUser, req, res, session);
						break;
					case A_RENOV_MANT :
						procActionRenovations(mc, msgUser, req, res, session);
						break;
					case A_REPRICING :
						procActionRepricing(mc, msgUser, req, res, session);
						break;
					case A_PRINT_FIRST :
						procActionPrintNew(mc, msgUser, req, res, session);
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
		UserPos userPO = null;

		try {
			userPO = (UserPos) ses.getAttribute("userPO");
			userPO.setIdentifier(req.getParameter("E01DEAACC"));
			userPO.setProdCode(req.getParameter("E01DEAPRO"));
						
			ses.setAttribute("userPO", userPO);
			procReqNew(mc, user, req, res, ses);
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqNew(
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
		opCode = "0001";

		// Send Initial data
		try {
			msgCP = (EDL010501Message) mc.getMessageRecord("EDL010501");
			msgCP.setH01USERID(user.getH01USR());
			msgCP.setH01PROGRM("EDL0105");
			msgCP.setH01TIMSYS(getTimeStamp());
			msgCP.setH01SCRCOD("01");
			msgCP.setH01OPECOD(opCode);
			msgCP.setE01DEAPRO(userPO.getProdCode());
			
			try{
			 msgCP.setE01OFFAC1(userPO.getHeader15());
			 msgCP.setE01DEAREF(userPO.getHeader15());
			 msgCP.setH01FLGWK3("T");
			} catch (Exception e) {
			}

			try{
			 msgCP.setE01DEAOAM(userPO.getHeader16());
			 msgCP.setE01OFFAM1(userPO.getHeader16());
			} catch (Exception e) {
			}
			
			
			try {
				msgCP.setE01DEAACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
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
				flexLog("Message " + newmessage.getFormatName() + " 	.");

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
				ses.setAttribute("cdNew", msgCP);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_opening.jsp");
						callPage(LangPath + "EDL0105_cp_opening.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_enter_new.jsp");
						//callPage(LangPath + "EDL0130_cd_enter_new.jsp", req, res);	
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
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010501Message msgCP = null;
		EFT000010Message msgCPNew = null;
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
			msgCP = (EDL010501Message)mc.getMessageRecord("EDL010501");
			//msgCP = (EDL010501Message) ses.getAttribute("cdNew");
			msgCP.setH01USERID(user.getH01USR());
			msgCP.setH01PROGRM("EDL0150");
			msgCP.setH01TIMSYS(getTimeStamp());
			msgCP.setH01SCRCOD("01");
			msgCP.setH01OPECOD("0005");
			msgCP.setH01FLGWK3("U");

			// all the fields here
			java.util.Enumeration enu = msgCP.fieldEnumeration();
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

			// msgCD.send();
			mc.sendMessage(msgCP);
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

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCPNew = new EFT000010Message();
					flexLog("EFT000010 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCPNew = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCPNew.getE10DEAACC());
				userPO.setCurrency(msgCPNew.getE10DEACCY());
				userPO.setHeader3(msgCPNew.getE10CUSNA1());
				userPO.setHeader2(msgCPNew.getE10DEACUN());
				userPO.setHeader1(msgCPNew.getE10DEAPRO());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdFinish", msgCPNew);
					flexLog("About to call Page1: " + LangPath + "EDL0105_cp_confirm.jsp");
					callPage(LangPath + "EDL0105_cp_confirm.jsp", req, res);
				
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				if (newmessage.getFormatName().equals("EDL010501")) {
					try {
						msgCP = new EDL010501Message();
						flexLog("EDL010501 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCP = (EDL010501Message) newmessage;
					// showESD008004(msgCD);

					userPO.setIdentifier(msgCP.getE01DEAACC());
					userPO.setCurrency(msgCP.getE01DEACCY());
					userPO.setHeader3(msgCP.getE01CUSNA1());
					userPO.setHeader2(msgCP.getE01DEACUN());
					userPO.setHeader1(msgCP.getE01DEAPRO());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdNew", msgCP);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page2: " + LangPath + "EDL0105_cp_opening.jsp");
						callPage(LangPath + "EDL0105_cp_opening.jsp", req, res);
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
	protected void procActionPrintNew(
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
			msgCP.setH01OPECOD("0002");

			try {
				msgCP.setE01DEAACC(req.getParameter("E10DEAACC"));
			} catch (Exception e) {
				msgCP.setE01DEAACC(userPO.getIdentifier());
			}

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
				

				userPO.setIdentifier(msgCP.getE01DEAACC());
				userPO.setHeader1(msgCP.getE01DEAPRO());
				userPO.setHeader2(msgCP.getE01DEACUN());
				userPO.setHeader3(msgCP.getE01CUSNA1());
				userPO.setCurrency(msgCP.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_enter_maint.jsp");
						callPage(LangPath + "EDL0105_cp_enter_maint.jsp", req, res);
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
	
	protected void procActionEnterMaint(
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
			msgCP.setH01OPECOD("0002");
			try {
				msgCP.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
				msgCP.setE01DEAACC(userPO.getIdentifier());
			}
			try {
				msgCP.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
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
					flexLog("EDL013001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010501Message) newmessage;
				

				userPO.setIdentifier(msgCP.getE01DEAACC());
				userPO.setHeader1(msgCP.getE01DEAPRO());
				userPO.setHeader2(msgCP.getE01DEACUN());
				userPO.setHeader3(msgCP.getE01CUSNA1());
				userPO.setCurrency(msgCP.getE01DEACCY());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_enter_maint.jsp");
						callPage(LangPath + "EDL0105_cp_enter_maint.jsp", req, res);
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
	
	protected void procReqMaintenance(
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
		opCode = "0002";

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
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_enter_maint.jsp");
						callPage(LangPath + "EDL0105_cp_enter_maint.jsp", req, res);
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
	
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010501Message msgCP = null;
		EFT000010Message msgCDNew = null;
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
			msgCP = (EDL010501Message) ses.getAttribute("cdMant");
			//msgCD = (EDL013001Message)mc.getMessageRecord("EDL013001");
			msgCP.setH01USERID(user.getH01USR());
			msgCP.setH01PROGRM("EDL0130");
			msgCP.setH01TIMSYS(getTimeStamp());
			msgCP.setH01SCRCOD("01");
			msgCP.setH01OPECOD("0005");
			msgCP.setH01FLGWK3("U");

			// all the fields here
			java.util.Enumeration enu = msgCP.fieldEnumeration();
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
			mc.sendMessage(msgCP);
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

			if (newmessage.getFormatName().equals("EFT000010")) {
				try {
					msgCDNew = new EFT000010Message();
					flexLog("EFT000010 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCDNew = (EFT000010Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCDNew.getE10DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFinish", msgCDNew);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page1: " + LangPath + "EDL0105_cp_confirm.jsp");
					callPage(LangPath + "EDL0105_cp_confirm.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				if (newmessage.getFormatName().equals("EDL010501")) {
					try {
						msgCP = new EDL010501Message();
						flexLog("EDL010501 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCP = (EDL010501Message) newmessage;
					// showESD008004(msgCD);

					userPO.setIdentifier(msgCP.getE01DEAACC());

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("cdMant", msgCP);
					ses.setAttribute("userPO", userPO);

					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page1: " + LangPath + "EDL0105_cp_enter_maint.jsp");
							callPage(LangPath + "EDL0105_cp_enter_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page2: " + LangPath + "EDL0105_cp_maint.jsp");
							callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
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
	
	protected void procReqExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010506Message msgCP = null;
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
			msgCP = (EDL010506Message) mc.getMessageRecord("EDL010506");
			msgCP.setH06USERID(user.getH01USR());
			msgCP.setH06PROGRM("EDL0105");
			msgCP.setH06TIMSYS(getTimeStamp());
			msgCP.setH06SCRCOD("01");
			msgCP.setH06OPECOD(opCode);
			msgCP.setE06DEAACC(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDL010506")) {
				try {
					msgCP = new EDL010506Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010506Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRate", msgCP);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_xchg_rate.jsp");
						callPage(LangPath + "EDL0105_cp_xchg_rate.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
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
	protected void procActionExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL010506Message msgCP = null;
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
			msgCP = (EDL010506Message) ses.getAttribute("cdRate");
			//msgCD = (EDL013006Message)mc.getMessageRecord("EDL013006");
			msgCP.setH06USERID(user.getH01USR());
			msgCP.setH06PROGRM("EDL0130");
			msgCP.setH06TIMSYS(getTimeStamp());
			msgCP.setH06SCRCOD("01");
			msgCP.setH06OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgCP.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCP);
			msgCP.destroy();
			flexLog("EDL010506 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL010506")) {
				try {
					msgCP = new EDL010506Message();
					flexLog("EDL010506 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010506Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCP.getE06DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRate", msgCP);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						{
							flexLog("About to call Page: " + LangPath + "EDL0105_cp_maint.jsp");
							callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDL0105_cp_xchg_rate.jsp");
						callPage(LangPath + "EDL0105_cp_xchg_rate.jsp", req, res);
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
		opCode = "0002";

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
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_renov_options.jsp");
						callPage(LangPath + "EDL0105_cp_renov_options.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
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
	
	protected void procActionRenovations(
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

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCP = (EDL010508Message) ses.getAttribute("cdRenov");
			//msgCD = (EDL013008Message) mc.getMessageRecord("EDL013008");
			msgCP.setH08USERID(user.getH01USR());
			msgCP.setH08PROGRM("EDL0105");
			msgCP.setH08TIMSYS(getTimeStamp());
			msgCP.setH08SCRCOD("01");
			msgCP.setH08OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgCP.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCP);
			msgCP.destroy();
			flexLog("EDL010508 Message Sent");
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
					flexLog("EDL010508 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010508Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCP.getE08DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCP);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0105_cp_renov_options.jsp");
						callPage(LangPath + "EDL0105_cp_renov_options.jsp", req, res);
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
		opCode = "0002";

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
						flexLog("About to call Page3: " + LangPath + "EDL0105_cp_repricing.jsp");
						callPage(LangPath + "EDL0105_cp_repricing.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page4: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
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

	protected void procActionRepricing(
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

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCP = (EDL010512Message) ses.getAttribute("cdRep");
			//msgCD = (EDL013008Message) mc.getMessageRecord("EDL013008");
			msgCP.setH12USERID(user.getH01USR());
			msgCP.setH12PROGRM("EDL0105");
			msgCP.setH12TIMSYS(getTimeStamp());
			msgCP.setH12SCRCOD("01");
			msgCP.setH12OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgCP.fieldEnumeration();
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

			//msgCD.send();
			mc.sendMessage(msgCP);
			msgCP.destroy();
			flexLog("EDL010512 Message Sent");
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
					flexLog("EDL010512 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCP = (EDL010512Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCP.getE12DEAACC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRep", msgCP);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0105_cp_maint.jsp");
						callPage(LangPath + "EDL0105_cp_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0105_cp_repricing.jsp");
						callPage(LangPath + "EDL0105_cp_repricing.jsp", req, res);
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
	
	protected void procReqEnterMaint(
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
			userPO.setOption("CD");
			userPO.setPurpose("MAINTENANCE");
			msgCP = new EDL010501Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("cdMant", msgCP);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDL0105_cp_enter_maint.jsp");
			callPage(LangPath + "EDL0105_cp_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


}