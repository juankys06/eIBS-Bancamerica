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

import datapro.eibs.sockets.*;

public class JSELD0100 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_ENTER_INQUIRY = 100;
	protected static final int A_ENTER_INQUIRY = 200;
	
	protected static final int R_ACTIVITY_DETAILS = 300;
	
	protected static final int R_EXCEPTION_LIST = 500;
	protected static final int A_EXCEPTION_LIST_DETAILS = 600;
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSELD0100() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnterInquiry(
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ELD0100_rt_money_laundering.jsp");
			callPage(LangPath + "ELD0100_rt_money_laundering.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procRequestActivity(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELD010002Message msgInst = null;
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

		// Send Initial data
		try {
			msgInst = (ELD010002Message) mc.getMessageRecord("ELD010002");
			msgInst.setH02USERID(user.getH01USR());
			msgInst.setH02PROGRM("ELD010002");
			msgInst.setH02TIMSYS(getTimeStamp());
			msgInst.setH02SCRCOD("01");

			try {
				msgInst.setE02LDMACC(req.getParameter("ACCOUNT"));
			} catch (Exception e) {

			}

			try {
				msgInst.setE02LDEBDM(req.getParameter("MONTH"));
			} catch (Exception e) {

			}

			try {
				msgInst.setE02LDEBDY(req.getParameter("YEAR"));
			} catch (Exception e) {

			}

			msgInst.send();
			msgInst.destroy();
			flexLog("ELD010002 Message Sent");
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

			if (newmessage.getFormatName().equals("ELD010002")) {
				try {
					msgInst = new ELD010002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgInst = (ELD010002Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rtLaunder", msgInst);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ELD0100_rt_activity_details.jsp");
						callPage(
							LangPath + "ELD0100_rt_activity_details.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.products.JSELD0100?SCREEN=100");
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		String ACCOUNT = req.getParameter("ACCOUNT");
		String MONTH = req.getParameter("MONTH");
		String YEAR = req.getParameter("YEAR");

		switch (inptOPT) {
			case 1 : //Activity
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.products.JSELD0100?SCREEN=300"
						+ "&ACCOUNT="
						+ ACCOUNT
						+ "&MONTH="
						+ MONTH
						+ "&YEAR="
						+ YEAR
					);
				break;

			case 2 : //Exceptions
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.products.JSELD0100?SCREEN=500"
						+ "&ACCOUNT="
						+ ACCOUNT
						+ "&MONTH="
						+ MONTH
						+ "&YEAR="
						+ YEAR
					);
				break;

			default :
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.products.JSELD0100?SCREEN=300"
					+ "&ACCOUNT="
					+ ACCOUNT
					+ "&MONTH="
					+ MONTH
					+ "&YEAR="
					+ YEAR
				);
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
		ELD010001Message msgLaundry = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
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

			msgLaundry = (ELD010001Message) mc.getMessageRecord("ELD010001");
			msgLaundry.setH01USERID(user.getH01USR());
			msgLaundry.setH01PROGRM("EIE0000");
			msgLaundry.setH01TIMSYS(getTimeStamp());
			msgLaundry.setH01SCRCOD("01");

			try { // Search By Account
				msgLaundry.setE01LDMACC(req.getParameter("E01LDMACC"));
			} catch (Exception e) {
			}
			msgLaundry.send();
			msgLaundry.destroy();
			flexLog("ELD010001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			}

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELD010001")) {

				try {
					beanList = new JBList();

				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";

				int indexRow = 0;
				while (true) {

					msgLaundry = (ELD010001Message) newmessage;

					marker = msgLaundry.getE01LDEOPE();

					if (firstTime) {
						firstTime = false;
						userPO.setAccNum(msgLaundry.getE01LDMACC());
						userPO.setCusNum(msgLaundry.getE01LDMCUN());
						userPO.setCusName(msgLaundry.getE01CUSNA1());
						userPO.setCurrency(msgLaundry.getE01LDMCCY());
						userPO.setProdCode(msgLaundry.getE01LDMPRO());
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER><input type=\"radio\" name=\"index\" value=\""
								+ msgLaundry.getE01LDMACC()
								+ "\" "
								+ chk
								+ " onclick=\"getParams('"
								+ msgLaundry.getE01LDMACC()
								+ "', '"
								+ msgLaundry.getE01LDMHYY()
								+ "', '"
								+ msgLaundry.getE01LDMHMM()
								+ "')\"></TD>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgLaundry.getE01LDMHYY()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgLaundry.getE01LDMHMM()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgLaundry.getE01LDMNDEB()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgLaundry.getE01LDMMDEA())
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=CENTER>"
								+ msgLaundry.getE01LDMNCRE()
								+ "</td>");
						myRow.append(
							"<TD NOWRAP  ALIGN=RIGHT>"
								+ Util.fcolorCCY(msgLaundry.getE01LDMMCRA())
								+ "</td>");

						myRow.append("</TR>");

						beanList.addRow(myFlag, myRow.toString());
						indexRow++;
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ELD0100Help", beanList);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ELD0100_monthly_account_activity.jsp");
						callPage(
							LangPath + "ELD0100_monthly_account_activity.jsp",
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
								+ "ELD0100_rt_money_laundering.jsp");
						callPage(
							LangPath + "ELD0100_rt_money_laundering.jsp",
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


	protected void procReqExceptionsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELD010003Message msgList = null;
		ELEERRMessage msgError = null;
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

		// Send Initial data
		try {
			msgList = (ELD010003Message) mc.getMessageRecord("ELD010003");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ELD010003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");

			try {
				msgList.setE03LDEACC(req.getParameter("ACCOUNT"));

			} catch (Exception e) {

			}

			try {
				msgList.setE03LDEBDM(req.getParameter("MONTH"));

			} catch (Exception e) {

			}

			try {
				msgList.setE03LDEBDY(req.getParameter("YEAR"));

				} catch (Exception e) {

				}

			msgList.send();
			msgList.destroy();
			flexLog("ELD010003 Message Sent");
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
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELD010003")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (ELD010003Message) newmessage;

					marker = msgList.getE03LDEOPE();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
//						userPO.setAccNum(msgList.getE03LDEACC());
//						userPO.setCusNum(msgList.getE03LDECUN());
//						userPO.setCusName(msgList.getE03CUSNA1());
//						userPO.setHeader1(msgList.getE03LDEBDM());
//						userPO.setHeader2(msgList.getE03LDEBDY());


					} else {
						chk = "";
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						beanList.addRow(msgList);

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("ELD010003Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ELD0100_rt_exceptions_list.jsp");
					callPage(
						LangPath + "ELD0100_rt_exceptions_list.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}


	protected void procReqExceptionDetails(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELD010003Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("ELD010003Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgDoc = (ELD010003Message) bl.getRecord();

			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("excDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ELD0100_rt_exceptions_details.jsp");
				callPage(
					LangPath + "ELD0100_rt_exceptions_details.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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

			int screen = A_POSITION;

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

					switch (screen) {

						//Request

						case R_ENTER_INQUIRY :
							procReqEnterInquiry(msgUser, req, res, session);
							break;
						
						case R_ACTIVITY_DETAILS :
							procRequestActivity(mc,msgUser, req, res, session);
							break;	
						
						case R_EXCEPTION_LIST :
						procReqExceptionsList(mc,msgUser, req, res, session);
							break;
						
							// Action

						case A_ENTER_INQUIRY :
							procActionEnterInquiry(mc,msgUser,req,res,session);
							break;
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_EXCEPTION_LIST_DETAILS :
							procReqExceptionDetails(mc, msgUser, req, res, session);							
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

}
