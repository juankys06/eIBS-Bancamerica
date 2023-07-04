package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD0180 extends datapro.eibs.master.SuperServlet {

	// options
	protected static final int R_SUSP 			= 1;
	protected static final int A_SUSP 			= 2;
	protected static final int R_SUSP_DET 		= 3;
	protected static final int A_SUSP_DET 		= 4;
	protected static final int R_SUSP_ACLARAR 	= 5;
	protected static final int A_SUSP_ACLARAR 	= 6;
	protected static final int R_OF_SUSP 			= 7;
	protected static final int A_OF_SUSP 			= 8;
	protected static final int A_OF_SUSP_DET 		= 9;
	protected static final int A_OF_SUSP_ACLARAR 	= 10;
	protected static final int A_CD_SUSP_DET 		= 11;
	
	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_ENTER 			= 200;
	protected static final int R_OF_ENTER 		= 300;
	protected static final int A_OF_ENTER 		= 400;
	protected static final int R_CD_ENTER 		= 500;
	protected static final int A_CD_ENTER 		= 600;
	protected static final int R_CD_SUSP_INQ 		= 700;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0180() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionEnterStopPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			userPO.setIdentifier(req.getParameter("E01STPACC"));

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			flexLog("Calling Request");
			procReqStopPayList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionStopPayDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
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
			msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0180");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			if (option == 1)
				msgRT.setH01OPECOD("0001");
			else
				msgRT.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgRT);
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

			if (newmessage.getFormatName().equals("EDD018001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD018001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD018001Message) newmessage;
				// showESD008004(msgRT);

				if (IsNotError) { // There are no errors
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
							+ "/servlet/datapro.eibs.products.JSEDD0180?SCREEN=1'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtStop", msgRT);
					ses.setAttribute("userPO", userPO);
					if (option == 1) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD0180_rt_stop_pay_det_new.jsp");
							callPage(LangPath + "EDD0180_rt_stop_pay_det_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDD0180_rt_stop_pay_det.jsp");
							callPage(LangPath + "EDD0180_rt_stop_pay_det.jsp", req, res);
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
	protected void procActionStopPayList(
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
		JBListRec stpList = null;

		stpList = (JBListRec) ses.getAttribute("stop");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}

			switch (option) {
				case 1 : // New
					int seq = 0;
					if (stpList.getNoResult()) {
						seq = 1;
					} else {
						stpList.setLastRow();
						seq = Integer.parseInt(stpList.getRecord(0)) + 1;
					}
					EDD018001Message msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
					userPO.setHeader19(seq + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_rt_stop_pay_det_new.jsp?seq=" + seq);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("rtStop", msgRT);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_rt_stop_pay.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_rt_stop_pay_det.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_rt_stop_pay.jsp?SEL=" + row);
					break;
				case 3 : // Delete
					boolean IsNotError = true;
					try {
						msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
						msgRT.setH01USERID(user.getH01USR());
						msgRT.setH01PROGRM("EDD0180");
						msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01SCRCOD("01");
						msgRT.setH01OPECOD("0009");
						try {
							msgRT.setE01STPACC(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPCCY(userPO.getCurrency());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPBNK(userPO.getHeader10());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPBRN(userPO.getHeader11());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPGLN(userPO.getHeader12());
						} catch (Exception e) {
						}
						try {
							stpList.setCurrentRow(row);
							msgRT.setE01STPSEQ(stpList.getRecord(0));
						} catch (Exception e) {
						}

						msgRT.send();
						msgRT.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);

							if (IsNotError) { // There are no errors
								procReqStopPayList(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx + LangPath + "EDD0180_rt_stop_pay.jsp?ROW=" + row);
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
					break;
				case 5 : // Clear
					userPO.setHeader20("DO_CLEAR");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_rt_stop_pay_clear.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_rt_stop_pay.jsp?SEL=" + row);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Send Initial data

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterStopPay(
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
			userPO.setOption("STOP_PAYMENT");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD0180_rt_enter_stop_pay.jsp");
			callPage(LangPath + "EDD0180_rt_enter_stop_pay.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqStopPayList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgList = null;
		JBListRec stpList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD0180");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				msgList.setE01STPACC(userPO.getIdentifier());
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
		int colNum = 20;
		try {
			stpList = new datapro.eibs.beans.JBListRec();
			stpList.init(colNum);
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
					flexLog("About to call Page: " + LangPath + "EDD0180_rt_enter_stop_pay.jsp");
					callPage(LangPath + "EDD0180_rt_enter_stop_pay.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("EDD018001")) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgList = (EDD018001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								userPO.setHeader2(msgList.getE01STPSEQ());
								userPO.setIdentifier(msgList.getE01STPACC());
								userPO.setCurrency(msgList.getE01STPCCY());
								userPO.setHeader1(msgList.getE01STPCUN());
								userPO.setHeader5(msgList.getE01CUSNA1());
								userPO.setHeader6(msgList.getE01STPPRO());
								userPO.setHeader10(msgList.getE01STPBNK());
								userPO.setHeader11(msgList.getE01STPBRN());
								userPO.setHeader12(msgList.getE01STPGLN());
								userPO.setHeader20("");
								userPO.setHeader21("");
							
							stpList.setShowNext(false);
							break;
						} else {
							
							if (firstTime) {
								firstTime = false;
								stpList.setFirstRec(Integer.parseInt(msgList.getE01STPSEQ()));
								userPO.setIdentifier(msgList.getE01STPACC());
								userPO.setCurrency(msgList.getE01STPCCY());
								userPO.setHeader1(msgList.getE01STPCUN());
								userPO.setHeader5(msgList.getE01CUSNA1());
								userPO.setHeader6(msgList.getE01STPPRO());
								userPO.setHeader10(msgList.getE01STPBNK());
								userPO.setHeader11(msgList.getE01STPBRN());
								userPO.setHeader12(msgList.getE01STPGLN());
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							
							// Quote List
							myRow[0] = msgList.getE01STPSEQ(); // Sequence
							myRow[1] = msgList.getE01STPFCK(); // From Check
							myRow[2] = msgList.getE01STPTCK(); // To Check
							myRow[3] = msgList.getE01STPDT1(); // Date 1
							myRow[4] = msgList.getE01STPDT2(); // Date 2
							myRow[5] = msgList.getE01STPDT3(); // Date 3
							myRow[6] = msgList.getE01STPTIM(); // Time
							myRow[7] = msgList.getE01STPAMT(); // Amount
							if (msgList.getE01STPRMK().length() >= 23) {
								myRow[8] = msgList.getE01STPRMK().substring(0,23); // Remarks
							}else {	
								myRow[8] = msgList.getE01STPRMK(); // Remarks
							}
							myRow[9] = msgList.getE01STPPRF(); // ACH Type
							myRow[10] = msgList.getE01STPCCF(); // Customer Charges
							myRow[11] = msgList.getE01STPPTS(); // Sub Account
							myRow[12] = msgList.getE01STPF04(); // 180 Days
							myRow[13] = msgList.getE01STPF02(); // Filler
							myRow[14] = msgList.getE01STPCK1(); // Date 4
							myRow[15] = msgList.getE01STPCK2(); // Date 5
							myRow[16] = msgList.getE01STPCK3(); // Date 6
							myRow[17] = msgList.getD01DSCCAU(); // Cause Description
							myRow[18] = msgList.getE01STPF05(); // Flag
							myRow[19] = msgList.getE01STPFLG(); // purge after 180

							stpList.addRow(myFlag, myRow);
							
							if (marker.equals("+")) {
								stpList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();

					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("stop", stpList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDD0180_rt_stop_pay.jsp");
						callPage(LangPath + "EDD0180_rt_stop_pay.jsp", req, res);

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

			int screen = R_ENTER;

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
					case R_SUSP :
						procReqStopPayList(mc, msgUser, req, res, session);
						break;
					case A_SUSP :
						procActionStopPayList(mc, msgUser, req, res, session);
						break;
					case A_SUSP_DET :
						procActionStopPayDet(mc, msgUser, req, res, session);
						break;
					case A_SUSP_ACLARAR :
						procActionStopPayClear(mc, msgUser, req, res, session);
						break;
					case R_OF_SUSP :
						procReqOFStopPayList(mc, msgUser, req, res, session);
						break;
					case A_OF_SUSP :
						procActionOFStopPayList(mc, msgUser, req, res, session);
						break;
					case A_OF_SUSP_DET :
						procActionOFStopPayDet(mc, msgUser, req, res, session);
						break;
					case A_OF_SUSP_ACLARAR :
						procActionOFStopPayClear(mc, msgUser, req, res, session);
						break;
					case A_CD_SUSP_DET :
						procActionCDStopPayDet(mc, msgUser, req, res, session);
						break;
					case R_CD_SUSP_INQ :
						procReqInqCDStopPay(mc, msgUser, req, res, session);
						break;
						// Entering options
					case R_ENTER :
						procReqEnterStopPay(msgUser, req, res, session);
						break;
					case A_ENTER :
						procActionEnterStopPay(mc, msgUser, req, res, session);
						break;
					case R_OF_ENTER :
						procReqEnterOF(mc, msgUser, req, res, session);
						break;
					case A_OF_ENTER :
						procActionEnterOFStopPay(mc, msgUser, req, res, session);
						break;
					case R_CD_ENTER :
						procReqEnterCD(msgUser, req, res, session);
						break;
					case A_CD_ENTER :
						procActionEnterCDStopPay(mc, msgUser, req, res, session);
						break;
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
	
	protected void procActionEnterOFStopPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			try {
				userPO.setIdentifier(req.getParameter("GLN"));
			} catch (Exception e) {
			}
			try {
				userPO.setBank(req.getParameter("BNK"));
			} catch (Exception e) {
			}
			try {
				userPO.setCurrency(req.getParameter("CCY"));
			} catch (Exception e) {
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			flexLog("Calling Request");
			procReqOFStopPayList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	} /**
	* This method was created in VisualAge.
	*/
	protected void procActionOFStopPayDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
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
			msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0180");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01FLGWK1("2");
			if (option == 1)
				msgRT.setH01OPECOD("0001");
			else
				msgRT.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgRT);
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

			if (newmessage.getFormatName().equals("EDD018001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD018001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD018001Message) newmessage;
				// showESD008004(msgRT);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
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
							+ "/servlet/datapro.eibs.products.JSEDD0180?SCREEN=7&FTY="
							+ req.getParameter("FTY")
							+ "&DSC="
							+ req.getParameter("DSC")
							+ "'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					//ses.setAttribute("userPO", userPO);
					if (option == 1) {
						ses.setAttribute("ofStop", msgRT);
						try {
							flexLog("About to call Page: " + LangPath + "EDD0180_of_stop_pay_det_new.jsp");
							callPage(LangPath + "EDD0180_of_stop_pay_det_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0180_of_stop_pay_det.jsp?ROW="
									+ req.getParameter("ROW"));
							res.sendRedirect(super.srctx + 
								LangPath + "EDD0180_of_stop_pay_det.jsp?ROW=" + req.getParameter("ROW"));
							//callPage(LangPath + "EDD0180_of_stop_pay_det.jsp", req, res);
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

	} /**
	* This method was created in VisualAge.
	*/
	protected void procActionOFStopPayList(
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
		JBListRec stpList = null;

		stpList = (JBListRec) ses.getAttribute("stop");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("CKROW"));
			} catch (Exception e) {
			}

			switch (option) {
				case 1 : // New
					int seq = 0;
					if (stpList.getNoResult()) {
						seq = 1;
					} else {
						stpList.setLastRow();
						seq = Integer.parseInt(stpList.getRecord(0)) + 1;
					}
					EDD018001Message msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
					userPO.setHeader19(seq + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_of_stop_pay_det_new.jsp?seq=" + seq);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("ofStop", msgRT);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_of_stop_pay.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_of_stop_pay_det.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_of_stop_pay.jsp?SEL=" + row);
					break;
				case 3 : // Delete
					boolean IsNotError = true;
					try {
						msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
						msgRT.setH01USERID(user.getH01USR());
						msgRT.setH01PROGRM("EDD0180");
						msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01SCRCOD("01");
						msgRT.setH01OPECOD("0009");
						msgRT.setH01FLGWK1("2");
						try {
							msgRT.setE01STPGLN(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPCCY(userPO.getCurrency());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01STPBNK(userPO.getBank());
						} catch (Exception e) {
						}
						try {
							stpList.setCurrentRow(row);
							msgRT.setE01STPBRN(stpList.getRecord(17));
						} catch (Exception e) {
						}

						try {
							stpList.setCurrentRow(row);
							msgRT.setE01STPFCK(stpList.getRecord(1));
						} catch (Exception e) {
						}
						try {
							stpList.setCurrentRow(row);
							msgRT.setE01STPSEQ(stpList.getRecord(0));
						} catch (Exception e) {
						}

						msgRT.send();
						msgRT.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);

							if (IsNotError) { // There are no errors
								procReqOFStopPayList(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx + LangPath + "EDD0180_of_stop_pay.jsp?ROW=" + row);
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
					break;
				case 5 : // Clear
					userPO.setHeader20("DO_CLEAR");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0180_of_stop_pay_clear.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + LangPath + "EDD0180_of_stop_pay.jsp?SEL=" + row);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Send Initial data

	} /**
	* This method was created in VisualAge.
	*/
	protected void procReqEnterOF(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EWD0115DSMessage msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		userPO.setOption("STOP_PAYMENT");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("userPO", userPO);

		int posi = 0;
		// Send Initial data
		try {
			msgList = (EWD0115DSMessage) mc.getMessageRecord("EWD0115DS");
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0115DS")) {

				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;

				while (true) {

					msgList = (EWD0115DSMessage) newmessage;

					marker = msgList.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myRow = new StringBuffer("<TR>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('"
							+ msgList.getEWDBNK()
							+ "','"
							+ msgList.getEWDGLN()
							+ "', '"
							+ msgList.getEWDCCY()
							+ "', '"
							+ msgList.getEWDFTY()
							+ "', '"
							+ msgList.getEWDDSC()
							+ "')\">"
							+ msgList.getEWDBNK()
							+ "</a></td>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('"
							+ msgList.getEWDBNK()
							+ "','"
							+ msgList.getEWDGLN()
							+ "', '"
							+ msgList.getEWDCCY()
							+ "', '"
							+ msgList.getEWDFTY()
							+ "', '"
							+ msgList.getEWDDSC()
							+ "')\">"
							+ msgList.getEWDCCY()
							+ "</a></td>");
						myRow.append("<td ALIGN=\"CENTER\"><a href=\"javascript:enter('"
							+ msgList.getEWDBNK()
							+ "','"
							+ msgList.getEWDGLN()
							+ "', '"
							+ msgList.getEWDCCY()
							+ "', '"
							+ msgList.getEWDFTY()
							+ "', '"
							+ msgList.getEWDDSC()
							+ "')\">"
							+ msgList.getEWDFTY()
							+ "</a></td>");
						myRow.append("<td ALIGN=\"LEFT\"><a href=\"javascript:enter('"
							+ msgList.getEWDBNK()
							+ "','"
							+ msgList.getEWDGLN()
							+ "', '"
							+ msgList.getEWDCCY()
							+ "', '"
							+ msgList.getEWDFTY()
							+ "', '"
							+ msgList.getEWDDSC()
							+ "')\">"
							+ msgList.getEWDDSC()
							+ "</a></td>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ewd0115Help", beanList);

				try {
					flexLog("About to call Page: " + LangPath + "EDD0180_of_enter_stop_pay.jsp");
					callPage(LangPath + "EDD0180_of_enter_stop_pay.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionStopPayClear(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
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
			msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0180");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0020");
			try {
				msgRT.setE01STPACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPCCY(userPO.getCurrency());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPBNK(userPO.getHeader10());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPBRN(userPO.getHeader11());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPGLN(userPO.getHeader12());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPSEQ(req.getParameter("E01STPSEQ"));
			} catch (Exception e) {
			}

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
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				if (IsNotError) { // There are no errors
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
							+ "/servlet/datapro.eibs.products.JSEDD0180?SCREEN=1'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "EDD0180_rt_stop_pay_clear.jsp");
						callPage(LangPath + "EDD0180_rt_stop_pay_clear.jsp", req, res);
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
	protected void procReqOFStopPayList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgList = null;
		JBListRec stpList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD0180");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setH01FLGWK1("2");
			try {
				msgList.setE01STPGLN(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgList.setE01STPBNK(userPO.getBank());
			} catch (Exception e) {
			}
			try {
				msgList.setE01STPCCY(userPO.getCurrency());
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
		try {
			int colNum = 19;
			stpList = new datapro.eibs.beans.JBListRec();
			stpList.init(colNum);
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
					flexLog("About to call Page: " + LangPath + "EDD0180_of_enter_stop_pay.jsp");
					callPage(LangPath + "EDD0180_of_enter_stop_pay.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("EDD018001")) {

					int colNum = 19;

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgList = (EDD018001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (firstTime) {
							firstTime = false;
							userPO.setBranch(msgList.getE01STPBRN());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}

						if (marker.equals("*")) {
							userPO.setHeader2(msgList.getE01STPSEQ());
							break;
						} else {
							// Quote List
							myRow[0] = msgList.getE01STPSEQ(); // Sequence
							myRow[1] = msgList.getE01STPFCK(); // From Check
							myRow[2] = msgList.getE01STPTCK(); // To Check
							myRow[3] = msgList.getE01STPDT1(); // Date 1
							myRow[4] = msgList.getE01STPDT2(); // Date 2
							myRow[5] = msgList.getE01STPDT3(); // Date 3
							myRow[6] = msgList.getE01STPTIM(); // Time
							myRow[7] = msgList.getE01STPAMT(); // Amount
							if (msgList.getE01STPRMK().length() >= 23) {
								myRow[8] = msgList.getE01STPRMK().substring(0,23); // Remarks
							}else {	
								myRow[8] = msgList.getE01STPRMK(); // Remarks
							}
							myRow[9] = msgList.getE01STPPRF(); // ACH Type
							myRow[10] = msgList.getE01STPCCF(); // Customer Charges
							myRow[11] = msgList.getE01STPPTS(); // Sub Account
							myRow[12] = msgList.getE01STPFLG(); // 180 Days
							myRow[13] = msgList.getE01STPF02(); // Filler
							myRow[14] = msgList.getE01STPCK1(); // Date 4
							myRow[15] = msgList.getE01STPCK2(); // Date 5
							myRow[16] = msgList.getE01STPCK3(); // Date 6
							myRow[17] = msgList.getE01STPBRN(); // Branch
							myRow[18] = msgList.getD01DSCCAU(); // Description

							stpList.addRow(myFlag, myRow);

						}

						newmessage = mc.receiveMessage();

					}
					try {
						userPO.setHeader11(req.getParameter("FTY"));
						userPO.setHeader12(req.getParameter("DSC"));
					} catch (Exception e) {

					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("stop", stpList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDD0180_of_stop_pay.jsp");
						callPage(LangPath + "EDD0180_of_stop_pay.jsp", req, res);

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

	

	protected void procActionCDStopPayDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgCD = null;
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

			switch (option) {
				case 2 :
					msgCD = (EDD018001Message) mc.getMessageRecord("EDD018001");
					msgCD.setH01USERID(user.getH01USR());
					msgCD.setH01PROGRM("EDD0180");
					msgCD.setH01TIMSYS(getTimeStamp());
					msgCD.setH01SCRCOD("01");
					msgCD.setH01FLGWK1("1");
					msgCD.setH01OPECOD("0003");
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
					break;
				case 3 :
					msgCD = (datapro.eibs.beans.EDD018001Message) ses.getAttribute("cdStop");
					msgCD.setH01OPECOD("0009");
					break;
				case 5 :
					msgCD = (datapro.eibs.beans.EDD018001Message) ses.getAttribute("cdStop");
					msgCD.setH01OPECOD("0020");
					break;
			}

			//msgCD.send();
			mc.sendMessage(msgCD);
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

		if (option == 2) {
			// Receive Data
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDD018001")) {
					try {
						msgCD = new datapro.eibs.beans.EDD018001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					msgCD = (EDD018001Message) newmessage;
					ses.setAttribute("cdStop", msgCD);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

		if (IsNotError) { // There are no errors
			try {
				flexLog("About to call Page: " + LangPath + "EDD0180_cd_enter_stop_pay.jsp");
				callPage(LangPath + "EDD0180_cd_enter_stop_pay.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else { // There are errors
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EDD0180_cd_stop_pay_det.jsp");
				callPage(LangPath + "EDD0180_cd_stop_pay_det.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}

	} /**
	* This method was created in VisualAge.
	*/

	protected void procActionEnterCDStopPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgCD = null;
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
			msgCD = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDD0180");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01FLGWK1("1");
			msgCD.setH01OPECOD("0002");

			try {
				msgCD.setE01STPACC(req.getParameter("E01STPACC"));
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

			if (newmessage.getFormatName().equals("EDD018001")) {
				try {
					msgCD = new datapro.eibs.beans.EDD018001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDD018001Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					ses.setAttribute("cdStop", msgCD);
					try {
						flexLog("About to call Page: " + LangPath + "EDD0180_cd_stop_pay_det.jsp");
						callPage(LangPath + "EDD0180_cd_stop_pay_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EDD0180_cd_enter_stop_pay_.jsp");
						callPage(LangPath + "EDD0180_cd_enter_stop_pay.jsp", req, res);
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
protected void procReqInqCDStopPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgCD = null;
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
			msgCD = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDD0180");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01FLGWK1("1");
			msgCD.setH01OPECOD("0004");

			try {
				msgCD.setE01STPACC(userPO.getIdentifier());
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

			if (newmessage.getFormatName().equals("EDD018001")) {
				try {
					msgCD = new datapro.eibs.beans.EDD018001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDD018001Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdStop", msgCD);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDD0180_cd_stop_pay_inq.jsp");
					callPage(LangPath + "EDD0180_cd_stop_pay_inq.jsp", req, res);
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
	protected void procActionOFStopPayClear(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
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
			msgRT = (EDD018001Message) mc.getMessageRecord("EDD018001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0180");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0020");
			msgRT.setH01FLGWK1("2");
			try {
				msgRT.setE01STPGLN(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPCCY(userPO.getCurrency());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPBNK(userPO.getBank());
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPBRN(req.getParameter("E01STPBRN"));
			} catch (Exception e) {
			}
			try {
				msgRT.setE01STPSEQ(req.getParameter("E01STPSEQ"));
			} catch (Exception e) {
			}

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
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

				if (IsNotError) { // There are no errors
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
							+ "/servlet/datapro.eibs.products.JSEDD0180?SCREEN=7&FTY="
							+ req.getParameter("FTY")
							+ "&DSC="
							+ req.getParameter("DSC")
							+ "'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0180_of_stop_pay_clear.jsp?ROW="
								+ req.getParameter("ROW"));
						res.sendRedirect(super.srctx + 
							LangPath + "EDD0180_of_stop_pay_clear.jsp?ROW=" + req.getParameter("ROW"));
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
	protected void procReqEnterCD(
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
			userPO.setOption("STOP_PAYMENT");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD0180_cd_enter_stop_pay.jsp");
			callPage(LangPath + "EDD0180_cd_enter_stop_pay.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}